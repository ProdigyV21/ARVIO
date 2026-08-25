package androidx.compose.runtime;

import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import r7.p;
import t.c0;
import t.d0;
import t.e0;
import t.h0;
import t.k0;
import t.u;
import t.z;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002Ê\u0001B'\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\u0011\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\"\u0010\u0013\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0016¢\u0006\u0004\b\u001e\u0010\u0012J\u000f\u0010\u001f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010$\u001a\u00020\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016¢\u0006\u0004\b$\u0010%J\u001d\u0010'\u001a\u00020&2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016¢\u0006\u0004\b'\u0010(J\u001d\u0010*\u001a\u00020\u000e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\"H\u0016¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\"H\u0016¢\u0006\u0004\b/\u0010.J\u000f\u00100\u001a\u00020&H\u0016¢\u0006\u0004\b0\u00101J+\u00106\u001a\u00020\u000e2\u001a\u00105\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u000204\u0012\u0006\u0012\u0004\u0018\u0001040302H\u0016¢\u0006\u0004\b6\u00107J\u0017\u0010:\u001a\u00020\u000e2\u0006\u00109\u001a\u000208H\u0016¢\u0006\u0004\b:\u0010;J\u000f\u0010<\u001a\u00020\u000eH\u0016¢\u0006\u0004\b<\u0010 J\u000f\u0010=\u001a\u00020\u000eH\u0016¢\u0006\u0004\b=\u0010 J\u000f\u0010>\u001a\u00020\u000eH\u0016¢\u0006\u0004\b>\u0010 J\u000f\u0010?\u001a\u00020\u000eH\u0016¢\u0006\u0004\b?\u0010 J\u000f\u0010@\u001a\u00020\u000eH\u0016¢\u0006\u0004\b@\u0010 J\u000f\u0010A\u001a\u00020\u000eH\u0016¢\u0006\u0004\bA\u0010 J5\u0010E\u001a\u00028\u0000\"\u0004\b\u0000\u0010B2\b\u0010C\u001a\u0004\u0018\u00010\u00012\u0006\u0010D\u001a\u00020\u001a2\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016¢\u0006\u0004\bE\u0010FJ!\u0010K\u001a\u00020J2\u0006\u0010H\u001a\u00020G2\b\u0010I\u001a\u0004\u0018\u00010\"H\u0016¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020GH\u0016¢\u0006\u0004\bM\u0010NJ%\u0010Q\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010O2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000PH\u0016¢\u0006\u0004\bQ\u0010RJ\u001f\u0010U\u001a\u00020\u000e2\u0006\u0010I\u001a\u00020\"2\u0006\u0010H\u001a\u00020GH\u0000¢\u0006\u0004\bS\u0010TJ\u001b\u0010Y\u001a\u00020\u000e2\n\u00109\u001a\u0006\u0012\u0002\b\u00030VH\u0000¢\u0006\u0004\bW\u0010XJ\u000f\u0010Z\u001a\u00020\u000eH\u0016¢\u0006\u0004\bZ\u0010 J\u000f\u0010]\u001a\u00020\u001aH\u0000¢\u0006\u0004\b[\u0010\\J\"\u0010^\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0002¢\u0006\u0004\b^\u0010\u0012J\u000f\u0010_\u001a\u00020\u000eH\u0002¢\u0006\u0004\b_\u0010 J\u000f\u0010`\u001a\u00020\u000eH\u0002¢\u0006\u0004\b`\u0010 J\u001f\u0010b\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\"2\u0006\u0010a\u001a\u00020&H\u0002¢\u0006\u0004\bb\u0010cJ%\u0010b\u001a\u00020\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010a\u001a\u00020&H\u0002¢\u0006\u0004\bb\u0010dJ\u000f\u0010e\u001a\u00020\u000eH\u0002¢\u0006\u0004\be\u0010 J\u0017\u0010f\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\"H\u0002¢\u0006\u0004\bf\u0010.J\u0017\u0010i\u001a\u00020\u000e2\u0006\u0010h\u001a\u00020gH\u0002¢\u0006\u0004\bi\u0010jJE\u0010o\u001a\u00028\u0000\"\u0004\b\u0000\u0010O2-\u0010)\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\"0l¢\u0006\f\bm\u0012\b\bn\u0012\u0004\b\b(h\u0012\u0004\u0012\u00028\u00000kH\u0082\b¢\u0006\u0004\bo\u0010pJ$\u0010q\u001a\u00028\u0000\"\u0004\b\u0000\u0010O2\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0082\b¢\u0006\u0004\bq\u0010rJ!\u0010s\u001a\u00020&2\u0006\u0010H\u001a\u00020G2\b\u0010I\u001a\u0004\u0018\u00010\"H\u0002¢\u0006\u0004\bs\u0010tJ)\u0010w\u001a\u00020J2\u0006\u0010H\u001a\u00020G2\u0006\u0010v\u001a\u00020u2\b\u0010I\u001a\u0004\u0018\u00010\"H\u0002¢\u0006\u0004\bw\u0010xJ\u001b\u0010y\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\"0lH\u0002¢\u0006\u0004\by\u0010zJ\u0017\u0010}\u001a\u00020\u000e2\u0006\u0010|\u001a\u00020{H\u0002¢\u0006\u0004\b}\u0010~J$\u0010\u007f\u001a\u00028\u0000\"\u0004\b\u0000\u0010O2\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0082\b¢\u0006\u0004\b\u007f\u0010rJ\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0005\b\u0015\u0010\u0080\u0001R\u0015\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0006\u0010\u0081\u0001R\u0019\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00078\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\b\u0010\u0082\u0001R-\u0010\u0085\u0001\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0083\u0001j\u000b\u0012\u0006\u0012\u0004\u0018\u00010\"`\u0084\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0017\u0010\u0087\u0001\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R&\u0010\u008b\u0001\u001a\n\u0012\u0005\u0012\u00030\u008a\u00010\u0089\u00018\u0002X\u0082\u0004¢\u0006\u000f\n\u0006\b\u008b\u0001\u0010\u008c\u0001\u0012\u0005\b\u008d\u0001\u0010 R$\u0010|\u001a\u00020{8\u0000X\u0080\u0004¢\u0006\u0016\n\u0005\b|\u0010\u008e\u0001\u0012\u0005\b\u0091\u0001\u0010 \u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R#\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020G0l8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R\u001e\u0010\u0095\u0001\u001a\t\u0012\u0004\u0012\u00020G0\u0094\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u001e\u0010\u0097\u0001\u001a\t\u0012\u0004\u0012\u00020G0\u0094\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0096\u0001R'\u0010\u0098\u0001\u001a\u0012\u0012\u0004\u0012\u00020\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030V0l8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0093\u0001R\u0015\u0010h\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\bh\u0010\u0099\u0001R\u0017\u0010\u009a\u0001\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u0099\u0001R#\u0010\u009b\u0001\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020G0l8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u0093\u0001R%\u0010\u009c\u0001\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\"0l8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u0093\u0001R/\u0010\u009d\u0001\u001a\u00020&8\u0000@\u0000X\u0080\u000e¢\u0006\u001e\n\u0006\b\u009d\u0001\u0010\u009e\u0001\u0012\u0005\b¢\u0001\u0010 \u001a\u0005\b\u009f\u0001\u00101\"\u0006\b \u0001\u0010¡\u0001R\u001b\u0010£\u0001\u001a\u0004\u0018\u00010\u00008\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b£\u0001\u0010¤\u0001R\u0019\u0010¥\u0001\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R \u0010¨\u0001\u001a\u00030§\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b¨\u0001\u0010©\u0001\u001a\u0006\bª\u0001\u0010«\u0001R\u0018\u0010\u00ad\u0001\u001a\u00030¬\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0001\u0010®\u0001R\u0019\u0010¯\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¯\u0001\u0010°\u0001R\u001b\u0010±\u0001\u001a\u00020&8\u0006¢\u0006\u000f\n\u0006\b±\u0001\u0010\u009e\u0001\u001a\u0005\b±\u0001\u00101R\u0019\u0010²\u0001\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b²\u0001\u0010\u009e\u0001R3\u0010³\u0001\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001\"\u0005\b·\u0001\u0010\u0012R\u001d\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020\"0!8AX\u0080\u0004¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u001d\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\"0!8AX\u0080\u0004¢\u0006\b\u001a\u0006\b»\u0001\u0010¹\u0001R\u001d\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020G028AX\u0080\u0004¢\u0006\b\u001a\u0006\b½\u0001\u0010¾\u0001R\u0013\u0010\n\u001a\u00020\t8F¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u0016\u0010Â\u0001\u001a\u00020&8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÂ\u0001\u00101R\u0016\u0010Ã\u0001\u001a\u00020&8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÃ\u0001\u00101R\u0016\u0010Å\u0001\u001a\u00020&8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÄ\u0001\u00101R\u0016\u0010Ç\u0001\u001a\u00020&8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÆ\u0001\u00101R\u0016\u0010É\u0001\u001a\u00020&8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÈ\u0001\u00101¨\u0006Ë\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "Landroidx/compose/runtime/CompositionContext;", "parent", "Landroidx/compose/runtime/Applier;", "applier", "Ld7/j;", "recomposeContext", "<init>", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Ld7/j;)V", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "setContent", "(Lr7/p;)V", "setContentWithReuse", "Landroidx/compose/runtime/tooling/CompositionObserver;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observe$runtime_release", "(Landroidx/compose/runtime/tooling/CompositionObserver;)Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observe", "", "key", "invalidateGroupsWithKey", "(I)V", "composeContent", "dispose", "()V", "", "", "values", "recordModificationsOf", "(Ljava/util/Set;)V", "", "observesAnyOf", "(Ljava/util/Set;)Z", "block", "prepareCompose", "(Lr7/a;)V", "value", "recordReadOf", "(Ljava/lang/Object;)V", "recordWriteOf", "recompose", "()Z", "", "Lx6/x;", "Landroidx/compose/runtime/MovableContentStateReference;", "references", "insertMovableContent", "(Ljava/util/List;)V", "Landroidx/compose/runtime/MovableContentState;", "state", "disposeUnusedMovableContent", "(Landroidx/compose/runtime/MovableContentState;)V", "applyChanges", "applyLateChanges", "changesApplied", "abandonChanges", "invalidateAll", "verifyConsistent", "R", "to", "groupIndex", "delegateInvalidations", "(Landroidx/compose/runtime/ControlledComposition;ILr7/a;)Ljava/lang/Object;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "scope", "instance", "Landroidx/compose/runtime/InvalidationResult;", "invalidate", "(Landroidx/compose/runtime/RecomposeScopeImpl;Ljava/lang/Object;)Landroidx/compose/runtime/InvalidationResult;", "recomposeScopeReleased", "(Landroidx/compose/runtime/RecomposeScopeImpl;)V", "T", "Landroidx/compose/runtime/CompositionServiceKey;", "getCompositionService", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "removeObservation$runtime_release", "(Ljava/lang/Object;Landroidx/compose/runtime/RecomposeScopeImpl;)V", "removeObservation", "Landroidx/compose/runtime/DerivedState;", "removeDerivedStateObservation$runtime_release", "(Landroidx/compose/runtime/DerivedState;)V", "removeDerivedStateObservation", "deactivate", "composerStacksSizes$runtime_release", "()I", "composerStacksSizes", "composeInitial", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "forgetConditionalScopes", "addPendingInvalidationsLocked", "(Ljava/lang/Object;Z)V", "(Ljava/util/Set;Z)V", "cleanUpDerivedStateObservations", "invalidateScopeOfLocked", "Landroidx/compose/runtime/changelist/ChangeList;", "changes", "applyChangesInLocked", "(Landroidx/compose/runtime/changelist/ChangeList;)V", "Lkotlin/Function1;", "Landroidx/compose/runtime/collection/ScopeMap;", "Lx6/y;", ContentDisposition.Parameters.Name, "guardInvalidationsLocked", "(Lr7/l;)Ljava/lang/Object;", "guardChanges", "(Lr7/a;)Ljava/lang/Object;", "tryImminentInvalidation", "(Landroidx/compose/runtime/RecomposeScopeImpl;Ljava/lang/Object;)Z", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "invalidateChecked", "(Landroidx/compose/runtime/RecomposeScopeImpl;Landroidx/compose/runtime/Anchor;Ljava/lang/Object;)Landroidx/compose/runtime/InvalidationResult;", "takeInvalidations", "()Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/SlotTable;", "slotTable", "validateRecomposeScopeAnchors", "(Landroidx/compose/runtime/SlotTable;)V", "trackAbandonedValues", "()Landroidx/compose/runtime/tooling/CompositionObserver;", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/Applier;", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "lock", "Ljava/lang/Object;", "", "Landroidx/compose/runtime/RememberObserver;", "abandonSet", "Ljava/util/Set;", "getAbandonSet$annotations", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime_release$annotations", "observations", "Landroidx/compose/runtime/collection/ScopeMap;", "Lt/d0;", "invalidatedScopes", "Lt/d0;", "conditionallyInvalidatedScopes", "derivedStates", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "observationsProcessed", "invalidations", "pendingInvalidScopes", "Z", "getPendingInvalidScopes$runtime_release", "setPendingInvalidScopes$runtime_release", "(Z)V", "getPendingInvalidScopes$runtime_release$annotations", "invalidationDelegate", "Landroidx/compose/runtime/CompositionImpl;", "invalidationDelegateGroup", "I", "Landroidx/compose/runtime/CompositionObserverHolder;", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "Landroidx/compose/runtime/ComposerImpl;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "_recomposeContext", "Ld7/j;", "isRoot", "disposed", "composable", "Lr7/p;", "getComposable", "()Lr7/p;", "setComposable", "getObservedObjects$runtime_release", "()Ljava/util/Set;", "observedObjects", "getDerivedStateDependencies$runtime_release", "derivedStateDependencies", "getConditionalScopes$runtime_release", "()Ljava/util/List;", "conditionalScopes", "getRecomposeContext", "()Ld7/j;", "isComposing", "isDisposed", "getHasPendingChanges", "hasPendingChanges", "getHasInvalidations", "hasInvalidations", "getAreChildrenComposing", "areChildrenComposing", "RememberEventDispatcher", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices {
    public static final int $stable = 8;
    private final d7.j _recomposeContext;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ChangeList changes;
    private p<? super Composer, ? super Integer, t0> composable;
    private final ComposerImpl composer;
    private final d0 conditionallyInvalidatedScopes;
    private final ScopeMap<Object, DerivedState<?>> derivedStates;
    private boolean disposed;
    private final d0 invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private ScopeMap<RecomposeScopeImpl, Object> invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final ScopeMap<Object, RecomposeScopeImpl> observations;
    private final ScopeMap<Object, RecomposeScopeImpl> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private final SlotTable slotTable;

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J/\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0014\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0018\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J/\u0010\u001b\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ/\u0010\u001d\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001d\u0010\u001cJ\r\u0010\u001e\u001a\u00020\r¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\r¢\u0006\u0004\b \u0010\u001fJ\r\u0010!\u001a\u00020\r¢\u0006\u0004\b!\u0010\u001fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\"R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010$R \u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00160#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010$R\u001e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010(R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010$R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010,¨\u0006."}, d2 = {"Landroidx/compose/runtime/CompositionImpl$RememberEventDispatcher;", "Landroidx/compose/runtime/RememberManager;", "", "Landroidx/compose/runtime/RememberObserver;", "abandoning", "<init>", "(Ljava/util/Set;)V", "", "instance", "", "endRelativeOrder", "priority", "endRelativeAfter", "Lx6/t0;", "recordLeaving", "(Ljava/lang/Object;III)V", "processPendingLeaving", "(I)V", "remembering", "(Landroidx/compose/runtime/RememberObserver;)V", "forgetting", "(Landroidx/compose/runtime/RememberObserver;III)V", "Lkotlin/Function0;", "effect", "sideEffect", "(Lr7/a;)V", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "deactivating", "(Landroidx/compose/runtime/ComposeNodeLifecycleCallback;III)V", "releasing", "dispatchRememberObservers", "()V", "dispatchSideEffects", "dispatchAbandons", "Ljava/util/Set;", "", "Ljava/util/List;", "leaving", "sideEffects", "Lt/d0;", "Lt/d0;", "pending", "Lt/u;", "priorities", "Lt/u;", "afters", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RememberEventDispatcher implements RememberManager {
        private final Set<RememberObserver> abandoning;
        private d0 releasing;
        private final List<RememberObserver> remembering = new ArrayList();
        private final List<Object> leaving = new ArrayList();
        private final List<r7.a<t0>> sideEffects = new ArrayList();
        private final List<Object> pending = new ArrayList();
        private final u priorities = new u();
        private final u afters = new u();

        public RememberEventDispatcher(Set<RememberObserver> set) {
            this.abandoning = set;
        }

        private final void processPendingLeaving(int endRelativeOrder) {
            if (this.pending.isEmpty()) {
                return;
            }
            int i10 = 0;
            ArrayList arrayListG = null;
            int i11 = 0;
            u uVar = null;
            u uVar2 = null;
            while (true) {
                u uVar3 = this.afters;
                if (i11 >= uVar3.f21840b) {
                    break;
                }
                if (endRelativeOrder <= uVar3.a(i11)) {
                    Object objRemove = this.pending.remove(i11);
                    int iC = this.afters.c(i11);
                    int iC2 = this.priorities.c(i11);
                    if (arrayListG == null) {
                        arrayListG = t7.a.G(objRemove);
                        uVar2 = new u();
                        uVar2.b(iC);
                        uVar = new u();
                        uVar.b(iC2);
                    } else {
                        arrayListG.add(objRemove);
                        uVar2.b(iC);
                        uVar.b(iC2);
                    }
                } else {
                    i11++;
                }
            }
            if (arrayListG != null) {
                int size = arrayListG.size() - 1;
                while (i10 < size) {
                    int i12 = i10 + 1;
                    int size2 = arrayListG.size();
                    for (int i13 = i12; i13 < size2; i13++) {
                        int iA = uVar2.a(i10);
                        int iA2 = uVar2.a(i13);
                        if (iA < iA2 || (iA2 == iA && uVar.a(i10) < uVar.a(i13))) {
                            CompositionKt.swap(arrayListG, i10, i13);
                            CompositionKt.swap(uVar, i10, i13);
                            CompositionKt.swap(uVar2, i10, i13);
                        }
                    }
                    i10 = i12;
                }
                this.leaving.addAll(arrayListG);
            }
        }

        private final void recordLeaving(Object instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            processPendingLeaving(endRelativeOrder);
            if (endRelativeAfter < 0 || endRelativeAfter >= endRelativeOrder) {
                this.leaving.add(instance);
                return;
            }
            this.pending.add(instance);
            this.priorities.b(priority);
            this.afters.b(endRelativeAfter);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void deactivating(ComposeNodeLifecycleCallback instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            recordLeaving(instance, endRelativeOrder, priority, endRelativeAfter);
        }

        public final void dispatchAbandons() {
            if (this.abandoning.isEmpty()) {
                return;
            }
            Object objBeginSection = Trace.INSTANCE.beginSection("Compose:abandons");
            try {
                Iterator<RememberObserver> it = this.abandoning.iterator();
                while (it.hasNext()) {
                    RememberObserver next = it.next();
                    it.remove();
                    next.onAbandoned();
                }
            } finally {
                Trace.INSTANCE.endSection(objBeginSection);
            }
        }

        public final void dispatchRememberObservers() {
            Object objBeginSection;
            processPendingLeaving(Integer.MIN_VALUE);
            if (!this.leaving.isEmpty()) {
                objBeginSection = Trace.INSTANCE.beginSection("Compose:onForgotten");
                try {
                    d0 d0Var = this.releasing;
                    int size = this.leaving.size();
                    while (true) {
                        size--;
                        if (-1 >= size) {
                            break;
                        }
                        Object obj = this.leaving.get(size);
                        if (obj instanceof RememberObserver) {
                            this.abandoning.remove(obj);
                            ((RememberObserver) obj).onForgotten();
                        }
                        if (obj instanceof ComposeNodeLifecycleCallback) {
                            if (d0Var == null || !d0Var.a(obj)) {
                                ((ComposeNodeLifecycleCallback) obj).onDeactivate();
                            } else {
                                ((ComposeNodeLifecycleCallback) obj).onRelease();
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (this.remembering.isEmpty()) {
                return;
            }
            objBeginSection = Trace.INSTANCE.beginSection("Compose:onRemembered");
            try {
                List<RememberObserver> list = this.remembering;
                int size2 = list.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    RememberObserver rememberObserver = list.get(i10);
                    this.abandoning.remove(rememberObserver);
                    rememberObserver.onRemembered();
                }
            } finally {
                Trace.INSTANCE.endSection(objBeginSection);
            }
        }

        public final void dispatchSideEffects() {
            if (this.sideEffects.isEmpty()) {
                return;
            }
            Object objBeginSection = Trace.INSTANCE.beginSection("Compose:sideeffects");
            try {
                List<r7.a<t0>> list = this.sideEffects;
                int size = list.size();
                for (int i10 = 0; i10 < size; i10++) {
                    list.get(i10).invoke();
                }
                this.sideEffects.clear();
                Trace.INSTANCE.endSection(objBeginSection);
            } catch (Throwable th) {
                Trace.INSTANCE.endSection(objBeginSection);
                throw th;
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            recordLeaving(instance, endRelativeOrder, priority, endRelativeAfter);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void releasing(ComposeNodeLifecycleCallback instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            d0 d0Var = this.releasing;
            if (d0Var == null) {
                int i10 = k0.f21841a;
                d0Var = new d0();
                this.releasing = d0Var;
            }
            d0Var.f21836b[d0Var.f(instance)] = instance;
            recordLeaving(instance, endRelativeOrder, priority, endRelativeAfter);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver instance) {
            this.remembering.add(instance);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(r7.a<t0> effect) {
            this.sideEffects.add(effect);
        }
    }

    public CompositionImpl(CompositionContext compositionContext, Applier<?> applier, d7.j jVar) {
        this.parent = compositionContext;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        c0 c0Var = new c0(new d0());
        this.abandonSet = c0Var;
        SlotTable slotTable = new SlotTable();
        if (compositionContext.getCollectingCallByInformation$runtime_release()) {
            slotTable.collectCalledByInformation();
        }
        if (compositionContext.getCollectingSourceInformation()) {
            slotTable.collectSourceInformation();
        }
        this.slotTable = slotTable;
        this.observations = new ScopeMap<>();
        this.invalidatedScopes = new d0();
        this.conditionallyInvalidatedScopes = new d0();
        this.derivedStates = new ScopeMap<>();
        ChangeList changeList = new ChangeList();
        this.changes = changeList;
        ChangeList changeList2 = new ChangeList();
        this.lateChanges = changeList2;
        this.observationsProcessed = new ScopeMap<>();
        this.invalidations = new ScopeMap<>();
        this.observerHolder = new CompositionObserverHolder(null, false, 3, null);
        ComposerImpl composerImpl = new ComposerImpl(applier, compositionContext, slotTable, c0Var, changeList, changeList2, this);
        compositionContext.registerComposer$runtime_release(composerImpl);
        this.composer = composerImpl;
        this._recomposeContext = jVar;
        this.isRoot = compositionContext instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m2946getLambda1$runtime_release();
    }

    private final void addPendingInvalidationsLocked(Object value, boolean forgetConditionalScopes) {
        Object objE = this.observations.getMap().e(value);
        if (objE == null) {
            return;
        }
        if (!(objE instanceof d0)) {
            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objE;
            if (this.observationsProcessed.remove(value, recomposeScopeImpl) || recomposeScopeImpl.invalidateForResult(value) == InvalidationResult.IGNORED) {
                return;
            }
            if (!recomposeScopeImpl.isConditional() || forgetConditionalScopes) {
                this.invalidatedScopes.d(recomposeScopeImpl);
                return;
            } else {
                this.conditionallyInvalidatedScopes.d(recomposeScopeImpl);
                return;
            }
        }
        d0 d0Var = (d0) objE;
        Object[] objArr = d0Var.f21836b;
        long[] jArr = d0Var.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) objArr[(i10 << 3) + i12];
                        if (!this.observationsProcessed.remove(value, recomposeScopeImpl2) && recomposeScopeImpl2.invalidateForResult(value) != InvalidationResult.IGNORED) {
                            if (!recomposeScopeImpl2.isConditional() || forgetConditionalScopes) {
                                this.invalidatedScopes.d(recomposeScopeImpl2);
                            } else {
                                this.conditionallyInvalidatedScopes.d(recomposeScopeImpl2);
                            }
                        }
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }

    private final void applyChangesInLocked(ChangeList changes) throws Throwable {
        RememberEventDispatcher rememberEventDispatcher;
        long[] jArr;
        int i10;
        RememberEventDispatcher rememberEventDispatcher2;
        long[] jArr2;
        long j10;
        char c10;
        long j11;
        int i11;
        boolean zB;
        RememberEventDispatcher rememberEventDispatcher3;
        RememberEventDispatcher rememberEventDispatcher4 = new RememberEventDispatcher(this.abandonSet);
        try {
            if (changes.isEmpty()) {
                if (this.lateChanges.isEmpty()) {
                    rememberEventDispatcher4.dispatchAbandons();
                    return;
                }
                return;
            }
            try {
                Trace trace = Trace.INSTANCE;
                Object objBeginSection = trace.beginSection("Compose:applyChanges");
                try {
                    this.applier.onBeginChanges();
                    SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                    int i12 = 0;
                    try {
                        changes.executeAndFlushAllPendingChanges(this.applier, slotWriterOpenWriter, rememberEventDispatcher4);
                        slotWriterOpenWriter.close(true);
                        this.applier.onEndChanges();
                        trace.endSection(objBeginSection);
                        rememberEventDispatcher4.dispatchRememberObservers();
                        rememberEventDispatcher4.dispatchSideEffects();
                        if (this.pendingInvalidScopes) {
                            Object objBeginSection2 = trace.beginSection("Compose:unobserve");
                            try {
                                this.pendingInvalidScopes = false;
                                z map = this.observations.getMap();
                                long[] jArr3 = map.f21881a;
                                int length = jArr3.length - 2;
                                if (length >= 0) {
                                    int i13 = 0;
                                    while (true) {
                                        long j12 = jArr3[i13];
                                        char c11 = 7;
                                        long j13 = -9187201950435737472L;
                                        if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i14 = 8;
                                            int i15 = 8 - ((~(i13 - length)) >>> 31);
                                            int i16 = i12;
                                            while (i16 < i15) {
                                                if ((j12 & 255) < 128) {
                                                    c10 = c11;
                                                    int i17 = (i13 << 3) + i16;
                                                    j11 = j13;
                                                    Object obj = map.f21882b[i17];
                                                    Object obj2 = map.f21883c[i17];
                                                    if (obj2 instanceof d0) {
                                                        d0 d0Var = (d0) obj2;
                                                        Object[] objArr = d0Var.f21836b;
                                                        long[] jArr4 = d0Var.f21835a;
                                                        int i18 = i14;
                                                        int length2 = jArr4.length - 2;
                                                        i10 = i16;
                                                        if (length2 >= 0) {
                                                            jArr2 = jArr3;
                                                            int i19 = 0;
                                                            while (true) {
                                                                long j14 = jArr4[i19];
                                                                j10 = j12;
                                                                if ((((~j14) << c10) & j14 & j11) != j11) {
                                                                    int i20 = 8 - ((~(i19 - length2)) >>> 31);
                                                                    int i21 = 0;
                                                                    while (i21 < i20) {
                                                                        if ((j14 & 255) < 128) {
                                                                            rememberEventDispatcher3 = rememberEventDispatcher4;
                                                                            int i22 = (i19 << 3) + i21;
                                                                            try {
                                                                                if (!((RecomposeScopeImpl) objArr[i22]).getValid()) {
                                                                                    d0Var.k(i22);
                                                                                }
                                                                            } catch (Throwable th) {
                                                                                th = th;
                                                                                Trace.INSTANCE.endSection(objBeginSection2);
                                                                                throw th;
                                                                            }
                                                                        } else {
                                                                            rememberEventDispatcher3 = rememberEventDispatcher4;
                                                                        }
                                                                        j14 >>= i18;
                                                                        i21++;
                                                                        rememberEventDispatcher4 = rememberEventDispatcher3;
                                                                    }
                                                                    rememberEventDispatcher2 = rememberEventDispatcher4;
                                                                    if (i20 != i18) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    rememberEventDispatcher2 = rememberEventDispatcher4;
                                                                }
                                                                if (i19 == length2) {
                                                                    break;
                                                                }
                                                                i19++;
                                                                j12 = j10;
                                                                rememberEventDispatcher4 = rememberEventDispatcher2;
                                                                i18 = 8;
                                                            }
                                                        } else {
                                                            rememberEventDispatcher2 = rememberEventDispatcher4;
                                                            jArr2 = jArr3;
                                                            j10 = j12;
                                                        }
                                                        zB = d0Var.b();
                                                    } else {
                                                        i10 = i16;
                                                        rememberEventDispatcher2 = rememberEventDispatcher4;
                                                        jArr2 = jArr3;
                                                        j10 = j12;
                                                        zB = !((RecomposeScopeImpl) obj2).getValid();
                                                    }
                                                    if (zB) {
                                                        map.i(i17);
                                                    }
                                                    i11 = 8;
                                                } else {
                                                    i10 = i16;
                                                    rememberEventDispatcher2 = rememberEventDispatcher4;
                                                    jArr2 = jArr3;
                                                    j10 = j12;
                                                    c10 = c11;
                                                    j11 = j13;
                                                    i11 = i14;
                                                }
                                                j12 = j10 >> i11;
                                                i16 = i10 + 1;
                                                i14 = i11;
                                                c11 = c10;
                                                j13 = j11;
                                                jArr3 = jArr2;
                                                rememberEventDispatcher4 = rememberEventDispatcher2;
                                            }
                                            rememberEventDispatcher = rememberEventDispatcher4;
                                            jArr = jArr3;
                                            if (i15 != i14) {
                                                break;
                                            }
                                        } else {
                                            rememberEventDispatcher = rememberEventDispatcher4;
                                            jArr = jArr3;
                                        }
                                        if (i13 == length) {
                                            break;
                                        }
                                        i13++;
                                        jArr3 = jArr;
                                        rememberEventDispatcher4 = rememberEventDispatcher;
                                        i12 = 0;
                                    }
                                } else {
                                    rememberEventDispatcher = rememberEventDispatcher4;
                                }
                                cleanUpDerivedStateObservations();
                                Trace.INSTANCE.endSection(objBeginSection2);
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } else {
                            rememberEventDispatcher = rememberEventDispatcher4;
                        }
                        if (this.lateChanges.isEmpty()) {
                            rememberEventDispatcher.dispatchAbandons();
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        try {
                            slotWriterOpenWriter.close(false);
                            throw th3;
                        } catch (Throwable th4) {
                            th = th4;
                            Trace.INSTANCE.endSection(objBeginSection);
                            throw th;
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (Throwable th7) {
            th = th7;
        }
        if (this.lateChanges.isEmpty()) {
            rememberEventDispatcher4.dispatchAbandons();
        }
        throw th;
    }

    private final void cleanUpDerivedStateObservations() {
        char c10;
        long j10;
        long j11;
        long j12;
        long[] jArr;
        long[] jArr2;
        long j13;
        int i10;
        char c11;
        long j14;
        long j15;
        int i11;
        boolean zB;
        long[] jArr3;
        int i12;
        int i13;
        z map = this.derivedStates.getMap();
        long[] jArr4 = map.f21881a;
        int length = jArr4.length - 2;
        char c12 = 7;
        long j16 = -9187201950435737472L;
        int i14 = 8;
        if (length >= 0) {
            int i15 = 0;
            long j17 = 128;
            while (true) {
                long j18 = jArr4[i15];
                j11 = 255;
                if ((((~j18) << c12) & j18 & j16) != j16) {
                    int i16 = 8 - ((~(i15 - length)) >>> 31);
                    int i17 = 0;
                    while (i17 < i16) {
                        if ((j18 & 255) < j17) {
                            c11 = c12;
                            int i18 = (i15 << 3) + i17;
                            j14 = j16;
                            Object obj = map.f21882b[i18];
                            Object obj2 = map.f21883c[i18];
                            if (obj2 instanceof d0) {
                                d0 d0Var = (d0) obj2;
                                Object[] objArr = d0Var.f21836b;
                                long[] jArr5 = d0Var.f21835a;
                                int length2 = jArr5.length - 2;
                                if (length2 >= 0) {
                                    j15 = j17;
                                    int i19 = 0;
                                    int i20 = i14;
                                    while (true) {
                                        int i21 = length2;
                                        long j19 = jArr5[i19];
                                        j13 = j18;
                                        if ((((~j19) << c11) & j19 & j14) != j14) {
                                            int i22 = 8 - ((~(i19 - i21)) >>> 31);
                                            int i23 = 0;
                                            while (i23 < i22) {
                                                if ((j19 & 255) < j15) {
                                                    jArr3 = jArr4;
                                                    int i24 = (i19 << 3) + i23;
                                                    i12 = i23;
                                                    i13 = i17;
                                                    if (!this.observations.contains((DerivedState) objArr[i24])) {
                                                        d0Var.k(i24);
                                                    }
                                                } else {
                                                    jArr3 = jArr4;
                                                    i12 = i23;
                                                    i13 = i17;
                                                }
                                                j19 >>= i20;
                                                i23 = i12 + 1;
                                                i17 = i13;
                                                jArr4 = jArr3;
                                            }
                                            jArr2 = jArr4;
                                            i10 = i17;
                                            if (i22 != i20) {
                                                break;
                                            }
                                        } else {
                                            jArr2 = jArr4;
                                            i10 = i17;
                                        }
                                        length2 = i21;
                                        if (i19 == length2) {
                                            break;
                                        }
                                        i19++;
                                        j18 = j13;
                                        i17 = i10;
                                        jArr4 = jArr2;
                                        i20 = 8;
                                    }
                                } else {
                                    jArr2 = jArr4;
                                    j13 = j18;
                                    i10 = i17;
                                    j15 = j17;
                                }
                                zB = d0Var.b();
                            } else {
                                jArr2 = jArr4;
                                j13 = j18;
                                i10 = i17;
                                j15 = j17;
                                zB = !this.observations.contains((DerivedState) obj2);
                            }
                            if (zB) {
                                map.i(i18);
                            }
                            i11 = 8;
                        } else {
                            jArr2 = jArr4;
                            j13 = j18;
                            i10 = i17;
                            c11 = c12;
                            j14 = j16;
                            j15 = j17;
                            i11 = i14;
                        }
                        j18 = j13 >> i11;
                        i17 = i10 + 1;
                        i14 = i11;
                        c12 = c11;
                        j16 = j14;
                        j17 = j15;
                        jArr4 = jArr2;
                    }
                    jArr = jArr4;
                    c10 = c12;
                    j10 = j16;
                    j12 = j17;
                    if (i16 != i14) {
                        break;
                    }
                } else {
                    jArr = jArr4;
                    c10 = c12;
                    j10 = j16;
                    j12 = j17;
                }
                if (i15 == length) {
                    break;
                }
                i15++;
                c12 = c10;
                j16 = j10;
                j17 = j12;
                jArr4 = jArr;
                i14 = 8;
            }
        } else {
            c10 = 7;
            j10 = -9187201950435737472L;
            j11 = 255;
            j12 = 128;
        }
        if (!this.conditionallyInvalidatedScopes.c()) {
            return;
        }
        d0 d0Var2 = this.conditionallyInvalidatedScopes;
        Object[] objArr2 = d0Var2.f21836b;
        long[] jArr6 = d0Var2.f21835a;
        int length3 = jArr6.length - 2;
        if (length3 < 0) {
            return;
        }
        int i25 = 0;
        while (true) {
            long j20 = jArr6[i25];
            if ((((~j20) << c10) & j20 & j10) != j10) {
                int i26 = 8 - ((~(i25 - length3)) >>> 31);
                for (int i27 = 0; i27 < i26; i27++) {
                    if ((j20 & j11) < j12) {
                        int i28 = (i25 << 3) + i27;
                        if (!((RecomposeScopeImpl) objArr2[i28]).isConditional()) {
                            d0Var2.k(i28);
                        }
                    }
                    j20 >>= 8;
                }
                if (i26 != 8) {
                    return;
                }
            }
            if (i25 == length3) {
                return;
            } else {
                i25++;
            }
        }
    }

    private final void composeInitial(p<? super Composer, ? super Integer, t0> content) {
        if (this.disposed) {
            PreconditionsKt.throwIllegalStateException("The composition is disposed");
        }
        this.composable = content;
        this.parent.composeInitial$runtime_release(this, content);
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object andSet = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (andSet != null) {
            if (andSet.equals(CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set<? extends Object> set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, true);
            }
        }
    }

    private final void drainPendingModificationsLocked() {
        Object andSet = this.pendingModifications.getAndSet(null);
        if (kotlin.jvm.internal.p.a(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set<? extends Object>) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set<? extends Object> set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        if (andSet == null) {
            ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
        throw new KotlinNothingValueException();
    }

    private static /* synthetic */ void getAbandonSet$annotations() {
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    public static /* synthetic */ void getPendingInvalidScopes$runtime_release$annotations() {
    }

    public static /* synthetic */ void getSlotTable$runtime_release$annotations() {
    }

    private final <T> T guardChanges(r7.a<? extends T> block) throws Exception {
        try {
            return (T) block.invoke();
        } catch (Throwable th) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                throw th;
            } catch (Exception e5) {
                abandonChanges();
                throw e5;
            }
        }
    }

    private final <T> T guardInvalidationsLocked(r7.l<? super ScopeMap<RecomposeScopeImpl, Object>, ? extends T> block) throws Exception {
        ScopeMap<RecomposeScopeImpl, Object> scopeMapTakeInvalidations = takeInvalidations();
        try {
            return (T) block.invoke(scopeMapTakeInvalidations);
        } catch (Exception e5) {
            this.invalidations = scopeMapTakeInvalidations;
            throw e5;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b0 A[Catch: all -> 0x001e, EDGE_INSN: B:66:0x00b0->B:51:0x00b0 BREAK  A[LOOP:0: B:33:0x0067->B:47:0x00a8], EDGE_INSN: B:67:0x00b0->B:51:0x00b0 BREAK  A[LOOP:0: B:33:0x0067->B:47:0x00a8], TRY_LEAVE, TryCatch #0 {all -> 0x001e, blocks: (B:4:0x000b, B:6:0x0010, B:14:0x0023, B:16:0x0029, B:19:0x002d, B:21:0x0033, B:23:0x003e, B:25:0x0042, B:26:0x004b, B:28:0x0057, B:30:0x005b, B:33:0x0067, B:35:0x0077, B:37:0x0083, B:39:0x008d, B:43:0x009c, B:47:0x00a8, B:48:0x00ab, B:51:0x00b0), top: B:64:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final androidx.compose.runtime.InvalidationResult invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl r21, androidx.compose.runtime.Anchor r22, java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl, androidx.compose.runtime.Anchor, java.lang.Object):androidx.compose.runtime.InvalidationResult");
    }

    private final void invalidateScopeOfLocked(Object value) {
        Object objE = this.observations.getMap().e(value);
        if (objE == null) {
            return;
        }
        if (!(objE instanceof d0)) {
            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objE;
            if (recomposeScopeImpl.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                this.observationsProcessed.add(value, recomposeScopeImpl);
                return;
            }
            return;
        }
        d0 d0Var = (d0) objE;
        Object[] objArr = d0Var.f21836b;
        long[] jArr = d0Var.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) objArr[(i10 << 3) + i12];
                        if (recomposeScopeImpl2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                            this.observationsProcessed.add(value, recomposeScopeImpl2);
                        }
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }

    private final CompositionObserver observer() {
        CompositionObserverHolder compositionObserverHolder = this.observerHolder;
        if (compositionObserverHolder.getRoot()) {
            return compositionObserverHolder.getObserver();
        }
        CompositionObserverHolder observerHolder$runtime_release = this.parent.getObserverHolder();
        CompositionObserver observer = observerHolder$runtime_release != null ? observerHolder$runtime_release.getObserver() : null;
        if (!kotlin.jvm.internal.p.a(observer, compositionObserverHolder.getObserver())) {
            compositionObserverHolder.setObserver(observer);
        }
        return observer;
    }

    private final ScopeMap<RecomposeScopeImpl, Object> takeInvalidations() {
        ScopeMap<RecomposeScopeImpl, Object> scopeMap = this.invalidations;
        this.invalidations = new ScopeMap<>();
        return scopeMap;
    }

    private final <T> T trackAbandonedValues(r7.a<? extends T> block) {
        try {
            return (T) block.invoke();
        } catch (Throwable th) {
            if (!this.abandonSet.isEmpty()) {
                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
            }
            throw th;
        }
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime_release(scope, instance);
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] slots = slotTable.getSlots();
        ArrayList arrayList = new ArrayList();
        for (Object obj : slots) {
            RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
            if (recomposeScopeImpl != null) {
                arrayList.add(recomposeScopeImpl);
            }
        }
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) arrayList.get(i10);
            Anchor anchor = recomposeScopeImpl2.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime_release(anchor.toIndexFor(slotTable)).contains(recomposeScopeImpl2)) {
                PreconditionsKt.throwIllegalStateException("Misaligned anchor " + anchor + " in scope " + recomposeScopeImpl2 + " encountered, scope found at " + r.d0(slotTable.getSlots(), recomposeScopeImpl2));
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (this.abandonSet.isEmpty()) {
            return;
        }
        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e5) {
                        abandonChanges();
                        throw e5;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e5) {
                        abandonChanges();
                        throw e5;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        synchronized (this.lock) {
            try {
                this.composer.changesApplied$runtime_release();
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e5) {
                        abandonChanges();
                        throw e5;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(p<? super Composer, ? super Integer, t0> content) {
        ScopeMap<RecomposeScopeImpl, Object> scopeMapTakeInvalidations;
        try {
            synchronized (this.lock) {
                try {
                    drainPendingModificationsForCompositionLocked();
                    scopeMapTakeInvalidations = takeInvalidations();
                    CompositionObserver compositionObserverObserver = observer();
                    if (compositionObserverObserver != null) {
                        compositionObserverObserver.onBeginComposition(this, scopeMapTakeInvalidations.asMap());
                    }
                    this.composer.composeContent$runtime_release(scopeMapTakeInvalidations, content);
                    if (compositionObserverObserver != null) {
                        compositionObserverObserver.onEndComposition(this);
                    }
                } catch (Exception e5) {
                    this.invalidations = scopeMapTakeInvalidations;
                    throw e5;
                } finally {
                }
            }
        } finally {
        }
    }

    public final int composerStacksSizes$runtime_release() {
        return this.composer.stacksSize$runtime_release();
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        synchronized (this.lock) {
            try {
                boolean z = this.slotTable.getGroupsSize() > 0;
                if (z || !this.abandonSet.isEmpty()) {
                    Trace trace = Trace.INSTANCE;
                    Object objBeginSection = trace.beginSection("Compose:deactivate");
                    try {
                        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                        if (z) {
                            this.applier.onBeginChanges();
                            SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.deactivateCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
                                slotWriterOpenWriter.close(true);
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                slotWriterOpenWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                        trace.endSection(objBeginSection);
                    } catch (Throwable th2) {
                        Trace.INSTANCE.endSection(objBeginSection);
                        throw th2;
                    }
                }
                this.observations.clear();
                this.derivedStates.clear();
                this.invalidations.clear();
                this.changes.clear();
                this.lateChanges.clear();
                this.composer.deactivate$runtime_release();
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, r7.a<? extends R> block) {
        if (to == null || to.equals(this) || groupIndex < 0) {
            return (R) block.invoke();
        }
        this.invalidationDelegate = (CompositionImpl) to;
        this.invalidationDelegateGroup = groupIndex;
        try {
            return (R) block.invoke();
        } finally {
            this.invalidationDelegate = null;
            this.invalidationDelegateGroup = 0;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        synchronized (this.lock) {
            try {
                if (this.composer.getIsComposing()) {
                    PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                }
                if (!this.disposed) {
                    this.disposed = true;
                    this.composable = ComposableSingletons$CompositionKt.INSTANCE.m2947getLambda2$runtime_release();
                    ChangeList deferredChanges$runtime_release = this.composer.getDeferredChanges();
                    if (deferredChanges$runtime_release != null) {
                        applyChangesInLocked(deferredChanges$runtime_release);
                    }
                    boolean z = this.slotTable.getGroupsSize() > 0;
                    if (z || !this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                        if (z) {
                            this.applier.onBeginChanges();
                            SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.removeCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
                                slotWriterOpenWriter.close(true);
                                this.applier.clear();
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                slotWriterOpenWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                    }
                    this.composer.dispose$runtime_release();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
        SlotWriter slotWriterOpenWriter = state.getSlotTable().openWriter();
        try {
            ComposerKt.removeCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
            slotWriterOpenWriter.close(true);
            rememberEventDispatcher.dispatchRememberObservers();
        } catch (Throwable th) {
            slotWriterOpenWriter.close(false);
            throw th;
        }
    }

    public final p<Composer, Integer, t0> getComposable() {
        return this.composable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (kotlin.jvm.internal.p.a(key, CompositionKt.getCompositionImplServiceKey())) {
            return this;
        }
        return null;
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime_release() {
        d0 d0Var = this.conditionallyInvalidatedScopes;
        d0Var.getClass();
        return x.c1(new h0(d0Var, 1));
    }

    public final Set<Object> getDerivedStateDependencies$runtime_release() {
        z map = this.derivedStates.getMap();
        map.getClass();
        return new h0(map, 0);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        synchronized (this.lock) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime_release;
        synchronized (this.lock) {
            hasPendingChanges$runtime_release = this.composer.getHasPendingChanges$runtime_release();
        }
        return hasPendingChanges$runtime_release;
    }

    public final Set<Object> getObservedObjects$runtime_release() {
        z map = this.observations.getMap();
        map.getClass();
        return new h0(map, 0);
    }

    /* JADX INFO: renamed from: getObserverHolder$runtime_release, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime_release, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final d7.j getRecomposeContext() {
        d7.j jVar = this._recomposeContext;
        return jVar == null ? this.parent.getRecomposeCoroutineContext$runtime_release() : jVar;
    }

    /* JADX INFO: renamed from: getSlotTable$runtime_release, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<x6.x> references) {
        int size = references.size();
        boolean z = false;
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                z = true;
                break;
            } else if (!kotlin.jvm.internal.p.a(((MovableContentStateReference) references.get(i10).f22608i).getComposition(), this)) {
                break;
            } else {
                i10++;
            }
        }
        ComposerKt.runtimeCheck(z);
        try {
            this.composer.insertMovableContentReferences(references);
        } finally {
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        CompositionImpl compositionImpl;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (this.slotTable.ownsAnchor(anchor)) {
            return !scope.getCanRecompose() ? InvalidationResult.IGNORED : invalidateChecked(scope, anchor, instance);
        }
        synchronized (this.lock) {
            compositionImpl = this.invalidationDelegate;
        }
        return (compositionImpl == null || !compositionImpl.tryImminentInvalidation(scope, instance)) ? InvalidationResult.IGNORED : InvalidationResult.IMMINENT;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        synchronized (this.lock) {
            try {
                for (Object obj : this.slotTable.getSlots()) {
                    RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                    if (recomposeScopeImpl != null) {
                        recomposeScopeImpl.invalidate();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void invalidateGroupsWithKey(int key) {
        List<RecomposeScopeImpl> listInvalidateGroupsWithKey$runtime_release;
        synchronized (this.lock) {
            listInvalidateGroupsWithKey$runtime_release = this.slotTable.invalidateGroupsWithKey$runtime_release(key);
        }
        if (listInvalidateGroupsWithKey$runtime_release != null) {
            int size = listInvalidateGroupsWithKey$runtime_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (listInvalidateGroupsWithKey$runtime_release.get(i10).invalidateForResult(null) != InvalidationResult.IGNORED) {
                }
            }
            return;
        }
        if (this.composer.forceRecomposeScopes$runtime_release()) {
            this.parent.invalidate$runtime_release(this);
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    /* JADX INFO: renamed from: isDisposed, reason: from getter */
    public boolean getDisposed() {
        return this.disposed;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    public final CompositionObserverHandle observe$runtime_release(final CompositionObserver observer) {
        synchronized (this.lock) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl$observe$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object obj = this.this$0.lock;
                CompositionImpl compositionImpl = this.this$0;
                CompositionObserver compositionObserver = observer;
                synchronized (obj) {
                    if (kotlin.jvm.internal.p.a(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                        compositionImpl.getObserverHolder().setObserver(null);
                        compositionImpl.getObserverHolder().setRoot(false);
                    }
                }
            }
        };
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0050, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0057  */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean observesAnyOf(java.util.Set<? extends java.lang.Object> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof androidx.compose.runtime.collection.ScatterSetWrapper
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5c
            androidx.compose.runtime.collection.ScatterSetWrapper r15 = (androidx.compose.runtime.collection.ScatterSetWrapper) r15
            t.j0 r15 = r15.getSet()
            java.lang.Object[] r0 = r15.f21836b
            long[] r15 = r15.f21835a
            int r3 = r15.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L7d
            r4 = r1
        L16:
            r5 = r15[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L57
            int r7 = r4 - r3
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r1
        L30:
            if (r9 >= r7) goto L55
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L51
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r0[r10]
            androidx.compose.runtime.collection.ScopeMap<java.lang.Object, androidx.compose.runtime.RecomposeScopeImpl> r11 = r14.observations
            boolean r11 = r11.contains(r10)
            if (r11 != 0) goto L50
            androidx.compose.runtime.collection.ScopeMap<java.lang.Object, androidx.compose.runtime.DerivedState<?>> r11 = r14.derivedStates
            boolean r10 = r11.contains(r10)
            if (r10 == 0) goto L51
        L50:
            return r2
        L51:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L30
        L55:
            if (r7 != r8) goto L7d
        L57:
            if (r4 == r3) goto L7d
            int r4 = r4 + 1
            goto L16
        L5c:
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.Iterator r15 = r15.iterator()
        L62:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L7d
            java.lang.Object r0 = r15.next()
            androidx.compose.runtime.collection.ScopeMap<java.lang.Object, androidx.compose.runtime.RecomposeScopeImpl> r3 = r14.observations
            boolean r3 = r3.contains(r0)
            if (r3 != 0) goto L7c
            androidx.compose.runtime.collection.ScopeMap<java.lang.Object, androidx.compose.runtime.DerivedState<?>> r3 = r14.derivedStates
            boolean r0 = r3.contains(r0)
            if (r0 == 0) goto L62
        L7c:
            return r2
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.observesAnyOf(java.util.Set):boolean");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(r7.a<t0> block) {
        this.composer.prepareCompose$runtime_release(block);
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        boolean zRecompose$runtime_release;
        synchronized (this.lock) {
            try {
                drainPendingModificationsForCompositionLocked();
                try {
                    ScopeMap<RecomposeScopeImpl, Object> scopeMapTakeInvalidations = takeInvalidations();
                    try {
                        CompositionObserver compositionObserverObserver = observer();
                        if (compositionObserverObserver != null) {
                            compositionObserverObserver.onBeginComposition(this, scopeMapTakeInvalidations.asMap());
                        }
                        zRecompose$runtime_release = this.composer.recompose$runtime_release(scopeMapTakeInvalidations);
                        if (!zRecompose$runtime_release) {
                            drainPendingModificationsLocked();
                        }
                        if (compositionObserverObserver != null) {
                            compositionObserverObserver.onEndComposition(this);
                        }
                    } catch (Exception e5) {
                        this.invalidations = scopeMapTakeInvalidations;
                        throw e5;
                    }
                } finally {
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zRecompose$runtime_release;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        boolean z;
        Object obj2;
        do {
            obj = this.pendingModifications.get();
            z = true;
            if (obj == null ? true : obj.equals(CompositionKt.PendingApplyNoModifications)) {
                obj2 = values;
            } else if (obj instanceof Set) {
                obj2 = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Set[] setArr = (Set[]) obj;
                int length = setArr.length;
                Object[] objArrCopyOf = Arrays.copyOf(setArr, length + 1);
                objArrCopyOf[length] = values;
                obj2 = objArrCopyOf;
            }
            AtomicReference<Object> atomicReference = this.pendingModifications;
            while (true) {
                if (atomicReference.compareAndSet(obj, obj2)) {
                    break;
                } else if (atomicReference.get() != obj) {
                    z = false;
                    break;
                }
            }
        } while (!z);
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        int i10;
        int i11;
        int i12;
        if (getAreChildrenComposing() || (currentRecomposeScope$runtime_release = this.composer.getCurrentRecomposeScope$runtime_release()) == null) {
            return;
        }
        int i13 = 1;
        currentRecomposeScope$runtime_release.setUsed(true);
        if (currentRecomposeScope$runtime_release.recordRead(value)) {
            return;
        }
        if (value instanceof StateObjectImpl) {
            ((StateObjectImpl) value).m3108recordReadInh_f27i8$runtime_release(ReaderKind.m3091constructorimpl(1));
        }
        this.observations.add(value, currentRecomposeScope$runtime_release);
        if (value instanceof DerivedState) {
            DerivedState<?> derivedState = (DerivedState) value;
            DerivedState.Record<?> currentRecord = derivedState.getCurrentRecord();
            this.derivedStates.removeScope(value);
            e0 dependencies = currentRecord.getDependencies();
            Object[] objArr = dependencies.f21805b;
            long[] jArr = dependencies.f21804a;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i14 = 0;
                while (true) {
                    long j10 = jArr[i14];
                    if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i15 = 8;
                        int i16 = 8 - ((~(i14 - length)) >>> 31);
                        int i17 = 0;
                        while (i17 < i16) {
                            if ((j10 & 255) < 128) {
                                i11 = i13;
                                StateObject stateObject = (StateObject) objArr[(i14 << 3) + i17];
                                if (stateObject instanceof StateObjectImpl) {
                                    i12 = i15;
                                    ((StateObjectImpl) stateObject).m3108recordReadInh_f27i8$runtime_release(ReaderKind.m3091constructorimpl(i11));
                                } else {
                                    i12 = i15;
                                }
                                this.derivedStates.add(stateObject, value);
                            } else {
                                i11 = i13;
                                i12 = i15;
                            }
                            j10 >>= i12;
                            i17++;
                            i13 = i11;
                            i15 = i12;
                        }
                        i10 = i13;
                        if (i16 != i15) {
                            break;
                        }
                    } else {
                        i10 = i13;
                    }
                    if (i14 == length) {
                        break;
                    }
                    i14++;
                    i13 = i10;
                }
            }
            currentRecomposeScope$runtime_release.recordDerivedStateValue(derivedState, currentRecord.getCurrentValue());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void recordWriteOf(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.lock
            monitor-enter(r0)
            r14.invalidateScopeOfLocked(r15)     // Catch: java.lang.Throwable -> L53
            androidx.compose.runtime.collection.ScopeMap<java.lang.Object, androidx.compose.runtime.DerivedState<?>> r1 = r14.derivedStates     // Catch: java.lang.Throwable -> L53
            t.z r1 = r1.getMap()     // Catch: java.lang.Throwable -> L53
            java.lang.Object r15 = r1.e(r15)     // Catch: java.lang.Throwable -> L53
            if (r15 == 0) goto L65
            boolean r1 = r15 instanceof t.d0     // Catch: java.lang.Throwable -> L53
            if (r1 == 0) goto L60
            t.d0 r15 = (t.d0) r15     // Catch: java.lang.Throwable -> L53
            java.lang.Object[] r1 = r15.f21836b     // Catch: java.lang.Throwable -> L53
            long[] r15 = r15.f21835a     // Catch: java.lang.Throwable -> L53
            int r2 = r15.length     // Catch: java.lang.Throwable -> L53
            int r2 = r2 + (-2)
            if (r2 < 0) goto L65
            r3 = 0
            r4 = r3
        L23:
            r5 = r15[r4]     // Catch: java.lang.Throwable -> L53
            long r7 = ~r5     // Catch: java.lang.Throwable -> L53
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L5b
            int r7 = r4 - r2
            int r7 = ~r7     // Catch: java.lang.Throwable -> L53
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r3
        L3d:
            if (r9 >= r7) goto L59
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L55
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]     // Catch: java.lang.Throwable -> L53
            androidx.compose.runtime.DerivedState r10 = (androidx.compose.runtime.DerivedState) r10     // Catch: java.lang.Throwable -> L53
            r14.invalidateScopeOfLocked(r10)     // Catch: java.lang.Throwable -> L53
            goto L55
        L53:
            r15 = move-exception
            goto L67
        L55:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L3d
        L59:
            if (r7 != r8) goto L65
        L5b:
            if (r4 == r2) goto L65
            int r4 = r4 + 1
            goto L23
        L60:
            androidx.compose.runtime.DerivedState r15 = (androidx.compose.runtime.DerivedState) r15     // Catch: java.lang.Throwable -> L53
            r14.invalidateScopeOfLocked(r15)     // Catch: java.lang.Throwable -> L53
        L65:
            monitor-exit(r0)
            return
        L67:
            monitor-exit(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.recordWriteOf(java.lang.Object):void");
    }

    public final void removeDerivedStateObservation$runtime_release(DerivedState<?> state) {
        if (this.observations.contains(state)) {
            return;
        }
        this.derivedStates.removeScope(state);
    }

    public final void removeObservation$runtime_release(Object instance, RecomposeScopeImpl scope) {
        this.observations.remove(instance, scope);
    }

    public final void setComposable(p<? super Composer, ? super Integer, t0> pVar) {
        this.composable = pVar;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(p<? super Composer, ? super Integer, t0> content) {
        composeInitial(content);
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(p<? super Composer, ? super Integer, t0> content) {
        this.composer.startReuseFromRoot();
        composeInitial(content);
        this.composer.endReuseFromRoot();
    }

    public final void setPendingInvalidScopes$runtime_release(boolean z) {
        this.pendingInvalidScopes = z;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        synchronized (this.lock) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime_release();
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x017e A[EDGE_INSN: B:70:0x017e->B:223:0x0117 BREAK  A[LOOP:13: B:60:0x014c->B:71:0x0180]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addPendingInvalidationsLocked(java.util.Set<? extends java.lang.Object> r34, boolean r35) {
        /*
            Method dump skipped, instruction units count: 924
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.addPendingInvalidationsLocked(java.util.Set, boolean):void");
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, d7.j jVar, int i10, kotlin.jvm.internal.h hVar) {
        this(compositionContext, applier, (i10 & 4) != 0 ? null : jVar);
    }
}
