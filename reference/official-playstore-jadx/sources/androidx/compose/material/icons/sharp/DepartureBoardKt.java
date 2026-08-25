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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_departureBoard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DepartureBoard", "Landroidx/compose/material/icons/Icons$Sharp;", "getDepartureBoard", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DepartureBoardKt {
    private static ImageVector _departureBoard;

    public static final ImageVector getDepartureBoard(Icons.Sharp sharp) {
        ImageVector imageVector = _departureBoard;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DepartureBoard", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.34f, 1.13f);
        pathBuilderA.curveToRelative(-2.94f, -0.55f, -5.63f, 0.75f, -7.12f, 2.92f);
        pathBuilderA.curveToRelative(0.01f, -0.01f, 0.01f, -0.02f, 0.02f, -0.03f);
        pathBuilderA.curveTo(9.84f, 4.0f, 9.42f, 4.0f, 9.0f, 4.0f);
        pathBuilderA.curveToRelative(-4.42f, 0.0f, -8.0f, 0.5f, -8.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 0.88f, 0.39f, 1.67f, 1.0f, 2.22f);
        b.A(pathBuilderA, 2.0f, 23.0f, 3.0f, -2.0f);
        a.o(pathBuilderA, 8.0f, 2.0f, 3.0f, -2.78f);
        pathBuilderA.curveToRelative(0.61f, -0.55f, 1.0f, -1.34f, 1.0f, -2.22f);
        pathBuilderA.verticalLineToRelative(-3.08f);
        pathBuilderA.curveToRelative(3.72f, -0.54f, 6.5f, -3.98f, 5.92f, -7.97f);
        pathBuilderA.curveToRelative(-0.42f, -2.9f, -2.7f, -5.29f, -5.58f, -5.82f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.5f, 19.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(3.67f, 16.0f, 4.5f, 16.0f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(5.33f, 19.0f, 4.5f, 19.0f);
        a.p(pathBuilderA, 3.0f, 13.0f, 3.0f, 8.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.0f, 1.96f, 0.81f, 3.73f, 2.11f, 5.0f);
        c.z(pathBuilderA, 3.0f, 13.0f, 13.5f, 19.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.24f, 5.0f, -5.0f, 5.0f);
        a.p(pathBuilderA, 16.5f, 4.0f, 15.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.lineToRelative(3.62f, 2.16f);
        pathBuilderA.lineToRelative(0.75f, -1.23f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, -2.87f, -1.68f, 16.5f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _departureBoard = imageVectorBuild;
        return imageVectorBuild;
    }
}
