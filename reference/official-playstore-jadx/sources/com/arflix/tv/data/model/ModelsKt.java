package com.arflix.tv.data.model;

import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.text.u;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"isPortrait", "", "Lcom/arflix/tv/data/model/Category;", "globalPosterMode", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ModelsKt {
    public static final boolean isPortrait(Category category, boolean z) {
        if (!u.P(category.getId(), "collection_row_", false)) {
            return z;
        }
        MediaItem mediaItem = (MediaItem) x.o0(category.getItems());
        return (mediaItem != null ? mediaItem.getCollectionTileShape() : null) == CollectionTileShape.POSTER;
    }
}
