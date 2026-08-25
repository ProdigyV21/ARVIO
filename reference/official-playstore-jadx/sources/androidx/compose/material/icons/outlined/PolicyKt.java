package androidx.compose.material.icons.outlined;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_policy", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Policy", "Landroidx/compose/material/icons/Icons$Outlined;", "getPolicy", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PolicyKt {
    private static ImageVector _policy;

    public static final ImageVector getPolicy(Icons.Outlined outlined) {
        ImageVector imageVector = _policy;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Policy", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(12.0f, 1.0f, 3.0f, 5.0f, 6.0f);
        pathBuilderS.curveToRelative(0.0f, 5.55f, 3.84f, 10.74f, 9.0f, 12.0f);
        pathBuilderS.curveToRelative(5.16f, -1.26f, 9.0f, -6.45f, 9.0f, -12.0f);
        c.x(pathBuilderS, 5.0f, 12.0f, 1.0f);
        pathBuilderS.moveTo(19.0f, 11.0f);
        pathBuilderS.curveToRelative(0.0f, 1.85f, -0.51f, 3.65f, -1.38f, 5.21f);
        pathBuilderS.lineToRelative(-1.45f, -1.45f);
        pathBuilderS.curveToRelative(1.29f, -1.94f, 1.07f, -4.58f, -0.64f, -6.29f);
        pathBuilderS.curveToRelative(-1.95f, -1.95f, -5.12f, -1.95f, -7.07f, 0.0f);
        pathBuilderS.curveToRelative(-1.95f, 1.95f, -1.95f, 5.12f, 0.0f, 7.07f);
        pathBuilderS.curveToRelative(1.71f, 1.71f, 4.35f, 1.92f, 6.29f, 0.64f);
        pathBuilderS.lineToRelative(1.72f, 1.72f);
        pathBuilderS.curveToRelative(-1.19f, 1.42f, -2.73f, 2.51f, -4.47f, 3.04f);
        pathBuilderS.curveTo(7.98f, 19.69f, 5.0f, 15.52f, 5.0f, 11.0f);
        pathBuilderS.verticalLineTo(6.3f);
        pathBuilderS.lineToRelative(7.0f, -3.11f);
        c.e(pathBuilderS, 7.0f, 3.11f, 11.0f);
        pathBuilderS.moveTo(12.0f, 15.0f);
        pathBuilderS.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderS.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderS.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderS, 13.66f, 15.0f, 12.0f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _policy = imageVectorBuild;
        return imageVectorBuild;
    }
}
