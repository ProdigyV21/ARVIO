package io.github.jan.supabase.gotrue;

import androidx.media3.exoplayer.RendererCapabilities;
import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.plugins.PluginManager;
import io.github.jan.supabase.plugins.SupabasePlugin;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@¢\u0006\u0004\b\u0004\u0010\u0005\"\u0015\u0010\t\u001a\u00020\u0000*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/gotrue/Auth;", "", "jwt", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "tryToGetUser", "(Lio/github/jan/supabase/gotrue/Auth;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/SupabaseClient;", "getAuth", "(Lio/github/jan/supabase/SupabaseClient;)Lio/github/jan/supabase/gotrue/Auth;", "auth", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AuthKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthKt$tryToGetUser$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.AuthKt", f = "Auth.kt", l = {RendererCapabilities.DECODER_SUPPORT_MASK}, m = "tryToGetUser")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthKt.tryToGetUser(null, null, this);
        }
    }

    public static final Auth getAuth(SupabaseClient supabaseClient) {
        PluginManager pluginManager = supabaseClient.getPluginManager();
        Auth.Companion companion = Auth.INSTANCE;
        SupabasePlugin supabasePlugin = pluginManager.getInstalledPlugins().get(companion.getKey());
        if (!(supabasePlugin instanceof Auth)) {
            supabasePlugin = null;
        }
        Auth auth = (Auth) supabasePlugin;
        if (auth != null) {
            return auth;
        }
        StringBuilder sb2 = new StringBuilder("Plugin ");
        sb2.append(companion.getKey());
        sb2.append(" not installed or not of type ");
        m0 m0Var = l0.f19747a;
        sb2.append(m0Var.b(Auth.class).r());
        sb2.append(". Consider installing ");
        sb2.append(m0Var.b(Auth.class).r());
        sb2.append(" within your SupabaseClientBuilder");
        throw new IllegalStateException(sb2.toString().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object tryToGetUser(io.github.jan.supabase.gotrue.Auth r4, java.lang.String r5, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r6) {
        /*
            boolean r0 = r6 instanceof io.github.jan.supabase.gotrue.AuthKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.github.jan.supabase.gotrue.AuthKt$tryToGetUser$1 r0 = (io.github.jan.supabase.gotrue.AuthKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.AuthKt$tryToGetUser$1 r0 = new io.github.jan.supabase.gotrue.AuthKt$tryToGetUser$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r6)     // Catch: java.lang.Exception -> L25
            goto L3d
        L25:
            r4 = move-exception
            goto L40
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            k2.c.G(r6)
            r0.label = r2     // Catch: java.lang.Exception -> L25
            java.lang.Object r6 = r4.retrieveUser(r5, r0)     // Catch: java.lang.Exception -> L25
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L3d
            return r4
        L3d:
            io.github.jan.supabase.gotrue.user.UserInfo r6 = (io.github.jan.supabase.gotrue.user.UserInfo) r6     // Catch: java.lang.Exception -> L25
            return r6
        L40:
            q2.f r5 = q2.f.f21327l
            java.lang.Object r6 = r5.f6607i
            r6 = 5
            int r0 = h.f0.a(r2, r6)
            if (r0 > 0) goto L52
            java.lang.String r0 = "Couldn't retrieve user using your custom jwt token. If you use the project secret ignore this message"
            java.lang.String r1 = "Auth"
            r5.g0(r6, r1, r4, r0)
        L52:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.AuthKt.tryToGetUser(io.github.jan.supabase.gotrue.Auth, java.lang.String, d7.d):java.lang.Object");
    }
}
