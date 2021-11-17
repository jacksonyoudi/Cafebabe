package ch04

func Square(a int) int {
	return a * a
}

var square = func(a int) int {
	return a * a
}

func name() {
	square(10)
}

func double() func() int {
	var r int
	return func() int {
		r++
		return r * 2
	}
}
