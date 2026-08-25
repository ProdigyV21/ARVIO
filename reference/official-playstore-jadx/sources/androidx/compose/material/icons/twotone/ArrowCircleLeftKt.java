package androidx.compose.material.icons.twotone;

import a0.a;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_arrowCircleLeft", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ArrowCircleLeft", "Landroidx/compose/material/icons/Icons$TwoTone;", "getArrowCircleLeft", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ArrowCircleLeftKt {
    private static ImageVector _arrowCircleLeft;

    public static final ImageVector getArrowCircleLeft(Icons.TwoTone twoTone) {
        ImageVector imageVector = _arrowCircleLeft;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ArrowCircleLeft", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.41f, -3.59f, 8.0f, -8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(-8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveTo(20.0f, 7.59f, 20.0f, 12.0f);
        pathBuilderA.moveTo(12.0f, 13.0f);
        pathBuilderA.lineToRelative(4.0f, 0.0f);
        a.l(pathBuilderA, -2.0f, -4.0f, 0.0f, 8.0f);
        pathBuilderA.lineToRelative(-4.0f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderA, 4.0f, 4.0f, 13.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(20.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 4.41f, -3.59f, 8.0f, -8.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(-8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveTo(20.0f, 7.59f, 20.0f, 12.0f);
        pathBuilderA2.moveTo(22.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA2.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
        c.z(pathBuilderA2, 22.0f, 12.0f, 12.0f, 13.0f);
        pathBuilderA2.lineToRelative(4.0f, 0.0f);
        a.l(pathBuilderA2, -2.0f, -4.0f, 0.0f, 8.0f);
        pathBuilderA2.lineToRelative(-4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderA2, 4.0f, 4.0f, 13.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _arrowCircleLeft = imageVectorBuild;
        return imageVectorBuild;
    }
}
