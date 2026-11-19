import type { Metadata } from "next";
import { PartnerDashboard } from "@/components/partner/PartnerDashboard";

export const metadata: Metadata = { title: "ARVIO Partner" };
export default function PartnerPage() { return <PartnerDashboard />; }
