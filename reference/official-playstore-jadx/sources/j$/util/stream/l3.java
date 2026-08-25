package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class l3 extends k3 implements x1 {
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

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.k5
    public final /* synthetic */ void l(Long l10) {
        v3.H(this, l10);
    }

    @Override // j$.util.stream.y1
    public final /* bridge */ /* synthetic */ g2 build() {
        build();
        return this;
    }

    @Override // j$.util.stream.x1, j$.util.stream.y1
    public final e2 build() {
        int i10 = this.f18761b;
        long[] jArr = this.f18760a;
        if (i10 >= jArr.length) {
            return this;
        }
        throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.f18761b), Integer.valueOf(jArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        long[] jArr = this.f18760a;
        if (j10 != jArr.length) {
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j10), Integer.valueOf(jArr.length)));
        }
        this.f18761b = 0;
    }

    @Override // j$.util.stream.l5
    public final void accept(long j10) {
        int i10 = this.f18761b;
        long[] jArr = this.f18760a;
        if (i10 < jArr.length) {
            this.f18761b = i10 + 1;
            jArr[i10] = j10;
            return;
        }
        throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(jArr.length)));
    }

    @Override // j$.util.stream.l5
    public final void end() {
        int i10 = this.f18761b;
        long[] jArr = this.f18760a;
        if (i10 < jArr.length) {
            throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.f18761b), Integer.valueOf(jArr.length)));
        }
    }

    @Override // j$.util.stream.k3
    public final String toString() {
        long[] jArr = this.f18760a;
        return String.format("LongFixedNodeBuilder[%d][%s]", Integer.valueOf(jArr.length - this.f18761b), Arrays.toString(jArr));
    }
}
