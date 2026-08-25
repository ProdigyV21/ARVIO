package na;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class j1 extends oa.a implements q0, d, oa.p {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20599p = AtomicReferenceFieldUpdater.newUpdater(j1.class, Object.class, "_state");

    @q7.w
    private volatile Object _state;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20600o;

    public j1(Object obj) {
        this._state = obj;
    }

    @Override // na.p0
    public final boolean b(Object obj) {
        setValue(obj);
        return true;
    }

    @Override // oa.a
    public final oa.c c() {
        return new k1();
    }

    /* JADX WARN: Path cross not found for [B:58:0x00f4, B:59:0x00f5], limit reached: 66 */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085 A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:14:0x0039, B:28:0x007d, B:30:0x0085, B:33:0x008c, B:34:0x0090, B:36:0x0093, B:46:0x00b4, B:49:0x00c4, B:50:0x00de, B:56:0x00ee, B:53:0x00e5, B:55:0x00eb, B:38:0x0099, B:42:0x00a0, B:21:0x0053, B:24:0x005d, B:27:0x006e), top: B:63:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0093 A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:14:0x0039, B:28:0x007d, B:30:0x0085, B:33:0x008c, B:34:0x0090, B:36:0x0093, B:46:0x00b4, B:49:0x00c4, B:50:0x00de, B:56:0x00ee, B:53:0x00e5, B:55:0x00eb, B:38:0x0099, B:42:0x00a0, B:21:0x0053, B:24:0x005d, B:27:0x006e), top: B:63:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c4 A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:14:0x0039, B:28:0x007d, B:30:0x0085, B:33:0x008c, B:34:0x0090, B:36:0x0093, B:46:0x00b4, B:49:0x00c4, B:50:0x00de, B:56:0x00ee, B:53:0x00e5, B:55:0x00eb, B:38:0x0099, B:42:0x00a0, B:21:0x0053, B:24:0x005d, B:27:0x006e), top: B:63:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x00c3 -> B:28:0x007d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // na.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(na.k r17, d7.d r18) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: na.j1.collect(na.k, d7.d):java.lang.Object");
    }

    @Override // oa.a
    public final oa.c[] d() {
        return new k1[2];
    }

    @Override // na.q0
    public final boolean e(Object obj, Object obj2) {
        c2.a aVar = oa.b.f20807b;
        if (obj == null) {
            obj = aVar;
        }
        if (obj2 == null) {
            obj2 = aVar;
        }
        return h(obj, obj2);
    }

    @Override // na.p0, na.k
    public final Object emit(Object obj, d7.d dVar) {
        setValue(obj);
        return x6.t0.f22605a;
    }

    @Override // na.q0, na.h1
    public final Object getValue() {
        c2.a aVar = oa.b.f20807b;
        Object obj = f20599p.get(this);
        if (obj == aVar) {
            return null;
        }
        return obj;
    }

    public final boolean h(Object obj, Object obj2) {
        int i10;
        oa.c[] cVarArr;
        c2.a aVar;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f20599p;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !kotlin.jvm.internal.p.a(obj3, obj)) {
                return false;
            }
            if (kotlin.jvm.internal.p.a(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i11 = this.f20600o;
            if ((i11 & 1) != 0) {
                this.f20600o = i11 + 2;
                return true;
            }
            int i12 = i11 + 1;
            this.f20600o = i12;
            oa.c[] cVarArr2 = this.f20802i;
            while (true) {
                k1[] k1VarArr = (k1[]) cVarArr2;
                if (k1VarArr != null) {
                    for (k1 k1Var : k1VarArr) {
                        if (k1Var != null) {
                            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = k1.f20604a;
                            while (true) {
                                Object obj4 = atomicReferenceFieldUpdater2.get(k1Var);
                                if (obj4 != null && obj4 != (aVar = y0.f20691c)) {
                                    c2.a aVar2 = y0.f20690b;
                                    if (obj4 != aVar2) {
                                        while (!atomicReferenceFieldUpdater2.compareAndSet(k1Var, obj4, aVar2)) {
                                            if (atomicReferenceFieldUpdater2.get(k1Var) != obj4) {
                                                break;
                                            }
                                        }
                                        ((ka.l) obj4).resumeWith(x6.t0.f22605a);
                                        break;
                                    }
                                    while (!atomicReferenceFieldUpdater2.compareAndSet(k1Var, obj4, aVar)) {
                                        if (atomicReferenceFieldUpdater2.get(k1Var) != obj4) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i10 = this.f20600o;
                    if (i10 == i12) {
                        this.f20600o = i12 + 1;
                        return true;
                    }
                    cVarArr = this.f20802i;
                }
                cVarArr2 = cVarArr;
                i12 = i10;
            }
        }
    }

    @Override // na.q0
    public final void setValue(Object obj) {
        if (obj == null) {
            obj = oa.b.f20807b;
        }
        h(null, obj);
    }
}
