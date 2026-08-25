package com.google.firebase.crashlytics.internal;

import com.google.android.datatransport.runtime.i;
import com.google.android.datatransport.runtime.scheduling.persistence.d;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import k3.f;
import l3.a;
import m5.a;
import m5.b;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements a.InterfaceC0243a, a.InterfaceC0242a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f14347i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f14348l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f14349m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f14350n;

    public /* synthetic */ a(String str, String str2, long j10, StaticSessionData staticSessionData) {
        this.f14348l = str;
        this.f14349m = str2;
        this.f14347i = j10;
        this.f14350n = staticSessionData;
    }

    @Override // l3.a.InterfaceC0242a
    public Object execute() {
        f fVar = (f) this.f14348l;
        Iterable iterable = (Iterable) this.f14349m;
        i iVar = (i) this.f14350n;
        d dVar = fVar.f19490c;
        dVar.W(iterable);
        dVar.z(fVar.f19494g.a() + this.f14347i, iVar);
        return null;
    }

    @Override // m5.a.InterfaceC0243a
    public void h(b bVar) {
        CrashlyticsNativeComponentDeferredProxy.lambda$prepareNativeSession$1((String) this.f14348l, (String) this.f14349m, this.f14347i, (StaticSessionData) this.f14350n, bVar);
    }

    public /* synthetic */ a(f fVar, Iterable iterable, i iVar, long j10) {
        this.f14348l = fVar;
        this.f14349m = iterable;
        this.f14350n = iVar;
        this.f14347i = j10;
    }
}
