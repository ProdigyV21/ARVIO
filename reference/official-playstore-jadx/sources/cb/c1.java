package cb;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class c1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7470i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d1 f7471l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c1(d1 d1Var, int i10) {
        super(0);
        this.f7470i = i10;
        this.f7471l = d1Var;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() {
        ya.h[] hVarArrChildSerializers;
        ArrayList arrayList;
        ya.h[] hVarArrTypeParametersSerializers;
        switch (this.f7470i) {
            case 0:
                d1 d1Var = this.f7471l;
                return Integer.valueOf(b1.e(d1Var, (ab.g[]) d1Var.f7486j.getValue()));
            case 1:
                e0 e0Var = this.f7471l.f7478b;
                return (e0Var == null || (hVarArrChildSerializers = e0Var.childSerializers()) == null) ? b1.f7464b : hVarArrChildSerializers;
            default:
                e0 e0Var2 = this.f7471l.f7478b;
                if (e0Var2 == null || (hVarArrTypeParametersSerializers = e0Var2.typeParametersSerializers()) == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(hVarArrTypeParametersSerializers.length);
                    for (ya.h hVar : hVarArrTypeParametersSerializers) {
                        arrayList.add(hVar.getDescriptor());
                    }
                }
                return b1.c(arrayList);
        }
    }
}
