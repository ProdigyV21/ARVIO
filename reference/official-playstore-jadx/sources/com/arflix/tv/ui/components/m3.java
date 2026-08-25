package com.arflix.tv.ui.components;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m3 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8187i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ InputMethodManager f8188l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ EditText f8189m;

    public /* synthetic */ m3(InputMethodManager inputMethodManager, EditText editText) {
        this.f8188l = inputMethodManager;
        this.f8189m = editText;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f8187i) {
            case 0:
                TextInputModalKt.TextInputModal$showKeyboard$lambda$13(this.f8189m, this.f8188l);
                break;
            default:
                TextInputModalKt.TextInputModal$hideKeyboard$lambda$14(this.f8188l, this.f8189m);
                break;
        }
    }

    public /* synthetic */ m3(EditText editText, InputMethodManager inputMethodManager) {
        this.f8189m = editText;
        this.f8188l = inputMethodManager;
    }
}
