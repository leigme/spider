package cmd

import (
	loki "github.com/leigme/loki/cobra"
	"github.com/leigme/spider/parse"
	"github.com/spf13/cobra"
	"golang.org/x/exp/slog"
)

func init() {
	loki.Add(rootCmd, &get{})
}

type get struct{}

func (g get) Execute() loki.Exec {
	return func(cmd *cobra.Command, args []string) {
		if len(args) != 1 {
			slog.Error("url is nil")
			return
		}
		r := parse.New(args[0]).Parse(args[0])
		for k, v := range r {
			slog.Info("%s: %s\n", k, v)
		}
	}
}
