package com.arflix.tv.ui.screens.settings;

import android.widget.EditText;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h4 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10548i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10549l;

    public /* synthetic */ h4(Object obj, int i10) {
        this.f10548i = i10;
        this.f10549l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10548i) {
            case 0:
                return Boolean.valueOf(SettingsViewModel.C14982.invokeSuspend$lambda$0((String) this.f10549l, (IptvChannel) obj));
            case 1:
                return Boolean.valueOf(SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1.invokeSuspend$lambda$0((String) this.f10549l, (x6.j0) obj));
            default:
                return SettingsScreenKt.InputModal$lambda$19$2$1$0$0$0$1$1$0((InputField) this.f10549l, (EditText) obj);
        }
    }
}
