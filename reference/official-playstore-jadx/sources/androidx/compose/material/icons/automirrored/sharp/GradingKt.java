package androidx.compose.material.icons.automirrored.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_grading", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Grading", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getGrading", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GradingKt {
    private static ImageVector _grading;

    public static final ImageVector getGrading(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _grading;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.Grading", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(4.0f, 7.0f, 16.0f, 2.0f, 4.0f);
        a.D(pathBuilderH, 7.0f, 4.0f, 13.0f, 16.0f);
        d.u(pathBuilderH, -2.0f, 4.0f, 13.0f);
        a.C(pathBuilderH, 4.0f, 17.0f, 7.0f, -2.0f);
        c.l(pathBuilderH, 4.0f, 17.0f, 4.0f, 21.0f);
        d.z(pathBuilderH, 7.0f, -2.0f, 4.0f, 21.0f);
        pathBuilderH.moveTo(15.41f, 18.17f);
        pathBuilderH.lineTo(14.0f, 16.75f);
        pathBuilderH.lineToRelative(-1.41f, 1.41f);
        pathBuilderH.lineTo(15.41f, 21.0f);
        pathBuilderH.lineTo(20.0f, 16.42f);
        b.y(pathBuilderH, 18.58f, 15.0f, 15.41f, 18.17f);
        c.m(pathBuilderH, 4.0f, 3.0f, 2.0f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderH, 3.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _grading = imageVectorBuild;
        return imageVectorBuild;
    }
}
