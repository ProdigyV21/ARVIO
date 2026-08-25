package com.arflix.tv.ui.components;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.BookmarkBorderKt;
import androidx.compose.material.icons.filled.BookmarkKt;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.ClearKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material.icons.filled.PlayArrowKt;
import androidx.compose.material.icons.filled.VisibilityKt;
import androidx.compose.material.icons.filled.VisibilityOffKt;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.ui.theme.ColorKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/components/ContextActions;", "", "<init>", "()V", BuildConfig.FLAVOR, "Lcom/arflix/tv/ui/components/ContextAction;", "getPlay", "()Lcom/arflix/tv/ui/components/ContextAction;", "selectSource", "getSelectSource", "markWatched", "getMarkWatched", "markUnwatched", "getMarkUnwatched", "addWatchlist", "getAddWatchlist", "removeWatchlist", "getRemoveWatchlist", "viewDetails", "getViewDetails", "markSeasonWatched", "getMarkSeasonWatched", "markSeasonUnwatched", "getMarkSeasonUnwatched", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ContextActions {
    public static final int $stable = 0;
    public static final ContextActions INSTANCE = new ContextActions();
    private static final ContextAction addWatchlist;
    private static final ContextAction markSeasonUnwatched;
    private static final ContextAction markSeasonWatched;
    private static final ContextAction markUnwatched;
    private static final ContextAction markWatched;
    private static final ContextAction play;
    private static final ContextAction removeWatchlist;
    private static final ContextAction selectSource;
    private static final ContextAction viewDetails;

    static {
        Icons icons = Icons.INSTANCE;
        play = new ContextAction(BuildConfig.FLAVOR, "Play", PlayArrowKt.getPlayArrow(icons.getDefault()), ColorKt.getPink(), null);
        selectSource = new ContextAction("sources", "Select Source", InfoKt.getInfo(icons.getDefault()), ColorKt.getTextPrimary(), null);
        markWatched = new ContextAction("mark_watched", "Mark as Watched", VisibilityKt.getVisibility(icons.getDefault()), androidx.compose.ui.graphics.ColorKt.Color(4280468830L), null);
        markUnwatched = new ContextAction("mark_unwatched", "Mark as Unwatched", VisibilityOffKt.getVisibilityOff(icons.getDefault()), ColorKt.getTextSecondary(), null);
        addWatchlist = new ContextAction("add_watchlist", "Add to Watchlist", BookmarkBorderKt.getBookmarkBorder(icons.getDefault()), ColorKt.getPink(), null);
        removeWatchlist = new ContextAction("remove_watchlist", "Remove from Watchlist", BookmarkKt.getBookmark(icons.getDefault()), ColorKt.getTextSecondary(), null);
        viewDetails = new ContextAction("view_details", "View Details", InfoKt.getInfo(icons.getDefault()), ColorKt.getTextPrimary(), null);
        markSeasonWatched = new ContextAction("mark_season_watched", "Mark Season Watched", CheckKt.getCheck(icons.getDefault()), androidx.compose.ui.graphics.ColorKt.Color(4280468830L), null);
        markSeasonUnwatched = new ContextAction("mark_season_unwatched", "Mark Season Unwatched", ClearKt.getClear(icons.getDefault()), ColorKt.getTextSecondary(), null);
    }

    private ContextActions() {
    }

    public final ContextAction getAddWatchlist() {
        return addWatchlist;
    }

    public final ContextAction getMarkSeasonUnwatched() {
        return markSeasonUnwatched;
    }

    public final ContextAction getMarkSeasonWatched() {
        return markSeasonWatched;
    }

    public final ContextAction getMarkUnwatched() {
        return markUnwatched;
    }

    public final ContextAction getMarkWatched() {
        return markWatched;
    }

    public final ContextAction getPlay() {
        return play;
    }

    public final ContextAction getRemoveWatchlist() {
        return removeWatchlist;
    }

    public final ContextAction getSelectSource() {
        return selectSource;
    }

    public final ContextAction getViewDetails() {
        return viewDetails;
    }
}
