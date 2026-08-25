package androidx.compose.foundation.draganddrop;

import android.graphics.Picture;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/draganddrop/CacheDrawScopeDragShadowCallback;", "", "<init>", "()V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawScope", "Lx6/t0;", "drawDragShadow", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "Landroidx/compose/ui/draw/CacheDrawScope;", "scope", "Landroidx/compose/ui/draw/DrawResult;", "cachePicture", "(Landroidx/compose/ui/draw/CacheDrawScope;)Landroidx/compose/ui/draw/DrawResult;", "Landroid/graphics/Picture;", "cachedPicture", "Landroid/graphics/Picture;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CacheDrawScopeDragShadowCallback {
    private Picture cachedPicture;

    public final DrawResult cachePicture(CacheDrawScope scope) {
        Picture picture = new Picture();
        this.cachedPicture = picture;
        return scope.onDrawWithContent(new CacheDrawScopeDragShadowCallback$cachePicture$1$1(picture, (int) Size.m3305getWidthimpl(scope.m3140getSizeNHjbRc()), (int) Size.m3302getHeightimpl(scope.m3140getSizeNHjbRc())));
    }

    public final void drawDragShadow(DrawScope drawScope) {
        Picture picture = this.cachedPicture;
        if (picture == null) {
            throw new IllegalArgumentException("No cached drag shadow. Check if Modifier.cacheDragShadow(painter) was called.");
        }
        AndroidCanvas_androidKt.getNativeCanvas(drawScope.getDrawContext().getCanvas()).drawPicture(picture);
    }
}
