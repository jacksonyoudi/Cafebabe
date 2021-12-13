package functional_options

import (
	"crypto/tls"
	"time"
)

type Config struct {
	Protocol string
	Timeout  time.Duration
	MaxConns int
	TLS      *tls.Config
}

type Server struct {
	Addr string
	Port int
	Conf *Config
}

type ServerBuilder struct {
	Server
}


func (sb *ServerBuilder) Create(addr string, port int) *ServerBuilder {
	sb.Server.Addr = addr
	sb.Server.Port = port
	return  sb
}




func (sb *ServerBuilder) WithProtocol(protocol string) *ServerBuilder {
	sb.Server.Conf.Protocol = protocol
	return sb
}

func (sb *ServerBuilder) WithMaxConn( maxconn int) *ServerBuilder {
	sb.Server.Conf.MaxConns = maxconn
	return sb
}

func (sb *ServerBuilder) WithTimeOut( timeout time.Duration) *ServerBuilder {
	sb.Server.Conf.Timeout = timeout
	return sb
}

func (sb *ServerBuilder) WithTLS( tls *tls.Config) *ServerBuilder {
	sb.Server.Conf.TLS = tls
	return sb
}

func (sb *ServerBuilder) Build() (Server) {
	return  sb.Server
}