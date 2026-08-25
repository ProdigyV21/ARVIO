package j$.util;

import j$.util.function.Consumer$CC;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p0 implements Iterator, Consumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18583a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18584b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Spliterator f18585c;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public p0(Spliterator spliterator) {
        this.f18585c = spliterator;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f18583a = true;
        this.f18584b = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.f18583a) {
            this.f18585c.tryAdvance(this);
        }
        return this.f18583a;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f18583a && !hasNext()) {
            throw new NoSuchElementException();
        }
        this.f18583a = false;
        return this.f18584b;
    }
}
