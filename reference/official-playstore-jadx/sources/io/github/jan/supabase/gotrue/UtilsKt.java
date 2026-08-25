package io.github.jan.supabase.gotrue;

import d7.d;
import f7.e;
import f7.j;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.ktor.client.request.HttpRequestBuilder;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import q2.f;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0003\u001a1\u0010\u0007\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\u000b\u001a\u00020\u0005*\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/github/jan/supabase/gotrue/Auth;", "", "fragment", "Lkotlin/Function1;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Lx6/t0;", "onSessionSuccess", "parseFragmentAndImportSession", "(Lio/github/jan/supabase/gotrue/Auth;Ljava/lang/String;Lr7/l;)V", "Lio/ktor/client/request/HttpRequestBuilder;", "url", "redirectTo", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;)V", "message", "", "invalidArg", "(Ljava/lang/String;)Ljava/lang/Void;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.UtilsKt$parseFragmentAndImportSession$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserSession;", "it", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/gotrue/user/UserSession;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass1 extends r implements l<UserSession, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(UserSession userSession) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((UserSession) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.UtilsKt$parseFragmentAndImportSession$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<String> {
        final /* synthetic */ String $fragment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str) {
            super(0);
            this.$fragment = str;
        }

        @Override // r7.a
        public final String invoke() {
            return "Parsing deeplink fragment " + this.$fragment;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.UtilsKt$parseFragmentAndImportSession$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.UtilsKt$parseFragmentAndImportSession$3", f = "Utils.kt", l = {16, 19}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ l<UserSession, t0> $onSessionSuccess;
        final /* synthetic */ UserSession $session;
        final /* synthetic */ Auth $this_parseFragmentAndImportSession;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Auth auth, UserSession userSession, l<? super UserSession, t0> lVar, d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$this_parseFragmentAndImportSession = auth;
            this.$session = userSession;
            this.$onSessionSuccess = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass3(this.$this_parseFragmentAndImportSession, this.$session, this.$onSessionSuccess, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x005e, code lost:
        
            if (io.github.jan.supabase.gotrue.Auth.DefaultImpls.importSession$default(r0, r0, false, r20, 2, null) == r6) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                r20 = this;
                r3 = r20
                int r0 = r3.label
                r1 = 2
                r2 = 1
                e7.a r6 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L1a
                if (r0 != r1) goto L12
                k2.c.G(r21)
                goto L61
            L12:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L1a:
                k2.c.G(r21)
                r0 = r21
                goto L34
            L20:
                k2.c.G(r21)
                io.github.jan.supabase.gotrue.Auth r0 = r3.$this_parseFragmentAndImportSession
                io.github.jan.supabase.gotrue.user.UserSession r4 = r3.$session
                java.lang.String r4 = r4.getAccessToken()
                r3.label = r2
                java.lang.Object r0 = r0.retrieveUser(r4, r3)
                if (r0 != r6) goto L34
                goto L60
            L34:
                r15 = r0
                io.github.jan.supabase.gotrue.user.UserInfo r15 = (io.github.jan.supabase.gotrue.user.UserInfo) r15
                io.github.jan.supabase.gotrue.user.UserSession r7 = r3.$session
                r18 = 447(0x1bf, float:6.26E-43)
                r19 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r14 = 0
                r16 = 0
                r17 = 0
                io.github.jan.supabase.gotrue.user.UserSession r0 = io.github.jan.supabase.gotrue.user.UserSession.copy$default(r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19)
                r7.l<io.github.jan.supabase.gotrue.user.UserSession, x6.t0> r2 = r3.$onSessionSuccess
                r2.invoke(r0)
                r2 = r0
                io.github.jan.supabase.gotrue.Auth r0 = r3.$this_parseFragmentAndImportSession
                r3.label = r1
                r1 = r2
                r2 = 0
                r4 = 2
                r5 = 0
                java.lang.Object r0 = io.github.jan.supabase.gotrue.Auth.DefaultImpls.importSession$default(r0, r1, r2, r3, r4, r5)
                if (r0 != r6) goto L61
            L60:
                return r6
            L61:
                x6.t0 r0 = x6.t0.f22605a
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.UtilsKt.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final Void invalidArg(String str) {
        throw new IllegalArgumentException(str);
    }

    @SupabaseInternal
    public static final void parseFragmentAndImportSession(Auth auth, String str, l<? super UserSession, t0> lVar) {
        f.A0(f.f21327l, "Auth", new AnonymousClass2(str));
        m0.p(((AuthImpl) auth).getAuthScope(), null, 0, new AnonymousClass3(auth, AuthExtensionsKt.parseSessionFromFragment(auth, str), lVar, null), 3);
    }

    public static /* synthetic */ void parseFragmentAndImportSession$default(Auth auth, String str, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        parseFragmentAndImportSession(auth, str, lVar);
    }

    @SupabaseInternal
    public static final void redirectTo(HttpRequestBuilder httpRequestBuilder, String str) {
        httpRequestBuilder.getUrl().getParameters().set("redirect_to", str);
    }
}
