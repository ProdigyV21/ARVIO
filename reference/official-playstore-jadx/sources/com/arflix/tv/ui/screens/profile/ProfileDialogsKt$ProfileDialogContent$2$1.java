package com.arflix.tv.ui.screens.profile;

import android.content.Context;
import android.text.Editable;
import android.widget.EditText;
import androidx.compose.runtime.MutableState;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileDialogsKt$ProfileDialogContent$2$1", f = "ProfileDialogs.kt", l = {}, m = "invokeSuspend", v = 2)
public final class ProfileDialogsKt$ProfileDialogContent$2$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $autoFocusNameInput;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<EditText> $editTextRef$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileDialogsKt$ProfileDialogContent$2$1(boolean z, MutableState<EditText> mutableState, Context context, d7.d<? super ProfileDialogsKt$ProfileDialogContent$2$1> dVar) {
        super(2, dVar);
        this.$autoFocusNameInput = z;
        this.$editTextRef$delegate = mutableState;
        this.$context = context;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileDialogsKt$ProfileDialogContent$2$1(this.$autoFocusNameInput, this.$editTextRef$delegate, this.$context, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        EditText editTextProfileDialogContent$lambda$1 = ProfileDialogsKt.ProfileDialogContent$lambda$1(this.$editTextRef$delegate);
        t0 t0Var = t0.f22605a;
        if (editTextProfileDialogContent$lambda$1 != null && this.$autoFocusNameInput) {
            editTextProfileDialogContent$lambda$1.requestFocus();
            Editable text = editTextProfileDialogContent$lambda$1.getText();
            editTextProfileDialogContent$lambda$1.setSelection(text != null ? text.length() : 0);
            ProfileDialogsKt.ProfileDialogContent$showKeyboard(this.$context, editTextProfileDialogContent$lambda$1);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((ProfileDialogsKt$ProfileDialogContent$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
