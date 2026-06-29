import type { Profile } from "./types";

/** Netflix-style profile colors — mirrors Android ProfileColors.colors (ARGB longs). */
export const profileColors = [
  0xffe50914, // Netflix Red
  0xff1db954, // Green
  0xff3b82f6, // Blue
  0xfff59e0b, // Orange
  0xff8b5cf6, // Purple
  0xffec4899, // Pink
  0xff14b8a6, // Teal
  0xff6366f1  // Indigo
];

export function colorToCss(argb: number): string {
  const rgb = (argb & 0xffffff).toString(16).padStart(6, "0");
  return `#${rgb}`;
}

export const TOTAL_AVATARS = 84;

export const avatarCategories: Array<{ label: string; ids: number[] }> = [
  { label: "Animals", ids: [1, 2, 3, 4, 5, 6, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39] },
  { label: "Characters", ids: [7, 8, 9, 10, 11, 12, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54] },
  { label: "Media", ids: [13, 14, 15, 16, 17, 18, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69] },
  { label: "Nature", ids: [19, 20, 21, 22, 23, 24, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84] }
];

export function avatarSrc(avatarId: number): string {
  return `/avatars/avatar_${avatarId}.png`;
}

/** Gradient backgrounds per avatar — ported from Android AvatarRegistry.gradientColors. */
const AVATAR_GRADIENTS: Record<number, [string, string]> = {
  1: ["#2a1800", "#3d2508"], 2: ["#1a1508", "#2d2010"], 3: ["#2a1400", "#3a2008"], 4: ["#1a1810", "#2a2518"],
  5: ["#0a1520", "#152535"], 6: ["#101a10", "#1a2a1a"], 7: ["#1a1030", "#2a1a45"], 8: ["#0a1a10", "#152a18"],
  9: ["#18182a", "#252540"], 10: ["#2a1a08", "#3a2810"], 11: ["#1a1a1a", "#2a2828"], 12: ["#0a0a20", "#15153a"],
  13: ["#2a0a18", "#3a1025"], 14: ["#1a1a20", "#2a2a35"], 15: ["#101020", "#1a1a35"], 16: ["#1a0a20", "#2a1535"],
  17: ["#2a2000", "#3a3008"], 18: ["#2a0a00", "#3a1508"], 19: ["#0a1a08", "#152a12"], 20: ["#1a0a08", "#2a1510"],
  21: ["#1a1025", "#2a1a3a"], 22: ["#081a18", "#102a28"], 23: ["#0a1510", "#15251a"], 24: ["#2a2008", "#3a3010"],
  25: ["#1a1008", "#2a1a10"], 26: ["#1a1a20", "#2a2535"], 27: ["#2a1a08", "#3a2510"], 28: ["#0a1a08", "#152a15"],
  29: ["#1a1508", "#2a2010"], 30: ["#2a1a00", "#3a2808"], 31: ["#2a1800", "#3a2508"], 32: ["#1a1a1a", "#2a2828"],
  33: ["#1a1a20", "#2a2530"], 34: ["#0a1520", "#152538"], 35: ["#1a0a18", "#2a1528"], 36: ["#1a1025", "#2a1a38"],
  37: ["#0a1510", "#15251a"], 38: ["#1a1510", "#2a2018"], 39: ["#1a1508", "#2a2010"], 40: ["#1a1008", "#2a1a10"],
  41: ["#1a1a20", "#2a2535"], 42: ["#2a1020", "#3a1830"], 43: ["#1a1508", "#2a2010"], 44: ["#1a1025", "#2a1a38"],
  45: ["#2a1a00", "#3a2808"], 46: ["#2a0a18", "#3a1025"], 47: ["#2a1a00", "#3a2808"], 48: ["#1a1a28", "#2a2538"],
  49: ["#1a1a20", "#2a2530"], 50: ["#2a1800", "#3a2508"], 51: ["#2a0a0a", "#3a1515"], 52: ["#1a1020", "#2a1830"],
  53: ["#0a1520", "#152538"], 54: ["#2a1a08", "#3a2510"], 55: ["#1a1a1a", "#2a2828"], 56: ["#1a1a20", "#2a2a35"],
  57: ["#2a1a08", "#3a2510"], 58: ["#1a1025", "#2a1a38"], 59: ["#2a2000", "#3a3008"], 60: ["#1a0a25", "#2a1538"],
  61: ["#1a1a1a", "#2a2828"], 62: ["#1a1a20", "#2a2530"], 63: ["#1a1a1a", "#2a2828"], 64: ["#2a0a18", "#3a1025"],
  65: ["#1a1508", "#2a2010"], 66: ["#0a1520", "#152538"], 67: ["#0a1510", "#15251a"], 68: ["#1a0a08", "#2a1510"],
  69: ["#2a0a0a", "#3a1515"], 70: ["#1a1025", "#2a1a38"], 71: ["#0a1520", "#152538"], 72: ["#2a1020", "#3a1830"],
  73: ["#2a0a10", "#3a1518"], 74: ["#2a0a18", "#3a1025"], 75: ["#2a1020", "#3a1830"], 76: ["#0a1a08", "#152a12"],
  77: ["#0a1510", "#15251a"], 78: ["#2a1008", "#3a1a10"], 79: ["#0a0a20", "#15153a"], 80: ["#0a1a08", "#152a15"],
  81: ["#1a1a28", "#2a2538"], 82: ["#1a1020", "#2a1830"], 83: ["#2a1008", "#3a1a10"], 84: ["#0a1a08", "#152a12"]
};

export function avatarGradient(avatarId: number): [string, string] {
  return AVATAR_GRADIENTS[avatarId] ?? ["#1a1a1a", "#2d2d2d"];
}

/** Resolve a custom uploaded avatar (base64 from account_sync_state.payload.profileAvatarImagesById). */
export function customAvatarSrc(profile: Profile, avatarImages: Record<string, string>): string | null {
  if (!profile.avatarImageVersion || profile.avatarImageVersion <= 0) return null;
  const base64 = avatarImages[profile.id];
  if (!base64) return null;
  return base64.startsWith("data:") ? base64 : `data:image/jpeg;base64,${base64}`;
}
