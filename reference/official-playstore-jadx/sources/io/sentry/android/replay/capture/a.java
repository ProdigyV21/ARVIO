package io.sentry.android.replay.capture;

import io.sentry.android.replay.y;
import java.util.Date;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16880i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f16881l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16882m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ c f16883n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Object obj, Object obj2, c cVar, int i10) {
        super(0);
        this.f16880i = i10;
        this.f16881l = obj;
        this.f16882m = obj2;
        this.f16883n = cVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f16880i) {
            case 0:
                io.sentry.android.replay.m mVar = this.f16883n.f16896h;
                if (mVar != null) {
                    mVar.l("replay.id", String.valueOf(this.f16882m));
                }
                break;
            case 1:
                io.sentry.android.replay.m mVar2 = this.f16883n.f16896h;
                if (mVar2 != null) {
                    mVar2.l("segment.id", String.valueOf(this.f16882m));
                }
                break;
            case 2:
                io.sentry.android.replay.m mVar3 = this.f16883n.f16896h;
                if (mVar3 != null) {
                    mVar3.l("replay.type", String.valueOf(this.f16882m));
                }
                break;
            case 3:
                y yVar = (y) this.f16882m;
                if (yVar != null) {
                    c cVar = this.f16883n;
                    io.sentry.android.replay.m mVar4 = cVar.f16896h;
                    if (mVar4 != null) {
                        mVar4.l("config.height", String.valueOf(yVar.f17090b));
                    }
                    io.sentry.android.replay.m mVar5 = cVar.f16896h;
                    if (mVar5 != null) {
                        mVar5.l("config.width", String.valueOf(yVar.f17089a));
                    }
                    io.sentry.android.replay.m mVar6 = cVar.f16896h;
                    if (mVar6 != null) {
                        mVar6.l("config.frame-rate", String.valueOf(yVar.f17093e));
                    }
                    io.sentry.android.replay.m mVar7 = cVar.f16896h;
                    if (mVar7 != null) {
                        mVar7.l("config.bit-rate", String.valueOf(yVar.f17094f));
                    }
                }
                break;
            case 4:
                Date date = (Date) this.f16882m;
                io.sentry.android.replay.m mVar8 = this.f16883n.f16896h;
                if (mVar8 != null) {
                    mVar8.l("segment.timestamp", date == null ? null : k2.c.r(date));
                }
                break;
            default:
                io.sentry.android.replay.m mVar9 = this.f16883n.f16896h;
                if (mVar9 != null) {
                    mVar9.l("replay.screen-at-start", String.valueOf(this.f16882m));
                }
                break;
        }
        return t0.f22605a;
    }
}
