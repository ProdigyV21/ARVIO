package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$SettingsScreen$14$1", f = "SettingsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$SettingsScreen$14$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $audioLanguagePickerIndex$delegate;
    final /* synthetic */ MutableState<Boolean> $showAudioLanguagePicker$delegate;
    final /* synthetic */ State<SettingsUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$SettingsScreen$14$1(MutableState<Boolean> mutableState, State<SettingsUiState> state, MutableIntState mutableIntState, d7.d<? super SettingsScreenKt$SettingsScreen$14$1> dVar) {
        super(2, dVar);
        this.$showAudioLanguagePicker$delegate = mutableState;
        this.$uiState$delegate = state;
        this.$audioLanguagePickerIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsScreenKt$SettingsScreen$14$1(this.$showAudioLanguagePicker$delegate, this.$uiState$delegate, this.$audioLanguagePickerIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (SettingsScreenKt.SettingsScreen$lambda$127(this.$showAudioLanguagePicker$delegate)) {
            List<String> audioLanguageOptions = SettingsScreenKt.SettingsScreen$lambda$7(this.$uiState$delegate).getAudioLanguageOptions();
            int size = audioLanguageOptions.size() - 1;
            if (size < 0) {
                size = 0;
            }
            State<SettingsUiState> state = this.$uiState$delegate;
            Iterator<String> it = audioLanguageOptions.iterator();
            int iN = 0;
            while (true) {
                if (!it.hasNext()) {
                    iN = -1;
                    break;
                }
                if (kotlin.text.u.L(it.next(), SettingsScreenKt.SettingsScreen$lambda$7(state).getDefaultAudioLanguage(), true)) {
                    break;
                }
                iN++;
            }
            MutableIntState mutableIntState = this.$audioLanguagePickerIndex$delegate;
            if (iN < 0) {
                iN = qb.d.n(mutableIntState.getIntValue(), 0, size);
            }
            mutableIntState.setIntValue(iN);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$SettingsScreen$14$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
