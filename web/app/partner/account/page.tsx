"use client";
import { useEffect, useState, type FormEvent } from "react";
import { ArrowLeft, LockKeyhole, LogOut, Save, ShieldCheck } from "lucide-react";
import { partnerAuth, partnerRequest, permissionLabels, type PartnerPermissions, type PartnerIptvOptions } from "@/lib/partnerManagement";
import { IptvCredentialFields } from "@/components/partner/IptvCredentialFields";
import "@/components/partner/partner.css";

type Membership = {
  managed: boolean; name?: string; status?: string; billingStatus?: string; active?: boolean;
  permissions?: PartnerPermissions; revision?: number;
  services?: Array<{ id: string; name: string; kind: string; credentialMode: string; credentialsSupplied: boolean; iptv?: PartnerIptvOptions }>;
};

export default function ManagedAccount() {
  const [signedIn, setSignedIn] = useState(false);
  const [membership, setMembership] = useState<Membership | null>(null);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState("");
  const [notice, setNotice] = useState("");
  const [leaving, setLeaving] = useState(false);
  const [editing, setEditing] = useState<string | null>(null);
  useEffect(() => setSignedIn(!!partnerAuth.session), []);
  useEffect(() => {
    if (!signedIn) return;
    let disposed = false;
    setBusy(true);
    partnerRequest<Membership>("membership").then(value => { if (!disposed) setMembership(value); })
      .catch(caught => { if (!disposed) setError(caught.message); }).finally(() => { if (!disposed) setBusy(false); });
    return () => { disposed = true; };
  }, [signedIn]);
  async function login(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setBusy(true); setError("");
    const data = new FormData(event.currentTarget);
    try { await partnerAuth.signIn(String(data.get("email")), String(data.get("password"))); setSignedIn(true); }
    catch (caught) { setError(caught instanceof Error ? caught.message : "Sign-in failed"); }
    finally { setBusy(false); }
  }
  async function credentials(event: FormEvent<HTMLFormElement>, serviceId: string) {
    event.preventDefault(); setBusy(true); setError(""); setNotice("");
    const form = event.currentTarget;
    const credentials = Object.fromEntries(new FormData(form).entries());
    try {
      await partnerRequest("customer-credentials", { serviceId, credentials });
      form.reset(); setEditing(null);
      setMembership(await partnerRequest<Membership>("membership"));
      setNotice("Credentials saved. Your devices will update when idle.");
    } catch (caught) { setError(caught instanceof Error ? caught.message : "Could not save credentials"); }
    finally { setBusy(false); }
  }
  async function leave() {
    setBusy(true); setError("");
    try { await partnerRequest("leave", { confirm: true }); setMembership({ managed: false }); setLeaving(false); setNotice("Managed access removed. Your personal account and history are unchanged."); }
    catch (caught) { setError(caught instanceof Error ? caught.message : "Could not leave"); }
    finally { setBusy(false); }
  }
  return <main className="partner-app partner-auth">
    <a className="partner-wordmark" href="/"><img src="/arvio-icon-192.png" alt="" /><img className="partner-logo-wordmark" src="/arvio-wordmark.svg" alt="ARVIO" /> <span>Managed account</span></a>
    <section className="partner-auth-form">
      <ShieldCheck size={30} className="partner-accent" /><h1>{membership?.name || "Your managed account"}</h1>
      {error && <div role="alert" className="partner-message error">{error}</div>}
      {notice && <p role="status" className="partner-message">{notice}</p>}
      {!signedIn ? <form onSubmit={login}>
        <label>Email<input name="email" type="email" autoComplete="username" required /></label>
        <label>Password<input name="password" type="password" autoComplete="current-password" required /></label>
        <button className="partner-primary" disabled={busy}>Sign in</button>
        <a href="https://auth.arvio.tv" target="_blank" rel="noreferrer">Create an account or reset your password</a>
      </form> : <>
        <p>{partnerAuth.session?.email}</p>
        {!membership ? <p role="status">{busy ? "Loading membership..." : "Membership unavailable. Try again later."}</p> : !membership.managed ? <p>No managed membership is connected to this account.</p> : <>
          <p>Status: <strong>{membership.active ? "Active" : membership.status === "active" ? "Access unavailable" : membership.status}</strong></p>
          <h2>Connections</h2>
          {(membership.services || []).map(service => <div key={service.id} className="partner-account-connection">
            <div><strong>{service.name}</strong><span>{service.kind.toUpperCase()}</span></div>
            {service.credentialMode === "assigned" ? <p><LockKeyhole size={14} /> Configured by your administrator</p> : editing === service.id ? <form onSubmit={event => void credentials(event, service.id)}>
              {service.kind === "addon" ? <label>Manifest URL<input name="manifestUrl" type="url" autoComplete="off" required /></label> : service.kind === "iptv" ? <IptvCredentialFields options={service.iptv} /> : service.kind === "plex" ? <label>Plex token<input name="token" type="password" autoComplete="new-password" required /></label> : <>
                <label>Username<input name="username" autoComplete="off" required /></label>
                <label>Password<input name="password" type="password" autoComplete="new-password" required /></label>
              </>}
              <div className="partner-actions"><button className="partner-primary" disabled={busy}><Save size={16} />Save credentials</button><button type="button" disabled={busy} onClick={() => setEditing(null)}>Cancel</button></div>
            </form> : <button disabled={!membership.active || busy} onClick={() => { setEditing(service.id); setNotice(""); }}>{service.credentialsSupplied ? "Replace credentials" : "Add your credentials"}</button>}
          </div>)}
          {!membership.services?.length && <p>No connections assigned.</p>}
          <h2>Your permissions</h2><ul>{Object.entries(permissionLabels).map(([key, label]) => <li key={key}>{label}: {membership.permissions?.[key as keyof PartnerPermissions] ? "Allowed" : "Managed by administrator"}</li>)}</ul>
          <h2>Leave managed access</h2><p>Your personal lists, watch history, login and separately purchased Premium remain yours. Managed services and included Partner access will be removed.</p>
          {leaving ? <div className="partner-actions" role="group" aria-label="Confirm leaving"><button disabled={busy} className="partner-danger" onClick={() => void leave()}>Confirm leave</button><button disabled={busy} onClick={() => setLeaving(false)}>Cancel</button></div> : <button className="partner-danger" disabled={busy} onClick={() => setLeaving(true)}>Leave membership</button>}
        </>}
        <button className="partner-text" disabled={busy} onClick={() => { partnerAuth.signOut(); setSignedIn(false); setMembership(null); setEditing(null); setNotice(""); setError(""); }}><LogOut size={16} />Sign out</button>
      </>}
    </section><a className="partner-back" href="/"><ArrowLeft size={16} />Back to ARVIO</a>
  </main>;
}
