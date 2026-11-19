"use client";

import { useCallback, useEffect, useRef, useState } from "react";
import { createPortal } from "react-dom";
import { ArrowBigUp, CornerDownLeft, Delete } from "lucide-react";
import { useTranslation } from "@/lib/i18n";

// Built-in keyboard for couch input (gamepad A / Xbox remote Enter on a text
// field — see lib/tvNav.ts requestOnScreenKeyboard). A D-pad grid of keys types
// into the field through the native value setter + an `input` event, which is
// exactly what React's onChange tracks, so controlled inputs update normally.
//
// Mounted once in AppShell. It renders inside the field's open native
// <dialog> when there is one (everything outside a modal dialog is inert),
// otherwise at the end of <body>. role="dialog" makes spatial navigation stay
// on the keys while it is open; Back/Escape closes it and returns focus.

type Field = HTMLInputElement | HTMLTextAreaElement;

const LETTER_ROWS = [
  ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"],
  ["q", "w", "e", "r", "t", "y", "u", "i", "o", "p"],
  ["a", "s", "d", "f", "g", "h", "j", "k", "l", "@"],
  ["z", "x", "c", "v", "b", "n", "m", ".", "-", "_"],
  [":", "/", "?", "=", "&", "%", "+", "#", "~", ","]
];

function setFieldValue(field: Field, value: string, caret: number) {
  if (field.maxLength > 0 && value.length > field.maxLength) return;
  const proto = field instanceof HTMLTextAreaElement ? HTMLTextAreaElement.prototype : HTMLInputElement.prototype;
  const setter = Object.getOwnPropertyDescriptor(proto, "value")?.set;
  if (setter) setter.call(field, value);
  else field.value = value;
  field.dispatchEvent(new Event("input", { bubbles: true }));
  try { field.setSelectionRange(caret, caret); } catch { /* email/number inputs have no selection API */ }
}

function selection(field: Field): [number, number] {
  const end = field.value.length;
  try {
    if (field.selectionStart !== null && field.selectionStart !== undefined) {
      return [field.selectionStart, field.selectionEnd ?? field.selectionStart];
    }
  } catch { /* no selection API on this input type */ }
  return [end, end];
}

/** Insert text at the caret (replacing a selection), like typing. */
export function insertIntoField(field: Field, text: string) {
  const [start, end] = selection(field);
  const value = field.value;
  setFieldValue(field, value.slice(0, start) + text + value.slice(end), start + text.length);
}

/** Backspace at the caret. */
export function deleteFromField(field: Field) {
  const [start, end] = selection(field);
  const value = field.value;
  if (start !== end) setFieldValue(field, value.slice(0, start) + value.slice(end), start);
  else if (start > 0) setFieldValue(field, value.slice(0, start - 1) + value.slice(start), start - 1);
}

export function OnScreenKeyboard() {
  const translateUi = useTranslation();
  const [field, setField] = useState<Field | null>(null);
  const [shift, setShift] = useState(false);
  const [preview, setPreview] = useState("");
  const root = useRef<HTMLDivElement | null>(null);
  const fieldRef = useRef<Field | null>(null);
  fieldRef.current = field;

  const close = useCallback((refocus = true) => {
    const target = fieldRef.current;
    setField(null);
    setShift(false);
    if (refocus && target?.isConnected) target.focus({ preventScroll: true });
  }, []);

  useEffect(() => {
    const onOpen = (event: Event) => {
      const detail = (event as CustomEvent<{ target: Field; accepted: boolean }>).detail;
      if (!detail?.target) return;
      detail.accepted = true;
      setField(detail.target);
      setPreview(detail.target.value);
    };
    window.addEventListener("arvio:osk-open", onOpen);
    return () => window.removeEventListener("arvio:osk-open", onOpen);
  }, []);

  const type = useCallback((text: string) => {
    const target = fieldRef.current;
    if (!target?.isConnected) { close(false); return; }
    insertIntoField(target, text);
    setPreview(target.value);
  }, [close]);

  const backspace = useCallback(() => {
    const target = fieldRef.current;
    if (!target?.isConnected) { close(false); return; }
    deleteFromField(target);
    setPreview(target.value);
  }, [close]);

  // Focus the first letter on open; capture keys while open so Escape/Back
  // closes only the keyboard (not the layer below) and a physical keyboard
  // still types.
  useEffect(() => {
    if (!field) return undefined;
    const frame = requestAnimationFrame(() => root.current?.querySelector<HTMLButtonElement>('[data-osk-key="q"]')?.focus({ preventScroll: true }));
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Escape") {
        event.preventDefault();
        event.stopImmediatePropagation();
        close();
        return;
      }
      if (!event.isTrusted || event.ctrlKey || event.metaKey || event.altKey) return;
      if (event.key === "Backspace") { event.preventDefault(); backspace(); return; }
      if (event.key.length === 1 && event.key !== " " ) { event.preventDefault(); type(event.key); }
    };
    window.addEventListener("keydown", onKey, true);
    const watch = window.setInterval(() => { if (!fieldRef.current?.isConnected) close(false); }, 500);
    return () => {
      cancelAnimationFrame(frame);
      window.removeEventListener("keydown", onKey, true);
      window.clearInterval(watch);
    };
  }, [backspace, close, field, type]);

  if (!field || typeof document === "undefined") return null;
  const host = field.closest("dialog[open]") ?? document.body;
  const masked = field instanceof HTMLInputElement && field.type === "password";
  const label = field.getAttribute("aria-label") || field.getAttribute("placeholder") || translateUi("Text");

  const submit = () => {
    const target = fieldRef.current;
    close();
    if (!target) return;
    // React onKeyDown handlers (add addon, search) see an Enter; a form
    // submits through its own submit path.
    const enter = new KeyboardEvent("keydown", { key: "Enter", code: "Enter", bubbles: true, cancelable: true });
    target.dispatchEvent(enter);
    if (!enter.defaultPrevented && target.form) target.form.requestSubmit();
  };

  return createPortal(
    <div ref={root} className="osk" role="dialog" aria-modal="false" aria-label={translateUi("On-screen keyboard")}>
      <div className="osk-preview" aria-live="polite">
        <span className="osk-preview-label">{label}</span>
        <span className="osk-preview-value">{masked ? "•".repeat(preview.length) : preview}<span className="osk-caret" aria-hidden="true" /></span>
      </div>
      <div className="osk-grid">
        {LETTER_ROWS.map((row, index) => (
          <div className="osk-row" key={index}>
            {row.map((key) => {
              const char = shift ? key.toUpperCase() : key;
              return (
                <button type="button" key={key} className="osk-key" data-osk-key={key} onClick={() => { type(char); if (shift) setShift(false); }}>
                  {char}
                </button>
              );
            })}
          </div>
        ))}
        <div className="osk-row osk-row-actions">
          <button type="button" className={`osk-key osk-key-wide${shift ? " is-active" : ""}`} aria-pressed={shift} onClick={() => setShift((value) => !value)} aria-label={translateUi("Shift")}>
            <ArrowBigUp size={20} aria-hidden="true" />
          </button>
          <button type="button" className="osk-key osk-key-space" onClick={() => type(" ")}>{translateUi("Space")}</button>
          <button type="button" className="osk-key osk-key-wide" onClick={backspace} aria-label={translateUi("Delete")}>
            <Delete size={20} aria-hidden="true" />
          </button>
          <button type="button" className="osk-key osk-key-wide" onClick={submit} aria-label={translateUi("Enter")}>
            <CornerDownLeft size={20} aria-hidden="true" />
          </button>
          <button type="button" className="osk-key osk-key-done" onClick={() => close()}>{translateUi("Done")}</button>
        </div>
      </div>
    </div>,
    host
  );
}
