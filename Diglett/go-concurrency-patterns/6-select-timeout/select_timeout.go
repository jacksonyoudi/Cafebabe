package main

import (
	"fmt"
	"math/rand"
	"time"
)

func boring(msg string) <-chan string {
	c := make(chan string)
	go func() {
		for i := 0; ; i++ {
			c <- fmt.Sprintf("%s %d", msg, i)
			time.Sleep(time.Duration(rand.Intn(1500)) * time.Millisecond)
		}
	}()
	return c
}

func main() {
	c := boring("joe")
	after := time.After(5 * time.Second)

	for {
		select {
		case s := <-c:
			fmt.Println(s)
		case <-after:
			fmt.Println("You talk too much.")
			// 退出
			return
		}
	}

}
