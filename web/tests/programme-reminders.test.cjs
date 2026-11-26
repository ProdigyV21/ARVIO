const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');

const filename = path.resolve(__dirname, '../lib/programmeReminders.ts');
const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), {
  fileName: filename,
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 }
}).outputText;
const mod = { exports: {} };
vm.runInNewContext(code, { module: mod, exports: mod.exports, require: name => { throw Error(`Unmocked dependency ${name}`); } }, { filename });
const { programmeReminder, programmeReminderId, remindedProgrammeIds, withProgrammeReminder } = mod.exports;

const show = { title: 'Evening News', startUtcMillis: 10000, endUtcMillis: 20000 };

test('a show reminder switches to its channel at the start, same id as Android', () => {
  const reminder = programmeReminder(['ch1', 'ch1-hd', 'ch1'], show);
  assert.equal(reminder.eventId, 'tv:ch1:10000');
  assert.equal(programmeReminderId('ch1', show), 'tv:ch1:10000');
  assert.equal(reminder.startsAt, 10000);
  assert.deepEqual([...reminder.channelIds], ['ch1', 'ch1-hd']);
  assert.equal(programmeReminder([], show), undefined);
});

test('toggling a show keeps game reminders', () => {
  const game = { eventId: 'sportsdb:1', title: 'Game', startsAt: 5000, channelIds: [] };
  const reminder = programmeReminder(['ch1'], show);
  const on = withProgrammeReminder([game], reminder, true);
  assert.equal(on.length, 2);
  assert.deepEqual([...remindedProgrammeIds(on)], ['tv:ch1:10000']);
  const off = withProgrammeReminder(on, reminder, false);
  assert.equal(off.length, 1);
  assert.equal(off[0], game);
  assert.equal(remindedProgrammeIds(off).size, 0);
});
