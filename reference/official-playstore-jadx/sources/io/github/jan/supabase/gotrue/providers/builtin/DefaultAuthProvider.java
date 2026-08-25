package io.github.jan.supabase.gotrue.providers.builtin;

import ab.g;
import androidx.compose.material3.MenuKt;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import bb.c;
import cb.l1;
import db.b0;
import db.e0;
import f4.f;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.gotrue.providers.AuthProvider;
import io.github.jan.supabase.gotrue.providers.builtin.Email;
import io.github.jan.supabase.gotrue.providers.builtin.IDToken;
import io.github.jan.supabase.gotrue.providers.builtin.Phone;
import io.github.jan.supabase.gotrue.user.UserSession;
import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.d;
import r7.a;
import r7.l;
import r7.p;
import x6.e;
import x6.s;
import x6.t0;
import ya.h;
import ya.n;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003:\u0001\u001eJa\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00062\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0019\u0010\u0010\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e¢\u0006\u0002\b\u000fH\u0096@¢\u0006\u0004\b\u0011\u0010\u0012Jc\u0010\u0013\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00062\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0019\u0010\u0010\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e¢\u0006\u0002\b\u000fH\u0096@¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00020\u0014H'¢\u0006\u0004\b\u0016\u0010\u0017J(\u0010\u0019\u001a\u00020\u00142\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\u000e¢\u0006\u0002\b\u000fH'¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0001\u0003\u001f !¨\u0006\""}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider;", "C", "R", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lkotlin/Function2;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Ld7/d;", "Lx6/t0;", "", "onSuccess", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/n;", "config", "login", "(Lio/github/jan/supabase/SupabaseClient;Lr7/p;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signUp", "Ldb/b0;", "json", "decodeResult", "(Ldb/b0;)Ljava/lang/Object;", "credentials", "encodeCredentials", "(Lr7/l;)Ldb/b0;", "getGrantType", "()Ljava/lang/String;", "grantType", "Config", "Lio/github/jan/supabase/gotrue/providers/builtin/Email;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface DefaultAuthProvider<C, R> extends AuthProvider<C, R> {

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 !2\u00020\u0001:\u0001!B!\b\u0004\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007B1\b\u0017\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\fJ(\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010HÇ\u0001¢\u0006\u0004\b\u0013\u0010\u0014R*\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0003\u0010\u0015\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u0082\u0001\u0003\"#$¨\u0006%"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider$Config;", "", "", "captchaToken", "Ldb/b0;", "data", "<init>", "(Ljava/lang/String;Ldb/b0;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ldb/b0;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self", "(Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider$Config;Lbb/c;Lab/g;)V", "Ljava/lang/String;", "getCaptchaToken", "()Ljava/lang/String;", "setCaptchaToken", "(Ljava/lang/String;)V", "getCaptchaToken$annotations", "()V", "Ldb/b0;", "getData", "()Ldb/b0;", "setData", "(Ldb/b0;)V", "Companion", "Lio/github/jan/supabase/gotrue/providers/builtin/Email$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static abstract class Config {
        private String captchaToken;
        private b0 data;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final s<h<Object>> $cachedSerializer$delegate = f.o(2, Companion.AnonymousClass1.INSTANCE);

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider$Config$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider$Config;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {

            /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider$Config$Companion$1, reason: invalid class name */
            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements a<h<Object>> {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                public AnonymousClass1() {
                    super(0);
                }

                @Override // r7.a
                public final h<Object> invoke() {
                    m0 m0Var = l0.f19747a;
                    return new n(m0Var.b(Config.class), new d[]{m0Var.b(Email.Config.class), m0Var.b(IDToken.Config.class), m0Var.b(Phone.Config.class)}, new h[]{Email$Config$$serializer.INSTANCE, IDToken$Config$$serializer.INSTANCE, Phone$Config$$serializer.INSTANCE}, new Annotation[0]);
                }
            }

            public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
                this();
            }

            private final /* synthetic */ h get$cachedSerializer() {
                return (h) Config.$cachedSerializer$delegate.getValue();
            }

            public final h<Config> serializer() {
                return get$cachedSerializer();
            }

            private Companion() {
            }
        }

        public /* synthetic */ Config(String str, b0 b0Var, kotlin.jvm.internal.h hVar) {
            this(str, b0Var);
        }

        @r(with = CaptchaTokenSerializer.class)
        @q("gotrue_meta_security")
        public static /* synthetic */ void getCaptchaToken$annotations() {
        }

        @q7.n
        public static final /* synthetic */ void write$Self(Config self, c output, g serialDesc) {
            if (output.C() || self.captchaToken != null) {
                output.m(serialDesc, 0, CaptchaTokenSerializer.INSTANCE, self.captchaToken);
            }
            if (!output.C() && self.data == null) {
                return;
            }
            output.m(serialDesc, 1, e0.f14890a, self.data);
        }

        public final String getCaptchaToken() {
            return this.captchaToken;
        }

        public final b0 getData() {
            return this.data;
        }

        public final void setCaptchaToken(String str) {
            this.captchaToken = str;
        }

        public final void setData(b0 b0Var) {
            this.data = b0Var;
        }

        @e
        public /* synthetic */ Config(int i10, @r(with = CaptchaTokenSerializer.class) @q("gotrue_meta_security") String str, b0 b0Var, l1 l1Var) {
            if ((i10 & 1) == 0) {
                this.captchaToken = null;
            } else {
                this.captchaToken = str;
            }
            if ((i10 & 2) == 0) {
                this.data = null;
            } else {
                this.data = b0Var;
            }
        }

        private Config(String str, b0 b0Var) {
            this.captchaToken = str;
            this.data = b0Var;
        }

        public /* synthetic */ Config(String str, b0 b0Var, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : b0Var, null);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Code restructure failed: missing block: B:29:0x00b9, code lost:
        
            if (r6.invoke(r11, r0) == r5) goto L30;
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00af  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static <C, R> java.lang.Object login(io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider<C, R> r6, io.github.jan.supabase.SupabaseClient r7, r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r8, java.lang.String r9, r7.l<? super C, x6.t0> r10, d7.d<? super x6.t0> r11) {
            /*
                Method dump skipped, instruction units count: 207
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider.DefaultImpls.login(io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider, io.github.jan.supabase.SupabaseClient, r7.p, java.lang.String, r7.l, d7.d):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x00e5  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00fa  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x012c  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0155 A[PHI: r9 r10 r14
          0x0155: PHI (r9v11 r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object>) = 
          (r9v10 r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object>)
          (r9v20 r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object>)
         binds: [B:47:0x0152, B:18:0x0039] A[DONT_GENERATE, DONT_INLINE]
          0x0155: PHI (r10v11 io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider<C, R>) = 
          (r10v10 io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider<C, R>)
          (r10v21 io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider<C, R>)
         binds: [B:47:0x0152, B:18:0x0039] A[DONT_GENERATE, DONT_INLINE]
          0x0155: PHI (r14v14 java.lang.Object) = (r14v13 java.lang.Object), (r14v1 java.lang.Object) binds: [B:47:0x0152, B:18:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0157  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0187  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x018f  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static <C, R> java.lang.Object signUp(io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider<C, R> r9, io.github.jan.supabase.SupabaseClient r10, r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r11, java.lang.String r12, r7.l<? super C, x6.t0> r13, d7.d<? super R> r14) {
            /*
                Method dump skipped, instruction units count: 413
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider.DefaultImpls.signUp(io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider, io.github.jan.supabase.SupabaseClient, r7.p, java.lang.String, r7.l, d7.d):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider$login$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @f7.e(c = "io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider$DefaultImpls", f = "DefaultAuthProvider.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 122, 64}, m = "login")
    public static final class AnonymousClass1<C, R> extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.login(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider$signUp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @f7.e(c = "io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider$DefaultImpls", f = "DefaultAuthProvider.kt", l = {81, MenuKt.InTransitionDuration, 126, 101}, m = "signUp")
    public static final class C17021<C, R> extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C17021(d7.d<? super C17021> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.signUp(null, null, null, null, null, this);
        }
    }

    @SupabaseInternal
    R decodeResult(b0 json);

    @SupabaseInternal
    b0 encodeCredentials(l<? super C, t0> credentials);

    String getGrantType();

    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    Object login(SupabaseClient supabaseClient, p<? super UserSession, ? super d7.d<? super t0>, ? extends Object> pVar, String str, l<? super C, t0> lVar, d7.d<? super t0> dVar);

    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    Object signUp(SupabaseClient supabaseClient, p<? super UserSession, ? super d7.d<? super t0>, ? extends Object> pVar, String str, l<? super C, t0> lVar, d7.d<? super R> dVar);
}
