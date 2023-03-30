package parse

import (
	"fmt"
	"github.com/axgle/mahonia"
	"github.com/gocolly/colly"
	"log"
	"strings"
)

func New6vhao() Parser {
	return &parser{
		c: colly.NewCollector(
			colly.UserAgent(UserAgent),
			colly.MaxDepth(2),
		),
	}
}

type parser struct {
	c *colly.Collector
}

func (p *parser) Parse(url string) (result map[string]string) {
	result = make(map[string]string, 0)
	p.c.OnHTML("meta[http-equiv]", func(element *colly.HTMLElement) {
		if strings.Contains(element.Attr("content"), "gb") {
			defaultEncode = "gbk"
		}
	})
	p.c.OnHTML(
		"tbody", func(e *colly.HTMLElement) {
			e.ForEach("tr", func(i int, item *colly.HTMLElement) {
				href := item.ChildAttr("a[href]", "href")
				title := item.ChildText("td")
				title = mahonia.NewDecoder(defaultEncode).ConvertString(title)
				if strings.Contains(href, "magnet:?") || strings.Contains(href, "thunder://") {
					result[title] = href
				}
			})
		},
	)
	p.c.OnRequest(func(r *colly.Request) {
		fmt.Println("Visiting", r.URL)
	})
	err := p.c.Visit(url)
	if err != nil {
		log.Fatal(err)
	}
	return result
}
