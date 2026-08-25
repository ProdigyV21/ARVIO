package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0001¢\u0006\u0004\b\u0004\u0010\u0003\u001a%\u0010\t\u001a\u00020\u0005*\u00020\u00052\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\t\u0010\n\"\u001a\u0010\f\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\f\n\u0004\b\f\u0010\r\u0012\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "", "saveFocusedChild", "(Landroidx/compose/ui/focus/FocusTargetNode;)Z", "restoreFocusedChild", "Landroidx/compose/ui/Modifier;", "Lkotlin/Function0;", "Landroidx/compose/ui/focus/FocusRequester;", "onRestoreFailed", "focusRestorer", "(Landroidx/compose/ui/Modifier;Lr7/a;)Landroidx/compose/ui/Modifier;", "", "PrevFocusedChild", "Ljava/lang/String;", "getPrevFocusedChild$annotations", "()V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusRestorerKt {
    private static final String PrevFocusedChild = "previouslyFocusedChildHash";

    public static final Modifier focusRestorer(Modifier modifier, r7.a<FocusRequester> aVar) {
        return modifier.then(new FocusRestorerElement(aVar));
    }

    public static /* synthetic */ Modifier focusRestorer$default(Modifier modifier, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = null;
        }
        return focusRestorer(modifier, aVar);
    }

    private static /* synthetic */ void getPrevFocusedChild$annotations() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x005a, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean restoreFocusedChild(androidx.compose.ui.focus.FocusTargetNode r11) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRestorerKt.restoreFocusedChild(androidx.compose.ui.focus.FocusTargetNode):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x003a, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean saveFocusedChild(androidx.compose.ui.focus.FocusTargetNode r11) {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRestorerKt.saveFocusedChild(androidx.compose.ui.focus.FocusTargetNode):boolean");
    }
}
