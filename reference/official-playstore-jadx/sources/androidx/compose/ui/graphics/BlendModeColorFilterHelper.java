package androidx.compose.ui.graphics;

import android.support.v4.media.session.q;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/graphics/BlendModeColorFilterHelper;", "", "()V", "BlendModeColorFilter", "Landroid/graphics/BlendModeColorFilter;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "BlendModeColorFilter-xETnrds", "(JI)Landroid/graphics/BlendModeColorFilter;", "createBlendModeColorFilter", "Landroidx/compose/ui/graphics/BlendModeColorFilter;", "androidBlendModeColorFilter", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class BlendModeColorFilterHelper {
    public static final BlendModeColorFilterHelper INSTANCE = new BlendModeColorFilterHelper();

    private BlendModeColorFilterHelper() {
    }

    /* JADX INFO: renamed from: BlendModeColorFilter-xETnrds, reason: not valid java name */
    public final android.graphics.BlendModeColorFilter m3424BlendModeColorFilterxETnrds(long color, int blendMode) {
        q.g();
        return q.a(ColorKt.m3526toArgb8_81llA(color), AndroidBlendMode_androidKt.m3328toAndroidBlendModes9anfk8(blendMode));
    }

    public final BlendModeColorFilter createBlendModeColorFilter(android.graphics.BlendModeColorFilter androidBlendModeColorFilter) {
        return new BlendModeColorFilter(ColorKt.Color(androidBlendModeColorFilter.getColor()), AndroidBlendMode_androidKt.toComposeBlendMode(androidBlendModeColorFilter.getMode()), androidBlendModeColorFilter, null);
    }
}
