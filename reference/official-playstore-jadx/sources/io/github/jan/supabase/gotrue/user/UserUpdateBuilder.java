package io.github.jan.supabase.gotrue.user;

import ab.g;
import bb.c;
import cb.l1;
import cb.r1;
import db.a;
import db.b;
import db.b0;
import db.c0;
import db.e0;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.serializer.KotlinXSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import r7.l;
import x6.e;
import x6.t0;
import ya.q;
import ya.r;
import ya.y;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0087\b\u0018\u0000 J2\u00020\u0001:\u0002KJBM\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fBO\b\u0011\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0011J$\u0010\b\u001a\u00020\u0013\"\n\b\u0000\u0010\u0012\u0018\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0086\b¢\u0006\u0004\b\b\u0010\u0014J,\u0010\b\u001a\u00020\u00132\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\u0002\b\u0017H\u0086\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001bJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001bJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010#\u001a\u00020\tHÀ\u0003¢\u0006\u0004\b!\u0010\"JV\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b&\u0010\u001bJ\u0010\u0010'\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b'\u0010(J\u001a\u0010+\u001a\u00020*2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b+\u0010,J(\u00104\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00002\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u000200HÁ\u0001¢\u0006\u0004\b2\u00103R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u00105\u001a\u0004\b6\u0010\u001b\"\u0004\b7\u00108R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u00105\u001a\u0004\b9\u0010\u001b\"\u0004\b:\u00108R*\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0005\u00105\u0012\u0004\b=\u0010>\u001a\u0004\b;\u0010\u001b\"\u0004\b<\u00108R$\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u00105\u001a\u0004\b?\u0010\u001b\"\u0004\b@\u00108R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010A\u001a\u0004\bB\u0010 \"\u0004\bC\u0010DR(\u0010\n\u001a\u00020\t8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b\n\u0010E\u0012\u0004\bI\u0010>\u001a\u0004\bF\u0010\"\"\u0004\bG\u0010H\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006L"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder;", "", "", "email", "password", "phone", "nonce", "Ldb/b0;", "data", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ldb/b0;Lio/github/jan/supabase/SupabaseSerializer;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ldb/b0;Lcb/l1;)V", "T", "Lx6/t0;", "(Ljava/lang/Object;)V", "Lkotlin/Function1;", "Ldb/c0;", "Lx6/n;", "builder", "(Lr7/l;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()Ldb/b0;", "component6$gotrue_kt_release", "()Lio/github/jan/supabase/SupabaseSerializer;", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ldb/b0;Lio/github/jan/supabase/SupabaseSerializer;)Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder;Lbb/c;Lab/g;)V", "write$Self", "Ljava/lang/String;", "getEmail", "setEmail", "(Ljava/lang/String;)V", "getPassword", "setPassword", "getPhone", "setPhone", "getPhone$annotations", "()V", "getNonce", "setNonce", "Ldb/b0;", "getData", "setData", "(Ldb/b0;)V", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "setSerializer", "(Lio/github/jan/supabase/SupabaseSerializer;)V", "getSerializer$annotations", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class UserUpdateBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private b0 data;
    private String email;
    private String nonce;
    private String password;
    private String phone;
    private SupabaseSerializer serializer;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/user/UserUpdateBuilder;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<UserUpdateBuilder> serializer() {
            return UserUpdateBuilder$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public UserUpdateBuilder() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ UserUpdateBuilder copy$default(UserUpdateBuilder userUpdateBuilder, String str, String str2, String str3, String str4, b0 b0Var, SupabaseSerializer supabaseSerializer, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = userUpdateBuilder.email;
        }
        if ((i10 & 2) != 0) {
            str2 = userUpdateBuilder.password;
        }
        if ((i10 & 4) != 0) {
            str3 = userUpdateBuilder.phone;
        }
        if ((i10 & 8) != 0) {
            str4 = userUpdateBuilder.nonce;
        }
        if ((i10 & 16) != 0) {
            b0Var = userUpdateBuilder.data;
        }
        if ((i10 & 32) != 0) {
            supabaseSerializer = userUpdateBuilder.serializer;
        }
        b0 b0Var2 = b0Var;
        SupabaseSerializer supabaseSerializer2 = supabaseSerializer;
        return userUpdateBuilder.copy(str, str2, str3, str4, b0Var2, supabaseSerializer2);
    }

    @q("phone")
    public static /* synthetic */ void getPhone$annotations() {
    }

    @y
    public static /* synthetic */ void getSerializer$annotations() {
    }

    @n
    public static final /* synthetic */ void write$Self$gotrue_kt_release(UserUpdateBuilder self, c output, g serialDesc) {
        if (output.C() || self.email != null) {
            output.m(serialDesc, 0, r1.f7553a, self.email);
        }
        if (output.C() || self.password != null) {
            output.m(serialDesc, 1, r1.f7553a, self.password);
        }
        if (output.C() || self.phone != null) {
            output.m(serialDesc, 2, r1.f7553a, self.phone);
        }
        if (output.C() || self.nonce != null) {
            output.m(serialDesc, 3, r1.f7553a, self.nonce);
        }
        if (!output.C() && self.data == null) {
            return;
        }
        output.m(serialDesc, 4, e0.f14890a, self.data);
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
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getNonce() {
        return this.nonce;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final b0 getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component6$gotrue_kt_release, reason: from getter */
    public final SupabaseSerializer getSerializer() {
        return this.serializer;
    }

    public final UserUpdateBuilder copy(String email, String password, String phone, String nonce, b0 data, SupabaseSerializer serializer) {
        return new UserUpdateBuilder(email, password, phone, nonce, data, serializer);
    }

    public final <T> void data(T data) {
        getSerializer();
        a aVar = b.f14877d;
        p.h();
        throw null;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserUpdateBuilder)) {
            return false;
        }
        UserUpdateBuilder userUpdateBuilder = (UserUpdateBuilder) other;
        return p.a(this.email, userUpdateBuilder.email) && p.a(this.password, userUpdateBuilder.password) && p.a(this.phone, userUpdateBuilder.phone) && p.a(this.nonce, userUpdateBuilder.nonce) && p.a(this.data, userUpdateBuilder.data) && p.a(this.serializer, userUpdateBuilder.serializer);
    }

    public final b0 getData() {
        return this.data;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final SupabaseSerializer getSerializer() {
        return this.serializer;
    }

    public int hashCode() {
        String str = this.email;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.password;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.phone;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.nonce;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        b0 b0Var = this.data;
        return this.serializer.hashCode() + ((iHashCode4 + (b0Var != null ? b0Var.f14881i.hashCode() : 0)) * 31);
    }

    public final void setData(b0 b0Var) {
        this.data = b0Var;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final void setNonce(String str) {
        this.nonce = str;
    }

    public final void setPassword(String str) {
        this.password = str;
    }

    public final void setPhone(String str) {
        this.phone = str;
    }

    public final void setSerializer(SupabaseSerializer supabaseSerializer) {
        this.serializer = supabaseSerializer;
    }

    public String toString() {
        return "UserUpdateBuilder(email=" + this.email + ", password=" + this.password + ", phone=" + this.phone + ", nonce=" + this.nonce + ", data=" + this.data + ", serializer=" + this.serializer + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    @e
    public /* synthetic */ UserUpdateBuilder(int i10, String str, String str2, @q("phone") String str3, String str4, b0 b0Var, l1 l1Var) {
        b bVar = null;
        Object[] objArr = 0;
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
            this.phone = null;
        } else {
            this.phone = str3;
        }
        if ((i10 & 8) == 0) {
            this.nonce = null;
        } else {
            this.nonce = str4;
        }
        if ((i10 & 16) == 0) {
            this.data = null;
        } else {
            this.data = b0Var;
        }
        this.serializer = new KotlinXSerializer(bVar, 1, objArr == true ? 1 : 0);
    }

    public final void data(l<? super c0, t0> builder) {
        c0 c0Var = new c0();
        builder.invoke(c0Var);
        setData(c0Var.a());
    }

    public UserUpdateBuilder(String str, String str2, String str3, String str4, b0 b0Var, SupabaseSerializer supabaseSerializer) {
        this.email = str;
        this.password = str2;
        this.phone = str3;
        this.nonce = str4;
        this.data = b0Var;
        this.serializer = supabaseSerializer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ UserUpdateBuilder(String str, String str2, String str3, String str4, b0 b0Var, SupabaseSerializer supabaseSerializer, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : b0Var, (i10 & 32) != 0 ? new KotlinXSerializer(null, 1, 0 == true ? 1 : 0) : supabaseSerializer);
    }
}
