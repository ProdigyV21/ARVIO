package j$.time.zone;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static final d STANDARD;
    public static final d UTC;
    public static final d WALL;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ d[] f18395a;

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) f18395a.clone();
    }

    static {
        d dVar = new d("UTC", 0);
        UTC = dVar;
        d dVar2 = new d("WALL", 1);
        WALL = dVar2;
        d dVar3 = new d("STANDARD", 2);
        STANDARD = dVar3;
        f18395a = new d[]{dVar, dVar2, dVar3};
    }
}
