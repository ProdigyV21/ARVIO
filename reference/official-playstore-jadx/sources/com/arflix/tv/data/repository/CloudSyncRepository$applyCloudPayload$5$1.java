package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.ui.components.CardLayoutModeKt;
import com.arflix.tv.util.AppLanguageKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncRepository$applyCloudPayload$5$1", f = "CloudSyncRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CloudSyncRepository$applyCloudPayload$5$1 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
    final /* synthetic */ String $activeProfileId;
    final /* synthetic */ LinkedHashMap<String, String> $homeServerConnectionsToImport;
    final /* synthetic */ kotlin.jvm.internal.f0 $preservedNewerLocalSubtitle;
    final /* synthetic */ Map<String, CloudSyncRepository.CloudProfileSettings> $settingsByProfile;
    final /* synthetic */ LinkedHashMap<String, String> $torrServerUrlsToImport;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CloudSyncRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncRepository$applyCloudPayload$5$1(Map<String, CloudSyncRepository.CloudProfileSettings> map, CloudSyncRepository cloudSyncRepository, kotlin.jvm.internal.f0 f0Var, String str, LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<String, String> linkedHashMap2, d7.d<? super CloudSyncRepository$applyCloudPayload$5$1> dVar) {
        super(2, dVar);
        this.$settingsByProfile = map;
        this.this$0 = cloudSyncRepository;
        this.$preservedNewerLocalSubtitle = f0Var;
        this.$activeProfileId = str;
        this.$homeServerConnectionsToImport = linkedHashMap;
        this.$torrServerUrlsToImport = linkedHashMap2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        CloudSyncRepository$applyCloudPayload$5$1 cloudSyncRepository$applyCloudPayload$5$1 = new CloudSyncRepository$applyCloudPayload$5$1(this.$settingsByProfile, this.this$0, this.$preservedNewerLocalSubtitle, this.$activeProfileId, this.$homeServerConnectionsToImport, this.$torrServerUrlsToImport, dVar);
        cloudSyncRepository$applyCloudPayload$5$1.L$0 = obj;
        return cloudSyncRepository$applyCloudPayload$5$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
        return ((CloudSyncRepository$applyCloudPayload$5$1) create(bVar, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Long lS;
        c1.b bVar = (c1.b) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        Map<String, CloudSyncRepository.CloudProfileSettings> map = this.$settingsByProfile;
        CloudSyncRepository cloudSyncRepository = this.this$0;
        kotlin.jvm.internal.f0 f0Var = this.$preservedNewerLocalSubtitle;
        String str = this.$activeProfileId;
        LinkedHashMap<String, String> linkedHashMap = this.$homeServerConnectionsToImport;
        LinkedHashMap<String, String> linkedHashMap2 = this.$torrServerUrlsToImport;
        for (Map.Entry<String, CloudSyncRepository.CloudProfileSettings> entry : map.entrySet()) {
            String key = entry.getKey();
            CloudSyncRepository.CloudProfileSettings value = entry.getValue();
            c1.e eVarDefaultSubtitleKeyFor = cloudSyncRepository.defaultSubtitleKeyFor(key);
            c1.e eVarSubtitleSettingsUpdatedAtKeyFor = cloudSyncRepository.subtitleSettingsUpdatedAtKeyFor(key);
            String str2 = (String) bVar.f7368a.get(eVarDefaultSubtitleKeyFor);
            String string = str2 != null ? kotlin.text.o.L0(str2).toString() : null;
            if (string == null) {
                string = "";
            }
            String str3 = (String) bVar.f7368a.get(eVarSubtitleSettingsUpdatedAtKeyFor);
            long jLongValue = (str3 == null || (lS = kotlin.text.u.S(str3)) == null) ? 0L : lS.longValue();
            String string2 = kotlin.text.o.L0(value.getDefaultSubtitle()).toString();
            if (kotlin.text.o.h0(string2)) {
                string2 = "Off";
            }
            if (kotlin.text.o.h0(string) || string.equalsIgnoreCase(string2) || (jLongValue <= value.getSubtitleSettingsUpdatedAt() && (value.getSubtitleSettingsUpdatedAt() > 0 || !string2.equalsIgnoreCase("Off") || string.equalsIgnoreCase("Off")))) {
                bVar.d(eVarDefaultSubtitleKeyFor, string2);
                if (value.getSubtitleSettingsUpdatedAt() > 0) {
                    bVar.d(eVarSubtitleSettingsUpdatedAtKeyFor, String.valueOf(value.getSubtitleSettingsUpdatedAt()));
                }
            } else {
                f0Var.f19738i = true;
            }
            bVar.d(cloudSyncRepository.defaultAudioLanguageKeyFor(key), value.getDefaultAudioLanguage());
            bVar.d(cloudSyncRepository.contentLanguageKeyFor(key), value.getContentLanguage());
            if (kotlin.jvm.internal.p.a(key, str)) {
                bVar.d(AppLanguageKt.getLAST_APP_LANGUAGE_KEY(), value.getContentLanguage());
                cloudSyncRepository.context.getSharedPreferences("app_locale", 0).edit().putString("locale_tag", value.getContentLanguage()).apply();
            }
            bVar.d(cloudSyncRepository.trailerAutoPlayKeyFor(key), Boolean.valueOf(value.getTrailerAutoPlay()));
            bVar.d(cloudSyncRepository.trailerSoundEnabledKeyFor(key), Boolean.valueOf(value.getTrailerSoundEnabled()));
            bVar.d(cloudSyncRepository.trailerDelayKeyFor(key), String.valueOf(value.getTrailerDelaySeconds()));
            bVar.d(cloudSyncRepository.trailerInCardsKeyFor(key), Boolean.valueOf(value.getTrailerInCards()));
            bVar.d(cloudSyncRepository.clockFormatKeyFor(key), value.getClockFormat());
            bVar.d(cloudSyncRepository.showBudgetKeyFor(key), Boolean.valueOf(value.getShowBudget()));
            bVar.d(cloudSyncRepository.showEpisodeRatingsKeyFor(key), Boolean.valueOf(value.getShowEpisodeRatings()));
            Boolean showLoadingStats = value.getShowLoadingStats();
            if (showLoadingStats != null) {
                bVar.d(cloudSyncRepository.showLoadingStatsKeyFor(key), showLoadingStats);
            }
            bVar.d(cloudSyncRepository.spoilerBlurKeyFor(key), Boolean.valueOf(value.getSpoilerBlurEnabled()));
            bVar.d(cloudSyncRepository.volumeBoostDbKeyFor(key), String.valueOf(qb.d.n(value.getVolumeBoostDb(), 0, 15)));
            c1.e eVarDnsProviderKeyFor = cloudSyncRepository.dnsProviderKeyFor(key);
            String dnsProvider = value.getDnsProvider();
            if (kotlin.text.o.h0(dnsProvider)) {
                dnsProvider = "system";
            }
            bVar.d(eVarDnsProviderKeyFor, dnsProvider);
            if (kotlin.text.o.h0(value.getSubtitleUsageJson())) {
                bVar.c(cloudSyncRepository.subtitleUsageKeyFor(key));
            } else {
                bVar.d(cloudSyncRepository.subtitleUsageKeyFor(key), value.getSubtitleUsageJson());
            }
            bVar.d(cloudSyncRepository.subtitleSizeKeyFor(key), value.getSubtitleSize());
            bVar.d(cloudSyncRepository.subtitleColorKeyFor(key), value.getSubtitleColor());
            bVar.d(cloudSyncRepository.subtitleOffsetKeyFor(key), value.getSubtitleOffset());
            bVar.d(cloudSyncRepository.subtitleStyleKeyFor(key), value.getSubtitleStyle());
            bVar.d(cloudSyncRepository.subtitleStylizedKeyFor(key), Boolean.valueOf(value.getSubtitleStylized()));
            c1.e eVarSecondarySubtitleKeyFor = cloudSyncRepository.secondarySubtitleKeyFor(key);
            String secondarySubtitle = value.getSecondarySubtitle();
            bVar.d(eVarSecondarySubtitleKeyFor, kotlin.text.o.h0(secondarySubtitle) ? "Off" : secondarySubtitle);
            bVar.d(cloudSyncRepository.filterSubtitlesByLanguageKeyFor(key), Boolean.valueOf(value.getFilterSubtitlesByLanguage()));
            String homeServerConnectionJson = value.getHomeServerConnectionJson();
            if (homeServerConnectionJson != null) {
                linkedHashMap.put(key, homeServerConnectionJson);
            }
            String torrServerBaseUrl = value.getTorrServerBaseUrl();
            if (torrServerBaseUrl != null) {
                linkedHashMap2.put(key, torrServerBaseUrl);
            }
            bVar.d(cloudSyncRepository.cardLayoutModeKeyFor(key), CardLayoutModeKt.normalizeCardLayoutMode(value.getCardLayoutMode()));
            for (Map.Entry<String, String> entry2 : value.getCatalogueRowLayoutModes().entrySet()) {
                bVar.d(CardLayoutModeKt.profileCatalogueRowLayoutModeKey(key, entry2.getKey()), CardLayoutModeKt.normalizeCardLayoutMode(entry2.getValue()));
            }
            bVar.d(cloudSyncRepository.frameRateMatchingModeKeyFor(key), cloudSyncRepository.normalizeFrameRateMode(value.getFrameRateMatchingMode()));
            bVar.d(cloudSyncRepository.autoPlayNextKeyFor(key), Boolean.valueOf(value.getAutoPlayNext()));
            bVar.d(cloudSyncRepository.autoPlaySingleSourceKeyFor(key), Boolean.valueOf(value.getAutoPlaySingleSource()));
            bVar.d(cloudSyncRepository.autoPlayMinQualityKeyFor(key), cloudSyncRepository.normalizeAutoPlayMinQuality(value.getAutoPlayMinQuality()));
            bVar.d(cloudSyncRepository.includeSpecialsKeyFor(key), Boolean.valueOf(value.getIncludeSpecials()));
        }
        return t0.f22605a;
    }
}
