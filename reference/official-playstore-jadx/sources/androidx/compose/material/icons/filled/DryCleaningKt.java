package androidx.compose.material.icons.filled;

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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dryCleaning", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DryCleaning", "Landroidx/compose/material/icons/Icons$Filled;", "getDryCleaning", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DryCleaningKt {
    private static ImageVector _dryCleaning;

    public static final ImageVector getDryCleaning(Icons.Filled filled) {
        ImageVector imageVector = _dryCleaning;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DryCleaning", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(19.56f, 11.36f, 13.0f, 8.44f, 7.0f);
        pathBuilderN.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderN.lineToRelative(0.0f, 0.0f);
        pathBuilderN.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderN.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderN.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderN.horizontalLineToRelative(2.0f);
        pathBuilderN.curveToRelative(0.0f, -1.84f, -1.66f, -3.3f, -3.56f, -2.95f);
        pathBuilderN.curveTo(10.26f, 2.27f, 9.29f, 3.22f, 9.06f, 4.4f);
        pathBuilderN.curveTo(8.76f, 5.96f, 9.66f, 7.34f, 11.0f, 7.82f);
        pathBuilderN.verticalLineToRelative(0.63f);
        pathBuilderN.lineToRelative(-6.56f, 2.92f);
        pathBuilderN.curveTo(3.56f, 11.75f, 3.0f, 12.62f, 3.0f, 13.57f);
        pathBuilderN.verticalLineToRelative(0.01f);
        pathBuilderN.curveTo(3.0f, 14.92f, 4.08f, 16.0f, 5.42f, 16.0f);
        b.q(pathBuilderN, 7.0f, 6.0f, 10.0f, -6.0f);
        pathBuilderN.horizontalLineToRelative(1.58f);
        pathBuilderN.curveToRelative(1.34f, 0.0f, 2.42f, -1.08f, 2.42f, -2.42f);
        pathBuilderN.verticalLineToRelative(-0.01f);
        pathBuilderN.curveTo(21.0f, 12.62f, 20.44f, 11.75f, 19.56f, 11.36f);
        c.C(pathBuilderN, 18.58f, 14.0f, 17.0f, -1.0f);
        a0.a.w(pathBuilderN, 7.0f, 1.0f, 5.42f);
        pathBuilderN.curveTo(5.19f, 14.0f, 5.0f, 13.81f, 5.0f, 13.57f);
        pathBuilderN.curveToRelative(0.0f, -0.17f, 0.1f, -0.32f, 0.25f, -0.38f);
        pathBuilderN.lineToRelative(6.75f, -3.0f);
        pathBuilderN.lineToRelative(6.75f, 3.0f);
        pathBuilderN.curveTo(18.9f, 13.26f, 19.0f, 13.41f, 19.0f, 13.58f);
        pathBuilderN.curveTo(19.0f, 13.81f, 18.81f, 14.0f, 18.58f, 14.0f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dryCleaning = imageVectorBuild;
        return imageVectorBuild;
    }
}
