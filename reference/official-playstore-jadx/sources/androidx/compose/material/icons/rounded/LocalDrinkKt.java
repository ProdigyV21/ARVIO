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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localDrink", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalDrink", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalDrink", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalDrinkKt {
    private static ImageVector _localDrink;

    public static final ImageVector getLocalDrink(Icons.Rounded rounded) {
        ImageVector imageVector = _localDrink;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalDrink", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.23f, 2.0f);
        pathBuilderA.curveTo(4.04f, 2.0f, 3.11f, 3.04f, 3.24f, 4.22f);
        pathBuilderA.lineToRelative(1.77f, 16.01f);
        pathBuilderA.curveTo(5.13f, 21.23f, 5.97f, 22.0f, 7.0f, 22.0f);
        pathBuilderA.horizontalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(1.03f, 0.0f, 1.87f, -0.77f, 1.99f, -1.77f);
        pathBuilderA.lineToRelative(1.77f, -16.01f);
        pathBuilderA.curveToRelative(0.13f, -1.18f, -0.8f, -2.22f, -1.99f, -2.22f);
        c.z(pathBuilderA, 5.23f, 2.0f, 12.0f, 19.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.55f, 1.81f, -3.95f, 2.62f, -4.94f);
        pathBuilderA.curveToRelative(0.2f, -0.25f, 0.57f, -0.25f, 0.77f, 0.0f);
        pathBuilderA.curveToRelative(0.81f, 1.0f, 2.62f, 3.39f, 2.62f, 4.94f);
        pathBuilderA.curveToRelative(-0.01f, 1.66f, -1.35f, 3.0f, -3.01f, 3.0f);
        a.p(pathBuilderA, 18.33f, 8.0f, 5.67f, 8.0f);
        pathBuilderA.lineToRelative(-0.32f, -2.89f);
        pathBuilderA.curveToRelative(-0.06f, -0.59f, 0.4f, -1.11f, 1.0f, -1.11f);
        pathBuilderA.horizontalLineToRelative(11.3f);
        pathBuilderA.curveToRelative(0.59f, 0.0f, 1.06f, 0.52f, 0.99f, 1.11f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 18.33f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localDrink = imageVectorBuild;
        return imageVectorBuild;
    }
}
