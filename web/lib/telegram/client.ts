// Browser-side Telegram (MTProto) client — the web counterpart of Android's
// native TDLib TelegramClient. It runs GramJS entirely in the browser over
// Telegram's WebSocket transport: QR-code login, session persistence in
// localStorage, chat search, and byte-range file download for streaming.
//
// GramJS is a large, browser-only bundle, so it is loaded lazily on first use
// (never in the Next.js server bundle) via dynamic import().

import type { Api, TelegramClient } from "telegram";
import type { BigInteger } from "big-integer";
import {
  TELEGRAM_API_HASH,
  TELEGRAM_API_ID,
  TELEGRAM_SESSION_KEY,
} from "./config";

export type TgAuthState =
  | { k: "idle" }
  | { k: "initializing" }
  | { k: "waitQr"; url: string }
  | { k: "waitPhone" }
  | { k: "waitCode"; codeLength: number }
  | { k: "waitPassword"; hint?: string }
  | { k: "ready"; firstName: string; userId: string }
  | { k: "error"; message: string };

export interface TgVideo {
  /** Dedupe key: `${fileName}|${size}` — mirrors Android's (fileName, fileSize). */
  key: string;
  fileName: string;
  size: number;
  mime: string;
  caption: string;
  duration: number;
  chatId: string;
  messageId: number;
  /** Raw document, kept for streaming (rebuilds InputDocumentFileLocation). */
  document: Api.Document;
  /** Peer the message came from, for file-reference refresh on expiry. */
  peer: Api.TypePeer;
}

// ---- reactive auth state --------------------------------------------------

let state: TgAuthState = { k: "idle" };
const listeners = new Set<(s: TgAuthState) => void>();

export function getAuthState(): TgAuthState {
  return state;
}

export function subscribe(cb: (s: TgAuthState) => void): () => void {
  listeners.add(cb);
  cb(state);
  return () => listeners.delete(cb);
}

function setState(next: TgAuthState) {
  state = next;
  for (const cb of listeners) cb(next);
}

export function isConnected(): boolean {
  return state.k === "ready";
}

// ---- deferred user inputs (2FA password, phone code) ----------------------

type Deferred<T> = { promise: Promise<T>; resolve: (v: T) => void };
function deferred<T>(): Deferred<T> {
  let resolve!: (v: T) => void;
  const promise = new Promise<T>((r) => (resolve = r));
  return { promise, resolve };
}

let pendingPassword: Deferred<string> | null = null;
let pendingCode: Deferred<string> | null = null;

export function submitPassword(pw: string) {
  pendingPassword?.resolve(pw);
}
export function submitCode(code: string) {
  pendingCode?.resolve(code);
}

// ---- GramJS singleton -----------------------------------------------------

interface Gram {
  client: TelegramClient;
  Api: typeof Api;
  bigInt: (v: number | string) => BigInteger;
}

let gram: Gram | null = null;
let loadingSession = false;

async function loadGram(sessionString: string): Promise<Gram> {
  const [{ TelegramClient, Api }, { StringSession }, bigIntMod] = await Promise.all([
    import("telegram"),
    import("telegram/sessions"),
    import("big-integer"),
  ]);
  const bigInt = (bigIntMod.default ?? bigIntMod) as unknown as (v: number | string) => BigInteger;
  const session = new StringSession(sessionString);
  const client = new TelegramClient(session, TELEGRAM_API_ID, TELEGRAM_API_HASH, {
    connectionRetries: 5,
    deviceModel: "ARVIO Web",
    appVersion: "1.0.0",
    systemVersion: typeof navigator !== "undefined" ? navigator.platform || "Web" : "Web",
    langCode: typeof navigator !== "undefined" ? navigator.language || "en" : "en",
    systemLangCode: typeof navigator !== "undefined" ? navigator.language || "en" : "en",
    // GramJS auto-selects the browser WebSocket transport; keep logs quiet.
    baseLogger: undefined,
  });
  client.setLogLevel?.("error" as never);
  return { client, Api, bigInt };
}

function savedSession(): string {
  if (typeof window === "undefined") return "";
  try {
    return window.localStorage.getItem(TELEGRAM_SESSION_KEY) ?? "";
  } catch {
    return "";
  }
}

function persistSession() {
  if (!gram || typeof window === "undefined") return;
  try {
    const str = (gram.client.session.save() as unknown as string) ?? "";
    if (str) window.localStorage.setItem(TELEGRAM_SESSION_KEY, str);
  } catch {
    /* ignore */
  }
}

function clearSession() {
  if (typeof window === "undefined") return;
  try {
    window.localStorage.removeItem(TELEGRAM_SESSION_KEY);
  } catch {
    /* ignore */
  }
}

async function ensureGram(): Promise<Gram> {
  if (gram) return gram;
  gram = await loadGram(savedSession());
  return gram;
}

/**
 * Restore a previously-saved session on app start. Sets state to `ready` when a
 * valid session exists, otherwise `idle`. Safe to call repeatedly.
 */
export async function restoreSession(): Promise<void> {
  if (typeof window === "undefined") return;
  if (state.k === "ready" || loadingSession) return;
  const saved = savedSession();
  if (!saved) return;
  loadingSession = true;
  try {
    const g = await ensureGram();
    await g.client.connect();
    if (await g.client.isUserAuthorized()) {
      const me = (await g.client.getMe()) as Api.User;
      setState({ k: "ready", firstName: me?.firstName ?? "", userId: String(me?.id ?? "") });
    } else {
      clearSession();
      setState({ k: "idle" });
    }
  } catch {
    setState({ k: "idle" });
  } finally {
    loadingSession = false;
  }
}

// ---- QR authentication ----------------------------------------------------

function base64Url(buf: Uint8Array): string {
  let bin = "";
  for (const b of buf) bin += String.fromCharCode(b);
  return btoa(bin).replace(/\+/g, "-").replace(/\//g, "_").replace(/=+$/, "");
}

export async function startQrAuth(): Promise<void> {
  setState({ k: "initializing" });
  pendingPassword = deferred<string>();
  try {
    // A fresh QR login needs a clean, unauthorized session.
    clearSession();
    gram = await loadGram("");
    const g = gram;
    await g.client.connect();

    const me = (await g.client.signInUserWithQrCode(
      { apiId: TELEGRAM_API_ID, apiHash: TELEGRAM_API_HASH },
      {
        qrCode: async (code: { token: Buffer | Uint8Array }) => {
          const url = `tg://login?token=${base64Url(code.token as Uint8Array)}`;
          setState({ k: "waitQr", url });
        },
        password: async (hint?: string) => {
          pendingPassword = deferred<string>();
          setState({ k: "waitPassword", hint });
          return pendingPassword.promise;
        },
        onError: async (err: Error) => {
          setState({ k: "error", message: friendlyError(err.message) });
          return true; // stop retrying
        },
      }
    )) as Api.User;

    persistSession();
    setState({ k: "ready", firstName: me?.firstName ?? "", userId: String(me?.id ?? "") });
  } catch (e) {
    if (state.k !== "error") {
      setState({ k: "error", message: friendlyError((e as Error)?.message) });
    }
  }
}

// ---- phone / code authentication (fallback) -------------------------------

export async function startPhoneAuth(phone: string): Promise<void> {
  setState({ k: "initializing" });
  try {
    clearSession();
    gram = await loadGram("");
    const g = gram;
    await g.client.connect();

    const me = (await g.client.signInUser(
      { apiId: TELEGRAM_API_ID, apiHash: TELEGRAM_API_HASH },
      {
        phoneNumber: async () => phone,
        phoneCode: async () => {
          pendingCode = deferred<string>();
          setState({ k: "waitCode", codeLength: 5 });
          return pendingCode.promise;
        },
        password: async (hint?: string) => {
          pendingPassword = deferred<string>();
          setState({ k: "waitPassword", hint });
          return pendingPassword.promise;
        },
        onError: async (err: Error) => {
          setState({ k: "error", message: friendlyError(err.message) });
          return true;
        },
      }
    )) as Api.User;

    persistSession();
    setState({ k: "ready", firstName: me?.firstName ?? "", userId: String(me?.id ?? "") });
  } catch (e) {
    if (state.k !== "error") {
      setState({ k: "error", message: friendlyError((e as Error)?.message) });
    }
  }
}

// ---- disconnect -----------------------------------------------------------

export async function disconnect(): Promise<void> {
  try {
    if (gram) {
      await gram.client.invoke(new gram.Api.auth.LogOut()).catch(() => {});
      await gram.client.disconnect().catch(() => {});
      await gram.client.destroy?.().catch(() => {});
    }
  } finally {
    gram = null;
    clearSession();
    setState({ k: "idle" });
  }
}

export function resetToIdle() {
  setState({ k: "idle" });
}

// ---- chat search ----------------------------------------------------------

/**
 * Global search across all the user's chats for video files matching `query`.
 * Runs the Video and Document message filters (like Android's two-filter search)
 * and dedupes by (fileName, size).
 */
const PAGE_SIZE = 100; // max messages.SearchGlobal returns per call
const MAX_PAGES = 3; // paginate up to 300 raw messages per filter

export async function searchVideoMessages(query: string, limit: number): Promise<TgVideo[]> {
  if (!gram || state.k !== "ready") return [];
  const { client, Api, bigInt } = gram;
  const filters = [new Api.InputMessagesFilterVideo(), new Api.InputMessagesFilterDocument()];
  const seen = new Set<string>();
  const out: TgVideo[] = [];

  for (const filter of filters) {
    // SearchGlobal caps each call at 100 results; TDLib (Android) effectively
    // pages through everything. Paginate here so large libraries aren't
    // truncated to the newest 100 — the cursor is (nextRate, lastMsgId, lastPeer).
    let offsetRate = 0;
    let offsetId = 0;
    let offsetPeer: Api.TypeInputPeer = new Api.InputPeerEmpty();

    for (let page = 0; page < MAX_PAGES; page++) {
      const res = await client.invoke(
        new Api.messages.SearchGlobal({
          q: query,
          filter,
          minDate: 0,
          maxDate: 0,
          offsetRate,
          offsetPeer,
          offsetId,
          limit: PAGE_SIZE,
        })
      );
      const messages = (res as { messages?: Api.TypeMessage[] }).messages ?? [];
      if (messages.length === 0) break;

      for (const msg of messages) {
        if (!(msg instanceof Api.Message)) continue;
        const media = msg.media;
        if (!(media instanceof Api.MessageMediaDocument)) continue;
        const doc = media.document;
        if (!(doc instanceof Api.Document)) continue;

        const mime = doc.mimeType ?? "";
        if (!mime.startsWith("video/") && mime !== "application/x-matroska") continue;

        const size = typeof doc.size === "number" ? doc.size : Number(doc.size?.toString() ?? 0);
        const fileName = documentFileName(doc, Api) || defaultName(mime);
        const key = `${fileName}|${size}`;
        if (seen.has(key)) continue;
        seen.add(key);

        out.push({
          key,
          fileName,
          size,
          mime,
          caption: msg.message ?? "",
          duration: documentDuration(doc, Api),
          chatId: peerIdString(msg.peerId, bigInt),
          messageId: msg.id,
          document: doc,
          peer: msg.peerId,
        });
      }

      // Stop when this filter has gathered enough, or the server signalled the
      // last page (fewer than a full page returned).
      if (out.length >= limit || messages.length < PAGE_SIZE) break;

      const last = messages[messages.length - 1] as Api.Message;
      const nextRate = (res as { nextRate?: number }).nextRate;
      offsetRate = nextRate ?? offsetRate;
      offsetId = last.id;
      try {
        offsetPeer = await client.getInputEntity(last.peerId);
      } catch {
        break; // can't resolve the cursor peer — stop paginating this filter
      }
    }
  }
  return out;
}

// ---- byte-range download (streaming) --------------------------------------

/**
 * Download exactly `[start, start+length)` of a Telegram document. GramJS
 * `iterDownload` handles MTProto offset/limit alignment internally; we slice the
 * assembled buffer to the exact window the player asked for. This is the browser
 * equivalent of Android's TelegramStreamingProxy.downloadChunk.
 */
export async function downloadRange(video: TgVideo, start: number, length: number): Promise<Uint8Array> {
  const g = await ensureGram();
  const { client, Api, bigInt } = g;

  const run = async (doc: Api.Document): Promise<Uint8Array> => {
    const location = new Api.InputDocumentFileLocation({
      id: doc.id,
      accessHash: doc.accessHash,
      fileReference: doc.fileReference,
      thumbSize: "",
    });
    const chunks: Uint8Array[] = [];
    let received = 0;
    const iter = client.iterDownload({
      file: location,
      offset: bigInt(start),
      limit: length,
      requestSize: 512 * 1024,
    });
    for await (const chunk of iter) {
      const bytes = chunk as unknown as Uint8Array;
      chunks.push(bytes);
      received += bytes.length;
      if (received >= length) break;
    }
    return concat(chunks).subarray(0, length);
  };

  try {
    return await run(video.document);
  } catch (e) {
    // File references expire (~a few hours). Refresh by re-fetching the message,
    // then retry once. Mirrors how Telegram web clients recover mid-playback.
    if (isFileReferenceError((e as Error)?.message)) {
      const fresh = await refreshDocument(video).catch(() => null);
      if (fresh) {
        video.document = fresh;
        return run(fresh);
      }
    }
    throw e;
  }
}

async function refreshDocument(video: TgVideo): Promise<Api.Document | null> {
  const g = gram;
  if (!g) return null;
  const { client, Api } = g;
  const entity = await client.getInputEntity(video.peer);
  const msgs = await client.getMessages(entity, { ids: [video.messageId] });
  const msg = msgs?.[0];
  if (msg instanceof Api.Message && msg.media instanceof Api.MessageMediaDocument) {
    const doc = msg.media.document;
    if (doc instanceof Api.Document) return doc;
  }
  return null;
}

// ---- helpers --------------------------------------------------------------

function documentFileName(doc: Api.Document, Api: typeof import("telegram").Api): string {
  for (const attr of doc.attributes ?? []) {
    if (attr instanceof Api.DocumentAttributeFilename) return attr.fileName;
  }
  return "";
}

function documentDuration(doc: Api.Document, Api: typeof import("telegram").Api): number {
  for (const attr of doc.attributes ?? []) {
    if (attr instanceof Api.DocumentAttributeVideo) return Math.round(attr.duration ?? 0);
  }
  return 0;
}

function defaultName(mime: string): string {
  return mime === "video/mp4" ? "Default_Name.mp4" : "Default_Name.mkv";
}

function peerIdString(peer: Api.TypePeer, bigInt: (v: number | string) => BigInteger): string {
  const p = peer as unknown as { channelId?: unknown; chatId?: unknown; userId?: unknown };
  const raw = p.channelId ?? p.chatId ?? p.userId;
  return raw != null ? String(raw) : "";
}

function isFileReferenceError(msg?: string): boolean {
  return !!msg && /FILE_REFERENCE/i.test(msg);
}

function concat(chunks: Uint8Array[]): Uint8Array {
  const total = chunks.reduce((n, c) => n + c.length, 0);
  const out = new Uint8Array(total);
  let offset = 0;
  for (const c of chunks) {
    out.set(c, offset);
    offset += c.length;
  }
  return out;
}

function friendlyError(raw?: string): string {
  if (!raw) return "Telegram connection failed.";
  const flood = /FLOOD_WAIT_(\d+)/.exec(raw);
  if (flood) return `Too many attempts. Try again in ${flood[1]}s.`;
  if (/PHONE_NUMBER_INVALID/.test(raw)) return "That phone number is not valid.";
  if (/PHONE_CODE_INVALID/.test(raw)) return "That code is not valid.";
  if (/PASSWORD_HASH_INVALID/.test(raw)) return "That 2FA password is not correct.";
  return raw;
}
