package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k7 implements j5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ IntConsumer f18768b;

    public /* synthetic */ k7(IntConsumer intConsumer, int i10) {
        this.f18767a = i10;
        this.f18768b = intConsumer;
    }

    private final /* synthetic */ void a(long j10) {
    }

    private final /* synthetic */ void b(long j10) {
    }

    private final /* synthetic */ void e() {
    }

    private final /* synthetic */ void g() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        switch (this.f18767a) {
            case 0:
                v3.C();
                throw null;
            default:
                v3.C();
                throw null;
        }
    }

    @Override // j$.util.stream.j5, j$.util.stream.l5
    public final void accept(int i10) {
        switch (this.f18767a) {
            case 0:
                this.f18768b.accept(i10);
                break;
            default:
                ((q6) this.f18768b).accept(i10);
                break;
        }
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        switch (this.f18767a) {
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
        switch (this.f18767a) {
            case 0:
                d((Integer) obj);
                break;
            default:
                d((Integer) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.f18767a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        switch (this.f18767a) {
        }
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void c(long j10) {
        int i10 = this.f18767a;
    }

    @Override // j$.util.stream.j5
    public final /* synthetic */ void d(Integer num) {
        switch (this.f18767a) {
            case 0:
                v3.F(this, num);
                break;
            default:
                v3.F(this, num);
                break;
        }
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
        int i10 = this.f18767a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        switch (this.f18767a) {
        }
        return false;
    }
}
