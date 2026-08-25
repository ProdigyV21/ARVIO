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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dialerSip", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DialerSip", "Landroidx/compose/material/icons/Icons$Outlined;", "getDialerSip", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DialerSipKt {
    private static ImageVector _dialerSip;

    public static final ImageVector getDialerSip(Icons.Outlined outlined) {
        ImageVector imageVector = _dialerSip;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DialerSip", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(16.0f, 3.0f, 1.0f, 5.0f, -1.0f);
        pathBuilderB.moveTo(15.0f, 5.0f);
        c.A(pathBuilderB, -2.0f, 13.0f, 4.0f, 2.0f);
        b.A(pathBuilderB, 15.0f, 3.0f, -3.0f, 3.0f);
        a.o(pathBuilderB, 2.0f, 1.0f, -2.0f, 1.0f);
        pathBuilderB.horizontalLineToRelative(3.0f);
        pathBuilderB.close();
        pathBuilderB.moveTo(18.0f, 3.0f);
        pathBuilderB.verticalLineToRelative(5.0f);
        c.A(pathBuilderB, 1.0f, 19.0f, 6.0f, 2.0f);
        f.n(pathBuilderB, 21.0f, 3.0f, -3.0f);
        pathBuilderB.moveTo(20.0f, 5.0f);
        c.A(pathBuilderB, -1.0f, 19.0f, 4.0f, 1.0f);
        b.f(pathBuilderB, 1.0f, 20.0f, 15.5f);
        pathBuilderB.curveToRelative(-1.25f, 0.0f, -2.45f, -0.2f, -3.57f, -0.57f);
        pathBuilderB.curveToRelative(-0.1f, -0.03f, -0.21f, -0.05f, -0.31f, -0.05f);
        pathBuilderB.curveToRelative(-0.26f, 0.0f, -0.51f, 0.1f, -0.7f, 0.29f);
        pathBuilderB.lineToRelative(-2.2f, 2.2f);
        pathBuilderB.curveToRelative(-2.83f, -1.44f, -5.15f, -3.75f, -6.59f, -6.59f);
        pathBuilderB.lineToRelative(2.2f, -2.21f);
        pathBuilderB.curveToRelative(0.27f, -0.26f, 0.35f, -0.65f, 0.24f, -1.0f);
        pathBuilderB.curveTo(8.7f, 6.45f, 8.5f, 5.25f, 8.5f, 4.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderB.lineTo(4.0f, 3.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderB.curveToRelative(0.0f, 9.39f, 7.61f, 17.0f, 17.0f, 17.0f);
        pathBuilderB.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderB.verticalLineToRelative(-3.5f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.g(pathBuilderB, 5.03f, 5.0f, 1.5f);
        pathBuilderB.curveToRelative(0.07f, 0.88f, 0.22f, 1.75f, 0.46f, 2.59f);
        pathBuilderB.lineTo(5.79f, 8.8f);
        pathBuilderB.curveToRelative(-0.41f, -1.21f, -0.67f, -2.48f, -0.76f, -3.8f);
        pathBuilderB.close();
        pathBuilderB.moveTo(19.0f, 18.97f);
        pathBuilderB.curveToRelative(-1.32f, -0.09f, -2.59f, -0.35f, -3.8f, -0.75f);
        pathBuilderB.lineToRelative(1.2f, -1.2f);
        pathBuilderB.curveToRelative(0.85f, 0.24f, 1.71f, 0.39f, 2.59f, 0.45f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderB, 1.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dialerSip = imageVectorBuild;
        return imageVectorBuild;
    }
}
