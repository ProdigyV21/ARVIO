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
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_labelOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LabelOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLabelOff$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getLabelOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LabelOffKt {
    private static ImageVector _labelOff;

    public static final ImageVector getLabelOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _labelOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LabelOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.0f, 7.03f);
        pathBuilder.verticalLineTo(17.0f);
        pathBuilder.horizontalLineToRelative(9.97f);
        pathBuilder.close();
        pathBuilder.moveTo(16.0f, 7.0f);
        pathBuilder.horizontalLineToRelative(-5.37f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilder, 7.29f, 7.29f, 19.55f, 12.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.0f, 7.0f, 3.55f, 5.0f);
        pathBuilderR.lineToRelative(-1.63f, 2.29f);
        pathBuilderR.lineToRelative(1.43f, 1.43f);
        pathBuilderR.lineTo(22.0f, 12.0f);
        pathBuilderR.lineToRelative(-4.37f, -6.16f);
        pathBuilderR.curveTo(17.27f, 5.33f, 16.67f, 5.0f, 16.0f, 5.0f);
        pathBuilderR.lineToRelative(-7.37f, 0.01f);
        d.C(pathBuilderR, 2.0f, 1.99f, 16.0f, 7.0f);
        pathBuilderR.moveTo(2.0f, 4.03f);
        pathBuilderR.lineToRelative(1.58f, 1.58f);
        pathBuilderR.curveTo(3.22f, 5.96f, 3.0f, 6.46f, 3.0f, 7.0f);
        pathBuilderR.verticalLineToRelative(10.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 1.99f, 2.0f, 1.99f);
        pathBuilderR.lineTo(16.0f, 19.0f);
        pathBuilderR.curveToRelative(0.28f, 0.0f, 0.55f, -0.07f, 0.79f, -0.18f);
        pathBuilderR.lineTo(18.97f, 21.0f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 3.41f, 2.62f, 2.0f, 4.03f);
        pathBuilderR.moveTo(5.0f, 7.03f);
        pathBuilderR.lineTo(14.97f, 17.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 5.0f, 17.0f, 5.0f, 7.03f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _labelOff = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getLabelOff$annotations(Icons.TwoTone twoTone) {
    }
}
