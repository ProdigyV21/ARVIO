"use client";
import "@/app/premium.css";

import { useState, type ReactNode } from "react";
import { ArrowRight, Check, Cloud, Download, Layers, LockKeyhole, LogOut, Monitor, Server, ShieldCheck, Tv } from "lucide-react";
import { kofiYearlyUrl } from "@/lib/entitlement";
import { useTranslation } from "@/lib/i18n";
import { REGIONAL_YEARLY_PRICE, STANDARD_YEARLY_PRICE, useRegionalYearly } from "@/lib/regionalPricing";
import { AndroidAppNote, PremiumPlans } from "./PremiumPlans";

// A public product preview, not a second app mode. Only static screenshots and
// feature copy are mounted here: no catalogs, source configuration or player.
export function WebFeaturePreview({ checkout, isSignedIn, expired = false, onConnect, onSignOut }: {
  checkout: ReactNode;
  isSignedIn: boolean;
  expired?: boolean;
  onConnect: () => void;
  onSignOut: () => void;
}) {
  const translateUi = useTranslation();
  const regional = useRegionalYearly();
  const yearlyPrice = regional ? REGIONAL_YEARLY_PRICE : STANDARD_YEARLY_PRICE;
  const [screen, setScreen] = useState("home");
  const screens = [["home", "Home"], ["tv", "Live TV"], ["library", "Library"], ["homeserver", "Home Server"], ["sports", "Sports"]];
  const selectedLabel = screens.find(([id]) => id === screen)?.[1] ?? "Home";

  return <main className="web-feature-preview" data-testid="web-feature-preview">
    <header className="web-preview-header">
      <a href="https://arvio.tv/" className="web-preview-brand" aria-label="ARVIO">
        <img src="/arvio-logo.svg" width="34" height="34" alt="" />
        <img src="/arvio-wordmark.svg" width="108" height="24" alt="ARVIO" />
      </a>
      {isSignedIn ? <button type="button" className="web-preview-signin" onClick={onSignOut}><LogOut size={15} />{translateUi("Sign out")}</button>
        : <button type="button" className="web-preview-signin" onClick={onConnect}>{translateUi("Sign in")}<ArrowRight size={15} /></button>}
    </header>

    <div className="web-preview-layout">
      <div className="web-preview-content">
        <section className="web-preview-hero" aria-labelledby="web-preview-title">
          <p className="web-preview-eyebrow"><span />ARVIO WEB · PREMIUM</p>
          <h1 id="web-preview-title">{translateUi("Your browser. Your ARVIO.")}</h1>
          <p className="web-preview-lead">{translateUi("Take your existing ARVIO setup to Windows, Mac, iPhone, iPad and smart-TV browsers. Your profiles, libraries, addons and progress stay connected through ARVIO Cloud.")}</p>
          <div className="web-preview-platforms"><Monitor size={16} /><span>Windows · Mac · Linux · iPhone · iPad · {translateUi("Smart-TV browsers")}</span></div>
          {/* Phones: the main action without scrolling past the screenshots. */}
          <div className="web-preview-hero-cta">{checkout}<p>
            {translateUi("Monthly · $2.99")}
            {kofiYearlyUrl() && <> / {translateUi("12-month pass · {value0}", { value0: yearlyPrice })}</>}
            {". "}{translateUi("You only pay when you press Play.")}
          </p></div>
          <AndroidAppNote />
        </section>

        <section className="web-preview-showcase" aria-label={translateUi("Explore ARVIO Web")}>
          <div className="web-preview-tabs" role="group" aria-label={translateUi("Features")}>
            {screens.map(([id, label]) => <button type="button" key={id} aria-pressed={screen === id} onClick={() => setScreen(id)}>{translateUi(label)}</button>)}
          </div>
          <div className="web-preview-screenshot" data-testid="feature-screenshot">
            <img src={`/premium/${screen}.png`} width="1920" height="1080" alt={`ARVIO Web — ${translateUi(selectedLabel)}`} fetchPriority="high" />
            <span className="web-preview-image-label"><LockKeyhole size={12} />{translateUi("Feature preview")}</span>
          </div>
        </section>

        <section className="web-preview-features" aria-label={translateUi("ARVIO Web benefits")}>
          <article><Tv size={22} /><h2>{translateUi("Live TV")}</h2><p>{translateUi("Bring your own IPTV service. Browse the programme guide and play supported channels right in ARVIO Web.")}</p></article>
          <article><Server size={22} /><h2>Jellyfin · Plex · Emby</h2><p>{translateUi("Bring your Jellyfin, Plex, Emby or Silo library into the same familiar interface.")}</p></article>
          <article><Layers size={22} /><h2>{translateUi("Addons")}</h2><p>{translateUi("Connect the services and addons you already use.")}</p></article>
          <article><Cloud size={22} /><h2>ARVIO Cloud</h2><p>{translateUi("Same profiles, libraries and watch progress")}</p></article>
          <article><Check size={22} /><h2>Trakt · Simkl</h2><p>{translateUi("Your watchlists, personal lists and watch progress — connected through ARVIO Cloud.")}</p></article>
          <article><Download size={22} /><h2>{translateUi("Downloads")}</h2><p>{translateUi("Save supported sources from the source picker. Download on desktop, or hand off to VLC on iPhone and iPad.")}</p></article>
        </section>
        <p className="web-preview-source-note">{translateUi("Membership does not include media or subscriptions to other services. Connect only services and media you are authorized to use. Browser playback depends on your source and device.")}</p>
      </div>

      <aside className="web-preview-membership" aria-labelledby="web-preview-membership-title">
        <div className="web-preview-plan-icon"><LockKeyhole size={22} /></div>
        <h2 id="web-preview-membership-title">{expired ? translateUi("Your ARVIO Web membership has ended") : translateUi("ARVIO Web Premium")}</h2>
        <p className="web-preview-plan-description">{translateUi("24/7 hosting. No server to set up or maintain.")}</p>
        <PremiumPlans compact />
        <ul className="web-preview-plan-benefits">
          <li><Check size={16} />{translateUi("All ARVIO Web features")}</li>
          <li><Check size={16} />{translateUi("Browser playback and one-click VLC")}</li>
          <li><Check size={16} />{translateUi("Same profiles, libraries and watch progress")}</li>
        </ul>
        <div className="web-preview-checkout">{checkout}</div>
        <p className="web-preview-unlock"><ShieldCheck size={16} /><span>{translateUi("Look around first: sign in with your ARVIO account and you only pay when you press Play.")}</span></p>
        {!isSignedIn && <p className="web-preview-account-note">{translateUi("Your profiles, watchlist and addons from the Android app are already there. Already a member? Sign in to continue.")}</p>}
        <div className="web-preview-free"><p>{translateUi("Android and TV app remains completely free")}</p><a href="https://github.com/ProdigyV21/ARVIO/blob/main/web/README.md" target="_blank" rel="noopener noreferrer">{translateUi("Self-host ARVIO Web for free.")}<ArrowRight size={13} /></a></div>
      </aside>
    </div>
    <footer className="web-preview-footer"><span>ARVIO · {translateUi("Open source")}</span><a href="https://arvio.tv/premium/">{translateUi("Learn more")}</a></footer>
  </main>;
}
