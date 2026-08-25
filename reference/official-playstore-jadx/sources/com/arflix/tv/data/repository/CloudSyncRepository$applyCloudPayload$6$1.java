package com.arflix.tv.data.repository;

import kotlin.Metadata;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncRepository$applyCloudPayload$6$1", f = "CloudSyncRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CloudSyncRepository$applyCloudPayload$6$1 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
    final /* synthetic */ String $activeProfileId;
    final /* synthetic */ String $fallbackAutoPlayMinQuality;
    final /* synthetic */ boolean $fallbackAutoPlayNext;
    final /* synthetic */ boolean $fallbackAutoPlaySingleSource;
    final /* synthetic */ String $fallbackCardLayoutMode;
    final /* synthetic */ String $fallbackDefaultAudioLanguage;
    final /* synthetic */ String $fallbackDefaultSubtitle;
    final /* synthetic */ String $fallbackFrameRateMatchingMode;
    final /* synthetic */ boolean $fallbackIncludeSpecials;
    final /* synthetic */ long $fallbackSubtitleSettingsUpdatedAt;
    final /* synthetic */ kotlin.jvm.internal.f0 $preservedNewerLocalSubtitle;
    final /* synthetic */ JSONObject $root;
    final /* synthetic */ CloudSyncRepository $this_run;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncRepository$applyCloudPayload$6$1(CloudSyncRepository cloudSyncRepository, String str, String str2, long j10, kotlin.jvm.internal.f0 f0Var, String str3, String str4, String str5, boolean z, boolean z5, String str6, boolean z10, JSONObject jSONObject, d7.d<? super CloudSyncRepository$applyCloudPayload$6$1> dVar) {
        super(2, dVar);
        this.$this_run = cloudSyncRepository;
        this.$activeProfileId = str;
        this.$fallbackDefaultSubtitle = str2;
        this.$fallbackSubtitleSettingsUpdatedAt = j10;
        this.$preservedNewerLocalSubtitle = f0Var;
        this.$fallbackDefaultAudioLanguage = str3;
        this.$fallbackCardLayoutMode = str4;
        this.$fallbackFrameRateMatchingMode = str5;
        this.$fallbackAutoPlayNext = z;
        this.$fallbackAutoPlaySingleSource = z5;
        this.$fallbackAutoPlayMinQuality = str6;
        this.$fallbackIncludeSpecials = z10;
        this.$root = jSONObject;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        CloudSyncRepository$applyCloudPayload$6$1 cloudSyncRepository$applyCloudPayload$6$1 = new CloudSyncRepository$applyCloudPayload$6$1(this.$this_run, this.$activeProfileId, this.$fallbackDefaultSubtitle, this.$fallbackSubtitleSettingsUpdatedAt, this.$preservedNewerLocalSubtitle, this.$fallbackDefaultAudioLanguage, this.$fallbackCardLayoutMode, this.$fallbackFrameRateMatchingMode, this.$fallbackAutoPlayNext, this.$fallbackAutoPlaySingleSource, this.$fallbackAutoPlayMinQuality, this.$fallbackIncludeSpecials, this.$root, dVar);
        cloudSyncRepository$applyCloudPayload$6$1.L$0 = obj;
        return cloudSyncRepository$applyCloudPayload$6$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
        return ((CloudSyncRepository$applyCloudPayload$6$1) create(bVar, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0087  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncRepository$applyCloudPayload$6$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
