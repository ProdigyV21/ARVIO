package z2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c f23164i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c f23165l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f23166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ c[] f23167n;

    static {
        c cVar = new c("DEFAULT", 0);
        f23164i = cVar;
        c cVar2 = new c("VERY_LOW", 1);
        f23165l = cVar2;
        c cVar3 = new c("HIGHEST", 2);
        f23166m = cVar3;
        f23167n = new c[]{cVar, cVar2, cVar3};
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) f23167n.clone();
    }
}
