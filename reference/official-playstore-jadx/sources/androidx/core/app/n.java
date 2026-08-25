package androidx.core.app;

import android.view.FrameMetrics;
import android.view.Window;
import android.view.Window$OnFrameMetricsAvailableListener;

/* JADX INFO: loaded from: classes3.dex */
public final class n implements Window$OnFrameMetricsAvailableListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f2024a;

    public n(o oVar) {
        this.f2024a = oVar;
    }

    public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i10) {
        o oVar = this.f2024a;
        if ((oVar.f2031l & 1) != 0) {
            o.u(oVar.f2032m[0], frameMetrics.getMetric(8));
        }
        o oVar2 = this.f2024a;
        if ((oVar2.f2031l & 2) != 0) {
            o.u(oVar2.f2032m[1], frameMetrics.getMetric(1));
        }
        o oVar3 = this.f2024a;
        if ((oVar3.f2031l & 4) != 0) {
            o.u(oVar3.f2032m[2], frameMetrics.getMetric(3));
        }
        o oVar4 = this.f2024a;
        if ((oVar4.f2031l & 8) != 0) {
            o.u(oVar4.f2032m[3], frameMetrics.getMetric(4));
        }
        o oVar5 = this.f2024a;
        if ((oVar5.f2031l & 16) != 0) {
            o.u(oVar5.f2032m[4], frameMetrics.getMetric(5));
        }
        o oVar6 = this.f2024a;
        if ((oVar6.f2031l & 64) != 0) {
            o.u(oVar6.f2032m[6], frameMetrics.getMetric(7));
        }
        o oVar7 = this.f2024a;
        if ((oVar7.f2031l & 32) != 0) {
            o.u(oVar7.f2032m[5], frameMetrics.getMetric(6));
        }
        o oVar8 = this.f2024a;
        if ((oVar8.f2031l & 128) != 0) {
            o.u(oVar8.f2032m[7], frameMetrics.getMetric(0));
        }
        o oVar9 = this.f2024a;
        if ((oVar9.f2031l & 256) != 0) {
            o.u(oVar9.f2032m[8], frameMetrics.getMetric(2));
        }
    }
}
