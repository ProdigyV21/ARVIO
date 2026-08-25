package androidx.compose.material.icons.twotone;

import a0.c;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_manageHistory", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ManageHistory", "Landroidx/compose/material/icons/Icons$TwoTone;", "getManageHistory", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ManageHistoryKt {
    private static ImageVector _manageHistory;

    public static final ImageVector getManageHistory(Icons.TwoTone twoTone) {
        ImageVector imageVector = _manageHistory;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ManageHistory", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.69f, 18.37f, 1.14f, -1.0f);
        pathBuilderR.lineToRelative(-1.0f, -1.73f);
        pathBuilderR.lineToRelative(-1.45f, 0.49f);
        pathBuilderR.curveToRelative(-0.32f, -0.27f, -0.68f, -0.48f, -1.08f, -0.63f);
        pathBuilderR.lineTo(20.0f, 14.0f);
        pathBuilderR.horizontalLineToRelative(-2.0f);
        pathBuilderR.lineToRelative(-0.3f, 1.49f);
        pathBuilderR.curveToRelative(-0.4f, 0.15f, -0.76f, 0.36f, -1.08f, 0.63f);
        pathBuilderR.lineToRelative(-1.45f, -0.49f);
        pathBuilderR.lineToRelative(-1.0f, 1.73f);
        pathBuilderR.lineToRelative(1.14f, 1.0f);
        pathBuilderR.curveToRelative(-0.08f, 0.5f, -0.08f, 0.76f, 0.0f, 1.26f);
        pathBuilderR.lineToRelative(-1.14f, 1.0f);
        pathBuilderR.lineToRelative(1.0f, 1.73f);
        pathBuilderR.lineToRelative(1.45f, -0.49f);
        pathBuilderR.curveToRelative(0.32f, 0.27f, 0.68f, 0.48f, 1.08f, 0.63f);
        pathBuilderR.lineTo(18.0f, 24.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.lineToRelative(0.3f, -1.49f);
        pathBuilderR.curveToRelative(0.4f, -0.15f, 0.76f, -0.36f, 1.08f, -0.63f);
        pathBuilderR.lineToRelative(1.45f, 0.49f);
        pathBuilderR.lineToRelative(1.0f, -1.73f);
        pathBuilderR.lineToRelative(-1.14f, -1.0f);
        pathBuilderR.curveTo(22.77f, 19.13f, 22.77f, 18.87f, 22.69f, 18.37f);
        pathBuilderR.close();
        pathBuilderR.moveTo(19.0f, 21.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderR.reflectiveCurveTo(20.1f, 21.0f, 19.0f, 21.0f);
        d.k(pathBuilderR, 11.0f, 7.0f, 5.41f);
        pathBuilderR.lineToRelative(2.36f, 2.36f);
        pathBuilderR.lineToRelative(1.04f, -1.79f);
        c.z(pathBuilderR, 13.0f, 11.59f, 7.0f, 11.0f);
        pathBuilderR.moveTo(21.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderR.curveTo(9.17f, 3.0f, 6.65f, 4.32f, 5.0f, 6.36f);
        v.c.o(pathBuilderR, 4.0f, 3.0f, 6.0f, 6.0f);
        pathBuilderR.verticalLineTo(8.0f);
        pathBuilderR.horizontalLineTo(6.26f);
        pathBuilderR.curveTo(7.53f, 6.19f, 9.63f, 5.0f, 12.0f, 5.0f);
        pathBuilderR.curveToRelative(3.86f, 0.0f, 7.0f, 3.14f, 7.0f, 7.0f);
        a.x(pathBuilderR, 21.0f, 10.86f, 18.91f);
        pathBuilderR.curveTo(7.87f, 18.42f, 5.51f, 16.01f, 5.08f, 13.0f);
        pathBuilderR.horizontalLineTo(3.06f);
        pathBuilderR.curveToRelative(0.5f, 4.5f, 4.31f, 8.0f, 8.94f, 8.0f);
        pathBuilderR.curveToRelative(0.02f, 0.0f, 0.05f, 0.0f, 0.07f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 10.86f, 18.91f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _manageHistory = imageVectorBuild;
        return imageVectorBuild;
    }
}
