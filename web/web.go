package web

import (
  "github.com/gin-gonic/gin"
  "http/net"
)

type Web struct {}

type Running struct {}

func (r *Running) Handle(w http.Writer, r *http.Reader) {}
