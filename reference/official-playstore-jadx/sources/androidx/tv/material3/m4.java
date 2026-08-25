package androidx.tv.material3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class m4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final m4 f6219i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final m4 f6220l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m4 f6221m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ m4[] f6222n;

    static {
        m4 m4Var = new m4("Tabs", 0);
        f6219i = m4Var;
        m4 m4Var2 = new m4("Indicator", 1);
        f6220l = m4Var2;
        m4 m4Var3 = new m4("Separator", 2);
        f6221m = m4Var3;
        f6222n = new m4[]{m4Var, m4Var2, m4Var3};
    }

    public static m4 valueOf(String str) {
        return (m4) Enum.valueOf(m4.class, str);
    }

    public static m4[] values() {
        return (m4[]) f6222n.clone();
    }
}
