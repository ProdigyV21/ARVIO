package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.translation.ViewTranslationCallback;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AndroidAutofill_androidKt;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.draganddrop.ComposeDragShadowBuilder;
import androidx.compose.ui.draganddrop.DragAndDropManager;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusTransactionManager;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PointerInputEventProcessorKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.input.pointer.ProcessResult;
import androidx.compose.ui.input.rotary.RotaryInputModifierKt;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.platform.ViewLayer;
import androidx.compose.ui.semantics.EmptySemanticsElement;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.PlatformTextInputService;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.view.b2;
import androidx.core.view.c2;
import androidx.core.view.f2;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.y;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.q0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000þ\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0002ð\u0003\b\u0000\u0018\u0000 \u009e\u00042\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0006\u009f\u0004\u009e\u0004 \u0004B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ9\u0010\u0013\u001a\u00020\u000f2'\u0010\u0012\u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0096@¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ)\u0010#\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010\u0015H\u0014¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u001eH\u0016¢\u0006\u0004\b&\u0010'J\u001a\u0010,\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020(H\u0016ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u0017\u0010/\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b/\u0010+J\u0017\u00100\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b0\u0010+J\u0017\u00103\u001a\u00020\u00172\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u00020\u00172\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b5\u00104J\r\u00106\u001a\u00020\u0017¢\u0006\u0004\b6\u00107J\u000f\u00108\u001a\u00020\u0017H\u0016¢\u0006\u0004\b8\u00107J\u001d\u0010;\u001a\u00020\u00172\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001709H\u0016¢\u0006\u0004\b;\u0010<J\u001d\u0010@\u001a\u00020\u00172\u0006\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u000201¢\u0006\u0004\b@\u0010AJ\u0015\u0010B\u001a\u00020\u00172\u0006\u0010>\u001a\u00020=¢\u0006\u0004\bB\u0010CJ\u001d\u0010F\u001a\u00020\u00172\u0006\u0010>\u001a\u00020=2\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bF\u0010GJ\u0017\u0010I\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u001eH\u0016¢\u0006\u0004\bI\u0010'J\"\u0010I\u001a\u00020\u00172\u0006\u0010?\u001a\u0002012\u0006\u0010K\u001a\u00020JH\u0016ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ\u001f\u0010O\u001a\u00020\u00172\u0006\u0010?\u001a\u0002012\u0006\u0010N\u001a\u00020\u001eH\u0016¢\u0006\u0004\bO\u0010PJ/\u0010S\u001a\u00020\u00172\u0006\u0010?\u001a\u0002012\u0006\u0010N\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020\u001eH\u0016¢\u0006\u0004\bS\u0010TJ'\u0010U\u001a\u00020\u00172\u0006\u0010?\u001a\u0002012\u0006\u0010N\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020\u001eH\u0016¢\u0006\u0004\bU\u0010VJ\u0017\u0010W\u001a\u00020\u00172\u0006\u0010?\u001a\u000201H\u0016¢\u0006\u0004\bW\u00104J\u000f\u0010X\u001a\u00020\u0017H\u0016¢\u0006\u0004\bX\u00107J\u001f\u0010[\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020 2\u0006\u0010Z\u001a\u00020 H\u0014¢\u0006\u0004\b[\u0010\\J7\u0010b\u001a\u00020\u00172\u0006\u0010]\u001a\u00020\u001e2\u0006\u0010^\u001a\u00020 2\u0006\u0010_\u001a\u00020 2\u0006\u0010`\u001a\u00020 2\u0006\u0010a\u001a\u00020 H\u0014¢\u0006\u0004\bb\u0010cJ\u0017\u0010d\u001a\u00020\u00172\u0006\u0010E\u001a\u00020DH\u0014¢\u0006\u0004\bd\u0010eJ1\u0010k\u001a\u00020j2\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u00020g\u0012\u0004\u0012\u00020\u00170f2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u001709H\u0016¢\u0006\u0004\bk\u0010lJ\u0017\u0010p\u001a\u00020\u001e2\u0006\u0010m\u001a\u00020jH\u0000¢\u0006\u0004\bn\u0010oJ\u000f\u0010q\u001a\u00020\u0017H\u0016¢\u0006\u0004\bq\u00107J\u0017\u0010r\u001a\u00020\u00172\u0006\u0010?\u001a\u000201H\u0016¢\u0006\u0004\br\u00104J\u0017\u0010t\u001a\u00020\u00172\u0006\u0010:\u001a\u00020sH\u0016¢\u0006\u0004\bt\u0010uJ\u001c\u0010y\u001a\u0004\u0018\u00010v2\u0006\u0010)\u001a\u00020(H\u0016ø\u0001\u0000¢\u0006\u0004\bw\u0010xJ\u0017\u0010z\u001a\u00020\u00172\u0006\u0010E\u001a\u00020DH\u0014¢\u0006\u0004\bz\u0010eJ\u001f\u0010~\u001a\u00020\u00172\u0006\u0010m\u001a\u00020j2\u0006\u0010{\u001a\u00020\u001eH\u0000¢\u0006\u0004\b|\u0010}J%\u0010\u0081\u0001\u001a\u00020\u00172\u0013\u0010\u0080\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u007f\u0012\u0004\u0012\u00020\u00170f¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u0013\u0010\u0083\u0001\u001a\u00020\u0017H\u0086@¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u0011\u0010\u0085\u0001\u001a\u00020\u0017H\u0016¢\u0006\u0005\b\u0085\u0001\u00107J\u0011\u0010\u0086\u0001\u001a\u00020\u0017H\u0014¢\u0006\u0005\b\u0086\u0001\u00107J\u0011\u0010\u0087\u0001\u001a\u00020\u0017H\u0014¢\u0006\u0005\b\u0087\u0001\u00107J'\u0010\u008b\u0001\u001a\u00020\u00172\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0088\u00012\u0007\u0010\u008a\u0001\u001a\u00020 H\u0016¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J#\u0010\u0090\u0001\u001a\u00020\u00172\u000f\u0010\u008f\u0001\u001a\n\u0012\u0005\u0012\u00030\u008e\u00010\u008d\u0001H\u0016¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J9\u0010\u0099\u0001\u001a\u00020\u00172\b\u0010\u0093\u0001\u001a\u00030\u0092\u00012\b\u0010\u0095\u0001\u001a\u00030\u0094\u00012\u0011\u0010\u0098\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010\u0097\u00010\u0096\u0001H\u0017¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J%\u0010\u009e\u0001\u001a\u00020\u00172\u0011\u0010\u009d\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010\u009c\u00010\u009b\u0001H\u0017¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J\u001b\u0010¡\u0001\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u001c\u0010¤\u0001\u001a\u00020\u001e2\b\u0010£\u0001\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¤\u0001\u0010¢\u0001J\u001a\u0010¥\u0001\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0006\b¥\u0001\u0010¦\u0001J\u001a\u0010§\u0001\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0006\b§\u0001\u0010¦\u0001J \u0010¬\u0001\u001a\u00030¨\u00012\b\u0010©\u0001\u001a\u00030¨\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\bª\u0001\u0010«\u0001J\u001f\u0010¬\u0001\u001a\u00020\u00172\b\u0010®\u0001\u001a\u00030\u00ad\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b¯\u0001\u0010°\u0001J \u0010³\u0001\u001a\u00030¨\u00012\b\u0010±\u0001\u001a\u00030¨\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b²\u0001\u0010«\u0001J\u0012\u0010´\u0001\u001a\u00020\u001eH\u0016¢\u0006\u0006\b´\u0001\u0010µ\u0001J\u001f\u0010¹\u0001\u001a\u0005\u0018\u00010¸\u00012\b\u0010·\u0001\u001a\u00030¶\u0001H\u0016¢\u0006\u0006\b¹\u0001\u0010º\u0001J \u0010½\u0001\u001a\u00030¨\u00012\b\u0010»\u0001\u001a\u00030¨\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b¼\u0001\u0010«\u0001J \u0010¿\u0001\u001a\u00030¨\u00012\b\u0010©\u0001\u001a\u00030¨\u0001H\u0016ø\u0001\u0000¢\u0006\u0006\b¾\u0001\u0010«\u0001J\u001c\u0010Â\u0001\u001a\u00020\u00172\b\u0010Á\u0001\u001a\u00030À\u0001H\u0014¢\u0006\u0006\bÂ\u0001\u0010Ã\u0001J\u001b\u0010Å\u0001\u001a\u00020\u00172\u0007\u0010Ä\u0001\u001a\u00020 H\u0016¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001J\u001b\u0010Ç\u0001\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u0001H\u0016¢\u0006\u0006\bÇ\u0001\u0010¢\u0001J\u001c\u0010Ê\u0001\u001a\u0005\u0018\u00010É\u00012\u0007\u0010È\u0001\u001a\u00020 ¢\u0006\u0006\bÊ\u0001\u0010Ë\u0001J\u0012\u0010Ì\u0001\u001a\u00020\u001eH\u0016¢\u0006\u0006\bÌ\u0001\u0010µ\u0001JD\u0010Õ\u0001\u001a\u00020\u001e2\b\u0010Î\u0001\u001a\u00030Í\u00012\b\u0010Ð\u0001\u001a\u00030Ï\u00012\u0019\u0010Ò\u0001\u001a\u0014\u0012\u0005\u0012\u00030Ñ\u0001\u0012\u0004\u0012\u00020\u00170f¢\u0006\u0002\b\u0011H\u0002ø\u0001\u0000¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J\u001b\u0010×\u0001\u001a\u00020\u00172\u0007\u0010Ö\u0001\u001a\u00020\u0001H\u0002¢\u0006\u0006\b×\u0001\u0010Ø\u0001J/\u0010Þ\u0001\u001a\u00020\u00172\u0007\u0010Ù\u0001\u001a\u00020 2\b\u0010Û\u0001\u001a\u00030Ú\u00012\b\u0010Ý\u0001\u001a\u00030Ü\u0001H\u0002¢\u0006\u0006\bÞ\u0001\u0010ß\u0001J\u001c\u0010R\u001a\u00020\u00172\u000b\b\u0002\u0010à\u0001\u001a\u0004\u0018\u000101H\u0002¢\u0006\u0004\bR\u00104J\u0016\u0010á\u0001\u001a\u00020\u001e*\u000201H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001J\u001b\u0010æ\u0001\u001a\u00020 *\u00030ã\u0001H\u0082\nø\u0001\u0000¢\u0006\u0006\bä\u0001\u0010å\u0001J\u001b\u0010è\u0001\u001a\u00020 *\u00030ã\u0001H\u0082\nø\u0001\u0000¢\u0006\u0006\bç\u0001\u0010å\u0001J*\u0010ì\u0001\u001a\u00030ã\u00012\u0007\u0010é\u0001\u001a\u00020 2\u0006\u0010a\u001a\u00020 H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bê\u0001\u0010ë\u0001J\"\u0010ð\u0001\u001a\u00030ã\u00012\u0007\u0010í\u0001\u001a\u00020 H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bî\u0001\u0010ï\u0001J\u0011\u0010ñ\u0001\u001a\u00020\u0017H\u0002¢\u0006\u0005\bñ\u0001\u00107J\u0019\u0010ò\u0001\u001a\u00020\u00172\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0005\bò\u0001\u00104J\u0019\u0010ó\u0001\u001a\u00020\u00172\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0005\bó\u0001\u00104J\u001b\u0010ô\u0001\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u0001H\u0002¢\u0006\u0006\bô\u0001\u0010¢\u0001J#\u0010ø\u0001\u001a\u00030õ\u00012\b\u0010£\u0001\u001a\u00030 \u0001H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bö\u0001\u0010÷\u0001J%\u0010ú\u0001\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u00012\b\u0010ù\u0001\u001a\u00030 \u0001H\u0002¢\u0006\u0006\bú\u0001\u0010û\u0001J\u001b\u0010ü\u0001\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u0001H\u0002¢\u0006\u0006\bü\u0001\u0010¢\u0001J#\u0010þ\u0001\u001a\u00030õ\u00012\b\u0010£\u0001\u001a\u00030 \u0001H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bý\u0001\u0010÷\u0001J:\u0010\u0083\u0002\u001a\u00020\u00172\b\u0010£\u0001\u001a\u00030 \u00012\u0007\u0010ÿ\u0001\u001a\u00020 2\b\u0010\u0081\u0002\u001a\u00030\u0080\u00022\t\b\u0002\u0010\u0082\u0002\u001a\u00020\u001eH\u0002¢\u0006\u0006\b\u0083\u0002\u0010\u0084\u0002J\u001c\u0010\u0085\u0002\u001a\u00020\u001e2\b\u0010£\u0001\u001a\u00030 \u0001H\u0002¢\u0006\u0006\b\u0085\u0002\u0010¢\u0001J\u0011\u0010\u0086\u0002\u001a\u00020\u0017H\u0002¢\u0006\u0005\b\u0086\u0002\u00107J\u001c\u0010\u0086\u0002\u001a\u00020\u00172\b\u0010£\u0001\u001a\u00030 \u0001H\u0002¢\u0006\u0006\b\u0086\u0002\u0010\u0087\u0002J\u0011\u0010\u0088\u0002\u001a\u00020\u0017H\u0002¢\u0006\u0005\b\u0088\u0002\u00107J\u0012\u0010\u0089\u0002\u001a\u00020\u001eH\u0002¢\u0006\u0006\b\u0089\u0002\u0010µ\u0001J\u001b\u0010\u008a\u0002\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u0001H\u0002¢\u0006\u0006\b\u008a\u0002\u0010¢\u0001J\u001b\u0010\u008b\u0002\u001a\u00020\u001e2\u0007\u0010.\u001a\u00030 \u0001H\u0002¢\u0006\u0006\b\u008b\u0002\u0010¢\u0001J(\u0010\u008d\u0002\u001a\u0005\u0018\u00010É\u00012\u0007\u0010È\u0001\u001a\u00020 2\b\u0010\u008c\u0002\u001a\u00030É\u0001H\u0002¢\u0006\u0006\b\u008d\u0002\u0010\u008e\u0002R\u001d\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\t\u0010\u008f\u0002\u001a\u0006\b\u0090\u0002\u0010\u0091\u0002R \u0010\u0092\u0002\u001a\u00030¨\u00018\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u0092\u0002\u0010\u0093\u0002R\u0019\u0010\u0094\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0094\u0002\u0010\u0095\u0002R \u0010\u0097\u0002\u001a\u00030\u0096\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0097\u0002\u0010\u0098\u0002\u001a\u0006\b\u0099\u0002\u0010\u009a\u0002R,\u0010\u009d\u0002\u001a\u00030\u009b\u00022\b\u0010\u009c\u0002\u001a\u00030\u009b\u00028\u0016@RX\u0096\u000e¢\u0006\u0010\n\u0006\b\u009d\u0002\u0010\u009e\u0002\u001a\u0006\b\u009f\u0002\u0010 \u0002R\u0018\u0010¢\u0002\u001a\u00030¡\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0002\u0010£\u0002R \u0010¥\u0002\u001a\u00030¤\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b¥\u0002\u0010¦\u0002\u001a\u0006\b§\u0002\u0010¨\u0002R\u0018\u0010ª\u0002\u001a\u00030©\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0002\u0010«\u0002R \u0010\u00ad\u0002\u001a\u00030¬\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u00ad\u0002\u0010®\u0002\u001a\u0006\b¯\u0002\u0010°\u0002R\u0018\u0010²\u0002\u001a\u00030±\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b²\u0002\u0010³\u0002R\u0018\u0010µ\u0002\u001a\u00030´\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bµ\u0002\u0010¶\u0002R\u0018\u0010·\u0002\u001a\u00030´\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b·\u0002\u0010¶\u0002R\u0018\u0010¹\u0002\u001a\u00030¸\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¹\u0002\u0010º\u0002R\u001f\u0010»\u0002\u001a\u0002018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b»\u0002\u0010¼\u0002\u001a\u0006\b½\u0002\u0010¾\u0002R \u0010À\u0002\u001a\u00030¿\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÀ\u0002\u0010Á\u0002\u001a\u0006\bÂ\u0002\u0010Ã\u0002R \u0010Å\u0002\u001a\u00030Ä\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÅ\u0002\u0010Æ\u0002\u001a\u0006\bÇ\u0002\u0010È\u0002R\u0018\u0010Ê\u0002\u001a\u00030É\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÊ\u0002\u0010Ë\u0002R \u0010Í\u0002\u001a\u00030Ì\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÍ\u0002\u0010Î\u0002\u001a\u0006\bÏ\u0002\u0010Ð\u0002R\u001e\u0010Ò\u0002\u001a\t\u0012\u0004\u0012\u00020j0Ñ\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÒ\u0002\u0010Ó\u0002R\"\u0010Ô\u0002\u001a\u000b\u0012\u0004\u0012\u00020j\u0018\u00010Ñ\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÔ\u0002\u0010Ó\u0002R\u0019\u0010Õ\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÕ\u0002\u0010\u0095\u0002R\u0018\u0010×\u0002\u001a\u00030Ö\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b×\u0002\u0010Ø\u0002R\u0018\u0010Ú\u0002\u001a\u00030Ù\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÚ\u0002\u0010Û\u0002R6\u0010Ü\u0002\u001a\u000f\u0012\u0005\u0012\u00030À\u0001\u0012\u0004\u0012\u00020\u00170f8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bÜ\u0002\u0010Ý\u0002\u001a\u0006\bÞ\u0002\u0010ß\u0002\"\u0006\bà\u0002\u0010\u0082\u0001R\u001a\u0010â\u0002\u001a\u0005\u0018\u00010á\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bâ\u0002\u0010ã\u0002R\u0019\u0010ä\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bä\u0002\u0010\u0095\u0002R \u0010æ\u0002\u001a\u00030å\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bæ\u0002\u0010ç\u0002\u001a\u0006\bè\u0002\u0010é\u0002R \u0010ë\u0002\u001a\u00030ê\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bë\u0002\u0010ì\u0002\u001a\u0006\bí\u0002\u0010î\u0002R \u0010ð\u0002\u001a\u00030ï\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bð\u0002\u0010ñ\u0002\u001a\u0006\bò\u0002\u0010ó\u0002R/\u0010ô\u0002\u001a\u00020\u001e8\u0016@\u0016X\u0096\u000e¢\u0006\u001e\n\u0006\bô\u0002\u0010\u0095\u0002\u0012\u0005\b÷\u0002\u00107\u001a\u0006\bõ\u0002\u0010µ\u0001\"\u0005\bö\u0002\u0010'R\u001c\u0010ù\u0002\u001a\u0005\u0018\u00010ø\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bù\u0002\u0010ú\u0002R\u001c\u0010ü\u0002\u001a\u0005\u0018\u00010û\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bü\u0002\u0010ý\u0002R!\u0010þ\u0002\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\bþ\u0002\u0010ÿ\u0002R\u0019\u0010\u0080\u0003\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0003\u0010\u0095\u0002R\u0018\u0010\u0082\u0003\u001a\u00030\u0081\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0003\u0010\u0083\u0003R \u0010\u0085\u0003\u001a\u00030\u0084\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0085\u0003\u0010\u0086\u0003\u001a\u0006\b\u0087\u0003\u0010\u0088\u0003R \u0010\u008a\u0003\u001a\u00030\u0089\u00038\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u008a\u0003\u0010\u0093\u0002R\u0018\u0010\u008b\u0003\u001a\u00030\u0094\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008b\u0003\u0010\u008c\u0003R\u001e\u0010\u008d\u0003\u001a\u00030\u00ad\u00018\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u008d\u0003\u0010\u008e\u0003R\u001e\u0010\u008f\u0003\u001a\u00030\u00ad\u00018\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u008f\u0003\u0010\u008e\u0003R\u001e\u0010\u0090\u0003\u001a\u00030\u00ad\u00018\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u0090\u0003\u0010\u008e\u0003R1\u0010\u0091\u0003\u001a\u00030\u0080\u00028\u0000@\u0000X\u0081\u000e¢\u0006\u001f\n\u0006\b\u0091\u0003\u0010\u0093\u0002\u0012\u0005\b\u0096\u0003\u00107\u001a\u0006\b\u0092\u0003\u0010\u0093\u0003\"\u0006\b\u0094\u0003\u0010\u0095\u0003R\u0019\u0010\u0097\u0003\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0097\u0003\u0010\u0095\u0002R \u0010\u0098\u0003\u001a\u00030¨\u00018\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u0098\u0003\u0010\u0093\u0002R\u0019\u0010\u0099\u0003\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0099\u0003\u0010\u0095\u0002R7\u0010 \u0003\u001a\u0004\u0018\u00010\u007f2\t\u0010\u009c\u0002\u001a\u0004\u0018\u00010\u007f8B@BX\u0082\u008e\u0002¢\u0006\u0018\n\u0006\b\u009a\u0003\u0010\u009b\u0003\u001a\u0006\b\u009c\u0003\u0010\u009d\u0003\"\u0006\b\u009e\u0003\u0010\u009f\u0003R\"\u0010¤\u0003\u001a\u0004\u0018\u00010\u007f8FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b¡\u0003\u0010¢\u0003\u001a\u0006\b£\u0003\u0010\u009d\u0003R'\u0010¥\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u007f\u0012\u0004\u0012\u00020\u0017\u0018\u00010f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¥\u0003\u0010Ý\u0002R\u0018\u0010§\u0003\u001a\u00030¦\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b§\u0003\u0010¨\u0003R\u0018\u0010ª\u0003\u001a\u00030©\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0003\u0010«\u0003R\u0018\u0010\u00ad\u0003\u001a\u00030¬\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0003\u0010®\u0003R\u0018\u0010°\u0003\u001a\u00030¯\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0003\u0010±\u0003R \u0010³\u0003\u001a\u00030²\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b³\u0003\u0010´\u0003\u001a\u0006\bµ\u0003\u0010¶\u0003R%\u0010¹\u0003\u001a\n\u0012\u0005\u0012\u00030¸\u00030·\u00038\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b¹\u0003\u0010º\u0003R \u0010¼\u0003\u001a\u00030»\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b¼\u0003\u0010½\u0003\u001a\u0006\b¾\u0003\u0010¿\u0003R'\u0010Á\u0003\u001a\u00030À\u00038\u0016X\u0097\u0004¢\u0006\u0017\n\u0006\bÁ\u0003\u0010Â\u0003\u0012\u0005\bÅ\u0003\u00107\u001a\u0006\bÃ\u0003\u0010Ä\u0003R5\u0010Ì\u0003\u001a\u00030Æ\u00032\b\u0010\u009c\u0002\u001a\u00030Æ\u00038V@RX\u0096\u008e\u0002¢\u0006\u0018\n\u0006\bÇ\u0003\u0010\u009b\u0003\u001a\u0006\bÈ\u0003\u0010É\u0003\"\u0006\bÊ\u0003\u0010Ë\u0003R\u0019\u0010Í\u0003\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÍ\u0003\u0010Î\u0003R5\u0010Ä\u0001\u001a\u00030Ï\u00032\b\u0010\u009c\u0002\u001a\u00030Ï\u00038V@RX\u0096\u008e\u0002¢\u0006\u0018\n\u0006\bÐ\u0003\u0010\u009b\u0003\u001a\u0006\bÑ\u0003\u0010Ò\u0003\"\u0006\bÓ\u0003\u0010Ô\u0003R \u0010Ö\u0003\u001a\u00030Õ\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÖ\u0003\u0010×\u0003\u001a\u0006\bØ\u0003\u0010Ù\u0003R\u0018\u0010Û\u0003\u001a\u00030Ú\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÛ\u0003\u0010Ü\u0003R \u0010Þ\u0003\u001a\u00030Ý\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÞ\u0003\u0010ß\u0003\u001a\u0006\bà\u0003\u0010á\u0003R \u0010ã\u0003\u001a\u00030â\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bã\u0003\u0010ä\u0003\u001a\u0006\bå\u0003\u0010æ\u0003R\u001c\u0010ç\u0003\u001a\u0005\u0018\u00010 \u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bç\u0003\u0010è\u0003R\u001a\u0010é\u0003\u001a\u00030\u0080\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bé\u0003\u0010\u0093\u0002R\u001e\u0010ë\u0003\u001a\t\u0012\u0004\u0012\u00020j0ê\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bë\u0003\u0010ì\u0003R&\u0010î\u0003\u001a\u0011\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0017\u0018\u0001090í\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bî\u0003\u0010ï\u0003R\u0018\u0010ñ\u0003\u001a\u00030ð\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bñ\u0003\u0010ò\u0003R\u0018\u0010ô\u0003\u001a\u00030ó\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bô\u0003\u0010õ\u0003R\u0019\u0010ö\u0003\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bö\u0003\u0010\u0095\u0002R\u001d\u0010÷\u0003\u001a\b\u0012\u0004\u0012\u00020\u0017098\u0002X\u0082\u0004¢\u0006\b\n\u0006\b÷\u0003\u0010ø\u0003R\u0018\u0010ú\u0003\u001a\u00030ù\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bú\u0003\u0010û\u0003R\u0019\u0010ü\u0003\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bü\u0003\u0010\u0095\u0002R \u0010þ\u0003\u001a\u00030ý\u00038\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bþ\u0003\u0010ÿ\u0003\u001a\u0006\b\u0080\u0004\u0010\u0081\u0004R\u0017\u0010>\u001a\u00030É\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0082\u0004\u0010\u0083\u0004R\u0018\u0010\u0087\u0004\u001a\u00030\u0084\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0085\u0004\u0010\u0086\u0004R\u001a\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0088\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0089\u0004\u0010\u008a\u0004R\u0018\u0010\u008d\u0004\u001a\u00030ø\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u008b\u0004\u0010\u008c\u0004R\u0018\u0010\u008f\u0004\u001a\u00030\u0080\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008e\u0004\u0010\u0093\u0003R\u0017\u0010\u0091\u0004\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0090\u0004\u0010µ\u0001R\u0018\u0010\u0095\u0004\u001a\u00030\u0092\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0093\u0004\u0010\u0094\u0004R\u0018\u0010\u0099\u0004\u001a\u00030\u0096\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0097\u0004\u0010\u0098\u0004R\u0017\u0010\u009a\u0004\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009a\u0004\u0010µ\u0001R\u001c\u0010\u009d\u0004\u001a\u00020 *\u00030À\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u009b\u0004\u0010\u009c\u0004\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006¡\u0004"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView;", "Landroid/view/ViewGroup;", "Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/platform/ViewRootForTest;", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/content/Context;", "context", "Ld7/j;", "coroutineContext", "<init>", "(Landroid/content/Context;Ld7/j;)V", "Lkotlin/Function2;", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "Ld7/d;", "", "", "Lx6/n;", SettingsSessionManager.SETTINGS_KEY, "textInputSession", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroid/graphics/Rect;", "rect", "Lx6/t0;", "getFocusedRect", "(Landroid/graphics/Rect;)V", "Landroidx/lifecycle/y;", "owner", "onResume", "(Landroidx/lifecycle/y;)V", "", "gainFocus", "", "direction", "previouslyFocusedRect", "onFocusChanged", "(ZILandroid/graphics/Rect;)V", "hasWindowFocus", "onWindowFocusChanged", "(Z)V", "Landroidx/compose/ui/input/key/KeyEvent;", "keyEvent", "sendKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "sendKeyEvent", "Landroid/view/KeyEvent;", "event", "dispatchKeyEvent", "dispatchKeyEventPreIme", "Landroidx/compose/ui/node/LayoutNode;", "node", "onAttach", "(Landroidx/compose/ui/node/LayoutNode;)V", "onDetach", "requestClearInvalidObservations", "()V", "onEndApplyChanges", "Lkotlin/Function0;", "listener", "registerOnEndApplyChangesListener", "(Lr7/a;)V", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "view", "layoutNode", "addAndroidView", "(Landroidx/compose/ui/viewinterop/AndroidViewHolder;Landroidx/compose/ui/node/LayoutNode;)V", "removeAndroidView", "(Landroidx/compose/ui/viewinterop/AndroidViewHolder;)V", "Landroid/graphics/Canvas;", "canvas", "drawAndroidView", "(Landroidx/compose/ui/viewinterop/AndroidViewHolder;Landroid/graphics/Canvas;)V", "sendPointerUpdate", "measureAndLayout", "Landroidx/compose/ui/unit/Constraints;", "constraints", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "affectsLookahead", "forceMeasureTheSubtree", "(Landroidx/compose/ui/node/LayoutNode;Z)V", "forceRequest", "scheduleMeasureAndLayout", "onRequestMeasure", "(Landroidx/compose/ui/node/LayoutNode;ZZZ)V", "onRequestRelayout", "(Landroidx/compose/ui/node/LayoutNode;ZZ)V", "requestOnPositionedCallback", "measureAndLayoutForTest", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "changed", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "onLayout", "(ZIIII)V", "onDraw", "(Landroid/graphics/Canvas;)V", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "drawBlock", "invalidateParentLayer", "Landroidx/compose/ui/node/OwnedLayer;", "createLayer", "(Lr7/l;Lr7/a;)Landroidx/compose/ui/node/OwnedLayer;", "layer", "recycle$ui_release", "(Landroidx/compose/ui/node/OwnedLayer;)Z", "recycle", "onSemanticsChange", "onLayoutChange", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "registerOnLayoutCompletedListener", "(Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;)V", "Landroidx/compose/ui/focus/FocusDirection;", "getFocusDirection-P8AzH3I", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/focus/FocusDirection;", "getFocusDirection", "dispatchDraw", "isDirty", "notifyLayerIsDirty$ui_release", "(Landroidx/compose/ui/node/OwnedLayer;Z)V", "notifyLayerIsDirty", "Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "callback", "setOnViewTreeOwnersAvailable", "(Lr7/l;)V", "boundsUpdatesEventLoop", "(Ld7/d;)Ljava/lang/Object;", "invalidateDescendants", "onAttachedToWindow", "onDetachedFromWindow", "Landroid/view/ViewStructure;", "structure", "flags", "onProvideAutofillVirtualStructure", "(Landroid/view/ViewStructure;I)V", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "values", "autofill", "(Landroid/util/SparseArray;)V", "", "virtualIds", "", "supportedFormats", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "requestsCollector", "onCreateVirtualViewTranslationRequests", "([J[ILjava/util/function/Consumer;)V", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "response", "onVirtualViewTranslationResponses", "(Landroid/util/LongSparseArray;)V", "Landroid/view/MotionEvent;", "dispatchGenericMotionEvent", "(Landroid/view/MotionEvent;)Z", "motionEvent", "dispatchTouchEvent", "canScrollHorizontally", "(I)Z", "canScrollVertically", "Landroidx/compose/ui/geometry/Offset;", "localPosition", "localToScreen-MK-Hz9U", "(J)J", "localToScreen", "Landroidx/compose/ui/graphics/Matrix;", "localTransform", "localToScreen-58bKbWc", "([F)V", "positionOnScreen", "screenToLocal-MK-Hz9U", "screenToLocal", "onCheckIsTextEditor", "()Z", "Landroid/view/inputmethod/EditorInfo;", "outAttrs", "Landroid/view/inputmethod/InputConnection;", "onCreateInputConnection", "(Landroid/view/inputmethod/EditorInfo;)Landroid/view/inputmethod/InputConnection;", "positionInWindow", "calculateLocalPosition-MK-Hz9U", "calculateLocalPosition", "calculatePositionInWindow-MK-Hz9U", "calculatePositionInWindow", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "layoutDirection", "onRtlPropertiesChanged", "(I)V", "dispatchHoverEvent", "accessibilityId", "Landroid/view/View;", "findViewByAccessibilityIdTraversal", "(I)Landroid/view/View;", "shouldDelayChildPressedState", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "transferData", "Landroidx/compose/ui/geometry/Size;", "decorationSize", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawDragDecoration", "startDrag-12SF9DM", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLr7/l;)Z", "startDrag", "viewGroup", "clearChildInvalidObservations", "(Landroid/view/ViewGroup;)V", "virtualViewId", "Landroid/view/accessibility/AccessibilityNodeInfo;", "info", "", "extraDataKey", "addExtraDataToAccessibilityNodeInfoHelper", "(ILandroid/view/accessibility/AccessibilityNodeInfo;Ljava/lang/String;)V", "nodeToRemeasure", "childSizeCanAffectParentSize", "(Landroidx/compose/ui/node/LayoutNode;)Z", "Lx6/o0;", "component1-VKZWuLQ", "(J)I", "component1", "component2-VKZWuLQ", "component2", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "pack-ZIaKswc", "(II)J", "pack", "measureSpec", "convertMeasureSpec-I7RO_PI", "(I)J", "convertMeasureSpec", "updatePositionCacheAndDispatch", "invalidateLayoutNodeMeasurement", "invalidateLayers", "handleRotaryEvent", "Landroidx/compose/ui/input/pointer/ProcessResult;", "handleMotionEvent-8iAsVTc", "(Landroid/view/MotionEvent;)I", "handleMotionEvent", "lastEvent", "hasChangedDevices", "(Landroid/view/MotionEvent;Landroid/view/MotionEvent;)Z", "isDevicePressEvent", "sendMotionEvent-8iAsVTc", "sendMotionEvent", "action", "", "eventTime", "forceHover", "sendSimulatedEvent", "(Landroid/view/MotionEvent;IJZ)V", "isInBounds", "recalculateWindowPosition", "(Landroid/view/MotionEvent;)V", "recalculateWindowViewTransforms", "autofillSupported", "isBadMotionEvent", "isPositionChanged", "currentView", "findViewByAccessibilityIdRootedAtCurrentView", "(ILandroid/view/View;)Landroid/view/View;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "lastDownPointerPosition", "J", "superclassInitComplete", "Z", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "sharedDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getSharedDrawScope", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "Landroidx/compose/ui/unit/Density;", "<set-?>", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/semantics/EmptySemanticsElement;", "semanticsModifier", "Landroidx/compose/ui/semantics/EmptySemanticsElement;", "Landroidx/compose/ui/focus/FocusOwner;", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "getFocusOwner", "()Landroidx/compose/ui/focus/FocusOwner;", "Landroidx/compose/ui/platform/DragAndDropModifierOnDragListener;", "dragAndDropModifierOnDragListener", "Landroidx/compose/ui/platform/DragAndDropModifierOnDragListener;", "Landroidx/compose/ui/draganddrop/DragAndDropManager;", "dragAndDropManager", "Landroidx/compose/ui/draganddrop/DragAndDropManager;", "getDragAndDropManager", "()Landroidx/compose/ui/draganddrop/DragAndDropManager;", "Landroidx/compose/ui/platform/WindowInfoImpl;", "_windowInfo", "Landroidx/compose/ui/platform/WindowInfoImpl;", "Landroidx/compose/ui/Modifier;", "keyInputModifier", "Landroidx/compose/ui/Modifier;", "rotaryInputModifier", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "root", "Landroidx/compose/ui/node/LayoutNode;", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/node/RootForTest;", "rootForTest", "Landroidx/compose/ui/node/RootForTest;", "getRootForTest", "()Landroidx/compose/ui/node/RootForTest;", "Landroidx/compose/ui/semantics/SemanticsOwner;", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "getSemanticsOwner", "()Landroidx/compose/ui/semantics/SemanticsOwner;", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "composeAccessibilityDelegate", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "Landroidx/compose/ui/autofill/AutofillTree;", "autofillTree", "Landroidx/compose/ui/autofill/AutofillTree;", "getAutofillTree", "()Landroidx/compose/ui/autofill/AutofillTree;", "", "dirtyLayers", "Ljava/util/List;", "postponedDirtyLayers", "isDrawingContent", "Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "motionEventAdapter", "Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "pointerInputEventProcessor", "Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "configurationChangeObserver", "Lr7/l;", "getConfigurationChangeObserver", "()Lr7/l;", "setConfigurationChangeObserver", "Landroidx/compose/ui/autofill/AndroidAutofill;", "_autofill", "Landroidx/compose/ui/autofill/AndroidAutofill;", "observationClearRequested", "Landroidx/compose/ui/platform/AndroidClipboardManager;", "clipboardManager", "Landroidx/compose/ui/platform/AndroidClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/AndroidClipboardManager;", "Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "accessibilityManager", "Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "getAccessibilityManager", "()Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "showLayoutBounds", "getShowLayoutBounds", "setShowLayoutBounds", "getShowLayoutBounds$annotations", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "_androidViewsHandler", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "Landroidx/compose/ui/platform/DrawChildContainer;", "viewLayersContainer", "Landroidx/compose/ui/platform/DrawChildContainer;", "onMeasureConstraints", "Landroidx/compose/ui/unit/Constraints;", "wasMeasuredWithMultipleConstraints", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "measureAndLayoutDelegate", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "Landroidx/compose/ui/unit/IntOffset;", "globalPosition", "tmpPositionArray", "[I", "tmpMatrix", "[F", "viewToWindowMatrix", "windowToViewMatrix", "lastMatrixRecalculationAnimationTime", "getLastMatrixRecalculationAnimationTime$ui_release", "()J", "setLastMatrixRecalculationAnimationTime$ui_release", "(J)V", "getLastMatrixRecalculationAnimationTime$ui_release$annotations", "forceUseMatrixCache", "windowPosition", "isRenderNodeCompatible", "_viewTreeOwners$delegate", "Landroidx/compose/runtime/MutableState;", "get_viewTreeOwners", "()Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "set_viewTreeOwners", "(Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;)V", "_viewTreeOwners", "viewTreeOwners$delegate", "Landroidx/compose/runtime/State;", "getViewTreeOwners", "viewTreeOwners", "onViewTreeOwnersAvailable", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "globalLayoutListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "scrollChangedListener", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "Landroid/view/ViewTreeObserver$OnTouchModeChangeListener;", "touchModeChangeListener", "Landroid/view/ViewTreeObserver$OnTouchModeChangeListener;", "Landroidx/compose/ui/text/input/TextInputServiceAndroid;", "legacyTextInputServiceAndroid", "Landroidx/compose/ui/text/input/TextInputServiceAndroid;", "Landroidx/compose/ui/text/input/TextInputService;", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "getTextInputService", "()Landroidx/compose/ui/text/input/TextInputService;", "Landroidx/compose/ui/SessionMutex;", "Landroidx/compose/ui/platform/AndroidPlatformTextInputSession;", "textInputSessionMutex", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "softwareKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "getSoftwareKeyboardController", "()Landroidx/compose/ui/platform/SoftwareKeyboardController;", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "fontLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getFontLoader", "()Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getFontLoader$annotations", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver$delegate", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "fontFamilyResolver", "currentFontWeightAdjustment", "I", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection$delegate", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "Landroidx/compose/ui/input/InputModeManagerImpl;", "_inputModeManager", "Landroidx/compose/ui/input/InputModeManagerImpl;", "Landroidx/compose/ui/modifier/ModifierLocalManager;", "modifierLocalManager", "Landroidx/compose/ui/modifier/ModifierLocalManager;", "getModifierLocalManager", "()Landroidx/compose/ui/modifier/ModifierLocalManager;", "Landroidx/compose/ui/platform/TextToolbar;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "previousMotionEvent", "Landroid/view/MotionEvent;", "relayoutTime", "Landroidx/compose/ui/platform/WeakCache;", "layerCache", "Landroidx/compose/ui/platform/WeakCache;", "Landroidx/compose/runtime/collection/MutableVector;", "endApplyChangesListeners", "Landroidx/compose/runtime/collection/MutableVector;", "androidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1", "resendMotionEventRunnable", "Landroidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1;", "Ljava/lang/Runnable;", "sendHoverExitEvent", "Ljava/lang/Runnable;", "hoverExitReceived", "resendMotionEventOnLayout", "Lr7/a;", "Landroidx/compose/ui/platform/CalculateMatrixToWindow;", "matrixToWindow", "Landroidx/compose/ui/platform/CalculateMatrixToWindow;", "keyboardModifiersRequireUpdate", "Landroidx/compose/ui/input/pointer/PointerIconService;", "pointerIconService", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getPointerIconService", "()Landroidx/compose/ui/input/pointer/PointerIconService;", "getView", "()Landroid/view/View;", "Landroidx/compose/ui/platform/WindowInfo;", "getWindowInfo", "()Landroidx/compose/ui/platform/WindowInfo;", "windowInfo", "Landroidx/compose/ui/autofill/Autofill;", "getAutofill", "()Landroidx/compose/ui/autofill/Autofill;", "getAndroidViewsHandler$ui_release", "()Landroidx/compose/ui/platform/AndroidViewsHandler;", "androidViewsHandler", "getMeasureIteration", "measureIteration", "getHasPendingMeasureOrLayout", "hasPendingMeasureOrLayout", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getPlacementScope", "()Landroidx/compose/ui/layout/Placeable$PlacementScope;", "placementScope", "Landroidx/compose/ui/input/InputModeManager;", "getInputModeManager", "()Landroidx/compose/ui/input/InputModeManager;", "inputModeManager", "isLifecycleInResumedState", "getFontWeightAdjustmentCompat", "(Landroid/content/res/Configuration;)I", "fontWeightAdjustmentCompat", "Companion", "AndroidComposeViewTranslationCallback", "ViewTreeOwners", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeView extends ViewGroup implements Owner, ViewRootForTest, PositionCalculator, DefaultLifecycleObserver {
    private static final String FocusTag = "Compose Focus";
    private static final int MaximumLayerCacheSize = 10;
    private static Method getBooleanMethod;
    private static Class<?> systemPropertiesClass;
    private AndroidViewsHandler _androidViewsHandler;
    private final AndroidAutofill _autofill;
    private final InputModeManagerImpl _inputModeManager;

    /* JADX INFO: renamed from: _viewTreeOwners$delegate, reason: from kotlin metadata */
    private final MutableState _viewTreeOwners;
    private final WindowInfoImpl _windowInfo;
    private final AndroidAccessibilityManager accessibilityManager;
    private final AutofillTree autofillTree;
    private final CanvasHolder canvasHolder;
    private final AndroidClipboardManager clipboardManager;
    private final AndroidComposeViewAccessibilityDelegateCompat composeAccessibilityDelegate;
    private r7.l<? super Configuration, t0> configurationChangeObserver;
    private final d7.j coroutineContext;
    private int currentFontWeightAdjustment;
    private Density density;
    private final List<OwnedLayer> dirtyLayers;
    private final DragAndDropManager dragAndDropManager;
    private final DragAndDropModifierOnDragListener dragAndDropModifierOnDragListener;
    private final MutableVector<r7.a<t0>> endApplyChangesListeners;
    private final FocusOwner focusOwner;

    /* JADX INFO: renamed from: fontFamilyResolver$delegate, reason: from kotlin metadata */
    private final MutableState fontFamilyResolver;
    private final Font.ResourceLoader fontLoader;
    private boolean forceUseMatrixCache;
    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    private long globalPosition;
    private final HapticFeedback hapticFeedBack;
    private boolean hoverExitReceived;
    private boolean isDrawingContent;
    private boolean isRenderNodeCompatible;
    private final Modifier keyInputModifier;
    private boolean keyboardModifiersRequireUpdate;
    private long lastDownPointerPosition;
    private long lastMatrixRecalculationAnimationTime;
    private final WeakCache<OwnedLayer> layerCache;

    /* JADX INFO: renamed from: layoutDirection$delegate, reason: from kotlin metadata */
    private final MutableState layoutDirection;
    private final TextInputServiceAndroid legacyTextInputServiceAndroid;
    private final CalculateMatrixToWindow matrixToWindow;
    private final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    private final ModifierLocalManager modifierLocalManager;
    private final MotionEventAdapter motionEventAdapter;
    private boolean observationClearRequested;
    private Constraints onMeasureConstraints;
    private r7.l<? super ViewTreeOwners, t0> onViewTreeOwnersAvailable;
    private final PointerIconService pointerIconService;
    private final PointerInputEventProcessor pointerInputEventProcessor;
    private List<OwnedLayer> postponedDirtyLayers;
    private MotionEvent previousMotionEvent;
    private long relayoutTime;
    private final r7.a<t0> resendMotionEventOnLayout;
    private final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    private final LayoutNode root;
    private final RootForTest rootForTest;
    private final Modifier rotaryInputModifier;
    private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    private final EmptySemanticsElement semanticsModifier;
    private final SemanticsOwner semanticsOwner;
    private final Runnable sendHoverExitEvent;
    private final LayoutNodeDrawScope sharedDrawScope;
    private boolean showLayoutBounds;
    private final OwnerSnapshotObserver snapshotObserver;
    private final SoftwareKeyboardController softwareKeyboardController;
    private boolean superclassInitComplete;
    private final TextInputService textInputService;
    private final AtomicReference textInputSessionMutex;
    private final TextToolbar textToolbar;
    private final float[] tmpMatrix;
    private final int[] tmpPositionArray;
    private final ViewTreeObserver.OnTouchModeChangeListener touchModeChangeListener;
    private final ViewConfiguration viewConfiguration;
    private DrawChildContainer viewLayersContainer;
    private final float[] viewToWindowMatrix;

    /* JADX INFO: renamed from: viewTreeOwners$delegate, reason: from kotlin metadata */
    private final State viewTreeOwners;
    private boolean wasMeasuredWithMultipleConstraints;
    private long windowPosition;
    private final float[] windowToViewMatrix;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$AndroidComposeViewTranslationCallback;", "Landroid/view/translation/ViewTranslationCallback;", "()V", "onClearTranslation", "", "view", "Landroid/view/View;", "onHideTranslation", "onShowTranslation", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AndroidComposeViewTranslationCallback implements ViewTranslationCallback {
        public boolean onClearTranslation(View view) {
            ((AndroidComposeView) view).composeAccessibilityDelegate.onClearTranslation$ui_release();
            return true;
        }

        public boolean onHideTranslation(View view) {
            ((AndroidComposeView) view).composeAccessibilityDelegate.onHideTranslation$ui_release();
            return true;
        }

        public boolean onShowTranslation(View view) {
            ((AndroidComposeView) view).composeAccessibilityDelegate.onShowTranslation$ui_release();
            return true;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$Companion;", "", "()V", "FocusTag", "", "MaximumLayerCacheSize", "", "getBooleanMethod", "Ljava/lang/reflect/Method;", "systemPropertiesClass", "Ljava/lang/Class;", "getIsShowingLayoutBounds", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getIsShowingLayoutBounds() {
            try {
                if (AndroidComposeView.systemPropertiesClass == null) {
                    AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                    Class cls = AndroidComposeView.systemPropertiesClass;
                    AndroidComposeView.getBooleanMethod = cls != null ? cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE) : null;
                }
                Method method = AndroidComposeView.getBooleanMethod;
                Object objInvoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "", "Landroidx/lifecycle/y;", "lifecycleOwner", "Lb2/g;", "savedStateRegistryOwner", "<init>", "(Landroidx/lifecycle/y;Lb2/g;)V", "Landroidx/lifecycle/y;", "getLifecycleOwner", "()Landroidx/lifecycle/y;", "Lb2/g;", "getSavedStateRegistryOwner", "()Lb2/g;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ViewTreeOwners {
        public static final int $stable = 8;
        private final y lifecycleOwner;
        private final b2.g savedStateRegistryOwner;

        public ViewTreeOwners(y yVar, b2.g gVar) {
            this.lifecycleOwner = yVar;
            this.savedStateRegistryOwner = gVar;
        }

        public final y getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final b2.g getSavedStateRegistryOwner() {
            return this.savedStateRegistryOwner;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$onFocusChanged$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06171 extends kotlin.jvm.internal.r implements r7.a<t0> {
        final /* synthetic */ boolean $gainFocus;
        final /* synthetic */ AndroidComposeView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06171(boolean z, AndroidComposeView androidComposeView) {
            super(0);
            this.$gainFocus = z;
            this.this$0 = androidComposeView;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4928invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4928invoke() {
            if (this.$gainFocus) {
                this.this$0.clearFocus();
            } else {
                this.this$0.requestFocus();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$removeAndroidView$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06181 extends kotlin.jvm.internal.r implements r7.a<t0> {
        final /* synthetic */ AndroidViewHolder $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06181(AndroidViewHolder androidViewHolder) {
            super(0);
            this.$view = androidViewHolder;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4929invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4929invoke() {
            AndroidComposeView.this.getAndroidViewsHandler$ui_release().removeViewInLayout(this.$view);
            HashMap<LayoutNode, AndroidViewHolder> layoutNodeToHolder = AndroidComposeView.this.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder();
            q0.c(layoutNodeToHolder).remove(AndroidComposeView.this.getAndroidViewsHandler$ui_release().getHolderToLayoutNode().remove(this.$view));
            AndroidViewHolder androidViewHolder = this.$view;
            WeakHashMap weakHashMap = b2.f2200a;
            androidViewHolder.setImportantForAccessibility(0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$textInputSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.ui.platform.AndroidComposeView", f = "AndroidComposeView.android.kt", l = {428}, m = "textInputSession")
    public static final class C06191 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C06191(d7.d<? super C06191> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidComposeView.this.textInputSession(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$textInputSession$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "it", "Landroidx/compose/ui/platform/AndroidPlatformTextInputSession;", "invoke", "(Lka/k0;)Landroidx/compose/ui/platform/AndroidPlatformTextInputSession;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.l<k0, AndroidPlatformTextInputSession> {
        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final AndroidPlatformTextInputSession invoke(k0 k0Var) {
            AndroidComposeView androidComposeView = AndroidComposeView.this;
            return new AndroidPlatformTextInputSession(androidComposeView, androidComposeView.getTextInputService(), k0Var);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v11, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    public AndroidComposeView(Context context, d7.j jVar) {
        super(context);
        this.coroutineContext = jVar;
        Offset.Companion companion = Offset.INSTANCE;
        this.lastDownPointerPosition = companion.m3251getUnspecifiedF1C5BW0();
        this.superclassInitComplete = true;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        this.sharedDrawScope = new LayoutNodeDrawScope(null, 1, 0 == true ? 1 : 0);
        this.density = AndroidDensity_androidKt.Density(context);
        EmptySemanticsElement emptySemanticsElement = EmptySemanticsElement.INSTANCE;
        this.semanticsModifier = emptySemanticsElement;
        this.focusOwner = new FocusOwnerImpl(new AndroidComposeView$focusOwner$1(this));
        DragAndDropModifierOnDragListener dragAndDropModifierOnDragListener = new DragAndDropModifierOnDragListener(new AndroidComposeView$dragAndDropModifierOnDragListener$1(this));
        this.dragAndDropModifierOnDragListener = dragAndDropModifierOnDragListener;
        this.dragAndDropManager = dragAndDropModifierOnDragListener;
        this._windowInfo = new WindowInfoImpl();
        Modifier.Companion companion2 = Modifier.INSTANCE;
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion2, new AndroidComposeView$keyInputModifier$1(this));
        this.keyInputModifier = modifierOnKeyEvent;
        Modifier modifierOnRotaryScrollEvent = RotaryInputModifierKt.onRotaryScrollEvent(companion2, AndroidComposeView$rotaryInputModifier$1.INSTANCE);
        this.rotaryInputModifier = modifierOnRotaryScrollEvent;
        this.canvasHolder = new CanvasHolder();
        LayoutNode layoutNode = new LayoutNode(false, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(getDensity());
        layoutNode.setModifier(companion2.then(emptySemanticsElement).then(modifierOnRotaryScrollEvent).then(getFocusOwner().getModifier()).then(modifierOnKeyEvent).then(dragAndDropModifierOnDragListener.getModifier()));
        this.root = layoutNode;
        this.rootForTest = this;
        this.semanticsOwner = new SemanticsOwner(getRoot());
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.composeAccessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        this.autofillTree = new AutofillTree();
        this.dirtyLayers = new ArrayList();
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(getRoot());
        this.configurationChangeObserver = AndroidComposeView$configurationChangeObserver$1.INSTANCE;
        this._autofill = autofillSupported() ? new AndroidAutofill(this, getAutofillTree()) : null;
        this.clipboardManager = new AndroidClipboardManager(context);
        this.accessibilityManager = new AndroidAccessibilityManager(context);
        this.snapshotObserver = new OwnerSnapshotObserver(new AndroidComposeView$snapshotObserver$1(this));
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(getRoot());
        this.viewConfiguration = new AndroidViewConfiguration(android.view.ViewConfiguration.get(context));
        this.globalPosition = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tmpPositionArray = new int[]{0, 0};
        float[] fArrM3684constructorimpl$default = Matrix.m3684constructorimpl$default(null, 1, null);
        this.tmpMatrix = fArrM3684constructorimpl$default;
        this.viewToWindowMatrix = Matrix.m3684constructorimpl$default(null, 1, null);
        this.windowToViewMatrix = Matrix.m3684constructorimpl$default(null, 1, null);
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.windowPosition = companion.m3250getInfiniteF1C5BW0();
        this.isRenderNodeCompatible = true;
        this._viewTreeOwners = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.viewTreeOwners = SnapshotStateKt.derivedStateOf(new AndroidComposeView$viewTreeOwners$2(this));
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.platform.b
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f1929i.updatePositionCacheAndDispatch();
            }
        };
        this.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.compose.ui.platform.c
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f1930a.updatePositionCacheAndDispatch();
            }
        };
        this.touchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: androidx.compose.ui.platform.d
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z) {
                AndroidComposeView.touchModeChangeListener$lambda$3(this.f1931i, z);
            }
        };
        TextInputServiceAndroid textInputServiceAndroid = new TextInputServiceAndroid(getView(), this);
        this.legacyTextInputServiceAndroid = textInputServiceAndroid;
        this.textInputService = new TextInputService((PlatformTextInputService) AndroidComposeView_androidKt.getPlatformTextInputServiceInterceptor().invoke(textInputServiceAndroid));
        this.textInputSessionMutex = SessionMutex.m3112constructorimpl();
        this.softwareKeyboardController = new DelegatingSoftwareKeyboardController(getTextInputService());
        this.fontLoader = new AndroidFontResourceLoader(context);
        this.fontFamilyResolver = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        this.currentFontWeightAdjustment = getFontWeightAdjustmentCompat(context.getResources().getConfiguration());
        this.layoutDirection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AndroidComposeView_androidKt.getLocaleLayoutDirection(context.getResources().getConfiguration()), null, 2, null);
        this.hapticFeedBack = new PlatformHapticFeedback(this);
        this._inputModeManager = new InputModeManagerImpl(isInTouchMode() ? InputMode.INSTANCE.m4084getTouchaOaMEAU() : InputMode.INSTANCE.m4083getKeyboardaOaMEAU(), new AndroidComposeView$_inputModeManager$1(this), objArr2 == true ? 1 : 0);
        this.modifierLocalManager = new ModifierLocalManager(this);
        this.textToolbar = new AndroidTextToolbar(this);
        this.layerCache = new WeakCache<>();
        this.endApplyChangesListeners = new MutableVector<>(new r7.a[16], 0);
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                this.this$0.removeCallbacks(this);
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z = motionEvent.getToolType(0) == 3;
                    int actionMasked = motionEvent.getActionMasked();
                    if (z) {
                        if (actionMasked == 10 || actionMasked == 1) {
                            return;
                        }
                    } else if (actionMasked == 1) {
                        return;
                    }
                    int i10 = 7;
                    if (actionMasked != 7 && actionMasked != 9) {
                        i10 = 2;
                    }
                    AndroidComposeView androidComposeView = this.this$0;
                    androidComposeView.sendSimulatedEvent(motionEvent, i10, androidComposeView.relayoutTime, false);
                }
            }
        };
        this.sendHoverExitEvent = new e(this, 1);
        this.resendMotionEventOnLayout = new AndroidComposeView$resendMotionEventOnLayout$1(this);
        int i10 = Build.VERSION.SDK_INT;
        this.matrixToWindow = i10 >= 29 ? new CalculateMatrixToWindowApi29() : new CalculateMatrixToWindowApi21(fArrM3684constructorimpl$default, objArr == true ? 1 : 0);
        setWillNotDraw(false);
        setFocusable(true);
        if (i10 >= 26) {
            AndroidComposeViewVerificationHelperMethodsO.INSTANCE.focusable(this, 1, false);
        }
        setFocusableInTouchMode(true);
        setClipChildren(false);
        b2.h(this, androidComposeViewAccessibilityDelegateCompat);
        r7.l<ViewRootForTest, t0> onViewCreatedCallback = ViewRootForTest.INSTANCE.getOnViewCreatedCallback();
        if (onViewCreatedCallback != null) {
            onViewCreatedCallback.invoke(this);
        }
        setOnDragListener(dragAndDropModifierOnDragListener);
        getRoot().attach$ui_release(this);
        if (i10 >= 29) {
            AndroidComposeViewForceDarkModeQ.INSTANCE.disallowForceDark(this);
        }
        this.pointerIconService = new PointerIconService() { // from class: androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1
            private PointerIcon currentIcon = PointerIcon.INSTANCE.getDefault();

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            /* JADX INFO: renamed from: getIcon, reason: from getter */
            public PointerIcon getCurrentIcon() {
                return this.currentIcon;
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setIcon(PointerIcon value) {
                if (value == null) {
                    value = PointerIcon.INSTANCE.getDefault();
                }
                this.currentIcon = value;
                if (Build.VERSION.SDK_INT >= 24) {
                    AndroidComposeViewVerificationHelperMethodsN.INSTANCE.setPointerIcon(this.this$0, value);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey) {
        Integer num;
        if (kotlin.jvm.internal.p.a(extraDataKey, this.composeAccessibilityDelegate.getExtraDataTestTraversalBeforeVal())) {
            Integer num2 = this.composeAccessibilityDelegate.getIdToBeforeMap$ui_release().get(Integer.valueOf(virtualViewId));
            if (num2 != null) {
                info.getExtras().putInt(extraDataKey, num2.intValue());
                return;
            }
            return;
        }
        if (!kotlin.jvm.internal.p.a(extraDataKey, this.composeAccessibilityDelegate.getExtraDataTestTraversalAfterVal()) || (num = this.composeAccessibilityDelegate.getIdToAfterMap$ui_release().get(Integer.valueOf(virtualViewId))) == null) {
            return;
        }
        info.getExtras().putInt(extraDataKey, num.intValue());
    }

    private final boolean autofillSupported() {
        return Build.VERSION.SDK_INT >= 26;
    }

    private final boolean childSizeCanAffectParentSize(LayoutNode layoutNode) {
        if (this.wasMeasuredWithMultipleConstraints) {
            return true;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        return (parent$ui_release == null || parent$ui_release.getHasFixedInnerContentConstraints$ui_release()) ? false : true;
    }

    private final void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = viewGroup.getChildAt(i10);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    /* JADX INFO: renamed from: component1-VKZWuLQ, reason: not valid java name */
    private final int m4918component1VKZWuLQ(long j10) {
        return (int) (j10 >>> 32);
    }

    /* JADX INFO: renamed from: component2-VKZWuLQ, reason: not valid java name */
    private final int m4919component2VKZWuLQ(long j10) {
        return (int) (j10 & 4294967295L);
    }

    /* JADX INFO: renamed from: convertMeasureSpec-I7RO_PI, reason: not valid java name */
    private final long m4920convertMeasureSpecI7RO_PI(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        if (mode == Integer.MIN_VALUE) {
            return m4922packZIaKswc(0, size);
        }
        if (mode == 0) {
            return m4922packZIaKswc(0, Integer.MAX_VALUE);
        }
        if (mode == 1073741824) {
            return m4922packZIaKswc(size, size);
        }
        throw new IllegalStateException();
    }

    private final View findViewByAccessibilityIdRootedAtCurrentView(int accessibilityId, View currentView) throws NoSuchMethodException {
        if (Build.VERSION.SDK_INT < 29) {
            Method declaredMethod = View.class.getDeclaredMethod("getAccessibilityViewId", null);
            declaredMethod.setAccessible(true);
            if (kotlin.jvm.internal.p.a(declaredMethod.invoke(currentView, null), Integer.valueOf(accessibilityId))) {
                return currentView;
            }
            if (currentView instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) currentView;
                int childCount = viewGroup.getChildCount();
                for (int i10 = 0; i10 < childCount; i10++) {
                    View viewFindViewByAccessibilityIdRootedAtCurrentView = findViewByAccessibilityIdRootedAtCurrentView(accessibilityId, viewGroup.getChildAt(i10));
                    if (viewFindViewByAccessibilityIdRootedAtCurrentView != null) {
                        return viewFindViewByAccessibilityIdRootedAtCurrentView;
                    }
                }
            }
        }
        return null;
    }

    @x6.e
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    private final int getFontWeightAdjustmentCompat(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            return configuration.fontWeightAdjustment;
        }
        return 0;
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui_release$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final ViewTreeOwners get_viewTreeOwners() {
        return (ViewTreeOwners) this._viewTreeOwners.getValue();
    }

    /* JADX INFO: renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m4921handleMotionEvent8iAsVTc(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        removeCallbacks(this.resendMotionEventRunnable);
        try {
            recalculateWindowPosition(motionEvent);
            boolean z = true;
            this.forceUseMatrixCache = true;
            measureAndLayout(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked = motionEvent.getActionMasked();
                MotionEvent motionEvent3 = this.previousMotionEvent;
                boolean z5 = motionEvent3 != null && motionEvent3.getToolType(0) == 3;
                if (motionEvent3 == null || !hasChangedDevices(motionEvent, motionEvent3)) {
                    motionEvent2 = motionEvent3;
                } else {
                    if (isDevicePressEvent(motionEvent3)) {
                        this.pointerInputEventProcessor.processCancel();
                    } else if (motionEvent3.getActionMasked() != 10 && z5) {
                        sendSimulatedEvent$default(this, motionEvent3, 10, motionEvent3.getEventTime(), false, 8, null);
                        motionEvent2 = motionEvent3;
                    }
                    motionEvent2 = motionEvent3;
                }
                if (motionEvent.getToolType(0) != 3) {
                    z = false;
                }
                if (!z5 && z && actionMasked != 3 && actionMasked != 9 && isInBounds(motionEvent)) {
                    sendSimulatedEvent$default(this, motionEvent, 9, motionEvent.getEventTime(), false, 8, null);
                }
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                int iM4923sendMotionEvent8iAsVTc = m4923sendMotionEvent8iAsVTc(motionEvent);
                Trace.endSection();
                return iM4923sendMotionEvent8iAsVTc;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } finally {
            this.forceUseMatrixCache = false;
        }
    }

    private final boolean handleRotaryEvent(MotionEvent event) {
        float fA;
        android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
        float f10 = -event.getAxisValue(26);
        Context context = getContext();
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 26) {
            Method method = f2.f2233a;
            fA = c2.b(viewConfiguration);
        } else {
            fA = f2.a(viewConfiguration, context);
        }
        return getFocusOwner().dispatchRotaryEvent(new RotaryScrollEvent(fA * f10, (i10 >= 26 ? c2.a(viewConfiguration) : f2.a(viewConfiguration, getContext())) * f10, event.getEventTime(), event.getDeviceId()));
    }

    private final boolean hasChangedDevices(MotionEvent event, MotionEvent lastEvent) {
        return (lastEvent.getSource() == event.getSource() && lastEvent.getToolType(0) == event.getToolType(0)) ? false : true;
    }

    private final void invalidateLayers(LayoutNode node) {
        node.invalidateLayers$ui_release();
        MutableVector<LayoutNode> mutableVector = node.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                invalidateLayers(content[i10]);
                i10++;
            } while (i10 < size);
        }
    }

    private final void invalidateLayoutNodeMeasurement(LayoutNode node) {
        int i10 = 0;
        MeasureAndLayoutDelegate.requestRemeasure$default(this.measureAndLayoutDelegate, node, false, 2, null);
        MutableVector<LayoutNode> mutableVector = node.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            do {
                invalidateLayoutNodeMeasurement(content[i10]);
                i10++;
            } while (i10 < size);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0082 A[LOOP:0: B:22:0x004c->B:39:0x0082, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0085 A[EDGE_INSN: B:41:0x0085->B:40:0x0085 BREAK  A[LOOP:0: B:22:0x004c->B:39:0x0082], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean isBadMotionEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            float r0 = r7.getX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r7.getY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r7.getRawX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r7.getRawY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            r0 = r2
            goto L45
        L44:
            r0 = r3
        L45:
            if (r0 != 0) goto L85
            int r1 = r7.getPointerCount()
            r4 = r3
        L4c:
            if (r4 >= r1) goto L85
            float r0 = r7.getX(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L7f
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L7f
            float r0 = r7.getY(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L7f
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L7f
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 29
            if (r0 < r5) goto L7d
            androidx.compose.ui.platform.MotionEventVerifierApi29 r0 = androidx.compose.ui.platform.MotionEventVerifierApi29.INSTANCE
            boolean r0 = r0.isValidMotionEvent(r7, r4)
            if (r0 != 0) goto L7d
            goto L7f
        L7d:
            r0 = r2
            goto L80
        L7f:
            r0 = r3
        L80:
            if (r0 != 0) goto L85
            int r4 = r4 + 1
            goto L4c
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.isBadMotionEvent(android.view.MotionEvent):boolean");
    }

    private final boolean isDevicePressEvent(MotionEvent event) {
        int actionMasked;
        return event.getButtonState() != 0 || (actionMasked = event.getActionMasked()) == 0 || actionMasked == 2 || actionMasked == 6;
    }

    private final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y10 = motionEvent.getY();
        return 0.0f <= x && x <= ((float) getWidth()) && 0.0f <= y10 && y10 <= ((float) getHeight());
    }

    private final boolean isPositionChanged(MotionEvent event) {
        MotionEvent motionEvent;
        return (event.getPointerCount() == 1 && (motionEvent = this.previousMotionEvent) != null && motionEvent.getPointerCount() == event.getPointerCount() && event.getRawX() == motionEvent.getRawX() && event.getRawY() == motionEvent.getRawY()) ? false : true;
    }

    /* JADX INFO: renamed from: pack-ZIaKswc, reason: not valid java name */
    private final long m4922packZIaKswc(int a10, int b10) {
        return ((long) b10) | (((long) a10) << 32);
    }

    private final void recalculateWindowPosition() {
        if (this.forceUseMatrixCache) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (jCurrentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = jCurrentAnimationTimeMillis;
            recalculateWindowViewTransforms();
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            view.getLocationOnScreen(this.tmpPositionArray);
            int[] iArr = this.tmpPositionArray;
            float f10 = iArr[0];
            float f11 = iArr[1];
            view.getLocationInWindow(iArr);
            int[] iArr2 = this.tmpPositionArray;
            this.windowPosition = OffsetKt.Offset(f10 - iArr2[0], f11 - iArr2[1]);
        }
    }

    private final void recalculateWindowViewTransforms() {
        this.matrixToWindow.mo4943calculateMatrixToWindowEL8BTi8(this, this.viewToWindowMatrix);
        InvertMatrixKt.m4969invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
    }

    private final void scheduleMeasureAndLayout(LayoutNode nodeToRemeasure) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (nodeToRemeasure != null) {
            while (nodeToRemeasure != null && nodeToRemeasure.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock && childSizeCanAffectParentSize(nodeToRemeasure)) {
                nodeToRemeasure = nodeToRemeasure.getParent$ui_release();
            }
            if (nodeToRemeasure == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    public static /* synthetic */ void scheduleMeasureAndLayout$default(AndroidComposeView androidComposeView, LayoutNode layoutNode, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            layoutNode = null;
        }
        androidComposeView.scheduleMeasureAndLayout(layoutNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendHoverExitEvent$lambda$5(AndroidComposeView androidComposeView) {
        androidComposeView.hoverExitReceived = false;
        MotionEvent motionEvent = androidComposeView.previousMotionEvent;
        if (motionEvent.getActionMasked() != 10) {
            throw new IllegalStateException("The ACTION_HOVER_EXIT event was not cleared.");
        }
        androidComposeView.m4923sendMotionEvent8iAsVTc(motionEvent);
    }

    /* JADX INFO: renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m4923sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        PointerInputEventData pointerInputEventData;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            this._windowInfo.m5005setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m4568constructorimpl(motionEvent.getMetaState()));
        }
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEvent, this);
        if (pointerInputEventConvertToPointerInputEvent$ui_release == null) {
            this.pointerInputEventProcessor.processCancel();
            return PointerInputEventProcessorKt.ProcessResult(false, false);
        }
        List<PointerInputEventData> pointers = pointerInputEventConvertToPointerInputEvent$ui_release.getPointers();
        int size = pointers.size() - 1;
        if (size >= 0) {
            while (true) {
                int i10 = size - 1;
                pointerInputEventData = pointers.get(size);
                if (pointerInputEventData.getDown()) {
                    break;
                }
                if (i10 < 0) {
                    break;
                }
                size = i10;
            }
            pointerInputEventData = null;
        } else {
            pointerInputEventData = null;
        }
        PointerInputEventData pointerInputEventData2 = pointerInputEventData;
        if (pointerInputEventData2 != null) {
            this.lastDownPointerPosition = pointerInputEventData2.m4535getPositionF1C5BW0();
        }
        int iM4540processBIzXfog = this.pointerInputEventProcessor.m4540processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui_release, this, isInBounds(motionEvent));
        int actionMasked = motionEvent.getActionMasked();
        if ((actionMasked == 0 || actionMasked == 5) && !ProcessResult.m4594getDispatchedToAPointerInputModifierimpl(iM4540processBIzXfog)) {
            this.motionEventAdapter.endStream(motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return iM4540processBIzXfog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSimulatedEvent(MotionEvent motionEvent, int action, long eventTime, boolean forceHover) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
            }
        } else if (action != 9 && action != 10) {
            actionIndex = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (actionIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i10 = 0; i10 < pointerCount; i10++) {
            pointerPropertiesArr[i10] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i11 = 0; i11 < pointerCount; i11++) {
            pointerCoordsArr[i11] = new MotionEvent.PointerCoords();
        }
        int i12 = 0;
        while (i12 < pointerCount) {
            int i13 = ((actionIndex < 0 || i12 < actionIndex) ? 0 : 1) + i12;
            motionEvent.getPointerProperties(i13, pointerPropertiesArr[i12]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i12];
            motionEvent.getPointerCoords(i13, pointerCoords);
            long jMo4587localToScreenMKHz9U = mo4587localToScreenMKHz9U(OffsetKt.Offset(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = Offset.m3236getXimpl(jMo4587localToScreenMKHz9U);
            pointerCoords.y = Offset.m3237getYimpl(jMo4587localToScreenMKHz9U);
            i12++;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? eventTime : motionEvent.getDownTime(), eventTime, action, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), forceHover ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        this.pointerInputEventProcessor.m4540processBIzXfog(this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEventObtain, this), this, true);
        motionEventObtain.recycle();
    }

    public static /* synthetic */ void sendSimulatedEvent$default(AndroidComposeView androidComposeView, MotionEvent motionEvent, int i10, long j10, boolean z, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            z = true;
        }
        androidComposeView.sendSimulatedEvent(motionEvent, i10, j10, z);
    }

    private void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver.setValue(resolver);
    }

    private void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection.setValue(layoutDirection);
    }

    private final void set_viewTreeOwners(ViewTreeOwners viewTreeOwners) {
        this._viewTreeOwners.setValue(viewTreeOwners);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startDrag-12SF9DM, reason: not valid java name */
    public final boolean m4924startDrag12SF9DM(DragAndDropTransferData transferData, long decorationSize, r7.l<? super DrawScope, t0> drawDragDecoration) {
        Resources resources = getContext().getResources();
        ComposeDragShadowBuilder composeDragShadowBuilder = new ComposeDragShadowBuilder(DensityKt.Density(resources.getDisplayMetrics().density, resources.getConfiguration().fontScale), decorationSize, drawDragDecoration, null);
        return Build.VERSION.SDK_INT >= 24 ? AndroidComposeViewStartDragAndDropN.INSTANCE.startDragAndDrop(this, transferData, composeDragShadowBuilder) : startDrag(transferData.getClipData(), composeDragShadowBuilder, transferData.getLocalState(), transferData.getFlags());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void touchModeChangeListener$lambda$3(AndroidComposeView androidComposeView, boolean z) {
        androidComposeView._inputModeManager.m4087setInputModeiuPiT84(z ? InputMode.INSTANCE.m4084getTouchaOaMEAU() : InputMode.INSTANCE.m4083getKeyboardaOaMEAU());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePositionCacheAndDispatch() {
        getLocationOnScreen(this.tmpPositionArray);
        long j10 = this.globalPosition;
        int iM5794component1impl = IntOffset.m5794component1impl(j10);
        int iM5795component2impl = IntOffset.m5795component2impl(j10);
        int[] iArr = this.tmpPositionArray;
        boolean z = false;
        int i10 = iArr[0];
        if (iM5794component1impl != i10 || iM5795component2impl != iArr[1]) {
            this.globalPosition = IntOffsetKt.IntOffset(i10, iArr[1]);
            if (iM5794component1impl != Integer.MAX_VALUE && iM5795component2impl != Integer.MAX_VALUE) {
                getRoot().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    public final void addAndroidView(AndroidViewHolder view, final LayoutNode layoutNode) {
        getAndroidViewsHandler$ui_release().getHolderToLayoutNode().put(view, layoutNode);
        getAndroidViewsHandler$ui_release().addView(view);
        getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().put(layoutNode, view);
        WeakHashMap weakHashMap = b2.f2200a;
        view.setImportantForAccessibility(1);
        b2.h(view, new androidx.core.view.b() { // from class: androidx.compose.ui.platform.AndroidComposeView.addAndroidView.1
            /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
            @Override // androidx.core.view.b
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onInitializeAccessibilityNodeInfo(android.view.View r6, q0.k r7) {
                /*
                    Method dump skipped, instruction units count: 212
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.AnonymousClass1.onInitializeAccessibilityNodeInfo(android.view.View, q0.k):void");
            }
        });
    }

    @Override // android.view.View
    public void autofill(SparseArray<AutofillValue> values) {
        AndroidAutofill androidAutofill;
        if (!autofillSupported() || (androidAutofill = this._autofill) == null) {
            return;
        }
        AndroidAutofill_androidKt.performAutofill(androidAutofill, values);
    }

    public final Object boundsUpdatesEventLoop(d7.d<? super t0> dVar) throws Throwable {
        Object objBoundsUpdatesEventLoop$ui_release = this.composeAccessibilityDelegate.boundsUpdatesEventLoop$ui_release(dVar);
        return objBoundsUpdatesEventLoop$ui_release == e7.a.f15033i ? objBoundsUpdatesEventLoop$ui_release : t0.f22605a;
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculateLocalPosition-MK-Hz9U */
    public long mo4896calculateLocalPositionMKHz9U(long positionInWindow) {
        recalculateWindowPosition();
        return Matrix.m3690mapMKHz9U(this.windowToViewMatrix, positionInWindow);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculatePositionInWindow-MK-Hz9U */
    public long mo4897calculatePositionInWindowMKHz9U(long localPosition) {
        recalculateWindowPosition();
        return Matrix.m3690mapMKHz9U(this.viewToWindowMatrix, localPosition);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int direction) {
        return this.composeAccessibilityDelegate.m4932canScroll0AR0LA0$ui_release(false, direction, this.lastDownPointerPosition);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        return this.composeAccessibilityDelegate.m4932canScroll0AR0LA0$ui_release(true, direction, this.lastDownPointerPosition);
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnedLayer createLayer(r7.l<? super Canvas, t0> drawBlock, r7.a<t0> invalidateParentLayer) {
        OwnedLayer ownedLayerPop = this.layerCache.pop();
        if (ownedLayerPop != null) {
            ownedLayerPop.reuseLayer(drawBlock, invalidateParentLayer);
            return ownedLayerPop;
        }
        if (isHardwareAccelerated() && this.isRenderNodeCompatible) {
            try {
                return new RenderNodeLayer(this, drawBlock, invalidateParentLayer);
            } catch (Throwable unused) {
                this.isRenderNodeCompatible = false;
            }
        }
        if (this.viewLayersContainer == null) {
            ViewLayer.Companion companion = ViewLayer.INSTANCE;
            if (!companion.getHasRetrievedMethod()) {
                companion.updateDisplayList(new View(getContext()));
            }
            DrawChildContainer drawChildContainer = companion.getShouldUseDispatchDraw() ? new DrawChildContainer(getContext()) : new ViewLayerContainer(getContext());
            this.viewLayersContainer = drawChildContainer;
            addView(drawChildContainer);
        }
        return new ViewLayer(this, this.viewLayersContainer, drawBlock, invalidateParentLayer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(android.graphics.Canvas canvas) {
        if (!isAttachedToWindow()) {
            invalidateLayers(getRoot());
        }
        androidx.compose.ui.node.e.f(this, false, 1, null);
        Snapshot.INSTANCE.sendApplyNotifications();
        this.isDrawingContent = true;
        CanvasHolder canvasHolder = this.canvasHolder;
        android.graphics.Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        getRoot().draw$ui_release(canvasHolder.getAndroidCanvas());
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        if (!this.dirtyLayers.isEmpty()) {
            int size = this.dirtyLayers.size();
            for (int i10 = 0; i10 < size; i10++) {
                this.dirtyLayers.get(i10).updateDisplayList();
            }
        }
        if (ViewLayer.INSTANCE.getShouldUseDispatchDraw()) {
            int iSave = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(iSave);
        }
        this.dirtyLayers.clear();
        this.isDrawingContent = false;
        List<OwnedLayer> list = this.postponedDirtyLayers;
        if (list != null) {
            this.dirtyLayers.addAll(list);
            list.clear();
        }
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return event.getActionMasked() == 8 ? event.isFromSource(4194304) ? handleRotaryEvent(event) : (isBadMotionEvent(event) || !isAttachedToWindow()) ? super.dispatchGenericMotionEvent(event) : ProcessResult.m4594getDispatchedToAPointerInputModifierimpl(m4921handleMotionEvent8iAsVTc(event)) : super.dispatchGenericMotionEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(event) || !isAttachedToWindow()) {
            return false;
        }
        this.composeAccessibilityDelegate.dispatchHoverEvent$ui_release(event);
        int actionMasked = event.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10 && isInBounds(event)) {
                if (event.getToolType(0) == 3 && event.getButtonState() != 0) {
                    return false;
                }
                MotionEvent motionEvent = this.previousMotionEvent;
                if (motionEvent != null) {
                    motionEvent.recycle();
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(event);
                this.hoverExitReceived = true;
                post(this.sendHoverExitEvent);
                return false;
            }
        } else if (!isPositionChanged(event)) {
            return false;
        }
        return ProcessResult.m4594getDispatchedToAPointerInputModifierimpl(m4921handleMotionEvent8iAsVTc(event));
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!isFocused()) {
            return super.dispatchKeyEvent(event);
        }
        this._windowInfo.m5005setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m4568constructorimpl(event.getMetaState()));
        return getFocusOwner().mo3170dispatchKeyEventZmokQxo(androidx.compose.ui.input.key.KeyEvent.m4385constructorimpl(event)) || super.dispatchKeyEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        return (isFocused() && getFocusOwner().mo3169dispatchInterceptedSoftKeyboardEventZmokQxo(androidx.compose.ui.input.key.KeyEvent.m4385constructorimpl(event))) || super.dispatchKeyEventPreIme(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            if (motionEvent.getActionMasked() != 0 || hasChangedDevices(motionEvent, motionEvent2)) {
                this.sendHoverExitEvent.run();
            } else {
                this.hoverExitReceived = false;
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int iM4921handleMotionEvent8iAsVTc = m4921handleMotionEvent8iAsVTc(motionEvent);
        if (ProcessResult.m4593getAnyMovementConsumedimpl(iM4921handleMotionEvent8iAsVTc)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return ProcessResult.m4594getDispatchedToAPointerInputModifierimpl(iM4921handleMotionEvent8iAsVTc);
    }

    public final void drawAndroidView(AndroidViewHolder view, android.graphics.Canvas canvas) {
        getAndroidViewsHandler$ui_release().drawView(view, canvas);
    }

    public final View findViewByAccessibilityIdTraversal(int accessibilityId) throws IllegalAccessException, InvocationTargetException {
        try {
            if (Build.VERSION.SDK_INT < 29) {
                return findViewByAccessibilityIdRootedAtCurrentView(accessibilityId, this);
            }
            Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(this, Integer.valueOf(accessibilityId));
            if (objInvoke instanceof View) {
                return (View) objInvoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode, affectsLookahead);
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui_release() {
        if (this._androidViewsHandler == null) {
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(getContext());
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler);
        }
        return this._androidViewsHandler;
    }

    @Override // androidx.compose.ui.node.Owner
    public Autofill getAutofill() {
        return this._autofill;
    }

    @Override // androidx.compose.ui.node.Owner
    public AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    public final r7.l<Configuration, t0> getConfigurationChangeObserver() {
        return this.configurationChangeObserver;
    }

    @Override // androidx.compose.ui.node.Owner
    public d7.j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public Density getDensity() {
        return this.density;
    }

    @Override // androidx.compose.ui.node.Owner
    public DragAndDropManager getDragAndDropManager() {
        return this.dragAndDropManager;
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: getFocusDirection-P8AzH3I */
    public FocusDirection mo4898getFocusDirectionP8AzH3I(KeyEvent keyEvent) {
        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
        Key.Companion companion = Key.INSTANCE;
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4329getTabEK5gGoQ())) {
            return FocusDirection.m3150boximpl(KeyEvent_androidKt.m4407isShiftPressedZmokQxo(keyEvent) ? FocusDirection.INSTANCE.m3164getPreviousdhqQ8s() : FocusDirection.INSTANCE.m3163getNextdhqQ8s());
        }
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4170getDirectionRightEK5gGoQ())) {
            return FocusDirection.m3150boximpl(FocusDirection.INSTANCE.m3165getRightdhqQ8s());
        }
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4169getDirectionLeftEK5gGoQ())) {
            return FocusDirection.m3150boximpl(FocusDirection.INSTANCE.m3162getLeftdhqQ8s());
        }
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4171getDirectionUpEK5gGoQ()) ? true : Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4282getPageUpEK5gGoQ())) {
            return FocusDirection.m3150boximpl(FocusDirection.INSTANCE.m3166getUpdhqQ8s());
        }
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4166getDirectionDownEK5gGoQ()) ? true : Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4281getPageDownEK5gGoQ())) {
            return FocusDirection.m3150boximpl(FocusDirection.INSTANCE.m3159getDowndhqQ8s());
        }
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4165getDirectionCenterEK5gGoQ()) ? true : Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4179getEnterEK5gGoQ()) ? true : Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4271getNumPadEnterEK5gGoQ())) {
            return FocusDirection.m3150boximpl(FocusDirection.INSTANCE.m3160getEnterdhqQ8s());
        }
        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4108getBackEK5gGoQ()) ? true : Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4182getEscapeEK5gGoQ())) {
            return FocusDirection.m3150boximpl(FocusDirection.INSTANCE.m3161getExitdhqQ8s());
        }
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // android.view.View
    public void getFocusedRect(Rect rect) {
        t0 t0Var;
        androidx.compose.ui.geometry.Rect focusRect = getFocusOwner().getFocusRect();
        if (focusRect != null) {
            rect.left = t7.a.M(focusRect.getLeft());
            rect.top = t7.a.M(focusRect.getTop());
            rect.right = t7.a.M(focusRect.getRight());
            rect.bottom = t7.a.M(focusRect.getBottom());
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            super.getFocusedRect(rect);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public FontFamily.Resolver getFontFamilyResolver() {
        return (FontFamily.Resolver) this.fontFamilyResolver.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public Font.ResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    @Override // androidx.compose.ui.node.Owner
    public HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean getHasPendingMeasureOrLayout() {
        return this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout();
    }

    @Override // androidx.compose.ui.node.Owner
    public InputModeManager getInputModeManager() {
        return this._inputModeManager;
    }

    /* JADX INFO: renamed from: getLastMatrixRecalculationAnimationTime$ui_release, reason: from getter */
    public final long getLastMatrixRecalculationAnimationTime() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View, android.view.ViewParent, androidx.compose.ui.node.Owner
    public LayoutDirection getLayoutDirection() {
        return (LayoutDirection) this.layoutDirection.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public long getMeasureIteration() {
        return this.measureAndLayoutDelegate.getMeasureIteration();
    }

    @Override // androidx.compose.ui.node.Owner
    public ModifierLocalManager getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public Placeable.PlacementScope getPlacementScope() {
        return PlaceableKt.PlacementScope(this);
    }

    @Override // androidx.compose.ui.node.Owner
    public PointerIconService getPointerIconService() {
        return this.pointerIconService;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNode getRoot() {
        return this.root;
    }

    @Override // androidx.compose.ui.node.Owner
    public RootForTest getRootForTest() {
        return this.rootForTest;
    }

    @Override // androidx.compose.ui.node.RootForTest
    public SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    @Override // androidx.compose.ui.node.Owner
    public boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    @Override // androidx.compose.ui.node.Owner
    public SoftwareKeyboardController getSoftwareKeyboardController() {
        return this.softwareKeyboardController;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public TextInputService getTextInputService() {
        return this.textInputService;
    }

    @Override // androidx.compose.ui.node.Owner
    public TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public View getView() {
        return this;
    }

    @Override // androidx.compose.ui.node.Owner
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public WindowInfo getWindowInfo() {
        return this._windowInfo;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public void invalidateDescendants() {
        invalidateLayers(getRoot());
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean isLifecycleInResumedState() {
        y lifecycleOwner;
        androidx.lifecycle.r lifecycle;
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        return ((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) ? null : lifecycle.getCurrentState()) == androidx.lifecycle.q.f3270o;
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: localToScreen-58bKbWc */
    public void mo4586localToScreen58bKbWc(float[] localTransform) {
        recalculateWindowPosition();
        Matrix.m3701timesAssign58bKbWc(localTransform, this.viewToWindowMatrix);
        AndroidComposeView_androidKt.m4940preTranslatecG2Xzmc(localTransform, Offset.m3236getXimpl(this.windowPosition), Offset.m3237getYimpl(this.windowPosition), this.tmpMatrix);
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo4587localToScreenMKHz9U(long localPosition) {
        recalculateWindowPosition();
        long jM3690mapMKHz9U = Matrix.m3690mapMKHz9U(this.viewToWindowMatrix, localPosition);
        return OffsetKt.Offset(Offset.m3236getXimpl(this.windowPosition) + Offset.m3236getXimpl(jM3690mapMKHz9U), Offset.m3237getYimpl(this.windowPosition) + Offset.m3237getYimpl(jM3690mapMKHz9U));
    }

    @Override // androidx.compose.ui.node.Owner
    public void measureAndLayout(boolean sendPointerUpdate) {
        r7.a<t0> aVar;
        if (this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout() || this.measureAndLayoutDelegate.getHasPendingOnPositionedCallbacks()) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (sendPointerUpdate) {
                try {
                    aVar = this.resendMotionEventOnLayout;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                aVar = null;
            }
            if (this.measureAndLayoutDelegate.measureAndLayout(aVar)) {
                requestLayout();
            }
            MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
            Trace.endSection();
        }
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw */
    public void mo4899measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            this.measureAndLayoutDelegate.m4793measureAndLayout0kLqBqw(layoutNode, constraints);
            if (!this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout()) {
                MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
            }
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void measureAndLayoutForTest() {
        androidx.compose.ui.node.e.f(this, false, 1, null);
    }

    public final void notifyLayerIsDirty$ui_release(OwnedLayer layer, boolean isDirty) {
        if (!isDirty) {
            if (this.isDrawingContent) {
                return;
            }
            this.dirtyLayers.remove(layer);
            List<OwnedLayer> list = this.postponedDirtyLayers;
            if (list != null) {
                list.remove(layer);
                return;
            }
            return;
        }
        if (!this.isDrawingContent) {
            this.dirtyLayers.add(layer);
            return;
        }
        List arrayList = this.postponedDirtyLayers;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.postponedDirtyLayers = arrayList;
        }
        arrayList.add(layer);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onAttach(LayoutNode node) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        y lifecycleOwner;
        androidx.lifecycle.r lifecycle;
        AndroidAutofill androidAutofill;
        super.onAttachedToWindow();
        invalidateLayoutNodeMeasurement(getRoot());
        invalidateLayers(getRoot());
        getSnapshotObserver().startObserving$ui_release();
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.register(androidAutofill);
        }
        y yVarE = z0.e(this);
        b2.g gVarK = f4.f.k(this);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners == null || (yVarE != null && gVarK != null && (yVarE != viewTreeOwners.getLifecycleOwner() || gVarK != viewTreeOwners.getLifecycleOwner()))) {
            if (yVarE == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (gVarK == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
            yVarE.getLifecycle().addObserver(this);
            ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(yVarE, gVarK);
            set_viewTreeOwners(viewTreeOwners2);
            r7.l<? super ViewTreeOwners, t0> lVar = this.onViewTreeOwnersAvailable;
            if (lVar != null) {
                lVar.invoke(viewTreeOwners2);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        this._inputModeManager.m4087setInputModeiuPiT84(isInTouchMode() ? InputMode.INSTANCE.m4084getTouchaOaMEAU() : InputMode.INSTANCE.m4083getKeyboardaOaMEAU());
        getViewTreeOwners().getLifecycleOwner().getLifecycle().addObserver(this);
        getViewTreeOwners().getLifecycleOwner().getLifecycle().addObserver(this.composeAccessibilityDelegate);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
        if (Build.VERSION.SDK_INT >= 31) {
            AndroidComposeViewTranslationCallbackS.INSTANCE.setViewTranslationCallback(this, new AndroidComposeViewTranslationCallback());
        }
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m3116getCurrentSessionimpl(this.textInputSessionMutex);
        return androidPlatformTextInputSession == null ? this.legacyTextInputServiceAndroid.getEditorHasFocus() : androidPlatformTextInputSession.isReadyForConnection();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.density = AndroidDensity_androidKt.Density(getContext());
        if (getFontWeightAdjustmentCompat(newConfig) != this.currentFontWeightAdjustment) {
            this.currentFontWeightAdjustment = getFontWeightAdjustmentCompat(newConfig);
            setFontFamilyResolver(FontFamilyResolver_androidKt.createFontFamilyResolver(getContext()));
        }
        this.configurationChangeObserver.invoke(newConfig);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(y yVar) {
        androidx.lifecycle.e.a(this, yVar);
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        AndroidPlatformTextInputSession androidPlatformTextInputSession = (AndroidPlatformTextInputSession) SessionMutex.m3116getCurrentSessionimpl(this.textInputSessionMutex);
        return androidPlatformTextInputSession == null ? this.legacyTextInputServiceAndroid.createInputConnection(outAttrs) : androidPlatformTextInputSession.createInputConnection(outAttrs);
    }

    @Override // android.view.View
    public void onCreateVirtualViewTranslationRequests(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        this.composeAccessibilityDelegate.onCreateVirtualViewTranslationRequests$ui_release(virtualIds, supportedFormats, requestsCollector);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onDestroy(y yVar) {
        androidx.lifecycle.e.b(this, yVar);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onDetach(LayoutNode node) {
        this.measureAndLayoutDelegate.onNodeDetached(node);
        requestClearInvalidObservations();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AndroidAutofill androidAutofill;
        y lifecycleOwner;
        androidx.lifecycle.r lifecycle;
        y lifecycleOwner2;
        androidx.lifecycle.r lifecycle2;
        super.onDetachedFromWindow();
        getSnapshotObserver().stopObserving$ui_release();
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null && (lifecycleOwner2 = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle2 = lifecycleOwner2.getLifecycle()) != null) {
            lifecycle2.removeObserver(this);
        }
        ViewTreeOwners viewTreeOwners2 = getViewTreeOwners();
        if (viewTreeOwners2 != null && (lifecycleOwner = viewTreeOwners2.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            lifecycle.removeObserver(this.composeAccessibilityDelegate);
        }
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.unregister(androidAutofill);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
        if (Build.VERSION.SDK_INT >= 31) {
            AndroidComposeViewTranslationCallbackS.INSTANCE.clearViewTranslationCallback(this);
        }
    }

    @Override // android.view.View
    public void onDraw(android.graphics.Canvas canvas) {
    }

    @Override // androidx.compose.ui.node.Owner
    public void onEndApplyChanges() {
        if (this.observationClearRequested) {
            getSnapshotObserver().clearInvalidObservations$ui_release();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        while (this.endApplyChangesListeners.isNotEmpty()) {
            int size = this.endApplyChangesListeners.getSize();
            for (int i10 = 0; i10 < size; i10++) {
                r7.a<t0> aVar = this.endApplyChangesListeners.getContent()[i10];
                this.endApplyChangesListeners.set(i10, null);
                if (aVar != null) {
                    aVar.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, size);
        }
    }

    @Override // android.view.View
    public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(FocusTag, "Owner FocusChanged(" + gainFocus + ')');
        FocusTransactionManager focusTransactionManager = getFocusOwner().getFocusTransactionManager();
        focusTransactionManager.cancellationListener.add(new C06171(gainFocus, this));
        if (focusTransactionManager.ongoingTransaction) {
            if (gainFocus) {
                getFocusOwner().takeFocus();
                return;
            } else {
                getFocusOwner().releaseFocus();
                return;
            }
        }
        try {
            focusTransactionManager.beginTransaction();
            if (gainFocus) {
                getFocusOwner().takeFocus();
            } else {
                getFocusOwner().releaseFocus();
            }
            focusTransactionManager.commitTransaction();
        } catch (Throwable th) {
            focusTransactionManager.commitTransaction();
            throw th;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int l10, int t2, int r4, int b10) throws Throwable {
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, r4 - l10, b10 - t2);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutChange(LayoutNode layoutNode) {
        this.composeAccessibilityDelegate.onLayoutChange$ui_release(layoutNode);
    }

    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(getRoot());
            }
            long jM4920convertMeasureSpecI7RO_PI = m4920convertMeasureSpecI7RO_PI(widthMeasureSpec);
            long jM4920convertMeasureSpecI7RO_PI2 = m4920convertMeasureSpecI7RO_PI(heightMeasureSpec);
            long jConstraints = ConstraintsKt.Constraints((int) (jM4920convertMeasureSpecI7RO_PI >>> 32), (int) (jM4920convertMeasureSpecI7RO_PI & 4294967295L), (int) (jM4920convertMeasureSpecI7RO_PI2 >>> 32), (int) (4294967295L & jM4920convertMeasureSpecI7RO_PI2));
            Constraints constraints = this.onMeasureConstraints;
            boolean zM5639equalsimpl0 = false;
            if (constraints == null) {
                this.onMeasureConstraints = Constraints.m5634boximpl(jConstraints);
                this.wasMeasuredWithMultipleConstraints = false;
            } else {
                if (constraints != null) {
                    zM5639equalsimpl0 = Constraints.m5639equalsimpl0(constraints.getValue(), jConstraints);
                }
                if (!zM5639equalsimpl0) {
                    this.wasMeasuredWithMultipleConstraints = true;
                }
            }
            this.measureAndLayoutDelegate.m4794updateRootConstraintsBRTryo0(jConstraints);
            this.measureAndLayoutDelegate.measureOnly();
            setMeasuredDimension(getRoot().getWidth(), getRoot().getHeight());
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(getRoot().getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().getHeight(), 1073741824));
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onPause(y yVar) {
        androidx.lifecycle.e.c(this, yVar);
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure structure, int flags) {
        AndroidAutofill androidAutofill;
        if (!autofillSupported() || structure == null || (androidAutofill = this._autofill) == null) {
            return;
        }
        AndroidAutofill_androidKt.populateViewStructure(androidAutofill, structure);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestMeasure(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest, boolean scheduleMeasureAndLayout) {
        if (affectsLookahead) {
            if (this.measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, forceRequest) && scheduleMeasureAndLayout) {
                scheduleMeasureAndLayout(layoutNode);
                return;
            }
            return;
        }
        if (this.measureAndLayoutDelegate.requestRemeasure(layoutNode, forceRequest) && scheduleMeasureAndLayout) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestRelayout(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest) {
        if (affectsLookahead) {
            if (this.measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, forceRequest)) {
                scheduleMeasureAndLayout$default(this, null, 1, null);
            }
        } else if (this.measureAndLayoutDelegate.requestRelayout(layoutNode, forceRequest)) {
            scheduleMeasureAndLayout$default(this, null, 1, null);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(y owner) {
        setShowLayoutBounds(INSTANCE.getIsShowingLayoutBounds());
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirectionLayoutDirectionFromInt = AndroidComposeView_androidKt.layoutDirectionFromInt(layoutDirection);
            setLayoutDirection(layoutDirectionLayoutDirectionFromInt);
            getFocusOwner().setLayoutDirection(layoutDirectionLayoutDirectionFromInt);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onSemanticsChange() {
        this.composeAccessibilityDelegate.onSemanticsChange$ui_release();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onStart(y yVar) {
        androidx.lifecycle.e.e(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onStop(y yVar) {
        androidx.lifecycle.e.f(this, yVar);
    }

    @Override // android.view.View
    public void onVirtualViewTranslationResponses(LongSparseArray<ViewTranslationResponse> response) {
        this.composeAccessibilityDelegate.onVirtualViewTranslationResponses$ui_release(response);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        boolean isShowingLayoutBounds;
        this._windowInfo.setWindowFocused(hasWindowFocus);
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus || getShowLayoutBounds() == (isShowingLayoutBounds = INSTANCE.getIsShowingLayoutBounds())) {
            return;
        }
        setShowLayoutBounds(isShowingLayoutBounds);
        invalidateDescendants();
    }

    public final boolean recycle$ui_release(OwnedLayer layer) {
        if (this.viewLayersContainer != null) {
            ViewLayer.INSTANCE.getShouldUseDispatchDraw();
        }
        this.layerCache.push(layer);
        return true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnEndApplyChangesListener(r7.a<t0> listener) {
        if (this.endApplyChangesListeners.contains(listener)) {
            return;
        }
        this.endApplyChangesListeners.add(listener);
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        this.measureAndLayoutDelegate.registerOnLayoutCompletedListener(listener);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    public final void removeAndroidView(AndroidViewHolder view) {
        registerOnEndApplyChangesListener(new C06181(view));
    }

    public final void requestClearInvalidObservations() {
        this.observationClearRequested = true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.measureAndLayoutDelegate.requestOnPositionedCallback(layoutNode);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo4588screenToLocalMKHz9U(long positionOnScreen) {
        recalculateWindowPosition();
        return Matrix.m3690mapMKHz9U(this.windowToViewMatrix, OffsetKt.Offset(Offset.m3236getXimpl(positionOnScreen) - Offset.m3236getXimpl(this.windowPosition), Offset.m3237getYimpl(positionOnScreen) - Offset.m3237getYimpl(this.windowPosition)));
    }

    @Override // androidx.compose.ui.node.RootForTest
    /* JADX INFO: renamed from: sendKeyEvent-ZmokQxo */
    public boolean mo4900sendKeyEventZmokQxo(KeyEvent keyEvent) {
        return getFocusOwner().mo3169dispatchInterceptedSoftKeyboardEventZmokQxo(keyEvent) || getFocusOwner().mo3170dispatchKeyEventZmokQxo(keyEvent);
    }

    public final void setConfigurationChangeObserver(r7.l<? super Configuration, t0> lVar) {
        this.configurationChangeObserver = lVar;
    }

    public final void setLastMatrixRecalculationAnimationTime$ui_release(long j10) {
        this.lastMatrixRecalculationAnimationTime = j10;
    }

    public final void setOnViewTreeOwnersAvailable(r7.l<? super ViewTreeOwners, t0> callback) {
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            callback.invoke(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.onViewTreeOwnersAvailable = callback;
    }

    @Override // androidx.compose.ui.node.Owner
    public void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.platform.PlatformTextInputSessionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object textInputSession(r7.p<? super androidx.compose.ui.platform.PlatformTextInputSessionScope, ? super d7.d<?>, ? extends java.lang.Object> r5, d7.d<?> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.ui.platform.AndroidComposeView.C06191
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$1 r0 = (androidx.compose.ui.platform.AndroidComposeView.C06191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$1 r0 = new androidx.compose.ui.platform.AndroidComposeView$textInputSession$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L29:
            k2.c.G(r6)
            goto L42
        L2d:
            k2.c.G(r6)
            java.util.concurrent.atomic.AtomicReference r6 = r4.textInputSessionMutex
            androidx.compose.ui.platform.AndroidComposeView$textInputSession$2 r1 = new androidx.compose.ui.platform.AndroidComposeView$textInputSession$2
            r1.<init>()
            r0.label = r2
            java.lang.Object r5 = androidx.compose.ui.SessionMutex.m3119withSessionCancellingPreviousimpl(r6, r1, r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L42
            return r6
        L42:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.textInputSession(r7.p, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    private final void recalculateWindowPosition(MotionEvent motionEvent) {
        this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
        recalculateWindowViewTransforms();
        long jM3690mapMKHz9U = Matrix.m3690mapMKHz9U(this.viewToWindowMatrix, OffsetKt.Offset(motionEvent.getX(), motionEvent.getY()));
        this.windowPosition = OffsetKt.Offset(motionEvent.getRawX() - Offset.m3236getXimpl(jM3690mapMKHz9U), motionEvent.getRawY() - Offset.m3237getYimpl(jM3690mapMKHz9U));
    }
}
