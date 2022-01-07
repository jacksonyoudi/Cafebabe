package main

import "time"

type Item struct {
	Tile    string
	Channel string
	GUID    string
}

// 定义接口
type Fetcher interface {
	Fetch() (item []*Item, next time.Time, err error)
}

type Subscription interface {
	Updates() <-chan *Item
	Close() error
}

type sub struct {
	fetcher Fetcher
	updates chan *Item
	closing chan chan error
}

func (s *sub) Updates() <-chan *Item {
	return s.updates
}

func (s *sub) Close() error {
	errc := make(chan error)
	s.closing <- errc
	return <-errc
}

func Subscribe(fetcher Fetcher) Subscription {
	s := &sub{
		fetcher: fetcher,
		updates: make(chan *Item),
		closing: make(chan chan error),
	}
	return s
}
