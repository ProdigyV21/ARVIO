package com.arflix.tv.ui.screens.details;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.util.DataStoresKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$8$1", f = "DetailsScreen.kt", l = {288}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsScreen$8$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Profile $currentProfile;
    final /* synthetic */ MutableState<Boolean> $spoilerBlurEnabled$delegate;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsScreen$8$1(Context context, Profile profile, MutableState<Boolean> mutableState, d7.d<? super DetailsScreenKt$DetailsScreen$8$1> dVar) {
        super(2, dVar);
        this.$context = context;
        this.$currentProfile = profile;
        this.$spoilerBlurEnabled$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        DetailsScreenKt$DetailsScreen$8$1 detailsScreenKt$DetailsScreen$8$1 = new DetailsScreenKt$DetailsScreen$8$1(this.$context, this.$currentProfile, this.$spoilerBlurEnabled$delegate, dVar);
        detailsScreenKt$DetailsScreen$8$1.L$0 = obj;
        return detailsScreenKt$DetailsScreen$8$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Profile profile;
        MutableState<Boolean> mutableState;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                Context context = this.$context;
                Profile profile2 = this.$currentProfile;
                MutableState<Boolean> mutableState2 = this.$spoilerBlurEnabled$delegate;
                na.j data = DataStoresKt.getSettingsDataStore(context).getData();
                this.L$0 = null;
                this.L$1 = profile2;
                this.L$2 = mutableState2;
                this.L$3 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = na.y0.l(data, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
                profile = profile2;
                mutableState = mutableState2;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableState = (MutableState) this.L$2;
                profile = (Profile) this.L$1;
                k2.c.G(obj);
            }
            c1.g gVar = (c1.g) obj;
            String id = profile != null ? profile.getId() : null;
            if (id != null) {
                Boolean bool = (Boolean) ((c1.b) gVar).f7368a.get(new c1.e("profile_" + id + "_spoiler_blur"));
                DetailsScreenKt.DetailsScreen$lambda$77(mutableState, bool != null ? bool.booleanValue() : false);
            }
        } catch (Throwable unused) {
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsScreen$8$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
