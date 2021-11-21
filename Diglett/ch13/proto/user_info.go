package user

import (
	"google.golang.org/protobuf/proto"
	"log"
)

func testuser() {
	u := &UserInfo{
		Message: *proto.String("testInfo"),
		Length:  *proto.Int32(10),
	}

	data, err := proto.Marshal(u)

	if err != nil {
		log.Fatalln("marshaling error:", err)
	}

	newInfo := &UserInfo{}
	err = proto.Unmarshal(data, newInfo)

	if err != nil {
		log.Fatalln("unmarshaling error:", err)
	}

	log.Println(newInfo.GetMessage())
}
