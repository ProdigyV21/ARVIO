export { i2h, i2s, s2i } from './converters';

export interface HashStream {
  update(chunk: string | Uint32Array): HashStream;
  digest(): Uint32Array;
  digest(format: 'hex' | 'binary'): string;
}

export interface HashFunction {
  (message: string | Uint32Array): Uint32Array;
  (message: string | Uint32Array, format: 'hex' | 'binary'): string;
  stream(buf?: Uint32Array): HashStream;
  blockLength: number;
  digestLength: number;
}
