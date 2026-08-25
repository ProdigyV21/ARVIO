package androidx.compose.material.icons.twotone;

import a0.b;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dry", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Dry", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDry", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DryKt {
    private static ImageVector _dry;

    public static final ImageVector getDry(Icons.TwoTone twoTone) {
        ImageVector imageVector = _dry;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Dry", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(10.0f, 21.0f, 4.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-6.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.39f, 0.23f, -0.64f, 0.36f, -0.75f);
        pathBuilderQ.lineTo(7.0f, 9.87f);
        pathBuilderQ.verticalLineTo(12.0f);
        pathBuilderQ.lineToRelative(3.0f, 0.0f);
        pathBuilderQ.verticalLineTo(21.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.75f, 16.0f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        a.n(pathBuilderA, 12.0f, -1.0f, 6.75f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.curveToRelative(0.0f, -0.67f, -0.53f, -1.2f, -1.18f, -1.24f);
        pathBuilderA.lineTo(8.87f, 10.0f);
        pathBuilderA.lineToRelative(1.48f, -2.6f);
        pathBuilderA.curveToRelative(0.09f, -0.17f, 0.14f, -0.34f, 0.14f, -0.54f);
        pathBuilderA.curveToRelative(0.0f, -0.26f, -0.09f, -0.5f, -0.26f, -0.7f);
        pathBuilderA.lineTo(9.12f, 5.0f);
        pathBuilderA.lineToRelative(-7.18f, 6.8f);
        pathBuilderA.curveTo(1.34f, 12.36f, 1.0f, 13.15f, 1.0f, 13.97f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(13.75f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        a.n(pathBuilderA, 12.0f, -1.0f, 7.75f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveTo(20.44f, 17.0f, 19.75f, 17.0f);
        b.e(pathBuilderA, 12.0f, -1.0f, 20.75f);
        pathBuilderA.moveTo(10.0f, 21.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.39f, 0.23f, -0.64f, 0.36f, -0.75f);
        pathBuilderA.lineTo(7.0f, 9.87f);
        pathBuilderA.verticalLineTo(12.0f);
        pathBuilderA.lineToRelative(3.0f, 0.0f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.65f, 4.86f);
        pathBuilderA.lineToRelative(-0.07f, -0.07f);
        pathBuilderA.curveToRelative(-0.57f, -0.62f, -0.82f, -1.41f, -0.67f, -2.2f);
        pathBuilderA.lineTo(15.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-1.89f);
        pathBuilderA.lineToRelative(-0.06f, 0.43f);
        pathBuilderA.curveToRelative(-0.2f, 1.36f, 0.27f, 2.71f, 1.3f, 3.72f);
        pathBuilderA.lineToRelative(0.07f, 0.06f);
        pathBuilderA.curveToRelative(0.57f, 0.62f, 0.82f, 1.41f, 0.67f, 2.2f);
        pathBuilderA.lineTo(14.98f, 9.0f);
        pathBuilderA.horizontalLineToRelative(1.91f);
        pathBuilderA.lineToRelative(0.06f, -0.43f);
        pathBuilderA.curveTo(17.16f, 7.21f, 16.68f, 5.86f, 15.65f, 4.86f);
        a.z(pathBuilderA, 19.65f, 4.86f, -0.07f, -0.07f);
        pathBuilderA.curveToRelative(-0.57f, -0.62f, -0.82f, -1.41f, -0.67f, -2.2f);
        pathBuilderA.lineTo(19.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-1.89f);
        pathBuilderA.lineToRelative(-0.06f, 0.43f);
        pathBuilderA.curveToRelative(-0.2f, 1.36f, 0.27f, 2.71f, 1.3f, 3.72f);
        pathBuilderA.lineToRelative(0.07f, 0.06f);
        pathBuilderA.curveToRelative(0.57f, 0.62f, 0.82f, 1.41f, 0.67f, 2.2f);
        pathBuilderA.lineTo(18.98f, 9.0f);
        pathBuilderA.horizontalLineToRelative(1.91f);
        pathBuilderA.lineToRelative(0.06f, -0.43f);
        pathBuilderA.curveTo(21.16f, 7.21f, 20.68f, 5.86f, 19.65f, 4.86f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dry = imageVectorBuild;
        return imageVectorBuild;
    }
}
