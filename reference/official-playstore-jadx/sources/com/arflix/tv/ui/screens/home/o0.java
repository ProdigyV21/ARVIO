package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9238i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9239l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9240m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9241n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9242o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9243p;

    public /* synthetic */ o0(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i10) {
        this.f9238i = i10;
        this.f9239l = obj;
        this.f9240m = obj2;
        this.f9241n = obj3;
        this.f9242o = obj4;
        this.f9243p = obj5;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9238i) {
            case 0:
                return HomeScreenKt.HomeScreen$lambda$77$12$0$0$0((HomeViewModel) this.f9239l, (MediaItem) this.f9240m, (r7.l) this.f9241n, (r7.p) this.f9242o, (r7.r) this.f9243p);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$77$12$0$1$0((HomeViewModel) this.f9239l, (MediaItem) this.f9240m, (r7.l) this.f9241n, (r7.p) this.f9242o, (r7.r) this.f9243p);
            default:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$27$0((HomeFocusState) this.f9239l, (r7.a) this.f9240m, (List) this.f9241n, (MutableState) this.f9242o, (MutableLongState) this.f9243p);
        }
    }
}
