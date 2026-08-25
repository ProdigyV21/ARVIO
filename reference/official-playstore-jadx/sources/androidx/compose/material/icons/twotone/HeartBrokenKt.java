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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_heartBroken", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeartBroken", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHeartBroken", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeartBrokenKt {
    private static ImageVector _heartBroken;

    public static final ImageVector getHeartBroken(Icons.TwoTone twoTone) {
        ImageVector imageVector = _heartBroken;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HeartBroken", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.23f, 5.42f);
        pathBuilderA.curveTo(8.69f, 5.15f, 8.09f, 5.0f, 7.5f, 5.0f);
        pathBuilderA.curveTo(5.54f, 5.0f, 4.0f, 6.54f, 4.0f, 8.5f);
        pathBuilderA.curveToRelative(0.0f, 2.5f, 2.45f, 4.84f, 6.24f, 8.23f);
        pathBuilderA.lineTo(10.77f, 12.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderA, 7.35f, 9.23f, 5.42f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(16.5f, 5.0f);
        pathBuilderA2.curveToRelative(-0.37f, 0.0f, -0.75f, 0.06f, -1.12f, 0.18f);
        pathBuilderA2.lineTo(14.77f, 7.0f);
        pathBuilderA2.horizontalLineToRelative(2.91f);
        pathBuilderA2.lineToRelative(-2.56f, 8.53f);
        pathBuilderA2.curveTo(17.98f, 12.93f, 20.0f, 10.71f, 20.0f, 8.5f);
        pathBuilderA2.curveTo(20.0f, 6.54f, 18.46f, 5.0f, 16.5f, 5.0f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(16.5f, 3.0f);
        pathBuilderA3.curveToRelative(-0.96f, 0.0f, -1.9f, 0.25f, -2.73f, 0.69f);
        pathBuilderA3.lineTo(12.0f, 9.0f);
        pathBuilderA3.horizontalLineToRelative(3.0f);
        pathBuilderA3.lineToRelative(-3.0f, 10.0f);
        pathBuilderA3.lineToRelative(1.0f, -9.0f);
        pathBuilderA3.horizontalLineToRelative(-3.0f);
        pathBuilderA3.lineToRelative(1.54f, -5.39f);
        pathBuilderA3.curveTo(10.47f, 3.61f, 9.01f, 3.0f, 7.5f, 3.0f);
        pathBuilderA3.curveTo(4.42f, 3.0f, 2.0f, 5.42f, 2.0f, 8.5f);
        pathBuilderA3.curveToRelative(0.0f, 4.13f, 4.16f, 7.18f, 10.0f, 12.5f);
        pathBuilderA3.curveToRelative(5.47f, -4.94f, 10.0f, -8.26f, 10.0f, -12.5f);
        pathBuilderA3.curveTo(22.0f, 5.42f, 19.58f, 3.0f, 16.5f, 3.0f);
        pathBuilderA3.close();
        pathBuilderA3.moveTo(10.24f, 16.73f);
        pathBuilderA3.curveTo(6.45f, 13.34f, 4.0f, 11.0f, 4.0f, 8.5f);
        pathBuilderA3.curveTo(4.0f, 6.54f, 5.54f, 5.0f, 7.5f, 5.0f);
        pathBuilderA3.curveToRelative(0.59f, 0.0f, 1.19f, 0.15f, 1.73f, 0.42f);
        pathBuilderA3.lineTo(7.35f, 12.0f);
        d.q(pathBuilderA3, 3.42f, 10.24f, 16.73f);
        pathBuilderA3.moveTo(15.13f, 15.53f);
        pathBuilderA3.lineTo(17.69f, 7.0f);
        pathBuilderA3.horizontalLineToRelative(-2.91f);
        pathBuilderA3.lineToRelative(0.61f, -1.82f);
        pathBuilderA3.curveTo(15.75f, 5.06f, 16.13f, 5.0f, 16.5f, 5.0f);
        pathBuilderA3.curveTo(18.46f, 5.0f, 20.0f, 6.54f, 20.0f, 8.5f);
        pathBuilderA3.curveTo(20.0f, 10.71f, 17.98f, 12.93f, 15.13f, 15.53f);
        pathBuilderA3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _heartBroken = imageVectorBuild;
        return imageVectorBuild;
    }
}
