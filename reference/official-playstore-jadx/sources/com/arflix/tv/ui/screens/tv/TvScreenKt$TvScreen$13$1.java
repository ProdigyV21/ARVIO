package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$13$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $channelIndex$delegate;
    final /* synthetic */ MutableState<TvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableIntState $groupIndex$delegate;
    final /* synthetic */ List<String> $groups;
    final /* synthetic */ String $initialChannelId;
    final /* synthetic */ String $initialStreamUrl;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableLongState $restoredSessionAt$delegate;
    final /* synthetic */ MutableState<String> $selectedChannelId$delegate;
    final /* synthetic */ MutableState<Boolean> $startupDefaultApplied$delegate;
    final /* synthetic */ String $startupGroupName;
    final /* synthetic */ State<TvUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$13$1(String str, String str2, String str3, List<String> list, State<TvUiState> state, MutableState<Boolean> mutableState, MutableLongState mutableLongState, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<TvFocusZone> mutableState4, d7.d<? super TvScreenKt$TvScreen$13$1> dVar) {
        super(2, dVar);
        this.$initialChannelId = str;
        this.$initialStreamUrl = str2;
        this.$startupGroupName = str3;
        this.$groups = list;
        this.$uiState$delegate = state;
        this.$startupDefaultApplied$delegate = mutableState;
        this.$restoredSessionAt$delegate = mutableLongState;
        this.$groupIndex$delegate = mutableIntState;
        this.$channelIndex$delegate = mutableIntState2;
        this.$selectedChannelId$delegate = mutableState2;
        this.$playingChannelId$delegate = mutableState3;
        this.$focusZone$delegate = mutableState4;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$13$1(this.$initialChannelId, this.$initialStreamUrl, this.$startupGroupName, this.$groups, this.$uiState$delegate, this.$startupDefaultApplied$delegate, this.$restoredSessionAt$delegate, this.$groupIndex$delegate, this.$channelIndex$delegate, this.$selectedChannelId$delegate, this.$playingChannelId$delegate, this.$focusZone$delegate, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007e  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 724
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$13$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$13$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
