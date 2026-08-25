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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Stroller", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStroller", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrollerKt {
    private static ImageVector _stroller;

    public static final ImageVector getStroller(Icons.TwoTone twoTone) {
        ImageVector imageVector = _stroller;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Stroller", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.0f, 5.0f);
        pathBuilderA.curveToRelative(0.29f, 0.0f, 0.58f, 0.02f, 0.86f, 0.05f);
        pathBuilderA.lineTo(9.49f, 6.66f);
        pathBuilderA.lineToRelative(-1.4f, -1.4f);
        pathBuilderA.curveTo(8.71f, 5.09f, 9.35f, 5.0f, 10.0f, 5.0f);
        f.g(pathBuilderA, 15.0f, 8.66f, 15.0f, 9.6f);
        pathBuilderA.lineTo(15.0f, 8.66f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(10.0f, 5.0f);
        pathBuilderA2.curveToRelative(0.29f, 0.0f, 0.58f, 0.02f, 0.86f, 0.05f);
        pathBuilderA2.lineTo(9.49f, 6.66f);
        pathBuilderA2.lineToRelative(-1.4f, -1.4f);
        pathBuilderA2.curveTo(8.71f, 5.09f, 9.35f, 5.0f, 10.0f, 5.0f);
        f.g(pathBuilderA2, 15.0f, 8.66f, 15.0f, 9.6f);
        pathBuilderA2.lineTo(15.0f, 8.66f);
        pathBuilderA2.moveTo(18.65f, 3.0f);
        pathBuilderA2.curveToRelative(-1.66f, 0.0f, -2.54f, 1.27f, -3.18f, 2.03f);
        pathBuilderA2.lineToRelative(-8.8f, 10.32f);
        pathBuilderA2.curveTo(6.12f, 16.0f, 6.58f, 17.0f, 7.43f, 17.0f);
        pathBuilderA2.horizontalLineTo(15.0f);
        pathBuilderA2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.verticalLineTo(6.27f);
        pathBuilderA2.curveTo(17.58f, 5.59f, 17.97f, 5.0f, 18.65f, 5.0f);
        pathBuilderA2.curveTo(19.42f, 5.0f, 20.0f, 5.66f, 20.0f, 6.48f);
        c.i(pathBuilderA2, 7.0f, 2.0f, 6.48f);
        pathBuilderA2.curveTo(22.0f, 4.56f, 20.52f, 3.0f, 18.65f, 3.0f);
        c.z(pathBuilderA2, 18.65f, 3.0f, 10.0f, 3.0f);
        pathBuilderA2.curveTo(8.03f, 3.0f, 6.21f, 3.64f, 4.72f, 4.72f);
        pathBuilderA2.lineToRelative(4.89f, 4.89f);
        pathBuilderA2.lineToRelative(4.7f, -5.51f);
        pathBuilderA2.curveTo(13.03f, 3.4f, 11.56f, 3.0f, 10.0f, 3.0f);
        c.z(pathBuilderA2, 10.0f, 3.0f, 16.0f, 18.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveTo(17.1f, 18.0f, 16.0f, 18.0f);
        c.z(pathBuilderA2, 16.0f, 18.0f, 6.0f, 18.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveTo(7.1f, 18.0f, 6.0f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA2, 6.0f, 18.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stroller = imageVectorBuild;
        return imageVectorBuild;
    }
}
