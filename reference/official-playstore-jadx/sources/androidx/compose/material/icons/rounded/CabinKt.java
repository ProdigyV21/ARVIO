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
import androidx.fragment.app.a2;
import kotlin.Metadata;
import v.a;
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cabin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cabin", "Landroidx/compose/material/icons/Icons$Rounded;", "getCabin", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CabinKt {
    private static ImageVector _cabin;

    public static final ImageVector getCabin(Icons.Rounded rounded) {
        ImageVector imageVector = _cabin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Cabin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.37f, 3.55f);
        pathBuilderA.curveTo(4.89f, 2.62f, 5.87f, 2.0f, 7.0f, 2.0f);
        pathBuilderA.curveToRelative(0.38f, 0.0f, 0.72f, -0.22f, 0.89f, -0.53f);
        pathBuilderA.curveTo(8.04f, 1.16f, 8.39f, 1.0f, 8.73f, 1.0f);
        pathBuilderA.curveToRelative(0.74f, 0.0f, 1.26f, 0.8f, 0.9f, 1.45f);
        pathBuilderA.curveTo(9.11f, 3.38f, 8.13f, 4.0f, 7.0f, 4.0f);
        pathBuilderA.curveTo(6.62f, 4.0f, 6.28f, 4.22f, 6.11f, 4.53f);
        pathBuilderA.curveTo(5.96f, 4.84f, 5.61f, 5.0f, 5.27f, 5.0f);
        pathBuilderA.curveTo(4.53f, 5.0f, 4.01f, 4.2f, 4.37f, 3.55f);
        pathBuilderA.close();
        pathBuilderA.moveTo(22.39f, 12.19f);
        pathBuilderA.curveToRelative(-0.34f, 0.44f, -0.96f, 0.52f, -1.4f, 0.19f);
        pathBuilderA.lineTo(20.0f, 11.62f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-8.38f);
        pathBuilderA.lineToRelative(-0.99f, 0.76f);
        pathBuilderA.curveToRelative(-0.44f, 0.34f, -1.07f, 0.25f, -1.4f, -0.19f);
        pathBuilderA.curveToRelative(-0.33f, -0.44f, -0.25f, -1.07f, 0.19f, -1.4f);
        pathBuilderA.lineTo(4.0f, 9.11f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.58f);
        pathBuilderA.lineToRelative(5.39f, -4.12f);
        pathBuilderA.curveToRelative(0.36f, -0.27f, 0.86f, -0.27f, 1.21f, 0.0f);
        pathBuilderA.lineToRelative(9.6f, 7.33f);
        pathBuilderA.curveTo(22.64f, 11.13f, 22.73f, 11.76f, 22.39f, 12.19f);
        a.g(pathBuilderA, 10.06f, 7.0f, 3.89f);
        b.y(pathBuilderA, 12.0f, 5.52f, 10.06f, 7.0f);
        e.o(pathBuilderA, 6.0f, 10.1f, 11.0f, 12.0f);
        a2.u(pathBuilderA, -0.9f, 16.56f, 9.0f, 7.44f);
        c.z(pathBuilderA, 6.0f, 10.1f, 6.0f, 13.0f);
        a0.b.f(pathBuilderA, 2.0f, 12.0f, -2.0f, 6.0f);
        c.u(pathBuilderA, 18.0f, 19.0f, -2.0f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderA, 2.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cabin = imageVectorBuild;
        return imageVectorBuild;
    }
}
