package com.google.android.datatransport.runtime;

import android.content.Context;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class j implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f12592a;

    public final k a() {
        Context context = this.f12592a;
        if (context == null) {
            throw new IllegalStateException(Context.class.getCanonicalName() + " must be set");
        }
        k kVar = new k();
        kVar.f12593i = e3.c.a(o.f12602a);
        c3.g gVar = new c3.g(context, 1);
        kVar.f12594l = gVar;
        kVar.f12595m = e3.c.a(new c3.i(gVar, new c3.g(gVar, 0), 0));
        c3.g gVar2 = kVar.f12594l;
        kVar.f12596n = new com.google.android.datatransport.runtime.scheduling.persistence.e(gVar2, 1);
        Provider providerA = e3.c.a(new com.google.android.datatransport.runtime.scheduling.persistence.e(gVar2, 0));
        kVar.f12597o = providerA;
        Provider providerA2 = e3.c.a(new c3.i(kVar.f12596n, providerA, 1));
        kVar.f12598p = providerA2;
        p pVar = new p(1);
        c3.g gVar3 = kVar.f12594l;
        y yVar = new y(gVar3, providerA2, pVar, 1);
        Provider provider = kVar.f12593i;
        Provider provider2 = kVar.f12595m;
        kVar.f12599q = e3.c.a(new y(new j3.b(provider, provider2, yVar, providerA2, providerA2), new k3.g(gVar3, provider2, providerA2, yVar, provider, providerA2, providerA2), new k3.i(provider, providerA2, yVar, providerA2), 0));
        return kVar;
    }
}
