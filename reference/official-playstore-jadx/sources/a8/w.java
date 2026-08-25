package a8;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f294i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ e0 f295l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(e0 e0Var, int i10) {
        super(0);
        this.f294i = i10;
        this.f295l = e0Var;
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() throws InvocationTargetException {
        int i10 = this.f294i;
        e0 e0Var = this.f295l;
        switch (i10) {
            case 0:
                Collection collectionT = e0Var.t();
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collectionT, 10));
                Iterator it = collectionT.iterator();
                while (it.hasNext()) {
                    arrayList.add(new m0(e0Var, (g8.j) it.next()));
                }
                return arrayList;
            case 1:
                return e0Var.w(e0Var.getDescriptor().m().l(), 1);
            case 2:
                return e0Var.w(e0Var.getDescriptor().j0(), 1);
            case 3:
                int i11 = e0.f184n;
                f9.b bVarE = e0Var.E();
                Class cls = e0Var.f185l;
                z1 z1Var = ((a0) e0Var.f186m.getValue()).f199a;
                kotlin.reflect.m mVar = h0.f198b[0];
                l8.h hVar = (l8.h) z1Var.invoke();
                s9.j jVar = hVar.f20068a;
                g8.f fVarD = (bVarE.f15357c && cls.isAnnotationPresent(Metadata.class)) ? (g8.f) jVar.f21724t.f21696b.invoke(new s9.f(bVarE, null)) : g8.y.d(jVar.f21707b, bVarE);
                if (fVarD != null) {
                    return fVarD;
                }
                if (cls.isSynthetic()) {
                    return e0.D(bVarE, hVar);
                }
                l8.e eVarA = l8.d.a(cls);
                z8.a aVar = eVarA != null ? (z8.a) eVarA.f20064b.f20777d : null;
                switch (aVar == null ? -1 : b0.f162a[aVar.ordinal()]) {
                    case -1:
                    case 6:
                        throw new x1("Unresolved class: " + cls + " (kind = " + aVar + ')');
                    case 0:
                    default:
                        throw new NoWhenBranchMatchedException();
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return e0.D(bVarE, hVar);
                    case 5:
                        throw new x1("Unknown class: " + cls + " (kind = " + aVar + ')');
                }
            case 4:
                return e0Var.w(e0Var.getDescriptor().m().l(), 2);
            case 5:
                return e0Var.w(e0Var.getDescriptor().j0(), 2);
            case 6:
                if (e0Var.f185l.isAnonymousClass()) {
                    return null;
                }
                f9.b bVarE2 = e0Var.E();
                if (bVarE2.f15357c) {
                    return null;
                }
                return bVarE2.b().b();
            case 7:
                Class cls2 = e0Var.f185l;
                if (cls2.isAnonymousClass()) {
                    return null;
                }
                f9.b bVarE3 = e0Var.E();
                if (!bVarE3.f15357c) {
                    return bVarE3.i().d();
                }
                String simpleName = cls2.getSimpleName();
                Method enclosingMethod = cls2.getEnclosingMethod();
                if (enclosingMethod != null) {
                    return kotlin.text.o.D0(simpleName, enclosingMethod.getName() + '$', simpleName);
                }
                Constructor<?> enclosingConstructor = cls2.getEnclosingConstructor();
                if (enclosingConstructor == null) {
                    return kotlin.text.o.C0('$', simpleName, simpleName);
                }
                return kotlin.text.o.D0(simpleName, enclosingConstructor.getName() + '$', simpleName);
            default:
                return new a0(e0Var);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(e0 e0Var, a0 a0Var) {
        super(0);
        this.f294i = 7;
        this.f295l = e0Var;
    }
}
