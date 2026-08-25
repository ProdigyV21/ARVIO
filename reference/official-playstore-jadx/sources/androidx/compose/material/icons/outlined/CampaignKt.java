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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_campaign", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Campaign", "Landroidx/compose/material/icons/Icons$Outlined;", "getCampaign", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CampaignKt {
    private static ImageVector _campaign;

    public static final ImageVector getCampaign(Icons.Outlined outlined) {
        ImageVector imageVector = _campaign;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Campaign", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, 0.67f, 0.0f, 1.33f, 0.0f, 2.0f);
        pathBuilderA.curveToRelative(1.2f, 0.0f, 2.76f, 0.0f, 4.0f, 0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.67f, 0.0f, -1.33f, 0.0f, -2.0f);
        pathBuilderA.curveTo(20.76f, 11.0f, 19.2f, 11.0f, 18.0f, 11.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(16.0f, 17.61f);
        pathBuilderA2.curveToRelative(0.96f, 0.71f, 2.21f, 1.65f, 3.2f, 2.39f);
        pathBuilderA2.curveToRelative(0.4f, -0.53f, 0.8f, -1.07f, 1.2f, -1.6f);
        pathBuilderA2.curveToRelative(-0.99f, -0.74f, -2.24f, -1.68f, -3.2f, -2.4f);
        pathBuilderA2.curveTo(16.8f, 16.54f, 16.4f, 17.08f, 16.0f, 17.61f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(20.4f, 5.6f);
        pathBuilderA3.curveTo(20.0f, 5.07f, 19.6f, 4.53f, 19.2f, 4.0f);
        pathBuilderA3.curveToRelative(-0.99f, 0.74f, -2.24f, 1.68f, -3.2f, 2.4f);
        pathBuilderA3.curveToRelative(0.4f, 0.53f, 0.8f, 1.07f, 1.2f, 1.6f);
        pathBuilderA3.curveTo(18.16f, 7.28f, 19.41f, 6.35f, 20.4f, 5.6f);
        pathBuilderA3.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA4 = c.a(4.0f, 9.0f);
        pathBuilderA4.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA4.verticalLineToRelative(2.0f);
        pathBuilderA4.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a.o(pathBuilderA4, 1.0f, 4.0f, 2.0f, -4.0f);
        e.n(pathBuilderA4, 1.0f, 5.0f, 3.0f, 6.0f);
        w.a.e(pathBuilderA4, 8.0f, 9.0f, 4.0f);
        pathBuilderA4.moveTo(9.03f, 10.71f);
        pathBuilderA4.lineTo(11.0f, 9.53f);
        pathBuilderA4.verticalLineToRelative(4.94f);
        pathBuilderA4.lineToRelative(-1.97f, -1.18f);
        pathBuilderA4.lineTo(8.55f, 13.0f);
        pathBuilderA4.horizontalLineTo(8.0f);
        pathBuilderA4.horizontalLineTo(4.0f);
        pathBuilderA4.verticalLineToRelative(-2.0f);
        pathBuilderA4.horizontalLineToRelative(4.0f);
        pathBuilderA4.horizontalLineToRelative(0.55f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA4, 9.03f, 10.71f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA5 = c.a(15.5f, 12.0f);
        pathBuilderA5.curveToRelative(0.0f, -1.33f, -0.58f, -2.53f, -1.5f, -3.35f);
        pathBuilderA5.verticalLineToRelative(6.69f);
        pathBuilderA5.curveTo(14.92f, 14.53f, 15.5f, 13.33f, 15.5f, 12.0f);
        pathBuilderA5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _campaign = imageVectorBuild;
        return imageVectorBuild;
    }
}
