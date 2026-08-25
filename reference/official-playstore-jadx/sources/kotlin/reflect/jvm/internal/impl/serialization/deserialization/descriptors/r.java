package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.i0;
import a9.t0;
import androidx.emoji2.text.e0;
import g8.p0;
import g8.u0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19891i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f19892l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(Object obj, int i10) {
        super(0);
        this.f19891i = i10;
        this.f19892l = obj;
    }

    /* JADX WARN: Type inference failed for: r0v20, types: [kotlin.jvm.internal.r, r7.a] */
    @Override // r7.a
    public final Object invoke() {
        switch (this.f19891i) {
            case 0:
                s sVar = (s) this.f19892l;
                Set setN = sVar.n();
                if (setN == null) {
                    return null;
                }
                return q0.b0(q0.b0(sVar.m(), sVar.f19895c.d()), setN);
            case 1:
                return (ArrayList) this.f19892l;
            case 2:
                e0 e0Var = (e0) this.f19892l;
                HashSet hashSet = new HashSet();
                k kVar = (k) e0Var.f2603o;
                i iVar = kVar.x;
                s9.m mVar = kVar.f19873v;
                a9.n nVar = kVar.f19866o;
                Iterator it = iVar.d().iterator();
                while (it.hasNext()) {
                    for (g8.k kVar2 : a.a.P(((v9.w) it.next()).l(), null, 3)) {
                        if ((kVar2 instanceof u0) || (kVar2 instanceof p0)) {
                            hashSet.add(kVar2.getName());
                        }
                    }
                }
                Iterator it2 = nVar.A.iterator();
                while (it2.hasNext()) {
                    hashSet.add(t7.a.x(mVar.f21732b, ((i0) it2.next()).f489p));
                }
                Iterator it3 = nVar.B.iterator();
                while (it3.hasNext()) {
                    hashSet.add(t7.a.x(mVar.f21732b, ((t0) it3.next()).f680p));
                }
                return q0.b0(hashSet, hashSet);
            case 3:
                return kotlin.collections.x.g1((Iterable) ((kotlin.jvm.internal.r) this.f19892l).invoke());
            default:
                x xVar = (x) this.f19892l;
                s9.m mVar2 = xVar.f19906u;
                return kotlin.collections.x.c1(mVar2.f21731a.f21710e.r(xVar.f19907v, mVar2.f21732b));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public r(r7.a aVar) {
        super(0);
        this.f19891i = 3;
        this.f19892l = (kotlin.jvm.internal.r) aVar;
    }
}
