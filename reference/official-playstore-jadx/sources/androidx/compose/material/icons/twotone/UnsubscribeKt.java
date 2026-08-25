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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_unsubscribe", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Unsubscribe", "Landroidx/compose/material/icons/Icons$TwoTone;", "getUnsubscribe", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnsubscribeKt {
    private static ImageVector _unsubscribe;

    public static final ImageVector getUnsubscribe(Icons.TwoTone twoTone) {
        ImageVector imageVector = _unsubscribe;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Unsubscribe", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.99f, 5.0f, 5.0f, 5.0f);
        b.C(pathBuilderJ, 7.0f, 3.5f, 19.0f, 13.05f);
        pathBuilderJ.lineTo(19.0f, 7.0f);
        pathBuilderJ.lineToRelative(-7.0f, 3.5f);
        a.h(pathBuilderJ, 5.0f, 7.0f, 8.0f, 10.35f);
        pathBuilderJ.curveToRelative(0.56f, -1.18f, 1.76f, -2.0f, 3.15f, -2.0f);
        pathBuilderJ.curveToRelative(0.17f, 0.0f, 0.34f, 0.03f, 0.5f, 0.05f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(20.99f, 14.04f, 20.99f, 5.0f);
        pathBuilderJ2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ2.lineTo(5.0f, 3.0f);
        pathBuilderJ2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ2.verticalLineToRelative(10.0f);
        pathBuilderJ2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ2.horizontalLineToRelative(10.05f);
        pathBuilderJ2.curveToRelative(0.28f, 1.92f, 2.1f, 3.35f, 4.18f, 2.93f);
        pathBuilderJ2.curveToRelative(1.34f, -0.27f, 2.43f, -1.37f, 2.7f, -2.71f);
        pathBuilderJ2.curveToRelative(0.25f, -1.24f, -0.16f, -2.39f, -0.94f, -3.18f);
        a.p(pathBuilderJ2, 18.99f, 5.0f, 12.0f, 8.5f);
        f.n(pathBuilderJ2, 5.0f, 5.0f, 13.99f);
        pathBuilderJ2.moveTo(15.35f, 15.0f);
        pathBuilderJ2.lineTo(5.0f, 15.0f);
        pathBuilderJ2.lineTo(5.0f, 7.0f);
        pathBuilderJ2.lineToRelative(7.0f, 3.5f);
        pathBuilderJ2.lineTo(19.0f, 7.0f);
        pathBuilderJ2.verticalLineToRelative(6.05f);
        pathBuilderJ2.curveToRelative(-0.16f, -0.02f, -0.33f, -0.05f, -0.5f, -0.05f);
        pathBuilderJ2.curveToRelative(-1.39f, 0.0f, -2.59f, 0.82f, -3.15f, 2.0f);
        b.w(pathBuilderJ2, 20.5f, 17.0f, -4.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderJ2, 4.0f, 1.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _unsubscribe = imageVectorBuild;
        return imageVectorBuild;
    }
}
