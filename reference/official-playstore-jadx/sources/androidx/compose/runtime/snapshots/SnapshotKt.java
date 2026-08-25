package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.AtomicInt;
import androidx.compose.runtime.SnapshotThreadLocal;
import androidx.compose.runtime.WeakReference;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotIdSet;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u000f\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a;\u0010\u0013\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001aS\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001aI\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000e2\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a$\u0010\u001f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0081\b¢\u0006\u0004\b\u001f\u0010 \u001a@\u0010$\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001c2\u0006\u0010!\u001a\u00020\n2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\b$\u0010%\u001a8\u0010&\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001c2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\b&\u0010'\u001a\u000f\u0010&\u001a\u00020\u0007H\u0002¢\u0006\u0004\b&\u0010(\u001a<\u0010)\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u00020\n2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\b)\u0010*\u001a\u0017\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\nH\u0002¢\u0006\u0004\b,\u0010-\u001a'\u0010/\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b/\u00100\u001a'\u0010/\u001a\u00020\u00112\u0006\u00102\u001a\u0002012\u0006\u0010+\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b/\u00103\u001a3\u00105\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u001c*\u0002012\u0006\u00104\u001a\u00028\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b5\u00106\u001a#\u00105\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u000207¢\u0006\u0004\b5\u00109\u001a+\u00105\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010+\u001a\u00020\n¢\u0006\u0004\b5\u0010:\u001a\u000f\u0010<\u001a\u00020;H\u0002¢\u0006\u0004\b<\u0010=\u001a\u0019\u0010>\u001a\u0004\u0018\u0001012\u0006\u00108\u001a\u000207H\u0002¢\u0006\u0004\b>\u0010?\u001a\u0017\u0010@\u001a\u00020\u00112\u0006\u00108\u001a\u000207H\u0002¢\u0006\u0004\b@\u0010A\u001a(\u0010C\u001a\u000201*\u0002012\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00110\u000eH\u0082\b¢\u0006\u0004\bC\u0010D\u001a\u000f\u0010E\u001a\u00020\u0007H\u0002¢\u0006\u0004\bE\u0010(\u001a\u0017\u0010F\u001a\u00020\u00072\u0006\u00108\u001a\u000207H\u0002¢\u0006\u0004\bF\u0010G\u001a-\u0010H\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010+\u001a\u00020\nH\u0001¢\u0006\u0004\bH\u0010:\u001a5\u0010J\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010+\u001a\u00020\n2\u0006\u0010I\u001a\u00028\u0000H\u0000¢\u0006\u0004\bJ\u0010K\u001a-\u0010L\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010+\u001a\u00020\nH\u0000¢\u0006\u0004\bL\u0010:\u001a-\u0010M\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010+\u001a\u00020\nH\u0002¢\u0006\u0004\bM\u0010:\u001a%\u0010N\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u000201*\u00028\u00002\u0006\u00108\u001a\u000207H\u0000¢\u0006\u0004\bN\u00109\u001a\u001f\u0010O\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\n2\u0006\u00108\u001a\u000207H\u0001¢\u0006\u0004\bO\u0010P\u001aM\u0010S\u001a\u00028\u0001\"\b\b\u0000\u0010\u001c*\u000201\"\u0004\b\u0001\u0010Q*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010+\u001a\u00020\n2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e¢\u0006\u0002\bRH\u0086\b¢\u0006\u0004\bS\u0010T\u001aE\u0010S\u001a\u00028\u0001\"\b\b\u0000\u0010\u001c*\u000201\"\u0004\b\u0001\u0010Q*\u00028\u00002\u0006\u00108\u001a\u0002072\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e¢\u0006\u0002\bRH\u0086\b¢\u0006\u0004\bS\u0010U\u001aM\u0010V\u001a\u00028\u0001\"\b\b\u0000\u0010\u001c*\u000201\"\u0004\b\u0001\u0010Q*\u00028\u00002\u0006\u00108\u001a\u0002072\u0006\u0010I\u001a\u00028\u00002\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e¢\u0006\u0002\bRH\u0080\b¢\u0006\u0004\bV\u0010W\u001a5\u0010\\\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0018\u00010[2\u0006\u0010\u000b\u001a\u00020X2\u0006\u0010Y\u001a\u00020X2\u0006\u0010Z\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\\\u0010]\u001a\u000f\u0010^\u001a\u00020;H\u0002¢\u0006\u0004\b^\u0010=\u001a)\u0010_\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u0002012\u0006\u00104\u001a\u00028\u00002\u0006\u0010+\u001a\u00020\nH\u0001¢\u0006\u0004\b_\u0010`\u001a!\u0010_\u001a\u00028\u0000\"\b\b\u0000\u0010\u001c*\u0002012\u0006\u00104\u001a\u00028\u0000H\u0001¢\u0006\u0004\b_\u0010a\u001aG\u0010b\u001a\u00028\u0001\"\b\b\u0000\u0010\u001c*\u000201\"\u0004\b\u0001\u0010Q*\u00028\u00002!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(4\u0012\u0004\u0012\u00028\u00010\u000eH\u0086\b¢\u0006\u0004\bb\u0010c\u001a#\u0010f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010d\u001a\u00020\u00002\u0006\u0010e\u001a\u00020\u0000H\u0000¢\u0006\u0004\bf\u0010g\"/\u0010h\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00070\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010i\"\u0014\u0010j\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\bj\u0010k\"\u001a\u0010m\u001a\b\u0012\u0004\u0012\u00020\n0l8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bm\u0010n\" \u0010o\u001a\u00020\u000f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bo\u0010p\u0012\u0004\bs\u0010(\u001a\u0004\bq\u0010r\"\u0016\u0010t\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bt\u0010u\"\u0016\u0010v\u001a\u00020\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010k\"\u0014\u0010x\u001a\u00020w8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010y\"\u001a\u0010{\u001a\b\u0012\u0004\u0012\u0002070z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|\"7\u0010\u0080\u0001\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u007f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070~0}8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001\"+\u0010\u0082\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000e0}8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0081\u0001\"?\u0010\u0087\u0001\u001a*\u0012\u000f\u0012\r \u0085\u0001*\u0005\u0018\u00010\u0084\u00010\u0084\u00010\u0083\u0001j\u0014\u0012\u000f\u0012\r \u0085\u0001*\u0005\u0018\u00010\u0084\u00010\u0084\u0001`\u0086\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001\"%\u0010\u0089\u0001\u001a\u00020\n8\u0000X\u0081\u0004¢\u0006\u0016\n\u0006\b\u0089\u0001\u0010\u008a\u0001\u0012\u0005\b\u008c\u0001\u0010(\u001a\u0005\b\u008b\u0001\u0010\f\"\u001a\u0010\u008e\u0001\u001a\u00030\u008d\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001¨\u0006\u0090\u0001"}, d2 = {"", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invalid", "trackPinning", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)I", "handle", "Lx6/t0;", "releasePinningLocked", "(I)V", "Landroidx/compose/runtime/snapshots/Snapshot;", "currentSnapshot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "previousSnapshot", "Lkotlin/Function1;", "", "readObserver", "", "ownsPreviousSnapshot", "createTransparentSnapshotWithNoParentReadObserver", "(Landroidx/compose/runtime/snapshots/Snapshot;Lr7/l;Z)Landroidx/compose/runtime/snapshots/Snapshot;", "parentObserver", "mergeReadObserver", "mergedReadObserver", "(Lr7/l;Lr7/l;Z)Lr7/l;", "writeObserver", "mergedWriteObserver", "(Lr7/l;Lr7/l;)Lr7/l;", "T", "Lkotlin/Function0;", "block", "sync", "(Lr7/a;)Ljava/lang/Object;", "previousGlobalSnapshot", "Lx6/y;", ContentDisposition.Parameters.Name, "takeNewGlobalSnapshot", "(Landroidx/compose/runtime/snapshots/Snapshot;Lr7/l;)Ljava/lang/Object;", "advanceGlobalSnapshot", "(Lr7/l;)Ljava/lang/Object;", "()V", "takeNewSnapshot", "(Lr7/l;)Landroidx/compose/runtime/snapshots/Snapshot;", "snapshot", "validateOpen", "(Landroidx/compose/runtime/snapshots/Snapshot;)V", "candidateSnapshot", "valid", "(IILandroidx/compose/runtime/snapshots/SnapshotIdSet;)Z", "Landroidx/compose/runtime/snapshots/StateRecord;", "data", "(Landroidx/compose/runtime/snapshots/StateRecord;ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)Z", "r", "readable", "(Landroidx/compose/runtime/snapshots/StateRecord;ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/snapshots/StateObject;", "state", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;)Landroidx/compose/runtime/snapshots/StateRecord;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "", "readError", "()Ljava/lang/Void;", "usedLocked", "(Landroidx/compose/runtime/snapshots/StateObject;)Landroidx/compose/runtime/snapshots/StateRecord;", "overwriteUnusedRecordsLocked", "(Landroidx/compose/runtime/snapshots/StateObject;)Z", "predicate", "findYoungestOr", "(Landroidx/compose/runtime/snapshots/StateRecord;Lr7/l;)Landroidx/compose/runtime/snapshots/StateRecord;", "checkAndOverwriteUnusedRecordsLocked", "processForUnusedRecordsLocked", "(Landroidx/compose/runtime/snapshots/StateObject;)V", "writableRecord", "candidate", "overwritableRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", "newWritableRecord", "newWritableRecordLocked", "newOverwritableRecordLocked", "notifyWrite", "(Landroidx/compose/runtime/snapshots/Snapshot;Landroidx/compose/runtime/snapshots/StateObject;)V", "R", "Lx6/n;", "writable", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Lr7/l;)Ljava/lang/Object;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Lr7/l;)Ljava/lang/Object;", "overwritable", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/StateRecord;Lr7/l;)Ljava/lang/Object;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "applyingSnapshot", "invalidSnapshots", "", "optimisticMerges", "(Landroidx/compose/runtime/snapshots/MutableSnapshot;Landroidx/compose/runtime/snapshots/MutableSnapshot;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Ljava/util/Map;", "reportReadonlySnapshotWrite", "current", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "(Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", "withCurrent", "(Landroidx/compose/runtime/snapshots/StateRecord;Lr7/l;)Ljava/lang/Object;", "from", "until", "addRange", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;II)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "emptyLambda", "Lr7/l;", "INVALID_SNAPSHOT", "I", "Landroidx/compose/runtime/SnapshotThreadLocal;", "threadSnapshot", "Landroidx/compose/runtime/SnapshotThreadLocal;", "lock", "Ljava/lang/Object;", "getLock", "()Ljava/lang/Object;", "getLock$annotations", "openSnapshots", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "nextSnapshotId", "Landroidx/compose/runtime/snapshots/SnapshotDoubleIndexHeap;", "pinningTable", "Landroidx/compose/runtime/snapshots/SnapshotDoubleIndexHeap;", "Landroidx/compose/runtime/snapshots/SnapshotWeakSet;", "extraStateObjects", "Landroidx/compose/runtime/snapshots/SnapshotWeakSet;", "", "Lkotlin/Function2;", "", "applyObservers", "Ljava/util/List;", "globalWriteObservers", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/snapshots/GlobalSnapshot;", "kotlin.jvm.PlatformType", "Landroidx/compose/runtime/AtomicReference;", "currentGlobalSnapshot", "Ljava/util/concurrent/atomic/AtomicReference;", "snapshotInitializer", "Landroidx/compose/runtime/snapshots/Snapshot;", "getSnapshotInitializer", "getSnapshotInitializer$annotations", "Landroidx/compose/runtime/AtomicInt;", "pendingApplyObserverCount", "Landroidx/compose/runtime/AtomicInt;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnapshotKt {
    private static final int INVALID_SNAPSHOT = 0;
    private static List<? extends p<? super Set<? extends Object>, ? super Snapshot, t0>> applyObservers;
    private static final AtomicReference<GlobalSnapshot> currentGlobalSnapshot;
    private static final SnapshotWeakSet<StateObject> extraStateObjects;
    private static List<? extends l<Object, t0>> globalWriteObservers;
    private static int nextSnapshotId;
    private static SnapshotIdSet openSnapshots;
    private static AtomicInt pendingApplyObserverCount;
    private static final SnapshotDoubleIndexHeap pinningTable;
    private static final Snapshot snapshotInitializer;
    private static final l<SnapshotIdSet, t0> emptyLambda = SnapshotKt$emptyLambda$1.INSTANCE;
    private static final SnapshotThreadLocal<Snapshot> threadSnapshot = new SnapshotThreadLocal<>();
    private static final Object lock = new Object();

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotKt$advanceGlobalSnapshot$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<SnapshotIdSet, t0> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final void invoke(SnapshotIdSet snapshotIdSet) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SnapshotIdSet) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotKt$mergedReadObserver$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "state", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Object, t0> {
        final /* synthetic */ l<Object, t0> $parentObserver;
        final /* synthetic */ l<Object, t0> $readObserver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(l<Object, t0> lVar, l<Object, t0> lVar2) {
            super(1);
            this.$readObserver = lVar;
            this.$parentObserver = lVar2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m3103invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m3103invoke(Object obj) {
            this.$readObserver.invoke(obj);
            this.$parentObserver.invoke(obj);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotKt$mergedWriteObserver$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "state", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06031 extends r implements l<Object, t0> {
        final /* synthetic */ l<Object, t0> $parentObserver;
        final /* synthetic */ l<Object, t0> $writeObserver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06031(l<Object, t0> lVar, l<Object, t0> lVar2) {
            super(1);
            this.$writeObserver = lVar;
            this.$parentObserver = lVar2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m3104invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m3104invoke(Object obj) {
            this.$writeObserver.invoke(obj);
            this.$parentObserver.invoke(obj);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotKt$takeNewSnapshot$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "T", "Landroidx/compose/runtime/snapshots/Snapshot;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invoke", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/Snapshot;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06041<T> extends r implements l<SnapshotIdSet, T> {
        final /* synthetic */ l<SnapshotIdSet, T> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C06041(l<? super SnapshotIdSet, ? extends T> lVar) {
            super(1);
            this.$block = lVar;
        }

        /* JADX WARN: Incorrect return type in method signature: (Landroidx/compose/runtime/snapshots/SnapshotIdSet;)TT; */
        @Override // r7.l
        public final Snapshot invoke(SnapshotIdSet snapshotIdSet) {
            Snapshot snapshot = (Snapshot) this.$block.invoke(snapshotIdSet);
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(snapshot.getId());
            }
            return snapshot;
        }
    }

    static {
        SnapshotIdSet.Companion companion = SnapshotIdSet.INSTANCE;
        openSnapshots = companion.getEMPTY();
        nextSnapshotId = 2;
        pinningTable = new SnapshotDoubleIndexHeap();
        extraStateObjects = new SnapshotWeakSet<>();
        z zVar = z.f19728i;
        applyObservers = zVar;
        globalWriteObservers = zVar;
        int i10 = nextSnapshotId;
        nextSnapshotId = i10 + 1;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i10, companion.getEMPTY());
        openSnapshots = openSnapshots.set(globalSnapshot.getId());
        AtomicReference<GlobalSnapshot> atomicReference = new AtomicReference<>(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        snapshotInitializer = atomicReference.get();
        pendingApplyObserverCount = new AtomicInt(0);
    }

    public static final SnapshotIdSet addRange(SnapshotIdSet snapshotIdSet, int i10, int i11) {
        while (i10 < i11) {
            snapshotIdSet = snapshotIdSet.set(i10);
            i10++;
        }
        return snapshotIdSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> T advanceGlobalSnapshot(r7.l<? super androidx.compose.runtime.snapshots.SnapshotIdSet, ? extends T> r15) {
        /*
            androidx.compose.runtime.snapshots.Snapshot r0 = androidx.compose.runtime.snapshots.SnapshotKt.snapshotInitializer
            androidx.compose.runtime.snapshots.GlobalSnapshot r0 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r0
            java.lang.Object r0 = getLock()
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference<androidx.compose.runtime.snapshots.GlobalSnapshot> r1 = androidx.compose.runtime.snapshots.SnapshotKt.currentGlobalSnapshot     // Catch: java.lang.Throwable -> L1f
            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> L1f
            r2 = r1
            androidx.compose.runtime.snapshots.GlobalSnapshot r2 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r2     // Catch: java.lang.Throwable -> L1f
            t.d0 r2 = r2.getModified()     // Catch: java.lang.Throwable -> L1f
            if (r2 == 0) goto L22
            androidx.compose.runtime.AtomicInt r3 = androidx.compose.runtime.snapshots.SnapshotKt.pendingApplyObserverCount     // Catch: java.lang.Throwable -> L1f
            r4 = 1
            r3.add(r4)     // Catch: java.lang.Throwable -> L1f
            goto L22
        L1f:
            r15 = move-exception
            goto Laa
        L22:
            r3 = r1
            androidx.compose.runtime.snapshots.Snapshot r3 = (androidx.compose.runtime.snapshots.Snapshot) r3     // Catch: java.lang.Throwable -> L1f
            java.lang.Object r15 = takeNewGlobalSnapshot(r3, r15)     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r0)
            r0 = 0
            if (r2 == 0) goto L55
            r3 = -1
            java.util.List<? extends r7.p<? super java.util.Set<? extends java.lang.Object>, ? super androidx.compose.runtime.snapshots.Snapshot, x6.t0>> r4 = androidx.compose.runtime.snapshots.SnapshotKt.applyObservers     // Catch: java.lang.Throwable -> L47
            int r5 = r4.size()     // Catch: java.lang.Throwable -> L47
            r6 = r0
        L35:
            if (r6 >= r5) goto L49
            java.lang.Object r7 = r4.get(r6)     // Catch: java.lang.Throwable -> L47
            r7.p r7 = (r7.p) r7     // Catch: java.lang.Throwable -> L47
            java.util.Set r8 = androidx.compose.runtime.collection.ScatterSetWrapperKt.wrapIntoSet(r2)     // Catch: java.lang.Throwable -> L47
            r7.invoke(r8, r1)     // Catch: java.lang.Throwable -> L47
            int r6 = r6 + 1
            goto L35
        L47:
            r15 = move-exception
            goto L4f
        L49:
            androidx.compose.runtime.AtomicInt r1 = androidx.compose.runtime.snapshots.SnapshotKt.pendingApplyObserverCount
            r1.add(r3)
            goto L55
        L4f:
            androidx.compose.runtime.AtomicInt r0 = androidx.compose.runtime.snapshots.SnapshotKt.pendingApplyObserverCount
            r0.add(r3)
            throw r15
        L55:
            java.lang.Object r1 = getLock()
            monitor-enter(r1)
            checkAndOverwriteUnusedRecordsLocked()     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto La6
            java.lang.Object[] r3 = r2.f21836b     // Catch: java.lang.Throwable -> L99
            long[] r2 = r2.f21835a     // Catch: java.lang.Throwable -> L99
            int r4 = r2.length     // Catch: java.lang.Throwable -> L99
            int r4 = r4 + (-2)
            if (r4 < 0) goto La6
            r5 = r0
        L69:
            r6 = r2[r5]     // Catch: java.lang.Throwable -> L99
            long r8 = ~r6     // Catch: java.lang.Throwable -> L99
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto La1
            int r8 = r5 - r4
            int r8 = ~r8     // Catch: java.lang.Throwable -> L99
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r0
        L83:
            if (r10 >= r8) goto L9f
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L9b
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r3[r11]     // Catch: java.lang.Throwable -> L99
            androidx.compose.runtime.snapshots.StateObject r11 = (androidx.compose.runtime.snapshots.StateObject) r11     // Catch: java.lang.Throwable -> L99
            processForUnusedRecordsLocked(r11)     // Catch: java.lang.Throwable -> L99
            goto L9b
        L99:
            r15 = move-exception
            goto La8
        L9b:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L83
        L9f:
            if (r8 != r9) goto La6
        La1:
            if (r5 == r4) goto La6
            int r5 = r5 + 1
            goto L69
        La6:
            monitor-exit(r1)
            return r15
        La8:
            monitor-exit(r1)
            throw r15
        Laa:
            monitor-exit(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotKt.advanceGlobalSnapshot(r7.l):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndOverwriteUnusedRecordsLocked() {
        SnapshotWeakSet<StateObject> snapshotWeakSet = extraStateObjects;
        int size$runtime_release = snapshotWeakSet.getSize();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i10 >= size$runtime_release) {
                break;
            }
            WeakReference<StateObject> weakReference = snapshotWeakSet.getValues$runtime_release()[i10];
            StateObject stateObject = weakReference != null ? weakReference.get() : null;
            if (stateObject != null && overwriteUnusedRecordsLocked(stateObject)) {
                if (i11 != i10) {
                    snapshotWeakSet.getValues$runtime_release()[i11] = weakReference;
                    snapshotWeakSet.getHashes()[i11] = snapshotWeakSet.getHashes()[i10];
                }
                i11++;
            }
            i10++;
        }
        for (int i12 = i11; i12 < size$runtime_release; i12++) {
            snapshotWeakSet.getValues$runtime_release()[i12] = null;
            snapshotWeakSet.getHashes()[i12] = 0;
        }
        if (i11 != size$runtime_release) {
            snapshotWeakSet.setSize$runtime_release(i11);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, l<Object, t0> lVar, boolean z) {
        boolean z5 = snapshot instanceof MutableSnapshot;
        if (z5 || snapshot == null) {
            return new TransparentObserverMutableSnapshot(z5 ? (MutableSnapshot) snapshot : null, lVar, null, false, z);
        }
        return new TransparentObserverSnapshot(snapshot, lVar, false, z);
    }

    public static /* synthetic */ Snapshot createTransparentSnapshotWithNoParentReadObserver$default(Snapshot snapshot, l lVar, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        if ((i10 & 4) != 0) {
            z = false;
        }
        return createTransparentSnapshotWithNoParentReadObserver(snapshot, lVar, z);
    }

    public static final <T extends StateRecord> T current(T t2, Snapshot snapshot) {
        T t10 = (T) readable(t2, snapshot.getId(), snapshot.getInvalid());
        if (t10 != null) {
            return t10;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = threadSnapshot.get();
        return snapshot == null ? currentGlobalSnapshot.get() : snapshot;
    }

    private static final StateRecord findYoungestOr(StateRecord stateRecord, l<? super StateRecord, Boolean> lVar) {
        StateRecord stateRecord2 = stateRecord;
        while (stateRecord != null) {
            if (((Boolean) lVar.invoke(stateRecord)).booleanValue()) {
                return stateRecord;
            }
            if (stateRecord2.getSnapshotId() < stateRecord.getSnapshotId()) {
                stateRecord2 = stateRecord;
            }
            stateRecord = stateRecord.getNext();
        }
        return stateRecord2;
    }

    public static final Object getLock() {
        return lock;
    }

    public static /* synthetic */ void getLock$annotations() {
    }

    public static final Snapshot getSnapshotInitializer() {
        return snapshotInitializer;
    }

    public static /* synthetic */ void getSnapshotInitializer$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final l<Object, t0> mergedReadObserver(l<Object, t0> lVar, l<Object, t0> lVar2, boolean z) {
        if (!z) {
            lVar2 = null;
        }
        return (lVar == null || lVar2 == null || lVar == lVar2) ? lVar == null ? lVar2 : lVar : new AnonymousClass1(lVar, lVar2);
    }

    public static /* synthetic */ l mergedReadObserver$default(l lVar, l lVar2, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        return mergedReadObserver(lVar, lVar2, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final l<Object, t0> mergedWriteObserver(l<Object, t0> lVar, l<Object, t0> lVar2) {
        return (lVar == null || lVar2 == null || lVar == lVar2) ? lVar == null ? lVar2 : lVar : new C06031(lVar, lVar2);
    }

    public static final <T extends StateRecord> T newOverwritableRecordLocked(T t2, StateObject stateObject) {
        T t10 = (T) usedLocked(stateObject);
        if (t10 != null) {
            t10.setSnapshotId$runtime_release(Integer.MAX_VALUE);
            return t10;
        }
        T t11 = (T) t2.create();
        t11.setSnapshotId$runtime_release(Integer.MAX_VALUE);
        t11.setNext$runtime_release(stateObject.getFirstStateRecord());
        stateObject.prependStateRecord(t11);
        return t11;
    }

    public static final <T extends StateRecord> T newWritableRecord(T t2, StateObject stateObject, Snapshot snapshot) {
        T t10;
        synchronized (getLock()) {
            t10 = (T) newWritableRecordLocked(t2, stateObject, snapshot);
        }
        return t10;
    }

    private static final <T extends StateRecord> T newWritableRecordLocked(T t2, StateObject stateObject, Snapshot snapshot) {
        T t10 = (T) newOverwritableRecordLocked(t2, stateObject);
        t10.assign(t2);
        t10.setSnapshotId$runtime_release(snapshot.getId());
        return t10;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject stateObject) {
        snapshot.setWriteCount$runtime_release(snapshot.getWriteCount() + 1);
        l<Object, t0> writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release != null) {
            writeObserver$runtime_release.invoke(stateObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<StateRecord, StateRecord> optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        long[] jArr;
        int i10;
        Map<StateRecord, StateRecord> map;
        long[] jArr2;
        int i11;
        Map<StateRecord, StateRecord> map2;
        int i12;
        d0 modified$runtime_release = mutableSnapshot2.getModified();
        int id = mutableSnapshot.getId();
        Map<StateRecord, StateRecord> map3 = null;
        if (modified$runtime_release == null) {
            return null;
        }
        SnapshotIdSet snapshotIdSetOr = mutableSnapshot2.getInvalid().set(mutableSnapshot2.getId()).or(mutableSnapshot2.getPreviousIds());
        Object[] objArr = modified$runtime_release.f21836b;
        long[] jArr3 = modified$runtime_release.f21835a;
        int length = jArr3.length - 2;
        if (length < 0) {
            return null;
        }
        HashMap map4 = null;
        int i13 = 0;
        while (true) {
            long j10 = jArr3[i13];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i14 = 8;
                int i15 = 8 - ((~(i13 - length)) >>> 31);
                int i16 = 0;
                while (i16 < i15) {
                    if ((255 & j10) < 128) {
                        StateObject stateObject = (StateObject) objArr[(i13 << 3) + i16];
                        StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                        map2 = map3;
                        StateRecord stateRecord = readable(firstStateRecord, id, snapshotIdSet);
                        if (stateRecord == null) {
                            i12 = i14;
                        } else {
                            i12 = i14;
                            StateRecord stateRecord2 = readable(firstStateRecord, id, snapshotIdSetOr);
                            if (stateRecord2 != null && !stateRecord.equals(stateRecord2)) {
                                jArr2 = jArr3;
                                i11 = id;
                                StateRecord stateRecord3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid());
                                if (stateRecord3 == null) {
                                    readError();
                                    throw new KotlinNothingValueException();
                                }
                                StateRecord stateRecordMergeRecords = stateObject.mergeRecords(stateRecord2, stateRecord, stateRecord3);
                                if (stateRecordMergeRecords == null) {
                                    return map2;
                                }
                                if (map4 == null) {
                                    map4 = new HashMap();
                                }
                                map4.put(stateRecord, stateRecordMergeRecords);
                                map4 = map4;
                            }
                        }
                        jArr2 = jArr3;
                        i11 = id;
                    } else {
                        jArr2 = jArr3;
                        i11 = id;
                        map2 = map3;
                        i12 = i14;
                    }
                    j10 >>= i12;
                    i16++;
                    map3 = map2;
                    i14 = i12;
                    jArr3 = jArr2;
                    id = i11;
                }
                jArr = jArr3;
                i10 = id;
                map = map3;
                if (i15 != i14) {
                    return map4;
                }
            } else {
                jArr = jArr3;
                i10 = id;
                map = map3;
            }
            if (i13 == length) {
                return map4;
            }
            i13++;
            map3 = map;
            jArr3 = jArr;
            id = i10;
        }
    }

    public static final <T extends StateRecord, R> R overwritable(T t2, StateObject stateObject, T t10, l<? super T, ? extends R> lVar) {
        Snapshot current;
        R r4;
        getSnapshotInitializer();
        synchronized (getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            r4 = (R) lVar.invoke(overwritableRecord(t2, stateObject, current, t10));
        }
        notifyWrite(current, stateObject);
        return r4;
    }

    public static final <T extends StateRecord> T overwritableRecord(T t2, StateObject stateObject, Snapshot snapshot, T t10) {
        T t11;
        if (snapshot.getReadOnly()) {
            snapshot.mo3089recordModified$runtime_release(stateObject);
        }
        int id = snapshot.getId();
        if (t10.getSnapshotId() == id) {
            return t10;
        }
        synchronized (getLock()) {
            t11 = (T) newOverwritableRecordLocked(t2, stateObject);
        }
        t11.setSnapshotId$runtime_release(id);
        if (t10.getSnapshotId() != 1) {
            snapshot.mo3089recordModified$runtime_release(stateObject);
        }
        return t11;
    }

    private static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        int iLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId);
        StateRecord stateRecord2 = null;
        StateRecord firstStateRecord = null;
        int i10 = 0;
        for (StateRecord firstStateRecord2 = stateObject.getFirstStateRecord(); firstStateRecord2 != null; firstStateRecord2 = firstStateRecord2.getNext()) {
            int snapshotId$runtime_release = firstStateRecord2.getSnapshotId();
            if (snapshotId$runtime_release != 0) {
                if (snapshotId$runtime_release >= iLowestOrDefault) {
                    i10++;
                } else if (stateRecord2 == null) {
                    i10++;
                    stateRecord2 = firstStateRecord2;
                } else {
                    if (firstStateRecord2.getSnapshotId() < stateRecord2.getSnapshotId()) {
                        stateRecord = stateRecord2;
                        stateRecord2 = firstStateRecord2;
                    } else {
                        stateRecord = firstStateRecord2;
                    }
                    if (firstStateRecord == null) {
                        firstStateRecord = stateObject.getFirstStateRecord();
                        StateRecord stateRecord3 = firstStateRecord;
                        while (true) {
                            if (firstStateRecord == null) {
                                firstStateRecord = stateRecord3;
                                break;
                            }
                            if (firstStateRecord.getSnapshotId() >= iLowestOrDefault) {
                                break;
                            }
                            if (stateRecord3.getSnapshotId() < firstStateRecord.getSnapshotId()) {
                                stateRecord3 = firstStateRecord;
                            }
                            firstStateRecord = firstStateRecord.getNext();
                        }
                    }
                    stateRecord2.setSnapshotId$runtime_release(0);
                    stateRecord2.assign(firstStateRecord);
                    stateRecord2 = stateRecord;
                }
            }
        }
        return i10 > 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processForUnusedRecordsLocked(StateObject stateObject) {
        if (overwriteUnusedRecordsLocked(stateObject)) {
            extraStateObjects.add(stateObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends StateRecord> T readable(T t2, int i10, SnapshotIdSet snapshotIdSet) {
        T t10 = null;
        while (t2 != null) {
            if (valid(t2, i10, snapshotIdSet) && (t10 == null || t10.getSnapshotId() < t2.getSnapshotId())) {
                t10 = t2;
            }
            t2 = (T) t2.getNext();
        }
        if (t10 != null) {
            return t10;
        }
        return null;
    }

    public static final void releasePinningLocked(int i10) {
        pinningTable.remove(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void reportReadonlySnapshotWrite() {
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot");
    }

    public static final <T> T sync(r7.a<? extends T> aVar) {
        T t2;
        synchronized (getLock()) {
            t2 = (T) aVar.invoke();
        }
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T takeNewGlobalSnapshot(Snapshot snapshot, l<? super SnapshotIdSet, ? extends T> lVar) {
        T t2 = (T) lVar.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (getLock()) {
            int i10 = nextSnapshotId;
            nextSnapshotId = i10 + 1;
            openSnapshots = openSnapshots.clear(snapshot.getId());
            currentGlobalSnapshot.set(new GlobalSnapshot(i10, openSnapshots));
            snapshot.dispose();
            openSnapshots = openSnapshots.set(i10);
        }
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends Snapshot> T takeNewSnapshot(l<? super SnapshotIdSet, ? extends T> lVar) {
        return (T) advanceGlobalSnapshot(new C06041(lVar));
    }

    public static final int trackPinning(int i10, SnapshotIdSet snapshotIdSet) {
        int iAdd;
        int iLowest = snapshotIdSet.lowest(i10);
        synchronized (getLock()) {
            iAdd = pinningTable.add(iLowest);
        }
        return iAdd;
    }

    private static final StateRecord usedLocked(StateObject stateObject) {
        int iLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId) - 1;
        SnapshotIdSet empty = SnapshotIdSet.INSTANCE.getEMPTY();
        StateRecord stateRecord = null;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
            if (firstStateRecord.getSnapshotId() != 0) {
                if (valid(firstStateRecord, iLowestOrDefault, empty)) {
                    if (stateRecord == null) {
                        stateRecord = firstStateRecord;
                    } else if (firstStateRecord.getSnapshotId() >= stateRecord.getSnapshotId()) {
                        return stateRecord;
                    }
                }
            }
            return firstStateRecord;
        }
        return null;
    }

    private static final boolean valid(int i10, int i11, SnapshotIdSet snapshotIdSet) {
        return (i11 == 0 || i11 > i10 || snapshotIdSet.get(i11)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateOpen(Snapshot snapshot) {
        int iLowestOrDefault;
        if (openSnapshots.get(snapshot.getId())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("Snapshot is not open: id=");
        sb2.append(snapshot.getId());
        sb2.append(", disposed=");
        sb2.append(snapshot.getDisposed());
        sb2.append(", applied=");
        MutableSnapshot mutableSnapshot = snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null;
        sb2.append(mutableSnapshot != null ? Boolean.valueOf(mutableSnapshot.getApplied()) : "read-only");
        sb2.append(", lowestPin=");
        synchronized (getLock()) {
            iLowestOrDefault = pinningTable.lowestOrDefault(-1);
        }
        sb2.append(iLowestOrDefault);
        throw new IllegalStateException(sb2.toString().toString());
    }

    public static final <T extends StateRecord, R> R withCurrent(T t2, l<? super T, ? extends R> lVar) {
        return (R) lVar.invoke(current(t2));
    }

    public static final <T extends StateRecord, R> R writable(T t2, StateObject stateObject, l<? super T, ? extends R> lVar) {
        Snapshot current;
        R r4;
        getSnapshotInitializer();
        synchronized (getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            r4 = (R) lVar.invoke(writableRecord(t2, stateObject, current));
        }
        notifyWrite(current, stateObject);
        return r4;
    }

    public static final <T extends StateRecord> T writableRecord(T t2, StateObject stateObject, Snapshot snapshot) {
        T t10;
        if (snapshot.getReadOnly()) {
            snapshot.mo3089recordModified$runtime_release(stateObject);
        }
        int id = snapshot.getId();
        T t11 = (T) readable(t2, id, snapshot.getInvalid());
        if (t11 == null) {
            readError();
            throw new KotlinNothingValueException();
        }
        if (t11.getSnapshotId() == snapshot.getId()) {
            return t11;
        }
        synchronized (getLock()) {
            t10 = (T) readable(stateObject.getFirstStateRecord(), id, snapshot.getInvalid());
            if (t10 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
            if (t10.getSnapshotId() != id) {
                t10 = (T) newWritableRecordLocked(t10, stateObject, snapshot);
            }
        }
        if (t11.getSnapshotId() != 1) {
            snapshot.mo3089recordModified$runtime_release(stateObject);
        }
        return t10;
    }

    public static final <T extends StateRecord> T current(T t2) {
        T t10;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot current = companion.getCurrent();
        T t11 = (T) readable(t2, current.getId(), current.getInvalid());
        if (t11 != null) {
            return t11;
        }
        synchronized (getLock()) {
            Snapshot current2 = companion.getCurrent();
            t10 = (T) readable(t2, current2.getId(), current2.getInvalid());
        }
        if (t10 != null) {
            return t10;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    private static final boolean valid(StateRecord stateRecord, int i10, SnapshotIdSet snapshotIdSet) {
        return valid(i10, stateRecord.getSnapshotId(), snapshotIdSet);
    }

    public static final <T extends StateRecord> T readable(T t2, StateObject stateObject) {
        T t10;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot current = companion.getCurrent();
        l<Object, t0> readObserver = current.getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(stateObject);
        }
        T t11 = (T) readable(t2, current.getId(), current.getInvalid());
        if (t11 != null) {
            return t11;
        }
        synchronized (getLock()) {
            Snapshot current2 = companion.getCurrent();
            t10 = (T) readable(stateObject.getFirstStateRecord(), current2.getId(), current2.getInvalid());
            if (t10 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
        }
        return t10;
    }

    public static final <T extends StateRecord, R> R writable(T t2, StateObject stateObject, Snapshot snapshot, l<? super T, ? extends R> lVar) {
        R r4;
        synchronized (getLock()) {
            r4 = (R) lVar.invoke(writableRecord(t2, stateObject, snapshot));
        }
        notifyWrite(snapshot, stateObject);
        return r4;
    }

    public static final <T extends StateRecord> T readable(T t2, StateObject stateObject, Snapshot snapshot) {
        l<Object, t0> readObserver = snapshot.getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(stateObject);
        }
        T t10 = (T) readable(t2, snapshot.getId(), snapshot.getInvalid());
        if (t10 != null) {
            return t10;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void advanceGlobalSnapshot() {
        advanceGlobalSnapshot(AnonymousClass3.INSTANCE);
    }
}
