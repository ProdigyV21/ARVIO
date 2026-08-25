package androidx.tv.foundation.lazy.grid;

import android.util.Log;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.ui.layout.Remeasurement;
import androidx.work.CoroutineWorker;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class a1 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5144i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5145l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f5146m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a1(int i10, androidx.tv.material3.l0 l0Var, d7.d dVar) {
        super(2, dVar);
        this.f5144i = 1;
        this.f5145l = i10;
        this.f5146m = l0Var;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f5144i) {
            case 0:
                return new a1((b1) this.f5146m, this.f5145l, dVar);
            case 1:
                return new a1(this.f5145l, (androidx.tv.material3.l0) this.f5146m, dVar);
            case 2:
                return new a1((CoroutineWorker) this.f5146m, dVar, 2);
            case 3:
                return new a1((w5.x) this.f5146m, dVar, 3);
            default:
                return new a1((String) this.f5146m, dVar, 4);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5144i) {
            case 0:
                a1 a1Var = (a1) create((ScrollScope) obj, (d7.d) obj2);
                x6.t0 t0Var = x6.t0.f22605a;
                a1Var.invokeSuspend(t0Var);
                break;
            case 1:
                a1 a1Var2 = (a1) create((ka.k0) obj, (d7.d) obj2);
                x6.t0 t0Var2 = x6.t0.f22605a;
                a1Var2.invokeSuspend(t0Var2);
                break;
        }
        return ((a1) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f5144i) {
            case 0:
                k2.c.G(obj);
                b1 b1Var = (b1) this.f5146m;
                int i10 = this.f5145l;
                f0 f0Var = b1Var.f5153a;
                f0Var.b(i10, 0);
                f0Var.f5235e = null;
                o oVar = b1Var.f5169r;
                oVar.f5276a.clear();
                oVar.f5277b = i2.j.f16053a;
                Remeasurement remeasurement = b1Var.f5165n;
                if (remeasurement != null) {
                    remeasurement.forceRemeasure();
                }
                return x6.t0.f22605a;
            case 1:
                k2.c.G(obj);
                int i11 = this.f5145l;
                if (i11 != 0) {
                    androidx.tv.material3.l0 l0Var = (androidx.tv.material3.l0) this.f5146m;
                    int intValue = l0Var.f6185b.getIntValue();
                    int i12 = intValue % i11;
                    if (i12 == 0) {
                        i12 = 0;
                    } else if ((((intValue ^ i11) >> 31) | 1) <= 0) {
                        i12 += i11;
                    }
                    l0Var.f6185b.setIntValue(i12);
                }
                return x6.t0.f22605a;
            case 2:
                CoroutineWorker coroutineWorker = (CoroutineWorker) this.f5146m;
                int i13 = this.f5145l;
                try {
                    if (i13 == 0) {
                        k2.c.G(obj);
                        this.f5145l = 1;
                        obj = coroutineWorker.doWork(this);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i13 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    coroutineWorker.getFuture().h((m2.r) obj);
                    break;
                } catch (Throwable th) {
                    coroutineWorker.getFuture().i(th);
                }
                return x6.t0.f22605a;
            case 3:
                int i14 = this.f5145l;
                if (i14 == 0) {
                    k2.c.G(obj);
                    w5.x xVar = (w5.x) this.f5146m;
                    na.s sVar = xVar.f22497d;
                    na.d0 d0Var = new na.d0(xVar, 1);
                    this.f5145l = 1;
                    Object objCollect = sVar.collect(d0Var, this);
                    e7.a aVar2 = e7.a.f15033i;
                    if (objCollect == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i14 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return x6.t0.f22605a;
            default:
                int i15 = this.f5145l;
                if (i15 == 0) {
                    k2.c.G(obj);
                    x5.c cVar = x5.c.f22570a;
                    this.f5145l = 1;
                    obj = cVar.b(this);
                    e7.a aVar3 = e7.a.f15033i;
                    if (obj == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i15 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                Collection<x5.f> collectionValues = ((Map) obj).values();
                String str = (String) this.f5146m;
                for (x5.f fVar : collectionValues) {
                    fVar.onSessionChanged(new x5.e(str));
                    Log.d("SessionLifecycleClient", "Notified " + fVar.getSessionSubscriberName() + " of new session " + str);
                }
                return x6.t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a1(b1 b1Var, int i10, d7.d dVar) {
        super(2, dVar);
        this.f5144i = 0;
        this.f5146m = b1Var;
        this.f5145l = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a1(Object obj, d7.d dVar, int i10) {
        super(2, dVar);
        this.f5144i = i10;
        this.f5146m = obj;
    }
}
