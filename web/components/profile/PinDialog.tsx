"use client";

import { LockKeyhole, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { verifyProfilePin } from "@/lib/profilePin";
import type { Profile } from "@/lib/types";

export function PinDialog({ profile, onUnlock, onClose }: { profile: Profile; onUnlock: (pin: string) => void; onClose: () => void }) {
  const dialog = useRef<HTMLDialogElement>(null);
  const [pin, setPin] = useState("");
  const [error, setError] = useState("");
  const [checking, setChecking] = useState(false);
  const attempts = useRef(0);
  const retryAfter = useRef(0);
  useEffect(() => { dialog.current?.showModal(); }, []);
  return (
    <dialog ref={dialog} className="profile-dialog pin-dialog" onCancel={onClose} aria-labelledby="profile-pin-title">
      <form onSubmit={async (event) => {
        event.preventDefault();
        if (checking) return;
        if (Date.now() < retryAfter.current) { setError("Please wait 30 seconds before trying again."); return; }
        setChecking(true);
        const valid = await verifyProfilePin(pin, profile.pin);
        setChecking(false);
        if (valid) { onUnlock(pin); return; }
        attempts.current++;
        if (attempts.current % 5 === 0) retryAfter.current = Date.now() + 30_000;
        setPin(""); setError("Incorrect PIN. Try again.");
      }}>
        <div className="profile-dialog-head"><h2 id="profile-pin-title"><LockKeyhole size={22} /> {profile.name}</h2><button className="icon-button" type="button" aria-label="Close" onClick={onClose}><X size={20} /></button></div>
        <label htmlFor="profile-pin">Profile PIN</label>
        <input id="profile-pin" autoFocus type="password" inputMode="numeric" autoComplete="off" pattern="[0-9]{4,5}" maxLength={5} required value={pin} onChange={(event) => setPin(event.target.value.replace(/\D/g, ""))} aria-describedby="profile-pin-error" />
        <p id="profile-pin-error" role="alert">{error}</p>
        <button type="submit" className="primary" disabled={checking || pin.length < 4}>{checking ? "Checking..." : "Unlock"}</button>
      </form>
    </dialog>
  );
}
