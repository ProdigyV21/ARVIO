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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoAlbum", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoAlbum", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhotoAlbum", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoAlbumKt {
    private static ImageVector _photoAlbum;

    public static final ImageVector getPhotoAlbum(Icons.Rounded rounded) {
        ImageVector imageVector = _photoAlbum;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhotoAlbum", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(18.0f, 2.0f, 6.0f);
        pathBuilderQ.curveTo(4.9f, 2.0f, 4.0f, 2.9f, 4.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(20.0f, 2.9f, 19.1f, 2.0f, 18.0f, 2.0f);
        a.p(pathBuilderQ, 15.24f, 10.55f, 13.5f, 9.5f);
        pathBuilderQ.lineToRelative(-1.74f, 1.05f);
        pathBuilderQ.curveToRelative(-0.33f, 0.2f, -0.76f, -0.04f, -0.76f, -0.43f);
        w.a.j(pathBuilderQ, 4.0f, 5.0f, 6.12f);
        pathBuilderQ.curveTo(16.0f, 10.51f, 15.58f, 10.75f, 15.24f, 10.55f);
        a.z(pathBuilderQ, 7.6f, 17.2f, 1.38f, -1.83f);
        pathBuilderQ.curveToRelative(0.2f, -0.27f, 0.6f, -0.27f, 0.8f, 0.0f);
        pathBuilderQ.lineTo(11.0f, 17.0f);
        pathBuilderQ.lineToRelative(2.23f, -2.97f);
        pathBuilderQ.curveToRelative(0.2f, -0.27f, 0.6f, -0.27f, 0.8f, 0.0f);
        pathBuilderQ.lineToRelative(2.38f, 3.17f);
        pathBuilderQ.curveToRelative(0.25f, 0.33f, 0.01f, 0.8f, -0.4f, 0.8f);
        pathBuilderQ.horizontalLineTo(8.0f);
        pathBuilderQ.curveTo(7.59f, 18.0f, 7.35f, 17.53f, 7.6f, 17.2f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoAlbum = imageVectorBuild;
        return imageVectorBuild;
    }
}
