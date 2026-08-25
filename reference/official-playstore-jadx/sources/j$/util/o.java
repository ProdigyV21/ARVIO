package j$.util;

import j$.util.stream.l5;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class o implements DoubleConsumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18579a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Consumer f18580b;

    public /* synthetic */ o(Consumer consumer, int i10) {
        this.f18579a = i10;
        this.f18580b = consumer;
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d4) {
        switch (this.f18579a) {
            case 0:
                this.f18580b.accept(Double.valueOf(d4));
                break;
            default:
                ((l5) this.f18580b).accept(d4);
                break;
        }
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.f18579a) {
        }
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }
}
