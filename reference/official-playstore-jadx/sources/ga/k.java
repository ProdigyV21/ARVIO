package ga;

import com.arflix.tv.ui.screens.tv.live.l0;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15586i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f15587l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f15588m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f15589n;

    public k(l lVar, byte b10) {
        this.f15586i = 2;
        this.f15589n = lVar;
        this.f15588m = new h((i) lVar.f15591b);
    }

    public void a() {
        l lVar = (l) this.f15589n;
        Object objInvoke = this.f15587l == -2 ? ((r7.a) lVar.f15591b).invoke() : ((r7.l) lVar.f15592c).invoke(this.f15588m);
        this.f15588m = objInvoke;
        this.f15587l = objInvoke == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f15586i) {
            case 0:
                if (this.f15587l < 0) {
                    a();
                }
                return this.f15587l == 1;
            case 1:
                s sVar = (s) this.f15589n;
                Iterator it = (Iterator) this.f15588m;
                while (this.f15587l < sVar.f15601b && it.hasNext()) {
                    it.next();
                    this.f15587l++;
                }
                return this.f15587l < sVar.f15602c && it.hasNext();
            default:
                return ((Iterator) this.f15588m).hasNext();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f15586i) {
            case 0:
                if (this.f15587l < 0) {
                    a();
                }
                if (this.f15587l == 0) {
                    throw new NoSuchElementException();
                }
                Object obj = this.f15588m;
                this.f15587l = -1;
                return obj;
            case 1:
                s sVar = (s) this.f15589n;
                Iterator it = (Iterator) this.f15588m;
                while (this.f15587l < sVar.f15601b && it.hasNext()) {
                    it.next();
                    this.f15587l++;
                }
                int i10 = this.f15587l;
                if (i10 >= sVar.f15602c) {
                    throw new NoSuchElementException();
                }
                this.f15587l = i10 + 1;
                return it.next();
            default:
                l0 l0Var = (l0) ((l) this.f15589n).f15592c;
                int i11 = this.f15587l;
                this.f15587l = i11 + 1;
                if (i11 >= 0) {
                    return l0Var.invoke(Integer.valueOf(i11), ((Iterator) this.f15588m).next());
                }
                t7.a.Q();
                throw null;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f15586i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public k(s sVar) {
        this.f15586i = 1;
        this.f15589n = sVar;
        this.f15588m = sVar.f15600a.iterator();
    }

    public k(l lVar) {
        this.f15586i = 0;
        this.f15589n = lVar;
        this.f15587l = -2;
    }
}
