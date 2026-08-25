package u9;

import com.google.android.gms.internal.auth.d1;

/* JADX INFO: loaded from: classes5.dex */
public class k implements t {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f22078i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r7.a f22079l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile Object f22080m = o.f22084i;

    public k(p pVar, r7.a aVar) {
        this.f22078i = pVar;
        this.f22079l = aVar;
    }

    public d1 b(boolean z) {
        d1 d1VarH = this.f22078i.h(null, "in a lazy value");
        if (d1VarH != null) {
            return d1VarH;
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue", "recursionDetected"));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004d A[Catch: all -> 0x0026, TRY_LEAVE, TryCatch #0 {all -> 0x0026, blocks: (B:7:0x0015, B:9:0x001b, B:15:0x002a, B:17:0x0035, B:22:0x0042, B:24:0x004a, B:25:0x004d, B:29:0x005c, B:31:0x0062, B:33:0x0066, B:34:0x006d, B:35:0x0074, B:36:0x0075, B:37:0x007b, B:26:0x004f), top: B:40:0x0015, inners: #1 }] */
    @Override // r7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object invoke() throws java.lang.Throwable {
        /*
            r5 = this;
            u9.o r0 = u9.o.f22086m
            u9.o r1 = u9.o.f22085l
            java.lang.Object r2 = r5.f22080m
            boolean r3 = r2 instanceof u9.o
            if (r3 != 0) goto Le
            ea.o.j(r2)
            return r2
        Le:
            u9.p r2 = r5.f22078i
            u9.u r2 = r2.f22090a
            r2.lock()
            java.lang.Object r2 = r5.f22080m     // Catch: java.lang.Throwable -> L26
            boolean r3 = r2 instanceof u9.o     // Catch: java.lang.Throwable -> L26
            if (r3 != 0) goto L28
            ea.o.j(r2)     // Catch: java.lang.Throwable -> L26
            u9.p r0 = r5.f22078i
            u9.u r0 = r0.f22090a
            r0.unlock()
            return r2
        L26:
            r0 = move-exception
            goto L7c
        L28:
            if (r2 != r1) goto L3f
            r5.f22080m = r0     // Catch: java.lang.Throwable -> L26
            r3 = 1
            com.google.android.gms.internal.auth.d1 r3 = r5.b(r3)     // Catch: java.lang.Throwable -> L26
            boolean r4 = r3.f13017b     // Catch: java.lang.Throwable -> L26
            if (r4 != 0) goto L3f
            java.lang.Object r0 = r3.f13018c     // Catch: java.lang.Throwable -> L26
        L37:
            u9.p r1 = r5.f22078i
            u9.u r1 = r1.f22090a
            r1.unlock()
            return r0
        L3f:
            if (r2 != r0) goto L4d
            r0 = 0
            com.google.android.gms.internal.auth.d1 r0 = r5.b(r0)     // Catch: java.lang.Throwable -> L26
            boolean r2 = r0.f13017b     // Catch: java.lang.Throwable -> L26
            if (r2 != 0) goto L4d
            java.lang.Object r0 = r0.f13018c     // Catch: java.lang.Throwable -> L26
            goto L37
        L4d:
            r5.f22080m = r1     // Catch: java.lang.Throwable -> L26
            r7.a r0 = r5.f22079l     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r0 = r0.invoke()     // Catch: java.lang.Throwable -> L5b
            r5.a(r0)     // Catch: java.lang.Throwable -> L5b
            r5.f22080m = r0     // Catch: java.lang.Throwable -> L5b
            goto L37
        L5b:
            r0 = move-exception
            boolean r2 = ea.o.i(r0)     // Catch: java.lang.Throwable -> L26
            if (r2 != 0) goto L75
            java.lang.Object r2 = r5.f22080m     // Catch: java.lang.Throwable -> L26
            if (r2 != r1) goto L6d
            ea.n r1 = new ea.n     // Catch: java.lang.Throwable -> L26
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L26
            r5.f22080m = r1     // Catch: java.lang.Throwable -> L26
        L6d:
            u9.p r1 = r5.f22078i     // Catch: java.lang.Throwable -> L26
            u9.c r1 = r1.f22091b     // Catch: java.lang.Throwable -> L26
            r1.getClass()     // Catch: java.lang.Throwable -> L26
            throw r0     // Catch: java.lang.Throwable -> L26
        L75:
            u9.o r1 = u9.o.f22084i     // Catch: java.lang.Throwable -> L26
            r5.f22080m = r1     // Catch: java.lang.Throwable -> L26
            java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0     // Catch: java.lang.Throwable -> L26
            throw r0     // Catch: java.lang.Throwable -> L26
        L7c:
            u9.p r1 = r5.f22078i
            u9.u r1 = r1.f22090a
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u9.k.invoke():java.lang.Object");
    }

    public final boolean p() {
        return (this.f22080m == o.f22084i || this.f22080m == o.f22085l) ? false : true;
    }

    public void a(Object obj) {
    }
}
