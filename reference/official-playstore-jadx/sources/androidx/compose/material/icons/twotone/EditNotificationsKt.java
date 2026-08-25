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
import v.b;
import v.c;
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editNotifications", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditNotifications", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEditNotifications", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditNotificationsKt {
    private static ImageVector _editNotifications;

    public static final ImageVector getEditNotifications(Icons.TwoTone twoTone) {
        ImageVector imageVector = _editNotifications;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EditNotifications", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(16.0f, 14.2f, 15.2f, 15.0f, -4.6f);
        pathBuilderT.verticalLineToRelative(-4.6f);
        pathBuilderT.lineToRelative(3.68f, -3.68f);
        pathBuilderT.curveTo(13.64f, 6.26f, 12.85f, 6.0f, 12.0f, 6.0f);
        pathBuilderT.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderT, 7.0f, 8.0f, 14.2f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(17.58f, 6.25f, 1.77f, 1.77f);
        b.m(pathBuilderR, 14.37f, 13.0f, 12.6f, -1.77f);
        c.z(pathBuilderR, 17.58f, 6.25f, 20.85f, 5.81f);
        pathBuilderR.lineToRelative(-1.06f, -1.06f);
        pathBuilderR.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderR.lineToRelative(-0.85f, 0.85f);
        pathBuilderR.lineToRelative(1.77f, 1.77f);
        pathBuilderR.lineToRelative(0.85f, -0.85f);
        pathBuilderR.curveTo(21.05f, 6.32f, 21.05f, 6.0f, 20.85f, 5.81f);
        a0.b.t(pathBuilderR, 18.0f, 12.2f, 17.0f, 2.0f);
        a0.a.A(pathBuilderR, 2.0f, 4.0f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(-7.0f);
        pathBuilderR.curveToRelative(0.0f, -2.79f, 1.91f, -5.14f, 4.5f, -5.8f);
        pathBuilderR.verticalLineTo(3.5f);
        pathBuilderR.curveTo(10.5f, 2.67f, 11.17f, 2.0f, 12.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderR.verticalLineToRelative(0.7f);
        pathBuilderR.curveToRelative(0.82f, 0.21f, 1.57f, 0.59f, 2.21f, 1.09f);
        pathBuilderR.lineToRelative(-1.43f, 1.43f);
        pathBuilderR.curveTo(13.64f, 6.26f, 12.85f, 6.0f, 12.0f, 6.0f);
        pathBuilderR.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        e.l(pathBuilderR, 7.0f, 8.0f, -2.8f);
        c.z(pathBuilderR, 18.0f, 12.2f, 10.0f, 20.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 10.0f, 21.1f, 10.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editNotifications = imageVectorBuild;
        return imageVectorBuild;
    }
}
