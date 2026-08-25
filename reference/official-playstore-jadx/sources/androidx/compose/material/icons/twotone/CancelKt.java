package androidx.compose.material.icons.twotone;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cancel", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cancel", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCancel", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CancelKt {
    private static ImageVector _cancel;

    public static final ImageVector getCancel(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cancel;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Cancel", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-4.41f, 0.0f, -8.0f, 3.59f, -8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.59f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.59f, -8.0f, -8.0f, -8.0f);
        a.p(pathBuilderA, 17.0f, 15.59f, 15.59f, 17.0f);
        pathBuilderA.lineTo(12.0f, 13.41f);
        pathBuilderA.lineTo(8.41f, 17.0f);
        pathBuilderA.lineTo(7.0f, 15.59f);
        pathBuilderA.lineTo(10.59f, 12.0f);
        pathBuilderA.lineTo(7.0f, 8.41f);
        pathBuilderA.lineTo(8.41f, 7.0f);
        pathBuilderA.lineTo(12.0f, 10.59f);
        pathBuilderA.lineTo(15.59f, 7.0f);
        pathBuilderA.lineTo(17.0f, 8.41f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 13.41f, 12.0f, 17.0f, 15.59f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 2.0f);
        pathBuilderA2.curveTo(6.47f, 2.0f, 2.0f, 6.47f, 2.0f, 12.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.47f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.47f, 10.0f, -10.0f);
        pathBuilderA2.reflectiveCurveTo(17.53f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 20.0f);
        pathBuilderA2.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(-3.59f, 8.0f, -8.0f, 8.0f);
        a.p(pathBuilderA2, 15.59f, 7.0f, 12.0f, 10.59f);
        pathBuilderA2.lineTo(8.41f, 7.0f);
        pathBuilderA2.lineTo(7.0f, 8.41f);
        pathBuilderA2.lineTo(10.59f, 12.0f);
        pathBuilderA2.lineTo(7.0f, 15.59f);
        pathBuilderA2.lineTo(8.41f, 17.0f);
        pathBuilderA2.lineTo(12.0f, 13.41f);
        pathBuilderA2.lineTo(15.59f, 17.0f);
        pathBuilderA2.lineTo(17.0f, 15.59f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA2, 13.41f, 12.0f, 17.0f, 8.41f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cancel = imageVectorBuild;
        return imageVectorBuild;
    }
}
