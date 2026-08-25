package io.github.jan.supabase.gotrue.user;

import ab.g;
import androidx.media3.extractor.text.ttml.TtmlNode;
import cb.b1;
import cb.l1;
import cb.r1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import wa.c;
import x6.e;
import xa.a;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0087\b\u0018\u0000 92\u00020\u0001:\u0002:9B;\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bB_\b\u0011\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J(\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016HÁ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0012J\u0010\u0010\u001d\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u0012J\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u0012JN\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\t\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b$\u0010\u0012J\u0010\u0010%\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b%\u0010&J\u001a\u0010)\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b)\u0010*R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010+\u001a\u0004\b,\u0010\u0012R \u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010-\u0012\u0004\b/\u00100\u001a\u0004\b.\u0010\u001eR \u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010-\u0012\u0004\b2\u00100\u001a\u0004\b1\u0010\u001eR\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010+R\"\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\b\u0010+\u0012\u0004\b4\u00100\u001a\u0004\b3\u0010\u0012R \u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\t\u0010+\u0012\u0004\b6\u00100\u001a\u0004\b5\u0010\u0012R\u0011\u00107\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b7\u00108¨\u0006;"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "", "", TtmlNode.ATTR_ID, "Lwa/c;", "createdAt", "updatedAt", "status", "friendlyName", "factorType", "<init>", "(Ljava/lang/String;Lwa/c;Lwa/c;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Lwa/c;Lwa/c;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "component4", "()Ljava/lang/String;", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/user/UserMfaFactor;Lbb/c;Lab/g;)V", "write$Self", "component1", "component2", "()Lwa/c;", "component3", "component5", "component6", "copy", "(Ljava/lang/String;Lwa/c;Lwa/c;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Lwa/c;", "getCreatedAt", "getCreatedAt$annotations", "()V", "getUpdatedAt", "getUpdatedAt$annotations", "getFriendlyName", "getFriendlyName$annotations", "getFactorType", "getFactorType$annotations", "isVerified", "()Z", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class UserMfaFactor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final c createdAt;
    private final String factorType;
    private final String friendlyName;
    private final String id;
    private final String status;
    private final c updatedAt;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserMfaFactor$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<UserMfaFactor> serializer() {
            return UserMfaFactor$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    @e
    public /* synthetic */ UserMfaFactor(int i10, String str, @q("created_at") c cVar, @q("updated_at") c cVar2, String str2, @q("friendly_name") String str3, @q("factor_type") String str4, l1 l1Var) {
        if (47 != (i10 & 47)) {
            b1.h(i10, 47, UserMfaFactor$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.id = str;
        this.createdAt = cVar;
        this.updatedAt = cVar2;
        this.status = str2;
        if ((i10 & 16) == 0) {
            this.friendlyName = null;
        } else {
            this.friendlyName = str3;
        }
        this.factorType = str4;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final String getStatus() {
        return this.status;
    }

    public static /* synthetic */ UserMfaFactor copy$default(UserMfaFactor userMfaFactor, String str, c cVar, c cVar2, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = userMfaFactor.id;
        }
        if ((i10 & 2) != 0) {
            cVar = userMfaFactor.createdAt;
        }
        if ((i10 & 4) != 0) {
            cVar2 = userMfaFactor.updatedAt;
        }
        if ((i10 & 8) != 0) {
            str2 = userMfaFactor.status;
        }
        if ((i10 & 16) != 0) {
            str3 = userMfaFactor.friendlyName;
        }
        if ((i10 & 32) != 0) {
            str4 = userMfaFactor.factorType;
        }
        String str5 = str3;
        String str6 = str4;
        return userMfaFactor.copy(str, cVar, cVar2, str2, str5, str6);
    }

    @q("created_at")
    public static /* synthetic */ void getCreatedAt$annotations() {
    }

    @q("factor_type")
    public static /* synthetic */ void getFactorType$annotations() {
    }

    @q("friendly_name")
    public static /* synthetic */ void getFriendlyName$annotations() {
    }

    @q("updated_at")
    public static /* synthetic */ void getUpdatedAt$annotations() {
    }

    @n
    public static final /* synthetic */ void write$Self$gotrue_kt_release(UserMfaFactor self, bb.c output, g serialDesc) {
        output.x(serialDesc, 0, self.id);
        a aVar = a.f22712a;
        output.j(serialDesc, 1, aVar, self.createdAt);
        output.j(serialDesc, 2, aVar, self.updatedAt);
        output.x(serialDesc, 3, self.status);
        if (output.C() || self.friendlyName != null) {
            output.m(serialDesc, 4, r1.f7553a, self.friendlyName);
        }
        output.x(serialDesc, 5, self.factorType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final c getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final c getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getFactorType() {
        return this.factorType;
    }

    public final UserMfaFactor copy(String id, c createdAt, c updatedAt, String status, String friendlyName, String factorType) {
        return new UserMfaFactor(id, createdAt, updatedAt, status, friendlyName, factorType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserMfaFactor)) {
            return false;
        }
        UserMfaFactor userMfaFactor = (UserMfaFactor) other;
        return p.a(this.id, userMfaFactor.id) && p.a(this.createdAt, userMfaFactor.createdAt) && p.a(this.updatedAt, userMfaFactor.updatedAt) && p.a(this.status, userMfaFactor.status) && p.a(this.friendlyName, userMfaFactor.friendlyName) && p.a(this.factorType, userMfaFactor.factorType);
    }

    public final c getCreatedAt() {
        return this.createdAt;
    }

    public final String getFactorType() {
        return this.factorType;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final String getId() {
        return this.id;
    }

    public final c getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c((this.updatedAt.f22539i.hashCode() + ((this.createdAt.f22539i.hashCode() + (this.id.hashCode() * 31)) * 31)) * 31, 31, this.status);
        String str = this.friendlyName;
        return this.factorType.hashCode() + ((iC + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final boolean isVerified() {
        return p.a(this.status, "verified");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("UserMfaFactor(id=");
        sb2.append(this.id);
        sb2.append(", createdAt=");
        sb2.append(this.createdAt);
        sb2.append(", updatedAt=");
        sb2.append(this.updatedAt);
        sb2.append(", status=");
        sb2.append(this.status);
        sb2.append(", friendlyName=");
        sb2.append(this.friendlyName);
        sb2.append(", factorType=");
        return androidx.compose.foundation.c.u(sb2, this.factorType, ')');
    }

    public UserMfaFactor(String str, c cVar, c cVar2, String str2, String str3, String str4) {
        this.id = str;
        this.createdAt = cVar;
        this.updatedAt = cVar2;
        this.status = str2;
        this.friendlyName = str3;
        this.factorType = str4;
    }

    public /* synthetic */ UserMfaFactor(String str, c cVar, c cVar2, String str2, String str3, String str4, int i10, h hVar) {
        this(str, cVar, cVar2, str2, (i10 & 16) != 0 ? null : str3, str4);
    }
}
