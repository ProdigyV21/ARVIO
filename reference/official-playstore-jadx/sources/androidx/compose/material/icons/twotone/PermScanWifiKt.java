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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_permScanWifi", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PermScanWifi", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPermScanWifi", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PermScanWifiKt {
    private static ImageVector _permScanWifi;

    public static final ImageVector getPermScanWifi(Icons.TwoTone twoTone) {
        ImageVector imageVector = _permScanWifi;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PermScanWifi", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 5.0f);
        pathBuilderA.curveToRelative(-3.26f, 0.0f, -6.2f, 0.85f, -9.08f, 2.65f);
        pathBuilderA.lineTo(12.0f, 18.83f);
        pathBuilderA.lineToRelative(9.08f, -11.16f);
        pathBuilderA.curveTo(18.18f, 5.85f, 15.25f, 5.0f, 12.0f, 5.0f);
        b.w(pathBuilderA, 13.0f, 16.0f, -2.0f, -6.0f);
        b.B(pathBuilderA, 2.0f, 6.0f, 11.0f, 8.0f);
        b.A(pathBuilderA, 11.0f, 6.0f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderA, -2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 3.0f);
        pathBuilderA2.curveTo(6.95f, 3.0f, 3.15f, 4.85f, 0.0f, 7.23f);
        pathBuilderA2.lineTo(12.0f, 22.0f);
        pathBuilderA2.lineTo(24.0f, 7.25f);
        pathBuilderA2.curveTo(20.85f, 4.87f, 17.05f, 3.0f, 12.0f, 3.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(2.92f, 7.65f);
        pathBuilderA2.curveTo(5.8f, 5.85f, 8.74f, 5.0f, 12.0f, 5.0f);
        pathBuilderA2.curveToRelative(3.25f, 0.0f, 6.18f, 0.85f, 9.08f, 2.67f);
        b.y(pathBuilderA2, 12.0f, 18.83f, 2.92f, 7.65f);
        a.C(pathBuilderA2, 11.0f, 10.0f, 2.0f, 6.0f);
        e.r(pathBuilderA2, -2.0f, 11.0f, 6.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderA2, 2.0f, -2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _permScanWifi = imageVectorBuild;
        return imageVectorBuild;
    }
}
