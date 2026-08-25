package androidx.compose.material.icons.sharp;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_transgender", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Transgender", "Landroidx/compose/material/icons/Icons$Sharp;", "getTransgender", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransgenderKt {
    private static ImageVector _transgender;

    public static final ImageVector getTransgender(Icons.Sharp sharp) {
        ImageVector imageVector = _transgender;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Transgender", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 8.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilderA.reflectiveCurveTo(13.93f, 15.0f, 12.0f, 15.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilderA.reflectiveCurveTo(10.07f, 8.0f, 12.0f, 8.0f);
        a.z(pathBuilderA, 16.53f, 8.38f, 3.97f, -3.96f);
        d.A(pathBuilderA, 7.0f, 2.0f, 1.0f, -6.0f);
        d.D(pathBuilderA, 2.0f, 2.58f, -3.97f, 3.97f);
        pathBuilderA.curveTo(14.23f, 6.36f, 13.16f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(-1.16f, 0.0f, -2.23f, 0.36f, -3.11f, 0.97f);
        pathBuilderA.lineTo(8.24f, 6.32f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineTo(8.24f, 3.49f);
        pathBuilderA.lineTo(6.82f, 4.9f);
        b.p(pathBuilderA, 4.92f, 3.0f, 7.5f, 1.0f);
        a0.a.j(pathBuilderA, -6.0f, 6.0f, 2.0f, 4.42f);
        pathBuilderA.lineToRelative(1.91f, 1.9f);
        pathBuilderA.lineTo(3.99f, 7.74f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineToRelative(0.65f, 0.65f);
        pathBuilderA.curveTo(6.86f, 9.27f, 6.5f, 10.34f, 6.5f, 11.5f);
        pathBuilderA.curveToRelative(0.0f, 2.7f, 1.94f, 4.94f, 4.5f, 5.41f);
        b.m(pathBuilderA, 11.0f, 19.0f, 9.0f, 2.0f);
        a.o(pathBuilderA, 2.0f, 2.0f, 2.0f, -2.0f);
        e.g(pathBuilderA, 2.0f, -2.0f, -2.0f);
        pathBuilderA.lineToRelative(0.0f, -2.09f);
        pathBuilderA.curveToRelative(2.56f, -0.47f, 4.5f, -2.71f, 4.5f, -5.41f);
        pathBuilderA.curveTo(17.5f, 10.34f, 17.14f, 9.27f, 16.53f, 8.38f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _transgender = imageVectorBuild;
        return imageVectorBuild;
    }
}
