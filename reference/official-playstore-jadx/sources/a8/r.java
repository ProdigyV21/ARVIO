package a8;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f250i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ u f251l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(u uVar, int i10) {
        super(0);
        this.f250i = i10;
        this.f251l = uVar;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() {
        int iY;
        int i10;
        Type[] lowerBounds;
        int i11 = this.f250i;
        int i12 = 3;
        type = null;
        type = null;
        type = null;
        Type type = null;
        int i13 = 0;
        z = false;
        boolean z = false;
        u uVar = this.f251l;
        switch (i11) {
            case 0:
                List<kotlin.reflect.l> parameters = uVar.getParameters();
                int size = (uVar.isSuspend() ? 1 : 0) + parameters.size();
                if (((Boolean) uVar.f277p.getValue()).booleanValue()) {
                    iY = 0;
                    for (kotlin.reflect.l lVar : parameters) {
                        iY += lVar.getKind() == 4 ? uVar.y(lVar) : 0;
                    }
                } else if (parameters.isEmpty()) {
                    iY = 0;
                } else {
                    Iterator it = parameters.iterator();
                    int i14 = 0;
                    while (it.hasNext()) {
                        if (((kotlin.reflect.l) it.next()).getKind() == 4 && (i14 = i14 + 1) < 0) {
                            t7.a.P();
                            throw null;
                        }
                    }
                    iY = i14;
                }
                int i15 = (iY + 31) / 32;
                Object[] objArr = new Object[size + i15 + 1];
                for (kotlin.reflect.l lVar2 : parameters) {
                    if (lVar2.s()) {
                        u1 type2 = lVar2.getType();
                        f9.c cVar = e2.f188a;
                        v9.w wVar = type2.f282i;
                        if (wVar != null) {
                            int i16 = i9.h.f16250a;
                            g8.h hVarE = wVar.I0().e();
                            if (hVarE != null ? i9.h.b(hVarE) : false) {
                            }
                        }
                        objArr[lVar2.getIndex()] = e2.e(z7.d.d(lVar2.getType()));
                    }
                    if (lVar2.f()) {
                        objArr[lVar2.getIndex()] = u.t(lVar2.getType());
                    }
                }
                for (int i17 = 0; i17 < i15; i17++) {
                    objArr[size + i17] = 0;
                }
                return objArr;
            case 1:
                return e2.d(uVar.x());
            case 2:
                g8.c cVarX = uVar.x();
                ArrayList arrayList = new ArrayList();
                if (uVar.A()) {
                    i10 = 0;
                } else {
                    f9.c cVar2 = e2.f188a;
                    g8.s0 s0VarF0 = cVarX.I() != null ? ((g8.f) cVarX.d()).F0() : null;
                    if (s0VarF0 != null) {
                        arrayList.add(new b1(uVar, 0, 1, new s(s0VarF0, 0)));
                        i10 = 1;
                    } else {
                        i10 = 0;
                    }
                    g8.s0 s0VarM = cVarX.M();
                    if (s0VarM != null) {
                        arrayList.add(new b1(uVar, i10, 3, new s(s0VarM, 1)));
                        i10++;
                    }
                }
                int size2 = cVarX.e().size();
                while (i13 < size2) {
                    arrayList.add(new b1(uVar, i10, 4, new t(cVarX, i13)));
                    i13++;
                    i10++;
                }
                if (uVar.z() && (cVarX instanceof r8.a) && arrayList.size() > 1) {
                    kotlin.collections.w.X(new h(1), arrayList);
                }
                arrayList.trimToSize();
                return arrayList;
            case 3:
                if (uVar.isSuspend()) {
                    Object objY0 = kotlin.collections.x.y0(uVar.u().a());
                    ParameterizedType parameterizedType = objY0 instanceof ParameterizedType ? (ParameterizedType) objY0 : null;
                    if (kotlin.jvm.internal.p.a(parameterizedType != null ? parameterizedType.getRawType() : null, d7.d.class)) {
                        Object objJ0 = kotlin.collections.r.j0(parameterizedType.getActualTypeArguments());
                        WildcardType wildcardType = objJ0 instanceof WildcardType ? (WildcardType) objJ0 : null;
                        if (wildcardType != null && (lowerBounds = wildcardType.getLowerBounds()) != null) {
                            type = (Type) kotlin.collections.r.Y(lowerBounds);
                        }
                    }
                }
                return type == null ? uVar.u().getReturnType() : type;
            case 4:
                return new u1(uVar.x().getReturnType(), new r(uVar, i12));
            case 5:
                List typeParameters = uVar.x().getTypeParameters();
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(typeParameters, 10));
                Iterator it2 = typeParameters.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new v1(uVar, (g8.b1) it2.next()));
                }
                return arrayList2;
            default:
                List parameters2 = uVar.getParameters();
                if (!parameters2.isEmpty()) {
                    Iterator it3 = parameters2.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            u1 type3 = ((kotlin.reflect.l) it3.next()).getType();
                            f9.c cVar3 = e2.f188a;
                            v9.w wVar2 = type3.f282i;
                            if (wVar2 != null && i9.h.f(wVar2)) {
                                z = true;
                            }
                        }
                    }
                }
                return Boolean.valueOf(z);
        }
    }
}
