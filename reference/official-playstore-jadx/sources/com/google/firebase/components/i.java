package com.google.firebase.components;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements m5.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f14266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f14267c;

    public /* synthetic */ i(Object obj, Object obj2, int i10) {
        this.f14265a = i10;
        this.f14266b = obj;
        this.f14267c = obj2;
    }

    @Override // m5.b
    public final Object get() {
        switch (this.f14265a) {
            case 0:
                j jVar = (j) this.f14266b;
                b bVar = (b) this.f14267c;
                return bVar.f14260f.f(new u(bVar, jVar));
            case 1:
                return new l5.g((Context) this.f14266b, (String) this.f14267c);
            default:
                z4.g gVar = (z4.g) this.f14266b;
                return new r5.a((Context) this.f14267c, gVar.d(), (k5.b) gVar.f23187d.a(k5.b.class));
        }
    }
}
