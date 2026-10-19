"use client";

import { useEffect, useId, useRef, useState } from "react";
import { Copy, ExternalLink, Loader2, X } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { authClient, useApp } from "@/lib/store";
import { ENTITLEMENT_REFRESH_EVENT, fetchEntitlement, kofiSubscribeUrl, type EntitlementState } from "@/lib/entitlement";
import { createPaymentRecovery } from "@/lib/paymentRecovery";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";
import { BillingEmailForm } from "./BillingEmailForm";

const flow = { flow_version: "direct_v2" };

export function MembershipCheckout({ children, className, entry, title, onEntitled, showEmailHint = false }: {
  children: React.ReactNode; className?: string; entry: string; title?: string;
  onEntitled?: (state: EntitlementState) => void; showEmailHint?: boolean;
}) {
  const translateUi = useTranslation();
  const { auth, goToLogin } = useApp();
  const [open, setOpen] = useState(false);
  const [waiting, setWaiting] = useState(false);
  const [status, setStatus] = useState<"idle" | "checking" | "pending" | "active" | "error">("idle");
  const [copied, setCopied] = useState(false);
  const [handoffAttempt, setHandoffAttempt] = useState(0);
  const dialog = useRef<HTMLDialogElement>(null);
  const trigger = useRef<HTMLAnchorElement | HTMLButtonElement | null>(null);
  const recovery = useRef<ReturnType<typeof createPaymentRecovery> | null>(null);
  const applied = useRef(onEntitled);
  applied.current = onEntitled;
  const id = useId();
  const accountId = auth?.userId;

  useEffect(() => {
    setOpen(false); setWaiting(false); setStatus("idle"); setCopied(false);
  }, [accountId]);

  // Count visible offers, not hidden desktop/mobile duplicates or every render.
  useEffect(() => {
    const element = trigger.current;
    if (!accountId || !element || waiting || status === "active") return;
    let recorded = false;
    const record = () => {
      if (recorded || authClient.session?.userId !== accountId || document.visibilityState === "hidden" || !element.getClientRects().length) return;
      recorded = true;
      void trackPremiumEvent(authClient, "membership_cta_viewed", { entry, ...flow });
    };
    const observer = typeof IntersectionObserver === "undefined" ? null : new IntersectionObserver(entries => {
      if (entries.some(item => item.isIntersecting)) record();
    }, { threshold: 0.5 });
    observer?.observe(element);
    const onVisible = () => { if (!observer) record(); else { observer.unobserve(element); observer.observe(element); } };
    document.addEventListener("visibilitychange", onVisible);
    if (!observer) record();
    return () => { observer?.disconnect(); document.removeEventListener("visibilitychange", onVisible); };
  }, [accountId, entry, waiting, status === "active"]);

  useEffect(() => {
    if (!open || !accountId) return;
    dialog.current?.showModal();
    void trackPremiumEvent(authClient, "checkout_recovery_opened", { entry, ...flow });
    return () => { dialog.current?.close(); trigger.current?.focus(); };
  }, [open, accountId, entry]);

  const activate = (state: EntitlementState) => {
    if (!state.entitled || state.reason !== "subscription") return;
    setStatus("active"); setWaiting(false);
    applied.current?.(state);
    window.dispatchEvent(new Event(ENTITLEMENT_REFRESH_EVENT));
  };

  useEffect(() => {
    if (!open || !accountId || status === "active") return;
    let returned = false;
    let hasLeft = document.visibilityState === "hidden" || !document.hasFocus();
    const controller = createPaymentRecovery({
      check: () => fetchEntitlement(authClient),
      isCurrent: () => authClient.session?.userId === accountId,
      isVisible: () => document.visibilityState === "visible" && document.hasFocus(),
      onResult: (result, state) => {
        setStatus(result);
        if (result !== "checking") void trackPremiumEvent(authClient, "payment_status_checked", { entry, status: result, ...flow });
        if (result === "active" && state) activate(state);
      }
    });
    recovery.current = controller;
    const onReturn = () => {
      if (document.visibilityState === "hidden" || !document.hasFocus()) { hasLeft = true; return; }
      if (!hasLeft) return;
      hasLeft = false;
      if (waiting && !returned) {
        returned = true;
        void trackPremiumEvent(authClient, "checkout_returned", { entry, ...flow });
      }
      // A later genuine return can follow an exhausted earlier cycle (for
      // example a background Ko-fi tab opened with Ctrl-click). Recheck rather
      // than stranding a just-paid customer behind the old four-check budget.
      void controller.retry();
    };
    const onLeave = () => { hasLeft = true; };
    window.addEventListener("focus", onReturn);
    window.addEventListener("blur", onLeave);
    document.addEventListener("visibilitychange", onReturn);
    // Manual recovery checks immediately; the native payment link never waits
    // for an entitlement request, telemetry or an asynchronous popup.
    if (!waiting || document.hasFocus()) void controller.resume();
    return () => {
      controller.stop(); recovery.current = null;
      window.removeEventListener("focus", onReturn);
      window.removeEventListener("blur", onLeave);
      document.removeEventListener("visibilitychange", onReturn);
    };
  }, [open, waiting, accountId, entry, handoffAttempt, status === "active"]);

  const close = () => {
    void trackPremiumEvent(authClient, "checkout_recovery_closed", { entry, ...flow });
    setOpen(false);
  };
  const handoff = () => {
    setWaiting(true); setStatus("idle"); setOpen(true);
    setHandoffAttempt(value => value + 1);
    void trackPremiumEvent(authClient, "checkout_opened", { entry, ...flow });
  };
  const onPaymentClick = (event: React.MouseEvent<HTMLAnchorElement>) => {
    if (waiting || status === "active") { event.preventDefault(); setOpen(true); }
    else handoff();
  };

  return <>
    {!auth ? <button ref={element => { trigger.current = element; }} type="button" className={className} title={title} aria-label={title} onClick={goToLogin}>{children}</button>
      : <a ref={element => { trigger.current = element; }} className={className} title={waiting || status === "active" ? undefined : title} aria-label={waiting || status === "active" ? undefined : title}
        role={waiting || status === "active" ? "button" : undefined}
        aria-describedby={showEmailHint ? `${id}-hint` : undefined} href={kofiSubscribeUrl()} target="_blank" rel="noopener noreferrer"
        onKeyDown={event => { if ((waiting || status === "active") && event.key === " ") { event.preventDefault(); setOpen(true); } }}
        onClick={onPaymentClick} onAuxClick={event => { if (event.button === 1) onPaymentClick(event); }}>
          {waiting || status === "active" ? translateUi(status === "active" ? "Membership active" : "Check payment status") : children}
      </a>}
    {showEmailHint && auth && <div className="membership-inline-hint" id={`${id}-hint`}>
      <p>{translateUi("Use this email at checkout")} <strong>{auth.email}</strong></p>
      <button type="button" className="paywall-link-toggle" onClick={() => setOpen(true)}>{translateUi("Already paid? Check access")}</button>
    </div>}
    {open && auth && <dialog ref={dialog} className="membership-checkout" aria-labelledby={`${id}-title`} onCancel={close}>
      <button type="button" className="membership-close" aria-label={translateUi("Close")} onClick={close}><X size={20} /></button>
      <h2 id={`${id}-title`}>{translateUi("Finish your membership")}</h2>
      <p>{translateUi("Finish on Ko-fi, then return to this tab. We will check your access automatically. Do not pay again if activation is delayed.")}</p>
      <div className="membership-email">
        <span>{translateUi("Your ARVIO account")}</span><strong>{auth.email}</strong>
        <button type="button" className="paywall-trial" onClick={async () => {
          try { await navigator.clipboard.writeText(auth.email); setCopied(true); } catch { setCopied(false); }
        }}><Copy size={15} /> {translateUi(copied ? "Copied" : "Copy email")}</button>
      </div>
      <p role="status" aria-live="polite">
        {status === "checking" && <Loader2 size={16} className="paywall-spinner" />}
        {translateUi(status === "active" ? "Your Premium membership is active." : status === "checking" ? "Checking your payment status…" : status === "error" ? "Could not check access. Your payment status has not changed; please retry." : status === "pending" ? "No paid membership found yet. Check again or link the email on your receipt." : "After payment, come back here to confirm activation.")}
      </p>
      {status !== "active" && <>
        <button type="button" className="paywall-primary" disabled={status === "checking"} onClick={() => { void recovery.current?.retry(); }}>{translateUi("Check payment status")}</button>
        <p>{translateUi("PayPal or Ko-fi may use a different email. You can securely link that email here after paying.")}</p>
        <BillingEmailForm key={accountId} onEntitled={activate} />
        <a className="membership-return-link" href={kofiSubscribeUrl()} target="_blank" rel="noopener noreferrer" onClick={handoff} onAuxClick={event => { if (event.button === 1) handoff(); }}>{translateUi("Return to Ko-fi")} <ExternalLink size={14} /></a>
      </>}
      {status === "active" && <button type="button" className="paywall-primary" onClick={close}>{translateUi("Continue watching")}</button>}
    </dialog>}
  </>;
}
