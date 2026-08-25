package b8;

import a8.e2;
import androidx.appcompat.app.i1;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f7220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Member f7221c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i1 f7222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final x7.i[] f7223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f7224f;

    /* JADX WARN: Removed duplicated region for block: B:119:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public a0(b8.e r12, g8.c r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 770
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b8.a0.<init>(b8.e, g8.c, boolean):void");
    }

    @Override // b8.e
    public final List a() {
        return this.f7220b.a();
    }

    @Override // b8.e
    public final Member b() {
        return this.f7221c;
    }

    @Override // b8.e
    public final Object call(Object[] objArr) {
        Object objInvoke;
        Object objInvoke2;
        i1 i1Var = this.f7222d;
        x7.i iVar = (x7.i) i1Var.f1059l;
        List[] listArr = (List[]) i1Var.f1060m;
        Method method = (Method) i1Var.f1061n;
        boolean zIsEmpty = iVar.isEmpty();
        int i10 = iVar.f22620l;
        int i11 = iVar.f22619i;
        if (!zIsEmpty) {
            if (this.f7224f) {
                z6.c cVar = new z6.c(objArr.length);
                for (int i12 = 0; i12 < i11; i12++) {
                    cVar.add(objArr[i12]);
                }
                if (i11 <= i10) {
                    while (true) {
                        List<Method> list = listArr[i11];
                        Object obj = objArr[i11];
                        if (list != null) {
                            for (Method method2 : list) {
                                cVar.add(obj != null ? method2.invoke(obj, null) : e2.e(method2.getReturnType()));
                            }
                        } else {
                            cVar.add(obj);
                        }
                        if (i11 == i10) {
                            break;
                        }
                        i11++;
                    }
                }
                int i13 = i10 + 1;
                int length = objArr.length - 1;
                if (i13 <= length) {
                    while (true) {
                        cVar.add(objArr[i13]);
                        if (i13 == length) {
                            break;
                        }
                        i13++;
                    }
                }
                objArr = t7.a.e(cVar).toArray(new Object[0]);
            } else {
                int length2 = objArr.length;
                Object[] objArr2 = new Object[length2];
                for (int i14 = 0; i14 < length2; i14++) {
                    if (i14 > i10 || i11 > i14) {
                        objInvoke2 = objArr[i14];
                    } else {
                        List list2 = listArr[i14];
                        Method method3 = list2 != null ? (Method) kotlin.collections.x.R0(list2) : null;
                        objInvoke2 = objArr[i14];
                        if (method3 != null) {
                            objInvoke2 = objInvoke2 != null ? method3.invoke(objInvoke2, null) : e2.e(method3.getReturnType());
                        }
                    }
                    objArr2[i14] = objInvoke2;
                }
                objArr = objArr2;
            }
        }
        Object objCall = this.f7220b.call(objArr);
        return (objCall == e7.a.f15033i || method == null || (objInvoke = method.invoke(null, objCall)) == null) ? objCall : objInvoke;
    }

    @Override // b8.e
    public final Type getReturnType() {
        return this.f7220b.getReturnType();
    }
}
