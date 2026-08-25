package com.arflix.tv.data.repository;

import androidx.compose.foundation.lazy.LazyListScope;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7669i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7670l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7671m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7672n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7673o;

    public /* synthetic */ m0(MediaType mediaType, int i10, Integer num, Integer num2) {
        this.f7670l = i10;
        this.f7671m = mediaType;
        this.f7672n = num;
        this.f7673o = num2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7669i) {
            case 0:
                return Boolean.valueOf(TraktRepository.removeFromLocalContinueWatching$lambda$0(this.f7670l, (MediaType) this.f7671m, (Integer) this.f7672n, (Integer) this.f7673o, (ContinueWatchingItem) obj));
            default:
                return SettingsScreenKt.SubtitlePickerModal$lambda$3$1$1$0$0((List) this.f7671m, this.f7670l, (String) this.f7672n, (r7.l) this.f7673o, (LazyListScope) obj);
        }
    }

    public /* synthetic */ m0(List list, int i10, String str, r7.l lVar) {
        this.f7671m = list;
        this.f7670l = i10;
        this.f7672n = str;
        this.f7673o = lVar;
    }
}
