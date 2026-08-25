package androidx.compose.material.icons.filled;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_interests", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Interests", "Landroidx/compose/material/icons/Icons$Filled;", "getInterests", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InterestsKt {
    private static ImageVector _interests;

    public static final ImageVector getInterests(Icons.Filled filled) {
        ImageVector imageVector = _interests;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Interests", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.02f, 13.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveTo(9.23f, 13.0f, 7.02f, 13.0f);
        c.y(pathBuilderA, 13.0f, 13.0f, 8.0f, 8.0f);
        e.y(pathBuilderA, -8.0f, 13.0f, 7.0f, 2.0f);
        pathBuilderA.lineToRelative(-5.0f, 9.0f);
        d.q(pathBuilderA, 10.0f, 7.0f, 2.0f);
        pathBuilderA.moveTo(19.25f, 2.5f);
        pathBuilderA.curveToRelative(-1.06f, 0.0f, -1.81f, 0.56f, -2.25f, 1.17f);
        pathBuilderA.curveToRelative(-0.44f, -0.61f, -1.19f, -1.17f, -2.25f, -1.17f);
        pathBuilderA.curveTo(13.19f, 2.5f, 12.0f, 3.78f, 12.0f, 5.25f);
        pathBuilderA.curveToRelative(0.0f, 2.0f, 2.42f, 3.42f, 5.0f, 5.75f);
        pathBuilderA.curveToRelative(2.58f, -2.33f, 5.0f, -3.75f, 5.0f, -5.75f);
        pathBuilderA.curveTo(22.0f, 3.78f, 20.81f, 2.5f, 19.25f, 2.5f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _interests = imageVectorBuild;
        return imageVectorBuild;
    }
}
