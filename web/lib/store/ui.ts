// App store (lib/store.tsx): The media context menu.
import { useCallback, useState } from "react";
import type { ContextMenuTarget } from "./types";

export function useContextMenu() {
  const [activeContextMenu, setActiveContextMenu] = useState<ContextMenuTarget | null>(null);

  const openContextMenu = useCallback((target: ContextMenuTarget) => {
    setActiveContextMenu(target);
  }, []);

  const closeContextMenu = useCallback(() => {
    setActiveContextMenu(null);
  }, []);
  return { activeContextMenu, setActiveContextMenu, openContextMenu, closeContextMenu };
}
