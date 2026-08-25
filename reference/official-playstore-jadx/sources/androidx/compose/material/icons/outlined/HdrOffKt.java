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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getHdrOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrOffKt {
    private static ImageVector _hdrOff;

    public static final ImageVector getHdrOff(Icons.Outlined outlined) {
        ImageVector imageVector = _hdrOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.HdrOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(17.5f, 15.0f, -2.0f, 1.1f);
        pathBuilderA.lineToRelative(0.9f, 2.0f);
        pathBuilderA.lineTo(21.0f, 15.0f);
        pathBuilderA.lineToRelative(-0.9f, -2.1f);
        pathBuilderA.curveToRelative(0.5f, -0.3f, 0.9f, -0.8f, 0.9f, -1.4f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.8f, -0.7f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.lineTo(16.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(4.86f);
        pathBuilderA.lineTo(17.14f, 15.0f);
        pathBuilderA.horizontalLineToRelative(0.36f);
        pathBuilderA.close();
        a.C(pathBuilderA, 17.5f, 10.5f, 2.0f, 1.0f);
        b.B(pathBuilderA, -2.0f, -1.0f, 13.0f, 10.5f);
        pathBuilderA.verticalLineToRelative(0.36f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.lineTo(14.5f, 10.5f);
        pathBuilderA.curveToRelative(0.0f, -0.8f, -0.7f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.horizontalLineToRelative(-1.86f);
        d.C(pathBuilderA, 1.5f, 1.5f, 13.0f, 10.5f);
        pathBuilderA.moveTo(2.51f, 2.49f);
        pathBuilderA.lineTo(1.45f, 3.55f);
        b.A(pathBuilderA, 6.9f, 9.0f, -0.4f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.lineTo(4.5f, 9.0f);
        a.h(pathBuilderA, 3.0f, 9.0f, 6.0f, 1.5f);
        c.w(pathBuilderA, -2.5f, 2.0f, 6.5f, 15.0f);
        pathBuilderA.lineTo(8.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(-4.9f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.lineTo(9.5f, 15.0f);
        pathBuilderA.horizontalLineToRelative(3.4f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 7.6f, 7.6f, 1.06f, -1.06f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hdrOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
