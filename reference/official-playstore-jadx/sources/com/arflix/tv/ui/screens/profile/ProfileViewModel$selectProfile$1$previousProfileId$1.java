package com.arflix.tv.ui.screens.profile;

import com.arflix.tv.data.repository.ProfileRepository;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$previousProfileId$1", f = "ProfileViewModel.kt", l = {199}, m = "invokeSuspend", v = 2)
public final class ProfileViewModel$selectProfile$1$previousProfileId$1 extends f7.j implements r7.p<k0, d7.d<? super String>, Object> {
    int label;
    final /* synthetic */ ProfileViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileViewModel$selectProfile$1$previousProfileId$1(ProfileViewModel profileViewModel, d7.d<? super ProfileViewModel$selectProfile$1$previousProfileId$1> dVar) {
        super(2, dVar);
        this.this$0 = profileViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileViewModel$selectProfile$1$previousProfileId$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        ProfileRepository profileRepository = this.this$0.profileRepository;
        this.label = 1;
        Object activeProfileId = profileRepository.getActiveProfileId(this);
        e7.a aVar = e7.a.f15033i;
        return activeProfileId == aVar ? aVar : activeProfileId;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super String> dVar) {
        return ((ProfileViewModel$selectProfile$1$previousProfileId$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
