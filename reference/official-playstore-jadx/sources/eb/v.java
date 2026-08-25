package eb;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements ga.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f15184b;

    public /* synthetic */ v(Object obj, int i10) {
        this.f15183a = i10;
        this.f15184b = obj;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Iterator] */
    private final Iterator c() {
        return this.f15184b;
    }

    @Override // ga.m
    public final Iterator iterator() {
        switch (this.f15183a) {
            case 0:
                return c();
            default:
                return new ba.q(this.f15184b, 1);
        }
    }
}
