package main

import (
	"fmt"
	"math/rand"
	"time"
)

type Message struct {
	str  string
	wait chan bool
}

func fanIn(inputs ...<-chan *Message) <-chan *Message {
	c := make(chan *Message)
	for _, i := range inputs {
		go func(m <-chan *Message) {
			for {
				c <- <-m
			}
		}(i)
	}
	return c
}

func boring(msg string) <-chan *Message {
	c := make(chan *Message)
	waitForIt := make(chan bool)

	go func() {
		for i := 0; ; i++ {
			c <- &Message{
				str:  fmt.Sprintf("%s %d", msg, i),
				wait: waitForIt,
			}
			time.Sleep(time.Duration(rand.Intn(1e3)) * time.Millisecond)
			// 阻塞
			<-waitForIt
		}
	}()
	return c
}

func main() {
	c := fanIn(boring("Joe"), boring("Ahn"))

	for i := 0; i < 5; i++ {
		msg1 := <-c // wait to receive message
		fmt.Println(msg1.str)
		msg2 := <-c
		fmt.Println(msg2.str)

		msg1.wait <- true // main goroutine allows the boring goroutine to send next value to message channel.
		msg2.wait <- true
	}
	fmt.Println("You're both boring. I'm leaving")

}


// main: goroutine                                          boring: goroutine
//    |                                                           |
//    |                                                           |
// wait for receiving msg from channel c                    c <- Message{} // send message
//   <-c                                                          |
//    |                                                           |
//    |                                                     <-waitForIt // wait for wake up signal
// send value to channel                                          |
// hey, boring. You can send next value to me                     |
//   wait <-true                                                  |
///                            REPEAT THE PROCESS