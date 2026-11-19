"use client";
import "./profiles.css";
import { useTranslation } from "@/lib/i18n";


import { LockKeyhole, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { verifyProfilePin } from "@/lib/profilePin";
import type { Profile } from "@/lib/types";

/** Asks for a profile's PIN, or its parent PIN when [storedPin] / [label] say so (kids profiles). */
export function PinDialog({ profile, onUnlock, onClose, storedPin, label = "Profile PIN" }: {
  profile: Profile;
  onUnlock: (pin: string) => void;
  onClose: () => void;
  storedPin?: string | null;
  label?: "Profile PIN" | "Parent PIN";
}) {
  const translateUi = useTranslation();
  const dialog = useRef<HTMLDialogElement>(null);
  const [pin, setPin] = useState("");
  const [error, setError] = useState("");
  const [checking, setChecking] = useState(false);
  const attempts = useRef(0);
  const retryAfter = useRef(0);
  const input = useRef<HTMLInputElement>(null);
  // showModal() focuses the first button (Close); start on the PIN field.
  useEffect(() => { dialog.current?.showModal(); input.current?.focus(); }, []);
  return (
    <dialog ref={dialog} className="profile-dialog pin-dialog" onCancel={(event) => { event.preventDefault(); onClose(); }} aria-labelledby="profile-pin-title"
      // TV Back is a synthetic Escape (no "cancel"); the first one only leaves the field (tvNav).
      onKeyDown={(event) => { if (event.key === "Escape" && !(event.target instanceof HTMLInputElement)) { event.preventDefault(); onClose(); } }}>
      <form onSubmit={async (event) => {
        event.preventDefault();
        if (checking) return;
        if (Date.now() < retryAfter.current) { setError(translateUi("Please wait 30 seconds before trying again.")); return; }
        setChecking(true);
        const valid = await verifyProfilePin(pin, storedPin === undefined ? profile.pin : storedPin);
        setChecking(false);
        if (valid) { onUnlock(pin); return; }
        attempts.current++;
        if (attempts.current % 5 === 0) retryAfter.current = Date.now() + 30_000;
        setPin(""); setError(translateUi("Incorrect PIN. Try again.")); input.current?.focus();
      }}>
        <div className="profile-dialog-head"><h2 id="profile-pin-title"><LockKeyhole size={22} /> {profile.name}</h2><button className="icon-button" type="button" aria-label={translateUi("Close")} onClick={onClose}><X size={20} /></button></div>
        <label htmlFor="profile-pin">{label === "Parent PIN" ? translateUi("Parent PIN") : translateUi("Profile PIN")}</label>
        <input ref={input} id="profile-pin" autoFocus type="password" inputMode="numeric" autoComplete="off" pattern="[0-9]{4,5}" maxLength={5} required value={pin} onChange={(event) => setPin(event.target.value.replace(/\D/g, ""))} aria-describedby="profile-pin-error" />
        <p id="profile-pin-error" role="alert">{error}</p>
        <button type="submit" className="primary" disabled={checking || pin.length < 4}>{checking ? translateUi("Checking...") : translateUi("Unlock")}</button>
      </form>
    </dialog>
  );
}
