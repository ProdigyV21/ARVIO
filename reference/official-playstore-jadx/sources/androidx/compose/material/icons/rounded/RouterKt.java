package androidx.compose.material.icons.rounded;

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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_router", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Router", "Landroidx/compose/material/icons/Icons$Rounded;", "getRouter", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RouterKt {
    private static ImageVector _router;

    public static final ImageVector getRouter(Icons.Rounded rounded) {
        ImageVector imageVector = _router;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Router", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.45f, 5.55f);
        pathBuilderA.curveToRelative(0.19f, 0.19f, 0.5f, 0.21f, 0.72f, 0.04f);
        pathBuilderA.curveTo(13.3f, 4.69f, 14.65f, 4.2f, 16.0f, 4.2f);
        pathBuilderA.reflectiveCurveToRelative(2.7f, 0.49f, 3.84f, 1.39f);
        pathBuilderA.curveToRelative(0.21f, 0.17f, 0.52f, 0.15f, 0.72f, -0.04f);
        pathBuilderA.lineToRelative(0.04f, -0.05f);
        pathBuilderA.curveToRelative(0.22f, -0.22f, 0.21f, -0.59f, -0.03f, -0.8f);
        pathBuilderA.curveTo(19.24f, 3.57f, 17.62f, 3.0f, 16.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.24f, 0.57f, -4.57f, 1.7f);
        pathBuilderA.curveToRelative(-0.24f, 0.21f, -0.26f, 0.57f, -0.03f, 0.8f);
        b.C(pathBuilderA, 0.05f, 0.05f, 13.15f, 6.31f);
        pathBuilderA.curveToRelative(-0.25f, 0.2f, -0.26f, 0.58f, -0.04f, 0.8f);
        pathBuilderA.lineToRelative(0.04f, 0.04f);
        pathBuilderA.curveToRelative(0.2f, 0.2f, 0.5f, 0.2f, 0.72f, 0.04f);
        pathBuilderA.curveToRelative(0.63f, -0.48f, 1.38f, -0.69f, 2.13f, -0.69f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.21f, 2.13f, 0.68f);
        pathBuilderA.curveToRelative(0.22f, 0.17f, 0.53f, 0.16f, 0.72f, -0.04f);
        pathBuilderA.lineToRelative(0.04f, -0.04f);
        pathBuilderA.curveToRelative(0.23f, -0.23f, 0.21f, -0.6f, -0.04f, -0.8f);
        pathBuilderA.curveToRelative(-0.83f, -0.64f, -1.84f, -1.0f, -2.85f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.02f, 0.36f, -2.85f, 1.01f);
        b.w(pathBuilderA, 19.0f, 13.0f, -2.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.lineTo(5.0f, 13.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderA, 8.0f, 18.0f, 6.0f, 18.0f);
        b.l(pathBuilderA, -2.0f, 2.0f, 2.0f);
        a.C(pathBuilderA, 11.5f, 18.0f, -2.0f, -2.0f);
        b.B(pathBuilderA, 2.0f, 2.0f, 15.0f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.h(pathBuilderA, -2.0f, -2.0f, 2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _router = imageVectorBuild;
        return imageVectorBuild;
    }
}
