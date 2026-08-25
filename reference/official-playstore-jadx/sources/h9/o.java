package h9;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class o {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final o f15952i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final o f15953l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ o[] f15954m;

    static {
        o oVar = new o("RENDER_OVERRIDE", 0);
        f15952i = oVar;
        o oVar2 = new o("RENDER_OPEN", 1);
        f15953l = oVar2;
        f15954m = new o[]{oVar, oVar2, new o("RENDER_OPEN_OVERRIDE", 2)};
    }

    public static o valueOf(String str) {
        return (o) Enum.valueOf(o.class, str);
    }

    public static o[] values() {
        return (o[]) f15954m.clone();
    }
}
