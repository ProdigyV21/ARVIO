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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_crib", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Crib", "Landroidx/compose/material/icons/Icons$Rounded;", "getCrib", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CribKt {
    private static ImageVector _crib;

    public static final ImageVector getCrib(Icons.Rounded rounded) {
        ImageVector imageVector = _crib;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Crib", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.32f, 18.32f);
        pathBuilderA.curveToRelative(-0.36f, -0.36f, -0.92f, -0.4f, -1.31f, -0.08f);
        pathBuilderA.curveToRelative(-0.32f, 0.25f, -0.65f, 0.48f, -1.0f, 0.69f);
        pathBuilderA.verticalLineTo(16.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-6.0f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveTo(5.79f, 4.0f, 4.0f, 5.79f, 4.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineToRelative(2.93f);
        pathBuilderA.curveToRelative(-0.35f, -0.2f, -0.69f, -0.43f, -1.0f, -0.69f);
        pathBuilderA.curveToRelative(-0.39f, -0.32f, -0.96f, -0.27f, -1.31f, 0.08f);
        pathBuilderA.curveToRelative(-0.42f, 0.42f, -0.39f, 1.12f, 0.08f, 1.5f);
        pathBuilderA.curveTo(7.47f, 21.18f, 9.64f, 22.0f, 12.0f, 22.0f);
        pathBuilderA.curveToRelative(2.36f, 0.0f, 4.53f, -0.82f, 6.24f, -2.18f);
        pathBuilderA.curveTo(18.71f, 19.44f, 18.74f, 18.74f, 18.32f, 18.32f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 19.75f);
        pathBuilderA.curveTo(13.36f, 19.91f, 12.69f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.curveToRelative(-0.69f, 0.0f, -1.36f, -0.09f, -2.0f, -0.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.m(pathBuilderA, 16.0f, 4.0f, 19.75f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _crib = imageVectorBuild;
        return imageVectorBuild;
    }
}
