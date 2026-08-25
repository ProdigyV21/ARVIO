package io.sentry.android.replay.util;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class h {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ h[] $VALUES;
    public static final h SOC_MODEL = new h("SOC_MODEL", 0);
    public static final h SOC_MANUFACTURER = new h("SOC_MANUFACTURER", 1);

    private static final /* synthetic */ h[] $values() {
        return new h[]{SOC_MODEL, SOC_MANUFACTURER};
    }

    static {
        h[] hVarArr$values = $values();
        $VALUES = hVarArr$values;
        $ENTRIES = new g7.b(hVarArr$values);
    }

    private h(String str, int i10) {
    }

    public static g7.a<h> getEntries() {
        return $ENTRIES;
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) $VALUES.clone();
    }
}
