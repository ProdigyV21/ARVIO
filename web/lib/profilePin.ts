export function isValidProfilePin(pin: string) {
  return /^\d{4,5}$/.test(pin);
}

/** Hashes a 4-5 digit PIN exactly like Android PinUtil.hashPin: base64(16-byte salt)$base64(SHA-256(salt + pin)). */
export async function hashProfilePin(pin: string): Promise<string> {
  if (!isValidProfilePin(pin)) throw new Error("Invalid PIN");
  const salt = crypto.getRandomValues(new Uint8Array(16));
  const bytes = new Uint8Array([...salt, ...new TextEncoder().encode(pin)]);
  const hash = new Uint8Array(await crypto.subtle.digest("SHA-256", bytes));
  const b64 = (data: Uint8Array) => btoa(String.fromCharCode(...data));
  return `${b64(salt)}$${b64(hash)}`;
}

/** Same salted SHA-256 format as Android PinUtil: base64(salt)$base64(hash). */
export async function verifyProfilePin(pin: string, stored?: string | null): Promise<boolean> {
  if (!/^\d{4,5}$/.test(pin) || !stored) return false;
  try {
    const parts = stored.split("$");
    if (parts.length !== 2) return false;
    const salt = Uint8Array.from(atob(parts[0]), (char) => char.charCodeAt(0));
    const expected = Uint8Array.from(atob(parts[1]), (char) => char.charCodeAt(0));
    if (salt.length !== 16 || expected.length !== 32) return false;
    const bytes = new Uint8Array([...salt, ...new TextEncoder().encode(pin)]);
    const actual = new Uint8Array(await crypto.subtle.digest("SHA-256", bytes));
    return actual.reduce((different, byte, index) => different | (byte ^ expected[index]), 0) === 0;
  } catch { return false; }
}
