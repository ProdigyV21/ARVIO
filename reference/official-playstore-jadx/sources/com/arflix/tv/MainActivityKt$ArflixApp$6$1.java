package com.arflix.tv;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivityKt$ArflixApp$6$1", f = "MainActivity.kt", l = {618, 620}, m = "invokeSuspend", v = 2)
public final class MainActivityKt$ArflixApp$6$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Profile $activeProfile;
    final /* synthetic */ State<AuthState> $authState$delegate;
    final /* synthetic */ MutableState<String> $lastAddonsSyncKey$delegate;
    final /* synthetic */ LauncherContinueWatchingRepository $launcherContinueWatchingRepository;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MainActivityKt$ArflixApp$6$1(Profile profile, LauncherContinueWatchingRepository launcherContinueWatchingRepository, State<? extends AuthState> state, MutableState<String> mutableState, d7.d<? super MainActivityKt$ArflixApp$6$1> dVar) {
        super(2, dVar);
        this.$activeProfile = profile;
        this.$launcherContinueWatchingRepository = launcherContinueWatchingRepository;
        this.$authState$delegate = state;
        this.$lastAddonsSyncKey$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MainActivityKt$ArflixApp$6$1(this.$activeProfile, this.$launcherContinueWatchingRepository, this.$authState$delegate, this.$lastAddonsSyncKey$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        if (r4.refreshForCurrentProfile(r3) == r0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        if (r4.clearPublishedPrograms(r3) == r0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        return r0;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            int r0 = r3.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L17
            if (r0 == r2) goto L13
            if (r0 != r1) goto Lb
            goto L13
        Lb:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L13:
            k2.c.G(r4)
            goto L46
        L17:
            k2.c.G(r4)
            androidx.compose.runtime.State<com.arflix.tv.data.repository.AuthState> r4 = r3.$authState$delegate
            com.arflix.tv.data.repository.AuthState r4 = com.arflix.tv.MainActivityKt.access$ArflixApp$lambda$3(r4)
            boolean r4 = r4 instanceof com.arflix.tv.data.repository.AuthState.NotAuthenticated
            if (r4 == 0) goto L2a
            androidx.compose.runtime.MutableState<java.lang.String> r4 = r3.$lastAddonsSyncKey$delegate
            r0 = 0
            com.arflix.tv.MainActivityKt.access$ArflixApp$lambda$13(r4, r0)
        L2a:
            com.arflix.tv.data.model.Profile r4 = r3.$activeProfile
            e7.a r0 = e7.a.f15033i
            if (r4 == 0) goto L3b
            com.arflix.tv.data.repository.LauncherContinueWatchingRepository r4 = r3.$launcherContinueWatchingRepository
            r3.label = r2
            java.lang.Object r4 = r4.refreshForCurrentProfile(r3)
            if (r4 != r0) goto L46
            goto L45
        L3b:
            com.arflix.tv.data.repository.LauncherContinueWatchingRepository r4 = r3.$launcherContinueWatchingRepository
            r3.label = r1
            java.lang.Object r4 = r4.clearPublishedPrograms(r3)
            if (r4 != r0) goto L46
        L45:
            return r0
        L46:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.MainActivityKt$ArflixApp$6$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivityKt$ArflixApp$6$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
