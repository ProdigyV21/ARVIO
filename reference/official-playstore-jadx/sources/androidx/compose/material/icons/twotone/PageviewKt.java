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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pageview", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pageview", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPageview", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PageviewKt {
    private static ImageVector _pageview;

    public static final ImageVector getPageview(Icons.TwoTone twoTone) {
        ImageVector imageVector = _pageview;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Pageview", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(4.0f, 18.0f, 16.0f, 20.0f, 6.0f);
        d.f(pathBuilderI, 4.0f, 6.0f, 12.0f);
        pathBuilderI.moveTo(11.5f, 7.0f);
        pathBuilderI.curveToRelative(2.49f, 0.0f, 4.5f, 2.01f, 4.5f, 4.5f);
        pathBuilderI.curveToRelative(0.0f, 0.88f, -0.26f, 1.69f, -0.7f, 2.39f);
        pathBuilderI.lineToRelative(2.44f, 2.43f);
        pathBuilderI.lineToRelative(-1.42f, 1.42f);
        pathBuilderI.lineToRelative(-2.44f, -2.44f);
        pathBuilderI.curveToRelative(-0.69f, 0.44f, -1.51f, 0.7f, -2.39f, 0.7f);
        pathBuilderI.curveTo(9.01f, 16.0f, 7.0f, 13.99f, 7.0f, 11.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderI, 9.01f, 7.0f, 11.5f, 7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.49f, 16.0f);
        pathBuilderA.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilderA.lineToRelative(2.44f, 2.44f);
        pathBuilderA.lineToRelative(1.42f, -1.42f);
        pathBuilderA.lineToRelative(-2.44f, -2.43f);
        pathBuilderA.curveToRelative(0.44f, -0.7f, 0.7f, -1.51f, 0.7f, -2.39f);
        pathBuilderA.curveTo(16.0f, 9.01f, 13.99f, 7.0f, 11.5f, 7.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 9.01f, 7.0f, 11.5f);
        pathBuilderA.reflectiveCurveTo(9.01f, 16.0f, 11.49f, 16.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.5f, 9.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.reflectiveCurveTo(12.88f, 14.0f, 11.5f, 14.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 12.88f, 9.0f, 11.5f);
        pathBuilderA.reflectiveCurveTo(10.12f, 9.0f, 11.5f, 9.0f);
        a.p(pathBuilderA, 20.0f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineTo(22.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderA, 20.0f, 18.0f, 4.0f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderA, 4.0f, 6.0f, 16.0f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pageview = imageVectorBuild;
        return imageVectorBuild;
    }
}
