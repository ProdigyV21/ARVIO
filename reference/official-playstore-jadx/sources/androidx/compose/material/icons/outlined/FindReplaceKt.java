package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_findReplace", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FindReplace", "Landroidx/compose/material/icons/Icons$Outlined;", "getFindReplace", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FindReplaceKt {
    private static ImageVector _findReplace;

    public static final ImageVector getFindReplace(Icons.Outlined outlined) {
        ImageVector imageVector = _findReplace;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FindReplace", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.0f, 6.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.63f, 0.56f, 3.54f, 1.46f);
        pathBuilderA.lineTo(12.0f, 10.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.lineTo(18.0f, 4.0f);
        pathBuilderA.lineToRelative(-2.05f, 2.05f);
        pathBuilderA.curveTo(14.68f, 4.78f, 12.93f, 4.0f, 11.0f, 4.0f);
        pathBuilderA.curveToRelative(-3.53f, 0.0f, -6.43f, 2.61f, -6.92f, 6.0f);
        pathBuilderA.lineTo(6.1f, 10.0f);
        pathBuilderA.curveToRelative(0.46f, -2.28f, 2.48f, -4.0f, 4.9f, -4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.64f, 15.14f);
        pathBuilderA.curveToRelative(0.66f, -0.9f, 1.12f, -1.97f, 1.28f, -3.14f);
        pathBuilderA.lineTo(15.9f, 12.0f);
        pathBuilderA.curveToRelative(-0.46f, 2.28f, -2.48f, 4.0f, -4.9f, 4.0f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.63f, -0.56f, -3.54f, -1.46f);
        pathBuilderA.lineTo(10.0f, 12.0f);
        pathBuilderA.lineTo(4.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.lineToRelative(2.05f, -2.05f);
        pathBuilderA.curveTo(7.32f, 17.22f, 9.07f, 18.0f, 11.0f, 18.0f);
        pathBuilderA.curveToRelative(1.55f, 0.0f, 2.98f, -0.51f, 4.14f, -1.36f);
        pathBuilderA.lineTo(20.0f, 21.49f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderA, 21.49f, 20.0f, -4.85f, -4.86f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _findReplace = imageVectorBuild;
        return imageVectorBuild;
    }
}
