package cmd

import (
	"bufio"
	"fmt"
	loki "github.com/leigme/loki/cobra"
	"github.com/leigme/spider/parse"
	"github.com/spf13/cobra"
	"golang.org/x/exp/slog"
	"io/fs"
	"os"
)

func init() {
	loki.Add(rootCmd, &get{})
}

type get struct{}

func (g get) Execute() loki.Exec {
	return func(cmd *cobra.Command, args []string) {
		if len(args) < 1 {
			slog.Error("url is nil")
			return
		}
		r := parse.New(args[0]).Parse(args[0])
		if len(args) == 2 {
			if out, err := os.OpenFile(args[1], os.O_CREATE|os.O_RDWR, fs.ModePerm); err == nil {
				defer out.Close()
				w := bufio.NewWriter(out)
				for k, v := range r {
					w.WriteString(fmt.Sprintf("%s: %s\n", k, v))
				}
				w.Flush()
			}
		} else {
			for _, v := range r {
				slog.Info("%s\n", v)
			}
		}
	}
}
