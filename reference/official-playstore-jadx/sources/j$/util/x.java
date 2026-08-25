package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class x implements z, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PrimitiveIterator.OfLong f19011a;

    public /* synthetic */ x(PrimitiveIterator.OfLong ofLong) {
        this.f19011a = ofLong;
    }

    public final /* synthetic */ boolean equals(Object obj) {
        PrimitiveIterator.OfLong ofLong = this.f19011a;
        if (obj instanceof x) {
            obj = ((x) obj).f19011a;
        }
        return ofLong.equals(obj);
    }

    @Override // j$.util.a0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f19011a.forEachRemaining(obj);
    }

    @Override // j$.util.z, java.util.Iterator, j$.util.j
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f19011a.forEachRemaining((Consumer<? super Long>) consumer);
    }

    @Override // j$.util.z
    public final /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        this.f19011a.forEachRemaining(longConsumer);
    }

    @Override // java.util.Iterator
    public final /* synthetic */ boolean hasNext() {
        return this.f19011a.hasNext();
    }

    public final /* synthetic */ int hashCode() {
        return this.f19011a.hashCode();
    }

    @Override // j$.util.z, java.util.Iterator
    public final /* synthetic */ Long next() {
        return this.f19011a.next();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.f19011a.next();
    }

    @Override // j$.util.z
    public final /* synthetic */ long nextLong() {
        return this.f19011a.nextLong();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ void remove() {
        this.f19011a.remove();
    }
}
