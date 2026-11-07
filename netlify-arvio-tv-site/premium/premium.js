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

  const REGIONAL_COUNTRIES = new Set([
    "AR", "BD", "BO", "BR", "CI", "CM", "CO", "DO", "DZ", "EC", "EG", "ET", "GE", "GH", "GT", "HN",
    "ID", "IN", "JM", "JO", "KE", "KG", "KH", "KZ", "LA", "LK", "MA", "MD", "MM", "MN", "MX", "NG",
    "NI", "NP", "PE", "PH", "PK", "PY", "SN", "SV", "TH", "TN", "TR", "TZ", "UA", "UG", "UZ", "VE",
    "VN", "ZA", "ZM", "ZW"
  ]);

  let isRegionalVisitor = false;

  const REGIONAL_BADGES = {
    'en': 'Regional price · save 72%',
    'es': 'Precio regional · ahorra un 72%',
    'pt': 'Preço regional · economize 72%',
    'pt-BR': 'Preço regional · economize 72%',
    'hi': 'क्षेत्रीय कीमत · 72% बचत',
    'id': 'Harga regional · hemat 72%',
    'vi': 'Giá khu vực · tiết kiệm 72%',
    'th': 'ราคาตามภูมิภาค · ประหยัด 72%',
    'tr': 'Bölgesel fiyat · 72% tasarruf',
    'ar': 'سعر إقليمي · وفّر 72%',
    'de': 'Regionaler Preis · 72% sparen',
    'fr': 'Prix régional · 72% d’économie'
  };

  const REGIONAL_NOTES = {
    'en': 'One payment, no automatic renewal. About $0.83 a month.',
    'es': 'Un pago único, sin renovación automática. Aprox. $0.83 al mes.',
    'pt': 'Pagamento único, sem renovação automática. Cerca de US$ 0,83 por mês.',
    'pt-BR': 'Pagamento único, sem renovação automática. Cerca de US$ 0,83 por mês.',
    'hi': 'एक बार का भुगतान, कोई स्वचालित नवीनीकरण नहीं। लगभग $0.83 प्रति माह।',
  };

  async function checkRegional() {
    try {
      const cached = sessionStorage.getItem('arvio.visitorCountry.v1');
      if (cached) {
        if (REGIONAL_COUNTRIES.has(cached.toUpperCase())) isRegionalVisitor = true;
        return;
      }
      const res = await fetch('https://web.arvio.tv/api/geo', { cache: 'no-store' });
      if (res.ok) {
        const data = await res.json();
        const country = typeof data?.country === 'string' ? data.country.toUpperCase() : null;
        if (country) {
          sessionStorage.setItem('arvio.visitorCountry.v1', country);
          if (REGIONAL_COUNTRIES.has(country)) isRegionalVisitor = true;
        }
      }
    } catch {
      const tz = Intl.DateTimeFormat().resolvedOptions().timeZone || '';
      const lang = navigator.language || '';
      const regionalTz = /^(Asia\/(Kolkata|Calcutta|Manila|Jakarta|Bangkok|Ho_Chi_Minh|Karachi|Dhaka)|America\/(Sao_Paulo|Buenos_Aires|Argentina)|Africa\/(Lagos|Nairobi|Johannesburg)|Europe\/Kyiv)/i;
      const regionalLang = /-(IN|BR|AR|PH|ID|VN|TH|PK|BD|NG|KE|ZA|UA|MX|CO|CL|PE|EG)$/i;
      if (regionalTz.test(tz) || regionalLang.test(lang)) {
        isRegionalVisitor = true;
      }
    }
  }

  function applyRegionalPricing(code) {
    if (!isRegionalVisitor) return;
    const base = (code || 'en').split('-')[0];
    const planBest = document.querySelector('.plan-best');
    if (planBest) {
      const badge = planBest.querySelector('[data-copy="yearlyBadge"], .plan-badge');
      if (badge) {
        badge.textContent = REGIONAL_BADGES[code] || REGIONAL_BADGES[base] || REGIONAL_BADGES['en'];
        badge.classList.add('regional');
      }
      const price = planBest.querySelector('.plan-price');
      if (price) {
        const onceLabel = languages[code]?.once || 'once';
        price.innerHTML = '<s class="plan-price-was">$20</s> <strong>$10</strong> <span data-copy="once">' + onceLabel + '</span>';
      }
      const note = planBest.querySelector('[data-copy="yearlyNote"], .plan-note');
      if (note) {
        note.textContent = REGIONAL_NOTES[code] || REGIONAL_NOTES[base] || REGIONAL_NOTES['en'];
      }
    }

    document.querySelectorAll('[data-copy="fromPrice"]').forEach(el => {
      el.textContent = el.textContent.replace(/(\$|\b)20(\$|\b)?/, '$110$2');
    });
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
    applyRegionalPricing(code);
  }

  await checkRegional();

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
