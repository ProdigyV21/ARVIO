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
    'join': 'Subscribe on Ko-fi',
    'trial': 'Start {value0}-day free trial',
    'period': '/ month',
    'notice': 'ARVIO is a media hub for sources you configure. Catalog entries do not grant viewing rights. Connect only services and media you are authorized to use.',
    'language': 'App Language',
    'home': 'Home', 'library': 'Library', 'server': 'Homeserver', 'tv': 'Live TV', 'sports': 'Sports',
}
hosting = json.loads((site / 'scripts/premium-hosting.json').read_text(encoding='utf-8'))
membership_terms = json.loads((site / 'scripts/premium-membership-terms.json').read_text(encoding='utf-8'))
manifest = json.loads((root / 'web/lib/i18n/manifest.json').read_text())
languages = json.loads((root / 'web/lib/i18n/languages.json').read_text(encoding='utf-8'))
languages.append({'code': 'en-GB', 'label': 'English (UK)'})
# These short public-site notes have no app dictionary key. Keep each locale
# explicit so a newly supported language cannot silently receive English copy.
# The action itself below reuses the app's translated free-trial key.
trial_copy = {
    'en': ('No payment required.', 'One free trial per ARVIO Cloud account.', 'after the trial', 'Self-host ARVIO Web for free.'),
    'af': ('Geen betaling nodig nie.', 'Een gratis proeftydperk per ARVIO Cloud-rekening.', 'ná die proeftydperk', 'Huisves ARVIO Web self gratis.'),
    'sq': ('Nuk kërkohet pagesë.', 'Një provë falas për çdo llogari ARVIO Cloud.', 'pas provës', 'Priteni vetë ARVIO Web falas.'),
    'ar': ('لا يلزم الدفع.', 'تجربة مجانية واحدة لكل حساب ARVIO Cloud.', 'بعد التجربة', 'استضف ARVIO Web بنفسك مجانًا.'),
    'eu': ('Ez da ordainketarik behar.', 'Doako proba bat ARVIO Cloud kontu bakoitzeko.', 'probaren ondoren', 'Ostatu ARVIO Web zure zerbitzarian doan.'),
    'bn': ('কোনো অর্থপ্রদান প্রয়োজন নেই।', 'প্রতি ARVIO Cloud অ্যাকাউন্টে একটি বিনামূল্যের ট্রায়াল।', 'ট্রায়ালের পরে', 'বিনামূল্যে নিজে ARVIO Web হোস্ট করুন।'),
    'bg': ('Не се изисква плащане.', 'Един безплатен пробен период за всеки ARVIO Cloud акаунт.', 'след пробния период', 'Хоствайте ARVIO Web сами безплатно.'),
    'ca': ('No cal pagar.', 'Una prova gratuïta per compte d’ARVIO Cloud.', 'després de la prova', 'Allotja ARVIO Web tu mateix gratuïtament.'),
    'zh-CN': ('无需付款。', '每个 ARVIO Cloud 账户可免费试用一次。', '试用结束后', '免费自行托管 ARVIO Web。'),
    'zh-TW': ('無需付款。', '每個 ARVIO Cloud 帳戶可免費試用一次。', '試用結束後', '免費自行託管 ARVIO Web。'),
    'hr': ('Plaćanje nije potrebno.', 'Jedno besplatno probno razdoblje po ARVIO Cloud računu.', 'nakon probnog razdoblja', 'Besplatno sami hostajte ARVIO Web.'),
    'cs': ('Není nutná platba.', 'Jedna bezplatná zkušební verze na účet ARVIO Cloud.', 'po zkušební době', 'Hostujte ARVIO Web sami zdarma.'),
    'da': ('Ingen betaling kræves.', 'Én gratis prøveperiode pr. ARVIO Cloud-konto.', 'efter prøveperioden', 'Host ARVIO Web selv gratis.'),
    'nl': ('Geen betaling nodig.', 'Eén gratis proefperiode per ARVIO Cloud-account.', 'na de proefperiode', 'Host ARVIO Web zelf gratis.'),
    'et': ('Makset pole vaja.', 'Üks tasuta prooviperiood ARVIO Cloudi konto kohta.', 'pärast prooviperioodi', 'Majuta ARVIO Webi ise tasuta.'),
    'tl': ('Walang kailangang bayaran.', 'Isang libreng pagsubok sa bawat ARVIO Cloud account.', 'pagkatapos ng pagsubok', 'I-host ang ARVIO Web nang libre sa sarili mong server.'),
    'fi': ('Maksua ei tarvita.', 'Yksi maksuton kokeilu ARVIO Cloud -tiliä kohden.', 'kokeilun jälkeen', 'Ylläpidä ARVIO Webiä itse ilmaiseksi.'),
    'fr': ('Aucun paiement requis.', 'Un essai gratuit par compte ARVIO Cloud.', 'après l’essai', 'Hébergez ARVIO Web vous-même gratuitement.'),
    'gl': ('Non é necesario pagar.', 'Unha proba gratuíta por conta de ARVIO Cloud.', 'despois da proba', 'Aloxa ARVIO Web ti mesmo de balde.'),
    'de': ('Keine Zahlung erforderlich.', 'Eine kostenlose Testphase pro ARVIO Cloud-Konto.', 'nach der Testphase', 'ARVIO Web kostenlos selbst hosten.'),
    'el': ('Δεν απαιτείται πληρωμή.', 'Μία δωρεάν δοκιμή ανά λογαριασμό ARVIO Cloud.', 'μετά τη δοκιμή', 'Φιλοξενήστε μόνοι σας το ARVIO Web δωρεάν.'),
    'gu': ('કોઈ ચુકવણી જરૂરી નથી.', 'દરેક ARVIO Cloud ખાતા માટે એક મફત અજમાયશ.', 'અજમાયશ પછી', 'ARVIO Web જાતે મફતમાં હોસ્ટ કરો.'),
    'he': ('אין צורך בתשלום.', 'תקופת ניסיון חינם אחת לכל חשבון ARVIO Cloud.', 'לאחר תקופת הניסיון', 'ארחו את ARVIO Web בעצמכם בחינם.'),
    'hi': ('भुगतान की आवश्यकता नहीं है।', 'प्रत्येक ARVIO Cloud खाते पर एक निःशुल्क ट्रायल।', 'ट्रायल के बाद', 'ARVIO Web को स्वयं मुफ़्त में होस्ट करें।'),
    'hu': ('Nincs szükség fizetésre.', 'ARVIO Cloud-fiókonként egy ingyenes próba.', 'a próbaidőszak után', 'Futtasd saját szerveren az ARVIO Webet ingyen.'),
    'id': ('Tidak perlu pembayaran.', 'Satu uji coba gratis per akun ARVIO Cloud.', 'setelah uji coba', 'Host ARVIO Web sendiri secara gratis.'),
    'it': ('Nessun pagamento richiesto.', 'Una prova gratuita per account ARVIO Cloud.', 'dopo la prova', 'Ospita ARVIO Web gratuitamente sul tuo server.'),
    'ja': ('支払いは不要です。', 'ARVIO Cloud アカウントごとに無料体験は1回です。', '体験期間終了後', 'ARVIO Web を無料でセルフホストできます。'),
    'kn': ('ಪಾವತಿ ಅಗತ್ಯವಿಲ್ಲ.', 'ಪ್ರತಿ ARVIO Cloud ಖಾತೆಗೆ ಒಂದು ಉಚಿತ ಪ್ರಯೋಗ.', 'ಪ್ರಯೋಗದ ನಂತರ', 'ARVIO Web ಅನ್ನು ಉಚಿತವಾಗಿ ಸ್ವತಃ ಹೋಸ್ಟ್ ಮಾಡಿ.'),
    'ko': ('결제가 필요하지 않습니다.', 'ARVIO Cloud 계정당 한 번 무료로 체험할 수 있습니다.', '체험 종료 후', 'ARVIO Web을 무료로 직접 호스팅하세요.'),
    'lv': ('Maksājums nav nepieciešams.', 'Viens bezmaksas izmēģinājums katram ARVIO Cloud kontam.', 'pēc izmēģinājuma', 'Izmitiniet ARVIO Web pats bez maksas.'),
    'lt': ('Mokėti nereikia.', 'Viena nemokama bandomoji versija kiekvienai ARVIO Cloud paskyrai.', 'po bandomojo laikotarpio', 'Nemokamai talpinkite ARVIO Web patys.'),
    'ms': ('Tiada bayaran diperlukan.', 'Satu percubaan percuma bagi setiap akaun ARVIO Cloud.', 'selepas percubaan', 'Hos ARVIO Web sendiri secara percuma.'),
    'ml': ('പണമടയ്ക്കേണ്ടതില്ല.', 'ഓരോ ARVIO Cloud അക്കൗണ്ടിനും ഒരു സൗജന്യ പരീക്ഷണം.', 'പരീക്ഷണത്തിന് ശേഷം', 'ARVIO Web സൗജന്യമായി സ്വയം ഹോസ്റ്റ് ചെയ്യൂ.'),
    'mr': ('पैसे देण्याची गरज नाही.', 'प्रत्येक ARVIO Cloud खात्यासाठी एक मोफत चाचणी.', 'चाचणीनंतर', 'ARVIO Web स्वतः मोफत होस्ट करा.'),
    'nb': ('Ingen betaling kreves.', 'Én gratis prøveperiode per ARVIO Cloud-konto.', 'etter prøveperioden', 'Host ARVIO Web selv gratis.'),
    'fa': ('نیازی به پرداخت نیست.', 'یک دوره آزمایشی رایگان برای هر حساب ARVIO Cloud.', 'پس از دوره آزمایشی', 'ARVIO Web را رایگان روی سرور خود میزبانی کنید.'),
    'pl': ('Płatność nie jest wymagana.', 'Jeden bezpłatny okres próbny na konto ARVIO Cloud.', 'po okresie próbnym', 'Hostuj ARVIO Web samodzielnie za darmo.'),
    'pt-PT': ('Não é necessário pagar.', 'Um período experimental gratuito por conta ARVIO Cloud.', 'após o período experimental', 'Aloje o ARVIO Web gratuitamente no seu servidor.'),
    'pt-BR': ('Não é necessário pagar.', 'Um teste grátis por conta ARVIO Cloud.', 'após o teste', 'Hospede o ARVIO Web gratuitamente no seu servidor.'),
    'pa': ('ਭੁਗਤਾਨ ਦੀ ਲੋੜ ਨਹੀਂ ਹੈ।', 'ਹਰੇਕ ARVIO Cloud ਖਾਤੇ ਲਈ ਇੱਕ ਮੁਫ਼ਤ ਅਜ਼ਮਾਇਸ਼।', 'ਅਜ਼ਮਾਇਸ਼ ਤੋਂ ਬਾਅਦ', 'ARVIO Web ਨੂੰ ਆਪ ਮੁਫ਼ਤ ਹੋਸਟ ਕਰੋ।'),
    'ro': ('Nu este necesară plata.', 'O perioadă de probă gratuită pentru fiecare cont ARVIO Cloud.', 'după perioada de probă', 'Găzduiește ARVIO Web gratuit pe propriul server.'),
    'ru': ('Оплата не требуется.', 'Один бесплатный пробный период на аккаунт ARVIO Cloud.', 'после пробного периода', 'Размещайте ARVIO Web на своём сервере бесплатно.'),
    'sr': ('Плаћање није потребно.', 'Један бесплатан пробни период по ARVIO Cloud налогу.', 'након пробног периода', 'Бесплатно хостујте ARVIO Web на свом серверу.'),
    'sk': ('Platba nie je potrebná.', 'Jedna bezplatná skúšobná verzia na účet ARVIO Cloud.', 'po skúšobnej dobe', 'Hostite ARVIO Web sami zadarmo.'),
    'sl': ('Plačilo ni potrebno.', 'En brezplačen preizkus za vsak račun ARVIO Cloud.', 'po preizkusu', 'Gostujte ARVIO Web brezplačno na svojem strežniku.'),
    'es': ('No se requiere pago.', 'Una prueba gratuita por cuenta de ARVIO Cloud.', 'después de la prueba', 'Aloja ARVIO Web gratis en tu propio servidor.'),
    'sw': ('Hakuna malipo yanayohitajika.', 'Jaribio moja la bure kwa kila akaunti ya ARVIO Cloud.', 'baada ya jaribio', 'Pangisha ARVIO Web mwenyewe bila malipo.'),
    'sv': ('Ingen betalning krävs.', 'En kostnadsfri provperiod per ARVIO Cloud-konto.', 'efter provperioden', 'Hosta ARVIO Web själv gratis.'),
    'ta': ('பணம் செலுத்தத் தேவையில்லை.', 'ஒவ்வொரு ARVIO Cloud கணக்கிற்கும் ஒரு இலவசச் சோதனை.', 'சோதனைக்குப் பிறகு', 'ARVIO Web-ஐ இலவசமாக நீங்களே ஹோஸ்ட் செய்யுங்கள்.'),
    'te': ('చెల్లింపు అవసరం లేదు.', 'ప్రతి ARVIO Cloud ఖాతాకు ఒక ఉచిత ట్రయల్.', 'ట్రయల్ తర్వాత', 'ARVIO Web ను ఉచితంగా మీరే హోస్ట్ చేయండి.'),
    'th': ('ไม่ต้องชำระเงิน', 'ทดลองใช้งานฟรีหนึ่งครั้งต่อบัญชี ARVIO Cloud', 'หลังช่วงทดลองใช้งาน', 'โฮสต์ ARVIO Web เองได้ฟรี'),
    'tr': ('Ödeme gerekmez.', 'Her ARVIO Cloud hesabı için bir ücretsiz deneme.', 'denemeden sonra', 'ARVIO Web’i ücretsiz olarak kendiniz barındırın.'),
    'uk': ('Оплата не потрібна.', 'Один безкоштовний пробний період на обліковий запис ARVIO Cloud.', 'після пробного періоду', 'Розміщуйте ARVIO Web на власному сервері безкоштовно.'),
    'ur': ('ادائیگی کی ضرورت نہیں۔', 'ہر ARVIO Cloud اکاؤنٹ کے لیے ایک مفت آزمائش۔', 'آزمائش کے بعد', 'ARVIO Web کو اپنے سرور پر مفت ہوسٹ کریں۔'),
    'vi': ('Không cần thanh toán.', 'Một lần dùng thử miễn phí cho mỗi tài khoản ARVIO Cloud.', 'sau khi dùng thử', 'Tự lưu trữ ARVIO Web miễn phí.'),
}
feature_copy = {
    'en': {
        'hosting': 'Live TV. Your favourite sources. Your ARVIO, in a browser. We handle the hosting — you enjoy the setup you already love.',
        'coffee': 'Buy the developer a monthly coffee. Get ARVIO Web hosted 24/7 in return — no server to set up or maintain. Your support keeps ARVIO growing.',
        'tv': 'Live TV in your browser',
        'tvBody': 'Bring your own IPTV service. Browse the programme guide and play supported channels right in ARVIO Web.',
        'download': 'Your sources. Ready to download.',
        'downloadBody': 'Save supported sources from the source picker. Download on desktop, or hand off to VLC on iPhone and iPad.',
        'libraryBody': 'Your watchlists, personal lists and watch progress — connected through ARVIO Cloud.',
        'serverBody': 'Bring your Jellyfin, Plex, Emby or Silo library into the same familiar interface.',
        'sportsBody': 'Explore sports events and find sources from your connected services.',
        'free': 'Android & TV stay free. Self-hosting stays free.',
        'join': 'Get Premium on Ko-fi',
    },
    'nl': {
        'intro': 'Neem je ARVIO mee naar Windows, Mac, iPhone, iPad en smart-tv-browsers. Je profielen, bibliotheken, add-ons en kijkvoortgang blijven verbonden via ARVIO Cloud.',
        'hosting': 'Live-tv. Jouw favoriete bronnen. Jouw ARVIO, in de browser. Wij regelen de hosting — jij geniet van je vertrouwde setup.',
        'coffee': 'Trakteer de ontwikkelaar elke maand op een kop koffie. Krijg er 24/7 gehost ARVIO Web voor terug — zonder zelf een server te beheren. Jouw steun laat ARVIO groeien.',
        'tv': 'Live-tv in je browser',
        'tvBody': 'Koppel je eigen IPTV-dienst. Bekijk de tv-gids en speel ondersteunde zenders rechtstreeks af in ARVIO Web.',
        'download': 'Jouw bronnen. Klaar om te downloaden.',
        'downloadBody': 'Bewaar ondersteunde bronnen vanuit de bronkeuze. Download op je computer of open de download in VLC op iPhone en iPad.',
        'libraryBody': 'Je kijklijsten, persoonlijke lijsten en kijkvoortgang — verbonden via ARVIO Cloud.',
        'serverBody': 'Breng je Jellyfin-, Plex-, Emby- of Silo-bibliotheek samen in dezelfde vertrouwde interface.',
        'sportsBody': 'Ontdek sportevenementen en vind bronnen via je gekoppelde diensten.',
        'free': 'Android en tv blijven gratis. Zelf hosten ook.',
        'join': 'Neem Premium via Ko-fi',
        'play': 'Afspelen in je browser of openen in VLC',
        'sync': 'Je profielen, bibliotheken en kijkvoortgang blijven verbonden',
    },
}
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
        for key in ('intro', 'sync', 'play', 'free', 'join', 'period', 'notice', 'trial'):
            assert keys[key].lower() in normalized, (code, key)
            assert isinstance(normalized[keys[key].lower()], str) and normalized[keys[key].lower()].strip(), (code, key)
        assert translations['trial'] != keys['trial'], ('Untranslated trial action', code)
    translations['trial'] = translations['trial'].replace('{value0}', '3')
    notes = trial_copy.get(locale, trial_copy.get(base))
    assert notes and len(notes) == 4, ('Missing trial notes', code)
    for key, value in zip(('trialNoPayment', 'trialEligibility', 'afterTrial', 'selfHost'), notes):
        assert value and '{' not in value and '}' not in value, (code, key)
        if base != 'en':
            assert value != trial_copy['en'][('trialNoPayment', 'trialEligibility', 'afterTrial', 'selfHost').index(key)], (code, key)
        translations[key] = value
    assert '{' not in translations['trial'] and '}' not in translations['trial'], code
    translations['hosting'] = hosting.get(locale, hosting.get(base))
    assert translations['hosting'], code
    translations['membershipTerms'] = membership_terms.get(locale, membership_terms.get(base))
    assert translations['membershipTerms'], ('Missing membership terms', code)
    translations.update({
        'coffee': translations['hosting'],
        'tvBody': translations['play'],
        'libraryBody': translations['sync'],
        'serverBody': 'Jellyfin · Plex · Emby · Silo',
        'sportsBody': translations['tv'],
    })
    if base in ('en', 'nl'):
        translations.update(feature_copy[base])
    data[code] = {'label': entry['label'], **translations}
required_copy = set(re.findall(r'data-copy="([A-Za-z]+)"', (out / 'index.html').read_text(encoding='utf-8')))
for code, copy in data.items():
    for key in required_copy:
        assert isinstance(copy.get(key), str) and copy[key].strip(), ('Missing rendered Premium copy', code, key)
(out / 'languages.json').write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding='utf-8')
print(f'Built Premium copy for {len(data)} language variants.')

