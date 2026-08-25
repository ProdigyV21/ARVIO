package androidx.work.impl.constraints.controllers;

import ma.u;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements androidx.work.impl.constraints.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f6792a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ u f6793b;

    public b(d dVar, u uVar) {
        this.f6792a = dVar;
        this.f6793b = uVar;
    }

    @Override // androidx.work.impl.constraints.a
    public final void a(Object obj) {
        d dVar = this.f6792a;
        this.f6793b.getChannel().mo6685trySendJP2dKIU(dVar.c(obj) ? new androidx.work.impl.constraints.c(dVar.a()) : androidx.work.impl.constraints.b.f6789a);
    }
}
