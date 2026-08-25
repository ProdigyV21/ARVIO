package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class t2 extends s2 implements v1 {
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

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.i5
    public final /* synthetic */ void n(Double d4) {
        v3.D(this, d4);
    }

    @Override // j$.util.stream.y1
    public final /* bridge */ /* synthetic */ g2 build() {
        build();
        return this;
    }

    @Override // j$.util.stream.v1, j$.util.stream.y1
    public final a2 build() {
        int i10 = this.f18868b;
        double[] dArr = this.f18867a;
        if (i10 >= dArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.f18868b), Integer.valueOf(dArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        double[] dArr = this.f18867a;
        if (j10 != dArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j10), Integer.valueOf(dArr.length)));
        }
        this.f18868b = 0;
    }

    @Override // j$.util.stream.l5
    public final void accept(double d4) {
        int i10 = this.f18868b;
        double[] dArr = this.f18867a;
        if (i10 < dArr.length) {
            this.f18868b = i10 + 1;
            dArr[i10] = d4;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(dArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void end() {
        int i10 = this.f18868b;
        double[] dArr = this.f18867a;
        if (i10 < dArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.f18868b), Integer.valueOf(dArr.length)));
        }
    }

    @Override // j$.util.stream.s2
    public final String toString() {
        double[] dArr = this.f18867a;
        return String.format("DoubleFixedNodeBuilder[%d][%s]", Integer.valueOf(dArr.length - this.f18868b), Arrays.toString(dArr));
    }
}
