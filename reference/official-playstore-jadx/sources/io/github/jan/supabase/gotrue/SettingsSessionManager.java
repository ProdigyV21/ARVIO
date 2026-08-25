package io.github.jan.supabase.gotrue;

import d7.d;
import db.b;
import e7.a;
import f7.e;
import fi.iki.elonen.f;
import i6.c;
import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0096@¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0004\b\r\u0010\fR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lio/github/jan/supabase/gotrue/SettingsSessionManager;", "Lio/github/jan/supabase/gotrue/SessionManager;", "Lh6/d;", "settings", "<init>", "(Lh6/d;)V", "Lio/github/jan/supabase/gotrue/user/UserSession;", SettingsSessionManager.SETTINGS_KEY, "Lx6/t0;", "saveSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ld7/d;)Ljava/lang/Object;", "loadSession", "(Ld7/d;)Ljava/lang/Object;", "deleteSession", "Li6/c;", "suspendSettings", "Li6/c;", "Companion", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SettingsSessionManager implements SessionManager {
    public static final String SETTINGS_KEY = "session";
    private final c suspendSettings;

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.SettingsSessionManager$loadSession$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.SettingsSessionManager", f = "SettingsSessionManager.kt", l = {25}, m = "loadSession")
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
            return SettingsSessionManager.this.loadSession(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsSessionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // io.github.jan.supabase.gotrue.SessionManager
    public Object deleteSession(d<? super t0> dVar) {
        Object objX = this.suspendSettings.x(SETTINGS_KEY, dVar);
        return objX == a.f15033i ? objX : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.SessionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadSession(d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.github.jan.supabase.gotrue.SettingsSessionManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            io.github.jan.supabase.gotrue.SettingsSessionManager$loadSession$1 r0 = (io.github.jan.supabase.gotrue.SettingsSessionManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.SettingsSessionManager$loadSession$1 r0 = new io.github.jan.supabase.gotrue.SettingsSessionManager$loadSession$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3f
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            i6.c r5 = r4.suspendSettings
            r0.label = r2
            java.lang.String r1 = "session"
            java.lang.Object r5 = r5.n(r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3f
            return r0
        L3f:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 != 0) goto L44
            goto L6f
        L44:
            db.b r0 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: java.lang.Exception -> L5c
            r0.getClass()     // Catch: java.lang.Exception -> L5c
            io.github.jan.supabase.gotrue.user.UserSession$Companion r1 = io.github.jan.supabase.gotrue.user.UserSession.INSTANCE     // Catch: java.lang.Exception -> L5c
            ya.h r1 = r1.serializer()     // Catch: java.lang.Exception -> L5c
            ya.h r1 = a.a.Q(r1)     // Catch: java.lang.Exception -> L5c
            java.lang.Object r5 = r0.c(r5, r1)     // Catch: java.lang.Exception -> L5c
            io.github.jan.supabase.gotrue.user.UserSession r5 = (io.github.jan.supabase.gotrue.user.UserSession) r5     // Catch: java.lang.Exception -> L5c
            return r5
        L5c:
            r5 = move-exception
            q2.f r0 = q2.f.f21327l
            java.lang.Object r1 = r0.f6607i
            r1 = 5
            int r2 = h.f0.a(r2, r1)
            if (r2 > 0) goto L6f
            java.lang.String r2 = "Failed to load session"
            java.lang.String r3 = "Auth"
            r0.g0(r1, r3, r5, r2)
        L6f:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.SettingsSessionManager.loadSession(d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.SessionManager
    public Object saveSession(UserSession userSession, d<? super t0> dVar) {
        c cVar = this.suspendSettings;
        b supabaseJson = io.github.jan.supabase.UtilsKt.getSupabaseJson();
        supabaseJson.getClass();
        Object objF = cVar.f(SETTINGS_KEY, supabaseJson.b(UserSession.INSTANCE.serializer(), userSession), dVar);
        return objF == a.f15033i ? objF : t0.f22605a;
    }

    public SettingsSessionManager(h6.d dVar) {
        this.suspendSettings = new f(dVar, i6.a.f16232a);
    }

    public /* synthetic */ SettingsSessionManager(h6.d dVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? SettingsUtilKt.createDefaultSettings() : dVar);
    }
}
