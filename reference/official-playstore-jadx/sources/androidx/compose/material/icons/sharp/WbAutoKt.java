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
import v.b;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbAuto", "Landroidx/compose/material/icons/Icons$Sharp;", "getWbAuto", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbAutoKt {
    private static ImageVector _wbAuto;

    public static final ImageVector getWbAuto(Icons.Sharp sharp) {
        ImageVector imageVector = _wbAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.WbAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(6.85f, 12.65f, 2.3f, 8.0f, 9.0f);
        b.C(pathBuilderI, -1.15f, 3.65f, 22.0f, 7.0f);
        pathBuilderI.lineToRelative(-1.2f, 6.29f);
        pathBuilderI.lineTo(19.3f, 7.0f);
        pathBuilderI.horizontalLineToRelative(-1.6f);
        pathBuilderI.lineToRelative(-1.49f, 6.29f);
        pathBuilderI.lineTo(15.0f, 7.0f);
        pathBuilderI.horizontalLineToRelative(-0.76f);
        pathBuilderI.curveTo(12.77f, 5.17f, 10.53f, 4.0f, 8.0f, 4.0f);
        pathBuilderI.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderI.reflectiveCurveToRelative(3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderI.curveToRelative(3.13f, 0.0f, 5.84f, -1.81f, 7.15f, -4.43f);
        pathBuilderI.lineToRelative(0.1f, 0.43f);
        pathBuilderI.lineTo(17.0f, 16.0f);
        pathBuilderI.lineToRelative(1.5f, -6.1f);
        pathBuilderI.lineTo(20.0f, 16.0f);
        pathBuilderI.horizontalLineToRelative(1.75f);
        d.C(pathBuilderI, 2.05f, -9.0f, 22.0f, 7.0f);
        pathBuilderI.moveTo(10.3f, 16.0f);
        pathBuilderI.lineToRelative(-0.7f, -2.0f);
        pathBuilderI.lineTo(6.4f, 14.0f);
        pathBuilderI.lineToRelative(-0.7f, 2.0f);
        pathBuilderI.lineTo(3.8f, 16.0f);
        pathBuilderI.lineTo(7.0f, 7.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.lineToRelative(3.2f, 9.0f);
        pathBuilderI.horizontalLineToRelative(-1.9f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}
