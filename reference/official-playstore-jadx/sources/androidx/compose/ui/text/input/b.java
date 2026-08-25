package androidx.compose.ui.text.input;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.appcompat.app.v;
import java.util.Random;
import v1.h;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1942i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1943l;

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j10) {
        switch (this.f1942i) {
            case 0:
                ((Runnable) this.f1943l).run();
                break;
            default:
                (Build.VERSION.SDK_INT >= 28 ? h.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new v((Context) this.f1943l, 1), new Random().nextInt(Math.max(1000, 1)) + 5000);
                break;
        }
    }

    public /* synthetic */ b(Runnable runnable) {
        this.f1943l = runnable;
    }
}
