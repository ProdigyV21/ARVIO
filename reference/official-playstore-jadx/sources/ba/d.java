package ba;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d implements Iterable, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f7274i;

    public final boolean isEmpty() {
        return this.f7274i.a() == 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f7274i.iterator();
    }
}
