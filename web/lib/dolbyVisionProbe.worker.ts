import { probeDolbyVisionMetadata } from './dolbyVisionMetadata';
import type { DolbyVisionProbeOptions } from './dolbyVision';

const port = self as unknown as {
  onmessage: ((event: MessageEvent<{ url: string; options: DolbyVisionProbeOptions }>) => void) | null;
  postMessage(value: unknown): void;
  close(): void;
};
port.onmessage = async ({ data }) => {
  try {
    port.postMessage(await probeDolbyVisionMetadata(data.url, data.options));
  } finally {
    // Do not leave an idle child worker if its remux-worker caller was disposed.
    port.close();
  }
};
