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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_assistWalker", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssistWalker", "Landroidx/compose/material/icons/Icons$Rounded;", "getAssistWalker", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssistWalkerKt {
    private static ImageVector _assistWalker;

    public static final ImageVector getAssistWalker(Icons.Rounded rounded) {
        ImageVector imageVector = _assistWalker;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AssistWalker", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.5f, 4.5f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.77f, 17.72f, -0.64f, -6.37f);
        pathBuilderR.curveTo(19.06f, 10.58f, 18.41f, 10.0f, 17.64f, 10.0f);
        pathBuilderR.horizontalLineTo(16.0f);
        pathBuilderR.curveToRelative(-1.5f, -0.02f, -2.86f, -0.54f, -3.76f, -1.44f);
        pathBuilderR.lineToRelative(-2.0f, -1.98f);
        pathBuilderR.curveTo(10.08f, 6.42f, 9.62f, 6.0f, 8.83f, 6.0f);
        pathBuilderR.curveTo(8.32f, 6.0f, 7.81f, 6.2f, 7.42f, 6.59f);
        pathBuilderR.lineTo(4.08f, 9.91f);
        pathBuilderR.curveToRelative(-0.53f, 0.68f, -0.51f, 1.57f, -0.21f, 2.13f);
        pathBuilderR.lineToRelative(1.43f, 2.8f);
        pathBuilderR.lineTo(2.75f, 18.1f);
        pathBuilderR.curveToRelative(-0.34f, 0.43f, -0.26f, 1.06f, 0.17f, 1.4f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.44f, 0.34f, 1.07f, 0.26f, 1.41f, -0.17f);
        pathBuilderR.lineToRelative(2.56f, -3.29f);
        pathBuilderR.curveToRelative(0.33f, -0.42f, 0.47f, -0.95f, 0.41f, -1.48f);
        pathBuilderR.lineToRelative(-0.07f, -0.53f);
        pathBuilderR.lineTo(8.0f, 14.75f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-4.29f);
        pathBuilderR.curveToRelative(0.0f, -0.53f, -0.21f, -1.04f, -0.59f, -1.41f);
        pathBuilderR.lineToRelative(-1.53f, -1.53f);
        pathBuilderR.lineToRelative(2.36f, -2.36f);
        pathBuilderR.curveToRelative(0.94f, 0.94f, 1.72f, 1.82f, 3.59f, 2.32f);
        pathBuilderR.lineToRelative(-0.75f, 7.46f);
        pathBuilderR.curveToRelative(-0.04f, 0.44f, 0.3f, 0.83f, 0.75f, 0.83f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.38f, 0.0f, 0.7f, -0.29f, 0.75f, -0.66f);
        pathBuilderR.lineToRelative(0.33f, -2.84f);
        pathBuilderR.horizontalLineToRelative(3.18f);
        pathBuilderR.lineToRelative(0.14f, 1.22f);
        pathBuilderR.curveToRelative(-0.44f, 0.26f, -0.73f, 0.74f, -0.73f, 1.28f);
        pathBuilderR.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderR.curveTo(20.5f, 18.46f, 20.21f, 17.98f, 19.77f, 17.72f);
        a.z(pathBuilderR, 15.09f, 15.0f, 0.41f, -3.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderR, 2.0f, 0.41f, 3.5f, 15.09f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assistWalker = imageVectorBuild;
        return imageVectorBuild;
    }
}
