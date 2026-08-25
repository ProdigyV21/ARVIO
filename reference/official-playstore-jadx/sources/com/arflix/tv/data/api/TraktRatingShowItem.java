package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/TraktRatingShowItem;", "", "rating", "", "ratedAt", "", "ids", "Lcom/arflix/tv/data/api/TraktIds;", "<init>", "(ILjava/lang/String;Lcom/arflix/tv/data/api/TraktIds;)V", "getRating", "()I", "getRatedAt", "()Ljava/lang/String;", "getIds", "()Lcom/arflix/tv/data/api/TraktIds;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktRatingShowItem {
    public static final int $stable = 0;
    private final TraktIds ids;

    @SerializedName("rated_at")
    private final String ratedAt;
    private final int rating;

    public TraktRatingShowItem(int i10, String str, TraktIds traktIds) {
        this.rating = i10;
        this.ratedAt = str;
        this.ids = traktIds;
    }

    public static /* synthetic */ TraktRatingShowItem copy$default(TraktRatingShowItem traktRatingShowItem, int i10, String str, TraktIds traktIds, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktRatingShowItem.rating;
        }
        if ((i11 & 2) != 0) {
            str = traktRatingShowItem.ratedAt;
        }
        if ((i11 & 4) != 0) {
            traktIds = traktRatingShowItem.ids;
        }
        return traktRatingShowItem.copy(i10, str, traktIds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRatedAt() {
        return this.ratedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktIds getIds() {
        return this.ids;
    }

    public final TraktRatingShowItem copy(int rating, String ratedAt, TraktIds ids) {
        return new TraktRatingShowItem(rating, ratedAt, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktRatingShowItem)) {
            return false;
        }
        TraktRatingShowItem traktRatingShowItem = (TraktRatingShowItem) other;
        return this.rating == traktRatingShowItem.rating && p.a(this.ratedAt, traktRatingShowItem.ratedAt) && p.a(this.ids, traktRatingShowItem.ids);
    }

    public final TraktIds getIds() {
        return this.ids;
    }

    public final String getRatedAt() {
        return this.ratedAt;
    }

    public final int getRating() {
        return this.rating;
    }

    public int hashCode() {
        int i10 = this.rating * 31;
        String str = this.ratedAt;
        return this.ids.hashCode() + ((i10 + (str == null ? 0 : str.hashCode())) * 31);
    }

    public String toString() {
        int i10 = this.rating;
        String str = this.ratedAt;
        TraktIds traktIds = this.ids;
        StringBuilder sbO = a2.o("TraktRatingShowItem(rating=", i10, ", ratedAt=", str, ", ids=");
        sbO.append(traktIds);
        sbO.append(")");
        return sbO.toString();
    }

    public /* synthetic */ TraktRatingShowItem(int i10, String str, TraktIds traktIds, int i11, kotlin.jvm.internal.h hVar) {
        this(i10, (i11 & 2) != 0 ? null : str, traktIds);
    }
}
