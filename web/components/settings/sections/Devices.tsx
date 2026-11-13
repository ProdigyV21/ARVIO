"use client";
import { CheckCircle, Download, RefreshCw, Trash2 } from "lucide-react";
import { useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { ACCENTS, ACCENT_NAMES } from "@/lib/accents";
import { isLinux, isMac, isWindows, setVlcProtocolReady, triggerDownload, vlcProtocolReady, VLC_SETUP_SH_URL, VLC_SETUP_URL } from "@/lib/externalPlayers";
import { useAppPick } from "@/lib/store";
import { kidsLevelOf } from "@/lib/kids";
import { navigationSectionVisible, withNavigationSectionVisible } from "@/lib/navigationVisibility";
import { DownloadsPanel } from "../DownloadsPanel";
import { Group, Row, Segmented, Select, SectionHeader, Status, Swatches, TextInput, Toggle } from "../ui";

const settingsKey = "arvio.web.settings";

/* ---------- Home and appearance ---------- */

export function AppearanceSection() {
  const { settings, updateSettings: set, activeProfile } = useAppPick("settings", "updateSettings", "activeProfile");
  return (
    <>
      <SectionHeader title="Home and appearance" description="How ARVIO looks, and what shows on Home." />
      <Group title="Look">
        <Row id="accent" label="Accent color" scope="all">
          <Swatches label="Accent color" value={ACCENTS[settings.accentColor] ? settings.accentColor : "arctic"} onChange={(v) => set({ accentColor: v })}
            options={ACCENT_NAMES.map(([id, name]) => [id, name, ACCENTS[id]])} />
        </Row>
        <Row id="oled" label="Pure black background" hint="Saves power on OLED screens." scope="all">
          <Toggle label="Pure black background" value={settings.oledBlack} onChange={(v) => set({ oledBlack: v })} />
        </Row>
        <Row id="card-layout" label="Card style" hint="Individual rows can override this in Home rows." scope="all">
          <Segmented label="Card style" value={settings.cardLayoutMode} onChange={(v) => set({ cardLayoutMode: v })} options={[["landscape", "Wide"], ["poster", "Poster"]]} />
        </Row>
        <Row id="clock" label="Clock format" scope="all">
          <Segmented label="Clock format" value={settings.clockFormat} onChange={(v) => set({ clockFormat: v })} options={[["24h", "24-hour"], ["12h", "12-hour"]]} />
        </Row>
      </Group>
      <Group title="Home screen">
        <Row id="netflix-home" label="Netflix-style Home" hint="A large featured billboard with trailer; the focused card expands with its description." scope="android">
          <Toggle label="Netflix-style Home" value={settings.netflixHomeStyle ?? true} onChange={(v) => set({ netflixHomeStyle: v })} />
        </Row>
        <Row id="watchlist-home" label="Show watchlist on Home" hint="Place your watchlist below Continue Watching." scope="android">
          <Toggle label="Show watchlist on Home" value={settings.watchlistOnHome ?? false} onChange={(v) => set({ watchlistOnHome: v })} />
        </Row>
      </Group>
      <Group title="Navigation">
        <Row id="navigation-search" label="Show Search" scope="all">
          <Toggle label="Show Search" value={navigationSectionVisible("search", settings.hiddenNavigationSections)}
            onChange={(visible) => set({ hiddenNavigationSections: withNavigationSectionVisible(settings.hiddenNavigationSections, "search", visible) })} />
        </Row>
        <Row id="navigation-library" label="Show Library" scope="all">
          <Toggle label="Show Library" value={navigationSectionVisible("watchlist", settings.hiddenNavigationSections)}
            onChange={(visible) => set({ hiddenNavigationSections: withNavigationSectionVisible(settings.hiddenNavigationSections, "watchlist", visible) })} />
        </Row>
        {!kidsLevelOf(activeProfile) && <Row id="navigation-tv" label="Show TV Player" scope="all">
          <Toggle label="Show TV Player" value={navigationSectionVisible("tv", settings.hiddenNavigationSections)}
            onChange={(visible) => set({ hiddenNavigationSections: withNavigationSectionVisible(settings.hiddenNavigationSections, "tv", visible) })} />
        </Row>}
      </Group>
      <Group title="Title pages">
        <Row id="spoiler-blur" label="Blur episode spoilers" hint="Blur thumbnails and descriptions of episodes you haven't watched." scope="all">
          <Toggle label="Blur episode spoilers" value={settings.spoilerBlur} onChange={(v) => set({ spoilerBlur: v })} />
        </Row>
        <Row id="show-budget" label="Show budget and box office" scope="all">
          <Toggle label="Show budget and box office" value={settings.showBudget} onChange={(v) => set({ showBudget: v })} />
        </Row>
        <Row id="episode-ratings" label="Show episode ratings" hint="Display episode ratings as a visual row below the episode list." scope="android">
          <Toggle label="Show episode ratings" value={settings.showEpisodeRatings} onChange={(v) => set({ showEpisodeRatings: v })} />
        </Row>
        <Row id="anime-order" label="Anime episode ordering" hint={settings.animeEpisodeStructuring === "standard" ? "Standard (TMDB) seasons." : "Broadcast order (cours)."} scope="android">
          <Segmented label="Anime episode ordering" value={settings.animeEpisodeStructuring} onChange={(v) => set({ animeEpisodeStructuring: v })}
            options={[["broadcast", "Broadcast"], ["standard", "Standard"]]} />
        </Row>
      </Group>
    </>
  );
}

/* ---------- Android and TV ---------- */

export function AndroidTvSection() {
  const { settings, updateSettings: set } = useAppPick("settings", "updateSettings");
  return (
    <>
      <SectionHeader title="Android and TV" description="These only change the ARVIO app on Android phones, tablets and TVs. Set them here and the app uses them the next time it opens." />
      <Group title="Picture and sound">
        <Row id="frame-rate" label="Frame rate matching" hint="Switch the TV's refresh rate to match the video for smoother motion." scope="android">
          <Select label="Frame rate matching" value={settings.frameRateMatchingMode} onChange={(v) => set({ frameRateMatchingMode: v })}
            options={[["off", "Off"], ["seamless", "Only if seamless"], ["always", "Always"]]} />
        </Row>
        <Row id="dolby-vision" label="Dolby Vision compatibility" hint="Play Dolby Vision releases as HDR10 when the device can't decode them." scope="android">
          <Toggle label="Dolby Vision compatibility" value={settings.dolbyVisionCompat} onChange={(v) => set({ dolbyVisionCompat: v })} />
        </Row>
        <Row id="volume-boost" label="Volume boost" hint="Make quiet films louder. Above +12 dB can distort." scope="android">
          <Select label="Volume boost" value={String(settings.volumeBoostDb)} onChange={(v) => set({ volumeBoostDb: Number(v) })}
            options={["0", "3", "6", "9", "12", "15"].map((value) => [value, value === "0" ? "Off" : `+${value} dB`])} />
        </Row>
        <Row id="loading-stats" label="Show loading stats" hint="Display stream resolution progress while sources load." scope="android">
          <Toggle label="Show loading stats" value={settings.showLoadingStats} onChange={(v) => set({ showLoadingStats: v })} />
        </Row>
      </Group>
      <Group title="Network">
        <Row id="dns" label="DNS provider" hint="Private DNS can get around blocked addon or source domains." scope="android">
          <Select label="DNS provider" value={settings.dnsProvider === "quad9" ? "system" : settings.dnsProvider} onChange={(v) => set({ dnsProvider: v })}
            options={[["system", "System"], ["cloudflare", "Cloudflare"], ["google", "Google"], ["adguard", "AdGuard"]]} />
        </Row>
        <Row id="user-agent" label="Custom user agent" hint="Only if a source needs it. Leave empty for the default." scope="android" stack>
          <TextInput label="Custom user agent" wide value={settings.customUserAgent} placeholder="Default" onChange={(v) => set({ customUserAgent: v })} />
        </Row>
        <Row id="torrserver" label="TorrServer address" hint="Your TorrServer, for torrent sources." scope="android" stack>
          <TextInput label="TorrServer address" wide value={settings.torrServerBaseUrl} placeholder="http://127.0.0.1:8090" inputMode="url" onChange={(v) => set({ torrServerBaseUrl: v.trim() })} />
        </Row>
      </Group>
    </>
  );
}

/* ---------- This browser ---------- */

export function BrowserSection() {
  const translateUi = useTranslation();
  const { settings, updateSettings: set, setToast } = useAppPick("settings", "updateSettings", "setToast");
  const [vlcReady, setVlcReady] = useState<boolean>(() => vlcProtocolReady());
  const [confirmReset, setConfirmReset] = useState(false);
  const [checkStatus, setCheckStatus] = useState<"idle" | "testing" | "working" | "not_installed">("idle");
  const mac = isMac();
  const os = isWindows() ? "windows" : isLinux() ? "linux" : mac ? "mac" : "other";

  const download = (kind: "windows" | "linux") => {
    if (kind === "windows") triggerDownload(VLC_SETUP_URL, "vlc-setup.bat");
    else triggerDownload(VLC_SETUP_SH_URL, "vlc-setup.sh");
    setVlcProtocolReady(true);
    setVlcReady(true);
    setToast(kind === "windows" ? "Downloaded vlc-setup.bat — run it once on Windows to enable direct VLC launching." : "Downloaded vlc-setup.sh — run `bash vlc-setup.sh` to enable direct VLC launching on Linux.");
  };

  const checkIntegration = () => {
    setCheckStatus("testing");
    let blurred = false;
    const onBlur = () => { blurred = true; };
    window.addEventListener("blur", onBlur);
    const testUrl = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/720/Big_Buck_Bunny_720_10s_5MB.mp4";
    try {
      const a = document.createElement("a");
      a.href = `vlc://${testUrl}`;
      a.rel = "noopener";
      a.style.position = "fixed";
      a.style.left = "-9999px";
      document.body.appendChild(a);
      a.click();
      setTimeout(() => a.remove(), 1000);
    } catch { /* ignore */ }
    setTimeout(() => {
      window.removeEventListener("blur", onBlur);
      if (blurred) {
        setCheckStatus("working");
        setVlcProtocolReady(true);
        setVlcReady(true);
      } else {
        setCheckStatus("not_installed");
      }
    }, 1500);
  };

  return (
    <>
      <SectionHeader title="This browser" description="Player, VLC and download options for the browser you're using now." />
      <Group title="Player">
        <Row id="default-player" label="Preferred player for manual sources" hint="Autoplay always plays in this browser. Choose external players manually from Sources." scope="web">
          <Select label="Preferred player" value={settings.defaultPlayer} onChange={(v) => set({ defaultPlayer: v })}
            options={[["browser", "ARVIO player (browser)"], ["vlc", "VLC"], ["infuse", "Infuse"]]} />
        </Row>
        <Row id="smooth-scrolling" label="Smooth scrolling" scope="web">
          <Toggle label="Smooth scrolling" value={settings.smoothScrolling} onChange={(v) => set({ smoothScrolling: v })} />
        </Row>
      </Group>

      <Group title="VLC" description={mac
        ? "On macOS, VLC registers vlc:// itself when installed. No setup needed."
        : "Open streams straight in VLC. Windows and Linux need a one-time setup script so the browser can hand links to VLC."}>
        <Row id="vlc-check" label="Check VLC" hint="Tries to open a short test video in VLC." scope="browser">
          <button type="button" className="st-btn" disabled={checkStatus === "testing"} onClick={checkIntegration}>
            {checkStatus === "testing" ? <RefreshCw size={16} className="spin" /> : <CheckCircle size={16} />}
            {translateUi(checkStatus === "testing" ? "Testing VLC Launcher..." : "Check")}
          </button>
          {checkStatus === "working" && <Status tone="ok">{translateUi("Working")}</Status>}
          {checkStatus === "not_installed" && <Status tone="warn">{translateUi("Not detected — run the setup script")}</Status>}
        </Row>
        {!mac && (
          <Row id="vlc-protocol" label="Open VLC directly" hint="When on, ARVIO launches streams via vlc:// instead of saving .m3u playlist files." scope="browser">
            <Toggle label="Open VLC directly" value={vlcReady} onChange={(next) => { setVlcProtocolReady(next); setVlcReady(next); }} />
          </Row>
        )}
        {!mac && (
          <Row id="vlc-setup" label="Setup script" hint={os === "linux" ? "Run bash vlc-setup.sh once in a terminal." : "Run vlc-setup.bat once. No administrator rights needed."} scope="browser">
            {os !== "linux" && <button type="button" className="st-btn" onClick={() => download("windows")}><Download size={16} /> {translateUi("Windows (.bat)")}</button>}
            {os !== "windows" && <button type="button" className="st-btn" onClick={() => download("linux")}><Download size={16} /> {translateUi("Linux (.sh)")}</button>}
          </Row>
        )}
      </Group>

      <Group title="Downloads">
        <div className="st-downloads"><DownloadsPanel /></div>
      </Group>

      <Group title="Reset">
        <Row id="reset-web" label="Reset ARVIO Web in this browser" hint="Clears settings saved in this browser and reloads. Your account and other devices are not affected." scope="browser">
          {confirmReset ? (
            <>
              <button type="button" className="st-btn is-danger" onClick={() => { localStorage.removeItem(settingsKey); window.location.reload(); }}><Trash2 size={16} /> {translateUi("Yes, reset")}</button>
              <button type="button" className="st-btn" onClick={() => setConfirmReset(false)}>{translateUi("Cancel")}</button>
            </>
          ) : (
            <button type="button" className="st-btn is-danger" onClick={() => setConfirmReset(true)}><Trash2 size={16} /> {translateUi("Reset")}</button>
          )}
        </Row>
      </Group>
    </>
  );
}
