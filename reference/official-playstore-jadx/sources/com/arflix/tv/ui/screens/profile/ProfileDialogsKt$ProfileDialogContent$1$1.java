package com.arflix.tv.ui.screens.profile;

import androidx.compose.ui.focus.FocusRequester;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileDialogsKt$ProfileDialogContent$1$1", f = "ProfileDialogs.kt", l = {}, m = "invokeSuspend", v = 2)
public final class ProfileDialogsKt$ProfileDialogContent$1$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $autoFocusNameInput;
    final /* synthetic */ FocusRequester $confirmButtonFocusRequester;
    final /* synthetic */ boolean $isTouchDevice;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileDialogsKt$ProfileDialogContent$1$1(boolean z, boolean z5, FocusRequester focusRequester, d7.d<? super ProfileDialogsKt$ProfileDialogContent$1$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$autoFocusNameInput = z5;
        this.$confirmButtonFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        ProfileDialogsKt$ProfileDialogContent$1$1 profileDialogsKt$ProfileDialogContent$1$1 = new ProfileDialogsKt$ProfileDialogContent$1$1(this.$isTouchDevice, this.$autoFocusNameInput, this.$confirmButtonFocusRequester, dVar);
        profileDialogsKt$ProfileDialogContent$1$1.L$0 = obj;
        return profileDialogsKt$ProfileDialogContent$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!this.$isTouchDevice && !this.$autoFocusNameInput) {
            try {
                this.$confirmButtonFocusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((ProfileDialogsKt$ProfileDialogContent$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
