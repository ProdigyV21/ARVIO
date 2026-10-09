import fs from 'node:fs';
import path from 'node:path';
import {fileURLToPath} from 'node:url';
import ts from 'typescript';

export const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
export const normalize = text => text.trim().toLowerCase().replace(/…/g, '...');
export const neutral = new Set(['ARVIO','ARVIO Web Premium','Android:','DASH','E','HLS','Infuse','MPEG-TS','S','Simkl','TV (IPTV)','Telegram','TheSportsDB','VLC','VS','iOS / iPadOS:','s','vlc-setup.bat (Windows)','vlc-setup.sh (Linux)','{value0} x {value1}','{value0}m','••••••••','+1 650 555 1234','00:1A:79:00:00:00']);
export function isNeutral(text) {
  if (text === 'Jellyfin / Silo') return true;
  return !/[A-Za-z]/.test(text) || neutral.has(text) || /^https?:\/\//.test(text) || /^(?:\d+[KkPp]|[A-Z\d.+-]{2,12})$/.test(text);
}
export function androidLanguages() {
  const file = fs.readFileSync(path.join(root, '../app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsScreen.kt'), 'utf8');
  const block = file.match(/val TMDB_LANGUAGES = listOf\(([\s\S]*?)\n\)/)?.[1];
  if (!block) throw new Error('Android language catalog not found');
  return [...block.matchAll(/"([^"]+)" to "([^"]+)"/g)].map(([,code,label]) => ({code,label}));
}
export function localeFor(code) {
  if (/^pt-(BR|PT)$|^zh-(CN|TW)$/.test(code)) return code;
  return code.split('-')[0].replace(/^no$/, 'nb').replace(/^iw$/, 'he').replace(/^fil$/, 'tl');
}
export function requiredPhrases() {
  const keys = new Set(Object.keys(JSON.parse(fs.readFileSync(path.join(root, 'lib/i18n/es.json'), 'utf8'))));
  for (const directory of ['components','lib']) {
    for (const file of fs.readdirSync(path.join(root,directory), {recursive:true}).filter(file=>/\.tsx?$/.test(file) && !file.startsWith('i18n'))) {
      const source=ts.createSourceFile(file,fs.readFileSync(path.join(root,directory,file),'utf8'),ts.ScriptTarget.Latest,true,file.endsWith('.tsx')?ts.ScriptKind.TSX:ts.ScriptKind.TS);
      function visit(node) {
        if (ts.isCallExpression(node) && node.expression.getText(source)==='translateUi' && ts.isStringLiteral(node.arguments[0])) keys.add(node.arguments[0].text.trim());
        if (file === 'calendar.ts' && ts.isVariableDeclaration(node) && node.name.getText(source) === 'CALENDAR_KIND_LABELS' && node.initializer && ts.isObjectLiteralExpression(node.initializer)) {
          for (const property of node.initializer.properties) if (ts.isPropertyAssignment(property) && ts.isStringLiteral(property.initializer)) keys.add(property.initializer.text);
        }
        if (ts.isPropertyAssignment(node) && ['label','hint','emptyMessage', ...(file.endsWith('TrackerLibrary.tsx') ? ['name'] : [])].includes(node.name.getText(source)) && ts.isStringLiteral(node.initializer)) keys.add(node.initializer.text.trim());
        if (directory==='components' && ts.isArrayLiteralExpression(node) && node.elements.length===2 && node.elements.every(ts.isStringLiteral)) {
          const label=node.elements[1].text;
          if (/^[A-Z][A-Za-z\s()+/.-]+$/.test(label)) keys.add(label);
        }
        ts.forEachChild(node, visit);
      }
      visit(source);
    }
  }
  return [...keys].filter(key=>!isNeutral(key)).sort();
}
