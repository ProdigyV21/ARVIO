package com.arflix.tv.ui.screens.search;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/arflix/tv/ui/screens/search/SortOption;", "", "label", "", "apiValue", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "getApiValue", "POPULAR", "TOP_RATED", "NEWEST", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SortOption {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ SortOption[] $VALUES;
    private final String apiValue;
    private final String label;
    public static final SortOption POPULAR = new SortOption("POPULAR", 0, "Popular", "popularity.desc");
    public static final SortOption TOP_RATED = new SortOption("TOP_RATED", 1, "Top Rated", "vote_average.desc");
    public static final SortOption NEWEST = new SortOption("NEWEST", 2, "Newest", "primary_release_date.desc");

    private static final /* synthetic */ SortOption[] $values() {
        return new SortOption[]{POPULAR, TOP_RATED, NEWEST};
    }

    static {
        SortOption[] sortOptionArr$values = $values();
        $VALUES = sortOptionArr$values;
        $ENTRIES = new g7.b(sortOptionArr$values);
    }

    private SortOption(String str, int i10, String str2, String str3) {
        this.label = str2;
        this.apiValue = str3;
    }

    public static g7.a<SortOption> getEntries() {
        return $ENTRIES;
    }

    public static SortOption valueOf(String str) {
        return (SortOption) Enum.valueOf(SortOption.class, str);
    }

    public static SortOption[] values() {
        return (SortOption[]) $VALUES.clone();
    }

    public final String getApiValue() {
        return this.apiValue;
    }

    public final String getLabel() {
        return this.label;
    }
}
