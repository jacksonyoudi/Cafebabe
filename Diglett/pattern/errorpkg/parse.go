package errorpkg

import (
	"encoding/binary"
	"github.com/pkg/errors"
	"io"
)

type Point struct {
	Longitude     int64
	Latitude      int64
	Distance      int64
	ElevationGain int64
	ElevationLoss int64
}

// 只是将err变成一个
func parse(r io.Reader) (*Point, error) {
	var p Point
	var err error
	read := func(data interface{}) {
		if err != nil {
			return
		}

		err = binary.Read(r, binary.BigEndian, data)

	}

	read(&p.Distance)
	read(&p.ElevationGain)
	read(&p.ElevationLoss)

	if err != nil {
		return &p, errors.Wrap(err, "errr")
	}

	return &p, nil
}

type Reader struct {
	r   io.Reader
	err error
}

func (r *Reader) read(data interface{}) {
	if r.err == nil {
		r.err = binary.Read(r.r, binary.BigEndian, data)
	}
}


