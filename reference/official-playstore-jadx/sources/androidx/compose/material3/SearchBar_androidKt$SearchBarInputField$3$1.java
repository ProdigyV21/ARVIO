package androidx.compose.material3;

import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SearchBar_androidKt$SearchBarInputField$3$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ boolean $active;
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ String $searchSemantics;
    final /* synthetic */ String $suggestionsAvailableSemantics;

    /* JADX INFO: renamed from: androidx.compose.material3.SearchBar_androidKt$SearchBarInputField$3$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<Boolean> {
        final /* synthetic */ FocusRequester $focusRequester;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FocusRequester focusRequester) {
            super(0);
            this.$focusRequester = focusRequester;
        }

        @Override // r7.a
        public final Boolean invoke() {
            this.$focusRequester.requestFocus();
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBar_androidKt$SearchBarInputField$3$1(String str, boolean z, String str2, FocusRequester focusRequester) {
        super(1);
        this.$searchSemantics = str;
        this.$active = z;
        this.$suggestionsAvailableSemantics = str2;
        this.$focusRequester = focusRequester;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, this.$searchSemantics);
        if (this.$active) {
            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, this.$suggestionsAvailableSemantics);
        }
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new AnonymousClass1(this.$focusRequester), 1, null);
    }
}
