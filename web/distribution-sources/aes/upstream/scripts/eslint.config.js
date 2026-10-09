module.exports = {
  env: {
    browser: true,
    'jest/globals': true,
  },
  extends: [
    'airbnb-typescript',
  ],
  globals: {
    Atomics: 'readonly',
    SharedArrayBuffer: 'readonly',
  },
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: 'module',
  },
  plugins: [
    '@typescript-eslint',
    'jest',
  ],
  rules: {
    'no-bitwise': 'off',
    'no-underscore-dangle': 'off',
    'no-plusplus': 'off',
    'max-len': ['error', { code: 160 }],
    'import/export': 'off',
    'prefer-destructuring': 'off',
    'no-multi-assign': 'off',
    'no-param-reassign': 'off',
    'func-names': 'off',
    'lines-between-class-members': 'off',
  },
  ignorePatterns: [
    'dist/',
    'tests/',
    'node_modules/',
  ],
};
