package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class t implements v, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PrimitiveIterator.OfInt f18999a;

    public /* synthetic */ t(PrimitiveIterator.OfInt ofInt) {
        this.f18999a = ofInt;
    }

    public final /* synthetic */ boolean equals(Object obj) {
        PrimitiveIterator.OfInt ofInt = this.f18999a;
        if (obj instanceof t) {
            obj = ((t) obj).f18999a;
        }
        return ofInt.equals(obj);
    }

    @Override // j$.util.a0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18999a.forEachRemaining(obj);
    }

    @Override // j$.util.v, java.util.Iterator, j$.util.j
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18999a.forEachRemaining((Consumer<? super Integer>) consumer);
    }

    @Override // j$.util.v
    public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        this.f18999a.forEachRemaining(intConsumer);
    }

    @Override // java.util.Iterator
    public final /* synthetic */ boolean hasNext() {
        return this.f18999a.hasNext();
    }

    public final /* synthetic */ int hashCode() {
        return this.f18999a.hashCode();
    }

    @Override // j$.util.v, java.util.Iterator
    public final /* synthetic */ Integer next() {
        return this.f18999a.next();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.f18999a.next();
    }

    @Override // j$.util.v
    public final /* synthetic */ int nextInt() {
        return this.f18999a.nextInt();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ void remove() {
        this.f18999a.remove();
    }
}
