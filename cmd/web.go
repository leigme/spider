package cmd

import (
	loki "github.com/leigme/loki/cobra"
	"github.com/leigme/spider/parse"
	"github.com/spf13/cobra"
	"golang.org/x/exp/slog"
	"net/http"
	"strings"
)

func init() {
	loki.Add(rootCmd, &web{})
}

type web struct {
}

func (w web) Execute() loki.Exec {
	return func(cmd *cobra.Command, args []string) {
		r := http.DefaultServeMux
		r.HandleFunc("/running", healthHandler)
		r.HandleFunc("/get", getHandler)
		err := http.ListenAndServe(":9090", nil)
		if err != nil {
			slog.Error(err.Error())
		}
	}
}

func healthHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	_, err := w.Write([]byte("ok"))
	if err != nil {
		slog.Error(err.Error())
	}
}

func getHandler(w http.ResponseWriter, r *http.Request) {
	url := r.URL.Query().Get("url")
	if strings.EqualFold(url, "") {
		w.WriteHeader(http.StatusOK)
		_, err := w.Write([]byte("url is nil"))
		if err != nil {
			slog.Error(err.Error())
			return
		}
		return
	}
	m := parse.New(url).Parse(url)
	for k, v := range m {
		slog.Info("%s: %s", k, v)
	}
}
