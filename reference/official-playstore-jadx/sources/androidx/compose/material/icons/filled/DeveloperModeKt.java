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
import v.a;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_developerMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DeveloperMode", "Landroidx/compose/material/icons/Icons$Filled;", "getDeveloperMode", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeveloperModeKt {
    private static ImageVector _developerMode;

    public static final ImageVector getDeveloperMode(Icons.Filled filled) {
        ImageVector imageVector = _developerMode;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DeveloperMode", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(7.0f, 5.0f, 10.0f, 2.0f, 2.0f);
        pathBuilderK.lineTo(19.0f, 3.0f);
        pathBuilderK.curveToRelative(0.0f, -1.1f, -0.9f, -1.99f, -2.0f, -1.99f);
        pathBuilderK.lineTo(7.0f, 1.0f);
        pathBuilderK.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        w.a.z(pathBuilderK, 4.0f, 2.0f, 7.0f, 5.0f);
        pathBuilderK.moveTo(15.41f, 16.59f);
        pathBuilderK.lineTo(20.0f, 12.0f);
        pathBuilderK.lineToRelative(-4.59f, -4.59f);
        pathBuilderK.lineTo(14.0f, 8.83f);
        pathBuilderK.lineTo(17.17f, 12.0f);
        d.l(pathBuilderK, 14.0f, 15.17f, 1.41f, 1.42f);
        pathBuilderK.moveTo(10.0f, 15.17f);
        pathBuilderK.lineTo(6.83f, 12.0f);
        pathBuilderK.lineTo(10.0f, 8.83f);
        pathBuilderK.lineTo(8.59f, 7.41f);
        pathBuilderK.lineTo(4.0f, 12.0f);
        d.C(pathBuilderK, 4.59f, 4.59f, 10.0f, 15.17f);
        pathBuilderK.moveTo(17.0f, 19.0f);
        pathBuilderK.lineTo(7.0f, 19.0f);
        d.r(pathBuilderK, -2.0f, 5.0f, 17.0f, 4.0f);
        pathBuilderK.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderK.horizontalLineToRelative(10.0f);
        pathBuilderK.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.t(pathBuilderK, -4.0f, -2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _developerMode = imageVectorBuild;
        return imageVectorBuild;
    }
}
