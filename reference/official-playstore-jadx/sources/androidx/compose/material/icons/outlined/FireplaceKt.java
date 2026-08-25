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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fireplace", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Fireplace", "Landroidx/compose/material/icons/Icons$Outlined;", "getFireplace", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FireplaceKt {
    private static ImageVector _fireplace;

    public static final ImageVector getFireplace(Icons.Outlined outlined) {
        ImageVector imageVector = _fireplace;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Fireplace", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.01f, 12.46f);
        pathBuilderA.curveToRelative(-0.15f, 0.42f, -0.15f, 0.82f, -0.08f, 1.28f);
        pathBuilderA.curveToRelative(0.1f, 0.55f, 0.33f, 1.04f, 0.2f, 1.6f);
        pathBuilderA.curveToRelative(-0.13f, 0.59f, -0.77f, 1.38f, -1.53f, 1.63f);
        pathBuilderA.curveToRelative(1.28f, 1.05f, 3.2f, 0.37f, 3.39f, -1.32f);
        pathBuilderA.curveTo(14.16f, 14.11f, 12.55f, 13.67f, 12.01f, 12.46f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(2.0f, 2.0f, 20.0f, 20.0f, 2.0f);
        v.a.x(pathBuilderQ, 2.0f, 12.0f, 18.0f);
        pathBuilderQ.curveToRelative(-1.58f, 0.0f, -2.97f, -1.88f, -3.0f, -3.06f);
        pathBuilderQ.curveToRelative(0.0f, -0.05f, -0.01f, -0.13f, -0.01f, -0.22f);
        pathBuilderQ.curveToRelative(-0.13f, -1.73f, 1.0f, -3.2f, 2.47f, -4.37f);
        pathBuilderQ.curveToRelative(0.47f, 1.01f, 1.27f, 2.03f, 2.57f, 2.92f);
        pathBuilderQ.curveTo(14.61f, 13.69f, 15.0f, 14.13f, 15.0f, 15.0f);
        pathBuilderQ.curveTo(15.0f, 16.65f, 13.65f, 18.0f, 12.0f, 18.0f);
        b.w(pathBuilderQ, 20.0f, 20.0f, -2.0f, -2.0f);
        pathBuilderQ.horizontalLineToRelative(-2.02f);
        pathBuilderQ.curveToRelative(0.63f, -0.84f, 1.02f, -1.87f, 1.02f, -3.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.89f, -1.09f, -2.85f, -1.85f, -3.37f);
        pathBuilderQ.curveTo(12.2f, 9.61f, 13.0f, 7.0f, 13.0f, 7.0f);
        pathBuilderQ.curveToRelative(-6.73f, 3.57f, -6.02f, 7.47f, -6.0f, 8.0f);
        pathBuilderQ.curveToRelative(0.03f, 0.96f, 0.49f, 2.07f, 1.23f, 3.0f);
        a0.b.C(pathBuilderQ, 6.0f, 2.0f, 4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 16.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fireplace = imageVectorBuild;
        return imageVectorBuild;
    }
}
