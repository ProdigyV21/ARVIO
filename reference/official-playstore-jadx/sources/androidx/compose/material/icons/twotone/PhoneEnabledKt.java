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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phoneEnabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneEnabled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPhoneEnabled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneEnabledKt {
    private static ImageVector _phoneEnabled;

    public static final ImageVector getPhoneEnabled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _phoneEnabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PhoneEnabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 9.39f, -7.61f, 17.0f, -17.0f, 17.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.49f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(1.24f, 0.0f, 2.45f, -0.2f, 3.57f, -0.57f);
        pathBuilderA.curveToRelative(0.1f, -0.04f, 0.21f, -0.05f, 0.31f, -0.05f);
        pathBuilderA.curveToRelative(0.26f, 0.0f, 0.51f, 0.1f, 0.71f, 0.29f);
        pathBuilderA.lineToRelative(2.2f, 2.2f);
        pathBuilderA.curveToRelative(2.83f, -1.45f, 5.15f, -3.76f, 6.59f, -6.59f);
        pathBuilderA.lineToRelative(-2.2f, -2.2f);
        pathBuilderA.curveToRelative(-0.28f, -0.28f, -0.36f, -0.67f, -0.25f, -1.02f);
        pathBuilderA.curveTo(15.3f, 6.45f, 15.5f, 5.25f, 15.5f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(20.0f);
        pathBuilderA.curveTo(20.55f, 3.0f, 21.0f, 3.45f, 21.0f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.6f, 17.02f);
        pathBuilderA.curveToRelative(-0.85f, 0.24f, -1.72f, 0.39f, -2.6f, 0.45f);
        pathBuilderA.verticalLineToRelative(1.49f);
        pathBuilderA.curveToRelative(1.32f, -0.09f, 2.59f, -0.35f, 3.8f, -0.75f);
        c.z(pathBuilderA, 7.6f, 17.02f, 17.46f, 5.0f);
        pathBuilderA.curveToRelative(-0.06f, 0.89f, -0.21f, 1.76f, -0.45f, 2.59f);
        pathBuilderA.lineToRelative(1.2f, 1.2f);
        pathBuilderA.curveToRelative(0.41f, -1.2f, 0.67f, -2.47f, 0.76f, -3.79f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 17.46f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneEnabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
