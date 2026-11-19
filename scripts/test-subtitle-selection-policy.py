#!/usr/bin/env python3
"""Run the pure subtitle policy's JUnit tests without an Android SDK.

Bootstrap the wrapper with `bash ./gradlew --version` once first. This uses the
wrapper distribution's Kotlin compiler (not the app's Kotlin/Android toolchain),
so it does NOT replace :app:testSideloadDebugUnitTest or a device playback test.
The production Subtitle declaration is extracted verbatim to avoid pulling the
unrelated Compose-annotated models into this isolated compiler invocation.
"""
import os
from pathlib import Path
import re
import subprocess
import tempfile

ROOT = Path(__file__).resolve().parents[1]
GRADLE_HOME = Path(os.environ.get("GRADLE_USER_HOME", Path.home() / ".gradle"))
wrapper = (ROOT / "gradle/wrapper/gradle-wrapper.properties").read_text()
version = re.search(r"gradle-([\d.]+)-bin\.zip", wrapper).group(1)
libs = sorted((GRADLE_HOME / "wrapper/dists" / f"gradle-{version}-bin").glob(f"*/gradle-{version}/lib"))
if not libs:
    raise SystemExit("Run `bash ./gradlew --version` first to download the wrapper distribution.")
lib = libs[0]
compiler_classpath = str(lib / "*")
classpath = os.pathsep.join(str(p) for pattern in ("kotlin-stdlib-*.jar", "junit-4*.jar", "hamcrest-core-*.jar") for p in lib.glob(pattern))
models = (ROOT / "app/src/main/kotlin/com/arflix/tv/data/model/Models.kt").read_text()
subtitle = re.search(r"^data class Subtitle\([\s\S]*?^\) : Serializable", models, re.MULTILINE)
if not subtitle:
    raise SystemExit("Cannot isolate the production Subtitle declaration; use the Android test task.")
main = ROOT / "app/src/main/kotlin/com/arflix/tv"
tests = ROOT / "app/src/test/kotlin/com/arflix/tv/ui/screens/player"
with tempfile.TemporaryDirectory(prefix="arvio-subtitle-policy-") as directory:
    temp = Path(directory)
    model = temp / "Subtitle.kt"
    model.write_text("package com.arflix.tv.data.model\nimport java.io.Serializable\n" + subtitle.group() + "\n")
    classes = temp / "classes"
    subprocess.run([
        "java", "-Xmx768m", "-XX:ActiveProcessorCount=2", "-cp", compiler_classpath,
        "org.jetbrains.kotlin.cli.jvm.K2JVMCompiler", "-no-stdlib", "-no-reflect",
        "-classpath", classpath, "-d", str(classes), str(model),
        str(main / "util/LanguageCodes.kt"),
        str(main / "ui/screens/player/subtitles/SubtitleAutoSync.kt"),
        str(main / "ui/screens/player/SubtitleSelectionPolicy.kt"),
        str(tests / "SubtitleSelectionPolicyTest.kt"),
    ], check=True)
    subprocess.run([
        "java", "-Xmx512m", "-XX:ActiveProcessorCount=2", "-cp", str(classes) + os.pathsep + classpath,
        "org.junit.runner.JUnitCore", "com.arflix.tv.ui.screens.player.SubtitleSelectionPolicyTest",
    ], check=True)
