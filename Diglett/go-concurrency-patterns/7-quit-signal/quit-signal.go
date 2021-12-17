package main

import (
	"fmt"
	"math/rand"
	"time"
)

func boring(msg string, quit chan string) <-chan string {
	c := make(chan string)
	go func() {
		for i := 0; ; i++ {
			select {
			case c <- fmt.Sprintf("%s %d", msg, i):
				// do someting
			case <-quit:
				fmt.Println("clean up")
				quit <- "see you"
				return
			}
			time.Sleep(time.Duration(rand.Intn(1e3)) * time.Millisecond)
		}
	}()

	return c
}


// 通过 chan将 退出信号 传递到 boring里面
func main() {
	quit := make(chan string)
	c := boring("joe", quit)

	// 读取3次
	for i := 3; i >= 0; i-- {
		fmt.Println(<-c)
	}
	quit <- "Bye"
	// boring 会停止
	fmt.Println("Joe say", <-quit)

	// 退出， boring也会退出

}
