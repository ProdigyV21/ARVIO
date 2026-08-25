package androidx.compose.material.icons.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_howToVote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HowToVote", "Landroidx/compose/material/icons/Icons$Sharp;", "getHowToVote", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HowToVoteKt {
    private static ImageVector _howToVote;

    public static final ImageVector getHowToVote(Icons.Sharp sharp) {
        ImageVector imageVector = _howToVote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HowToVote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        b.u(pathBuilderG, -3.0f, 3.0f, 6.0f, 18.0f);
        b.f(pathBuilderG, -6.0f, 19.81f, 7.96f);
        pathBuilderG.lineTo(13.45f, 1.6f);
        pathBuilderG.lineTo(5.68f, 9.36f);
        b.D(pathBuilderG, 6.36f, 6.36f, 7.77f, -7.76f);
        pathBuilderG.moveTo(13.46f, 4.41f);
        pathBuilderG.lineTo(17.0f, 7.95f);
        pathBuilderG.lineToRelative(-4.95f, 4.95f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderG, -3.54f, -3.54f, 4.95f, -4.95f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _howToVote = imageVectorBuild;
        return imageVectorBuild;
    }
}
