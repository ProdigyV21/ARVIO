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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_outbox", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Outbox", "Landroidx/compose/material/icons/Icons$Outlined;", "getOutbox", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutboxKt {
    private static ImageVector _outbox;

    public static final ImageVector getOutbox(Icons.Outlined outlined) {
        ImageVector imageVector = _outbox;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Outbox", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.0f, 9.83f, 0.0f, 4.17f);
        pathBuilderR.lineToRelative(2.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -4.17f);
        pathBuilderR.lineToRelative(1.59f, 1.58f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.lineToRelative(-4.0f, -4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -4.0f, 4.0f, 1.41f, 1.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        c.C(pathBuilderQ, 19.0f, 19.0f, 5.0f, -3.0f);
        pathBuilderQ.horizontalLineToRelative(3.02f);
        pathBuilderQ.curveToRelative(0.91f, 1.21f, 2.35f, 2.0f, 3.98f, 2.0f);
        pathBuilderQ.reflectiveCurveToRelative(3.06f, -0.79f, 3.98f, -2.0f);
        c.l(pathBuilderQ, 19.0f, 19.0f, 19.0f, 14.0f);
        pathBuilderQ.horizontalLineToRelative(-4.18f);
        pathBuilderQ.curveToRelative(-0.41f, 1.16f, -1.51f, 2.0f, -2.82f, 2.0f);
        pathBuilderQ.reflectiveCurveToRelative(-2.4f, -0.84f, -2.82f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.i(pathBuilderQ, 5.0f, 5.0f, 14.0f, 14.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _outbox = imageVectorBuild;
        return imageVectorBuild;
    }
}
