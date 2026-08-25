package h8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class m {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final m f15873i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final m f15874l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m f15875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ m[] f15876n;

    static {
        m mVar = new m("RUNTIME", 0);
        f15873i = mVar;
        m mVar2 = new m("BINARY", 1);
        f15874l = mVar2;
        m mVar3 = new m("SOURCE", 2);
        f15875m = mVar3;
        f15876n = new m[]{mVar, mVar2, mVar3};
    }

    public static m valueOf(String str) {
        return (m) Enum.valueOf(m.class, str);
    }

    public static m[] values() {
        return (m[]) f15876n.clone();
    }
}
