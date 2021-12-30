package main

import (
	"fmt"
	"math/rand"
	"time"
)

// c 是chan类型，string
func boring(msg string, c chan string) {
	for i := 0; ; i++ {
		c <- fmt.Sprintf("%s %d", msg, i)
		time.Sleep(time.Duration(rand.Intn(1e3)) * time.Millisecond)
	}
}

func main() {
	// 只有一个容量
	c := make(chan string)

	go boring("boring", c)

	for i := 0; i < 5; i++ {
		// 会阻塞
		fmt.Println(<-c)
	}

	fmt.Println("You're boring. I'm leaving")
}
