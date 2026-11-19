// Dismissal of the Home "Add a source" banner, per profile. localStorage, not
// sessionStorage: an iPhone home-screen app starts a fresh session on every
// launch, so a session-scoped dismissal brought the banner back each time.
export const SOURCE_PROMPT_KEY_PREFIX = "arvio.web.sourcePrompt.dismissed.v1:";
export const SOURCE_PROMPT_EVENT = "arvio:source-prompt";

function storage(): Storage | null {
  try {
    return typeof window === "undefined" ? null : window.localStorage;
  } catch {
    return null;
  }
}

export function isSourcePromptDismissed(profileId: string): boolean {
  if (!profileId) return false;
  try {
    return storage()?.getItem(`${SOURCE_PROMPT_KEY_PREFIX}${profileId}`) === "1";
  } catch {
    return false;
  }
}

// Whether the last check found no sources. The real answer needs the cloud
// addon pull, which lands after Home has painted; on phones and tablets the
// banner sits in the page flow, so it is shown from this hint at first paint
// instead of pushing Home down a few seconds later.
const EXPECTED_KEY_PREFIX = "arvio.web.sourcePrompt.expected.v1:";

export function isSourcePromptExpected(profileId: string): boolean {
  if (!profileId) return false;
  try {
    return storage()?.getItem(`${EXPECTED_KEY_PREFIX}${profileId}`) === "1";
  } catch {
    return false;
  }
}

export function setSourcePromptExpected(profileId: string, expected: boolean) {
  if (!profileId) return;
  try {
    if (expected) storage()?.setItem(`${EXPECTED_KEY_PREFIX}${profileId}`, "1");
    else storage()?.removeItem(`${EXPECTED_KEY_PREFIX}${profileId}`);
  } catch {
    /* optional */
  }
}

export function setSourcePromptDismissed(profileId: string, dismissed: boolean) {
  if (!profileId) return;
  try {
    const store = storage();
    if (dismissed) store?.setItem(`${SOURCE_PROMPT_KEY_PREFIX}${profileId}`, "1");
    else store?.removeItem(`${SOURCE_PROMPT_KEY_PREFIX}${profileId}`);
  } catch {
    /* storage is optional: the banner then simply returns next launch */
  }
  try {
    window.dispatchEvent(new CustomEvent(SOURCE_PROMPT_EVENT, { detail: { profileId, dismissed } }));
  } catch {
    /* no window (tests) */
  }
}
