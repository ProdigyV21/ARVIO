package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import cb.l1;
import cb.r1;
import com.arflix.tv.util.Constants;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\b\u0087\b\u0018\u0000 ;2\u00020\u0001:\u0002<;Bc\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eBk\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0013J'\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u001eJ\u0012\u0010#\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b#\u0010\u001eJ\u0012\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b$\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b&\u0010\u001eJ\u0012\u0010'\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b'\u0010\u001eJl\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b*\u0010\u001eJ\u0010\u0010+\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b+\u0010,J\u001a\u0010.\u001a\u00020\t2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b.\u0010/R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00100\u001a\u0004\b1\u0010\u001eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00100\u001a\u0004\b2\u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u00103\u001a\u0004\b4\u0010!R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u00100\u001a\u0004\b5\u0010\u001eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u00100\u001a\u0004\b6\u0010\u001eR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u00107\u001a\u0004\b8\u0010%R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u00100\u001a\u0004\b9\u0010\u001eR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u00100\u001a\u0004\b:\u0010\u001e¨\u0006="}, d2 = {"Lcom/arflix/tv/data/repository/UserProfile;", "", "", TtmlNode.ATTR_ID, "email", "Ldb/b0;", Constants.PREF_TRAKT_TOKEN, "addons", Constants.PREF_DEFAULT_SUBTITLE, "", Constants.PREF_AUTO_PLAY_NEXT, "created_at", "updated_at", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "", "seen0", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$app_playRelease", "(Lcom/arflix/tv/data/repository/UserProfile;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ldb/b0;", "component4", "component5", "component6", "()Ljava/lang/Boolean;", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/UserProfile;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getEmail", "Ldb/b0;", "getTrakt_token", "getAddons", "getDefault_subtitle", "Ljava/lang/Boolean;", "getAuto_play_next", "getCreated_at", "getUpdated_at", "Companion", "$serializer", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
public final /* data */ class UserProfile {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String addons;
    private final Boolean auto_play_next;
    private final String created_at;
    private final String default_subtitle;
    private final String email;
    private final String id;
    private final db.b0 trakt_token;
    private final String updated_at;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/UserProfile$Companion;", "", "<init>", "()V", "Lya/h;", "Lcom/arflix/tv/data/repository/UserProfile;", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<UserProfile> serializer() {
            return UserProfile$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public UserProfile() {
        this((String) null, (String) null, (db.b0) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, 255, (kotlin.jvm.internal.h) null);
    }

    public static /* synthetic */ UserProfile copy$default(UserProfile userProfile, String str, String str2, db.b0 b0Var, String str3, String str4, Boolean bool, String str5, String str6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = userProfile.id;
        }
        if ((i10 & 2) != 0) {
            str2 = userProfile.email;
        }
        if ((i10 & 4) != 0) {
            b0Var = userProfile.trakt_token;
        }
        if ((i10 & 8) != 0) {
            str3 = userProfile.addons;
        }
        if ((i10 & 16) != 0) {
            str4 = userProfile.default_subtitle;
        }
        if ((i10 & 32) != 0) {
            bool = userProfile.auto_play_next;
        }
        if ((i10 & 64) != 0) {
            str5 = userProfile.created_at;
        }
        if ((i10 & 128) != 0) {
            str6 = userProfile.updated_at;
        }
        String str7 = str5;
        String str8 = str6;
        String str9 = str4;
        Boolean bool2 = bool;
        return userProfile.copy(str, str2, b0Var, str3, str9, bool2, str7, str8);
    }

    @q7.n
    public static final /* synthetic */ void write$Self$app_playRelease(UserProfile self, bb.c output, ab.g serialDesc) {
        if (output.C() || !kotlin.jvm.internal.p.a(self.id, "")) {
            output.x(serialDesc, 0, self.id);
        }
        if (output.C() || !kotlin.jvm.internal.p.a(self.email, "")) {
            output.x(serialDesc, 1, self.email);
        }
        if (output.C() || self.trakt_token != null) {
            output.m(serialDesc, 2, db.e0.f14890a, self.trakt_token);
        }
        if (output.C() || self.addons != null) {
            output.m(serialDesc, 3, r1.f7553a, self.addons);
        }
        if (output.C() || self.default_subtitle != null) {
            output.m(serialDesc, 4, r1.f7553a, self.default_subtitle);
        }
        if (output.C() || self.auto_play_next != null) {
            output.m(serialDesc, 5, cb.g.f7499a, self.auto_play_next);
        }
        if (output.C() || self.created_at != null) {
            output.m(serialDesc, 6, r1.f7553a, self.created_at);
        }
        if (!output.C() && self.updated_at == null) {
            return;
        }
        output.m(serialDesc, 7, r1.f7553a, self.updated_at);
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
    public final db.b0 getTrakt_token() {
        return this.trakt_token;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddons() {
        return this.addons;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDefault_subtitle() {
        return this.default_subtitle;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Boolean getAuto_play_next() {
        return this.auto_play_next;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCreated_at() {
        return this.created_at;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUpdated_at() {
        return this.updated_at;
    }

    public final UserProfile copy(String id, String email, db.b0 trakt_token, String addons, String default_subtitle, Boolean auto_play_next, String created_at, String updated_at) {
        return new UserProfile(id, email, trakt_token, addons, default_subtitle, auto_play_next, created_at, updated_at);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserProfile)) {
            return false;
        }
        UserProfile userProfile = (UserProfile) other;
        return kotlin.jvm.internal.p.a(this.id, userProfile.id) && kotlin.jvm.internal.p.a(this.email, userProfile.email) && kotlin.jvm.internal.p.a(this.trakt_token, userProfile.trakt_token) && kotlin.jvm.internal.p.a(this.addons, userProfile.addons) && kotlin.jvm.internal.p.a(this.default_subtitle, userProfile.default_subtitle) && kotlin.jvm.internal.p.a(this.auto_play_next, userProfile.auto_play_next) && kotlin.jvm.internal.p.a(this.created_at, userProfile.created_at) && kotlin.jvm.internal.p.a(this.updated_at, userProfile.updated_at);
    }

    public final String getAddons() {
        return this.addons;
    }

    public final Boolean getAuto_play_next() {
        return this.auto_play_next;
    }

    public final String getCreated_at() {
        return this.created_at;
    }

    public final String getDefault_subtitle() {
        return this.default_subtitle;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getId() {
        return this.id;
    }

    public final db.b0 getTrakt_token() {
        return this.trakt_token;
    }

    public final String getUpdated_at() {
        return this.updated_at;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.email);
        db.b0 b0Var = this.trakt_token;
        int iHashCode = (iC + (b0Var == null ? 0 : b0Var.f14881i.hashCode())) * 31;
        String str = this.addons;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.default_subtitle;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.auto_play_next;
        int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.created_at;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.updated_at;
        return iHashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.email;
        db.b0 b0Var = this.trakt_token;
        String str3 = this.addons;
        String str4 = this.default_subtitle;
        Boolean bool = this.auto_play_next;
        String str5 = this.created_at;
        String str6 = this.updated_at;
        StringBuilder sbR = a2.r("UserProfile(id=", str, ", email=", str2, ", trakt_token=");
        sbR.append(b0Var);
        sbR.append(", addons=");
        sbR.append(str3);
        sbR.append(", default_subtitle=");
        sbR.append(str4);
        sbR.append(", auto_play_next=");
        sbR.append(bool);
        sbR.append(", created_at=");
        return a2.n(sbR, str5, ", updated_at=", str6, ")");
    }

    public /* synthetic */ UserProfile(int i10, String str, String str2, db.b0 b0Var, String str3, String str4, Boolean bool, String str5, String str6, l1 l1Var) {
        if ((i10 & 1) == 0) {
            this.id = "";
        } else {
            this.id = str;
        }
        if ((i10 & 2) == 0) {
            this.email = "";
        } else {
            this.email = str2;
        }
        if ((i10 & 4) == 0) {
            this.trakt_token = null;
        } else {
            this.trakt_token = b0Var;
        }
        if ((i10 & 8) == 0) {
            this.addons = null;
        } else {
            this.addons = str3;
        }
        if ((i10 & 16) == 0) {
            this.default_subtitle = null;
        } else {
            this.default_subtitle = str4;
        }
        if ((i10 & 32) == 0) {
            this.auto_play_next = null;
        } else {
            this.auto_play_next = bool;
        }
        if ((i10 & 64) == 0) {
            this.created_at = null;
        } else {
            this.created_at = str5;
        }
        if ((i10 & 128) == 0) {
            this.updated_at = null;
        } else {
            this.updated_at = str6;
        }
    }

    public UserProfile(String str, String str2, db.b0 b0Var, String str3, String str4, Boolean bool, String str5, String str6) {
        this.id = str;
        this.email = str2;
        this.trakt_token = b0Var;
        this.addons = str3;
        this.default_subtitle = str4;
        this.auto_play_next = bool;
        this.created_at = str5;
        this.updated_at = str6;
    }

    public /* synthetic */ UserProfile(String str, String str2, db.b0 b0Var, String str3, String str4, Boolean bool, String str5, String str6, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? null : b0Var, (i10 & 8) != 0 ? null : str3, (i10 & 16) != 0 ? null : str4, (i10 & 32) != 0 ? null : bool, (i10 & 64) != 0 ? null : str5, (i10 & 128) != 0 ? null : str6);
    }
}
