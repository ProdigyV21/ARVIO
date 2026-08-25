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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_toys", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Toys", "Landroidx/compose/material/icons/Icons$TwoTone;", "getToys", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ToysKt {
    private static ImageVector _toys;

    public static final ImageVector getToys(Icons.TwoTone twoTone) {
        ImageVector imageVector = _toys;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Toys", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(18.0f, 12.0f, 6.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.51f, 0.2f, 0.97f, 0.51f, 1.32f);
        pathBuilderQ.curveTo(5.05f, 14.53f, 5.97f, 14.0f, 7.0f, 14.0f);
        pathBuilderQ.curveToRelative(1.3f, 0.0f, 2.4f, 0.84f, 2.82f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(4.37f);
        pathBuilderQ.curveToRelative(0.41f, -1.16f, 1.51f, -2.0f, 2.82f, -2.0f);
        pathBuilderQ.curveToRelative(1.03f, 0.0f, 1.95f, 0.53f, 2.49f, 1.32f);
        pathBuilderQ.curveTo(19.8f, 14.97f, 20.0f, 14.51f, 20.0f, 14.0f);
        pathBuilderQ.curveTo(20.0f, 12.9f, 19.1f, 12.0f, 18.0f, 12.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.75f, 10.08f, 17.4f, 6.05f);
        pathBuilderJ.curveTo(17.0f, 4.82f, 15.85f, 4.0f, 14.56f, 4.0f);
        pathBuilderJ.horizontalLineTo(9.44f);
        pathBuilderJ.curveTo(8.15f, 4.0f, 7.0f, 4.82f, 6.6f, 6.05f);
        pathBuilderJ.lineTo(5.81f, 8.4f);
        pathBuilderJ.lineTo(4.41f, 7.0f);
        pathBuilderJ.lineToRelative(0.29f, -0.29f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineToRelative(-2.0f, 2.0f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineTo(3.0f, 8.41f);
        pathBuilderJ.lineToRelative(1.79f, 1.79f);
        pathBuilderJ.curveTo(3.18f, 10.72f, 2.0f, 12.22f, 2.0f, 14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.49f, 0.83f, 2.78f, 2.05f, 3.47f);
        pathBuilderJ.curveTo(4.27f, 18.9f, 5.51f, 20.0f, 7.0f, 20.0f);
        pathBuilderJ.curveToRelative(1.3f, 0.0f, 2.4f, -0.84f, 2.82f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(4.37f);
        pathBuilderJ.curveToRelative(0.41f, 1.16f, 1.51f, 2.0f, 2.82f, 2.0f);
        pathBuilderJ.curveToRelative(1.49f, 0.0f, 2.73f, -1.1f, 2.95f, -2.53f);
        pathBuilderJ.curveTo(21.17f, 16.78f, 22.0f, 15.49f, 22.0f, 14.0f);
        pathBuilderJ.curveTo(22.0f, 12.05f, 20.6f, 10.43f, 18.75f, 10.08f);
        a.g(pathBuilderJ, 13.0f, 6.0f, 1.56f);
        pathBuilderJ.curveToRelative(0.43f, 0.0f, 0.81f, 0.27f, 0.95f, 0.68f);
        pathBuilderJ.lineTo(16.61f, 10.0f);
        pathBuilderJ.horizontalLineTo(13.0f);
        pathBuilderJ.verticalLineTo(6.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(8.49f, 6.68f);
        pathBuilderJ.curveTo(8.63f, 6.27f, 9.01f, 6.0f, 9.44f, 6.0f);
        a0.a.w(pathBuilderJ, 11.0f, 4.0f, 7.41f);
        b.y(pathBuilderJ, 7.39f, 9.98f, 8.49f, 6.68f);
        pathBuilderJ.moveTo(7.0f, 18.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveTo(7.55f, 18.0f, 7.0f, 18.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(17.0f, 18.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveTo(17.55f, 18.0f, 17.0f, 18.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.49f, 15.32f);
        pathBuilderJ.curveTo(18.95f, 14.53f, 18.03f, 14.0f, 17.0f, 14.0f);
        pathBuilderJ.curveToRelative(-1.3f, 0.0f, -2.4f, 0.84f, -2.82f, 2.0f);
        pathBuilderJ.horizontalLineTo(9.82f);
        pathBuilderJ.curveTo(9.4f, 14.84f, 8.3f, 14.0f, 7.0f, 14.0f);
        pathBuilderJ.curveToRelative(-1.03f, 0.0f, -1.95f, 0.53f, -2.49f, 1.32f);
        pathBuilderJ.curveTo(4.2f, 14.97f, 4.0f, 14.51f, 4.0f, 14.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderJ.curveTo(20.0f, 14.51f, 19.8f, 14.97f, 19.49f, 15.32f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _toys = imageVectorBuild;
        return imageVectorBuild;
    }
}
