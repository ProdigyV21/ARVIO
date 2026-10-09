"use client";
import { useTranslation } from "@/lib/i18n";


import { Cloud, Pencil, Plus } from "lucide-react";
import { useState } from "react";
import { useApp } from "@/lib/store";
import { config } from "@/lib/config";
import type { Profile } from "@/lib/types";
import { ProfileAvatarVisual } from "./ProfileAvatar";
import { ProfileDialog } from "./ProfileDialog";
import { PinDialog } from "./PinDialog";

export function ProfileSelectionScreen() {
  const translateUi = useTranslation();
  const {
    profiles, avatarImages, manageMode, setManageMode,
    selectProfile, createProfile, updateProfile, deleteProfile,
    goToLogin, auth
  } = useApp();

  const [dialog, setDialog] = useState<{ mode: "add" | "edit"; profile?: Profile } | null>(null);
  const [openingProfileId, setOpeningProfileId] = useState<string | null>(null);
  const [lockedProfile, setLockedProfile] = useState<Profile | null>(null);

  const openProfile = (profile: Profile, pin?: string) => {
    if (profile.isLocked && profile.pin && !pin) { setLockedProfile(profile); return; }
    if (manageMode) {
      setDialog({ mode: "edit", profile });
      return;
    }
    setOpeningProfileId(profile.id);
    void selectProfile(profile, pin).finally(() => setOpeningProfileId(null));
  };

  return (
    <main className="profile-shell">
      <div className="profile-center">
        <div className="profile-brand-lockup">
          <img className="profile-brand-logo" src="/arvio-icon-512.png" alt="" width={56} height={56} />
          <img className="profile-wordmark" src="/arvio-wordmark.svg" alt="ARVIO" />
        </div>
        <h1 className="profile-heading">{manageMode ? translateUi("Manage Profiles") : translateUi("Who's watching?")}</h1>

        <div className="profile-row">
          {profiles.map((profile) => (
            <button
              type="button"
              key={profile.id}
              className="profile-pick"
              onClick={() => openProfile(profile)}
              aria-busy={openingProfileId === profile.id}
            >
              <div className="avatar-tile">
                <ProfileAvatarVisual profile={profile} avatarImages={avatarImages} />
                {manageMode && (
                  <div className="avatar-edit-overlay"><Pencil size={26} /></div>
                )}
              </div>
              <span>{openingProfileId === profile.id ? translateUi("Opening...") : profile.name}</span>
            </button>
          ))}

          {profiles.length < 5 && (
            <button type="button" className="profile-pick" onClick={() => setDialog({ mode: "add" })}>
              <div className="avatar-tile add">
                <Plus size={48} />
              </div>
              <span>{translateUi("Add Profile")}</span>
            </button>
          )}
        </div>

        <button type="button" className="manage-profiles-btn" onClick={() => setManageMode(!manageMode)}>
          {manageMode ? translateUi("Done") : translateUi("Manage Profiles")}
        </button>

        {process.env.NEXT_PUBLIC_UNRAID_DISTRIBUTION === "true" && (
          <p><a className="secondary text-button" href="/distribution-sources/index.html" target="_blank" rel="noopener noreferrer">Source code & licences</a></p>
        )}

        {!auth && !config.selfHosted && (
          <button
            type="button"
            className="cloud-connect-btn"
            onClick={(event) => {
              event.preventDefault();
              event.stopPropagation();
              goToLogin();
            }}
          >
            <Cloud size={18} /> {translateUi(" Connect to Cloud")}</button>
        )}
      </div>

      {lockedProfile && <PinDialog profile={lockedProfile} onClose={() => setLockedProfile(null)} onUnlock={(pin) => {
        openProfile(lockedProfile, pin);
        setLockedProfile(null);
      }} />}
      {dialog && (
        <ProfileDialog
          mode={dialog.mode}
          initial={dialog.profile}
          onConfirm={(name, color, avatarId) => {
            if (dialog.mode === "add") {
              void createProfile(name, color, avatarId);
            } else if (dialog.profile) {
              void updateProfile({ ...dialog.profile, name, avatarColor: color, avatarId });
            }
            setDialog(null);
          }}
          onDelete={dialog.mode === "edit" && dialog.profile ? () => {
            void deleteProfile(dialog.profile!.id);
            setDialog(null);
          } : undefined}
          onClose={() => setDialog(null)}
        />
      )}
    </main>
  );
}
