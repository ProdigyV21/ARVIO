package io.sentry.android.replay.capture;

import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16911i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f16912l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(f fVar, int i10) {
        super(1);
        this.f16911i = i10;
        this.f16912l = fVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f16911i) {
            case 0:
                k kVar = (k) obj;
                if (kVar instanceof i) {
                    f fVar = this.f16912l;
                    fVar.f16917v.add(kVar);
                    fVar.f(fVar.h() + 1);
                }
                break;
            default:
                k kVar2 = (k) obj;
                if (kVar2 instanceof i) {
                    f fVar2 = this.f16912l;
                    fVar2.f16917v.add(kVar2);
                    fVar2.f(fVar2.h() + 1);
                }
                break;
        }
        return t0.f22605a;
    }
}
