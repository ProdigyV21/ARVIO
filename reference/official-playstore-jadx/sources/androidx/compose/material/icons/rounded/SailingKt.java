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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sailing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sailing", "Landroidx/compose/material/icons/Icons$Rounded;", "getSailing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SailingKt {
    private static ImageVector _sailing;

    public static final ImageVector getSailing(Icons.Rounded rounded) {
        ImageVector imageVector = _sailing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Sailing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(11.0f, 13.0f, 3.59f);
        pathBuilderA.curveToRelative(0.0f, -0.49f, -0.63f, -0.69f, -0.91f, -0.29f);
        pathBuilderA.lineToRelative(-6.54f, 9.41f);
        pathBuilderA.curveToRelative(-0.23f, 0.33f, 0.01f, 0.79f, 0.41f, 0.79f);
        pathBuilderA.horizontalLineToRelative(6.54f);
        pathBuilderA.curveTo(10.78f, 13.5f, 11.0f, 13.28f, 11.0f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.99f, 12.98f);
        pathBuilderA.curveTo(20.72f, 7.07f, 15.9f, 2.32f, 13.4f, 1.23f);
        pathBuilderA.curveToRelative(-0.37f, -0.16f, -0.77f, 0.2f, -0.67f, 0.59f);
        pathBuilderA.curveToRelative(0.3f, 1.13f, 0.76f, 3.28f, 0.76f, 5.68f);
        pathBuilderA.curveToRelative(0.0f, 2.44f, -0.49f, 4.39f, -0.78f, 5.35f);
        pathBuilderA.curveToRelative(-0.1f, 0.32f, 0.14f, 0.65f, 0.48f, 0.65f);
        pathBuilderA.horizontalLineToRelative(7.28f);
        pathBuilderA.curveTo(20.76f, 13.5f, 21.0f, 13.26f, 20.99f, 12.98f);
        a.r(pathBuilderA, 20.62f, 15.0f, 3.38f);
        pathBuilderA.curveToRelative(-0.73f, 0.0f, -1.22f, 0.76f, -0.92f, 1.42f);
        pathBuilderA.curveToRelative(0.43f, 0.92f, 1.07f, 1.71f, 1.86f, 2.31f);
        pathBuilderA.curveToRelative(0.38f, -0.16f, 0.74f, -0.38f, 1.06f, -0.63f);
        pathBuilderA.curveToRelative(0.35f, -0.29f, 0.87f, -0.29f, 1.23f, 0.0f);
        pathBuilderA.curveTo(7.28f, 18.63f, 8.1f, 19.0f, 9.0f, 19.0f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.72f, -0.37f, 2.39f, -0.91f);
        pathBuilderA.curveToRelative(0.35f, -0.28f, 0.87f, -0.28f, 1.22f, 0.0f);
        pathBuilderA.curveTo(13.28f, 18.63f, 14.1f, 19.0f, 15.0f, 19.0f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.72f, -0.37f, 2.39f, -0.91f);
        pathBuilderA.curveToRelative(0.35f, -0.29f, 0.87f, -0.28f, 1.23f, 0.0f);
        pathBuilderA.curveToRelative(0.32f, 0.26f, 0.67f, 0.48f, 1.06f, 0.63f);
        pathBuilderA.curveToRelative(0.79f, -0.6f, 1.43f, -1.39f, 1.86f, -2.31f);
        pathBuilderA.curveTo(21.84f, 15.76f, 21.35f, 15.0f, 20.62f, 15.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(22.0f, 22.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.87f, 0.0f, -1.73f, -0.24f, -2.53f, -0.7f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.65f, -0.17f, -0.94f, 0.0f);
        pathBuilderA.curveToRelative(-1.59f, 0.9f, -3.47f, 0.9f, -5.06f, 0.0f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.65f, -0.16f, -0.94f, 0.0f);
        pathBuilderA.curveToRelative(-1.59f, 0.9f, -3.47f, 0.9f, -5.06f, 0.0f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.65f, -0.16f, -0.94f, 0.0f);
        pathBuilderA.curveTo(4.73f, 20.76f, 3.87f, 21.0f, 3.0f, 21.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(1.15f, 0.0f, 2.3f, -0.31f, 3.33f, -0.94f);
        pathBuilderA.curveToRelative(1.66f, 1.11f, 3.78f, 1.01f, 5.58f, 0.14f);
        pathBuilderA.curveToRelative(1.91f, 1.05f, 4.17f, 1.07f, 6.09f, 0.05f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.95f, 0.5f, 1.97f, 0.75f, 3.0f, 0.75f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveTo(21.55f, 23.0f, 22.0f, 22.55f, 22.0f, 22.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sailing = imageVectorBuild;
        return imageVectorBuild;
    }
}
