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
import v.b;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_forum", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Forum", "Landroidx/compose/material/icons/Icons$Outlined;", "getForum", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForumKt {
    private static ImageVector _forum;

    public static final ImageVector getForum(Icons.Outlined outlined) {
        ImageVector imageVector = _forum;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Forum", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(15.0f, 4.0f, 7.0f, 5.17f, 11.0f);
        pathBuilderS.lineTo(4.0f, 12.17f);
        pathBuilderS.lineTo(4.0f, 4.0f);
        pathBuilderS.horizontalLineToRelative(11.0f);
        pathBuilderS.moveToRelative(1.0f, -2.0f);
        pathBuilderS.lineTo(3.0f, 2.0f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        f.q(pathBuilderS, 14.0f, 4.0f, -4.0f, 10.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderS.lineTo(17.0f, 3.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        b.w(pathBuilderS, 21.0f, 6.0f, -2.0f, 9.0f);
        pathBuilderS.lineTo(6.0f, 15.0f);
        pathBuilderS.verticalLineToRelative(2.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderS.horizontalLineToRelative(11.0f);
        pathBuilderS.lineToRelative(4.0f, 4.0f);
        pathBuilderS.lineTo(22.0f, 7.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _forum = imageVectorBuild;
        return imageVectorBuild;
    }
}
