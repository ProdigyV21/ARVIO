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
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flip", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flip", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFlip", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlipKt {
    private static ImageVector _flip;

    public static final ImageVector getFlip(Icons.TwoTone twoTone) {
        ImageVector imageVector = _flip;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Flip", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(19.0f, 7.0f, 2.0f, 2.0f, -2.0f);
        pathBuilderB.moveTo(19.0f, 21.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        b.B(pathBuilderB, -2.0f, 2.0f, 19.0f, 15.0f);
        d.x(pathBuilderB, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderB, 19.0f, 11.0f, 2.0f, 2.0f);
        f.x(pathBuilderB, -2.0f, 9.0f, 5.0f);
        pathBuilderB.lineTo(9.0f, 3.0f);
        pathBuilderB.lineTo(5.0f, 3.0f);
        pathBuilderB.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderB.verticalLineToRelative(14.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        c.q(pathBuilderB, 4.0f, -2.0f, 5.0f, 19.0f);
        f.n(pathBuilderB, 5.0f, 5.0f, 4.0f);
        c.m(pathBuilderB, 19.0f, 3.0f, 2.0f, 2.0f);
        pathBuilderB.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        b.w(pathBuilderB, 11.0f, 1.0f, 2.0f, 22.0f);
        e.r(pathBuilderB, -2.0f, 15.0f, 3.0f, 2.0f);
        e.D(pathBuilderB, 2.0f, -2.0f, 15.0f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.j(pathBuilderB, 2.0f, 2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flip = imageVectorBuild;
        return imageVectorBuild;
    }
}
