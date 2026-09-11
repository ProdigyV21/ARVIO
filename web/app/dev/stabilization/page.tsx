import { notFound } from "next/navigation";
import { StabilizationFixture } from "./fixture";

export const dynamic = "force-dynamic";

export default async function Page({ searchParams }: { searchParams: Promise<{ mobile?: string }> }) {
  if (process.env.NODE_ENV !== "development" || process.env.ARVIO_UI_FIXTURES !== "true") notFound();
  if ((await searchParams).mobile === "1") return <iframe title="Mobile QA viewport" src="/dev/stabilization" style={{ display: "block", width: 390, height: 844, border: "1px solid #444", margin: "20px auto" }} />;
  return <StabilizationFixture testLiveUrl={process.env.ARVIO_TEST_LIVE_URL} />;
}
