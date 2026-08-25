package androidx.credentials;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2356i;

    public /* synthetic */ a(int i10) {
        this.f2356i = i10;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f2356i) {
            case 0:
                runnable.run();
                break;
            default:
                o.a.Z().f20717d.f20719e.execute(runnable);
                break;
        }
    }
}
