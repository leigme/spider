package cmd

/*

Copyright © 2023 leig <leigme@gmail.com>

*/
import (
	"github.com/leigme/loki/app"
	"github.com/leigme/loki/file"
	"github.com/spf13/cobra"
	"golang.org/x/exp/slog"
	"log"
	"os"
	"path/filepath"
)

// rootCmd represents the base command when called without any subcommands
var rootCmd = &cobra.Command{
	Use:   "spider",
	Short: "A brief description of your application",
	Long: `A longer description that spans multiple lines and likely contains
examples and usage of using your application. For example:

Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	// Uncomment the following line if your bare application
	// has an action associated with it:
	// Run: func(cmd *cobra.Command, args []string) { },
}

// Execute adds all child commands to the root command and sets flags appropriately.
// This is called by main.main(). It only needs to happen once to the rootCmd.
func Execute() {
	err := rootCmd.Execute()
	if err != nil {
		os.Exit(1)
	}
}

func init() {
	err := file.CreateDir(app.WorkDir())
	if err != nil {
		log.Fatal(err)
	}
	f, err := os.Create(filepath.Join(app.WorkDir(), app.Name()+".log"))
	if err != nil {
		f = os.Stderr
	}
	slog.SetDefault(slog.New(slog.NewTextHandler(f)))
	rootCmd.Flags().BoolP("toggle", "t", false, "Help message for toggle")
}
