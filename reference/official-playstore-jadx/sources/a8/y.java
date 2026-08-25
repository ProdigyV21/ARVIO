package a8;

import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f306i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f307l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f308m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f309n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(Object obj, Object obj2, Object obj3, int i10) {
        super(0);
        this.f306i = i10;
        this.f307l = obj;
        this.f308m = obj2;
        this.f309n = obj3;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f306i) {
            case 0:
                a0 a0Var = (a0) this.f308m;
                e0 e0Var = (e0) this.f309n;
                g8.h hVarE = ((v9.w) this.f307l).I0().e();
                if (!(hVarE instanceof g8.f)) {
                    throw new x1("Supertype not a class: " + hVarE);
                }
                Class clsI = e2.i((g8.f) hVarE);
                if (clsI == null) {
                    throw new x1("Unsupported superclass of " + a0Var + ": " + hVarE);
                }
                Class cls = e0Var.f185l;
                if (kotlin.jvm.internal.p.a(cls.getSuperclass(), clsI)) {
                    return cls.getGenericSuperclass();
                }
                int iD0 = kotlin.collections.r.d0(cls.getInterfaces(), clsI);
                if (iD0 >= 0) {
                    return cls.getGenericInterfaces()[iD0];
                }
                throw new x1("No superclass of " + a0Var + " in Java reflection for " + hVarE);
            case 1:
                return ((kotlin.reflect.jvm.internal.impl.protobuf.e0) this.f307l).a((ByteArrayInputStream) this.f308m, ((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s) this.f309n).f19894b.f21731a.f21720p);
            default:
                return ((gb.k) this.f307l).f15729b.i(((gb.a) this.f309n).f15612h.f15620d, ((gb.y) this.f308m).a());
        }
    }
}
