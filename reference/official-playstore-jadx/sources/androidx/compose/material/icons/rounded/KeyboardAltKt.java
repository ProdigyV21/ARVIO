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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_keyboardAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "KeyboardAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getKeyboardAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyboardAltKt {
    private static ImageVector _keyboardAlt;

    public static final ImageVector getKeyboardAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _keyboardAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.KeyboardAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(21.0f, 4.0f, 3.0f);
        pathBuilderQ.curveTo(1.9f, 4.0f, 1.0f, 4.9f, 1.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(13.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(23.0f, 4.9f, 22.1f, 4.0f, 21.0f, 4.0f);
        a0.a.z(pathBuilderQ, 7.0f, 12.0f, 2.0f, 5.0f);
        e.y(pathBuilderQ, -2.0f, 7.0f, 5.0f, 10.0f);
        f.z(pathBuilderQ, 8.0f, 2.0f, 2.0f, 5.0f);
        c.u(pathBuilderQ, 11.0f, 12.0f, 2.0f, 9.0f);
        e.y(pathBuilderQ, -2.0f, 11.0f, 9.0f, 10.0f);
        f.z(pathBuilderQ, 8.0f, 2.0f, 2.0f, 9.0f);
        pathBuilderQ.moveTo(16.0f, 16.5f);
        pathBuilderQ.lineTo(16.0f, 16.5f);
        pathBuilderQ.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderQ.horizontalLineToRelative(-7.0f);
        pathBuilderQ.curveTo(8.22f, 17.0f, 8.0f, 16.78f, 8.0f, 16.5f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveTo(8.0f, 16.22f, 8.22f, 16.0f, 8.5f, 16.0f);
        pathBuilderQ.horizontalLineToRelative(7.0f);
        pathBuilderQ.curveTo(15.78f, 16.0f, 16.0f, 16.22f, 16.0f, 16.5f);
        c.y(pathBuilderQ, 15.0f, 12.0f, 2.0f, -2.0f);
        e.y(pathBuilderQ, -2.0f, 15.0f, 13.0f, 10.0f);
        f.z(pathBuilderQ, 8.0f, 2.0f, 2.0f, 13.0f);
        c.m(pathBuilderQ, 17.0f, 14.0f, -2.0f, 2.0f);
        e.y(pathBuilderQ, 2.0f, 17.0f, 19.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.v(pathBuilderQ, -2.0f, 8.0f, 2.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _keyboardAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
