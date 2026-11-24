// 5.1 to stereo for laptop and phone speakers, with speech kept clear.
//
// The standard downmix (ITU-R BS.775: centre and surrounds at -3 dB) leaves
// dialogue, which lives in the centre channel, quiet next to music and effects:
// the "turn it up for voices, down for explosions" problem. This mix keeps the
// centre at full level, lowers the surrounds to -6 dB, drops the LFE (small
// speakers cannot reproduce it and it only eats headroom) and then applies a
// gentle peak limiter instead of hard clipping. Channel order is the SMPTE /
// WebCodecs order decoders deliver: L, R, C, LFE, Ls, Rs.

const CENTRE = 1.0;
const SURROUND = 0.5;
const FRONT = 0.8;
/** Peaks above this are compressed softly toward 1.0 instead of clipping. */
const KNEE = 0.85;

function soften(value: number) {
  const magnitude = Math.abs(value);
  if (magnitude <= KNEE) return value;
  // Smoothly approaches 1.0: tanh above the knee, continuous at the knee.
  const over = (magnitude - KNEE) / (1 - KNEE);
  return Math.sign(value) * (KNEE + (1 - KNEE) * Math.tanh(over));
}

/** Downmixes planar 5.1 (6 planes of equal length) into two planes. */
export function dialogueDownmix(planes: Float32Array[]): [Float32Array, Float32Array] {
  const [left, right, centre, , leftSurround, rightSurround] = planes;
  const frames = left.length;
  const outLeft = new Float32Array(frames);
  const outRight = new Float32Array(frames);
  for (let index = 0; index < frames; index += 1) {
    const voice = CENTRE * centre[index];
    outLeft[index] = soften(FRONT * left[index] + voice + SURROUND * leftSurround[index]);
    outRight[index] = soften(FRONT * right[index] + voice + SURROUND * rightSurround[index]);
  }
  return [outLeft, outRight];
}
