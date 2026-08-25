package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.media3.session.MediaController;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class n {
    public static com.google.common.util.concurrent.d1 b(MediaController.Listener listener, MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
        return xc.d.s0(new SessionResult(-6));
    }

    public static com.google.common.util.concurrent.d1 i(MediaController.Listener listener, MediaController mediaController, List list) {
        return xc.d.s0(new SessionResult(-6));
    }

    public static void d(MediaController.Listener listener, MediaController mediaController) {
    }

    public static void a(MediaController.Listener listener, MediaController mediaController, SessionCommands sessionCommands) {
    }

    public static void c(MediaController.Listener listener, MediaController mediaController, List list) {
    }

    public static void e(MediaController.Listener listener, MediaController mediaController, SessionError sessionError) {
    }

    public static void f(MediaController.Listener listener, MediaController mediaController, Bundle bundle) {
    }

    public static void g(MediaController.Listener listener, MediaController mediaController, List list) {
    }

    public static void h(MediaController.Listener listener, MediaController mediaController, PendingIntent pendingIntent) {
    }
}
