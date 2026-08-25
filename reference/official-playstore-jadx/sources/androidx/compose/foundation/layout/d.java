package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d {
    public static /* synthetic */ Modifier a(ColumnScope columnScope, Modifier modifier, float f10, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: weight");
        }
        if ((i10 & 2) != 0) {
            z = true;
        }
        return columnScope.weight(modifier, f10, z);
    }
}
