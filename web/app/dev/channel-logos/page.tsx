import { notFound } from "next/navigation";
import { ChannelLogoFixture } from "./fixture";

export const dynamic = "force-dynamic";
export default function Page() {
  if (process.env.NODE_ENV !== "development" || process.env.ARVIO_UI_FIXTURES !== "true") notFound();
  return <ChannelLogoFixture />;
}
