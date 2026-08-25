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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbDownOffAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbDownOffAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getThumbDownOffAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbDownOffAltKt {
    private static ImageVector _thumbDownOffAlt;

    public static final ImageVector getThumbDownOffAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _thumbDownOffAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ThumbDownOffAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(15.0f, 3.0f, 6.0f);
        pathBuilderQ.curveTo(5.17f, 3.0f, 4.46f, 3.5f, 4.16f, 4.22f);
        pathBuilderQ.lineToRelative(-3.02f, 7.05f);
        pathBuilderQ.curveTo(1.05f, 11.5f, 1.0f, 11.74f, 1.0f, 12.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(6.31f);
        pathBuilderQ.lineToRelative(-0.95f, 4.57f);
        pathBuilderQ.lineToRelative(-0.03f, 0.32f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, 0.17f, 0.79f, 0.44f, 1.06f);
        pathBuilderQ.lineTo(9.83f, 23.0f);
        pathBuilderQ.lineToRelative(6.59f, -6.59f);
        pathBuilderQ.curveTo(16.78f, 16.05f, 17.0f, 15.55f, 17.0f, 15.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(17.0f, 3.9f, 16.1f, 3.0f, 15.0f, 3.0f);
        a.z(pathBuilderQ, 15.0f, 15.0f, -4.34f, 4.34f);
        b.m(pathBuilderQ, 12.0f, 14.0f, 3.0f, -2.0f);
        pathBuilderQ.lineToRelative(3.0f, -7.0f);
        pathBuilderQ.horizontalLineToRelative(9.0f);
        pathBuilderQ.verticalLineTo(15.0f);
        pathBuilderQ.close();
        a.C(pathBuilderQ, 19.0f, 3.0f, 4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderQ, -4.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbDownOffAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
