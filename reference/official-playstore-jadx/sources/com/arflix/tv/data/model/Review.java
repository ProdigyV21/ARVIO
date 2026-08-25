package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JX\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0083\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006'"}, d2 = {"Lcom/arflix/tv/data/model/Review;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", "author", "authorUsername", "authorAvatar", "content", "rating", "", "createdAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getAuthor", "getAuthorUsername", "getAuthorAvatar", "getContent", "getRating", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;)Lcom/arflix/tv/data/model/Review;", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Review implements Serializable {
    public static final int $stable = 0;
    private final String author;
    private final String authorAvatar;
    private final String authorUsername;
    private final String content;
    private final String createdAt;
    private final String id;
    private final Float rating;

    public Review(String str, String str2, String str3, String str4, String str5, Float f10, String str6) {
        this.id = str;
        this.author = str2;
        this.authorUsername = str3;
        this.authorAvatar = str4;
        this.content = str5;
        this.rating = f10;
        this.createdAt = str6;
    }

    public static /* synthetic */ Review copy$default(Review review, String str, String str2, String str3, String str4, String str5, Float f10, String str6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = review.id;
        }
        if ((i10 & 2) != 0) {
            str2 = review.author;
        }
        if ((i10 & 4) != 0) {
            str3 = review.authorUsername;
        }
        if ((i10 & 8) != 0) {
            str4 = review.authorAvatar;
        }
        if ((i10 & 16) != 0) {
            str5 = review.content;
        }
        if ((i10 & 32) != 0) {
            f10 = review.rating;
        }
        if ((i10 & 64) != 0) {
            str6 = review.createdAt;
        }
        Float f11 = f10;
        String str7 = str6;
        String str8 = str5;
        String str9 = str3;
        return review.copy(str, str2, str9, str4, str8, f11, str7);
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
    public final String getAuthorUsername() {
        return this.authorUsername;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAuthorAvatar() {
        return this.authorAvatar;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Float getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final Review copy(String id, String author, String authorUsername, String authorAvatar, String content, Float rating, String createdAt) {
        return new Review(id, author, authorUsername, authorAvatar, content, rating, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Review)) {
            return false;
        }
        Review review = (Review) other;
        return p.a(this.id, review.id) && p.a(this.author, review.author) && p.a(this.authorUsername, review.authorUsername) && p.a(this.authorAvatar, review.authorAvatar) && p.a(this.content, review.content) && p.a(this.rating, review.rating) && p.a(this.createdAt, review.createdAt);
    }

    public final String getAuthor() {
        return this.author;
    }

    public final String getAuthorAvatar() {
        return this.authorAvatar;
    }

    public final String getAuthorUsername() {
        return this.authorUsername;
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

    public final Float getRating() {
        return this.rating;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.id.hashCode() * 31, 31, this.author), 31, this.authorUsername);
        String str = this.authorAvatar;
        int iC2 = c.c((iC + (str == null ? 0 : str.hashCode())) * 31, 31, this.content);
        Float f10 = this.rating;
        return this.createdAt.hashCode() + ((iC2 + (f10 != null ? f10.hashCode() : 0)) * 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.author;
        String str3 = this.authorUsername;
        String str4 = this.authorAvatar;
        String str5 = this.content;
        Float f10 = this.rating;
        String str6 = this.createdAt;
        StringBuilder sbR = a2.r("Review(id=", str, ", author=", str2, ", authorUsername=");
        a.i(sbR, str3, ", authorAvatar=", str4, ", content=");
        sbR.append(str5);
        sbR.append(", rating=");
        sbR.append(f10);
        sbR.append(", createdAt=");
        return a0.c.p(sbR, str6, ")");
    }

    public /* synthetic */ Review(String str, String str2, String str3, String str4, String str5, Float f10, String str6, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? "" : str3, (i10 & 8) != 0 ? null : str4, str5, (i10 & 32) != 0 ? null : f10, (i10 & 64) != 0 ? "" : str6);
    }
}
