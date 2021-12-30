package main

import (
	"fmt"
	"time"
)

type Ball struct {
	hits int
}

// 拿出来， 加1, 再写回去
func player(name string, table chan *Ball) {
	for {
		ball := <-table
		ball.hits++
		fmt.Println(name, ball.hits)
		time.Sleep(100 * time.Millisecond)
		table <- ball
	}
}

func main() {
	// chan是线程安全的
	table := make(chan *Ball)
	go player("ping", table)
	go player("pong", table)
	table <- new(Ball)
	<-table
	panic("show me the stack")

}
