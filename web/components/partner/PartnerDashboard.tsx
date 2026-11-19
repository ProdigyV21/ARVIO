"use client";

import { useCallback, useEffect, useRef, useState, type FormEvent, type ReactNode } from "react";
import { ArrowLeft, ArrowUpRight, Check, ChevronRight, Copy, CreditCard, FileStack, History, LayoutDashboard, Link2, LogOut, Monitor, MoreHorizontal, Package, Pencil, Plus, RefreshCw, Search, Send, ShieldCheck, Trash2, Users, X } from "lucide-react";
import { defaultPartnerIptv, emptyPartnerConfig, partnerAuth, partnerRequest, permissionLabels, type PartnerCustomer, type PartnerOrganization, type PartnerPackage, type PartnerPermissions, type PartnerService, type PartnerTemplate } from "@/lib/partnerManagement";
import { IptvCredentialFields } from "./IptvCredentialFields";
import "./partner.css";

type Section = "Overview" | "Customers" | "Templates" | "Packages" | "Connections" | "Devices" | "Activity" | "Billing";
const sections = [
  ["Overview", LayoutDashboard], ["Customers", Users], ["Templates", FileStack], ["Packages", Package],
  ["Connections", Link2], ["Devices", Monitor], ["Activity", History], ["Billing", CreditCard]
] as const;
const dates = (value?: string | null) => value ? new Intl.DateTimeFormat(undefined, { day: "numeric", month: "short", year: "numeric" }).format(new Date(value)) : "Not yet";
const timestamp = (value: string) => new Intl.DateTimeFormat(undefined, { dateStyle: "medium", timeStyle: "short" }).format(new Date(value));
function Badge({ children, status = "neutral" }: { children: ReactNode; status?: string }) { return <span className={`partner-badge ${status}`}>{children}</span>; }
function IconButton({ label, children, ...props }: React.ButtonHTMLAttributes<HTMLButtonElement> & { label: string }) {
  return <button type="button" className="partner-icon" title={label} aria-label={label} {...props}>{children}</button>;
}
function Empty({ title, children, action }: { title: string; children: ReactNode; action?: ReactNode }) {
  return <div className="partner-empty"><h2>{title}</h2><p>{children}</p>{action}</div>;
}
function Modal({ title, children, onClose }: { title: string; children: ReactNode; onClose: () => void }) {
  const ref = useRef<HTMLDialogElement>(null);
  useEffect(() => { ref.current?.showModal(); }, []);
  return <dialog ref={ref} className="partner-dialog" onCancel={(event) => { event.preventDefault(); onClose(); }} aria-label={title}>
    <header><h2>{title}</h2><IconButton label="Close dialog" onClick={onClose}><X size={20} /></IconButton></header>{children}
  </dialog>;
}
const field = (event: FormEvent<HTMLFormElement>, key: string) => String(new FormData(event.currentTarget).get(key) || "");

export function PartnerDashboard() {
  const [signedIn, setSignedIn] = useState(false);
  const [proof, setProof] = useState("");
  const [codeSent, setCodeSent] = useState(false);
  const [org, setOrg] = useState<PartnerOrganization | null>(null);
  const [loaded, setLoaded] = useState(false);
  const [section, setSection] = useState<Section>("Overview");
  const [query, setQuery] = useState("");
  const [filter, setFilter] = useState("all");
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState("");
  const [notice, setNotice] = useState("");
  const [selected, setSelected] = useState<string[]>([]);
  const [dialog, setDialog] = useState<{ kind: string; id?: string } | null>(null);
  const [confirmation, setConfirmation] = useState<{ title: string; text: string; command: Record<string, unknown> } | null>(null);
  const [editorError, setEditorError] = useState("");

  useEffect(() => { setSignedIn(!!partnerAuth.session); }, []);
  const run = useCallback(async (work: () => Promise<void>) => {
    setBusy(true); setError(""); setNotice("");
    try { await work(); }
    catch (caught) {
      setError(caught instanceof Error ? caught.message : "Something went wrong");
      if ((caught as { status?: number }).status === 403) { setProof(""); setCodeSent(false); }
    } finally { setBusy(false); }
  }, []);
  const load = useCallback(async (adminProof = proof) => {
    const result = await partnerRequest("overview", { proof: adminProof });
    setOrg(result.organization || null); setLoaded(true);
  }, [proof]);
  async function command(input: Record<string, unknown>) {
    if (!org) return;
    const response = await partnerRequest("command", { proof, organizationId: org.id, revision: org.revision, command: input });
    if (response.organization) setOrg(response.organization);
    setNotice(response.invitationSent === false ? "Customer saved, but the invitation email could not be sent. Use Resend invitation to retry." : response.invitationSent ? "Invitation sent. Access starts after the customer accepts." : "Changes saved.");
    setDialog(null); setConfirmation(null); setSelected([]);
  }
  function edit(kind: string, id?: string) { setEditorError(""); setDialog({ kind, id }); }
  function submit(input: Record<string, unknown>) { void run(() => command(input)); }
  const customer = org?.customers.find((c) => c.id === dialog?.id);
  const template = org?.templates.find((t) => t.id === dialog?.id);
  const pkg = org?.packages.find((p) => p.id === dialog?.id);
  const service = org?.services.find((s) => s.id === dialog?.id);
  const dialogTitle = dialog?.kind.startsWith("credentials:") ? "Assign customer credentials" : ({ customer: "Invite customer", manage: customer?.name || "Customer", template: template ? "Edit template" : "New template", package: pkg ? "Edit package" : "New package", service: service ? "Edit connection" : "Add connection", copy: "Copy customer configuration" }[dialog?.kind || ""] || "Edit");
  const selectedPackage = (id: string | null) => org?.packages.find((p) => p.id === id)?.name || "No package";
  const customers = (org?.customers || []).filter((c) => (filter === "all" || c.status === filter) && `${c.name} ${c.email} ${selectedPackage(c.packageId)}`.toLowerCase().includes(query.toLowerCase()));
  const activeDevices = org?.devices.filter((d) => !d.revokedAt) || [];
  const pending = activeDevices.filter((d) => d.appliedRevision < (org?.customers.find((c) => c.id === d.customerId)?.deliveryRevision || 0));
  const signOut = () => { partnerAuth.signOut(); setSignedIn(false); setProof(""); setOrg(null); setLoaded(false); setCodeSent(false); };

  if (!signedIn || !proof || !loaded || !org) return <main className="partner-app partner-auth">
    <a className="partner-wordmark" href="/"><img src="/arvio-icon-192.png" alt="" /><img className="partner-logo-wordmark" src="/arvio-wordmark.svg" alt="ARVIO" /> <span>Partner</span></a>
    <section className="partner-auth-form">
      <ShieldCheck size={28} className="partner-accent" />
      <h1>{!signedIn ? "Partner sign-in" : !proof ? "Verify your sign-in" : !loaded ? "Loading workspace" : "Create your workspace"}</h1>
      <p>{!signedIn ? "Use your own ARVIO Cloud account." : !proof ? `Administrator verification for ${partnerAuth.session?.email}.` : !loaded ? "Checking your organization." : "Your billing email is your verified ARVIO account email. Use the same address at Ko-fi checkout."}</p>
      {error && <div role="alert" className="partner-message error">{error}</div>}
      {!signedIn ? <form onSubmit={(event) => { event.preventDefault(); const address = field(event, "email"), password = field(event, "password"); void run(async () => { await partnerAuth.signIn(address, password); setSignedIn(true); }); }}>
        <label>Email<input name="email" type="email" required autoComplete="username" /></label>
        <label>Password<input name="password" type="password" required autoComplete="current-password" /></label>
        <button className="partner-primary" disabled={busy}>Sign in <ChevronRight size={18} /></button>
        <a href="https://auth.arvio.tv" target="_blank" rel="noreferrer">Create an ARVIO account or reset your password <ArrowUpRight size={14} /></a>
      </form> : !proof ? <>
        {codeSent ? <form onSubmit={(event) => { event.preventDefault(); const code = field(event, "code"); void run(async () => { const response = await partnerRequest("verification-complete", { code }); setProof(response.proof!); await load(response.proof); }); }}>
          <label>Email verification code<input name="code" inputMode="numeric" pattern="[0-9]{6}" maxLength={6} autoComplete="one-time-code" required autoFocus /></label>
          <button className="partner-primary" disabled={busy}>Verify sign-in <ShieldCheck size={18} /></button>
        </form> : null}
        <button disabled={busy} className={codeSent ? "partner-secondary" : "partner-primary"} onClick={() => void run(async () => { await partnerRequest("verification-start"); setCodeSent(true); })}>{codeSent ? "Send a new code" : "Send verification code"}<Send size={16} /></button>
        <button className="partner-text" onClick={signOut}>Use another account</button>
      </> : !loaded ? <button disabled={busy} onClick={() => void run(() => load())}><RefreshCw size={16} />Load workspace</button> : <form onSubmit={(event) => { event.preventDefault(); const name = field(event, "name"); void run(async () => { const response = await partnerRequest("create-organization", { proof, name }); setOrg(response.organization!); }); }}>
        <label>Organization name<input name="name" maxLength={100} required placeholder="Your business name" /></label>
        <button className="partner-primary" disabled={busy}>Create workspace <ChevronRight size={18} /></button>
        <p className="partner-muted">No charge. Customer invitations require an active Partner membership.</p>
      </form>}
    </section>
    <a href="/" className="partner-back"><ArrowLeft size={16} />Back to ARVIO</a>
  </main>;

  const actions = <>
    <IconButton label="Refresh workspace" disabled={busy} onClick={() => void run(() => load())}><RefreshCw size={18} className={busy ? "partner-spin" : ""} /></IconButton>
    {section === "Customers" && <button className="partner-primary" disabled={busy || org.billing.status !== "active" || org.seatsUsed >= org.billing.seats} onClick={() => edit("customer")}><Plus size={17} />Invite customer</button>}
    {section === "Templates" && <button className="partner-primary" onClick={() => edit("template")}><Plus size={17} />New template</button>}
    {section === "Packages" && <button className="partner-primary" onClick={() => edit("package")}><Plus size={17} />New package</button>}
    {section === "Connections" && <button className="partner-primary" onClick={() => edit("service")}><Plus size={17} />Add connection</button>}
  </>;
  return <div className="partner-app partner-shell">
    <aside className="partner-sidebar">
      <a href="/partner" className="partner-wordmark"><img src="/arvio-icon-192.png" alt="" /><img className="partner-logo-wordmark" src="/arvio-wordmark.svg" alt="ARVIO" /> <span>Partner</span></a>
      <div className="partner-workspace"><span className="partner-avatar">{org.name.slice(0, 1).toUpperCase()}</span><div><strong>{org.name}</strong><small>Administrator workspace</small></div></div>
      <nav aria-label="Partner navigation">{sections.map(([name, Icon]) => <button key={name} aria-current={section === name ? "page" : undefined} onClick={() => { setSection(name); setQuery(""); setFilter("all"); setSelected([]); }}><Icon size={18} /><span>{name}</span>{name === "Customers" && <small>{org.customers.length}</small>}</button>)}</nav>
      <div className="partner-sidebar-bottom"><Badge status={org.billing.status}>{org.billing.source === "pilot" ? "Private pilot" : org.billing.status === "active" ? "Membership active" : org.billing.status === "grace" ? "Payment overdue" : "Membership required"}</Badge><small>{partnerAuth.session?.email}</small><button className="partner-text" onClick={signOut}><LogOut size={16} />Sign out</button></div>
    </aside>
    <main className="partner-main" aria-busy={busy}>
      <header className="partner-page-header"><div><span className="partner-eyebrow">WORKSPACE</span><h1>{section}</h1></div><div className="partner-actions">{actions}</div></header>
      {error && <div className="partner-message error" role="alert">{error}<button onClick={() => void run(() => load())}>Refresh</button></div>}
      {notice && <div className="partner-message" role="status"><Check size={18} />{notice}<IconButton label="Dismiss notification" onClick={() => setNotice("")}><X size={16} /></IconButton></div>}
      {org.billing.status !== "active" && section !== "Billing" && <div className="partner-message warning"><CreditCard size={18} /><span>{org.billing.status === "grace" ? `Payment is overdue. Managed access continues until ${dates(org.billing.graceUntil)}.` : "Configure your workspace now. Activate a Partner membership before inviting customers."}</span><button onClick={() => setSection("Billing")}>View billing <ChevronRight size={16} /></button></div>}

      {section === "Overview" && <>
        <section className="partner-metrics" aria-label="Workspace statistics">
          <div><span>Customer seats</span><strong>{org.seatsUsed}<small> / {org.billing.seats || "--"}</small></strong><meter min={0} max={org.billing.seats || 1} value={org.seatsUsed} /><small>Active accounts and pending invitations</small></div>
          <div><span>Connected devices</span><strong>{activeDevices.length}</strong><small>{pending.length ? `${pending.length} awaiting configuration` : "All reporting devices up to date"}</small></div>
          <div><span>Packages</span><strong>{org.packages.length}</strong><small>{org.templates.length} configuration templates</small></div>
          <div><span>{org.billing.source === "pilot" ? "Pilot through" : "Paid through"}</span><strong className="partner-date">{dates(org.billing.paidUntil)}</strong><small>{org.billing.tier || "No membership linked"}</small></div>
        </section>
        <div className="partner-overview-columns">
          <section><div className="partner-section-title"><h2>Customer activity</h2><button className="partner-text" onClick={() => setSection("Customers")}>All customers <ChevronRight size={16} /></button></div>
            {org.customers.length ? <CustomerTable customers={org.customers.slice(-6).reverse()} org={org} onOpen={(id) => edit("manage", id)} /> : <Empty title="Your workspace is ready">Start with a template, create a package, then invite your first customer.</Empty>}
          </section>
          <section><div className="partner-section-title"><h2>Workspace setup</h2><ShieldCheck size={18} /></div>
            {[["Templates", org.templates.length, "Choose the home screen and catalogs"], ["Connections", org.services.length, "Register your services"], ["Packages", org.packages.length, "Combine configuration and access"], ["Customers", org.customers.length, "Invite customers to their own accounts"]].map(([name, count, detail]) => <button className="partner-setup-row" key={name} onClick={() => setSection(name as Section)}><span className={`partner-step ${count ? "complete" : ""}`}>{count ? <Check size={16} /> : <Plus size={16} />}</span><span><strong>{name}</strong><small>{detail}</small></span><ChevronRight size={16} /></button>)}
          </section>
        </div>
        <section><div className="partner-section-title"><h2>Recent changes</h2><button className="partner-text" onClick={() => setSection("Activity")}>View activity <ChevronRight size={16} /></button></div><Audit entries={org.audit.slice(0, 5)} /></section>
      </>}

      {section === "Customers" && <>
        <div className="partner-toolbar"><label className="partner-search"><Search size={17} /><input aria-label="Search customers" placeholder="Search customers" value={query} onChange={(event) => setQuery(event.target.value)} /></label><select aria-label="Customer status" value={filter} onChange={(event) => setFilter(event.target.value)}><option value="all">All statuses</option><option value="active">Active</option><option value="invited">Invited</option><option value="suspended">Suspended</option></select><span className="partner-muted">{org.seatsUsed} of {org.billing.seats} seats</span></div>
        {selected.length > 0 && <div className="partner-selection"><span>{selected.length} selected</span><button onClick={() => edit("copy")}><Copy size={16} />Copy configuration to selection</button><button className="partner-text" onClick={() => setSelected([])}>Clear</button></div>}
        {customers.length ? <CustomerTable customers={customers} org={org} selected={selected} onSelect={setSelected} onOpen={(id) => edit("manage", id)} /> : <Empty title={query || filter !== "all" ? "No matching customers" : "No customers yet"}>{query || filter !== "all" ? "Try another search or status." : "Invited customers keep their own login, profiles and watch history."}</Empty>}
      </>}

      {section === "Templates" && <>{org.templates.length ? <div className="partner-table-wrap"><table><thead><tr><th>Template</th><th>Catalogs</th><th>Collection feeds</th><th>Connections</th><th>Last edited</th><th><span className="partner-sr">Actions</span></th></tr></thead><tbody>{org.templates.map((t) => <tr key={t.id}><td><button className="partner-row-link" onClick={() => edit("template", t.id)}><FileStack size={18} />{t.name}</button></td><td>{t.config.catalogs.length}</td><td>{t.config.collectionUrls.length}</td><td>{t.config.serviceIds?.length || 0}</td><td>{dates(t.updatedAt)}</td><td><IconButton label={`Edit ${t.name}`} onClick={() => edit("template", t.id)}><Pencil size={16} /></IconButton><IconButton label={`Delete ${t.name}`} onClick={() => setConfirmation({ title: "Delete template?", text: "Only unassigned templates can be deleted. Customer configuration is not erased.", command: { action: "delete-resource", kind: "templates", id: t.id } })}><Trash2 size={16} /></IconButton></td></tr>)}</tbody></table></div> : <Empty title="Build your first template">Templates contain layout preferences, catalogs, collections and connections, never customer credentials or history.</Empty>}</>}

      {section === "Packages" && <>{org.packages.length ? <div className="partner-table-wrap"><table><thead><tr><th>Package</th><th>Template</th><th>Connections</th><th>Customers</th><th>Actions</th></tr></thead><tbody>{org.packages.map((p) => <tr key={p.id}><td><button className="partner-row-link" onClick={() => edit("package", p.id)}><Package size={18} />{p.name}</button></td><td>{org.templates.find((t) => t.id === p.templateId)?.name || "None"}</td><td>{new Set([...p.serviceIds, ...(org.templates.find(t => t.id === p.templateId)?.config.serviceIds || [])]).size}</td><td>{org.customers.filter((c) => c.packageId === p.id).length}</td><td><button className="partner-secondary" disabled={busy} onClick={() => setConfirmation({ title: `Publish ${p.name}?`, text: "Linked customers receive the latest template and permissions. Devices apply the update when they are online and idle. Copied templates keep their copied settings.", command: { action: "publish-package", id: p.id } })}><Send size={15} />Publish</button><IconButton label={`Edit ${p.name}`} onClick={() => edit("package", p.id)}><Pencil size={16} /></IconButton></td></tr>)}</tbody></table></div> : <Empty title="Create a customer package">Combine a template, connections and permissions. Saving a draft does not change customers until you publish it.</Empty>}</>}

      {section === "Connections" && <>{org.services.length ? <div className="partner-table-wrap"><table><thead><tr><th>Connection</th><th>Type</th><th>Credentials</th><th>Status</th><th><span className="partner-sr">Actions</span></th></tr></thead><tbody>{org.services.map((s) => <tr key={s.id}><td><button className="partner-row-link" onClick={() => edit("service", s.id)}><Link2 size={18} />{s.name}</button></td><td className="partner-capitalize">{s.kind}</td><td>{s.credentialMode === "customer" ? "Customer supplies" : "Assigned per customer"}</td><td><Badge status="active">Configured</Badge></td><td><IconButton label={`Edit ${s.name}`} onClick={() => edit("service", s.id)}><Pencil size={16} /></IconButton><IconButton label={`Delete ${s.name}`} onClick={() => setConfirmation({ title: "Delete connection?", text: "Remove it from packages and published configurations first. This does not delete the external service account.", command: { action: "delete-resource", kind: "services", id: s.id } })}><Trash2 size={16} /></IconButton></td></tr>)}</tbody></table></div> : <Empty title="Connect your services">Add an Emby, Jellyfin, Plex, IPTV or compatible add-on endpoint. Credentials are assigned separately to each customer.</Empty>}<p className="partner-footnote">Credentials are hidden from customer settings. Direct playback still requires service URLs or tokens on their device. External service accounts must be revoked with their provider.</p></>}

      {section === "Devices" && <>{org.devices.length ? <div className="partner-table-wrap"><table><thead><tr><th>Device</th><th>Customer</th><th>Configuration</th><th>Last seen</th><th>Access</th><th /></tr></thead><tbody>{org.devices.map((d) => <tr key={d.id}><td><strong>{d.name}</strong><small>{d.platform}</small></td><td>{org.customers.find((c) => c.id === d.customerId)?.name || "Removed customer"}</td><td>v{d.appliedRevision}<Badge status={pending.includes(d) ? "invited" : "active"}>{pending.includes(d) ? "Pending" : "Applied"}</Badge></td><td>{dates(d.lastSeenAt)}</td><td><Badge status={d.revokedAt ? "suspended" : "active"}>{d.revokedAt ? "Revoked" : "Allowed"}</Badge></td><td>{!d.revokedAt && <button className="partner-text" disabled={busy} onClick={() => setConfirmation({ title: "Revoke managed device access?", text: "This device will stop receiving managed configuration. This does not revoke credentials already issued by an external provider or sign the customer out of their personal account.", command: { action: "revoke-device", id: d.id } })}>Revoke</button>}</td></tr>)}</tbody></table></div> : <Empty title="No devices registered">Devices appear after an invited customer signs in to a compatible ARVIO app.</Empty>}</>}

      {section === "Activity" && <Audit entries={org.audit} />}
      {section === "Billing" && <>
        {org.billing.privatePilot ? <section className="partner-billing-summary"><div><h2>Private pilot</h2><p>{org.billing.source === "pilot" ? `Complimentary access for ${org.billing.seats} customer seats through ${dates(org.billing.paidUntil)}.` : "Your pilot grant is not currently active."}</p><p>No charge, no automatic renewal. Partner memberships are not on sale yet.</p></div><Badge status={org.billing.status}>{org.billing.status}</Badge></section> : <>
        <section className="partner-billing-summary"><div><h2>{org.billing.tier || "Choose your Partner capacity"}</h2><p>Billing email: <strong>{partnerAuth.session?.email}</strong></p><p>{org.billing.paidUntil ? `Paid through ${dates(org.billing.paidUntil)}` : "No Partner payment is linked to this account."}</p></div><Badge status={org.billing.status}>{org.billing.status}</Badge></section>
        <div className="partner-plans">{Object.entries(org.plans).map(([name, plan]) => <article key={name}><h2>{plan.seats} customers</h2><p className="partner-price">${plan.cents / 100}<small>/ month</small></p><p>Managed accounts and Premium access</p><ul><li>Templates and remote configuration</li><li>Packages and customer permissions</li><li>Device status and audit history</li></ul><a href="https://ko-fi.com/arvio/tiers" target="_blank" rel="noreferrer" className="partner-secondary">{org.billing.tier === name ? "Manage on Ko-fi" : "View membership"}<ArrowUpRight size={16} /></a></article>)}</div>
        <section className="partner-billing-notes"><h2>Membership details</h2><p>Use the billing email shown above at checkout. Pending invitations reserve seats. Increasing capacity never happens automatically.</p><p>Ko-fi charges the full price when changing tiers. Review the payment screen before confirming. Cancellation stops future renewals; paid access continues through its paid period.</p><p>The partner handles first-line customer support. Third-party services, content, video bandwidth and custom development are not included. Your customers keep their personal accounts and history if managed access ends.</p></section>
        </>}
      </>}
    </main>

    {dialog && <Modal title={dialogTitle} onClose={() => { if (!busy) setDialog(null); }}>
      {error && <div role="alert" className="partner-message error">{error}<button type="button" disabled={busy} onClick={() => void run(() => load())}>Reload workspace</button></div>}
      {editorError && <div role="alert" className="partner-message error">{editorError}</div>}
      {dialog.kind === "customer" && <form onSubmit={(event) => { event.preventDefault(); submit({ action: "invite-customer", name: field(event, "name"), email: field(event, "email"), packageId: field(event, "packageId") }); }}>
        <label>Name<input name="name" required maxLength={100} autoFocus /></label><label>Email<input name="email" required type="email" autoComplete="off" /></label><PackageSelect packages={org.packages} />
        <p className="partner-muted">An email invitation asks the customer to accept management using their own ARVIO account. No password is shared with you.</p><FormFooter busy={busy} label="Send invitation" onCancel={() => setDialog(null)} />
      </form>}
      {dialog.kind === "template" && <TemplateEditor template={template} services={org.services} busy={busy} onCancel={() => setDialog(null)} onSave={submit} onError={setEditorError} />}
      {dialog.kind === "package" && <PackageEditor pkg={pkg} org={org} busy={busy} onCancel={() => setDialog(null)} onSave={submit} />}
      {dialog.kind === "service" && <ServiceEditor service={service} busy={busy} onCancel={() => setDialog(null)} onSave={submit} />}
      {dialog.kind === "manage" && customer && <>
        <div className="partner-customer-summary"><span className="partner-avatar">{customer.name.slice(0, 1)}</span><div><strong>{customer.email}</strong><small>Joined {dates(customer.createdAt)}</small></div><Badge status={customer.status}>{customer.status}</Badge></div>
        <form onSubmit={(event) => { event.preventDefault(); submit({ action: "assign-package", id: customer.id, packageId: field(event, "packageId") }); }}><PackageSelect packages={org.packages} value={customer.packageId || ""} /><div className="partner-form-row"><span className="partner-muted">Configuration v{customer.deliveryRevision} · {customer.configurationMode}</span><button disabled={busy} className="partner-secondary">Assign and publish</button></div></form>
        <div className="partner-divider" /><h3>Connections</h3>{org.services.filter((s) => (customer.connectionServiceIds || org.packages.find((p) => p.id === customer.packageId)?.serviceIds || []).includes(s.id)).map((s) => <div className="partner-detail-row" key={s.id}><span>{s.name}<small>{customer.credentialServiceIds.includes(s.id) ? "Credentials assigned" : s.credentialMode === "customer" ? "Customer supplies credentials" : "Credentials needed"}</small></span><button onClick={() => edit(`credentials:${s.id}`, customer.id)}>Set credentials <ChevronRight size={15} /></button></div>)}
        {customer.history.length > 0 && <form onSubmit={(event) => { event.preventDefault(); submit({ action: "rollback-customer", id: customer.id, deliveryRevision: Number(field(event, "revision")) }); }}><label>Configuration history<select name="revision">{[...customer.history].reverse().map((h) => <option key={h.revision} value={h.revision}>v{h.revision} · {timestamp(h.publishedAt)}</option>)}</select></label><button disabled={busy} className="partner-secondary"><History size={15} />Restore selected configuration</button></form>}
        <div className="partner-divider" /><div className="partner-form-row">{customer.status === "invited" ? <button disabled={busy} onClick={() => submit({ action: "resend-invitation", id: customer.id })}><Send size={15} />Resend invitation</button> : <button disabled={busy} onClick={() => submit({ action: "set-customer-status", id: customer.id, status: customer.status === "active" ? "suspended" : "active" })}>{customer.status === "active" ? "Suspend managed access" : "Enable managed access"}</button>}<button className="partner-danger" disabled={busy} onClick={() => { setDialog(null); setConfirmation({ title: "Remove customer management?", text: "Managed configuration and assigned credentials will be removed. Their ARVIO login, personal settings and watch history are not deleted. External service subscriptions are not cancelled.", command: { action: "set-customer-status", id: customer.id, status: "removed" } }); }}>Remove customer</button></div>
      </>}
      {dialog.kind.startsWith("credentials:") && customer && <CredentialEditor service={org.services.find((s) => s.id === dialog.kind.split(":")[1])!} customer={customer} busy={busy} onSave={submit} onCancel={() => edit("manage", customer.id)} />}
      {dialog.kind === "copy" && <form onSubmit={(event) => { event.preventDefault(); submit({ action: "copy-configuration", sourceId: field(event, "sourceId"), customerIds: selected, linked: field(event, "mode") === "linked" }); }}>
        <label>Copy from<select name="sourceId" required>{org.customers.filter((c) => !selected.includes(c.id)).map((c) => <option key={c.id} value={c.id}>{c.name}</option>)}</select></label>
        <label>Configuration mode<select name="mode"><option value="copy">Copy once</option><option value="linked">Follow the package template</option></select></label><p className="partner-muted">Applies to {selected.length} customers. Passwords, tokens, watch history and personal lists are never copied. Assign credentials separately.</p><FormFooter busy={busy} label="Copy and publish" onCancel={() => setDialog(null)} />
      </form>}
    </Modal>}
    {confirmation && <Modal title={confirmation.title} onClose={() => { if (!busy) setConfirmation(null); }}><p>{confirmation.text}</p><div className="partner-form-footer"><button disabled={busy} onClick={() => setConfirmation(null)}>Cancel</button><button className="partner-primary" disabled={busy} onClick={() => submit(confirmation.command)}>Confirm</button></div></Modal>}
  </div>;
}

function CustomerTable({ customers, org, onOpen, selected, onSelect }: { customers: PartnerCustomer[]; org: PartnerOrganization; onOpen: (id: string) => void; selected?: string[]; onSelect?: (ids: string[]) => void }) {
  return <div className="partner-table-wrap"><table className="partner-customer-table"><thead><tr>{onSelect && <th className="partner-selection-column"><input type="checkbox" aria-label="Select all displayed customers" checked={customers.length > 0 && customers.every((c) => selected?.includes(c.id))} onChange={(event) => onSelect(event.target.checked ? customers.map((c) => c.id) : [])} /></th>}<th>Customer</th><th className="partner-desktop-column">Package</th><th className="partner-desktop-column">Status</th><th className="partner-desktop-column">Delivery</th><th><span className="partner-sr">Manage</span></th></tr></thead><tbody>{customers.map((c) => {
    const devices = org.devices.filter((d) => d.customerId === c.id && !d.revokedAt);
    const upToDate = devices.length > 0 && devices.every((d) => d.appliedRevision >= c.deliveryRevision);
    const packageName = org.packages.find((p) => p.id === c.packageId)?.name || "No package";
    const delivery = devices.length ? upToDate ? `v${c.deliveryRevision} applied` : `v${c.deliveryRevision} pending` : "Awaiting device";
    return <tr key={c.id}>
      {onSelect && <td className="partner-selection-column"><input type="checkbox" aria-label={`Select ${c.name}`} checked={selected?.includes(c.id) || false} onChange={(event) => onSelect(event.target.checked ? [...selected!, c.id] : selected!.filter((id) => id !== c.id))} /></td>}
      <td><button className="partner-customer-link" onClick={() => onOpen(c.id)}><span className="partner-avatar">{c.name.slice(0, 1).toUpperCase()}</span><span><strong>{c.name}</strong><small>{c.email}</small><span className="partner-mobile-meta"><small>{packageName} / {delivery}</small><Badge status={c.status}>{c.status}</Badge></span></span></button></td>
      <td className="partner-desktop-column">{packageName}</td><td className="partner-desktop-column"><Badge status={c.status}>{c.status}</Badge></td>
      <td className="partner-desktop-column"><span className={`partner-dot ${upToDate ? "active" : ""}`} />{delivery}</td><td><IconButton label={`Manage ${c.name}`} onClick={() => onOpen(c.id)}><MoreHorizontal size={19} /></IconButton></td>
    </tr>;
  })}</tbody></table></div>;
}
function Audit({ entries }: { entries: PartnerOrganization["audit"] }) {
  return entries.length ? <div className="partner-table-wrap"><table><thead><tr><th>Action</th><th>Administrator</th><th>Time</th></tr></thead><tbody>{entries.map((a) => <tr key={a.id}><td className="partner-capitalize">{a.action.replaceAll("-", " ")}</td><td>{a.actor}</td><td>{timestamp(a.at)}</td></tr>)}</tbody></table></div> : <Empty title="No activity yet">Workspace changes will appear here.</Empty>;
}
function FormFooter({ busy, label, onCancel }: { busy: boolean; label: string; onCancel: () => void }) {
  return <div className="partner-form-footer"><button type="button" disabled={busy} onClick={onCancel}>Cancel</button><button disabled={busy} className="partner-primary">{busy ? "Saving..." : label}</button></div>;
}
function PackageSelect({ packages, value = "" }: { packages: PartnerPackage[]; value?: string }) {
  return <label>Package<select name="packageId" aria-label="Package" defaultValue={value}><option value="">No package</option>{packages.map((p) => <option key={p.id} value={p.id}>{p.name}</option>)}</select></label>;
}
function TemplateEditor({ template, services, busy, onSave, onCancel, onError }: { template?: PartnerTemplate; services: PartnerService[]; busy: boolean; onSave: (value: Record<string, unknown>) => void; onCancel: () => void; onError: (value: string) => void }) {
  const cfg = template?.config || emptyPartnerConfig();
  const [settings, setSettings] = useState(cfg.settings);
  const choices = { netflixHomeStyle: "New Home layout", watchlistOnHome: "Watchlist on Home", trailerAutoPlay: "Autoplay trailers", trailerInCards: "Trailers in cards", autoPlayNext: "Autoplay next episode", showEpisodeRatings: "Episode ratings" };
  return <form onSubmit={(event) => {
    event.preventDefault(); onError("");
    try {
      const catalogs = JSON.parse(field(event, "catalogs") || "[]");
      if (!Array.isArray(catalogs)) throw new Error("Catalogs must be a JSON array");
      onSave({ action: "save-template", id: template?.id, name: field(event, "name"), config: { settings, catalogs, serviceIds: new FormData(event.currentTarget).getAll("services"), collectionUrls: field(event, "feeds").split(/\r?\n/).map((s) => s.trim()).filter(Boolean) } });
    } catch (error) { onError(error instanceof Error ? error.message : "Invalid catalog JSON"); }
  }}>
    <label>Template name<input name="name" required maxLength={100} defaultValue={template?.name} autoFocus /></label>
    <fieldset><legend>Home and playback defaults</legend>{Object.entries(choices).map(([key, label]) => <label key={key} className="partner-setting"><span>{label}</span><select aria-label={label} value={settings[key] === undefined ? "inherit" : settings[key] ? "on" : "off"} onChange={(event) => setSettings((current) => { const next = { ...current }; if (event.target.value === "inherit") delete next[key]; else next[key] = event.target.value === "on"; return next; })}><option value="inherit">Keep personal preference</option><option value="on">On</option><option value="off">Off</option></select></label>)}</fieldset>
    <fieldset><legend>Included add-ons and connections</legend>{services.length ? services.map(service => <label className="partner-check" key={service.id}>
      <input type="checkbox" name="services" value={service.id} defaultChecked={cfg.serviceIds?.includes(service.id)} />
      <span>{service.name}<small>{service.kind === "iptv" ? "IPTV / Live TV / VOD" : service.kind === "addon" ? "Add-on" : service.kind}</small></span>
    </label>) : <p className="partner-muted">No connections registered.</p>}</fieldset>
    <label>Collection feed URLs<textarea name="feeds" defaultValue={cfg.collectionUrls.join("\n")} rows={3} placeholder="https://your-host/collections.json" spellCheck={false} /></label>
    <details><summary>Catalog configuration</summary><label>Catalog JSON<textarea className="partner-code" name="catalogs" rows={8} defaultValue={JSON.stringify(cfg.catalogs, null, 2)} spellCheck={false} /></label></details>
    <p className="partner-muted">Saving creates a draft. Publish the package when you are ready to update linked customers.</p>
    <FormFooter busy={busy} label="Save template" onCancel={onCancel} />
  </form>;
}
function PackageEditor({ pkg, org, busy, onSave, onCancel }: { pkg?: PartnerPackage; org: PartnerOrganization; busy: boolean; onSave: (value: Record<string, unknown>) => void; onCancel: () => void }) {
  const [templateId, setTemplateId] = useState(pkg?.templateId || "");
  const [selectedServices, setSelectedServices] = useState(pkg?.serviceIds || []);
  const inherited = org.templates.find(t => t.id === templateId)?.config.serviceIds || [];
  return <form onSubmit={(event) => { event.preventDefault(); const data = new FormData(event.currentTarget); onSave({ action: "save-package", id: pkg?.id, name: data.get("name"), templateId, serviceIds: selectedServices, permissions: Object.fromEntries(Object.keys(permissionLabels).map((key) => [key, data.has(key)])) }); }}>
    <label>Package name<input name="name" required maxLength={100} defaultValue={pkg?.name} autoFocus /></label>
    <label>Template<select name="templateId" value={templateId} onChange={event => setTemplateId(event.target.value)}><option value="">No template</option>{org.templates.map(t => <option key={t.id} value={t.id}>{t.name}</option>)}</select></label>
    <fieldset><legend>Included connections</legend>{org.services.length ? org.services.map(s => <label className="partner-check" key={s.id}>
      <input type="checkbox" name="services" value={s.id} checked={inherited.includes(s.id) || selectedServices.includes(s.id)} disabled={inherited.includes(s.id)} onChange={event => setSelectedServices(current => event.target.checked ? [...current, s.id] : current.filter(id => id !== s.id))} />
      <span>{s.name}<small>{inherited.includes(s.id) ? "Included by template" : s.kind}</small></span>
    </label>) : <p className="partner-muted">No connections registered.</p>}</fieldset>
    <fieldset><legend>Customer permissions</legend>{Object.entries(permissionLabels).map(([key, label]) => <label key={key} className="partner-check"><input name={key} type="checkbox" defaultChecked={pkg ? pkg.permissions[key as keyof PartnerPermissions] : ["editAppearance", "editPlayback"].includes(key)} /><span>{label}</span></label>)}</fieldset>
    <FormFooter busy={busy} label="Save package" onCancel={onCancel} />
  </form>;
}
function ServiceEditor({ service, busy, onSave, onCancel }: { service?: PartnerService; busy: boolean; onSave: (value: Record<string, unknown>) => void; onCancel: () => void }) {
  const [kind, setKind] = useState(service?.kind || "iptv");
  const [iptv, setIptv] = useState(service?.iptv || defaultPartnerIptv());
  return <form onSubmit={(event) => { event.preventDefault(); onSave({ action: "save-service", id: service?.id, name: field(event, "name"), kind, url: field(event, "url"), credentialMode: field(event, "credentialMode"), ...(kind === "iptv" ? { iptv } : {}) }); }}>
    <label>Connection name<input name="name" required maxLength={100} defaultValue={service?.name} autoFocus /></label>
    <label>Type<select aria-label="Type" name="kind" value={kind} onChange={event => setKind(event.target.value as PartnerService["kind"])} disabled={!!service}>
      <option value="iptv">IPTV / Live TV / VOD</option><option value="addon">Stremio-compatible add-on</option><option value="jellyfin">Jellyfin</option><option value="emby">Emby</option><option value="plex">Plex</option>
    </select></label>
    {kind === "iptv" && <>
      <label>IPTV format<select aria-label="IPTV format" value={iptv.mode} onChange={event => setIptv(current => ({ ...current, mode: event.target.value as typeof current.mode }))}><option value="auto">Xtream or M3U</option><option value="xtream">Xtream Codes</option><option value="m3u">M3U playlist</option></select></label>
      <fieldset><legend>Included IPTV content</legend>{([['liveTv', 'Live TV'], ['movies', 'VOD movies'], ['series', 'VOD series'], ['epg', 'Programme guide (EPG)'], ['catchup', 'Catch-up']] as const).map(([key, label]) => {
        const disabled = key === "epg" && !iptv.liveTv || key === "catchup" && (!iptv.liveTv || !iptv.epg);
        return <label className="partner-check" key={key}><input type="checkbox" checked={!disabled && iptv[key]} disabled={disabled} onChange={event => setIptv(current => ({ ...current, [key]: event.target.checked }))} /><span>{label}</span></label>;
      })}</fieldset>
    </>}
    <label>{service ? "Replacement endpoint (leave empty to keep)" : "Service endpoint"}<input name="url" type="url" required={!service} autoComplete="off" placeholder={kind === "addon" ? "https://your-addon/manifest.json" : "https://your-service.example"} /></label>
    <label>Credential assignment<select name="credentialMode" defaultValue={service?.credentialMode || "assigned"}><option value="assigned">Assign separately for each customer</option><option value="customer">Customer supplies their own credentials</option></select></label>
    <p className="partner-muted">Use an account with appropriate access for each customer. Do not use a server administrator token.</p>
    <FormFooter busy={busy} label="Save connection" onCancel={onCancel} />
  </form>;
}
function CredentialEditor({ service, customer, busy, onSave, onCancel }: { service: PartnerService; customer: PartnerCustomer; busy: boolean; onSave: (value: Record<string, unknown>) => void; onCancel: () => void }) {
  return <form onSubmit={(event) => { event.preventDefault(); const data = new FormData(event.currentTarget); onSave({ action: "set-credentials", id: customer.id, serviceId: service.id, credentials: Object.fromEntries(data.entries()) }); }}><p>{customer.name} / {service.name}</p>{service.kind === "addon" ? <label>Customer-specific manifest URL<input name="manifestUrl" type="url" required autoComplete="off" /></label> : service.kind === "iptv" ? <IptvCredentialFields options={service.iptv} /> : <><label>Access token<input name="token" type="password" required autoComplete="new-password" /></label>{service.kind !== "plex" && <label>User ID<input name="userId" required autoComplete="off" /></label>}</>}<p className="partner-muted">Replaces this customer's assigned credentials. Existing secrets are never displayed or copied to another customer.</p><FormFooter busy={busy} label="Save and publish" onCancel={onCancel} /></form>;
}
