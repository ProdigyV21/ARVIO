package androidx.work.impl;

import a8.g0;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.view.Surface;
import androidx.appcompat.app.i1;
import androidx.media3.common.MimeTypes;
import io.sentry.w5;
import io.sentry.y6;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f6987b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6988c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f6989d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f6990e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f6991f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f6992g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f6993h;

    public u(y6 y6Var, io.sentry.android.replay.video.a aVar) {
        this.f6986a = y6Var;
        this.f6987b = aVar;
        x6.s sVarO = f4.f.o(3, io.sentry.android.replay.video.d.f17067i);
        this.f6988c = sVarO;
        this.f6989d = ((Boolean) sVarO.getValue()).booleanValue() ? MediaCodec.createByCodecName("c2.android.avc.encoder") : MediaCodec.createEncoderByType(MimeTypes.VIDEO_H264);
        this.f6990e = f4.f.o(3, new g0(this, 28));
        this.f6991f = new MediaCodec.BufferInfo();
        this.f6992g = new io.sentry.android.replay.video.c(aVar.f17056a.getAbsolutePath(), aVar.f17059d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0188, code lost:
    
        throw new java.lang.RuntimeException(androidx.compose.foundation.c.o(r6, "encoderOutputBuffer ", " was null"));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r13) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.u.a(boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.graphics.Bitmap r5) {
        /*
            r4 = this;
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = "xiaomi"
            r2 = 1
            boolean r1 = kotlin.text.o.T(r0, r1, r2)
            r3 = 0
            if (r1 != 0) goto L3e
            java.lang.String r1 = "motorola"
            boolean r0 = kotlin.text.o.T(r0, r1, r2)
            if (r0 != 0) goto L3e
            io.sentry.android.replay.util.h r0 = io.sentry.android.replay.util.h.SOC_MANUFACTURER
            java.lang.String r1 = io.sentry.android.replay.util.j.a(r0)
            java.lang.String r2 = "spreadtrum"
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 != 0) goto L3e
            java.lang.String r0 = io.sentry.android.replay.util.j.a(r0)
            java.lang.String r1 = "unisoc"
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L31
            goto L3e
        L31:
            java.lang.Object r0 = r4.f6993h
            android.view.Surface r0 = (android.view.Surface) r0
            if (r0 == 0) goto L3c
            android.graphics.Canvas r0 = r0.lockHardwareCanvas()
            goto L48
        L3c:
            r0 = r3
            goto L48
        L3e:
            java.lang.Object r0 = r4.f6993h
            android.view.Surface r0 = (android.view.Surface) r0
            if (r0 == 0) goto L3c
            android.graphics.Canvas r0 = r0.lockCanvas(r3)
        L48:
            if (r0 == 0) goto L4e
            r1 = 0
            r0.drawBitmap(r5, r1, r1, r3)
        L4e:
            java.lang.Object r5 = r4.f6993h
            android.view.Surface r5 = (android.view.Surface) r5
            if (r5 == 0) goto L57
            r5.unlockCanvasAndPost(r0)
        L57:
            r5 = 0
            r4.a(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.u.b(android.graphics.Bitmap):void");
    }

    public void c() {
        MediaCodec mediaCodec = (MediaCodec) this.f6989d;
        try {
            a(true);
            mediaCodec.stop();
            mediaCodec.release();
            Surface surface = (Surface) this.f6993h;
            if (surface != null) {
                surface.release();
            }
            MediaMuxer mediaMuxer = ((io.sentry.android.replay.video.c) this.f6992g).f17062b;
            mediaMuxer.stop();
            mediaMuxer.release();
        } catch (Throwable th) {
            ((y6) this.f6986a).getLogger().l(w5.DEBUG, "Failed to properly release video encoder", th);
        }
    }

    public u(Context context, m2.c cVar, androidx.work.impl.utils.taskexecutor.d dVar, g gVar, WorkDatabase workDatabase, androidx.work.impl.model.p pVar, ArrayList arrayList) {
        this.f6993h = new i1(23);
        this.f6986a = context.getApplicationContext();
        this.f6988c = dVar;
        this.f6987b = gVar;
        this.f6989d = cVar;
        this.f6990e = workDatabase;
        this.f6991f = pVar;
        this.f6992g = arrayList;
    }
}
