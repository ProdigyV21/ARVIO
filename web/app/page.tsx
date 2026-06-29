import { AppShell } from "@/components/shell/AppShell";
import { AppProvider } from "@/lib/store";

export default function Page() {
  return (
    <AppProvider>
      <AppShell />
    </AppProvider>
  );
}
