package com.arflix.tv.ui.screens.profile;

import com.arflix.tv.data.repository.CloudSyncRepository;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1", f = "ProfileViewModel.kt", l = {142}, m = "invokeSuspend", v = 2)
public final class ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1 extends f7.j implements r7.p<k0, d7.d<? super CloudSyncRepository.RestoreResult>, Object> {
    int label;
    final /* synthetic */ ProfileViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1$1", f = "ProfileViewModel.kt", l = {143}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super CloudSyncRepository.RestoreResult>, Object> {
        int label;
        final /* synthetic */ ProfileViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ProfileViewModel profileViewModel, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = profileViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.this$0, dVar);
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
            CloudSyncRepository cloudSyncRepository = this.this$0.cloudSyncRepository;
            this.label = 1;
            Object objPullFromCloud = cloudSyncRepository.pullFromCloud(false, this);
            e7.a aVar = e7.a.f15033i;
            return objPullFromCloud == aVar ? aVar : objPullFromCloud;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super CloudSyncRepository.RestoreResult> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1(ProfileViewModel profileViewModel, d7.d<? super ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1> dVar) {
        super(2, dVar);
        this.this$0 = profileViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            obj = m0.A(18000L, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        CloudSyncRepository.RestoreResult restoreResult = (CloudSyncRepository.RestoreResult) obj;
        return restoreResult == null ? CloudSyncRepository.RestoreResult.FAILED : restoreResult;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super CloudSyncRepository.RestoreResult> dVar) {
        return ((ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$restoreResult$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
