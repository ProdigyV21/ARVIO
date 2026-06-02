# Contributing to ARVIO

Thank you for your interest in contributing to ARVIO! We welcome bug fixes, documentation, feature requests, and code improvements. 

---

## 1. Prerequisites

Before you start contributing, ensure you have the following installed:
- **Java Development Kit (JDK):** Version 17 is required. We recommend Azul Zulu 17 or Eclipse Temurin.
- **Android SDK:** Command Line tools or Android Studio with SDK API Level 35.
- **Node.js:** Node.js (v18+) is required for running code-signing scripts and executing GitHub actions.
- **Git:** Installed and configured with your name and email.

---

## 2. Local Environment Setup

1. Fork the ARVIO repository to your own GitHub account.
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/ARVIO.git
   cd ARVIO
   ```
3. Set the upstream remote to point to the main repository:
   ```bash
   git remote add upstream https://github.com/ProdigyV21/ARVIO.git
   ```
4. Verify your remote configuration:
   ```bash
   git remote -v
   ```

---

## 3. Installation Steps

1. Copy the default secrets file to create your local `secrets.properties`:
   ```bash
   cp secrets.defaults.properties secrets.properties
   ```
2. (Optional) For signed release builds, copy the keystore template:
   ```bash
   cp keystore.properties.template keystore.properties
   ```
3. Sync Gradle dependencies:
   - On Android Studio, click **File > Sync Project with Gradle Files**.
   - Or run from command line:
     ```bash
     ./gradlew --sync-dev
     ```

---

## 4. Running Tests

Testing is required for all pull requests.

### Running Local JUnit Unit Tests
Run unit tests for the main app flavor from the command line:
```bash
./gradlew :app:testPlayDebugUnitTest
```

### Running Instrumented/UI Tests (Requires Emulator or Connected Device)
```bash
./gradlew :app:connectedPlayDebugAndroidTest
```

---

## 5. Development Workflow

We use a feature branch workflow. Here is an example of creating a new feature:

1. Sync your local `main` branch with the upstream repository:
   ```bash
   git checkout main
   git pull upstream main
   ```
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Write your code and run formatting checks.
4. Stage and commit your changes:
   ```bash
   git add .
   git commit -m "feat: add support for new media types"
   ```
5. Push to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```
6. Open a Pull Request from your branch to ARVIO's `main` branch.

---

## 6. Branch Naming Conventions

Branches should follow these naming patterns:
- **Features:** `feature/short-description`
- **Bug Fixes:** `bugfix/short-description`
- **Documentation:** `docs/short-description`
- **Performance Updates:** `perf/short-description`
- **Refactoring:** `refactor/short-description`

---

## 7. Commit Message Conventions

We follow the Conventional Commits specification. Commit messages should be structured as follows:

`<type>(<scope>): <description>`

### Allowed Types
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation changes
- `style`: Formatting, missing semi-colons, etc. (no production code changes)
- `refactor`: Refactoring production code
- `perf`: Code changes that improve performance
- `test`: Adding missing tests or correcting existing tests
- `chore`: Build tasks, package manager configs, etc.

### Examples
- `feat(player): add subtitle offset settings`
- `fix(iptv): resolve race condition in category EPG loading`
- `docs(readme): update build variant documentation`

---

## 8. Pull Request Process

1. Ensure all local unit tests pass before submitting.
2. Update the [CHANGELOG.md](file:///Users/durgaprasadml/Documents/ARVIO/CHANGELOG.md) with your changes under the `[Unreleased]` section.
3. Open a Pull Request on GitHub.
4. Link the PR to the relevant issue in the description (e.g. `Closes #123`).
5. Ensure the CI build check passes. If it fails, fix the issues in your branch and push updates.

---

## 9. Code Review Expectations

All PRs must be reviewed and approved by at least one maintainer. Reviewers will check for:
- Maintainability, readability, and naming conventions.
- Test coverage for new logic.
- Separation of concerns and proper architectural patterns.
- Absence of credentials or hardcoded keys.

---

## 10. Linting and Formatting Requirements

We use **Detekt** for static analysis of Kotlin source files. To run Detekt checks locally, execute:
```bash
./gradlew detekt
```
Ensure your code does not introduce new lint violations. If necessary, you can run `./gradlew detektBaseline` to baseline current issues or correct your code formatting.

---

## 11. Documentation Contribution Standards

- Every new feature should be accompanied by relevant documentation in `docs/` or an update to [README.md](file:///Users/durgaprasadml/Documents/ARVIO/README.md).
- Keep explanations clear and concise.
- Use relative Markdown paths with absolute links for repository resources to maintain interlinking consistency.
- Maintain the navigation index at the bottom of all refactored markdown documents.

---

## 12. Issue Reporting Guidelines

If you find a bug, please open an issue containing:
- A descriptive title.
- Steps to reproduce the issue.
- Expected vs actual behavior.
- App version/build variant and device type (Android TV model, Fire TV, Mobile, etc.).
- Relevant logcat traces or crash logs.

---

## 13. First-Time Contributor Guide

If this is your first time contributing to an open-source project:
1. Search for issues labeled `good first issue`.
2. Do not hesitate to ask questions in the issue discussion thread if you need help.
3. Check the [docs/setup.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/setup.md) guide for help setting up your machine.

---

## 14. Debugging Tips

- **Logcat Logs:** Filter Logcat in Android Studio with `package:mine` or tag `ARVIO` to see output from [AppLogger.kt](file:///Users/durgaprasadml/Documents/ARVIO/app/src/main/kotlin/com/arflix/tv/util/AppLogger.kt).
- **Network Inspector:** Use the Network Profiler in Android Studio to monitor OkHttp requests routed through [OkHttpProvider.kt](file:///Users/durgaprasadml/Documents/ARVIO/app/src/main/kotlin/com/arflix/tv/network/OkHttpProvider.kt).
- **Supabase Logs:** If debugging cloud sync, check the Supabase CLI logs:
  ```bash
  supabase functions serve
  ```

---

## 15. Common Development Pitfalls

- **Committing Secrets:** Never commit `secrets.properties` or `keystore.properties` to version control. They are git-ignored by design.
- **Compose Recomposition Loops:** Be careful when using mutable states inside Compose columns/rows. Always check stability criteria and register stable types in `app/compose_stability_config.conf`.
- **D-Pad Focus Skips:** Ensure focus bounds are properly specified using focus modifiers for Android TV remotes to avoid trapping focus.

---

## 🛠 Recommended Tooling and Extensions

- **Android Studio (IDE):** JetBrains Kotlin plugin, Detekt IntelliJ plugin.
- **VS Code / Cursor (for Deno/Edge functions):** Deno Extension, Tailwind CSS IntelliSense (if editing netlify site).
- **Postman / Hoppscotch:** For querying Supabase edge function endpoints.

---

## 📖 Documentation Navigation

- [README.md](file:///Users/durgaprasadml/Documents/ARVIO/README.md) - Main repository overview.
- [CONTRIBUTING.md](file:///Users/durgaprasadml/Documents/ARVIO/CONTRIBUTING.md) - Guidelines for contributing code (this document).
- [CODE_OF_CONDUCT.md](file:///Users/durgaprasadml/Documents/ARVIO/CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/architecture.md) - System architecture and dependency dataflows.
- [docs/setup.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/setup.md) - Environment installation checklist.
- [docs/development.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/development.md) - Development commands and workflows.
- [docs/configuration.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/configuration.md) - App parameters and credentials reference.
- [docs/api.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/deployment.md) - CI/CD pipeline automation and TestFlight uploads.
- [docs/troubleshooting.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/ios-testflight.md) - iOS App Store/TestFlight packaging instructions.
