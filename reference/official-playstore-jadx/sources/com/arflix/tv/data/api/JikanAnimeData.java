package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ>\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0013\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/api/JikanAnimeData;", "", "malId", "", LinkHeader.Parameters.Title, "", "score", "", "scoredBy", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;)V", "getMalId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "()Ljava/lang/String;", "getScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getScoredBy", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/JikanAnimeData;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class JikanAnimeData {
    public static final int $stable = 0;

    @SerializedName("mal_id")
    private final Integer malId;

    @SerializedName("score")
    private final Double score;

    @SerializedName("scored_by")
    private final Integer scoredBy;

    @SerializedName(LinkHeader.Parameters.Title)
    private final String title;

    public JikanAnimeData(Integer num, String str, Double d4, Integer num2) {
        this.malId = num;
        this.title = str;
        this.score = d4;
        this.scoredBy = num2;
    }

    public static /* synthetic */ JikanAnimeData copy$default(JikanAnimeData jikanAnimeData, Integer num, String str, Double d4, Integer num2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = jikanAnimeData.malId;
        }
        if ((i10 & 2) != 0) {
            str = jikanAnimeData.title;
        }
        if ((i10 & 4) != 0) {
            d4 = jikanAnimeData.score;
        }
        if ((i10 & 8) != 0) {
            num2 = jikanAnimeData.scoredBy;
        }
        return jikanAnimeData.copy(num, str, d4, num2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getMalId() {
        return this.malId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Double getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getScoredBy() {
        return this.scoredBy;
    }

    public final JikanAnimeData copy(Integer malId, String title, Double score, Integer scoredBy) {
        return new JikanAnimeData(malId, title, score, scoredBy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JikanAnimeData)) {
            return false;
        }
        JikanAnimeData jikanAnimeData = (JikanAnimeData) other;
        return p.a(this.malId, jikanAnimeData.malId) && p.a(this.title, jikanAnimeData.title) && p.a(this.score, jikanAnimeData.score) && p.a(this.scoredBy, jikanAnimeData.scoredBy);
    }

    public final Integer getMalId() {
        return this.malId;
    }

    public final Double getScore() {
        return this.score;
    }

    public final Integer getScoredBy() {
        return this.scoredBy;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        Integer num = this.malId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Double d4 = this.score;
        int iHashCode3 = (iHashCode2 + (d4 == null ? 0 : d4.hashCode())) * 31;
        Integer num2 = this.scoredBy;
        return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "JikanAnimeData(malId=" + this.malId + ", title=" + this.title + ", score=" + this.score + ", scoredBy=" + this.scoredBy + ")";
    }
}
