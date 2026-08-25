package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/data/repository/TraktOutboxAction;", "", "<init>", "(Ljava/lang/String;I)V", "MARK_MOVIE_WATCHED", "MARK_EPISODE_WATCHED", "REMOVE_PLAYBACK_ITEM", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TraktOutboxAction {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ TraktOutboxAction[] $VALUES;
    public static final TraktOutboxAction MARK_MOVIE_WATCHED = new TraktOutboxAction("MARK_MOVIE_WATCHED", 0);
    public static final TraktOutboxAction MARK_EPISODE_WATCHED = new TraktOutboxAction("MARK_EPISODE_WATCHED", 1);
    public static final TraktOutboxAction REMOVE_PLAYBACK_ITEM = new TraktOutboxAction("REMOVE_PLAYBACK_ITEM", 2);

    private static final /* synthetic */ TraktOutboxAction[] $values() {
        return new TraktOutboxAction[]{MARK_MOVIE_WATCHED, MARK_EPISODE_WATCHED, REMOVE_PLAYBACK_ITEM};
    }

    static {
        TraktOutboxAction[] traktOutboxActionArr$values = $values();
        $VALUES = traktOutboxActionArr$values;
        $ENTRIES = new g7.b(traktOutboxActionArr$values);
    }

    private TraktOutboxAction(String str, int i10) {
    }

    public static g7.a<TraktOutboxAction> getEntries() {
        return $ENTRIES;
    }

    public static TraktOutboxAction valueOf(String str) {
        return (TraktOutboxAction) Enum.valueOf(TraktOutboxAction.class, str);
    }

    public static TraktOutboxAction[] values() {
        return (TraktOutboxAction[]) $VALUES.clone();
    }
}
