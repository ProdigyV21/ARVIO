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
import v.b;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_closedCaptionDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ClosedCaptionDisabled", "Landroidx/compose/material/icons/Icons$Filled;", "getClosedCaptionDisabled", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClosedCaptionDisabledKt {
    private static ImageVector _closedCaptionDisabled;

    public static final ImageVector getClosedCaptionDisabled(Icons.Filled filled) {
        ImageVector imageVector = _closedCaptionDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ClosedCaptionDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(6.83f, 4.0f, 19.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.05f, -0.01f, 0.1f, -0.02f, 0.16f);
        pathBuilderQ.lineToRelative(-3.38f, -3.38f);
        pathBuilderQ.curveTo(17.84f, 14.59f, 18.0f, 14.32f, 18.0f, 14.0f);
        b.z(pathBuilderQ, -1.0f, -1.5f, 0.5f, -0.17f);
        b.o(pathBuilderQ, -1.83f, -1.83f, 10.5f, 2.0f);
        a0.a.s(pathBuilderQ, 11.0f, 18.0f, -1.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(-3.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        y.a.n(pathBuilderQ, 0.17f, 6.83f, 4.0f);
        pathBuilderQ.moveTo(19.78f, 22.61f);
        pathBuilderQ.lineTo(17.17f, 20.0f);
        pathBuilderQ.horizontalLineTo(5.0f);
        pathBuilderQ.curveToRelative(-1.11f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.05f, 0.02f, -0.1f, 0.02f, -0.15f);
        pathBuilderQ.lineTo(1.39f, 4.22f);
        pathBuilderQ.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderQ, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderQ.moveTo(11.0f, 13.83f);
        b.m(pathBuilderQ, 10.17f, 13.0f, 9.5f, 0.5f);
        e.g(pathBuilderQ, -2.0f, -3.0f, 0.17f);
        pathBuilderQ.lineTo(6.4f, 9.22f);
        pathBuilderQ.curveTo(6.16f, 9.41f, 6.0f, 9.68f, 6.0f, 10.0f);
        pathBuilderQ.verticalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(3.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderQ, 13.83f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _closedCaptionDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
