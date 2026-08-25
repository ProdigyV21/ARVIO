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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_connectedTv", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ConnectedTv", "Landroidx/compose/material/icons/Icons$Outlined;", "getConnectedTv", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConnectedTvKt {
    private static ImageVector _connectedTv;

    public static final ImageVector getConnectedTv(Icons.Outlined outlined) {
        ImageVector imageVector = _connectedTv;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ConnectedTv", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 3.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 3.0f, 2.0f, 3.9f, 2.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a.o(pathBuilderQ, 4.0f, 2.0f, 8.0f, -2.0f);
        pathBuilderQ.horizontalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
        pathBuilderQ.lineTo(22.0f, 5.0f);
        pathBuilderQ.curveTo(22.0f, 3.9f, 21.1f, 3.0f, 20.0f, 3.0f);
        f.o(pathBuilderQ, 20.0f, 17.0f, 4.0f, 5.0f);
        c.D(pathBuilderQ, 16.0f, 17.0f, 5.0f, 14.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.horizontalLineToRelative(2.0f);
        pathBuilderQ.curveTo(7.0f, 14.89f, 6.11f, 14.0f, 5.0f, 14.0f);
        d.k(pathBuilderQ, 5.0f, 11.0f, 1.43f);
        pathBuilderQ.curveToRelative(1.97f, 0.0f, 3.57f, 1.6f, 3.57f, 3.57f);
        pathBuilderQ.horizontalLineTo(10.0f);
        pathBuilderQ.curveTo(10.0f, 13.24f, 7.76f, 11.0f, 5.0f, 11.0f);
        d.k(pathBuilderQ, 5.0f, 8.0f, 1.45f);
        pathBuilderQ.curveToRelative(3.61f, 0.0f, 6.55f, 2.93f, 6.55f, 6.55f);
        pathBuilderQ.horizontalLineTo(13.0f);
        pathBuilderQ.curveTo(13.0f, 11.58f, 9.41f, 8.0f, 5.0f, 8.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _connectedTv = imageVectorBuild;
        return imageVectorBuild;
    }
}
