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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_backspace", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Backspace", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getBackspace", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackspaceKt {
    private static ImageVector _backspace;

    public static final ImageVector getBackspace(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _backspace;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.Backspace", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 3.0f, 7.0f, 3.0f);
        pathBuilderJ.curveToRelative(-0.69f, 0.0f, -1.23f, 0.35f, -1.59f, 0.88f);
        pathBuilderJ.lineTo(0.37f, 11.45f);
        pathBuilderJ.curveToRelative(-0.22f, 0.34f, -0.22f, 0.77f, 0.0f, 1.11f);
        pathBuilderJ.lineToRelative(5.04f, 7.56f);
        pathBuilderJ.curveToRelative(0.36f, 0.52f, 0.9f, 0.88f, 1.59f, 0.88f);
        pathBuilderJ.horizontalLineToRelative(15.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(24.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(18.3f, 16.3f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(14.0f, 13.41f);
        pathBuilderJ.lineToRelative(-2.89f, 2.89f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineTo(12.59f, 12.0f);
        pathBuilderJ.lineTo(9.7f, 9.11f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineTo(14.0f, 10.59f);
        pathBuilderJ.lineToRelative(2.89f, -2.89f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineTo(15.41f, 12.0f);
        pathBuilderJ.lineToRelative(2.89f, 2.89f);
        pathBuilderJ.curveToRelative(0.38f, 0.38f, 0.38f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _backspace = imageVectorBuild;
        return imageVectorBuild;
    }
}
