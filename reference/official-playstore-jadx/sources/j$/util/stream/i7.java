package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class i7 implements i5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18730a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DoubleConsumer f18731b;

    public /* synthetic */ i7(DoubleConsumer doubleConsumer, int i10) {
        this.f18730a = i10;
        this.f18731b = doubleConsumer;
    }

    private final /* synthetic */ void a(long j10) {
    }

    private final /* synthetic */ void b(long j10) {
    }

    private final /* synthetic */ void e() {
    }

    private final /* synthetic */ void g() {
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) {
        switch (this.f18730a) {
            case 0:
                this.f18731b.accept(d4);
                break;
            default:
                ((o6) this.f18731b).accept(d4);
                break;
        }
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        switch (this.f18730a) {
            case 0:
                v3.J();
                throw null;
            default:
                v3.J();
                throw null;
        }
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        switch (this.f18730a) {
            case 0:
                v3.K();
                throw null;
            default:
                v3.K();
                throw null;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        switch (this.f18730a) {
            case 0:
                n((Double) obj);
                break;
            default:
                n((Double) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.f18730a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.f18730a) {
        }
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void c(long j10) {
        int i10 = this.f18730a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
        int i10 = this.f18730a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        switch (this.f18730a) {
        }
        return false;
    }

    @Override // j$.util.stream.i5
    public final /* synthetic */ void n(Double d4) {
        switch (this.f18730a) {
            case 0:
                v3.D(this, d4);
                break;
            default:
                v3.D(this, d4);
                break;
        }
    }
}
