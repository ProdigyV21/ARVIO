package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u0000 22\u00020\u0001:\u00012B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR+\u0010\u0010\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R+\u0010\u0017\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u0018\u0010\u0012\"\u0004\b\u0019\u0010\u0014R+\u0010\u001b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u0016\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R+\u0010 \u001a\u00020\u001f2\u0006\u0010\b\u001a\u00020\u001f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R+\u0010'\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u000f\u001a\u0004\b(\u0010\u000b\"\u0004\b)\u0010\rR\u001d\u0010+\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00030,¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001d\u00100\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020-0,¢\u0006\b\n\u0000\u001a\u0004\b1\u0010/¨\u00063"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeFocusState;", "", "initialRowIndex", "", "initialItemIndex", "initialSidebarIndex", "<init>", "(III)V", "<set-?>", "", "isSidebarFocused", "()Z", "setSidebarFocused", "(Z)V", "isSidebarFocused$delegate", "Landroidx/compose/runtime/MutableState;", "sidebarFocusIndex", "getSidebarFocusIndex", "()I", "setSidebarFocusIndex", "(I)V", "sidebarFocusIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "currentRowIndex", "getCurrentRowIndex", "setCurrentRowIndex", "currentRowIndex$delegate", "currentItemIndex", "getCurrentItemIndex", "setCurrentItemIndex", "currentItemIndex$delegate", "", "lastNavEventTime", "getLastNavEventTime", "()J", "setLastNavEventTime", "(J)V", "lastNavEventTime$delegate", "Landroidx/compose/runtime/MutableLongState;", "userHasNavigated", "getUserHasNavigated", "setUserHasNavigated", "userHasNavigated$delegate", "rowItemIndicesByCategoryId", "", "", "getRowItemIndicesByCategoryId", "()Ljava/util/Map;", "rowItemKeysByCategoryId", "getRowItemKeysByCategoryId", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class HomeFocusState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<HomeFocusState, List<Integer>> Saver = SaverKt.Saver(new c(0), new d(0));

    /* JADX INFO: renamed from: currentItemIndex$delegate, reason: from kotlin metadata */
    private final MutableIntState currentItemIndex;

    /* JADX INFO: renamed from: currentRowIndex$delegate, reason: from kotlin metadata */
    private final MutableIntState currentRowIndex;

    /* JADX INFO: renamed from: isSidebarFocused$delegate, reason: from kotlin metadata */
    private final MutableState isSidebarFocused;

    /* JADX INFO: renamed from: lastNavEventTime$delegate, reason: from kotlin metadata */
    private final MutableLongState lastNavEventTime;
    private final Map<String, Integer> rowItemIndicesByCategoryId;
    private final Map<String, String> rowItemKeysByCategoryId;

    /* JADX INFO: renamed from: sidebarFocusIndex$delegate, reason: from kotlin metadata */
    private final MutableIntState sidebarFocusIndex;

    /* JADX INFO: renamed from: userHasNavigated$delegate, reason: from kotlin metadata */
    private final MutableState userHasNavigated;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeFocusState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Lcom/arflix/tv/ui/screens/home/HomeFocusState;", "", "", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final Saver<HomeFocusState, List<Integer>> getSaver() {
            return HomeFocusState.Saver;
        }

        private Companion() {
        }
    }

    public HomeFocusState() {
        this(0, 0, 0, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List Saver$lambda$0(SaverScope saverScope, HomeFocusState homeFocusState) {
        return t7.a.E(Integer.valueOf(homeFocusState.getCurrentRowIndex()), Integer.valueOf(homeFocusState.getCurrentItemIndex()), Integer.valueOf(homeFocusState.getSidebarFocusIndex()), Integer.valueOf(homeFocusState.getUserHasNavigated() ? 1 : 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeFocusState Saver$lambda$1(List list) {
        HomeFocusState homeFocusState = new HomeFocusState(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue(), ((Number) list.get(2)).intValue());
        Integer num = (Integer) kotlin.collections.x.p0(3, list);
        homeFocusState.setUserHasNavigated((num != null ? num.intValue() : 0) == 1);
        return homeFocusState;
    }

    public final int getCurrentItemIndex() {
        return this.currentItemIndex.getIntValue();
    }

    public final int getCurrentRowIndex() {
        return this.currentRowIndex.getIntValue();
    }

    public final long getLastNavEventTime() {
        return this.lastNavEventTime.getLongValue();
    }

    public final Map<String, Integer> getRowItemIndicesByCategoryId() {
        return this.rowItemIndicesByCategoryId;
    }

    public final Map<String, String> getRowItemKeysByCategoryId() {
        return this.rowItemKeysByCategoryId;
    }

    public final int getSidebarFocusIndex() {
        return this.sidebarFocusIndex.getIntValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getUserHasNavigated() {
        return ((Boolean) this.userHasNavigated.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isSidebarFocused() {
        return ((Boolean) this.isSidebarFocused.getValue()).booleanValue();
    }

    public final void setCurrentItemIndex(int i10) {
        this.currentItemIndex.setIntValue(i10);
    }

    public final void setCurrentRowIndex(int i10) {
        this.currentRowIndex.setIntValue(i10);
    }

    public final void setLastNavEventTime(long j10) {
        this.lastNavEventTime.setLongValue(j10);
    }

    public final void setSidebarFocusIndex(int i10) {
        this.sidebarFocusIndex.setIntValue(i10);
    }

    public final void setSidebarFocused(boolean z) {
        this.isSidebarFocused.setValue(Boolean.valueOf(z));
    }

    public final void setUserHasNavigated(boolean z) {
        this.userHasNavigated.setValue(Boolean.valueOf(z));
    }

    public HomeFocusState(int i10, int i11, int i12) {
        Boolean bool = Boolean.FALSE;
        this.isSidebarFocused = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.sidebarFocusIndex = SnapshotIntStateKt.mutableIntStateOf(i12);
        this.currentRowIndex = SnapshotIntStateKt.mutableIntStateOf(i10);
        this.currentItemIndex = SnapshotIntStateKt.mutableIntStateOf(i11);
        this.lastNavEventTime = SnapshotLongStateKt.mutableLongStateOf(0L);
        this.userHasNavigated = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.rowItemIndicesByCategoryId = new LinkedHashMap();
        this.rowItemKeysByCategoryId = new LinkedHashMap();
    }

    public /* synthetic */ HomeFocusState(int i10, int i11, int i12, int i13, kotlin.jvm.internal.h hVar) {
        this((i13 & 1) != 0 ? 0 : i10, (i13 & 2) != 0 ? 0 : i11, (i13 & 4) != 0 ? 1 : i12);
    }
}
