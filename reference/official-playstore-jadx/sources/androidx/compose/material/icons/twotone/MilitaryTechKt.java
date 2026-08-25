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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_militaryTech", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MilitaryTech", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMilitaryTech", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MilitaryTechKt {
    private static ImageVector _militaryTech;

    public static final ImageVector getMilitaryTech(Icons.TwoTone twoTone) {
        ImageVector imageVector = _militaryTech;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MilitaryTech", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(13.0f, 11.07f, 2.0f, -1.2f), 0.0f, -5.87f, -2.0f, 0.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(9.0f, 4.0f, 0.0f, 5.87f), 2.0f, 1.2f, 0.0f, -7.07f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = y.a.j(17.0f, 10.43f, 2.0f, 7.0f, 8.43f);
        pathBuilderJ.curveToRelative(0.0f, 0.35f, 0.18f, 0.68f, 0.49f, 0.86f);
        pathBuilderJ.lineToRelative(4.18f, 2.51f);
        pathBuilderJ.lineToRelative(-0.99f, 2.34f);
        pathBuilderJ.lineToRelative(-3.41f, 0.29f);
        pathBuilderJ.lineToRelative(2.59f, 2.24f);
        pathBuilderJ.lineTo(9.07f, 22.0f);
        pathBuilderJ.lineTo(12.0f, 20.23f);
        pathBuilderJ.lineTo(14.93f, 22.0f);
        pathBuilderJ.lineToRelative(-0.78f, -3.33f);
        pathBuilderJ.lineToRelative(2.59f, -2.24f);
        pathBuilderJ.lineToRelative(-3.41f, -0.29f);
        pathBuilderJ.lineToRelative(-0.99f, -2.34f);
        pathBuilderJ.lineToRelative(4.18f, -2.51f);
        pathBuilderJ.curveTo(16.82f, 11.11f, 17.0f, 10.79f, 17.0f, 10.43f);
        a.z(pathBuilderJ, 11.0f, 11.07f, -2.0f, -1.2f);
        f.k(pathBuilderJ, 4.0f, 2.0f, 11.07f);
        pathBuilderJ.moveTo(15.0f, 9.87f);
        b.o(pathBuilderJ, -2.0f, 1.2f, 4.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderJ, 9.87f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _militaryTech = imageVectorBuild;
        return imageVectorBuild;
    }
}
