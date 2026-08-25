package com.google.firebase.components;

import m5.a;

/* JADX INFO: loaded from: classes4.dex */
public final class q implements m5.b, m5.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final androidx.media3.extractor.mp4.b f14290c = new androidx.media3.extractor.mp4.b(24);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f14291d = new h(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a.InterfaceC0243a f14292a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile m5.b f14293b;

    public q(androidx.media3.extractor.mp4.b bVar, m5.b bVar2) {
        this.f14292a = bVar;
        this.f14293b = bVar2;
    }

    @Override // m5.a
    public final void a(a.InterfaceC0243a interfaceC0243a) {
        m5.b bVar;
        m5.b bVar2;
        m5.b bVar3 = this.f14293b;
        h hVar = f14291d;
        if (bVar3 != hVar) {
            interfaceC0243a.h(bVar3);
            return;
        }
        synchronized (this) {
            bVar = this.f14293b;
            if (bVar != hVar) {
                bVar2 = bVar;
            } else {
                this.f14292a = new androidx.media3.exoplayer.analytics.b(this.f14292a, interfaceC0243a, 14);
                bVar2 = null;
            }
        }
        if (bVar2 != null) {
            interfaceC0243a.h(bVar);
        }
    }

    @Override // m5.b
    public final Object get() {
        return this.f14293b.get();
    }
}
