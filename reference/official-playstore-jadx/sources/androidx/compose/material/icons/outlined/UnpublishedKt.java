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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_unpublished", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Unpublished", "Landroidx/compose/material/icons/Icons$Outlined;", "getUnpublished", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnpublishedKt {
    private static ImageVector _unpublished;

    public static final ImageVector getUnpublished(Icons.Outlined outlined) {
        ImageVector imageVector = _unpublished;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Unpublished", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.94f, 5.12f, 6.49f, 3.66f);
        pathBuilderJ.curveTo(8.07f, 2.61f, 9.96f, 2.0f, 12.0f, 2.0f);
        pathBuilderJ.curveToRelative(5.52f, 0.0f, 10.0f, 4.48f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.04f, -0.61f, 3.93f, -1.66f, 5.51f);
        pathBuilderJ.lineToRelative(-1.46f, -1.46f);
        pathBuilderJ.curveTo(19.59f, 14.86f, 20.0f, 13.48f, 20.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderJ.curveTo(10.52f, 4.0f, 9.14f, 4.41f, 7.94f, 5.12f);
        a.z(pathBuilderJ, 17.66f, 9.53f, -1.41f, -1.41f);
        pathBuilderJ.lineToRelative(-2.65f, 2.65f);
        d.C(pathBuilderJ, 1.41f, 1.41f, 17.66f, 9.53f);
        pathBuilderJ.moveTo(19.78f, 22.61f);
        pathBuilderJ.lineToRelative(-2.27f, -2.27f);
        pathBuilderJ.curveTo(15.93f, 21.39f, 14.04f, 22.0f, 12.0f, 22.0f);
        pathBuilderJ.curveTo(6.48f, 22.0f, 2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.04f, 0.61f, -3.93f, 1.66f, -5.51f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderJ, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderJ.moveTo(16.06f, 18.88f);
        pathBuilderJ.lineToRelative(-3.88f, -3.88f);
        pathBuilderJ.lineToRelative(-1.59f, 1.59f);
        pathBuilderJ.lineToRelative(-4.24f, -4.24f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        pathBuilderJ.lineToRelative(2.83f, 2.83f);
        pathBuilderJ.lineToRelative(0.18f, -0.18f);
        pathBuilderJ.lineTo(5.12f, 7.94f);
        pathBuilderJ.curveTo(4.41f, 9.14f, 4.0f, 10.52f, 4.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderJ.curveTo(13.48f, 20.0f, 14.86f, 19.59f, 16.06f, 18.88f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _unpublished = imageVectorBuild;
        return imageVectorBuild;
    }
}
