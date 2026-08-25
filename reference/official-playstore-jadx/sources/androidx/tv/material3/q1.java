package androidx.tv.material3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q1 f6329i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final q1 f6330l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ q1[] f6331m;

    static {
        q1 q1Var = new q1("Closed", 0);
        f6329i = q1Var;
        q1 q1Var2 = new q1("Open", 1);
        f6330l = q1Var2;
        f6331m = new q1[]{q1Var, q1Var2};
    }

    public static q1 valueOf(String str) {
        return (q1) Enum.valueOf(q1.class, str);
    }

    public static q1[] values() {
        return (q1[]) f6331m.clone();
    }
}
