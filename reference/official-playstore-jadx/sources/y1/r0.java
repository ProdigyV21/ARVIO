package y1;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
public final class r0 implements c2.f, c2.e {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final TreeMap f22851s = new TreeMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22852i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile String f22853l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long[] f22854m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final double[] f22855n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String[] f22856o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final byte[][] f22857p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int[] f22858q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f22859r;

    public r0(int i10) {
        this.f22852i = i10;
        int i11 = i10 + 1;
        this.f22858q = new int[i11];
        this.f22854m = new long[i11];
        this.f22855n = new double[i11];
        this.f22856o = new String[i11];
        this.f22857p = new byte[i11][];
    }

    public static final r0 k(int i10, String str) {
        TreeMap treeMap = f22851s;
        synchronized (treeMap) {
            Map.Entry entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i10));
            if (entryCeilingEntry == null) {
                r0 r0Var = new r0(i10);
                r0Var.f22853l = str;
                r0Var.f22859r = i10;
                return r0Var;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            r0 r0Var2 = (r0) entryCeilingEntry.getValue();
            r0Var2.f22853l = str;
            r0Var2.f22859r = i10;
            return r0Var2;
        }
    }

    @Override // c2.e
    public final void C0(int i10) {
        this.f22858q[i10] = 1;
    }

    @Override // c2.e
    public final void e0(int i10, String str) {
        this.f22858q[i10] = 4;
        this.f22856o[i10] = str;
    }

    @Override // c2.f
    public final String i() {
        String str = this.f22853l;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // c2.f
    public final void j(c2.e eVar) {
        int i10 = this.f22859r;
        if (1 > i10) {
            return;
        }
        int i11 = 1;
        while (true) {
            int i12 = this.f22858q[i11];
            if (i12 == 1) {
                eVar.C0(i11);
            } else if (i12 == 2) {
                eVar.n0(i11, this.f22854m[i11]);
            } else if (i12 == 3) {
                eVar.u(i11, this.f22855n[i11]);
            } else if (i12 == 4) {
                String str = this.f22856o[i11];
                if (str == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                eVar.e0(i11, str);
            } else if (i12 == 5) {
                byte[] bArr = this.f22857p[i11];
                if (bArr == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                eVar.o0(i11, bArr);
            }
            if (i11 == i10) {
                return;
            } else {
                i11++;
            }
        }
    }

    public final void l() {
        TreeMap treeMap = f22851s;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.f22852i), this);
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator it = treeMap.descendingKeySet().iterator();
                while (true) {
                    int i10 = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    it.next();
                    it.remove();
                    size = i10;
                }
            }
        }
    }

    @Override // c2.e
    public final void n0(int i10, long j10) {
        this.f22858q[i10] = 2;
        this.f22854m[i10] = j10;
    }

    @Override // c2.e
    public final void o0(int i10, byte[] bArr) {
        this.f22858q[i10] = 5;
        this.f22857p[i10] = bArr;
    }

    @Override // c2.e
    public final void u(int i10, double d4) {
        this.f22858q[i10] = 3;
        this.f22855n[i10] = d4;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
