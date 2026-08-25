package androidx.compose.runtime;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.tooling.CompositionData;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import ka.k0;
import ka.m0;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import na.h1;
import na.q0;
import na.y0;
import r7.p;
import r7.q;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¶\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b#\b\u0007\u0018\u0000 á\u00012\u00020\u0001:\ná\u0001â\u0001ã\u0001ä\u0001å\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u0086@¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0002H\u0087@¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\tH\u0086@¢\u0006\u0004\b\u0012\u0010\u000bJ*\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00132\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\u0016H\u0010¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\tH\u0086@¢\u0006\u0004\b\u001b\u0010\u000bJ\r\u0010\u001c\u001a\u00020\t¢\u0006\u0004\b\u001c\u0010\u0010J\r\u0010\u001d\u001a\u00020\t¢\u0006\u0004\b\u001d\u0010\u0010J\u001d\u0010#\u001a\u00020\t2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0010¢\u0006\u0004\b!\u0010\"J\u0017\u0010&\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0010¢\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0010¢\u0006\u0004\b'\u0010%J\u0017\u0010*\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0010¢\u0006\u0004\b)\u0010%J\u0017\u0010/\u001a\u00020\t2\u0006\u0010,\u001a\u00020+H\u0010¢\u0006\u0004\b-\u0010.J\u0017\u00104\u001a\u00020\t2\u0006\u00101\u001a\u000200H\u0010¢\u0006\u0004\b2\u00103J\u0017\u00106\u001a\u00020\t2\u0006\u00101\u001a\u000200H\u0010¢\u0006\u0004\b5\u00103J\u001f\u0010;\u001a\u00020\t2\u0006\u00101\u001a\u0002002\u0006\u00108\u001a\u000207H\u0010¢\u0006\u0004\b9\u0010:J\u0017\u0010=\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0010¢\u0006\u0004\b<\u0010%J\u0019\u0010@\u001a\u0004\u0018\u0001072\u0006\u00101\u001a\u000200H\u0010¢\u0006\u0004\b>\u0010?J\u0017\u0010B\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010AH\u0002¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020DH\u0002¢\u0006\u0004\bE\u0010FJ$\u0010E\u001a\u00020\t2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0GH\u0082\b¢\u0006\u0004\bE\u0010IJ\u0017\u0010L\u001a\u00020\t2\u0006\u0010K\u001a\u00020JH\u0002¢\u0006\u0004\bL\u0010MJ1\u0010S\u001a\u00020\t2\n\u0010P\u001a\u00060Nj\u0002`O2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010R\u001a\u00020DH\u0002¢\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\tH\u0002¢\u0006\u0004\bU\u0010\u0010J\u0017\u0010V\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\bV\u0010%J\u0017\u0010W\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\bW\u0010%J\u0011\u0010Y\u001a\u0004\u0018\u00010XH\u0002¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020\tH\u0002¢\u0006\u0004\b[\u0010\u0010J\u0017\u0010\\\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\\\u0010%J \u0010a\u001a\u00020\t2\u0006\u0010^\u001a\u00020]2\u0006\u0010`\u001a\u00020_H\u0082@¢\u0006\u0004\ba\u0010bJ\u0010\u0010c\u001a\u00020\tH\u0082@¢\u0006\u0004\bc\u0010\u000bJN\u0010l\u001a\u00020\t2<\u0010k\u001a8\b\u0001\u0012\u0004\u0012\u00020e\u0012\u0013\u0012\u00110]¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(^\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0h\u0012\u0006\u0012\u0004\u0018\u00010i0d¢\u0006\u0002\bjH\u0082@¢\u0006\u0004\bl\u0010mJ\u0017\u0010n\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\bn\u0010%J)\u0010q\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u000e\u0010p\u001a\n\u0012\u0004\u0012\u00020i\u0018\u00010oH\u0002¢\u0006\u0004\bq\u0010rJ3\u0010u\u001a\b\u0012\u0004\u0012\u00020\u00130s2\f\u0010t\u001a\b\u0012\u0004\u0012\u0002000s2\u000e\u0010p\u001a\n\u0012\u0004\u0012\u00020i\u0018\u00010oH\u0002¢\u0006\u0004\bu\u0010vJ\u000f\u0010w\u001a\u00020\tH\u0002¢\u0006\u0004\bw\u0010\u0010J#\u0010x\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020\t0G2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\bx\u0010yJ3\u0010z\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020\t0G2\u0006\u0010\u0014\u001a\u00020\u00132\u000e\u0010p\u001a\n\u0012\u0004\u0012\u00020i\u0018\u00010oH\u0002¢\u0006\u0004\bz\u0010{J<\u0010}\u001a\u00028\u0000\"\u0004\b\u0000\u0010|2\u0006\u0010\u0014\u001a\u00020\u00132\u000e\u0010p\u001a\n\u0012\u0004\u0012\u00020i\u0018\u00010o2\f\u0010k\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0082\b¢\u0006\u0004\b}\u0010~J\u001b\u0010\u0081\u0001\u001a\u00020\t2\u0007\u0010\u0080\u0001\u001a\u00020\u007fH\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001R,\u0010\u0085\u0001\u001a\u00030\u0083\u00012\b\u0010\u0084\u0001\u001a\u00030\u0083\u00018\u0006@BX\u0086\u000e¢\u0006\u0010\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0018\u0010\u008a\u0001\u001a\u00030\u0089\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0017\u0010\u008c\u0001\u001a\u00020i8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u001b\u0010\u008e\u0001\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u001c\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0090\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u0092\u0001R\u001e\u0010\u0094\u0001\u001a\t\u0012\u0004\u0012\u00020\u00130\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R!\u0010\u0096\u0001\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010s8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0095\u0001R\u001f\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020i0o8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001e\u0010\u009a\u0001\u001a\t\u0012\u0004\u0012\u00020\u00130\u0099\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u009b\u0001R\u001e\u0010\u009c\u0001\u001a\t\u0012\u0004\u0012\u00020\u00130\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u0095\u0001R\u001e\u0010\u009d\u0001\u001a\t\u0012\u0004\u0012\u0002000\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009d\u0001\u0010\u0095\u0001R4\u0010 \u0001\u001a\u001f\u0012\r\u0012\u000b\u0012\u0006\u0012\u0004\u0018\u00010i0\u009f\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002000\u0093\u00010\u009e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b \u0001\u0010¡\u0001R$\u0010¢\u0001\u001a\u000f\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002070\u009e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010¡\u0001R\"\u0010£\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0093\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b£\u0001\u0010\u0095\u0001R!\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¤\u0001\u0010¥\u0001R!\u0010¦\u0001\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010A8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¦\u0001\u0010§\u0001R\u001a\u0010©\u0001\u001a\u00030¨\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b©\u0001\u0010ª\u0001R\u0019\u0010«\u0001\u001a\u00020D8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b«\u0001\u0010¬\u0001R\u001b\u0010\u00ad\u0001\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u00ad\u0001\u0010®\u0001R\u0019\u0010¯\u0001\u001a\u00020D8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¯\u0001\u0010¬\u0001R\u001f\u0010²\u0001\u001a\n\u0012\u0005\u0012\u00030±\u00010°\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b²\u0001\u0010³\u0001R\u0018\u0010µ\u0001\u001a\u00030´\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R\u001d\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\u0003\u0010·\u0001\u001a\u0006\b¸\u0001\u0010¹\u0001R\u001c\u0010»\u0001\u001a\u00070º\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\b»\u0001\u0010¼\u0001R\u0016\u0010\f\u001a\u00020\u00028PX\u0090\u0004¢\u0006\b\u001a\u0006\b½\u0001\u0010¹\u0001R&\u0010Â\u0001\u001a\n\u0012\u0005\u0012\u00030±\u00010¾\u00018FX\u0087\u0004¢\u0006\u000f\u0012\u0005\bÁ\u0001\u0010\u0010\u001a\u0006\b¿\u0001\u0010À\u0001R\u001c\u0010Æ\u0001\u001a\n\u0012\u0005\u0012\u00030±\u00010Ã\u00018F¢\u0006\b\u001a\u0006\bÄ\u0001\u0010Å\u0001R\u0013\u0010È\u0001\u001a\u00020D8F¢\u0006\u0007\u001a\u0005\bÇ\u0001\u0010FR\u0018\u0010Ë\u0001\u001a\u00030¨\u00018PX\u0090\u0004¢\u0006\b\u001a\u0006\bÉ\u0001\u0010Ê\u0001R\u0016\u0010Í\u0001\u001a\u00020D8PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÌ\u0001\u0010FR\u0016\u0010Ï\u0001\u001a\u00020D8PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÎ\u0001\u0010FR\u0016\u0010Ñ\u0001\u001a\u00020D8PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÐ\u0001\u0010FR\u001d\u0010Ô\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130s8BX\u0082\u0004¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ó\u0001R\u0016\u0010Ö\u0001\u001a\u00020D8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÕ\u0001\u0010FR\u0016\u0010Ø\u0001\u001a\u00020D8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b×\u0001\u0010FR\u0016\u0010Ú\u0001\u001a\u00020D8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÙ\u0001\u0010FR\u0016\u0010Ü\u0001\u001a\u00020D8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÛ\u0001\u0010FR\u0016\u0010Þ\u0001\u001a\u00020D8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÝ\u0001\u0010FR\u0016\u0010à\u0001\u001a\u00020D8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bß\u0001\u0010F¨\u0006æ\u0001"}, d2 = {"Landroidx/compose/runtime/Recomposer;", "Landroidx/compose/runtime/CompositionContext;", "Ld7/j;", "effectCoroutineContext", "<init>", "(Ld7/j;)V", "Landroidx/compose/runtime/RecomposerInfo;", "asRecomposerInfo", "()Landroidx/compose/runtime/RecomposerInfo;", "Lx6/t0;", "runRecomposeAndApplyChanges", "(Ld7/d;)Ljava/lang/Object;", "recomposeCoroutineContext", "runRecomposeConcurrentlyAndApplyChanges", "(Ld7/j;Ld7/d;)Ljava/lang/Object;", "cancel", "()V", "close", "join", "Landroidx/compose/runtime/ControlledComposition;", "composition", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "composeInitial$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;Lr7/p;)V", "composeInitial", "awaitIdle", "pauseCompositionFrameClock", "resumeCompositionFrameClock", "", "Landroidx/compose/runtime/tooling/CompositionData;", "table", "recordInspectionTable$runtime_release", "(Ljava/util/Set;)V", "recordInspectionTable", "registerComposition$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;)V", "registerComposition", "unregisterComposition$runtime_release", "unregisterComposition", "invalidate$runtime_release", "invalidate", "Landroidx/compose/runtime/RecomposeScopeImpl;", "scope", "invalidateScope$runtime_release", "(Landroidx/compose/runtime/RecomposeScopeImpl;)V", "invalidateScope", "Landroidx/compose/runtime/MovableContentStateReference;", "reference", "insertMovableContent$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;)V", "insertMovableContent", "deletedMovableContent$runtime_release", "deletedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "data", "movableContentStateReleased$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;Landroidx/compose/runtime/MovableContentState;)V", "movableContentStateReleased", "reportRemovedComposition$runtime_release", "reportRemovedComposition", "movableContentStateResolve$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;)Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve", "Lka/k;", "deriveStateLocked", "()Lka/k;", "", "recordComposerModifications", "()Z", "Lkotlin/Function1;", "onEachInvalidComposition", "(Lr7/l;)V", "Lka/v1;", "callingJob", "registerRunnerJob", "(Lka/v1;)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "failedInitialComposition", "recoverable", "processCompositionError", "(Ljava/lang/Exception;Landroidx/compose/runtime/ControlledComposition;Z)V", "clearKnownCompositionsLocked", "removeKnownCompositionLocked", "addKnownCompositionLocked", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "resetErrorState", "()Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "retryFailedCompositions", "recordFailedCompositionLocked", "Landroidx/compose/runtime/MonotonicFrameClock;", "parentFrameClock", "Landroidx/compose/runtime/ProduceFrameSignal;", "frameSignal", "runFrameLoop", "(Landroidx/compose/runtime/MonotonicFrameClock;Landroidx/compose/runtime/ProduceFrameSignal;Ld7/d;)Ljava/lang/Object;", "awaitWorkAvailable", "Lkotlin/Function3;", "Lka/k0;", "Lx6/y;", ContentDisposition.Parameters.Name, "Ld7/d;", "", "Lx6/n;", "block", "recompositionRunner", "(Lr7/q;Ld7/d;)Ljava/lang/Object;", "performInitialMovableContentInserts", "Lt/d0;", "modifiedValues", "performRecompose", "(Landroidx/compose/runtime/ControlledComposition;Lt/d0;)Landroidx/compose/runtime/ControlledComposition;", "", "references", "performInsertValues", "(Ljava/util/List;Lt/d0;)Ljava/util/List;", "discardUnusedValues", "readObserverOf", "(Landroidx/compose/runtime/ControlledComposition;)Lr7/l;", "writeObserverOf", "(Landroidx/compose/runtime/ControlledComposition;Lt/d0;)Lr7/l;", "T", "composing", "(Landroidx/compose/runtime/ControlledComposition;Lt/d0;Lr7/a;)Ljava/lang/Object;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "snapshot", "applyAndCheck", "(Landroidx/compose/runtime/snapshots/MutableSnapshot;)V", "", "<set-?>", "changeCount", "J", "getChangeCount", "()J", "Landroidx/compose/runtime/BroadcastFrameClock;", "broadcastFrameClock", "Landroidx/compose/runtime/BroadcastFrameClock;", "stateLock", "Ljava/lang/Object;", "runnerJob", "Lka/v1;", "", "closeCause", "Ljava/lang/Throwable;", "", "_knownCompositions", "Ljava/util/List;", "_knownCompositionsCache", "snapshotInvalidations", "Lt/d0;", "Landroidx/compose/runtime/collection/MutableVector;", "compositionInvalidations", "Landroidx/compose/runtime/collection/MutableVector;", "compositionsAwaitingApply", "compositionValuesAwaitingInsert", "", "Landroidx/compose/runtime/MovableContent;", "compositionValuesRemoved", "Ljava/util/Map;", "compositionValueStatesAvailable", "failedCompositions", "compositionsRemoved", "Ljava/util/Set;", "workContinuation", "Lka/k;", "", "concurrentCompositionsOutstanding", "I", "isClosed", "Z", "errorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "frameClockPaused", "Lna/q0;", "Landroidx/compose/runtime/Recomposer$State;", "_state", "Lna/q0;", "Lka/t;", "effectJob", "Lka/t;", "Ld7/j;", "getEffectCoroutineContext", "()Ld7/j;", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "recomposerInfo", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "getRecomposeCoroutineContext$runtime_release", "Lna/j;", "getState", "()Lna/j;", "getState$annotations", "state", "Lna/h1;", "getCurrentState", "()Lna/h1;", "currentState", "getHasPendingWork", "hasPendingWork", "getCompoundHashKey$runtime_release", "()I", "compoundHashKey", "getCollectingCallByInformation$runtime_release", "collectingCallByInformation", "getCollectingParameterInformation$runtime_release", "collectingParameterInformation", "getCollectingSourceInformation$runtime_release", "collectingSourceInformation", "getKnownCompositions", "()Ljava/util/List;", "knownCompositions", "getHasBroadcastFrameClockAwaitersLocked", "hasBroadcastFrameClockAwaitersLocked", "getHasBroadcastFrameClockAwaiters", "hasBroadcastFrameClockAwaiters", "getShouldKeepRecomposing", "shouldKeepRecomposing", "getHasSchedulingWork", "hasSchedulingWork", "getHasFrameWorkLocked", "hasFrameWorkLocked", "getHasConcurrentFrameWorkLocked", "hasConcurrentFrameWorkLocked", "Companion", "HotReloadable", "RecomposerErrorState", "RecomposerInfoImpl", "State", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Recomposer extends CompositionContext {
    private final List<ControlledComposition> _knownCompositions;
    private List<? extends ControlledComposition> _knownCompositionsCache;
    private final q0<State> _state;
    private final BroadcastFrameClock broadcastFrameClock;
    private long changeCount;
    private Throwable closeCause;
    private final MutableVector<ControlledComposition> compositionInvalidations;
    private final Map<MovableContentStateReference, MovableContentState> compositionValueStatesAvailable;
    private final List<MovableContentStateReference> compositionValuesAwaitingInsert;
    private final Map<MovableContent<Object>, List<MovableContentStateReference>> compositionValuesRemoved;
    private final List<ControlledComposition> compositionsAwaitingApply;
    private Set<ControlledComposition> compositionsRemoved;
    private int concurrentCompositionsOutstanding;
    private final d7.j effectCoroutineContext;
    private final t effectJob;
    private RecomposerErrorState errorState;
    private List<ControlledComposition> failedCompositions;
    private boolean frameClockPaused;
    private boolean isClosed;
    private final RecomposerInfoImpl recomposerInfo;
    private v1 runnerJob;
    private d0 snapshotInvalidations;
    private final Object stateLock;
    private ka.k<? super t0> workContinuation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final q0<PersistentSet<RecomposerInfoImpl>> _runningRecomposers = y0.b(ExtensionsKt.persistentSetOf());
    private static final AtomicReference<Boolean> _hotReloadEnabled = new AtomicReference<>(Boolean.FALSE);

    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00072\n\u0010\u0006\u001a\u00060\u0004R\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\n\u001a\u00020\u00072\n\u0010\u0006\u001a\u00060\u0004R\u00020\u0005H\u0002¢\u0006\u0004\b\n\u0010\tJ\u0017\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0012\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010\"\u001a\u00020\u0007H\u0000¢\u0006\u0004\b!\u0010\u0003R\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0#8F¢\u0006\u0006\u001a\u0004\b&\u0010'R4\u0010,\u001a\"\u0012\f\u0012\n **\u0004\u0018\u00010\u000b0\u000b0)j\u0010\u0012\f\u0012\n **\u0004\u0018\u00010\u000b0\u000b`+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R$\u00100\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0004R\u00020\u00050/0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101¨\u00062"}, d2 = {"Landroidx/compose/runtime/Recomposer$Companion;", "", "<init>", "()V", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/Recomposer;", "info", "Lx6/t0;", "addRunning", "(Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;)V", "removeRunning", "", "value", "setHotReloadEnabled$runtime_release", "(Z)V", "setHotReloadEnabled", "saveStateAndDisposeForHotReload$runtime_release", "()Ljava/lang/Object;", "saveStateAndDisposeForHotReload", "token", "loadStateAndComposeForHotReload$runtime_release", "(Ljava/lang/Object;)V", "loadStateAndComposeForHotReload", "", "key", "invalidateGroupsWithKey$runtime_release", "(I)V", "invalidateGroupsWithKey", "", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentErrors$runtime_release", "()Ljava/util/List;", "getCurrentErrors", "clearErrors$runtime_release", "clearErrors", "Lna/h1;", "", "Landroidx/compose/runtime/RecomposerInfo;", "getRunningRecomposers", "()Lna/h1;", "runningRecomposers", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "Landroidx/compose/runtime/AtomicReference;", "_hotReloadEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "Lna/q0;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "_runningRecomposers", "Lna/q0;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetAdd;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetAdd = persistentSet.add(info);
                if (persistentSet == persistentSetAdd) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.e(persistentSet, persistentSetAdd));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetRemove;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetRemove = persistentSet.remove(info);
                if (persistentSet == persistentSetRemove) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.e(persistentSet, persistentSetRemove));
        }

        public final void clearErrors$runtime_release() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                RecomposerErrorState recomposerErrorStateResetErrorState = ((RecomposerInfoImpl) it.next()).resetErrorState();
                if (recomposerErrorStateResetErrorState != null) {
                    arrayList.add(recomposerErrorStateResetErrorState);
                }
            }
        }

        public final List<RecomposerErrorInfo> getCurrentErrors$runtime_release() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                RecomposerErrorInfo currentError = ((RecomposerInfoImpl) it.next()).getCurrentError();
                if (currentError != null) {
                    arrayList.add(currentError);
                }
            }
            return arrayList;
        }

        public final h1<Set<RecomposerInfo>> getRunningRecomposers() {
            return Recomposer._runningRecomposers;
        }

        public final void invalidateGroupsWithKey$runtime_release(int key) {
            Recomposer._hotReloadEnabled.set(Boolean.TRUE);
            for (RecomposerInfoImpl recomposerInfoImpl : (Iterable) Recomposer._runningRecomposers.getValue()) {
                RecomposerErrorInfo currentError = recomposerInfoImpl.getCurrentError();
                if (currentError == null || currentError.getRecoverable()) {
                    recomposerInfoImpl.resetErrorState();
                    recomposerInfoImpl.invalidateGroupsWithKey(key);
                    recomposerInfoImpl.retryFailedCompositions();
                }
            }
        }

        public final void loadStateAndComposeForHotReload$runtime_release(Object token) {
            Recomposer._hotReloadEnabled.set(Boolean.TRUE);
            Iterator it = ((Iterable) Recomposer._runningRecomposers.getValue()).iterator();
            while (it.hasNext()) {
                ((RecomposerInfoImpl) it.next()).resetErrorState();
            }
            List list = (List) token;
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                ((HotReloadable) list.get(i10)).resetContent();
            }
            int size2 = list.size();
            for (int i11 = 0; i11 < size2; i11++) {
                ((HotReloadable) list.get(i11)).recompose();
            }
            Iterator it2 = ((Iterable) Recomposer._runningRecomposers.getValue()).iterator();
            while (it2.hasNext()) {
                ((RecomposerInfoImpl) it2.next()).retryFailedCompositions();
            }
        }

        public final Object saveStateAndDisposeForHotReload$runtime_release() {
            Recomposer._hotReloadEnabled.set(Boolean.TRUE);
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                x.b0(arrayList, ((RecomposerInfoImpl) it.next()).saveStateAndDisposeForHotReload());
            }
            return arrayList;
        }

        public final void setHotReloadEnabled$runtime_release(boolean value) {
            Recomposer._hotReloadEnabled.set(Boolean.valueOf(value));
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR!\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/Recomposer$HotReloadable;", "", "Landroidx/compose/runtime/CompositionImpl;", "composition", "<init>", "(Landroidx/compose/runtime/CompositionImpl;)V", "Lx6/t0;", "clearContent", "()V", "resetContent", "recompose", "Landroidx/compose/runtime/CompositionImpl;", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composable", "Lr7/p;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class HotReloadable {
        private p<? super Composer, ? super Integer, t0> composable;
        private final CompositionImpl composition;

        public HotReloadable(CompositionImpl compositionImpl) {
            this.composition = compositionImpl;
            this.composable = compositionImpl.getComposable();
        }

        public final void clearContent() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(ComposableSingletons$RecomposerKt.INSTANCE.m2948getLambda1$runtime_release());
            }
        }

        public final void recompose() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(this.composable);
            }
        }

        public final void resetContent() {
            this.composition.setComposable(this.composable);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007R\u0018\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "Landroidx/compose/runtime/RecomposerErrorInfo;", "recoverable", "", "cause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(ZLjava/lang/Exception;)V", "getCause", "()Ljava/lang/Exception;", "getRecoverable", "()Z", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RecomposerErrorState implements RecomposerErrorInfo {
        private final Exception cause;
        private final boolean recoverable;

        public RecomposerErrorState(boolean z, Exception exc) {
            this.recoverable = z;
            this.cause = exc;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public Exception getCause() {
            return this.cause;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public boolean getRecoverable() {
            return this.recoverable;
        }
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\"\u001a\u0004\u0018\u00010\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/RecomposerInfo;", "<init>", "(Landroidx/compose/runtime/Recomposer;)V", "", "key", "Lx6/t0;", "invalidateGroupsWithKey", "(I)V", "", "Landroidx/compose/runtime/Recomposer$HotReloadable;", "saveStateAndDisposeForHotReload", "()Ljava/util/List;", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "resetErrorState", "()Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "retryFailedCompositions", "()V", "Lna/j;", "Landroidx/compose/runtime/Recomposer$State;", "getState", "()Lna/j;", "state", "", "getHasPendingWork", "()Z", "hasPendingWork", "", "getChangeCount", "()J", "changeCount", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentError", "()Landroidx/compose/runtime/RecomposerErrorInfo;", "currentError", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class RecomposerInfoImpl implements RecomposerInfo {
        public RecomposerInfoImpl() {
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public long getChangeCount() {
            return Recomposer.this.getChangeCount();
        }

        public final RecomposerErrorInfo getCurrentError() {
            RecomposerErrorState recomposerErrorState;
            Object obj = Recomposer.this.stateLock;
            Recomposer recomposer = Recomposer.this;
            synchronized (obj) {
                recomposerErrorState = recomposer.errorState;
            }
            return recomposerErrorState;
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public boolean getHasPendingWork() {
            return Recomposer.this.getHasPendingWork();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public na.j<State> getState() {
            return Recomposer.this.getCurrentState();
        }

        public final void invalidateGroupsWithKey(int key) {
            List knownCompositions;
            Object obj = Recomposer.this.stateLock;
            Recomposer recomposer = Recomposer.this;
            synchronized (obj) {
                knownCompositions = recomposer.getKnownCompositions();
            }
            ArrayList arrayList = new ArrayList(knownCompositions.size());
            int size = knownCompositions.size();
            for (int i10 = 0; i10 < size; i10++) {
                ControlledComposition controlledComposition = (ControlledComposition) knownCompositions.get(i10);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
            }
            int size2 = arrayList.size();
            for (int i11 = 0; i11 < size2; i11++) {
                ((CompositionImpl) arrayList.get(i11)).invalidateGroupsWithKey(key);
            }
        }

        public final RecomposerErrorState resetErrorState() {
            return Recomposer.this.resetErrorState();
        }

        public final void retryFailedCompositions() {
            Recomposer.this.retryFailedCompositions();
        }

        public final List<HotReloadable> saveStateAndDisposeForHotReload() {
            List knownCompositions;
            Object obj = Recomposer.this.stateLock;
            Recomposer recomposer = Recomposer.this;
            synchronized (obj) {
                knownCompositions = recomposer.getKnownCompositions();
            }
            ArrayList arrayList = new ArrayList(knownCompositions.size());
            int size = knownCompositions.size();
            for (int i10 = 0; i10 < size; i10++) {
                ControlledComposition controlledComposition = (ControlledComposition) knownCompositions.get(i10);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i11 = 0; i11 < size2; i11++) {
                HotReloadable hotReloadable = new HotReloadable((CompositionImpl) arrayList.get(i11));
                hotReloadable.clearContent();
                arrayList2.add(hotReloadable);
            }
            return arrayList2;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "", "(Ljava/lang/String;I)V", "ShutDown", "ShuttingDown", "Inactive", "InactivePendingWork", "Idle", "PendingWork", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum State {
        ShutDown,
        ShuttingDown,
        Inactive,
        InactivePendingWork,
        Idle,
        PendingWork
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$awaitIdle$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.runtime.Recomposer$awaitIdle$2", f = "Recomposer.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends f7.j implements p<State, d7.d<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(State state, d7.d<? super Boolean> dVar) {
            return ((AnonymousClass2) create(state, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return Boolean.valueOf(((State) this.L$0).compareTo(State.Idle) > 0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$join$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.runtime.Recomposer$join$2", f = "Recomposer.kt", l = {}, m = "invokeSuspend")
    public static final class C05942 extends f7.j implements p<State, d7.d<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C05942(d7.d<? super C05942> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C05942 c05942 = new C05942(dVar);
            c05942.L$0 = obj;
            return c05942;
        }

        @Override // r7.p
        public final Object invoke(State state, d7.d<? super Boolean> dVar) {
            return ((C05942) create(state, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return Boolean.valueOf(((State) this.L$0) == State.ShutDown);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$readObserverOf$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "value", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.l<Object, t0> {
        final /* synthetic */ ControlledComposition $composition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ControlledComposition controlledComposition) {
            super(1);
            this.$composition = controlledComposition;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m2978invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m2978invoke(Object obj) {
            this.$composition.recordReadOf(obj);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2", f = "Recomposer.kt", l = {1054}, m = "invokeSuspend")
    public static final class C05952 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ q<k0, MonotonicFrameClock, d7.d<? super t0>, Object> $block;
        final /* synthetic */ MonotonicFrameClock $parentFrameClock;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2$3", f = "Recomposer.kt", l = {1055}, m = "invokeSuspend")
        public static final class AnonymousClass3 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ q<k0, MonotonicFrameClock, d7.d<? super t0>, Object> $block;
            final /* synthetic */ MonotonicFrameClock $parentFrameClock;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass3(q<? super k0, ? super MonotonicFrameClock, ? super d7.d<? super t0>, ? extends Object> qVar, MonotonicFrameClock monotonicFrameClock, d7.d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.$block = qVar;
                this.$parentFrameClock = monotonicFrameClock;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$block, this.$parentFrameClock, dVar);
                anonymousClass3.L$0 = obj;
                return anonymousClass3;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.L$0;
                    q<k0, MonotonicFrameClock, d7.d<? super t0>, Object> qVar = this.$block;
                    MonotonicFrameClock monotonicFrameClock = this.$parentFrameClock;
                    this.label = 1;
                    Object objInvoke = qVar.invoke(k0Var, monotonicFrameClock, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05952(q<? super k0, ? super MonotonicFrameClock, ? super d7.d<? super t0>, ? extends Object> qVar, MonotonicFrameClock monotonicFrameClock, d7.d<? super C05952> dVar) {
            super(2, dVar);
            this.$block = qVar;
            this.$parentFrameClock = monotonicFrameClock;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C05952 c05952 = Recomposer.this.new C05952(this.$block, this.$parentFrameClock, dVar);
            c05952.L$0 = obj;
            return c05952;
        }

        /* JADX WARN: Removed duplicated region for block: B:55:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 228
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05952.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C05952) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runFrameLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.runtime.Recomposer", f = "Recomposer.kt", l = {921, 928}, m = "runFrameLoop")
    public static final class C05961 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C05961(d7.d<? super C05961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Recomposer.this.runFrameLoop(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runFrameLoop$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "frameTime", "Lka/k;", "Lx6/t0;", "invoke", "(J)Lka/k;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05972 extends r implements r7.l<Long, ka.k<? super t0>> {
        final /* synthetic */ ProduceFrameSignal $frameSignal;
        final /* synthetic */ List<ControlledComposition> $toApply;
        final /* synthetic */ List<ControlledComposition> $toRecompose;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05972(List<ControlledComposition> list, List<ControlledComposition> list2, ProduceFrameSignal produceFrameSignal) {
            super(1);
            this.$toRecompose = list;
            this.$toApply = list2;
            this.$frameSignal = produceFrameSignal;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).longValue());
        }

        public final ka.k<t0> invoke(long j10) {
            Object objBeginSection;
            int i10;
            ka.k<t0> kVarDeriveStateLocked;
            if (Recomposer.this.getHasBroadcastFrameClockAwaiters()) {
                Recomposer recomposer = Recomposer.this;
                Trace trace = Trace.INSTANCE;
                objBeginSection = trace.beginSection("Recomposer:animation");
                try {
                    recomposer.broadcastFrameClock.sendFrame(j10);
                    Snapshot.INSTANCE.sendApplyNotifications();
                    trace.endSection(objBeginSection);
                } finally {
                }
            }
            Recomposer recomposer2 = Recomposer.this;
            List<ControlledComposition> list = this.$toRecompose;
            List<ControlledComposition> list2 = this.$toApply;
            ProduceFrameSignal produceFrameSignal = this.$frameSignal;
            objBeginSection = Trace.INSTANCE.beginSection("Recomposer:recompose");
            try {
                recomposer2.recordComposerModifications();
                synchronized (recomposer2.stateLock) {
                    try {
                        List list3 = recomposer2.compositionsAwaitingApply;
                        int size = list3.size();
                        for (int i11 = 0; i11 < size; i11++) {
                            list2.add((ControlledComposition) list3.get(i11));
                        }
                        recomposer2.compositionsAwaitingApply.clear();
                        MutableVector mutableVector = recomposer2.compositionInvalidations;
                        int size2 = mutableVector.getSize();
                        if (size2 > 0) {
                            Object[] content = mutableVector.getContent();
                            int i12 = 0;
                            do {
                                list.add((ControlledComposition) content[i12]);
                                i12++;
                            } while (i12 < size2);
                        }
                        recomposer2.compositionInvalidations.clear();
                        produceFrameSignal.takeFrameRequestLocked();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                d0 d0Var = new d0();
                try {
                    int size3 = list.size();
                    for (int i13 = 0; i13 < size3; i13++) {
                        ControlledComposition controlledCompositionPerformRecompose = recomposer2.performRecompose(list.get(i13), d0Var);
                        if (controlledCompositionPerformRecompose != null) {
                            list2.add(controlledCompositionPerformRecompose);
                        }
                    }
                    list.clear();
                    if (!list2.isEmpty()) {
                        recomposer2.changeCount = recomposer2.getChangeCount() + 1;
                    }
                    try {
                        int size4 = list2.size();
                        for (i10 = 0; i10 < size4; i10++) {
                            list2.get(i10).applyChanges();
                        }
                        list2.clear();
                        synchronized (recomposer2.stateLock) {
                            kVarDeriveStateLocked = recomposer2.deriveStateLocked();
                        }
                        return kVarDeriveStateLocked;
                    } catch (Throwable th2) {
                        list2.clear();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    list.clear();
                    throw th3;
                }
            } finally {
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/runtime/MonotonicFrameClock;", "parentFrameClock", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/runtime/MonotonicFrameClock;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", l = {574, 585}, m = "invokeSuspend")
    public static final class C05982 extends f7.j implements q<k0, MonotonicFrameClock, d7.d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;

        /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "frameTime", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements r7.l<Long, t0> {
            final /* synthetic */ d0 $alreadyComposed;
            final /* synthetic */ d0 $modifiedValues;
            final /* synthetic */ Set<Object> $modifiedValuesSet;
            final /* synthetic */ List<ControlledComposition> $toApply;
            final /* synthetic */ d0 $toComplete;
            final /* synthetic */ List<MovableContentStateReference> $toInsert;
            final /* synthetic */ d0 $toLateApply;
            final /* synthetic */ List<ControlledComposition> $toRecompose;
            final /* synthetic */ Recomposer this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(Recomposer recomposer, d0 d0Var, d0 d0Var2, List<ControlledComposition> list, List<MovableContentStateReference> list2, d0 d0Var3, List<ControlledComposition> list3, d0 d0Var4, Set<? extends Object> set) {
                super(1);
                this.this$0 = recomposer;
                this.$modifiedValues = d0Var;
                this.$alreadyComposed = d0Var2;
                this.$toRecompose = list;
                this.$toInsert = list2;
                this.$toLateApply = d0Var3;
                this.$toApply = list3;
                this.$toComplete = d0Var4;
                this.$modifiedValuesSet = set;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).longValue());
                return t0.f22605a;
            }

            /* JADX WARN: Removed duplicated region for block: B:100:0x017a  */
            /* JADX WARN: Removed duplicated region for block: B:137:0x01f8 A[Catch: all -> 0x00cc, TryCatch #5 {all -> 0x00cc, blocks: (B:11:0x0047, B:12:0x004e, B:22:0x0076, B:23:0x0077, B:24:0x007d, B:26:0x0083, B:29:0x008b, B:31:0x0091, B:43:0x00c8, B:57:0x00ea, B:58:0x00ed, B:52:0x00df, B:59:0x00ee, B:95:0x016f, B:98:0x0176, B:99:0x0179, B:88:0x0157, B:101:0x017e, B:125:0x01d9, B:137:0x01f8, B:138:0x01fc, B:140:0x0200, B:141:0x0201, B:143:0x0213, B:144:0x0214, B:135:0x01f4, B:136:0x01f7, B:132:0x01ed, B:156:0x023a, B:158:0x0243, B:184:0x02c0, B:186:0x02c6, B:187:0x02c9, B:189:0x02cf, B:190:0x02da, B:192:0x02e0, B:193:0x02ed, B:199:0x02fc, B:160:0x024d, B:161:0x0251, B:183:0x02bf, B:200:0x0309, B:201:0x030a, B:208:0x0320, B:209:0x0323, B:206:0x031a, B:210:0x0324, B:211:0x0325, B:13:0x004f, B:15:0x0059, B:16:0x005e, B:21:0x006f, B:32:0x009c, B:34:0x00a3, B:39:0x00b5, B:41:0x00bc, B:103:0x0184, B:106:0x018e, B:108:0x019c, B:110:0x01a8, B:112:0x01ae, B:139:0x01fd, B:162:0x0252, B:164:0x025d, B:166:0x0269, B:168:0x026f, B:171:0x0276, B:172:0x0279, B:174:0x0285, B:176:0x0293, B:178:0x0299, B:181:0x02af, B:180:0x02a1, B:182:0x02b2), top: B:223:0x0047, inners: #4, #6, #7, #10, #12, #13 }] */
            /* JADX WARN: Removed duplicated region for block: B:228:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:231:0x0184 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:235:0x01fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:82:0x0148  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke(long r28) {
                /*
                    Method dump skipped, instruction units count: 812
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05982.AnonymousClass1.invoke(long):void");
            }
        }

        public C05982(d7.d<? super C05982> dVar) {
            super(3, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00bc  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00ff  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final void invokeSuspend$clearRecompositionState(androidx.compose.runtime.Recomposer r22, java.util.List<androidx.compose.runtime.ControlledComposition> r23, java.util.List<androidx.compose.runtime.MovableContentStateReference> r24, java.util.List<androidx.compose.runtime.ControlledComposition> r25, t.d0 r26, t.d0 r27, t.d0 r28, t.d0 r29) {
            /*
                Method dump skipped, instruction units count: 267
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05982.invokeSuspend$clearRecompositionState(androidx.compose.runtime.Recomposer, java.util.List, java.util.List, java.util.List, t.d0, t.d0, t.d0, t.d0):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer) {
            list.clear();
            synchronized (recomposer.stateLock) {
                try {
                    List list2 = recomposer.compositionValuesAwaitingInsert;
                    int size = list2.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        list.add((MovableContentStateReference) list2.get(i10));
                    }
                    recomposer.compositionValuesAwaitingInsert.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0119  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0102 -> B:24:0x010a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0110 -> B:12:0x00ad). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 284
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05982.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.q
        public final Object invoke(k0 k0Var, MonotonicFrameClock monotonicFrameClock, d7.d<? super t0> dVar) {
            C05982 c05982 = Recomposer.this.new C05982(dVar);
            c05982.L$0 = monotonicFrameClock;
            return c05982.invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/runtime/MonotonicFrameClock;", "parentFrameClock", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/runtime/MonotonicFrameClock;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2", f = "Recomposer.kt", l = {890, 910, 911}, m = "invokeSuspend")
    public static final class C05992 extends f7.j implements q<k0, MonotonicFrameClock, d7.d<? super t0>, Object> {
        final /* synthetic */ d7.j $recomposeCoroutineContext;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ Recomposer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05992(d7.j jVar, Recomposer recomposer, d7.d<? super C05992> dVar) {
            super(3, dVar);
            this.$recomposeCoroutineContext = jVar;
            this.this$0 = recomposer;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x00b2, code lost:
        
            if (r10.awaitWorkAvailable(r18) == r0) goto L86;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0199, code lost:
        
            if (ka.m0.h(r2, r18) != r0) goto L87;
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x017a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00b2 -> B:25:0x00b6). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 415
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C05992.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.q
        public final Object invoke(k0 k0Var, MonotonicFrameClock monotonicFrameClock, d7.d<? super t0> dVar) {
            C05992 c05992 = new C05992(this.$recomposeCoroutineContext, this.this$0, dVar);
            c05992.L$0 = k0Var;
            c05992.L$1 = monotonicFrameClock;
            return c05992.invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$writeObserverOf$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "value", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06001 extends r implements r7.l<Object, t0> {
        final /* synthetic */ ControlledComposition $composition;
        final /* synthetic */ d0 $modifiedValues;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06001(ControlledComposition controlledComposition, d0 d0Var) {
            super(1);
            this.$composition = controlledComposition;
            this.$modifiedValues = d0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m2979invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m2979invoke(Object obj) {
            this.$composition.recordWriteOf(obj);
            d0 d0Var = this.$modifiedValues;
            if (d0Var != null) {
                d0Var.d(obj);
            }
        }
    }

    public Recomposer(d7.j jVar) {
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Recomposer$broadcastFrameClock$1(this));
        this.broadcastFrameClock = broadcastFrameClock;
        this.stateLock = new Object();
        this._knownCompositions = new ArrayList();
        this.snapshotInvalidations = new d0();
        this.compositionInvalidations = new MutableVector<>(new ControlledComposition[16], 0);
        this.compositionsAwaitingApply = new ArrayList();
        this.compositionValuesAwaitingInsert = new ArrayList();
        this.compositionValuesRemoved = new LinkedHashMap();
        this.compositionValueStatesAvailable = new LinkedHashMap();
        this._state = y0.b(State.Inactive);
        x1 x1Var = new x1((v1) jVar.get(u1.f19642i));
        x1Var.invokeOnCompletion(new Recomposer$effectJob$1$1(this));
        this.effectJob = x1Var;
        this.effectCoroutineContext = jVar.plus(broadcastFrameClock).plus(x1Var);
        this.recomposerInfo = new RecomposerInfoImpl();
    }

    private final void addKnownCompositionLocked(ControlledComposition composition) {
        this._knownCompositions.add(composition);
        this._knownCompositionsCache = null;
    }

    private final void applyAndCheck(MutableSnapshot snapshot) {
        try {
            if (snapshot.apply() instanceof SnapshotApplyResult.Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.");
            }
        } finally {
            snapshot.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitWorkAvailable(d7.d<? super t0> dVar) {
        ka.l lVar;
        if (getHasSchedulingWork()) {
            return t0.f22605a;
        }
        ka.l lVar2 = new ka.l(1, t7.a.A(dVar));
        lVar2.s();
        synchronized (this.stateLock) {
            if (getHasSchedulingWork()) {
                lVar = lVar2;
            } else {
                this.workContinuation = lVar2;
                lVar = null;
            }
        }
        if (lVar != null) {
            lVar.resumeWith(t0.f22605a);
        }
        Object objR = lVar2.r();
        return objR == e7.a.f15033i ? objR : t0.f22605a;
    }

    private final void clearKnownCompositionsLocked() {
        this._knownCompositions.clear();
        this._knownCompositionsCache = z.f19728i;
    }

    private final <T> T composing(ControlledComposition composition, d0 modifiedValues, r7.a<? extends T> block) {
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
            try {
                return (T) block.invoke();
            } finally {
                mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ka.k<t0> deriveStateLocked() {
        State state;
        if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
            clearKnownCompositionsLocked();
            this.snapshotInvalidations = new d0();
            this.compositionInvalidations.clear();
            this.compositionsAwaitingApply.clear();
            this.compositionValuesAwaitingInsert.clear();
            this.failedCompositions = null;
            ka.k<? super t0> kVar = this.workContinuation;
            if (kVar != null) {
                kVar.cancel(null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        if (this.errorState != null) {
            state = State.Inactive;
        } else if (this.runnerJob == null) {
            this.snapshotInvalidations = new d0();
            this.compositionInvalidations.clear();
            state = getHasBroadcastFrameClockAwaitersLocked() ? State.InactivePendingWork : State.Inactive;
        } else {
            state = (this.compositionInvalidations.isNotEmpty() || this.snapshotInvalidations.c() || !this.compositionsAwaitingApply.isEmpty() || !this.compositionValuesAwaitingInsert.isEmpty() || this.concurrentCompositionsOutstanding > 0 || getHasBroadcastFrameClockAwaitersLocked()) ? State.PendingWork : State.Idle;
        }
        this._state.setValue(state);
        if (state != State.PendingWork) {
            return null;
        }
        ka.k kVar2 = this.workContinuation;
        this.workContinuation = null;
        return kVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void discardUnusedValues() {
        int i10;
        List list;
        synchronized (this.stateLock) {
            try {
                if (this.compositionValuesRemoved.isEmpty()) {
                    list = z.f19728i;
                } else {
                    ArrayList arrayListV = s.V(this.compositionValuesRemoved.values());
                    this.compositionValuesRemoved.clear();
                    ArrayList arrayList = new ArrayList(arrayListV.size());
                    int size = arrayListV.size();
                    for (int i11 = 0; i11 < size; i11++) {
                        MovableContentStateReference movableContentStateReference = (MovableContentStateReference) arrayListV.get(i11);
                        arrayList.add(new x6.x(movableContentStateReference, this.compositionValueStatesAvailable.get(movableContentStateReference)));
                    }
                    this.compositionValueStatesAvailable.clear();
                    list = arrayList;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        int size2 = list.size();
        for (i10 = 0; i10 < size2; i10++) {
            x6.x xVar = (x6.x) list.get(i10);
            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) xVar.f22608i;
            MovableContentState movableContentState = (MovableContentState) xVar.f22609l;
            if (movableContentState != null) {
                movableContentStateReference2.getComposition().disposeUnusedMovableContent(movableContentState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasBroadcastFrameClockAwaiters() {
        boolean hasBroadcastFrameClockAwaitersLocked;
        synchronized (this.stateLock) {
            hasBroadcastFrameClockAwaitersLocked = getHasBroadcastFrameClockAwaitersLocked();
        }
        return hasBroadcastFrameClockAwaitersLocked;
    }

    private final boolean getHasBroadcastFrameClockAwaitersLocked() {
        return !this.frameClockPaused && this.broadcastFrameClock.getHasAwaiters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasConcurrentFrameWorkLocked() {
        return !this.compositionsAwaitingApply.isEmpty() || getHasBroadcastFrameClockAwaitersLocked();
    }

    private final boolean getHasFrameWorkLocked() {
        return this.compositionInvalidations.isNotEmpty() || getHasBroadcastFrameClockAwaitersLocked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean getHasSchedulingWork() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.stateLock
            monitor-enter(r0)
            t.d0 r1 = r2.snapshotInvalidations     // Catch: java.lang.Throwable -> L1c
            boolean r1 = r1.c()     // Catch: java.lang.Throwable -> L1c
            if (r1 != 0) goto L1e
            androidx.compose.runtime.collection.MutableVector<androidx.compose.runtime.ControlledComposition> r1 = r2.compositionInvalidations     // Catch: java.lang.Throwable -> L1c
            boolean r1 = r1.isNotEmpty()     // Catch: java.lang.Throwable -> L1c
            if (r1 != 0) goto L1e
            boolean r1 = r2.getHasBroadcastFrameClockAwaitersLocked()     // Catch: java.lang.Throwable -> L1c
            if (r1 == 0) goto L1a
            goto L1e
        L1a:
            r1 = 0
            goto L1f
        L1c:
            r1 = move-exception
            goto L21
        L1e:
            r1 = 1
        L1f:
            monitor-exit(r0)
            return r1
        L21:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.getHasSchedulingWork():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> getKnownCompositions() {
        List arrayList = this._knownCompositionsCache;
        if (arrayList == null) {
            List<ControlledComposition> list = this._knownCompositions;
            arrayList = list.isEmpty() ? z.f19728i : new ArrayList(list);
            this._knownCompositionsCache = arrayList;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getShouldKeepRecomposing() {
        boolean z;
        synchronized (this.stateLock) {
            z = this.isClosed;
        }
        if (!z) {
            return true;
        }
        Iterator it = this.effectJob.getChildren().iterator();
        while (it.hasNext()) {
            if (((v1) it.next()).isActive()) {
                return true;
            }
        }
        return false;
    }

    @x6.e
    public static /* synthetic */ void getState$annotations() {
    }

    private final void performInitialMovableContentInserts(ControlledComposition composition) {
        synchronized (this.stateLock) {
            List<MovableContentStateReference> list = this.compositionValuesAwaitingInsert;
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (kotlin.jvm.internal.p.a(list.get(i10).getComposition(), composition)) {
                    ArrayList arrayList = new ArrayList();
                    performInitialMovableContentInserts$fillToInsert(arrayList, this, composition);
                    while (!arrayList.isEmpty()) {
                        performInsertValues(arrayList, null);
                        performInitialMovableContentInserts$fillToInsert(arrayList, this, composition);
                    }
                    return;
                }
            }
        }
    }

    private static final void performInitialMovableContentInserts$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer, ControlledComposition controlledComposition) {
        list.clear();
        synchronized (recomposer.stateLock) {
            Iterator<MovableContentStateReference> it = recomposer.compositionValuesAwaitingInsert.iterator();
            while (it.hasNext()) {
                MovableContentStateReference next = it.next();
                if (kotlin.jvm.internal.p.a(next.getComposition(), controlledComposition)) {
                    list.add(next);
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b3, code lost:
    
        r1 = r7.size();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b8, code lost:
    
        if (r6 >= r1) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c2, code lost:
    
        if (((x6.x) r7.get(r6)).f22609l == null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c4, code lost:
    
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c7, code lost:
    
        r1 = new java.util.ArrayList(r7.size());
        r6 = r7.size();
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d5, code lost:
    
        if (r8 >= r6) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d7, code lost:
    
        r9 = (x6.x) r7.get(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00df, code lost:
    
        if (r9.f22609l != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e1, code lost:
    
        r9 = (androidx.compose.runtime.MovableContentStateReference) r9.f22608i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e8, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e9, code lost:
    
        if (r9 == null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00eb, code lost:
    
        r1.add(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ee, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f1, code lost:
    
        r6 = r13.stateLock;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f3, code lost:
    
        monitor-enter(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00f4, code lost:
    
        kotlin.collections.x.b0(r13.compositionValuesAwaitingInsert, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00f9, code lost:
    
        monitor-exit(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00fa, code lost:
    
        r1 = new java.util.ArrayList(r7.size());
        r6 = r7.size();
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0108, code lost:
    
        if (r8 >= r6) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x010a, code lost:
    
        r9 = r7.get(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0113, code lost:
    
        if (((x6.x) r9).f22609l == null) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0115, code lost:
    
        r1.add(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0118, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x011b, code lost:
    
        r7 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<androidx.compose.runtime.ControlledComposition> performInsertValues(java.util.List<androidx.compose.runtime.MovableContentStateReference> r14, t.d0 r15) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.performInsertValues(java.util.List, t.d0):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlledComposition performRecompose(ControlledComposition composition, d0 modifiedValues) {
        Set<ControlledComposition> set;
        if (composition.isComposing() || composition.getDisposed() || ((set = this.compositionsRemoved) != null && set.contains(composition))) {
            return null;
        }
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
            if (modifiedValues != null) {
                try {
                    if (modifiedValues.c()) {
                        composition.prepareCompose(new Recomposer$performRecompose$1$1(modifiedValues, composition));
                    }
                } catch (Throwable th) {
                    mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            }
            boolean zRecompose = composition.recompose();
            mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
            if (zRecompose) {
                return composition;
            }
            return null;
        } finally {
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
        }
    }

    private final void processCompositionError(Exception e5, ControlledComposition failedInitialComposition, boolean recoverable) throws Exception {
        if (!_hotReloadEnabled.get().booleanValue() || (e5 instanceof ComposeRuntimeError)) {
            synchronized (this.stateLock) {
                RecomposerErrorState recomposerErrorState = this.errorState;
                if (recomposerErrorState != null) {
                    throw recomposerErrorState.getCause();
                }
                this.errorState = new RecomposerErrorState(false, e5);
            }
            throw e5;
        }
        synchronized (this.stateLock) {
            try {
                ActualAndroid_androidKt.logError("Error was captured in composition while live edit was enabled.", e5);
                this.compositionsAwaitingApply.clear();
                this.compositionInvalidations.clear();
                this.snapshotInvalidations = new d0();
                this.compositionValuesAwaitingInsert.clear();
                this.compositionValuesRemoved.clear();
                this.compositionValueStatesAvailable.clear();
                this.errorState = new RecomposerErrorState(recoverable, e5);
                if (failedInitialComposition != null) {
                    recordFailedCompositionLocked(failedInitialComposition);
                }
                deriveStateLocked();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ void processCompositionError$default(Recomposer recomposer, Exception exc, ControlledComposition controlledComposition, boolean z, int i10, Object obj) throws Exception {
        if ((i10 & 2) != 0) {
            controlledComposition = null;
        }
        if ((i10 & 4) != 0) {
            z = false;
        }
        recomposer.processCompositionError(exc, controlledComposition, z);
    }

    private final r7.l<Object, t0> readObserverOf(ControlledComposition composition) {
        return new AnonymousClass1(composition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object recompositionRunner(q<? super k0, ? super MonotonicFrameClock, ? super d7.d<? super t0>, ? extends Object> qVar, d7.d<? super t0> dVar) throws Throwable {
        Object objY = m0.y(this.broadcastFrameClock, new C05952(qVar, MonotonicFrameClockKt.getMonotonicFrameClock(dVar.getContext()), null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean recordComposerModifications() {
        List<ControlledComposition> knownCompositions;
        boolean hasFrameWorkLocked;
        synchronized (this.stateLock) {
            if (this.snapshotInvalidations.b()) {
                return getHasFrameWorkLocked();
            }
            Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(this.snapshotInvalidations);
            this.snapshotInvalidations = new d0();
            synchronized (this.stateLock) {
                knownCompositions = getKnownCompositions();
            }
            try {
                int size = knownCompositions.size();
                for (int i10 = 0; i10 < size; i10++) {
                    knownCompositions.get(i10).recordModificationsOf(setWrapIntoSet);
                    if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                        break;
                    }
                }
                synchronized (this.stateLock) {
                    this.snapshotInvalidations = new d0();
                }
                synchronized (this.stateLock) {
                    if (deriveStateLocked() != null) {
                        throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
                    }
                    hasFrameWorkLocked = getHasFrameWorkLocked();
                }
                return hasFrameWorkLocked;
            } catch (Throwable th) {
                synchronized (this.stateLock) {
                    d0 d0Var = this.snapshotInvalidations;
                    int i11 = d0Var.f21838d;
                    for (Object obj : setWrapIntoSet) {
                        d0Var.f21836b[d0Var.f(obj)] = obj;
                    }
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordFailedCompositionLocked(ControlledComposition composition) {
        List arrayList = this.failedCompositions;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.failedCompositions = arrayList;
        }
        if (!arrayList.contains(composition)) {
            arrayList.add(composition);
        }
        removeKnownCompositionLocked(composition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerRunnerJob(v1 callingJob) {
        synchronized (this.stateLock) {
            Throwable th = this.closeCause;
            if (th != null) {
                throw th;
            }
            if (((State) this._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                throw new IllegalStateException("Recomposer shut down");
            }
            if (this.runnerJob != null) {
                throw new IllegalStateException("Recomposer already running");
            }
            this.runnerJob = callingJob;
            deriveStateLocked();
        }
    }

    private final void removeKnownCompositionLocked(ControlledComposition composition) {
        this._knownCompositions.remove(composition);
        this._knownCompositionsCache = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecomposerErrorState resetErrorState() {
        RecomposerErrorState recomposerErrorState;
        synchronized (this.stateLock) {
            recomposerErrorState = this.errorState;
            if (recomposerErrorState != null) {
                this.errorState = null;
                deriveStateLocked();
            }
        }
        return recomposerErrorState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retryFailedCompositions() {
        List<ControlledComposition> list;
        int i10;
        synchronized (this.stateLock) {
            list = this.failedCompositions;
            this.failedCompositions = null;
        }
        if (list == null) {
            return;
        }
        while (true) {
            i10 = 0;
            try {
                if (list.isEmpty()) {
                    break;
                }
                ControlledComposition controlledComposition = (ControlledComposition) x.O0(list);
                if (controlledComposition instanceof CompositionImpl) {
                    controlledComposition.invalidateAll();
                    controlledComposition.setContent(((CompositionImpl) controlledComposition).getComposable());
                    if (this.errorState != null) {
                        break;
                    }
                }
            } catch (Throwable th) {
                if (!list.isEmpty()) {
                    synchronized (this.stateLock) {
                        int size = list.size();
                        while (i10 < size) {
                            recordFailedCompositionLocked(list.get(i10));
                            i10++;
                        }
                    }
                }
                throw th;
            }
        }
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.stateLock) {
            int size2 = list.size();
            while (i10 < size2) {
                recordFailedCompositionLocked(list.get(i10));
                i10++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009c, code lost:
    
        if (r5.withFrameNanos(r10, r0) != r4) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x009c -> B:13:0x003d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runFrameLoop(androidx.compose.runtime.MonotonicFrameClock r8, androidx.compose.runtime.ProduceFrameSignal r9, d7.d<? super x6.t0> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof androidx.compose.runtime.Recomposer.C05961
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = (androidx.compose.runtime.Recomposer.C05961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = new androidx.compose.runtime.Recomposer$runFrameLoop$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L63
            if (r1 == r3) goto L4b
            if (r1 != r2) goto L43
            java.lang.Object r8 = r0.L$4
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r1 = r0.L$2
            androidx.compose.runtime.ProduceFrameSignal r1 = (androidx.compose.runtime.ProduceFrameSignal) r1
            java.lang.Object r5 = r0.L$1
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.runtime.Recomposer r6 = (androidx.compose.runtime.Recomposer) r6
            k2.c.G(r10)
        L3d:
            r10 = r9
            r9 = r1
            r1 = r8
            r8 = r5
            r5 = r6
            goto L6d
        L43:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4b:
            java.lang.Object r8 = r0.L$4
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r1 = r0.L$2
            androidx.compose.runtime.ProduceFrameSignal r1 = (androidx.compose.runtime.ProduceFrameSignal) r1
            java.lang.Object r5 = r0.L$1
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.runtime.Recomposer r6 = (androidx.compose.runtime.Recomposer) r6
            k2.c.G(r10)
            goto L87
        L63:
            java.util.ArrayList r10 = androidx.compose.foundation.c.w(r10)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5 = r7
        L6d:
            java.lang.Object r6 = r5.stateLock
            r0.L$0 = r5
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.L$4 = r1
            r0.label = r3
            java.lang.Object r6 = r9.awaitFrameRequest(r6, r0)
            if (r6 != r4) goto L82
            goto L9e
        L82:
            r6 = r5
            r5 = r8
            r8 = r1
            r1 = r9
            r9 = r10
        L87:
            androidx.compose.runtime.Recomposer$runFrameLoop$2 r10 = new androidx.compose.runtime.Recomposer$runFrameLoop$2
            r10.<init>(r9, r8, r1)
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r1
            r0.L$3 = r9
            r0.L$4 = r8
            r0.label = r2
            java.lang.Object r10 = r5.withFrameNanos(r10, r0)
            if (r10 != r4) goto L3d
        L9e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.runFrameLoop(androidx.compose.runtime.MonotonicFrameClock, androidx.compose.runtime.ProduceFrameSignal, d7.d):java.lang.Object");
    }

    private final r7.l<Object, t0> writeObserverOf(ControlledComposition composition, d0 modifiedValues) {
        return new C06001(composition, modifiedValues);
    }

    public final RecomposerInfo asRecomposerInfo() {
        return this.recomposerInfo;
    }

    public final Object awaitIdle(d7.d<? super t0> dVar) {
        Object objCollect = new na.s(getCurrentState(), new AnonymousClass2(null), 2).collect(oa.r.f20852i, dVar);
        t0 t0Var = t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        if (objCollect != aVar) {
            objCollect = t0Var;
        }
        return objCollect == aVar ? objCollect : t0Var;
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            if (((State) this._state.getValue()).compareTo(State.Idle) >= 0) {
                this._state.setValue(State.ShuttingDown);
            }
        }
        this.effectJob.cancel((CancellationException) null);
    }

    public final void close() {
        if (this.effectJob.complete()) {
            synchronized (this.stateLock) {
                this.isClosed = true;
            }
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void composeInitial$runtime_release(ControlledComposition composition, p<? super Composer, ? super Integer, t0> content) throws Exception {
        Throwable th;
        boolean zIsComposing = composition.isComposing();
        try {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            MutableSnapshot mutableSnapshotTakeMutableSnapshot = companion.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, null));
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
                try {
                    composition.composeContent(content);
                    mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                    if (!zIsComposing) {
                        companion.notifyObjectsInitialized();
                    }
                    synchronized (this.stateLock) {
                        try {
                            if (((State) this._state.getValue()).compareTo(State.ShuttingDown) > 0) {
                                try {
                                    if (!getKnownCompositions().contains(composition)) {
                                        addKnownCompositionLocked(composition);
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    throw th;
                                }
                            }
                            try {
                                performInitialMovableContentInserts(composition);
                                try {
                                    composition.applyChanges();
                                    composition.applyLateChanges();
                                    if (zIsComposing) {
                                        return;
                                    }
                                    companion.notifyObjectsInitialized();
                                } catch (Exception e5) {
                                    processCompositionError$default(this, e5, null, false, 6, null);
                                }
                            } catch (Exception e6) {
                                processCompositionError(e6, composition, true);
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                } catch (Throwable th4) {
                    try {
                        mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        throw th4;
                    } catch (Throwable th5) {
                        th = th5;
                        Throwable th6 = th;
                        try {
                            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
                            throw th6;
                        } catch (Exception e10) {
                            e = e10;
                            processCompositionError(e, composition, true);
                        }
                    }
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Exception e11) {
            e = e11;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void deletedMovableContent$runtime_release(MovableContentStateReference reference) {
        synchronized (this.stateLock) {
            RecomposerKt.addMultiValue(this.compositionValuesRemoved, reference.getContent$runtime_release(), reference);
        }
    }

    public final long getChangeCount() {
        return this.changeCount;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingCallByInformation$runtime_release() {
        return _hotReloadEnabled.get().booleanValue();
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingParameterInformation$runtime_release() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingSourceInformation$runtime_release() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public int getCompoundHashKey$runtime_release() {
        return 1000;
    }

    public final h1<State> getCurrentState() {
        return this._state;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public d7.j getEffectCoroutineContext() {
        return this.effectCoroutineContext;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean getHasPendingWork() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.stateLock
            monitor-enter(r0)
            t.d0 r1 = r2.snapshotInvalidations     // Catch: java.lang.Throwable -> L28
            boolean r1 = r1.c()     // Catch: java.lang.Throwable -> L28
            if (r1 != 0) goto L2a
            androidx.compose.runtime.collection.MutableVector<androidx.compose.runtime.ControlledComposition> r1 = r2.compositionInvalidations     // Catch: java.lang.Throwable -> L28
            boolean r1 = r1.isNotEmpty()     // Catch: java.lang.Throwable -> L28
            if (r1 != 0) goto L2a
            int r1 = r2.concurrentCompositionsOutstanding     // Catch: java.lang.Throwable -> L28
            if (r1 > 0) goto L2a
            java.util.List<androidx.compose.runtime.ControlledComposition> r1 = r2.compositionsAwaitingApply     // Catch: java.lang.Throwable -> L28
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L2a
            boolean r1 = r2.getHasBroadcastFrameClockAwaitersLocked()     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L26
            goto L2a
        L26:
            r1 = 0
            goto L2b
        L28:
            r1 = move-exception
            goto L2d
        L2a:
            r1 = 1
        L2b:
            monitor-exit(r0)
            return r1
        L2d:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.getHasPendingWork():boolean");
    }

    @Override // androidx.compose.runtime.CompositionContext
    public d7.j getRecomposeCoroutineContext$runtime_release() {
        return d7.k.f14688i;
    }

    public final na.j<State> getState() {
        return getCurrentState();
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void insertMovableContent$runtime_release(MovableContentStateReference reference) {
        ka.k<t0> kVarDeriveStateLocked;
        synchronized (this.stateLock) {
            this.compositionValuesAwaitingInsert.add(reference);
            kVarDeriveStateLocked = deriveStateLocked();
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidate$runtime_release(ControlledComposition composition) {
        ka.k<t0> kVarDeriveStateLocked;
        synchronized (this.stateLock) {
            if (this.compositionInvalidations.contains(composition)) {
                kVarDeriveStateLocked = null;
            } else {
                this.compositionInvalidations.add(composition);
                kVarDeriveStateLocked = deriveStateLocked();
            }
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidateScope$runtime_release(RecomposeScopeImpl scope) {
        ka.k<t0> kVarDeriveStateLocked;
        synchronized (this.stateLock) {
            this.snapshotInvalidations.d(scope);
            kVarDeriveStateLocked = deriveStateLocked();
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
    }

    public final Object join(d7.d<? super t0> dVar) {
        Object objM = y0.m(getCurrentState(), new C05942(null), dVar);
        return objM == e7.a.f15033i ? objM : t0.f22605a;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void movableContentStateReleased$runtime_release(MovableContentStateReference reference, MovableContentState data) {
        synchronized (this.stateLock) {
            this.compositionValueStatesAvailable.put(reference, data);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference reference) {
        MovableContentState movableContentStateRemove;
        synchronized (this.stateLock) {
            movableContentStateRemove = this.compositionValueStatesAvailable.remove(reference);
        }
        return movableContentStateRemove;
    }

    public final void pauseCompositionFrameClock() {
        synchronized (this.stateLock) {
            this.frameClockPaused = true;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void recordInspectionTable$runtime_release(Set<CompositionData> table) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void registerComposition$runtime_release(ControlledComposition composition) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportRemovedComposition$runtime_release(ControlledComposition composition) {
        synchronized (this.stateLock) {
            try {
                Set linkedHashSet = this.compositionsRemoved;
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                    this.compositionsRemoved = linkedHashSet;
                }
                linkedHashSet.add(composition);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void resumeCompositionFrameClock() {
        ka.k<t0> kVarDeriveStateLocked;
        synchronized (this.stateLock) {
            if (this.frameClockPaused) {
                this.frameClockPaused = false;
                kVarDeriveStateLocked = deriveStateLocked();
            } else {
                kVarDeriveStateLocked = null;
            }
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
    }

    public final Object runRecomposeAndApplyChanges(d7.d<? super t0> dVar) throws Throwable {
        Object objRecompositionRunner = recompositionRunner(new C05982(null), dVar);
        return objRecompositionRunner == e7.a.f15033i ? objRecompositionRunner : t0.f22605a;
    }

    public final Object runRecomposeConcurrentlyAndApplyChanges(d7.j jVar, d7.d<? super t0> dVar) throws Throwable {
        Object objRecompositionRunner = recompositionRunner(new C05992(jVar, this, null), dVar);
        return objRecompositionRunner == e7.a.f15033i ? objRecompositionRunner : t0.f22605a;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void unregisterComposition$runtime_release(ControlledComposition composition) {
        synchronized (this.stateLock) {
            removeKnownCompositionLocked(composition);
            this.compositionInvalidations.remove(composition);
            this.compositionsAwaitingApply.remove(composition);
        }
    }

    private final void recordComposerModifications(r7.l<? super ControlledComposition, t0> onEachInvalidComposition) {
        d0 d0Var;
        synchronized (this.stateLock) {
            d0Var = this.snapshotInvalidations;
            if (d0Var.c()) {
                this.snapshotInvalidations = new d0();
            }
        }
        Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(d0Var);
        int i10 = 0;
        if (!setWrapIntoSet.isEmpty()) {
            List knownCompositions = getKnownCompositions();
            int size = knownCompositions.size();
            for (int i11 = 0; i11 < size; i11++) {
                ((ControlledComposition) knownCompositions.get(i11)).recordModificationsOf(setWrapIntoSet);
            }
        }
        MutableVector mutableVector = this.compositionInvalidations;
        int size2 = mutableVector.getSize();
        if (size2 > 0) {
            Object[] content = mutableVector.getContent();
            do {
                onEachInvalidComposition.invoke(content[i10]);
                i10++;
            } while (i10 < size2);
        }
        this.compositionInvalidations.clear();
        synchronized (this.stateLock) {
            if (deriveStateLocked() != null) {
                throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
            }
        }
    }
}
