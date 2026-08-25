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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_newReleases", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NewReleases", "Landroidx/compose/material/icons/Icons$Rounded;", "getNewReleases", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NewReleasesKt {
    private static ImageVector _newReleases;

    public static final ImageVector getNewReleases(Icons.Rounded rounded) {
        ImageVector imageVector = _newReleases;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NewReleases", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.42f, 11.34f, -1.86f, -2.12f);
        pathBuilderR.lineToRelative(0.26f, -2.81f);
        pathBuilderR.curveToRelative(0.05f, -0.5f, -0.29f, -0.96f, -0.77f, -1.07f);
        pathBuilderR.lineToRelative(-2.76f, -0.63f);
        pathBuilderR.lineToRelative(-1.44f, -2.43f);
        pathBuilderR.curveToRelative(-0.26f, -0.43f, -0.79f, -0.61f, -1.25f, -0.41f);
        pathBuilderR.lineTo(12.0f, 3.0f);
        pathBuilderR.lineTo(9.41f, 1.89f);
        pathBuilderR.curveToRelative(-0.46f, -0.2f, -1.0f, -0.02f, -1.25f, 0.41f);
        pathBuilderR.lineTo(6.71f, 4.72f);
        pathBuilderR.lineToRelative(-2.75f, 0.62f);
        pathBuilderR.curveToRelative(-0.49f, 0.11f, -0.83f, 0.56f, -0.78f, 1.07f);
        pathBuilderR.lineToRelative(0.26f, 2.8f);
        pathBuilderR.lineToRelative(-1.86f, 2.13f);
        pathBuilderR.curveToRelative(-0.33f, 0.38f, -0.33f, 0.94f, 0.0f, 1.32f);
        pathBuilderR.lineToRelative(1.86f, 2.12f);
        pathBuilderR.lineToRelative(-0.26f, 2.82f);
        pathBuilderR.curveToRelative(-0.05f, 0.5f, 0.29f, 0.96f, 0.77f, 1.07f);
        pathBuilderR.lineToRelative(2.76f, 0.63f);
        pathBuilderR.lineToRelative(1.44f, 2.42f);
        pathBuilderR.curveToRelative(0.26f, 0.43f, 0.79f, 0.61f, 1.26f, 0.41f);
        pathBuilderR.lineTo(12.0f, 21.0f);
        pathBuilderR.lineToRelative(2.59f, 1.11f);
        pathBuilderR.curveToRelative(0.46f, 0.2f, 1.0f, 0.02f, 1.25f, -0.41f);
        pathBuilderR.lineToRelative(1.44f, -2.43f);
        pathBuilderR.lineToRelative(2.76f, -0.63f);
        pathBuilderR.curveToRelative(0.49f, -0.11f, 0.82f, -0.57f, 0.77f, -1.07f);
        pathBuilderR.lineToRelative(-0.26f, -2.81f);
        pathBuilderR.lineToRelative(1.86f, -2.12f);
        pathBuilderR.curveToRelative(0.34f, -0.36f, 0.34f, -0.92f, 0.01f, -1.3f);
        b.w(pathBuilderR, 13.0f, 17.0f, -2.0f, -2.0f);
        b.B(pathBuilderR, 2.0f, 2.0f, 12.0f, 13.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.lineTo(11.0f, 8.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _newReleases = imageVectorBuild;
        return imageVectorBuild;
    }
}
