package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.Arrays;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class a3 extends j2 implements y1 {
    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.y1
    public final g2 build() {
        int i10 = this.f18740b;
        Object[] objArr = this.f18739a;
        if (i10 >= objArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.f18740b), Integer.valueOf(objArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        Object[] objArr = this.f18739a;
        if (j10 != objArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j10), Integer.valueOf(objArr.length)));
        }
        this.f18740b = 0;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        int i10 = this.f18740b;
        Object[] objArr = this.f18739a;
        if (i10 < objArr.length) {
            this.f18740b = i10 + 1;
            objArr[i10] = obj;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(objArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void end() {
        int i10 = this.f18740b;
        Object[] objArr = this.f18739a;
        if (i10 < objArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.f18740b), Integer.valueOf(objArr.length)));
        }
    }

    @Override // j$.util.stream.j2
    public final String toString() {
        Object[] objArr = this.f18739a;
        return String.format("FixedNodeBuilder[%d][%s]", Integer.valueOf(objArr.length - this.f18740b), Arrays.toString(objArr));
    }
}
