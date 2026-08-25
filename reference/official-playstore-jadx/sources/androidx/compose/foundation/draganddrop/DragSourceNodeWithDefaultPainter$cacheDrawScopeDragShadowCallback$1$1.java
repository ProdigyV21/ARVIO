package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class DragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1 extends m implements l<CacheDrawScope, DrawResult> {
    public DragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1(Object obj) {
        super(1, 0, CacheDrawScopeDragShadowCallback.class, obj, "cachePicture", "cachePicture(Landroidx/compose/ui/draw/CacheDrawScope;)Landroidx/compose/ui/draw/DrawResult;");
    }

    @Override // r7.l
    public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
        return ((CacheDrawScopeDragShadowCallback) this.receiver).cachePicture(cacheDrawScope);
    }
}
