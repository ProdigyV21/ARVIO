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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_recentActors", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RecentActors", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRecentActors", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RecentActorsKt {
    private static ImageVector _recentActors;

    public static final ImageVector getRecentActors(Icons.TwoTone twoTone) {
        ImageVector imageVector = _recentActors;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.RecentActors", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(13.0f, 7.0f, 3.0f, 7.0f, 10.0f);
        d.q(pathBuilderS, 10.0f, 13.0f, 7.0f);
        pathBuilderS.moveTo(8.0f, 8.0f);
        pathBuilderS.curveToRelative(1.07f, 0.0f, 1.95f, 0.87f, 1.95f, 1.95f);
        pathBuilderS.curveToRelative(0.0f, 1.07f, -0.87f, 1.95f, -1.95f, 1.95f);
        pathBuilderS.reflectiveCurveToRelative(-1.95f, -0.87f, -1.95f, -1.95f);
        pathBuilderS.reflectiveCurveTo(6.93f, 8.0f, 8.0f, 8.0f);
        a.p(pathBuilderS, 11.89f, 16.0f, 4.11f, 16.0f);
        pathBuilderS.verticalLineToRelative(-0.65f);
        pathBuilderS.curveToRelative(0.0f, -1.3f, 2.59f, -1.95f, 3.89f, -1.95f);
        pathBuilderS.reflectiveCurveToRelative(3.89f, 0.65f, 3.89f, 1.95f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 11.89f, 16.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(21.0f, 5.0f, 2.0f, 14.0f, -2.0f);
        a.C(pathBuilderB, 17.0f, 5.0f, 2.0f, 14.0f);
        f.x(pathBuilderB, -2.0f, 14.0f, 19.0f);
        pathBuilderB.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderB.lineTo(15.0f, 6.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderB.lineTo(2.0f, 5.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderB.verticalLineToRelative(12.0f);
        pathBuilderB.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        e.r(pathBuilderB, 12.0f, 3.0f, 7.0f, 10.0f);
        pathBuilderB.verticalLineToRelative(10.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderB, 3.0f, 17.0f, 3.0f, 7.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = a.b(8.0f, 9.94f, -1.95f, 0.0f);
        pathBuilderB2.arcToRelative(1.95f, 1.95f, 0.0f, true, true, 3.9f, 0.0f);
        pathBuilderB2.arcToRelative(1.95f, 1.95f, 0.0f, true, true, -3.9f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.0f, 13.4f);
        pathBuilderA.curveToRelative(-1.3f, 0.0f, -3.89f, 0.65f, -3.89f, 1.95f);
        w.a.j(pathBuilderA, 16.0f, 7.78f, -0.65f);
        pathBuilderA.curveToRelative(0.0f, -1.3f, -2.59f, -1.95f, -3.89f, -1.95f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _recentActors = imageVectorBuild;
        return imageVectorBuild;
    }
}
