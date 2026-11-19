import manifest from './manifest.json';
export type Dictionary = Record<string, string>;
const indexes = new WeakMap<Dictionary, Map<string, string>>();
const patterns = new WeakMap<Dictionary, Array<{regex:RegExp; slots:string[]; target:string}>>();
const normalizeKey = (text: string) => text.trim().toLocaleLowerCase('en').replace(/…/g, '...');
function translateTemplate(dictionary: Dictionary, text: string): string | undefined {
  let list = patterns.get(dictionary);
  if (!list) {
    list = Object.entries(dictionary).filter(([key]) => /\{\w+\}/.test(key)).map(([key, target]) => {
      const slots: string[] = [];
      const parts = key.split(/(\{\w+\})/g).map(part => {
        if (/^\{\w+\}$/.test(part)) { slots.push(part); return '(.+?)'; }
        return part.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
      });
      return {regex:new RegExp(`^${parts.join('')}$`, 'i'), slots, target};
    });
    patterns.set(dictionary, list);
  }
  for (const {regex, slots, target} of list) {
    const match = text.match(regex);
    if (match) return target.replace(/\{\w+\}/g, slot => match[slots.indexOf(slot) + 1] ?? slot);
  }
}
export function resolveLocale(language: string): string {
  const normalized = language.replace(/_/g, '-');
  const base = normalized.split('-')[0].toLowerCase().replace(/^iw$/, 'he').replace(/^no$/, 'nb').replace(/^fil$/, 'tl').replace(/^in$/, 'id');
  if (base === 'en') return 'en';
  const keys = Object.keys(manifest);
  return keys.find(key => key.toLowerCase() === normalized.toLowerCase())
    ?? (base === 'zh' ? (/TW|HK|Hant/i.test(normalized) ? 'zh-TW' : 'zh-CN') : keys.find(key => key === base) ?? keys.find(key => key.startsWith(`${base}-`))) ?? 'en';
}
export function translate(dictionary: Dictionary, text: string, values?: Record<string, string | number>): string {
  const key = text.trim();
  let index = indexes.get(dictionary);
  if (!index) { index = new Map(Object.entries(dictionary).map(([source, target]) => [normalizeKey(source), target])); indexes.set(dictionary, index); }
  const exact = Object.prototype.hasOwnProperty.call(dictionary, key) ? dictionary[key] : undefined;
  const translated = exact ?? index.get(normalizeKey(key)) ?? translateTemplate(dictionary, key) ?? key;
  const result = text.slice(0, text.length - text.trimStart().length) + translated + text.slice(text.trimEnd().length);
  return values ? result.replace(/\{(\w+)\}/g, (match, name) => String(values[name] ?? match)) : result;
}
/** Writing direction of an interface language (Arabic, Hebrew, Persian, Urdu read right to left). */
export function directionOf(language: string): 'rtl' | 'ltr' {
  return /^(ar|he|iw|fa|ur)(-|$)/i.test(language) ? 'rtl' : 'ltr';
}

export function localeUrl(locale: string): string {
  return `/i18n/${locale}.json?v=${manifest[locale as keyof typeof manifest]}`;
}
