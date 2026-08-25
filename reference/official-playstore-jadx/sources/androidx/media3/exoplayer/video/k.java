package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.VideoRendererEventListener;
import io.sentry.android.core.AppComponentsBreadcrumbsIntegration;
import io.sentry.w5;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3864i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3865l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f3866m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3867n;

    public /* synthetic */ k(int i10, long j10, VideoRendererEventListener.EventDispatcher eventDispatcher) {
        this.f3864i = 0;
        this.f3865l = eventDispatcher;
        this.f3867n = i10;
        this.f3866m = j10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3864i) {
            case 0:
                ((VideoRendererEventListener.EventDispatcher) this.f3865l).lambda$droppedFrames$3(this.f3867n, this.f3866m);
                break;
            case 1:
                ((VideoRendererEventListener.EventDispatcher) this.f3865l).lambda$reportVideoFrameProcessingOffset$4(this.f3866m, this.f3867n);
                break;
            default:
                AppComponentsBreadcrumbsIntegration appComponentsBreadcrumbsIntegration = (AppComponentsBreadcrumbsIntegration) this.f3865l;
                if (appComponentsBreadcrumbsIntegration.f16329l != null) {
                    io.sentry.f fVar = new io.sentry.f(this.f3866m);
                    fVar.f17247o = "system";
                    fVar.f17249q = "device.event";
                    fVar.f17246n = "Low memory";
                    fVar.f("LOW_MEMORY", "action");
                    fVar.f(Integer.valueOf(this.f3867n), "level");
                    fVar.f17251s = w5.WARNING;
                    appComponentsBreadcrumbsIntegration.f16329l.h(fVar, AppComponentsBreadcrumbsIntegration.f16327o);
                }
                break;
        }
    }

    public /* synthetic */ k(Object obj, int i10, int i11, long j10) {
        this.f3864i = i11;
        this.f3865l = obj;
        this.f3866m = j10;
        this.f3867n = i10;
    }
}
