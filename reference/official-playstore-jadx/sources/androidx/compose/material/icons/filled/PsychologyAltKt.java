package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_psychologyAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PsychologyAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getPsychologyAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PsychologyAltKt {
    private static ImageVector _psychologyAlt;

    public static final ImageVector getPsychologyAlt(Icons.Filled filled) {
        ImageVector imageVector = _psychologyAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PsychologyAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.94f, 9.06f);
        pathBuilderA.curveTo(19.5f, 5.73f, 16.57f, 3.0f, 13.0f, 3.0f);
        pathBuilderA.curveTo(9.47f, 3.0f, 6.57f, 5.61f, 6.08f, 9.0f);
        pathBuilderA.lineToRelative(-1.93f, 3.48f);
        pathBuilderA.curveTo(3.74f, 13.14f, 4.22f, 14.0f, 5.0f, 14.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.lineToRelative(0.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        e.g(pathBuilderA, 1.0f, 3.0f, 7.0f);
        pathBuilderA.lineToRelative(0.0f, -4.68f);
        pathBuilderA.curveTo(18.62f, 15.07f, 20.35f, 12.24f, 19.94f, 9.06f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.5f, 14.0f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.74f, -0.33f, -0.74f, -0.74f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, 0.33f, -0.73f, 0.74f, -0.73f);
        pathBuilderA.curveToRelative(0.41f, 0.0f, 0.73f, 0.32f, 0.73f, 0.73f);
        pathBuilderA.curveTo(13.23f, 13.67f, 12.92f, 14.0f, 12.5f, 14.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.26f, 9.68f);
        pathBuilderA.curveToRelative(-0.44f, 0.65f, -0.86f, 0.85f, -1.09f, 1.27f);
        pathBuilderA.curveToRelative(-0.09f, 0.17f, -0.13f, 0.28f, -0.13f, 0.82f);
        pathBuilderA.horizontalLineToRelative(-1.06f);
        pathBuilderA.curveToRelative(0.0f, -0.29f, -0.04f, -0.75f, 0.18f, -1.16f);
        pathBuilderA.curveToRelative(0.28f, -0.51f, 0.83f, -0.81f, 1.14f, -1.26f);
        pathBuilderA.curveToRelative(0.33f, -0.47f, 0.15f, -1.36f, -0.8f, -1.36f);
        pathBuilderA.curveToRelative(-0.62f, 0.0f, -0.92f, 0.47f, -1.05f, 0.86f);
        pathBuilderA.lineToRelative(-0.96f, -0.4f);
        pathBuilderA.curveTo(10.76f, 7.67f, 11.46f, 7.0f, 12.5f, 7.0f);
        pathBuilderA.curveToRelative(0.86f, 0.0f, 1.45f, 0.39f, 1.75f, 0.88f);
        pathBuilderA.curveTo(14.51f, 8.31f, 14.66f, 9.1f, 14.26f, 9.68f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _psychologyAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
