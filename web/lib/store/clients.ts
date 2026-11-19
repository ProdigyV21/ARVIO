// App store (lib/store.tsx): The app-wide auth and Trakt clients, created after superseded storage is purged.
import { AuthClient } from "../auth";
import { purgeLegacyStorage } from "../storage";
import { TraktClient } from "../trakt";

// Reclaim space from superseded cache keys before anything reads or writes —
// once localStorage is full every save fails silently, which starved the
// Continue Watching seed and Trakt progress caches on long-lived installs.
purgeLegacyStorage();

export const authClient = new AuthClient();
export const traktClient = new TraktClient();
