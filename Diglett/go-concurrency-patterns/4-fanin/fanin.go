package main

import (
	"fmt"
	"math/rand"
	"time"
)

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

func fanIn(c1, c2 <-chan string) <-chan string {
	c := make(chan string)
	go func() {
		for {
			v1 := <-c1
			c <- v1
		}
	}()

	go func() {
		c <- <-c2
	}()
	return c
}

func fanInSimple(cs ...<-chan string) <-chan string {
	c := make(chan string)
	for _, ci := range cs {
		go func(cv <-chan string) {
			for {
				c <- <-cv
			}
		}(ci)
	}
	return c
}




// 生产者 消费者模型
//
func main() {
	// 异步
	c := fanInSimple(boring("Joe"), boring("Ahn"))
	//
	for i := 0; i < 5; i++ {
		fmt.Println(<-c)
	}
	fmt.Println("you're both boring, i'm leaving")
}
