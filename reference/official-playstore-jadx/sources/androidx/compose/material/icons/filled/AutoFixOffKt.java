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
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoFixOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoFixOff", "Landroidx/compose/material/icons/Icons$Filled;", "getAutoFixOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoFixOffKt {
    private static ImageVector _autoFixOff;

    public static final ImageVector getAutoFixOff(Icons.Filled filled) {
        ImageVector imageVector = _autoFixOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AutoFixOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(23.0f, 1.0f, -2.5f, 1.4f);
        pathBuilderR.lineTo(18.0f, 1.0f);
        pathBuilderR.lineToRelative(1.4f, 2.5f);
        pathBuilderR.lineTo(18.0f, 6.0f);
        pathBuilderR.lineToRelative(2.5f, -1.4f);
        pathBuilderR.lineTo(23.0f, 6.0f);
        d.C(pathBuilderR, -1.4f, -2.5f, 23.0f, 1.0f);
        pathBuilderR.moveTo(14.66f, 7.22f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.lineToRelative(-2.44f, 2.44f);
        pathBuilderR.lineToRelative(0.81f, 0.81f);
        pathBuilderR.lineToRelative(2.55f, -2.55f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineToRelative(-2.34f, -2.34f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR.lineTo(11.4f, 8.84f);
        b.D(pathBuilderR, 0.81f, 0.81f, 2.45f, -2.43f);
        pathBuilderR.moveTo(13.88f, 13.87f);
        pathBuilderR.lineToRelative(-3.75f, -3.75f);
        pathBuilderR.lineToRelative(-6.86f, -6.86f);
        pathBuilderR.lineTo(2.0f, 4.53f);
        pathBuilderR.lineToRelative(6.86f, 6.86f);
        pathBuilderR.lineToRelative(-6.57f, 6.57f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineToRelative(2.34f, 2.34f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(6.57f, -6.57f);
        pathBuilderR.lineTo(19.47f, 22.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 1.27f, -1.27f, -6.86f, -6.86f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autoFixOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
