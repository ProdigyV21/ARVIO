package e7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f15033i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f15034l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f15035m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ a[] f15036n;

    static {
        a aVar = new a("COROUTINE_SUSPENDED", 0);
        f15033i = aVar;
        a aVar2 = new a("UNDECIDED", 1);
        f15034l = aVar2;
        a aVar3 = new a("RESUMED", 2);
        f15035m = aVar3;
        f15036n = new a[]{aVar, aVar2, aVar3};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f15036n.clone();
    }
}
