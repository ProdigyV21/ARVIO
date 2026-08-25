package cb;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g1 extends t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f1 f7505b;

    public g1(ya.h hVar) {
        super(hVar);
        this.f7505b = new f1(hVar.getDescriptor());
    }

    @Override // cb.a
    public final Object a() {
        return (e1) g(j());
    }

    @Override // cb.a
    public final int b(Object obj) {
        return ((e1) obj).d();
    }

    @Override // cb.a
    public final Iterator c(Object obj) {
        throw new IllegalStateException("This method lead to boxing and must not be used, use writeContents instead");
    }

    @Override // cb.a, ya.c
    public final Object deserialize(bb.d dVar) {
        return e(dVar);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return this.f7505b;
    }

    @Override // cb.a
    public final Object h(Object obj) {
        return ((e1) obj).a();
    }

    @Override // cb.t
    public final void i(int i10, Object obj, Object obj2) {
        throw new IllegalStateException("This method lead to boxing and must not be used, use Builder.append instead");
    }

    public abstract Object j();

    public abstract void k(bb.c cVar, Object obj, int i10);

    @Override // cb.t, ya.s
    public final void serialize(bb.e eVar, Object obj) {
        int iD = d(obj);
        bb.c cVarV = eVar.v(this.f7505b);
        k(cVarV, obj, iD);
        cVarV.e();
    }
}
