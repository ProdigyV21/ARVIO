"use client";

import { Cloud, Pencil, Plus } from "lucide-react";
import { useState } from "react";
import { useApp } from "@/lib/store";
import type { Profile } from "@/lib/types";
import { ProfileAvatarVisual } from "./ProfileAvatar";
import { ProfileDialog } from "./ProfileDialog";

export function ProfileSelectionScreen() {
  const {
    profiles, avatarImages, manageMode, setManageMode,
    selectProfile, createProfile, updateProfile, deleteProfile,
    goToLogin, auth
  } = useApp();

  const [dialog, setDialog] = useState<{ mode: "add" | "edit"; profile?: Profile } | null>(null);
  const [openingProfileId, setOpeningProfileId] = useState<string | null>(null);

  const openProfile = (profile: Profile) => {
    if (manageMode) {
      setDialog({ mode: "edit", profile });
      return;
    }
    setOpeningProfileId(profile.id);
    void selectProfile(profile);
  };

  return (
    <main className="profile-shell">
      <div className="profile-center">
        <div className="profile-brand-lockup">
          <img className="profile-brand-logo" src="/arvio-icon-512.png" alt="" width={56} height={56} />
          <img className="profile-wordmark" src="/arvio-wordmark.svg" alt="ARVIO" />
        </div>
        <h1 className="profile-heading">{manageMode ? "Manage Profiles" : "Who's watching?"}</h1>

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
              <span>{openingProfileId === profile.id ? "Opening..." : profile.name}</span>
            </button>
          ))}

          {profiles.length < 5 && (
            <button type="button" className="profile-pick" onClick={() => setDialog({ mode: "add" })}>
              <div className="avatar-tile add">
                <Plus size={48} />
              </div>
              <span>Add Profile</span>
            </button>
          )}
        </div>

        <button type="button" className="manage-profiles-btn" onClick={() => setManageMode(!manageMode)}>
          {manageMode ? "Done" : "Manage Profiles"}
        </button>

        {!auth && (
          <button
            type="button"
            className="cloud-connect-btn"
            onClick={(event) => {
              event.preventDefault();
              event.stopPropagation();
              goToLogin();
            }}
          >
            <Cloud size={18} /> Connect to Cloud
          </button>
        )}
      </div>

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
