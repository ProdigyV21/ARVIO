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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wallet", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wallet", "Landroidx/compose/material/icons/Icons$Outlined;", "getWallet", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WalletKt {
    private static ImageVector _wallet;

    public static final ImageVector getWallet(Icons.Outlined outlined) {
        ImageVector imageVector = _wallet;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Wallet", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(18.0f, 4.0f, 6.0f);
        pathBuilderQ.curveTo(3.79f, 4.0f, 2.0f, 5.79f, 2.0f, 8.0f);
        pathBuilderQ.verticalLineToRelative(8.0f);
        pathBuilderQ.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderQ.verticalLineTo(8.0f);
        pathBuilderQ.curveTo(22.0f, 5.79f, 20.21f, 4.0f, 18.0f, 4.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(16.14f, 13.77f);
        pathBuilderQ.curveToRelative(-0.24f, 0.2f, -0.57f, 0.28f, -0.88f, 0.2f);
        pathBuilderQ.lineTo(4.15f, 11.25f);
        pathBuilderQ.curveTo(4.45f, 10.52f, 5.16f, 10.0f, 6.0f, 10.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.67f, 0.0f, 1.26f, 0.34f, 1.63f, 0.84f);
        c.z(pathBuilderQ, 16.14f, 13.77f, 6.0f, 6.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderQ.verticalLineToRelative(0.55f);
        pathBuilderQ.curveTo(19.41f, 8.21f, 18.73f, 8.0f, 18.0f, 8.0f);
        pathBuilderQ.horizontalLineTo(6.0f);
        pathBuilderQ.curveTo(5.27f, 8.0f, 4.59f, 8.21f, 4.0f, 8.55f);
        pathBuilderQ.verticalLineTo(8.0f);
        pathBuilderQ.curveTo(4.0f, 6.9f, 4.9f, 6.0f, 6.0f, 6.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wallet = imageVectorBuild;
        return imageVectorBuild;
    }
}
