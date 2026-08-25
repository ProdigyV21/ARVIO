package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.text.font.FontFamily;
import com.arflix.tv.ui.theme.FontsKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"LiveMono", "Landroidx/compose/ui/text/font/FontFamily;", "getLiveMono", "()Landroidx/compose/ui/text/font/FontFamily;", "LocalLiveColors", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcom/arflix/tv/ui/screens/tv/live/LiveColors;", "getLocalLiveColors", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalLiveType", "Lcom/arflix/tv/ui/screens/tv/live/LiveType;", "getLocalLiveType", "LocalLiveDims", "Lcom/arflix/tv/ui/screens/tv/live/LiveDims;", "getLocalLiveDims", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LiveTokensKt {
    private static final FontFamily LiveMono = FontsKt.getInterFontFamily();
    private static final ProvidableCompositionLocal<LiveColors> LocalLiveColors = CompositionLocalKt.staticCompositionLocalOf(new f(16));
    private static final ProvidableCompositionLocal<LiveType> LocalLiveType = CompositionLocalKt.staticCompositionLocalOf(new f(17));
    private static final ProvidableCompositionLocal<LiveDims> LocalLiveDims = CompositionLocalKt.staticCompositionLocalOf(new f(18));

    public static final FontFamily getLiveMono() {
        return LiveMono;
    }

    public static final ProvidableCompositionLocal<LiveColors> getLocalLiveColors() {
        return LocalLiveColors;
    }

    public static final ProvidableCompositionLocal<LiveDims> getLocalLiveDims() {
        return LocalLiveDims;
    }

    public static final ProvidableCompositionLocal<LiveType> getLocalLiveType() {
        return LocalLiveType;
    }
}
