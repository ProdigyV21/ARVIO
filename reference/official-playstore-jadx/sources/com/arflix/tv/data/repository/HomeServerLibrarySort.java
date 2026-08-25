package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "", "<init>", "(Ljava/lang/String;I)V", "RECENTLY_ADDED", "TITLE", "RATING", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerLibrarySort {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ HomeServerLibrarySort[] $VALUES;
    public static final HomeServerLibrarySort RECENTLY_ADDED = new HomeServerLibrarySort("RECENTLY_ADDED", 0);
    public static final HomeServerLibrarySort TITLE = new HomeServerLibrarySort("TITLE", 1);
    public static final HomeServerLibrarySort RATING = new HomeServerLibrarySort("RATING", 2);

    private static final /* synthetic */ HomeServerLibrarySort[] $values() {
        return new HomeServerLibrarySort[]{RECENTLY_ADDED, TITLE, RATING};
    }

    static {
        HomeServerLibrarySort[] homeServerLibrarySortArr$values = $values();
        $VALUES = homeServerLibrarySortArr$values;
        $ENTRIES = new g7.b(homeServerLibrarySortArr$values);
    }

    private HomeServerLibrarySort(String str, int i10) {
    }

    public static g7.a<HomeServerLibrarySort> getEntries() {
        return $ENTRIES;
    }

    public static HomeServerLibrarySort valueOf(String str) {
        return (HomeServerLibrarySort) Enum.valueOf(HomeServerLibrarySort.class, str);
    }

    public static HomeServerLibrarySort[] values() {
        return (HomeServerLibrarySort[]) $VALUES.clone();
    }
}
