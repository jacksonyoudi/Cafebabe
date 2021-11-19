package ch08

import (
	"fmt"
	"sync"
)

var m sync.Map

func test_mapper() {
	m.Store(1, "one")
	m.Store(2, "two")

	v, ok := m.LoadOrStore(3, "three")
	fmt.Println(v, ok)

	load, o := m.Load(1)
	fmt.Println(load, o)

	f := func(k, v interface{}) bool {
		fmt.Println(k, v)
		return true
	}

	m.Range(f)
	m.Delete(2)
	fmt.Println(m.Load(2))

}
