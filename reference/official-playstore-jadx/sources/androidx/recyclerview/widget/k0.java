package androidx.recyclerview.widget;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class k0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final k0 f4966i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ k0[] f4967l;

    static {
        k0 k0Var = new k0("ALLOW", 0);
        f4966i = k0Var;
        f4967l = new k0[]{k0Var, new k0("PREVENT_WHEN_EMPTY", 1), new k0("PREVENT", 2)};
    }

    public static k0 valueOf(String str) {
        return (k0) Enum.valueOf(k0.class, str);
    }

    public static k0[] values() {
        return (k0[]) f4967l.clone();
    }
}
