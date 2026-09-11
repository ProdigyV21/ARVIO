import { createFile } from "mp4box";

const MIB = 1024 * 1024;
const MAX_GOP_BYTES = 24 * MIB;
const MAX_PENDING_BYTES = 64 * MIB;

// A muxer can retain the next GOP while it waits for encoded audio. This is
// different from a single oversized GOP, which cannot be flushed incrementally.
export class RemuxBufferBudget {
  private gopBytes = 0;
  pendingBytes = 0;

  addVideo(bytes: number, key: boolean) {
    const next = (key ? 0 : this.gopBytes) + bytes;
    if (next > MAX_GOP_BYTES) {
      throw new Error("Keyframes are too far apart for bounded browser playback. Use server conversion or an external player.");
    }
    this.add(bytes);
    this.gopBytes = next;
  }

  add(bytes: number) {
    if (this.pendingBytes + bytes > MAX_PENDING_BYTES) {
      throw new Error("Audio and video could not be interleaved within the browser memory limit. Use provider conversion or an external player.");
    }
    this.pendingBytes += bytes;
  }

  releaseFragment(moof: Uint8Array) {
    // Parse only the small fragment header, not mdat (which would copy the entire
    // video fragment). Subtract actual sample bytes, leaving queued GOPs counted.
    const file = createFile(false);
    const buffer = Object.assign(moof.slice().buffer as ArrayBuffer, { fileStart: 0 });
    file.appendBuffer(buffer);
    const fragment = file.moofs[0];
    if (!fragment || file.moofs.length !== 1) throw new Error("Invalid browser media fragment");
    let released = 0;
    for (const track of fragment.trafs) {
      for (const run of track.truns) {
        released += run.flags & 0x200
          ? run.sample_size.reduce((total, size) => total + size, 0)
          : run.sample_count * (track.tfhd.default_sample_size ?? 0);
      }
    }
    if (!Number.isSafeInteger(released) || released < 0 || released > this.pendingBytes) {
      throw new Error("Invalid browser media buffer accounting");
    }
    this.pendingBytes -= released;
  }
}
