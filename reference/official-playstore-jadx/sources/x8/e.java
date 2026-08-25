package x8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f22644i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f22645l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ e[] f22646m;

    static {
        e eVar = new e("READ_ONLY", 0);
        f22644i = eVar;
        e eVar2 = new e("MUTABLE", 1);
        f22645l = eVar2;
        f22646m = new e[]{eVar, eVar2};
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f22646m.clone();
    }
}
