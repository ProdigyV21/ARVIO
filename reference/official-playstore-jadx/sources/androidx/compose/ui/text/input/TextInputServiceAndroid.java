package androidx.compose.ui.text.input;

import android.graphics.Rect;
import android.view.Choreographer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.activity.n;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import f4.f;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.e;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001hB)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0016¢\u0006\u0004\b\u001f\u0010 JM\u0010+\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0018\u0010(\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0&\u0012\u0004\u0012\u00020\u00100%2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00100%H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010+\u001a\u00020\u0010H\u0016¢\u0006\u0004\b+\u0010\u0014J\u000f\u0010-\u001a\u00020\u0010H\u0016¢\u0006\u0004\b-\u0010\u0014J\u000f\u0010.\u001a\u00020\u0010H\u0016¢\u0006\u0004\b.\u0010\u0014J\u000f\u0010/\u001a\u00020\u0010H\u0016¢\u0006\u0004\b/\u0010\u0014J!\u00102\u001a\u00020\u00102\b\u00100\u001a\u0004\u0018\u00010!2\u0006\u00101\u001a\u00020!H\u0016¢\u0006\u0004\b2\u00103J\u0017\u00106\u001a\u00020\u00102\u0006\u00105\u001a\u000204H\u0017¢\u0006\u0004\b6\u00107JK\u0010A\u001a\u00020\u00102\u0006\u00108\u001a\u00020!2\u0006\u0010:\u001a\u0002092\u0006\u0010<\u001a\u00020;2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020\u00100%2\u0006\u0010?\u001a\u0002042\u0006\u0010@\u001a\u000204H\u0016¢\u0006\u0004\bA\u0010BR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010C\u001a\u0004\bD\u0010ER\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010FR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010GR\u0016\u0010H\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR(\u0010(\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0&\u0012\u0004\u0012\u00020\u00100%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010JR\"\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00100%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010JR$\u0010L\u001a\u00020!2\u0006\u0010K\u001a\u00020!8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010PR\"\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0R0Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u001b\u0010[\u001a\u00020V8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bW\u0010X\u001a\u0004\bY\u0010ZR\u0018\u0010]\u001a\u0004\u0018\u00010\\8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010`\u001a\u00020_8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020\u000e0b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010f\u001a\u0004\u0018\u00010e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bf\u0010g¨\u0006i"}, d2 = {"Landroidx/compose/ui/text/input/TextInputServiceAndroid;", "Landroidx/compose/ui/text/input/PlatformTextInputService;", "Landroid/view/View;", "view", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "rootPositionCalculator", "Landroidx/compose/ui/text/input/InputMethodManager;", "inputMethodManager", "Ljava/util/concurrent/Executor;", "inputCommandProcessorExecutor", "<init>", "(Landroid/view/View;Landroidx/compose/ui/input/pointer/PositionCalculator;Landroidx/compose/ui/text/input/InputMethodManager;Ljava/util/concurrent/Executor;)V", "positionCalculator", "(Landroid/view/View;Landroidx/compose/ui/input/pointer/PositionCalculator;)V", "Landroidx/compose/ui/text/input/TextInputServiceAndroid$TextInputCommand;", "command", "Lx6/t0;", "sendInputCommand", "(Landroidx/compose/ui/text/input/TextInputServiceAndroid$TextInputCommand;)V", "processInputCommands", "()V", "restartInputImmediately", "", "visible", "setKeyboardVisibleImmediately", "(Z)V", "Landroid/view/inputmethod/EditorInfo;", "outAttrs", "Landroid/view/inputmethod/InputConnection;", "createInputConnection", "(Landroid/view/inputmethod/EditorInfo;)Landroid/view/inputmethod/InputConnection;", "isEditorFocused", "()Z", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Landroidx/compose/ui/text/input/ImeOptions;", "imeOptions", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/input/EditCommand;", "onEditCommand", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed", "startInput", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/ImeOptions;Lr7/l;Lr7/l;)V", "stopInput", "showSoftwareKeyboard", "hideSoftwareKeyboard", "oldValue", "newValue", "updateState", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;)V", "Landroidx/compose/ui/geometry/Rect;", "rect", "notifyFocusedRect", "(Landroidx/compose/ui/geometry/Rect;)V", "textFieldValue", "Landroidx/compose/ui/text/input/OffsetMapping;", "offsetMapping", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "Landroidx/compose/ui/graphics/Matrix;", "textFieldToRootTransform", "innerTextFieldBounds", "decorationBoxBounds", "updateTextLayoutResult", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/ui/text/TextLayoutResult;Lr7/l;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;)V", "Landroid/view/View;", "getView", "()Landroid/view/View;", "Landroidx/compose/ui/text/input/InputMethodManager;", "Ljava/util/concurrent/Executor;", "editorHasFocus", "Z", "Lr7/l;", "<set-?>", "state", "Landroidx/compose/ui/text/input/TextFieldValue;", "getState$ui_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "Landroidx/compose/ui/text/input/ImeOptions;", "", "Ljava/lang/ref/WeakReference;", "Landroidx/compose/ui/text/input/RecordingInputConnection;", "ics", "Ljava/util/List;", "Landroid/view/inputmethod/BaseInputConnection;", "baseInputConnection$delegate", "Lx6/s;", "getBaseInputConnection", "()Landroid/view/inputmethod/BaseInputConnection;", "baseInputConnection", "Landroid/graphics/Rect;", "focusedRect", "Landroid/graphics/Rect;", "Landroidx/compose/ui/text/input/CursorAnchorInfoController;", "cursorAnchorInfoController", "Landroidx/compose/ui/text/input/CursorAnchorInfoController;", "Landroidx/compose/runtime/collection/MutableVector;", "textInputCommandQueue", "Landroidx/compose/runtime/collection/MutableVector;", "Ljava/lang/Runnable;", "frameCallback", "Ljava/lang/Runnable;", "TextInputCommand", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextInputServiceAndroid implements PlatformTextInputService {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: baseInputConnection$delegate, reason: from kotlin metadata */
    private final s baseInputConnection;
    private final CursorAnchorInfoController cursorAnchorInfoController;
    private boolean editorHasFocus;
    private Rect focusedRect;
    private Runnable frameCallback;
    private List<WeakReference<RecordingInputConnection>> ics;
    private ImeOptions imeOptions;
    private final Executor inputCommandProcessorExecutor;
    private final InputMethodManager inputMethodManager;
    private l<? super List<? extends EditCommand>, t0> onEditCommand;
    private l<? super ImeAction, t0> onImeActionPerformed;
    private TextFieldValue state;
    private final MutableVector<TextInputCommand> textInputCommandQueue;
    private final View view;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/text/input/TextInputServiceAndroid$TextInputCommand;", "", "(Ljava/lang/String;I)V", "StartInput", "StopInput", "ShowKeyboard", "HideKeyboard", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum TextInputCommand {
        StartInput,
        StopInput,
        ShowKeyboard,
        HideKeyboard
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextInputCommand.values().length];
            try {
                iArr[TextInputCommand.StartInput.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextInputCommand.StopInput.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextInputCommand.ShowKeyboard.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TextInputCommand.HideKeyboard.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.input.TextInputServiceAndroid$stopInput$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "Landroidx/compose/ui/text/input/EditCommand;", "it", "Lx6/t0;", "invoke", "(Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06251 extends r implements l<List<? extends EditCommand>, t0> {
        public static final C06251 INSTANCE = new C06251();

        public C06251() {
            super(1);
        }

        public final void invoke(List<? extends EditCommand> list) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((List<? extends EditCommand>) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.input.TextInputServiceAndroid$stopInput$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/input/ImeAction;", "it", "Lx6/t0;", "invoke-KlQnJC8", "(I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<ImeAction, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m5431invokeKlQnJC8(((ImeAction) obj).getValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
        public final void m5431invokeKlQnJC8(int i10) {
        }
    }

    public TextInputServiceAndroid(View view, PositionCalculator positionCalculator, InputMethodManager inputMethodManager, Executor executor) {
        this.view = view;
        this.inputMethodManager = inputMethodManager;
        this.inputCommandProcessorExecutor = executor;
        this.onEditCommand = TextInputServiceAndroid$onEditCommand$1.INSTANCE;
        this.onImeActionPerformed = TextInputServiceAndroid$onImeActionPerformed$1.INSTANCE;
        this.state = new TextFieldValue("", TextRange.INSTANCE.m5197getZerod9O1mEE(), (TextRange) null, 4, (h) null);
        this.imeOptions = ImeOptions.INSTANCE.getDefault();
        this.ics = new ArrayList();
        this.baseInputConnection = f.o(3, new TextInputServiceAndroid$baseInputConnection$2(this));
        this.cursorAnchorInfoController = new CursorAnchorInfoController(positionCalculator, inputMethodManager);
        this.textInputCommandQueue = new MutableVector<>(new TextInputCommand[16], 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseInputConnection getBaseInputConnection() {
        return (BaseInputConnection) this.baseInputConnection.getValue();
    }

    private final void processInputCommands() {
        k0 k0Var = new k0();
        k0 k0Var2 = new k0();
        MutableVector<TextInputCommand> mutableVector = this.textInputCommandQueue;
        int size = mutableVector.getSize();
        if (size > 0) {
            TextInputCommand[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                processInputCommands$applyToState(content[i10], k0Var, k0Var2);
                i10++;
            } while (i10 < size);
        }
        this.textInputCommandQueue.clear();
        if (p.a(k0Var.f19746i, Boolean.TRUE)) {
            restartInputImmediately();
        }
        Boolean bool = (Boolean) k0Var2.f19746i;
        if (bool != null) {
            setKeyboardVisibleImmediately(bool.booleanValue());
        }
        if (p.a(k0Var.f19746i, Boolean.FALSE)) {
            restartInputImmediately();
        }
    }

    private static final void processInputCommands$applyToState(TextInputCommand textInputCommand, k0 k0Var, k0 k0Var2) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[textInputCommand.ordinal()];
        if (i10 == 1) {
            Boolean bool = Boolean.TRUE;
            k0Var.f19746i = bool;
            k0Var2.f19746i = bool;
        } else if (i10 == 2) {
            Boolean bool2 = Boolean.FALSE;
            k0Var.f19746i = bool2;
            k0Var2.f19746i = bool2;
        } else if ((i10 == 3 || i10 == 4) && !p.a(k0Var.f19746i, Boolean.FALSE)) {
            k0Var2.f19746i = Boolean.valueOf(textInputCommand == TextInputCommand.ShowKeyboard);
        }
    }

    private final void restartInputImmediately() {
        this.inputMethodManager.restartInput();
    }

    private final void sendInputCommand(TextInputCommand command) {
        this.textInputCommandQueue.add(command);
        if (this.frameCallback == null) {
            n nVar = new n(this, 4);
            this.inputCommandProcessorExecutor.execute(nVar);
            this.frameCallback = nVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendInputCommand$lambda$1(TextInputServiceAndroid textInputServiceAndroid) {
        textInputServiceAndroid.frameCallback = null;
        textInputServiceAndroid.processInputCommands();
    }

    private final void setKeyboardVisibleImmediately(boolean visible) {
        if (visible) {
            this.inputMethodManager.showSoftInput();
        } else {
            this.inputMethodManager.hideSoftInput();
        }
    }

    public final InputConnection createInputConnection(EditorInfo outAttrs) {
        if (!this.editorHasFocus) {
            return null;
        }
        TextInputServiceAndroid_androidKt.update(outAttrs, this.imeOptions, this.state);
        TextInputServiceAndroid_androidKt.updateWithEmojiCompat(outAttrs);
        RecordingInputConnection recordingInputConnection = new RecordingInputConnection(this.state, new InputEventCallback2() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid.createInputConnection.1
            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onConnectionClosed(RecordingInputConnection inputConnection) {
                int size = TextInputServiceAndroid.this.ics.size();
                for (int i10 = 0; i10 < size; i10++) {
                    if (p.a(((WeakReference) TextInputServiceAndroid.this.ics.get(i10)).get(), inputConnection)) {
                        TextInputServiceAndroid.this.ics.remove(i10);
                        return;
                    }
                }
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onEditCommands(List<? extends EditCommand> editCommands) {
                TextInputServiceAndroid.this.onEditCommand.invoke(editCommands);
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            /* JADX INFO: renamed from: onImeAction-KlQnJC8 */
            public void mo5383onImeActionKlQnJC8(int imeAction) {
                TextInputServiceAndroid.this.onImeActionPerformed.invoke(ImeAction.m5352boximpl(imeAction));
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onKeyEvent(KeyEvent event) {
                TextInputServiceAndroid.this.getBaseInputConnection().sendKeyEvent(event);
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onRequestCursorAnchorInfo(boolean immediate, boolean monitor, boolean includeInsertionMarker, boolean includeCharacterBounds, boolean includeEditorBounds, boolean includeLineBounds) {
                TextInputServiceAndroid.this.cursorAnchorInfoController.requestUpdate(immediate, monitor, includeInsertionMarker, includeCharacterBounds, includeEditorBounds, includeLineBounds);
            }
        }, this.imeOptions.getAutoCorrect());
        this.ics.add(new WeakReference<>(recordingInputConnection));
        return recordingInputConnection;
    }

    /* JADX INFO: renamed from: getState$ui_release, reason: from getter */
    public final TextFieldValue getState() {
        return this.state;
    }

    public final View getView() {
        return this.view;
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void hideSoftwareKeyboard() {
        sendInputCommand(TextInputCommand.HideKeyboard);
    }

    /* JADX INFO: renamed from: isEditorFocused, reason: from getter */
    public final boolean getEditorHasFocus() {
        return this.editorHasFocus;
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    @e
    public void notifyFocusedRect(androidx.compose.ui.geometry.Rect rect) {
        Rect rect2;
        this.focusedRect = new Rect(t7.a.M(rect.getLeft()), t7.a.M(rect.getTop()), t7.a.M(rect.getRight()), t7.a.M(rect.getBottom()));
        if (!this.ics.isEmpty() || (rect2 = this.focusedRect) == null) {
            return;
        }
        this.view.requestRectangleOnScreen(new Rect(rect2));
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void showSoftwareKeyboard() {
        sendInputCommand(TextInputCommand.ShowKeyboard);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void startInput(TextFieldValue value, ImeOptions imeOptions, l<? super List<? extends EditCommand>, t0> onEditCommand, l<? super ImeAction, t0> onImeActionPerformed) {
        this.editorHasFocus = true;
        this.state = value;
        this.imeOptions = imeOptions;
        this.onEditCommand = onEditCommand;
        this.onImeActionPerformed = onImeActionPerformed;
        sendInputCommand(TextInputCommand.StartInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void stopInput() {
        this.editorHasFocus = false;
        this.onEditCommand = C06251.INSTANCE;
        this.onImeActionPerformed = AnonymousClass2.INSTANCE;
        this.focusedRect = null;
        sendInputCommand(TextInputCommand.StopInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void updateState(TextFieldValue oldValue, TextFieldValue newValue) {
        boolean z = (TextRange.m5185equalsimpl0(this.state.getSelection(), newValue.getSelection()) && p.a(this.state.getComposition(), newValue.getComposition())) ? false : true;
        this.state = newValue;
        int size = this.ics.size();
        for (int i10 = 0; i10 < size; i10++) {
            RecordingInputConnection recordingInputConnection = this.ics.get(i10).get();
            if (recordingInputConnection != null) {
                recordingInputConnection.setMTextFieldValue$ui_release(newValue);
            }
        }
        this.cursorAnchorInfoController.invalidate();
        if (p.a(oldValue, newValue)) {
            if (z) {
                InputMethodManager inputMethodManager = this.inputMethodManager;
                int iM5190getMinimpl = TextRange.m5190getMinimpl(newValue.getSelection());
                int iM5189getMaximpl = TextRange.m5189getMaximpl(newValue.getSelection());
                TextRange textRangeM5428getCompositionMzsxiRA = this.state.getComposition();
                int iM5190getMinimpl2 = textRangeM5428getCompositionMzsxiRA != null ? TextRange.m5190getMinimpl(textRangeM5428getCompositionMzsxiRA.getPackedValue()) : -1;
                TextRange textRangeM5428getCompositionMzsxiRA2 = this.state.getComposition();
                inputMethodManager.updateSelection(iM5190getMinimpl, iM5189getMaximpl, iM5190getMinimpl2, textRangeM5428getCompositionMzsxiRA2 != null ? TextRange.m5189getMaximpl(textRangeM5428getCompositionMzsxiRA2.getPackedValue()) : -1);
                return;
            }
            return;
        }
        if (oldValue != null && (!p.a(oldValue.getText(), newValue.getText()) || (TextRange.m5185equalsimpl0(oldValue.getSelection(), newValue.getSelection()) && !p.a(oldValue.getComposition(), newValue.getComposition())))) {
            restartInputImmediately();
            return;
        }
        int size2 = this.ics.size();
        for (int i11 = 0; i11 < size2; i11++) {
            RecordingInputConnection recordingInputConnection2 = this.ics.get(i11).get();
            if (recordingInputConnection2 != null) {
                recordingInputConnection2.updateInputState(this.state, this.inputMethodManager);
            }
        }
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void updateTextLayoutResult(TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, l<? super Matrix, t0> textFieldToRootTransform, androidx.compose.ui.geometry.Rect innerTextFieldBounds, androidx.compose.ui.geometry.Rect decorationBoxBounds) {
        this.cursorAnchorInfoController.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResult, textFieldToRootTransform, innerTextFieldBounds, decorationBoxBounds);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void startInput() {
        sendInputCommand(TextInputCommand.StartInput);
    }

    public /* synthetic */ TextInputServiceAndroid(View view, PositionCalculator positionCalculator, InputMethodManager inputMethodManager, Executor executor, int i10, h hVar) {
        this(view, positionCalculator, inputMethodManager, (i10 & 8) != 0 ? TextInputServiceAndroid_androidKt.asExecutor(Choreographer.getInstance()) : executor);
    }

    public TextInputServiceAndroid(View view, PositionCalculator positionCalculator) {
        this(view, positionCalculator, new InputMethodManagerImpl(view), null, 8, null);
    }
}
