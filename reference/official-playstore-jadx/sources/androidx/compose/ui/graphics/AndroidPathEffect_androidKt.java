package androidx.compose.ui.graphics;

import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.PathDashPathEffect;
import androidx.compose.ui.graphics.StampedPathEffectStyle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001a2\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\n\u0010\u0013\u001a\u00020\u0014*\u00020\u0001\u001a\u0016\u0010\u0015\u001a\u00020\u0016*\u00020\u0010H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\n\u0010\u0019\u001a\u00020\u0001*\u00020\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"actualChainPathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "outer", "inner", "actualCornerPathEffect", "radius", "", "actualDashPathEffect", "intervals", "", "phase", "actualStampedPathEffect", "shape", "Landroidx/compose/ui/graphics/Path;", "advance", "style", "Landroidx/compose/ui/graphics/StampedPathEffectStyle;", "actualStampedPathEffect-7aD1DOk", "(Landroidx/compose/ui/graphics/Path;FFI)Landroidx/compose/ui/graphics/PathEffect;", "asAndroidPathEffect", "Landroid/graphics/PathEffect;", "toAndroidPathDashPathEffectStyle", "Landroid/graphics/PathDashPathEffect$Style;", "toAndroidPathDashPathEffectStyle-oQv6xUo", "(I)Landroid/graphics/PathDashPathEffect$Style;", "toComposePathEffect", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidPathEffect_androidKt {
    public static final PathEffect actualChainPathEffect(PathEffect pathEffect, PathEffect pathEffect2) {
        return new AndroidPathEffect(new ComposePathEffect(((AndroidPathEffect) pathEffect).getNativePathEffect(), ((AndroidPathEffect) pathEffect2).getNativePathEffect()));
    }

    public static final PathEffect actualCornerPathEffect(float f10) {
        return new AndroidPathEffect(new CornerPathEffect(f10));
    }

    public static final PathEffect actualDashPathEffect(float[] fArr, float f10) {
        return new AndroidPathEffect(new DashPathEffect(fArr, f10));
    }

    /* JADX INFO: renamed from: actualStampedPathEffect-7aD1DOk, reason: not valid java name */
    public static final PathEffect m3374actualStampedPathEffect7aD1DOk(Path path, float f10, float f11, int i10) {
        if (path instanceof AndroidPath) {
            return new AndroidPathEffect(new PathDashPathEffect(((AndroidPath) path).getInternalPath(), f10, f11, m3375toAndroidPathDashPathEffectStyleoQv6xUo(i10)));
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    public static final android.graphics.PathEffect asAndroidPathEffect(PathEffect pathEffect) {
        return ((AndroidPathEffect) pathEffect).getNativePathEffect();
    }

    /* JADX INFO: renamed from: toAndroidPathDashPathEffectStyle-oQv6xUo, reason: not valid java name */
    public static final PathDashPathEffect.Style m3375toAndroidPathDashPathEffectStyleoQv6xUo(int i10) {
        StampedPathEffectStyle.Companion companion = StampedPathEffectStyle.INSTANCE;
        return StampedPathEffectStyle.m3789equalsimpl0(i10, companion.m3793getMorphYpspkwk()) ? PathDashPathEffect.Style.MORPH : StampedPathEffectStyle.m3789equalsimpl0(i10, companion.m3794getRotateYpspkwk()) ? PathDashPathEffect.Style.ROTATE : StampedPathEffectStyle.m3789equalsimpl0(i10, companion.m3795getTranslateYpspkwk()) ? PathDashPathEffect.Style.TRANSLATE : PathDashPathEffect.Style.TRANSLATE;
    }

    public static final PathEffect toComposePathEffect(android.graphics.PathEffect pathEffect) {
        return new AndroidPathEffect(pathEffect);
    }
}
