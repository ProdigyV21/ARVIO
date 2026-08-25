package com.arflix.tv.ui.components;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u3 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8429i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Context f8430l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ View f8431m;

    public /* synthetic */ u3(Context context, View view, int i10) {
        this.f8429i = i10;
        this.f8430l = context;
        this.f8431m = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f8429i) {
            case 0:
                TextInputModalKt.TextInputModal$lambda$17$1$0$0$0$0$0$2$0(this.f8430l, this.f8431m);
                break;
            default:
                TextInputModalKt.TextInputModal$lambda$17$1$0$0$1$0$0(this.f8430l, (EditText) this.f8431m);
                break;
        }
    }
}
