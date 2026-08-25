package androidx.compose.material.icons.automirrored.twotone;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatIndentDecrease", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatIndentDecrease", "Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;", "getFormatIndentDecrease", "(Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatIndentDecreaseKt {
    private static ImageVector _formatIndentDecrease;

    public static final ImageVector getFormatIndentDecrease(Icons.AutoMirrored.TwoTone twoTone) {
        ImageVector imageVector = _formatIndentDecrease;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.TwoTone.FormatIndentDecrease", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.0f, 16.0f, 7.0f, 8.0f);
        b.C(pathBuilderJ, -4.0f, 4.0f, 11.0f, 7.0f);
        d.m(pathBuilderJ, 10.0f, 2.0f, 11.0f, 9.0f);
        a.C(pathBuilderJ, 11.0f, 11.0f, 10.0f, 2.0f);
        c.z(pathBuilderJ, 11.0f, 13.0f, 11.0f, 15.0f);
        d.m(pathBuilderJ, 10.0f, 2.0f, 11.0f, 17.0f);
        a.C(pathBuilderJ, 3.0f, 19.0f, 18.0f, 2.0f);
        c.z(pathBuilderJ, 3.0f, 21.0f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.w(pathBuilderJ, 18.0f, 2.0f, 3.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatIndentDecrease = imageVectorBuild;
        return imageVectorBuild;
    }
}
