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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localPolice", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalPolice", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalPolice", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPoliceKt {
    private static ImageVector _localPolice;

    public static final ImageVector getLocalPolice(Icons.Rounded rounded) {
        ImageVector imageVector = _localPolice;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalPolice", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.5f, 12.59f, 0.63f, 2.73f);
        pathBuilderR.curveToRelative(0.1f, 0.43f, -0.37f, 0.77f, -0.75f, 0.54f);
        pathBuilderR.lineTo(12.0f, 14.42f);
        pathBuilderR.lineToRelative(-2.39f, 1.44f);
        pathBuilderR.curveToRelative(-0.38f, 0.23f, -0.85f, -0.11f, -0.75f, -0.54f);
        pathBuilderR.lineTo(9.5f, 12.6f);
        pathBuilderR.lineToRelative(-2.1f, -1.81f);
        pathBuilderR.curveTo(7.06f, 10.5f, 7.24f, 9.95f, 7.68f, 9.91f);
        pathBuilderR.lineToRelative(2.78f, -0.24f);
        pathBuilderR.lineToRelative(1.08f, -2.56f);
        pathBuilderR.curveToRelative(0.17f, -0.41f, 0.75f, -0.41f, 0.92f, 0.0f);
        pathBuilderR.lineToRelative(1.08f, 2.55f);
        pathBuilderR.lineToRelative(2.78f, 0.24f);
        pathBuilderR.curveToRelative(0.44f, 0.04f, 0.62f, 0.59f, 0.28f, 0.88f);
        c.z(pathBuilderR, 14.5f, 12.59f, 4.19f, 4.47f);
        pathBuilderR.curveTo(3.47f, 4.79f, 3.0f, 5.51f, 3.0f, 6.3f);
        pathBuilderR.verticalLineTo(11.0f);
        pathBuilderR.curveToRelative(0.0f, 5.55f, 3.84f, 10.74f, 9.0f, 12.0f);
        pathBuilderR.curveToRelative(5.16f, -1.26f, 9.0f, -6.45f, 9.0f, -12.0f);
        pathBuilderR.verticalLineTo(6.3f);
        pathBuilderR.curveToRelative(0.0f, -0.79f, -0.47f, -1.51f, -1.19f, -1.83f);
        pathBuilderR.lineToRelative(-7.0f, -3.11f);
        pathBuilderR.curveToRelative(-0.52f, -0.23f, -1.11f, -0.23f, -1.62f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 4.19f, 4.47f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localPolice = imageVectorBuild;
        return imageVectorBuild;
    }
}
