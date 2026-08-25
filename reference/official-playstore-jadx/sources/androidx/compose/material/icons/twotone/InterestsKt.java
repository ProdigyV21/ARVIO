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
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_interests", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Interests", "Landroidx/compose/material/icons/Icons$TwoTone;", "getInterests", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InterestsKt {
    private static ImageVector _interests;

    public static final ImageVector getInterests(Icons.TwoTone twoTone) {
        ImageVector imageVector = _interests;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Interests", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.02f, 19.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(8.12f, 19.0f, 7.02f, 19.0f);
        b.w(pathBuilderA, 19.0f, 19.0f, -4.0f, -4.0f);
        c.D(pathBuilderA, 4.0f, 19.0f, 7.0f, 6.12f);
        pathBuilderA.lineTo(8.6f, 9.0f);
        a.w(pathBuilderA, 5.4f, 7.0f, 6.12f);
        pathBuilderA.moveTo(17.0f, 8.35f);
        pathBuilderA.curveToRelative(-1.45f, -1.22f, -3.0f, -2.4f, -3.0f, -3.1f);
        pathBuilderA.curveToRelative(0.0f, -0.43f, 0.35f, -0.75f, 0.75f, -0.75f);
        pathBuilderA.curveToRelative(0.31f, 0.0f, 0.52f, 0.17f, 0.73f, 0.37f);
        pathBuilderA.lineTo(17.0f, 6.3f);
        pathBuilderA.lineToRelative(1.52f, -1.43f);
        pathBuilderA.curveToRelative(0.21f, -0.2f, 0.42f, -0.37f, 0.73f, -0.37f);
        pathBuilderA.curveToRelative(0.4f, 0.0f, 0.75f, 0.32f, 0.75f, 0.75f);
        pathBuilderA.curveTo(20.0f, 5.95f, 18.45f, 7.13f, 17.0f, 8.35f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(7.02f, 13.0f);
        pathBuilderA2.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA2.reflectiveCurveTo(9.23f, 13.0f, 7.02f, 13.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(7.02f, 19.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveTo(8.12f, 19.0f, 7.02f, 19.0f);
        c.y(pathBuilderA2, 13.0f, 13.0f, 8.0f, 8.0f);
        e.y(pathBuilderA2, -8.0f, 13.0f, 19.0f, 19.0f);
        f.w(pathBuilderA2, -4.0f, -4.0f, 4.0f, 19.0f);
        pathBuilderA2.moveTo(7.0f, 2.0f);
        pathBuilderA2.lineToRelative(-5.0f, 9.0f);
        d.q(pathBuilderA2, 10.0f, 7.0f, 2.0f);
        pathBuilderA2.moveTo(7.0f, 6.12f);
        pathBuilderA2.lineTo(8.6f, 9.0f);
        a.w(pathBuilderA2, 5.4f, 7.0f, 6.12f);
        pathBuilderA2.moveTo(19.25f, 2.5f);
        pathBuilderA2.curveToRelative(-1.06f, 0.0f, -1.81f, 0.56f, -2.25f, 1.17f);
        pathBuilderA2.curveToRelative(-0.44f, -0.61f, -1.19f, -1.17f, -2.25f, -1.17f);
        pathBuilderA2.curveTo(13.19f, 2.5f, 12.0f, 3.78f, 12.0f, 5.25f);
        pathBuilderA2.curveToRelative(0.0f, 2.0f, 2.42f, 3.42f, 5.0f, 5.75f);
        pathBuilderA2.curveToRelative(2.58f, -2.33f, 5.0f, -3.75f, 5.0f, -5.75f);
        pathBuilderA2.curveTo(22.0f, 3.78f, 20.81f, 2.5f, 19.25f, 2.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(17.0f, 8.35f);
        pathBuilderA2.curveToRelative(-1.45f, -1.22f, -3.0f, -2.4f, -3.0f, -3.1f);
        pathBuilderA2.curveToRelative(0.0f, -0.43f, 0.35f, -0.75f, 0.75f, -0.75f);
        pathBuilderA2.curveToRelative(0.31f, 0.0f, 0.52f, 0.17f, 0.73f, 0.37f);
        pathBuilderA2.lineTo(17.0f, 6.3f);
        pathBuilderA2.lineToRelative(1.52f, -1.43f);
        pathBuilderA2.curveToRelative(0.21f, -0.2f, 0.42f, -0.37f, 0.73f, -0.37f);
        pathBuilderA2.curveToRelative(0.4f, 0.0f, 0.75f, 0.32f, 0.75f, 0.75f);
        pathBuilderA2.curveTo(20.0f, 5.95f, 18.45f, 7.13f, 17.0f, 8.35f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _interests = imageVectorBuild;
        return imageVectorBuild;
    }
}
