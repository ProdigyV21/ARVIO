// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { X, Check, Minus, Plus } from "lucide-react";
import { sourceFacts } from "@/components/details/SourcePicker";
import { SourceRow } from "@/components/details/SourceRow";
import { subtitleLanguageLabel, stepSubtitleDelay, type SubtitleGroup, type SubtitleLoadState } from "@/lib/playerSubtitles";
import { isSameStream, type PlayerPanel } from "./helpers";
import type { StreamSource, AppSettings } from "@/lib/types";
import type { ExternalPlayerOption } from "@/lib/externalPlayers";
import type { RemuxAudioTrack } from "@/lib/remux";
import type { PlaybackGroups } from "@/lib/sourceRank";
import type { PlaybackHandle, PlaybackTracks } from "@/lib/player";
import { useTranslation } from "@/lib/i18n";
import type { Dispatch, SetStateAction, RefObject } from "react";

/** The side panel: sources, audio, subtitles and playback settings. */
export function PlayerSidePanel({ activePanel, activeSubtitle, aiAvailable, aiError, aiSubsActive, aiTargetName, aiTranslating, audioProbeState, bufferAheadSec, buffering, changeSubtitleDelay, copyUrl, embeddedChoice, embeddedTracks, error, externalOptions, onSelectStream, onToast, openExternalOption, playbackRate, probeAudioTracks, remuxAudioIndex, remuxTracks, selectEmbedded, selectSubtitle, setActivePanel, setAiSubsActive, setPlaybackRate, setSubtitleRetry, settings, sourceGroups, stream, subtitleDelayMs, subtitleGroups, subtitleState, switchRemuxAudio, transportRef, transportTracks, updateSettings, videoRef }: { activePanel: PlayerPanel; activeSubtitle: number; aiAvailable: boolean; aiError: string | null; aiSubsActive: boolean; aiTargetName: string; aiTranslating: boolean; audioProbeState: "failed" | "idle" | "probing" | "done"; bufferAheadSec: number; buffering: boolean; changeSubtitleDelay: (next: number) => void; copyUrl: (selectedStream: StreamSource) => Promise<void>; embeddedChoice: TextTrack | null; embeddedTracks: TextTrack[]; error: boolean; externalOptions: ExternalPlayerOption[]; onSelectStream: (next: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean; }) => void; onToast: (message: string) => void; openExternalOption: (option: ExternalPlayerOption, selectedStream: StreamSource) => void; playbackRate: number; probeAudioTracks: () => void; remuxAudioIndex: number; remuxTracks: RemuxAudioTrack[]; selectEmbedded: (track: TextTrack) => void; selectSubtitle: (index: number) => void; setActivePanel: Dispatch<SetStateAction<PlayerPanel>>; setAiSubsActive: Dispatch<SetStateAction<boolean>>; setPlaybackRate: (rate: number) => void; setSubtitleRetry: Dispatch<SetStateAction<number>>; settings: AppSettings; sourceGroups: PlaybackGroups; stream: StreamSource; subtitleDelayMs: number; subtitleGroups: SubtitleGroup[]; subtitleState: SubtitleLoadState; switchRemuxAudio: (index: number) => void; transportRef: RefObject<PlaybackHandle | null>; transportTracks: PlaybackTracks; updateSettings: (patch: Partial<AppSettings>) => void; videoRef: RefObject<HTMLVideoElement | null> }) {
  const translateUi = useTranslation();
  return (<>
      {activePanel && (
        <aside className="player-side-panel" aria-label={activePanel === "sources" ? translateUi("Choose Source") : activePanel === "subtitles" ? translateUi("Subtitles") : activePanel === "audio" ? translateUi("Audio") : translateUi("Playback Settings")}>
          <div className="player-panel-head">
            <div>
              {activePanel !== "sources" && activePanel !== "audio" && <p className="eyebrow">{translateUi(activePanel)}</p>}
              <h3>{activePanel === "sources" ? translateUi("Choose Source") : activePanel === "subtitles" ? translateUi("Subtitles") : activePanel === "audio" ? translateUi("Audio") : translateUi("Playback Settings")}</h3>
            </div>
            <button type="button" className="player-icon-btn" onClick={() => setActivePanel(null)} aria-label={translateUi("Close panel")}><X size={18} /></button>
          </div>

          {activePanel === "sources" && (
            <div className="player-panel-list src-list player-source-list">
              {sourceGroups.here.length > 0 && <h4 className="src-group-head">{translateUi("Plays here")}</h4>}
              {[...sourceGroups.here, ...sourceGroups.external].map((candidate, index) => {
                const facts = sourceFacts(candidate);
                const isActive = isSameStream(candidate, stream) || Boolean(candidate.url && (candidate.url === stream.url || candidate.url === stream.originalUrl));
                return (
                  <div key={`${facts.key}-${index}`} className="player-source-entry">
                    {index === sourceGroups.here.length && sourceGroups.external.length > 0 ? <h4 className="src-group-head">{translateUi("External player")}</h4> : null}
                    <SourceRow
                      rank={index + 1}
                      title={facts.title}
                      provider={candidate.addonName}
                      presentation={facts.presentation}
                      badges={facts.badges}
                      externalOptions={externalOptions}
                      locked={facts.locked}
                      active={isActive}
                      compact
                      onPlay={() => { onSelectStream(candidate, { forceBrowser: true }); setActivePanel(null); }}
                      onExternal={(option) => openExternalOption(option, candidate)}
                      onCopy={() => void copyUrl(candidate)}
                    />
                  </div>
                );
              })}
            </div>
          )}

          {activePanel === "audio" && (
            <div className="player-panel-list player-audio-list">
              {transportTracks.audioTracks.length > 0 ? transportTracks.audioTracks.map((track) => (
                <button type="button" key={track.id} className={`player-panel-row ${transportTracks.selectedAudioTrackId === track.id ? "is-active" : ""}`} onClick={() => transportRef.current?.selectAudioTrack(track.id)}>
                  <span className="player-row-icon">{transportTracks.selectedAudioTrackId === track.id ? <Check size={17} /> : ""}</span>
                  <span><strong>{translateUi(track.label)}</strong><em>{track.language ? subtitleLanguageLabel(track.language) : ""}</em></span>
                </button>
              )) : remuxTracks.length > 0 ? (
                remuxTracks.map((track) => (
                  <button
                    type="button"
                    key={track.index}
                    className={`player-panel-row ${remuxAudioIndex === track.index ? "is-active" : ""} ${track.browserPlayable ? "" : "is-disabled"}`}
                    disabled={!track.browserPlayable}
                    onClick={() => track.browserPlayable && switchRemuxAudio(track.index)}
                  >
                    <span className="player-row-icon">{remuxAudioIndex === track.index ? <Check size={17} /> : ""}</span>
                    <span><strong>{translateUi(track.label)}</strong><em>{track.browserPlayable ? track.codec : translateUi("Lossless — external player only")}</em></span>
                  </button>
                ))
              ) : audioProbeState === "probing" || (stream.remux && buffering && !error) ? (
                <p className="player-panel-empty">{translateUi("Reading audio tracks from this source…")}</p>
              ) : audioProbeState === "failed" ? (
                <div className="player-panel-empty">
                  <p>{translateUi("Audio")} · {translateUi("Unavailable")}</p>
                  <button type="button" className="secondary" onClick={probeAudioTracks}>{translateUi("Retry")}</button>
                </div>
              ) : (
                <p className="player-panel-empty">{translateUi("This source plays its default audio track — no other selectable tracks were found.")}</p>
              )}
            </div>
          )}

          {activePanel === "subtitles" && (
            <div className="player-panel-list player-subtitle-list">
              <div className="player-subtitle-delay" role="group" aria-label={translateUi("Subtitle delay")}>
                <span><strong>{translateUi("Subtitle delay")}</strong><em>{translateUi("This title only")}</em></span>
                <div className="player-setting-stepper">
                  <button type="button" onClick={() => changeSubtitleDelay(stepSubtitleDelay(subtitleDelayMs, -1))} aria-label={translateUi("Show subtitles 0.1 s earlier")}><Minus size={16} /></button>
                  <b aria-live="polite">{subtitleDelayMs > 0 ? "+" : ""}{(subtitleDelayMs / 1000).toFixed(1)} s</b>
                  <button type="button" onClick={() => changeSubtitleDelay(stepSubtitleDelay(subtitleDelayMs, 1))} aria-label={translateUi("Show subtitles 0.1 s later")}><Plus size={16} /></button>
                </div>
                {subtitleDelayMs !== (settings.subtitleOffsetMs ?? 0) ? (
                  <button type="button" className="player-delay-reset" onClick={() => changeSubtitleDelay(settings.subtitleOffsetMs ?? 0)}>{translateUi("Reset")}</button>
                ) : null}
              </div>
              <button type="button" className={`player-panel-row ${activeSubtitle < 0 && !embeddedChoice ? "is-active" : ""}`} onClick={() => selectSubtitle(-1)}>
                <span className="player-row-icon">{activeSubtitle < 0 && !embeddedChoice ? <Check size={17} /> : ""}</span>
                <span><strong>{translateUi("Off")}</strong><em>{translateUi("No subtitle track")}</em></span>
              </button>
              {aiAvailable && (
                <button
                  type="button"
                  className={`player-panel-row ${aiSubsActive ? "is-active" : ""}`}
                  onClick={() => {
                    if (aiSubsActive) {
                      setAiSubsActive(false);
                      return;
                    }
                    const englishIndex = (stream.subtitles ?? []).findIndex((subtitle) => (subtitle.lang ?? "").toLowerCase().startsWith("en"));
                    if (englishIndex < 0) {
                      onToast("AI subtitles need an English source subtitle for this title.");
                      return;
                    }
                    selectSubtitle(englishIndex);
                    setAiSubsActive(true);
                  }}
                >
                  <span className="player-row-icon">{aiSubsActive ? <Check size={17} /> : translateUi("AI")}</span>
                  <span>
                    <strong>{translateUi("AI · ")}{aiTargetName}</strong>
                    <em role={aiSubsActive && aiError ? "alert" : undefined}>{aiSubsActive ? (aiTranslating ? translateUi("Translating…") : aiError ? translateUi(aiError) : translateUi("Live-translating English subtitles")) : translateUi("Translate English subtitles to {value0}", {value0: aiTargetName})}</em>
                  </span>
                </button>
              )}
              {embeddedTracks.length > 0 && <h4 className="player-subtitle-group">{translateUi("In this file")}</h4>}
              {embeddedTracks.map((track, index) => (
                <button type="button" key={`embedded-${track.id || index}`} className={`player-panel-row ${embeddedChoice === track ? "is-active" : ""}`} onClick={() => selectEmbedded(track)}>
                  <span className="player-row-icon">{embeddedChoice === track ? <Check size={17} /> : ""}</span>
                  <span>
                    <strong>{subtitleLanguageLabel(track.language || track.label)}</strong>
                    <em>{[track.label && track.label !== track.language ? track.label : "", track.kind === "captions" ? translateUi("Captions") : "", translateUi("Embedded")].filter(Boolean).join(" · ")}</em>
                  </span>
                </button>
              ))}
              {subtitleGroups.map((group) => (
                <div key={group.language} className="player-subtitle-language" role="group" aria-label={group.language}>
                  <h4 className="player-subtitle-group">{translateUi(group.language)} <small>{group.choices.length}</small></h4>
                  {group.choices.map((choice, rank) => {
                    const subtitle = stream.subtitles![choice.index];
                    const selectedRow = activeSubtitle === choice.index && !aiSubsActive;
                    return (
                      <button
                        type="button"
                        key={subtitle.id || subtitle.url}
                        className={`player-panel-row ${selectedRow ? "is-active" : ""}`}
                        onClick={() => selectSubtitle(choice.index)}
                      >
                        <span className="player-row-icon">{selectedRow ? <Check size={17} /> : ""}</span>
                        <span>
                          <strong>
                            {choice.release ?? (rank === 0 ? translateUi("Best match") : translateUi("{value0} · option {value1}", { value0: translateUi(group.language), value1: rank + 1 }))}
                            {choice.hearingImpaired ? <span className="player-sub-flag">{translateUi("SDH")}</span> : null}
                            {choice.forced ? <span className="player-sub-flag">{translateUi("Forced")}</span> : null}
                          </strong>
                          <em>{[choice.provider ?? subtitle.provider, choice.match > 0 && rank === 0 ? translateUi("Matches this release") : ""].filter(Boolean).join(" · ") || translateUi("External subtitle")}</em>
                        </span>
                      </button>
                    );
                  })}
                </div>
              ))}
              {subtitleState.status === "loading" && <p className="player-panel-empty" role="status">{translateUi("Subtitles")} · {translateUi("Loading")}</p>}
              {subtitleState.status === "failed" && <div className="player-panel-empty" role="alert">
                <p>{translateUi("Subtitles")} · {translateUi("Unavailable")}</p>
                <button type="button" className="secondary" onClick={() => setSubtitleRetry(value => value + 1)}>{translateUi("Retry")}</button>
              </div>}
              {(stream.subtitles?.length ?? 0) === 0 && embeddedTracks.length === 0 && <p className="player-panel-empty">{translateUi("No external subtitles were returned for this source.")}</p>}
              {stream.remux ? <p className="player-panel-note">{translateUi("Subtitles inside this MKV file can't be shown while it is converted in the browser. Use an addon subtitle or an external player.")}</p> : null}
            </div>
          )}

          {activePanel === "settings" && (
            <div className="player-settings-panel">
              <div className="player-setting-row"><span><strong>{translateUi("Playback")}</strong></span><span>{stream.transcoded ? translateUi("Server conversion") : stream.remux ? translateUi("On-device conversion") : stream.transport === "hls" ? translateUi("HLS") : stream.transport === "dash" ? translateUi("DASH") : stream.transport === "mpegts" ? translateUi("MPEG-TS") : translateUi("Direct")}</span></div>
              <div className="player-setting-row"><span><strong>{translateUi("Video")}</strong></span><span>{videoRef.current?.videoWidth ? translateUi("{value0} x {value1}", {value0: videoRef.current.videoWidth, value1: videoRef.current.videoHeight}) : translateUi("Loading")}</span></div>
              <div className="player-setting-row"><span><strong>{translateUi("Buffered ahead")}</strong></span><span>{Math.round(bufferAheadSec)} {translateUi(" s")}</span></div>
              {transportTracks.qualities.length > 0 && <div className="player-setting-row">
                <span><strong>{translateUi("Quality")}</strong></span>
                <select aria-label={translateUi("Playback quality")} value={transportTracks.selectedQualityId ?? "auto"} onChange={(event) => transportRef.current?.selectQuality(event.target.value === "auto" ? null : event.target.value)}>
                  <option value="auto">{translateUi("Auto")}</option>
                  {transportTracks.qualities.map((quality) => <option key={quality.id} value={quality.id}>{translateUi(quality.label)}</option>)}
                </select>
              </div>}
              <button type="button" className="player-setting-toggle" onClick={() => updateSettings({ autoPlayNext: !settings.autoPlayNext })}>
                <span><strong>{translateUi("Auto-play next episode")}</strong><em>{translateUi("Play the next episode automatically")}</em></span>
                <span className={`player-switch ${settings.autoPlayNext ? "is-on" : ""}`} />
              </button>
              <button type="button" className="player-setting-toggle" onClick={() => updateSettings({ autoPlaySingleSource: !settings.autoPlaySingleSource })}>
                <span><strong>{translateUi("Auto-play single source")}</strong><em>{translateUi("Start playing when only one source is found")}</em></span>
                <span className={`player-switch ${settings.autoPlaySingleSource ? "is-on" : ""}`} />
              </button>
              <div className="player-setting-row">
                <span><strong>{translateUi("Subtitle size")}</strong></span>
                <div className="player-setting-stepper">
                  <button type="button" onClick={() => updateSettings({ subtitleSize: Math.max(60, settings.subtitleSize - 10) })} aria-label={translateUi("Decrease subtitle size")}>−</button>
                  <b>{settings.subtitleSize}%</b>
                  <button type="button" onClick={() => updateSettings({ subtitleSize: Math.min(300, settings.subtitleSize + 10) })} aria-label={translateUi("Increase subtitle size")}>+</button>
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>{translateUi("Subtitle position")}</strong></span>
                <div className="player-setting-choices">
                  {([["bottom", "Bottom"], ["low", "Low"], ["medium", "Middle"], ["high", "High"]] as const).map(([value, label]) => (
                    <button
                      type="button"
                      key={value}
                      className={settings.subtitleOffset === value ? "is-active" : ""}
                      onClick={() => updateSettings({ subtitleOffset: value })}
                    >
                      {translateUi(label)}
                    </button>
                  ))}
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>{translateUi("Playback speed")}</strong></span>
                <div className="player-setting-choices">
                  {[0.75, 1, 1.25, 1.5, 2].map((rate) => (
                    <button
                      type="button"
                      key={rate}
                      className={playbackRate === rate ? "is-active" : ""}
                      onClick={() => setPlaybackRate(rate)}
                    >
                      {rate}×
                    </button>
                  ))}
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>{translateUi("Subtitle color")}</strong></span>
                <div className="player-setting-choices player-subtitle-colors">
                  {([["White", "#ffffff"], ["Yellow", "#ffeb3b"], ["Green", "#4caf50"], ["Cyan", "#00e5ff"]] as const).map(([name, hex]) => (
                    <button
                      type="button"
                      key={name}
                      className={settings.subtitleColorName === name ? "is-active" : ""}
                      style={{ ["--dot" as string]: hex }}
                      onClick={() => updateSettings({ subtitleColorName: name, subtitleColor: hex })}
                      aria-label={translateUi("{value0} subtitles", {value0: name})}
                    >
                      <i />
                    </button>
                  ))}
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>{translateUi("Subtitle style")}</strong></span>
                <div className="player-setting-choices">
                  {(["background", "outline", "shadow", "raised"] as const).map((style) => (
                    <button
                      type="button"
                      key={style}
                      className={settings.subtitleStyle === style ? "is-active" : ""}
                      onClick={() => updateSettings({ subtitleStyle: style })}
                    >
                      {style === "background" ? translateUi("Boxed") : style.charAt(0).toUpperCase() + style.slice(1)}
                    </button>
                  ))}
                </div>
              </div>
            </div>
          )}
        </aside>
      )}
  </>);
}
