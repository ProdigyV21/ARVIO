package com.arflix.tv.ui.screens.profile;

import android.content.Context;
import android.widget.EditText;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10004i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ EditText f10005l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f10006m;

    public /* synthetic */ l(Context context, EditText editText, int i10) {
        this.f10004i = i10;
        this.f10006m = context;
        this.f10005l = editText;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f10004i) {
            case 0:
                ProfileDialogsKt.ProfileDialogContent$hideKeyboard$lambda$5(this.f10006m, this.f10005l);
                break;
            case 1:
                ProfileDialogsKt.ProfileDialogContent$showKeyboard(this.f10006m, this.f10005l);
                break;
            case 2:
                ProfileDialogsKt.ProfileDialogContent$showKeyboard(this.f10006m, this.f10005l);
                break;
            default:
                ProfileDialogsKt.ProfileDialogContent$showKeyboard$lambda$4(this.f10006m, this.f10005l);
                break;
        }
    }

    public /* synthetic */ l(EditText editText, Context context, int i10) {
        this.f10004i = i10;
        this.f10005l = editText;
        this.f10006m = context;
    }
}
