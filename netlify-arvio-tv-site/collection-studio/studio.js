import { LIMITS, PRESETS, THEMES, createDefaultDraft, validateDraft, serializeCollections, encodeShare, decodeShare, sourceLabel } from './studio-core.mjs';

const byId = id => document.getElementById(id);
const fields = ['row-title', 'folder-title', 'folder-description', 'source-kind', 'preset', 'source-url', 'folder-theme'];
const pageAnchors = new Set(['#studio', '#how-to-use', '#examples']);
let draft = createDefaultDraft();
let selected = 0;
let nextFolderId = 4;
let shared = false;
let valid = false;
let json = '';
let shareFragment = '';

function node(tag, className, text) {
  const element = document.createElement(tag);
  if (className) element.className = className;
  if (text !== undefined) element.textContent = text;
  return element;
}

for (const preset of PRESETS) {
  const option = node('option', '', preset.label);
  option.value = preset.id;
  byId('preset').append(option);
}

function renderFolders() {
  byId('folder-tabs').replaceChildren();
  byId('preview-folders').replaceChildren();
  byId('preview-title').textContent = draft.title || 'Your Home row';
  draft.folders.forEach((folder, index) => {
    const selectFolder = () => {
      selected = index;
      fillForm();
      render();
      byId('folder-tabs').children[index]?.focus();
    };
    const tab = node('button', 'folder-tab', String(index + 1).padStart(2, '0'));
    tab.type = 'button';
    tab.dataset.folderIndex = String(index);
    tab.setAttribute('aria-label', `Edit folder ${index + 1}: ${folder.title || 'Untitled'}`);
    tab.setAttribute('aria-pressed', String(selected === index));
    tab.addEventListener('click', selectFolder);
    byId('folder-tabs').append(tab);

    const theme = THEMES.includes(folder.theme) ? folder.theme : 'sage';
    const card = node(shared ? 'article' : 'button', `folder-card theme-${theme}`);
    if (!shared) {
      card.type = 'button';
      card.setAttribute('aria-label', `Edit folder ${index + 1}: ${folder.title || 'Untitled'}`);
      card.setAttribute('aria-pressed', String(selected === index));
      card.addEventListener('click', selectFolder);
    }
    const art = node('span', 'folder-art');
    const top = node('span', 'art-top');
    const glyph = node('span', 'folder-glyph');
    glyph.setAttribute('aria-hidden', 'true');
    const number = node('span', 'folder-number', String(index + 1).padStart(2, '0'));
    number.setAttribute('aria-hidden', 'true');
    top.append(glyph, number);
    art.append(top, node('span', 'art-title', folder.title || 'Untitled folder'));
    card.append(art, node('span', 'folder-source', sourceLabel(folder.source)));
    if (folder.description) card.append(node('span', 'folder-desc', folder.description));
    if (shared && folder.source.kind !== 'tmdb') {
      // List addresses are plain text. The preview never opens or requests them.
      card.append(node('span', 'folder-desc', folder.source.url));
    }
    byId('preview-folders').append(card);
  });
}

function updateSourceFields() {
  const source = draft.folders[selected].source;
  byId('preset-field').hidden = source.kind !== 'tmdb';
  byId('url-field').hidden = source.kind === 'tmdb';
  byId('preset-detail').textContent = PRESETS.find(p => p.id === source.preset)?.detail || '';
  byId('source-url').placeholder = source.kind === 'trakt' ? 'https://trakt.tv/lists/12345' : 'https://mdblist.com/lists/username/list';
  byId('source-hint').textContent = source.kind === 'tmdb'
    ? 'Each folder has one source. Presets are ARVIO-authored TMDB queries.'
    : source.kind === 'mdblist'
      ? 'Use a public list page: https://mdblist.com/lists/USERNAME/LIST. No queries or tokens. Public access cannot be verified here.'
      : 'Use https://trakt.tv/lists/12345 with a numeric list ID. User/list slug links cannot be resolved here. If you only have a slug link, choose MDBList or a TMDB preset. The list must be public.';
}

function fillForm() {
  const folder = draft.folders[selected];
  byId('row-title').value = draft.title;
  byId('folder-title').value = folder.title;
  byId('folder-description').value = folder.description;
  byId('folder-theme').value = folder.theme;
  byId('source-kind').value = folder.source.kind;
  byId('preset').value = folder.source.preset || PRESETS[0].id;
  byId('source-url').value = folder.source.url || '';
  byId('editing-label').textContent = `Editing folder ${selected + 1}`;
  updateSourceFields();
}

function validate() {
  let error = '';
  try {
    validateDraft(draft);
    json = serializeCollections(draft);
    valid = true;
  } catch (issue) {
    error = issue.message;
    json = '';
    valid = false;
  }
  byId('field-error').textContent = error;
  byId('field-error').hidden = !error;
  fields.forEach(id => byId(id).removeAttribute('aria-invalid'));
  if (error) {
    const field = /Home row title/.test(error) ? 'row-title' : /description/.test(error) ? 'folder-description'
      : /title/.test(error) ? 'folder-title' : /https:|URL/.test(error) ? 'source-url' : 'source-kind';
    byId(field).setAttribute('aria-invalid', 'true');
  }
  byId('json-output').value = json;
  byId('copy-json').disabled = !valid;
  byId('download-json').disabled = !valid;
  let warning = '';
  shareFragment = '';
  if (valid) {
    try { shareFragment = encodeShare(draft); }
    catch (issue) { warning = issue.message; }
  }
  byId('copy-share').disabled = !valid || !shareFragment;
  byId('share-warning').textContent = warning;
  byId('share-warning').hidden = !warning;
}

function render() {
  renderFolders();
  validate();
  byId('add-folder').disabled = draft.folders.length >= LIMITS.folders;
  byId('remove-folder').disabled = draft.folders.length <= 1;
  byId('move-up').disabled = selected === 0;
  byId('move-down').disabled = selected === draft.folders.length - 1;
}

function showStudio(isShared) {
  shared = isShared;
  byId('recovery').hidden = true;
  byId('studio').hidden = false;
  byId('studio').classList.toggle('is-shared', shared);
  byId('editor').hidden = shared;
  byId('fork-shared').hidden = !shared;
  byId('session-note').hidden = shared;
  byId('mode-label').textContent = shared ? 'Collection Studio · shared preview' : 'Collection Studio · pilot';
  byId('workspace-title').textContent = shared ? 'A collection worth keeping.' : 'Make it yours.';
  byId('preview-caption-text').textContent = shared
    ? 'Read-only shared preview. Make your own copy to edit it, or copy JSON to import it manually.'
    : 'Select a folder to make it yours. No source requests are made here.';
  byId('clipboard-fallback').hidden = true;
  byId('status').textContent = '';
  fillForm();
  render();
}

function removeFragment() {
  const url = new URL(location.href);
  url.hash = '';
  history.replaceState(null, '', url);
}

function loadFromLocation() {
  const fragment = location.hash;
  if (fragment && !pageAnchors.has(fragment)) {
    try {
      draft = decodeShare(fragment);
      selected = 0;
      nextFolderId = Math.max(0, ...draft.folders.map(folder => {
        const number = Number(folder.id.match(/^folder-(\d+)$/)?.[1]);
        return Number.isSafeInteger(number) && number > 0 && number < 1_000_000 ? number : 0;
      })) + 1;
      showStudio(true);
    } catch (issue) {
      byId('studio').hidden = true;
      byId('recovery').hidden = false;
      byId('recovery-error').textContent = issue.message;
      byId('recovery-title').focus();
    }
  } else showStudio(false);
}

function changeDraft(callback) {
  if (shared) return;
  callback(draft.folders[selected]);
  byId('clipboard-fallback').hidden = true;
  byId('status').textContent = '';
  updateSourceFields();
  render();
}

byId('row-title').addEventListener('input', event => changeDraft(() => { draft.title = event.target.value; }));
byId('folder-title').addEventListener('input', event => changeDraft(folder => { folder.title = event.target.value; }));
byId('folder-description').addEventListener('input', event => changeDraft(folder => { folder.description = event.target.value; }));
byId('folder-theme').addEventListener('change', event => changeDraft(folder => { folder.theme = event.target.value; }));
byId('preset').addEventListener('change', event => changeDraft(folder => { folder.source = { kind: 'tmdb', preset: event.target.value }; }));
byId('source-url').addEventListener('input', event => changeDraft(folder => { folder.source.url = event.target.value; }));
byId('source-kind').addEventListener('change', event => {
  changeDraft(folder => { folder.source = event.target.value === 'tmdb' ? { kind: 'tmdb', preset: PRESETS[0].id } : { kind: event.target.value, url: '' }; });
  fillForm();
});

byId('add-folder').addEventListener('click', () => {
  if (shared || draft.folders.length >= LIMITS.folders) return;
  let next = nextFolderId;
  while (draft.folders.some(folder => folder.id === `folder-${next}`)) next++;
  nextFolderId = next + 1;
  draft.folders.push({ id: `folder-${next}`, title: 'New discoveries', description: '', theme: THEMES[draft.folders.length % THEMES.length], source: { kind: 'tmdb', preset: PRESETS[0].id } });
  selected = draft.folders.length - 1;
  fillForm();
  render();
  byId('folder-title').focus();
  byId('folder-title').select();
  byId('status').textContent = `Added folder ${selected + 1}.`;
});

byId('remove-folder').addEventListener('click', () => {
  if (shared || draft.folders.length <= 1) return;
  draft.folders.splice(selected, 1);
  selected = Math.min(selected, draft.folders.length - 1);
  fillForm();
  render();
  byId('folder-title').focus();
  byId('status').textContent = 'Folder removed from this draft.';
});

function moveFolder(offset) {
  const next = selected + offset;
  if (shared || next < 0 || next >= draft.folders.length) return;
  [draft.folders[selected], draft.folders[next]] = [draft.folders[next], draft.folders[selected]];
  selected = next;
  fillForm();
  render();
  const control = byId(offset < 0 ? 'move-up' : 'move-down');
  if (control.disabled) byId('folder-tabs').children[selected]?.focus();
  else control.focus();
  byId('status').textContent = `Folder moved to position ${selected + 1}.`;
}
byId('move-up').addEventListener('click', () => moveFolder(-1));
byId('move-down').addEventListener('click', () => moveFolder(1));

async function copyText(text, success) {
  byId('clipboard-fallback').hidden = true;
  try {
    if (!navigator.clipboard?.writeText) throw new Error('Clipboard unavailable');
    await navigator.clipboard.writeText(text);
    byId('status').textContent = success;
  } catch {
    byId('clipboard-fallback').hidden = false;
    byId('copy-fallback').value = text;
    byId('copy-fallback').focus();
    byId('copy-fallback').select();
    byId('status').textContent = 'Automatic copy is unavailable. Your text is selected below for manual copying.';
  }
}

byId('copy-json').addEventListener('click', () => {
  if (valid) void copyText(json, 'JSON copied. Paste it into ARVIO’s collection importer.');
});
byId('copy-share').addEventListener('click', () => {
  if (!valid || !shareFragment) return;
  const url = new URL(location.href);
  url.search = '';
  url.hash = shareFragment;
  void copyText(url.href, 'Preview link copied. Anyone with it can read the collection.');
});
byId('download-json').addEventListener('click', () => {
  if (!valid) return;
  const objectUrl = URL.createObjectURL(new Blob([json], { type: 'application/json;charset=utf-8' }));
  const link = node('a');
  link.href = objectUrl;
  link.download = 'collections.json';
  document.body.append(link);
  link.click();
  link.remove();
  setTimeout(() => URL.revokeObjectURL(objectUrl), 1000);
  byId('status').textContent = 'collections.json downloaded. Open the file to copy its text, or host it at a public JSON URL.';
});

byId('fork-shared').addEventListener('click', () => {
  draft = validateDraft(draft);
  removeFragment();
  showStudio(false);
  byId('row-title').focus();
  byId('status').textContent = 'This is your editable copy. Changes stay in this tab; copy or download them before leaving.';
});
byId('start-new').addEventListener('click', () => {
  draft = createDefaultDraft();
  selected = 0;
  nextFolderId = 4;
  removeFragment();
  showStudio(false);
  byId('row-title').focus();
});
window.addEventListener('hashchange', () => {
  // Normal page anchors must not turn an editor or shared preview into a new mode.
  if (!pageAnchors.has(location.hash)) loadFromLocation();
});
loadFromLocation();
