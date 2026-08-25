package androidx.media3.exoplayer.source.preload;

import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public interface RankingDataComparator<T> extends Comparator<T> {

    public interface InvalidationListener {
        void onRankingDataComparatorInvalidated();
    }

    void setInvalidationListener(InvalidationListener invalidationListener);
}
