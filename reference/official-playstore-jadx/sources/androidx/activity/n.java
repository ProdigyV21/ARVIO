package androidx.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Trace;
import java.nio.MappedByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f972i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f973l;

    public /* synthetic */ n(io.sentry.android.core.b bVar, com.google.firebase.installations.d dVar) {
        this.f972i = 13;
        this.f973l = bVar;
    }

    private final void a() {
        androidx.emoji2.text.a0 a0Var = (androidx.emoji2.text.a0) this.f973l;
        synchronized (a0Var.f2589d) {
            try {
                if (a0Var.f2593h == null) {
                    return;
                }
                try {
                    androidx.core.provider.k kVarD = a0Var.d();
                    int i10 = kVarD.f2165e;
                    if (i10 == 2) {
                        synchronized (a0Var.f2589d) {
                        }
                    }
                    if (i10 != 0) {
                        throw new RuntimeException("fetchFonts result is not OK. (" + i10 + ")");
                    }
                    try {
                        int i11 = androidx.core.os.r.f2132a;
                        Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                        a1.a aVar = a0Var.f2588c;
                        Context context = a0Var.f2586a;
                        aVar.getClass();
                        Typeface typefaceG = androidx.core.graphics.g.f2103a.g(context, new androidx.core.provider.k[]{kVarD}, 0);
                        MappedByteBuffer mappedByteBufferN = androidx.core.graphics.o.n(a0Var.f2586a, kVarD.f2161a);
                        if (mappedByteBufferN == null || typefaceG == null) {
                            throw new RuntimeException("Unable to open file.");
                        }
                        try {
                            Trace.beginSection("EmojiCompat.MetadataRepo.create");
                            androidx.emoji2.text.e0 e0Var = new androidx.emoji2.text.e0(typefaceG, qb.l.w(mappedByteBufferN));
                            Trace.endSection();
                            Trace.endSection();
                            synchronized (a0Var.f2589d) {
                                try {
                                    m2.f0 f0Var = a0Var.f2593h;
                                    if (f0Var != null) {
                                        f0Var.E(e0Var);
                                    }
                                } finally {
                                }
                            }
                            a0Var.b();
                        } finally {
                            int i12 = androidx.core.os.r.f2132a;
                            Trace.endSection();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                } catch (Throwable th2) {
                    synchronized (a0Var.f2589d) {
                        try {
                            m2.f0 f0Var2 = a0Var.f2593h;
                            if (f0Var2 != null) {
                                f0Var2.D(th2);
                            }
                            a0Var.b();
                        } finally {
                        }
                    }
                }
            } finally {
            }
        }
    }

    private final void b() {
        io.sentry.android.replay.screenshot.e eVar = (io.sentry.android.replay.screenshot.e) this.f973l;
        if (!eVar.f17018f.isRecycled()) {
            synchronized (eVar.f17018f) {
                if (!eVar.f17018f.isRecycled()) {
                    eVar.f17018f.recycle();
                }
            }
        }
        eVar.f17021i.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x032d  */
    /* JADX WARN: Type inference failed for: r0v73, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r21v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r21v1, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r22v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r23v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r25v1, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r27v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r27v4 */
    /* JADX WARN: Type inference failed for: r29v1, types: [io.sentry.z6] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v35, types: [io.sentry.android.replay.g] */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r3v26, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r7v23, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r7v24, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v42 */
    /* JADX WARN: Type inference failed for: r7v43 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r8v36 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.n.run():void");
    }

    public /* synthetic */ n(io.sentry.android.core.h0 h0Var, io.sentry.android.core.g0 g0Var) {
        this.f972i = 17;
        this.f973l = g0Var;
    }

    public /* synthetic */ n(Object obj, int i10) {
        this.f972i = i10;
        this.f973l = obj;
    }
}
