package kotlin.collections;

import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends f implements RandomAccess {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19690i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f19693n;

    public e(List list) {
        this.f19693n = list;
    }

    @Override // kotlin.collections.f, java.util.List
    public final Object get(int i10) {
        switch (this.f19690i) {
            case 0:
                c cVar = f.Companion;
                int i11 = this.f19692m;
                cVar.getClass();
                c.b(i10, i11);
                return ((f) this.f19693n).get(this.f19691l + i10);
            default:
                c cVar2 = f.Companion;
                int i12 = this.f19692m;
                cVar2.getClass();
                c.b(i10, i12);
                return this.f19693n.get(this.f19691l + i10);
        }
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    /* JADX INFO: renamed from: getSize */
    public final int get_size() {
        switch (this.f19690i) {
        }
        return this.f19692m;
    }

    @Override // kotlin.collections.f, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList
    public List subList(int i10, int i11) {
        switch (this.f19690i) {
            case 0:
                c cVar = f.Companion;
                int i12 = this.f19692m;
                cVar.getClass();
                c.d(i10, i11, i12);
                f fVar = (f) this.f19693n;
                int i13 = this.f19691l;
                return new e(fVar, i10 + i13, i13 + i11);
            default:
                return super.subList(i10, i11);
        }
    }

    public e(f fVar, int i10, int i11) {
        this.f19693n = fVar;
        this.f19691l = i10;
        c cVar = f.Companion;
        int size = fVar.size();
        cVar.getClass();
        c.d(i10, i11, size);
        this.f19692m = i11 - i10;
    }
}
