# Contributing to Arvio

Thank you for your interest in contributing to Arvio. This guide explains how to set up the project, develop across its components, validate changes, and submit pull requests.

Arvio is a multi-platform media browser and player ecosystem comprising an Android application (TV, phone, and tablet), a web application, serverless authentication services, and backend workers.

---

## Table of Contents

- [Project Architecture](#project-architecture)
- [Prerequisites and Setup](#prerequisites-and-setup)
- [Local Secrets and Configuration](#local-secrets-and-configuration)
- [Development and Build Commands](#development-and-build-commands)
  - [Android Application (`/` and `app/`)](#android-application--and-app)
  - [Web Application (`web/`)](#web-application-web)
  - [Cloudflare Resolver Worker (`resolver-worker/`)](#cloudflare-resolver-worker-resolver-worker)
  - [Netlify Auth Backend (`netlify-auth-site/`)](#netlify-auth-backend-netlify-auth-site)
- [Code Quality and Validation](#code-quality-and-validation)
  - [Android Validation](#android-validation)
  - [Web and Backend Validation](#web-and-backend-validation)
  - [Continuous Integration Expectations](#continuous-integration-expectations)
- [Development Guidelines](#development-guidelines)
  - [Engineering Standards](#engineering-standards)
  - [AI-Assisted Contributions](#ai-assisted-contributions)
  - [Content and Source Policy](#content-and-source-policy)
  - [Git and Commit Guidance](#git-and-commit-guidance)
- [Submitting a Pull Request](#submitting-a-pull-request)
  - [Pull Request Checklist](#pull-request-checklist)
- [Security and Privacy](#security-and-privacy)
- [License](#license)

---

## Project Architecture

Arvio is maintained as a monorepo. Source code and support components are organized into the following primary directories:

```text
ARVIO/
├── app/                      # Main Android application (Kotlin, Jetpack Compose for TV / Mobile)
├── benchmark/                # Baseline profile benchmarking module for Android
├── config/                   # Static analysis rules (Detekt configuration and baseline)
├── docs/                     # Architecture specifications and technical feature notes
├── iosApp/                   # iOS app configuration (XcodeGen) and source
├── netlify-arvio-tv-site/    # Static landing site hosted on Netlify
├── netlify-auth-site/        # Serverless authentication functions and database migrations (Netlify)
├── resolver-worker/          # Cloudflare Worker for stream resolution (TypeScript)
├── supabase/                 # Supabase configuration, schema migrations, and edge proxy functions
├── web/                      # Browser application for desktop, iPad, and TV browsers (Next.js, TypeScript)
├── build.gradle.kts          # Top-level Gradle build configuration
├── settings.gradle.kts       # Gradle module inclusion settings
└── README.md                 # Primary project documentation
```

Keep changes focused within the specific module relevant to your feature or fix.

---

## Prerequisites and Setup

Required tooling depends on the repository component you are modifying:

### Android Application (`app/`)
- **JDK**: Java Development Kit 17 (Java 17 target)
- **Android SDK**: Compile SDK 36, Target SDK 36, Minimum SDK 23 (SDK 35+ command-line tools)
- **Build Tooling**: Tracked Gradle wrapper (`./gradlew` on Linux/macOS, `gradlew.bat` on Windows)

### Web and Serverless Modules (`web/`, `resolver-worker/`, `netlify-auth-site/`)
- **Node.js**: Node.js runtime (v18+ recommended)
- **Package Manager**: `npm` (tracked via `package-lock.json` in each module directory)

---

## Local Secrets and Configuration

The Android app and web services rely on local configuration files for cloud sync, authentication, and API proxy routing.

1. **Android Configuration**:
   Copy `secrets.defaults.properties` to create `secrets.properties` in the repository root:
   ```bash
   cp secrets.defaults.properties secrets.properties
   ```
   `secrets.properties` is ignored by Git and holds placeholders for `SUPABASE_URL`, `SUPABASE_ANON_KEY`, `TMDB_API_KEY`, `TRAKT_CLIENT_ID`, and `TRAKT_CLIENT_SECRET`.

2. **Keystore Configuration**:
   For signed release builds, copy `keystore.properties.template`:
   ```bash
   cp keystore.properties.template keystore.properties
   ```

For detailed credential proxy handling, refer to [README.md](README.md#local-configuration).

---

## Development and Build Commands

### Android Application (`/` and `app/`)

Execute Gradle commands using the project wrapper:

- **Build Debug Variants**:
  ```bash
  # Play Store build (self-update disabled)
  ./gradlew :app:assemblePlayDebug

  # Sideload build (self-update and plugin engine enabled)
  ./gradlew :app:assembleSideloadDebug
  ```

- **Install to Connected Device or Emulator**:
  ```bash
  ./gradlew :app:installPlayDebug
  ./gradlew :app:installSideloadDebug
  ```

- **Install over Network ADB**:
  ```bash
  adb connect <device-ip>:5555
  adb install -r app/build/outputs/apk/sideload/debug/app-sideload-debug.apk
  ```

- **Release Build Verification**:
  ```bash
  ./gradlew :app:compilePlayDebugKotlin
  ./gradlew :app:assemblePlayRelease
  ./gradlew :app:assembleSideloadRelease
  ```

### Web Application (`web/`)

Navigate to `web/` to run Node commands:

```bash
cd web
npm install
npm run dev      # Start development server
npm run build    # Verify production build
npm run start    # Start production server
```

Copy `web/.env.example` to `web/.env.local` for local web environment configuration.

### Cloudflare Resolver Worker (`resolver-worker/`)

Navigate to `resolver-worker/`:

```bash
cd resolver-worker
npm install
npm run dev        # Run local Wrangler worker dev server
npm run typecheck  # Run TypeScript type check (tsc --noEmit)
```

### Netlify Auth Backend (`netlify-auth-site/`)

Navigate to `netlify-auth-site/`:

```bash
cd netlify-auth-site
npm install
npm run dev   # Run Netlify CLI development server
npm test      # Run backend test suite (node --test)
```

---

## Code Quality and Validation

Run the relevant local validation checks prior to submitting a pull request.

### Android Validation

- **Unit Tests**:
  ```bash
  ./gradlew :app:testPlayDebugUnitTest
  ```
  Runs the Play debug unit test suite.

- **Detekt Static Analysis**:
  ```bash
  ./gradlew detekt
  ```
  Runs Kotlin static analysis according to `config/detekt/detekt.yml`. Do not regenerate or alter `config/detekt/baseline.xml` merely to pass newly introduced lint failures.

### Web and Backend Validation

- **Web App Linting**:
  ```bash
  cd web && npm run lint
  ```
  Runs ESLint with `eslint-config-next`.

- **Worker Type-Checking**:
  ```bash
  cd resolver-worker && npm run typecheck
  ```
  Verifies TypeScript types across worker handlers.

- **Auth Backend Tests**:
  ```bash
  cd netlify-auth-site && npm test
  ```
  Executes Node.js backend integration tests.

### Continuous Integration Expectations

Automated build checks run on pull requests targeting `main` via [.github/workflows/build-check.yml](.github/workflows/build-check.yml). CI compiles the sideload debug variant (`./gradlew assembleSideloadDebug --no-daemon`) using JDK 17.

Running `./gradlew assembleSideloadDebug` locally reproduces the primary build check performed by CI.

---

## Development Guidelines

### Engineering Standards

Contributions should reflect high engineering quality and project alignment:

- **Understand the Code Path**: Trace and inspect existing execution flows before making changes.
- **Focused Changes**: Keep pull requests focused on a single problem or feature. Avoid unrelated refactoring.
- **Follow Existing Patterns**: Maintain consistency with Arvio's established architectural patterns, naming conventions, and state management.
- **Simplicity**: Prefer clear, maintainable implementations over unnecessary abstractions or speculative design.
- **Clean Code**: Remove unused imports, dead code, placeholder logic, debug prints, and temporary comments.
- **Test Coverage**: Add or update tests when behavior changes and meaningful automated coverage is practical.
- **Edge Cases**: Consider relevant failure and edge cases for the code path being modified, such as nullability, network timeouts, offline states, and playback failure paths.
- **Documentation**: Update user documentation or code comments if setup steps, APIs, or user-facing behavior changes.

### AI-Assisted Contributions

AI tools and code assistants are permitted. However, contributors remain fully accountable for every line of code, configuration, and documentation submitted.

- **Responsibility**: Contributors must understand, verify, and be capable of explaining all submitted changes and technical decisions.
- **Equal Review Standard**: AI-assisted contributions are evaluated against the same engineering, security, maintainability, and testing standards as manual contributions.
- **Quality Scrutiny**: Low-quality, unreviewed, or unexplained generated output—such as fabricated APIs, nonexistent configuration fields, superficial tests, speculative abstractions, or excessive restatements of code—may be rejected during review.

### Content and Source Policy

Arvio is a media browser and player for user-configured sources. The repository does not host, sell, or distribute third-party media content, IPTV credentials, debrid keys, or copyrighted playlists.

Do not submit code, links, or test fixtures containing copyrighted media streams, unauthorized access credentials, or private API keys. Refer to the [Content And Source Policy in README.md](README.md#content-and-source-policy) for full details.

### Git and Commit Guidance

- **Branching**: Use concise, descriptive branch names that summarize the change (for example, `fix-epg-parser-nullability` or `add-subtitle-styling`).
- **Commit Messages**: Write concise, imperative commit messages that explain the intent of the change (for example, `Fix null crash in IPTV category parser`). Keep commits logically grouped.

---

## Submitting a Pull Request

1. **Fork and Clone**: Fork the repository on GitHub and clone your fork locally:
   ```bash
   git clone <your-fork-url>
   cd ARVIO
   ```
2. **Create a Branch**: Create a branch off `main` for your work.
3. **Implement and Validate**: Implement your changes and run the applicable validation commands ([Code Quality and Validation](#code-quality-and-validation)).
4. **Submit Pull Request**: Push your branch to your fork and open a pull request targeting `main`. Describe the problem being addressed, summarize the implementation decisions, and outline how the change was tested. Reference related issues and include screenshots or recordings for UI changes.

### Pull Request Checklist

Before submitting a pull request, confirm that:

- [ ] The code compiles cleanly (`./gradlew assembleSideloadDebug` or `npm run build`).
- [ ] Relevant unit tests and linting checks pass locally.
- [ ] Changes do not contain hardcoded API keys, private credentials, or copyrighted media URLs.
- [ ] Screenshots or screen recordings are included for significant UI modifications.
- [ ] Related documentation is updated if setup or user-facing features changed.

---

## Security and Privacy

If you discover a potential security vulnerability, do not open a public GitHub issue. Disclose security vulnerabilities privately to the maintainers.

See [PRIVACY.md](PRIVACY.md) for data handling and cloud sync privacy details.

---

## License

All contributions to Arvio are licensed under the project's [Apache License 2.0](LICENSE).
