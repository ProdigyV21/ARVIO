package x5;

import j$.util.DesugarCollections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f22570a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f22571b = DesugarCollections.synchronizedMap(new LinkedHashMap());

    public static a a(d dVar) {
        Object obj = f22571b.get(dVar);
        if (obj != null) {
            return (a) obj;
        }
        throw new IllegalStateException("Cannot get dependency " + dVar + ". Dependencies should be added at class load time.");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a6 A[Catch: all -> 0x00bf, TRY_ENTER, TryCatch #0 {all -> 0x00bf, blocks: (B:22:0x0096, B:25:0x00a6, B:26:0x00be), top: B:31:0x0096 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0094 -> B:21:0x0095). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(f7.c r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof x5.b
            if (r0 == 0) goto L13
            r0 = r10
            x5.b r0 = (x5.b) r0
            int r1 = r0.f22569s
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f22569s = r1
            goto L18
        L13:
            x5.b r0 = new x5.b
            r0.<init>(r9, r10)
        L18:
            java.lang.Object r10 = r0.f22567q
            int r1 = r0.f22569s
            r2 = 1
            if (r1 == 0) goto L3d
            if (r1 != r2) goto L35
            java.lang.Object r1 = r0.f22566p
            java.util.Map r3 = r0.f22565o
            java.util.Map r3 = (java.util.Map) r3
            ua.d r4 = r0.f22564n
            x5.d r5 = r0.f22563m
            java.util.Iterator r6 = r0.f22562l
            java.util.Map r7 = r0.f22561i
            java.util.Map r7 = (java.util.Map) r7
            k2.c.G(r10)
            goto L95
        L35:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L3d:
            k2.c.G(r10)
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            java.util.Map r1 = x5.c.f22571b
            int r3 = r1.size()
            int r3 = kotlin.collections.i0.q0(r3)
            r10.<init>(r3)
            java.util.Set r1 = r1.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            r3 = r10
            r6 = r1
        L5b:
            boolean r10 = r6.hasNext()
            if (r10 == 0) goto Lc4
            java.lang.Object r10 = r6.next()
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            java.lang.Object r1 = r10.getKey()
            java.lang.Object r4 = r10.getKey()
            r5 = r4
            x5.d r5 = (x5.d) r5
            java.lang.Object r10 = r10.getValue()
            x5.a r10 = (x5.a) r10
            ua.d r4 = r10.f22559a
            r10 = r3
            java.util.Map r10 = (java.util.Map) r10
            r0.f22561i = r10
            r0.f22562l = r6
            r0.f22563m = r5
            r0.f22564n = r4
            r0.f22565o = r10
            r0.f22566p = r1
            r0.f22569s = r2
            java.lang.Object r10 = r4.c(r0)
            e7.a r7 = e7.a.f15033i
            if (r10 != r7) goto L94
            return r7
        L94:
            r7 = r3
        L95:
            r10 = 0
            x5.a r8 = a(r5)     // Catch: java.lang.Throwable -> Lbf
            com.google.firebase.crashlytics.internal.common.CrashlyticsAppQualitySessionsSubscriber r8 = r8.f22560b     // Catch: java.lang.Throwable -> Lbf
            if (r8 == 0) goto La6
            r4.b(r10)
            r3.put(r1, r8)
            r3 = r7
            goto L5b
        La6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lbf
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r2 = "Subscriber "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Lbf
            r1.append(r5)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r2 = " has not been registered."
            r1.append(r2)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lbf
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lbf
            throw r0     // Catch: java.lang.Throwable -> Lbf
        Lbf:
            r0 = move-exception
            r4.b(r10)
            throw r0
        Lc4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: x5.c.b(f7.c):java.lang.Object");
    }
}
