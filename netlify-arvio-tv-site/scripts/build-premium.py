"""Build the public Premium guide using the app's existing translations."""
import json
import re
from pathlib import Path

site = Path(__file__).resolve().parents[1]
root = site.parent
out = site / 'premium'
out.mkdir(exist_ok=True)
keys = {
    'download': 'Download this source',
    'downloadBody': 'Watch or download directly on Windows, Mac and mobile',
    'sources': 'Sources',
    'intro': 'Take your existing ARVIO setup to Windows, Mac, iPhone, iPad and smart-TV browsers. Your profiles, libraries, addons and progress stay connected through ARVIO Cloud.',
    'sync': 'Same profiles, libraries and watch progress',
    'play': 'Browser playback and one-click VLC',
    'free': 'Android and TV app remains completely free',
    'explore': 'Discover',
    'period': '/ month',
    'notice': 'ARVIO is a media hub for sources you configure. Catalog entries do not grant viewing rights. Connect only services and media you are authorized to use.',
    'language': 'App Language',
    'home': 'Home', 'library': 'Library', 'server': 'Homeserver', 'tv': 'Live TV', 'sports': 'Sports',
}
hosting = json.loads((site / 'scripts/premium-hosting.json').read_text(encoding='utf-8'))
membership_terms = json.loads((site / 'scripts/premium-membership-terms.json').read_text(encoding='utf-8'))
# Product copy for hosted ARVIO Web (plans, refund promise, explore mode). Locales without
# a translation fall back per key to English; 'open' falls back to the localized 'explore'.
offer_copy = json.loads((site / 'scripts/premium-offer-copy.json').read_text(encoding='utf-8'))
manifest = json.loads((root / 'web/lib/i18n/manifest.json').read_text())
languages = json.loads((root / 'web/lib/i18n/languages.json').read_text(encoding='utf-8'))
languages.append({'code': 'en-GB', 'label': 'English (UK)'})
# Explicit self-hosting copy is independent of the paid hosted membership.
self_host_copy = {
    'en': 'Self-host ARVIO Web for free.',
    'af': 'Huisves ARVIO Web self gratis.',
    'sq': 'Priteni vetë ARVIO Web falas.',
    'ar': 'استضف ARVIO Web بنفسك مجانًا.',
    'eu': 'Ostatu ARVIO Web zure zerbitzarian doan.',
    'bn': 'বিনামূল্যে নিজে ARVIO Web হোস্ট করুন।',
    'bg': 'Хоствайте ARVIO Web сами безплатно.',
    'ca': 'Allotja ARVIO Web tu mateix gratuïtament.',
    'zh-CN': '免费自行托管 ARVIO Web。',
    'zh-TW': '免費自行託管 ARVIO Web。',
    'hr': 'Besplatno sami hostajte ARVIO Web.',
    'cs': 'Hostujte ARVIO Web sami zdarma.',
    'da': 'Host ARVIO Web selv gratis.',
    'nl': 'Host ARVIO Web zelf gratis.',
    'et': 'Majuta ARVIO Webi ise tasuta.',
    'tl': 'I-host ang ARVIO Web nang libre sa sarili mong server.',
    'fi': 'Ylläpidä ARVIO Webiä itse ilmaiseksi.',
    'fr': 'Hébergez ARVIO Web vous-même gratuitement.',
    'gl': 'Aloxa ARVIO Web ti mesmo de balde.',
    'de': 'ARVIO Web kostenlos selbst hosten.',
    'el': 'Φιλοξενήστε μόνοι σας το ARVIO Web δωρεάν.',
    'gu': 'ARVIO Web જાતે મફતમાં હોસ્ટ કરો.',
    'he': 'ארחו את ARVIO Web בעצמכם בחינם.',
    'hi': 'ARVIO Web को स्वयं मुफ़्त में होस्ट करें।',
    'hu': 'Futtasd saját szerveren az ARVIO Webet ingyen.',
    'id': 'Host ARVIO Web sendiri secara gratis.',
    'it': 'Ospita ARVIO Web gratuitamente sul tuo server.',
    'ja': 'ARVIO Web を無料でセルフホストできます。',
    'kn': 'ARVIO Web ಅನ್ನು ಉಚಿತವಾಗಿ ಸ್ವತಃ ಹೋಸ್ಟ್ ಮಾಡಿ.',
    'ko': 'ARVIO Web을 무료로 직접 호스팅하세요.',
    'lv': 'Izmitiniet ARVIO Web pats bez maksas.',
    'lt': 'Nemokamai talpinkite ARVIO Web patys.',
    'ms': 'Hos ARVIO Web sendiri secara percuma.',
    'ml': 'ARVIO Web സൗജന്യമായി സ്വയം ഹോസ്റ്റ് ചെയ്യൂ.',
    'mr': 'ARVIO Web स्वतः मोफत होस्ट करा.',
    'nb': 'Host ARVIO Web selv gratis.',
    'fa': 'ARVIO Web را رایگان روی سرور خود میزبانی کنید.',
    'pl': 'Hostuj ARVIO Web samodzielnie za darmo.',
    'pt-PT': 'Aloje o ARVIO Web gratuitamente no seu servidor.',
    'pt-BR': 'Hospede o ARVIO Web gratuitamente no seu servidor.',
    'pa': 'ARVIO Web ਨੂੰ ਆਪ ਮੁਫ਼ਤ ਹੋਸਟ ਕਰੋ।',
    'ro': 'Găzduiește ARVIO Web gratuit pe propriul server.',
    'ru': 'Размещайте ARVIO Web на своём сервере бесплатно.',
    'sr': 'Бесплатно хостујте ARVIO Web на свом серверу.',
    'sk': 'Hostite ARVIO Web sami zadarmo.',
    'sl': 'Gostujte ARVIO Web brezplačno na svojem strežniku.',
    'es': 'Aloja ARVIO Web gratis en tu propio servidor.',
    'sw': 'Pangisha ARVIO Web mwenyewe bila malipo.',
    'sv': 'Hosta ARVIO Web själv gratis.',
    'ta': 'ARVIO Web-ஐ இலவசமாக நீங்களே ஹோஸ்ட் செய்யுங்கள்.',
    'te': 'ARVIO Web ను ఉచితంగా మీరే హోస్ట్ చేయండి.',
    'th': 'โฮสต์ ARVIO Web เองได้ฟรี',
    'tr': 'ARVIO Web’i ücretsiz olarak kendiniz barındırın.',
    'uk': 'Розміщуйте ARVIO Web на власному сервері безкоштовно.',
    'ur': 'ARVIO Web کو اپنے سرور پر مفت ہوسٹ کریں۔',
    'vi': 'Tự lưu trữ ARVIO Web miễn phí.',
}
feature_copy = {
    'en': {
        'hosting': 'Live TV. Your favourite sources. Your ARVIO, in a browser. We handle the hosting — you enjoy the setup you already love.',
        'tv': 'Live TV in your browser',
        'tvBody': 'Bring your own IPTV service. Browse the programme guide and play supported channels right in ARVIO Web.',
        'download': 'Your sources. Ready to download.',
        'downloadBody': 'Save supported sources from the source picker. Download on desktop, or hand off to VLC on iPhone and iPad.',
        'libraryBody': 'Your watchlists, personal lists and watch progress — connected through ARVIO Cloud.',
        'serverBody': 'Bring your Jellyfin, Plex, Emby or Silo library into the same familiar interface.',
        'sportsBody': 'Explore sports events and find sources from your connected services.',
        'free': 'Android & TV stay free. Self-hosting stays free.',
    },
    'nl': {
        'intro': 'Neem je ARVIO mee naar Windows, Mac, iPhone, iPad en smart-tv-browsers. Je profielen, bibliotheken, add-ons en kijkvoortgang blijven verbonden via ARVIO Cloud.',
        'hosting': 'Live-tv. Jouw favoriete bronnen. Jouw ARVIO, in de browser. Wij regelen de hosting — jij geniet van je vertrouwde setup.',
        'tv': 'Live-tv in je browser',
        'tvBody': 'Koppel je eigen IPTV-dienst. Bekijk de tv-gids en speel ondersteunde zenders rechtstreeks af in ARVIO Web.',
        'download': 'Jouw bronnen. Klaar om te downloaden.',
        'downloadBody': 'Bewaar ondersteunde bronnen vanuit de bronkeuze. Download op je computer of open de download in VLC op iPhone en iPad.',
        'libraryBody': 'Je kijklijsten, persoonlijke lijsten en kijkvoortgang — verbonden via ARVIO Cloud.',
        'serverBody': 'Breng je Jellyfin-, Plex-, Emby- of Silo-bibliotheek samen in dezelfde vertrouwde interface.',
        'sportsBody': 'Ontdek sportevenementen en vind bronnen via je gekoppelde diensten.',
        'free': 'Android en tv blijven gratis. Zelf hosten ook.',
        'play': 'Afspelen in je browser of openen in VLC',
        'sync': 'Je profielen, bibliotheken en kijkvoortgang blijven verbonden',
    },
}
# Per-locale copy written or reviewed by native speakers (scripts/premium-native-copy.json),
# keyed by the page's language code (de-DE, fr-FR, ...).
native_path = site / 'scripts/premium-native-copy.json'
native_copy = json.loads(native_path.read_text(encoding='utf-8')) if native_path.exists() else {}
data = {}
for entry in languages:
    code = entry['code']
    base = code.split('-')[0]
    locale = code if code in manifest else ('nb' if base == 'no' else base)
    dictionary = {} if base == 'en' else json.loads((root / f'web/public/i18n/{locale}.json').read_text(encoding='utf-8'))
    normalized = {k.strip().lower(): v for k, v in dictionary.items()}
    translations = {key: normalized.get(value.lower(), value) for key, value in keys.items()}
    # The main copy must never silently fall back to English.
    if base != 'en':
        for key in ('intro', 'sync', 'play', 'free', 'period', 'notice', 'explore'):
            assert keys[key].lower() in normalized, (code, key)
            assert isinstance(normalized[keys[key].lower()], str) and normalized[keys[key].lower()].strip(), (code, key)
    translations['explore'] = ('Explore' if base == 'en' else translations['explore']) + ' ARVIO Web'
    translations['selfHost'] = self_host_copy.get(locale, self_host_copy.get(base))
    assert translations['selfHost'], ('Missing self-hosting copy', code)
    translations['hosting'] = hosting.get(locale, hosting.get(base))
    assert translations['hosting'], code
    translations['membershipTerms'] = membership_terms.get(locale, membership_terms.get(base))
    assert translations['membershipTerms'], ('Missing membership terms', code)
    translations.update({
        'tvBody': translations['play'],
        'libraryBody': translations['sync'],
        'serverBody': 'Jellyfin · Plex · Emby · Silo',
        'sportsBody': translations['tv'],
    })
    if base in ('en', 'nl'):
        translations.update(feature_copy[base])
    offer = offer_copy.get(locale) or offer_copy.get(code) or offer_copy.get(base) or {}
    for key, english in offer_copy['en'].items():
        translations[key] = offer.get(key) or (translations['explore'] if key == 'open' and base != 'en' else english)
    # Reviewed native copy for the localized Premium pages wins over everything above.
    translations.update(native_copy.get(code, {}))
    data[code] = {'label': entry['label'], **translations}
required_copy = set(re.findall(r'data-copy="([A-Za-z]+)"', (out / 'index.html').read_text(encoding='utf-8')))
for code, copy in data.items():
    for key in required_copy:
        assert isinstance(copy.get(key), str) and copy[key].strip(), ('Missing rendered Premium copy', code, key)
(out / 'languages.json').write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding='utf-8')
print(f'Built Premium copy for {len(data)} language variants.')

