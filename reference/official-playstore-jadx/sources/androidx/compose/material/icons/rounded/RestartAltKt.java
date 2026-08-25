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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_restartAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RestartAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getRestartAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RestartAltKt {
    private static ImageVector _restartAlt;

    public static final ImageVector getRestartAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _restartAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RestartAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(12.0f, 5.0f, 3.21f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderA.lineTo(8.35f, 5.65f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderA.lineToRelative(2.79f, 2.79f);
        pathBuilderA.curveTo(11.46f, 9.46f, 12.0f, 9.24f, 12.0f, 8.79f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, 2.72f, -1.83f, 5.02f, -4.31f, 5.75f);
        pathBuilderA.curveTo(13.27f, 18.87f, 13.0f, 19.27f, 13.0f, 19.7f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.65f, 0.62f, 1.16f, 1.25f, 0.97f);
        pathBuilderA.curveTo(17.57f, 19.7f, 20.0f, 16.64f, 20.0f, 13.0f);
        pathBuilderA.curveTo(20.0f, 8.58f, 16.42f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(6.0f, 13.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.34f, 0.44f, -2.58f, 1.19f, -3.59f);
        pathBuilderA2.curveToRelative(0.3f, -0.4f, 0.26f, -0.95f, -0.09f, -1.31f);
        pathBuilderA2.lineToRelative(0.0f, 0.0f);
        pathBuilderA2.curveTo(6.68f, 7.68f, 5.96f, 7.72f, 5.6f, 8.2f);
        pathBuilderA2.curveTo(4.6f, 9.54f, 4.0f, 11.2f, 4.0f, 13.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.64f, 2.43f, 6.7f, 5.75f, 7.67f);
        pathBuilderA2.curveTo(10.38f, 20.86f, 11.0f, 20.35f, 11.0f, 19.7f);
        pathBuilderA2.verticalLineToRelative(0.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.43f, -0.27f, -0.83f, -0.69f, -0.95f);
        pathBuilderA2.curveTo(7.83f, 18.02f, 6.0f, 15.72f, 6.0f, 13.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _restartAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
