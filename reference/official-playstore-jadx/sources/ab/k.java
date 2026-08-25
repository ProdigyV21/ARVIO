package ab;

import cb.z;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements Iterable, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f814i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f815l;

    public /* synthetic */ k(Object obj, int i10) {
        this.f814i = i10;
        this.f815l = obj;
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [int[], java.lang.Cloneable] */
    @Override // java.lang.Iterable
    public final Iterator iterator() {
        switch (this.f814i) {
            case 0:
                return new j((z) this.f815l);
            case 1:
                return ((ga.m) this.f815l).iterator();
            case 2:
                return new j((Object[]) this.f815l, 4);
            case 3:
                return new kotlin.jvm.internal.b((int[]) this.f815l, 0);
            case 4:
                return new kotlin.jvm.internal.c((long[]) this.f815l);
            case 5:
                return new j((float[]) this.f815l, 3);
            case 6:
                return new j((double[]) this.f815l, 2);
            default:
                return new ga.d((Iterator) ((r7.a) this.f815l).invoke());
        }
    }
}
