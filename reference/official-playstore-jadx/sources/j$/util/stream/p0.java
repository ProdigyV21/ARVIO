package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p0 extends q0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Consumer f18824b;

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.e8
    public final Object e(a aVar, Spliterator spliterator) {
        aVar.E0(spliterator, this);
        return null;
    }

    @Override // j$.util.stream.e8
    public final /* bridge */ /* synthetic */ Object h(v3 v3Var, Spliterator spliterator) {
        a(v3Var, spliterator);
        return null;
    }

    public p0(Consumer consumer, boolean z) {
        super(z);
        this.f18824b = consumer;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.f18824b.n(obj);
    }
}
