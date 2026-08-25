package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class c3 extends b3 implements w1 {
    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        d((Integer) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.j5
    public final /* synthetic */ void d(Integer num) {
        v3.F(this, num);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.y1
    public final /* bridge */ /* synthetic */ g2 build() {
        build();
        return this;
    }

    @Override // j$.util.stream.w1, j$.util.stream.y1
    public final c2 build() {
        int i10 = this.f18637b;
        int[] iArr = this.f18636a;
        if (i10 >= iArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.f18637b), Integer.valueOf(iArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        int[] iArr = this.f18636a;
        if (j10 != iArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j10), Integer.valueOf(iArr.length)));
        }
        this.f18637b = 0;
    }

    @Override // j$.util.stream.l5
    public final void accept(int i10) {
        int i11 = this.f18637b;
        int[] iArr = this.f18636a;
        if (i11 < iArr.length) {
            this.f18637b = i11 + 1;
            iArr[i11] = i10;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(iArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void end() {
        int i10 = this.f18637b;
        int[] iArr = this.f18636a;
        if (i10 < iArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.f18637b), Integer.valueOf(iArr.length)));
        }
    }

    @Override // j$.util.stream.b3
    public final String toString() {
        int[] iArr = this.f18636a;
        return String.format("IntFixedNodeBuilder[%d][%s]", Integer.valueOf(iArr.length - this.f18637b), Arrays.toString(iArr));
    }
}
