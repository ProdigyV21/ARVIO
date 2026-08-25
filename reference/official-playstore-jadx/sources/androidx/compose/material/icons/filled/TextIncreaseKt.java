package androidx.compose.material.icons.filled;

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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textIncrease", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextIncrease", "Landroidx/compose/material/icons/Icons$Filled;", "getTextIncrease", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextIncreaseKt {
    private static ImageVector _textIncrease;

    public static final ImageVector getTextIncrease(Icons.Filled filled) {
        ImageVector imageVector = _textIncrease;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TextIncrease", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(0.99f, 19.0f, 2.42f, 1.27f, -3.58f);
        c.A(pathBuilderG, 5.65f, 11.59f, 19.0f, 2.42f);
        pathBuilderG.lineTo(8.75f, 5.0f);
        d.q(pathBuilderG, -2.5f, 0.99f, 19.0f);
        pathBuilderG.moveTo(5.41f, 13.39f);
        pathBuilderG.lineTo(7.44f, 7.6f);
        b.B(pathBuilderG, 0.12f, 2.03f, 5.79f, 5.41f);
        a.C(pathBuilderG, 20.0f, 11.0f, 3.0f, 2.0f);
        a.o(pathBuilderG, -3.0f, 3.0f, -2.0f, -3.0f);
        a0.a.j(pathBuilderG, -3.0f, -2.0f, 3.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderG, 2.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textIncrease = imageVectorBuild;
        return imageVectorBuild;
    }
}
