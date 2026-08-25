package ua;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends r implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22093i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d f22094l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(d dVar, c cVar, int i10) {
        super(1);
        this.f22093i = i10;
        this.f22094l = dVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f22093i) {
            case 0:
                this.f22094l.b(null);
                break;
            default:
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d.f22097h;
                d dVar = this.f22094l;
                atomicReferenceFieldUpdater.set(dVar, null);
                dVar.b(null);
                break;
        }
        return t0.f22605a;
    }
}
