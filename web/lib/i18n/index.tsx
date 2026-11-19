"use client";

import { createContext, useContext, useEffect, useMemo, useState, type ReactNode } from 'react';
import { directionOf, localeUrl, resolveLocale, translate, type Dictionary } from './core';
export { directionOf } from './core';

const empty: Dictionary = {};
const LanguageContext = createContext<{ language: string; dictionary: Dictionary }>({language:'en', dictionary:empty});
const cache = new Map<string, Dictionary>();

export function LanguageProvider({language, children}: {language:string; children:ReactNode}) {
  const locale = resolveLocale(language);
  const [loaded, setLoaded] = useState<{locale:string; dictionary:Dictionary}>({locale:'en', dictionary:empty});
  useEffect(() => {
    let active = true;
    if (locale === 'en') { setLoaded({locale, dictionary:empty}); return; }
    const cached = cache.get(locale);
    if (cached) { setLoaded({locale, dictionary:cached}); return; }
    fetch(localeUrl(locale)).then(response => {
      if (!response.ok) throw new Error('Translation download failed');
      return response.json();
    }).then((dictionary: Dictionary) => {
      cache.set(locale, dictionary);
      if (active) setLoaded({locale, dictionary});
    }).catch(() => { if (active) setLoaded({locale, dictionary:empty}); });
    return () => { active = false; };
  }, [locale]);
  useEffect(() => {
    document.documentElement.lang = language;
    document.documentElement.dir = directionOf(language);
  }, [language]);
  const dictionary = loaded.locale === locale ? loaded.dictionary : cache.get(locale) ?? empty;
  const value = useMemo(() => ({language, dictionary}), [language, dictionary]);
  return <LanguageContext.Provider value={value}>{children}</LanguageContext.Provider>;
}

export function useDirection(): 'rtl' | 'ltr' {
  return directionOf(useContext(LanguageContext).language);
}

export function useTranslation() {
  const {dictionary} = useContext(LanguageContext);
  return useMemo(() => (text: string, values?: Record<string, string | number>) => translate(dictionary, text, values), [dictionary]);
}
