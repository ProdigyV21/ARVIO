package androidx.compose.material.icons.automirrored.outlined;

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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatTextdirectionLToR", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatTextdirectionLToR", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getFormatTextdirectionLToR", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatTextdirectionLToRKt {
    private static ImageVector _formatTextdirectionLToR;

    public static final ImageVector getFormatTextdirectionLToR(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _formatTextdirectionLToR;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.FormatTextdirectionLToR", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(9.0f, 4.0f, 4.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderI.moveToRelative(8.0f, -2.0f);
        pathBuilderI.lineTo(9.0f, 2.0f);
        pathBuilderI.curveTo(6.79f, 2.0f, 5.0f, 3.79f, 5.0f, 6.0f);
        pathBuilderI.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        c.w(pathBuilderI, 5.0f, 2.0f, 11.0f, 4.0f);
        e.g(pathBuilderI, 2.0f, 11.0f, 2.0f);
        pathBuilderI.lineTo(15.0f, 4.0f);
        d.q(pathBuilderI, 2.0f, 17.0f, 2.0f);
        pathBuilderI.moveTo(17.0f, 14.0f);
        d.r(pathBuilderI, 3.0f, 5.0f, 17.0f, 2.0f);
        f.y(pathBuilderI, 12.0f, 3.0f, 4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderI, -4.0f, -4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatTextdirectionLToR = imageVectorBuild;
        return imageVectorBuild;
    }
}
