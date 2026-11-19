"use client";
import "./profiles.css";
import { useTranslation } from "@/lib/i18n";


import { Cloud, Pencil, Plus } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { ProfileCloudSaveError, useAppPick } from "@/lib/store";
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
    goToLogin, auth, setToast
  } = useAppPick("profiles", "avatarImages", "manageMode", "setManageMode", "selectProfile", "createProfile", "updateProfile", "deleteProfile", "goToLogin", "auth", "setToast");

  const [dialog, setDialog] = useState<{ mode: "add" | "edit"; profile?: Profile; error?: string | null } | null>(null);
  const row = useRef<HTMLDivElement>(null);
  const focused = useRef(false);
  // Remote users start on the profile they used last.
  const lastUsed = profiles.reduce<Profile | null>((best, profile) => (profile.lastUsedAt ?? 0) > (best?.lastUsedAt ?? 0) ? profile : best, null) ?? profiles[0] ?? null;
  useEffect(() => {
    if (focused.current || !lastUsed || dialog) return;
    const button = row.current?.querySelector<HTMLElement>(`[data-profile-id="${CSS.escape(lastUsed.id)}"]`);
    if (button) { button.focus({ preventScroll: true }); focused.current = true; }
  }, [lastUsed, dialog]);
  const saveError = (error: unknown) => error instanceof ProfileCloudSaveError
    ? translateUi("Saved on this device, but ARVIO Cloud could not be reached. Try again to sync it to your other devices.")
    : error instanceof Error && error.message === "You can have up to 5 profiles." ? translateUi("You can have up to 5 profiles.")
    : translateUi("Could not save this profile.");
  const [openingProfileId, setOpeningProfileId] = useState<string | null>(null);
  const [lockedProfile, setLockedProfile] = useState<Profile | null>(null);
  // A kids profile's parent PIN guards editing and deleting it, never watching (Android parity).
  const [parentGateProfile, setParentGateProfile] = useState<Profile | null>(null);

  const openProfile = (profile: Profile, pin?: string) => {
    if (profile.isLocked && profile.pin && !pin) { setLockedProfile(profile); return; }
    if (manageMode) {
      if (profile.isKidsProfile && profile.parentPin) { setParentGateProfile(profile); return; }
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

        <div className="profile-row" ref={row}>
          {profiles.map((profile) => (
            <button
              type="button"
              key={profile.id}
              className="profile-pick"
              data-autofocus={profile.id === lastUsed?.id ? "" : undefined}
              data-profile-id={profile.id}
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
      {parentGateProfile && <PinDialog profile={parentGateProfile} storedPin={parentGateProfile.parentPin} label="Parent PIN" onClose={() => setParentGateProfile(null)} onUnlock={() => {
        setDialog({ mode: "edit", profile: parentGateProfile });
        setParentGateProfile(null);
      }} />}
      {dialog && (
        <ProfileDialog
          key={`${dialog.mode}:${dialog.profile?.id ?? "new"}`}
          mode={dialog.mode}
          initial={dialog.profile}
          avatarImages={avatarImages}
          error={dialog.error}
          onConfirm={async (result) => {
            try {
              if (dialog.mode === "add") await createProfile(result.name, result.avatarColor, result.avatarId, { ...result });
              else if (dialog.profile) await updateProfile({ ...dialog.profile, ...result });
              setDialog(null);
            } catch (error) {
              // Saved locally but not synced: keep the dialog (now editing the
              // saved profile) so Save retries without creating a duplicate.
              setDialog({ mode: error instanceof ProfileCloudSaveError || dialog.mode === "edit" ? "edit" : "add",
                profile: error instanceof ProfileCloudSaveError ? error.profile : dialog.profile, error: saveError(error) });
            }
          }}
          onDelete={dialog.mode === "edit" && dialog.profile ? async () => {
            try { await deleteProfile(dialog.profile!.id); setDialog(null); }
            catch (error) {
              if (error instanceof ProfileCloudSaveError) { setDialog(null); setToast(translateUi("Deleted on this device, but ARVIO Cloud could not be reached. Try again later from another device.")); return; }
              throw error;
            }
          } : undefined}
          onClose={() => setDialog(null)}
        />
      )}
    </main>
  );
}
