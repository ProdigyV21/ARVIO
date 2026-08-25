package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.y;
import androidx.navigation.compose.q;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.squareup.moshi.w;
import java.util.ArrayList;
import kotlin.Metadata;
import n1.b;
import n1.c;
import n1.d;
import n1.e;
import n1.h;
import n1.i;
import n1.j;
import n1.k;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a<\u0010\u0010\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001aF\u0010\u0010\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0013\u001aP\u0010\u0010\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0015\u001aJ\u0010\u0010\u001a\u00020\u00052\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0016\"\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0018\u001a8\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\f2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0003¢\u0006\u0004\b\u001a\u0010\u001b\u001a<\u0010\u001e\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u001e\u0010\u0011\u001aF\u0010\u001e\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u001e\u0010\u0013\u001aP\u0010\u001e\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u001e\u0010\u0015\u001aJ\u0010\u001e\u001a\u00020\u00052\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0016\"\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u000b¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u001e\u0010\u0018\u001a8\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001c2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u000b¢\u0006\u0002\b\u000eH\u0003¢\u0006\u0004\b\u001f\u0010 ¨\u0006\"²\u0006\u0012\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/lifecycle/p;", "event", "Landroidx/lifecycle/y;", "lifecycleOwner", "Lkotlin/Function0;", "Lx6/t0;", "onEvent", "LifecycleEventEffect", "(Landroidx/lifecycle/p;Landroidx/lifecycle/y;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "", "key1", "Lkotlin/Function1;", "Ln1/j;", "Ln1/k;", "Lx6/n;", "effects", "LifecycleStartEffect", "(Ljava/lang/Object;Landroidx/lifecycle/y;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/lifecycle/y;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/lifecycle/y;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "", UserMetadata.KEYDATA_FILENAME, "([Ljava/lang/Object;Landroidx/lifecycle/y;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "scope", "LifecycleStartEffectImpl", "(Landroidx/lifecycle/y;Ln1/j;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "Ln1/i;", "Ln1/h;", "LifecycleResumeEffect", "LifecycleResumeEffectImpl", "(Landroidx/lifecycle/y;Ln1/i;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "currentOnEvent", "lifecycle-runtime-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LifecycleEffectKt {
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LifecycleEventEffect(androidx.lifecycle.p r9, androidx.lifecycle.y r10, r7.a<x6.t0> r11, androidx.compose.runtime.Composer r12, int r13, int r14) {
        /*
            r0 = -709389590(0xffffffffd5b792ea, float:-2.5230202E13)
            androidx.compose.runtime.Composer r12 = r12.startRestartGroup(r0)
            r0 = r14 & 1
            r1 = 2
            if (r0 == 0) goto Lf
            r0 = r13 | 6
            goto L1f
        Lf:
            r0 = r13 & 14
            if (r0 != 0) goto L1e
            boolean r0 = r12.changed(r9)
            if (r0 == 0) goto L1b
            r0 = 4
            goto L1c
        L1b:
            r0 = r1
        L1c:
            r0 = r0 | r13
            goto L1f
        L1e:
            r0 = r13
        L1f:
            r2 = r14 & 2
            if (r2 == 0) goto L25
            r0 = r0 | 16
        L25:
            r3 = r14 & 4
            if (r3 == 0) goto L2c
            r0 = r0 | 384(0x180, float:5.38E-43)
            goto L3c
        L2c:
            r3 = r13 & 896(0x380, float:1.256E-42)
            if (r3 != 0) goto L3c
            boolean r3 = r12.changed(r11)
            if (r3 == 0) goto L39
            r3 = 256(0x100, float:3.59E-43)
            goto L3b
        L39:
            r3 = 128(0x80, float:1.8E-43)
        L3b:
            r0 = r0 | r3
        L3c:
            if (r2 != r1) goto L50
            r1 = r0 & 731(0x2db, float:1.024E-42)
            r3 = 146(0x92, float:2.05E-43)
            if (r1 != r3) goto L50
            boolean r1 = r12.getSkipping()
            if (r1 != 0) goto L4b
            goto L50
        L4b:
            r12.skipToGroupEnd()
        L4e:
            r7 = r10
            goto L8f
        L50:
            r12.startDefaults()
            r1 = r13 & 1
            if (r1 == 0) goto L66
            boolean r1 = r12.getDefaultsInvalid()
            if (r1 == 0) goto L5e
            goto L66
        L5e:
            r12.skipToGroupEnd()
            if (r2 == 0) goto L73
        L63:
            r0 = r0 & (-113(0xffffffffffffff8f, float:NaN))
            goto L73
        L66:
            if (r2 == 0) goto L73
            androidx.compose.runtime.ProvidableCompositionLocal r10 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.getLocalLifecycleOwner()
            java.lang.Object r10 = r12.consume(r10)
            androidx.lifecycle.y r10 = (androidx.lifecycle.y) r10
            goto L63
        L73:
            r12.endDefaults()
            androidx.lifecycle.p r1 = androidx.lifecycle.p.ON_DESTROY
            if (r9 == r1) goto La3
            int r0 = r0 >> 6
            r0 = r0 & 14
            androidx.compose.runtime.State r0 = androidx.compose.runtime.SnapshotStateKt.rememberUpdatedState(r11, r12, r0)
            androidx.navigation.compose.h r1 = new androidx.navigation.compose.h
            r2 = 8
            r1.<init>(r10, r9, r0, r2)
            r0 = 8
            androidx.compose.runtime.EffectsKt.DisposableEffect(r10, r1, r12, r0)
            goto L4e
        L8f:
            androidx.compose.runtime.ScopeUpdateScope r10 = r12.endRestartGroup()
            if (r10 == 0) goto La2
            androidx.navigation.compose.z r2 = new androidx.navigation.compose.z
            r5 = 2
            r6 = r9
            r8 = r11
            r3 = r13
            r4 = r14
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r10.updateScope(r2)
        La2:
            return
        La3:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "LifecycleEventEffect cannot be used to listen for Lifecycle.Event.ON_DESTROY, since Compose disposes of the composition before ON_DESTROY observers are invoked."
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.compose.LifecycleEffectKt.LifecycleEventEffect(androidx.lifecycle.p, androidx.lifecycle.y, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void LifecycleResumeEffect(Object obj, y yVar, l<? super i, ? extends h> lVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1220373486);
        if ((i11 & 2) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10 & (-113);
        } else {
            i12 = i10;
        }
        y yVar2 = yVar;
        composerStartRestartGroup.startReplaceableGroup(-3686552);
        boolean zChanged = composerStartRestartGroup.changed(obj) | composerStartRestartGroup.changed(yVar2);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleResumeEffectImpl(yVar2, (i) objRememberedValue, lVar, composerStartRestartGroup, (i12 & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new b(obj, yVar2, lVar, i10, i11, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LifecycleResumeEffectImpl(y yVar, i iVar, l<? super i, ? extends h> lVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(912823238);
        EffectsKt.DisposableEffect(yVar, iVar, new androidx.navigation.compose.h(yVar, iVar, lVar, 9), composerStartRestartGroup, 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new q(yVar, iVar, lVar, i10, 1));
        }
    }

    public static final void LifecycleStartEffect(Object obj, y yVar, l<? super j, ? extends k> lVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1408314671);
        if ((i11 & 2) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10 & (-113);
        } else {
            i12 = i10;
        }
        y yVar2 = yVar;
        composerStartRestartGroup.startReplaceableGroup(-3686552);
        boolean zChanged = composerStartRestartGroup.changed(obj) | composerStartRestartGroup.changed(yVar2);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new j(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleStartEffectImpl(yVar2, (j) objRememberedValue, lVar, composerStartRestartGroup, (i12 & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new b(obj, yVar2, lVar, i10, i11, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LifecycleStartEffectImpl(y yVar, j jVar, l<? super j, ? extends k> lVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(228371534);
        EffectsKt.DisposableEffect(yVar, jVar, new androidx.navigation.compose.h(yVar, jVar, lVar, 10), composerStartRestartGroup, 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new q(yVar, jVar, lVar, i10, 2));
        }
    }

    public static final void LifecycleResumeEffect(Object obj, Object obj2, y yVar, l<? super i, ? extends h> lVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(752680142);
        if ((i11 & 4) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10 & (-897);
        } else {
            i12 = i10;
        }
        y yVar2 = yVar;
        composerStartRestartGroup.startReplaceableGroup(-3686095);
        boolean zChanged = composerStartRestartGroup.changed(obj) | composerStartRestartGroup.changed(obj2) | composerStartRestartGroup.changed(yVar2);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleResumeEffectImpl(yVar2, (i) objRememberedValue, lVar, composerStartRestartGroup, ((i12 >> 3) & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c(obj, obj2, yVar2, lVar, i10, i11, 0));
        }
    }

    public static final void LifecycleStartEffect(Object obj, Object obj2, y yVar, l<? super j, ? extends k> lVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(696924721);
        if ((i11 & 4) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10 & (-897);
        } else {
            i12 = i10;
        }
        y yVar2 = yVar;
        composerStartRestartGroup.startReplaceableGroup(-3686095);
        boolean zChanged = composerStartRestartGroup.changed(obj) | composerStartRestartGroup.changed(obj2) | composerStartRestartGroup.changed(yVar2);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new j(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleStartEffectImpl(yVar2, (j) objRememberedValue, lVar, composerStartRestartGroup, ((i12 >> 3) & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c(obj, obj2, yVar2, lVar, i10, i11, 1));
        }
    }

    public static final void LifecycleResumeEffect(Object obj, Object obj2, Object obj3, y yVar, l<? super i, ? extends h> lVar, Composer composer, int i10, int i11) {
        int i12;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-485941842);
        if ((i11 & 8) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10;
            i13 = i12 & (-7169);
        } else {
            i12 = i10;
            i13 = i12;
        }
        y yVar2 = yVar;
        int i14 = 0;
        Object[] objArr = {obj, obj2, obj3, yVar2};
        composerStartRestartGroup.startReplaceableGroup(-3685570);
        boolean zChanged = false;
        while (i14 < 4) {
            Object obj4 = objArr[i14];
            i14++;
            zChanged |= composerStartRestartGroup.changed(obj4);
        }
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleResumeEffectImpl(yVar2, (i) objRememberedValue, lVar, composerStartRestartGroup, ((i13 >> 6) & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(obj, obj2, obj3, yVar2, lVar, i12, i11, 0));
        }
    }

    public static final void LifecycleStartEffect(Object obj, Object obj2, Object obj3, y yVar, l<? super j, ? extends k> lVar, Composer composer, int i10, int i11) {
        int i12;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(574812561);
        if ((i11 & 8) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10;
            i13 = i12 & (-7169);
        } else {
            i12 = i10;
            i13 = i12;
        }
        y yVar2 = yVar;
        int i14 = 0;
        Object[] objArr = {obj, obj2, obj3, yVar2};
        composerStartRestartGroup.startReplaceableGroup(-3685570);
        boolean zChanged = false;
        while (i14 < 4) {
            Object obj4 = objArr[i14];
            i14++;
            zChanged |= composerStartRestartGroup.changed(obj4);
        }
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new j(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleStartEffectImpl(yVar2, (j) objRememberedValue, lVar, composerStartRestartGroup, ((i13 >> 6) & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(obj, obj2, obj3, yVar2, lVar, i12, i11, 1));
        }
    }

    public static final void LifecycleResumeEffect(Object[] objArr, y yVar, l<? super i, ? extends h> lVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-781756895);
        if ((i11 & 2) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10 & (-113);
        } else {
            i12 = i10;
        }
        y yVar2 = yVar;
        w wVar = new w(2);
        wVar.b(objArr);
        wVar.a(yVar2);
        ArrayList arrayList = wVar.f14489a;
        Object[] array = arrayList.toArray(new Object[arrayList.size()]);
        composerStartRestartGroup.startReplaceableGroup(-3685570);
        int length = array.length;
        int i13 = 0;
        boolean zChanged = false;
        while (i13 < length) {
            Object obj = array[i13];
            i13++;
            zChanged |= composerStartRestartGroup.changed(obj);
        }
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleResumeEffectImpl(yVar2, (i) objRememberedValue, lVar, composerStartRestartGroup, (i12 & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new e(objArr, yVar2, lVar, i10, i11, 0));
        }
    }

    public static final void LifecycleStartEffect(Object[] objArr, y yVar, l<? super j, ? extends k> lVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1510305724);
        if ((i11 & 2) != 0) {
            yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            i12 = i10 & (-113);
        } else {
            i12 = i10;
        }
        y yVar2 = yVar;
        w wVar = new w(2);
        wVar.b(objArr);
        wVar.a(yVar2);
        ArrayList arrayList = wVar.f14489a;
        Object[] array = arrayList.toArray(new Object[arrayList.size()]);
        composerStartRestartGroup.startReplaceableGroup(-3685570);
        int length = array.length;
        int i13 = 0;
        boolean zChanged = false;
        while (i13 < length) {
            Object obj = array[i13];
            i13++;
            zChanged |= composerStartRestartGroup.changed(obj);
        }
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new j(yVar2.getLifecycle());
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LifecycleStartEffectImpl(yVar2, (j) objRememberedValue, lVar, composerStartRestartGroup, (i12 & 896) | 72);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new e(objArr, yVar2, lVar, i10, i11, 1));
        }
    }
}
