package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"compactHomeCategoriesForCache", "", "Lcom/arflix/tv/data/model/Category;", "categories", "maxItemsPerCategory", "", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HomeViewModelKt {
    public static final List<Category> compactHomeCategoriesForCache(List<Category> list, int i10) {
        ArrayList arrayList = new ArrayList();
        for (Category category : list) {
            List listU = ga.r.U(ga.r.S(ga.r.I(new ga.p(category.getItems(), 3), new d(20)), i10));
            Category categoryCopy$default = Category.copy$default(category, null, null, listU, 3, null);
            if (listU.isEmpty()) {
                categoryCopy$default = null;
            }
            if (categoryCopy$default != null) {
                arrayList.add(categoryCopy$default);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean compactHomeCategoriesForCache$lambda$0$0(MediaItem mediaItem) {
        return (mediaItem.isPlaceholder() || kotlin.text.o.h0(mediaItem.getTitle()) || kotlin.jvm.internal.p.a(mediaItem.getTitle(), "Unknown")) ? false : true;
    }
}
