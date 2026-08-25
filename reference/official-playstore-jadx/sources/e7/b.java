package e7;

import d7.d;
import f7.g;
import kotlin.jvm.internal.q0;
import r7.p;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15037i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ p f15038l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d f15039m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(d dVar, d dVar2, p pVar) {
        super(dVar);
        this.f15038l = pVar;
        this.f15039m = dVar2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.f15037i;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.f15037i = 2;
            k2.c.G(obj);
            return obj;
        }
        this.f15037i = 1;
        k2.c.G(obj);
        p pVar = this.f15038l;
        q0.e(2, pVar);
        return pVar.invoke(this.f15039m, this);
    }
}
