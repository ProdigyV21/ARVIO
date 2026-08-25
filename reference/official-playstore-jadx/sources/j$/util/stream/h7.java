package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class h7 implements Spliterator, Consumer {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f18713d = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Spliterator f18714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f18715b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f18716c;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    public h7(Spliterator spliterator, ConcurrentHashMap concurrentHashMap) {
        this.f18714a = spliterator;
        this.f18715b = concurrentHashMap;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.f18716c = obj;
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        while (this.f18714a.tryAdvance(this)) {
            Object obj = this.f18716c;
            if (obj == null) {
                obj = f18713d;
            }
            if (this.f18715b.putIfAbsent(obj, Boolean.TRUE) == null) {
                consumer.n(this.f18716c);
                this.f18716c = null;
                return true;
            }
        }
        return false;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        this.f18714a.forEachRemaining(new j$.util.concurrent.t(8, this, consumer));
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        Spliterator spliteratorTrySplit = this.f18714a.trySplit();
        if (spliteratorTrySplit != null) {
            return new h7(spliteratorTrySplit, this.f18715b);
        }
        return null;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18714a.estimateSize();
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return (this.f18714a.characteristics() & (-16469)) | 1;
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return this.f18714a.getComparator();
    }
}
