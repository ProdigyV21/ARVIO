package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import ka.k0;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt$ProfileSelectionScreen$3$1", f = "ProfileSelectionScreen.kt", l = {100}, m = "invokeSuspend", v = 2)
public final class ProfileSelectionScreenKt$ProfileSelectionScreen$3$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $isReadyForInput$delegate;
    final /* synthetic */ boolean $isTouchDevice;
    final /* synthetic */ State<ProfileUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileSelectionScreenKt$ProfileSelectionScreen$3$1(boolean z, State<ProfileUiState> state, MutableState<Boolean> mutableState, d7.d<? super ProfileSelectionScreenKt$ProfileSelectionScreen$3$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$uiState$delegate = state;
        this.$isReadyForInput$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileSelectionScreenKt$ProfileSelectionScreen$3$1(this.$isTouchDevice, this.$uiState$delegate, this.$isReadyForInput$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!this.$isTouchDevice && !ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$1(this.$uiState$delegate).getShowAddDialog() && ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$1(this.$uiState$delegate).getEditingProfile() == null && ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$7(this.$isReadyForInput$delegate)) {
                ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$8(this.$isReadyForInput$delegate, false);
                this.label = 1;
                Object objA = s0.a(300L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0.f22605a;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$8(this.$isReadyForInput$delegate, true);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((ProfileSelectionScreenKt$ProfileSelectionScreen$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
