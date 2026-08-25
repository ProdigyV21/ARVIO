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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_howToVote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HowToVote", "Landroidx/compose/material/icons/Icons$Filled;", "getHowToVote", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HowToVoteKt {
    private static ImageVector _howToVote;

    public static final ImageVector getHowToVote(Icons.Filled filled) {
        ImageVector imageVector = _howToVote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HowToVote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(18.0f, 13.0f, -0.68f, -2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(1.91f);
        pathBuilderG.lineTo(19.0f, 17.0f);
        pathBuilderG.lineTo(5.0f, 17.0f);
        pathBuilderG.lineToRelative(1.78f, -2.0f);
        pathBuilderG.horizontalLineToRelative(2.05f);
        pathBuilderG.lineToRelative(-2.0f, -2.0f);
        pathBuilderG.lineTo(6.0f, 13.0f);
        pathBuilderG.lineToRelative(-3.0f, 3.0f);
        pathBuilderG.verticalLineToRelative(4.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderG.lineTo(19.0f, 22.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        c.t(pathBuilderG, -4.0f, -3.0f, -3.0f);
        pathBuilderG.moveTo(17.0f, 7.95f);
        pathBuilderG.lineToRelative(-4.95f, 4.95f);
        pathBuilderG.lineToRelative(-3.54f, -3.54f);
        d.C(pathBuilderG, 4.95f, -4.95f, 17.0f, 7.95f);
        pathBuilderG.moveTo(12.76f, 2.29f);
        pathBuilderG.lineTo(6.39f, 8.66f);
        pathBuilderG.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderG.lineToRelative(4.95f, 4.95f);
        pathBuilderG.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderG.lineToRelative(6.36f, -6.36f);
        pathBuilderG.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderG.lineTo(14.16f, 2.3f);
        pathBuilderG.curveToRelative(-0.38f, -0.4f, -1.01f, -0.4f, -1.4f, -0.01f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _howToVote = imageVectorBuild;
        return imageVectorBuild;
    }
}
