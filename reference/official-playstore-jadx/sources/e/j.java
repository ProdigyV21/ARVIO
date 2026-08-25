package e;

import java.lang.reflect.InvocationTargetException;
import ka.k0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class j extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ i f14955i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f14956l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(i iVar, boolean z, d7.d dVar) {
        super(2, dVar);
        this.f14955i = iVar;
        this.f14956l = z;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        return new j(this.f14955i, this.f14956l, dVar);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        j jVar = (j) create((k0) obj, (d7.d) obj2);
        t0 t0Var = t0.f22605a;
        jVar.invokeSuspend(t0Var);
        return t0Var;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.internal.m, r7.a] */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws IllegalAccessException, InvocationTargetException {
        androidx.loader.app.d dVar;
        k2.c.G(obj);
        i iVar = this.f14955i;
        boolean z = this.f14956l;
        if (!z && !iVar.f14954g && iVar.f931a && (dVar = iVar.f14953f) != null) {
            dVar.d();
        }
        iVar.f931a = z;
        ?? r32 = iVar.f933c;
        if (r32 != 0) {
            r32.invoke();
        }
        return t0.f22605a;
    }
}
