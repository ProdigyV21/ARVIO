package androidx.navigation.compose;

import a8.l0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateHolderKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.platform.InspectionModeKt;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a'\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0001¢\u0006\u0004\b\t\u0010\n\u001a#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0001¢\u0006\u0004\b\f\u0010\r¨\u0006\u0012²\u0006\u0012\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e8\nX\u008a\u0084\u0002²\u0006\u0012\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00108\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/navigation/compose/p;", "dialogNavigator", "Lx6/t0;", "DialogHost", "(Landroidx/navigation/compose/p;Landroidx/compose/runtime/Composer;I)V", "", "Landroidx/navigation/i;", "", "backStack", "PopulateVisibleList", "(Ljava/util/List;Ljava/util/Collection;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "rememberVisibleList", "(Ljava/util/Collection;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/snapshots/SnapshotStateList;", "", "dialogBackStack", "", "transitionInProgress", "navigation-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DialogHostKt {
    public static final void DialogHost(p pVar, Composer composer, int i10) {
        p pVar2 = pVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(294589392);
        int i11 = (i10 & 14) == 0 ? (composerStartRestartGroup.changed(pVar2) ? 4 : 2) | i10 : i10;
        if ((i11 & 11) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(294589392, i11, -1, "androidx.navigation.compose.DialogHost (DialogHost.kt:40)");
            }
            SaveableStateHolder saveableStateHolderRememberSaveableStateHolder = SaveableStateHolderKt.rememberSaveableStateHolder(composerStartRestartGroup, 0);
            State stateCollectAsState = SnapshotStateKt.collectAsState(pVar2.b().f4710e, null, composerStartRestartGroup, 8, 1);
            SnapshotStateList<androidx.navigation.i> snapshotStateListRememberVisibleList = rememberVisibleList((List) stateCollectAsState.getValue(), composerStartRestartGroup, 8);
            PopulateVisibleList(snapshotStateListRememberVisibleList, (List) stateCollectAsState.getValue(), composerStartRestartGroup, 64);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(pVar2.b().f4711f, null, composerStartRestartGroup, 8, 1);
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            SnapshotStateList snapshotStateList = (SnapshotStateList) objRememberedValue;
            composerStartRestartGroup.startReplaceableGroup(875188318);
            for (androidx.navigation.i iVar : snapshotStateListRememberVisibleList) {
                o oVar = (o) iVar.f4681l;
                l0 l0Var = new l0(pVar2, iVar, 3);
                DialogProperties dialogProperties = oVar.f4593s;
                SaveableStateHolder saveableStateHolder = saveableStateHolderRememberSaveableStateHolder;
                DialogHostKt$DialogHost$1$2 dialogHostKt$DialogHost$1$2 = new DialogHostKt$DialogHost$1$2(iVar, saveableStateHolder, snapshotStateList, pVar, oVar);
                saveableStateHolderRememberSaveableStateHolder = saveableStateHolder;
                pVar2 = pVar;
                AndroidDialog_androidKt.Dialog(l0Var, dialogProperties, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1129586364, true, dialogHostKt$DialogHost$1$2), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Set set = (Set) stateCollectAsState2.getValue();
            composerStartRestartGroup.startReplaceableGroup(1618982084);
            boolean zChanged = composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changed(pVar2) | composerStartRestartGroup.changed(snapshotStateList);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                i iVar2 = new i(stateCollectAsState2, pVar2, snapshotStateList, null, 0);
                composerStartRestartGroup.updateRememberedValue(iVar2);
                objRememberedValue2 = iVar2;
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(set, snapshotStateList, (r7.p) objRememberedValue2, composerStartRestartGroup, 568);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new j(pVar2, i10, 0));
    }

    public static final void PopulateVisibleList(List<androidx.navigation.i> list, Collection<androidx.navigation.i> collection, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1537894851);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1537894851, i10, -1, "androidx.navigation.compose.PopulateVisibleList (DialogHost.kt:86)");
        }
        boolean zBooleanValue = ((Boolean) composerStartRestartGroup.consume(InspectionModeKt.getLocalInspectionMode())).booleanValue();
        for (androidx.navigation.i iVar : collection) {
            EffectsKt.DisposableEffect(iVar.f4687r, new m(iVar, list, zBooleanValue), composerStartRestartGroup, 8);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new n(i10, 0, list, collection));
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.runtime.snapshots.SnapshotStateList<androidx.navigation.i> rememberVisibleList(java.util.Collection<androidx.navigation.i> r5, androidx.compose.runtime.Composer r6, int r7) {
        /*
            r0 = 467378629(0x1bdba1c5, float:3.6335052E-22)
            r6.startReplaceableGroup(r0)
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L12
            r1 = -1
            java.lang.String r2 = "androidx.navigation.compose.rememberVisibleList (DialogHost.kt:119)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r7, r1, r2)
        L12:
            androidx.compose.runtime.ProvidableCompositionLocal r7 = androidx.compose.ui.platform.InspectionModeKt.getLocalInspectionMode()
            java.lang.Object r7 = r6.consume(r7)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r0 = 1157296644(0x44faf204, float:2007.563)
            r6.startReplaceableGroup(r0)
            boolean r0 = r6.changed(r5)
            java.lang.Object r1 = r6.rememberedValue()
            if (r0 != 0) goto L38
            androidx.compose.runtime.Composer$Companion r0 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r0 = r0.getEmpty()
            if (r1 != r0) goto L6e
        L38:
            androidx.compose.runtime.snapshots.SnapshotStateList r1 = androidx.compose.runtime.SnapshotStateKt.mutableStateListOf()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r5 = r5.iterator()
        L47:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L68
            java.lang.Object r2 = r5.next()
            r3 = r2
            androidx.navigation.i r3 = (androidx.navigation.i) r3
            if (r7 == 0) goto L58
            r3 = 1
            goto L62
        L58:
            androidx.lifecycle.a0 r3 = r3.f4687r
            androidx.lifecycle.q r3 = r3.f3198c
            androidx.lifecycle.q r4 = androidx.lifecycle.q.f3269n
            boolean r3 = r3.a(r4)
        L62:
            if (r3 == 0) goto L47
            r0.add(r2)
            goto L47
        L68:
            r1.addAll(r0)
            r6.updateRememberedValue(r1)
        L6e:
            r6.endReplaceableGroup()
            androidx.compose.runtime.snapshots.SnapshotStateList r1 = (androidx.compose.runtime.snapshots.SnapshotStateList) r1
            boolean r5 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r5 == 0) goto L7c
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L7c:
            r6.endReplaceableGroup()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.compose.DialogHostKt.rememberVisibleList(java.util.Collection, androidx.compose.runtime.Composer, int):androidx.compose.runtime.snapshots.SnapshotStateList");
    }
}
