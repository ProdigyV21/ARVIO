package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class FullscreenGuideOverlayKt$FullscreenGuideContent$lambda$0$3$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ FocusRequester $anchorFocusRequester$inlined;
    final /* synthetic */ int $anchorIndex$inlined;
    final /* synthetic */ boolean $isTouchDevice$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ long $nowMillis$inlined;
    final /* synthetic */ r7.l $onProgramSelect$inlined;
    final /* synthetic */ IptvProgram $selectedProgram$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullscreenGuideOverlayKt$FullscreenGuideContent$lambda$0$3$0$$inlined$itemsIndexed$default$3(List list, IptvProgram iptvProgram, long j10, int i10, FocusRequester focusRequester, boolean z, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$selectedProgram$inlined = iptvProgram;
        this.$nowMillis$inlined = j10;
        this.$anchorIndex$inlined = i10;
        this.$anchorFocusRequester$inlined = focusRequester;
        this.$isTouchDevice$inlined = z;
        this.$onProgramSelect$inlined = lVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 14) == 0) {
            i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 112) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 731) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1091073711, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
        }
        final GuideProgramItem guideProgramItem = (GuideProgramItem) this.$items.get(i10);
        composer.startReplaceGroup(1097865820);
        boolean zA = true;
        if (FullscreenGuideOverlayKt.WhenMappings.$EnumSwitchMapping$0[guideProgramItem.getState().ordinal()] != 1) {
            zA = kotlin.jvm.internal.p.a(guideProgramItem.getProgram(), this.$selectedProgram$inlined);
        } else if (this.$selectedProgram$inlined != null) {
            zA = false;
        }
        boolean z = zA;
        long j10 = this.$nowMillis$inlined;
        FocusRequester focusRequester = i10 == this.$anchorIndex$inlined ? this.$anchorFocusRequester$inlined : null;
        boolean z5 = this.$isTouchDevice$inlined;
        boolean zChanged = composer.changed(guideProgramItem) | composer.changed(this.$onProgramSelect$inlined);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onProgramSelect$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt$FullscreenGuideContent$1$3$1$3$1$1

                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                public static final /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[GuideProgramState.values().length];
                        try {
                            iArr[GuideProgramState.PastPlayable.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[GuideProgramState.PastUnavailable.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[GuideProgramState.Live.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        try {
                            iArr[GuideProgramState.Future.ordinal()] = 4;
                        } catch (NoSuchFieldError unused4) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6393invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6393invoke() {
                    int i13 = WhenMappings.$EnumSwitchMapping$0[guideProgramItem.getState().ordinal()];
                    if (i13 == 1) {
                        lVar.invoke(guideProgramItem.getProgram());
                        return;
                    }
                    if (i13 != 2) {
                        if (i13 == 3) {
                            lVar.invoke(null);
                        } else if (i13 != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        FullscreenGuideOverlayKt.GuideProgramRow(guideProgramItem, z, j10, focusRequester, z5, (r7.a) objRememberedValue, composer, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
