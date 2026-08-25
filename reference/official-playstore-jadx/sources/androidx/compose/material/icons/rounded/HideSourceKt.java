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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hideSource", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HideSource", "Landroidx/compose/material/icons/Icons$Rounded;", "getHideSource", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HideSourceKt {
    private static ImageVector _hideSource;

    public static final ImageVector getHideSource(Icons.Rounded rounded) {
        ImageVector imageVector = _hideSource;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HideSource", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.1f, 3.51f, 2.1f, 3.51f);
        pathBuilderJ.curveTo(1.71f, 3.9f, 1.71f, 4.54f, 2.1f, 4.93f);
        pathBuilderJ.lineToRelative(1.56f, 1.56f);
        pathBuilderJ.curveToRelative(-1.25f, 1.88f, -1.88f, 4.2f, -1.59f, 6.69f);
        pathBuilderJ.curveToRelative(0.52f, 4.54f, 4.21f, 8.23f, 8.75f, 8.75f);
        pathBuilderJ.curveToRelative(2.49f, 0.29f, 4.81f, -0.34f, 6.69f, -1.59f);
        pathBuilderJ.lineToRelative(1.56f, 1.56f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineTo(3.51f, 3.51f);
        pathBuilderJ.curveTo(3.12f, 3.12f, 2.49f, 3.12f, 2.1f, 3.51f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 20.0f);
        pathBuilderJ.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.48f, 0.41f, -2.86f, 1.12f, -4.06f);
        pathBuilderJ.lineToRelative(10.94f, 10.94f);
        pathBuilderJ.curveTo(14.86f, 19.59f, 13.48f, 20.0f, 12.0f, 20.0f);
        a.p(pathBuilderJ, 7.94f, 5.12f, 6.49f, 3.66f);
        pathBuilderJ.curveTo(8.07f, 2.61f, 9.96f, 2.0f, 12.0f, 2.0f);
        pathBuilderJ.curveToRelative(5.52f, 0.0f, 10.0f, 4.48f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.04f, -0.61f, 3.93f, -1.66f, 5.51f);
        pathBuilderJ.lineToRelative(-1.46f, -1.46f);
        pathBuilderJ.curveTo(19.59f, 14.86f, 20.0f, 13.48f, 20.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderJ.curveTo(10.52f, 4.0f, 9.14f, 4.41f, 7.94f, 5.12f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hideSource = imageVectorBuild;
        return imageVectorBuild;
    }
}
