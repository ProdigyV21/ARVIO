package androidx.compose.material.icons.sharp;

import a0.a;
import a0.b;
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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pinInvoke", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PinInvoke", "Landroidx/compose/material/icons/Icons$Sharp;", "getPinInvoke", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinInvokeKt {
    private static ImageVector _pinInvoke;

    public static final ImageVector getPinInvoke(Icons.Sharp sharp) {
        ImageVector imageVector = _pinInvoke;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PinInvoke", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = f.b(22.0f, 12.0f, 8.0f, 2.0f, 4.0f);
        a.n(pathBuilderB, 12.0f, 0.0f, 2.0f, 4.0f);
        b.f(pathBuilderB, 12.0f, 16.0f, -6.0f, 22.0f);
        pathBuilderB.moveTo(22.0f, 7.0f);
        pathBuilderB.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderB.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderB.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderB.curveTo(20.66f, 10.0f, 22.0f, 8.66f, 22.0f, 7.0f);
        v.a.z(pathBuilderB, 11.47f, 12.12f, -2.83f, 2.83f);
        pathBuilderB.lineToRelative(1.41f, 1.41f);
        pathBuilderB.lineToRelative(2.83f, -2.83f);
        c.f(pathBuilderB, 15.0f, 15.66f, 10.0f, 9.34f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderB, 11.47f, 12.12f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pinInvoke = imageVectorBuild;
        return imageVectorBuild;
    }
}
