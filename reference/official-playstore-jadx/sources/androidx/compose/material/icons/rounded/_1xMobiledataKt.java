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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__1xMobiledata", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_1xMobiledata", "Landroidx/compose/material/icons/Icons$Rounded;", "get_1xMobiledata", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _1xMobiledataKt {
    private static ImageVector __1xMobiledata;

    public static final ImageVector get_1xMobiledata(Icons.Rounded rounded) {
        ImageVector imageVector = __1xMobiledata;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded._1xMobiledata", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(5.0f, 7.0f, 2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(4.45f, 9.0f, 4.0f, 8.55f, 4.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(4.0f, 7.45f, 4.45f, 7.0f, 5.0f, 7.0f);
        a.z(pathBuilderA, 15.83f, 11.72f, 1.92f, -3.21f);
        pathBuilderA.curveTo(18.15f, 7.85f, 17.67f, 7.0f, 16.9f, 7.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.35f, 0.0f, -0.68f, 0.18f, -0.86f, 0.49f);
        pathBuilderA.lineToRelative(-1.37f, 2.28f);
        pathBuilderA.lineToRelative(-1.38f, -2.29f);
        pathBuilderA.curveTo(13.11f, 7.18f, 12.79f, 7.0f, 12.44f, 7.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.78f, 0.0f, -1.26f, 0.85f, -0.86f, 1.51f);
        pathBuilderA.lineToRelative(1.92f, 3.21f);
        pathBuilderA.lineToRelative(-2.26f, 3.77f);
        pathBuilderA.curveToRelative(-0.4f, 0.67f, 0.08f, 1.51f, 0.86f, 1.51f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.35f, 0.0f, 0.68f, -0.18f, 0.86f, -0.49f);
        pathBuilderA.lineToRelative(1.71f, -2.85f);
        pathBuilderA.lineToRelative(1.71f, 2.85f);
        pathBuilderA.curveToRelative(0.18f, 0.3f, 0.51f, 0.49f, 0.86f, 0.49f);
        pathBuilderA.horizontalLineToRelative(0.01f);
        pathBuilderA.curveToRelative(0.78f, 0.0f, 1.26f, -0.85f, 0.86f, -1.51f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 15.83f, 11.72f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __1xMobiledata = imageVectorBuild;
        return imageVectorBuild;
    }
}
