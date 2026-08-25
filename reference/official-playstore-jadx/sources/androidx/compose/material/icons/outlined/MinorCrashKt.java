package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_minorCrash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MinorCrash", "Landroidx/compose/material/icons/Icons$Outlined;", "getMinorCrash", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MinorCrashKt {
    private static ImageVector _minorCrash;

    public static final ImageVector getMinorCrash(Icons.Outlined outlined) {
        ImageVector imageVector = _minorCrash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MinorCrash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.92f, 9.01f);
        pathBuilderA.curveTo(18.72f, 8.42f, 18.16f, 8.0f, 17.5f, 8.0f);
        pathBuilderA.horizontalLineToRelative(-11.0f);
        pathBuilderA.curveTo(5.84f, 8.0f, 5.29f, 8.42f, 5.08f, 9.01f);
        pathBuilderA.lineTo(3.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        e.l(pathBuilderA, -1.0f, 12.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        a.n(pathBuilderA, -8.0f, 18.92f, 9.01f);
        pathBuilderA.moveTo(6.85f, 10.0f);
        a0.a.n(pathBuilderA, 10.29f, 1.04f, 3.0f, 5.81f);
        c.z(pathBuilderA, 6.85f, 10.0f, 19.0f, 20.0f);
        c.k(pathBuilderA, 5.0f, -5.0f, 14.0f, 20.0f);
        pathBuilderA.moveTo(6.0f, 17.5f);
        pathBuilderA.curveTo(6.0f, 16.67f, 6.67f, 16.0f, 7.5f, 16.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 16.67f, 9.0f, 17.5f);
        pathBuilderA.reflectiveCurveTo(8.33f, 19.0f, 7.5f, 19.0f);
        pathBuilderA.reflectiveCurveTo(6.0f, 18.33f, 6.0f, 17.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 17.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(17.33f, 19.0f, 16.5f, 19.0f);
        pathBuilderA.reflectiveCurveTo(15.0f, 18.33f, 15.0f, 17.5f);
        v.a.p(pathBuilderA, 9.41f, 5.0f, 8.0f, 6.41f);
        pathBuilderA.lineToRelative(-3.0f, -3.0f);
        b.y(pathBuilderA, 6.41f, 2.0f, 9.41f, 5.0f);
        pathBuilderA.moveTo(16.0f, 6.41f);
        pathBuilderA.lineTo(14.59f, 5.0f);
        pathBuilderA.lineToRelative(3.0f, -3.0f);
        b.y(pathBuilderA, 19.0f, 3.41f, 16.0f, 6.41f);
        e.m(pathBuilderA, 13.0f, 5.0f, -2.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderA, 2.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _minorCrash = imageVectorBuild;
        return imageVectorBuild;
    }
}
