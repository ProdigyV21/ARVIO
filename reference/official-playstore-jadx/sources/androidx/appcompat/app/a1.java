package androidx.appcompat.app;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class a1 implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1032i;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f1032i) {
            case 0:
                new Thread(runnable).start();
                break;
            case 1:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
