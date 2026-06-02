# Local Environment Setup Guide

This guide walks you through setting up your local machine to build, run, and test the ARVIO applications.

---

## 1. System Prerequisites

Choose your target platforms:
- **Android development:** Supported on Windows, macOS, and Linux.
- **iOS development:** Requires macOS and Xcode.

---

## 2. Setting up Java Development Kit (JDK 17)

ARVIO requires **JDK 17** for Gradle compiles.

### Install on macOS (via Homebrew)
```bash
brew install openjdk@17
```
Configure your shell profile (e.g. `~/.zshrc` or `~/.bash_profile`):
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH
```

### Install on Windows
1. Download Zulu JDK 17 MSI installer from [Azul Downloads](https://www.azul.com/downloads/).
2. Run the installer and ensure "Add to PATH" and "Set JAVA_HOME" options are checked.

---

## 3. Setting up Android SDK & Android Studio

1. Download and install **Android Studio** (Jellyfish 2023.3.1 or newer) from [developer.android.com](https://developer.android.com/studio).
2. Open Android Studio and complete the Setup Wizard to install the default SDK components.
3. Open **SDK Manager** (Tools > SDK Manager):
   - Under **SDK Platforms**, check **Android 15 (VanillaIceCream / API Level 35)**.
   - Under **SDK Tools**, ensure the following are installed:
     - Android SDK Build-Tools (matching API 35)
     - Android SDK Command-line Tools (latest)
     - Android Emulator
4. Set up environment variables:
   - **macOS:** Add to `~/.zshrc`:
     ```bash
     export ANDROID_HOME=$HOME/Library/Android/sdk
     export PATH=$PATH:$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools
     ```
   - **Windows:** Add user environment variable `ANDROID_HOME` pointing to `C:\Users\YOUR_USERNAME\AppData\Local\Android\Sdk`. Add `%ANDROID_HOME%\platform-tools` and `%ANDROID_HOME%\emulator` to your system `Path`.

---

## 4. Setting up iOS Tools (macOS Only)

To build the native SwiftUI shell located in `iosApp/`, you need Xcode and XcodeGen.

1. Install **Xcode** (15.0 or newer) from the Mac App Store.
2. Open Xcode to accept the license agreement and install required platform tools.
3. Install **XcodeGen** via Homebrew:
   ```bash
   brew install xcodegen
   ```
4. Verify XcodeGen installation:
   ```bash
   xcodegen --version
   ```

---

## 5. Setting up Backend Tools (Supabase & Node)

If you plan to run backend proxy functions or TV pairing authentication locally, set up the Supabase tools.

1. Install **Node.js** (v18 or newer).
2. Install **Supabase CLI** via Homebrew or NPM:
   - **macOS:**
     ```bash
     brew install supabase/tap/supabase
     ```
   - **Windows/Linux (NPM):**
     ```bash
     npm install -g supabase
     ```
3. Verify installation:
   ```bash
   supabase --version
   ```

---

## 6. Verification Checklist

To confirm your system is fully configured, check that the following commands succeed:

```bash
java -version           # Should output OpenJDK runtime version "17.x.x"
adb --version           # Should show Android Debug Bridge version
xcodegen --version      # Should show version 2.40.0+ (iOS developers only)
node -v                 # Should show version 18+
```

Once confirmed, follow the [docs/development.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/development.md) guide to compile and run the application.

---

## 📖 Documentation Navigation

- [README.md](file:///Users/durgaprasadml/Documents/ARVIO/README.md) - Main repository overview.
- [CONTRIBUTING.md](file:///Users/durgaprasadml/Documents/ARVIO/CONTRIBUTING.md) - Guidelines for contributing code.
- [CODE_OF_CONDUCT.md](file:///Users/durgaprasadml/Documents/ARVIO/CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/architecture.md) - System architecture and dependency dataflows.
- [docs/setup.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/setup.md) - Environment installation checklist (this document).
- [docs/development.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/development.md) - Development commands and workflows.
- [docs/configuration.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/configuration.md) - App parameters and credentials reference.
- [docs/api.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/deployment.md) - CI/CD pipeline automation and TestFlight uploads.
- [docs/troubleshooting.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/ios-testflight.md) - iOS App Store/TestFlight packaging instructions.
