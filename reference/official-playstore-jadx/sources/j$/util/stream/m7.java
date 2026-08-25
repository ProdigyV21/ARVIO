package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m7 implements k5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18797a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LongConsumer f18798b;

    public /* synthetic */ m7(LongConsumer longConsumer, int i10) {
        this.f18797a = i10;
        this.f18798b = longConsumer;
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
        switch (this.f18797a) {
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
        switch (this.f18797a) {
            case 0:
                v3.J();
                throw null;
            default:
                v3.J();
                throw null;
        }
    }

    @Override // j$.util.stream.k5, j$.util.stream.l5
    public final void accept(long j10) {
        switch (this.f18797a) {
            case 0:
                this.f18798b.accept(j10);
                break;
            default:
                ((s6) this.f18798b).accept(j10);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        switch (this.f18797a) {
            case 0:
                l((Long) obj);
                break;
            default:
                l((Long) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.f18797a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        switch (this.f18797a) {
        }
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void c(long j10) {
        int i10 = this.f18797a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
        int i10 = this.f18797a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        switch (this.f18797a) {
        }
        return false;
    }

    @Override // j$.util.stream.k5
    public final /* synthetic */ void l(Long l10) {
        switch (this.f18797a) {
            case 0:
                v3.H(this, l10);
                break;
            default:
                v3.H(this, l10);
                break;
        }
    }
}
