import AES from '../src/aes';

test('aes | encrypt', () => {
  expect(
    new AES(new Uint32Array([0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF])).encrypt('testtesttesttest')
  ).toEqual(
    new Uint32Array([-1580214082, -549871682, 1135529346, 460977466])
  )
});

test('aes | decrypt', () => {
  expect(
    new AES(new Uint32Array([0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF])).decrypt('testtesttesttest')
  ).toEqual(
    new Uint32Array([-17607495, -1722016620, -19737959, -1060255347])
  )
});
