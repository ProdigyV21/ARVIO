package io.github.jan.supabase.gotrue;

import android.net.Uri;
import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.SupabaseClient;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0080@¢\u0006\u0004\b\u0004\u0010\u0005\u001aw\u0010\u0011\u001a\u00020\u0003*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u000123\u0010\u000e\u001a/\b\u0001\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\b2\"\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\bH\u0080@¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/github/jan/supabase/SupabaseClient;", "", "url", "Lx6/t0;", "openExternalUrl", "(Lio/github/jan/supabase/SupabaseClient;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/Auth;", "redirectUrl", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "redirectTo", "Ld7/d;", "", "getUrl", "Lio/github/jan/supabase/gotrue/user/UserSession;", "onSessionSuccess", "startExternalAuth", "(Lio/github/jan/supabase/gotrue/Auth;Ljava/lang/String;Lr7/p;Lr7/p;Ld7/d;)Ljava/lang/Object;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Utils_androidKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.Utils_androidKt$startExternalAuth$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.Utils_androidKt", f = "Utils.android.kt", l = {17, 17}, m = "startExternalAuth")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Utils_androidKt.startExternalAuth(null, null, null, null, this);
        }
    }

    public static final Object openExternalUrl(SupabaseClient supabaseClient, String str, d<? super t0> dVar) {
        AndroidKt.openUrl(Uri.parse(str), AuthKt.getAuth(supabaseClient).getConfig().getDefaultExternalAuthAction());
        return t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
    
        if (openExternalUrl(r4, (java.lang.String) r8, r7) == r3) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object startExternalAuth(io.github.jan.supabase.gotrue.Auth r4, java.lang.String r5, r7.p<? super java.lang.String, ? super d7.d<? super java.lang.String>, ? extends java.lang.Object> r6, r7.p<? super io.github.jan.supabase.gotrue.user.UserSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r7 = r8 instanceof io.github.jan.supabase.gotrue.Utils_androidKt.AnonymousClass1
            if (r7 == 0) goto L13
            r7 = r8
            io.github.jan.supabase.gotrue.Utils_androidKt$startExternalAuth$1 r7 = (io.github.jan.supabase.gotrue.Utils_androidKt.AnonymousClass1) r7
            int r0 = r7.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r7.label = r0
            goto L18
        L13:
            io.github.jan.supabase.gotrue.Utils_androidKt$startExternalAuth$1 r7 = new io.github.jan.supabase.gotrue.Utils_androidKt$startExternalAuth$1
            r7.<init>(r8)
        L18:
            java.lang.Object r8 = r7.result
            int r0 = r7.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L32
            if (r0 != r1) goto L2a
            k2.c.G(r8)
            goto L5a
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            java.lang.Object r4 = r7.L$0
            io.github.jan.supabase.SupabaseClient r4 = (io.github.jan.supabase.SupabaseClient) r4
            k2.c.G(r8)
            goto L4c
        L3a:
            k2.c.G(r8)
            io.github.jan.supabase.SupabaseClient r4 = r4.getSupabaseClient()
            r7.L$0 = r4
            r7.label = r2
            java.lang.Object r8 = r6.invoke(r5, r7)
            if (r8 != r3) goto L4c
            goto L59
        L4c:
            java.lang.String r8 = (java.lang.String) r8
            r5 = 0
            r7.L$0 = r5
            r7.label = r1
            java.lang.Object r4 = openExternalUrl(r4, r8, r7)
            if (r4 != r3) goto L5a
        L59:
            return r3
        L5a:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.Utils_androidKt.startExternalAuth(io.github.jan.supabase.gotrue.Auth, java.lang.String, r7.p, r7.p, d7.d):java.lang.Object");
    }
}
