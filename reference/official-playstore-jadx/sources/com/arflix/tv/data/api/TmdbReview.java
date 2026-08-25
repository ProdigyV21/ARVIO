package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JQ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/api/TmdbReview;", "", TtmlNode.ATTR_ID, "", "author", "authorDetails", "Lcom/arflix/tv/data/api/TmdbAuthorDetails;", "content", "createdAt", "updatedAt", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TmdbAuthorDetails;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getAuthor", "getAuthorDetails", "()Lcom/arflix/tv/data/api/TmdbAuthorDetails;", "getContent", "getCreatedAt", "getUpdatedAt", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbReview {
    public static final int $stable = 0;
    private final String author;

    @SerializedName("author_details")
    private final TmdbAuthorDetails authorDetails;
    private final String content;

    @SerializedName("created_at")
    private final String createdAt;
    private final String id;

    @SerializedName("updated_at")
    private final String updatedAt;
    private final String url;

    public TmdbReview() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ TmdbReview copy$default(TmdbReview tmdbReview, String str, String str2, TmdbAuthorDetails tmdbAuthorDetails, String str3, String str4, String str5, String str6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = tmdbReview.id;
        }
        if ((i10 & 2) != 0) {
            str2 = tmdbReview.author;
        }
        if ((i10 & 4) != 0) {
            tmdbAuthorDetails = tmdbReview.authorDetails;
        }
        if ((i10 & 8) != 0) {
            str3 = tmdbReview.content;
        }
        if ((i10 & 16) != 0) {
            str4 = tmdbReview.createdAt;
        }
        if ((i10 & 32) != 0) {
            str5 = tmdbReview.updatedAt;
        }
        if ((i10 & 64) != 0) {
            str6 = tmdbReview.url;
        }
        String str7 = str5;
        String str8 = str6;
        String str9 = str4;
        TmdbAuthorDetails tmdbAuthorDetails2 = tmdbAuthorDetails;
        return tmdbReview.copy(str, str2, tmdbAuthorDetails2, str3, str9, str7, str8);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAuthor() {
        return this.author;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TmdbAuthorDetails getAuthorDetails() {
        return this.authorDetails;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final TmdbReview copy(String id, String author, TmdbAuthorDetails authorDetails, String content, String createdAt, String updatedAt, String url) {
        return new TmdbReview(id, author, authorDetails, content, createdAt, updatedAt, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbReview)) {
            return false;
        }
        TmdbReview tmdbReview = (TmdbReview) other;
        return p.a(this.id, tmdbReview.id) && p.a(this.author, tmdbReview.author) && p.a(this.authorDetails, tmdbReview.authorDetails) && p.a(this.content, tmdbReview.content) && p.a(this.createdAt, tmdbReview.createdAt) && p.a(this.updatedAt, tmdbReview.updatedAt) && p.a(this.url, tmdbReview.url);
    }

    public final String getAuthor() {
        return this.author;
    }

    public final TmdbAuthorDetails getAuthorDetails() {
        return this.authorDetails;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getId() {
        return this.id;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.author);
        TmdbAuthorDetails tmdbAuthorDetails = this.authorDetails;
        return this.url.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iC + (tmdbAuthorDetails == null ? 0 : tmdbAuthorDetails.hashCode())) * 31, 31, this.content), 31, this.createdAt), 31, this.updatedAt);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.author;
        TmdbAuthorDetails tmdbAuthorDetails = this.authorDetails;
        String str3 = this.content;
        String str4 = this.createdAt;
        String str5 = this.updatedAt;
        String str6 = this.url;
        StringBuilder sbR = a2.r("TmdbReview(id=", str, ", author=", str2, ", authorDetails=");
        sbR.append(tmdbAuthorDetails);
        sbR.append(", content=");
        sbR.append(str3);
        sbR.append(", createdAt=");
        y.a.i(sbR, str4, ", updatedAt=", str5, ", url=");
        return a0.c.p(sbR, str6, ")");
    }

    public TmdbReview(String str, String str2, TmdbAuthorDetails tmdbAuthorDetails, String str3, String str4, String str5, String str6) {
        this.id = str;
        this.author = str2;
        this.authorDetails = tmdbAuthorDetails;
        this.content = str3;
        this.createdAt = str4;
        this.updatedAt = str5;
        this.url = str6;
    }

    public /* synthetic */ TmdbReview(String str, String str2, TmdbAuthorDetails tmdbAuthorDetails, String str3, String str4, String str5, String str6, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? null : tmdbAuthorDetails, (i10 & 8) != 0 ? "" : str3, (i10 & 16) != 0 ? "" : str4, (i10 & 32) != 0 ? "" : str5, (i10 & 64) != 0 ? "" : str6);
    }
}
