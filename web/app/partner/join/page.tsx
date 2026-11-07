"use client";
import { useEffect, useState, type FormEvent } from "react";
import { ArrowLeft, Check, ShieldCheck } from "lucide-react";
import { partnerAuth, partnerRequest, permissionLabels, type PartnerPermissions } from "@/lib/partnerManagement";
import "@/components/partner/partner.css";

export default function JoinPartner() {
  const [token, setToken] = useState("");
  const [signedIn, setSignedIn] = useState(false);
  const [details, setDetails] = useState<{ name: string; packageName?: string; permissions: PartnerPermissions } | null>(null);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState("");
  const [accepted, setAccepted] = useState(false);
  useEffect(() => {
    setToken(window.location.hash.slice(1));
    setSignedIn(!!partnerAuth.session);
  }, []);
  useEffect(() => {
    if (!signedIn || !token || accepted) return;
    let cancelled = false;
    setBusy(true);
    partnerRequest<typeof details>("invitation", { token }).then((result) => { if (!cancelled) setDetails(result); })
      .catch((caught) => { if (!cancelled) setError(caught.message); }).finally(() => { if (!cancelled) setBusy(false); });
    return () => { cancelled = true; };
  }, [signedIn, token, accepted]);
  async function login(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setError(""); setBusy(true);
    const data = new FormData(event.currentTarget);
    try { await partnerAuth.signIn(String(data.get("email")), String(data.get("password"))); setSignedIn(true); }
    catch (caught) { setError(caught instanceof Error ? caught.message : "Sign-in failed"); }
    finally { setBusy(false); }
  }
  return <main className="partner-app partner-auth"><a href="/" className="partner-wordmark"><img src="/arvio-icon-192.png" alt="" /><img className="partner-logo-wordmark" src="/arvio-wordmark.svg" alt="ARVIO" /> <span>Invitation</span></a><section className="partner-auth-form">
    {accepted ? <><Check size={30} className="partner-accent" /><h1>You are connected</h1><p>Your compatible ARVIO devices will receive your managed configuration after signing in. Your personal lists and history remain yours.</p><a className="partner-primary partner-secondary" href="/">Open ARVIO</a></> : <>
      <ShieldCheck size={30} className="partner-accent" /><h1>{details ? `Join ${details.name}` : "Your ARVIO invitation"}</h1>
      {error && <div role="alert" className="partner-message error">{error}</div>}
      {!token ? <p>This invitation link is incomplete. Open the full link from your invitation email.</p> : !signedIn ? <><p>Sign in with the ARVIO account matching the invitation email.</p><form onSubmit={login}><label>Email<input name="email" type="email" autoComplete="username" required /></label><label>Password<input name="password" type="password" autoComplete="current-password" required /></label><button disabled={busy} className="partner-primary">Sign in</button><a href="https://auth.arvio.tv" target="_blank" rel="noreferrer">Create an account or reset your password</a></form></> : details ? <>
        <p>Signed in as <strong>{partnerAuth.session?.email}</strong></p>
        {details.packageName && <p>Package: <strong>{details.packageName}</strong></p>}
        <p>The administrator can assign connections, configure your home screen and suspend managed access. They cannot read your personal watch history or your Trakt/Simkl credentials.</p>
        <h2>Your permissions</h2><ul>{Object.entries(permissionLabels).map(([key, label]) => <li key={key}>{label}: {details.permissions[key as keyof PartnerPermissions] ? "Allowed" : "Managed by administrator"}</li>)}</ul>
        <p>You can leave through your managed-account settings. This removes managed access, not your ARVIO account or personal history.</p>
        <button className="partner-primary" disabled={busy} onClick={async () => { setBusy(true); setError(""); try { await partnerRequest("accept-invitation", { token, consent: true }); setAccepted(true); window.history.replaceState(null, "", "/partner/join"); } catch (caught) { setError(caught instanceof Error ? caught.message : "Could not accept invitation"); } finally { setBusy(false); } }}>Accept managed access</button>
      </> : busy ? <p role="status">Checking invitation...</p> : null}
      {signedIn && <button className="partner-text" onClick={() => { partnerAuth.signOut(); setSignedIn(false); setDetails(null); setError(""); }}>Use another account</button>}
    </>}
  </section><a href="/" className="partner-back"><ArrowLeft size={16} />Back to ARVIO</a></main>;
}
