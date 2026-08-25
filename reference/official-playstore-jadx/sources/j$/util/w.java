package j$.util;

import j$.util.stream.l5;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class w implements LongConsumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Consumer f19010b;

    public /* synthetic */ w(Consumer consumer, int i10) {
        this.f19009a = i10;
        this.f19010b = consumer;
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j10) {
        switch (this.f19009a) {
            case 0:
                this.f19010b.accept(Long.valueOf(j10));
                break;
            default:
                ((l5) this.f19010b).accept(j10);
                break;
        }
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        switch (this.f19009a) {
        }
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }
}
