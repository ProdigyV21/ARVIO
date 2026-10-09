import AES from '../aes';
import { getWords } from '../utils/words';

/**
 * AES-IGE mode.
 */
export default class AES_IGE {
  cipher: AES;
  key: Uint32Array;
  counter: Uint32Array;
  blockSize: number;
  offset = 0;

  constructor(key: string | Uint32Array | Uint8Array, counter: string | Uint32Array | Uint8Array, blockSize = 16) {
    this.key = getWords(key);
    this.counter = getWords(counter);
    this.cipher = new AES(key);
    this.blockSize = blockSize / 4;

    if (this.counter.length !== 4) {
      throw new Error('AES-CTR mode counter must be 16 bytes length');
    }
  }

  /**
   * Encrypts plain text with AES-IGE mode.
   */
  encrypt(message: string | Uint32Array | Uint8Array, buf?: Uint32Array) {
    const text = getWords(message);
    const cipherText = buf || new Uint32Array(text.length);

    let offset = this.offset;
    for (let i = 0; i < text.length; i += this.blockSize) {
      const x = this.cipher.encrypt(this.counter);
      for (let j = i, k = offset; j < text.length && k < this.blockSize; j++, k++) cipherText[j] = x[k] ^ text[j];

      if (text.length - i >= this.blockSize) this.incrementCounter();
      if (offset) {
        i -= offset;
        offset = 0;
      }
    }

    this.offset = (this.offset + (text.length % 4)) % 4;

    return cipherText;
  }

  /**
   * Decrypts cipher text with AES-IGE mode.
   */
  decrypt(message: string | Uint32Array | Uint8Array, buf?: Uint32Array) {
    return this.encrypt(message, buf);
  }

  incrementCounter() {
    // increment counter
    for (let carry = this.counter.length - 1; carry >= 0; carry--) {
      if (++this.counter[carry] < 0xFFFFFFFF) break; // If overflowing, it'll be 0 and we'll have to continue propagating the carry
    }
  }
}
