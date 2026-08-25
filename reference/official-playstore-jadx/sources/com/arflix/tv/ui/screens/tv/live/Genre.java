package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/Genre;", "", "<init>", "(Ljava/lang/String;I)V", "Sports", "Movies", "Series", "News", "Kids", "Music", "Docs", "General", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Genre {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ Genre[] $VALUES;
    public static final Genre Sports = new Genre("Sports", 0);
    public static final Genre Movies = new Genre("Movies", 1);
    public static final Genre Series = new Genre("Series", 2);
    public static final Genre News = new Genre("News", 3);
    public static final Genre Kids = new Genre("Kids", 4);
    public static final Genre Music = new Genre("Music", 5);
    public static final Genre Docs = new Genre("Docs", 6);
    public static final Genre General = new Genre("General", 7);

    private static final /* synthetic */ Genre[] $values() {
        return new Genre[]{Sports, Movies, Series, News, Kids, Music, Docs, General};
    }

    static {
        Genre[] genreArr$values = $values();
        $VALUES = genreArr$values;
        $ENTRIES = new g7.b(genreArr$values);
    }

    private Genre(String str, int i10) {
    }

    public static g7.a<Genre> getEntries() {
        return $ENTRIES;
    }

    public static Genre valueOf(String str) {
        return (Genre) Enum.valueOf(Genre.class, str);
    }

    public static Genre[] values() {
        return (Genre[]) $VALUES.clone();
    }
}
