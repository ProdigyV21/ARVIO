module.exports = {
  roots: [
    '<rootDir>/tests',
  ],
  transform: {
    '^.+\\.ts$': 'ts-jest',
  },
  testPathIgnorePatterns: ['/dist/', '/node_modules/'],
  collectCoverage: true,
};
