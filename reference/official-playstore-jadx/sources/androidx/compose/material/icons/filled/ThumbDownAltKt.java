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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbDownAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbDownAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getThumbDownAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbDownAltKt {
    private static ImageVector _thumbDownAlt;

    public static final ImageVector getThumbDownAlt(Icons.Filled filled) {
        ImageVector imageVector = _thumbDownAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ThumbDownAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(22.0f, 4.0f, -2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(9.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        c.D(pathBuilderA, 2.0f, 4.0f, 2.17f, 11.12f);
        pathBuilderA.curveToRelative(-0.11f, 0.25f, -0.17f, 0.52f, -0.17f, 0.8f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(5.5f);
        pathBuilderA.lineToRelative(-0.92f, 4.65f);
        pathBuilderA.curveToRelative(-0.05f, 0.22f, -0.02f, 0.46f, 0.08f, 0.66f);
        pathBuilderA.curveToRelative(0.23f, 0.45f, 0.52f, 0.86f, 0.88f, 1.22f);
        pathBuilderA.lineTo(10.0f, 22.0f);
        pathBuilderA.lineToRelative(6.41f, -6.41f);
        pathBuilderA.curveToRelative(0.38f, -0.38f, 0.59f, -0.89f, 0.59f, -1.42f);
        pathBuilderA.verticalLineTo(6.34f);
        pathBuilderA.curveTo(17.0f, 5.05f, 15.95f, 4.0f, 14.66f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-8.1f);
        pathBuilderA.curveToRelative(-0.71f, 0.0f, -1.36f, 0.37f, -1.72f, 0.97f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, -2.67f, 6.15f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbDownAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
