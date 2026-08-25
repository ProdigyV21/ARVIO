package j$.util.stream;

import java.util.function.LongBinaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class w3 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ LongBinaryOperator f18939h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f18940i;

    @Override // j$.util.stream.v3
    public final q4 D0() {
        return new o4(this.f18940i, this.f18939h);
    }

    public w3(z6 z6Var, LongBinaryOperator longBinaryOperator, long j10) {
        this.f18939h = longBinaryOperator;
        this.f18940i = j10;
    }
}
