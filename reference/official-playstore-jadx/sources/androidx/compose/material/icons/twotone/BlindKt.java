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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_blind", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Blind", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBlind", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlindKt {
    private static ImageVector _blind;

    public static final ImageVector getBlind(Icons.TwoTone twoTone) {
        ImageVector imageVector = _blind;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Blind", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(11.5f, 3.5f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.13f, 7.12f);
        pathBuilderA.curveToRelative(-0.17f, -0.35f, -0.44f, -0.65f, -0.8f, -0.85f);
        pathBuilderA.curveTo(10.72f, 5.91f, 9.99f, 5.93f, 9.4f, 6.24f);
        pathBuilderA.lineToRelative(0.0f, -0.01f);
        b.n(pathBuilderA, 4.0f, 9.3f, 14.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(-3.54f);
        pathBuilderA.lineToRelative(1.5f, -0.85f);
        pathBuilderA.curveTo(7.18f, 10.71f, 7.0f, 11.85f, 7.0f, 13.0f);
        pathBuilderA.verticalLineToRelative(5.33f);
        pathBuilderA.lineTo(4.4f, 21.8f);
        pathBuilderA.lineTo(6.0f, 23.0f);
        pathBuilderA.lineToRelative(3.0f, -4.0f);
        pathBuilderA.lineToRelative(0.22f, -3.54f);
        a.h(pathBuilderA, 11.0f, 18.0f, 5.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(-6.5f);
        pathBuilderA.lineToRelative(-1.97f, -2.81f);
        pathBuilderA.curveToRelative(-0.04f, -0.52f, -0.14f, -1.76f, 0.45f, -3.4f);
        pathBuilderA.curveToRelative(0.75f, 1.14f, 1.88f, 1.98f, 3.2f, 2.41f);
        pathBuilderA.lineTo(20.63f, 23.0f);
        pathBuilderA.lineToRelative(0.87f, -0.5f);
        b.m(pathBuilderA, 16.02f, 13.0f, 17.0f, -2.0f);
        pathBuilderA.curveToRelative(-0.49f, 0.0f, -2.88f, 0.17f, -4.08f, -2.21f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _blind = imageVectorBuild;
        return imageVectorBuild;
    }
}
