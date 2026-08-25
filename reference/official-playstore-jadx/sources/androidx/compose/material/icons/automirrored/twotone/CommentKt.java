package androidx.compose.material.icons.automirrored.twotone;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_comment", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Comment", "Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;", "getComment", "(Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CommentKt {
    private static ImageVector _comment;

    public static final ImageVector getComment(Icons.AutoMirrored.TwoTone twoTone) {
        ImageVector imageVector = _comment;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.TwoTone.Comment", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 17.17f, 20.0f, 4.0f);
        a.h(pathBuilderJ, 4.0f, 4.0f, 12.0f, 14.83f);
        c.z(pathBuilderJ, 20.0f, 17.17f, 18.0f, 14.0f);
        a.h(pathBuilderJ, 6.0f, 14.0f, -2.0f, 12.0f);
        b.f(pathBuilderJ, 2.0f, 18.0f, 11.0f);
        pathBuilderJ.lineTo(6.0f, 11.0f);
        b.g(pathBuilderJ, 6.0f, 9.0f, 12.0f, 2.0f);
        pathBuilderJ.moveTo(18.0f, 8.0f);
        pathBuilderJ.lineTo(6.0f, 8.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderJ, 6.0f, 6.0f, 12.0f, 2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(4.0f, 18.0f, 14.0f, 4.0f, 4.0f);
        pathBuilderG.lineToRelative(-0.01f, -18.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.89f, -2.0f, -1.99f, -2.0f);
        pathBuilderG.lineTo(4.0f, 2.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        b.w(pathBuilderG, 4.0f, 4.0f, 16.0f, 13.17f);
        pathBuilderG.lineTo(18.83f, 16.0f);
        b.y(pathBuilderG, 4.0f, 16.0f, 4.0f, 4.0f);
        a.C(pathBuilderG, 6.0f, 12.0f, 12.0f, 2.0f);
        c.z(pathBuilderG, 6.0f, 14.0f, 6.0f, 9.0f);
        d.m(pathBuilderG, 12.0f, 2.0f, 6.0f, 11.0f);
        a.C(pathBuilderG, 6.0f, 6.0f, 12.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderG, 6.0f, 8.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _comment = imageVectorBuild;
        return imageVectorBuild;
    }
}
