package parse

import (
	"log"
	httpurl "net/url"
)

const UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36"

var (
	defaultEncode = "utf8"
	parsers       = map[string]Parser{
		"www.6vhao.net":  New6vhao(),
		"www.6vgood.net": New6vhao(),
	}
)

type Parser interface {
	Parse(url string) (result map[string]string)
}

func New(url string) Parser {
	u, err := httpurl.Parse(url)
	if err != nil {
		log.Fatal(err)
	}
	return parsers[u.Host]
}
