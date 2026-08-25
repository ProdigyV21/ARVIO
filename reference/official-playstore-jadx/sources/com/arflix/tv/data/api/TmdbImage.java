package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003JI\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/TmdbImage;", "", "filePath", "", "iso6391", "width", "", "height", "voteAverage", "", "voteCount", "<init>", "(Ljava/lang/String;Ljava/lang/String;IIFI)V", "getFilePath", "()Ljava/lang/String;", "getIso6391", "getWidth", "()I", "getHeight", "getVoteAverage", "()F", "getVoteCount", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbImage {
    public static final int $stable = 0;

    @SerializedName("file_path")
    private final String filePath;
    private final int height;

    @SerializedName("iso_639_1")
    private final String iso6391;

    @SerializedName("vote_average")
    private final float voteAverage;

    @SerializedName("vote_count")
    private final int voteCount;
    private final int width;

    public TmdbImage() {
        this(null, null, 0, 0, 0.0f, 0, 63, null);
    }

    public static /* synthetic */ TmdbImage copy$default(TmdbImage tmdbImage, String str, String str2, int i10, int i11, float f10, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            str = tmdbImage.filePath;
        }
        if ((i13 & 2) != 0) {
            str2 = tmdbImage.iso6391;
        }
        if ((i13 & 4) != 0) {
            i10 = tmdbImage.width;
        }
        if ((i13 & 8) != 0) {
            i11 = tmdbImage.height;
        }
        if ((i13 & 16) != 0) {
            f10 = tmdbImage.voteAverage;
        }
        if ((i13 & 32) != 0) {
            i12 = tmdbImage.voteCount;
        }
        float f11 = f10;
        int i14 = i12;
        return tmdbImage.copy(str, str2, i10, i11, f11, i14);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getIso6391() {
        return this.iso6391;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getVoteAverage() {
        return this.voteAverage;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getVoteCount() {
        return this.voteCount;
    }

    public final TmdbImage copy(String filePath, String iso6391, int width, int height, float voteAverage, int voteCount) {
        return new TmdbImage(filePath, iso6391, width, height, voteAverage, voteCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbImage)) {
            return false;
        }
        TmdbImage tmdbImage = (TmdbImage) other;
        return p.a(this.filePath, tmdbImage.filePath) && p.a(this.iso6391, tmdbImage.iso6391) && this.width == tmdbImage.width && this.height == tmdbImage.height && Float.compare(this.voteAverage, tmdbImage.voteAverage) == 0 && this.voteCount == tmdbImage.voteCount;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getIso6391() {
        return this.iso6391;
    }

    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public final int getVoteCount() {
        return this.voteCount;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        String str = this.filePath;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.iso6391;
        return a0.c.b(this.voteAverage, (((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.width) * 31) + this.height) * 31, 31) + this.voteCount;
    }

    public String toString() {
        String str = this.filePath;
        String str2 = this.iso6391;
        int i10 = this.width;
        int i11 = this.height;
        float f10 = this.voteAverage;
        int i12 = this.voteCount;
        StringBuilder sbR = a2.r("TmdbImage(filePath=", str, ", iso6391=", str2, ", width=");
        a2.y(sbR, i10, ", height=", i11, ", voteAverage=");
        sbR.append(f10);
        sbR.append(", voteCount=");
        sbR.append(i12);
        sbR.append(")");
        return sbR.toString();
    }

    public TmdbImage(String str, String str2, int i10, int i11, float f10, int i12) {
        this.filePath = str;
        this.iso6391 = str2;
        this.width = i10;
        this.height = i11;
        this.voteAverage = f10;
        this.voteCount = i12;
    }

    public /* synthetic */ TmdbImage(String str, String str2, int i10, int i11, float f10, int i12, int i13, kotlin.jvm.internal.h hVar) {
        this((i13 & 1) != 0 ? null : str, (i13 & 2) != 0 ? null : str2, (i13 & 4) != 0 ? 0 : i10, (i13 & 8) != 0 ? 0 : i11, (i13 & 16) != 0 ? 0.0f : f10, (i13 & 32) != 0 ? 0 : i12);
    }
}
