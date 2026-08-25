package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q f3266i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final q f3267l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final q f3268m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final q f3269n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final q f3270o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ q[] f3271p;

    static {
        q qVar = new q("DESTROYED", 0);
        f3266i = qVar;
        q qVar2 = new q("INITIALIZED", 1);
        f3267l = qVar2;
        q qVar3 = new q("CREATED", 2);
        f3268m = qVar3;
        q qVar4 = new q("STARTED", 3);
        f3269n = qVar4;
        q qVar5 = new q("RESUMED", 4);
        f3270o = qVar5;
        f3271p = new q[]{qVar, qVar2, qVar3, qVar4, qVar5};
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) f3271p.clone();
    }

    public final boolean a(q qVar) {
        return compareTo(qVar) >= 0;
    }
}
