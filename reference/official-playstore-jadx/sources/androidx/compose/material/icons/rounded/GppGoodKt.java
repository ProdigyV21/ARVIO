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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gppGood", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GppGood", "Landroidx/compose/material/icons/Icons$Rounded;", "getGppGood", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GppGoodKt {
    private static ImageVector _gppGood;

    public static final ImageVector getGppGood(Icons.Rounded rounded) {
        ImageVector imageVector = _gppGood;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.GppGood", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.3f, 2.26f, -6.0f, 2.25f);
        pathBuilderR.curveTo(4.52f, 4.81f, 4.0f, 5.55f, 4.0f, 6.39f);
        pathBuilderR.verticalLineToRelative(4.71f);
        pathBuilderR.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilderR.curveToRelative(4.59f, -1.15f, 8.0f, -5.86f, 8.0f, -10.91f);
        pathBuilderR.verticalLineTo(6.39f);
        pathBuilderR.curveToRelative(0.0f, -0.83f, -0.52f, -1.58f, -1.3f, -1.87f);
        pathBuilderR.lineToRelative(-6.0f, -2.25f);
        pathBuilderR.curveTo(12.25f, 2.09f, 11.75f, 2.09f, 11.3f, 2.26f);
        a.z(pathBuilderR, 10.23f, 14.83f, -2.12f, -2.12f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(3.54f, -3.54f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineToRelative(-4.24f, 4.24f);
        pathBuilderR.curveTo(11.26f, 15.22f, 10.62f, 15.22f, 10.23f, 14.83f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gppGood = imageVectorBuild;
        return imageVectorBuild;
    }
}
