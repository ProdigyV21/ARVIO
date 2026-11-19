import type { Metadata } from "next";

export const metadata: Metadata = { title: "Catalog packs · ARVIO", description: "Community-curated sets of Home rows for the ARVIO app." };

export default function DiscoverLayout({ children }: { children: React.ReactNode }) {
  return children;
}
