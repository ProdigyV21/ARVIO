package androidx.media3.exoplayer.source.preload;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.View;
import android.view.Window;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.work.impl.WorkDatabase;
import io.sentry.ILogger;
import io.sentry.android.core.ScreenshotEventProcessor;
import io.sentry.android.core.r0;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3782i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3783l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3784m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3785n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3786o;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f3782i = i10;
        this.f3783l = obj;
        this.f3786o = obj2;
        this.f3784m = obj3;
        this.f3785n = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CountDownLatch countDownLatch;
        switch (this.f3782i) {
            case 0:
                ((BasePreloadManager) this.f3783l).lambda$onError$7((MediaItem) this.f3786o, (com.google.common.base.r) this.f3784m, (PreloadException) this.f3785n);
                return;
            case 1:
                ((BasePreloadManager) this.f3783l).lambda$onError$5((MediaSource) this.f3786o, (com.google.common.base.r) this.f3784m, (PreloadException) this.f3785n);
                return;
            case 2:
                List list = (List) this.f3783l;
                androidx.work.impl.model.j jVar = (androidx.work.impl.model.j) this.f3786o;
                m2.c cVar = (m2.c) this.f3784m;
                WorkDatabase workDatabase = (WorkDatabase) this.f3785n;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((androidx.work.impl.i) it.next()).a(jVar.f6914a);
                }
                androidx.work.impl.k.b(cVar, workDatabase, list);
                return;
            case 3:
                ScreenshotEventProcessor screenshotEventProcessor = (ScreenshotEventProcessor) this.f3783l;
                AtomicReference atomicReference = (AtomicReference) this.f3786o;
                Activity activity = (Activity) this.f3784m;
                countDownLatch = (CountDownLatch) this.f3785n;
                screenshotEventProcessor.getClass();
                try {
                    atomicReference.set(screenshotEventProcessor.a(activity));
                    return;
                } finally {
                }
            case 4:
                Window window = (Window) this.f3783l;
                Window.Callback callback = (Window.Callback) this.f3786o;
                Runnable runnable = (Runnable) this.f3784m;
                r0 r0Var = (r0) this.f3785n;
                View viewPeekDecorView = window.peekDecorView();
                if (viewPeekDecorView != null) {
                    window.setCallback(callback);
                    io.sentry.android.core.internal.util.i.b(viewPeekDecorView, runnable, r0Var);
                    return;
                }
                return;
            case 5:
                View view = (View) this.f3783l;
                Canvas canvas = (Canvas) this.f3786o;
                ILogger iLogger = (ILogger) this.f3784m;
                countDownLatch = (CountDownLatch) this.f3785n;
                try {
                    view.draw(canvas);
                    break;
                } finally {
                    try {
                    } finally {
                    }
                    break;
                }
                return;
            default:
                j3.a aVar = (j3.a) this.f3783l;
                com.google.android.datatransport.runtime.i iVar = (com.google.android.datatransport.runtime.i) this.f3786o;
                String str = iVar.f12589a;
                z2.g gVar = (z2.g) this.f3784m;
                com.google.android.datatransport.runtime.h hVar = (com.google.android.datatransport.runtime.h) this.f3785n;
                Logger logger = j3.a.f19199f;
                try {
                    c3.j jVar2 = aVar.f19202c.get(str);
                    if (jVar2 == null) {
                        String str2 = "Transport backend '" + str + "' is not registered";
                        logger.warning(str2);
                        gVar.a(new IllegalArgumentException(str2));
                    } else {
                        aVar.f19204e.j(new androidx.media3.exoplayer.hls.b(aVar, iVar, jVar2.b(hVar), 6));
                        gVar.a(null);
                    }
                    return;
                } catch (Exception e5) {
                    logger.warning("Error scheduling event " + e5.getMessage());
                    gVar.a(e5);
                    return;
                }
        }
    }
}
