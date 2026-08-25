package z6;

import androidx.core.view.h1;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends h1 implements Iterator, s7.d {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f23220o;

    public d(f fVar, int i10) {
        this.f23220o = i10;
        this.f2241n = fVar;
        this.f2239l = -1;
        this.f2240m = fVar.f23231r;
        e();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f23220o) {
            case 0:
                b();
                int i10 = this.f2238i;
                f fVar = (f) this.f2241n;
                if (i10 >= fVar.f23229p) {
                    throw new NoSuchElementException();
                }
                this.f2238i = i10 + 1;
                this.f2239l = i10;
                e eVar = new e(fVar, i10);
                e();
                return eVar;
            case 1:
                b();
                int i11 = this.f2238i;
                f fVar2 = (f) this.f2241n;
                if (i11 >= fVar2.f23229p) {
                    throw new NoSuchElementException();
                }
                this.f2238i = i11 + 1;
                this.f2239l = i11;
                Object obj = fVar2.f23224i[i11];
                e();
                return obj;
            default:
                b();
                int i12 = this.f2238i;
                f fVar3 = (f) this.f2241n;
                if (i12 >= fVar3.f23229p) {
                    throw new NoSuchElementException();
                }
                this.f2238i = i12 + 1;
                this.f2239l = i12;
                Object obj2 = fVar3.f23225l[i12];
                e();
                return obj2;
        }
    }
}
