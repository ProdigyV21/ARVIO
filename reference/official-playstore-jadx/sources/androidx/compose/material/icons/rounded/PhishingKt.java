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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phishing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Phishing", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhishing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhishingKt {
    private static ImageVector _phishing;

    public static final ImageVector getPhishing(Icons.Rounded rounded) {
        ImageVector imageVector = _phishing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Phishing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -1.3f, -0.84f, -2.4f, -2.0f, -2.82f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.18f);
        pathBuilderA.curveTo(13.84f, 6.6f, 13.0f, 7.7f, 13.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(0.84f, 2.4f, 2.0f, 2.82f);
        pathBuilderA.lineToRelative(0.0f, 3.01f);
        pathBuilderA.curveToRelative(0.0f, 2.09f, -1.52f, 3.96f, -3.6f, 4.16f);
        pathBuilderA.curveTo(9.02f, 19.21f, 7.0f, 17.34f, 7.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.horizontalLineToRelative(1.79f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderA.lineTo(5.85f, 9.85f);
        pathBuilderA.curveTo(5.54f, 9.54f, 5.0f, 9.76f, 5.0f, 10.21f);
        pathBuilderA.lineToRelative(0.0f, 4.58f);
        pathBuilderA.curveToRelative(0.0f, 3.05f, 2.19f, 5.77f, 5.21f, 6.16f);
        pathBuilderA.curveTo(13.87f, 21.42f, 17.0f, 18.57f, 17.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(-3.18f);
        pathBuilderA.curveTo(18.16f, 11.4f, 19.0f, 10.3f, 19.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 10.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 16.55f, 10.0f, 16.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phishing = imageVectorBuild;
        return imageVectorBuild;
    }
}
