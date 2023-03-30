package cmd

import (
	loki "github.com/leigme/loki/cobra"
	"github.com/spf13/cobra"
	"net/http"
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
		http.ListenAndServe(":9090", nil)
	}
}

func healthHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	w.Write([]byte("ok"))
}
