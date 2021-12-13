package functional_options

import (
	"crypto/tls"
	"time"
)

type Option func(*Server)



func Protocol(p string) Option {
	return func(s *Server) {
		s.Conf.Protocol = p
	}
}
func Timeout(timeout time.Duration) Option {
	return func(s *Server) {
		s.Conf.Timeout = timeout
	}
}
func MaxConns(maxconns int) Option {
	return func(s *Server) {
		s.Conf.MaxConns = maxconns
	}
}
func TLS(tls *tls.Config) Option {
	return func(s *Server) {
		s.Conf.TLS = tls
	}
}



func NewServer(addr string, port int, options ...func(*Server)) (*Server, error) {

	srv := Server{
		Addr:     addr,
		Port:     port,
		//Protocol: "tcp",
		//Timeout:  30 * time.Second,
		//MaxConns: 1000,
		//TLS:      nil,
	}
	for _, option := range options {
		option(&srv)
	}
	//...
	return &srv, nil
}

func t() {

	//_, _ := NewServer("localhost", 1024)
	//s2, _ := NewServer("localhost", 2048, Protocol("udp"))
	//s3, _ := NewServer("0.0.0.0", 8080, Timeout(300*time.Second), MaxConns(1000))
}