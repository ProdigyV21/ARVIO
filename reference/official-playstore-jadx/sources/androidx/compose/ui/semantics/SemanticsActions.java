package androidx.compose.ui.semantics;

import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R5\u0010\n\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR)\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\rR)\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0012\u0010\rRS\u0010\u0019\u001a>\u0012:\u00128\u00124\u00122\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\t0\u00130\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\rR/\u0010\u001c\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u000b\u001a\u0004\b\u001d\u0010\rR>\u0010\u001f\u001a)\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b \u0010\rR;\u0010\"\u001a&\u0012\"\u0012 \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0!0\u00050\u00048\u0006¢\u0006\f\n\u0004\b\"\u0010\u000b\u001a\u0004\b#\u0010\rR/\u0010%\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b%\u0010\u000b\u001a\u0004\b&\u0010\rR/\u0010'\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b'\u0010\u000b\u001a\u0004\b(\u0010\rR/\u0010)\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b)\u0010\u000b\u001a\u0004\b*\u0010\rR)\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b+\u0010\u000b\u001a\u0004\b,\u0010\rR/\u0010-\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\t0\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b-\u0010\u000b\u001a\u0004\b.\u0010\rR)\u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b/\u0010\u000b\u001a\u0004\b0\u0010\rR2\u00101\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b1\u0010\u000b\u0012\u0004\b3\u0010\u0003\u001a\u0004\b2\u0010\rR)\u00104\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b4\u0010\u000b\u001a\u0004\b5\u0010\rR)\u00106\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b6\u0010\u000b\u001a\u0004\b7\u0010\rR)\u00108\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b8\u0010\u000b\u001a\u0004\b9\u0010\rR)\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b:\u0010\u000b\u001a\u0004\b;\u0010\rR)\u0010<\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b<\u0010\u000b\u001a\u0004\b=\u0010\rR)\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b>\u0010\u000b\u001a\u0004\b?\u0010\rR)\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\b@\u0010\u000b\u001a\u0004\bA\u0010\rR#\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0B0\u00048\u0006¢\u0006\f\n\u0004\bD\u0010\u000b\u001a\u0004\bE\u0010\rR)\u0010F\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\bF\u0010\u000b\u001a\u0004\bG\u0010\rR)\u0010H\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\bH\u0010\u000b\u001a\u0004\bI\u0010\rR)\u0010J\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\bJ\u0010\u000b\u001a\u0004\bK\u0010\rR)\u0010L\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00050\u00048\u0006¢\u0006\f\n\u0004\bL\u0010\u000b\u001a\u0004\bM\u0010\r¨\u0006N"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsActions;", "", "<init>", "()V", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Landroidx/compose/ui/semantics/AccessibilityAction;", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/TextLayoutResult;", "", "GetTextLayoutResult", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "getGetTextLayoutResult", "()Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Lkotlin/Function0;", "OnClick", "getOnClick", "OnLongClick", "getOnLongClick", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "x", "y", "ScrollBy", "getScrollBy", "", "ScrollToIndex", "getScrollToIndex", "progress", "SetProgress", "getSetProgress", "Lkotlin/Function3;", "SetSelection", "getSetSelection", "Landroidx/compose/ui/text/AnnotatedString;", "SetText", "getSetText", "SetTextSubstitution", "getSetTextSubstitution", "ShowTextSubstitution", "getShowTextSubstitution", "ClearTextSubstitution", "getClearTextSubstitution", "InsertTextAtCursor", "getInsertTextAtCursor", "OnImeAction", "getOnImeAction", "PerformImeAction", "getPerformImeAction", "getPerformImeAction$annotations", "CopyText", "getCopyText", "CutText", "getCutText", "PasteText", "getPasteText", "Expand", "getExpand", "Collapse", "getCollapse", "Dismiss", "getDismiss", "RequestFocus", "getRequestFocus", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "CustomActions", "getCustomActions", "PageUp", "getPageUp", "PageLeft", "getPageLeft", "PageDown", "getPageDown", "PageRight", "getPageRight", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SemanticsActions {
    public static final int $stable;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> ClearTextSubstitution;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> Collapse;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> CopyText;
    private static final SemanticsPropertyKey<List<CustomAccessibilityAction>> CustomActions;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> CutText;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> Dismiss;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> Expand;
    private static final SemanticsPropertyKey<AccessibilityAction<l<List<TextLayoutResult>, Boolean>>> GetTextLayoutResult;
    public static final SemanticsActions INSTANCE = new SemanticsActions();
    private static final SemanticsPropertyKey<AccessibilityAction<l<AnnotatedString, Boolean>>> InsertTextAtCursor;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> OnClick;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> OnImeAction;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> OnLongClick;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> PageDown;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> PageLeft;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> PageRight;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> PageUp;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> PasteText;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> PerformImeAction;
    private static final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> RequestFocus;
    private static final SemanticsPropertyKey<AccessibilityAction<p<Float, Float, Boolean>>> ScrollBy;
    private static final SemanticsPropertyKey<AccessibilityAction<l<Integer, Boolean>>> ScrollToIndex;
    private static final SemanticsPropertyKey<AccessibilityAction<l<Float, Boolean>>> SetProgress;
    private static final SemanticsPropertyKey<AccessibilityAction<q<Integer, Integer, Boolean, Boolean>>> SetSelection;
    private static final SemanticsPropertyKey<AccessibilityAction<l<AnnotatedString, Boolean>>> SetText;
    private static final SemanticsPropertyKey<AccessibilityAction<l<AnnotatedString, Boolean>>> SetTextSubstitution;
    private static final SemanticsPropertyKey<AccessibilityAction<l<Boolean, Boolean>>> ShowTextSubstitution;

    static {
        SemanticsPropertiesKt.AnonymousClass1 anonymousClass1 = SemanticsPropertiesKt.AnonymousClass1.INSTANCE;
        GetTextLayoutResult = SemanticsPropertiesKt.AccessibilityKey("GetTextLayoutResult", anonymousClass1);
        OnClick = SemanticsPropertiesKt.AccessibilityKey("OnClick", anonymousClass1);
        OnLongClick = SemanticsPropertiesKt.AccessibilityKey("OnLongClick", anonymousClass1);
        ScrollBy = SemanticsPropertiesKt.AccessibilityKey("ScrollBy", anonymousClass1);
        ScrollToIndex = SemanticsPropertiesKt.AccessibilityKey("ScrollToIndex", anonymousClass1);
        SetProgress = SemanticsPropertiesKt.AccessibilityKey("SetProgress", anonymousClass1);
        SetSelection = SemanticsPropertiesKt.AccessibilityKey("SetSelection", anonymousClass1);
        SetText = SemanticsPropertiesKt.AccessibilityKey("SetText", anonymousClass1);
        SetTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("SetTextSubstitution", anonymousClass1);
        ShowTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("ShowTextSubstitution", anonymousClass1);
        ClearTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("ClearTextSubstitution", anonymousClass1);
        InsertTextAtCursor = SemanticsPropertiesKt.AccessibilityKey("InsertTextAtCursor", anonymousClass1);
        OnImeAction = SemanticsPropertiesKt.AccessibilityKey("PerformImeAction", anonymousClass1);
        PerformImeAction = SemanticsPropertiesKt.AccessibilityKey("PerformImeAction", anonymousClass1);
        CopyText = SemanticsPropertiesKt.AccessibilityKey("CopyText", anonymousClass1);
        CutText = SemanticsPropertiesKt.AccessibilityKey("CutText", anonymousClass1);
        PasteText = SemanticsPropertiesKt.AccessibilityKey("PasteText", anonymousClass1);
        Expand = SemanticsPropertiesKt.AccessibilityKey("Expand", anonymousClass1);
        Collapse = SemanticsPropertiesKt.AccessibilityKey("Collapse", anonymousClass1);
        Dismiss = SemanticsPropertiesKt.AccessibilityKey("Dismiss", anonymousClass1);
        RequestFocus = SemanticsPropertiesKt.AccessibilityKey("RequestFocus", anonymousClass1);
        CustomActions = SemanticsPropertiesKt.AccessibilityKey("CustomActions");
        PageUp = SemanticsPropertiesKt.AccessibilityKey("PageUp", anonymousClass1);
        PageLeft = SemanticsPropertiesKt.AccessibilityKey("PageLeft", anonymousClass1);
        PageDown = SemanticsPropertiesKt.AccessibilityKey("PageDown", anonymousClass1);
        PageRight = SemanticsPropertiesKt.AccessibilityKey("PageRight", anonymousClass1);
        $stable = 8;
    }

    private SemanticsActions() {
    }

    @e
    public static /* synthetic */ void getPerformImeAction$annotations() {
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getClearTextSubstitution() {
        return ClearTextSubstitution;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getCollapse() {
        return Collapse;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getCopyText() {
        return CopyText;
    }

    public final SemanticsPropertyKey<List<CustomAccessibilityAction>> getCustomActions() {
        return CustomActions;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getCutText() {
        return CutText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getDismiss() {
        return Dismiss;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getExpand() {
        return Expand;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<List<TextLayoutResult>, Boolean>>> getGetTextLayoutResult() {
        return GetTextLayoutResult;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<AnnotatedString, Boolean>>> getInsertTextAtCursor() {
        return InsertTextAtCursor;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getOnClick() {
        return OnClick;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getOnImeAction() {
        return OnImeAction;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getOnLongClick() {
        return OnLongClick;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getPageDown() {
        return PageDown;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getPageLeft() {
        return PageLeft;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getPageRight() {
        return PageRight;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getPageUp() {
        return PageUp;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getPasteText() {
        return PasteText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getPerformImeAction() {
        return PerformImeAction;
    }

    public final SemanticsPropertyKey<AccessibilityAction<r7.a<Boolean>>> getRequestFocus() {
        return RequestFocus;
    }

    public final SemanticsPropertyKey<AccessibilityAction<p<Float, Float, Boolean>>> getScrollBy() {
        return ScrollBy;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<Integer, Boolean>>> getScrollToIndex() {
        return ScrollToIndex;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<Float, Boolean>>> getSetProgress() {
        return SetProgress;
    }

    public final SemanticsPropertyKey<AccessibilityAction<q<Integer, Integer, Boolean, Boolean>>> getSetSelection() {
        return SetSelection;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<AnnotatedString, Boolean>>> getSetText() {
        return SetText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<AnnotatedString, Boolean>>> getSetTextSubstitution() {
        return SetTextSubstitution;
    }

    public final SemanticsPropertyKey<AccessibilityAction<l<Boolean, Boolean>>> getShowTextSubstitution() {
        return ShowTextSubstitution;
    }
}
