package androidx.navigation.compose;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class l implements androidx.lifecycle.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f4582i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f4583l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ androidx.navigation.i f4584m;

    public l(androidx.navigation.i iVar, List list, boolean z) {
        this.f4582i = z;
        this.f4583l = list;
        this.f4584m = iVar;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        boolean z = this.f4582i;
        androidx.navigation.i iVar = this.f4584m;
        List list = this.f4583l;
        if (z && !list.contains(iVar)) {
            list.add(iVar);
        }
        if (pVar == androidx.lifecycle.p.ON_START && !list.contains(iVar)) {
            list.add(iVar);
        }
        if (pVar == androidx.lifecycle.p.ON_STOP) {
            list.remove(iVar);
        }
    }
}
