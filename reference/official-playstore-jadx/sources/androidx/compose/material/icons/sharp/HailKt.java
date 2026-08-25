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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hail", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hail", "Landroidx/compose/material/icons/Icons$Sharp;", "getHail", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HailKt {
    private static ImageVector _hail;

    public static final ImageVector getHail(Icons.Sharp sharp) {
        ImageVector imageVector = _hail;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Hail", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(13.1f, 6.0f, 12.0f, 6.0f);
        a.g(pathBuilderA, 17.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 2.7f, -0.93f, 4.41f, -2.3f, 5.5f);
        pathBuilderA.curveToRelative(-0.5f, 0.4f, -1.1f, 0.7f, -1.7f, 0.9f);
        b.r(pathBuilderA, 22.0f, -2.0f, -6.0f, -2.0f);
        w.a.y(pathBuilderA, 6.0f, 9.0f, 10.1f);
        pathBuilderA.curveToRelative(-0.3f, 0.1f, -0.5f, 0.2f, -0.6f, 0.3f);
        pathBuilderA.curveTo(7.87f, 10.81f, 7.0f, 11.43f, 7.0f, 14.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(0.0f, -2.06f, 0.35f, -3.78f, 2.11f, -5.29f);
        pathBuilderA.curveTo(8.21f, 7.81f, 10.0f, 7.0f, 12.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(2.68f, -0.46f, 3.48f, -1.06f);
        pathBuilderA.curveTo(15.96f, 5.55f, 17.0f, 4.76f, 17.0f, 2.0f);
        b.w(pathBuilderA, 4.0f, 16.0f, 3.0f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderA, 4.0f, 16.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hail = imageVectorBuild;
        return imageVectorBuild;
    }
}
