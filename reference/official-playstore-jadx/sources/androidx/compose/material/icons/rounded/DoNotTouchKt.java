package androidx.compose.material.icons.rounded;

import a0.b;
import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doNotTouch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoNotTouch", "Landroidx/compose/material/icons/Icons$Rounded;", "getDoNotTouch", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoNotTouchKt {
    private static ImageVector _doNotTouch;

    public static final ImageVector getDoNotTouch(Icons.Rounded rounded) {
        ImageVector imageVector = _doNotTouch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DoNotTouch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(13.0f, 10.17f, -2.5f, -2.5f, 2.25f);
        pathBuilderA.curveTo(10.5f, 1.56f, 11.06f, 1.0f, 11.75f, 1.0f);
        pathBuilderA.curveTo(12.44f, 1.0f, 13.0f, 1.56f, 13.0f, 2.25f);
        f.r(pathBuilderA, 10.17f, 20.0f, 5.32f);
        pathBuilderA.curveToRelative(0.0f, -0.65f, -0.47f, -1.25f, -1.12f, -1.32f);
        pathBuilderA.curveToRelative(-0.75f, -0.08f, -1.38f, 0.51f, -1.38f, 1.24f);
        pathBuilderA.verticalLineToRelative(5.25f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.lineToRelative(0.0f, -7.18f);
        pathBuilderA.curveToRelative(0.0f, -0.65f, -0.47f, -1.25f, -1.12f, -1.32f);
        pathBuilderA.curveTo(14.63f, 1.93f, 14.0f, 2.52f, 14.0f, 3.25f);
        pathBuilderA.verticalLineToRelative(7.92f);
        d.C(pathBuilderA, 6.0f, 6.0f, 20.0f, 5.32f);
        pathBuilderA.moveTo(9.5f, 4.25f);
        pathBuilderA.curveTo(9.5f, 3.56f, 8.94f, 3.0f, 8.25f, 3.0f);
        pathBuilderA.curveToRelative(-0.67f, 0.0f, -1.2f, 0.53f, -1.24f, 1.18f);
        b.s(pathBuilderA, 9.5f, 6.67f, 4.25f);
        pathBuilderA.moveTo(17.0f, 22.0f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.18f, -0.19f, 1.65f, -0.52f);
        pathBuilderA.lineToRelative(-0.02f, -0.02f);
        pathBuilderA.lineToRelative(0.44f, 0.44f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(3.51f, 3.51f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(4.92f, 4.92f);
        pathBuilderA.lineTo(7.0f, 9.83f);
        pathBuilderA.verticalLineToRelative(4.3f);
        pathBuilderA.lineToRelative(-2.6f, -1.48f);
        pathBuilderA.curveToRelative(-0.17f, -0.09f, -0.34f, -0.14f, -0.54f, -0.14f);
        pathBuilderA.curveToRelative(-0.26f, 0.0f, -0.5f, 0.09f, -0.7f, 0.26f);
        pathBuilderA.lineTo(2.0f, 13.88f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.lineToRelative(6.8f, 7.18f);
        pathBuilderA.curveToRelative(0.57f, 0.6f, 1.35f, 0.94f, 2.18f, 0.94f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderA, 17.0f, 22.0f, 17.0f, 22.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doNotTouch = imageVectorBuild;
        return imageVectorBuild;
    }
}
