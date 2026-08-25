package a8;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes5.dex */
public class a2 extends kotlin.jvm.internal.m0 {
    public static j0 l(kotlin.jvm.internal.e eVar) {
        kotlin.reflect.f owner = eVar.getOwner();
        return owner instanceof j0 ? (j0) owner : f.f189l;
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.g a(kotlin.jvm.internal.l lVar) {
        return new m0(l(lVar), lVar.getName(), lVar.getSignature(), null, lVar.getBoundReceiver());
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.d b(Class cls) {
        return (e0) c.f170a.c(cls);
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.f c(Class cls, String str) {
        return (kotlin.reflect.f) c.f171b.c(cls);
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.i d(kotlin.jvm.internal.s sVar) {
        return new o0(l(sVar), sVar.getName(), sVar.getSignature(), sVar.getBoundReceiver());
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.j e(kotlin.jvm.internal.u uVar) {
        return new q0(l(uVar), uVar.getName(), uVar.getSignature(), uVar.getBoundReceiver());
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.n f(kotlin.jvm.internal.y yVar) {
        return new e1(l(yVar), yVar.getName(), yVar.getSignature(), yVar.getBoundReceiver());
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.o g(kotlin.jvm.internal.a0 a0Var) {
        return new h1(l(a0Var), a0Var.getName(), a0Var.getSignature(), a0Var.getBoundReceiver());
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.p h(kotlin.jvm.internal.c0 c0Var) {
        return new k1(l(c0Var), c0Var.getName(), c0Var.getSignature());
    }

    @Override // kotlin.jvm.internal.m0
    public final String i(kotlin.jvm.internal.k kVar) throws IOException {
        m0 m0VarB;
        Metadata metadata = (Metadata) kVar.getClass().getAnnotation(Metadata.class);
        m0 m0Var = null;
        if (metadata != null) {
            String[] strArrD1 = metadata.d1();
            if (strArrD1.length == 0) {
                strArrD1 = null;
            }
            if (strArrD1 != null) {
                String[] strArrD2 = metadata.d2();
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar = e9.h.f15080a;
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(e9.a.a(strArrD1));
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar2 = e9.h.f15080a;
                e9.g gVarG = e9.h.g(byteArrayInputStream, strArrD2);
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar3 = e9.h.f15080a;
                a9.a aVar = a9.i0.F;
                aVar.getClass();
                kotlin.reflect.jvm.internal.impl.protobuf.i iVar = new kotlin.reflect.jvm.internal.impl.protobuf.i(byteArrayInputStream);
                kotlin.reflect.jvm.internal.impl.protobuf.c0 c0Var = (kotlin.reflect.jvm.internal.impl.protobuf.c0) aVar.b(iVar, lVar3);
                try {
                    iVar.a(0);
                    kotlin.reflect.jvm.internal.impl.protobuf.d.c(c0Var);
                    a9.i0 i0Var = (a9.i0) c0Var;
                    m0Var = new m0(f.f189l, (g8.u0) e2.f(kVar.getClass(), i0Var, gVarG, new androidx.work.impl.constraints.j(i0Var.z), new e9.f((metadata.xi() & 8) != 0, metadata.mv()), z7.e.f23243i));
                } catch (InvalidProtocolBufferException e5) {
                    e5.f19759i = c0Var;
                    throw e5;
                }
            }
        }
        if (m0Var == null || (m0VarB = e2.b(m0Var)) == null) {
            return super.i(kVar);
        }
        h9.h hVar = b2.f169a;
        g8.w wVarC = m0VarB.x();
        StringBuilder sb2 = new StringBuilder();
        b2.a(wVarC, sb2);
        kotlin.collections.x.t0(wVarC.e(), sb2, ", ", "(", ")", b.f159v, 48);
        sb2.append(" -> ");
        sb2.append(b2.f169a.V(wVarC.getReturnType()));
        return sb2.toString();
    }

    @Override // kotlin.jvm.internal.m0
    public final String j(kotlin.jvm.internal.r rVar) {
        return i(rVar);
    }

    @Override // kotlin.jvm.internal.m0
    public final kotlin.reflect.q k(kotlin.reflect.d dVar, List list) {
        if (!(dVar instanceof kotlin.jvm.internal.f)) {
            return xc.d.Y(dVar, list, false, Collections.EMPTY_LIST);
        }
        Class clsC = ((kotlin.jvm.internal.f) dVar).c();
        d dVar2 = c.f170a;
        if (list.isEmpty()) {
            return (kotlin.reflect.q) c.f172c.c(clsC);
        }
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) c.f173d.c(clsC);
        x6.x xVar = new x6.x(list, Boolean.FALSE);
        Object obj = concurrentHashMap.get(xVar);
        if (obj == null) {
            u1 u1VarY = xc.d.Y((e0) c.f170a.c(clsC), list, false, kotlin.collections.z.f19728i);
            Object objPutIfAbsent = concurrentHashMap.putIfAbsent(xVar, u1VarY);
            obj = objPutIfAbsent == null ? u1VarY : objPutIfAbsent;
        }
        return (kotlin.reflect.q) obj;
    }
}
