package androidx.compose.ui.semantics;

import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\nR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\b\u001a\u0004\b\u000f\u0010\nR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u0011\u0010\nR\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0014\u0010\nR\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0017\u0010\nR\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u001a\u0010\nR\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u001c\u0010\nR\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\b\u001a\u0004\b\u001e\u0010\nR\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u00048\u0006¢\u0006\f\n\u0004\b \u0010\b\u001a\u0004\b!\u0010\nR\u001d\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u00048\u0006¢\u0006\f\n\u0004\b#\u0010\b\u001a\u0004\b$\u0010\nR\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0\u00048\u0006¢\u0006\f\n\u0004\b%\u0010\b\u001a\u0004\b&\u0010\nR&\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b'\u0010\b\u0012\u0004\b)\u0010\u0003\u001a\u0004\b(\u0010\nR\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00048\u0006¢\u0006\f\n\u0004\b+\u0010\b\u001a\u0004\b,\u0010\nR\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00020-0\u00048\u0006¢\u0006\f\n\u0004\b.\u0010\b\u001a\u0004\b/\u0010\nR\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020-0\u00048\u0006¢\u0006\f\n\u0004\b0\u0010\b\u001a\u0004\b1\u0010\nR\u001d\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006¢\u0006\f\n\u0004\b2\u0010\b\u001a\u0004\b3\u0010\nR\u001d\u00104\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006¢\u0006\f\n\u0004\b4\u0010\b\u001a\u0004\b5\u0010\nR\u001d\u00107\u001a\b\u0012\u0004\u0012\u0002060\u00048\u0006¢\u0006\f\n\u0004\b7\u0010\b\u001a\u0004\b8\u0010\nR\u001d\u00109\u001a\b\u0012\u0004\u0012\u00020\u00060\u00048\u0006¢\u0006\f\n\u0004\b9\u0010\b\u001a\u0004\b:\u0010\nR#\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0\u00050\u00048\u0006¢\u0006\f\n\u0004\b<\u0010\b\u001a\u0004\b=\u0010\nR\u001d\u0010>\u001a\b\u0012\u0004\u0012\u00020;0\u00048\u0006¢\u0006\f\n\u0004\b>\u0010\b\u001a\u0004\b?\u0010\nR\u001d\u0010@\u001a\b\u0012\u0004\u0012\u00020\"0\u00048\u0006¢\u0006\f\n\u0004\b@\u0010\b\u001a\u0004\bA\u0010\nR\u001d\u0010B\u001a\b\u0012\u0004\u0012\u00020;0\u00048\u0006¢\u0006\f\n\u0004\bB\u0010\b\u001a\u0004\bC\u0010\nR\u001d\u0010E\u001a\b\u0012\u0004\u0012\u00020D0\u00048\u0006¢\u0006\f\n\u0004\bE\u0010\b\u001a\u0004\bF\u0010\nR\u001d\u0010H\u001a\b\u0012\u0004\u0012\u00020G0\u00048\u0006¢\u0006\f\n\u0004\bH\u0010\b\u001a\u0004\bI\u0010\nR\u001d\u0010J\u001a\b\u0012\u0004\u0012\u00020\"0\u00048\u0006¢\u0006\f\n\u0004\bJ\u0010\b\u001a\u0004\bK\u0010\nR\u001d\u0010M\u001a\b\u0012\u0004\u0012\u00020L0\u00048\u0006¢\u0006\f\n\u0004\bM\u0010\b\u001a\u0004\bN\u0010\nR\u001d\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006¢\u0006\f\n\u0004\bO\u0010\b\u001a\u0004\bP\u0010\nR\u001d\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00060\u00048\u0006¢\u0006\f\n\u0004\bQ\u0010\b\u001a\u0004\bR\u0010\nR)\u0010U\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020T0S0\u00048\u0006¢\u0006\f\n\u0004\bU\u0010\b\u001a\u0004\bV\u0010\nR \u0010Y\u001a\b\u0012\u0004\u0012\u00020\"0\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\bX\u0010\u0003\u001a\u0004\bW\u0010\n¨\u0006Z"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsProperties;", "", "<init>", "()V", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "", "", "ContentDescription", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "getContentDescription", "()Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "StateDescription", "getStateDescription", "Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", "ProgressBarRangeInfo", "getProgressBarRangeInfo", "PaneTitle", "getPaneTitle", "Lx6/t0;", "SelectableGroup", "getSelectableGroup", "Landroidx/compose/ui/semantics/CollectionInfo;", "CollectionInfo", "getCollectionInfo", "Landroidx/compose/ui/semantics/CollectionItemInfo;", "CollectionItemInfo", "getCollectionItemInfo", "Heading", "getHeading", "Disabled", "getDisabled", "Landroidx/compose/ui/semantics/LiveRegionMode;", "LiveRegion", "getLiveRegion", "", "Focused", "getFocused", "IsTraversalGroup", "getIsTraversalGroup", "InvisibleToUser", "getInvisibleToUser", "getInvisibleToUser$annotations", "", "TraversalIndex", "getTraversalIndex", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "HorizontalScrollAxisRange", "getHorizontalScrollAxisRange", "VerticalScrollAxisRange", "getVerticalScrollAxisRange", "IsPopup", "getIsPopup", "IsDialog", "getIsDialog", "Landroidx/compose/ui/semantics/Role;", "Role", "getRole", "TestTag", "getTestTag", "Landroidx/compose/ui/text/AnnotatedString;", "Text", "getText", "TextSubstitution", "getTextSubstitution", "IsShowingTextSubstitution", "getIsShowingTextSubstitution", "EditableText", "getEditableText", "Landroidx/compose/ui/text/TextRange;", "TextSelectionRange", "getTextSelectionRange", "Landroidx/compose/ui/text/input/ImeAction;", "ImeAction", "getImeAction", "Selected", "getSelected", "Landroidx/compose/ui/state/ToggleableState;", "ToggleableState", "getToggleableState", "Password", "getPassword", "Error", "getError", "Lkotlin/Function1;", "", "IndexForKey", "getIndexForKey", "getIsContainer", "getIsContainer$annotations", "IsContainer", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SemanticsProperties {
    public static final SemanticsProperties INSTANCE = new SemanticsProperties();
    private static final SemanticsPropertyKey<List<String>> ContentDescription = SemanticsPropertiesKt.AccessibilityKey("ContentDescription", SemanticsProperties$ContentDescription$1.INSTANCE);
    private static final SemanticsPropertyKey<String> StateDescription = SemanticsPropertiesKt.AccessibilityKey("StateDescription");
    private static final SemanticsPropertyKey<ProgressBarRangeInfo> ProgressBarRangeInfo = SemanticsPropertiesKt.AccessibilityKey("ProgressBarRangeInfo");
    private static final SemanticsPropertyKey<String> PaneTitle = SemanticsPropertiesKt.AccessibilityKey("PaneTitle", SemanticsProperties$PaneTitle$1.INSTANCE);
    private static final SemanticsPropertyKey<t0> SelectableGroup = SemanticsPropertiesKt.AccessibilityKey("SelectableGroup");
    private static final SemanticsPropertyKey<CollectionInfo> CollectionInfo = SemanticsPropertiesKt.AccessibilityKey("CollectionInfo");
    private static final SemanticsPropertyKey<CollectionItemInfo> CollectionItemInfo = SemanticsPropertiesKt.AccessibilityKey("CollectionItemInfo");
    private static final SemanticsPropertyKey<t0> Heading = SemanticsPropertiesKt.AccessibilityKey("Heading");
    private static final SemanticsPropertyKey<t0> Disabled = SemanticsPropertiesKt.AccessibilityKey("Disabled");
    private static final SemanticsPropertyKey<LiveRegionMode> LiveRegion = SemanticsPropertiesKt.AccessibilityKey("LiveRegion");
    private static final SemanticsPropertyKey<Boolean> Focused = SemanticsPropertiesKt.AccessibilityKey("Focused");
    private static final SemanticsPropertyKey<Boolean> IsTraversalGroup = SemanticsPropertiesKt.AccessibilityKey("IsTraversalGroup");
    private static final SemanticsPropertyKey<t0> InvisibleToUser = new SemanticsPropertyKey<>("InvisibleToUser", SemanticsProperties$InvisibleToUser$1.INSTANCE);
    private static final SemanticsPropertyKey<Float> TraversalIndex = SemanticsPropertiesKt.AccessibilityKey("TraversalIndex", SemanticsProperties$TraversalIndex$1.INSTANCE);
    private static final SemanticsPropertyKey<ScrollAxisRange> HorizontalScrollAxisRange = SemanticsPropertiesKt.AccessibilityKey("HorizontalScrollAxisRange");
    private static final SemanticsPropertyKey<ScrollAxisRange> VerticalScrollAxisRange = SemanticsPropertiesKt.AccessibilityKey("VerticalScrollAxisRange");
    private static final SemanticsPropertyKey<t0> IsPopup = SemanticsPropertiesKt.AccessibilityKey("IsPopup", SemanticsProperties$IsPopup$1.INSTANCE);
    private static final SemanticsPropertyKey<t0> IsDialog = SemanticsPropertiesKt.AccessibilityKey("IsDialog", SemanticsProperties$IsDialog$1.INSTANCE);
    private static final SemanticsPropertyKey<Role> Role = SemanticsPropertiesKt.AccessibilityKey("Role", SemanticsProperties$Role$1.INSTANCE);
    private static final SemanticsPropertyKey<String> TestTag = new SemanticsPropertyKey<>("TestTag", false, SemanticsProperties$TestTag$1.INSTANCE);
    private static final SemanticsPropertyKey<List<AnnotatedString>> Text = SemanticsPropertiesKt.AccessibilityKey("Text", SemanticsProperties$Text$1.INSTANCE);
    private static final SemanticsPropertyKey<AnnotatedString> TextSubstitution = new SemanticsPropertyKey<>("TextSubstitution", null, 2, null);
    private static final SemanticsPropertyKey<Boolean> IsShowingTextSubstitution = new SemanticsPropertyKey<>("IsShowingTextSubstitution", null, 2, null);
    private static final SemanticsPropertyKey<AnnotatedString> EditableText = SemanticsPropertiesKt.AccessibilityKey("EditableText");
    private static final SemanticsPropertyKey<TextRange> TextSelectionRange = SemanticsPropertiesKt.AccessibilityKey("TextSelectionRange");
    private static final SemanticsPropertyKey<ImeAction> ImeAction = SemanticsPropertiesKt.AccessibilityKey("ImeAction");
    private static final SemanticsPropertyKey<Boolean> Selected = SemanticsPropertiesKt.AccessibilityKey("Selected");
    private static final SemanticsPropertyKey<ToggleableState> ToggleableState = SemanticsPropertiesKt.AccessibilityKey("ToggleableState");
    private static final SemanticsPropertyKey<t0> Password = SemanticsPropertiesKt.AccessibilityKey("Password");
    private static final SemanticsPropertyKey<String> Error = SemanticsPropertiesKt.AccessibilityKey("Error");
    private static final SemanticsPropertyKey<l<Object, Integer>> IndexForKey = new SemanticsPropertyKey<>("IndexForKey", null, 2, null);
    public static final int $stable = 8;

    private SemanticsProperties() {
    }

    public static /* synthetic */ void getInvisibleToUser$annotations() {
    }

    @e
    public static /* synthetic */ void getIsContainer$annotations() {
    }

    public final SemanticsPropertyKey<CollectionInfo> getCollectionInfo() {
        return CollectionInfo;
    }

    public final SemanticsPropertyKey<CollectionItemInfo> getCollectionItemInfo() {
        return CollectionItemInfo;
    }

    public final SemanticsPropertyKey<List<String>> getContentDescription() {
        return ContentDescription;
    }

    public final SemanticsPropertyKey<t0> getDisabled() {
        return Disabled;
    }

    public final SemanticsPropertyKey<AnnotatedString> getEditableText() {
        return EditableText;
    }

    public final SemanticsPropertyKey<String> getError() {
        return Error;
    }

    public final SemanticsPropertyKey<Boolean> getFocused() {
        return Focused;
    }

    public final SemanticsPropertyKey<t0> getHeading() {
        return Heading;
    }

    public final SemanticsPropertyKey<ScrollAxisRange> getHorizontalScrollAxisRange() {
        return HorizontalScrollAxisRange;
    }

    public final SemanticsPropertyKey<ImeAction> getImeAction() {
        return ImeAction;
    }

    public final SemanticsPropertyKey<l<Object, Integer>> getIndexForKey() {
        return IndexForKey;
    }

    public final SemanticsPropertyKey<t0> getInvisibleToUser() {
        return InvisibleToUser;
    }

    public final SemanticsPropertyKey<Boolean> getIsContainer() {
        return IsTraversalGroup;
    }

    public final SemanticsPropertyKey<t0> getIsDialog() {
        return IsDialog;
    }

    public final SemanticsPropertyKey<t0> getIsPopup() {
        return IsPopup;
    }

    public final SemanticsPropertyKey<Boolean> getIsShowingTextSubstitution() {
        return IsShowingTextSubstitution;
    }

    public final SemanticsPropertyKey<Boolean> getIsTraversalGroup() {
        return IsTraversalGroup;
    }

    public final SemanticsPropertyKey<LiveRegionMode> getLiveRegion() {
        return LiveRegion;
    }

    public final SemanticsPropertyKey<String> getPaneTitle() {
        return PaneTitle;
    }

    public final SemanticsPropertyKey<t0> getPassword() {
        return Password;
    }

    public final SemanticsPropertyKey<ProgressBarRangeInfo> getProgressBarRangeInfo() {
        return ProgressBarRangeInfo;
    }

    public final SemanticsPropertyKey<Role> getRole() {
        return Role;
    }

    public final SemanticsPropertyKey<t0> getSelectableGroup() {
        return SelectableGroup;
    }

    public final SemanticsPropertyKey<Boolean> getSelected() {
        return Selected;
    }

    public final SemanticsPropertyKey<String> getStateDescription() {
        return StateDescription;
    }

    public final SemanticsPropertyKey<String> getTestTag() {
        return TestTag;
    }

    public final SemanticsPropertyKey<List<AnnotatedString>> getText() {
        return Text;
    }

    public final SemanticsPropertyKey<TextRange> getTextSelectionRange() {
        return TextSelectionRange;
    }

    public final SemanticsPropertyKey<AnnotatedString> getTextSubstitution() {
        return TextSubstitution;
    }

    public final SemanticsPropertyKey<ToggleableState> getToggleableState() {
        return ToggleableState;
    }

    public final SemanticsPropertyKey<Float> getTraversalIndex() {
        return TraversalIndex;
    }

    public final SemanticsPropertyKey<ScrollAxisRange> getVerticalScrollAxisRange() {
        return VerticalScrollAxisRange;
    }
}
