package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class x6 {
    public static final x6 OP;
    public static final x6 SPLITERATOR;
    public static final x6 STREAM;
    public static final x6 TERMINAL_OP;
    public static final x6 UPSTREAM_TERMINAL_OP;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ x6[] f18952a;

    public static x6 valueOf(String str) {
        return (x6) Enum.valueOf(x6.class, str);
    }

    public static x6[] values() {
        return (x6[]) f18952a.clone();
    }

    static {
        x6 x6Var = new x6("SPLITERATOR", 0);
        SPLITERATOR = x6Var;
        x6 x6Var2 = new x6("STREAM", 1);
        STREAM = x6Var2;
        x6 x6Var3 = new x6("OP", 2);
        OP = x6Var3;
        x6 x6Var4 = new x6("TERMINAL_OP", 3);
        TERMINAL_OP = x6Var4;
        x6 x6Var5 = new x6("UPSTREAM_TERMINAL_OP", 4);
        UPSTREAM_TERMINAL_OP = x6Var5;
        f18952a = new x6[]{x6Var, x6Var2, x6Var3, x6Var4, x6Var5};
    }
}
