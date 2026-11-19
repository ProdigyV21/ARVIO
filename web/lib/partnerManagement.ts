import { AuthClient } from "./auth";
import { config } from "./config";

export type PartnerPermissions = { editCatalogs: boolean; addConnections: boolean; editAppearance: boolean; editPlayback: boolean; editMetadata: boolean };
export type PartnerConfiguration = { settings: Record<string, boolean | string>; catalogs: Record<string, unknown>[]; collectionUrls: string[]; serviceIds?: string[] };
export type PartnerIptvOptions = { mode: "auto" | "xtream" | "m3u"; liveTv: boolean; movies: boolean; series: boolean; epg: boolean; catchup: boolean };
export const defaultPartnerIptv = (): PartnerIptvOptions => ({ mode: "auto", liveTv: true, movies: true, series: true, epg: true, catchup: true });
export type PartnerTemplate = { id: string; name: string; config: PartnerConfiguration; updatedAt: string };
export type PartnerPackage = { id: string; name: string; templateId: string | null; serviceIds: string[]; permissions: PartnerPermissions; updatedAt: string };
export type PartnerService = { id: string; name: string; kind: "emby" | "jellyfin" | "plex" | "addon" | "iptv"; credentialMode: "customer" | "assigned"; configured: boolean; iptv?: PartnerIptvOptions };
export type PartnerCustomer = { id: string; name: string; email: string; status: "invited" | "active" | "suspended"; packageId: string | null; createdAt: string; configurationMode: "linked" | "copy"; deliveryRevision: number; credentialServiceIds: string[]; connectionServiceIds?: string[]; history: { revision: number; publishedAt: string }[] };
export type PartnerDevice = { id: string; name: string; customerId: string; platform: string; registeredAt: string; lastSeenAt: string; appliedRevision: number; revokedAt?: string };
export type PartnerOrganization = {
  id: string; name: string; revision: number; seatsUsed: number;
  billing: { status: "active" | "grace" | "expired" | "unpaid"; seats: number; tier: string | null; paidUntil: string | null; graceUntil: string | null; source?: "pilot"; privatePilot?: boolean };
  plans: Record<string, { seats: number; cents: number }>;
  templates: PartnerTemplate[]; packages: PartnerPackage[]; services: PartnerService[]; customers: PartnerCustomer[]; devices: PartnerDevice[];
  audit: { id: string; actor: string; action: string; target: string | null; at: string }[];
};
export type PartnerResponse = { organization?: PartnerOrganization | null; proof?: string; expiresAt?: string; invitationSent?: boolean; target?: string };

export const partnerAuth = new AuthClient();
export async function partnerRequest<T = PartnerResponse>(operation: string, body: Record<string, unknown> = {}, auth = partnerAuth): Promise<T> {
  const token = await auth.accessToken();
  const response = await fetch(`${config.netlifyBackendUrl.replace(/\/+$/, "")}/partner-api`, {
    method: "POST", cache: "no-store", headers: { Authorization: `Bearer ${token}`, "content-type": "application/json" },
    body: JSON.stringify({ ...body, operation }), signal: AbortSignal.timeout(20000)
  });
  const result = await response.json();
  if (!response.ok) throw Object.assign(new Error(result.error || "Partner request failed"), { status: response.status });
  return result as T;
}
export const permissionLabels: Record<keyof PartnerPermissions, string> = {
  editCatalogs: "Edit collections and catalogs", addConnections: "Add personal connections",
  editAppearance: "Change appearance", editPlayback: "Change playback preferences", editMetadata: "Change metadata providers"
};
export const emptyPartnerConfig = (): PartnerConfiguration => ({ settings: {}, catalogs: [], collectionUrls: [] });
