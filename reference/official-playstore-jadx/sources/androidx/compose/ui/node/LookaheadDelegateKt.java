package androidx.compose.ui.node;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a \u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0080\b¢\u0006\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0006\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"", "width", "height", "Lx6/t0;", "checkMeasuredSize", "(II)V", "MaxLayoutDimension", "I", "MaxLayoutMask", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LookaheadDelegateKt {
    private static final int MaxLayoutDimension = 16777215;
    private static final int MaxLayoutMask = -16777216;

    public static final void checkMeasuredSize(int i10, int i11) {
        if ((i10 & MaxLayoutMask) != 0 || (MaxLayoutMask & i11) != 0) {
            throw new IllegalStateException(androidx.compose.foundation.c.s("Size(", i10, " x ", i11, ") is out of range. Each dimension must be between 0 and 16777215.").toString());
        }
    }
}
