package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SelectionContainerKt$SelectionContainer$3$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $children;
    final /* synthetic */ SelectionManager $manager;
    final /* synthetic */ Modifier $modifier;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$3$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $children;
        final /* synthetic */ SelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super Composer, ? super Integer, t0> pVar, SelectionManager selectionManager) {
            super(2);
            this.$children = pVar;
            this.$manager = selectionManager;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            Selection selection;
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1375295262, i10, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous>.<anonymous> (SelectionContainer.kt:102)");
            }
            this.$children.invoke(composer, 0);
            if (this.$manager.isInTouchMode() && this.$manager.getHasFocus() && !this.$manager.isTriviallyCollapsedSelection$foundation_release() && (selection = this.$manager.getSelection()) != null) {
                SelectionManager selectionManager = this.$manager;
                List listE = t7.a.E(Boolean.TRUE, Boolean.FALSE);
                int size = listE.size();
                for (int i11 = 0; i11 < size; i11++) {
                    Boolean bool = (Boolean) listE.get(i11);
                    boolean zBooleanValue = bool.booleanValue();
                    composer.startReplaceableGroup(1157296644);
                    boolean zChanged = composer.changed(bool);
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = selectionManager.handleDragObserver(zBooleanValue);
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceableGroup();
                    TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
                    composer.startReplaceableGroup(1157296644);
                    boolean zChanged2 = composer.changed(bool);
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = zBooleanValue ? new SelectionContainerKt$SelectionContainer$3$1$1$1$1$positionProvider$1$1(selectionManager) : new SelectionContainerKt$SelectionContainer$3$1$1$1$1$positionProvider$1$2(selectionManager);
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    composer.endReplaceableGroup();
                    AndroidSelectionHandles_androidKt.SelectionHandle(new SelectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0((r7.a) objRememberedValue2), zBooleanValue, zBooleanValue ? selection.getStart().getDirection() : selection.getEnd().getDirection(), selection.getHandlesCrossed(), SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textDragObserver, new SelectionContainerKt$SelectionContainer$3$1$1$1$1$1(textDragObserver, null)), composer, 0);
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SelectionContainerKt$SelectionContainer$3$1(Modifier modifier, SelectionManager selectionManager, p<? super Composer, ? super Integer, t0> pVar) {
        super(2);
        this.$modifier = modifier;
        this.$manager = selectionManager;
        this.$children = pVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 11) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(935424596, i10, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous> (SelectionContainer.kt:101)");
        }
        SimpleLayoutKt.SimpleLayout(this.$modifier.then(this.$manager.getModifier()), ComposableLambdaKt.composableLambda(composer, 1375295262, true, new AnonymousClass1(this.$children, this.$manager)), composer, 48, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
