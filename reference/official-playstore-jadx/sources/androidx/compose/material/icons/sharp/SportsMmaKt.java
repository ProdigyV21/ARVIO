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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsMma", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsMma", "Landroidx/compose/material/icons/Icons$Sharp;", "getSportsMma", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsMmaKt {
    private static ImageVector _sportsMma;

    public static final ImageVector getSportsMma(Icons.Sharp sharp) {
        ImageVector imageVector = _sportsMma;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SportsMma", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(7.0f, 17.0f, 10.0f, 4.0f, -10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.0f, 7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.curveTo(5.9f, 3.0f, 5.0f, 3.9f, 5.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(5.8f);
        pathBuilderA.curveToRelative(0.0f, 0.13f, 0.01f, 0.26f, 0.04f, 0.39f);
        pathBuilderA.lineToRelative(0.8f, 4.0f);
        pathBuilderA.curveToRelative(0.09f, 0.47f, 0.5f, 0.8f, 0.98f, 0.8f);
        pathBuilderA.horizontalLineToRelative(10.36f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.89f, -0.36f, 0.98f, -0.8f);
        pathBuilderA.lineToRelative(0.8f, -4.0f);
        pathBuilderA.curveTo(18.99f, 11.06f, 19.0f, 10.93f, 19.0f, 10.8f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveTo(19.0f, 7.45f, 18.55f, 7.0f, 18.0f, 7.0f);
        f.o(pathBuilderA, 15.0f, 10.0f, 7.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 8.0f, 10.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsMma = imageVectorBuild;
        return imageVectorBuild;
    }
}
