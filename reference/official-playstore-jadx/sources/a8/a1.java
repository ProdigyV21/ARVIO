package a8;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class a1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f147i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b1 f148l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a1(b1 b1Var, int i10) {
        super(0);
        this.f147i = i10;
        this.f148l = b1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        x7.i iVar;
        int i10 = this.f147i;
        b1 b1Var = this.f148l;
        switch (i10) {
            case 0:
                return e2.d(b1Var.m());
            default:
                g8.n0 n0VarM = b1Var.m();
                int i11 = b1Var.f165l;
                u uVar = b1Var.f164i;
                if (n0VarM instanceof g8.s0) {
                    g8.c cVarX = uVar.x();
                    f9.c cVar = e2.f188a;
                    if (kotlin.jvm.internal.p.a(cVarX.I() != null ? ((g8.f) cVarX.d()).F0() : null, n0VarM) && uVar.x().getKind() == 2) {
                        Class clsI = e2.i((g8.f) uVar.x().d());
                        if (clsI != null) {
                            return clsI;
                        }
                        throw new x1("Cannot determine receiver Java type of inherited declaration: " + n0VarM);
                    }
                }
                b8.e eVarU = uVar.u();
                if (!(eVarU instanceof b8.a0)) {
                    if (!(eVarU instanceof b8.z)) {
                        return (Type) eVarU.a().get(i11);
                    }
                    Class[] clsArr = (Class[]) ((Collection) ((b8.z) eVarU).f7256d.get(i11)).toArray(new Class[0]);
                    return b1.c(b1Var, (Type[]) Arrays.copyOf(clsArr, clsArr.length));
                }
                b8.a0 a0Var = (b8.a0) eVarU;
                x7.i[] iVarArr = a0Var.f7223e;
                if (i11 >= 0 && i11 < iVarArr.length) {
                    iVar = iVarArr[i11];
                } else if (iVarArr.length == 0) {
                    iVar = new x7.i(i11, i11, 1);
                } else {
                    int length = ((x7.i) kotlin.collections.r.g0(iVarArr)).f22620l + 1 + (i11 - iVarArr.length);
                    iVar = new x7.i(length, length, 1);
                }
                Type[] typeArr = (Type[]) kotlin.collections.x.U0(a0Var.f7220b.a(), iVar).toArray(new Type[0]);
                return b1.c(b1Var, (Type[]) Arrays.copyOf(typeArr, typeArr.length));
        }
    }
}
