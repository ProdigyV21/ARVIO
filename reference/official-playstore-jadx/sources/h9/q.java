package h9;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q f15959i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final q f15960l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ q[] f15961m;

    /* JADX INFO: Fake field, exist only in values array */
    q EF0;

    static {
        q qVar = new q("PRETTY", 0);
        q qVar2 = new q("DEBUG", 1);
        f15959i = qVar2;
        q qVar3 = new q("NONE", 2);
        f15960l = qVar3;
        f15961m = new q[]{qVar, qVar2, qVar3};
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) f15961m.clone();
    }
}
