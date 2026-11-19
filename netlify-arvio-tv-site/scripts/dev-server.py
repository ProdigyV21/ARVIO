#!/usr/bin/env python3
import http.server
import os
import sys
import socketserver

PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 8080
DIRECTORY = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

class NetlifyDevHandler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=DIRECTORY, **kwargs)

    def do_GET(self):
        path = self.translate_path(self.path)
        if not os.path.exists(path):
            self.send_response(404)
            self.send_header("Content-type", "text/html; charset=utf-8")
            not_found_file = os.path.join(DIRECTORY, "404.html")
            if os.path.exists(not_found_file):
                with open(not_found_file, "rb") as f:
                    content = f.read()
                self.send_header("Content-Length", str(len(content)))
                self.end_headers()
                self.wfile.write(content)
                return
        super().do_GET()

class ReusableTCPServer(socketserver.TCPServer):
    allow_reuse_address = True

if __name__ == "__main__":
    with ReusableTCPServer(("", PORT), NetlifyDevHandler) as httpd:
        print(f"Serving {DIRECTORY} on port {PORT} with 404.html fallback...")
        httpd.serve_forever()
