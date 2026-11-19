import type { Metadata } from "next";

export const metadata: Metadata = { title: "Report a problem · ARVIO", description: "Send an ARVIO crash report or problem to the ARVIO Discord." };

export default function ReportLayout({ children }: { children: React.ReactNode }) {
  return children;
}
