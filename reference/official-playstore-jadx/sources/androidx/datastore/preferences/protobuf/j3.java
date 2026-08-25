package androidx.datastore.preferences.protobuf;

import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class j3 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Iterator f2455i;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2455i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (String) this.f2455i.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
