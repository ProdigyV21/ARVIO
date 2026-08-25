package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001J0\u0010\u0003\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\t"}, d2 = {"Landroidx/tv/material3/CardContainerDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "contentColor", "focusedContentColor", "pressedContentColor", "Landroidx/tv/material3/o;", "contentColor-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/o;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CardContainerDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CardContainerDefaults f5538a = new CardContainerDefaults();

    /* JADX INFO: renamed from: contentColor-RGew2ao, reason: not valid java name */
    public final o m5977contentColorRGew2ao(long j10, long j11, long j12, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            j10 = MaterialTheme.f5670a.getColorScheme(composer, 6).n();
        }
        if ((i11 & 2) != 0) {
            j11 = j10;
        }
        if ((i11 & 4) != 0) {
            j12 = j11;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1223808757, i10, -1, "androidx.tv.material3.CardContainerDefaults.contentColor (CardContainer.kt:176)");
        }
        long j13 = j10;
        o oVar = new o(j13, j11, j12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return oVar;
    }
}
