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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_unpublished", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Unpublished", "Landroidx/compose/material/icons/Icons$Filled;", "getUnpublished", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnpublishedKt {
    private static ImageVector _unpublished;

    public static final ImageVector getUnpublished(Icons.Filled filled) {
        ImageVector imageVector = _unpublished;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Unpublished", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineToRelative(2.27f, 2.27f);
        pathBuilderJ.curveTo(2.61f, 8.07f, 2.0f, 9.96f, 2.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(2.04f, 0.0f, 3.93f, -0.61f, 5.51f, -1.66f);
        d.C(pathBuilderJ, 2.27f, 2.27f, 21.19f, 21.19f);
        pathBuilderJ.moveTo(10.59f, 16.6f);
        pathBuilderJ.lineToRelative(-4.24f, -4.24f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        pathBuilderJ.lineToRelative(2.83f, 2.83f);
        pathBuilderJ.lineToRelative(0.18f, -0.18f);
        d.C(pathBuilderJ, 1.41f, 1.41f, 10.59f, 16.6f);
        pathBuilderJ.moveTo(13.59f, 10.76f);
        pathBuilderJ.lineToRelative(-7.1f, -7.1f);
        pathBuilderJ.curveTo(8.07f, 2.61f, 9.96f, 2.0f, 12.0f, 2.0f);
        pathBuilderJ.curveToRelative(5.52f, 0.0f, 10.0f, 4.48f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.04f, -0.61f, 3.93f, -1.66f, 5.51f);
        pathBuilderJ.lineToRelative(-5.34f, -5.34f);
        pathBuilderJ.lineToRelative(2.65f, -2.65f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, -1.41f, -1.41f, 13.59f, 10.76f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _unpublished = imageVectorBuild;
        return imageVectorBuild;
    }
}
