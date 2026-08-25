package io.github.jan.supabase.gotrue.providers.builtin;

import androidx.compose.foundation.c;
import cb.b1;
import cb.l1;
import d7.d;
import e7.a;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.providers.AuthProvider;
import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.e;
import x6.t0;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001e\u001fB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005Ja\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0019\u0010\u0011\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0002\b\u0010H\u0096@¢\u0006\u0004\b\u0012\u0010\u0013Ja\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0019\u0010\u0011\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0002\b\u0010H\u0096@¢\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/SSO;", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;", "Lx6/t0;", "<init>", "()V", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lkotlin/Function2;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Ld7/d;", "", "onSuccess", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/n;", "config", "login", "(Lio/github/jan/supabase/SupabaseClient;Lr7/p;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signUp", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Config", "Result", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class SSO implements AuthProvider<Config, t0> {
    public static final SSO INSTANCE = new SSO();

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;", "", "providerId", "", "captchaToken", "domain", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCaptchaToken", "()Ljava/lang/String;", "setCaptchaToken", "(Ljava/lang/String;)V", "getDomain", "setDomain", "getProviderId", "setProviderId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Config {
        private String captchaToken;
        private String domain;
        private String providerId;

        public Config() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Config copy$default(Config config, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = config.providerId;
            }
            if ((i10 & 2) != 0) {
                str2 = config.captchaToken;
            }
            if ((i10 & 4) != 0) {
                str3 = config.domain;
            }
            return config.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProviderId() {
            return this.providerId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCaptchaToken() {
            return this.captchaToken;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDomain() {
            return this.domain;
        }

        public final Config copy(String providerId, String captchaToken, String domain) {
            return new Config(providerId, captchaToken, domain);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return p.a(this.providerId, config.providerId) && p.a(this.captchaToken, config.captchaToken) && p.a(this.domain, config.domain);
        }

        public final String getCaptchaToken() {
            return this.captchaToken;
        }

        public final String getDomain() {
            return this.domain;
        }

        public final String getProviderId() {
            return this.providerId;
        }

        public int hashCode() {
            String str = this.providerId;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.captchaToken;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.domain;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public final void setCaptchaToken(String str) {
            this.captchaToken = str;
        }

        public final void setDomain(String str) {
            this.domain = str;
        }

        public final void setProviderId(String str) {
            this.providerId = str;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Config(providerId=");
            sb2.append(this.providerId);
            sb2.append(", captchaToken=");
            sb2.append(this.captchaToken);
            sb2.append(", domain=");
            return c.u(sb2, this.domain, ')');
        }

        public Config(String str, String str2, String str3) {
            this.providerId = str;
            this.captchaToken = str2;
            this.domain = str3;
        }

        public /* synthetic */ Config(String str, String str2, String str3, int i10, h hVar) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002\"!B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0011\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0004\u0010\nJ(\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eHÁ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0015J\u0010\u0010\u0019\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u0015¨\u0006#"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result;", "", "", "url", "<init>", "(Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUrl", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static final /* data */ class Result {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String url;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Result;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final ya.h<Result> serializer() {
                return SSO$Result$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        @e
        public /* synthetic */ Result(int i10, String str, l1 l1Var) {
            if (1 == (i10 & 1)) {
                this.url = str;
            } else {
                b1.h(i10, 1, SSO$Result$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ Result copy$default(Result result, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = result.url;
            }
            return result.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final Result copy(String url) {
            return new Result(url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Result) && p.a(this.url, ((Result) other).url);
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return this.url.hashCode();
        }

        public String toString() {
            return c.u(new StringBuilder("Result(url="), this.url, ')');
        }

        public Result(String str) {
            this.url = str;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.SSO$signUp$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @f7.e(c = "io.github.jan.supabase.gotrue.providers.builtin.SSO", f = "SSO.kt", l = {54}, m = "signUp")
    public static final class AnonymousClass1 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SSO.this.signUp(null, null, null, null, this);
        }
    }

    private SSO() {
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof SSO);
    }

    public int hashCode() {
        return 771026512;
    }

    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object login(SupabaseClient supabaseClient, r7.p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super Config, t0> lVar, d<? super t0> dVar) {
        Object objSignUp = signUp(supabaseClient, pVar, str, lVar, dVar);
        return objSignUp == a.f15033i ? objSignUp : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object signUp(io.github.jan.supabase.SupabaseClient r5, r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r6, java.lang.String r7, r7.l<? super io.github.jan.supabase.gotrue.providers.builtin.SSO.Config, x6.t0> r8, d7.d<? super x6.t0> r9) {
        /*
            r4 = this;
            boolean r0 = r9 instanceof io.github.jan.supabase.gotrue.providers.builtin.SSO.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.github.jan.supabase.gotrue.providers.builtin.SSO$signUp$1 r0 = (io.github.jan.supabase.gotrue.providers.builtin.SSO.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.providers.builtin.SSO$signUp$1 r0 = new io.github.jan.supabase.gotrue.providers.builtin.SSO$signUp$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r9)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r9)
            r0.label = r2
            java.lang.Object r5 = io.github.jan.supabase.gotrue.providers.builtin.SSOKt.loginWithSSO(r5, r6, r7, r8, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L3b
            return r6
        L3b:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.providers.builtin.SSO.signUp(io.github.jan.supabase.SupabaseClient, r7.p, java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    public String toString() {
        return "SSO";
    }
}
