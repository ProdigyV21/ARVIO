package androidx.compose.material.icons.automirrored.outlined;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_message", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Message", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getMessage", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MessageKt {
    private static ImageVector _message;

    public static final ImageVector getMessage(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _message;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.Message", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(4.0f, 4.0f, 16.0f, 12.0f);
        pathBuilderA.lineTo(5.17f, 16.0f);
        pathBuilderA.lineTo(4.0f, 17.17f);
        pathBuilderA.lineTo(4.0f, 4.0f);
        pathBuilderA.moveToRelative(0.0f, -2.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderA.lineTo(2.0f, 22.0f);
        pathBuilderA.lineToRelative(4.0f, -4.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineTo(22.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        c.z(pathBuilderA, 4.0f, 2.0f, 6.0f, 12.0f);
        c.q(pathBuilderA, 12.0f, 2.0f, 6.0f, 14.0f);
        c.r(pathBuilderA, -2.0f, 6.0f, 9.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        b.y(pathBuilderA, 6.0f, 11.0f, 6.0f, 9.0f);
        a.C(pathBuilderA, 6.0f, 6.0f, 12.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 6.0f, 8.0f, 6.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _message = imageVectorBuild;
        return imageVectorBuild;
    }
}
