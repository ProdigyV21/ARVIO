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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_onlinePrediction", "Landroidx/compose/ui/graphics/vector/ImageVector;", "OnlinePrediction", "Landroidx/compose/material/icons/Icons$Rounded;", "getOnlinePrediction", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OnlinePredictionKt {
    private static ImageVector _onlinePrediction;

    public static final ImageVector getOnlinePrediction(Icons.Rounded rounded) {
        ImageVector imageVector = _onlinePrediction;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.OnlinePrediction", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.5f, 11.5f);
        pathBuilderA.curveToRelative(0.0f, 2.0f, -2.5f, 3.5f, -2.5f, 5.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.5f, -2.5f, -3.0f, -2.5f, -5.0f);
        pathBuilderA.curveTo(8.5f, 9.57f, 10.07f, 8.0f, 12.0f, 8.0f);
        pathBuilderA.reflectiveCurveTo(15.5f, 9.57f, 15.5f, 11.5f);
        a.u(pathBuilderA, 13.0f, 17.5f, -2.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        f.r(pathBuilderA, 17.5f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.46f, -0.89f, -4.71f, -2.36f, -6.45f);
        pathBuilderA.curveToRelative(-0.29f, -0.34f, -0.8f, -0.38f, -1.12f, -0.06f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.27f, 0.27f, -0.3f, 0.71f, -0.06f, 1.0f);
        pathBuilderA.curveTo(19.73f, 7.97f, 20.5f, 9.9f, 20.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.1f, -0.77f, 4.03f, -2.04f, 5.52f);
        pathBuilderA.curveToRelative(-0.25f, 0.29f, -0.21f, 0.73f, 0.06f, 1.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.32f, 0.32f, 0.83f, 0.28f, 1.12f, -0.06f);
        pathBuilderA.curveTo(21.11f, 16.71f, 22.0f, 14.46f, 22.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.1f, 0.77f, -4.03f, 2.04f, -5.52f);
        pathBuilderA.curveToRelative(0.25f, -0.29f, 0.21f, -0.73f, -0.06f, -1.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(5.17f, 5.17f, 4.65f, 5.2f, 4.36f, 5.54f);
        pathBuilderA.curveTo(2.89f, 7.29f, 2.0f, 9.54f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.46f, 0.89f, 4.71f, 2.36f, 6.46f);
        pathBuilderA.curveToRelative(0.29f, 0.34f, 0.8f, 0.38f, 1.12f, 0.06f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.27f, -0.27f, 0.3f, -0.71f, 0.06f, -1.0f);
        pathBuilderA.curveTo(4.27f, 16.03f, 3.5f, 14.1f, 3.5f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.28f, -0.44f, 2.47f, -1.18f, 3.41f);
        pathBuilderA.curveToRelative(-0.23f, 0.29f, -0.2f, 0.71f, 0.07f, 0.98f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.32f, 0.32f, 0.85f, 0.29f, 1.13f, -0.07f);
        pathBuilderA.curveTo(18.44f, 15.13f, 19.0f, 13.63f, 19.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.63f, -0.56f, -3.13f, -1.49f, -4.31f);
        pathBuilderA.curveToRelative(-0.28f, -0.36f, -0.81f, -0.39f, -1.13f, -0.07f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.26f, 0.26f, -0.3f, 0.68f, -0.07f, 0.98f);
        pathBuilderA.curveTo(17.06f, 9.53f, 17.5f, 10.72f, 17.5f, 12.0f);
        a.p(pathBuilderA, 7.62f, 16.38f, 7.62f, 16.38f);
        pathBuilderA.curveToRelative(0.26f, -0.26f, 0.3f, -0.68f, 0.07f, -0.98f);
        pathBuilderA.curveTo(6.94f, 14.47f, 6.5f, 13.28f, 6.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.28f, 0.44f, -2.47f, 1.18f, -3.41f);
        pathBuilderA.curveToRelative(0.23f, -0.29f, 0.2f, -0.71f, -0.07f, -0.98f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(7.3f, 7.3f, 6.77f, 7.33f, 6.49f, 7.68f);
        pathBuilderA.curveTo(5.56f, 8.87f, 5.0f, 10.37f, 5.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.63f, 0.56f, 3.13f, 1.49f, 4.32f);
        pathBuilderA.curveTo(6.77f, 16.67f, 7.3f, 16.7f, 7.62f, 16.38f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _onlinePrediction = imageVectorBuild;
        return imageVectorBuild;
    }
}
