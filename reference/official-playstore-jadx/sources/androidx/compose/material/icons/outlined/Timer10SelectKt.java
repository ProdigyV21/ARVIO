package androidx.compose.material.icons.outlined;

import a0.c;
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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_timer10Select", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Timer10Select", "Landroidx/compose/material/icons/Icons$Outlined;", "getTimer10Select", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Timer10SelectKt {
    private static ImageVector _timer10Select;

    public static final ImageVector getTimer10Select(Icons.Outlined outlined) {
        ImageVector imageVector = _timer10Select;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Timer10Select", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(13.0f, 8.0f, 8.0f, -3.0f, 8.0f);
        pathBuilderQ.horizontalLineTo(13.0f);
        pathBuilderQ.moveTo(13.0f, 5.0f);
        pathBuilderQ.horizontalLineToRelative(-3.0f);
        pathBuilderQ.curveTo(8.34f, 5.0f, 7.0f, 6.34f, 7.0f, 8.0f);
        pathBuilderQ.verticalLineToRelative(8.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderQ.horizontalLineToRelative(3.0f);
        pathBuilderQ.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderQ.verticalLineTo(8.0f);
        pathBuilderQ.curveTo(16.0f, 6.34f, 14.66f, 5.0f, 13.0f, 5.0f);
        b.w(pathBuilderQ, 1.0f, 8.0f, 2.0f, 11.0f);
        c.D(pathBuilderQ, 3.0f, 5.0f, 1.0f, 8.0f);
        pathBuilderQ.moveTo(18.5f, 11.0f);
        pathBuilderQ.curveToRelative(-0.83f, 0.0f, -1.5f, 0.68f, -1.5f, 1.5f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.82f, 0.67f, 1.5f, 1.5f, 1.5f);
        b.q(pathBuilderQ, 21.0f, 1.0f, -4.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(4.5f);
        pathBuilderQ.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderQ.verticalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        b.q(pathBuilderQ, 19.0f, -1.0f, 4.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderQ, 18.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _timer10Select = imageVectorBuild;
        return imageVectorBuild;
    }
}
