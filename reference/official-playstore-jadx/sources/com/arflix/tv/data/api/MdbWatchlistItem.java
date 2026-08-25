package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jb\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010!J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0016\u0010\u000fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012¨\u0006'"}, d2 = {"Lcom/arflix/tv/data/api/MdbWatchlistItem;", "", TtmlNode.ATTR_ID, "", "mediatype", "", "ids", "Lcom/arflix/tv/data/api/MdbIds;", LinkHeader.Parameters.Title, "releaseYear", "releaseDate", "watchlistAt", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbIds;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMediatype", "()Ljava/lang/String;", "getIds", "()Lcom/arflix/tv/data/api/MdbIds;", "getTitle", "getReleaseYear", "getReleaseDate", "getWatchlistAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbIds;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/MdbWatchlistItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbWatchlistItem {
    public static final int $stable = 0;
    private final Integer id;
    private final MdbIds ids;
    private final String mediatype;

    @SerializedName("release_date")
    private final String releaseDate;

    @SerializedName("release_year")
    private final Integer releaseYear;
    private final String title;

    @SerializedName("watchlist_at")
    private final String watchlistAt;

    public MdbWatchlistItem() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ MdbWatchlistItem copy$default(MdbWatchlistItem mdbWatchlistItem, Integer num, String str, MdbIds mdbIds, String str2, Integer num2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = mdbWatchlistItem.id;
        }
        if ((i10 & 2) != 0) {
            str = mdbWatchlistItem.mediatype;
        }
        if ((i10 & 4) != 0) {
            mdbIds = mdbWatchlistItem.ids;
        }
        if ((i10 & 8) != 0) {
            str2 = mdbWatchlistItem.title;
        }
        if ((i10 & 16) != 0) {
            num2 = mdbWatchlistItem.releaseYear;
        }
        if ((i10 & 32) != 0) {
            str3 = mdbWatchlistItem.releaseDate;
        }
        if ((i10 & 64) != 0) {
            str4 = mdbWatchlistItem.watchlistAt;
        }
        String str5 = str3;
        String str6 = str4;
        Integer num3 = num2;
        MdbIds mdbIds2 = mdbIds;
        return mdbWatchlistItem.copy(num, str, mdbIds2, str2, num3, str5, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMediatype() {
        return this.mediatype;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MdbIds getIds() {
        return this.ids;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getReleaseYear() {
        return this.releaseYear;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getReleaseDate() {
        return this.releaseDate;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getWatchlistAt() {
        return this.watchlistAt;
    }

    public final MdbWatchlistItem copy(Integer id, String mediatype, MdbIds ids, String title, Integer releaseYear, String releaseDate, String watchlistAt) {
        return new MdbWatchlistItem(id, mediatype, ids, title, releaseYear, releaseDate, watchlistAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbWatchlistItem)) {
            return false;
        }
        MdbWatchlistItem mdbWatchlistItem = (MdbWatchlistItem) other;
        return p.a(this.id, mdbWatchlistItem.id) && p.a(this.mediatype, mdbWatchlistItem.mediatype) && p.a(this.ids, mdbWatchlistItem.ids) && p.a(this.title, mdbWatchlistItem.title) && p.a(this.releaseYear, mdbWatchlistItem.releaseYear) && p.a(this.releaseDate, mdbWatchlistItem.releaseDate) && p.a(this.watchlistAt, mdbWatchlistItem.watchlistAt);
    }

    public final Integer getId() {
        return this.id;
    }

    public final MdbIds getIds() {
        return this.ids;
    }

    public final String getMediatype() {
        return this.mediatype;
    }

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final Integer getReleaseYear() {
        return this.releaseYear;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getWatchlistAt() {
        return this.watchlistAt;
    }

    public int hashCode() {
        Integer num = this.id;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.mediatype;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        MdbIds mdbIds = this.ids;
        int iHashCode3 = (iHashCode2 + (mdbIds == null ? 0 : mdbIds.hashCode())) * 31;
        String str2 = this.title;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num2 = this.releaseYear;
        int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str3 = this.releaseDate;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.watchlistAt;
        return iHashCode6 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        Integer num = this.id;
        String str = this.mediatype;
        MdbIds mdbIds = this.ids;
        String str2 = this.title;
        Integer num2 = this.releaseYear;
        String str3 = this.releaseDate;
        String str4 = this.watchlistAt;
        StringBuilder sb2 = new StringBuilder("MdbWatchlistItem(id=");
        sb2.append(num);
        sb2.append(", mediatype=");
        sb2.append(str);
        sb2.append(", ids=");
        sb2.append(mdbIds);
        sb2.append(", title=");
        sb2.append(str2);
        sb2.append(", releaseYear=");
        sb2.append(num2);
        sb2.append(", releaseDate=");
        sb2.append(str3);
        sb2.append(", watchlistAt=");
        return a0.c.p(sb2, str4, ")");
    }

    public MdbWatchlistItem(Integer num, String str, MdbIds mdbIds, String str2, Integer num2, String str3, String str4) {
        this.id = num;
        this.mediatype = str;
        this.ids = mdbIds;
        this.title = str2;
        this.releaseYear = num2;
        this.releaseDate = str3;
        this.watchlistAt = str4;
    }

    public /* synthetic */ MdbWatchlistItem(Integer num, String str, MdbIds mdbIds, String str2, Integer num2, String str3, String str4, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : mdbIds, (i10 & 8) != 0 ? null : str2, (i10 & 16) != 0 ? null : num2, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : str4);
    }
}
