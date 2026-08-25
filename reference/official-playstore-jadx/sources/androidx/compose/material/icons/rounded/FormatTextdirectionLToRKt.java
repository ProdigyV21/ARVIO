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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_formatTextdirectionLToR", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatTextdirectionLToR", "Landroidx/compose/material/icons/Icons$Rounded;", "getFormatTextdirectionLToR$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getFormatTextdirectionLToR", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatTextdirectionLToRKt {
    private static ImageVector _formatTextdirectionLToR;

    public static final ImageVector getFormatTextdirectionLToR(Icons.Rounded rounded) {
        ImageVector imageVector = _formatTextdirectionLToR;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FormatTextdirectionLToR", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(9.0f, 10.0f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        b.A(pathBuilderI, 11.0f, 4.0f, 2.0f, 10.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.lineTo(15.0f, 4.0f);
        pathBuilderI.horizontalLineToRelative(1.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.lineTo(9.17f, 2.0f);
        pathBuilderI.curveTo(7.08f, 2.0f, 5.22f, 3.53f, 5.02f, 5.61f);
        pathBuilderI.curveTo(4.79f, 7.99f, 6.66f, 10.0f, 9.0f, 10.0f);
        a.z(pathBuilderI, 20.65f, 17.65f, -2.79f, -2.79f);
        pathBuilderI.curveToRelative(-0.32f, -0.32f, -0.86f, -0.1f, -0.86f, 0.35f);
        pathBuilderI.lineTo(17.0f, 17.0f);
        pathBuilderI.lineTo(6.0f, 17.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(11.0f);
        pathBuilderI.verticalLineToRelative(1.79f);
        pathBuilderI.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilderI.lineToRelative(2.79f, -2.79f);
        pathBuilderI.curveToRelative(0.2f, -0.19f, 0.2f, -0.51f, 0.01f, -0.7f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatTextdirectionLToR = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getFormatTextdirectionLToR$annotations(Icons.Rounded rounded) {
    }
}
