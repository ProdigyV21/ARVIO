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
import v.b;
import v.c;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_blinds", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Blinds", "Landroidx/compose/material/icons/Icons$Outlined;", "getBlinds", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlindsKt {
    private static ImageVector _blinds;

    public static final ImageVector getBlinds(Icons.Outlined outlined) {
        ImageVector imageVector = _blinds;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Blinds", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 19.0f, 3.0f, 4.0f, 16.0f);
        b.q(pathBuilderJ, 2.0f, 2.0f, 20.0f, -2.0f);
        w.a.v(pathBuilderJ, 20.0f, 16.0f, 9.0f, 2.0f);
        v.a.t(pathBuilderJ, 2.0f, -2.0f, 9.0f);
        c.B(pathBuilderJ, 14.0f, 11.0f, 6.0f, 9.0f);
        c.D(pathBuilderJ, 8.0f, 11.0f, 18.0f, 7.0f);
        f.A(pathBuilderJ, -2.0f, 5.0f, 2.0f, 7.0f);
        c.u(pathBuilderJ, 14.0f, 5.0f, 2.0f, 6.0f);
        c.n(pathBuilderJ, 5.0f, 14.0f, 6.0f, 19.0f);
        e.l(pathBuilderJ, -6.0f, 8.0f, 1.82f);
        pathBuilderJ.curveToRelative(-0.45f, 0.32f, -0.75f, 0.84f, -0.75f, 1.43f);
        pathBuilderJ.curveToRelative(0.0f, 0.97f, 0.78f, 1.75f, 1.75f, 1.75f);
        pathBuilderJ.reflectiveCurveToRelative(1.75f, -0.78f, 1.75f, -1.75f);
        pathBuilderJ.curveToRelative(0.0f, -0.59f, -0.3f, -1.12f, -0.75f, -1.43f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.b.d(pathBuilderJ, 13.0f, 2.0f, 6.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _blinds = imageVectorBuild;
        return imageVectorBuild;
    }
}
