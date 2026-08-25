package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/Quality;", "", "label", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "SD", "HD", "FHD", "K4", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Quality {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ Quality[] $VALUES;
    private final String label;
    public static final Quality SD = new Quality("SD", 0, "SD");
    public static final Quality HD = new Quality("HD", 1, "HD");
    public static final Quality FHD = new Quality("FHD", 2, "FHD");
    public static final Quality K4 = new Quality("K4", 3, "4K");

    private static final /* synthetic */ Quality[] $values() {
        return new Quality[]{SD, HD, FHD, K4};
    }

    static {
        Quality[] qualityArr$values = $values();
        $VALUES = qualityArr$values;
        $ENTRIES = new g7.b(qualityArr$values);
    }

    private Quality(String str, int i10, String str2) {
        this.label = str2;
    }

    public static g7.a<Quality> getEntries() {
        return $ENTRIES;
    }

    public static Quality valueOf(String str) {
        return (Quality) Enum.valueOf(Quality.class, str);
    }

    public static Quality[] values() {
        return (Quality[]) $VALUES.clone();
    }

    public final String getLabel() {
        return this.label;
    }
}
