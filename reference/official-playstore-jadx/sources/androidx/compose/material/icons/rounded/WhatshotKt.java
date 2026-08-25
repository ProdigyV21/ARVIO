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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_whatshot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Whatshot", "Landroidx/compose/material/icons/Icons$Rounded;", "getWhatshot", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WhatshotKt {
    private static ImageVector _whatshot;

    public static final ImageVector getWhatshot(Icons.Rounded rounded) {
        ImageVector imageVector = _whatshot;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Whatshot", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.09f, 4.56f);
        pathBuilderA.curveToRelative(-0.7f, -1.03f, -1.5f, -1.99f, -2.4f, -2.85f);
        pathBuilderA.curveToRelative(-0.35f, -0.34f, -0.94f, -0.02f, -0.84f, 0.46f);
        pathBuilderA.curveToRelative(0.19f, 0.94f, 0.39f, 2.18f, 0.39f, 3.29f);
        pathBuilderA.curveToRelative(0.0f, 2.06f, -1.35f, 3.73f, -3.41f, 3.73f);
        pathBuilderA.curveToRelative(-1.54f, 0.0f, -2.8f, -0.93f, -3.35f, -2.26f);
        pathBuilderA.curveToRelative(-0.1f, -0.2f, -0.14f, -0.32f, -0.2f, -0.54f);
        pathBuilderA.curveToRelative(-0.11f, -0.42f, -0.66f, -0.55f, -0.9f, -0.18f);
        pathBuilderA.curveToRelative(-0.18f, 0.27f, -0.35f, 0.54f, -0.51f, 0.83f);
        pathBuilderA.curveTo(4.68f, 9.08f, 4.0f, 11.46f, 4.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -3.49f, -1.08f, -6.73f, -2.91f, -9.44f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.71f, 19.0f);
        pathBuilderA.curveToRelative(-1.78f, 0.0f, -3.22f, -1.4f, -3.22f, -3.14f);
        pathBuilderA.curveToRelative(0.0f, -1.62f, 1.05f, -2.76f, 2.81f, -3.12f);
        pathBuilderA.curveToRelative(1.47f, -0.3f, 2.98f, -0.93f, 4.03f, -1.92f);
        pathBuilderA.curveToRelative(0.28f, -0.26f, 0.74f, -0.14f, 0.82f, 0.23f);
        pathBuilderA.curveToRelative(0.23f, 1.02f, 0.35f, 2.08f, 0.35f, 3.15f);
        pathBuilderA.curveToRelative(0.01f, 2.65f, -2.14f, 4.8f, -4.79f, 4.8f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _whatshot = imageVectorBuild;
        return imageVectorBuild;
    }
}
