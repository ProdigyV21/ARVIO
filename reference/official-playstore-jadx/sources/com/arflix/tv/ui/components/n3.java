package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n3 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8209i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f8210l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f8211m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f8212n;

    public /* synthetic */ n3(r7.a aVar, Context context, MutableState mutableState, int i10) {
        this.f8209i = i10;
        this.f8210l = aVar;
        this.f8211m = context;
        this.f8212n = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8209i) {
            case 0:
                return TextInputModalKt.TextInputModal$lambda$16$0(this.f8210l, this.f8211m, this.f8212n);
            case 1:
                return TextInputModalKt.TextInputModal$lambda$17$1$0$1$0$0(this.f8210l, this.f8211m, this.f8212n);
            case 2:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$3$0$0$0(this.f8210l, this.f8212n, this.f8211m);
            case 3:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$3$0$1$0(this.f8210l, this.f8212n, this.f8211m);
            case 4:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$3$0$2$0(this.f8210l, this.f8212n, this.f8211m);
            case 5:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$4$0$0(this.f8210l, this.f8212n, this.f8211m);
            case 6:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$4$1$0$0(this.f8210l, this.f8212n, this.f8211m);
            case 7:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$4$1$1$0(this.f8210l, this.f8212n, this.f8211m);
            case 8:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$3$0$0$0(this.f8210l, this.f8212n, this.f8211m);
            case 9:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$3$0$1$0(this.f8210l, this.f8212n, this.f8211m);
            case 10:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$3$0$2$0(this.f8210l, this.f8212n, this.f8211m);
            case 11:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$4$0$0(this.f8210l, this.f8212n, this.f8211m);
            case 12:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$4$1$0$0(this.f8210l, this.f8212n, this.f8211m);
            case 13:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$4$1$1$0(this.f8210l, this.f8212n, this.f8211m);
            default:
                return ProfileDialogsKt.ProfileDialogContent$lambda$9$0(this.f8210l, this.f8212n, this.f8211m);
        }
    }

    public /* synthetic */ n3(r7.a aVar, MutableState mutableState, Context context, int i10) {
        this.f8209i = i10;
        this.f8210l = aVar;
        this.f8212n = mutableState;
        this.f8211m = context;
    }
}
