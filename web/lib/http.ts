export async function jsonRequest<T>(url: string, init: RequestInit = {}): Promise<T> {
  const response = await fetch(url, {
    ...init,
    headers: {
      Accept: "application/json",
      ...(init.body ? { "Content-Type": "application/json" } : {}),
      ...(init.headers ?? {})
    }
  });
  if (!response.ok) {
    const message = await response.text().catch(() => "");
    throw new Error(message || `Request failed with ${response.status}`);
  }
  if (response.status === 204) return undefined as T;
  return (await response.json()) as T;
}

export async function textRequest(url: string, init: RequestInit = {}): Promise<string> {
  const response = await fetch(url, init);
  if (!response.ok) {
    const message = await response.text().catch(() => "");
    throw new Error(message || `Request failed with ${response.status}`);
  }
  return response.text();
}

export function proxiedUrl(url: string, headers?: Record<string, string>) {
  const target = new URL("/api/proxy", window.location.origin);
  target.searchParams.set("url", url);
  if (headers && Object.keys(headers).length > 0) {
    target.searchParams.set("headers", btoa(JSON.stringify(headers)));
  }
  return target.toString();
}
