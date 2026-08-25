package io.github.jan.supabase;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lio/github/jan/supabase/PlatformTarget;", "", "(Ljava/lang/String;I)V", "JVM", "ANDROID", "JS", "WASM", "IOS", "WINDOWS", "MACOS", "TVOS", "WATCHOS", "LINUX", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PlatformTarget {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ PlatformTarget[] $VALUES;
    public static final PlatformTarget JVM = new PlatformTarget("JVM", 0);
    public static final PlatformTarget ANDROID = new PlatformTarget("ANDROID", 1);
    public static final PlatformTarget JS = new PlatformTarget("JS", 2);
    public static final PlatformTarget WASM = new PlatformTarget("WASM", 3);
    public static final PlatformTarget IOS = new PlatformTarget("IOS", 4);
    public static final PlatformTarget WINDOWS = new PlatformTarget("WINDOWS", 5);
    public static final PlatformTarget MACOS = new PlatformTarget("MACOS", 6);
    public static final PlatformTarget TVOS = new PlatformTarget("TVOS", 7);
    public static final PlatformTarget WATCHOS = new PlatformTarget("WATCHOS", 8);
    public static final PlatformTarget LINUX = new PlatformTarget("LINUX", 9);

    private static final /* synthetic */ PlatformTarget[] $values() {
        return new PlatformTarget[]{JVM, ANDROID, JS, WASM, IOS, WINDOWS, MACOS, TVOS, WATCHOS, LINUX};
    }

    static {
        PlatformTarget[] platformTargetArr$values = $values();
        $VALUES = platformTargetArr$values;
        $ENTRIES = new b(platformTargetArr$values);
    }

    private PlatformTarget(String str, int i10) {
    }

    public static a<PlatformTarget> getEntries() {
        return $ENTRIES;
    }

    public static PlatformTarget valueOf(String str) {
        return (PlatformTarget) Enum.valueOf(PlatformTarget.class, str);
    }

    public static PlatformTarget[] values() {
        return (PlatformTarget[]) $VALUES.clone();
    }
}
