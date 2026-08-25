package androidx.compose.ui.text.input;

import androidx.compose.material3.d;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/text/input/EditCommand;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EditProcessor$generateBatchErrorMessage$1$1 extends r implements l<EditCommand, CharSequence> {
    final /* synthetic */ EditCommand $failedCommand;
    final /* synthetic */ EditProcessor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditProcessor$generateBatchErrorMessage$1$1(EditCommand editCommand, EditProcessor editProcessor) {
        super(1);
        this.$failedCommand = editCommand;
        this.this$0 = editProcessor;
    }

    @Override // r7.l
    public final CharSequence invoke(EditCommand editCommand) {
        StringBuilder sbS = d.s(this.$failedCommand == editCommand ? " > " : "   ");
        sbS.append(this.this$0.toStringForLog(editCommand));
        return sbS.toString();
    }
}
