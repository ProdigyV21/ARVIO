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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ruleFolder", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RuleFolder", "Landroidx/compose/material/icons/Icons$Rounded;", "getRuleFolder", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RuleFolderKt {
    private static ImageVector _ruleFolder;

    public static final ImageVector getRuleFolder(Icons.Rounded rounded) {
        ImageVector imageVector = _ruleFolder;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RuleFolder", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -8.0f, -1.41f, -1.41f);
        pathBuilderG.curveTo(10.21f, 4.21f, 9.7f, 4.0f, 9.17f, 4.0f);
        pathBuilderG.horizontalLineTo(4.0f);
        pathBuilderG.curveTo(2.9f, 4.0f, 2.01f, 4.9f, 2.01f, 6.0f);
        pathBuilderG.lineTo(2.0f, 18.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(8.0f);
        pathBuilderG.curveTo(22.0f, 6.9f, 21.1f, 6.0f, 20.0f, 6.0f);
        a.z(pathBuilderG, 7.12f, 15.29f, -1.41f, -1.41f);
        pathBuilderG.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderG.lineToRelative(0.71f, 0.71f);
        pathBuilderG.lineToRelative(2.83f, -2.83f);
        pathBuilderG.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderG.lineToRelative(-3.54f, 3.54f);
        pathBuilderG.curveTo(8.14f, 15.68f, 7.51f, 15.68f, 7.12f, 15.29f);
        a.z(pathBuilderG, 17.41f, 13.0f, 0.88f, 0.88f);
        pathBuilderG.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderG.lineTo(16.0f, 14.41f);
        pathBuilderG.lineToRelative(-0.88f, 0.88f);
        pathBuilderG.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderG.lineTo(14.59f, 13.0f);
        pathBuilderG.lineToRelative(-0.88f, -0.88f);
        pathBuilderG.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderG.lineTo(16.0f, 11.59f);
        pathBuilderG.lineToRelative(0.88f, -0.88f);
        pathBuilderG.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderG, 17.41f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ruleFolder = imageVectorBuild;
        return imageVectorBuild;
    }
}
