package ka;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w0 extends ra.h {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19650m;

    public w0(int i10) {
        super(0L, ra.k.f21438g);
        this.f19650m = i10;
    }

    public abstract void b(Object obj, CancellationException cancellationException);

    public abstract d7.d g();

    public Throwable h(Object obj) {
        v vVar = obj instanceof v ? (v) obj : null;
        if (vVar != null) {
            return vVar.f19647a;
        }
        return null;
    }

    public final void j(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            com.google.common.util.concurrent.r0.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        h0.a(g().getContext(), new a8.x1("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object k();

    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r13 = this;
            x6.t0 r0 = x6.t0.f22605a
            ra.i r1 = r13.f21430l
            d7.d r2 = r13.g()     // Catch: java.lang.Throwable -> L20
            pa.g r2 = (pa.g) r2     // Catch: java.lang.Throwable -> L20
            d7.d r3 = r2.f21270o     // Catch: java.lang.Throwable -> L20
            java.lang.Object r2 = r2.f21272q     // Catch: java.lang.Throwable -> L20
            d7.j r4 = r3.getContext()     // Catch: java.lang.Throwable -> L20
            java.lang.Object r2 = pa.c0.b(r4, r2)     // Catch: java.lang.Throwable -> L20
            c2.a r5 = pa.c0.f21257a     // Catch: java.lang.Throwable -> L20
            r6 = 0
            if (r2 == r5) goto L23
            ka.w2 r5 = ka.c0.c(r3, r4, r2)     // Catch: java.lang.Throwable -> L20
            goto L24
        L20:
            r2 = move-exception
            goto L9e
        L23:
            r5 = r6
        L24:
            d7.j r7 = r3.getContext()     // Catch: java.lang.Throwable -> L47
            java.lang.Object r8 = r13.k()     // Catch: java.lang.Throwable -> L47
            java.lang.Throwable r9 = r13.h(r8)     // Catch: java.lang.Throwable -> L47
            if (r9 != 0) goto L49
            int r10 = r13.f19650m     // Catch: java.lang.Throwable -> L47
            r11 = 1
            if (r10 == r11) goto L3c
            r12 = 2
            if (r10 != r12) goto L3b
            goto L3c
        L3b:
            r11 = 0
        L3c:
            if (r11 == 0) goto L49
            ka.u1 r10 = ka.u1.f19642i     // Catch: java.lang.Throwable -> L47
            d7.j$a r7 = r7.get(r10)     // Catch: java.lang.Throwable -> L47
            ka.v1 r7 = (ka.v1) r7     // Catch: java.lang.Throwable -> L47
            goto L4a
        L47:
            r3 = move-exception
            goto L92
        L49:
            r7 = r6
        L4a:
            if (r7 == 0) goto L62
            boolean r10 = r7.isActive()     // Catch: java.lang.Throwable -> L47
            if (r10 != 0) goto L62
            java.util.concurrent.CancellationException r7 = r7.getCancellationException()     // Catch: java.lang.Throwable -> L47
            r13.b(r8, r7)     // Catch: java.lang.Throwable -> L47
            x6.c0 r8 = new x6.c0     // Catch: java.lang.Throwable -> L47
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L47
            r3.resumeWith(r8)     // Catch: java.lang.Throwable -> L47
            goto L74
        L62:
            if (r9 == 0) goto L6d
            x6.c0 r7 = new x6.c0     // Catch: java.lang.Throwable -> L47
            r7.<init>(r9)     // Catch: java.lang.Throwable -> L47
            r3.resumeWith(r7)     // Catch: java.lang.Throwable -> L47
            goto L74
        L6d:
            java.lang.Object r7 = r13.i(r8)     // Catch: java.lang.Throwable -> L47
            r3.resumeWith(r7)     // Catch: java.lang.Throwable -> L47
        L74:
            if (r5 == 0) goto L7c
            boolean r3 = r5.U()     // Catch: java.lang.Throwable -> L20
            if (r3 == 0) goto L7f
        L7c:
            pa.c0.a(r4, r2)     // Catch: java.lang.Throwable -> L20
        L7f:
            r1.getClass()     // Catch: java.lang.Throwable -> L83
            goto L8a
        L83:
            r0 = move-exception
            x6.c0 r1 = new x6.c0
            r1.<init>(r0)
            r0 = r1
        L8a:
            java.lang.Throwable r0 = x6.d0.a(r0)
            r13.j(r6, r0)
            goto Lb0
        L92:
            if (r5 == 0) goto L9a
            boolean r5 = r5.U()     // Catch: java.lang.Throwable -> L20
            if (r5 == 0) goto L9d
        L9a:
            pa.c0.a(r4, r2)     // Catch: java.lang.Throwable -> L20
        L9d:
            throw r3     // Catch: java.lang.Throwable -> L20
        L9e:
            r1.getClass()     // Catch: java.lang.Throwable -> La2
            goto La9
        La2:
            r0 = move-exception
            x6.c0 r1 = new x6.c0
            r1.<init>(r0)
            r0 = r1
        La9:
            java.lang.Throwable r0 = x6.d0.a(r0)
            r13.j(r2, r0)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.w0.run():void");
    }

    public Object i(Object obj) {
        return obj;
    }
}
