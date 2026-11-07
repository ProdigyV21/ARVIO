import { useCallback, useEffect, useRef, useState, type MutableRefObject } from "react";
import { installAddon } from "../addons";
import { parseCustomCollections } from "../customCollections";
import { proxiedUrl, textRequest } from "../http";
import { partnerRequest } from "../partnerManagement";
import { assertManagedEdit, managedSettings, overlayManagedSettings, resolveManagedResources, type ManagedDelivery, type ManagedResources } from "../managedConfiguration";
import { loadStored, saveStored } from "../storage";
import type { AppSettings } from "../types";
import { authClient } from "./clients";

export function useManagedConfiguration(accountId: string | undefined, profileId: string | null, playing: MutableRefObject<boolean>) {
  const enabled = process.env.NEXT_PUBLIC_PARTNER_MANAGEMENT === "true";
  const [state, setState] = useState<{ accountId: string; resources: ManagedResources | null; delivery: ManagedDelivery } | null>(null);
  const [failure, setFailure] = useState("");
  const preferenceKey = `arvio.managed.preferences.${accountId || "none"}.${profileId || "none"}`;
  const [preferences, setPreferences] = useState<{ key: string; value: Partial<AppSettings> }>({ key: "", value: {} });
  const ownState = state?.accountId === accountId ? state : null;
  const resources = ownState?.resources || null;
  const effective = useRef({ resources, delivery: ownState?.delivery || null, overrides: preferences.key === preferenceKey ? preferences.value : {} });
  effective.current = { resources, delivery: ownState?.delivery || null, overrides: preferences.key === preferenceKey ? preferences.value : {} };

  useEffect(() => {
    setPreferences({ key: preferenceKey, value: loadStored(preferenceKey, {}) });
  }, [preferenceKey]);

  useEffect(() => {
    if (!enabled || !accountId) { setState(null); setFailure(""); return; }
    let disposed = false, inFlight = false, lastAttempt = 0, lastSuccess = 0, failures = 0, leaseUntil = 0;
    let applied: ManagedResources | null = null;
    let pending: { resources: ManagedResources | null; delivery: ManagedDelivery } | null = null;
    const deviceKeyName = `arvio.managed.device.${accountId}`;
    let deviceKey = loadStored<string>(deviceKeyName, "");
    if (!/^[\w-]{32,128}$/.test(deviceKey)) { deviceKey = crypto.randomUUID(); saveStored(deviceKeyName, deviceKey); }
    const apply = () => {
      if (!pending || disposed || playing.current || authClient.session?.userId !== accountId) return;
      applied = pending.resources;
      setState({ accountId, ...pending }); pending = null;
    };
    const check = async () => {
      if (applied && leaseUntil <= Date.now() && !playing.current) pending = { resources: null, delivery: { ...applied.delivery, active: false, reason: "offline_expired" } };
      apply();
      if (disposed || inFlight || playing.current || document.visibilityState === "hidden") return;
      const now = Date.now();
      const interval = failures ? Math.min(300000, 30000 * 2 ** Math.min(failures, 4)) : 120000;
      if (now - lastAttempt < 30000 || now - (failures ? lastAttempt : lastSuccess) < interval) return;
      lastAttempt = now; inFlight = true;
      try {
        const delivery = await partnerRequest<ManagedDelivery>("delivery", { device: {
          key: deviceKey, name: "ARVIO Web", platform: "web", appliedRevision: applied?.delivery.revision || 0
        } }, authClient);
        if (disposed || authClient.session?.userId !== accountId) return;
        let next: ManagedResources | null = null;
        if (delivery.managed && delivery.active) {
          if (applied && applied.delivery.organizationId === delivery.organizationId && applied.delivery.revision === delivery.revision) next = { ...applied, delivery };
          else next = await resolveManagedResources(delivery, {
            addon: installAddon,
            collection: async url => parseCustomCollections(await textRequest(proxiedUrl(url), { signal: AbortSignal.timeout(20000) }), url)
          });
        }
        if (disposed || authClient.session?.userId !== accountId) return;
        leaseUntil = Date.parse(delivery.leaseUntil || "") || 0;
        pending = { resources: next, delivery }; apply(); setFailure(""); failures = 0; lastSuccess = Date.now();
      } catch (error) {
        if (disposed) return;
        failures++;
        if ([401, 403].includes((error as { status?: number }).status || 0)) {
          pending = { resources: null, delivery: { managed: true, active: false, reason: "access_unavailable" } }; apply();
        }
        setFailure("Managed configuration could not be refreshed. Retrying automatically.");
      } finally { inFlight = false; }
    };
    void check();
    const timer = window.setInterval(() => void check(), 5000);
    const visible = () => { if (document.visibilityState === "visible") void check(); };
    window.addEventListener("online", visible); document.addEventListener("visibilitychange", visible);
    return () => { disposed = true; clearInterval(timer); window.removeEventListener("online", visible); document.removeEventListener("visibilitychange", visible); };
  }, [enabled, accountId, playing]);

  const applySettings = useCallback((personal: AppSettings) => overlayManagedSettings(personal, effective.current.resources, effective.current.overrides), []);
  const recordPreferences = useCallback((patch: Partial<AppSettings>) => {
    const current = effective.current;
    assertManagedEdit(patch, current.delivery);
    if (!current.resources?.delivery.config) return;
    const managed = managedSettings(current.resources.delivery.config);
    const safe = Object.fromEntries(Object.entries(patch).filter(([key, value]) => key in managed && ["boolean", "string"].includes(typeof value)));
    if (!Object.keys(safe).length) return;
    const value = { ...current.overrides, ...safe };
    setPreferences({ key: preferenceKey, value }); saveStored(preferenceKey, value);
    effective.current = { ...current, overrides: value };
  }, [preferenceKey]);
  return { resources, overrides: effective.current.overrides, delivery: ownState?.delivery || null, failure, applySettings, recordPreferences, ref: effective };
}
