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
import v.d;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_curtains", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Curtains", "Landroidx/compose/material/icons/Icons$Outlined;", "getCurtains", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CurtainsKt {
    private static ImageVector _curtains;

    public static final ImageVector getCurtains(Icons.Outlined outlined) {
        ImageVector imageVector = _curtains;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Curtains", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 19.0f, 3.0f, 4.0f, 16.0f);
        b.q(pathBuilderJ, 2.0f, 2.0f, 20.0f, -2.0f);
        v.a.x(pathBuilderJ, 20.0f, 18.0f, 10.86f);
        pathBuilderJ.curveToRelative(-2.05f, -0.58f, -3.64f, -2.93f, -3.94f, -5.86f);
        c.l(pathBuilderJ, 18.0f, 10.86f, 15.81f, 12.0f);
        pathBuilderJ.curveToRelative(-2.04f, 1.35f, -3.5f, 3.94f, -3.76f, 7.0f);
        pathBuilderJ.horizontalLineToRelative(-0.09f);
        pathBuilderJ.curveToRelative(-0.26f, -3.06f, -1.72f, -5.65f, -3.76f, -7.0f);
        pathBuilderJ.curveToRelative(2.04f, -1.35f, 3.5f, -3.94f, 3.76f, -7.0f);
        pathBuilderJ.horizontalLineToRelative(0.09f);
        pathBuilderJ.curveTo(12.31f, 8.06f, 13.77f, 10.65f, 15.81f, 12.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.94f, 5.0f);
        pathBuilderJ.curveTo(9.64f, 7.93f, 8.05f, 10.27f, 6.0f, 10.86f);
        c.n(pathBuilderJ, 5.0f, 9.94f, 6.0f, 13.14f);
        pathBuilderJ.curveToRelative(2.05f, 0.58f, 3.64f, 2.93f, 3.94f, 5.86f);
        c.l(pathBuilderJ, 6.0f, 13.14f, 14.06f, 19.0f);
        pathBuilderJ.curveToRelative(0.3f, -2.93f, 1.89f, -5.27f, 3.94f, -5.86f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderJ, 19.0f, 14.06f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _curtains = imageVectorBuild;
        return imageVectorBuild;
    }
}
