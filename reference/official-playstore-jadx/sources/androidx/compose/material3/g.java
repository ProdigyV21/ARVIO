package androidx.compose.material3;

import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class g {
    public static /* synthetic */ Modifier a(TabIndicatorScope tabIndicatorScope, Modifier modifier, int i10, boolean z, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tabIndicatorOffset");
        }
        if ((i11 & 2) != 0) {
            z = false;
        }
        return tabIndicatorScope.tabIndicatorOffset(modifier, i10, z);
    }
}
