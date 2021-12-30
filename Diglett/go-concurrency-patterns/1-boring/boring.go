package main

import (
	"fmt"
	"math/rand"
	"time"
)

func boring(msg string) {
	// 死循环了
	for i := 0; ; i++ {
		fmt.Println(msg, i)
		time.Sleep(time.Duration(rand.Intn(1e3)) * time.Millisecond)
	}
}


// 主进程退出，协程也退出
func main() {
	go boring("boring")

	fmt.Println("I'm listening")
	time.Sleep(2 * time.Second)
	fmt.Println("You're boring. I'm leaving")
}
