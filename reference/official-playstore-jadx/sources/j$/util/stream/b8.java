package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b8 implements l5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Consumer f18644b;

    public /* synthetic */ b8(Consumer consumer, int i10) {
        this.f18643a = i10;
        this.f18644b = consumer;
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
        switch (this.f18643a) {
            case 0:
                v3.C();
                throw null;
            default:
                v3.C();
                throw null;
        }
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        switch (this.f18643a) {
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
        switch (this.f18643a) {
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
    public final void n(Object obj) {
        switch (this.f18643a) {
            case 0:
                ((v6) this.f18644b).n(obj);
                break;
            default:
                this.f18644b.n(obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.f18643a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void c(long j10) {
        int i10 = this.f18643a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
        int i10 = this.f18643a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        switch (this.f18643a) {
        }
        return false;
    }
}
