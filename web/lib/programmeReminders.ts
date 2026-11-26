import type { SportsReminder } from "./sportsFollows";
import type { IptvNowNext, IptvProgram } from "./types";

/**
 * Reminders for ordinary guide shows share the Sports reminder list, so one
 * banner (a minute before, with a countdown and Cancel) serves both. Mirrors
 * Android's ProgrammeInfo.kt; the "tv:" id keeps them apart from game ids.
 */
export const PROGRAMME_REMINDER_PREFIX = "tv:";

export const programmeReminderId = (channelId: string, program: Pick<IptvProgram, "startUtcMillis">) =>
  `${PROGRAMME_REMINDER_PREFIX}${channelId}:${program.startUtcMillis}`;

export function programmeReminder(channelIds: string[], program: IptvProgram): SportsReminder | undefined {
  const first = channelIds[0];
  if (!first) return undefined;
  return { eventId: programmeReminderId(first, program), title: program.title || first, startsAt: program.startUtcMillis, channelIds: [...new Set(channelIds)].slice(0, 8) };
}

export function withProgrammeReminder(reminders: SportsReminder[], reminder: SportsReminder, on: boolean): SportsReminder[] {
  const rest = reminders.filter(r => r.eventId !== reminder.eventId);
  return on ? [...rest, reminder] : rest;
}

export function remindedProgrammeIds(reminders: SportsReminder[]): Set<string> {
  return new Set(reminders.filter(r => !r.dismissed && r.eventId.startsWith(PROGRAMME_REMINDER_PREFIX)).map(r => r.eventId));
}

/** The first show that has not started yet. */
export function nextShowOf(guide: IptvNowNext | undefined, now = Date.now()): IptvProgram | undefined {
  if (!guide) return undefined;
  return [guide.next, ...(guide.upcoming ?? [])].filter((p): p is IptvProgram => Boolean(p) && p!.startUtcMillis > now)
    .sort((a, b) => a.startUtcMillis - b.startUtcMillis)[0];
}
