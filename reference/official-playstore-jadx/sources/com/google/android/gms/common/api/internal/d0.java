package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f12714a;

    public d0(h hVar) {
        this.f12714a = hVar;
    }

    @Override // com.google.android.gms.common.api.internal.b
    public final void a(boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        h hVar = this.f12714a;
        hVar.x.sendMessage(hVar.x.obtainMessage(1, boolValueOf));
    }
}
