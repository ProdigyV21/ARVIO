package androidx.emoji2.text;

import android.os.Trace;

/* JADX INFO: loaded from: classes3.dex */
public final class s implements Runnable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ s f2643l = new s(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2644i;

    public /* synthetic */ s(int i10) {
        this.f2644i = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2644i) {
            case 0:
                try {
                    int i10 = androidx.core.os.r.f2132a;
                    Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                    if (o.c()) {
                        o.a().d();
                        break;
                    }
                    Trace.endSection();
                    return;
                } catch (Throwable th) {
                    int i11 = androidx.core.os.r.f2132a;
                    Trace.endSection();
                    throw th;
                }
            case 1:
            default:
                return;
        }
    }

    private final /* synthetic */ void a() {
    }

    private final void b() {
    }
}
