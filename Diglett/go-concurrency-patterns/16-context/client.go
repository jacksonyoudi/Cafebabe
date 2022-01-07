package _6_context

import (
	"context"
	"io"
	"log"
	"net/http"
	"os"
)

func client() {
	ctx := context.Background()

	req, err := http.NewRequest("GET", "https://google.com", nil)
	if err != nil {
		log.Fatal(err)
	}
	req = req.WithContext(ctx)

	result, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Fatal(err)
	}
	defer result.Body.Close()
	_, _ = io.Copy(os.Stdout, result.Body)

}
