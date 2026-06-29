"use client";

import { avatarGradient, avatarSrc, colorToCss, customAvatarSrc } from "@/lib/profiles";
import type { Profile } from "@/lib/types";

export function ProfileAvatarVisual({ profile, avatarImages = {} }: {
  profile: Profile;
  avatarImages?: Record<string, string>;
}) {
  const custom = customAvatarSrc(profile, avatarImages);
  if (custom) {
    return <img className="avatar-visual" src={custom} alt="" />;
  }
  if (profile.avatarId && profile.avatarId > 0) {
    const [c1, c2] = avatarGradient(profile.avatarId);
    return (
      <div className="avatar-visual" style={{ background: `linear-gradient(180deg, ${c1}, ${c2})` }}>
        <img className="avatar-emoji" src={avatarSrc(profile.avatarId)} alt="" />
      </div>
    );
  }
  return (
    <div className="avatar-visual avatar-letter" style={{ background: colorToCss(profile.avatarColor) }}>
      <span>{profile.name.charAt(0)?.toUpperCase() || "?"}</span>
    </div>
  );
}
