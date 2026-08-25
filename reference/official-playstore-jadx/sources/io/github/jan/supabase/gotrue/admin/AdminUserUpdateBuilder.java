package io.github.jan.supabase.gotrue.admin;

import ab.g;
import bb.c;
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
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\b\u0087\b\u0018\u0000 P2\u00020\u0001:\u0002QPBs\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000e\u0010\u000fB\u007f\b\u0011\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0019J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001cJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u0016J|\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b#\u0010\u0016J\u0010\u0010$\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010'\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(J(\u00101\u001a\u00020.2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,HÁ\u0001¢\u0006\u0004\b/\u00100R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u00102\u001a\u0004\b3\u0010\u0016\"\u0004\b4\u00105R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u00102\u001a\u0004\b6\u0010\u0016\"\u0004\b7\u00105R*\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0006\u00108\u0012\u0004\b<\u0010=\u001a\u0004\b9\u0010\u0019\"\u0004\b:\u0010;R*\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0007\u00108\u0012\u0004\b@\u0010=\u001a\u0004\b>\u0010\u0019\"\u0004\b?\u0010;R*\u0010\t\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\t\u0010A\u0012\u0004\bE\u0010=\u001a\u0004\bB\u0010\u001c\"\u0004\bC\u0010DR*\u0010\n\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\n\u0010A\u0012\u0004\bH\u0010=\u001a\u0004\bF\u0010\u001c\"\u0004\bG\u0010DR$\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u00102\u001a\u0004\bI\u0010\u0016\"\u0004\bJ\u00105R*\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\f\u00102\u0012\u0004\bM\u0010=\u001a\u0004\bK\u0010\u0016\"\u0004\bL\u00105R$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u00102\u001a\u0004\bN\u0010\u0016\"\u0004\bO\u00105¨\u0006R"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder;", "", "", "email", "password", "Ldb/b0;", "appMetadata", "userMetadata", "", "emailConfirm", "phoneConfirm", "phone", "banDuration", "role", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ldb/b0;Ldb/b0;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ldb/b0;Ldb/b0;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ldb/b0;", "component4", "component5", "()Ljava/lang/Boolean;", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ldb/b0;Ldb/b0;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder;Lbb/c;Lab/g;)V", "write$Self", "Ljava/lang/String;", "getEmail", "setEmail", "(Ljava/lang/String;)V", "getPassword", "setPassword", "Ldb/b0;", "getAppMetadata", "setAppMetadata", "(Ldb/b0;)V", "getAppMetadata$annotations", "()V", "getUserMetadata", "setUserMetadata", "getUserMetadata$annotations", "Ljava/lang/Boolean;", "getEmailConfirm", "setEmailConfirm", "(Ljava/lang/Boolean;)V", "getEmailConfirm$annotations", "getPhoneConfirm", "setPhoneConfirm", "getPhoneConfirm$annotations", "getPhone", "setPhone", "getBanDuration", "setBanDuration", "getBanDuration$annotations", "getRole", "setRole", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class AdminUserUpdateBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private b0 appMetadata;
    private String banDuration;
    private String email;
    private Boolean emailConfirm;
    private String password;
    private String phone;
    private Boolean phoneConfirm;
    private String role;
    private b0 userMetadata;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<AdminUserUpdateBuilder> serializer() {
            return AdminUserUpdateBuilder$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public AdminUserUpdateBuilder() {
        this((String) null, (String) null, (b0) null, (b0) null, (Boolean) null, (Boolean) null, (String) null, (String) null, (String) null, 511, (h) null);
    }

    public static /* synthetic */ AdminUserUpdateBuilder copy$default(AdminUserUpdateBuilder adminUserUpdateBuilder, String str, String str2, b0 b0Var, b0 b0Var2, Boolean bool, Boolean bool2, String str3, String str4, String str5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = adminUserUpdateBuilder.email;
        }
        if ((i10 & 2) != 0) {
            str2 = adminUserUpdateBuilder.password;
        }
        if ((i10 & 4) != 0) {
            b0Var = adminUserUpdateBuilder.appMetadata;
        }
        if ((i10 & 8) != 0) {
            b0Var2 = adminUserUpdateBuilder.userMetadata;
        }
        if ((i10 & 16) != 0) {
            bool = adminUserUpdateBuilder.emailConfirm;
        }
        if ((i10 & 32) != 0) {
            bool2 = adminUserUpdateBuilder.phoneConfirm;
        }
        if ((i10 & 64) != 0) {
            str3 = adminUserUpdateBuilder.phone;
        }
        if ((i10 & 128) != 0) {
            str4 = adminUserUpdateBuilder.banDuration;
        }
        if ((i10 & 256) != 0) {
            str5 = adminUserUpdateBuilder.role;
        }
        String str6 = str4;
        String str7 = str5;
        Boolean bool3 = bool2;
        String str8 = str3;
        Boolean bool4 = bool;
        b0 b0Var3 = b0Var;
        return adminUserUpdateBuilder.copy(str, str2, b0Var3, b0Var2, bool4, bool3, str8, str6, str7);
    }

    @q("app_metadata")
    public static /* synthetic */ void getAppMetadata$annotations() {
    }

    @q("ban_duration")
    public static /* synthetic */ void getBanDuration$annotations() {
    }

    @q("email_confirm")
    public static /* synthetic */ void getEmailConfirm$annotations() {
    }

    @q("phone_confirm")
    public static /* synthetic */ void getPhoneConfirm$annotations() {
    }

    @q("user_metadata")
    public static /* synthetic */ void getUserMetadata$annotations() {
    }

    @n
    public static final /* synthetic */ void write$Self$gotrue_kt_release(AdminUserUpdateBuilder self, c output, g serialDesc) {
        if (output.C() || self.email != null) {
            output.m(serialDesc, 0, r1.f7553a, self.email);
        }
        if (output.C() || self.password != null) {
            output.m(serialDesc, 1, r1.f7553a, self.password);
        }
        if (output.C() || self.appMetadata != null) {
            output.m(serialDesc, 2, e0.f14890a, self.appMetadata);
        }
        if (output.C() || self.userMetadata != null) {
            output.m(serialDesc, 3, e0.f14890a, self.userMetadata);
        }
        if (output.C() || self.emailConfirm != null) {
            output.m(serialDesc, 4, cb.g.f7499a, self.emailConfirm);
        }
        if (output.C() || self.phoneConfirm != null) {
            output.m(serialDesc, 5, cb.g.f7499a, self.phoneConfirm);
        }
        if (output.C() || self.phone != null) {
            output.m(serialDesc, 6, r1.f7553a, self.phone);
        }
        if (output.C() || self.banDuration != null) {
            output.m(serialDesc, 7, r1.f7553a, self.banDuration);
        }
        if (!output.C() && self.role == null) {
            return;
        }
        output.m(serialDesc, 8, r1.f7553a, self.role);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final b0 getAppMetadata() {
        return this.appMetadata;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final b0 getUserMetadata() {
        return this.userMetadata;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getEmailConfirm() {
        return this.emailConfirm;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Boolean getPhoneConfirm() {
        return this.phoneConfirm;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getBanDuration() {
        return this.banDuration;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    public final AdminUserUpdateBuilder copy(String email, String password, b0 appMetadata, b0 userMetadata, Boolean emailConfirm, Boolean phoneConfirm, String phone, String banDuration, String role) {
        return new AdminUserUpdateBuilder(email, password, appMetadata, userMetadata, emailConfirm, phoneConfirm, phone, banDuration, role);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdminUserUpdateBuilder)) {
            return false;
        }
        AdminUserUpdateBuilder adminUserUpdateBuilder = (AdminUserUpdateBuilder) other;
        return p.a(this.email, adminUserUpdateBuilder.email) && p.a(this.password, adminUserUpdateBuilder.password) && p.a(this.appMetadata, adminUserUpdateBuilder.appMetadata) && p.a(this.userMetadata, adminUserUpdateBuilder.userMetadata) && p.a(this.emailConfirm, adminUserUpdateBuilder.emailConfirm) && p.a(this.phoneConfirm, adminUserUpdateBuilder.phoneConfirm) && p.a(this.phone, adminUserUpdateBuilder.phone) && p.a(this.banDuration, adminUserUpdateBuilder.banDuration) && p.a(this.role, adminUserUpdateBuilder.role);
    }

    public final b0 getAppMetadata() {
        return this.appMetadata;
    }

    public final String getBanDuration() {
        return this.banDuration;
    }

    public final String getEmail() {
        return this.email;
    }

    public final Boolean getEmailConfirm() {
        return this.emailConfirm;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final Boolean getPhoneConfirm() {
        return this.phoneConfirm;
    }

    public final String getRole() {
        return this.role;
    }

    public final b0 getUserMetadata() {
        return this.userMetadata;
    }

    public int hashCode() {
        String str = this.email;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.password;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        b0 b0Var = this.appMetadata;
        int iHashCode3 = (iHashCode2 + (b0Var == null ? 0 : b0Var.f14881i.hashCode())) * 31;
        b0 b0Var2 = this.userMetadata;
        int iHashCode4 = (iHashCode3 + (b0Var2 == null ? 0 : b0Var2.f14881i.hashCode())) * 31;
        Boolean bool = this.emailConfirm;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.phoneConfirm;
        int iHashCode6 = (iHashCode5 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str3 = this.phone;
        int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.banDuration;
        int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.role;
        return iHashCode8 + (str5 != null ? str5.hashCode() : 0);
    }

    public final void setAppMetadata(b0 b0Var) {
        this.appMetadata = b0Var;
    }

    public final void setBanDuration(String str) {
        this.banDuration = str;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final void setEmailConfirm(Boolean bool) {
        this.emailConfirm = bool;
    }

    public final void setPassword(String str) {
        this.password = str;
    }

    public final void setPhone(String str) {
        this.phone = str;
    }

    public final void setPhoneConfirm(Boolean bool) {
        this.phoneConfirm = bool;
    }

    public final void setRole(String str) {
        this.role = str;
    }

    public final void setUserMetadata(b0 b0Var) {
        this.userMetadata = b0Var;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AdminUserUpdateBuilder(email=");
        sb2.append(this.email);
        sb2.append(", password=");
        sb2.append(this.password);
        sb2.append(", appMetadata=");
        sb2.append(this.appMetadata);
        sb2.append(", userMetadata=");
        sb2.append(this.userMetadata);
        sb2.append(", emailConfirm=");
        sb2.append(this.emailConfirm);
        sb2.append(", phoneConfirm=");
        sb2.append(this.phoneConfirm);
        sb2.append(", phone=");
        sb2.append(this.phone);
        sb2.append(", banDuration=");
        sb2.append(this.banDuration);
        sb2.append(", role=");
        return androidx.compose.foundation.c.u(sb2, this.role, ')');
    }

    @e
    public /* synthetic */ AdminUserUpdateBuilder(int i10, String str, String str2, @q("app_metadata") b0 b0Var, @q("user_metadata") b0 b0Var2, @q("email_confirm") Boolean bool, @q("phone_confirm") Boolean bool2, String str3, @q("ban_duration") String str4, String str5, l1 l1Var) {
        if ((i10 & 1) == 0) {
            this.email = null;
        } else {
            this.email = str;
        }
        if ((i10 & 2) == 0) {
            this.password = null;
        } else {
            this.password = str2;
        }
        if ((i10 & 4) == 0) {
            this.appMetadata = null;
        } else {
            this.appMetadata = b0Var;
        }
        if ((i10 & 8) == 0) {
            this.userMetadata = null;
        } else {
            this.userMetadata = b0Var2;
        }
        if ((i10 & 16) == 0) {
            this.emailConfirm = null;
        } else {
            this.emailConfirm = bool;
        }
        if ((i10 & 32) == 0) {
            this.phoneConfirm = null;
        } else {
            this.phoneConfirm = bool2;
        }
        if ((i10 & 64) == 0) {
            this.phone = null;
        } else {
            this.phone = str3;
        }
        if ((i10 & 128) == 0) {
            this.banDuration = null;
        } else {
            this.banDuration = str4;
        }
        if ((i10 & 256) == 0) {
            this.role = null;
        } else {
            this.role = str5;
        }
    }

    public AdminUserUpdateBuilder(String str, String str2, b0 b0Var, b0 b0Var2, Boolean bool, Boolean bool2, String str3, String str4, String str5) {
        this.email = str;
        this.password = str2;
        this.appMetadata = b0Var;
        this.userMetadata = b0Var2;
        this.emailConfirm = bool;
        this.phoneConfirm = bool2;
        this.phone = str3;
        this.banDuration = str4;
        this.role = str5;
    }

    public /* synthetic */ AdminUserUpdateBuilder(String str, String str2, b0 b0Var, b0 b0Var2, Boolean bool, Boolean bool2, String str3, String str4, String str5, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : b0Var, (i10 & 8) != 0 ? null : b0Var2, (i10 & 16) != 0 ? null : bool, (i10 & 32) != 0 ? null : bool2, (i10 & 64) != 0 ? null : str3, (i10 & 128) != 0 ? null : str4, (i10 & 256) != 0 ? null : str5);
    }
}
