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
import v.a;
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__9kPlus", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_9kPlus", "Landroidx/compose/material/icons/Icons$Filled;", "get_9kPlus", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _9kPlusKt {
    private static ImageVector __9kPlus;

    public static final ImageVector get_9kPlus(Icons.Filled filled) {
        ImageVector imageVector = __9kPlus;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled._9kPlus", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(6.5f, 10.0f, 8.0f, 10.0f, 1.5f);
        c.z(pathBuilderS, 6.5f, 11.5f, 19.0f, 3.0f);
        pathBuilderS.lineTo(5.0f, 3.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderS.verticalLineToRelative(14.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderS.horizontalLineToRelative(14.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderS.lineTo(21.0f, 5.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(9.5f, 14.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a.h(pathBuilderS, 5.0f, 15.0f, -1.5f, 3.0f);
        pathBuilderS.verticalLineToRelative(-1.0f);
        pathBuilderS.lineTo(6.0f, 12.5f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderS.lineTo(5.0f, 10.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderS.horizontalLineToRelative(2.5f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        c.r(pathBuilderS, 4.0f, 16.0f, 15.0f, -1.75f);
        pathBuilderS.lineToRelative(-1.75f, -2.25f);
        pathBuilderS.lineTo(12.5f, 15.0f);
        pathBuilderS.lineTo(11.0f, 15.0f);
        b.A(pathBuilderS, 11.0f, 9.0f, 1.5f, 2.25f);
        pathBuilderS.lineTo(14.25f, 9.0f);
        pathBuilderS.lineTo(16.0f, 9.0f);
        d.C(pathBuilderS, -2.25f, 3.0f, 16.0f, 15.0f);
        pathBuilderS.moveTo(20.0f, 12.5f);
        c.A(pathBuilderS, -1.5f, 18.5f, 14.0f, -1.0f);
        d.r(pathBuilderS, -1.5f, 16.0f, 12.5f, -1.0f);
        c.A(pathBuilderS, 1.5f, 17.5f, 10.0f, 1.0f);
        pathBuilderS.verticalLineToRelative(1.5f);
        pathBuilderS.lineTo(20.0f, 11.5f);
        pathBuilderS.verticalLineToRelative(1.0f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __9kPlus = imageVectorBuild;
        return imageVectorBuild;
    }
}
