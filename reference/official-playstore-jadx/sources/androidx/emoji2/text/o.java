package androidx.emoji2.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.inputmethod.EditorInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes3.dex */
public final class o {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f2626j = new Object();
    public static volatile o k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantReadWriteLock f2627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t.f f2628b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f2629c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f2630d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f2631e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f2632f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a1.a f2633g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2634h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f2635i;

    public o(b0 b0Var) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f2627a = reentrantReadWriteLock;
        this.f2629c = 3;
        l lVar = (l) b0Var.f2613b;
        this.f2632f = lVar;
        int i10 = b0Var.f2612a;
        this.f2634h = i10;
        this.f2635i = (d) b0Var.f2614c;
        this.f2630d = new Handler(Looper.getMainLooper());
        this.f2628b = new t.f(0);
        this.f2633g = new a1.a();
        g gVar = new g(this);
        this.f2631e = gVar;
        reentrantReadWriteLock.writeLock().lock();
        if (i10 == 0) {
            try {
                this.f2629c = 0;
            } catch (Throwable th) {
                this.f2627a.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (b() == 0) {
            try {
                lVar.a(new f(gVar));
            } catch (Throwable th2) {
                e(th2);
            }
        }
    }

    public static o a() {
        o oVar;
        synchronized (f2626j) {
            try {
                oVar = k;
                if (!(oVar != null)) {
                    throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
                }
            } finally {
            }
        }
        return oVar;
    }

    public static boolean c() {
        return k != null;
    }

    public final int b() {
        this.f2627a.readLock().lock();
        try {
            return this.f2629c;
        } finally {
            this.f2627a.readLock().unlock();
        }
    }

    public final void d() {
        if (!(this.f2634h == 1)) {
            throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        }
        if (b() == 1) {
            return;
        }
        this.f2627a.writeLock().lock();
        try {
            if (this.f2629c == 0) {
                return;
            }
            this.f2629c = 0;
            this.f2627a.writeLock().unlock();
            g gVar = this.f2631e;
            o oVar = gVar.f2607a;
            try {
                oVar.f2632f.a(new f(gVar));
            } catch (Throwable th) {
                oVar.e(th);
            }
        } finally {
            this.f2627a.writeLock().unlock();
        }
    }

    public final void e(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f2627a.writeLock().lock();
        try {
            this.f2629c = 2;
            arrayList.addAll(this.f2628b);
            this.f2628b.clear();
            this.f2627a.writeLock().unlock();
            this.f2630d.post(new android.support.v4.os.e(arrayList, this.f2629c, th));
        } catch (Throwable th2) {
            this.f2627a.writeLock().unlock();
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086 A[Catch: all -> 0x0079, TRY_ENTER, TryCatch #0 {all -> 0x0079, blocks: (B:35:0x0051, B:38:0x0056, B:40:0x005a, B:42:0x0067, B:49:0x0086, B:51:0x0090, B:53:0x0093, B:55:0x0096, B:57:0x00a6, B:58:0x00a9), top: B:98:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0096 A[Catch: all -> 0x0079, TryCatch #0 {all -> 0x0079, blocks: (B:35:0x0051, B:38:0x0056, B:40:0x005a, B:42:0x0067, B:49:0x0086, B:51:0x0090, B:53:0x0093, B:55:0x0096, B:57:0x00a6, B:58:0x00a9), top: B:98:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b8 A[Catch: all -> 0x00eb, TRY_ENTER, TryCatch #1 {all -> 0x00eb, blocks: (B:62:0x00b8, B:65:0x00c0, B:47:0x007c), top: B:100:0x007c }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.CharSequence f(java.lang.CharSequence r11, int r12, int r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.o.f(java.lang.CharSequence, int, int):java.lang.CharSequence");
    }

    public final void g(j jVar) {
        p0.g.b(jVar, "initCallback cannot be null");
        this.f2627a.writeLock().lock();
        try {
            if (this.f2629c == 1 || this.f2629c == 2) {
                this.f2630d.post(new android.support.v4.os.e(Arrays.asList(jVar), this.f2629c, null));
            } else {
                this.f2628b.add(jVar);
            }
            this.f2627a.writeLock().unlock();
        } catch (Throwable th) {
            this.f2627a.writeLock().unlock();
            throw th;
        }
    }

    public final void h(EditorInfo editorInfo) {
        if (b() != 1 || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        g gVar = this.f2631e;
        gVar.getClass();
        Bundle bundle = editorInfo.extras;
        androidx.emoji2.text.flatbuffer.b bVar = (androidx.emoji2.text.flatbuffer.b) gVar.f2609c.f2600l;
        int iA = bVar.a(4);
        bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iA != 0 ? ((ByteBuffer) bVar.f2241n).getInt(iA + bVar.f2238i) : 0);
        editorInfo.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
    }
}
