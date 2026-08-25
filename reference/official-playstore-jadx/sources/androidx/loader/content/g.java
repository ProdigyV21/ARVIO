package androidx.loader.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import androidx.core.os.OperationCanceledException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3342b;

    public /* synthetic */ g(Object obj, int i10) {
        this.f3341a = i10;
        this.f3342b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f3341a) {
            case 0:
                a aVar = (a) this.f3342b;
                AtomicBoolean atomicBoolean = aVar.f3334n;
                aVar.f3335o.set(true);
                Object objOnLoadInBackground = null;
                try {
                    Process.setThreadPriority(10);
                    try {
                        objOnLoadInBackground = aVar.f3338r.onLoadInBackground();
                        break;
                    } catch (OperationCanceledException e5) {
                        if (!atomicBoolean.get()) {
                            throw e5;
                        }
                    }
                    Binder.flushPendingCommands();
                    return objOnLoadInBackground;
                } finally {
                }
            case 1:
                ((androidx.media3.exoplayer.a) this.f3342b).run();
                return null;
            default:
                return ((Context) this.f3342b).getSharedPreferences("google_sdk_flags", 0);
        }
    }
}
