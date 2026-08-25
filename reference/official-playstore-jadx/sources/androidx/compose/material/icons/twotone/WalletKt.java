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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wallet", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wallet", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWallet", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WalletKt {
    private static ImageVector _wallet;

    public static final ImageVector getWallet(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wallet;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Wallet", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(18.0f, 10.0f, 6.0f);
        pathBuilderQ.curveToRelative(-0.84f, 0.0f, -1.55f, 0.52f, -1.85f, 1.25f);
        pathBuilderQ.lineToRelative(11.11f, 2.72f);
        pathBuilderQ.curveToRelative(0.31f, 0.08f, 0.64f, 0.0f, 0.88f, -0.2f);
        pathBuilderQ.lineToRelative(3.49f, -2.92f);
        pathBuilderQ.curveTo(19.26f, 10.34f, 18.67f, 10.0f, 18.0f, 10.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ2 = a.q(18.0f, 6.0f, 6.0f);
        pathBuilderQ2.curveTo(4.9f, 6.0f, 4.0f, 6.9f, 4.0f, 8.0f);
        pathBuilderQ2.verticalLineToRelative(0.55f);
        pathBuilderQ2.curveTo(4.59f, 8.21f, 5.27f, 8.0f, 6.0f, 8.0f);
        pathBuilderQ2.horizontalLineToRelative(12.0f);
        pathBuilderQ2.curveToRelative(0.73f, 0.0f, 1.41f, 0.21f, 2.0f, 0.55f);
        pathBuilderQ2.verticalLineTo(8.0f);
        pathBuilderQ2.curveTo(20.0f, 6.9f, 19.1f, 6.0f, 18.0f, 6.0f);
        pathBuilderQ2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ3 = a.q(18.0f, 4.0f, 6.0f);
        pathBuilderQ3.curveTo(3.79f, 4.0f, 2.0f, 5.79f, 2.0f, 8.0f);
        pathBuilderQ3.verticalLineToRelative(8.0f);
        pathBuilderQ3.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderQ3.horizontalLineToRelative(12.0f);
        pathBuilderQ3.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderQ3.verticalLineTo(8.0f);
        pathBuilderQ3.curveTo(22.0f, 5.79f, 20.21f, 4.0f, 18.0f, 4.0f);
        pathBuilderQ3.close();
        pathBuilderQ3.moveTo(16.14f, 13.77f);
        pathBuilderQ3.curveToRelative(-0.24f, 0.2f, -0.57f, 0.28f, -0.88f, 0.2f);
        pathBuilderQ3.lineTo(4.15f, 11.25f);
        pathBuilderQ3.curveTo(4.45f, 10.52f, 5.16f, 10.0f, 6.0f, 10.0f);
        pathBuilderQ3.horizontalLineToRelative(12.0f);
        pathBuilderQ3.curveToRelative(0.67f, 0.0f, 1.26f, 0.34f, 1.63f, 0.84f);
        c.z(pathBuilderQ3, 16.14f, 13.77f, 20.0f, 8.55f);
        pathBuilderQ3.curveTo(19.41f, 8.21f, 18.73f, 8.0f, 18.0f, 8.0f);
        pathBuilderQ3.horizontalLineTo(6.0f);
        pathBuilderQ3.curveTo(5.27f, 8.0f, 4.59f, 8.21f, 4.0f, 8.55f);
        pathBuilderQ3.verticalLineTo(8.0f);
        pathBuilderQ3.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderQ3.horizontalLineToRelative(12.0f);
        pathBuilderQ3.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderQ3, 8.55f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wallet = imageVectorBuild;
        return imageVectorBuild;
    }
}
