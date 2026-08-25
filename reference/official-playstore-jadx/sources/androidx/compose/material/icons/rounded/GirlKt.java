package androidx.compose.material.icons.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_girl", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Girl", "Landroidx/compose/material/icons/Icons$Rounded;", "getGirl", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GirlKt {
    private static ImageVector _girl;

    public static final ImageVector getGirl(Icons.Rounded rounded) {
        ImageVector imageVector = _girl;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Girl", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 7.5f);
        pathBuilderA.curveToRelative(0.97f, 0.0f, 1.75f, -0.78f, 1.75f, -1.75f);
        pathBuilderA.reflectiveCurveTo(12.97f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.75f, 0.78f, -1.75f, 1.75f);
        pathBuilderA.reflectiveCurveTo(11.03f, 7.5f, 12.0f, 7.5f);
        d.k(pathBuilderA, 14.0f, 16.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.horizontalLineTo(9.44f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -1.18f, -0.7f, -0.94f, -1.35f);
        pathBuilderA.lineToRelative(1.88f, -5.03f);
        pathBuilderA.curveTo(10.63f, 8.95f, 11.28f, 8.5f, 12.0f, 8.5f);
        pathBuilderA.reflectiveCurveToRelative(1.37f, 0.45f, 1.62f, 1.12f);
        pathBuilderA.lineToRelative(1.88f, 5.03f);
        pathBuilderA.curveTo(15.74f, 15.3f, 15.26f, 16.0f, 14.56f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _girl = imageVectorBuild;
        return imageVectorBuild;
    }
}
