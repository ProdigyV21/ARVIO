package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_playlistPlay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PlaylistPlay", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPlaylistPlay$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getPlaylistPlay", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlaylistPlayKt {
    private static ImageVector _playlistPlay;

    public static final ImageVector getPlaylistPlay(Icons.TwoTone twoTone) {
        ImageVector imageVector = _playlistPlay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PlaylistPlay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(3.0f, 10.0f, 11.0f, 2.0f, -11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(3.0f, 6.0f, 11.0f, 2.0f, -11.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(3.0f, 14.0f, 7.0f, 2.0f, -7.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(a.r(16.0f, 13.0f, 0.0f, 8.0f), 6.0f, -4.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _playlistPlay = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getPlaylistPlay$annotations(Icons.TwoTone twoTone) {
    }
}
