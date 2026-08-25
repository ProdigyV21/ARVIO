package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class q3 extends r3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object[] f18835h;

    public q3(Spliterator spliterator, v3 v3Var, Object[] objArr) {
        super(spliterator, v3Var, objArr.length);
        this.f18835h = objArr;
    }

    public q3(q3 q3Var, Spliterator spliterator, long j10, long j11) {
        super(q3Var, spliterator, j10, j11, q3Var.f18835h.length);
        this.f18835h = q3Var.f18835h;
    }

    @Override // j$.util.stream.r3
    public final r3 a(Spliterator spliterator, long j10, long j11) {
        return new q3(this, spliterator, j10, j11);
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        int i10 = this.f18853f;
        if (i10 >= this.f18854g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f18853f));
        }
        Object[] objArr = this.f18835h;
        this.f18853f = i10 + 1;
        objArr[i10] = obj;
    }
}
