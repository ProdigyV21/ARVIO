package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0092\u0001\u00100\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u00101J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\nHÖ\u0081\u0004J\n\u00106\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00067"}, d2 = {"Lcom/arflix/tv/data/api/TraktSearchList;", "", ContentDisposition.Parameters.Name, "", MediaTrack.ROLE_DESCRIPTION, "privacy", LinkHeader.Parameters.Type, "createdAt", "updatedAt", "itemCount", "", "likes", "ids", "Lcom/arflix/tv/data/api/TraktSearchListIds;", "user", "Lcom/arflix/tv/data/api/TraktSearchListUser;", "images", "Lcom/arflix/tv/data/api/TraktSearchListImages;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/api/TraktSearchListIds;Lcom/arflix/tv/data/api/TraktSearchListUser;Lcom/arflix/tv/data/api/TraktSearchListImages;)V", "getName", "()Ljava/lang/String;", "getDescription", "getPrivacy", "getType", "getCreatedAt", "getUpdatedAt", "getItemCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLikes", "getIds", "()Lcom/arflix/tv/data/api/TraktSearchListIds;", "getUser", "()Lcom/arflix/tv/data/api/TraktSearchListUser;", "getImages", "()Lcom/arflix/tv/data/api/TraktSearchListImages;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/api/TraktSearchListIds;Lcom/arflix/tv/data/api/TraktSearchListUser;Lcom/arflix/tv/data/api/TraktSearchListImages;)Lcom/arflix/tv/data/api/TraktSearchList;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSearchList {
    public static final int $stable = 0;

    @SerializedName("created_at")
    private final String createdAt;
    private final String description;
    private final TraktSearchListIds ids;
    private final TraktSearchListImages images;

    @SerializedName("item_count")
    private final Integer itemCount;
    private final Integer likes;
    private final String name;
    private final String privacy;
    private final String type;

    @SerializedName("updated_at")
    private final String updatedAt;
    private final TraktSearchListUser user;

    public TraktSearchList() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    public static /* synthetic */ TraktSearchList copy$default(TraktSearchList traktSearchList, String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, TraktSearchListIds traktSearchListIds, TraktSearchListUser traktSearchListUser, TraktSearchListImages traktSearchListImages, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktSearchList.name;
        }
        if ((i10 & 2) != 0) {
            str2 = traktSearchList.description;
        }
        if ((i10 & 4) != 0) {
            str3 = traktSearchList.privacy;
        }
        if ((i10 & 8) != 0) {
            str4 = traktSearchList.type;
        }
        if ((i10 & 16) != 0) {
            str5 = traktSearchList.createdAt;
        }
        if ((i10 & 32) != 0) {
            str6 = traktSearchList.updatedAt;
        }
        if ((i10 & 64) != 0) {
            num = traktSearchList.itemCount;
        }
        if ((i10 & 128) != 0) {
            num2 = traktSearchList.likes;
        }
        if ((i10 & 256) != 0) {
            traktSearchListIds = traktSearchList.ids;
        }
        if ((i10 & 512) != 0) {
            traktSearchListUser = traktSearchList.user;
        }
        if ((i10 & 1024) != 0) {
            traktSearchListImages = traktSearchList.images;
        }
        TraktSearchListUser traktSearchListUser2 = traktSearchListUser;
        TraktSearchListImages traktSearchListImages2 = traktSearchListImages;
        Integer num3 = num2;
        TraktSearchListIds traktSearchListIds2 = traktSearchListIds;
        String str7 = str6;
        Integer num4 = num;
        String str8 = str5;
        String str9 = str3;
        return traktSearchList.copy(str, str2, str9, str4, str8, str7, num4, num3, traktSearchListIds2, traktSearchListUser2, traktSearchListImages2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final TraktSearchListUser getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final TraktSearchListImages getImages() {
        return this.images;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPrivacy() {
        return this.privacy;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
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
    public final Integer getItemCount() {
        return this.itemCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getLikes() {
        return this.likes;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final TraktSearchListIds getIds() {
        return this.ids;
    }

    public final TraktSearchList copy(String name, String description, String privacy, String type, String createdAt, String updatedAt, Integer itemCount, Integer likes, TraktSearchListIds ids, TraktSearchListUser user, TraktSearchListImages images) {
        return new TraktSearchList(name, description, privacy, type, createdAt, updatedAt, itemCount, likes, ids, user, images);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSearchList)) {
            return false;
        }
        TraktSearchList traktSearchList = (TraktSearchList) other;
        return p.a(this.name, traktSearchList.name) && p.a(this.description, traktSearchList.description) && p.a(this.privacy, traktSearchList.privacy) && p.a(this.type, traktSearchList.type) && p.a(this.createdAt, traktSearchList.createdAt) && p.a(this.updatedAt, traktSearchList.updatedAt) && p.a(this.itemCount, traktSearchList.itemCount) && p.a(this.likes, traktSearchList.likes) && p.a(this.ids, traktSearchList.ids) && p.a(this.user, traktSearchList.user) && p.a(this.images, traktSearchList.images);
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getDescription() {
        return this.description;
    }

    public final TraktSearchListIds getIds() {
        return this.ids;
    }

    public final TraktSearchListImages getImages() {
        return this.images;
    }

    public final Integer getItemCount() {
        return this.itemCount;
    }

    public final Integer getLikes() {
        return this.likes;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrivacy() {
        return this.privacy;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final TraktSearchListUser getUser() {
        return this.user;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.description;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.privacy;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.type;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.createdAt;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.updatedAt;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num = this.itemCount;
        int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.likes;
        int iHashCode8 = (iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31;
        TraktSearchListIds traktSearchListIds = this.ids;
        int iHashCode9 = (iHashCode8 + (traktSearchListIds == null ? 0 : traktSearchListIds.hashCode())) * 31;
        TraktSearchListUser traktSearchListUser = this.user;
        int iHashCode10 = (iHashCode9 + (traktSearchListUser == null ? 0 : traktSearchListUser.hashCode())) * 31;
        TraktSearchListImages traktSearchListImages = this.images;
        return iHashCode10 + (traktSearchListImages != null ? traktSearchListImages.hashCode() : 0);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.description;
        String str3 = this.privacy;
        String str4 = this.type;
        String str5 = this.createdAt;
        String str6 = this.updatedAt;
        Integer num = this.itemCount;
        Integer num2 = this.likes;
        TraktSearchListIds traktSearchListIds = this.ids;
        TraktSearchListUser traktSearchListUser = this.user;
        TraktSearchListImages traktSearchListImages = this.images;
        StringBuilder sbR = a2.r("TraktSearchList(name=", str, ", description=", str2, ", privacy=");
        y.a.i(sbR, str3, ", type=", str4, ", createdAt=");
        y.a.i(sbR, str5, ", updatedAt=", str6, ", itemCount=");
        a2.z(sbR, num, ", likes=", num2, ", ids=");
        sbR.append(traktSearchListIds);
        sbR.append(", user=");
        sbR.append(traktSearchListUser);
        sbR.append(", images=");
        sbR.append(traktSearchListImages);
        sbR.append(")");
        return sbR.toString();
    }

    public TraktSearchList(String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, TraktSearchListIds traktSearchListIds, TraktSearchListUser traktSearchListUser, TraktSearchListImages traktSearchListImages) {
        this.name = str;
        this.description = str2;
        this.privacy = str3;
        this.type = str4;
        this.createdAt = str5;
        this.updatedAt = str6;
        this.itemCount = num;
        this.likes = num2;
        this.ids = traktSearchListIds;
        this.user = traktSearchListUser;
        this.images = traktSearchListImages;
    }

    public /* synthetic */ TraktSearchList(String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, TraktSearchListIds traktSearchListIds, TraktSearchListUser traktSearchListUser, TraktSearchListImages traktSearchListImages, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : num, (i10 & 128) != 0 ? null : num2, (i10 & 256) != 0 ? null : traktSearchListIds, (i10 & 512) != 0 ? null : traktSearchListUser, (i10 & 1024) != 0 ? null : traktSearchListImages);
    }
}
