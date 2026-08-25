package ga;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f15580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.l f15582c;

    public i(m mVar, boolean z, r7.l lVar) {
        this.f15580a = mVar;
        this.f15581b = z;
        this.f15582c = lVar;
    }

    @Override // ga.m
    public final Iterator iterator() {
        return new h(this);
    }
}
