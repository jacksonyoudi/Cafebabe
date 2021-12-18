package main

import (
	"math/rand"
	"time"
)

type Result string

type Search func(query string) Result

var (
	Web   = fakeSearch("web")
	Image = fakeSearch("image")
	video = fakeSearch("video")
)

func fakeSearch(kind string) Search {
	return func(query string) Result {
		time.Sleep(time.Duration(rand.Intn(100)) * time.Microsecond)
	}
}

func main() {

}
