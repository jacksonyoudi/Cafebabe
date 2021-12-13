package runner

import (
	"errors"
	"os"
	"os/signal"
	"time"
)

//Runner 在给定的超时时间内执行一组任务
// 并且在操作系统发送中断信号时结束这些任务
type Runner struct {
	// 从操作系统发送信号
	interrupt chan os.Signal

	// 报告处理任务完成
	complete chan error

	// 报告处理任务已经超时
	timeout <-chan time.Time

	tasks []func(id int)
}

// 统一错误处理

var ErrTimeOut = errors.New("received timeout")
var ErrInterrupt = errors.New("received interrupt")

func New(d time.Duration) *Runner {
	return &Runner{
		interrupt: make(chan os.Signal, 1),
		complete:  make(chan error),
		//会在另一线程经过时间段 d 后向返回值发送当时的时间。
		timeout: time.After(d),
	}
}

func (r *Runner) Add(tasks ...func(id int)) {
	r.tasks = append(r.tasks, tasks...)
}

func (r *Runner) Start() error {
	//监控所有的中断信号
	signal.Notify(r.interrupt, os.Interrupt)

	go func() {
		r.complete <- r.run()
	}()

	select {
	case err := <-r.complete:
		return err
	case <-r.timeout:
		return ErrInterrupt
	}
}

func (r *Runner) run() error {
	for id, task := range r.tasks {
		if r.gotInterrupt() {
			return ErrInterrupt
		}
		task(id)
	}
	return nil
}

func (r *Runner) gotInterrupt() bool {
	select {
	case <-r.interrupt:
		signal.Stop(r.interrupt)
		return true
	default:
		return false
	}
}



func run_demo() {

}