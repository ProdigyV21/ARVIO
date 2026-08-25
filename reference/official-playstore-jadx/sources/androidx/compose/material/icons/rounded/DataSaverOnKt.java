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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dataSaverOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DataSaverOn", "Landroidx/compose/material/icons/Icons$Rounded;", "getDataSaverOn", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DataSaverOnKt {
    private static ImageVector _dataSaverOn;

    public static final ImageVector getDataSaverOn(Icons.Rounded rounded) {
        ImageVector imageVector = _dataSaverOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DataSaverOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(11.0f, 11.0f, 9.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(2.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-2.0f);
        pathBuilderQ.horizontalLineToRelative(2.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.verticalLineTo(9.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        f.r(pathBuilderQ, 11.0f, 12.0f, 19.0f);
        pathBuilderQ.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderQ.curveToRelative(0.0f, -3.53f, 2.61f, -6.43f, 6.0f, -6.92f);
        pathBuilderQ.verticalLineTo(2.05f);
        pathBuilderQ.curveTo(5.94f, 2.55f, 2.0f, 6.81f, 2.0f, 12.0f);
        pathBuilderQ.curveToRelative(0.0f, 5.52f, 4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderQ.curveToRelative(3.31f, 0.0f, 6.24f, -1.61f, 8.06f, -4.09f);
        pathBuilderQ.lineToRelative(-2.6f, -1.53f);
        pathBuilderQ.curveTo(16.17f, 17.98f, 14.21f, 19.0f, 12.0f, 19.0f);
        d.k(pathBuilderQ, 13.0f, 2.05f, 3.03f);
        pathBuilderQ.curveToRelative(3.39f, 0.49f, 6.0f, 3.39f, 6.0f, 6.92f);
        pathBuilderQ.curveToRelative(0.0f, 0.9f, -0.18f, 1.75f, -0.48f, 2.54f);
        pathBuilderQ.lineToRelative(2.6f, 1.53f);
        pathBuilderQ.curveTo(21.68f, 14.83f, 22.0f, 13.45f, 22.0f, 12.0f);
        pathBuilderQ.curveTo(22.0f, 6.82f, 18.05f, 2.55f, 13.0f, 2.05f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dataSaverOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
