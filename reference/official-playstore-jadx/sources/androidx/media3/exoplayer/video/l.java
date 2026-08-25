package androidx.media3.exoplayer.video;

import android.content.res.Configuration;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import io.sentry.android.core.AppComponentsBreadcrumbsIntegration;
import io.sentry.j0;
import io.sentry.w5;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.collections.x;
import kotlin.jvm.internal.f0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3868i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f3869l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3870m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3871n;

    public /* synthetic */ l(AppComponentsBreadcrumbsIntegration appComponentsBreadcrumbsIntegration, long j10, Configuration configuration) {
        this.f3868i = 1;
        this.f3870m = appComponentsBreadcrumbsIntegration;
        this.f3869l = j10;
        this.f3871n = configuration;
    }

    @Override // java.lang.Runnable
    public final void run() throws IOException {
        switch (this.f3868i) {
            case 0:
                ((VideoRendererEventListener.EventDispatcher) this.f3870m).lambda$renderedFirstFrame$6(this.f3871n, this.f3869l);
                return;
            case 1:
                AppComponentsBreadcrumbsIntegration appComponentsBreadcrumbsIntegration = (AppComponentsBreadcrumbsIntegration) this.f3870m;
                Configuration configuration = (Configuration) this.f3871n;
                if (appComponentsBreadcrumbsIntegration.f16329l != null) {
                    int i10 = appComponentsBreadcrumbsIntegration.f16328i.getResources().getConfiguration().orientation;
                    io.sentry.protocol.e eVar = i10 != 1 ? i10 != 2 ? null : io.sentry.protocol.e.LANDSCAPE : io.sentry.protocol.e.PORTRAIT;
                    String lowerCase = eVar != null ? eVar.name().toLowerCase(Locale.ROOT) : "undefined";
                    io.sentry.f fVar = new io.sentry.f(this.f3869l);
                    fVar.f17247o = "navigation";
                    fVar.f17249q = "device.orientation";
                    fVar.f(lowerCase, "position");
                    fVar.f17251s = w5.INFO;
                    j0 j0Var = new j0();
                    j0Var.d("android:configuration", configuration);
                    appComponentsBreadcrumbsIntegration.f16329l.h(fVar, j0Var);
                    return;
                }
                return;
            default:
                io.sentry.android.replay.capture.f fVar2 = (io.sentry.android.replay.capture.f) this.f3870m;
                i9.b bVar = (i9.b) this.f3871n;
                io.sentry.android.replay.m mVar = fVar2.f16896h;
                if (mVar != null) {
                    bVar.invoke(mVar, Long.valueOf(this.f3869l));
                }
                long currentTimeMillis = fVar2.f16915t.getCurrentTimeMillis() - fVar2.f16913r.getSessionReplay().f17221r;
                io.sentry.android.replay.m mVar2 = fVar2.f16896h;
                fVar2.f16899l.setValue(fVar2, io.sentry.android.replay.capture.c.f16888q[2], mVar2 != null ? mVar2.o(currentTimeMillis) : null);
                ArrayList arrayList = fVar2.f16917v;
                f0 f0Var = new f0();
                x.M0(arrayList, new io.sentry.android.replay.l(currentTimeMillis, fVar2, f0Var, 1));
                if (f0Var.f19738i) {
                    int i11 = 0;
                    for (Object obj : arrayList) {
                        int i12 = i11 + 1;
                        if (i11 < 0) {
                            t7.a.Q();
                            throw null;
                        }
                        io.sentry.android.replay.capture.i iVar = (io.sentry.android.replay.capture.i) obj;
                        iVar.f16918a.D = i11;
                        List<io.sentry.rrweb.b> list = iVar.f16919b.f18064l;
                        if (list != null) {
                            for (io.sentry.rrweb.b bVar2 : list) {
                                if (bVar2 instanceof io.sentry.rrweb.m) {
                                    ((io.sentry.rrweb.m) bVar2).f17836n = i11;
                                }
                            }
                        }
                        i11 = i12;
                    }
                    return;
                }
                return;
        }
    }

    public /* synthetic */ l(Object obj, Object obj2, long j10, int i10) {
        this.f3868i = i10;
        this.f3870m = obj;
        this.f3871n = obj2;
        this.f3869l = j10;
    }
}
