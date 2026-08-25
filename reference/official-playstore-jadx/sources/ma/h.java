package ma;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f20323a = new p(-1, null, null, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f20324b = pa.a.j(32, 12, "kotlinx.coroutines.bufferedChannel.segmentSize");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f20325c = pa.a.j(10000, 12, "kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c2.a f20326d = new c2.a("BUFFERED", 5);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c2.a f20327e = new c2.a("SHOULD_BUFFER", 5);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c2.a f20328f = new c2.a("S_RESUMING_BY_RCV", 5);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c2.a f20329g = new c2.a("RESUMING_BY_EB", 5);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c2.a f20330h = new c2.a("POISONED", 5);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c2.a f20331i = new c2.a("DONE_RCV", 5);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final c2.a f20332j = new c2.a("INTERRUPTED_SEND", 5);
    public static final c2.a k = new c2.a("INTERRUPTED_RCV", 5);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c2.a f20333l = new c2.a("CHANNEL_CLOSED", 5);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c2.a f20334m = new c2.a("SUSPEND", 5);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c2.a f20335n = new c2.a("SUSPEND_NO_WAITER", 5);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final c2.a f20336o = new c2.a("FAILED", 5);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final c2.a f20337p = new c2.a("NO_RECEIVE_RESULT", 5);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final c2.a f20338q = new c2.a("CLOSE_HANDLER_CLOSED", 5);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final c2.a f20339r = new c2.a("CLOSE_HANDLER_INVOKED", 5);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final c2.a f20340s = new c2.a("NO_CLOSE_CAUSE", 5);

    public static final boolean a(ka.k kVar, Object obj, pa.r rVar) {
        c2.a aVarA = kVar.a(obj, rVar);
        if (aVarA == null) {
            return false;
        }
        kVar.f(aVarA);
        return true;
    }
}
