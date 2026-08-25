package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/CategoryIcon;", "", "<init>", "(Ljava/lang/String;I)V", "Favorite", "Recent", "All", "Grid", "Sport", "Movie", "News", "Kids", "Docs", "Music", "Lock", "Country", "SubEntry", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CategoryIcon {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CategoryIcon[] $VALUES;
    public static final CategoryIcon Favorite = new CategoryIcon("Favorite", 0);
    public static final CategoryIcon Recent = new CategoryIcon("Recent", 1);
    public static final CategoryIcon All = new CategoryIcon("All", 2);
    public static final CategoryIcon Grid = new CategoryIcon("Grid", 3);
    public static final CategoryIcon Sport = new CategoryIcon("Sport", 4);
    public static final CategoryIcon Movie = new CategoryIcon("Movie", 5);
    public static final CategoryIcon News = new CategoryIcon("News", 6);
    public static final CategoryIcon Kids = new CategoryIcon("Kids", 7);
    public static final CategoryIcon Docs = new CategoryIcon("Docs", 8);
    public static final CategoryIcon Music = new CategoryIcon("Music", 9);
    public static final CategoryIcon Lock = new CategoryIcon("Lock", 10);
    public static final CategoryIcon Country = new CategoryIcon("Country", 11);
    public static final CategoryIcon SubEntry = new CategoryIcon("SubEntry", 12);

    private static final /* synthetic */ CategoryIcon[] $values() {
        return new CategoryIcon[]{Favorite, Recent, All, Grid, Sport, Movie, News, Kids, Docs, Music, Lock, Country, SubEntry};
    }

    static {
        CategoryIcon[] categoryIconArr$values = $values();
        $VALUES = categoryIconArr$values;
        $ENTRIES = new g7.b(categoryIconArr$values);
    }

    private CategoryIcon(String str, int i10) {
    }

    public static g7.a<CategoryIcon> getEntries() {
        return $ENTRIES;
    }

    public static CategoryIcon valueOf(String str) {
        return (CategoryIcon) Enum.valueOf(CategoryIcon.class, str);
    }

    public static CategoryIcon[] values() {
        return (CategoryIcon[]) $VALUES.clone();
    }
}
