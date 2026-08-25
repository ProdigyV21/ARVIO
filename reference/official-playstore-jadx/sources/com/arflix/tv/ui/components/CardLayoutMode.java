package com.arflix.tv.ui.components;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/components/CardLayoutMode;", "", "<init>", "(Ljava/lang/String;I)V", "LANDSCAPE", "POSTER", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CardLayoutMode {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CardLayoutMode[] $VALUES;
    public static final CardLayoutMode LANDSCAPE = new CardLayoutMode("LANDSCAPE", 0);
    public static final CardLayoutMode POSTER = new CardLayoutMode("POSTER", 1);

    private static final /* synthetic */ CardLayoutMode[] $values() {
        return new CardLayoutMode[]{LANDSCAPE, POSTER};
    }

    static {
        CardLayoutMode[] cardLayoutModeArr$values = $values();
        $VALUES = cardLayoutModeArr$values;
        $ENTRIES = new g7.b(cardLayoutModeArr$values);
    }

    private CardLayoutMode(String str, int i10) {
    }

    public static g7.a<CardLayoutMode> getEntries() {
        return $ENTRIES;
    }

    public static CardLayoutMode valueOf(String str) {
        return (CardLayoutMode) Enum.valueOf(CardLayoutMode.class, str);
    }

    public static CardLayoutMode[] values() {
        return (CardLayoutMode[]) $VALUES.clone();
    }
}
