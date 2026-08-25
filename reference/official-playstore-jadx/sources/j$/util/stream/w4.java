package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class w4 extends r4 implements q4 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18941b;

    public /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    public /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    public /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18941b = 0L;
    }
}
