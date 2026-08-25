package androidx.compose.material.icons.twotone;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_build", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Build", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBuild", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuildKt {
    private static ImageVector _build;

    public static final ImageVector getBuild(Icons.TwoTone twoTone) {
        ImageVector imageVector = _build;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Build", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.92f, 8.28f);
        pathBuilderA.curveToRelative(0.24f, -1.4f, -0.16f, -2.89f, -1.24f, -3.96f);
        pathBuilderA.curveToRelative(-0.94f, -0.95f, -2.2f, -1.39f, -3.44f, -1.32f);
        pathBuilderA.lineToRelative(3.09f, 3.09f);
        pathBuilderA.lineToRelative(-4.24f, 4.24f);
        pathBuilderA.lineTo(3.0f, 7.24f);
        pathBuilderA.curveToRelative(-0.07f, 1.24f, 0.37f, 2.49f, 1.31f, 3.44f);
        pathBuilderA.curveToRelative(1.04f, 1.04f, 2.47f, 1.45f, 3.83f, 1.25f);
        pathBuilderA.curveToRelative(0.71f, -0.1f, 1.4f, -0.38f, 2.0f, -0.82f);
        pathBuilderA.lineToRelative(9.46f, 9.46f);
        pathBuilderA.lineToRelative(0.88f, -0.88f);
        pathBuilderA.lineToRelative(-9.45f, -9.45f);
        pathBuilderA.curveToRelative(0.47f, -0.6f, 0.77f, -1.26f, 0.89f, -1.96f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.61f, 18.97f, 13.54f, 9.9f);
        pathBuilderJ.curveToRelative(0.93f, -2.34f, 0.45f, -5.1f, -1.44f, -7.0f);
        pathBuilderJ.curveTo(9.8f, 0.6f, 6.22f, 0.39f, 3.67f, 2.25f);
        pathBuilderJ.lineTo(7.5f, 6.08f);
        pathBuilderJ.lineTo(6.08f, 7.5f);
        pathBuilderJ.lineTo(2.25f, 3.67f);
        pathBuilderJ.curveTo(0.39f, 6.21f, 0.6f, 9.79f, 2.9f, 12.09f);
        pathBuilderJ.curveToRelative(1.86f, 1.86f, 4.57f, 2.35f, 6.89f, 1.48f);
        pathBuilderJ.lineToRelative(9.11f, 9.11f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(2.3f, -2.3f);
        pathBuilderJ.curveToRelative(0.4f, -0.38f, 0.4f, -1.02f, 0.0f, -1.41f);
        a.z(pathBuilderJ, 19.61f, 20.57f, -9.46f, -9.46f);
        pathBuilderJ.curveToRelative(-0.61f, 0.45f, -1.29f, 0.72f, -2.0f, 0.82f);
        pathBuilderJ.curveToRelative(-1.36f, 0.2f, -2.79f, -0.21f, -3.83f, -1.25f);
        pathBuilderJ.curveToRelative(-0.95f, -0.94f, -1.39f, -2.2f, -1.32f, -3.44f);
        pathBuilderJ.lineToRelative(3.09f, 3.09f);
        pathBuilderJ.lineToRelative(4.24f, -4.24f);
        pathBuilderJ.lineTo(7.24f, 3.0f);
        pathBuilderJ.curveToRelative(1.24f, -0.07f, 2.49f, 0.37f, 3.44f, 1.31f);
        pathBuilderJ.curveToRelative(1.08f, 1.08f, 1.49f, 2.57f, 1.24f, 3.96f);
        pathBuilderJ.curveToRelative(-0.12f, 0.7f, -0.42f, 1.36f, -0.88f, 1.95f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 9.45f, 9.45f, -0.88f, 0.9f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _build = imageVectorBuild;
        return imageVectorBuild;
    }
}
