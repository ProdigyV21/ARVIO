package androidx.compose.ui.graphics;

import android.graphics.Shader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0007\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\t"}, d2 = {"Landroidx/compose/ui/graphics/TileModeVerificationHelper;", "", "()V", "getComposeTileModeDecal", "Landroidx/compose/ui/graphics/TileMode;", "getComposeTileModeDecal-3opZhB0", "()I", "getFrameworkTileModeDecal", "Landroid/graphics/Shader$TileMode;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TileModeVerificationHelper {
    public static final TileModeVerificationHelper INSTANCE = new TileModeVerificationHelper();

    private TileModeVerificationHelper() {
    }

    /* JADX INFO: renamed from: getComposeTileModeDecal-3opZhB0, reason: not valid java name */
    public final int m3827getComposeTileModeDecal3opZhB0() {
        return TileMode.INSTANCE.m3824getDecal3opZhB0();
    }

    public final Shader.TileMode getFrameworkTileModeDecal() {
        return Shader.TileMode.DECAL;
    }
}
