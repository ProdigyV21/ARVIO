package androidx.compose.material.icons.automirrored.sharp;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nextPlan", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NextPlan", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getNextPlan", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NextPlanKt {
    private static ImageVector _nextPlan;

    public static final ImageVector getNextPlan(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _nextPlan;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.NextPlan", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        a.g(pathBuilderA, 18.0f, 13.97f, -5.0f);
        pathBuilderA.lineToRelative(2.26f, -2.26f);
        pathBuilderA.curveToRelative(-0.91f, -1.06f, -2.25f, -1.74f, -3.76f, -1.74f);
        pathBuilderA.curveToRelative(-2.37f, 0.0f, -4.35f, 1.66f, -4.86f, 3.88f);
        pathBuilderA.lineToRelative(-0.96f, -0.32f);
        pathBuilderA.curveToRelative(0.64f, -2.62f, 3.0f, -4.56f, 5.82f, -4.56f);
        pathBuilderA.curveToRelative(1.78f, 0.0f, 3.37f, 0.79f, 4.47f, 2.03f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderA, 18.0f, 8.97f, 13.97f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nextPlan = imageVectorBuild;
        return imageVectorBuild;
    }
}
