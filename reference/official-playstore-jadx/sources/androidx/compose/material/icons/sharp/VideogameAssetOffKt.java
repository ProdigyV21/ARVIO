package androidx.compose.material.icons.sharp;

import a0.b;
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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_videogameAssetOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VideogameAssetOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getVideogameAssetOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VideogameAssetOffKt {
    private static ImageVector _videogameAssetOff;

    public static final ImageVector getVideogameAssetOff(Icons.Sharp sharp) {
        ImageVector imageVector = _videogameAssetOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.VideogameAssetOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = b.g(20.83f, 18.0f, 22.0f, 6.0f, 8.83f);
        c.z(pathBuilderG, 20.83f, 18.0f, 17.5f, 9.0f);
        pathBuilderG.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderG.reflectiveCurveTo(18.33f, 12.0f, 17.5f, 12.0f);
        pathBuilderG.reflectiveCurveTo(16.0f, 11.33f, 16.0f, 10.5f);
        pathBuilderG.reflectiveCurveTo(16.67f, 9.0f, 17.5f, 9.0f);
        a.p(pathBuilderG, 21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderG.lineTo(1.39f, 4.22f);
        v.b.m(pathBuilderG, 3.17f, 6.0f, 2.0f, 12.0f);
        pathBuilderG.horizontalLineToRelative(13.17f);
        d.C(pathBuilderG, 4.61f, 4.61f, 21.19f, 21.19f);
        c.u(pathBuilderG, 9.0f, 13.0f, 2.0f, 7.0f);
        a0.a.A(pathBuilderG, -2.0f, 5.0f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.e(pathBuilderG, 9.83f, 10.17f, 13.0f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _videogameAssetOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
