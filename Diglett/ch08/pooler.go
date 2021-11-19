package ch08

import (
	"sync"
)

var byteSlicePool = sync.Pool{
	New: func() interface{} {
		b := make([]byte, 1024)
		return &b
	},
}

func test_pool() {
	//t1 := time.Now().Unix()

	for i := 0; i < 100000000; i++ {
		bytes := make([]byte, 1024)
		_ = bytes
	}

	//t2 := time.Now().Unix()
	for i := 0; i < 100000000; i++ {
		i2 := byteSlicePool.Get().(*[]byte)
		_ = i2
		byteSlicePool.Put(i2)
	}
}
