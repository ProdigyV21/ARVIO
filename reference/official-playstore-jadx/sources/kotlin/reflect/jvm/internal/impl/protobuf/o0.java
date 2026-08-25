package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Iterator f19816i;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f19816i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (String) this.f19816i.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
