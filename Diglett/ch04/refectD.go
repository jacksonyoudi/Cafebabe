package ch04

import (
	"fmt"
	"reflect"
)

type X struct {
	A1 int
	B1 float64
	C1 bool
}

type Y struct {
	A2 int
	B2 int
	C2 float64
	D2 string
}

func reflectTest() {
	x1 := X{100, 3.14, true}
	//y1 := Y{1, 2, 1.5, "hello"}
	rx1 := reflect.ValueOf(&x1).Elem()

	fields := rx1.NumField()

	typ := rx1.Type()
	for i := 0; i < fields; i++ {
		fmt.Println(
			typ.Field(i).Name,
			typ.Field(i).Type)
	}

}