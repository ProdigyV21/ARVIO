package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_webAssetOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WebAssetOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getWebAssetOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebAssetOffKt {
    private static ImageVector _webAssetOff;

    public static final ImageVector getWebAssetOff(Icons.Sharp sharp) {
        ImageVector imageVector = _webAssetOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.WebAssetOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = f.a(6.83f, 4.0f, 22.0f, 15.17f);
        b.o(pathBuilderA, -2.0f, -2.0f, 8.0f, -9.17f);
        c.z(pathBuilderA, 6.83f, 4.0f, 20.49f, 23.31f);
        b.p(pathBuilderA, 17.17f, 20.0f, 2.0f, 4.83f);
        pathBuilderA.lineTo(0.69f, 3.51f);
        pathBuilderA.lineTo(2.1f, 2.1f);
        d.C(pathBuilderA, 19.8f, 19.8f, 20.49f, 23.31f);
        pathBuilderA.moveTo(15.17f, 18.0f);
        e.h(pathBuilderA, -10.0f, -10.0f, 4.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 15.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _webAssetOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
