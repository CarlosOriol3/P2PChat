package main

import (
	"net"
	"time"
)

func main() {
	conn, err := net.Dial("tcp", "127.0.0.1:10000")
	if err != nil {
		panic(err)
	}
	time.Sleep(5 * time.Second)
	conn.Close()
}
