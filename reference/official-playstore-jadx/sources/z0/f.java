package z0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Iterator f23079i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f23080l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23081m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23082n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public /* synthetic */ Object f23083o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ List f23084p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ ArrayList f23085q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(List list, ArrayList arrayList, d7.d dVar) {
        super(2, dVar);
        this.f23084p = list;
        this.f23085q = arrayList;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        f fVar = new f(this.f23084p, this.f23085q, dVar);
        fVar.f23083o = obj;
        return fVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create(obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084 A[RETURN] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            int r0 = r8.f23082n
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L2f
            if (r0 == r2) goto L1e
            if (r0 != r1) goto L16
            java.util.Iterator r0 = r8.f23079i
            java.lang.Object r4 = r8.f23083o
            java.util.List r4 = (java.util.List) r4
            k2.c.G(r9)
            goto L3c
        L16:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1e:
            java.lang.Object r0 = r8.f23081m
            z0.c r4 = r8.f23080l
            java.util.Iterator r5 = r8.f23079i
            java.lang.Object r6 = r8.f23083o
            java.util.List r6 = (java.util.List) r6
            k2.c.G(r9)
            r7 = r6
            r6 = r4
            r4 = r7
            goto L5e
        L2f:
            k2.c.G(r9)
            java.lang.Object r9 = r8.f23083o
            java.util.List r0 = r8.f23084p
            java.util.Iterator r0 = r0.iterator()
            java.util.ArrayList r4 = r8.f23085q
        L3c:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L84
            java.lang.Object r5 = r0.next()
            z0.c r5 = (z0.c) r5
            r8.f23083o = r4
            r8.f23079i = r0
            r8.f23080l = r5
            r8.f23081m = r9
            r8.f23082n = r2
            java.lang.Object r6 = r5.b()
            if (r6 != r3) goto L59
            goto L7f
        L59:
            r7 = r0
            r0 = r9
            r9 = r6
            r6 = r5
            r5 = r7
        L5e:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L82
            z0.e r9 = new z0.e
            r0 = 0
            r9.<init>(r6, r0)
            r4.add(r9)
            r8.f23083o = r4
            r8.f23079i = r5
            r8.f23080l = r0
            r8.f23081m = r0
            r8.f23082n = r1
            java.lang.Object r9 = r6.a()
            if (r9 != r3) goto L80
        L7f:
            return r3
        L80:
            r0 = r5
            goto L3c
        L82:
            r9 = r0
            goto L80
        L84:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.f.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
