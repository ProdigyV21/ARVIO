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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_unarchive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Unarchive", "Landroidx/compose/material/icons/Icons$Rounded;", "getUnarchive", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnarchiveKt {
    private static ImageVector _unarchive;

    public static final ImageVector getUnarchive(Icons.Rounded rounded) {
        ImageVector imageVector = _unarchive;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Unarchive", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.55f, 5.22f, -1.39f, -1.68f);
        pathBuilderR.curveTo(18.88f, 3.21f, 18.47f, 3.0f, 18.0f, 3.0f);
        pathBuilderR.lineTo(6.0f, 3.0f);
        pathBuilderR.curveToRelative(-0.47f, 0.0f, -0.88f, 0.21f, -1.15f, 0.55f);
        pathBuilderR.lineTo(3.46f, 5.22f);
        pathBuilderR.curveTo(3.17f, 5.57f, 3.0f, 6.01f, 3.0f, 6.5f);
        pathBuilderR.lineTo(3.0f, 19.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.lineTo(21.0f, 6.5f);
        pathBuilderR.curveToRelative(0.0f, -0.49f, -0.17f, -0.93f, -0.45f, -1.28f);
        a.p(pathBuilderR, 12.35f, 9.85f, 17.5f, 15.0f);
        a.h(pathBuilderR, 14.0f, 15.0f, 2.0f, -4.0f);
        pathBuilderR.verticalLineToRelative(-2.0f);
        pathBuilderR.lineTo(6.5f, 15.0f);
        pathBuilderR.lineToRelative(5.15f, -5.15f);
        pathBuilderR.curveToRelative(0.19f, -0.19f, 0.51f, -0.19f, 0.7f, 0.0f);
        a.z(pathBuilderR, 5.12f, 5.0f, 0.82f, -1.0f);
        pathBuilderR.horizontalLineToRelative(12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 0.93f, 1.0f, 5.12f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _unarchive = imageVectorBuild;
        return imageVectorBuild;
    }
}
