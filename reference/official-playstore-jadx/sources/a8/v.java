package a8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f286i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a0 f287l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(a0 a0Var, int i10) {
        super(0);
        this.f286i = i10;
        this.f287l = a0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f286i) {
            case 0:
                a0 a0Var = this.f287l;
                z1 z1Var = a0Var.f144m;
                kotlin.reflect.m[] mVarArr = a0.f134p;
                kotlin.reflect.m mVar = mVarArr[13];
                Collection collection = (Collection) z1Var.invoke();
                z1 z1Var2 = a0Var.f145n;
                kotlin.reflect.m mVar2 = mVarArr[14];
                return kotlin.collections.x.I0(collection, (Collection) z1Var2.invoke());
            case 1:
                a0 a0Var2 = this.f287l;
                z1 z1Var3 = a0Var2.f141i;
                kotlin.reflect.m[] mVarArr2 = a0.f134p;
                kotlin.reflect.m mVar3 = mVarArr2[9];
                Collection collection2 = (Collection) z1Var3.invoke();
                z1 z1Var4 = a0Var2.k;
                kotlin.reflect.m mVar4 = mVarArr2[11];
                return kotlin.collections.x.I0(collection2, (Collection) z1Var4.invoke());
            case 2:
                a0 a0Var3 = this.f287l;
                z1 z1Var5 = a0Var3.f142j;
                kotlin.reflect.m[] mVarArr3 = a0.f134p;
                kotlin.reflect.m mVar5 = mVarArr3[10];
                Collection collection3 = (Collection) z1Var5.invoke();
                z1 z1Var6 = a0Var3.f143l;
                kotlin.reflect.m mVar6 = mVarArr3[12];
                return kotlin.collections.x.I0(collection3, (Collection) z1Var6.invoke());
            case 3:
                return e2.d(this.f287l.a());
            case 4:
                a0 a0Var4 = this.f287l;
                z1 z1Var7 = a0Var4.f141i;
                kotlin.reflect.m[] mVarArr4 = a0.f134p;
                kotlin.reflect.m mVar7 = mVarArr4[9];
                Collection collection4 = (Collection) z1Var7.invoke();
                z1 z1Var8 = a0Var4.f142j;
                kotlin.reflect.m mVar8 = mVarArr4[10];
                return kotlin.collections.x.I0(collection4, (Collection) z1Var8.invoke());
            case 5:
                Collection collectionP = a.a.P(this.f287l.a().R(), null, 3);
                ArrayList<g8.k> arrayList = new ArrayList();
                for (Object obj : collectionP) {
                    if (!i9.f.m((g8.k) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (g8.k kVar : arrayList) {
                    g8.f fVar = kVar instanceof g8.f ? (g8.f) kVar : null;
                    Class clsI = fVar != null ? e2.i(fVar) : null;
                    e0 e0Var = clsI != null ? new e0(clsI) : null;
                    if (e0Var != null) {
                        arrayList2.add(e0Var);
                    }
                }
                return arrayList2;
            default:
                Collection collectionW = this.f287l.a().w();
                ArrayList arrayList3 = new ArrayList();
                Iterator it = collectionW.iterator();
                while (it.hasNext()) {
                    Class clsI2 = e2.i((g8.f) it.next());
                    e0 e0Var2 = clsI2 != null ? new e0(clsI2) : null;
                    if (e0Var2 != null) {
                        arrayList3.add(e0Var2);
                    }
                }
                return arrayList3;
        }
    }
}
