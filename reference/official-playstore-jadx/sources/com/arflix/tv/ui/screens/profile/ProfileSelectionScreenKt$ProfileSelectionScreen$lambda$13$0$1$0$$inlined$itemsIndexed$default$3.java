package com.arflix.tv.ui.screens.profile;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import com.arflix.tv.data.model.Profile;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class ProfileSelectionScreenKt$ProfileSelectionScreen$lambda$13$0$1$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ float $avatarSize$inlined;
    final /* synthetic */ List $focusRequesters$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ MutableState $navigateTriggered$delegate$inlined;
    final /* synthetic */ State $uiState$delegate$inlined;
    final /* synthetic */ ProfileViewModel $viewModel$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileSelectionScreenKt$ProfileSelectionScreen$lambda$13$0$1$0$$inlined$itemsIndexed$default$3(List list, float f10, List list2, State state, ProfileViewModel profileViewModel, MutableState mutableState) {
        super(4);
        this.$items = list;
        this.$avatarSize$inlined = f10;
        this.$focusRequesters$inlined = list2;
        this.$uiState$delegate$inlined = state;
        this.$viewModel$inlined = profileViewModel;
        this.$navigateTriggered$delegate$inlined = mutableState;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
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
        final Profile profile = (Profile) this.$items.get(i10);
        composer.startReplaceGroup(-1229834238);
        boolean zIsManageMode = ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$1(this.$uiState$delegate$inlined).isManageMode();
        Profile activeProfile = ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$1(this.$uiState$delegate$inlined).getActiveProfile();
        boolean zA = kotlin.jvm.internal.p.a(activeProfile != null ? activeProfile.getId() : null, profile.getId());
        float f10 = this.$avatarSize$inlined;
        Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, (FocusRequester) this.$focusRequesters$inlined.get(i10));
        boolean zChanged = composer.changed(this.$uiState$delegate$inlined) | composer.changedInstance(this.$viewModel$inlined) | composer.changed(profile);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final ProfileViewModel profileViewModel = this.$viewModel$inlined;
            final State state = this.$uiState$delegate$inlined;
            final MutableState mutableState = this.$navigateTriggered$delegate$inlined;
            objRememberedValue = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt$ProfileSelectionScreen$6$1$2$1$1$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6281invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6281invoke() {
                    if (ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$1(state).isSwitchingProfile()) {
                        return;
                    }
                    if (ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$1(state).isManageMode()) {
                        profileViewModel.showEditDialog(profile);
                    } else {
                        ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$5(mutableState, true);
                        profileViewModel.selectProfileWithLockCheck(profile);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.a aVar = (r7.a) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(this.$viewModel$inlined) | composer.changed(profile);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final ProfileViewModel profileViewModel2 = this.$viewModel$inlined;
            objRememberedValue2 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt$ProfileSelectionScreen$6$1$2$1$1$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6282invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6282invoke() {
                    profileViewModel2.preloadForProfile(profile);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar2 = (r7.a) objRememberedValue2;
        boolean zChangedInstance2 = composer.changedInstance(this.$viewModel$inlined) | composer.changed(profile);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            final ProfileViewModel profileViewModel3 = this.$viewModel$inlined;
            objRememberedValue3 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt$ProfileSelectionScreen$6$1$2$1$1$3$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6283invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6283invoke() {
                    profileViewModel3.deleteProfile(profile);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ProfileSelectionScreenKt.m6279ProfileAvataraA_HZ9I(profile, zIsManageMode, zA, f10, modifierFocusRequester, aVar, aVar2, (r7.a) objRememberedValue3, composer, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
