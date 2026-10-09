
export const S = new Uint8Array(256);
export const Si = new Uint8Array(256);

export const T1 = new Uint32Array(256);
export const T2 = new Uint32Array(256);
export const T3 = new Uint32Array(256);
export const T4 = new Uint32Array(256);

export const T5 = new Uint32Array(256);
export const T6 = new Uint32Array(256);
export const T7 = new Uint32Array(256);
export const T8 = new Uint32Array(256);

export default function computeTables() {
  const d = new Uint8Array(256);
  const t = new Uint8Array(256);

  let x2; let x4; let x8; let s; let tEnc; let
    tDec;

  let x = 0;
  let xInv = 0;

  // Compute double and third tables
  for (let i = 0; i < 256; i++) {
    d[i] = i << 1 ^ (i >> 7) * 283;
    t[d[i] ^ i] = i;
  }

  for (; !S[x]; x ^= x2 || 1) {
    // Compute sbox
    s = xInv ^ xInv << 1 ^ xInv << 2 ^ xInv << 3 ^ xInv << 4;
    s = s >> 8 ^ s & 255 ^ 99;

    S[x] = s;
    Si[s] = x;

    // Compute MixColumns
    x8 = d[x4 = d[x2 = d[x]]];
    tDec = x8 * 0x1010101 ^ x4 * 0x10001 ^ x2 * 0x101 ^ x * 0x1010100;
    tEnc = d[s] * 0x101 ^ s * 0x1010100;

    T1[x] = tEnc = tEnc << 24 ^ tEnc >>> 8;
    T2[x] = tEnc = tEnc << 24 ^ tEnc >>> 8;
    T3[x] = tEnc = tEnc << 24 ^ tEnc >>> 8;
    T4[x] = tEnc = tEnc << 24 ^ tEnc >>> 8;

    T5[s] = tDec = tDec << 24 ^ tDec >>> 8;
    T6[s] = tDec = tDec << 24 ^ tDec >>> 8;
    T7[s] = tDec = tDec << 24 ^ tDec >>> 8;
    T8[s] = tDec = tDec << 24 ^ tDec >>> 8;

    xInv = t[xInv] || 1;
  }
}
