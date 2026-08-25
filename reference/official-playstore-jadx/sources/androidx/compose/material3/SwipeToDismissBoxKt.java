package androidx.compose.material3;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import io.ktor.http.ContentDisposition;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aT\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022#\b\u0002\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0002H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001am\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\n2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0002¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0002¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00000\u0016H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001aq\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\n2\u001c\u0010\u001a\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0002¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0002¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a+\u0010 \u001a\u00020\u0014*\u00020\u00142\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0003H\u0002¢\u0006\u0004\b \u0010!\"\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/compose/material3/SwipeToDismissBoxValue;", "initialValue", "Lkotlin/Function1;", "", "confirmValueChange", "", "Lx6/y;", ContentDisposition.Parameters.Name, "totalDistance", "positionalThreshold", "Landroidx/compose/material3/SwipeToDismissBoxState;", "rememberSwipeToDismissBoxState", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SwipeToDismissBoxState;", "state", "Landroidx/compose/foundation/layout/RowScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "background", "dismissContent", "Landroidx/compose/ui/Modifier;", "modifier", "", "directions", "SwipeToDismiss", "(Landroidx/compose/material3/SwipeToDismissBoxState;Lr7/q;Lr7/q;Landroidx/compose/ui/Modifier;Ljava/util/Set;Landroidx/compose/runtime/Composer;II)V", "backgroundContent", "enableDismissFromStartToEnd", "enableDismissFromEndToStart", "content", "SwipeToDismissBox", "(Landroidx/compose/material3/SwipeToDismissBoxState;Lr7/q;Landroidx/compose/ui/Modifier;ZZLr7/q;Landroidx/compose/runtime/Composer;II)V", "swipeToDismissBoxAnchors", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SwipeToDismissBoxState;ZZ)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "DismissThreshold", "F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeToDismissBoxKt {
    private static final float DismissThreshold = Dp.m5678constructorimpl(125);

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeToDismissBoxKt$SwipeToDismiss$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $background;
        final /* synthetic */ Set<SwipeToDismissBoxValue> $directions;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $dismissContent;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ SwipeToDismissBoxState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(SwipeToDismissBoxState swipeToDismissBoxState, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar2, Modifier modifier, Set<? extends SwipeToDismissBoxValue> set, int i10, int i11) {
            super(2);
            this.$state = swipeToDismissBoxState;
            this.$background = qVar;
            this.$dismissContent = qVar2;
            this.$modifier = modifier;
            this.$directions = set;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SwipeToDismissBoxKt.SwipeToDismiss(this.$state, this.$background, this.$dismissContent, this.$modifier, this.$directions, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeToDismissBoxKt$SwipeToDismissBox$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $backgroundContent;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $content;
        final /* synthetic */ boolean $enableDismissFromEndToStart;
        final /* synthetic */ boolean $enableDismissFromStartToEnd;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ SwipeToDismissBoxState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(SwipeToDismissBoxState swipeToDismissBoxState, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, Modifier modifier, boolean z, boolean z5, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar2, int i10, int i11) {
            super(2);
            this.$state = swipeToDismissBoxState;
            this.$backgroundContent = qVar;
            this.$modifier = modifier;
            this.$enableDismissFromStartToEnd = z;
            this.$enableDismissFromEndToStart = z5;
            this.$content = qVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SwipeToDismissBoxKt.SwipeToDismissBox(this.$state, this.$backgroundContent, this.$modifier, this.$enableDismissFromStartToEnd, this.$enableDismissFromEndToStart, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeToDismissBoxKt$rememberSwipeToDismissBoxState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05571 extends r implements l<SwipeToDismissBoxValue, Boolean> {
        public static final C05571 INSTANCE = new C05571();

        public C05571() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SwipeToDismissBoxValue swipeToDismissBoxValue) {
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SwipeToDismiss(androidx.compose.material3.SwipeToDismissBoxState r16, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r17, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r18, androidx.compose.ui.Modifier r19, java.util.Set<? extends androidx.compose.material3.SwipeToDismissBoxValue> r20, androidx.compose.runtime.Composer r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SwipeToDismissBoxKt.SwipeToDismiss(androidx.compose.material3.SwipeToDismissBoxState, r7.q, r7.q, androidx.compose.ui.Modifier, java.util.Set, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SwipeToDismissBox(androidx.compose.material3.SwipeToDismissBoxState r25, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r26, androidx.compose.ui.Modifier r27, boolean r28, boolean r29, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 700
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SwipeToDismissBoxKt.SwipeToDismissBox(androidx.compose.material3.SwipeToDismissBoxState, r7.q, androidx.compose.ui.Modifier, boolean, boolean, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final SwipeToDismissBoxState rememberSwipeToDismissBoxState(SwipeToDismissBoxValue swipeToDismissBoxValue, l<? super SwipeToDismissBoxValue, Boolean> lVar, l<? super Float, Float> lVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-246335487);
        if ((i11 & 1) != 0) {
            swipeToDismissBoxValue = SwipeToDismissBoxValue.Settled;
        }
        if ((i11 & 2) != 0) {
            lVar = C05571.INSTANCE;
        }
        if ((i11 & 4) != 0) {
            lVar2 = SwipeToDismissBoxDefaults.INSTANCE.getPositionalThreshold(composer, 6);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-246335487, i10, -1, "androidx.compose.material3.rememberSwipeToDismissBoxState (SwipeToDismissBox.kt:219)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        Object[] objArr = new Object[0];
        Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver = SwipeToDismissBoxState.INSTANCE.Saver(lVar, lVar2, density);
        composer.startReplaceableGroup(-1012845646);
        boolean zChanged = (((6 ^ (i10 & 14)) > 4 && composer.changed(swipeToDismissBoxValue)) || (i10 & 6) == 4) | composer.changed(density) | ((((i10 & 112) ^ 48) > 32 && composer.changed(lVar)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(lVar2)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SwipeToDismissBoxKt$rememberSwipeToDismissBoxState$2$1(swipeToDismissBoxValue, density, lVar, lVar2);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        SwipeToDismissBoxState swipeToDismissBoxState = (SwipeToDismissBoxState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return swipeToDismissBoxState;
    }

    private static final Modifier swipeToDismissBoxAnchors(Modifier modifier, SwipeToDismissBoxState swipeToDismissBoxState, boolean z, boolean z5) {
        return modifier.then(new SwipeToDismissAnchorsElement(swipeToDismissBoxState, z, z5));
    }
}
