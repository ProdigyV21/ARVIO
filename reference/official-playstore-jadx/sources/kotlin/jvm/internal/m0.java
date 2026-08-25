package kotlin.jvm.internal;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class m0 {
    public kotlin.reflect.g a(l lVar) {
        return lVar;
    }

    public kotlin.reflect.d b(Class cls) {
        return new g(cls);
    }

    public kotlin.reflect.f c(Class cls, String str) {
        return new x(cls);
    }

    public kotlin.reflect.i d(s sVar) {
        return sVar;
    }

    public kotlin.reflect.j e(u uVar) {
        return uVar;
    }

    public kotlin.reflect.n f(y yVar) {
        return yVar;
    }

    public kotlin.reflect.o g(a0 a0Var) {
        return a0Var;
    }

    public kotlin.reflect.p h(c0 c0Var) {
        return c0Var;
    }

    public String i(k kVar) {
        String string = kVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public String j(r rVar) {
        return i(rVar);
    }

    public kotlin.reflect.q k(kotlin.reflect.d dVar, List list) {
        return new r0(dVar, list);
    }
}
