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

  return (
    <main className="profile-shell">
      <div className="profile-center">
        <div className="profile-brand">ARVIO</div>
        <h1 className="profile-heading">{manageMode ? "Manage Profiles" : "Who's watching?"}</h1>

        <div className="profile-row">
          {profiles.map((profile) => (
            <button
              key={profile.id}
              className="profile-pick"
              onClick={() => (manageMode ? setDialog({ mode: "edit", profile }) : selectProfile(profile))}
            >
              <div className="avatar-tile">
                <ProfileAvatarVisual profile={profile} avatarImages={avatarImages} />
                {manageMode && (
                  <div className="avatar-edit-overlay"><Pencil size={26} /></div>
                )}
              </div>
              <span>{profile.name}</span>
            </button>
          ))}

          {profiles.length < 5 && (
            <button className="profile-pick" onClick={() => setDialog({ mode: "add" })}>
              <div className="avatar-tile add">
                <Plus size={48} />
              </div>
              <span>Add Profile</span>
            </button>
          )}
        </div>

        <button className="manage-profiles-btn" onClick={() => setManageMode(!manageMode)}>
          {manageMode ? "Done" : "Manage Profiles"}
        </button>

        {!auth && (
          <button className="cloud-connect-btn" onClick={goToLogin}>
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
