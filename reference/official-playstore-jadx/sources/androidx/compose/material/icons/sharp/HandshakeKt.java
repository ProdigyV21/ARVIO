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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_handshake", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Handshake", "Landroidx/compose/material/icons/Icons$Sharp;", "getHandshake", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HandshakeKt {
    private static ImageVector _handshake;

    public static final ImageVector getHandshake(Icons.Sharp sharp) {
        ImageVector imageVector = _handshake;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Handshake", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.59f, 5.95f, -7.05f, 7.04f);
        pathBuilderR.lineTo(0.7f, 10.3f);
        pathBuilderR.lineToRelative(8.55f, -8.55f);
        pathBuilderR.lineToRelative(7.95f, 7.95f);
        d.C(pathBuilderR, -1.42f, 1.42f, 10.59f, 5.95f);
        pathBuilderR.moveTo(23.24f, 10.24f);
        pathBuilderR.lineToRelative(-8.49f, -8.49f);
        pathBuilderR.lineToRelative(-2.06f, 2.06f);
        pathBuilderR.lineToRelative(5.9f, 5.88f);
        pathBuilderR.lineToRelative(-2.83f, 2.83f);
        pathBuilderR.lineToRelative(-5.17f, -5.17f);
        pathBuilderR.lineToRelative(-6.27f, 6.27f);
        pathBuilderR.lineToRelative(1.42f, 1.41f);
        pathBuilderR.lineToRelative(5.32f, -5.32f);
        pathBuilderR.lineToRelative(0.71f, 0.71f);
        pathBuilderR.lineToRelative(-5.32f, 5.32f);
        pathBuilderR.lineToRelative(1.42f, 1.41f);
        pathBuilderR.lineToRelative(5.32f, -5.32f);
        pathBuilderR.lineToRelative(0.71f, 0.71f);
        pathBuilderR.lineToRelative(-5.32f, 5.32f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(5.32f, -5.32f);
        pathBuilderR.lineToRelative(0.71f, 0.71f);
        pathBuilderR.lineTo(10.68f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.41f, 1.41f, 23.24f, 10.24f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _handshake = imageVectorBuild;
        return imageVectorBuild;
    }
}
