package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f7 extends g7 implements Consumer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f18693b;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public f7(int i10) {
        this.f18693b = new Object[i10];
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i10 = this.f18702a;
        this.f18702a = i10 + 1;
        this.f18693b[i10] = obj;
    }
}
