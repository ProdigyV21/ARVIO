package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u00020\t2\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/ui/graphics/WrapperVerificationHelperMethods;", "", "<init>", "()V", "Landroid/graphics/Paint;", "Landroidx/compose/ui/graphics/NativePaint;", "paint", "Landroidx/compose/ui/graphics/BlendMode;", "mode", "Lx6/t0;", "setBlendMode-GB0RdKg", "(Landroid/graphics/Paint;I)V", "setBlendMode", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WrapperVerificationHelperMethods {
    public static final WrapperVerificationHelperMethods INSTANCE = new WrapperVerificationHelperMethods();

    private WrapperVerificationHelperMethods() {
    }

    /* JADX INFO: renamed from: setBlendMode-GB0RdKg, reason: not valid java name */
    public final void m3853setBlendModeGB0RdKg(android.graphics.Paint paint, int mode) {
        paint.setBlendMode(AndroidBlendMode_androidKt.m3328toAndroidBlendModes9anfk8(mode));
    }
}
