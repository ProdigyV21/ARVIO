package o8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class e implements b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f20793i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f20794l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f20795m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f20796n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final e f20797o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f20798p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e f20799q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final e f20800r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ e[] f20801s;

    /* JADX INFO: Fake field, exist only in values array */
    e EF0;

    static {
        e eVar = new e("FROM_IDE", 0);
        e eVar2 = new e("FROM_BACKEND", 1);
        e eVar3 = new e("FROM_TEST", 2);
        e eVar4 = new e("FROM_BUILTINS", 3);
        f20793i = eVar4;
        e eVar5 = new e("WHEN_CHECK_DECLARATION_CONFLICTS", 4);
        e eVar6 = new e("WHEN_CHECK_OVERRIDES", 5);
        e eVar7 = new e("FOR_SCRIPT", 6);
        e eVar8 = new e("FROM_REFLECTION", 7);
        f20794l = eVar8;
        e eVar9 = new e("WHEN_RESOLVE_DECLARATION", 8);
        e eVar10 = new e("WHEN_GET_DECLARATION_SCOPE", 9);
        e eVar11 = new e("WHEN_RESOLVING_DEFAULT_TYPE_ARGUMENTS", 10);
        e eVar12 = new e("FOR_ALREADY_TRACKED", 11);
        f20795m = eVar12;
        e eVar13 = new e("WHEN_GET_ALL_DESCRIPTORS", 12);
        f20796n = eVar13;
        e eVar14 = new e("WHEN_TYPING", 13);
        e eVar15 = new e("WHEN_GET_SUPER_MEMBERS", 14);
        f20797o = eVar15;
        e eVar16 = new e("FOR_NON_TRACKED_SCOPE", 15);
        f20798p = eVar16;
        e eVar17 = new e("FROM_SYNTHETIC_SCOPE", 16);
        e eVar18 = new e("FROM_DESERIALIZATION", 17);
        f20799q = eVar18;
        e eVar19 = new e("FROM_JAVA_LOADER", 18);
        f20800r = eVar19;
        f20801s = new e[]{eVar, eVar2, eVar3, eVar4, eVar5, eVar6, eVar7, eVar8, eVar9, eVar10, eVar11, eVar12, eVar13, eVar14, eVar15, eVar16, eVar17, eVar18, eVar19, new e("WHEN_GET_LOCAL_VARIABLE", 19), new e("WHEN_FIND_BY_FQNAME", 20), new e("WHEN_GET_COMPANION_OBJECT", 21), new e("FOR_DEFAULT_IMPORTS", 22)};
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f20801s.clone();
    }
}
