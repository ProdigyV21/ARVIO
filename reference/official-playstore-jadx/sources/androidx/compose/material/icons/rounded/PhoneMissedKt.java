package androidx.compose.material.icons.rounded;

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
import v.c;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_phoneMissed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneMissed", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhoneMissed$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getPhoneMissed", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneMissedKt {
    private static ImageVector _phoneMissed;

    public static final ImageVector getPhoneMissed(Icons.Rounded rounded) {
        ImageVector imageVector = _phoneMissed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhoneMissed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(23.09f, 16.2f);
        pathBuilderA.curveToRelative(-6.33f, -5.59f, -15.86f, -5.59f, -22.18f, 0.0f);
        pathBuilderA.curveToRelative(-0.84f, 0.74f, -0.84f, 2.05f, -0.05f, 2.84f);
        pathBuilderA.lineToRelative(1.2f, 1.2f);
        pathBuilderA.curveToRelative(0.71f, 0.71f, 1.84f, 0.77f, 2.62f, 0.15f);
        pathBuilderA.lineToRelative(1.97f, -1.57f);
        pathBuilderA.curveToRelative(0.47f, -0.37f, 0.75f, -0.94f, 0.75f, -1.55f);
        pathBuilderA.verticalLineTo(14.7f);
        pathBuilderA.curveToRelative(2.98f, -0.97f, 6.21f, -0.98f, 9.2f, 0.0f);
        pathBuilderA.verticalLineToRelative(2.58f);
        pathBuilderA.curveToRelative(0.0f, 0.6f, 0.28f, 1.17f, 0.75f, 1.55f);
        pathBuilderA.lineToRelative(1.96f, 1.56f);
        pathBuilderA.curveToRelative(0.79f, 0.62f, 1.91f, 0.56f, 2.62f, -0.15f);
        pathBuilderA.lineToRelative(1.2f, -1.2f);
        pathBuilderA.curveToRelative(0.8f, -0.79f, 0.79f, -2.1f, -0.04f, -2.84f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 9.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(6.43f);
        pathBuilderA.lineToRelative(4.24f, 4.24f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(5.66f, -5.66f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-4.95f, 4.95f);
        pathBuilderA.lineTo(8.4f, 5.0f);
        pathBuilderA.horizontalLineTo(10.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneMissed = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getPhoneMissed$annotations(Icons.Rounded rounded) {
    }
}
