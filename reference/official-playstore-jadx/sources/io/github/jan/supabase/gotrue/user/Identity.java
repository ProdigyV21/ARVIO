package io.github.jan.supabase.gotrue.user;

import ab.g;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import bb.c;
import cb.b1;
import cb.l1;
import cb.r1;
import db.b0;
import db.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import x6.e;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0002CBBW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rB{\b\u0011\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\f\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0014J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0014J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0014J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0014J\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0014Jh\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0014J\u0010\u0010 \u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%J(\u0010.\u001a\u00020+2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010(\u001a\u00020'2\u0006\u0010*\u001a\u00020)HÁ\u0001¢\u0006\u0004\b,\u0010-R \u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010/\u0012\u0004\b1\u00102\u001a\u0004\b0\u0010\u0014R \u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u00103\u0012\u0004\b5\u00102\u001a\u0004\b4\u0010\u0016R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010/\u0012\u0004\b7\u00102\u001a\u0004\b6\u0010\u0014R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010/\u0012\u0004\b9\u00102\u001a\u0004\b8\u0010\u0014R\"\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\b\u0010/\u0012\u0004\b;\u00102\u001a\u0004\b:\u0010\u0014R\"\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\t\u0010/\u0012\u0004\b=\u00102\u001a\u0004\b<\u0010\u0014R \u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\n\u0010/\u0012\u0004\b?\u00102\u001a\u0004\b>\u0010\u0014R \u0010\u000b\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u000b\u0010/\u0012\u0004\bA\u00102\u001a\u0004\b@\u0010\u0014¨\u0006D"}, d2 = {"Lio/github/jan/supabase/gotrue/user/Identity;", "", "", TtmlNode.ATTR_ID, "Ldb/b0;", "identityData", "identityId", "lastSignInAt", "updatedAt", "createdAt", "provider", "userId", "<init>", "(Ljava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "component1", "()Ljava/lang/String;", "component2", "()Ldb/b0;", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/user/Identity;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/user/Identity;Lbb/c;Lab/g;)V", "write$Self", "Ljava/lang/String;", "getId", "getId$annotations", "()V", "Ldb/b0;", "getIdentityData", "getIdentityData$annotations", "getIdentityId", "getIdentityId$annotations", "getLastSignInAt", "getLastSignInAt$annotations", "getUpdatedAt", "getUpdatedAt$annotations", "getCreatedAt", "getCreatedAt$annotations", "getProvider", "getProvider$annotations", "getUserId", "getUserId$annotations", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class Identity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String createdAt;
    private final String id;
    private final b0 identityData;
    private final String identityId;
    private final String lastSignInAt;
    private final String provider;
    private final String updatedAt;
    private final String userId;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/user/Identity$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/user/Identity;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<Identity> serializer() {
            return Identity$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    @e
    public /* synthetic */ Identity(int i10, @q(TtmlNode.ATTR_ID) String str, @q("identity_data") b0 b0Var, @q("identity_id") String str2, @q("last_sign_in_at") String str3, @q("updated_at") String str4, @q("created_at") String str5, @q("provider") String str6, @q("user_id") String str7, l1 l1Var) {
        if (195 != (i10 & 195)) {
            b1.h(i10, 195, Identity$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.id = str;
        this.identityData = b0Var;
        if ((i10 & 4) == 0) {
            this.identityId = null;
        } else {
            this.identityId = str2;
        }
        if ((i10 & 8) == 0) {
            this.lastSignInAt = null;
        } else {
            this.lastSignInAt = str3;
        }
        if ((i10 & 16) == 0) {
            this.updatedAt = null;
        } else {
            this.updatedAt = str4;
        }
        if ((i10 & 32) == 0) {
            this.createdAt = null;
        } else {
            this.createdAt = str5;
        }
        this.provider = str6;
        this.userId = str7;
    }

    public static /* synthetic */ Identity copy$default(Identity identity, String str, b0 b0Var, String str2, String str3, String str4, String str5, String str6, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = identity.id;
        }
        if ((i10 & 2) != 0) {
            b0Var = identity.identityData;
        }
        if ((i10 & 4) != 0) {
            str2 = identity.identityId;
        }
        if ((i10 & 8) != 0) {
            str3 = identity.lastSignInAt;
        }
        if ((i10 & 16) != 0) {
            str4 = identity.updatedAt;
        }
        if ((i10 & 32) != 0) {
            str5 = identity.createdAt;
        }
        if ((i10 & 64) != 0) {
            str6 = identity.provider;
        }
        if ((i10 & 128) != 0) {
            str7 = identity.userId;
        }
        String str8 = str6;
        String str9 = str7;
        String str10 = str4;
        String str11 = str5;
        return identity.copy(str, b0Var, str2, str3, str10, str11, str8, str9);
    }

    @q("created_at")
    public static /* synthetic */ void getCreatedAt$annotations() {
    }

    @q(TtmlNode.ATTR_ID)
    public static /* synthetic */ void getId$annotations() {
    }

    @q("identity_data")
    public static /* synthetic */ void getIdentityData$annotations() {
    }

    @q("identity_id")
    public static /* synthetic */ void getIdentityId$annotations() {
    }

    @q("last_sign_in_at")
    public static /* synthetic */ void getLastSignInAt$annotations() {
    }

    @q("provider")
    public static /* synthetic */ void getProvider$annotations() {
    }

    @q("updated_at")
    public static /* synthetic */ void getUpdatedAt$annotations() {
    }

    @q("user_id")
    public static /* synthetic */ void getUserId$annotations() {
    }

    @n
    public static final /* synthetic */ void write$Self$gotrue_kt_release(Identity self, c output, g serialDesc) {
        output.x(serialDesc, 0, self.id);
        output.j(serialDesc, 1, e0.f14890a, self.identityData);
        if (output.C() || self.identityId != null) {
            output.m(serialDesc, 2, r1.f7553a, self.identityId);
        }
        if (output.C() || self.lastSignInAt != null) {
            output.m(serialDesc, 3, r1.f7553a, self.lastSignInAt);
        }
        if (output.C() || self.updatedAt != null) {
            output.m(serialDesc, 4, r1.f7553a, self.updatedAt);
        }
        if (output.C() || self.createdAt != null) {
            output.m(serialDesc, 5, r1.f7553a, self.createdAt);
        }
        output.x(serialDesc, 6, self.provider);
        output.x(serialDesc, 7, self.userId);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final b0 getIdentityData() {
        return this.identityData;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getIdentityId() {
        return this.identityId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLastSignInAt() {
        return this.lastSignInAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    public final Identity copy(String id, b0 identityData, String identityId, String lastSignInAt, String updatedAt, String createdAt, String provider, String userId) {
        return new Identity(id, identityData, identityId, lastSignInAt, updatedAt, createdAt, provider, userId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Identity)) {
            return false;
        }
        Identity identity = (Identity) other;
        return p.a(this.id, identity.id) && p.a(this.identityData, identity.identityData) && p.a(this.identityId, identity.identityId) && p.a(this.lastSignInAt, identity.lastSignInAt) && p.a(this.updatedAt, identity.updatedAt) && p.a(this.createdAt, identity.createdAt) && p.a(this.provider, identity.provider) && p.a(this.userId, identity.userId);
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getId() {
        return this.id;
    }

    public final b0 getIdentityData() {
        return this.identityData;
    }

    public final String getIdentityId() {
        return this.identityId;
    }

    public final String getLastSignInAt() {
        return this.lastSignInAt;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int iG = a2.g(this.identityData.f14881i, this.id.hashCode() * 31, 31);
        String str = this.identityId;
        int iHashCode = (iG + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.lastSignInAt;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.updatedAt;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.createdAt;
        return this.userId.hashCode() + androidx.compose.foundation.c.c((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31, 31, this.provider);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Identity(id=");
        sb2.append(this.id);
        sb2.append(", identityData=");
        sb2.append(this.identityData);
        sb2.append(", identityId=");
        sb2.append(this.identityId);
        sb2.append(", lastSignInAt=");
        sb2.append(this.lastSignInAt);
        sb2.append(", updatedAt=");
        sb2.append(this.updatedAt);
        sb2.append(", createdAt=");
        sb2.append(this.createdAt);
        sb2.append(", provider=");
        sb2.append(this.provider);
        sb2.append(", userId=");
        return androidx.compose.foundation.c.u(sb2, this.userId, ')');
    }

    public Identity(String str, b0 b0Var, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.id = str;
        this.identityData = b0Var;
        this.identityId = str2;
        this.lastSignInAt = str3;
        this.updatedAt = str4;
        this.createdAt = str5;
        this.provider = str6;
        this.userId = str7;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Identity(String str, b0 b0Var, String str2, String str3, String str4, String str5, String str6, String str7, int i10, h hVar) {
        String str8;
        String str9;
        String str10;
        str2 = (i10 & 4) != 0 ? null : str2;
        str3 = (i10 & 8) != 0 ? null : str3;
        str4 = (i10 & 16) != 0 ? null : str4;
        if ((i10 & 32) != 0) {
            str8 = str7;
            str9 = str6;
            str10 = null;
        } else {
            str8 = str7;
            str9 = str6;
            str10 = str5;
        }
        this(str, b0Var, str2, str3, str4, str10, str9, str8);
    }
}
