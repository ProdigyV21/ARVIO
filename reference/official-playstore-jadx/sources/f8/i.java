package f8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final i f15322i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i f15323l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i f15324m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f15325n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final i f15326o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ i[] f15327p;

    static {
        i iVar = new i("HIDDEN", 0);
        f15322i = iVar;
        i iVar2 = new i("VISIBLE", 1);
        f15323l = iVar2;
        i iVar3 = new i("DEPRECATED_LIST_METHODS", 2);
        f15324m = iVar3;
        i iVar4 = new i("NOT_CONSIDERED", 3);
        f15325n = iVar4;
        i iVar5 = new i("DROP", 4);
        f15326o = iVar5;
        f15327p = new i[]{iVar, iVar2, iVar3, iVar4, iVar5};
    }

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) f15327p.clone();
    }
}
