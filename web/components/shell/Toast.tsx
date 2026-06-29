"use client";

import { useApp } from "@/lib/store";

export function Toast() {
  const { toast, setToast } = useApp();
  if (!toast) return null;
  return (
    <button className="toast" onClick={() => setToast(null)}>
      {toast}
    </button>
  );
}
