package com.google.firebase.installations;

import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f14384a;

    public e(i iVar) {
        this.f14384a = iVar;
    }

    @Override // com.google.firebase.installations.g
    public final boolean a(o5.b bVar) {
        int i10 = bVar.f20783b;
        if (i10 != 3 && i10 != 4 && i10 != 5) {
            return false;
        }
        this.f14384a.d(bVar.f20782a);
        return true;
    }
}
