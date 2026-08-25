package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_taxiAlert", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TaxiAlert", "Landroidx/compose/material/icons/Icons$Filled;", "getTaxiAlert", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TaxiAlertKt {
    private static ImageVector _taxiAlert;

    public static final ImageVector getTaxiAlert(Icons.Filled filled) {
        ImageVector imageVector = _taxiAlert;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TaxiAlert", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(23.0f, 8.0f);
        pathBuilderA.arcTo(7.0f, 7.0f, 0.0f, false, false, 9.68f, 5.0f);
        pathBuilderA.lineTo(7.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.lineTo(4.5f, 7.0f);
        pathBuilderA.arcToRelative(1.5f, 1.5f, 0.0f, false, false, -1.42f, 1.01f);
        pathBuilderA.lineTo(1.0f, 14.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, -1.0f);
        e.l(pathBuilderA, -1.0f, 12.0f, 1.0f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-7.68f);
        pathBuilderA.arcTo(7.01f, 7.01f, 0.0f, false, false, 23.0f, 8.0f);
        a.g(pathBuilderA, 4.5f, 8.5f, 4.53f);
        pathBuilderA.arcToRelative(6.93f, 6.93f, 0.0f, false, false, 2.08f, 4.5f);
        d.l(pathBuilderA, 3.0f, 13.0f, 1.5f, -4.5f);
        pathBuilderA.moveTo(4.5f, 18.0f);
        pathBuilderA.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 0.0f, -3.0f);
        pathBuilderA.arcToRelative(1.5f, 1.5f, 0.0f, false, true, 0.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.5f, 18.0f);
        pathBuilderA.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 0.0f, -3.0f);
        pathBuilderA.arcToRelative(1.5f, 1.5f, 0.0f, false, true, 0.0f, 3.0f);
        a.z(pathBuilderA, 18.43f, 12.37f, -0.21f, 0.11f);
        pathBuilderA.lineToRelative(-0.18f, 0.09f);
        pathBuilderA.arcToRelative(4.97f, 4.97f, 0.0f, false, true, -0.42f, 0.16f);
        pathBuilderA.lineToRelative(-0.22f, 0.07f);
        pathBuilderA.lineToRelative(-0.23f, 0.06f);
        pathBuilderA.lineToRelative(-0.2f, 0.05f);
        pathBuilderA.arcToRelative(5.0f, 5.0f, 0.0f, false, true, -5.94f, -4.41f);
        pathBuilderA.arcTo(4.07f, 4.07f, 0.0f, false, true, 11.0f, 8.0f);
        pathBuilderA.lineToRelative(0.02f, -0.47f);
        pathBuilderA.lineToRelative(0.02f, -0.17f);
        pathBuilderA.lineToRelative(0.04f, -0.28f);
        pathBuilderA.lineToRelative(0.04f, -0.21f);
        pathBuilderA.lineToRelative(0.05f, -0.21f);
        pathBuilderA.lineToRelative(0.07f, -0.24f);
        pathBuilderA.lineToRelative(0.05f, -0.13f);
        pathBuilderA.arcToRelative(4.99f, 4.99f, 0.0f, false, true, 9.69f, 1.7f);
        pathBuilderA.arcToRelative(4.96f, 4.96f, 0.0f, false, true, -2.55f, 4.38f);
        b.w(pathBuilderA, 15.0f, 4.0f, 2.0f, 5.0f);
        e.r(pathBuilderA, -2.0f, 15.0f, 10.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderA, 2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _taxiAlert = imageVectorBuild;
        return imageVectorBuild;
    }
}
