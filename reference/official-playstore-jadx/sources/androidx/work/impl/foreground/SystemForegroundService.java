package androidx.work.impl.foreground;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.lifecycle.c0;
import androidx.work.impl.r;
import com.google.common.util.concurrent.w0;
import java.util.UUID;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public class SystemForegroundService extends c0 implements b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f6859p = t.f("SystemFgService");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Handler f6860l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6861m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public c f6862n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public NotificationManager f6863o;

    public final void a(int i10) {
        this.f6860l.post(new androidx.core.provider.a(this, i10, 2));
    }

    public final void b() {
        this.f6860l = new Handler(Looper.getMainLooper());
        this.f6863o = (NotificationManager) getApplicationContext().getSystemService("notification");
        c cVar = new c(getApplicationContext());
        this.f6862n = cVar;
        if (cVar.f6873s != null) {
            t.d().b(c.f6864t, "A callback already exists.");
        } else {
            cVar.f6873s = this;
        }
    }

    public final void c(int i10, int i11, Notification notification) {
        this.f6860l.post(new d(this, i10, notification, i11));
    }

    @Override // androidx.lifecycle.c0, android.app.Service
    public final void onCreate() {
        super.onCreate();
        b();
    }

    @Override // androidx.lifecycle.c0, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.f6862n.f();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i10, int i11) {
        super.onStartCommand(intent, i10, i11);
        boolean z = this.f6861m;
        String str = f6859p;
        if (z) {
            t.d().e(str, "Re-initializing SystemForegroundService after a request to shut-down.");
            this.f6862n.f();
            b();
            this.f6861m = false;
        }
        if (intent == null) {
            return 3;
        }
        c cVar = this.f6862n;
        cVar.getClass();
        String str2 = c.f6864t;
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            t.d().e(str2, "Started foreground service " + intent);
            cVar.f6866l.d(new w0(8, cVar, false, intent.getStringExtra("KEY_WORKSPEC_ID")));
            cVar.d(intent);
            return 3;
        }
        if ("ACTION_NOTIFY".equals(action)) {
            cVar.d(intent);
            return 3;
        }
        if (!"ACTION_CANCEL_WORK".equals(action)) {
            if (!"ACTION_STOP_FOREGROUND".equals(action)) {
                return 3;
            }
            t.d().e(str2, "Stopping foreground service");
            SystemForegroundService systemForegroundService = cVar.f6873s;
            if (systemForegroundService == null) {
                return 3;
            }
            systemForegroundService.f6861m = true;
            t.d().a(str, "All commands completed.");
            if (Build.VERSION.SDK_INT >= 26) {
                systemForegroundService.stopForeground(true);
            }
            systemForegroundService.stopSelf();
            return 3;
        }
        t.d().e(str2, "Stopping foreground work for " + intent);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra == null || TextUtils.isEmpty(stringExtra)) {
            return 3;
        }
        r rVar = cVar.f6865i;
        UUID uuidFromString = UUID.fromString(stringExtra);
        rVar.getClass();
        rVar.f6967d.d(new androidx.work.impl.utils.b(rVar, uuidFromString, 0));
        return 3;
    }
}
