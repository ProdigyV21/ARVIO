package j$.util.stream;

import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class c7 extends e7 implements IntConsumer {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f18654c;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    public c7(int i10) {
        this.f18654c = new int[i10];
    }

    @Override // j$.util.stream.e7
    public final void a(Object obj, long j10) {
        IntConsumer intConsumer = (IntConsumer) obj;
        for (int i10 = 0; i10 < j10; i10++) {
            intConsumer.accept(this.f18654c[i10]);
        }
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i10) {
        int i11 = this.f18682b;
        this.f18682b = i11 + 1;
        this.f18654c[i11] = i10;
    }
}
