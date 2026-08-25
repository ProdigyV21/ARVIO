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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_construction", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Construction", "Landroidx/compose/material/icons/Icons$Sharp;", "getConstruction", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConstructionKt {
    private static ImageVector _construction;

    public static final ImageVector getConstruction(Icons.Sharp sharp) {
        ImageVector imageVector = _construction;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Construction", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(13.783f, 15.172f, 2.121f, -2.121f), 5.996f, 5.996f, -2.121f, 2.121f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.5f, 10.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.curveToRelative(0.0f, -0.58f, -0.16f, -1.12f, -0.41f, -1.6f);
        pathBuilderA.lineToRelative(-2.7f, 2.7f);
        pathBuilderA.lineTo(16.4f, 6.11f);
        pathBuilderA.lineToRelative(2.7f, -2.7f);
        pathBuilderA.curveTo(18.62f, 3.16f, 18.08f, 3.0f, 17.5f, 3.0f);
        pathBuilderA.curveTo(15.57f, 3.0f, 14.0f, 4.57f, 14.0f, 6.5f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.08f, 0.8f, 0.21f, 1.16f);
        pathBuilderA.lineToRelative(-1.85f, 1.85f);
        pathBuilderA.lineToRelative(-1.78f, -1.78f);
        pathBuilderA.lineToRelative(0.71f, -0.71f);
        pathBuilderA.lineTo(9.88f, 5.61f);
        pathBuilderA.lineTo(12.0f, 3.49f);
        pathBuilderA.curveToRelative(-1.17f, -1.17f, -3.07f, -1.17f, -4.24f, 0.0f);
        pathBuilderA.lineTo(4.22f, 7.03f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.horizontalLineTo(2.81f);
        pathBuilderA.lineTo(2.1f, 9.15f);
        pathBuilderA.lineToRelative(3.54f, 3.54f);
        pathBuilderA.lineToRelative(0.71f, -0.71f);
        pathBuilderA.verticalLineTo(9.15f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.lineToRelative(0.71f, -0.71f);
        pathBuilderA.lineToRelative(1.78f, 1.78f);
        pathBuilderA.lineToRelative(-7.41f, 7.41f);
        pathBuilderA.lineToRelative(2.12f, 2.12f);
        pathBuilderA.lineTo(16.34f, 9.79f);
        pathBuilderA.curveTo(16.7f, 9.92f, 17.09f, 10.0f, 17.5f, 10.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _construction = imageVectorBuild;
        return imageVectorBuild;
    }
}
