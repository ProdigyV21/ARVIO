package com.arflix.tv.ui.screens.profile;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements TextView.OnEditorActionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10051a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EditText f10052b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ r7.a f10053c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ MutableState f10054d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Context f10055e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f10056f;

    public /* synthetic */ q(EditText editText, r7.a aVar, MutableState mutableState, Context context, FocusRequester focusRequester, int i10) {
        this.f10051a = i10;
        this.f10052b = editText;
        this.f10053c = aVar;
        this.f10054d = mutableState;
        this.f10055e = context;
        this.f10056f = focusRequester;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
        switch (this.f10051a) {
            case 0:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$0$2$0$0$0$1(this.f10052b, this.f10053c, this.f10054d, this.f10055e, this.f10056f, textView, i10, keyEvent);
            default:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$2$0$0$0$1(this.f10052b, this.f10053c, this.f10054d, this.f10055e, this.f10056f, textView, i10, keyEvent);
        }
    }
}
