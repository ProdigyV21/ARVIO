package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19929i = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19930l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19931m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public x7.i f19932n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f19933o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ c f19934p;

    public b(c cVar) {
        this.f19934p = cVar;
        int iN = qb.d.n(0, 0, cVar.f19935a.length());
        this.f19930l = iN;
        this.f19931m = iN;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r7 = this;
            int r0 = r7.f19931m
            r1 = 0
            if (r0 >= 0) goto Lb
            r7.f19929i = r1
            r0 = 0
            r7.f19932n = r0
            return
        Lb:
            kotlin.text.c r2 = r7.f19934p
            int r3 = r2.f19936b
            r4 = -1
            r5 = 1
            if (r3 <= 0) goto L1a
            int r6 = r7.f19933o
            int r6 = r6 + r5
            r7.f19933o = r6
            if (r6 >= r3) goto L22
        L1a:
            java.lang.CharSequence r3 = r2.f19935a
            int r3 = r3.length()
            if (r0 <= r3) goto L34
        L22:
            x7.i r0 = new x7.i
            int r1 = r7.f19930l
            java.lang.CharSequence r2 = r2.f19935a
            int r2 = kotlin.text.o.b0(r2)
            r0.<init>(r1, r2, r5)
            r7.f19932n = r0
            r7.f19931m = r4
            goto L79
        L34:
            r7.p r0 = r2.f19937c
            java.lang.CharSequence r3 = r2.f19935a
            int r6 = r7.f19931m
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r0 = r0.invoke(r3, r6)
            x6.x r0 = (x6.x) r0
            if (r0 != 0) goto L58
            x7.i r0 = new x7.i
            int r1 = r7.f19930l
            java.lang.CharSequence r2 = r2.f19935a
            int r2 = kotlin.text.o.b0(r2)
            r0.<init>(r1, r2, r5)
            r7.f19932n = r0
            r7.f19931m = r4
            goto L79
        L58:
            java.lang.Object r2 = r0.f22608i
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.f22609l
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r3 = r7.f19930l
            x7.i r3 = qb.d.X(r3, r2)
            r7.f19932n = r3
            int r2 = r2 + r0
            r7.f19930l = r2
            if (r0 != 0) goto L76
            r1 = r5
        L76:
            int r2 = r2 + r1
            r7.f19931m = r2
        L79:
            r7.f19929i = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.b.a():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f19929i == -1) {
            a();
        }
        return this.f19929i == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f19929i == -1) {
            a();
        }
        if (this.f19929i == 0) {
            throw new NoSuchElementException();
        }
        x7.i iVar = this.f19932n;
        this.f19932n = null;
        this.f19929i = -1;
        return iVar;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
