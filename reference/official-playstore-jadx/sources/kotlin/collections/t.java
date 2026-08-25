package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class t implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Enumeration f19726i;

    public t(Enumeration enumeration) {
        this.f19726i = enumeration;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f19726i.hasMoreElements();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.f19726i.nextElement();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
