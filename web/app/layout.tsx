import type { Metadata, Viewport } from "next";
import Script from "next/script";
import { UpdateWatcher } from "@/components/shell/UpdateWatcher";
import { DEVICE_CLASS_SCRIPT } from "@/lib/deviceClass";
// Shared sheets only: tokens, resets, shell, top nav, cards, rails, dialogs.
// Screen sheets are imported by their screen components (code-split with them).
import "./globals.css";
import "./source-setup.css";
import "./app-foundations.css";

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
      // Browsers and crawlers still request /favicon.ico directly.
      { url: "/favicon.ico", sizes: "16x16 32x32 48x48 64x64", type: "image/x-icon" },
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
  // No maximum scale: pinch-zoom must stay available (WCAG 1.4.4). iOS's
  // focus-zoom on small inputs is prevented by 16px form controls on touch
  // devices instead (app/app-foundations.css).
  // Cover the iOS status-bar area — without this the page top bleeds through
  // above fullscreen surfaces like the player.
  viewportFit: "cover"
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en" suppressHydrationWarning>
      <head>
        {/* Device classes (is-tv, is-xbox, no-hover) before first paint, so TV
            focus rings and touch hover guards never flash in late. The class
            attribute is not React-managed, hence suppressHydrationWarning. */}
        <script dangerouslySetInnerHTML={{ __html: DEVICE_CLASS_SCRIPT }} />
        {process.env.NEXT_PUBLIC_UNRAID_DISTRIBUTION === "true" && (
          <link rel="license" href="/distribution-sources/index.html" />
        )}
        {process.env.NEXT_PUBLIC_SELF_HOSTED === "true" && (
          <Script id="arvio-selfhost-config" src="/api/selfhost-config" strategy="beforeInteractive" />
        )}
        {/* iOS reads these raw links most reliably when adding to the home
            screen. `precomposed` is the legacy fallback older iOS honors; both
            carry the version bust. */}
        <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon-v3.png" />
        <link rel="apple-touch-icon-precomposed" sizes="180x180" href="/apple-touch-icon-v3.png" />
        {/* Safari, share sheets and crawlers still probe the legacy root paths
            /apple-touch-icon-precomposed.png and /favicon.ico regardless of
            these links; both files now exist in /public. */}
      </head>
      <body>
        <UpdateWatcher />
        {children}
      </body>
    </html>
  );
}
