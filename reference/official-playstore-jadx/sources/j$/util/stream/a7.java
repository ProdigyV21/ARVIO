package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a7 implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f18617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f18618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Supplier f18619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Spliterator f18620d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l5 f18621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BooleanSupplier f18622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f18623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c f18624h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f18625i;

    public abstract void d();

    public abstract a7 e(Spliterator spliterator);

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    public a7(a aVar, Supplier supplier, boolean z) {
        this.f18618b = aVar;
        this.f18619c = supplier;
        this.f18620d = null;
        this.f18617a = z;
    }

    public a7(a aVar, Spliterator spliterator, boolean z) {
        this.f18618b = aVar;
        this.f18619c = null;
        this.f18620d = spliterator;
        this.f18617a = z;
    }

    public final void c() {
        if (this.f18620d == null) {
            this.f18620d = (Spliterator) this.f18619c.get();
            this.f18619c = null;
        }
    }

    public final boolean a() {
        c cVar = this.f18624h;
        if (cVar == null) {
            if (this.f18625i) {
                return false;
            }
            c();
            d();
            this.f18623g = 0L;
            this.f18621e.c(this.f18620d.getExactSizeIfKnown());
            return b();
        }
        long j10 = this.f18623g + 1;
        this.f18623g = j10;
        boolean z = j10 < cVar.count();
        if (z) {
            return z;
        }
        this.f18623g = 0L;
        this.f18624h.clear();
        return b();
    }

    @Override // j$.util.Spliterator
    public Spliterator trySplit() {
        if (!this.f18617a || this.f18624h != null || this.f18625i) {
            return null;
        }
        c();
        Spliterator spliteratorTrySplit = this.f18620d.trySplit();
        if (spliteratorTrySplit == null) {
            return null;
        }
        return e(spliteratorTrySplit);
    }

    public final boolean b() {
        while (this.f18624h.count() == 0) {
            if (this.f18621e.f() || !this.f18622f.getAsBoolean()) {
                if (this.f18625i) {
                    return false;
                }
                this.f18621e.end();
                this.f18625i = true;
            }
        }
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        c();
        return this.f18620d.estimateSize();
    }

    @Override // j$.util.Spliterator
    public final long getExactSizeIfKnown() {
        c();
        if (y6.SIZED.r(this.f18618b.f18607m)) {
            return this.f18620d.getExactSizeIfKnown();
        }
        return -1L;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        c();
        int i10 = this.f18618b.f18607m;
        int i11 = i10 & ((~i10) >> 1) & y6.f18967j & y6.f18963f;
        return (i11 & 64) != 0 ? (i11 & (-16449)) | (this.f18620d.characteristics() & 16448) : i11;
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        if (j$.com.android.tools.r8.a.p(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }

    public final String toString() {
        return String.format("%s[%s]", getClass().getName(), this.f18620d);
    }
}
