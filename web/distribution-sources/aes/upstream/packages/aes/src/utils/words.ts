/* eslint-disable import/prefer-default-export */
import { s2i } from '@cryptography/utils';

/**
 * Helper function for transforming string key to Uint32Array
 */
export function getWords(key: string | Uint8Array | Uint32Array) {
  if (key instanceof Uint32Array) {
    return key;
  }

  if (typeof key === 'string') {
    if (key.length % 4 !== 0) for (let i = key.length % 4; i <= 4; i++) key += '\0x00';

    const buf = new Uint32Array(key.length / 4);
    for (let i = 0; i < key.length; i += 4) buf[i / 4] = s2i(key, i);

    return buf;
  }

  if (key instanceof Uint8Array) {
    const buf = new Uint32Array(key.length / 4);

    for (let i = 0; i < key.length; i += 4) {
      buf[i / 4] = (
        key[i] << 24
        ^ key[i + 1] << 16
        ^ key[i + 2] << 8
        ^ key[i + 3]
      );
    }

    return buf;
  }

  throw new Error('Unable to create 32-bit words');
}

export function xor(left: Uint32Array, right: Uint32Array, to = left) {
  for (let i = 0; i < left.length; i++) to[i] = left[i] ^ right[i];
}
