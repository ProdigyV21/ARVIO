import computeTables, {
  S, T1, T2, T5, T6, T7, T8, T3, T4, Si,
} from './utils/precompute';
import { getWords } from './utils/words';

computeTables();

/**
 * Low-level AES Cipher
 */
export default class AES {
  encKey: Uint32Array;
  decKey: Uint32Array;

  constructor(_key: string | Uint8Array | Uint32Array) {
    const key = getWords(_key);

    if (key.length !== 4 && key.length !== 6 && key.length !== 8) {
      throw new Error('Invalid key size');
    }

    this.encKey = new Uint32Array(4 * key.length + 28);
    this.decKey = new Uint32Array(4 * key.length + 28);

    this.encKey.set(key);

    let rcon = 1;
    let i = key.length;
    let tmp;

    // schedule encryption keys
    for (; i < 4 * key.length + 28; i++) {
      tmp = this.encKey[i - 1];

      // apply sbox
      if (i % key.length === 0 || (key.length === 8 && i % key.length === 4)) {
        tmp = S[tmp >>> 24] << 24 ^ S[(tmp >> 16) & 255] << 16 ^ S[(tmp >> 8) & 255] << 8 ^ S[tmp & 255];

        // shift rows and add rcon
        if (i % key.length === 0) {
          tmp = tmp << 8 ^ tmp >>> 24 ^ (rcon << 24);
          rcon = rcon << 1 ^ (rcon >> 7) * 283;
        }
      }

      this.encKey[i] = this.encKey[i - key.length] ^ tmp;
    }

    // schedule decryption keys
    for (let j = 0; i; j++, i--) {
      tmp = this.encKey[j & 3 ? i : i - 4];
      if (i <= 4 || j < 4) {
        this.decKey[j] = tmp;
      } else {
        this.decKey[j] = (
          T5[S[tmp >>> 24]]
        ^ T6[S[(tmp >> 16) & 255]]
        ^ T7[S[(tmp >> 8) & 255]]
        ^ T8[S[tmp & 255]]
        );
      }
    }
  }

  encrypt(_message: string | Uint32Array | Uint8Array) {
    const message = getWords(_message);
    const out = new Uint32Array(4);

    let a = message[0] ^ this.encKey[0];
    let b = message[1] ^ this.encKey[1];
    let c = message[2] ^ this.encKey[2];
    let d = message[3] ^ this.encKey[3];

    const rounds = this.encKey.length / 4 - 2;

    let k = 4;

    let a2; let b2; let c2;

    // Inner rounds.  Cribbed from OpenSSL.
    for (let i = 0; i < rounds; i++) {
      a2 = T1[a >>> 24] ^ T2[(b >> 16) & 255] ^ T3[(c >> 8) & 255] ^ T4[d & 255] ^ this.encKey[k];
      b2 = T1[b >>> 24] ^ T2[(c >> 16) & 255] ^ T3[(d >> 8) & 255] ^ T4[a & 255] ^ this.encKey[k + 1];
      c2 = T1[c >>> 24] ^ T2[(d >> 16) & 255] ^ T3[(a >> 8) & 255] ^ T4[b & 255] ^ this.encKey[k + 2];
      d = T1[d >>> 24] ^ T2[(a >> 16) & 255] ^ T3[(b >> 8) & 255] ^ T4[c & 255] ^ this.encKey[k + 3];
      a = a2; b = b2; c = c2;
      k += 4;
      // console.log(a, b, c, d);
    }

    // Last round.
    for (let i = 0; i < 4; i++) {
      out[i] = (
        S[a >>> 24] << 24
      ^ S[(b >> 16) & 255] << 16
      ^ S[(c >> 8) & 255] << 8
      ^ S[d & 255]
      ^ this.encKey[k++]
      );
      a2 = a; a = b; b = c; c = d; d = a2;
    }

    return out;
  }

  decrypt(_message: string | Uint32Array | Uint8Array) {
    const message = getWords(_message);
    const out = new Uint32Array(4);

    let a = message[0] ^ this.decKey[0];
    let b = message[3] ^ this.decKey[1];
    let c = message[2] ^ this.decKey[2];
    let d = message[1] ^ this.decKey[3];

    const rounds = this.decKey.length / 4 - 2;

    let a2; let b2; let c2;

    let k = 4;

    // Inner rounds.  Cribbed from OpenSSL.
    for (let i = 0; i < rounds; i++) {
      a2 = T5[a >>> 24] ^ T6[(b >> 16) & 255] ^ T7[(c >> 8) & 255] ^ T8[d & 255] ^ this.decKey[k];
      b2 = T5[b >>> 24] ^ T6[(c >> 16) & 255] ^ T7[(d >> 8) & 255] ^ T8[a & 255] ^ this.decKey[k + 1];
      c2 = T5[c >>> 24] ^ T6[(d >> 16) & 255] ^ T7[(a >> 8) & 255] ^ T8[b & 255] ^ this.decKey[k + 2];
      d = T5[d >>> 24] ^ T6[(a >> 16) & 255] ^ T7[(b >> 8) & 255] ^ T8[c & 255] ^ this.decKey[k + 3];
      a = a2; b = b2; c = c2;
      k += 4;
    }

    // Last round.
    for (let i = 0; i < 4; i++) {
      out[3 & -i] = (
        Si[a >>> 24] << 24
      ^ Si[(b >> 16) & 255] << 16
      ^ Si[(c >> 8) & 255] << 8
      ^ Si[d & 255]
      ^ this.decKey[k++]
      );
      a2 = a; a = b; b = c; c = d; d = a2;
    }

    return out;
  }
}
