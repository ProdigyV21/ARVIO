package com.arflix.tv.data.local;

import android.widget.EditText;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7614i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f7615l;

    public /* synthetic */ a(String str, int i10) {
        this.f7614i = i10;
        this.f7615l = str;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7614i) {
            case 0:
                return Boolean.valueOf(PluginDataStore.removeRepository$lambda$0(this.f7615l, (PluginRepository) obj));
            case 1:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$2$1$0(this.f7615l, (EditText) obj);
            default:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$2$1$0(this.f7615l, (EditText) obj);
        }
    }
}
