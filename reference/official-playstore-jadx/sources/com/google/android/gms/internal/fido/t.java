package com.google.android.gms.internal.fido;

import java.util.AbstractMap;

/* JADX INFO: loaded from: classes4.dex */
public final class t extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f13820m;

    public t(u uVar) {
        this.f13820m = uVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i10) {
        u uVar = this.f13820m;
        return new AbstractMap.SimpleImmutableEntry(uVar.f13824n.f13827m.f13770p.get(i10), uVar.f13824n.f13828n.get(i10));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13820m.f13824n.f13828n.size();
    }
}
