package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bà\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "", "adjust", "Landroidx/compose/foundation/text/selection/Selection;", TtmlNode.TAG_LAYOUT, "Landroidx/compose/foundation/text/selection/SelectionLayout;", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SelectionAdjustment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionAdjustment$Companion;", "", "()V", "Character", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getCharacter", "()Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "CharacterWithWordAccelerate", "getCharacterWithWordAccelerate", "None", "getNone", "Paragraph", "getParagraph", "Word", "getWord", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SelectionAdjustment Character;
        private static final SelectionAdjustment CharacterWithWordAccelerate;
        private static final SelectionAdjustment None;
        private static final SelectionAdjustment Paragraph;
        private static final SelectionAdjustment Word;

        static {
            final int i10 = 0;
            None = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.a
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    switch (i10) {
                        case 0:
                            return SelectionAdjustment.Companion.None$lambda$0(selectionLayout);
                        case 1:
                            return SelectionAdjustment.Companion.Character$lambda$1(selectionLayout);
                        case 2:
                            return SelectionAdjustment.Companion.Word$lambda$2(selectionLayout);
                        case 3:
                            return SelectionAdjustment.Companion.Paragraph$lambda$3(selectionLayout);
                        default:
                            return SelectionAdjustment.Companion.CharacterWithWordAccelerate$lambda$4(selectionLayout);
                    }
                }
            };
            final int i11 = 1;
            Character = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.a
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    switch (i11) {
                        case 0:
                            return SelectionAdjustment.Companion.None$lambda$0(selectionLayout);
                        case 1:
                            return SelectionAdjustment.Companion.Character$lambda$1(selectionLayout);
                        case 2:
                            return SelectionAdjustment.Companion.Word$lambda$2(selectionLayout);
                        case 3:
                            return SelectionAdjustment.Companion.Paragraph$lambda$3(selectionLayout);
                        default:
                            return SelectionAdjustment.Companion.CharacterWithWordAccelerate$lambda$4(selectionLayout);
                    }
                }
            };
            final int i12 = 2;
            Word = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.a
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    switch (i12) {
                        case 0:
                            return SelectionAdjustment.Companion.None$lambda$0(selectionLayout);
                        case 1:
                            return SelectionAdjustment.Companion.Character$lambda$1(selectionLayout);
                        case 2:
                            return SelectionAdjustment.Companion.Word$lambda$2(selectionLayout);
                        case 3:
                            return SelectionAdjustment.Companion.Paragraph$lambda$3(selectionLayout);
                        default:
                            return SelectionAdjustment.Companion.CharacterWithWordAccelerate$lambda$4(selectionLayout);
                    }
                }
            };
            final int i13 = 3;
            Paragraph = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.a
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    switch (i13) {
                        case 0:
                            return SelectionAdjustment.Companion.None$lambda$0(selectionLayout);
                        case 1:
                            return SelectionAdjustment.Companion.Character$lambda$1(selectionLayout);
                        case 2:
                            return SelectionAdjustment.Companion.Word$lambda$2(selectionLayout);
                        case 3:
                            return SelectionAdjustment.Companion.Paragraph$lambda$3(selectionLayout);
                        default:
                            return SelectionAdjustment.Companion.CharacterWithWordAccelerate$lambda$4(selectionLayout);
                    }
                }
            };
            final int i14 = 4;
            CharacterWithWordAccelerate = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.a
                @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
                public final Selection adjust(SelectionLayout selectionLayout) {
                    switch (i14) {
                        case 0:
                            return SelectionAdjustment.Companion.None$lambda$0(selectionLayout);
                        case 1:
                            return SelectionAdjustment.Companion.Character$lambda$1(selectionLayout);
                        case 2:
                            return SelectionAdjustment.Companion.Word$lambda$2(selectionLayout);
                        case 3:
                            return SelectionAdjustment.Companion.Paragraph$lambda$3(selectionLayout);
                        default:
                            return SelectionAdjustment.Companion.CharacterWithWordAccelerate$lambda$4(selectionLayout);
                    }
                }
            };
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Selection Character$lambda$1(SelectionLayout selectionLayout) {
            return SelectionAdjustmentKt.ensureAtLeastOneChar(None.adjust(selectionLayout), selectionLayout);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Selection CharacterWithWordAccelerate$lambda$4(SelectionLayout selectionLayout) {
            Selection.AnchorInfo end;
            Selection.AnchorInfo anchorInfoUpdateSelectionBoundary;
            Selection.AnchorInfo start;
            Selection.AnchorInfo end2;
            Selection previousSelection = selectionLayout.getPreviousSelection();
            if (previousSelection == null) {
                return Word.adjust(selectionLayout);
            }
            if (selectionLayout.isStartHandle()) {
                end = previousSelection.getStart();
                anchorInfoUpdateSelectionBoundary = SelectionAdjustmentKt.updateSelectionBoundary(selectionLayout, selectionLayout.getStartInfo(), end);
                end2 = previousSelection.getEnd();
                start = anchorInfoUpdateSelectionBoundary;
            } else {
                end = previousSelection.getEnd();
                anchorInfoUpdateSelectionBoundary = SelectionAdjustmentKt.updateSelectionBoundary(selectionLayout, selectionLayout.getEndInfo(), end);
                start = previousSelection.getStart();
                end2 = anchorInfoUpdateSelectionBoundary;
            }
            if (p.a(anchorInfoUpdateSelectionBoundary, end)) {
                return previousSelection;
            }
            return SelectionAdjustmentKt.ensureAtLeastOneChar(new Selection(start, end2, selectionLayout.getCrossStatus() == CrossStatus.CROSSED || (selectionLayout.getCrossStatus() == CrossStatus.COLLAPSED && start.getOffset() > end2.getOffset())), selectionLayout);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Selection None$lambda$0(SelectionLayout selectionLayout) {
            return new Selection(selectionLayout.getStartInfo().anchorForOffset(selectionLayout.getStartInfo().getRawStartHandleOffset()), selectionLayout.getEndInfo().anchorForOffset(selectionLayout.getEndInfo().getRawEndHandleOffset()), selectionLayout.getCrossStatus() == CrossStatus.CROSSED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Selection Paragraph$lambda$3(SelectionLayout selectionLayout) {
            return SelectionAdjustmentKt.adjustToBoundaries(selectionLayout, new BoundaryFunction() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Paragraph$1$1
                @Override // androidx.compose.foundation.text.selection.BoundaryFunction
                /* JADX INFO: renamed from: getBoundary-fzxv0v0 */
                public final long mo958getBoundaryfzxv0v0(SelectableInfo selectableInfo, int i10) {
                    return StringHelpersKt.getParagraphBoundary(selectableInfo.getInputText(), i10);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Selection Word$lambda$2(SelectionLayout selectionLayout) {
            return SelectionAdjustmentKt.adjustToBoundaries(selectionLayout, new BoundaryFunction() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Word$1$1
                @Override // androidx.compose.foundation.text.selection.BoundaryFunction
                /* JADX INFO: renamed from: getBoundary-fzxv0v0 */
                public final long mo958getBoundaryfzxv0v0(SelectableInfo selectableInfo, int i10) {
                    return selectableInfo.getTextLayoutResult().m5166getWordBoundaryjx7JFs(i10);
                }
            });
        }

        public final SelectionAdjustment getCharacter() {
            return Character;
        }

        public final SelectionAdjustment getCharacterWithWordAccelerate() {
            return CharacterWithWordAccelerate;
        }

        public final SelectionAdjustment getNone() {
            return None;
        }

        public final SelectionAdjustment getParagraph() {
            return Paragraph;
        }

        public final SelectionAdjustment getWord() {
            return Word;
        }
    }

    Selection adjust(SelectionLayout layout);
}
