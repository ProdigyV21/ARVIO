package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.util.Constants;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jl\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010$J\u0014\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001a\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006*"}, d2 = {"Lcom/arflix/tv/data/api/UserProfile;", "", TtmlNode.ATTR_ID, "", "email", "traktToken", "Lcom/google/gson/JsonObject;", "defaultSubtitle", "autoPlayNext", "", "addons", "createdAt", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getEmail", "getTraktToken", "()Lcom/google/gson/JsonObject;", "getDefaultSubtitle", "getAutoPlayNext", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getAddons", "getCreatedAt", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/UserProfile;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class UserProfile {
    public static final int $stable = 8;
    private final String addons;

    @SerializedName(Constants.PREF_AUTO_PLAY_NEXT)
    private final Boolean autoPlayNext;

    @SerializedName("created_at")
    private final String createdAt;

    @SerializedName(Constants.PREF_DEFAULT_SUBTITLE)
    private final String defaultSubtitle;
    private final String email;
    private final String id;

    @SerializedName(Constants.PREF_TRAKT_TOKEN)
    private final JsonObject traktToken;

    @SerializedName("updated_at")
    private final String updatedAt;

    public UserProfile(String str, String str2, JsonObject jsonObject, String str3, Boolean bool, String str4, String str5, String str6) {
        this.id = str;
        this.email = str2;
        this.traktToken = jsonObject;
        this.defaultSubtitle = str3;
        this.autoPlayNext = bool;
        this.addons = str4;
        this.createdAt = str5;
        this.updatedAt = str6;
    }

    public static /* synthetic */ UserProfile copy$default(UserProfile userProfile, String str, String str2, JsonObject jsonObject, String str3, Boolean bool, String str4, String str5, String str6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = userProfile.id;
        }
        if ((i10 & 2) != 0) {
            str2 = userProfile.email;
        }
        if ((i10 & 4) != 0) {
            jsonObject = userProfile.traktToken;
        }
        if ((i10 & 8) != 0) {
            str3 = userProfile.defaultSubtitle;
        }
        if ((i10 & 16) != 0) {
            bool = userProfile.autoPlayNext;
        }
        if ((i10 & 32) != 0) {
            str4 = userProfile.addons;
        }
        if ((i10 & 64) != 0) {
            str5 = userProfile.createdAt;
        }
        if ((i10 & 128) != 0) {
            str6 = userProfile.updatedAt;
        }
        String str7 = str5;
        String str8 = str6;
        Boolean bool2 = bool;
        String str9 = str4;
        return userProfile.copy(str, str2, jsonObject, str3, bool2, str9, str7, str8);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final JsonObject getTraktToken() {
        return this.traktToken;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDefaultSubtitle() {
        return this.defaultSubtitle;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAddons() {
        return this.addons;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final UserProfile copy(String id, String email, JsonObject traktToken, String defaultSubtitle, Boolean autoPlayNext, String addons, String createdAt, String updatedAt) {
        return new UserProfile(id, email, traktToken, defaultSubtitle, autoPlayNext, addons, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserProfile)) {
            return false;
        }
        UserProfile userProfile = (UserProfile) other;
        return p.a(this.id, userProfile.id) && p.a(this.email, userProfile.email) && p.a(this.traktToken, userProfile.traktToken) && p.a(this.defaultSubtitle, userProfile.defaultSubtitle) && p.a(this.autoPlayNext, userProfile.autoPlayNext) && p.a(this.addons, userProfile.addons) && p.a(this.createdAt, userProfile.createdAt) && p.a(this.updatedAt, userProfile.updatedAt);
    }

    public final String getAddons() {
        return this.addons;
    }

    public final Boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getDefaultSubtitle() {
        return this.defaultSubtitle;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getId() {
        return this.id;
    }

    public final JsonObject getTraktToken() {
        return this.traktToken;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.email;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        JsonObject jsonObject = this.traktToken;
        int iHashCode3 = (iHashCode2 + (jsonObject == null ? 0 : jsonObject.hashCode())) * 31;
        String str2 = this.defaultSubtitle;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.autoPlayNext;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.addons;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.createdAt;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.updatedAt;
        return iHashCode7 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.email;
        JsonObject jsonObject = this.traktToken;
        String str3 = this.defaultSubtitle;
        Boolean bool = this.autoPlayNext;
        String str4 = this.addons;
        String str5 = this.createdAt;
        String str6 = this.updatedAt;
        StringBuilder sbR = a2.r("UserProfile(id=", str, ", email=", str2, ", traktToken=");
        sbR.append(jsonObject);
        sbR.append(", defaultSubtitle=");
        sbR.append(str3);
        sbR.append(", autoPlayNext=");
        sbR.append(bool);
        sbR.append(", addons=");
        sbR.append(str4);
        sbR.append(", createdAt=");
        return a2.n(sbR, str5, ", updatedAt=", str6, ")");
    }
}
