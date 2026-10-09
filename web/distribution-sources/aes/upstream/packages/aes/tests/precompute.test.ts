import computeTables, { S, Si, T1, T2, T3, T4, T5, T6, T7, T8 } from '../src/utils/precompute';
import * as TABLES from '../src/utils/tables';

test('aes | precompute tables', () => {
  computeTables();

  expect(S).toEqual(TABLES.S);
  expect(Si).toEqual(TABLES.Si);
  expect(T1).toEqual(TABLES.T1);
  expect(T2).toEqual(TABLES.T2);
  expect(T3).toEqual(TABLES.T3);
  expect(T4).toEqual(TABLES.T4);
  expect(T5).toEqual(TABLES.T5);
  expect(T6).toEqual(TABLES.T6);
  expect(T7).toEqual(TABLES.T7);
  expect(T8).toEqual(TABLES.T8);
});
