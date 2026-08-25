package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.unit.IntSize;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5357a = new a();

    static {
        IntSize.INSTANCE.m5849getZeroYbymL2g();
        Orientation orientation = Orientation.Vertical;
    }

    @Override // androidx.tv.foundation.lazy.list.e0
    public final int getMainAxisItemSpacing() {
        return 0;
    }

    @Override // androidx.tv.foundation.lazy.list.e0
    public final int getTotalItemsCount() {
        return 0;
    }

    @Override // androidx.tv.foundation.lazy.list.e0
    public final List getVisibleItemsInfo() {
        return kotlin.collections.z.f19728i;
    }
}
