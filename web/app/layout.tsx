import type { Metadata, Viewport } from "next";
import { UpdateWatcher } from "@/components/shell/UpdateWatcher";
import "./globals.css";
import "./premium.css";
import "./tv-guide.css";

export const metadata: Metadata = {
  title: "ARVIO",
  description: "ARVIO media hub for web, iPad, desktop, and TV browsers",
  robots: {
    index: false,
    follow: false,
    noarchive: true,
    googleBot: {
      index: false,
      follow: false,
      noarchive: true,
      noimageindex: true
    }
  },
  manifest: "/manifest.webmanifest",
  icons: {
    icon: [
      { url: "/favicon-32.png", sizes: "32x32", type: "image/png" },
      { url: "/arvio-icon-192.png", sizes: "192x192", type: "image/png" },
      { url: "/arvio-icon-512.png", sizes: "512x512", type: "image/png" }
    ],
    // A brand-new FILENAME (not a ?v= query, which iOS Safari's icon cache can
    // ignore) forces the touch icon to be re-fetched — Safari cached a miss for
    // the old path and kept showing the "A" fallback on Add to Home Screen.
    apple: [{ url: "/apple-touch-icon-v3.png", sizes: "180x180", type: "image/png" }]
  },
  appleWebApp: {
    capable: true,
    title: "ARVIO",
    statusBarStyle: "black-translucent"
  }
};

export const viewport: Viewport = {
  themeColor: "#000000",
  colorScheme: "dark",
  width: "device-width",
  initialScale: 1,
  maximumScale: 1,
  // Cover the iOS status-bar area — without this the page top bleeds through
  // above fullscreen surfaces like the player.
  viewportFit: "cover"
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <head>
        {/* iOS reads these raw links most reliably when adding to the home
            screen. `precomposed` is the legacy fallback older iOS honors; both
            carry the version bust. */}
        <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon-v3.png" />
        <link rel="apple-touch-icon-precomposed" sizes="180x180" href="/apple-touch-icon-v3.png" />
      </head>
      <body>
        <UpdateWatcher />
        {children}
      </body>
    </html>
  );
}
