package ch08

import "sync"

var (
	ready     = false
	singerNum = 3
)

func Sing(singerId int, c *sync.Cond) {
	c.L.Lock()
	defer c.L.Unlock()
	for !ready {
		// 等待信号
		c.Wait()
	}
	ready = false
}

func test() {
	cond := sync.NewCond(&sync.Mutex{})
	for i := 0; i < singerNum; i++ {
		go Sing(i, cond)
	}

	for i := 0; i < singerNum; i++ {
		ready = true
		// 发送信号
		cond.Signal()
	}
}
