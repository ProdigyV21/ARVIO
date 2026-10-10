"use client";
import { useTranslation } from "@/lib/i18n";


import { Check, Trash2, X } from "lucide-react";
import { useEffect, useState } from "react";
import { avatarCategories, avatarGradient, avatarSrc, colorToCss, profileColors } from "@/lib/profiles";
import type { Profile } from "@/lib/types";

export function ProfileDialog({ mode, initial, onConfirm, onDelete, onClose }: {
  mode: "add" | "edit";
  initial?: Profile;
  onConfirm: (name: string, avatarColor: number, avatarId: number) => void;
  onDelete?: () => void;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const [name, setName] = useState(initial?.name ?? "");
  const [avatarColor, setAvatarColor] = useState(initial?.avatarColor ?? profileColors[0]);
  const [avatarId, setAvatarId] = useState(initial?.avatarId ?? 0);

  // Escape / remote Back / gamepad B closes the dialog like every other
  // modal. When the event targets the name input, tvNav already turned that
  // press into a blur — the next one closes.
  useEffect(() => {
    const onKey = (event: KeyboardEvent) => {
      if (event.key !== "Escape") return;
      const target = event.target;
      if (target instanceof HTMLElement && (target.tagName === "INPUT" || target.tagName === "TEXTAREA")) return;
      onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [onClose]);

  return (
    <div className="modal-scrim" onClick={onClose}>
      <div className="profile-dialog" role="dialog" aria-modal="true" onClick={(event) => event.stopPropagation()}>
        <div className="profile-dialog-head">
          <h2>{mode === "add" ? translateUi("Add Profile") : translateUi("Edit Profile")}</h2>
          <button type="button" className="icon-button" onClick={onClose} aria-label={translateUi("Close")}><X size={20} /></button>
        </div>

        <div className="profile-dialog-preview">
          <div className="avatar-tile lg">
            {avatarId > 0 ? (
              <div className="avatar-visual" style={{ background: `linear-gradient(180deg, ${avatarGradient(avatarId)[0]}, ${avatarGradient(avatarId)[1]})` }}>
                <img className="avatar-emoji" src={avatarSrc(avatarId)} alt="" />
              </div>
            ) : (
              <div className="avatar-visual avatar-letter" style={{ background: colorToCss(avatarColor) }}>
                <span>{name.charAt(0)?.toUpperCase() || "?"}</span>
              </div>
            )}
          </div>
        </div>

        <label className="profile-name-label" htmlFor="profile-name">{translateUi("Profile name")}</label>
        <input
          id="profile-name"
          className="profile-name-input"
          value={name}
          onChange={(event) => setName(event.target.value)}
          placeholder={translateUi("Profile name")}
          autoFocus
          maxLength={20}
        />

        <h3 className="profile-dialog-label">{translateUi("Color (letter avatar)")}</h3>
        <div className="color-row">
          {profileColors.map((color) => (
            <button
              type="button"
              key={color}
              className={`color-dot ${avatarId === 0 && avatarColor === color ? "is-active" : ""}`}
              style={{ background: colorToCss(color) }}
              onClick={() => { setAvatarColor(color); setAvatarId(0); }}
              aria-label={translateUi("Pick color")}
            >
              {avatarId === 0 && avatarColor === color ? <Check size={16} /> : null}
            </button>
          ))}
        </div>

        <h3 className="profile-dialog-label">{translateUi("Avatar")}</h3>
        <div className="avatar-picker">
          {avatarCategories.map((category) => (
            <div className="avatar-category" key={category.label}>
              <span className="avatar-category-label">{translateUi(category.label)}</span>
              <div className="avatar-grid">
                {category.ids.map((id) => (
                  <button
                    type="button"
                    key={id}
                    className={`avatar-cell ${avatarId === id ? "is-active" : ""}`}
                    style={{ background: `linear-gradient(180deg, ${avatarGradient(id)[0]}, ${avatarGradient(id)[1]})` }}
                    onClick={() => setAvatarId(id)}
                  >
                    <img src={avatarSrc(id)} alt="" />
                  </button>
                ))}
              </div>
            </div>
          ))}
        </div>

        <div className="profile-dialog-actions">
          {mode === "edit" && onDelete && (
            <button type="button" className="secondary text-button danger" onClick={onDelete}><Trash2 size={18} /> {translateUi(" Delete")}</button>
          )}
          <button type="button" className="secondary text-button" onClick={onClose}>{translateUi("Cancel")}</button>
          <button type="button" className="primary" onClick={() => onConfirm(name.trim() || "Profile", avatarColor, avatarId)} disabled={!name.trim()}>
            {mode === "add" ? translateUi("Create") : translateUi("Save")}
          </button>
        </div>
      </div>
    </div>
  );
}
