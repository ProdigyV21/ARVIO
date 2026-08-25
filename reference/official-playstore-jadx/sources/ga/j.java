package ga;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f15583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r7.l f15584b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.l f15585c;

    public j(m mVar, r7.l lVar, r7.l lVar2) {
        this.f15583a = mVar;
        this.f15584b = lVar;
        this.f15585c = lVar2;
    }

    @Override // ga.m
    public final Iterator iterator() {
        return new h(this);
    }
}
