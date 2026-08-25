package com.arflix.tv.ui.screens.details;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/details/FocusSection;", "", "<init>", "(Ljava/lang/String;I)V", "BUTTONS", "EPISODES", "SEASONS", "RATINGS", "CAST", "REVIEWS", "SIMILAR", "COLLECTION", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class FocusSection {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ FocusSection[] $VALUES;
    public static final FocusSection BUTTONS = new FocusSection("BUTTONS", 0);
    public static final FocusSection EPISODES = new FocusSection("EPISODES", 1);
    public static final FocusSection SEASONS = new FocusSection("SEASONS", 2);
    public static final FocusSection RATINGS = new FocusSection("RATINGS", 3);
    public static final FocusSection CAST = new FocusSection("CAST", 4);
    public static final FocusSection REVIEWS = new FocusSection("REVIEWS", 5);
    public static final FocusSection SIMILAR = new FocusSection("SIMILAR", 6);
    public static final FocusSection COLLECTION = new FocusSection("COLLECTION", 7);

    private static final /* synthetic */ FocusSection[] $values() {
        return new FocusSection[]{BUTTONS, EPISODES, SEASONS, RATINGS, CAST, REVIEWS, SIMILAR, COLLECTION};
    }

    static {
        FocusSection[] focusSectionArr$values = $values();
        $VALUES = focusSectionArr$values;
        $ENTRIES = new g7.b(focusSectionArr$values);
    }

    private FocusSection(String str, int i10) {
    }

    public static g7.a<FocusSection> getEntries() {
        return $ENTRIES;
    }

    public static FocusSection valueOf(String str) {
        return (FocusSection) Enum.valueOf(FocusSection.class, str);
    }

    public static FocusSection[] values() {
        return (FocusSection[]) $VALUES.clone();
    }
}
