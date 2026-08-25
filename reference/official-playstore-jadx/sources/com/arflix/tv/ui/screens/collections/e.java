package com.arflix.tv.ui.screens.collections;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.ScraperInfo;
import com.arflix.tv.ui.screens.crash.CrashReportActivityKt;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.plugin.PluginViewModel;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import java.util.ArrayList;
import java.util.regex.Matcher;
import kotlin.jvm.internal.f0;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8602i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8603l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8604m;

    public /* synthetic */ e(Object obj, Object obj2, int i10) {
        this.f8602i = i10;
        this.f8603l = obj;
        this.f8604m = obj2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8602i) {
            case 0:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$5$0((CollectionDetailsViewModel) this.f8603l, (CollectionTab) this.f8604m);
            case 1:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$2$0$0((Context) this.f8603l, (MediaItem) this.f8604m);
            case 2:
                return VideoHeroKt.VideoHero$lambda$2$0$0((f0) this.f8603l, (State) this.f8604m);
            case 3:
                return CrashReportActivityKt.CrashReportScreen$lambda$9$0$2$0$0((Context) this.f8603l, (String) this.f8604m);
            case 4:
                return PluginScreenKt.PluginScreen$lambda$18$3$1$0$0((PluginViewModel) this.f8603l, (ScraperInfo) this.f8604m);
            case 5:
                return PluginScreenKt.PluginScreen$lambda$20$4$0$0((PluginViewModel) this.f8603l, (PluginRepository) this.f8604m);
            case 6:
                return PluginScreenKt.PluginScreen$lambda$18$2$0$0$0((PluginRepository) this.f8603l, (MutableState) this.f8604m);
            case 7:
                return SearchScreenKt.SearchScreen$lambda$69$0$5$0((ArrayList) this.f8603l, (MutableIntState) this.f8604m);
            case 8:
                return TelegramSettingsScreenKt.TelegramSettingsScreen$lambda$5$1$0((TelegramSettingsViewModel) this.f8603l, (MutableState) this.f8604m);
            case 9:
                return WatchlistScreenKt.WatchlistScreen$lambda$61$0((r7.a) this.f8603l, (MutableState) this.f8604m);
            default:
                kotlin.text.m mVar = (kotlin.text.m) this.f8603l;
                CharSequence charSequence = (CharSequence) this.f8604m;
                Matcher matcher = mVar.f19951i.matcher(charSequence);
                if (matcher.find(0)) {
                    return new kotlin.text.k(matcher, charSequence);
                }
                return null;
        }
    }
}
