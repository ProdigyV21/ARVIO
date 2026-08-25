package com.arflix.tv;

import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.startup.StartupViewModel;
import com.arflix.tv.worker.TraktSyncWorker;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7611i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7612l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f7611i = i10;
        this.f7612l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7611i) {
            case 0:
                return ArflixApplication.onCreate$lambda$1((ArflixApplication) this.f7612l);
            case 1:
                return ArvioDpadFocusKt.arvioDpadFocusGroup$lambda$0((FocusRequester) this.f7612l);
            case 2:
                return StartupViewModel.imageLoader_delegate$lambda$0((StartupViewModel) this.f7612l);
            case 3:
                return TraktSyncWorker.deps_delegate$lambda$0((TraktSyncWorker) this.f7612l);
            case 4:
                return this.f7612l;
            case 5:
                return new ab.j((Object[]) this.f7612l, 4);
            default:
                return ((Iterable) this.f7612l).iterator();
        }
    }
}
