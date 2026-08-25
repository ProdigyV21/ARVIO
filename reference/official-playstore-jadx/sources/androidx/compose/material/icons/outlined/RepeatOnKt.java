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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_repeatOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RepeatOn", "Landroidx/compose/material/icons/Icons$Outlined;", "getRepeatOn", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RepeatOnKt {
    private static ImageVector _repeatOn;

    public static final ImageVector getRepeatOn(Icons.Outlined outlined) {
        ImageVector imageVector = _repeatOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RepeatOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(21.0f, 1.0f, 3.0f);
        pathBuilderQ.curveTo(1.9f, 1.0f, 1.0f, 1.9f, 1.0f, 3.0f);
        pathBuilderQ.verticalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(3.0f);
        pathBuilderQ.curveTo(23.0f, 1.9f, 22.1f, 1.0f, 21.0f, 1.0f);
        w.a.r(pathBuilderQ, 19.0f, 19.0f, 6.83f);
        pathBuilderQ.lineToRelative(1.58f, 1.58f);
        pathBuilderQ.lineTo(7.0f, 22.0f);
        pathBuilderQ.lineToRelative(-4.0f, -4.0f);
        pathBuilderQ.lineToRelative(4.0f, -4.0f);
        pathBuilderQ.lineToRelative(1.41f, 1.42f);
        b.m(pathBuilderQ, 6.83f, 17.0f, 17.0f, -4.0f);
        c.D(pathBuilderQ, 2.0f, 19.0f, 17.0f, 10.0f);
        pathBuilderQ.lineToRelative(-1.41f, -1.42f);
        b.m(pathBuilderQ, 17.17f, 7.0f, 7.0f, 4.0f);
        a0.b.u(pathBuilderQ, 5.0f, 5.0f, 12.17f);
        pathBuilderQ.lineToRelative(-1.58f, -1.58f);
        pathBuilderQ.lineTo(17.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderQ, 4.0f, 4.0f, 17.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _repeatOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
