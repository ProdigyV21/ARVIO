package u9;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class o {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final o f22084i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final o f22085l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final o f22086m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ o[] f22087n;

    static {
        o oVar = new o("NOT_COMPUTED", 0);
        f22084i = oVar;
        o oVar2 = new o("COMPUTING", 1);
        f22085l = oVar2;
        o oVar3 = new o("RECURSION_WAS_DETECTED", 2);
        f22086m = oVar3;
        f22087n = new o[]{oVar, oVar2, oVar3};
    }

    public static o valueOf(String str) {
        return (o) Enum.valueOf(o.class, str);
    }

    public static o[] values() {
        return (o[]) f22087n.clone();
    }
}
