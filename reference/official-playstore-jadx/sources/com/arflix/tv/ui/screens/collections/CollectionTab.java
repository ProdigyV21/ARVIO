package com.arflix.tv.ui.screens.collections;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/screens/collections/CollectionTab;", "", "<init>", "(Ljava/lang/String;I)V", "MOVIES", "SERIES", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CollectionTab {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CollectionTab[] $VALUES;
    public static final CollectionTab MOVIES = new CollectionTab("MOVIES", 0);
    public static final CollectionTab SERIES = new CollectionTab("SERIES", 1);

    private static final /* synthetic */ CollectionTab[] $values() {
        return new CollectionTab[]{MOVIES, SERIES};
    }

    static {
        CollectionTab[] collectionTabArr$values = $values();
        $VALUES = collectionTabArr$values;
        $ENTRIES = new g7.b(collectionTabArr$values);
    }

    private CollectionTab(String str, int i10) {
    }

    public static g7.a<CollectionTab> getEntries() {
        return $ENTRIES;
    }

    public static CollectionTab valueOf(String str) {
        return (CollectionTab) Enum.valueOf(CollectionTab.class, str);
    }

    public static CollectionTab[] values() {
        return (CollectionTab[]) $VALUES.clone();
    }
}
