package ga;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f15566b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.l f15567c;

    public /* synthetic */ c(m mVar, r7.l lVar, int i10) {
        this.f15565a = i10;
        this.f15566b = mVar;
        this.f15567c = lVar;
    }

    @Override // ga.m
    public final Iterator iterator() {
        switch (this.f15565a) {
            case 0:
                return new b(this.f15566b.iterator(), this.f15567c);
            default:
                return new h(this);
        }
    }
}
