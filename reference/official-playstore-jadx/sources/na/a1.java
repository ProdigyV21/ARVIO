package na;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class a1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a1 f20525i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a1 f20526l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a1 f20527m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ a1[] f20528n;

    static {
        a1 a1Var = new a1("START", 0);
        f20525i = a1Var;
        a1 a1Var2 = new a1("STOP", 1);
        f20526l = a1Var2;
        a1 a1Var3 = new a1("STOP_AND_RESET_REPLAY_CACHE", 2);
        f20527m = a1Var3;
        f20528n = new a1[]{a1Var, a1Var2, a1Var3};
    }

    public static a1 valueOf(String str) {
        return (a1) Enum.valueOf(a1.class, str);
    }

    public static a1[] values() {
        return (a1[]) f20528n.clone();
    }
}
