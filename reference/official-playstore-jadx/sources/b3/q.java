package b3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q f7192i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ q[] f7193l;

    /* JADX INFO: Fake field, exist only in values array */
    q EF0;

    static {
        q qVar = new q("UNKNOWN", 0);
        q qVar2 = new q("ANDROID_FIREBASE", 1);
        f7192i = qVar2;
        f7193l = new q[]{qVar, qVar2};
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) f7193l.clone();
    }
}
