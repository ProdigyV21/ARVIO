package androidx.compose.material.icons.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cookie", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cookie", "Landroidx/compose/material/icons/Icons$Sharp;", "getCookie", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CookieKt {
    private static ImageVector _cookie;

    public static final ImageVector getCookie(Icons.Sharp sharp) {
        ImageVector imageVector = _cookie;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Cookie", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.95f, 10.99f);
        pathBuilderA.curveToRelative(-1.79f, -0.03f, -3.7f, -1.95f, -2.68f, -4.22f);
        pathBuilderA.curveToRelative(-2.98f, 1.0f, -5.77f, -1.59f, -5.19f, -4.56f);
        pathBuilderA.curveTo(6.95f, 0.71f, 2.0f, 6.58f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.curveTo(17.89f, 22.0f, 22.54f, 16.92f, 21.95f, 10.99f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.5f, 15.0f);
        pathBuilderA.curveTo(7.67f, 15.0f, 7.0f, 14.33f, 7.0f, 13.5f);
        pathBuilderA.reflectiveCurveTo(7.67f, 12.0f, 8.5f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(9.33f, 15.0f, 8.5f, 15.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.5f, 10.0f);
        pathBuilderA.curveTo(9.67f, 10.0f, 9.0f, 9.33f, 9.0f, 8.5f);
        pathBuilderA.reflectiveCurveTo(9.67f, 7.0f, 10.5f, 7.0f);
        pathBuilderA.reflectiveCurveTo(12.0f, 7.67f, 12.0f, 8.5f);
        pathBuilderA.reflectiveCurveTo(11.33f, 10.0f, 10.5f, 10.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 16.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveTo(16.0f, 15.55f, 15.55f, 16.0f, 15.0f, 16.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cookie = imageVectorBuild;
        return imageVectorBuild;
    }
}
