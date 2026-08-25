package io.github.jan.supabase.gotrue;

import d7.d;
import e7.a;
import fi.iki.elonen.f;
import i6.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0096@¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0004\b\r\u0010\fR\u001a\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u0012\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lio/github/jan/supabase/gotrue/SettingsCodeVerifierCache;", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "Lh6/d;", "settings", "<init>", "(Lh6/d;)V", "", "codeVerifier", "Lx6/t0;", "saveCodeVerifier", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "loadCodeVerifier", "(Ld7/d;)Ljava/lang/Object;", "deleteCodeVerifier", "Li6/c;", "suspendSettings", "Li6/c;", "getSuspendSettings$annotations", "()V", "Companion", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SettingsCodeVerifierCache implements CodeVerifierCache {
    public static final String SETTINGS_KEY = "supabase_code_verifier";
    private final c suspendSettings;

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsCodeVerifierCache() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private static /* synthetic */ void getSuspendSettings$annotations() {
    }

    @Override // io.github.jan.supabase.gotrue.CodeVerifierCache
    public Object deleteCodeVerifier(d<? super t0> dVar) {
        Object objX = this.suspendSettings.x(SETTINGS_KEY, dVar);
        return objX == a.f15033i ? objX : t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.CodeVerifierCache
    public Object loadCodeVerifier(d<? super String> dVar) {
        return this.suspendSettings.n(SETTINGS_KEY, dVar);
    }

    @Override // io.github.jan.supabase.gotrue.CodeVerifierCache
    public Object saveCodeVerifier(String str, d<? super t0> dVar) {
        Object objF = this.suspendSettings.f(SETTINGS_KEY, str, dVar);
        return objF == a.f15033i ? objF : t0.f22605a;
    }

    public SettingsCodeVerifierCache(h6.d dVar) {
        this.suspendSettings = new f(dVar, i6.a.f16232a);
    }

    public /* synthetic */ SettingsCodeVerifierCache(h6.d dVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? SettingsUtilKt.createDefaultSettings() : dVar);
    }
}
