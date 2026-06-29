import type { Metadata, Viewport } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "ARVIO",
  description: "ARVIO media hub for web, iPad, desktop, and TV browsers",
  manifest: "/manifest.webmanifest"
};

export const viewport: Viewport = {
  themeColor: "#02070c",
  colorScheme: "dark",
  width: "device-width",
  initialScale: 1,
  maximumScale: 1
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
