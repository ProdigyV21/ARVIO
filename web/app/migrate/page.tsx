import type { Metadata } from "next";
import { MigrateApp } from "@/components/migrate/MigrateApp";
import "./setup.css";

export const metadata: Metadata = {
  title: "ARVIO setup",
  description: "Install addons and move a Nuvio account into ARVIO"
};

/**
 * Deliberately outside the subscription-gated app shell: someone who only uses
 * the free Android or TV app can set their account up here.
 */
export default function MigratePage() {
  return <MigrateApp />;
}
