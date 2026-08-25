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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_allInbox", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AllInbox", "Landroidx/compose/material/icons/Icons$Outlined;", "getAllInbox", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AllInboxKt {
    private static ImageVector _allInbox;

    public static final ImageVector getAllInbox(Icons.Outlined outlined) {
        ImageVector imageVector = _allInbox;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AllInbox", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 3.0f, 5.0f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(7.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(21.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.g(pathBuilderJ, 5.0f, 10.0f, 3.13f);
        pathBuilderJ.curveToRelative(0.21f, 0.78f, 0.67f, 1.47f, 1.27f, 2.0f);
        d.f(pathBuilderJ, 5.0f, 12.0f, -2.0f);
        pathBuilderJ.moveTo(19.0f, 12.0f);
        pathBuilderJ.horizontalLineToRelative(-4.4f);
        pathBuilderJ.curveToRelative(0.6f, -0.53f, 1.06f, -1.22f, 1.27f, -2.0f);
        d.f(pathBuilderJ, 19.0f, 10.0f, 2.0f);
        a.C(pathBuilderJ, 19.0f, 8.0f, -5.0f, 1.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.07f, -0.93f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.0f, -0.93f, -2.0f, -2.0f);
        pathBuilderJ.lineTo(10.0f, 8.0f);
        pathBuilderJ.lineTo(5.0f, 8.0f);
        b.g(pathBuilderJ, 5.0f, 5.0f, 14.0f, 3.0f);
        a.C(pathBuilderJ, 17.0f, 15.0f, -3.0f, 1.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.47f, -0.19f, 0.9f, -0.48f, 1.25f);
        pathBuilderJ.curveToRelative(-0.37f, 0.45f, -0.92f, 0.75f, -1.52f, 0.75f);
        pathBuilderJ.reflectiveCurveToRelative(-1.15f, -0.3f, -1.52f, -0.75f);
        pathBuilderJ.curveToRelative(-0.29f, -0.35f, -0.48f, -0.78f, -0.48f, -1.25f);
        d.r(pathBuilderJ, -1.0f, 3.0f, 15.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        e.D(pathBuilderJ, -4.0f, -4.0f, 5.0f, 17.0f);
        pathBuilderJ.horizontalLineToRelative(3.13f);
        pathBuilderJ.curveToRelative(0.02f, 0.09f, 0.06f, 0.17f, 0.09f, 0.25f);
        pathBuilderJ.curveToRelative(0.24f, 0.68f, 0.65f, 1.28f, 1.18f, 1.75f);
        d.f(pathBuilderJ, 5.0f, 19.0f, -2.0f);
        pathBuilderJ.moveTo(19.0f, 19.0f);
        pathBuilderJ.horizontalLineToRelative(-4.4f);
        pathBuilderJ.curveToRelative(0.54f, -0.47f, 0.95f, -1.07f, 1.18f, -1.75f);
        pathBuilderJ.curveToRelative(0.03f, -0.08f, 0.07f, -0.16f, 0.09f, -0.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderJ, 19.0f, 17.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _allInbox = imageVectorBuild;
        return imageVectorBuild;
    }
}
