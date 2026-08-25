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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_allInbox", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AllInbox", "Landroidx/compose/material/icons/Icons$Rounded;", "getAllInbox", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AllInboxKt {
    private static ImageVector _allInbox;

    public static final ImageVector getAllInbox(Icons.Rounded rounded) {
        ImageVector imageVector = _allInbox;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AllInbox", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        a.g(pathBuilderJ, 19.0f, 9.0f, -3.14f);
        pathBuilderJ.curveToRelative(-0.47f, 0.0f, -0.84f, 0.33f, -0.97f, 0.78f);
        pathBuilderJ.curveTo(14.53f, 11.04f, 13.35f, 12.0f, 12.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.53f, -0.96f, -2.89f, -2.22f);
        pathBuilderJ.curveToRelative(-0.13f, -0.45f, -0.5f, -0.78f, -0.97f, -0.78f);
        pathBuilderJ.lineTo(5.0f, 9.0f);
        pathBuilderJ.lineTo(5.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderJ, 3.0f, 15.87f, 16.0f);
        pathBuilderJ.lineTo(20.0f, 16.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.lineTo(5.0f, 21.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(4.13f);
        pathBuilderJ.curveToRelative(0.47f, 0.0f, 0.85f, 0.34f, 0.98f, 0.8f);
        pathBuilderJ.curveToRelative(0.35f, 1.27f, 1.51f, 2.2f, 2.89f, 2.2f);
        pathBuilderJ.reflectiveCurveToRelative(2.54f, -0.93f, 2.89f, -2.2f);
        pathBuilderJ.curveToRelative(0.13f, -0.46f, 0.51f, -0.8f, 0.98f, -0.8f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _allInbox = imageVectorBuild;
        return imageVectorBuild;
    }
}
