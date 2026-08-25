package androidx.compose.foundation.text2;

import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R+\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00128@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/text2/PasswordRevealFilter;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Lkotlin/Function0;", "Lx6/t0;", "scheduleHide", "<init>", "(Lr7/a;)V", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "originalValue", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "valueWithChanges", "transformInput", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "hide", "()V", "Lr7/a;", "getScheduleHide", "()Lr7/a;", "", "<set-?>", "revealCodepointIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "getRevealCodepointIndex$foundation_release", "()I", "setRevealCodepointIndex", "(I)V", "revealCodepointIndex", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PasswordRevealFilter implements InputTransformation {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: revealCodepointIndex$delegate, reason: from kotlin metadata */
    private final MutableIntState revealCodepointIndex = SnapshotIntStateKt.mutableIntStateOf(-1);
    private final a<t0> scheduleHide;

    public PasswordRevealFilter(a<t0> aVar) {
        this.scheduleHide = aVar;
    }

    private final void setRevealCodepointIndex(int i10) {
        this.revealCodepointIndex.setIntValue(i10);
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public final /* synthetic */ KeyboardOptions getKeyboardOptions() {
        return androidx.compose.foundation.text2.input.a.a(this);
    }

    public final int getRevealCodepointIndex$foundation_release() {
        return this.revealCodepointIndex.getIntValue();
    }

    public final a<t0> getScheduleHide() {
        return this.scheduleHide;
    }

    public final void hide() {
        setRevealCodepointIndex(-1);
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public void transformInput(TextFieldCharSequence originalValue, TextFieldBuffer valueWithChanges) {
        if (valueWithChanges.getChanges().getChangeCount() != 1 || TextRange.m5188getLengthimpl(valueWithChanges.getChanges().mo1054getRangejx7JFs(0)) != 1 || TextRange.m5188getLengthimpl(valueWithChanges.getChanges().mo1053getOriginalRangejx7JFs(0)) != 0 || valueWithChanges.hasSelection()) {
            setRevealCodepointIndex(-1);
            return;
        }
        int iM5190getMinimpl = TextRange.m5190getMinimpl(valueWithChanges.getChanges().mo1054getRangejx7JFs(0));
        if (getRevealCodepointIndex$foundation_release() != iM5190getMinimpl) {
            this.scheduleHide.invoke();
            setRevealCodepointIndex(iM5190getMinimpl);
        }
    }
}
