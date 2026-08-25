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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_partyMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PartyMode", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPartyMode", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PartyModeKt {
    private static ImageVector _partyMode;

    public static final ImageVector getPartyMode(Icons.TwoTone twoTone) {
        ImageVector imageVector = _partyMode;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PartyMode", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.95f, 6.0f, -0.59f, -0.65f);
        pathBuilderR.lineTo(14.12f, 4.0f);
        pathBuilderR.lineTo(9.88f, 4.0f);
        pathBuilderR.lineTo(8.65f, 5.35f);
        pathBuilderR.lineToRelative(-0.6f, 0.65f);
        a.h(pathBuilderR, 4.0f, 6.0f, 12.0f, 16.0f);
        f.n(pathBuilderR, 20.0f, 6.0f, -4.05f);
        pathBuilderR.moveTo(7.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -2.76f, 2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderR.curveToRelative(1.63f, 0.0f, 3.06f, 0.79f, 3.98f, 2.0f);
        pathBuilderR.lineTo(12.0f, 9.0f);
        pathBuilderR.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderR.curveToRelative(0.0f, 0.35f, 0.07f, 0.69f, 0.18f, 1.0f);
        pathBuilderR.lineTo(7.1f, 13.0f);
        pathBuilderR.curveToRelative(-0.06f, -0.32f, -0.1f, -0.66f, -0.1f, -1.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(17.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 2.76f, -2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderR.curveToRelative(-1.63f, 0.0f, -3.06f, -0.79f, -3.98f, -2.0f);
        pathBuilderR.lineTo(12.0f, 15.0f);
        pathBuilderR.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderR.curveToRelative(0.0f, -0.35f, -0.07f, -0.69f, -0.18f, -1.0f);
        pathBuilderR.horizontalLineToRelative(2.08f);
        pathBuilderR.curveToRelative(0.07f, 0.32f, 0.1f, 0.66f, 0.1f, 1.0f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(20.0f, 4.0f, -3.17f, 15.0f, 2.0f);
        pathBuilderI.lineTo(9.0f, 2.0f);
        pathBuilderI.lineTo(7.17f, 4.0f);
        pathBuilderI.lineTo(4.0f, 4.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.verticalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(16.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(22.0f, 6.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderI, 20.0f, 18.0f, 4.0f, 18.0f);
        pathBuilderI.lineTo(4.0f, 6.0f);
        pathBuilderI.horizontalLineToRelative(4.05f);
        pathBuilderI.lineToRelative(0.59f, -0.65f);
        pathBuilderI.lineTo(9.88f, 4.0f);
        pathBuilderI.horizontalLineToRelative(4.24f);
        pathBuilderI.lineToRelative(1.24f, 1.35f);
        pathBuilderI.lineToRelative(0.59f, 0.65f);
        d.f(pathBuilderI, 20.0f, 6.0f, 12.0f);
        pathBuilderI.moveTo(9.0f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderI.horizontalLineToRelative(3.98f);
        pathBuilderI.curveToRelative(-0.92f, -1.21f, -2.35f, -2.0f, -3.98f, -2.0f);
        pathBuilderI.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderI.curveToRelative(0.0f, 0.34f, 0.04f, 0.68f, 0.1f, 1.0f);
        pathBuilderI.horizontalLineToRelative(2.08f);
        pathBuilderI.curveToRelative(-0.11f, -0.31f, -0.18f, -0.65f, -0.18f, -1.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(15.0f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderI.lineTo(8.02f, 15.0f);
        pathBuilderI.curveToRelative(0.92f, 1.21f, 2.35f, 2.0f, 3.98f, 2.0f);
        pathBuilderI.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderI.curveToRelative(0.0f, -0.34f, -0.03f, -0.68f, -0.1f, -1.0f);
        pathBuilderI.horizontalLineToRelative(-2.08f);
        pathBuilderI.curveToRelative(0.11f, 0.31f, 0.18f, 0.65f, 0.18f, 1.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _partyMode = imageVectorBuild;
        return imageVectorBuild;
    }
}
