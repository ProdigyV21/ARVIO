package x5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f22572i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final d f22573l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ d[] f22574m;

    static {
        d dVar = new d("CRASHLYTICS", 0);
        f22572i = dVar;
        d dVar2 = new d("PERFORMANCE", 1);
        f22573l = dVar2;
        f22574m = new d[]{dVar, dVar2, new d("MATT_SAYS_HI", 2)};
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) f22574m.clone();
    }
}
