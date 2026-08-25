package j$.util.stream;

import j$.util.Collection;
import j$.util.Spliterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class k2 implements g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Collection f18759a;

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.V(this, j10, j11, intFunction);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.g2
    public final g2 a(int i10) {
        throw new IndexOutOfBoundsException();
    }

    public k2(Collection collection) {
        this.f18759a = collection;
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        return Collection.EL.stream(this.f18759a).spliterator();
    }

    @Override // j$.util.stream.g2
    public final void k(Object[] objArr, int i10) {
        Iterator it = this.f18759a.iterator();
        while (it.hasNext()) {
            objArr[i10] = it.next();
            i10++;
        }
    }

    @Override // j$.util.stream.g2
    public final Object[] m(IntFunction intFunction) {
        java.util.Collection collection = this.f18759a;
        return collection.toArray((Object[]) intFunction.apply(collection.size()));
    }

    @Override // j$.util.stream.g2
    public final long count() {
        return this.f18759a.size();
    }

    @Override // j$.util.stream.g2
    public final void forEach(Consumer consumer) {
        Collection.EL.a(this.f18759a, consumer);
    }

    public final String toString() {
        return String.format("CollectionNode[%d][%s]", Integer.valueOf(this.f18759a.size()), this.f18759a);
    }
}
