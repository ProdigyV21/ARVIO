package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$25$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$25$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $autoAdvanceAttempts$delegate;
    final /* synthetic */ MutableIntState $currentStreamIndex$delegate;
    final /* synthetic */ MutableState<Boolean> $isAutoAdvancing$delegate;
    final /* synthetic */ MutableState<Set<Integer>> $triedStreamIndexes$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$25$1(State<PlayerUiState> state, MutableIntState mutableIntState, MutableState<Boolean> mutableState, MutableState<Set<Integer>> mutableState2, MutableIntState mutableIntState2, d7.d<? super PlayerScreenKt$PlayerScreen$25$1> dVar) {
        super(2, dVar);
        this.$uiState$delegate = state;
        this.$currentStreamIndex$delegate = mutableIntState;
        this.$isAutoAdvancing$delegate = mutableState;
        this.$triedStreamIndexes$delegate = mutableState2;
        this.$autoAdvanceAttempts$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$25$1(this.$uiState$delegate, this.$currentStreamIndex$delegate, this.$isAutoAdvancing$delegate, this.$triedStreamIndexes$delegate, this.$autoAdvanceAttempts$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String selectedStreamUrl = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSelectedStreamUrl();
        x6.t0 t0Var = x6.t0.f22605a;
        if (selectedStreamUrl != null) {
            StreamSource selectedStream = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSelectedStream();
            Iterator<StreamSource> it = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getStreams().iterator();
            int i11 = 0;
            while (true) {
                i10 = -1;
                if (!it.hasNext()) {
                    i11 = -1;
                    break;
                }
                if (kotlin.jvm.internal.p.a(it.next().getUrl(), selectedStreamUrl)) {
                    break;
                }
                i11++;
            }
            if (i11 < 0) {
                Iterator<StreamSource> it2 = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getStreams().iterator();
                int i12 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    StreamSource next = it2.next();
                    if (selectedStream != null && kotlin.jvm.internal.p.a(next.getAddonId(), selectedStream.getAddonId()) && kotlin.jvm.internal.p.a(next.getSource(), selectedStream.getSource())) {
                        StreamBehaviorHints behaviorHints = next.getBehaviorHints();
                        String bingeGroup = behaviorHints != null ? behaviorHints.getBingeGroup() : null;
                        StreamBehaviorHints behaviorHints2 = selectedStream.getBehaviorHints();
                        if (kotlin.jvm.internal.p.a(bingeGroup, behaviorHints2 != null ? behaviorHints2.getBingeGroup() : null)) {
                            i10 = i12;
                            break;
                        }
                    }
                    i12++;
                }
            } else {
                i10 = i11;
            }
            if (i10 >= 0) {
                this.$currentStreamIndex$delegate.setIntValue(i10);
                if (PlayerScreenKt.PlayerScreen$lambda$267(this.$isAutoAdvancing$delegate)) {
                    MutableState<Set<Integer>> mutableState = this.$triedStreamIndexes$delegate;
                    mutableState.setValue(kotlin.collections.q0.c0(PlayerScreenKt.PlayerScreen$lambda$264(mutableState), new Integer(i10)));
                    PlayerScreenKt.PlayerScreen$lambda$268(this.$isAutoAdvancing$delegate, false);
                    return t0Var;
                }
                this.$triedStreamIndexes$delegate.setValue(Collections.singleton(new Integer(i10)));
                this.$autoAdvanceAttempts$delegate.setIntValue(0);
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$25$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
