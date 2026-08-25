package io.sentry.android.replay.capture;

import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16925i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ o f16926l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(o oVar, int i10) {
        super(1);
        this.f16925i = i10;
        this.f16926l = oVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f16925i) {
            case 0:
                k kVar = (k) obj;
                if (kVar instanceof i) {
                    i iVar = (i) kVar;
                    o oVar = this.f16926l;
                    i.a(iVar, oVar.f16928s);
                    oVar.f(oVar.h() + 1);
                    oVar.k(iVar.f16918a.E);
                }
                break;
            default:
                k kVar2 = (k) obj;
                if (kVar2 instanceof i) {
                    o oVar2 = this.f16926l;
                    i.a((i) kVar2, oVar2.f16928s);
                    oVar2.f(oVar2.h() + 1);
                }
                break;
        }
        return t0.f22605a;
    }
}
