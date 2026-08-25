package androidx.compose.material.icons.filled;

import a0.a;
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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_singleBed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SingleBed", "Landroidx/compose/material/icons/Icons$Filled;", "getSingleBed", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SingleBedKt {
    private static ImageVector _singleBed;

    public static final ImageVector getSingleBed(Icons.Filled filled) {
        ImageVector imageVector = _singleBed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SingleBed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveTo(6.9f, 5.0f, 6.0f, 5.9f, 6.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        c.w(pathBuilderA, 5.0f, 1.33f, 6.0f, 19.0f);
        e.C(pathBuilderA, 1.0f, 0.67f, -2.0f, 8.67f);
        pathBuilderA.lineTo(17.0f, 19.0f);
        a.n(pathBuilderA, 1.0f, 0.67f, -2.0f, 20.0f);
        v.a.D(pathBuilderA, 12.0f, 16.0f, 10.0f, -3.0f);
        f.k(pathBuilderA, 7.0f, 3.0f, 10.0f);
        v.a.C(pathBuilderA, 8.0f, 7.0f, 3.0f, 3.0f);
        c.l(pathBuilderA, 8.0f, 7.0f, 6.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.k(pathBuilderA, 12.0f, 3.0f, 6.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _singleBed = imageVectorBuild;
        return imageVectorBuild;
    }
}
