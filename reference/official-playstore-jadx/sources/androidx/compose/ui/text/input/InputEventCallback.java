package androidx.compose.ui.text.input;

import java.util.List;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/input/InputEventCallback;", "", "", "Landroidx/compose/ui/text/input/EditCommand;", "editCommands", "Lx6/t0;", "onEditCommands", "(Ljava/util/List;)V", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "onImeAction-KlQnJC8", "(I)V", "onImeAction", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public interface InputEventCallback {
    void onEditCommands(List<? extends EditCommand> editCommands);

    /* JADX INFO: renamed from: onImeAction-KlQnJC8, reason: not valid java name */
    void m5382onImeActionKlQnJC8(int imeAction);
}
