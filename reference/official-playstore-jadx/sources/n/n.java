package n;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    public static void a(Window.Callback callback, boolean z) {
        callback.onPointerCaptureChanged(z);
    }
}
