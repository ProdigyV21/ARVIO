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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_disabledVisible", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DisabledVisible", "Landroidx/compose/material/icons/Icons$Sharp;", "getDisabledVisible", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DisabledVisibleKt {
    private static ImageVector _disabledVisible;

    public static final ImageVector getDisabledVisible(Icons.Sharp sharp) {
        ImageVector imageVector = _disabledVisible;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DisabledVisible", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.99f, 12.34f);
        pathBuilderA.curveTo(22.0f, 12.23f, 22.0f, 12.11f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.17f, 3.93f, 9.43f, 8.96f, 9.95f);
        pathBuilderA.curveToRelative(-0.93f, -0.73f, -1.72f, -1.64f, -2.32f, -2.68f);
        pathBuilderA.curveTo(5.9f, 18.0f, 4.0f, 15.22f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.85f, 0.63f, -3.55f, 1.69f, -4.9f);
        pathBuilderA.lineToRelative(5.66f, 5.66f);
        pathBuilderA.curveToRelative(0.56f, -0.4f, 1.17f, -0.73f, 1.82f, -1.0f);
        pathBuilderA.lineTo(7.1f, 5.69f);
        pathBuilderA.curveTo(8.45f, 4.63f, 10.15f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(4.24f, 0.0f, 7.7f, 3.29f, 7.98f, 7.45f);
        pathBuilderA.curveTo(20.69f, 11.67f, 21.37f, 11.97f, 21.99f, 12.34f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 13.0f);
        pathBuilderA.curveToRelative(-3.18f, 0.0f, -5.9f, 1.87f, -7.0f, 4.5f);
        pathBuilderA.curveToRelative(1.1f, 2.63f, 3.82f, 4.5f, 7.0f, 4.5f);
        pathBuilderA.reflectiveCurveToRelative(5.9f, -1.87f, 7.0f, -4.5f);
        pathBuilderA.curveTo(22.9f, 14.87f, 20.18f, 13.0f, 17.0f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 20.0f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.curveTo(19.5f, 18.88f, 18.38f, 20.0f, 17.0f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.5f, 17.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 18.5f, 16.67f, 18.5f, 17.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _disabledVisible = imageVectorBuild;
        return imageVectorBuild;
    }
}
