const test = require('node:test');
const assert = require('node:assert/strict');
const React = require('react');
const { renderToStaticMarkup } = require('react-dom/server');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');

const mocks = {
  'react/jsx-runtime': require('react/jsx-runtime'),
  '@/lib/i18n': { useTranslation: () => text => text },
  '@/components/livetv/ChannelLogo': { ChannelLogo: () => React.createElement('span', { 'aria-hidden': true }) }
};
const filename = path.resolve(__dirname, '../components/livetv/ChannelProgramInfo.tsx');
const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), {
  fileName: filename,
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022, jsx: ts.JsxEmit.ReactJSX }
}).outputText;
const component = { exports: {} };
vm.runInNewContext(code, { module: component, exports: component.exports, require: name => {
  if (name in mocks) return mocks[name];
  throw Error(`Unmocked dependency ${name}`);
} }, { filename });
const { ChannelProgramInfo } = component.exports;
const channel = { id: 'event:1', name: 'PPV Event 23 – World Championship Final, Saturday evening, Main Arena', group: 'Events', streamUrl: 'https://example.invalid/live' };
const program = { title: 'Live coverage', startUtcMillis: 100, endUtcMillis: 200, description: 'Current programme description.' };
const render = (guide, extra = {}) => renderToStaticMarkup(React.createElement(ChannelProgramInfo, {
  channel: { ...channel, ...extra }, guide, formatTime: time => `time-${time}`
}));

test('missing EPG exposes the complete event channel name once without programme timing', () => {
  const html = render(undefined);
  assert.ok(html.includes(`<h2 class="livetv-program-title">${channel.name}</h2>`));
  assert.equal(html.split(channel.name).length - 1, 1);
  assert.ok(html.includes('No guide data for this channel.'));
  assert.ok(!html.includes('livetv-program-head'));
});

test('real programme data takes priority and preserves channel identity, times and description', () => {
  const html = render({ now: program }, { qualityLabel: 'HD' });
  assert.ok(html.includes('<h2 class="livetv-program-title">Live coverage</h2>'));
  assert.ok(html.includes(`${channel.name} · HD`));
  assert.ok(html.includes('time-100 – time-200'));
  assert.ok(html.includes(program.description));
  assert.ok(!html.includes('No guide data for this channel.'));
});

test('blank programme titles use the channel fallback and preserve genuine programme fields', () => {
  const html = render({ now: { ...program, title: ' \t ' } }, { qualityLabel: '4K' });
  assert.ok(html.includes(`<h2 class="livetv-program-title">${channel.name}</h2>`));
  assert.equal(html.split(channel.name).length - 1, 1);
  assert.ok(html.includes('<span>4K</span>'));
  assert.ok(html.includes('time-100 – time-200'));
  assert.ok(html.includes(program.description));
  assert.ok(!html.includes('No guide data for this channel.'));
});

test('a next programme remains genuine guide information even without a current programme', () => {
  const html = render({ next: { ...program, title: 'Next event' } });
  assert.ok(html.includes(`<h2 class="livetv-program-title">${channel.name}</h2>`));
  assert.ok(html.includes('<span>NEXT</span>'));
  assert.ok(html.includes('<strong>Next event</strong>'));
  assert.ok(html.includes('time-100'));
});
