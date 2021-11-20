package ch08

import (
	"fmt"
	"time"
)

func worker(id int, jobs <-chan int, results chan<- int) {
	for job := range jobs {
		fmt.Printf("worker(%d) start to do job(%\n)", id, job)
		time.Sleep(time.Second)
		fmt.Println("worker(%d) finish to do job(%\n)", id, job)
		results <- job * job
	}
}

func workpool() {
	jobs := make(chan int, 100)
	results := make(chan int, 100)

	for id := 0; id < 3; id++ {
		go worker(id, jobs, results)
	}
	for job :=1; job <= 5; job++ {
		
	}


}
