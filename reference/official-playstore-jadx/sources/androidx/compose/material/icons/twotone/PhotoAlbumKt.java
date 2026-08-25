package androidx.compose.material.icons.twotone;

import a0.b;
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
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoAlbum", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoAlbum", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPhotoAlbum", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoAlbumKt {
    private static ImageVector _photoAlbum;

    public static final ImageVector getPhotoAlbum(Icons.TwoTone twoTone) {
        ImageVector imageVector = _photoAlbum;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PhotoAlbum", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(16.0f, 4.0f, 7.0f, -2.5f, -1.5f);
        c.f(pathBuilderG, 11.0f, 11.0f, 4.0f, 6.0f);
        b.A(pathBuilderG, 16.0f, 12.0f, 4.0f, 16.0f);
        pathBuilderG.moveTo(7.0f, 18.0f);
        pathBuilderG.lineToRelative(2.38f, -3.17f);
        pathBuilderG.lineTo(11.0f, 17.0f);
        pathBuilderG.lineToRelative(2.62f, -3.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderG, 17.0f, 18.0f, 7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(18.0f, 2.0f, 6.0f);
        pathBuilderQ.curveTo(4.9f, 2.0f, 4.0f, 2.9f, 4.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(20.0f, 2.9f, 19.1f, 2.0f, 18.0f, 2.0f);
        f.o(pathBuilderQ, 18.0f, 20.0f, 6.0f, 4.0f);
        f.y(pathBuilderQ, 5.0f, 7.0f, 2.5f, -1.5f);
        v.b.n(pathBuilderQ, 16.0f, 11.0f, 4.0f, 2.0f);
        f.r(pathBuilderQ, 20.0f, 13.62f, 13.5f);
        pathBuilderQ.lineTo(17.0f, 18.0f);
        pathBuilderQ.horizontalLineTo(7.0f);
        pathBuilderQ.lineToRelative(2.38f, -3.17f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderQ, 11.0f, 17.0f, 13.62f, 13.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoAlbum = imageVectorBuild;
        return imageVectorBuild;
    }
}
