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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_accountBalance", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AccountBalance", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAccountBalance", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AccountBalanceKt {
    private static ImageVector _accountBalance;

    public static final ImageVector getAccountBalance(Icons.TwoTone twoTone) {
        ImageVector imageVector = _accountBalance;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AccountBalance", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(a.r(6.29f, 6.0f, 5.21f, -2.74f), 16.71f, 6.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(6.5f, 10.0f, -2.0f, 7.0f, 2.0f);
        c.r(pathBuilderK, -7.0f, 12.5f, 10.0f, -2.0f);
        b.l(pathBuilderK, 7.0f, 2.0f, -7.0f);
        pathBuilderK.moveTo(21.0f, 19.0f);
        a.h(pathBuilderK, 2.0f, 19.0f, 2.0f, 19.0f);
        c.r(pathBuilderK, -2.0f, 18.5f, 10.0f, -2.0f);
        b.l(pathBuilderK, 7.0f, 2.0f, -7.0f);
        pathBuilderK.moveTo(11.5f, 1.0f);
        a.h(pathBuilderK, 2.0f, 6.0f, 2.0f, 19.0f);
        d.l(pathBuilderK, 21.0f, 6.0f, -9.5f, -5.0f);
        pathBuilderK.moveTo(6.29f, 6.0f);
        pathBuilderK.lineToRelative(5.21f, -2.74f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderK, 16.71f, 6.0f, 6.29f, 6.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _accountBalance = imageVectorBuild;
        return imageVectorBuild;
    }
}
