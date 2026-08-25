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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_syncLock", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SyncLock", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSyncLock", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SyncLockKt {
    private static ImageVector _syncLock;

    public static final ImageVector getSyncLock(Icons.TwoTone twoTone) {
        ImageVector imageVector = _syncLock;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SyncLock", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(10.0f, 4.26f, 2.09f);
        pathBuilderI.curveTo(7.67f, 7.18f, 6.0f, 9.39f, 6.0f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.77f, 0.78f, 3.34f, 2.0f, 4.44f);
        e.x(pathBuilderI, 14.0f, 2.0f, 6.0f, 4.0f);
        pathBuilderI.verticalLineToRelative(-2.0f);
        pathBuilderI.horizontalLineToRelative(2.73f);
        pathBuilderI.curveTo(5.06f, 16.54f, 4.0f, 14.4f, 4.0f, 12.0f);
        pathBuilderI.curveTo(4.0f, 8.27f, 6.55f, 5.15f, 10.0f, 4.26f);
        b.w(pathBuilderI, 20.0f, 4.0f, -6.0f, 6.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.verticalLineTo(7.56f);
        pathBuilderI.curveToRelative(1.22f, 1.1f, 2.0f, 2.67f, 2.0f, 4.44f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, -2.4f, -1.06f, -4.54f, -2.73f, -6.0f);
        c.l(pathBuilderI, 20.0f, 4.0f, 20.0f, 17.0f);
        pathBuilderI.verticalLineToRelative(-1.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.verticalLineToRelative(1.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineToRelative(3.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(4.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(-3.0f);
        pathBuilderI.curveTo(21.0f, 17.45f, 20.55f, 17.0f, 20.0f, 17.0f);
        b.w(pathBuilderI, 19.0f, 17.0f, -2.0f, -1.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderI, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _syncLock = imageVectorBuild;
        return imageVectorBuild;
    }
}
