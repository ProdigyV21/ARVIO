package androidx.compose.material.icons.twotone;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_newspaper", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Newspaper", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNewspaper", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NewspaperKt {
    private static ImageVector _newspaper;

    public static final ImageVector getNewspaper(Icons.TwoTone twoTone) {
        ImageVector imageVector = _newspaper;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Newspaper", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.0f, 3.0f, -1.67f, 1.67f);
        pathBuilderR.lineTo(18.67f, 3.0f);
        pathBuilderR.lineTo(17.0f, 4.67f);
        pathBuilderR.lineTo(15.33f, 3.0f);
        pathBuilderR.lineToRelative(-1.66f, 1.67f);
        pathBuilderR.lineTo(12.0f, 3.0f);
        pathBuilderR.lineToRelative(-1.67f, 1.67f);
        pathBuilderR.lineTo(8.67f, 3.0f);
        pathBuilderR.lineTo(7.0f, 4.67f);
        pathBuilderR.lineTo(5.33f, 3.0f);
        pathBuilderR.lineTo(3.67f, 4.67f);
        pathBuilderR.lineTo(2.0f, 3.0f);
        pathBuilderR.verticalLineToRelative(16.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.lineToRelative(16.0f, 0.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        a.B(pathBuilderR, 3.0f, 11.0f, 19.0f, 4.0f);
        a.t(pathBuilderR, -6.0f, 7.0f, 19.0f);
        a.C(pathBuilderR, 20.0f, 19.0f, -7.0f, -2.0f);
        c.D(pathBuilderR, 7.0f, 19.0f, 20.0f, 15.0f);
        f.w(pathBuilderR, -7.0f, -2.0f, 7.0f, 15.0f);
        c.B(pathBuilderR, 20.0f, 11.0f, 4.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderR, 16.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _newspaper = imageVectorBuild;
        return imageVectorBuild;
    }
}
