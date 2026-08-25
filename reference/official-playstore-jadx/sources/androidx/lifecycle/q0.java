package androidx.lifecycle;

import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class q0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3272i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3273l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f3274m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f3275n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public /* synthetic */ Object f3276o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3277p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(r rVar, q qVar, androidx.work.impl.constraints.l lVar, d7.d dVar) {
        super(2, dVar);
        this.f3272i = 1;
        this.f3276o = rVar;
        this.f3277p = qVar;
        this.f3274m = lVar;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f3272i) {
            case 0:
                return new q0((ua.d) this.f3277p, (androidx.work.impl.constraints.l) this.f3276o, dVar);
            case 1:
                q0 q0Var = new q0((r) this.f3276o, (q) this.f3277p, (androidx.work.impl.constraints.l) this.f3274m, dVar);
                q0Var.f3275n = obj;
                return q0Var;
            case 2:
                return new q0((e.i) this.f3274m, (r7.p) this.f3276o, (androidx.loader.app.d) this.f3277p, dVar, 2);
            case 3:
                q0 q0Var2 = new q0((na.j) this.f3274m, (na.j1) this.f3276o, (Float) this.f3277p, dVar, 3);
                q0Var2.f3275n = obj;
                return q0Var2;
            case 4:
                return new q0((na.g1) this.f3275n, (na.j) this.f3274m, (na.j1) this.f3276o, (Float) this.f3277p, dVar, 4);
            case 5:
                q0 q0Var3 = new q0((y5.c) this.f3277p, dVar);
                q0Var3.f3276o = obj;
                return q0Var3;
            default:
                return new q0((y5.i) this.f3275n, this.f3274m, (q0) this.f3276o, (na.f1) this.f3277p, dVar, 6);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f3272i) {
            case 0:
                return ((q0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            case 1:
                return ((q0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            case 2:
                return ((q0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            case 3:
                return ((q0) create((na.a1) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            case 4:
                return ((q0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            case 5:
                return ((q0) create((JSONObject) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            default:
                return ((q0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:244:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:252:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f7  */
    /* JADX WARN: Type inference failed for: r2v53, types: [java.lang.Object, java.util.Map] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            Method dump skipped, instruction units count: 1128
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.q0.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q0(Object obj, Object obj2, Object obj3, d7.d dVar, int i10) {
        super(2, dVar);
        this.f3272i = i10;
        this.f3274m = obj;
        this.f3276o = obj2;
        this.f3277p = obj3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q0(Object obj, Object obj2, Object obj3, Serializable serializable, d7.d dVar, int i10) {
        super(2, dVar);
        this.f3272i = i10;
        this.f3275n = obj;
        this.f3274m = obj2;
        this.f3276o = obj3;
        this.f3277p = serializable;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(ua.d dVar, androidx.work.impl.constraints.l lVar, d7.d dVar2) {
        super(2, dVar2);
        this.f3272i = 0;
        this.f3277p = dVar;
        this.f3276o = lVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(y5.c cVar, d7.d dVar) {
        super(2, dVar);
        this.f3272i = 5;
        this.f3277p = cVar;
    }
}
