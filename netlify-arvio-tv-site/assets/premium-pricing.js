/* Keep public prices aligned with the hosted app's country-based offer. */
(function (root) {
  'use strict';
  const countries = new Set([
    'AR', 'BD', 'BO', 'BR', 'CI', 'CM', 'CO', 'DO', 'DZ', 'EC', 'EG', 'ET', 'GE', 'GH', 'GT', 'HN',
    'ID', 'IN', 'JM', 'JO', 'KE', 'KG', 'KH', 'KZ', 'LA', 'LK', 'MA', 'MD', 'MM', 'MN', 'MX', 'NG',
    'NI', 'NP', 'PE', 'PH', 'PK', 'PY', 'SN', 'SV', 'TH', 'TN', 'TR', 'TZ', 'UA', 'UG', 'UZ', 'VE',
    'VN', 'ZA', 'ZM', 'ZW'
  ]);
  let countryRequest;
  const validCountry = value => typeof value === 'string' && /^[A-Z]{2}$/.test(value) ? value : null;
  const regionalCountry = value => countries.has(validCountry(value));
  function loadCountry() {
    if (countryRequest) return countryRequest;
    countryRequest = (async () => {
      try {
        const cached = root.sessionStorage.getItem('arvio.visitorCountry.v1');
        if (validCountry(cached)) return cached;
      } catch { /* Storage is optional. */ }
      let timeout;
      try {
        const controller = new AbortController();
        timeout = root.setTimeout(() => controller.abort(), 4000);
        const response = await root.fetch('https://web.arvio.tv/api/geo', {cache:'no-store', signal:controller.signal});
        const country = validCountry(response.ok ? (await response.json())?.country : null);
        try { if (country) root.sessionStorage.setItem('arvio.visitorCountry.v1', country); } catch { /* optional */ }
        return country;
      } catch { return null; }
      finally { root.clearTimeout(timeout); }
    })();
    return countryRequest;
  }
  function apply(copy, regional, document = root.document) {
    const text = (selector, value) => {
      if (typeof value === 'string') document.querySelectorAll(selector).forEach(element => { element.textContent = value; });
    };
    text('[data-yearly-price]', regional ? '$10' : '$20');
    document.querySelectorAll('[data-yearly-was]').forEach(element => { element.hidden = !regional; });
    text('[data-yearly-badge]', regional ? copy.regionalBadge : copy.yearlyBadge);
    text('[data-yearly-saving]', regional ? copy.regionalSaving : copy.yearlySaving);
    text('[data-plan-price-summary]', regional ? copy.regionalFromPrice : copy.fromPrice);
    text('[data-device-price-summary]', `${copy.benefitExplore} ${regional ? copy.regionalFromPrice : copy.fromPrice}`);
  }
  const api = {loadCountry, regionalCountry, apply};
  if (typeof module !== 'undefined' && module.exports) module.exports = api;
  else root.ARVIOPremiumPricing = api;

  if (root.document?.querySelector('[data-premium-pricing-home]')) {
    Promise.all([
      root.fetch('/premium/languages.json').then(response => response.ok ? response.json() : null),
      loadCountry()
    ]).then(([languages, country]) => {
      if (!languages) return;
      const lang = root.document.documentElement.lang.toLowerCase();
      const code = Object.keys(languages).find(code => code.toLowerCase() === lang) ||
        Object.keys(languages).find(code => code.toLowerCase().split('-')[0] === lang.replace(/^nb$/, 'no')) || 'en-US';
      apply(languages[code], regionalCountry(country));
    }).catch(() => { /* Static prices and native plan links remain usable. */ });
  }
})(typeof window === 'undefined' ? globalThis : window);
