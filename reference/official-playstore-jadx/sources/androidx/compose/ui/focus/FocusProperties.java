package androidx.compose.ui.focus;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0007\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R$\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR$\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR$\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR$\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR$\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR$\u0010 \u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR$\u0010#\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\rRB\u0010,\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b0$2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b0$8W@WX\u0097\u000e¢\u0006\u0012\u0012\u0004\b*\u0010+\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)RB\u00100\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b0$2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b0$8W@WX\u0097\u000e¢\u0006\u0012\u0012\u0004\b/\u0010+\u001a\u0004\b-\u0010'\"\u0004\b.\u0010)ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00061À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/FocusProperties;", "", "", "getCanFocus", "()Z", "setCanFocus", "(Z)V", "canFocus", "Landroidx/compose/ui/focus/FocusRequester;", "<anonymous parameter 0>", "getNext", "()Landroidx/compose/ui/focus/FocusRequester;", "setNext", "(Landroidx/compose/ui/focus/FocusRequester;)V", LinkHeader.Rel.Next, "getPrevious", "setPrevious", "previous", "getUp", "setUp", "up", "getDown", "setDown", "down", "getLeft", "setLeft", TtmlNode.LEFT, "getRight", "setRight", TtmlNode.RIGHT, "getStart", "setStart", TtmlNode.START, "getEnd", "setEnd", TtmlNode.END, "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusDirection;", "getEnter", "()Lr7/l;", "setEnter", "(Lr7/l;)V", "getEnter$annotations", "()V", "enter", "getExit", "setExit", "getExit$annotations", "exit", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface FocusProperties {
    boolean getCanFocus();

    FocusRequester getDown();

    FocusRequester getEnd();

    l<FocusDirection, FocusRequester> getEnter();

    l<FocusDirection, FocusRequester> getExit();

    FocusRequester getLeft();

    FocusRequester getNext();

    FocusRequester getPrevious();

    FocusRequester getRight();

    FocusRequester getStart();

    FocusRequester getUp();

    void setCanFocus(boolean z);

    void setDown(FocusRequester focusRequester);

    void setEnd(FocusRequester focusRequester);

    void setEnter(l<? super FocusDirection, FocusRequester> lVar);

    void setExit(l<? super FocusDirection, FocusRequester> lVar);

    void setLeft(FocusRequester focusRequester);

    void setNext(FocusRequester focusRequester);

    void setPrevious(FocusRequester focusRequester);

    void setRight(FocusRequester focusRequester);

    void setStart(FocusRequester focusRequester);

    void setUp(FocusRequester focusRequester);

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusProperties$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static FocusRequester a(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static FocusRequester b(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static l c(FocusProperties focusProperties) {
            return FocusProperties$enter$1.INSTANCE;
        }

        public static l d(FocusProperties focusProperties) {
            return FocusProperties$exit$1.INSTANCE;
        }

        public static FocusRequester e(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static FocusRequester f(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static FocusRequester g(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static FocusRequester h(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static FocusRequester i(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static FocusRequester j(FocusProperties focusProperties) {
            return FocusRequester.INSTANCE.getDefault();
        }

        public static /* synthetic */ void u() {
        }

        public static /* synthetic */ void v() {
        }

        public static void k(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void l(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void m(FocusProperties focusProperties, l lVar) {
        }

        public static void n(FocusProperties focusProperties, l lVar) {
        }

        public static void o(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void p(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void q(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void r(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void s(FocusProperties focusProperties, FocusRequester focusRequester) {
        }

        public static void t(FocusProperties focusProperties, FocusRequester focusRequester) {
        }
    }
}
