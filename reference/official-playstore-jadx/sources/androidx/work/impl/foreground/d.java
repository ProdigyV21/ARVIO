package androidx.work.impl.foreground;

import android.app.Notification;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6874i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Notification f6875l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6876m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ SystemForegroundService f6877n;

    public d(SystemForegroundService systemForegroundService, int i10, Notification notification, int i11) {
        this.f6877n = systemForegroundService;
        this.f6874i = i10;
        this.f6875l = notification;
        this.f6876m = i11;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i10 = Build.VERSION.SDK_INT;
        int i11 = this.f6876m;
        Notification notification = this.f6875l;
        int i12 = this.f6874i;
        SystemForegroundService systemForegroundService = this.f6877n;
        if (i10 >= 31) {
            f.a(systemForegroundService, i12, notification, i11);
        } else if (i10 >= 29) {
            e.a(systemForegroundService, i12, notification, i11);
        } else {
            systemForegroundService.startForeground(i12, notification);
        }
    }
}
