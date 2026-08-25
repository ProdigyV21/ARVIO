package io.github.jan.supabase.gotrue.providers.builtin;

import d7.d;
import db.b;
import db.b0;
import e7.a;
import f7.c;
import f7.e;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.gotrue.providers.AuthProvider;
import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005Ja\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0019\u0010\u0011\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0002\b\u0010H\u0096@¢\u0006\u0004\b\u0012\u0010\u0013Jc\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0019\u0010\u0011\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0002\b\u0010H\u0096@¢\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/OTP;", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Lio/github/jan/supabase/gotrue/providers/builtin/OTP$Config;", "Lx6/t0;", "<init>", "()V", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lkotlin/Function2;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Ld7/d;", "", "onSuccess", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/n;", "config", "login", "(Lio/github/jan/supabase/SupabaseClient;Lr7/p;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signUp", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Config", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class OTP implements AuthProvider<Config, t0> {
    public static final OTP INSTANCE = new OTP();

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.OTP$login$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.providers.builtin.OTP", f = "OTP.kt", l = {81, 113}, m = "login")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OTP.this.login(null, null, null, null, this);
        }
    }

    private OTP() {
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof OTP);
    }

    public int hashCode() {
        return 771022700;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0189, code lost:
    
        if (r3.request("otp", r7, r1) != r6) goto L59;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object login(io.github.jan.supabase.SupabaseClient r19, r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r20, java.lang.String r21, r7.l<? super io.github.jan.supabase.gotrue.providers.builtin.OTP.Config, x6.t0> r22, d7.d<? super x6.t0> r23) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.providers.builtin.OTP.login(io.github.jan.supabase.SupabaseClient, r7.p, java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object signUp(SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super Config, t0> lVar, d<? super t0> dVar) {
        Object objLogin = login(supabaseClient, pVar, str, lVar, dVar);
        return objLogin == a.f15033i ? objLogin : t0.f22605a;
    }

    public String toString() {
        return "OTP";
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ$\u0010\b\u001a\u00020\u000e\"\n\b\u0000\u0010\r\u0018\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0086\b¢\u0006\u0004\b\b\u0010\u000fR \u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010\u0010\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/OTP$Config;", "", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "", "email", "phone", "Ldb/b0;", "data", "", "createUser", "<init>", "(Lio/github/jan/supabase/SupabaseSerializer;Ljava/lang/String;Ljava/lang/String;Ldb/b0;Z)V", "T", "Lx6/t0;", "(Ljava/lang/Object;)V", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer$annotations", "()V", "Ljava/lang/String;", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getPhone", "setPhone", "Ldb/b0;", "getData", "()Ldb/b0;", "setData", "(Ldb/b0;)V", "Z", "getCreateUser", "()Z", "setCreateUser", "(Z)V", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Config {
        private boolean createUser;
        private b0 data;
        private String email;
        private String phone;
        private final SupabaseSerializer serializer;

        public Config(SupabaseSerializer supabaseSerializer, String str, String str2, b0 b0Var, boolean z) {
            this.serializer = supabaseSerializer;
            this.email = str;
            this.phone = str2;
            this.data = b0Var;
            this.createUser = z;
        }

        public static /* synthetic */ void getSerializer$annotations() {
        }

        public final <T> void data(T data) {
            getSerializer();
            db.a aVar = b.f14877d;
            kotlin.jvm.internal.p.h();
            throw null;
        }

        public final boolean getCreateUser() {
            return this.createUser;
        }

        public final b0 getData() {
            return this.data;
        }

        public final String getEmail() {
            return this.email;
        }

        public final String getPhone() {
            return this.phone;
        }

        public final SupabaseSerializer getSerializer() {
            return this.serializer;
        }

        public final void setCreateUser(boolean z) {
            this.createUser = z;
        }

        public final void setData(b0 b0Var) {
            this.data = b0Var;
        }

        public final void setEmail(String str) {
            this.email = str;
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public /* synthetic */ Config(SupabaseSerializer supabaseSerializer, String str, String str2, b0 b0Var, boolean z, int i10, h hVar) {
            this(supabaseSerializer, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : b0Var, (i10 & 16) != 0 ? false : z);
        }
    }
}
