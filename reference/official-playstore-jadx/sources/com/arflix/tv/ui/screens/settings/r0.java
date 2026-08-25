package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10868i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10869l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f10870m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f10871n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f10872o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f10873p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ MutableState f10874q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f10875r;

    public /* synthetic */ r0(r7.a aVar, FocusRequester focusRequester, r7.a aVar2, MutableIntState mutableIntState, String str, String str2, boolean z) {
        this.f10869l = aVar;
        this.f10870m = focusRequester;
        this.f10873p = aVar2;
        this.f10874q = mutableIntState;
        this.f10871n = str;
        this.f10875r = str2;
        this.f10872o = z;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10868i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.AiApiKeyDialog$lambda$6(this.f10872o, (SubtitleAiModel) this.f10873p, this.f10870m, this.f10874q, this.f10871n, this.f10869l, (r7.l) this.f10875r, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CloudPairModal$lambda$6(this.f10869l, this.f10870m, (r7.a) this.f10873p, (MutableIntState) this.f10874q, this.f10871n, (String) this.f10875r, this.f10872o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ r0(boolean z, SubtitleAiModel subtitleAiModel, FocusRequester focusRequester, MutableState mutableState, String str, r7.a aVar, r7.l lVar) {
        this.f10872o = z;
        this.f10873p = subtitleAiModel;
        this.f10870m = focusRequester;
        this.f10874q = mutableState;
        this.f10871n = str;
        this.f10869l = aVar;
        this.f10875r = lVar;
    }
}
