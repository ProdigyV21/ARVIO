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
import v.b;
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_compareArrows", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CompareArrows", "Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;", "getCompareArrows", "(Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompareArrowsKt {
    private static ImageVector _compareArrows;

    public static final ImageVector getCompareArrows(Icons.AutoMirrored.TwoTone twoTone) {
        ImageVector imageVector = _compareArrows;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.TwoTone.CompareArrows", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(9.01f, 14.0f, 2.0f, 14.0f, 2.0f);
        c.q(pathBuilderS, 7.01f, 3.0f, 13.0f, 15.0f);
        f.p(pathBuilderS, -3.99f, -4.0f, 3.0f);
        pathBuilderS.moveTo(14.99f, 13.0f);
        pathBuilderS.verticalLineToRelative(-3.0f);
        pathBuilderS.lineTo(22.0f, 10.0f);
        pathBuilderS.lineTo(22.0f, 8.0f);
        pathBuilderS.horizontalLineToRelative(-7.01f);
        pathBuilderS.lineTo(14.99f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderS, 11.0f, 9.0f, 3.99f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _compareArrows = imageVectorBuild;
        return imageVectorBuild;
    }
}
