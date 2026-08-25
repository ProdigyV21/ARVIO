package va;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f22264i = new a();

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
