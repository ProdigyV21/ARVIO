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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pendingActions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PendingActions", "Landroidx/compose/material/icons/Icons$Filled;", "getPendingActions", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PendingActionsKt {
    private static ImageVector _pendingActions;

    public static final ImageVector getPendingActions(Icons.Filled filled) {
        ImageVector imageVector = _pendingActions;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PendingActions", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 12.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveTo(19.76f, 12.0f, 17.0f, 12.0f);
        a.z(pathBuilderA, 18.65f, 19.35f, -2.15f, -2.15f);
        w.a.j(pathBuilderA, 14.0f, 1.0f, 2.79f);
        d.C(pathBuilderA, 1.85f, 1.85f, 18.65f, 19.35f);
        pathBuilderA.moveTo(18.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(-3.18f);
        pathBuilderA.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(9.6f, 1.84f, 9.18f, 3.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveTo(4.9f, 3.0f, 4.0f, 3.9f, 4.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(15.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(6.11f);
        pathBuilderA.curveToRelative(-0.59f, -0.57f, -1.07f, -1.25f, -1.42f, -2.0f);
        y.a.t(pathBuilderA, 6.0f, 5.0f, 2.0f, 3.0f);
        w.a.C(pathBuilderA, 8.0f, 5.0f, 2.0f, 5.08f);
        pathBuilderA.curveToRelative(0.71f, 0.1f, 1.38f, 0.31f, 2.0f, 0.6f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveTo(20.0f, 3.9f, 19.1f, 3.0f, 18.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveTo(13.0f, 4.55f, 12.55f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pendingActions = imageVectorBuild;
        return imageVectorBuild;
    }
}
