"use client";
import { useTranslation } from "@/lib/i18n";
import "./profiles.css";

import { Check, ChevronDown, LockKeyhole, Trash2, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { avatarCategories, avatarGradient, avatarSrc, colorToCss, customAvatarSrc, profileColors } from "@/lib/profiles";
import type { Profile } from "@/lib/types";
import { DEFAULT_KIDS_LEVEL, KIDS_LEVELS, kidsLevelOf, type KidsAgeLevel } from "@/lib/kids";
import { hashProfilePin, isValidProfilePin } from "@/lib/profilePin";

/** Kids settings saved with the profile; same fields Android writes (Profile.kt). */
export interface KidsProfileFields {
  isKidsProfile: boolean;
  kidsAgeLevel: KidsAgeLevel | null;
  parentPin: string | null;
}

/** Everything the dialog saves. `pin`/`isLocked` follow Android's profile lock (hashed PIN, isLocked). */
export interface ProfileDialogResult extends KidsProfileFields {
  name: string;
  avatarColor: number;
  avatarId: number;
  pin: string | null;
  isLocked: boolean;
  avatarImageVersion?: number;
  avatarImageStoragePath?: string | null;
}

const COLOR_NAMES = ["Red", "Green", "Blue", "Orange", "Purple", "Pink", "Teal", "Indigo"];

type PinEdit = "keep" | "set" | "remove";

/** Resolve the PIN fields to save, hashing new PINs like Android PinUtil. */
export async function resolveProfilePins(initial: Pick<Profile, "pin" | "isLocked" | "parentPin"> | undefined, choices: {
  lock: PinEdit; lockPin: string; kidsOn: boolean; parentEdit: boolean; parentPin: string;
}, hash: (pin: string) => Promise<string> = hashProfilePin): Promise<{ pin: string | null; isLocked: boolean; parentPin: string | null }> {
  const pin = choices.lock === "set" ? await hash(choices.lockPin) : choices.lock === "remove" ? null : initial?.pin ?? null;
  const parentPin = !choices.kidsOn ? null
    : choices.parentEdit || !initial?.parentPin ? await hash(choices.parentPin) : initial.parentPin;
  return { pin, isLocked: Boolean(pin) && (choices.lock === "set" || Boolean(initial?.isLocked)), parentPin };
}

export function ProfileDialog({ mode, initial, avatarImages = {}, error: externalError, onConfirm, onDelete, onClose }: {
  mode: "add" | "edit";
  initial?: Profile;
  avatarImages?: Record<string, string>;
  /** An error from the last save (e.g. ARVIO Cloud could not be reached). */
  error?: string | null;
  onConfirm: (result: ProfileDialogResult) => Promise<void>;
  onDelete?: () => Promise<void>;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const [name, setName] = useState(initial?.name ?? "");
  const [avatarColor, setAvatarColor] = useState(initial?.avatarColor ?? profileColors[0]);
  const [avatarId, setAvatarId] = useState(initial?.avatarId ?? 0);
  const photo = initial ? customAvatarSrc(initial, avatarImages) : null;
  const [usePhoto, setUsePhoto] = useState(Boolean(photo));
  const [kidsOn, setKidsOn] = useState(Boolean(initial?.isKidsProfile));
  const [kidsLevel, setKidsLevel] = useState<KidsAgeLevel>(kidsLevelOf(initial) ?? DEFAULT_KIDS_LEVEL);
  const [parentPin, setParentPin] = useState("");
  const [parentEdit, setParentEdit] = useState(false);
  const [lock, setLock] = useState<PinEdit>("keep");
  const [lockPin, setLockPin] = useState("");
  const [confirmDelete, setConfirmDelete] = useState(false);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const hasLock = Boolean(initial?.pin);
  // A kids profile always has a parent PIN: an existing one is kept, a new one must be set here.
  const needsParentPin = kidsOn && (!initial?.parentPin || parentEdit);
  const pinMissing = (needsParentPin && !isValidProfilePin(parentPin)) || (lock === "set" && !isValidProfilePin(lockPin));
  const openCategory = avatarCategories.find((category) => category.ids.includes(avatarId))?.label ?? avatarCategories[0]?.label;
  const nameInput = useRef<HTMLInputElement>(null);
  const shownError = error ?? externalError ?? null;

  const confirm = async () => {
    if (saving || !name.trim() || pinMissing) return;
    setSaving(true);
    setError(null);
    try {
      const pins = await resolveProfilePins(initial, { lock, lockPin, kidsOn, parentEdit, parentPin });
      const droppedPhoto = Boolean(initial?.avatarImageVersion) && !usePhoto;
      await onConfirm({
        name: name.trim() || "Profile", avatarColor, avatarId: usePhoto ? 0 : avatarId,
        isKidsProfile: kidsOn, kidsAgeLevel: kidsOn ? kidsLevel : null, ...pins,
        ...(droppedPhoto ? { avatarImageVersion: 0, avatarImageStoragePath: null } : {})
      });
    } catch (cause) {
      setError(cause instanceof Error ? cause.message : "Could not save this profile.");
    } finally {
      setSaving(false);
    }
  };

  // Escape / remote Back / gamepad B closes the dialog like every other
  // modal. When the event targets an input, tvNav already turned that
  // press into a blur — the next one closes.
  useEffect(() => {
    const onKey = (event: KeyboardEvent) => {
      if (event.key !== "Escape") return;
      const target = event.target;
      if (target instanceof HTMLElement && (target.tagName === "INPUT" || target.tagName === "TEXTAREA")) return;
      if (confirmDelete) { setConfirmDelete(false); return; }
      onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [onClose, confirmDelete]);

  const preview = usePhoto && photo
    ? <img className="avatar-visual" src={photo} alt="" />
    : avatarId > 0
      ? <div className="avatar-visual" style={{ background: `linear-gradient(180deg, ${avatarGradient(avatarId)[0]}, ${avatarGradient(avatarId)[1]})` }}>
          <img className="avatar-emoji" src={avatarSrc(avatarId)} alt="" />
        </div>
      : <div className="avatar-visual avatar-letter" style={{ background: colorToCss(avatarColor) }}>
          <span>{name.charAt(0)?.toUpperCase() || "?"}</span>
        </div>;

  return (
    <div className="modal-scrim" onClick={onClose}>
      <div className="profile-dialog profile-edit-dialog" role="dialog" aria-modal="true" aria-labelledby="profile-dialog-title" onClick={(event) => event.stopPropagation()}>
        <div className="profile-dialog-head">
          <h2 id="profile-dialog-title">{mode === "add" ? translateUi("Add Profile") : translateUi("Edit Profile")}</h2>
          <button type="button" className="icon-button" onClick={onClose} aria-label={translateUi("Close")}><X size={20} /></button>
        </div>

        <div className="profile-dialog-body">
          <div className="profile-dialog-preview">
            <div className="avatar-tile lg">{preview}</div>
            {photo && <div className="profile-photo-actions">
              {usePhoto
                ? <button type="button" className="secondary text-button" onClick={() => setUsePhoto(false)}>{translateUi("Remove photo")}</button>
                : <button type="button" className="secondary text-button" onClick={() => setUsePhoto(true)}>{translateUi("Use synced photo")}</button>}
              <small>{translateUi("Photos are added in the Android app.")}</small>
            </div>}
          </div>

          <label className="profile-name-label" htmlFor="profile-name">{translateUi("Profile name")}</label>
          <input ref={nameInput} id="profile-name" className="profile-name-input" value={name} onChange={(event) => setName(event.target.value)}
            placeholder={translateUi("Profile name")} autoFocus maxLength={20} />

          <h3 className="profile-dialog-label">{translateUi("Color (letter avatar)")}</h3>
          <div className="color-row" role="radiogroup" aria-label={translateUi("Color (letter avatar)")}>
            {profileColors.map((color, index) => {
              const active = !usePhoto && avatarId === 0 && avatarColor === color;
              return (
                <button type="button" key={color} role="radio" aria-checked={active}
                  className={`color-dot ${active ? "is-active" : ""}`} style={{ background: colorToCss(color) }}
                  onClick={() => { setAvatarColor(color); setAvatarId(0); setUsePhoto(false); }}
                  aria-label={translateUi(COLOR_NAMES[index] ?? "Color")} title={translateUi(COLOR_NAMES[index] ?? "Color")}>
                  {active ? <Check size={16} /> : null}
                </button>
              );
            })}
          </div>

          <h3 className="profile-dialog-label">{translateUi("Avatar")}</h3>
          <div className="avatar-picker">
            {avatarCategories.map((category) => (
              <details className="avatar-category" key={category.label} open={category.label === openCategory}>
                <summary className="avatar-category-label">{translateUi(category.label)} <ChevronDown size={16} aria-hidden="true" /></summary>
                <div className="avatar-grid" role="radiogroup" aria-label={translateUi(category.label)}>
                  {category.ids.map((id, index) => (
                    <button type="button" key={id} role="radio" aria-checked={!usePhoto && avatarId === id}
                      aria-label={translateUi("{value0} avatar {value1}", { value0: translateUi(category.label), value1: index + 1 })}
                      className={`avatar-cell ${!usePhoto && avatarId === id ? "is-active" : ""}`}
                      style={{ background: `linear-gradient(180deg, ${avatarGradient(id)[0]}, ${avatarGradient(id)[1]})` }}
                      onClick={() => { setAvatarId(id); setUsePhoto(false); }}>
                      <img src={avatarSrc(id)} alt="" loading="lazy" />
                    </button>
                  ))}
                </div>
              </details>
            ))}
          </div>

          {mode === "edit" && <>
            <h3 className="profile-dialog-label"><LockKeyhole size={15} /> {translateUi("Profile Lock")}</h3>
            <p className="profile-kids-hint">{translateUi("A PIN asked for before anyone opens this profile, on every device.")}</p>
            <div className="profile-kids-row" role="group" aria-label={translateUi("Profile Lock")}>
              {!hasLock
                ? <button type="button" className={`secondary text-button ${lock === "set" ? "is-active" : ""}`} aria-pressed={lock === "set"} onClick={() => setLock(lock === "set" ? "keep" : "set")}>{translateUi("Set PIN")}</button>
                : <>
                  <button type="button" className={`secondary text-button ${lock === "set" ? "is-active" : ""}`} aria-pressed={lock === "set"} onClick={() => setLock(lock === "set" ? "keep" : "set")}>{translateUi("Change PIN")}</button>
                  <button type="button" className={`secondary text-button ${lock === "remove" ? "is-active" : ""}`} aria-pressed={lock === "remove"} onClick={() => setLock(lock === "remove" ? "keep" : "remove")}>{translateUi("Remove PIN")}</button>
                </>}
            </div>
            {lock === "set" && <>
              <label className="profile-name-label" htmlFor="profile-lock-pin">{hasLock ? translateUi("New profile PIN") : translateUi("Profile PIN")}</label>
              <input id="profile-lock-pin" className="profile-name-input" type="password" inputMode="numeric" autoComplete="new-password" maxLength={5} autoFocus
                value={lockPin} onChange={(event) => setLockPin(event.target.value.replace(/\D/g, "").slice(0, 5))} aria-describedby="profile-lock-pin-hint" />
              <p id="profile-lock-pin-hint" className="profile-kids-hint">{translateUi("4-5 digits.")}</p>
            </>}
            {lock === "remove" && <p className="profile-kids-hint">{translateUi("The PIN is removed when you save.")}</p>}
          </>}

          <h3 className="profile-dialog-label">{translateUi("Kids Profile")}</h3>
          <div className="profile-kids-row" role="group" aria-label={translateUi("Kids Profile")}>
            <button type="button" className={`secondary text-button ${kidsOn ? "is-active" : ""}`} aria-pressed={kidsOn} onClick={() => setKidsOn(true)}>{kidsOn ? <Check size={16} /> : null} {translateUi("On")}</button>
            <button type="button" className={`secondary text-button ${!kidsOn ? "is-active" : ""}`} aria-pressed={!kidsOn} onClick={() => setKidsOn(false)}>{!kidsOn ? <Check size={16} /> : null} {translateUi("Off")}</button>
          </div>
          {kidsOn && (
            <>
              <p className="profile-kids-hint">{translateUi("Only titles rated for this age. Unrated titles and Live TV are hidden.")}</p>
              <h3 className="profile-dialog-label">{translateUi("Max age")}</h3>
              <div className="profile-kids-row" role="radiogroup" aria-label={translateUi("Max age")}>
                {KIDS_LEVELS.map((level) => (
                  <button type="button" key={level} role="radio" aria-checked={kidsLevel === level} className={`secondary text-button ${kidsLevel === level ? "is-active" : ""}`} onClick={() => setKidsLevel(level)}>
                    {level === "LITTLE_KIDS" ? translateUi("Up to 7") : level === "OLDER_KIDS" ? translateUi("Up to 10") : translateUi("Up to 14")}
                  </button>
                ))}
              </div>
              {initial?.parentPin && !parentEdit && <button type="button" className="secondary text-button profile-change-parent" onClick={() => setParentEdit(true)}>{translateUi("Change parent PIN")}</button>}
              {needsParentPin && (
                <>
                  <label className="profile-name-label" htmlFor="profile-parent-pin">{parentEdit ? translateUi("New parent PIN") : translateUi("Set a parent PIN for this kids profile")}</label>
                  <input id="profile-parent-pin" className="profile-name-input" type="password" inputMode="numeric" autoComplete="new-password" maxLength={5}
                    value={parentPin} onChange={(event) => setParentPin(event.target.value.replace(/\D/g, "").slice(0, 5))} aria-describedby="profile-parent-pin-hint" autoFocus={parentEdit} />
                  <p id="profile-parent-pin-hint" className="profile-kids-hint">{translateUi("4-5 digits. Needed to change or delete this kids profile, not to watch.")}</p>
                </>
              )}
            </>
          )}
        </div>

        {/* Sticky: Save stays one press away however far the avatar list scrolls. */}
        <div className="profile-dialog-actions profile-dialog-sticky">
          {shownError && <p className="profile-dialog-error" role="alert">{translateUi(shownError)}</p>}
          {confirmDelete ? <div className="profile-delete-confirm" role="group" aria-label={translateUi("Delete profile")}>
            <p>{translateUi("Delete {value0}? It is removed from every device signed in to this account, with its watch history and settings.", { value0: initial?.name ?? "" })}</p>
            <button type="button" className="secondary text-button" onClick={() => setConfirmDelete(false)}>{translateUi("Cancel")}</button>
            <button type="button" className="primary danger" autoFocus disabled={saving} onClick={async () => {
              if (!onDelete) return;
              setSaving(true); setError(null);
              try { await onDelete(); } catch (cause) { setError(cause instanceof Error ? cause.message : "Could not delete this profile."); setConfirmDelete(false); }
              finally { setSaving(false); }
            }}><Trash2 size={18} /> {translateUi("Delete")}</button>
          </div> : <>
            {mode === "edit" && onDelete && (
              <button type="button" className="secondary text-button danger" onClick={() => setConfirmDelete(true)}><Trash2 size={18} /> {translateUi("Delete")}</button>
            )}
            <button type="button" className="secondary text-button" onClick={onClose}>{translateUi("Cancel")}</button>
            <button type="button" className="primary" onClick={() => void confirm()} disabled={!name.trim() || pinMissing || saving}>
              {saving ? translateUi("Saving…") : mode === "add" ? translateUi("Create") : translateUi("Save")}
            </button>
          </>}
        </div>
      </div>
    </div>
  );
}
