package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.BaseStream;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class f implements BaseStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f18683a;

    public /* synthetic */ f(g gVar) {
        this.f18683a = gVar;
    }

    public static /* synthetic */ BaseStream e(g gVar) {
        if (gVar == null) {
            return null;
        }
        return gVar instanceof e ? ((e) gVar).f18670a : gVar instanceof d0 ? c0.e((d0) gVar) : gVar instanceof IntStream ? IntStream.Wrapper.convert((IntStream) gVar) : gVar instanceof m1 ? l1.e((m1) gVar) : gVar instanceof Stream ? Stream.Wrapper.convert((Stream) gVar) : new f(gVar);
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() throws Exception {
        this.f18683a.close();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        g gVar = this.f18683a;
        if (obj instanceof f) {
            obj = ((f) obj).f18683a;
        }
        return gVar.equals(obj);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18683a.hashCode();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.f18683a.isParallel();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ Iterator iterator() {
        return this.f18683a.iterator();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream onClose(Runnable runnable) {
        return e(this.f18683a.onClose(runnable));
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream parallel() {
        return e(this.f18683a.parallel());
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream sequential() {
        return e(this.f18683a.sequential());
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ Spliterator spliterator() {
        return Spliterator.Wrapper.convert(this.f18683a.spliterator());
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream unordered() {
        return e(this.f18683a.unordered());
    }
}
