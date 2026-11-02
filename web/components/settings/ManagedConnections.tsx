"use client";
import { LockKeyhole } from "lucide-react";
import { useAppPick } from "@/lib/store";
import { Group, Row, Status } from "./ui";

export function ManagedConnections({ items }: { items: Array<{ id: string; name?: string }> }) {
  const { managedStatus } = useAppPick("managedStatus");
  if (!items.length) return null;
  return <Group title={managedStatus?.name || "Managed connections"}>
    {items.map(item => <Row key={item.id} label={item.name || "Connection"}><Status tone="off"><LockKeyhole size={14} />Managed</Status></Row>)}
    <a className="st-btn" href="/partner/account">Manage membership</a>
  </Group>;
}
