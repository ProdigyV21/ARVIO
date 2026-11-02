// App store (lib/store.tsx): The store's public types.
import type { AddonInstallMode } from "../addonInstall";
import type { TraktDeviceCode } from "../trakt";
import type { SimklPinCode } from "../simkl";
import type { TrackingPreferences } from "../sync";
import type { ManagedDelivery } from "../managedConfiguration";
import type { AppSettings, AuthSession, Category, InstalledAddon, IptvChannel, IptvProgram, CatalogConfig, IptvSnapshot, MediaItem, NavSection, Profile, StreamSource } from "../types";

export type AppView = "profiles" | "login" | "app";

export interface AppStore {
  view: AppView;
  partnerLinkReady: boolean;
  cloudLoginRequired: boolean;
  managedStatus?: Pick<ManagedDelivery, "managed" | "active" | "name" | "permissions" | "reason"> | null;
  profiles: Profile[];
  activeProfile: Profile | null;
  avatarImages: Record<string, string>;
  manageMode: boolean;
  setManageMode: (value: boolean) => void;
  selectProfile: (profile: Profile, pin?: string) => Promise<void>;
  createProfile: (name: string, avatarColor: number, avatarId: number, extra?: Partial<Profile>) => Promise<Profile>;
  updateProfile: (profile: Profile) => Promise<Profile>;
  deleteProfile: (id: string) => Promise<void>;
  switchProfile: () => void;
  goToLogin: () => void;
  backToProfiles: () => void;

  section: NavSection;
  setSection: (section: NavSection) => void;
  categories: Category[];
  catalogConfigs: CatalogConfig[];
  loadCatalogRow: (catalog: CatalogConfig) => Promise<Category | null>;
  homeServerRows: Category[];
  continueWatching: MediaItem[];
  watchlist: MediaItem[];
  isWatched: (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) => boolean;
  markWatchedLocally: (item: { mediaType: MediaItem["mediaType"]; id: number; season?: number | null; episode?: number | null }, watched?: boolean) => void;
  selected: MediaItem | null;
  streams: StreamSource[];
  selectedEpisode: { season: number; episode: number } | null;
  loadEpisodeStreams: (item: MediaItem, season: number, episode: number) => Promise<StreamSource[]>;
  advanceEpisode: () => Promise<boolean>;
  activeStream: StreamSource | null;
  activeChannel: IptvChannel | null;
  addons: InstalledAddon[];
  addonsReady: boolean;
  iptvSnapshot: IptvSnapshot;
  query: string;
  setQuery: (value: string) => void;
  results: MediaItem[];
  settings: AppSettings;
  setSettings: (next: AppSettings) => void;
  updateSettings: (patch: Partial<AppSettings>) => void;
  auth: AuthSession | null;
  traktConnected: boolean;
  mdblistConnected: boolean;
  simklConnected: boolean;
  trackingPreferences: TrackingPreferences;
  updateTrackingPreferences: (patch: Partial<TrackingPreferences>) => Promise<void>;
  deviceCode: TraktDeviceCode | null;
  simklDeviceCode: SimklPinCode | null;
  busy: string;
  toast: string | null;
  setToast: (value: string | null) => void;

  refreshData: (profileIdOverride?: string | null) => Promise<void>;
  refreshIptv: (options?: { quiet?: boolean; fresh?: boolean }) => Promise<void>;
  loadIptvGuide: (channels: IptvChannel[]) => Promise<void>;
  /** The Home "Favorite TV" row's channels, in the user's favorite order. */
  favoriteTvChannels: IptvChannel[];
  openDetails: (item: MediaItem) => Promise<void>;
  closeDetails: () => void;
  playStream: (stream: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean }) => void;
  playTrailer: (item: MediaItem) => Promise<void>;
  playChannel: (channel: IptvChannel) => void;
  recordChannelPlayback: (channel: IptvChannel) => void;
  playCatchup: (channel: IptvChannel, program: IptvProgram) => void;
  closePlayer: () => void;
  installAddon: (source: string | InstalledAddon, options?: { mode?: AddonInstallMode }) => Promise<InstalledAddon>;
  removeAddon: (addon: InstalledAddon) => Promise<void>;
  setAddonsState: (next: InstalledAddon[]) => Promise<void>;
  signIn: (email: string, password: string, mode: "sign-in" | "sign-up") => Promise<void>;
  signOut: () => void;
  beginTrakt: () => Promise<void>;
  pollTrakt: () => Promise<void>;
  disconnectTrakt: () => void;
  connectMdblist: (key: string) => Promise<void>;
  disconnectMdblist: () => void;
  beginSimkl: () => Promise<void>;
  pollSimkl: () => Promise<void>;
  disconnectSimkl: () => void;
  // Watchlist list-source switcher (Trakt custom lists / collection).
  loadTraktLists: () => Promise<Array<{ id: string; name: string }>>;
  loadTraktListItems: (source: string) => Promise<MediaItem[]>;
  loadTrackerLibrary: (provider: "trakt" | "simkl" | "wetrakr", source: string) => Promise<MediaItem[]>;
  settingsSyncState: "local" | "saved" | "pending" | "error";
  searchState: "idle" | "loading" | "error";

  toggleWatchlist: (item: MediaItem) => Promise<void>;
  toggleWatched: (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null, skipSync?: boolean) => Promise<void>;
  removeFromContinueWatching: (item: MediaItem) => Promise<void>;
  activeContextMenu: ContextMenuTarget | null;
  openContextMenu: (target: ContextMenuTarget) => void;
  closeContextMenu: () => void;
}

export interface ContextMenuTarget {
  item?: MediaItem;
  title?: string;
  subtitle?: string;
  isContinueWatching?: boolean;
  position?: { x: number; y: number } | null;
  actions?: Array<{
    id: string;
    label: string;
    icon: React.ReactNode;
    danger?: boolean;
    action: () => void | Promise<void>;
  }>;
}
