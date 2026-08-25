package m2;

import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class i implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final i f20204i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ i[] f20205l;

    static {
        i iVar = new i("INSTANCE", 0);
        f20204i = iVar;
        f20205l = new i[]{iVar};
    }

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) f20205l.clone();
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
