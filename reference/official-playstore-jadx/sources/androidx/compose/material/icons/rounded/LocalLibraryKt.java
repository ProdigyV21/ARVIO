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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localLibrary", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalLibrary", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalLibrary", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalLibraryKt {
    private static ImageVector _localLibrary;

    public static final ImageVector getLocalLibrary(Icons.Rounded rounded) {
        ImageVector imageVector = _localLibrary;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalLibrary", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 11.55f);
        pathBuilderA.curveToRelative(-1.82f, -1.7f, -4.12f, -2.89f, -6.68f, -3.35f);
        pathBuilderA.curveTo(4.11f, 7.99f, 3.0f, 8.95f, 3.0f, 10.18f);
        pathBuilderA.verticalLineToRelative(6.24f);
        pathBuilderA.curveToRelative(0.0f, 1.68f, 0.72f, 2.56f, 1.71f, 2.69f);
        pathBuilderA.curveToRelative(2.5f, 0.32f, 4.77f, 1.35f, 6.63f, 2.87f);
        pathBuilderA.curveToRelative(0.35f, 0.29f, 0.92f, 0.32f, 1.27f, 0.04f);
        pathBuilderA.curveToRelative(1.87f, -1.53f, 4.16f, -2.58f, 6.68f, -2.9f);
        pathBuilderA.curveToRelative(0.94f, -0.13f, 1.71f, -1.06f, 1.71f, -2.02f);
        pathBuilderA.verticalLineToRelative(-6.92f);
        pathBuilderA.curveToRelative(0.0f, -1.23f, -1.11f, -2.19f, -2.32f, -1.98f);
        pathBuilderA.curveToRelative(-2.56f, 0.46f, -4.86f, 1.65f, -6.68f, 3.35f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 8.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, 1.34f, -3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, 1.34f, 3.0f, 3.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localLibrary = imageVectorBuild;
        return imageVectorBuild;
    }
}
