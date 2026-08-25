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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cloudOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CloudOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCloudOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloudOffKt {
    private static ImageVector _cloudOff;

    public static final ImageVector getCloudOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cloudOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CloudOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(22.0f, 15.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.horizontalLineToRelative(-1.5f);
        pathBuilderA.verticalLineToRelative(-0.5f);
        pathBuilderA.curveTo(17.5f, 8.46f, 15.04f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(-0.77f, 0.0f, -1.49f, 0.17f, -2.16f, 0.46f);
        pathBuilderA.lineTo(20.79f, 17.4f);
        pathBuilderA.curveToRelative(0.73f, -0.55f, 1.21f, -1.41f, 1.21f, -2.4f);
        pathBuilderA.close();
        pathBuilderA.moveTo(2.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        a.n(pathBuilderA, 9.73f, -8.0f, -8.0f, 6.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(19.35f, 10.04f);
        pathBuilderA2.curveTo(18.67f, 6.59f, 15.64f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.curveToRelative(-1.33f, 0.0f, -2.57f, 0.36f, -3.65f, 0.97f);
        pathBuilderA2.lineToRelative(1.49f, 1.49f);
        pathBuilderA2.curveTo(10.51f, 6.17f, 11.23f, 6.0f, 12.0f, 6.0f);
        pathBuilderA2.curveToRelative(3.04f, 0.0f, 5.5f, 2.46f, 5.5f, 5.5f);
        pathBuilderA2.verticalLineToRelative(0.5f);
        pathBuilderA2.horizontalLineTo(19.0f);
        pathBuilderA2.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.99f, -0.48f, 1.85f, -1.21f, 2.4f);
        pathBuilderA2.lineToRelative(1.41f, 1.41f);
        pathBuilderA2.curveToRelative(1.09f, -0.92f, 1.8f, -2.27f, 1.8f, -3.81f);
        pathBuilderA2.curveToRelative(0.0f, -2.64f, -2.05f, -4.78f, -4.65f, -4.96f);
        v.a.z(pathBuilderA2, 3.0f, 5.27f, 2.77f, 2.77f);
        pathBuilderA2.horizontalLineToRelative(-0.42f);
        pathBuilderA2.curveTo(2.34f, 8.36f, 0.0f, 10.91f, 0.0f, 14.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA2.horizontalLineToRelative(11.73f);
        pathBuilderA2.lineToRelative(2.0f, 2.0f);
        pathBuilderA2.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderA2, 4.41f, 3.86f, 3.0f, 5.27f);
        pathBuilderA2.moveTo(7.73f, 10.0f);
        pathBuilderA2.lineToRelative(8.0f, 8.0f);
        pathBuilderA2.horizontalLineTo(6.0f);
        pathBuilderA2.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.d(pathBuilderA2, 1.73f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cloudOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
