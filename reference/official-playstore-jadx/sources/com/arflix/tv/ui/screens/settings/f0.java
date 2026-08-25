package com.arflix.tv.ui.screens.settings;

import android.content.Context;
import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10448i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10449l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f10450m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f10451n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ View f10452o;

    public /* synthetic */ f0(r7.a aVar, Context context, List list, View view, int i10) {
        this.f10448i = i10;
        this.f10449l = aVar;
        this.f10450m = context;
        this.f10451n = list;
        this.f10452o = view;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10448i) {
            case 0:
                return SettingsScreenKt.InputModal$lambda$19$2$1$4$0$0(this.f10449l, this.f10450m, this.f10451n, this.f10452o);
            case 1:
                return SettingsScreenKt.InputModal$lambda$19$2$1$4$2$0(this.f10449l, this.f10450m, this.f10451n, this.f10452o);
            case 2:
                return SettingsScreenKt.InputModal$lambda$19$0$0(this.f10449l, this.f10450m, this.f10451n, this.f10452o);
            case 3:
                return SettingsScreenKt.InputModal$lambda$19$1$0(this.f10449l, this.f10450m, this.f10451n, this.f10452o);
            default:
                return SettingsScreenKt.InputModal$lambda$18$0(this.f10449l, this.f10450m, this.f10451n, this.f10452o);
        }
    }
}
