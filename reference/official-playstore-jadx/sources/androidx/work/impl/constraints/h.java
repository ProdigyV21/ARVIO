package androidx.work.impl.constraints;

import android.util.Log;
import java.io.Serializable;
import kotlin.jvm.internal.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends f7.j implements q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6806i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6807l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ na.k f6808m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Serializable f6809n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(int i10, int i11, d7.d dVar) {
        super(i10, dVar);
        this.f6806i = i11;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.Serializable, java.lang.Object[]] */
    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        na.k kVar = (na.k) obj;
        switch (this.f6806i) {
            case 0:
                h hVar = new h(3, 0, (d7.d) obj3);
                hVar.f6808m = kVar;
                hVar.f6809n = (Object[]) obj2;
                return hVar.invokeSuspend(t0.f22605a);
            default:
                h hVar2 = new h(3, 1, (d7.d) obj3);
                hVar2.f6808m = kVar;
                hVar2.f6809n = (Throwable) obj2;
                return hVar2.invokeSuspend(t0.f22605a);
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        d dVar;
        d dVar2;
        switch (this.f6806i) {
            case 0:
                int i10 = this.f6807l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    na.k kVar = this.f6808m;
                    d[] dVarArr = (d[]) ((Object[]) this.f6809n);
                    int length = dVarArr.length;
                    int i11 = 0;
                    while (true) {
                        dVar = b.f6789a;
                        if (i11 < length) {
                            dVar2 = dVarArr[i11];
                            if (p.a(dVar2, dVar)) {
                                i11++;
                            }
                        } else {
                            dVar2 = null;
                        }
                    }
                    if (dVar2 != null) {
                        dVar = dVar2;
                    }
                    this.f6807l = 1;
                    Object objEmit = kVar.emit(dVar, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objEmit == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            default:
                int i12 = this.f6807l;
                boolean z = true;
                if (i12 == 0) {
                    k2.c.G(obj);
                    na.k kVar2 = this.f6808m;
                    Log.e("FirebaseSessionsRepo", "Error reading stored session data.", (Throwable) this.f6809n);
                    c1.b bVar = new c1.b(z);
                    this.f6808m = null;
                    this.f6807l = 1;
                    Object objEmit2 = kVar2.emit(bVar, this);
                    e7.a aVar2 = e7.a.f15033i;
                    if (objEmit2 == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i12 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
        }
    }
}
