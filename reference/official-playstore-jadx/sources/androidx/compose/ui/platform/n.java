package androidx.compose.ui.platform;

import androidx.compose.ui.geometry.Rect;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class n {
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(TextToolbar textToolbar, Rect rect, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showMenu");
        }
        if ((i10 & 2) != 0) {
            aVar = null;
        }
        if ((i10 & 4) != 0) {
            aVar2 = null;
        }
        if ((i10 & 8) != 0) {
            aVar3 = null;
        }
        if ((i10 & 16) != 0) {
            aVar4 = null;
        }
        textToolbar.showMenu(rect, aVar, aVar2, aVar3, aVar4);
    }
}
