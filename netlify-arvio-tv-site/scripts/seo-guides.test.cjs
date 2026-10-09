const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

const siteRoot = path.resolve(__dirname, '..');
const origin = 'https://arvio.tv';
const routes = [
  { route: '/android-tv-media-hub/', pt: '/pt-br/central-midia-android-tv/', es: '/es/centro-multimedia-android-tv/', updated: '2026-10-04' },
  { route: '/arvio-web/', pt: '/pt-br/arvio-web/', es: '/es/arvio-web/', updated: '2026-10-04' },
  { route: '/fire-tv-media-player/', pt: '/pt-br/arvio-fire-tv/', es: '/es/arvio-fire-tv/', updated: '2026-10-04' },
  { route: '/jellyfin-android-tv/', pt: '/pt-br/jellyfin-android-tv/', es: '/es/jellyfin-android-tv/', updated: '2026-10-08' },
  { route: '/plex-emby-jellyfin/', pt: '/pt-br/plex-emby-jellyfin/', es: '/es/plex-emby-jellyfin/', updated: '2026-10-08' },
  { route: '/debrid-usenet-android-tv/', pt: '/pt-br/debrid-usenet-android-tv/', es: '/es/debrid-usenet-android-tv/', updated: '2026-10-05' },
  { route: '/stremio-addons-android-tv/', updated: '2026-10-08' },
  { route: '/live-tv-epg/', pt: '/pt-br/tv-ao-vivo-epg/', es: '/es/tv-en-vivo-epg/', updated: '2026-10-05' },
  { route: '/trakt-simkl-sync/', pt: '/pt-br/sincronizacao-trakt-simkl/', es: '/es/sincronizacion-trakt-simkl/', updated: '2026-10-05' },
  { route: '/ai-subtitles-android-tv/', pt: '/pt-br/legendas-ia-android-tv/', es: '/es/subtitulos-ia-android-tv/', updated: '2026-10-05' }
];
const entities = { amp: '&', lt: '<', gt: '>', quot: '"', apos: "'", nbsp: ' ', ndash: '–', mdash: '—', lsquo: '‘', rsquo: '’', ldquo: '“', rdquo: '”', copy: '©', hellip: '…' };

function decode(value) {
  return String(value).replace(/&(#x[\da-f]+|#\d+|[a-z]+);/giu, (whole, name) => {
    if (name.startsWith('#')) return String.fromCodePoint(parseInt(name.slice(/^#x/i.test(name) ? 2 : 1), /^#x/i.test(name) ? 16 : 10));
    return entities[name] ?? whole;
  });
}

// Small tokenizer for these static documents: attribute order/quotes and nested
// inline markup do not affect checks, and script contents remain raw JSON.
function parse(source) {
  const root = { tag: '#document', attrs: {}, children: [] };
  const stack = [root];
  const voids = new Set(['area', 'base', 'br', 'col', 'embed', 'hr', 'img', 'input', 'link', 'meta', 'param', 'source', 'track', 'wbr']);
  const tags = /<!--[\s\S]*?-->|<![^>]*>|<\/?[a-z][a-z\d:-]*(?:"[^"]*"|'[^']*'|[^'">])*>/giu;
  let cursor = 0;
  let match;
  while ((match = tags.exec(source))) {
    stack.at(-1).children.push({ text: source.slice(cursor, match.index) });
    const token = match[0];
    cursor = tags.lastIndex;
    if (token.startsWith('<!')) continue;
    const tag = /^<\/?([a-z][a-z\d:-]*)/iu.exec(token)[1].toLowerCase();
    if (token.startsWith('</')) {
      const index = stack.findLastIndex((node) => node.tag === tag);
      if (index > 0) stack.length = index;
      continue;
    }
    const attrs = {};
    const attributeText = token.slice(tag.length + 1, token.endsWith('/>') ? -2 : -1);
    for (const attr of attributeText.matchAll(/([^\s=/>]+)(?:\s*=\s*(?:"([^"]*)"|'([^']*)'|([^\s>]+)))?/gu)) {
      attrs[attr[1].toLowerCase()] = decode(attr[2] ?? attr[3] ?? attr[4] ?? '');
    }
    const node = { tag, attrs, children: [] };
    stack.at(-1).children.push(node);
    if (tag === 'script' || tag === 'style') {
      const close = new RegExp(`</${tag}\\s*>`, 'gi');
      close.lastIndex = cursor;
      const end = close.exec(source);
      assert.ok(end, `Unclosed ${tag}`);
      node.children.push({ text: source.slice(cursor, end.index) });
      cursor = tags.lastIndex = close.lastIndex;
    } else if (!voids.has(tag) && !token.endsWith('/>')) stack.push(node);
  }
  stack.at(-1).children.push({ text: source.slice(cursor) });
  return root;
}

function select(node, predicate) {
  return (node.children ?? []).flatMap((child) => child.tag ? [...(predicate(child) ? [child] : []), ...select(child, predicate)] : []);
}
const byTag = (node, tag) => select(node, (child) => child.tag === tag);
const rawText = (node) => node.text ?? (node.children ?? []).map(rawText).join('');
const normalize = (text) => decode(text).replace(/\s+/gu, ' ').trim();
const visibleText = (node) => normalize(rawText(node));
const relContains = (node, value) => (node.attrs.rel ?? '').split(/\s+/u).includes(value);
function one(nodes, message) {
  assert.equal(nodes.length, 1, message);
  return nodes[0];
}
function localFile(url) {
  const resolved = path.resolve(siteRoot, `.${decodeURIComponent(url.pathname)}`);
  assert.ok(resolved === siteRoot || resolved.startsWith(`${siteRoot}${path.sep}`), `Path outside site: ${url.href}`);
  assert.ok(fs.existsSync(resolved), `Missing local target: ${url.href}`);
  const file = fs.statSync(resolved).isDirectory() ? path.join(resolved, 'index.html') : resolved;
  assert.ok(fs.existsSync(file) && fs.statSync(file).isFile(), `Missing local file: ${url.href}`);
  return file;
}
const parsedFiles = new Map();
function documentAt(file) {
  if (!parsedFiles.has(file)) parsedFiles.set(file, parse(fs.readFileSync(file, 'utf8')));
  return parsedFiles.get(file);
}
function structuredData(doc) {
  const blocks = byTag(doc, 'script').filter((node) => node.attrs.type === 'application/ld+json');
  assert.ok(blocks.length, 'Missing JSON-LD');
  return blocks.flatMap((node) => {
    const parsed = JSON.parse(rawText(node));
    return Array.isArray(parsed) ? parsed.flatMap((item) => item['@graph'] ?? [item]) : parsed['@graph'] ?? [parsed];
  });
}
const pages = routes.map((config) => {
  const canonical = new URL(config.route, origin).href;
  return { ...config, canonical };
});

test('revised English guides have distinct nonempty titles, descriptions and headings', () => {
  for (const [name, read] of [
    ['title', (doc) => visibleText(one(byTag(doc, 'title'), 'One title'))],
    ['description', (doc) => normalize(one(byTag(doc, 'meta').filter((node) => node.attrs.name === 'description'), 'One description').attrs.content ?? '')],
    ['h1', (doc) => visibleText(one(byTag(doc, 'h1'), 'One h1'))]
  ]) {
    const values = pages.map(({ route, canonical }) => {
      const doc = documentAt(localFile(new URL(canonical)));
      const value = read(doc);
      assert.ok(value, `${route}: empty ${name}`);
      return value;
    });
    assert.equal(new Set(values).size, pages.length, `Guide ${name}s must be unique`);
  }
});

for (const { route, canonical, pt, es, updated } of pages) {
  test(`${route} metadata, language and alternate URLs`, () => {
    const doc = documentAt(localFile(new URL(canonical)));
    assert.equal(one(byTag(doc, 'html'), 'One html element').attrs.lang, 'en');
    const meta = byTag(doc, 'meta');
    const description = one(meta.filter((node) => node.attrs.name === 'description'), 'One description').attrs.content;
    assert.ok(description?.trim(), 'Description must not be empty');
    const links = byTag(doc, 'link');
    assert.equal(one(links.filter((node) => relContains(node, 'canonical')), 'One canonical').attrs.href, canonical);
    const alternates = links.filter((node) => node.attrs.hreflang);
    // An English-only page may omit hreflang, or declare only itself and
    // x-default. Never manufacture alternate URLs for untranslated content.
    const expected = pt && es
      ? { en: canonical, 'pt-BR': origin + pt, es: origin + es, 'x-default': canonical }
      : alternates.length ? { en: canonical, 'x-default': canonical } : {};
    assert.deepEqual(alternates.map((node) => node.attrs.hreflang).sort(), Object.keys(expected).sort(), 'Exactly one alternate per language');
    for (const alternate of alternates) {
      assert.ok(relContains(alternate, 'alternate'));
      assert.equal(alternate.attrs.href, expected[alternate.attrs.hreflang]);
      const translated = documentAt(localFile(new URL(alternate.attrs.href)));
      const translatedLinks = byTag(translated, 'link');
      assert.equal(one(translatedLinks.filter((node) => relContains(node, 'canonical')), 'Alternate has one canonical').attrs.href, alternate.attrs.href);
      const translatedAlternates = translatedLinks.filter((node) => node.attrs.hreflang);
      assert.equal(translatedAlternates.length, Object.keys(expected).length, 'Alternate has complete reciprocal language set');
      for (const [language, href] of Object.entries(expected)) {
        const reciprocal = one(translatedAlternates.filter((node) => node.attrs.hreflang === language), `One reciprocal ${language}`);
        assert.ok(relContains(reciprocal, 'alternate'));
        assert.equal(reciprocal.attrs.href, href);
      }
    }
    for (const directive of meta.filter((node) => /^(robots|googlebot|bingbot)$/iu.test(node.attrs.name ?? ''))) {
      assert.doesNotMatch(directive.attrs.content ?? '', /\b(?:noindex|nofollow|none)\b/iu, 'Public guides must remain indexable and followable');
    }
    const title = visibleText(one(byTag(doc, 'title'), 'One title'));
    for (const [key, value] of Object.entries({ 'og:title': title, 'twitter:title': title, 'og:description': description, 'twitter:description': description, 'og:url': canonical })) {
      assert.equal(one(meta.filter((node) => node.attrs.property === key || node.attrs.name === key), `One ${key}`).attrs.content, value);
    }
  });

  test(`${route} JSON-LD agrees with the heading and visible FAQ`, () => {
    const doc = documentAt(localFile(new URL(canonical)));
    const heading = visibleText(one(byTag(doc, 'h1'), 'Exactly one h1'));
    assert.ok(heading, 'Heading must not be empty');
    const graph = structuredData(doc);
    const article = one(graph.filter((node) => [node['@type']].flat().includes('TechArticle')), 'One TechArticle');
    assert.equal(article.dateModified, updated);
    assert.equal(normalize(article.headline), heading);
    assert.equal(article.url, canonical);
    const description = one(byTag(doc, 'meta').filter((node) => node.attrs.name === 'description'), 'One description').attrs.content;
    assert.equal(normalize(article.description), normalize(description), 'Article description agrees with page metadata');
    const faq = one(graph.filter((node) => [node['@type']].flat().includes('FAQPage')), 'One FAQPage');
    const lists = select(doc, (node) => (node.attrs.class ?? '').split(/\s+/u).includes('faq-list'));
    const details = lists.flatMap((list) => byTag(list, 'details'));
    assert.ok(details.length, 'Missing visible FAQ');
    const visible = details.map((detail) => {
      const summary = one(byTag(detail, 'summary'), 'One summary per FAQ');
      return {
        question: visibleText(summary),
        answer: normalize(detail.children.filter((node) => node !== summary).map(rawText).join(' '))
      };
    });
    assert.ok(Array.isArray(faq.mainEntity), 'FAQ mainEntity must be an array');
    const structured = faq.mainEntity.map((question) => {
      assert.equal(question['@type'], 'Question');
      assert.equal(question.acceptedAnswer?.['@type'], 'Answer');
      return { question: normalize(question.name), answer: visibleText(parse(question.acceptedAnswer.text)) };
    });
    assert.deepEqual(structured, visible, 'Structured FAQ must match visible questions and answers in order');
  });

  test(`${route} internal links and fragments resolve`, () => {
    const doc = documentAt(localFile(new URL(canonical)));
    const ids = select(doc, (entry) => Object.hasOwn(entry.attrs, 'id')).map((entry) => entry.attrs.id);
    assert.equal(new Set(ids).size, ids.length, 'Document IDs must be unique');
    for (const node of select(doc, (entry) => Object.hasOwn(entry.attrs, 'href'))) {
      const url = new URL(node.attrs.href, canonical);
      if (url.origin !== origin || url.pathname === '/privacy' || url.pathname.startsWith('/go/')) continue;
      const file = localFile(url);
      if (!url.hash) continue;
      const fragment = decodeURIComponent(url.hash.slice(1));
      const targets = select(documentAt(file), (entry) => entry.attrs.id === fragment || (entry.tag === 'a' && entry.attrs.name === fragment));
      assert.ok(targets.length, `Missing fragment: ${url.href}`);
    }
    for (const node of select(doc, (entry) => Object.hasOwn(entry.attrs, 'src'))) {
      const url = new URL(node.attrs.src, canonical);
      if (url.origin === origin) localFile(url);
    }
  });

  test(`${route} images exist and reserve layout dimensions`, () => {
    const doc = documentAt(localFile(new URL(canonical)));
    const images = byTag(doc, 'img');
    assert.ok(images.length, 'Missing guide images');
    for (const image of images) {
      assert.ok(image.attrs.src, 'Image missing src');
      const url = new URL(image.attrs.src, canonical);
      assert.equal(url.origin, origin, `Expected a checked-in image: ${url.href}`);
      localFile(url);
      assert.ok(/^[1-9]\d*$/u.test(image.attrs.width ?? ''), `${image.attrs.src}: missing positive width`);
      assert.ok(/^[1-9]\d*$/u.test(image.attrs.height ?? ''), `${image.attrs.src}: missing positive height`);
      assert.ok(image.attrs.alt?.trim(), `${image.attrs.src}: missing alt text`);
    }
    for (const meta of byTag(doc, 'meta').filter((node) => node.attrs.property === 'og:image' || node.attrs.name === 'twitter:image')) {
      const url = new URL(meta.attrs.content, canonical);
      assert.equal(url.origin, origin);
      localFile(url);
    }
  });
}

test('sitemap includes each revised guide once with its modification date', () => {
  const sitemap = parse(fs.readFileSync(path.join(siteRoot, 'sitemap.xml'), 'utf8'));
  for (const { canonical, updated } of pages) {
    const entry = one(byTag(sitemap, 'url').filter((node) => byTag(node, 'loc').some((loc) => visibleText(loc) === canonical)), `${canonical}: one sitemap entry`);
    assert.equal(visibleText(one(byTag(entry, 'lastmod'), `${canonical}: one lastmod`)), updated);
  }
});

test('guides are discoverable through crawlable links, including the new addon guide', () => {
  const directory = documentAt(localFile(new URL('/guides/', origin)));
  const directoryLinks = byTag(directory, 'a').filter((node) => node.attrs.href);
  for (const { canonical } of pages) {
    assert.ok(directoryLinks.some((node) => new URL(node.attrs.href, origin + '/guides/').href === canonical && visibleText(node)), `${canonical}: missing descriptive directory link`);
  }
  const homepage = documentAt(localFile(new URL('/', origin)));
  const homeLinks = byTag(homepage, 'a').filter((node) => node.attrs.href);
  for (const route of ['/guides/', '/stremio-addons-android-tv/']) {
    assert.ok(homeLinks.some((node) => new URL(node.attrs.href, origin).href === origin + route && visibleText(node)), `${route}: missing descriptive homepage link`);
  }
});

for (const { language, home, directory } of [
  { language: 'en', home: '/', directory: '/guides/' },
  { language: 'pt-BR', home: '/pt-br/', directory: '/pt-br/guias/' },
  { language: 'es', home: '/es/', directory: '/es/guias/' }
]) {
  for (const route of [home, directory]) {
    test(`${route} localized landing metadata and structured data agree`, () => {
      const canonical = origin + route;
      const doc = documentAt(localFile(new URL(canonical)));
      assert.equal(one(byTag(doc, 'html'), 'One html element').attrs.lang, language);
      const title = visibleText(one(byTag(doc, 'title'), 'One title'));
      const meta = byTag(doc, 'meta');
      const description = one(meta.filter((node) => node.attrs.name === 'description'), 'One description').attrs.content;
      assert.ok(title && description?.trim(), 'Landing pages have nonempty metadata');
      assert.equal(one(byTag(doc, 'link').filter((node) => relContains(node, 'canonical')), 'One canonical').attrs.href, canonical);
      for (const [key, value] of Object.entries({ 'og:title': title, 'twitter:title': title, 'og:description': description, 'twitter:description': description, 'og:url': canonical })) {
        assert.equal(one(meta.filter((node) => node.attrs.property === key || node.attrs.name === key), `One ${key}`).attrs.content, value);
      }
      const expectedType = route === home ? 'SoftwareApplication' : 'CollectionPage';
      const schema = one(structuredData(doc).filter((node) => [node['@type']].flat().includes(expectedType)), `One ${expectedType}`);
      assert.equal(schema.url, canonical);
      assert.equal(normalize(schema.description), normalize(description));
      if (route === home) assert.equal(schema.inLanguage, language, 'App schema language follows the homepage');
    });
  }

  test(`${home} service links use translated guides or a marked English-only destination`, () => {
    const doc = documentAt(localFile(new URL(home, origin)));
    const section = one(select(doc, (node) => (node.attrs.class ?? '').split(/\s+/u).includes('connections')), 'One service links section');
    const links = byTag(section, 'a');
    assert.ok(links.length >= 7, 'Service names must remain crawlable guide links');
    for (const link of links) {
      assert.ok(link.attrs.href && visibleText(link), 'Service link has a URL and descriptive text');
      const url = new URL(link.attrs.href, origin + home);
      assert.equal(url.origin, origin, 'Service guide stays on ARVIO');
      const target = documentAt(localFile(url));
      const targetLanguage = one(byTag(target, 'html'), 'Target has one html element').attrs.lang;
      if (url.pathname === '/stremio-addons-android-tv/') {
        assert.equal(link.attrs.hreflang, 'en', 'English-only guide is marked as English');
        assert.equal(targetLanguage, 'en');
      } else assert.equal(targetLanguage, language, `${url.href}: service link should use the current language`);
    }
    const directoryDoc = documentAt(localFile(new URL(directory, origin)));
    const addon = one(byTag(directoryDoc, 'a').filter((node) => node.attrs.href === '/stremio-addons-android-tv/'), 'One addon guide card');
    assert.ok(visibleText(addon));
    if (language !== 'en') assert.equal(addon.attrs.hreflang, 'en', 'Translated directory marks its English-only guide');
  });
}
