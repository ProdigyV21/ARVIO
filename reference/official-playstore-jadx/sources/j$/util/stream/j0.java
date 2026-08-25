package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j0 implements f8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18737a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18738b;

    @Override // j$.util.stream.l5
    public /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    @Override // j$.util.stream.l5
    public /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void c(long j10) {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void n(Object obj) {
        if (this.f18737a) {
            return;
        }
        this.f18737a = true;
        this.f18738b = obj;
    }

    @Override // j$.util.stream.l5
    public final boolean f() {
        return this.f18737a;
    }
}
