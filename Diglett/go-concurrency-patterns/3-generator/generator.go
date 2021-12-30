package main

import (
	"fmt"
	"math/rand"
	"time"
)

// 异步操作，就是返回 future
func boring(msg string) <-chan string {
	c := make(chan string)

	go func() {
		for i := 0; i < 10; i++ {
			c <- fmt.Sprintf("%s %d", msg, i)
			time.Sleep(time.Duration(rand.Intn(1e3)) * time.Millisecond)
		}
		close(c)
	}()

	return c
}

func main() {
	joe := boring("joe")
	ahn := boring("ahn")

	// This loop yields 2 channels in sequence
	for i := 0; i < 10; i++ {
		// 会阻塞
		fmt.Println(<-joe)
		fmt.Println(<-ahn)
	}

	//for msg := range ahn {
	//	fmt.Println(msg)
	//}

	fmt.Println("You're both boring. I'm leaving")

}
