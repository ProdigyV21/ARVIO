package com.arflix.tv.ui.screens.home;

import com.arflix.tv.updater.ApkDownloader;
import com.arflix.tv.updater.AppUpdate;
import com.arflix.tv.updater.UpdateStatus;
import java.io.File;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Ljava/io/File;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$downloadAppUpdate$1$result$1", f = "HomeViewModel.kt", l = {4769}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$downloadAppUpdate$1$result$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ File $dest;
    final /* synthetic */ AppUpdate $update;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$downloadAppUpdate$1$result$1(HomeViewModel homeViewModel, AppUpdate appUpdate, File file, d7.d<? super HomeViewModel$downloadAppUpdate$1$result$1> dVar) {
        super(2, dVar);
        this.this$0 = homeViewModel;
        this.$update = appUpdate;
        this.$dest = file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 invokeSuspend$lambda$0(HomeViewModel homeViewModel, AppUpdate appUpdate, long j10, Long l10) {
        homeViewModel.updateStatusManager.updateStatus(new UpdateStatus.Downloading((l10 == null || l10.longValue() <= 0) ? null : Float.valueOf(qb.d.m(j10 / l10.longValue(), 0.0f, 1.0f)), appUpdate));
        return t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$downloadAppUpdate$1$result$1(this.this$0, this.$update, this.$dest, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object objM6612downloadBWLJW6A;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            ApkDownloader apkDownloader = this.this$0.apkDownloader;
            String assetUrl = this.$update.getAssetUrl();
            File file = this.$dest;
            s0 s0Var = new s0(this.this$0, this.$update, 1);
            this.label = 1;
            objM6612downloadBWLJW6A = apkDownloader.m6612downloadBWLJW6A(assetUrl, file, s0Var, this);
            e7.a aVar = e7.a.f15033i;
            if (objM6612downloadBWLJW6A == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            objM6612downloadBWLJW6A = ((x6.d0) obj).f22580i;
        }
        return new x6.d0(objM6612downloadBWLJW6A);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeViewModel$downloadAppUpdate$1$result$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
