package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b'\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u0084\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u00104J\u0014\u00105\u001a\u00020\b2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00107\u001a\u00020\u000bHÖ\u0081\u0004J\n\u00108\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u00069"}, d2 = {"Lcom/arflix/tv/data/api/TraktComment;", "", TtmlNode.ATTR_ID, "", "parentId", "comment", "", "spoiler", "", "review", "replies", "", "likes", "userStats", "Lcom/arflix/tv/data/api/TraktCommentUserStats;", "createdAt", "updatedAt", "user", "Lcom/arflix/tv/data/api/TraktUser;", "<init>", "(JLjava/lang/Long;Ljava/lang/String;ZZIILcom/arflix/tv/data/api/TraktCommentUserStats;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktUser;)V", "getId", "()J", "getParentId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getComment", "()Ljava/lang/String;", "getSpoiler", "()Z", "getReview", "getReplies", "()I", "getLikes", "getUserStats", "()Lcom/arflix/tv/data/api/TraktCommentUserStats;", "getCreatedAt", "getUpdatedAt", "getUser", "()Lcom/arflix/tv/data/api/TraktUser;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(JLjava/lang/Long;Ljava/lang/String;ZZIILcom/arflix/tv/data/api/TraktCommentUserStats;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktUser;)Lcom/arflix/tv/data/api/TraktComment;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktComment {
    public static final int $stable = 0;
    private final String comment;

    @SerializedName("created_at")
    private final String createdAt;
    private final long id;
    private final int likes;

    @SerializedName("parent_id")
    private final Long parentId;
    private final int replies;
    private final boolean review;
    private final boolean spoiler;

    @SerializedName("updated_at")
    private final String updatedAt;
    private final TraktUser user;

    @SerializedName("user_stats")
    private final TraktCommentUserStats userStats;

    public TraktComment(long j10, Long l10, String str, boolean z, boolean z5, int i10, int i11, TraktCommentUserStats traktCommentUserStats, String str2, String str3, TraktUser traktUser) {
        this.id = j10;
        this.parentId = l10;
        this.comment = str;
        this.spoiler = z;
        this.review = z5;
        this.replies = i10;
        this.likes = i11;
        this.userStats = traktCommentUserStats;
        this.createdAt = str2;
        this.updatedAt = str3;
        this.user = traktUser;
    }

    public static /* synthetic */ TraktComment copy$default(TraktComment traktComment, long j10, Long l10, String str, boolean z, boolean z5, int i10, int i11, TraktCommentUserStats traktCommentUserStats, String str2, String str3, TraktUser traktUser, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            j10 = traktComment.id;
        }
        return traktComment.copy(j10, (i12 & 2) != 0 ? traktComment.parentId : l10, (i12 & 4) != 0 ? traktComment.comment : str, (i12 & 8) != 0 ? traktComment.spoiler : z, (i12 & 16) != 0 ? traktComment.review : z5, (i12 & 32) != 0 ? traktComment.replies : i10, (i12 & 64) != 0 ? traktComment.likes : i11, (i12 & 128) != 0 ? traktComment.userStats : traktCommentUserStats, (i12 & 256) != 0 ? traktComment.createdAt : str2, (i12 & 512) != 0 ? traktComment.updatedAt : str3, (i12 & 1024) != 0 ? traktComment.user : traktUser);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final TraktUser getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getParentId() {
        return this.parentId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getComment() {
        return this.comment;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getSpoiler() {
        return this.spoiler;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getReview() {
        return this.review;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getReplies() {
        return this.replies;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getLikes() {
        return this.likes;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final TraktCommentUserStats getUserStats() {
        return this.userStats;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final TraktComment copy(long id, Long parentId, String comment, boolean spoiler, boolean review, int replies, int likes, TraktCommentUserStats userStats, String createdAt, String updatedAt, TraktUser user) {
        return new TraktComment(id, parentId, comment, spoiler, review, replies, likes, userStats, createdAt, updatedAt, user);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktComment)) {
            return false;
        }
        TraktComment traktComment = (TraktComment) other;
        return this.id == traktComment.id && p.a(this.parentId, traktComment.parentId) && p.a(this.comment, traktComment.comment) && this.spoiler == traktComment.spoiler && this.review == traktComment.review && this.replies == traktComment.replies && this.likes == traktComment.likes && p.a(this.userStats, traktComment.userStats) && p.a(this.createdAt, traktComment.createdAt) && p.a(this.updatedAt, traktComment.updatedAt) && p.a(this.user, traktComment.user);
    }

    public final String getComment() {
        return this.comment;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final long getId() {
        return this.id;
    }

    public final int getLikes() {
        return this.likes;
    }

    public final Long getParentId() {
        return this.parentId;
    }

    public final int getReplies() {
        return this.replies;
    }

    public final boolean getReview() {
        return this.review;
    }

    public final boolean getSpoiler() {
        return this.spoiler;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final TraktUser getUser() {
        return this.user;
    }

    public final TraktCommentUserStats getUserStats() {
        return this.userStats;
    }

    public int hashCode() {
        long j10 = this.id;
        int i10 = ((int) (j10 ^ (j10 >>> 32))) * 31;
        Long l10 = this.parentId;
        int iC = (((((((androidx.compose.foundation.c.c((i10 + (l10 == null ? 0 : l10.hashCode())) * 31, 31, this.comment) + (this.spoiler ? 1231 : 1237)) * 31) + (this.review ? 1231 : 1237)) * 31) + this.replies) * 31) + this.likes) * 31;
        TraktCommentUserStats traktCommentUserStats = this.userStats;
        int iC2 = androidx.compose.foundation.c.c((iC + (traktCommentUserStats == null ? 0 : traktCommentUserStats.hashCode())) * 31, 31, this.createdAt);
        String str = this.updatedAt;
        int iHashCode = (iC2 + (str == null ? 0 : str.hashCode())) * 31;
        TraktUser traktUser = this.user;
        return iHashCode + (traktUser != null ? traktUser.hashCode() : 0);
    }

    public String toString() {
        long j10 = this.id;
        Long l10 = this.parentId;
        String str = this.comment;
        boolean z = this.spoiler;
        boolean z5 = this.review;
        int i10 = this.replies;
        int i11 = this.likes;
        TraktCommentUserStats traktCommentUserStats = this.userStats;
        String str2 = this.createdAt;
        String str3 = this.updatedAt;
        TraktUser traktUser = this.user;
        StringBuilder sb2 = new StringBuilder("TraktComment(id=");
        sb2.append(j10);
        sb2.append(", parentId=");
        sb2.append(l10);
        sb2.append(", comment=");
        sb2.append(str);
        sb2.append(", spoiler=");
        sb2.append(z);
        sb2.append(", review=");
        sb2.append(z5);
        sb2.append(", replies=");
        sb2.append(i10);
        sb2.append(", likes=");
        sb2.append(i11);
        sb2.append(", userStats=");
        sb2.append(traktCommentUserStats);
        y.a.i(sb2, ", createdAt=", str2, ", updatedAt=", str3);
        sb2.append(", user=");
        sb2.append(traktUser);
        sb2.append(")");
        return sb2.toString();
    }
}
