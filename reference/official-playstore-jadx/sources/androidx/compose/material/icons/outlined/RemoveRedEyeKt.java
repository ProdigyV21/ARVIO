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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_removeRedEye", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RemoveRedEye", "Landroidx/compose/material/icons/Icons$Outlined;", "getRemoveRedEye", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RemoveRedEyeKt {
    private static ImageVector _removeRedEye;

    public static final ImageVector getRemoveRedEye(Icons.Outlined outlined) {
        ImageVector imageVector = _removeRedEye;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RemoveRedEye", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.5f);
        pathBuilderA.curveToRelative(3.79f, 0.0f, 7.17f, 2.13f, 8.82f, 5.5f);
        pathBuilderA.curveToRelative(-1.65f, 3.37f, -5.02f, 5.5f, -8.82f, 5.5f);
        pathBuilderA.reflectiveCurveTo(4.83f, 15.37f, 3.18f, 12.0f);
        pathBuilderA.curveTo(4.83f, 8.63f, 8.21f, 6.5f, 12.0f, 6.5f);
        pathBuilderA.moveToRelative(0.0f, -2.0f);
        pathBuilderA.curveTo(7.0f, 4.5f, 2.73f, 7.61f, 1.0f, 12.0f);
        pathBuilderA.curveToRelative(1.73f, 4.39f, 6.0f, 7.5f, 11.0f, 7.5f);
        pathBuilderA.reflectiveCurveToRelative(9.27f, -3.11f, 11.0f, -7.5f);
        pathBuilderA.curveToRelative(-1.73f, -4.39f, -6.0f, -7.5f, -11.0f, -7.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 9.5f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.12f, 2.5f, -2.5f, 2.5f);
        pathBuilderA.reflectiveCurveToRelative(-2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.moveToRelative(0.0f, -2.0f);
        pathBuilderA.curveToRelative(-2.48f, 0.0f, -4.5f, 2.02f, -4.5f, 4.5f);
        pathBuilderA.reflectiveCurveToRelative(2.02f, 4.5f, 4.5f, 4.5f);
        pathBuilderA.reflectiveCurveToRelative(4.5f, -2.02f, 4.5f, -4.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -2.02f, -4.5f, -4.5f, -4.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _removeRedEye = imageVectorBuild;
        return imageVectorBuild;
    }
}
