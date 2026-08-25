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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_restore", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Restore", "Landroidx/compose/material/icons/Icons$Rounded;", "getRestore", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RestoreKt {
    private static ImageVector _restore;

    public static final ImageVector getRestore(Icons.Rounded rounded) {
        ImageVector imageVector = _restore;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Restore", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.25f, 3.0f);
        pathBuilderA.curveToRelative(-5.09f, -0.14f, -9.26f, 3.94f, -9.26f, 9.0f);
        pathBuilderA.lineTo(2.2f, 12.0f);
        pathBuilderA.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.35f, 0.85f);
        pathBuilderA.lineToRelative(2.79f, 2.8f);
        pathBuilderA.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderA.lineToRelative(2.79f, -2.8f);
        pathBuilderA.curveToRelative(0.32f, -0.31f, 0.09f, -0.85f, -0.35f, -0.85f);
        pathBuilderA.horizontalLineToRelative(-1.8f);
        pathBuilderA.curveToRelative(0.0f, -3.9f, 3.18f, -7.05f, 7.1f, -7.0f);
        pathBuilderA.curveToRelative(3.72f, 0.05f, 6.85f, 3.18f, 6.9f, 6.9f);
        pathBuilderA.curveToRelative(0.05f, 3.91f, -3.1f, 7.1f, -7.0f, 7.1f);
        pathBuilderA.curveToRelative(-1.61f, 0.0f, -3.1f, -0.55f, -4.28f, -1.48f);
        pathBuilderA.curveToRelative(-0.4f, -0.31f, -0.96f, -0.28f, -1.32f, 0.08f);
        pathBuilderA.curveToRelative(-0.42f, 0.43f, -0.39f, 1.13f, 0.08f, 1.5f);
        pathBuilderA.curveToRelative(1.52f, 1.19f, 3.44f, 1.9f, 5.52f, 1.9f);
        pathBuilderA.curveToRelative(5.05f, 0.0f, 9.14f, -4.17f, 9.0f, -9.26f);
        pathBuilderA.curveToRelative(-0.13f, -4.69f, -4.05f, -8.61f, -8.74f, -8.74f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.74f, 8.0f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderA.verticalLineToRelative(3.68f);
        pathBuilderA.curveToRelative(0.0f, 0.35f, 0.19f, 0.68f, 0.49f, 0.86f);
        pathBuilderA.lineToRelative(3.12f, 1.85f);
        pathBuilderA.curveToRelative(0.36f, 0.21f, 0.82f, 0.09f, 1.03f, -0.26f);
        pathBuilderA.curveToRelative(0.21f, -0.36f, 0.09f, -0.82f, -0.26f, -1.03f);
        pathBuilderA.lineToRelative(-2.88f, -1.71f);
        pathBuilderA.verticalLineToRelative(-3.4f);
        pathBuilderA.curveToRelative(0.0f, -0.4f, -0.33f, -0.74f, -0.75f, -0.74f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _restore = imageVectorBuild;
        return imageVectorBuild;
    }
}
