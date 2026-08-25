package io.github.jan.supabase.gotrue.providers;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.gotrue.AuthKt;
import io.github.jan.supabase.gotrue.Utils_androidKt;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.ktor.http.ContentDisposition;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 \u00182\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0004\u0010\u0005Ja\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0019\u0010\u0011\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0002\b\u0010H\u0096@¢\u0006\u0004\b\u0012\u0010\u0013Ja\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0019\u0010\u0011\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0002\b\u0010H\u0096@¢\u0006\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/OAuthProvider;", "Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfig;", "Lx6/t0;", "<init>", "()V", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lkotlin/Function2;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Ld7/d;", "", "onSuccess", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/n;", "config", "login", "(Lio/github/jan/supabase/SupabaseClient;Lr7/p;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signUp", "getName", "()Ljava/lang/String;", ContentDisposition.Parameters.Name, "Companion", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class OAuthProvider implements AuthProvider<ExternalAuthConfig, t0> {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.OAuthProvider$login$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.providers.OAuthProvider$login$2", f = "OAuthProvider.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<String, d<? super String>, Object> {
        final /* synthetic */ ExternalAuthConfig $authConfig;
        final /* synthetic */ SupabaseClient $supabaseClient;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OAuthProvider this$0;

        /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.OAuthProvider$login$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
        public static final class AnonymousClass1 extends r implements l<ExternalAuthConfigDefaults, t0> {
            final /* synthetic */ ExternalAuthConfig $authConfig;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(ExternalAuthConfig externalAuthConfig) {
                super(1);
                this.$authConfig = externalAuthConfig;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ExternalAuthConfigDefaults) obj);
                return t0.f22605a;
            }

            public final void invoke(ExternalAuthConfigDefaults externalAuthConfigDefaults) {
                externalAuthConfigDefaults.getScopes().addAll(this.$authConfig.getScopes());
                externalAuthConfigDefaults.getQueryParams().putAll(this.$authConfig.getQueryParams());
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SupabaseClient supabaseClient, OAuthProvider oAuthProvider, ExternalAuthConfig externalAuthConfig, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$supabaseClient = supabaseClient;
            this.this$0 = oAuthProvider;
            this.$authConfig = externalAuthConfig;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$supabaseClient, this.this$0, this.$authConfig, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return Auth.DefaultImpls.oAuthUrl$default(AuthKt.getAuth(this.$supabaseClient), this.this$0, (String) this.L$0, null, new AnonymousClass1(this.$authConfig), 4, null);
        }

        @Override // r7.p
        public final Object invoke(String str, d<? super String> dVar) {
            return ((AnonymousClass2) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.OAuthProvider$signUp$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.providers.OAuthProvider", f = "OAuthProvider.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS}, m = "signUp$suspendImpl")
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
            return OAuthProvider.signUp$suspendImpl(OAuthProvider.this, null, null, null, null, this);
        }
    }

    public static /* synthetic */ Object login$suspendImpl(OAuthProvider oAuthProvider, SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super ExternalAuthConfig, t0> lVar, d<? super t0> dVar) {
        ExternalAuthConfig externalAuthConfig = new ExternalAuthConfig();
        if (lVar != null) {
            lVar.invoke(externalAuthConfig);
        }
        Object objStartExternalAuth = Utils_androidKt.startExternalAuth(AuthKt.getAuth(supabaseClient), str, new AnonymousClass2(supabaseClient, oAuthProvider, externalAuthConfig, null), pVar, dVar);
        return objStartExternalAuth == a.f15033i ? objStartExternalAuth : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object signUp$suspendImpl(io.github.jan.supabase.gotrue.providers.OAuthProvider r4, io.github.jan.supabase.SupabaseClient r5, r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r6, java.lang.String r7, r7.l<? super io.github.jan.supabase.gotrue.providers.ExternalAuthConfig, x6.t0> r8, d7.d<? super x6.t0> r9) {
        /*
            boolean r0 = r9 instanceof io.github.jan.supabase.gotrue.providers.OAuthProvider.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            io.github.jan.supabase.gotrue.providers.OAuthProvider$signUp$1 r0 = (io.github.jan.supabase.gotrue.providers.OAuthProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r9 = r0
            goto L1a
        L14:
            io.github.jan.supabase.gotrue.providers.OAuthProvider$signUp$1 r0 = new io.github.jan.supabase.gotrue.providers.OAuthProvider$signUp$1
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r0 = r9.result
            int r1 = r9.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r0)
            goto L3d
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            k2.c.G(r0)
            r9.label = r2
            java.lang.Object r4 = r4.login(r5, r6, r7, r8, r9)
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L3d
            return r5
        L3d:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.providers.OAuthProvider.signUp$suspendImpl(io.github.jan.supabase.gotrue.providers.OAuthProvider, io.github.jan.supabase.SupabaseClient, r7.p, java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    public abstract String getName();

    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object login(SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super ExternalAuthConfig, t0> lVar, d<? super t0> dVar) {
        return login$suspendImpl(this, supabaseClient, pVar, str, lVar, dVar);
    }

    @Override // io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object signUp(SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super ExternalAuthConfig, t0> lVar, d<? super t0> dVar) {
        return signUp$suspendImpl(this, supabaseClient, pVar, str, lVar, dVar);
    }
}
