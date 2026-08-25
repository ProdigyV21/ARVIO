package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003R#\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/SettingsFocusTracker;", "", "<init>", "()V", "Lx6/t0;", "clear", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "requesters", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "getRequesters", "()Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsFocusTracker {
    public static final int $stable = 0;
    private final SnapshotStateMap<Integer, BringIntoViewRequester> requesters = SnapshotStateKt.mutableStateMapOf();

    public final void clear() {
        this.requesters.clear();
    }

    public final SnapshotStateMap<Integer, BringIntoViewRequester> getRequesters() {
        return this.requesters;
    }
}
