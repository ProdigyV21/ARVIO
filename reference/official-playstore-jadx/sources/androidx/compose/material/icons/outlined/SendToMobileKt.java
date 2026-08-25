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
import v.b;
import v.c;
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_sendToMobile", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SendToMobile", "Landroidx/compose/material/icons/Icons$Outlined;", "getSendToMobile$annotations", "(Landroidx/compose/material/icons/Icons$Outlined;)V", "getSendToMobile", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SendToMobileKt {
    private static ImageVector _sendToMobile;

    public static final ImageVector getSendToMobile(Icons.Outlined outlined) {
        ImageVector imageVector = _sendToMobile;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SendToMobile", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.0f, 8.0f, 4.0f, 4.0f);
        pathBuilderR.lineToRelative(-4.0f, 4.0f);
        pathBuilderR.lineToRelative(-1.41f, -1.41f);
        b.m(pathBuilderR, 18.17f, 13.0f, 13.0f, -2.0f);
        pathBuilderR.horizontalLineToRelative(5.17f);
        d.C(pathBuilderR, -1.59f, -1.59f, 18.0f, 8.0f);
        pathBuilderR.moveTo(7.0f, 1.01f);
        pathBuilderR.lineTo(17.0f, 1.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        c.p(pathBuilderR, 4.0f, -2.0f, 6.0f, 7.0f);
        b.z(pathBuilderR, 12.0f, 10.0f, -1.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderR.horizontalLineTo(7.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.verticalLineTo(3.0f);
        pathBuilderR.curveTo(5.0f, 1.9f, 5.9f, 1.01f, 7.0f, 1.01f);
        b.w(pathBuilderR, 7.0f, 21.0f, 10.0f, -1.0f);
        c.l(pathBuilderR, 7.0f, 21.0f, 7.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.d(pathBuilderR, 10.0f, 3.0f, 7.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sendToMobile = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getSendToMobile$annotations(Icons.Outlined outlined) {
    }
}
