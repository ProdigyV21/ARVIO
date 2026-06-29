// ── Supabase Config ───────────────────────────────────────────────────────
const SUPABASE_URL = 'https://zrdwvortcfnoykltzuqf.supabase.co';
const SUPABASE_ANON = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpyZHd2b3J0Y2Zub3lrbHR6dXFmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjY3NDU4NzMsImV4cCI6MjA4MjMyMTg3M30.YfKZbSwxGs6_xMd6jkDtn1PKkfuyOHo9qVhUvFRddGU';
const TMDB_IMG = 'https://image.tmdb.org/t/p/w92';

const { createClient } = supabase;
const db = createClient(SUPABASE_URL, SUPABASE_ANON);

// ── i18n ──────────────────────────────────────────────────────────────────
const STRINGS = {
  en: {
    nav_dashboard: 'Dashboard', nav_profiles: 'Profiles', nav_addons: 'Add-ons',
    nav_iptv: 'IPTV', nav_plugins: 'Plugins', nav_history: 'History',
    nav_watchlist: 'Watchlist', nav_ai: 'AI Subtitles', nav_settings: 'Settings',
    sign_out: 'Sign out', user_fallback: 'User', loading: 'Loading...',
    saved: 'Saved ✓', save_err: 'Failed to save', no_sync: 'No sync data',
    // auth
    auth_sub: 'Manage your ARVIO account — profiles, add-ons, history & AI subtitles',
    auth_google: 'Continue with Google',
    // dashboard
    dash_sub: 'A quick overview of your ARVIO account',
    stat_profiles: 'Profiles', stat_addons: 'Add-ons',
    stat_history: 'Watch History', stat_watchlist: 'Watchlist',
    recent_activity: 'Recent Activity', no_activity: 'No recent activity',
    type_movie: '🎬 Movie', type_series: '📺 Series',
    // profiles
    profiles_title: 'Profiles',
    profiles_sub: (n) => `${n} profile${n !== 1 ? 's' : ''} in account`,
    profiles_empty: 'No profiles found — open ARVIO on your TV to create one',
    profiles_tip: '💡 Profile management (create, delete, rename) is available in ARVIO on your TV.<br>Changes sync automatically to the cloud.',
    badge_active: 'Active', badge_kids: 'Kids', badge_locked: '🔒 Locked',
    // addons
    addons_title: 'Add-ons',
    addons_sub: (a, t) => `${a} active of ${t}`,
    addons_empty: 'No add-ons installed',
    addons_tip: '💡 To add or remove add-ons, go to Settings in ARVIO on your TV. Data syncs automatically.',
    badge_official: 'Official', badge_subtitles: 'Subtitles',
    badge_metadata: 'Metadata', badge_community: 'Community',
    badge_telegram: 'Telegram', badge_enabled: 'Enabled', badge_disabled: 'Disabled',
    stremio_label: (n) => `Stremio Add-ons (${n})`,
    telegram_label: (n) => `Telegram Sources (${n})`,
    // iptv
    iptv_sub: (n) => `${n} active playlist${n !== 1 ? 's' : ''}`,
    iptv_empty: 'No IPTV playlists configured — go to ARVIO → Settings → IPTV',
    iptv_m3u: 'M3U Playlists', iptv_active: 'Active', iptv_disabled: 'Disabled',
    fav_groups: (n) => `Favourite Groups (${n})`,
    fav_channels: (n) => `Favourite Channels (${n})`,
    iptv_more: (n) => `+${n} more`,
    iptv_tip: '💡 To add M3U playlists and manage channels — go to IPTV Settings in ARVIO. Changes sync automatically to the cloud.',
    // plugins
    plugins_title: 'Plugins (Sideload)',
    plugins_sub: (r, s) => `${r} repositor${r !== 1 ? 'ies' : 'y'} · ${s} scrapers`,
    plugins_status_on: 'Plugins enabled', plugins_status_off: 'Plugins disabled',
    plugins_empty: 'No plugin repositories — add one in ARVIO → Settings → Plugins',
    repos_label: (n) => `Repositories (${n})`,
    repo_updated: 'updated', repo_never: 'never',
    scrapers_label: (n) => `Scrapers (${n})`,
    plugin_active: 'Active', plugin_disabled: 'Disabled',
    plugins_tip: '💡 Plugins sync to the cloud automatically. Each scraper\'s JS code stays local on your TV only and is never uploaded to the cloud.',
    // history
    history_title: 'Watch History',
    tab_movies: 'Movies', tab_tv: 'TV Shows', tab_all: 'All',
    history_empty: 'No watch history', history_err: 'Failed to load',
    delete_err: 'Failed to delete', deleted_history: 'Removed from history ✓',
    // watchlist
    watchlist_title: 'Watchlist',
    watchlist_sub: (n) => `${n} item${n !== 1 ? 's' : ''}`,
    watchlist_empty: 'Your watchlist is empty',
    wl_movies: '🎬 Movies', wl_tv: '📺 TV Shows',
    wl_remove: 'Remove', wl_remove_err: 'Failed to remove',
    wl_removed: 'Removed from watchlist ✓',
    // ai
    ai_title: 'AI Subtitle Translation',
    ai_sub: 'Configure real-time AI subtitle translation',
    ai_banner_title: '🔑 API Key — set directly on your TV',
    ai_banner_sub: 'For security, your API key is stored locally on your TV only.<br>Open ARVIO → Settings → Subtitles → AI Translation',
    ai_settings_title: 'Translation Settings',
    ai_enable: 'Enable AI Translation', ai_enable_desc: 'Automatically translate subtitles while watching',
    ai_auto: 'Auto-select', ai_auto_desc: 'Automatically pick subtitles for translation',
    ai_hi: 'Remove Hearing Impaired (HI)', ai_hi_desc: 'Strip audio descriptions from subtitles [SDH]',
    ai_model_title: 'Select AI Model',
    ai_groq_desc: 'Fastest · Free · Great quality for subtitles',
    ai_gemini_desc: 'Google Gemini, high quality, very fast',
    ai_key_title: 'How to get a free API key?',
    ai_key_desc: '🔹 <b>Groq (recommended)</b>: go to <code>console.groq.com</code> → Create account → API Keys → Create Key<br>🔹 <b>Gemini</b>: go to <code>aistudio.google.com</code> → Get API Key',
    ai_recommended: 'Recommended',
    // settings
    settings_title: 'Settings', settings_sub: 'General account settings',
    appearance: 'Appearance',
    oled_label: 'OLED Black Background', oled_desc: 'Pure black background to save battery on OLED screens',
    layout_label: 'Media Card Layout', layout_landscape: 'Landscape', layout_portrait: 'Portrait',
    lang_label: 'App Language',
    profile_section: 'Profile',
    skip_label: 'Skip Profile Selection', skip_desc: 'Launch directly into the app with the active profile',
    account_section: 'Account', user_id_label: 'User ID',
    // time
    just_now: 'just now', m_ago: (m) => `${m}m ago`, h_ago: (h) => `${h}h ago`, d_ago: (d) => `${d}d ago`,
  },
  he: {
    nav_dashboard: 'דשבורד', nav_profiles: 'פרופילים', nav_addons: 'הרחבות',
    nav_iptv: 'IPTV', nav_plugins: 'פלאגינים', nav_history: 'היסטוריה',
    nav_watchlist: 'רשימת צפייה', nav_ai: 'כתוביות AI', nav_settings: 'הגדרות',
    sign_out: 'התנתק', user_fallback: 'משתמש', loading: 'טוען...',
    saved: 'נשמר ✓', save_err: 'שגיאה בשמירה', no_sync: 'אין נתוני סנכרון',
    // auth
    auth_sub: 'נהל את חשבון ה-ARVIO שלך — פרופילים, הרחבות, היסטוריה ותרגום AI',
    auth_google: 'המשך עם Google',
    // dashboard
    dash_sub: 'סקירה מהירה של חשבון ה-ARVIO שלך',
    stat_profiles: 'פרופילים', stat_addons: 'הרחבות',
    stat_history: 'היסטוריית צפייה', stat_watchlist: 'רשימת צפייה',
    recent_activity: 'פעילות אחרונה', no_activity: 'אין פעילות אחרונה',
    type_movie: '🎬 סרט', type_series: '📺 סדרה',
    // profiles
    profiles_title: 'פרופילים',
    profiles_sub: (n) => `${n} פרופילים בחשבון`,
    profiles_empty: 'אין פרופילים — פתח את ARVIO בטלוויזיה ליצירת פרופיל',
    profiles_tip: '💡 ניהול פרופילים (יצירה, מחיקה, שינוי שם) זמין ב-ARVIO על הטלוויזיה.<br>שינויים מסונכרנים אוטומטית עם הענן.',
    badge_active: 'פעיל', badge_kids: 'ילדים', badge_locked: '🔒 נעול',
    // addons
    addons_title: 'הרחבות',
    addons_sub: (a, total) => `${a} פעילות מתוך ${total}`,
    addons_empty: 'אין הרחבות מותקנות',
    addons_tip: '💡 להוספה/הסרה של הרחבות — גש להגדרות ב-ARVIO. הנתונים מסונכרנים אוטומטית.',
    badge_official: 'רשמי', badge_subtitles: 'כתוביות',
    badge_metadata: 'מטאדאטה', badge_community: 'קהילה',
    badge_telegram: 'Telegram', badge_enabled: 'פעיל', badge_disabled: 'כבוי',
    stremio_label: (n) => `הרחבות Stremio (${n})`,
    telegram_label: (n) => `מקורות Telegram (${n})`,
    // iptv
    iptv_sub: (n) => `${n} רשימות פעילות`,
    iptv_empty: 'אין רשימות IPTV מוגדרות — הגדר ב-ARVIO ← הגדרות ← IPTV',
    iptv_m3u: 'רשימות M3U', iptv_active: 'פעיל', iptv_disabled: 'כבוי',
    fav_groups: (n) => `קבוצות מועדפות (${n})`,
    fav_channels: (n) => `ערוצים מועדפים (${n})`,
    iptv_more: (n) => `+${n} נוספים`,
    iptv_tip: '💡 להוספת רשימות M3U ולניהול ערוצים — גש להגדרות IPTV ב-ARVIO. השינויים מסונכרנים אוטומטית לענן.',
    // plugins
    plugins_title: 'פלאגינים (Sideload)',
    plugins_sub: (r, s) => `${r} מאגרים · ${s} scrapers`,
    plugins_status_on: 'פלאגינים פעילים', plugins_status_off: 'פלאגינים כבויים',
    plugins_empty: 'אין מאגרי פלאגינים — הוסף ב-ARVIO ← הגדרות ← פלאגינים',
    repos_label: (n) => `מאגרים (${n})`,
    repo_updated: 'עודכן', repo_never: 'אף פעם',
    scrapers_label: (n) => `Scrapers (${n})`,
    plugin_active: 'פעיל', plugin_disabled: 'כבוי',
    plugins_tip: '💡 הפלאגינים מסונכרנים לענן אוטומטית. קוד ה-JS של כל scraper נשמר מקומית בטלוויזיה בלבד ולא עולה לענן.',
    // history
    history_title: 'היסטוריית צפייה',
    tab_movies: 'סרטים', tab_tv: 'סדרות', tab_all: 'הכל',
    history_empty: 'אין היסטוריית צפייה', history_err: 'שגיאה בטעינה',
    delete_err: 'שגיאה במחיקה', deleted_history: 'הוסר מההיסטוריה ✓',
    // watchlist
    watchlist_title: 'רשימת צפייה',
    watchlist_sub: (n) => `${n} פריטים`,
    watchlist_empty: 'רשימת הצפייה ריקה',
    wl_movies: '🎬 סרטים', wl_tv: '📺 סדרות',
    wl_remove: 'הסר', wl_remove_err: 'שגיאה במחיקה',
    wl_removed: 'הוסר מהרשימה ✓',
    // ai
    ai_title: 'תרגום כתוביות AI',
    ai_sub: 'הגדר תרגום כתוביות בזמן אמת על ידי AI',
    ai_banner_title: '🔑 מפתח API — הגדר ישירות בטלוויזיה',
    ai_banner_sub: 'מטעמי אבטחה, מפתח ה-API נשמר רק מקומית בטלוויזיה.<br>פתח את ARVIO ← הגדרות ← כתוביות ← תרגום AI',
    ai_settings_title: 'הגדרות תרגום',
    ai_enable: 'הפעל תרגום AI', ai_enable_desc: 'תרגם כתוביות אוטומטית בזמן צפייה',
    ai_auto: 'בחירה אוטומטית', ai_auto_desc: 'בחר אוטומטית כתוביות לתרגום',
    ai_hi: 'הסר כתוביות לכבדי שמיעה', ai_hi_desc: 'הסר תיאורי קול מהכתוביות [SDH]',
    ai_model_title: 'בחר מודל AI',
    ai_groq_desc: 'מהיר ביותר, חינמי, מומלץ לתרגום כתוביות',
    ai_gemini_desc: 'Google Gemini, איכות גבוהה, מהיר מאוד',
    ai_key_title: 'איך לקבל מפתח API חינמי?',
    ai_key_desc: '🔹 <b>Groq (מומלץ)</b>: גש ל-<code>console.groq.com</code> ← צור חשבון ← API Keys ← Create Key<br>🔹 <b>Gemini</b>: גש ל-<code>aistudio.google.com</code> ← Get API Key',
    ai_recommended: 'מומלץ',
    // settings
    settings_title: 'הגדרות', settings_sub: 'הגדרות כלליות לחשבון',
    appearance: 'מראה',
    oled_label: 'רקע OLED שחור', oled_desc: 'רקע שחור לחלוטין לחיסכון בסוללה',
    layout_label: 'פריסת כרטיסי מדיה', layout_landscape: 'Landscape (רוחב)', layout_portrait: 'Portrait (אנכי)',
    lang_label: 'שפת ממשק',
    profile_section: 'פרופיל',
    skip_label: 'דלג על בחירת פרופיל', skip_desc: 'עבור ישירות לאפליקציה עם הפרופיל הפעיל',
    account_section: 'חשבון', user_id_label: 'מזהה משתמש',
    // time
    just_now: 'עכשיו', m_ago: (m) => `לפני ${m} דק׳`, h_ago: (h) => `לפני ${h} שע׳`, d_ago: (d) => `לפני ${d} ימים`,
  },
};

let currentLang = localStorage.getItem('arvio_lang') || 'en';

function t(key, ...args) {
  const s = STRINGS[currentLang][key];
  return typeof s === 'function' ? s(...args) : (s ?? key);
}

function setLang(lang) {
  currentLang = lang;
  localStorage.setItem('arvio_lang', lang);
  const isRTL = lang === 'he';
  document.documentElement.lang = lang;
  document.documentElement.dir = isRTL ? 'rtl' : 'ltr';
  // Re-render auth text if on auth screen
  const authSub = document.querySelector('.auth-sub');
  if (authSub) authSub.textContent = t('auth_sub');
  const authBtn = document.querySelector('.btn-google');
  if (authBtn) authBtn.childNodes[authBtn.childNodes.length - 1].textContent = ' ' + t('auth_google');
  // Re-render app if logged in
  if (state.session) {
    buildShell(state.session.user);
    renderSection();
  }
}

// ── State ─────────────────────────────────────────────────────────────────
let state = {
  session: null,
  userId: null,
  syncPayload: null,
  activeSection: 'dashboard',
  historyTab: 'movies',
};

// ── Toast ─────────────────────────────────────────────────────────────────
function toast(msg, type = 'ok') {
  const el = document.getElementById('toast');
  el.textContent = msg;
  el.className = `show toast-${type}`;
  clearTimeout(el._t);
  el._t = setTimeout(() => { el.className = ''; }, 3000);
}

// ── Auth ──────────────────────────────────────────────────────────────────
async function signInGoogle() {
  const { error } = await db.auth.signInWithOAuth({
    provider: 'google',
    options: { redirectTo: window.location.href },
  });
  if (error) toast(error.message, 'err');
}

async function signOut() {
  await db.auth.signOut();
  location.reload();
}

// ── Cloud Sync Payload ────────────────────────────────────────────────────
async function loadSyncPayload() {
  try {
    const { data, error } = await db
      .from('profiles')
      .select('addons')
      .eq('id', state.userId)
      .single();
    if (error || !data?.addons) return null;
    const wrapper = JSON.parse(data.addons);
    const raw = wrapper['__arvioAccountSyncPayload'];
    if (!raw) return null;
    return JSON.parse(raw);
  } catch { return null; }
}

async function saveSyncPayload(payload) {
  const raw = JSON.stringify(payload);
  // Read existing wrapper so we only overwrite our two keys and preserve any others
  const { data: existing } = await db.from('profiles').select('addons').eq('id', state.userId).single();
  let wrapper = {};
  try { wrapper = JSON.parse(existing?.addons ?? '{}'); } catch {}
  wrapper.__arvioAccountSyncPayload = raw;
  wrapper.__arvioAccountSyncUpdatedAt = new Date().toISOString();
  const { error } = await db
    .from('profiles')
    .update({ addons: JSON.stringify(wrapper) })
    .eq('id', state.userId);
  if (error) throw error;
  state.syncPayload = payload;
}

// ── Sections ──────────────────────────────────────────────────────────────
const sections = {
  dashboard: renderDashboard,
  profiles: renderProfiles,
  addons: renderAddons,
  iptv: renderIPTV,
  plugins: renderPlugins,
  history: renderHistory,
  watchlist: renderWatchlist,
  ai: renderAI,
  settings: renderSettings,
};

function navigate(id) {
  state.activeSection = id;
  document.querySelectorAll('.nav-item').forEach(el => {
    el.classList.toggle('active', el.dataset.section === id);
  });
  renderSection();
}

async function renderSection() {
  const main = document.getElementById('main-content');
  main.innerHTML = `<div class="loading"><div class="spinner"></div> ${t('loading')}</div>`;
  await sections[state.activeSection]?.();
}

// ── Dashboard ─────────────────────────────────────────────────────────────
async function renderDashboard() {
  const main = document.getElementById('main-content');

  const [histRes, wlRes] = await Promise.all([
    db.from('watch_history').select('id, media_type', { count: 'exact', head: false })
      .eq('user_id', state.userId).limit(1),
    db.from('watchlist').select('tmdb_id', { count: 'exact', head: false })
      .eq('user_id', state.userId).limit(1),
  ]);

  const payload = state.syncPayload;
  const profiles = payload?.profiles ?? [];
  const addons = getAddons(payload);

  main.innerHTML = `
    <div class="section-header">
      <div>
        <div class="section-title">${t('nav_dashboard')}</div>
        <div class="section-sub">${t('dash_sub')}</div>
      </div>
    </div>
    <div class="card-grid">
      <div class="stat-card"><div class="stat-label">${t('stat_profiles')}</div><div class="stat-value gold">${profiles.length || 1}</div></div>
      <div class="stat-card"><div class="stat-label">${t('stat_addons')}</div><div class="stat-value">${addons.filter(a => a.isEnabled).length}</div></div>
      <div class="stat-card"><div class="stat-label">${t('stat_history')}</div><div class="stat-value">${histRes.count ?? '—'}</div></div>
      <div class="stat-card"><div class="stat-label">${t('stat_watchlist')}</div><div class="stat-value">${wlRes.count ?? '—'}</div></div>
    </div>
    <div class="card">
      <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin-bottom:14px">${t('recent_activity')}</div>
      <div id="recent-activity"><div class="loading"><div class="spinner"></div></div></div>
    </div>
  `;

  const { data: recent } = await db.from('watch_history')
    .select('title, media_type, progress, updated_at, poster_path, backdrop_path')
    .eq('user_id', state.userId)
    .order('updated_at', { ascending: false })
    .limit(6);

  const ra = document.getElementById('recent-activity');
  if (!recent?.length) {
    ra.innerHTML = emptyState(t('no_activity'));
    return;
  }
  ra.innerHTML = recent.map(r => `
    <div class="list-item">
      <img class="history-poster" src="${r.poster_path ? escapeHtml(TMDB_IMG + r.poster_path) : ''}" onerror="this.style.background='var(--bg-card2)';this.src=''" alt="">
      <div class="item-info">
        <div class="item-title">${escapeHtml(r.title) || '—'}</div>
        <div class="item-sub">${r.media_type === 'movie' ? t('type_movie') : t('type_series')} · ${timeAgo(r.updated_at)}</div>
        <div class="progress-bar"><div class="progress-fill" style="width:${Math.round((r.progress||0)*100)}%"></div></div>
      </div>
      <span class="badge badge-gray">${Math.round((r.progress||0)*100)}%</span>
    </div>
  `).join('');
}

// ── Profiles ──────────────────────────────────────────────────────────────
function getProfileColors() {
  return ['#E53935','#8E24AA','#1E88E5','#00897B','#F4511E','#6D4C41','#3949AB','#039BE5'];
}

function renderProfileAvatar(profile) {
  const color = /^[0-9a-fA-F]{6}$/.test((profile.avatarColor?.toString(16).padStart(8,'0').slice(2) || ''))
    ? '#' + profile.avatarColor.toString(16).padStart(8,'0').slice(2)
    : '#F5C442';
  const letter = escapeHtml((profile.name || '?')[0].toUpperCase());
  return `<div class="profile-avatar-big" style="background:${color}22;color:${color}">${letter}</div>`;
}

async function renderProfiles() {
  const main = document.getElementById('main-content');
  const payload = state.syncPayload;
  const profiles = payload?.profiles ?? [];
  const activeId = payload?.activeProfileId;

  if (!profiles.length) {
    main.innerHTML = `
      <div class="section-header"><div class="section-title">${t('profiles_title')}</div></div>
      ${emptyState(t('profiles_empty'))}`;
    return;
  }

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('profiles_title')}</div><div class="section-sub">${t('profiles_sub', profiles.length)}</div></div>
    </div>
    <div class="profiles-grid">
      ${profiles.map(p => `
        <div class="profile-card ${p.id === activeId ? 'active-profile' : ''}">
          ${renderProfileAvatar(p)}
          <div class="profile-name">${escapeHtml(p.name)}</div>
          <div style="display:flex;gap:6px;flex-wrap:wrap;justify-content:center">
            ${p.id === activeId ? `<span class="badge badge-gold">${t('badge_active')}</span>` : ''}
            ${p.isKidsProfile ? `<span class="badge badge-blue">${t('badge_kids')}</span>` : ''}
            ${p.pin ? `<span class="badge badge-gray">${t('badge_locked')}</span>` : ''}
          </div>
        </div>
      `).join('')}
    </div>
    <div class="card">
      <div style="font-size:13px;color:var(--text-muted)">${t('profiles_tip')}</div>
    </div>
  `;
}

// ── Addons ────────────────────────────────────────────────────────────────
function getAddons(payload) {
  if (!payload) return [];
  const shared = payload.addons ?? [];
  const byProfile = payload.addonsByProfile ?? {};
  const allIds = new Set();
  const all = [...shared];
  Object.values(byProfile).forEach(arr => {
    (arr || []).forEach(a => { if (!allIds.has(a.id)) { allIds.add(a.id); all.push(a); } });
  });
  return all;
}

async function renderAddons() {
  const main = document.getElementById('main-content');
  const addons = getAddons(state.syncPayload);

  if (!addons.length) {
    main.innerHTML = `
      <div class="section-header"><div class="section-title">${t('addons_title')}</div></div>
      ${emptyState(t('addons_empty'))}`;
    return;
  }

  const byType = {
    STREMIO: addons.filter(a => a.runtimeKind !== 'TELEGRAM'),
    TELEGRAM: addons.filter(a => a.runtimeKind === 'TELEGRAM'),
  };

  function addonIcon(a) {
    const logo = safeUrl(a.manifest?.logo || a.logo);
    if (logo) return `<img src="${logo}" alt="" onerror="this.style.display='none'">`;
    return `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5"><rect x="3" y="3" width="18" height="18" rx="3"/><path d="M9 9h6M9 12h6M9 15h4"/></svg>`;
  }

  function addonTypeBadge(a) {
    const t = a.type || 'COMMUNITY';
    if (t === 'OFFICIAL') return `<span class="badge badge-gold">${STRINGS[currentLang].badge_official}</span>`;
    if (t === 'SUBTITLE') return `<span class="badge badge-blue">${STRINGS[currentLang].badge_subtitles}</span>`;
    if (t === 'METADATA') return `<span class="badge badge-gray">${STRINGS[currentLang].badge_metadata}</span>`;
    if (a.runtimeKind === 'TELEGRAM') return `<span class="badge badge-blue">${STRINGS[currentLang].badge_telegram}</span>`;
    return `<span class="badge badge-gray">${STRINGS[currentLang].badge_community}</span>`;
  }

  function renderAddonList(list, label) {
    if (!list.length) return '';
    return `
      <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin:20px 0 12px">${label}</div>
      <div class="card" style="padding:0 20px">
        ${list.map(a => `
          <div class="list-item">
            <div class="item-icon">${addonIcon(a)}</div>
            <div class="item-info">
              <div class="item-title">${escapeHtml(a.name || a.id)}</div>
              <div class="item-sub">${escapeHtml(a.manifest?.description || a.description || a.id)}</div>
            </div>
            <div style="display:flex;gap:8px;align-items:center">
              ${addonTypeBadge(a)}
              <span class="badge ${a.isEnabled ? 'badge-green' : 'badge-red'}">${a.isEnabled ? t('badge_enabled') : t('badge_disabled')}</span>
            </div>
          </div>
        `).join('')}
      </div>
    `;
  }

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('addons_title')}</div><div class="section-sub">${t('addons_sub', addons.filter(a=>a.isEnabled).length, addons.length)}</div></div>
    </div>
    ${renderAddonList(byType.STREMIO, t('stremio_label', byType.STREMIO.length))}
    ${renderAddonList(byType.TELEGRAM, t('telegram_label', byType.TELEGRAM.length))}
    <div class="card" style="margin-top:16px">
      <div style="font-size:13px;color:var(--text-muted)">${t('addons_tip')}</div>
    </div>
  `;
}

// ── IPTV ──────────────────────────────────────────────────────────────────
async function renderIPTV() {
  const main = document.getElementById('main-content');
  const payload = state.syncPayload;
  const profiles = payload?.profiles ?? [];
  const activeId = payload?.activeProfileId;
  const iptvByProfile = payload?.iptvByProfile ?? {};

  // Collect all playlists across profiles
  const allPlaylists = [];
  const seenUrls = new Set();
  for (const [profileId, iptvState] of Object.entries(iptvByProfile)) {
    const profile = profiles.find(p => p.id === profileId);
    const pName = profile?.name ?? profileId.slice(0, 8);
    (iptvState.playlists ?? []).forEach(pl => {
      if (!seenUrls.has(pl.m3uUrl)) {
        seenUrls.add(pl.m3uUrl);
        allPlaylists.push({ ...pl, _profileName: pName, _profileId: profileId });
      }
    });
    // Legacy single M3U
    if (iptvState.m3uUrl && !seenUrls.has(iptvState.m3uUrl)) {
      seenUrls.add(iptvState.m3uUrl);
      allPlaylists.push({ id: 'legacy', name: 'M3U', m3uUrl: iptvState.m3uUrl, epgUrl: iptvState.epgUrl, enabled: true, _profileName: pName, _profileId: profileId });
    }
  }

  // Collect favourite channels/groups from active profile
  const activeIPTV = iptvByProfile[activeId] ?? Object.values(iptvByProfile)[0] ?? {};
  const favGroups = activeIPTV.favoriteGroups ?? [];
  const favChannels = activeIPTV.favoriteChannels ?? [];

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">IPTV</div><div class="section-sub">${t('iptv_sub', allPlaylists.length)}</div></div>
    </div>

    ${allPlaylists.length === 0 ? emptyState(t('iptv_empty')) : `
    <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin-bottom:12px">${t('iptv_m3u')}</div>
    <div class="card" style="padding:0 20px">
      ${allPlaylists.map(pl => `
        <div class="list-item">
          <div class="item-icon">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5"><path d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z"/><path d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/></svg>
          </div>
          <div class="item-info">
            <div class="item-title">${escapeHtml(pl.name) || 'M3U Playlist'}</div>
            <div class="item-sub" style="font-family:monospace;font-size:11px;max-width:380px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">${escapeHtml(pl.m3uUrl)}</div>
            ${pl.epgUrl ? `<div class="item-sub" style="font-size:11px">EPG: ${escapeHtml(pl.epgUrl)}</div>` : ''}
          </div>
          <div style="display:flex;flex-direction:column;align-items:flex-end;gap:6px">
            <span class="badge ${pl.enabled !== false ? 'badge-green' : 'badge-red'}">${pl.enabled !== false ? t('iptv_active') : t('iptv_disabled')}</span>
            <span class="badge badge-gray">${escapeHtml(pl._profileName)}</span>
          </div>
        </div>
      `).join('')}
    </div>`}

    ${favGroups.length > 0 ? `
    <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin:24px 0 12px">${t('fav_groups', favGroups.length)}</div>
    <div class="card" style="padding:14px 20px;display:flex;flex-wrap:wrap;gap:8px">
      ${favGroups.map(g => `<span class="badge badge-gold">⭐ ${escapeHtml(g)}</span>`).join('')}
    </div>` : ''}

    ${favChannels.length > 0 ? `
    <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin:24px 0 12px">${t('fav_channels', favChannels.length)}</div>
    <div class="card" style="padding:14px 20px;display:flex;flex-wrap:wrap;gap:8px">
      ${favChannels.slice(0, 50).map(c => `<span class="badge badge-blue">📺 ${escapeHtml(c)}</span>`).join('')}
      ${favChannels.length > 50 ? `<span class="badge badge-gray">${t('iptv_more', favChannels.length - 50)}</span>` : ''}
    </div>` : ''}

    <div class="card" style="margin-top:16px">
      <div style="font-size:13px;color:var(--text-muted)">${t('iptv_tip')}</div>
    </div>
  `;
}

// ── Plugins ───────────────────────────────────────────────────────────────
async function renderPlugins() {
  const main = document.getElementById('main-content');
  const payload = state.syncPayload;
  const repos = payload?.pluginRepositories ?? [];
  const scrapers = payload?.pluginScrapers ?? [];
  const pluginsEnabled = payload?.pluginsEnabled ?? false;

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('plugins_title')}</div><div class="section-sub">${t('plugins_sub', repos.length, scrapers.length)}</div></div>
      <div style="display:flex;align-items:center;gap:10px">
        <span style="font-size:13px;color:var(--text-muted)">${pluginsEnabled ? t('plugins_status_on') : t('plugins_status_off')}</span>
        <label class="toggle">
          <input type="checkbox" ${pluginsEnabled ? 'checked' : ''} onchange="updateSetting('pluginsEnabled',this.checked)">
          <span class="toggle-slider"></span>
        </label>
      </div>
    </div>

    ${repos.length === 0 ? emptyState(t('plugins_empty')) : `
    <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin-bottom:12px">${t('repos_label', repos.length)}</div>
    <div class="card" style="padding:0 20px">
      ${repos.map(r => `
        <div class="list-item">
          <div class="item-icon">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5"><path d="M3 7h18M3 12h18M3 17h18"/></svg>
          </div>
          <div class="item-info">
            <div class="item-title">${escapeHtml(r.name)}</div>
            <div class="item-sub" style="font-family:monospace;font-size:11px">${escapeHtml(r.url)}</div>
            <div class="item-sub">${r.scraperCount ?? 0} scrapers · ${t('repo_updated')} ${r.lastUpdated ? timeAgo(new Date(r.lastUpdated).toISOString()) : t('repo_never')}</div>
          </div>
          <span class="badge ${r.enabled ? 'badge-green' : 'badge-red'}">${r.enabled ? t('plugin_active') : t('plugin_disabled')}</span>
        </div>
      `).join('')}
    </div>`}

    ${scrapers.length > 0 ? `
    <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin:24px 0 12px">${t('scrapers_label', scrapers.length)}</div>
    <div class="card" style="padding:0 20px">
      ${scrapers.map(s => `
        <div class="list-item">
          <div class="item-icon">
            ${safeUrl(s.logo) ? `<img src="${safeUrl(s.logo)}" alt="" onerror="this.style.display='none'">` : `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5"><path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"/></svg>`}
          </div>
          <div class="item-info">
            <div class="item-title">${escapeHtml(s.name)}</div>
            <div class="item-sub">${escapeHtml(s.description)} · v${escapeHtml(s.version)}</div>
            <div style="display:flex;gap:6px;margin-top:4px;flex-wrap:wrap">
              ${(s.supportedTypes ?? []).map(st => `<span class="badge badge-gray">${escapeHtml(st)}</span>`).join('')}
              ${(s.contentLanguage ?? []).map(l => `<span class="badge badge-blue">${escapeHtml(l)}</span>`).join('')}
            </div>
          </div>
          <span class="badge ${s.enabled && s.manifestEnabled ? 'badge-green' : 'badge-red'}">${s.enabled && s.manifestEnabled ? t('plugin_active') : t('plugin_disabled')}</span>
        </div>
      `).join('')}
    </div>` : ''}

    <div class="card" style="margin-top:16px">
      <div style="font-size:13px;color:var(--text-muted)">${t('plugins_tip')}</div>
    </div>
  `;
}

// ── Watch History ─────────────────────────────────────────────────────────
async function renderHistory() {
  const main = document.getElementById('main-content');

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('history_title')}</div></div>
    </div>
    <div class="tabs">
      <button class="tab ${state.historyTab==='movies'?'active':''}" onclick="state.historyTab='movies';renderHistoryContent()">${t('tab_movies')}</button>
      <button class="tab ${state.historyTab==='tv'?'active':''}" onclick="state.historyTab='tv';renderHistoryContent()">${t('tab_tv')}</button>
      <button class="tab ${state.historyTab==='all'?'active':''}" onclick="state.historyTab='all';renderHistoryContent()">${t('tab_all')}</button>
    </div>
    <div id="history-content"><div class="loading"><div class="spinner"></div></div></div>
  `;
  await renderHistoryContent();
}

async function renderHistoryContent() {
  document.querySelectorAll('.tab').forEach(tab => {
    const map = { movies: t('tab_movies'), tv: t('tab_tv'), all: t('tab_all') };
    tab.classList.toggle('active', tab.textContent.trim() === map[state.historyTab]);
  });

  const el = document.getElementById('history-content');
  if (!el) return;
  el.innerHTML = `<div class="loading"><div class="spinner"></div></div>`;

  let q = db.from('watch_history')
    .select('*')
    .eq('user_id', state.userId)
    .order('updated_at', { ascending: false })
    .limit(50);

  if (state.historyTab === 'movies') q = q.eq('media_type', 'movie');
  if (state.historyTab === 'tv') q = q.eq('media_type', 'tv');

  const { data, error } = await q;
  if (error) { el.innerHTML = `<div class="empty"><div class="empty-title">${t('history_err')}</div></div>`; return; }
  if (!data?.length) { el.innerHTML = emptyState(t('history_empty')); return; }

  el.innerHTML = `<div class="card" style="padding:0 20px">
    ${data.map(r => `
      <div class="list-item">
        <img class="history-poster" src="${r.poster_path ? escapeHtml(TMDB_IMG+r.poster_path) : ''}" onerror="this.style.background='var(--bg-card2)';this.src=''" alt="">
        <div class="item-info">
          <div class="item-title">${escapeHtml(r.title) || '—'}${r.season ? ` S${String(Number(r.season)||0).padStart(2,'0')}E${String(Number(r.episode)||0).padStart(2,'0')}` : ''}</div>
          <div class="item-sub">${timeAgo(r.updated_at)} · ${formatDuration(r.position_seconds)} / ${formatDuration(r.duration_seconds)}</div>
          <div class="progress-bar"><div class="progress-fill" style="width:${Math.round((r.progress||0)*100)}%"></div></div>
        </div>
        <div style="display:flex;flex-direction:column;align-items:flex-end;gap:6px">
          <span class="badge badge-gray">${Math.round((r.progress||0)*100)}%</span>
          <button class="btn btn-danger" style="padding:4px 10px;font-size:11px" onclick="deleteHistory('${r.id}',this)">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polyline points="3 6 5 6 21 6"/><path d="M19 6l-1 14H6L5 6"/><path d="M10 11v6M14 11v6"/></svg>
          </button>
        </div>
      </div>
    `).join('')}
  </div>`;
}

async function deleteHistory(id, btn) {
  btn.disabled = true;
  const { error } = await db.from('watch_history').delete().eq('id', id).eq('user_id', state.userId);
  if (error) { toast(t('delete_err'), 'err'); btn.disabled = false; return; }
  toast(t('deleted_history'));
  btn.closest('.list-item').remove();
}

// ── Watchlist ─────────────────────────────────────────────────────────────
async function renderWatchlist() {
  const main = document.getElementById('main-content');
  main.innerHTML = `<div class="section-header"><div class="section-title">${t('watchlist_title')}</div></div><div class="loading"><div class="spinner"></div></div>`;

  const { data, error } = await db.from('watchlist')
    .select('*')
    .eq('user_id', state.userId)
    .order('added_at', { ascending: false });

  if (error || !data?.length) {
    main.innerHTML = `<div class="section-header"><div class="section-title">${t('watchlist_title')}</div></div>${emptyState(t('watchlist_empty'))}`;
    return;
  }

  const movies = data.filter(i => i.media_type === 'movie');
  const shows = data.filter(i => i.media_type === 'tv');

  function renderWLSection(items, label) {
    if (!items.length) return '';
    return `
      <div style="font-size:13px;font-weight:700;color:var(--text-muted);text-transform:uppercase;letter-spacing:.5px;margin:20px 0 12px">${label} (${items.length})</div>
      <div class="card" style="padding:0 20px">
        ${items.map(i => `
          <div class="list-item" id="wl-${i.tmdb_id}-${i.media_type}">
            <div class="item-icon">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">${i.media_type==='movie'?'<path d="M15 10l4.553-2.069A1 1 0 0121 8.882v6.236a1 1 0 01-1.447.894L15 14M3 8a2 2 0 012-2h10a2 2 0 012 2v8a2 2 0 01-2 2H5a2 2 0 01-2-2V8z"/>':'<rect x="2" y="3" width="20" height="14" rx="2"/><path d="M8 21h8M12 17v4"/>'}
              </svg>
            </div>
            <div class="item-info">
              <div class="item-title">TMDB #${i.tmdb_id}</div>
              <div class="item-sub">${timeAgo(i.added_at)}</div>
            </div>
            <button class="btn btn-danger" style="padding:6px 10px" onclick="removeWatchlist(${i.tmdb_id},'${i.media_type}',this)">${t('wl_remove')}</button>
          </div>
        `).join('')}
      </div>`;
  }

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('watchlist_title')}</div><div class="section-sub">${t('watchlist_sub', data.length)}</div></div>
    </div>
    ${renderWLSection(movies, t('wl_movies'))}
    ${renderWLSection(shows, t('wl_tv'))}
  `;
}

async function removeWatchlist(tmdbId, mediaType, btn) {
  btn.disabled = true;
  const { error } = await db.from('watchlist').delete()
    .eq('user_id', state.userId).eq('tmdb_id', tmdbId).eq('media_type', mediaType);
  if (error) { toast(t('wl_remove_err'), 'err'); btn.disabled = false; return; }
  toast(t('wl_removed'));
  document.getElementById(`wl-${tmdbId}-${mediaType}`)?.remove();
}

// ── AI Subtitle Translation ────────────────────────────────────────────────
async function renderAI() {
  const main = document.getElementById('main-content');
  const payload = state.syncPayload;
  const enabled = payload?.subtitleAiEnabled ?? false;
  const autoSelect = payload?.subtitleAiAutoSelect ?? false;
  const model = payload?.subtitleAiModel ?? 'GROQ_LLAMA_70B';
  const removeHI = payload?.subtitleRemoveHearingImpaired ?? false;

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('ai_title')}</div><div class="section-sub">${t('ai_sub')}</div></div>
    </div>

    <div class="tv-banner">
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
        <rect x="2" y="3" width="20" height="14" rx="2"/>
        <path d="M8 21h8M12 17v4"/>
      </svg>
      <div>
        <div class="tv-banner-title">${t('ai_banner_title')}</div>
        <div class="tv-banner-sub">${t('ai_banner_sub')}</div>
      </div>
    </div>

    <div class="card">
      <div style="font-size:16px;font-weight:700;margin-bottom:16px">${t('ai_settings_title')}</div>

      <div class="form-group">
        <div style="display:flex;align-items:center;justify-content:space-between">
          <div>
            <div style="font-weight:600">${t('ai_enable')}</div>
            <div style="font-size:12px;color:var(--text-muted);margin-top:2px">${t('ai_enable_desc')}</div>
          </div>
          <label class="toggle">
            <input type="checkbox" id="ai-enabled" ${enabled ? 'checked' : ''} onchange="updateAISetting('subtitleAiEnabled', this.checked)">
            <span class="toggle-slider"></span>
          </label>
        </div>
      </div>

      <div class="form-group">
        <div style="display:flex;align-items:center;justify-content:space-between">
          <div>
            <div style="font-weight:600">${t('ai_auto')}</div>
            <div style="font-size:12px;color:var(--text-muted);margin-top:2px">${t('ai_auto_desc')}</div>
          </div>
          <label class="toggle">
            <input type="checkbox" id="ai-auto" ${autoSelect ? 'checked' : ''} onchange="updateAISetting('subtitleAiAutoSelect', this.checked)">
            <span class="toggle-slider"></span>
          </label>
        </div>
      </div>

      <div class="form-group">
        <div style="display:flex;align-items:center;justify-content:space-between">
          <div>
            <div style="font-weight:600">${t('ai_hi')}</div>
            <div style="font-size:12px;color:var(--text-muted);margin-top:2px">${t('ai_hi_desc')}</div>
          </div>
          <label class="toggle">
            <input type="checkbox" id="ai-hi" ${removeHI ? 'checked' : ''} onchange="updateAISetting('subtitleRemoveHearingImpaired', this.checked)">
            <span class="toggle-slider"></span>
          </label>
        </div>
      </div>
    </div>

    <div class="card">
      <div style="font-size:16px;font-weight:700;margin-bottom:16px">${t('ai_model_title')}</div>
      <div class="models-grid">
        <label class="model-card ${model==='GROQ_LLAMA_70B'?'selected':''}">
          <input type="radio" name="ai-model" value="GROQ_LLAMA_70B" ${model==='GROQ_LLAMA_70B'?'checked':''} onchange="updateAISetting('subtitleAiModel',this.value)">
          <div>
            <div class="model-name">⚡ Groq Llama 70B <span class="badge badge-gold" style="font-size:10px">${t('ai_recommended')}</span></div>
            <div class="model-desc">${t('ai_groq_desc')}</div>
          </div>
        </label>
        <label class="model-card ${model==='GEMINI_FLASH_25'?'selected':''}">
          <input type="radio" name="ai-model" value="GEMINI_FLASH_25" ${model==='GEMINI_FLASH_25'?'checked':''} onchange="updateAISetting('subtitleAiModel',this.value)">
          <div>
            <div class="model-name">🤖 Gemini Flash 2.5</div>
            <div class="model-desc">${t('ai_gemini_desc')}</div>
          </div>
        </label>
      </div>

      <div style="background:var(--bg-card2);border-radius:8px;padding:14px 16px;margin-top:8px">
        <div style="font-size:13px;font-weight:600;margin-bottom:8px">${t('ai_key_title')}</div>
        <div style="font-size:12px;color:var(--text-muted);line-height:1.7">${t('ai_key_desc')}</div>
      </div>
    </div>
  `;
}

async function updateAISetting(key, value) {
  if (!state.syncPayload) { toast(t('no_sync'), 'err'); return; }
  state.syncPayload[key] = value;
  try {
    await saveSyncPayload(state.syncPayload);
    toast(t('saved'));
    if (key === 'subtitleAiModel') {
      document.querySelectorAll('.model-card').forEach(card => {
        const inp = card.querySelector('input');
        card.classList.toggle('selected', inp?.value === value);
      });
    }
  } catch (e) {
    toast(t('save_err'), 'err');
  }
}

// ── Settings ──────────────────────────────────────────────────────────────
async function renderSettings() {
  const main = document.getElementById('main-content');
  const p = state.syncPayload;

  const cardLayout = p?.cardLayoutMode ?? 'landscape';
  const accentColor = p?.accentColor ?? '#F5C442';
  const oled = p?.oledBlackBackground ?? true;
  const skipProfile = p?.skipProfileSelection ?? false;
  const lang = p?.lastAppLanguage ?? 'en';

  main.innerHTML = `
    <div class="section-header">
      <div><div class="section-title">${t('settings_title')}</div><div class="section-sub">${t('settings_sub')}</div></div>
    </div>

    <div class="card">
      <div style="font-size:16px;font-weight:700;margin-bottom:16px">${t('appearance')}</div>

      <div class="form-group">
        <div style="display:flex;align-items:center;justify-content:space-between">
          <div>
            <div style="font-weight:600">${t('oled_label')}</div>
            <div style="font-size:12px;color:var(--text-muted);margin-top:2px">${t('oled_desc')}</div>
          </div>
          <label class="toggle">
            <input type="checkbox" ${oled?'checked':''} onchange="updateSetting('oledBlackBackground',this.checked)">
            <span class="toggle-slider"></span>
          </label>
        </div>
      </div>

      <div class="form-group">
        <label class="form-label">${t('layout_label')}</label>
        <select class="form-select" onchange="updateSetting('cardLayoutMode',this.value)">
          <option value="landscape" ${cardLayout==='landscape'?'selected':''}>${t('layout_landscape')}</option>
          <option value="portrait" ${cardLayout==='portrait'?'selected':''}>${t('layout_portrait')}</option>
        </select>
      </div>

      <div class="form-group">
        <label class="form-label">${t('lang_label')}</label>
        <select class="form-select" onchange="updateSetting('lastAppLanguage',this.value)">
          <option value="en" ${lang==='en'?'selected':''}>English</option>
          <option value="he" ${lang==='he'?'selected':''}>עברית</option>
          <option value="ar" ${lang==='ar'?'selected':''}>العربية</option>
          <option value="fr" ${lang==='fr'?'selected':''}>Français</option>
          <option value="de" ${lang==='de'?'selected':''}>Deutsch</option>
          <option value="es" ${lang==='es'?'selected':''}>Español</option>
          <option value="ru" ${lang==='ru'?'selected':''}>Русский</option>
          <option value="tr" ${lang==='tr'?'selected':''}>Türkçe</option>
        </select>
      </div>
    </div>

    <div class="card">
      <div style="font-size:16px;font-weight:700;margin-bottom:16px">${t('profile_section')}</div>
      <div class="form-group">
        <div style="display:flex;align-items:center;justify-content:space-between">
          <div>
            <div style="font-weight:600">${t('skip_label')}</div>
            <div style="font-size:12px;color:var(--text-muted);margin-top:2px">${t('skip_desc')}</div>
          </div>
          <label class="toggle">
            <input type="checkbox" ${skipProfile?'checked':''} onchange="updateSetting('skipProfileSelection',this.checked)">
            <span class="toggle-slider"></span>
          </label>
        </div>
      </div>
    </div>

    <div class="card">
      <div style="font-size:16px;font-weight:700;margin-bottom:16px">${t('account_section')}</div>
      <div class="list-item" style="padding:10px 0;border:none">
        <div class="item-info">
          <div class="item-title">${t('user_id_label')}</div>
          <div class="item-sub" style="font-family:monospace;font-size:11px">${escapeHtml(state.userId)}</div>
        </div>
      </div>
      <button class="btn btn-danger" onclick="signOut()" style="margin-top:8px">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>
        ${t('sign_out')}
      </button>
    </div>
  `;
}

async function updateSetting(key, value) {
  if (!state.syncPayload) { toast(t('no_sync'), 'err'); return; }
  state.syncPayload[key] = value;
  try {
    await saveSyncPayload(state.syncPayload);
    toast(t('saved'));
  } catch (e) {
    toast(t('save_err'), 'err');
  }
}

// ── Helpers ───────────────────────────────────────────────────────────────
function escapeHtml(str) {
  if (str == null) return '';
  return String(str)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#039;');
}

function safeUrl(url) {
  if (!url) return '';
  try {
    const u = new URL(url);
    return (u.protocol === 'https:' || u.protocol === 'http:') ? escapeHtml(u.href) : '';
  } catch { return ''; }
}

function timeAgo(iso) {
  if (!iso) return '';
  const d = Date.now() - new Date(iso).getTime();
  const m = Math.floor(d / 60000);
  if (m < 1) return t('just_now');
  if (m < 60) return t('m_ago', m);
  const h = Math.floor(m / 60);
  if (h < 24) return t('h_ago', h);
  const days = Math.floor(h / 24);
  if (days < 30) return t('d_ago', days);
  return new Date(iso).toLocaleDateString(currentLang === 'he' ? 'he-IL' : 'en-US');
}

function formatDuration(secs) {
  if (!secs) return '0:00';
  const h = Math.floor(secs / 3600);
  const m = Math.floor((secs % 3600) / 60);
  const s = Math.floor(secs % 60);
  if (h) return `${h}:${String(m).padStart(2,'0')}:${String(s).padStart(2,'0')}`;
  return `${m}:${String(s).padStart(2,'0')}`;
}

function emptyState(msg) {
  return `<div class="empty">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5"><circle cx="12" cy="12" r="10"/><path d="M8 12h8M12 8v8"/></svg>
    <div class="empty-title">${msg}</div>
  </div>`;
}

// ── Shell ─────────────────────────────────────────────────────────────────
function buildShell(user) {
  const name = user.user_metadata?.full_name || user.email || t('user_fallback');
  const avatar = safeUrl(user.user_metadata?.avatar_url || '');
  const email = user.email || '';
  const initial = escapeHtml(name[0].toUpperCase());

  const navItems = [
    { id: 'dashboard', label: t('nav_dashboard'), icon: '<path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/>' },
    { id: 'profiles', label: t('nav_profiles'), icon: '<path d="M17 21v-2a4 4 0 00-4-4H5a4 4 0 00-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 00-3-3.87"/><path d="M16 3.13a4 4 0 010 7.75"/>' },
    { id: 'addons', label: t('nav_addons'), icon: '<rect x="3" y="3" width="18" height="18" rx="3"/><path d="M9 9h6M9 12h6M9 15h4"/>' },
    { id: 'iptv', label: 'IPTV', icon: '<path d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z"/><path d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>' },
    { id: 'plugins', label: t('nav_plugins'), icon: '<path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"/>' },
    { id: 'history', label: t('nav_history'), icon: '<circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/>' },
    { id: 'watchlist', label: t('nav_watchlist'), icon: '<path d="M19 21l-7-5-7 5V5a2 2 0 012-2h10a2 2 0 012 2z"/>' },
    { id: 'ai', label: t('nav_ai'), icon: '<path d="M12 2a3 3 0 013 3v7a3 3 0 01-6 0V5a3 3 0 013-3z"/><path d="M19 10v2a7 7 0 01-14 0v-2"/><line x1="12" y1="19" x2="12" y2="23"/><line x1="8" y1="23" x2="16" y2="23"/>' },
    { id: 'settings', label: t('nav_settings'), icon: '<circle cx="12" cy="12" r="3"/><path d="M19.07 4.93l-1.41 1.41M4.93 4.93l1.41 1.41M19.07 19.07l-1.41-1.41M4.93 19.07l1.41-1.41M1 12h2M21 12h2M12 1v2M12 21v2"/>' },
  ];

  document.getElementById('app-screen').innerHTML = `
    <aside class="sidebar">
      <div class="sidebar-logo">
        <img src="../assets/arvio-icon-512.png" class="auth-logo" style="width:32px;height:32px" onerror="this.style.display='none'" alt="ARVIO">
        <span>ARVIO</span>
      </div>
      <nav>
        ${navItems.map(n => `
          <button class="nav-item ${n.id === state.activeSection ? 'active' : ''}" data-section="${n.id}" onclick="navigate('${n.id}')">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">${n.icon}</svg>
            ${n.label}
          </button>
        `).join('')}
      </nav>
      <div class="sidebar-user">
        <div class="user-avatar">
          ${avatar ? `<img src="${avatar}" alt="">` : initial}
        </div>
        <div class="user-info">
          <div class="user-name">${escapeHtml(name)}</div>
          <div class="user-email">${escapeHtml(email)}</div>
        </div>
        <button class="btn-lang" onclick="setLang(currentLang==='en'?'he':'en')" title="Switch language" style="background:none;border:1px solid var(--border);border-radius:6px;color:var(--text-muted);cursor:pointer;padding:4px 8px;font-size:12px;font-weight:600;margin-left:4px">
          ${currentLang === 'en' ? 'עב' : 'EN'}
        </button>
        <button class="btn-logout" onclick="signOut()" title="${t('sign_out')}">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>
        </button>
      </div>
    </aside>
    <main class="main" id="main-content"></main>
  `;
}

// ── Boot ──────────────────────────────────────────────────────────────────
async function boot() {
  const { data: { session } } = await db.auth.getSession();

  if (!session) {
    document.getElementById('auth-screen').style.display = 'flex';
    document.getElementById('app-screen').style.display = 'none';
    return;
  }

  state.session = session;
  state.userId = session.user.id;

  document.getElementById('auth-screen').style.display = 'none';
  document.getElementById('app-screen').style.display = 'flex';

  buildShell(session.user);

  state.syncPayload = await loadSyncPayload();

  await renderSection();

  db.auth.onAuthStateChange((_e, s) => {
    if (!s) location.reload();
  });
}

boot();
