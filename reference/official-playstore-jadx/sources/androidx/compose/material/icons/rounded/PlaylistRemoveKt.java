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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_playlistRemove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PlaylistRemove", "Landroidx/compose/material/icons/Icons$Rounded;", "getPlaylistRemove", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlaylistRemoveKt {
    private static ImageVector _playlistRemove;

    public static final ImageVector getPlaylistRemove(Icons.Rounded rounded) {
        ImageVector imageVector = _playlistRemove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PlaylistRemove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.71f, 21.3f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineTo(17.0f, 19.41f);
        pathBuilderA.lineToRelative(1.89f, 1.89f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(18.41f, 18.0f);
        pathBuilderA.lineToRelative(1.89f, -1.89f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.reflectiveCurveToRelative(-1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineTo(17.0f, 16.59f);
        pathBuilderA.lineToRelative(-1.89f, -1.89f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineTo(15.59f, 18.0f);
        pathBuilderA.lineToRelative(-1.89f, 1.89f);
        pathBuilderA.curveTo(13.32f, 20.27f, 13.32f, 20.91f, 13.71f, 21.3f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(9.0f);
        pathBuilderA.curveTo(13.55f, 12.0f, 14.0f, 11.55f, 14.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveTo(3.45f, 6.0f, 3.0f, 6.45f, 3.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(9.0f);
        pathBuilderA.curveTo(13.55f, 8.0f, 14.0f, 7.55f, 14.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.0f, 15.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveTo(3.45f, 14.0f, 3.0f, 14.45f, 3.0f, 15.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _playlistRemove = imageVectorBuild;
        return imageVectorBuild;
    }
}
