package p8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public class i0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i0 f21151l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i0 f21152m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i0 f21153n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final h0 f21154o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ i0[] f21155p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f21156i;

    static {
        i0 i0Var = new i0(0, null, "NULL");
        f21151l = i0Var;
        i0 i0Var2 = new i0(1, -1, "INDEX");
        f21152m = i0Var2;
        i0 i0Var3 = new i0(2, Boolean.FALSE, "FALSE");
        f21153n = i0Var3;
        h0 h0Var = new h0(3, null, "MAP_GET_OR_DEFAULT");
        f21154o = h0Var;
        f21155p = new i0[]{i0Var, i0Var2, i0Var3, h0Var};
    }

    public i0(int i10, Object obj, String str) {
        this.f21156i = obj;
    }

    public static i0 valueOf(String str) {
        return (i0) Enum.valueOf(i0.class, str);
    }

    public static i0[] values() {
        return (i0[]) f21155p.clone();
    }
}
