package cmd

import (
	loki "github.com/leigme/loki/cobra"
	"github.com/leigme/spider/parse"
	"github.com/spf13/cobra"
	"log"
)

func init() {
	loki.Add(rootCmd, &get{})
}

type get struct{}

func (g get) Execute() loki.Exec {
	return func(cmd *cobra.Command, args []string) {
		if len(args) != 1 {
			log.Fatalln("args must be url")
		}
		r := parse.New(args[0]).Parse(args[0])
		for k, v := range r {
			log.Printf("%s: %s\n", k, v)
		}
	}
}
