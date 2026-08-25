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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_badge", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Badge", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBadge", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BadgeKt {
    private static ImageVector _badge;

    public static final ImageVector getBadge(Icons.TwoTone twoTone) {
        ImageVector imageVector = _badge;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Badge", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(14.0f, 13.5f, 4.0f, 12.0f, -4.0f);
        v.a.D(pathBuilderU, 13.5f, 14.0f, 16.5f, 4.0f);
        f.k(pathBuilderU, 15.0f, -4.0f, 16.5f);
        e.m(pathBuilderU, 20.0f, 7.0f, -5.0f, 4.0f);
        pathBuilderU.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderU.horizontalLineToRelative(-2.0f);
        pathBuilderU.curveTo(9.9f, 2.0f, 9.0f, 2.9f, 9.0f, 4.0f);
        pathBuilderU.verticalLineToRelative(3.0f);
        pathBuilderU.horizontalLineTo(4.0f);
        pathBuilderU.curveTo(2.9f, 7.0f, 2.0f, 7.9f, 2.0f, 9.0f);
        pathBuilderU.verticalLineToRelative(11.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(16.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.verticalLineTo(9.0f);
        pathBuilderU.curveTo(22.0f, 7.9f, 21.1f, 7.0f, 20.0f, 7.0f);
        b.w(pathBuilderU, 11.0f, 4.0f, 2.0f, 5.0f);
        c.D(pathBuilderU, -2.0f, 4.0f, 20.0f, 20.0f);
        a0.b.u(pathBuilderU, 4.0f, 9.0f, 5.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(2.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        c.D(pathBuilderU, 5.0f, 20.0f, 9.0f, 15.0f);
        pathBuilderU.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderU.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderU.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderU.curveTo(7.5f, 14.33f, 8.17f, 15.0f, 9.0f, 15.0f);
        pathBuilderU.close();
        pathBuilderU.moveTo(11.08f, 16.18f);
        pathBuilderU.curveTo(10.44f, 15.9f, 9.74f, 15.75f, 9.0f, 15.75f);
        pathBuilderU.reflectiveCurveToRelative(-1.44f, 0.15f, -2.08f, 0.43f);
        pathBuilderU.curveTo(6.36f, 16.42f, 6.0f, 16.96f, 6.0f, 17.57f);
        w.a.j(pathBuilderU, 18.0f, 6.0f, -0.43f);
        pathBuilderU.curveTo(12.0f, 16.96f, 11.64f, 16.42f, 11.08f, 16.18f);
        pathBuilderU.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderU.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = v.a.a(13.0f, 11.0f, -2.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        a.e(pathBuilderA, 4.0f, 11.0f, 16.0f, 9.0f);
        pathBuilderA.horizontalLineToRelative(-5.0f);
        pathBuilderA.curveTo(15.0f, 10.1f, 14.1f, 11.0f, 13.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 12.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.curveTo(7.5f, 12.67f, 8.17f, 12.0f, 9.0f, 12.0f);
        c.C(pathBuilderA, 12.0f, 18.0f, 6.0f, -0.43f);
        pathBuilderA.curveToRelative(0.0f, -0.6f, 0.36f, -1.15f, 0.92f, -1.39f);
        pathBuilderA.curveTo(7.56f, 15.9f, 8.26f, 15.75f, 9.0f, 15.75f);
        pathBuilderA.reflectiveCurveToRelative(1.44f, 0.15f, 2.08f, 0.43f);
        pathBuilderA.curveToRelative(0.55f, 0.24f, 0.92f, 0.78f, 0.92f, 1.39f);
        v.a.D(pathBuilderA, 18.0f, 18.0f, 16.5f, -4.0f);
        f.k(pathBuilderA, 15.0f, 4.0f, 16.5f);
        e.m(pathBuilderA, 18.0f, 13.5f, -4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderA, 4.0f, 13.5f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _badge = imageVectorBuild;
        return imageVectorBuild;
    }
}
