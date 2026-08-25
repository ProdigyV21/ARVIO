package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {
    public static boolean a(ScrollableState scrollableState) {
        return true;
    }

    public static boolean b(ScrollableState scrollableState) {
        return true;
    }

    public static /* synthetic */ Object e(ScrollableState scrollableState, MutatePriority mutatePriority, p pVar, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scroll");
        }
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return scrollableState.scroll(mutatePriority, pVar, dVar);
    }
}
