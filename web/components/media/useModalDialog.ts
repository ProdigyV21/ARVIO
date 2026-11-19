"use client";

import { useCallback, useEffect, useRef, type KeyboardEvent as ReactKeyboardEvent, type RefObject, type SyntheticEvent } from "react";

const TEXT_INPUT = /^(INPUT|TEXTAREA|SELECT)$/;

/**
 * Focus handling for the full-screen <dialog> pages (collections, "View all").
 * - Focus moves inside when the dialog opens and comes back to the card a
 *   title was opened from when it reopens after the details page.
 * - Escape and the TV Back key (which tvNav turns into a synthetic Escape the
 *   native dialog never sees) close it.
 * - On unmount focus returns to the tile that opened the dialog.
 */
export function useModalDialog(ref: RefObject<HTMLDialogElement | null>, { open, onClose }: { open: boolean; onClose: () => void }) {
  const opener = useRef<HTMLElement | null>(null);
  const lastInside = useRef<HTMLElement | null>(null);
  const closing = useRef(false);
  const close = useRef(onClose);
  close.current = onClose;

  useEffect(() => {
    const active = document.activeElement;
    opener.current = active instanceof HTMLElement && active !== document.body ? active : null;
    return () => {
      const target = opener.current;
      // The opener may have re-rendered away (rail refresh); only refocus a live node.
      if (target?.isConnected) target.focus({ preventScroll: true });
    };
  }, []);

  useEffect(() => {
    const dialog = ref.current;
    if (!dialog) return undefined;
    const remember = (event: FocusEvent) => {
      if (event.target instanceof HTMLElement && dialog.contains(event.target)) lastInside.current = event.target;
    };
    dialog.addEventListener("focusin", remember);
    if (open) {
      closing.current = false;
      // Read before showModal: the browser's own initial focus (sometimes the
      // scroll container) fires focusin and would be mistaken for the user's.
      const previous = lastInside.current;
      if (!dialog.open) dialog.showModal();
      const target = previous?.isConnected && dialog.contains(previous)
        ? previous
        : dialog.querySelector<HTMLElement>("[data-autofocus]") ?? dialog.querySelector<HTMLElement>("button, [href], [tabindex]:not([tabindex='-1'])");
      target?.focus({ preventScroll: Boolean(previous) });
    } else if (dialog.open) {
      dialog.close();
    }
    return () => dialog.removeEventListener("focusin", remember);
  }, [open, ref]);

  const requestClose = useCallback(() => {
    if (closing.current) return;
    closing.current = true;
    close.current();
  }, []);

  return {
    onKeyDown: (event: ReactKeyboardEvent<HTMLDialogElement>) => {
      if (event.key !== "Escape" || event.defaultPrevented) return;
      // Escape inside a text field only leaves the field (tvNav), never the page.
      if (event.target instanceof HTMLElement && (TEXT_INPUT.test(event.target.tagName) || event.target.isContentEditable)) return;
      event.preventDefault();
      event.stopPropagation();
      requestClose();
    },
    onCancel: (event: SyntheticEvent<HTMLDialogElement>) => {
      event.preventDefault();
      requestClose();
    }
  };
}
