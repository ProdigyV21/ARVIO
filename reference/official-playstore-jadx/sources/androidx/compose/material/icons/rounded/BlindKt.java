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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_blind", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Blind", "Landroidx/compose/material/icons/Icons$Rounded;", "getBlind", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlindKt {
    private static ImageVector _blind;

    public static final ImageVector getBlind(Icons.Rounded rounded) {
        ImageVector imageVector = _blind;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Blind", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(11.41f, 3.5f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.04f, 7.12f);
        pathBuilderA.curveToRelative(-0.17f, -0.35f, -0.44f, -0.65f, -0.8f, -0.85f);
        pathBuilderA.curveTo(10.63f, 5.91f, 9.9f, 5.93f, 9.31f, 6.24f);
        pathBuilderA.lineToRelative(0.0f, -0.01f);
        pathBuilderA.lineTo(4.92f, 8.73f);
        pathBuilderA.curveTo(4.3f, 9.08f, 3.91f, 9.74f, 3.91f, 10.46f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.54f);
        pathBuilderA.lineToRelative(1.5f, -0.85f);
        pathBuilderA.curveToRelative(-0.32f, 1.1f, -0.5f, 2.24f, -0.5f, 3.39f);
        pathBuilderA.verticalLineToRelative(5.33f);
        pathBuilderA.lineToRelative(-2.0f, 2.67f);
        pathBuilderA.curveToRelative(-0.33f, 0.44f, -0.24f, 1.07f, 0.2f, 1.4f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.44f, 0.33f, 1.07f, 0.24f, 1.4f, -0.2f);
        pathBuilderA.lineToRelative(2.04f, -2.72f);
        pathBuilderA.curveToRelative(0.23f, -0.31f, 0.37f, -0.69f, 0.4f, -1.08f);
        pathBuilderA.lineToRelative(0.18f, -2.94f);
        pathBuilderA.lineTo(10.91f, 18.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.87f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, -0.13f, -0.81f, -0.36f, -1.15f);
        pathBuilderA.lineToRelative(-1.6f, -2.29f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.01f);
        pathBuilderA.curveToRelative(-0.11f, -1.16f, 0.07f, -2.32f, 0.46f, -3.4f);
        pathBuilderA.curveToRelative(0.75f, 1.14f, 1.88f, 1.98f, 3.2f, 2.41f);
        pathBuilderA.lineToRelative(5.7f, 9.87f);
        pathBuilderA.curveToRelative(0.14f, 0.24f, 0.44f, 0.32f, 0.68f, 0.18f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.24f, -0.14f, 0.32f, -0.44f, 0.18f, -0.68f);
        pathBuilderA.lineTo(15.92f, 13.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.54f, 0.0f, 0.98f, -0.44f, 0.98f, -0.98f);
        pathBuilderA.verticalLineToRelative(-0.05f);
        pathBuilderA.curveToRelative(0.0f, -0.5f, -0.37f, -0.94f, -0.87f, -0.99f);
        pathBuilderA.curveToRelative(-0.95f, -0.1f, -2.37f, -0.52f, -3.21f, -2.18f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _blind = imageVectorBuild;
        return imageVectorBuild;
    }
}
