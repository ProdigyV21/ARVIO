package androidx.compose.material.icons.automirrored.rounded;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_forwardToInbox", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ForwardToInbox", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getForwardToInbox", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForwardToInboxKt {
    private static ImageVector _forwardToInbox;

    public static final ImageVector getForwardToInbox(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _forwardToInbox;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.ForwardToInbox", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        d.y(pathBuilderQ, 9.0f, -2.0f, 4.0f, 8.0f);
        pathBuilderQ.lineToRelative(6.94f, 4.34f);
        pathBuilderQ.curveToRelative(0.65f, 0.41f, 1.47f, 0.41f, 2.12f, 0.0f);
        a.h(pathBuilderQ, 20.0f, 8.0f, 5.0f, 2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        a.p(pathBuilderQ, 12.0f, 11.0f, 4.0f, 6.0f);
        d.q(pathBuilderQ, 16.0f, 12.0f, 11.0f);
        pathBuilderQ.moveTo(19.0f, 16.21f);
        pathBuilderQ.curveToRelative(0.0f, -0.45f, 0.54f, -0.67f, 0.85f, -0.35f);
        pathBuilderQ.lineToRelative(2.79f, 2.79f);
        pathBuilderQ.curveToRelative(0.2f, 0.2f, 0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderQ.lineToRelative(-2.79f, 2.79f);
        pathBuilderQ.curveTo(19.54f, 22.46f, 19.0f, 22.24f, 19.0f, 21.79f);
        pathBuilderQ.verticalLineTo(20.0f);
        pathBuilderQ.horizontalLineToRelative(-3.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderQ, 3.0f, 16.21f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _forwardToInbox = imageVectorBuild;
        return imageVectorBuild;
    }
}
