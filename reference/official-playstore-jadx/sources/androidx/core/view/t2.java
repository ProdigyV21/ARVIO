package androidx.core.view;

import android.view.WindowInsets;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class t2 {
    public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
    public static final int DISPATCH_MODE_STOP = 0;
    WindowInsets mDispachedInsets;
    private final int mDispatchMode;

    public t2(int i10) {
        this.mDispatchMode = i10;
    }

    public final int getDispatchMode() {
        return this.mDispatchMode;
    }

    public abstract void onEnd(b3 b3Var);

    public abstract void onPrepare(b3 b3Var);

    public abstract q3 onProgress(q3 q3Var, List list);

    public abstract r2 onStart(b3 b3Var, r2 r2Var);
}
