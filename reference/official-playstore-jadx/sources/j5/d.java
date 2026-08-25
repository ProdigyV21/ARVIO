package j5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f19212i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ d[] f19213l;

    static {
        d dVar = new d("DEFAULT", 0);
        f19212i = dVar;
        f19213l = new d[]{dVar, new d("SIGNED", 1), new d("FIXED", 2)};
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) f19213l.clone();
    }
}
