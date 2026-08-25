package h9;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final p f15955i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final p f15956l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final p f15957m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ p[] f15958n;

    static {
        p pVar = new p("ALL", 0);
        f15955i = pVar;
        p pVar2 = new p("ONLY_NON_SYNTHESIZED", 1);
        f15956l = pVar2;
        p pVar3 = new p("NONE", 2);
        f15957m = pVar3;
        f15958n = new p[]{pVar, pVar2, pVar3};
    }

    public static p valueOf(String str) {
        return (p) Enum.valueOf(p.class, str);
    }

    public static p[] values() {
        return (p[]) f15958n.clone();
    }
}
