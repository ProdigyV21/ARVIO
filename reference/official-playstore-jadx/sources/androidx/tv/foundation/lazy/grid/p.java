package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Landroidx/tv/foundation/lazy/grid/p;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "tv-foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface p extends LazyLayoutItemProvider {
    i2.k getKeyIndexMap();

    k0 getSpanLayoutProvider();
}
