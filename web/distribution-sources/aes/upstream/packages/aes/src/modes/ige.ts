import AES from '../aes';
import { getWords, xor } from '../utils/words';

/**
 * AES-IGE mode.
 */
export default class AES_IGE {
  cipher: AES;
  key: Uint32Array;
  iv: Uint32Array;
  blockSize: number;

  constructor(key: string | Uint32Array | Uint8Array, iv: string | Uint32Array | Uint8Array, blockSize = 16) {
    this.key = getWords(key);
    this.iv = getWords(iv);
    this.cipher = new AES(key);
    this.blockSize = blockSize / 4;
  }

  /**
   * Encrypts plain text with AES-IGE mode.
   */
  encrypt(message: string | Uint32Array | Uint8Array, buf?: Uint32Array) {
    const text = getWords(message);
    const cipherText = buf || new Uint32Array(text.length);

    let prevX = this.iv.subarray(this.blockSize, this.iv.length);
    let prevY = this.iv.subarray(0, this.blockSize);
    const yXOR = new Uint32Array(this.blockSize);

    for (let i = 0; i < text.length; i += this.blockSize) {
      const x = text.subarray(i, i + this.blockSize);
      xor(x, prevY, yXOR);

      const y = this.cipher.encrypt(yXOR);
      xor(y, prevX);

      prevX = x;
      prevY = y;

      for (let j = i, k = 0; j < text.length && k < 4; j++, k++) cipherText[j] = y[k];
    }

    return cipherText;
  }

  /**
   * Decrypts cipher text with AES-IGE mode.
   */
  decrypt(message: string | Uint32Array | Uint8Array, buf?: Uint32Array) {
    const cipherText = getWords(message);
    const text = buf || new Uint32Array(cipherText.length);

    let prevY = this.iv.subarray(this.blockSize, this.iv.length);
    let prevX = this.iv.subarray(0, this.blockSize);
    const yXOR = new Uint32Array(this.blockSize);

    for (let i = 0; i < text.length; i += this.blockSize) {
      const x = cipherText.subarray(i, i + this.blockSize);
      xor(x, prevY, yXOR);

      const y = this.cipher.decrypt(yXOR);
      xor(y, prevX);

      prevX = x;
      prevY = y;

      for (let j = i, k = 0; j < text.length && k < 4; j++, k++) text[j] = y[k];
    }

    return text;
  }
}
