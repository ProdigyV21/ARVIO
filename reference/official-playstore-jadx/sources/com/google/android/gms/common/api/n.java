package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.internal.a1;
import com.google.android.gms.common.api.internal.b1;
import com.google.android.gms.common.api.internal.c1;
import com.google.android.gms.common.api.internal.q0;
import com.google.android.gms.common.api.internal.t0;
import com.google.android.gms.common.api.internal.v0;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class n implements r {
    protected final com.google.android.gms.common.api.internal.h zaa;
    private final Context zab;
    private final String zac;
    private final e4.a zad;
    private final i zae;
    private final d zaf;
    private final com.google.android.gms.common.api.internal.a zag;
    private final Looper zah;
    private final int zai;
    private final q zaj;
    private final com.google.android.gms.common.api.internal.v zak;

    /* JADX WARN: Illegal instructions before constructor call */
    public n(Activity activity, i iVar, d.InterfaceC0221d interfaceC0221d, a1.a aVar) {
        Looper mainLooper = activity.getMainLooper();
        com.google.android.gms.common.internal.t.j(mainLooper, "Looper must not be null.");
        this(activity, activity, iVar, interfaceC0221d, new m(aVar, mainLooper));
    }

    public final void a(int i10, com.google.android.gms.common.api.internal.d dVar) {
        dVar.zak();
        com.google.android.gms.common.api.internal.h hVar = this.zaa;
        hVar.getClass();
        q0 q0Var = new q0(new a1(i10, dVar), hVar.f12748s.get(), this);
        androidx.loader.content.j jVar = hVar.x;
        jVar.sendMessage(jVar.obtainMessage(4, q0Var));
    }

    public q asGoogleApiClient() {
        return this.zaj;
    }

    public final com.google.android.gms.tasks.t b(int i10, com.google.android.gms.common.api.internal.y yVar) {
        com.google.android.gms.tasks.i iVar = new com.google.android.gms.tasks.i();
        com.google.android.gms.common.api.internal.v vVar = this.zak;
        com.google.android.gms.common.api.internal.h hVar = this.zaa;
        hVar.getClass();
        hVar.c(iVar, yVar.zab(), this);
        q0 q0Var = new q0(new c1(i10, yVar, iVar, vVar), hVar.f12748s.get(), this);
        androidx.loader.content.j jVar = hVar.x;
        jVar.sendMessage(jVar.obtainMessage(4, q0Var));
        return iVar.f13855a;
    }

    public com.google.android.gms.common.internal.g createClientSettingsBuilder() {
        GoogleSignInAccount googleSignInAccountC;
        GoogleSignInAccount googleSignInAccountC2;
        com.google.android.gms.common.internal.g gVar = new com.google.android.gms.common.internal.g();
        d dVar = this.zaf;
        boolean z = dVar instanceof d.b;
        gVar.f12838a = (!z || (googleSignInAccountC2 = ((d.b) dVar).c()) == null) ? dVar instanceof d.a ? ((d.a) dVar).getAccount() : null : googleSignInAccountC2.getAccount();
        Set<Scope> requestedScopes = (!z || (googleSignInAccountC = ((d.b) dVar).c()) == null) ? Collections.EMPTY_SET : googleSignInAccountC.getRequestedScopes();
        if (gVar.f12839b == null) {
            gVar.f12839b = new t.f(0);
        }
        gVar.f12839b.addAll(requestedScopes);
        Context context = this.zab;
        gVar.f12841d = context.getClass().getName();
        gVar.f12840c = context.getPackageName();
        return gVar;
    }

    public com.google.android.gms.tasks.h disconnectService() {
        com.google.android.gms.common.api.internal.h hVar = this.zaa;
        hVar.getClass();
        com.google.android.gms.common.api.internal.b0 b0Var = new com.google.android.gms.common.api.internal.b0(getApiKey());
        androidx.loader.content.j jVar = hVar.x;
        jVar.sendMessage(jVar.obtainMessage(14, b0Var));
        return b0Var.f12702b.f13855a;
    }

    public <A extends b, T extends com.google.android.gms.common.api.internal.d> T doBestEffortWrite(T t2) {
        a(2, t2);
        return t2;
    }

    public <A extends b, T extends com.google.android.gms.common.api.internal.d> T doRead(T t2) {
        a(0, t2);
        return t2;
    }

    @Deprecated
    public <A extends b, T extends com.google.android.gms.common.api.internal.q, U extends com.google.android.gms.common.api.internal.z> com.google.android.gms.tasks.h doRegisterEventListener(T t2, U u2) {
        com.google.android.gms.common.internal.t.i(t2);
        com.google.android.gms.common.internal.t.i(u2);
        com.google.android.gms.common.internal.t.j(t2.f12780a.f12769c, "Listener has already been released.");
        com.google.android.gms.common.internal.t.j(u2.f12815a, "Listener has already been released.");
        com.google.android.gms.common.internal.t.a("Listener registration and unregistration methods must be constructed with the same ListenerHolder.", com.google.android.gms.common.internal.t.l(t2.f12780a.f12769c, u2.f12815a));
        return this.zaa.h(this, t2, u2, b0.f12680i);
    }

    public com.google.android.gms.tasks.h doUnregisterEventListener(com.google.android.gms.common.api.internal.l lVar) {
        return doUnregisterEventListener(lVar, 0);
    }

    public <A extends b, T extends com.google.android.gms.common.api.internal.d> T doWrite(T t2) {
        a(1, t2);
        return t2;
    }

    public String getApiFallbackAttributionTag(Context context) {
        return null;
    }

    public final com.google.android.gms.common.api.internal.a getApiKey() {
        return this.zag;
    }

    public d getApiOptions() {
        return this.zaf;
    }

    public Context getApplicationContext() {
        return this.zab;
    }

    public String getContextAttributionTag() {
        return this.zac;
    }

    @Deprecated
    public String getContextFeatureId() {
        return this.zac;
    }

    public Looper getLooper() {
        return this.zah;
    }

    public <L> com.google.android.gms.common.api.internal.n registerListener(L l10, String str) {
        Looper looper = this.zah;
        com.google.android.gms.common.internal.t.j(l10, "Listener must not be null");
        com.google.android.gms.common.internal.t.j(looper, "Looper must not be null");
        com.google.android.gms.common.internal.t.j(str, "Listener type must not be null");
        return new com.google.android.gms.common.api.internal.n(looper, l10, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final g zaa(Looper looper, com.google.android.gms.common.api.internal.e0 e0Var) {
        com.google.android.gms.common.internal.g gVarCreateClientSettingsBuilder = createClientSettingsBuilder();
        com.google.android.gms.common.internal.h hVar = new com.google.android.gms.common.internal.h(gVarCreateClientSettingsBuilder.f12838a, gVarCreateClientSettingsBuilder.f12839b, gVarCreateClientSettingsBuilder.f12840c, gVarCreateClientSettingsBuilder.f12841d);
        a aVar = this.zae.f12686a;
        com.google.android.gms.common.internal.t.i(aVar);
        g gVarBuildClient = aVar.buildClient(this.zab, looper, hVar, (Object) this.zaf, (o) e0Var, (p) e0Var);
        e4.a aVar2 = this.zad;
        if (aVar2 != null && (gVarBuildClient instanceof com.google.android.gms.common.internal.f)) {
            ((com.google.android.gms.common.internal.f) gVarBuildClient).setAttributionSourceWrapper(aVar2);
            return gVarBuildClient;
        }
        String contextAttributionTag = getContextAttributionTag();
        if (contextAttributionTag != null && (gVarBuildClient instanceof com.google.android.gms.common.internal.f)) {
            ((com.google.android.gms.common.internal.f) gVarBuildClient).setAttributionTag(contextAttributionTag);
        }
        return gVarBuildClient;
    }

    public final int zab() {
        return this.zai;
    }

    public final v0 zac(Context context, Handler handler) {
        com.google.android.gms.common.internal.g gVarCreateClientSettingsBuilder = createClientSettingsBuilder();
        return new v0(context, handler, new com.google.android.gms.common.internal.h(gVarCreateClientSettingsBuilder.f12838a, gVarCreateClientSettingsBuilder.f12839b, gVarCreateClientSettingsBuilder.f12840c, gVarCreateClientSettingsBuilder.f12841d));
    }

    public <TResult, A extends b> com.google.android.gms.tasks.h doBestEffortWrite(com.google.android.gms.common.api.internal.y yVar) {
        return b(2, yVar);
    }

    public <TResult, A extends b> com.google.android.gms.tasks.h doRead(com.google.android.gms.common.api.internal.y yVar) {
        return b(0, yVar);
    }

    public com.google.android.gms.tasks.h doUnregisterEventListener(com.google.android.gms.common.api.internal.l lVar, int i10) {
        com.google.android.gms.common.internal.t.j(lVar, "Listener key cannot be null.");
        com.google.android.gms.common.api.internal.h hVar = this.zaa;
        hVar.getClass();
        com.google.android.gms.tasks.i iVar = new com.google.android.gms.tasks.i();
        hVar.c(iVar, i10, this);
        q0 q0Var = new q0(new b1(lVar, iVar), hVar.f12748s.get(), this);
        androidx.loader.content.j jVar = hVar.x;
        jVar.sendMessage(jVar.obtainMessage(13, q0Var));
        return iVar.f13855a;
    }

    public <TResult, A extends b> com.google.android.gms.tasks.h doWrite(com.google.android.gms.common.api.internal.y yVar) {
        return b(1, yVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public n(android.content.Context r6, android.app.Activity r7, com.google.android.gms.common.api.i r8, com.google.android.gms.common.api.d r9, com.google.android.gms.common.api.m r10) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.n.<init>(android.content.Context, android.app.Activity, com.google.android.gms.common.api.i, com.google.android.gms.common.api.d, com.google.android.gms.common.api.m):void");
    }

    public <A extends b> com.google.android.gms.tasks.h doRegisterEventListener(com.google.android.gms.common.api.internal.s sVar) {
        com.google.android.gms.common.internal.t.i(sVar);
        com.google.android.gms.common.internal.t.j(sVar.f12794a.f12780a.f12769c, "Listener has already been released.");
        t0 t0Var = sVar.f12795b;
        com.google.android.gms.common.internal.t.j(t0Var.f12815a, "Listener has already been released.");
        return this.zaa.h(this, sVar.f12794a, t0Var, androidx.emoji2.text.s.f2643l);
    }
}
