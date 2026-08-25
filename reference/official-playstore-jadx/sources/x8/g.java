package x8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g f22648i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final g f22649l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final g f22650m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ g[] f22651n;

    static {
        g gVar = new g("FORCE_FLEXIBILITY", 0);
        f22648i = gVar;
        g gVar2 = new g("NULLABLE", 1);
        f22649l = gVar2;
        g gVar3 = new g("NOT_NULL", 2);
        f22650m = gVar3;
        f22651n = new g[]{gVar, gVar2, gVar3};
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f22651n.clone();
    }
}
