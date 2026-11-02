/* Localized public copy. Campaign measurement is handled by premium-journey.js. */
(async function () {
  const response = await fetch('/premium/languages.json');
  if (!response.ok) return;
  const languages = await response.json();
  const select = document.getElementById('language');
  select.replaceChildren();
  for (const [code, copy] of Object.entries(languages)) {
    const option = document.createElement('option');
    option.value = code;
    try {
      const locale = code.replace(/^no-/, 'nb-');
      const name = new Intl.DisplayNames([locale], {type:'language'}).of(code.startsWith('zh-') ? (code === 'zh-CN' ? 'zh-Hans' : 'zh-Hant') : code);
      option.textContent = name || copy.label;
    } catch { option.textContent = copy.label; }
    select.append(option);
  }
  function match(value) {
    if (!value) return null;
    const code = Object.keys(languages).find(k => k.toLowerCase() === value.toLowerCase());
    if (code) return code;
    if (/^zh/i.test(value)) return /TW|HK|Hant/i.test(value) ? 'zh-TW' : 'zh-CN';
    const base = value.split('-')[0].replace(/^nb$/, 'no');
    return Object.keys(languages).find(k => k.split('-')[0] === base);
  }
  function render(code) {
    const copy = languages[code];
    document.documentElement.lang = code;
    document.documentElement.dir = /^(ar|he|fa|ur)-/.test(code) ? 'rtl' : 'ltr';
    select.value = code;
    select.setAttribute('aria-label', copy.language);
    for (const element of document.querySelectorAll('[data-copy]')) if (copy[element.dataset.copy]) element.textContent = copy[element.dataset.copy];
    for (const [index, key] of ['tv','library','server','sports'].entries()) document.querySelectorAll('.gallery img')[index].alt = 'ARVIO Web — ' + copy[key];
    document.title = 'ARVIO Premium — ' + copy.headline;
  }
  // A localized page (/de/premium/ ...) opens in its own language; the English page follows the browser.
  const initial = match(new URL(location.href).searchParams.get('lang')) || match(document.documentElement.dataset?.pageLang) || (navigator.languages || [navigator.language]).map(match).find(Boolean) || 'en-US';
  render(initial);
  select.addEventListener('change', () => {
    render(select.value);
    const url = new URL(location.href);
    url.searchParams.set('lang', select.value);
    history.replaceState(null, '', url);
  });
})().catch(() => { /* English content remains usable when translations are unavailable. */ });
