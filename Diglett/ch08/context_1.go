package ch08

import (
	"context"
	"fmt"
	"time"
)

func testWCancel(t int) {
	ctx := context.Background()
	ctx, cancel := context.WithCancel(ctx)

	defer cancel()
	go func() {
		time.Sleep(time.Second * 3)
		cancel()
	}()

	select {
	case <-ctx.Done():
		fmt.Println("testWCanel.Done", ctx.Err())
	case e := <-time.After(time.Duration(t) * time.Second):
		fmt.Println("testWcancel:", e)
	}
	return
}

func testWDeadLine(t int) {

	ctx := context.Background()
	dl := time.Now().Add(time.Duration(t) * time.Second)

	ctx, cancel := context.WithDeadline(ctx, dl)
	defer cancel()

	go func() {
		time.Sleep(3 * time.Second)
		cancel()
	}()

	select {
	case <-ctx.Done():
		fmt.Println("err", ctx.Err())
	case e := <-time.After(time.Duration(t) * time.Second):
		fmt.Println("testWDealline", e)
	}

	return
}

func testWTimeout(t int) {
	ctx := context.Background()
	ctx, cancel := context.WithTimeout(ctx, time.Duration(t)*time.Second)
	defer cancel()

	go func() {
		time.Sleep(3 * time.Second)
		cancel()
	}()

	select {
	case <-ctx.Done():
		fmt.Println("testWtime.Done")
	case e := <-time.After(time.Duration(t) * time.Second):
		fmt.Println("testWtimeOut:", e)
	}

}
