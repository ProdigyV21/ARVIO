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
import v.a;
import v.b;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bed", "Landroidx/compose/material/icons/Icons$Outlined;", "getBed", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BedKt {
    private static ImageVector _bed;

    public static final ImageVector getBed(Icons.Outlined outlined) {
        ImageVector imageVector = _bed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Bed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(21.0f, 10.78f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -1.65f, -1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(-0.77f, 0.0f, -1.47f, 0.3f, -2.0f, 0.78f);
        pathBuilderA.curveTo(11.47f, 5.3f, 10.77f, 5.0f, 10.0f, 5.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveTo(4.35f, 5.0f, 3.0f, 6.35f, 3.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(2.78f);
        pathBuilderA.curveTo(2.39f, 11.33f, 2.0f, 12.12f, 2.0f, 13.0f);
        b.z(pathBuilderA, 6.0f, 2.0f, -2.0f, 16.0f);
        e.l(pathBuilderA, 2.0f, 2.0f, -6.0f);
        pathBuilderA.curveTo(22.0f, 12.12f, 21.61f, 11.33f, 21.0f, 10.78f);
        a.g(pathBuilderA, 14.0f, 7.0f, 4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        f.t(pathBuilderA, 2.0f, -6.0f, 8.0f);
        pathBuilderA.curveTo(13.0f, 7.45f, 13.45f, 7.0f, 14.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        d.u(pathBuilderA, 2.0f, 5.0f, 8.0f);
        pathBuilderA.moveTo(4.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderA, 2.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bed = imageVectorBuild;
        return imageVectorBuild;
    }
}
