package io.sentry.android.replay.capture;

import io.sentry.android.replay.y;
import io.sentry.protocol.v;
import java.util.Date;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16904i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f16905l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Date f16906m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f16907n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ y f16908o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r f16909p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ c f16910q;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ d(f fVar, long j10, Date date, v vVar, y yVar, r7.l lVar) {
        this.f16910q = fVar;
        this.f16905l = j10;
        this.f16906m = date;
        this.f16907n = vVar;
        this.f16908o = yVar;
        this.f16909p = (r) lVar;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.internal.r, r7.l] */
    /* JADX WARN: Type inference failed for: r1v5, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16904i) {
            case 0:
                f fVar = (f) this.f16910q;
                int iH = fVar.h();
                y yVar = this.f16908o;
                this.f16909p.invoke(c.l(fVar, this.f16905l, this.f16906m, this.f16907n, iH, yVar.f17090b, yVar.f17089a, yVar.f17093e, yVar.f17094f));
                break;
            default:
                o oVar = (o) this.f16910q;
                int iH2 = oVar.h();
                y yVar2 = this.f16908o;
                this.f16909p.invoke(c.l(oVar, this.f16905l, this.f16906m, this.f16907n, iH2, yVar2.f17090b, yVar2.f17089a, yVar2.f17093e, yVar2.f17094f));
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ d(o oVar, long j10, Date date, v vVar, y yVar, r7.l lVar) {
        this.f16910q = oVar;
        this.f16905l = j10;
        this.f16906m = date;
        this.f16907n = vVar;
        this.f16908o = yVar;
        this.f16909p = (r) lVar;
    }
}
