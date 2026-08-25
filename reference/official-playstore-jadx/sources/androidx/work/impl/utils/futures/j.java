package androidx.work.impl.utils.futures;

import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class j implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final j f7040i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ j[] f7041l;

    static {
        j jVar = new j("INSTANCE", 0);
        f7040i = jVar;
        f7041l = new j[]{jVar};
    }

    public static j valueOf(String str) {
        return (j) Enum.valueOf(j.class, str);
    }

    public static j[] values() {
        return (j[]) f7041l.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "DirectExecutor";
    }
}
