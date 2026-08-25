package androidx.compose.ui.text.input;

import android.view.Choreographer;
import androidx.media3.common.SimpleBasePlayer;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1944i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1945l;

    public /* synthetic */ c(Object obj, int i10) {
        this.f1944i = i10;
        this.f1945l = obj;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f1944i) {
            case 0:
                TextInputServiceAndroid_androidKt.asExecutor$lambda$2((Choreographer) this.f1945l, runnable);
                break;
            default:
                ((SimpleBasePlayer) this.f1945l).postOrRunOnApplicationHandler(runnable);
                break;
        }
    }
}
