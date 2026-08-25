package com.arflix.tv.data.api;

import com.arflix.tv.util.Constants;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J>\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/api/UserProfileUpdate;", "", "traktToken", "Lcom/google/gson/JsonObject;", "defaultSubtitle", "", "autoPlayNext", "", "addons", "<init>", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "getTraktToken", "()Lcom/google/gson/JsonObject;", "getDefaultSubtitle", "()Ljava/lang/String;", "getAutoPlayNext", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getAddons", "component1", "component2", "component3", "component4", "copy", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/arflix/tv/data/api/UserProfileUpdate;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class UserProfileUpdate {
    public static final int $stable = 8;
    private final String addons;

    @SerializedName(Constants.PREF_AUTO_PLAY_NEXT)
    private final Boolean autoPlayNext;

    @SerializedName(Constants.PREF_DEFAULT_SUBTITLE)
    private final String defaultSubtitle;

    @SerializedName(Constants.PREF_TRAKT_TOKEN)
    private final JsonObject traktToken;

    public UserProfileUpdate() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ UserProfileUpdate copy$default(UserProfileUpdate userProfileUpdate, JsonObject jsonObject, String str, Boolean bool, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jsonObject = userProfileUpdate.traktToken;
        }
        if ((i10 & 2) != 0) {
            str = userProfileUpdate.defaultSubtitle;
        }
        if ((i10 & 4) != 0) {
            bool = userProfileUpdate.autoPlayNext;
        }
        if ((i10 & 8) != 0) {
            str2 = userProfileUpdate.addons;
        }
        return userProfileUpdate.copy(jsonObject, str, bool, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final JsonObject getTraktToken() {
        return this.traktToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDefaultSubtitle() {
        return this.defaultSubtitle;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddons() {
        return this.addons;
    }

    public final UserProfileUpdate copy(JsonObject traktToken, String defaultSubtitle, Boolean autoPlayNext, String addons) {
        return new UserProfileUpdate(traktToken, defaultSubtitle, autoPlayNext, addons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserProfileUpdate)) {
            return false;
        }
        UserProfileUpdate userProfileUpdate = (UserProfileUpdate) other;
        return p.a(this.traktToken, userProfileUpdate.traktToken) && p.a(this.defaultSubtitle, userProfileUpdate.defaultSubtitle) && p.a(this.autoPlayNext, userProfileUpdate.autoPlayNext) && p.a(this.addons, userProfileUpdate.addons);
    }

    public final String getAddons() {
        return this.addons;
    }

    public final Boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    public final String getDefaultSubtitle() {
        return this.defaultSubtitle;
    }

    public final JsonObject getTraktToken() {
        return this.traktToken;
    }

    public int hashCode() {
        JsonObject jsonObject = this.traktToken;
        int iHashCode = (jsonObject == null ? 0 : jsonObject.hashCode()) * 31;
        String str = this.defaultSubtitle;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.autoPlayNext;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str2 = this.addons;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "UserProfileUpdate(traktToken=" + this.traktToken + ", defaultSubtitle=" + this.defaultSubtitle + ", autoPlayNext=" + this.autoPlayNext + ", addons=" + this.addons + ")";
    }

    public UserProfileUpdate(JsonObject jsonObject, String str, Boolean bool, String str2) {
        this.traktToken = jsonObject;
        this.defaultSubtitle = str;
        this.autoPlayNext = bool;
        this.addons = str2;
    }

    public /* synthetic */ UserProfileUpdate(JsonObject jsonObject, String str, Boolean bool, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : jsonObject, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : bool, (i10 & 8) != 0 ? null : str2);
    }
}
