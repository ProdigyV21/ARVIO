package ga;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f15598b;

    public /* synthetic */ p(Object obj, int i10) {
        this.f15597a = i10;
        this.f15598b = obj;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [f7.h, r7.p] */
    @Override // ga.m
    public final Iterator iterator() {
        switch (this.f15597a) {
            case 0:
                return f4.f.n((f7.h) this.f15598b);
            case 1:
                return (Iterator) this.f15598b;
            case 2:
                return new ab.j((Object[]) this.f15598b, 4);
            case 3:
                return ((Iterable) this.f15598b).iterator();
            case 4:
                return new kotlin.text.e((CharSequence) this.f15598b);
            default:
                return new l7.j(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public p(r7.p pVar) {
        this.f15597a = 0;
        this.f15598b = (f7.h) pVar;
    }
}
