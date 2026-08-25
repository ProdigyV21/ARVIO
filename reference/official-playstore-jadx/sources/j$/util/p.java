package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class p implements r, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PrimitiveIterator.OfDouble f18582a;

    public /* synthetic */ p(PrimitiveIterator.OfDouble ofDouble) {
        this.f18582a = ofDouble;
    }

    public final /* synthetic */ boolean equals(Object obj) {
        PrimitiveIterator.OfDouble ofDouble = this.f18582a;
        if (obj instanceof p) {
            obj = ((p) obj).f18582a;
        }
        return ofDouble.equals(obj);
    }

    @Override // j$.util.a0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18582a.forEachRemaining(obj);
    }

    @Override // j$.util.r, java.util.Iterator, j$.util.j
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18582a.forEachRemaining((Consumer<? super Double>) consumer);
    }

    @Override // j$.util.r
    public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        this.f18582a.forEachRemaining(doubleConsumer);
    }

    @Override // java.util.Iterator
    public final /* synthetic */ boolean hasNext() {
        return this.f18582a.hasNext();
    }

    public final /* synthetic */ int hashCode() {
        return this.f18582a.hashCode();
    }

    @Override // j$.util.r, java.util.Iterator
    public final /* synthetic */ Double next() {
        return this.f18582a.next();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.f18582a.next();
    }

    @Override // j$.util.r
    public final /* synthetic */ double nextDouble() {
        return this.f18582a.nextDouble();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ void remove() {
        this.f18582a.remove();
    }
}
