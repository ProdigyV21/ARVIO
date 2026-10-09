# Cryptography JS
[![Travis CI](https://img.shields.io/travis/spalt08/cryptography)](https://travis-ci.com/spalt08/cryptography)
[![Coverage](https://img.shields.io/codecov/c/github/spalt08/cryptography?token=617017dc35344eb6b4637420457746c8)](https://codecov.io/gh/spalt08/cryptography)

High-performance cryptographic packages for JavaScript. Optimized for browsers.

## Packages & Documentation
* [[@cryptography/sha1](./packages/sha1)] SHA-1 implementation for JavaScript
* [[@cryptography/sha256](./packages/sha256)] SHA-256 implementation for JavaScript
* [[@cryptography/sha512](./packages/sha512)] SHA-512 implementation for JavaScript

Beta:
* [[@cryptography/aes](./packages/aes)] AES (IGE & CTR modes) implementation for JavaScript
* [[@cryptography/pbkdf2](./packages/pbkdf2)] pbkdf2

## Contribution

As this project is a monorepo it is extremely important to write correct commit messages and keep git history clean. All commits made in this repository are divided in two groups: core commits (those that are only related to core repository and are not related to any package) and package commits (those that are related to particular package). Examples of core commit contents: update this readme, update lerna configuration, update scripts in core package.json. Examples of package commits: update eslint configuration in @cryptography/eslint-config package, fix issues in @cryptography/sha256 package.

When writing commit message start with `[core]` prefix if it is a core commit and with `[@cryptography/package-name]` prefix if it is a package commit. After prefix write commit message as you usually do. For example,

- `[core] feat: git commits documentation to readme`
- `[core] feat: @cryptography/sha256 publishing script`
- `[@cryptography/eslint-config] fix: rules to work with prettier`
- `[@cryptography/sha256] feat: stream hashing`
