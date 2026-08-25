package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.State;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class PlayerScreenKt$PlayerScreen$dvStripExtractorsFactory$1$1 extends kotlin.jvm.internal.m implements r7.a<Boolean> {
    final /* synthetic */ Set<String> $dvForcedStripUrls;
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$dvStripExtractorsFactory$1$1(Set<String> set, State<PlayerUiState> state) {
        super(0, kotlin.jvm.internal.o.class, "dvStripEnabledNow", "PlayerScreen$dvStripEnabledNow(Ljava/util/Set;Landroidx/compose/runtime/State;)Z", 0);
        this.$dvForcedStripUrls = set;
        this.$latestUiState$delegate = state;
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf(PlayerScreenKt.PlayerScreen$dvStripEnabledNow(this.$dvForcedStripUrls, this.$latestUiState$delegate));
    }
}
