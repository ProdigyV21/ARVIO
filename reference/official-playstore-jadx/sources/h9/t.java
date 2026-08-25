package h9;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public abstract class t {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final s f15962i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final r f15963l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ t[] f15964m;

    static {
        s sVar = new s();
        f15962i = sVar;
        r rVar = new r();
        f15963l = rVar;
        f15964m = new t[]{sVar, rVar};
    }

    public static t valueOf(String str) {
        return (t) Enum.valueOf(t.class, str);
    }

    public static t[] values() {
        return (t[]) f15964m.clone();
    }

    public abstract String a(String str);
}
