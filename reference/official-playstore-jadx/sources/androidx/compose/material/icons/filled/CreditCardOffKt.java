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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_creditCardOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CreditCardOff", "Landroidx/compose/material/icons/Icons$Filled;", "getCreditCardOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CreditCardOffKt {
    private static ImageVector _creditCardOff;

    public static final ImageVector getCreditCardOff(Icons.Filled filled) {
        ImageVector imageVector = _creditCardOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.CreditCardOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.9f, 21.9f, 2.1f, 2.1f);
        pathBuilderJ.lineTo(0.69f, 3.51f);
        pathBuilderJ.lineToRelative(1.55f, 1.55f);
        pathBuilderJ.curveTo(2.09f, 5.34f, 2.01f, 5.66f, 2.01f, 6.0f);
        pathBuilderJ.lineTo(2.0f, 18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(13.17f);
        d.C(pathBuilderJ, 3.31f, 3.31f, 21.9f, 21.9f);
        e.o(pathBuilderJ, 4.0f, 12.0f, 8.0f, 1.17f);
        f.v(pathBuilderJ, 4.0f, 4.0f, 4.0f);
        pathBuilderJ.moveTo(6.83f, 4.0f);
        pathBuilderJ.horizontalLineTo(20.0f);
        pathBuilderJ.curveToRelative(1.11f, 0.0f, 2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.34f, -0.08f, 0.66f, -0.23f, 0.94f);
        b.p(pathBuilderJ, 14.83f, 12.0f, 20.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderJ, -9.17f, 6.83f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _creditCardOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
