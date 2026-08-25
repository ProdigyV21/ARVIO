package z0;

import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends f7.j implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f23077i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ c f23078l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(c cVar, d7.d dVar) {
        super(1, dVar);
        this.f23078l = cVar;
    }

    @Override // f7.a
    public final d7.d create(d7.d dVar) {
        return new e(this.f23078l, dVar);
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return ((e) create((d7.d) obj)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.f23077i;
        if (i10 == 0) {
            k2.c.G(obj);
            this.f23077i = 1;
            Object objE = this.f23078l.e();
            e7.a aVar = e7.a.f15033i;
            if (objE == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }
}
