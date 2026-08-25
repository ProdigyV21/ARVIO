package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.NextEpisode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000b\u001a\u00020\fJE\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\b*\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/ui/screens/home/ContinueWatchingRowReducer;", "", "<init>", "()V", "CATEGORY_ID", "", "CATEGORY_TITLE", "upsert", "", "Lcom/arflix/tv/data/model/Category;", "categories", "fresh", "Lcom/arflix/tv/data/model/MediaItem;", "remove", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "tmdbId", "", "season", "episode", "(Ljava/util/List;Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;)Ljava/util/List;", "orEmptyItems", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ContinueWatchingRowReducer {
    public static final int $stable = 0;
    private static final String CATEGORY_ID = "continue_watching";
    private static final String CATEGORY_TITLE = "Continue Watching";
    public static final ContinueWatchingRowReducer INSTANCE = new ContinueWatchingRowReducer();

    private ContinueWatchingRowReducer() {
    }

    private final List<MediaItem> orEmptyItems(Category category) {
        List<MediaItem> items = category != null ? category.getItems() : null;
        return items == null ? kotlin.collections.z.f19728i : items;
    }

    public final List<Category> remove(List<Category> categories, MediaType mediaType, int tmdbId, Integer season, Integer episode) {
        ArrayList arrayList = new ArrayList();
        for (Category category : categories) {
            if (kotlin.jvm.internal.p.a(category.getId(), CATEGORY_ID)) {
                List<MediaItem> items = category.getItems();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : items) {
                    MediaItem mediaItem = (MediaItem) obj;
                    if (mediaItem.getId() == tmdbId && (mediaType == null || mediaItem.getMediaType() == mediaType)) {
                        if (season != null || episode != null) {
                            NextEpisode nextEpisode = mediaItem.getNextEpisode();
                            if (nextEpisode != null && ((season != null && nextEpisode.getSeasonNumber() != season.intValue()) || (episode != null && nextEpisode.getEpisodeNumber() != episode.intValue()))) {
                            }
                        }
                    }
                    arrayList2.add(obj);
                }
                Category categoryCopy$default = Category.copy$default(category, null, null, arrayList2, 3, null);
                if (arrayList2.isEmpty()) {
                    categoryCopy$default = null;
                }
                category = categoryCopy$default;
            }
            if (category != null) {
                arrayList.add(category);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0104  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.arflix.tv.data.model.Category> upsert(java.util.List<com.arflix.tv.data.model.Category> r54, com.arflix.tv.data.model.MediaItem r55) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.ContinueWatchingRowReducer.upsert(java.util.List, com.arflix.tv.data.model.MediaItem):java.util.List");
    }
}
