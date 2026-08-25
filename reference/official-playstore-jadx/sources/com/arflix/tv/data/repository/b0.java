package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.AudioTrackSelectorKt;
import com.arflix.tv.ui.components.ContextAction;
import com.arflix.tv.ui.components.ContextMenuKt;
import com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel;
import com.arflix.tv.ui.screens.tv.live.CategorySidebarKt;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import com.arflix.tv.ui.screens.tv.live.SearchOverlayKt;
import com.arflix.tv.ui.screens.tv.live.TouchCategoryRailKt;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7623i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7624l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7625m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7626n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7627o;

    public /* synthetic */ b0(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f7623i = i10;
        this.f7625m = obj;
        this.f7626n = obj2;
        this.f7627o = obj3;
        this.f7624l = obj4;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7623i) {
            case 0:
                return ProfileRepository.pushProfilesStateToCloud$lambda$0((String) this.f7625m, (ProfileRepository) this.f7627o, (List) this.f7624l, (String) this.f7626n, (JSONObject) obj);
            case 1:
                return AudioTrackSelectorKt.AudioTrackSelector$lambda$4$1$0$2$0((List) this.f7624l, (String) this.f7625m, (r7.l) this.f7626n, (MutableIntState) this.f7627o, (LazyListScope) obj);
            case 2:
                return ContextMenuKt.EpisodeContextMenu$lambda$0$0((r7.a) this.f7625m, (r7.a) this.f7626n, (r7.a) this.f7627o, (r7.a) this.f7624l, (ContextAction) obj);
            case 3:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$2$0((CollectionDetailsViewModel) this.f7625m, (r7.s) this.f7626n, (r7.p) this.f7627o, (Context) this.f7624l, (MediaItem) obj);
            case 4:
                return CategorySidebarKt.CategorySidebar$lambda$38$1$0((r7.l) this.f7625m, (MutableState) this.f7626n, (MutableState) this.f7627o, (MutableState) this.f7624l, ((Boolean) obj).booleanValue());
            case 5:
                return LiveTvEnhancementsKt.VariantPickerOverlay$lambda$5$0$1$0((List) this.f7624l, (FocusRequester) this.f7625m, (r7.l) this.f7626n, (r7.a) this.f7627o, (LazyListScope) obj);
            case 6:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$21$0((MutableState) this.f7625m, (MutableState) this.f7626n, (MutableState) this.f7627o, (MutableState) this.f7624l, (EnrichedChannel) obj);
            case 7:
                return SearchOverlayKt.SearchOverlay$lambda$16$1$1$0((MutableState) this.f7625m, (FocusRequester) this.f7626n, (r7.l) this.f7627o, (FocusRequester) this.f7624l, (LazyListScope) obj);
            default:
                return TouchCategoryRailKt.TouchCategoryRail$lambda$0$0((List) this.f7624l, (r7.a) this.f7626n, (String) this.f7625m, (r7.l) this.f7627o, (LazyListScope) obj);
        }
    }

    public /* synthetic */ b0(String str, ProfileRepository profileRepository, List list, String str2) {
        this.f7623i = 0;
        this.f7625m = str;
        this.f7627o = profileRepository;
        this.f7624l = list;
        this.f7626n = str2;
    }

    public /* synthetic */ b0(List list, Object obj, r7.l lVar, Object obj2, int i10) {
        this.f7623i = i10;
        this.f7624l = list;
        this.f7625m = obj;
        this.f7626n = lVar;
        this.f7627o = obj2;
    }

    public /* synthetic */ b0(List list, r7.a aVar, String str, r7.l lVar) {
        this.f7623i = 8;
        this.f7624l = list;
        this.f7626n = aVar;
        this.f7625m = str;
        this.f7627o = lVar;
    }
}
