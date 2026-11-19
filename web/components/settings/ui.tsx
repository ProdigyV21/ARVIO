"use client";
// Building blocks for the Settings screen. Every row says where its value
// applies (all ARVIO apps, the Android/TV app, ARVIO Web, or this browser
// only), so settings changed here are never a guess about what syncs.

import { Check, ChevronDown, Cloud, Eye, EyeOff, Globe, Monitor, Tv, type LucideIcon } from "lucide-react";
import { isValidElement, useEffect, useId, useRef, useState, type ReactNode } from "react";
import { createPortal } from "react-dom";
import { useTranslation } from "@/lib/i18n";

export type SettingScope = "all" | "android" | "web" | "browser";

const SCOPES: Record<SettingScope, { label: string; title: string; icon: LucideIcon }> = {
  all: { label: "All devices", title: "Saved to your ARVIO account and used by the Android/TV app and ARVIO Web.", icon: Cloud },
  android: { label: "Android & TV", title: "Saved to your ARVIO account. Only the Android and TV app uses it.", icon: Tv },
  web: { label: "ARVIO Web", title: "Saved to your ARVIO account for ARVIO Web in any browser. The Android app keeps its own value.", icon: Globe },
  browser: { label: "This browser", title: "Kept in this browser only. Not synced.", icon: Monitor },
};

export function ScopeBadge({ scope }: { scope: SettingScope }) {
  const translateUi = useTranslation();
  const { label, title, icon: Icon } = SCOPES[scope];
  return (
    <span className={`st-scope st-scope-${scope}`} title={translateUi(title)}>
      <Icon size={12} aria-hidden="true" />
      {translateUi(label)}
    </span>
  );
}

export function SectionHeader({ title, description, children }: { title: string; description?: ReactNode; children?: ReactNode }) {
  const translateUi = useTranslation();
  return (
    <header className="st-section-head">
      <div>
        <h2>{translateUi(title)}</h2>
        {description && <p>{typeof description === "string" ? translateUi(description) : description}</p>}
      </div>
      {children && <div className="st-section-head-actions">{children}</div>}
    </header>
  );
}

export function Group({ title, description, children, footer }: { title?: string; description?: ReactNode; children: ReactNode; footer?: ReactNode }) {
  const translateUi = useTranslation();
  return (
    <section className="st-group">
      {(title || description) && (
        <div className="st-group-head">
          {title && <h3>{translateUi(title)}</h3>}
          {description && <p>{typeof description === "string" ? translateUi(description) : description}</p>}
        </div>
      )}
      <div className="st-group-body">{children}</div>
      {footer && <div className="st-group-foot">{footer}</div>}
    </section>
  );
}

/** One setting: label + help text on the left, control on the right. `id`
 *  matches the search index so a search result can scroll to it. */
export function Row({ id, label, hint, scope, children, stack, disabled }: {
  id?: string;
  label: string;
  hint?: ReactNode;
  scope?: SettingScope;
  children?: ReactNode;
  /** Put the control under the text (wide inputs, lists). */
  stack?: boolean;
  disabled?: boolean;
}) {
  const translateUi = useTranslation();
  // A row whose only control is a switch keeps the switch beside the text on phones.
  const toggleOnly = isValidElement(children) && children.type === Toggle;
  return (
    <div className={`st-row ${stack ? "is-stacked" : ""} ${disabled ? "is-disabled" : ""} ${toggleOnly ? "is-toggle" : ""}`} id={id ? `st-${id}` : undefined} data-setting={id}>
      <div className="st-row-text">
        <span className="st-row-label">
          {translateUi(label)}
          {scope && <ScopeBadge scope={scope} />}
        </span>
        {hint && <span className="st-row-hint">{typeof hint === "string" ? translateUi(hint) : hint}</span>}
      </div>
      {children !== undefined && <div className="st-row-control">{children}</div>}
    </div>
  );
}

/** A search target for a block that is not a single Row (e.g. a layout editor). */
export function SettingAnchor({ id, children }: { id: string; children: ReactNode }) {
  return <div className="st-anchor" id={`st-${id}`} data-setting={id}>{children}</div>;
}

export function Toggle({ value, onChange, disabled, label }: { value: boolean; onChange: (v: boolean) => void; disabled?: boolean; label?: string }) {
  const translateUi = useTranslation();
  return (
    <button
      type="button"
      className={`toggle-switch st-toggle ${value ? "is-on" : ""}`}
      role="switch"
      aria-checked={value}
      aria-label={label ? translateUi(label) : undefined}
      disabled={disabled}
      onClick={() => onChange(!value)}
    >
      <span />
    </button>
  );
}

/** Two to four short choices shown side by side. */
export function Segmented<T extends string>({ value, options, onChange, label, disabled }: {
  value: T;
  options: Array<[T, string]>;
  onChange: (v: T) => void;
  label: string;
  disabled?: boolean;
}) {
  const translateUi = useTranslation();
  return (
    <div className="st-segmented" role="radiogroup" aria-label={translateUi(label)}>
      {options.map(([option, text]) => (
        <button
          key={option}
          type="button"
          role="radio"
          aria-checked={option === value}
          className={option === value ? "is-active" : ""}
          disabled={disabled}
          onClick={() => onChange(option)}
        >
          {translateUi(text)}
        </button>
      ))}
    </div>
  );
}

/** A dropdown that opens a bottom sheet on phones and a list on desktop. */
export function Select<T extends string>({
  value,
  options,
  onChange,
  disabled,
  translateLabels = true,
  label,
}: {
  value: T;
  options: Array<[T, string]>;
  onChange: (v: T) => void;
  disabled?: boolean;
  translateLabels?: boolean;
  label?: string;
}) {
  const translateUi = useTranslation();
  const [open, setOpen] = useState(false);
  const buttonRef = useRef<HTMLButtonElement>(null);
  const selected = options.find(([option]) => option === value)?.[1] ?? value;
  // Give focus back to the dropdown so keyboards and TV remotes carry on from it.
  const close = () => {
    setOpen(false);
    window.requestAnimationFrame(() => buttonRef.current?.focus({ preventScroll: true }));
  };
  const choose = (next: T) => {
    onChange(next);
    close();
  };

  // Lock body scroll while the option sheet is open — the position:fixed
  // technique is the only reliable way to prevent scroll-through on mobile.
  useEffect(() => {
    if (!open) return;
    const scrollY = window.scrollY;
    const { style } = document.body;
    const prev = { position: style.position, top: style.top, left: style.left, right: style.right, overflow: style.overflow };
    style.position = "fixed";
    style.top = `-${scrollY}px`;
    style.left = "0";
    style.right = "0";
    style.overflow = "hidden";
    const onKey = (event: KeyboardEvent) => {
      if (event.key !== "Escape") return;
      event.stopPropagation();
      setOpen(false);
      window.requestAnimationFrame(() => buttonRef.current?.focus({ preventScroll: true }));
    };
    window.addEventListener("keydown", onKey, true);
    return () => {
      style.position = prev.position;
      style.top = prev.top;
      style.left = prev.left;
      style.right = prev.right;
      style.overflow = prev.overflow;
      window.scrollTo(0, scrollY);
      window.removeEventListener("keydown", onKey, true);
    };
  }, [open]);

  return (
    <>
      <button
        ref={buttonRef}
        type="button"
        className="option-button st-select"
        disabled={disabled}
        aria-haspopup="dialog"
        aria-label={label ? `${translateUi(label)}: ${translateLabels ? translateUi(selected) : selected}` : undefined}
        onClick={(event) => {
          event.preventDefault();
          setOpen(true);
        }}
      >
        <span>{translateLabels ? translateUi(selected) : selected}</span>
        <ChevronDown size={16} />
      </button>
      {open && typeof document !== "undefined" && createPortal(
        <div className="option-sheet-backdrop" role="presentation" onClick={close}>
          <div className="option-sheet" role="dialog" aria-modal="true" aria-label={translateUi(label ?? "Choose option")} onClick={(event) => event.stopPropagation()}>
            <div className="option-sheet-head">
              <strong>{translateUi(label ?? "Choose option")}</strong>
              <button type="button" className="secondary" onClick={close}>{translateUi("Close")}</button>
            </div>
            <div className="option-sheet-list">
              {options.map(([option, text]) => (
                <button
                  type="button"
                  key={option}
                  autoFocus={option === value}
                  className={`option-row ${option === value ? "is-selected" : ""}`}
                  onClick={() => choose(option)}
                >
                  <span>{translateLabels ? translateUi(text) : text}</span>
                  {option === value && <Check size={18} />}
                </button>
              ))}
            </div>
          </div>
        </div>,
        document.body
      )}
    </>
  );
}

/** A slider with the value shown next to it, plus -/+ buttons for remotes
 *  and keyboards where dragging is awkward. */
export function Slider({ value, min, max, step = 1, onChange, format, label }: {
  value: number;
  min: number;
  max: number;
  step?: number;
  onChange: (v: number) => void;
  format?: (v: number) => string;
  label: string;
}) {
  const translateUi = useTranslation();
  const clamp = (v: number) => Math.min(max, Math.max(min, Math.round(v / step) * step));
  const current = clamp(Number.isFinite(value) ? value : min);
  const shown = format ? format(current) : String(current);
  return (
    <div className="st-slider">
      <button type="button" className="st-step" aria-label={translateUi("Decrease {value0}", { value0: translateUi(label) })} disabled={current <= min} onClick={() => onChange(clamp(current - step))}>−</button>
      <input
        type="range"
        min={min}
        max={max}
        step={step}
        value={current}
        aria-label={translateUi(label)}
        aria-valuetext={shown}
        onChange={(event) => onChange(clamp(Number(event.target.value)))}
        style={{ ["--st-fill" as string]: `${((current - min) / (max - min)) * 100}%` }}
      />
      <button type="button" className="st-step" aria-label={translateUi("Increase {value0}", { value0: translateUi(label) })} disabled={current >= max} onClick={() => onChange(clamp(current + step))}>+</button>
      <output className="st-slider-value">{shown}</output>
    </div>
  );
}

export function TextInput({ value, onChange, placeholder, label, type = "text", inputMode, wide }: {
  value: string;
  onChange: (v: string) => void;
  placeholder?: string;
  label: string;
  type?: "text" | "url" | "number";
  inputMode?: "text" | "url" | "numeric";
  wide?: boolean;
}) {
  const translateUi = useTranslation();
  return (
    <input
      className={`st-input ${wide ? "is-wide" : ""}`}
      type={type}
      inputMode={inputMode}
      value={value}
      aria-label={translateUi(label)}
      placeholder={placeholder ? translateUi(placeholder) : undefined}
      autoComplete="off"
      spellCheck={false}
      onChange={(event) => onChange(event.target.value)}
    />
  );
}

/** Password-style field with a show/hide button. */
export function SecretInput({ value, onChange, placeholder, label }: { value: string; onChange: (v: string) => void; placeholder?: string; label: string }) {
  const translateUi = useTranslation();
  const [shown, setShown] = useState(false);
  const id = useId();
  const maskWithCss = typeof CSS !== "undefined" && CSS.supports?.("-webkit-text-security", "disc");
  return (
    <div className="st-secret">
      <input
        id={id}
        className="st-input is-wide"
        // A key, not a login password: masked text where the browser can mask
        // it, so password managers neither fill nor offer to save it.
        type={shown || maskWithCss ? "text" : "password"}
        style={!shown && maskWithCss ? { WebkitTextSecurity: "disc" } as React.CSSProperties : undefined}
        value={value}
        aria-label={translateUi(label)}
        placeholder={placeholder ? translateUi(placeholder) : undefined}
        autoComplete={maskWithCss ? "off" : "new-password"}
        data-1p-ignore="" data-lpignore="true" data-form-type="other"
        spellCheck={false}
        onChange={(event) => onChange(event.target.value.trim())}
      />
      <button type="button" className="st-icon-btn" aria-controls={id} aria-label={translateUi(shown ? "Hide" : "Show")} onClick={() => setShown(!shown)}>
        {shown ? <EyeOff size={17} /> : <Eye size={17} />}
      </button>
    </div>
  );
}

export function Swatches<T extends string>({ value, options, onChange, label }: { value: T | null; options: Array<[T, string, string]>; onChange: (v: T) => void; label: string }) {
  const translateUi = useTranslation();
  return (
    <div className="st-swatches" role="radiogroup" aria-label={translateUi(label)}>
      {options.map(([option, name, color]) => (
        <button
          key={option}
          type="button"
          role="radio"
          aria-checked={option === value}
          aria-label={translateUi(name)}
          title={translateUi(name)}
          className={option === value ? "is-active" : ""}
          style={{ ["--swatch" as string]: color }}
          onClick={() => onChange(option)}
        >
          {option === value && <Check size={14} />}
        </button>
      ))}
    </div>
  );
}

/** Small status pill: ok / warn / off. */
export function Status({ tone, children }: { tone: "ok" | "warn" | "off" | "busy"; children: ReactNode }) {
  return <span className={`st-status st-status-${tone}`}>{children}</span>;
}

export function Empty({ children }: { children: ReactNode }) {
  return <p className="st-empty">{children}</p>;
}

export function safeArray<T>(value: T[] | null | undefined): T[] {
  return Array.isArray(value) ? value : [];
}

export function fallbackId(prefix: string, index: number, preferred?: string | null) {
  return preferred && String(preferred).trim() ? String(preferred) : `${prefix}-${index}`;
}
