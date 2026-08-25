package ka;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19578b = AtomicIntegerFieldUpdater.newUpdater(e.class, "notCompletedCount");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p0[] f19579a;

    @q7.w
    private volatile int notCompletedCount;

    public e(p0[] p0VarArr) {
        this.f19579a = p0VarArr;
        this.notCompletedCount = p0VarArr.length;
    }
}
