package e7;

import d7.d;
import d7.j;
import kotlin.jvm.internal.q0;
import r7.p;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15040i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ p f15041l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d f15042m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(d dVar, j jVar, p pVar, d dVar2) {
        super(jVar, dVar);
        this.f15041l = pVar;
        this.f15042m = dVar2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.f15040i;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.f15040i = 2;
            k2.c.G(obj);
            return obj;
        }
        this.f15040i = 1;
        k2.c.G(obj);
        p pVar = this.f15041l;
        q0.e(2, pVar);
        return pVar.invoke(this.f15042m, this);
    }
}
