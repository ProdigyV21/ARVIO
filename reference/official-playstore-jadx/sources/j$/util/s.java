package j$.util;

import j$.util.function.IntConsumer$CC;
import j$.util.stream.l5;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class s implements IntConsumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18593a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Consumer f18594b;

    public /* synthetic */ s(Consumer consumer, int i10) {
        this.f18593a = i10;
        this.f18594b = consumer;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i10) {
        switch (this.f18593a) {
            case 0:
                this.f18594b.accept(Integer.valueOf(i10));
                break;
            default:
                ((l5) this.f18594b).accept(i10);
                break;
        }
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        switch (this.f18593a) {
        }
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }
}
