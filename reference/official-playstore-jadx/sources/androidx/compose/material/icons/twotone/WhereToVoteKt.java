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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_whereToVote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WhereToVote", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWhereToVote", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WhereToVoteKt {
    private static ImageVector _whereToVote;

    public static final ImageVector getWhereToVote(Icons.TwoTone twoTone) {
        ImageVector imageVector = _whereToVote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WhereToVote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 3.0f);
        pathBuilderA.curveTo(8.69f, 3.0f, 6.0f, 5.69f, 6.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 3.54f, 3.82f, 8.86f, 6.0f, 11.47f);
        pathBuilderA.curveToRelative(1.75f, -2.11f, 6.0f, -7.63f, 6.0f, -11.47f);
        pathBuilderA.curveToRelative(0.0f, -3.31f, -2.69f, -6.0f, -6.0f, -6.0f);
        a.z(pathBuilderA, 10.47f, 14.0f, -3.18f, -3.18f);
        pathBuilderA.lineTo(8.71f, 9.4f);
        pathBuilderA.lineToRelative(1.77f, 1.77f);
        pathBuilderA.lineToRelative(4.6f, -4.6f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.41f, 1.41f, 10.47f, 14.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 1.0f);
        pathBuilderA2.curveTo(7.59f, 1.0f, 4.0f, 4.59f, 4.0f, 9.0f);
        pathBuilderA2.curveToRelative(0.0f, 5.57f, 6.96f, 13.34f, 7.26f, 13.67f);
        pathBuilderA2.lineToRelative(0.74f, 0.82f);
        pathBuilderA2.lineToRelative(0.74f, -0.82f);
        pathBuilderA2.curveTo(13.04f, 22.34f, 20.0f, 14.57f, 20.0f, 9.0f);
        pathBuilderA2.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 20.47f);
        pathBuilderA2.curveTo(9.82f, 17.86f, 6.0f, 12.54f, 6.0f, 9.0f);
        pathBuilderA2.curveToRelative(0.0f, -3.31f, 2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderA2.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.83f, -4.25f, 9.36f, -6.0f, 11.47f);
        a.z(pathBuilderA2, 15.07f, 6.57f, -4.6f, 4.6f);
        pathBuilderA2.lineTo(8.71f, 9.4f);
        pathBuilderA2.lineToRelative(-1.42f, 1.42f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderA2, 10.47f, 14.0f, 6.01f, -6.01f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _whereToVote = imageVectorBuild;
        return imageVectorBuild;
    }
}
