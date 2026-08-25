package androidx.compose.runtime;

import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.changelist.FixupList;
import androidx.compose.runtime.collection.IntMap;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import androidx.compose.runtime.snapshots.ListUtilsKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.q0;
import kotlin.jvm.internal.r;
import r7.p;
import t.t;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015*\u0002È\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0004\u0099\u0003\u009a\u0003BI\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0017¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0017¢\u0006\u0004\b\u0019\u0010\u0016J\u000f\u0010\u001a\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u001a\u0010\u0018J\u000f\u0010\u001b\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u001b\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u001c\u0010\u0018J!\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0014H\u0017¢\u0006\u0004\b!\u0010\u0018J\u000f\u0010#\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\"\u0010\u0018J\u000f\u0010$\u001a\u00020\u0014H\u0016¢\u0006\u0004\b$\u0010\u0018J\u000f\u0010&\u001a\u00020\u0014H\u0000¢\u0006\u0004\b%\u0010\u0018J\u000f\u0010(\u001a\u00020\u0014H\u0000¢\u0006\u0004\b'\u0010\u0018J\u000f\u0010,\u001a\u00020)H\u0000¢\u0006\u0004\b*\u0010+J\u000f\u0010-\u001a\u00020\u0014H\u0016¢\u0006\u0004\b-\u0010\u0018J\u000f\u0010.\u001a\u00020\u0014H\u0016¢\u0006\u0004\b.\u0010\u0018J#\u00102\u001a\u00020\u0014\"\u0004\b\u0000\u0010/2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0016¢\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\u0014H\u0016¢\u0006\u0004\b4\u0010\u0018J\u000f\u00105\u001a\u00020\u0014H\u0016¢\u0006\u0004\b5\u0010\u0018J!\u00106\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0004\b6\u0010 J\u000f\u00107\u001a\u00020\u0014H\u0016¢\u0006\u0004\b7\u0010\u0018J\u000f\u00108\u001a\u00020\u0014H\u0016¢\u0006\u0004\b8\u0010\u0018J\u000f\u00109\u001a\u00020\u0014H\u0016¢\u0006\u0004\b9\u0010\u0018J\r\u0010:\u001a\u00020\u0014¢\u0006\u0004\b:\u0010\u0018J\r\u0010;\u001a\u00020\u0014¢\u0006\u0004\b;\u0010\u0018J\u0017\u0010=\u001a\u00020\u00142\u0006\u0010<\u001a\u00020\u0012H\u0016¢\u0006\u0004\b=\u0010\u0016JB\u0010C\u001a\u00020\u0014\"\u0004\b\u0000\u0010>\"\u0004\b\u0001\u0010/2\u0006\u0010?\u001a\u00028\u00002\u001d\u0010B\u001a\u0019\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140@¢\u0006\u0002\bAH\u0016¢\u0006\u0004\bC\u0010DJ#\u0010G\u001a\u00020\u001d2\b\u0010E\u001a\u0004\u0018\u00010\u001d2\b\u0010F\u001a\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0004\bG\u0010HJ\u0011\u0010I\u001a\u0004\u0018\u00010\u001dH\u0001¢\u0006\u0004\bI\u0010JJ\u0011\u0010K\u001a\u0004\u0018\u00010\u001dH\u0001¢\u0006\u0004\bK\u0010JJ\u0019\u0010L\u001a\u00020)2\b\u0010?\u001a\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0004\bL\u0010MJ\u0019\u0010N\u001a\u00020)2\b\u0010?\u001a\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0004\bN\u0010MJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020OH\u0017¢\u0006\u0004\bL\u0010PJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020QH\u0017¢\u0006\u0004\bL\u0010RJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020SH\u0017¢\u0006\u0004\bL\u0010TJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020)H\u0017¢\u0006\u0004\bL\u0010UJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020VH\u0017¢\u0006\u0004\bL\u0010WJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020XH\u0017¢\u0006\u0004\bL\u0010YJ\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020ZH\u0017¢\u0006\u0004\bL\u0010[J\u0017\u0010L\u001a\u00020)2\u0006\u0010?\u001a\u00020\u0012H\u0017¢\u0006\u0004\bL\u0010\\J,\u0010^\u001a\u00028\u0000\"\u0004\b\u0000\u0010/2\u0006\u0010]\u001a\u00020)2\f\u0010B\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0087\b¢\u0006\u0004\b^\u0010_J\u0019\u0010`\u001a\u00020\u00142\b\u0010?\u001a\u0004\u0018\u00010\u001dH\u0001¢\u0006\u0004\b`\u0010aJ\u0019\u0010b\u001a\u00020\u00142\b\u0010?\u001a\u0004\u0018\u00010\u001dH\u0001¢\u0006\u0004\bb\u0010aJ\u001d\u0010d\u001a\u00020\u00142\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u001400H\u0016¢\u0006\u0004\bd\u00103J\u001b\u0010f\u001a\u00020\u00142\n\u0010?\u001a\u0006\u0012\u0002\b\u00030eH\u0017¢\u0006\u0004\bf\u0010gJ\u000f\u0010h\u001a\u00020\u0014H\u0017¢\u0006\u0004\bh\u0010\u0018J#\u0010k\u001a\u00020\u00142\u0012\u0010j\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030e0iH\u0017¢\u0006\u0004\bk\u0010lJ\u000f\u0010m\u001a\u00020\u0014H\u0017¢\u0006\u0004\bm\u0010\u0018J#\u0010o\u001a\u00028\u0000\"\u0004\b\u0000\u0010/2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000nH\u0017¢\u0006\u0004\bo\u0010pJ\u000f\u0010q\u001a\u00020\u0004H\u0016¢\u0006\u0004\bq\u0010rJ!\u0010x\u001a\u00020)2\u0006\u0010t\u001a\u00020s2\b\u0010u\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0004\bv\u0010wJ\u000f\u0010{\u001a\u00020\u0012H\u0001¢\u0006\u0004\by\u0010zJ\u000f\u0010|\u001a\u00020\u0014H\u0017¢\u0006\u0004\b|\u0010\u0018J\u000f\u0010}\u001a\u00020\u0014H\u0017¢\u0006\u0004\b}\u0010\u0018J\u0017\u0010~\u001a\u00020\u00142\u0006\u0010L\u001a\u00020)H\u0017¢\u0006\u0004\b~\u0010\u007fJ\u001a\u0010\u0080\u0001\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0017¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u0015\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0017¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J*\u0010\u0087\u0001\u001a\u00020\u00142\u000b\u0010?\u001a\u0007\u0012\u0002\b\u00030\u0085\u00012\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J3\u0010\u008d\u0001\u001a\u00020\u00142\u001f\u0010\u008c\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u008b\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u008b\u00010\u008a\u00010\u0089\u0001H\u0017¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u001c\u0010\u0090\u0001\u001a\u00020\u00142\b\u0010\u0090\u0001\u001a\u00030\u008f\u0001H\u0017¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J$\u0010\u0092\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0090\u0001\u001a\u00030\u008f\u0001H\u0017¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u0011\u0010\u0094\u0001\u001a\u00020\u0014H\u0017¢\u0006\u0005\b\u0094\u0001\u0010\u0018J\u0011\u0010\u0095\u0001\u001a\u00020\u0014H\u0016¢\u0006\u0005\b\u0095\u0001\u0010\u0018J=\u0010\u009c\u0001\u001a\u00020\u00142\u0014\u0010\u0097\u0001\u001a\u000f\u0012\u0004\u0012\u00020s\u0012\u0004\u0012\u00020\u001d0\u0096\u00012\u0013\u0010\u0099\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001400¢\u0006\u0003\b\u0098\u0001H\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u001f\u0010\u009e\u0001\u001a\u00020\u00142\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u001400H\u0000¢\u0006\u0005\b\u009d\u0001\u00103J(\u0010¡\u0001\u001a\u00020)2\u0014\u0010\u0097\u0001\u001a\u000f\u0012\u0004\u0012\u00020s\u0012\u0004\u0012\u00020\u001d0\u0096\u0001H\u0000¢\u0006\u0006\b\u009f\u0001\u0010 \u0001J\u0011\u0010£\u0001\u001a\u00020\u0014H\u0000¢\u0006\u0005\b¢\u0001\u0010\u0018J\u0011\u0010¥\u0001\u001a\u00020\u0012H\u0000¢\u0006\u0005\b¤\u0001\u0010zJ\u0013\u0010¦\u0001\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0005\b¦\u0001\u0010JJ\u001b\u0010§\u0001\u001a\u00020\u00142\b\u0010?\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0005\b§\u0001\u0010aJ\u001b\u0010©\u0001\u001a\u00020\u00142\u0007\u0010t\u001a\u00030¨\u0001H\u0016¢\u0006\u0006\b©\u0001\u0010ª\u0001J\u0011\u0010«\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\b«\u0001\u0010\u0018J\u0011\u0010¬\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\b¬\u0001\u0010\u0018J\u0011\u0010\u00ad\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u00ad\u0001\u0010\u0018J\u0019\u0010®\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0005\b®\u0001\u0010\u0016J#\u0010®\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0005\b®\u0001\u0010 J\u0011\u0010¯\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\b¯\u0001\u0010\u0018J\u0011\u0010°\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\b°\u0001\u0010\u0018J\u001b\u0010±\u0001\u001a\u00020\u00142\b\u0010?\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0005\b±\u0001\u0010aJ\u0015\u0010³\u0001\u001a\u0005\u0018\u00010²\u0001H\u0002¢\u0006\u0006\b³\u0001\u0010´\u0001J\u0013\u0010¶\u0001\u001a\u00030µ\u0001H\u0002¢\u0006\u0006\b¶\u0001\u0010·\u0001J\u001c\u0010¶\u0001\u001a\u00030µ\u00012\u0007\u0010¸\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\b¶\u0001\u0010¹\u0001J'\u0010¼\u0001\u001a\u00030µ\u00012\b\u0010º\u0001\u001a\u00030µ\u00012\b\u0010»\u0001\u001a\u00030µ\u0001H\u0002¢\u0006\u0006\b¼\u0001\u0010½\u0001J\u001c\u0010¿\u0001\u001a\u00020\u00142\b\u0010¾\u0001\u001a\u00030µ\u0001H\u0002¢\u0006\u0006\b¿\u0001\u0010À\u0001J\u0011\u0010Á\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÁ\u0001\u0010\u0018J\u0011\u0010Â\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÂ\u0001\u0010\u0018J\u0011\u0010Ã\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÃ\u0001\u0010\u0018J&\u0010Æ\u0001\u001a\u00020\u00142\u0007\u0010Ä\u0001\u001a\u00020)2\t\u0010Å\u0001\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001J=\u0010Í\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\t\u0010È\u0001\u001a\u0004\u0018\u00010\u001d2\b\u0010Ê\u0001\u001a\u00030É\u00012\t\u0010Å\u0001\u001a\u0004\u0018\u00010\u001dH\u0002ø\u0001\u0000¢\u0006\u0006\bË\u0001\u0010Ì\u0001J'\u0010Ð\u0001\u001a\u00020\u00142\u0007\u0010Ä\u0001\u001a\u00020)2\n\u0010Ï\u0001\u001a\u0005\u0018\u00010Î\u0001H\u0002¢\u0006\u0006\bÐ\u0001\u0010Ñ\u0001J$\u0010Ô\u0001\u001a\u00020\u00142\u0007\u0010Ò\u0001\u001a\u00020\u00122\u0007\u0010Ó\u0001\u001a\u00020)H\u0002¢\u0006\u0006\bÔ\u0001\u0010Õ\u0001J\u001a\u0010Ö\u0001\u001a\u00020\u00142\u0007\u0010Ä\u0001\u001a\u00020)H\u0002¢\u0006\u0005\bÖ\u0001\u0010\u007fJ\u0011\u0010×\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\b×\u0001\u0010\u0018J\u001b\u0010Ù\u0001\u001a\u00020\u00122\u0007\u0010Ø\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bÙ\u0001\u0010Ú\u0001J$\u0010Ü\u0001\u001a\u00020\u00142\u0007\u0010¸\u0001\u001a\u00020\u00122\u0007\u0010Û\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bÜ\u0001\u0010Ý\u0001J6\u0010á\u0001\u001a\u00020\u00122\u0007\u0010Þ\u0001\u001a\u00020\u00122\u0007\u0010¸\u0001\u001a\u00020\u00122\u0007\u0010ß\u0001\u001a\u00020\u00122\u0007\u0010à\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001J\u001b\u0010ã\u0001\u001a\u00020\u00122\u0007\u0010¸\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bã\u0001\u0010Ú\u0001J\u001b\u0010ä\u0001\u001a\u00020\u00122\u0007\u0010¸\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bä\u0001\u0010Ú\u0001J$\u0010æ\u0001\u001a\u00020\u00142\u0007\u0010¸\u0001\u001a\u00020\u00122\u0007\u0010å\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bæ\u0001\u0010Ý\u0001J\u0011\u0010ç\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bç\u0001\u0010\u0018J-\u0010ë\u0001\u001a\u00020\u00142\u0007\u0010è\u0001\u001a\u00020\u00122\u0007\u0010é\u0001\u001a\u00020\u00122\u0007\u0010ê\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bë\u0001\u0010ì\u0001J$\u0010î\u0001\u001a\u00020\u00142\u0007\u0010¸\u0001\u001a\u00020\u00122\u0007\u0010í\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bî\u0001\u0010Ý\u0001J6\u0010ñ\u0001\u001a\u00020\u00122\u0007\u0010¸\u0001\u001a\u00020\u00122\u0007\u0010ï\u0001\u001a\u00020\u00122\u0007\u0010ß\u0001\u001a\u00020\u00122\u0007\u0010ð\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bñ\u0001\u0010â\u0001J \u0010ó\u0001\u001a\u00020\u0012*\u00030ò\u00012\u0007\u0010¸\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\bó\u0001\u0010ô\u0001J\u0011\u0010õ\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bõ\u0001\u0010\u0018J\u0011\u0010ö\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bö\u0001\u0010\u0018JB\u0010ù\u0001\u001a\u00020\u00142\u0010\u0010\u0099\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u0085\u00012\b\u0010÷\u0001\u001a\u00030µ\u00012\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u001d2\u0007\u0010ø\u0001\u001a\u00020)H\u0002¢\u0006\u0006\bù\u0001\u0010ú\u0001J3\u0010û\u0001\u001a\u00020\u00142\u001f\u0010\u008c\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u008b\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u008b\u00010\u008a\u00010\u0089\u0001H\u0002¢\u0006\u0006\bû\u0001\u0010\u008e\u0001J2\u0010þ\u0001\u001a\u00028\u0000\"\u0005\b\u0000\u0010ü\u00012\b\u0010ý\u0001\u001a\u00030ò\u00012\f\u0010B\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0082\b¢\u0006\u0006\bþ\u0001\u0010ÿ\u0001Jo\u0010\u0083\u0002\u001a\u00028\u0000\"\u0005\b\u0000\u0010ü\u00012\u000b\b\u0002\u0010\u0080\u0002\u001a\u0004\u0018\u00010\u000e2\u000b\b\u0002\u0010\u0081\u0002\u001a\u0004\u0018\u00010\u000e2\u000b\b\u0002\u0010Ø\u0001\u001a\u0004\u0018\u00010\u00122\u001f\b\u0002\u0010\u0082\u0002\u001a\u0018\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020s\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u008a\u00010\u0089\u00012\f\u0010B\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0002¢\u0006\u0006\b\u0083\u0002\u0010\u0084\u0002J?\u0010\u0085\u0002\u001a\u00020\u00142\u0014\u0010\u0097\u0001\u001a\u000f\u0012\u0004\u0012\u00020s\u0012\u0004\u0012\u00020\u001d0\u0096\u00012\u0015\u0010\u0099\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0018\u000100¢\u0006\u0003\b\u0098\u0001H\u0002¢\u0006\u0006\b\u0085\u0002\u0010\u009b\u0001J\"\u0010\u0086\u0002\u001a\u0004\u0018\u00010\u001d*\u00030ò\u00012\u0007\u0010Ø\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0006\b\u0086\u0002\u0010\u0087\u0002J\u0011\u0010\u0088\u0002\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u0088\u0002\u0010\u0018J\u0011\u0010\u0089\u0002\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u0089\u0002\u0010\u0018J\u001c\u0010\u008b\u0002\u001a\u00020\u00142\b\u0010\u008a\u0002\u001a\u00030²\u0001H\u0002¢\u0006\u0006\b\u008b\u0002\u0010\u008c\u0002J\u0011\u0010\u008d\u0002\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u008d\u0002\u0010\u0018J\u001a\u0010\u008f\u0002\u001a\u00020\u00142\u0007\u0010\u008e\u0002\u001a\u00020\u0012H\u0002¢\u0006\u0005\b\u008f\u0002\u0010\u0016J\u0011\u0010\u0090\u0002\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u0090\u0002\u0010\u0018J\u0011\u0010\u0091\u0002\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u0091\u0002\u0010\u0018J\u0011\u0010\u0092\u0002\u001a\u00020\u0014H\u0002¢\u0006\u0005\b\u0092\u0002\u0010\u0018J:\u0010\u0094\u0002\u001a\u00020\u00142\u0007\u0010\u0093\u0002\u001a\u00020\u00122\u0007\u0010ï\u0001\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\t\u0010Å\u0001\u001a\u0004\u0018\u00010\u001dH\u0082\b¢\u0006\u0006\b\u0094\u0002\u0010\u0095\u0002J%\u0010\u0097\u0002\u001a\u00020\u00142\u0007\u0010\u0096\u0002\u001a\u00020\u00122\u0007\u0010ï\u0001\u001a\u00020\u0012H\u0082\b¢\u0006\u0006\b\u0097\u0002\u0010Ý\u0001J:\u0010\u0098\u0002\u001a\u00020\u00142\u0007\u0010\u0093\u0002\u001a\u00020\u00122\u0007\u0010ï\u0001\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\t\u0010Å\u0001\u001a\u0004\u0018\u00010\u001dH\u0082\b¢\u0006\u0006\b\u0098\u0002\u0010\u0095\u0002J%\u0010\u0099\u0002\u001a\u00020\u00142\u0007\u0010\u0093\u0002\u001a\u00020\u00122\u0007\u0010ï\u0001\u001a\u00020\u0012H\u0082\b¢\u0006\u0006\b\u0099\u0002\u0010Ý\u0001R!\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\u0003\u0010\u009a\u0002\u001a\u0006\b\u009b\u0002\u0010\u009c\u0002R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010\u009d\u0002R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010\u009e\u0002R\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\n\u0010\u009f\u0002R\u0017\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\f\u0010 \u0002R\u0017\u0010\r\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\r\u0010 \u0002R\u001d\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\u000f\u0010¡\u0002\u001a\u0006\b¢\u0002\u0010£\u0002R!\u0010¥\u0002\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010Î\u00010¤\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0002\u0010¦\u0002R\u001c\u0010§\u0002\u001a\u0005\u0018\u00010Î\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b§\u0002\u0010¨\u0002R\u0019\u0010©\u0002\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b©\u0002\u0010ª\u0002R\u0019\u0010«\u0002\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b«\u0002\u0010ª\u0002R\u0019\u0010ï\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bï\u0001\u0010ª\u0002R\u0018\u0010\u00ad\u0002\u001a\u00030¬\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0002\u0010®\u0002R\u001c\u0010°\u0002\u001a\u0005\u0018\u00010¯\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b°\u0002\u0010±\u0002R\u001c\u0010³\u0002\u001a\u0005\u0018\u00010²\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b³\u0002\u0010´\u0002R\u0017\u0010,\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b,\u0010µ\u0002R\u0019\u0010¶\u0002\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0002\u0010µ\u0002R\u0019\u0010·\u0002\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b·\u0002\u0010µ\u0002R\u001f\u0010\u0082\u0002\u001a\n\u0012\u0005\u0012\u00030¹\u00020¸\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0002\u0010º\u0002R\u0018\u0010»\u0002\u001a\u00030¬\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b»\u0002\u0010®\u0002R\u001a\u0010¼\u0002\u001a\u00030µ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0002\u0010½\u0002R#\u0010¿\u0002\u001a\f\u0012\u0005\u0012\u00030µ\u0001\u0018\u00010¾\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¿\u0002\u0010À\u0002R\u0019\u0010Á\u0002\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÁ\u0002\u0010µ\u0002R\u0018\u0010Â\u0002\u001a\u00030¬\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÂ\u0002\u0010®\u0002R\u0019\u0010Ã\u0002\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0002\u0010µ\u0002R\u0019\u0010Ä\u0002\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÄ\u0002\u0010ª\u0002R\u0019\u0010Å\u0002\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0002\u0010ª\u0002R\u0019\u0010Æ\u0002\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÆ\u0002\u0010ª\u0002R\u0019\u0010Ç\u0002\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÇ\u0002\u0010µ\u0002R\u0018\u0010É\u0002\u001a\u00030È\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÉ\u0002\u0010Ê\u0002R\u001e\u0010Ë\u0002\u001a\t\u0012\u0004\u0012\u00020s0¤\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bË\u0002\u0010¦\u0002R)\u0010Í\u0002\u001a\u00020)2\u0007\u0010Ì\u0002\u001a\u00020)8\u0000@BX\u0080\u000e¢\u0006\u000f\n\u0006\bÍ\u0002\u0010µ\u0002\u001a\u0005\bÎ\u0002\u0010+R)\u0010Ï\u0002\u001a\u00020)2\u0007\u0010Ì\u0002\u001a\u00020)8\u0000@BX\u0080\u000e¢\u0006\u000f\n\u0006\bÏ\u0002\u0010µ\u0002\u001a\u0005\bÐ\u0002\u0010+R*\u0010ý\u0001\u001a\u00030ò\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bý\u0001\u0010Ñ\u0002\u001a\u0006\bÒ\u0002\u0010Ó\u0002\"\u0006\bÔ\u0002\u0010Õ\u0002R)\u0010Ö\u0002\u001a\u00020\u00068\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bÖ\u0002\u0010\u009e\u0002\u001a\u0006\b×\u0002\u0010Ø\u0002\"\u0006\bÙ\u0002\u0010Ú\u0002R\u001a\u0010Ü\u0002\u001a\u00030Û\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÜ\u0002\u0010Ý\u0002R\u0019\u0010Þ\u0002\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÞ\u0002\u0010µ\u0002R\u001c\u0010ß\u0002\u001a\u0005\u0018\u00010µ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bß\u0002\u0010½\u0002R+\u0010à\u0002\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bà\u0002\u0010 \u0002\u001a\u0006\bá\u0002\u0010â\u0002\"\u0006\bã\u0002\u0010ä\u0002R\u0018\u0010æ\u0002\u001a\u00030å\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bæ\u0002\u0010ç\u0002R\u001a\u0010è\u0002\u001a\u00030²\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bè\u0002\u0010é\u0002R\u001a\u0010ë\u0002\u001a\u00030ê\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bë\u0002\u0010ì\u0002R0\u0010Ó\u0001\u001a\u00020)2\u0007\u0010Ì\u0002\u001a\u00020)8\u0016@RX\u0097\u000e¢\u0006\u0016\n\u0006\bÓ\u0001\u0010µ\u0002\u0012\u0005\bî\u0002\u0010\u0018\u001a\u0005\bí\u0002\u0010+R0\u0010ï\u0002\u001a\u00020\u00122\u0007\u0010Ì\u0002\u001a\u00020\u00128\u0016@RX\u0097\u000e¢\u0006\u0016\n\u0006\bï\u0002\u0010ª\u0002\u0012\u0005\bñ\u0002\u0010\u0018\u001a\u0005\bð\u0002\u0010zR\u0016\u0010ó\u0002\u001a\u00020)8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bò\u0002\u0010+R\u0016\u0010õ\u0002\u001a\u00020)8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bô\u0002\u0010+R\u0018\u0010ù\u0002\u001a\u00030ö\u00028WX\u0096\u0004¢\u0006\b\u001a\u0006\b÷\u0002\u0010ø\u0002R\u001d\u0010ü\u0002\u001a\u00020)8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bû\u0002\u0010\u0018\u001a\u0005\bú\u0002\u0010+R\u001d\u0010ÿ\u0002\u001a\u00020)8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bþ\u0002\u0010\u0018\u001a\u0005\bý\u0002\u0010+R\u0016\u0010\u0081\u0003\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0080\u0003\u0010zR\u0018\u0010\u0085\u0003\u001a\u00030\u0082\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0083\u0003\u0010\u0084\u0003R\u0018\u0010\u0089\u0003\u001a\u00030\u0086\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0087\u0003\u0010\u0088\u0003R\u0016\u0010\u008b\u0003\u001a\u00020\u00128@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u008a\u0003\u0010zR\u0019\u0010\u008e\u0003\u001a\u0004\u0018\u00010s8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u008c\u0003\u0010\u008d\u0003R\u0013\u0010\u0090\u0003\u001a\u00020)8F¢\u0006\u0007\u001a\u0005\b\u008f\u0003\u0010+R\u001a\u0010\u0093\u0003\u001a\u0005\u0018\u00010¨\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0091\u0003\u0010\u0092\u0003R\u0018\u0010\u0095\u0003\u001a\u0004\u0018\u00010\u001d8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0094\u0003\u0010JR\u001e\u0010\u0098\u0003\u001a\u0004\u0018\u00010\u001d*\u00030ò\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0096\u0003\u0010\u0097\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u009b\u0003"}, d2 = {"Landroidx/compose/runtime/ComposerImpl;", "Landroidx/compose/runtime/Composer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/CompositionContext;", "parentContext", "Landroidx/compose/runtime/SlotTable;", "slotTable", "", "Landroidx/compose/runtime/RememberObserver;", "abandonSet", "Landroidx/compose/runtime/changelist/ChangeList;", "changes", "lateChanges", "Landroidx/compose/runtime/ControlledComposition;", "composition", "<init>", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/SlotTable;Ljava/util/Set;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/ControlledComposition;)V", "", "key", "Lx6/t0;", "startReplaceableGroup", "(I)V", "endReplaceableGroup", "()V", "startReplaceGroup", "endReplaceGroup", "startDefaults", "endDefaults", "", "dataKey", "startMovableGroup", "(ILjava/lang/Object;)V", "endMovableGroup", "changesApplied$runtime_release", "changesApplied", "collectParameterInformation", "dispose$runtime_release", "dispose", "deactivate$runtime_release", "deactivate", "", "forceRecomposeScopes$runtime_release", "()Z", "forceRecomposeScopes", "startNode", "startReusableNode", "T", "Lkotlin/Function0;", "factory", "createNode", "(Lr7/a;)V", "useNode", "endNode", "startReusableGroup", "endReusableGroup", "disableReusing", "enableReusing", "startReuseFromRoot", "endReuseFromRoot", "marker", "endToMarker", "V", "value", "Lkotlin/Function2;", "Lx6/n;", "block", "apply", "(Ljava/lang/Object;Lr7/p;)V", TtmlNode.LEFT, TtmlNode.RIGHT, "joinKey", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "nextSlot", "()Ljava/lang/Object;", "nextSlotForCache", "changed", "(Ljava/lang/Object;)Z", "changedInstance", "", "(C)Z", "", "(B)Z", "", "(S)Z", "(Z)Z", "", "(F)Z", "", "(J)Z", "", "(D)Z", "(I)Z", "invalid", "cache", "(ZLr7/a;)Ljava/lang/Object;", "updateValue", "(Ljava/lang/Object;)V", "updateCachedValue", "effect", "recordSideEffect", "Landroidx/compose/runtime/ProvidedValue;", "startProvider", "(Landroidx/compose/runtime/ProvidedValue;)V", "endProvider", "", "values", "startProviders", "([Landroidx/compose/runtime/ProvidedValue;)V", "endProviders", "Landroidx/compose/runtime/CompositionLocal;", "consume", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "buildContext", "()Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "scope", "instance", "tryImminentInvalidation$runtime_release", "(Landroidx/compose/runtime/RecomposeScopeImpl;Ljava/lang/Object;)Z", "tryImminentInvalidation", "parentKey$runtime_release", "()I", "parentKey", "skipCurrentGroup", "skipToGroupEnd", "deactivateToEndGroup", "(Z)V", "startRestartGroup", "(I)Landroidx/compose/runtime/Composer;", "Landroidx/compose/runtime/ScopeUpdateScope;", "endRestartGroup", "()Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/MovableContent;", "parameter", "insertMovableContent", "(Landroidx/compose/runtime/MovableContent;Ljava/lang/Object;)V", "", "Lx6/x;", "Landroidx/compose/runtime/MovableContentStateReference;", "references", "insertMovableContentReferences", "(Ljava/util/List;)V", "", "sourceInformation", "(Ljava/lang/String;)V", "sourceInformationMarkerStart", "(ILjava/lang/String;)V", "sourceInformationMarkerEnd", "disableSourceInformation", "Landroidx/compose/runtime/collection/ScopeMap;", "invalidationsRequested", "Landroidx/compose/runtime/Composable;", "content", "composeContent$runtime_release", "(Landroidx/compose/runtime/collection/ScopeMap;Lr7/p;)V", "composeContent", "prepareCompose$runtime_release", "prepareCompose", "recompose$runtime_release", "(Landroidx/compose/runtime/collection/ScopeMap;)Z", "recompose", "verifyConsistent$runtime_release", "verifyConsistent", "stacksSize$runtime_release", "stacksSize", "rememberedValue", "updateRememberedValue", "Landroidx/compose/runtime/RecomposeScope;", "recordUsed", "(Landroidx/compose/runtime/RecomposeScope;)V", "startRoot", "endRoot", "abortRoot", "startGroup", "endGroup", "skipGroup", "updateSlot", "Landroidx/compose/runtime/Anchor;", "rememberObserverAnchor", "()Landroidx/compose/runtime/Anchor;", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "currentCompositionLocalScope", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "group", "(I)Landroidx/compose/runtime/PersistentCompositionLocalMap;", "parentScope", "currentProviders", "updateProviderMapGroup", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;Landroidx/compose/runtime/PersistentCompositionLocalMap;)Landroidx/compose/runtime/PersistentCompositionLocalMap;", "providers", "recordProviderUpdate", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "ensureWriter", "createFreshInsertTable", "forceFreshInsertTable", "isNode", "data", "startReaderGroup", "(ZLjava/lang/Object;)V", "objectKey", "Landroidx/compose/runtime/GroupKind;", "kind", "start-BaiHCIY", "(ILjava/lang/Object;ILjava/lang/Object;)V", TtmlNode.START, "Landroidx/compose/runtime/Pending;", "newPending", "enterGroup", "(ZLandroidx/compose/runtime/Pending;)V", "expectedNodeCount", "inserting", "exitGroup", "(IZ)V", TtmlNode.END, "recomposeToGroupEnd", "index", "insertedGroupVirtualIndex", "(I)I", "newCount", "updateNodeCountOverrides", "(II)V", "groupLocation", "recomposeGroup", "recomposeIndex", "nodeIndexOf", "(IIII)I", "rGroupIndexOf", "updatedNodeCount", "count", "updateNodeCount", "clearUpdatedNodeCounts", "oldGroup", "newGroup", "commonRoot", "recordUpsAndDowns", "(III)V", "nearestCommonRoot", "doRecordDownsFor", "rGroupIndex", "recomposeKey", "compoundKeyOf", "Landroidx/compose/runtime/SlotReader;", "groupCompoundKeyPart", "(Landroidx/compose/runtime/SlotReader;I)I", "skipReaderToGroupEnd", "addRecomposeScope", "locals", "force", "invokeMovableContentLambda", "(Landroidx/compose/runtime/MovableContent;Landroidx/compose/runtime/PersistentCompositionLocalMap;Ljava/lang/Object;Z)V", "insertMovableContentGuarded", "R", "reader", "withReader", "(Landroidx/compose/runtime/SlotReader;Lr7/a;)Ljava/lang/Object;", "from", "to", "invalidations", "recomposeMovableContent", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ControlledComposition;Ljava/lang/Integer;Ljava/util/List;Lr7/a;)Ljava/lang/Object;", "doCompose", "nodeAt", "(Landroidx/compose/runtime/SlotReader;I)Ljava/lang/Object;", "validateNodeExpected", "validateNodeNotExpected", LinkHeader.Parameters.Anchor, "recordInsert", "(Landroidx/compose/runtime/Anchor;)V", "recordDelete", "groupBeingRemoved", "reportFreeMovableContent", "reportAllMovableContent", "finalizeCompose", "cleanUpCompose", "groupKey", "updateCompoundKeyWhenWeEnterGroup", "(IILjava/lang/Object;Ljava/lang/Object;)V", "keyHash", "updateCompoundKeyWhenWeEnterGroupKeyHash", "updateCompoundKeyWhenWeExitGroup", "updateCompoundKeyWhenWeExitGroupKeyHash", "Landroidx/compose/runtime/Applier;", "getApplier", "()Landroidx/compose/runtime/Applier;", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/SlotTable;", "Ljava/util/Set;", "Landroidx/compose/runtime/changelist/ChangeList;", "Landroidx/compose/runtime/ControlledComposition;", "getComposition", "()Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/Stack;", "pendingStack", "Landroidx/compose/runtime/Stack;", "pending", "Landroidx/compose/runtime/Pending;", "nodeIndex", "I", "groupNodeCount", "Landroidx/compose/runtime/IntStack;", "parentStateStack", "Landroidx/compose/runtime/IntStack;", "", "nodeCountOverrides", "[I", "Lt/t;", "nodeCountVirtualOverrides", "Lt/t;", "Z", "forciblyRecompose", "nodeExpected", "", "Landroidx/compose/runtime/Invalidation;", "Ljava/util/List;", "entersStack", "parentProvider", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "Landroidx/compose/runtime/collection/IntMap;", "providerUpdates", "Landroidx/compose/runtime/collection/IntMap;", "providersInvalid", "providersInvalidStack", "reusing", "reusingGroup", "childrenComposing", "compositionToken", "sourceMarkersEnabled", "androidx/compose/runtime/ComposerImpl$derivedStateObserver$1", "derivedStateObserver", "Landroidx/compose/runtime/ComposerImpl$derivedStateObserver$1;", "invalidateStack", "<set-?>", "isComposing", "isComposing$runtime_release", "isDisposed", "isDisposed$runtime_release", "Landroidx/compose/runtime/SlotReader;", "getReader$runtime_release", "()Landroidx/compose/runtime/SlotReader;", "setReader$runtime_release", "(Landroidx/compose/runtime/SlotReader;)V", "insertTable", "getInsertTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "setInsertTable$runtime_release", "(Landroidx/compose/runtime/SlotTable;)V", "Landroidx/compose/runtime/SlotWriter;", "writer", "Landroidx/compose/runtime/SlotWriter;", "writerHasAProvider", "providerCache", "deferredChanges", "getDeferredChanges$runtime_release", "()Landroidx/compose/runtime/changelist/ChangeList;", "setDeferredChanges$runtime_release", "(Landroidx/compose/runtime/changelist/ChangeList;)V", "Landroidx/compose/runtime/changelist/ComposerChangeListWriter;", "changeListWriter", "Landroidx/compose/runtime/changelist/ComposerChangeListWriter;", "insertAnchor", "Landroidx/compose/runtime/Anchor;", "Landroidx/compose/runtime/changelist/FixupList;", "insertFixups", "Landroidx/compose/runtime/changelist/FixupList;", "getInserting", "getInserting$annotations", "compoundKeyHash", "getCompoundKeyHash", "getCompoundKeyHash$annotations", "getAreChildrenComposing$runtime_release", "areChildrenComposing", "getHasPendingChanges$runtime_release", "hasPendingChanges", "Ld7/j;", "getApplyCoroutineContext", "()Ld7/j;", "applyCoroutineContext", "getDefaultsInvalid", "getDefaultsInvalid$annotations", "defaultsInvalid", "getSkipping", "getSkipping$annotations", "skipping", "getCurrentMarker", "currentMarker", "Landroidx/compose/runtime/tooling/CompositionData;", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "compositionData", "Landroidx/compose/runtime/CompositionLocalMap;", "getCurrentCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "currentCompositionLocalMap", "getChangeCount$runtime_release", "changeCount", "getCurrentRecomposeScope$runtime_release", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "currentRecomposeScope", "getHasInvalidations", "hasInvalidations", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "recomposeScope", "getRecomposeScopeIdentity", "recomposeScopeIdentity", "getNode", "(Landroidx/compose/runtime/SlotReader;)Ljava/lang/Object;", "node", "CompositionContextHolder", "CompositionContextImpl", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ComposerImpl implements Composer {
    public static final int $stable = 8;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ComposerChangeListWriter changeListWriter;
    private ChangeList changes;
    private int childrenComposing;
    private final ControlledComposition composition;
    private int compositionToken;
    private int compoundKeyHash;
    private ChangeList deferredChanges;
    private final ComposerImpl$derivedStateObserver$1 derivedStateObserver;
    private boolean forceRecomposeScopes;
    private boolean forciblyRecompose;
    private int groupNodeCount;
    private Anchor insertAnchor;
    private FixupList insertFixups;
    private SlotTable insertTable;
    private boolean inserting;
    private final Stack<RecomposeScopeImpl> invalidateStack;
    private boolean isComposing;
    private boolean isDisposed;
    private ChangeList lateChanges;
    private int[] nodeCountOverrides;
    private t nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private final CompositionContext parentContext;
    private Pending pending;
    private PersistentCompositionLocalMap providerCache;
    private IntMap<PersistentCompositionLocalMap> providerUpdates;
    private boolean providersInvalid;
    private int rGroupIndex;
    private SlotReader reader;
    private boolean reusing;
    private final SlotTable slotTable;
    private boolean sourceMarkersEnabled;
    private SlotWriter writer;
    private boolean writerHasAProvider;
    private final Stack<Pending> pendingStack = new Stack<>();
    private final IntStack parentStateStack = new IntStack();
    private final List<Invalidation> invalidations = new ArrayList();
    private final IntStack entersStack = new IntStack();
    private PersistentCompositionLocalMap parentProvider = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
    private final IntStack providersInvalidStack = new IntStack();
    private int reusingGroup = -1;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002R\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\tJ\u000f\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000b\u0010\tR\u001b\u0010\u0004\u001a\u00060\u0002R\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextHolder;", "Landroidx/compose/runtime/ReusableRememberObserver;", "Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/ComposerImpl;", "ref", "<init>", "(Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;)V", "Lx6/t0;", "onRemembered", "()V", "onAbandoned", "onForgotten", "Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "getRef", "()Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class CompositionContextHolder implements ReusableRememberObserver {
        private final CompositionContextImpl ref;

        public CompositionContextHolder(CompositionContextImpl compositionContextImpl) {
            this.ref = compositionContextImpl;
        }

        public final CompositionContextImpl getRef() {
            return this.ref;
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onAbandoned() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onForgotten() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onRemembered() {
        }
    }

    @Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0010¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0010¢\u0006\u0004\b\u0013\u0010\u0011J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\b\u001a\u0010\u0018J*\u0010!\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0011\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\u000b0\u001c¢\u0006\u0002\b\u001dH\u0010¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010#\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\b\"\u0010\u0018J\u0017\u0010(\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020$H\u0010¢\u0006\u0004\b&\u0010'J\u000f\u0010,\u001a\u00020)H\u0010¢\u0006\u0004\b*\u0010+J\u0015\u0010-\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020)¢\u0006\u0004\b-\u0010.J\u001d\u00104\u001a\u00020\u000b2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000/H\u0010¢\u0006\u0004\b2\u00103J\u000f\u00106\u001a\u00020\u000bH\u0010¢\u0006\u0004\b5\u0010\rJ\u000f\u00108\u001a\u00020\u000bH\u0010¢\u0006\u0004\b7\u0010\rJ\u0017\u0010=\u001a\u00020\u000b2\u0006\u0010:\u001a\u000209H\u0010¢\u0006\u0004\b;\u0010<J\u0017\u0010?\u001a\u00020\u000b2\u0006\u0010:\u001a\u000209H\u0010¢\u0006\u0004\b>\u0010<J\u0019\u0010C\u001a\u0004\u0018\u00010@2\u0006\u0010:\u001a\u000209H\u0010¢\u0006\u0004\bA\u0010BJ\u001f\u0010G\u001a\u00020\u000b2\u0006\u0010:\u001a\u0002092\u0006\u0010D\u001a\u00020@H\u0010¢\u0006\u0004\bE\u0010FJ\u0017\u0010I\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\bH\u0010\u0018R\u001a\u0010\u0003\u001a\u00020\u00028\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u0003\u0010J\u001a\u0004\bK\u0010LR\u001a\u0010\u0005\u001a\u00020\u00048\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u0005\u0010M\u001a\u0004\bN\u0010OR\u001a\u0010\u0006\u001a\u00020\u00048\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u0006\u0010M\u001a\u0004\bP\u0010OR\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\b\u0010Q\u001a\u0004\bR\u0010SR0\u0010T\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/\u0018\u00010/8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u00103R\u001d\u0010Z\u001a\b\u0012\u0004\u0012\u00020Y0/8\u0006¢\u0006\f\n\u0004\bZ\u0010U\u001a\u0004\b[\u0010WR+\u0010`\u001a\u00020)2\u0006\u0010\\\u001a\u00020)8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b,\u0010+\"\u0004\b_\u0010.R\u0014\u0010b\u001a\u00020\u00048PX\u0090\u0004¢\u0006\u0006\u001a\u0004\ba\u0010OR\u0014\u0010f\u001a\u00020c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u001a\u0010i\u001a\u00020c8PX\u0090\u0004¢\u0006\f\u0012\u0004\bh\u0010\r\u001a\u0004\bg\u0010e¨\u0006j"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/CompositionContext;", "", "compoundHashKey", "", "collectingParameterInformation", "collectingSourceInformation", "Landroidx/compose/runtime/CompositionObserverHolder;", "observerHolder", "<init>", "(Landroidx/compose/runtime/ComposerImpl;IZZLandroidx/compose/runtime/CompositionObserverHolder;)V", "Lx6/t0;", "dispose", "()V", "Landroidx/compose/runtime/Composer;", "composer", "registerComposer$runtime_release", "(Landroidx/compose/runtime/Composer;)V", "registerComposer", "unregisterComposer$runtime_release", "unregisterComposer", "Landroidx/compose/runtime/ControlledComposition;", "composition", "registerComposition$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;)V", "registerComposition", "unregisterComposition$runtime_release", "unregisterComposition", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "composeInitial$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;Lr7/p;)V", "composeInitial", "invalidate$runtime_release", "invalidate", "Landroidx/compose/runtime/RecomposeScopeImpl;", "scope", "invalidateScope$runtime_release", "(Landroidx/compose/runtime/RecomposeScopeImpl;)V", "invalidateScope", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "getCompositionLocalScope$runtime_release", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "getCompositionLocalScope", "updateCompositionLocalScope", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "", "Landroidx/compose/runtime/tooling/CompositionData;", "table", "recordInspectionTable$runtime_release", "(Ljava/util/Set;)V", "recordInspectionTable", "startComposing$runtime_release", "startComposing", "doneComposing$runtime_release", "doneComposing", "Landroidx/compose/runtime/MovableContentStateReference;", "reference", "insertMovableContent$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;)V", "insertMovableContent", "deletedMovableContent$runtime_release", "deletedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;)Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve", "data", "movableContentStateReleased$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;Landroidx/compose/runtime/MovableContentState;)V", "movableContentStateReleased", "reportRemovedComposition$runtime_release", "reportRemovedComposition", "I", "getCompoundHashKey$runtime_release", "()I", "Z", "getCollectingParameterInformation$runtime_release", "()Z", "getCollectingSourceInformation$runtime_release", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "inspectionTables", "Ljava/util/Set;", "getInspectionTables", "()Ljava/util/Set;", "setInspectionTables", "Landroidx/compose/runtime/ComposerImpl;", "composers", "getComposers", "<set-?>", "compositionLocalScope$delegate", "Landroidx/compose/runtime/MutableState;", "setCompositionLocalScope", "compositionLocalScope", "getCollectingCallByInformation$runtime_release", "collectingCallByInformation", "Ld7/j;", "getEffectCoroutineContext", "()Ld7/j;", "effectCoroutineContext", "getRecomposeCoroutineContext$runtime_release", "getRecomposeCoroutineContext$runtime_release$annotations", "recomposeCoroutineContext", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class CompositionContextImpl extends CompositionContext {
        private final boolean collectingParameterInformation;
        private final boolean collectingSourceInformation;
        private final Set<ComposerImpl> composers = new LinkedHashSet();

        /* JADX INFO: renamed from: compositionLocalScope$delegate, reason: from kotlin metadata */
        private final MutableState compositionLocalScope = SnapshotStateKt.mutableStateOf(PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf(), SnapshotStateKt.referentialEqualityPolicy());
        private final int compoundHashKey;
        private Set<Set<CompositionData>> inspectionTables;
        private final CompositionObserverHolder observerHolder;

        public CompositionContextImpl(int i10, boolean z, boolean z5, CompositionObserverHolder compositionObserverHolder) {
            this.compoundHashKey = i10;
            this.collectingParameterInformation = z;
            this.collectingSourceInformation = z5;
            this.observerHolder = compositionObserverHolder;
        }

        private final PersistentCompositionLocalMap getCompositionLocalScope() {
            return (PersistentCompositionLocalMap) this.compositionLocalScope.getValue();
        }

        public static /* synthetic */ void getRecomposeCoroutineContext$runtime_release$annotations() {
        }

        private final void setCompositionLocalScope(PersistentCompositionLocalMap persistentCompositionLocalMap) {
            this.compositionLocalScope.setValue(persistentCompositionLocalMap);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void composeInitial$runtime_release(ControlledComposition composition, p<? super Composer, ? super Integer, t0> content) {
            ComposerImpl.this.parentContext.composeInitial$runtime_release(composition, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void deletedMovableContent$runtime_release(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.deletedMovableContent$runtime_release(reference);
        }

        public final void dispose() {
            if (this.composers.isEmpty()) {
                return;
            }
            Set<Set<CompositionData>> set = this.inspectionTables;
            if (set != null) {
                for (ComposerImpl composerImpl : this.composers) {
                    Iterator<Set<CompositionData>> it = set.iterator();
                    while (it.hasNext()) {
                        it.next().remove(composerImpl.slotTable);
                    }
                }
            }
            this.composers.clear();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void doneComposing$runtime_release() {
            ComposerImpl composerImpl = ComposerImpl.this;
            composerImpl.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getCollectingCallByInformation$runtime_release() {
            return ComposerImpl.this.parentContext.getCollectingCallByInformation$runtime_release();
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCollectingParameterInformation$runtime_release, reason: from getter */
        public boolean getCollectingParameterInformation() {
            return this.collectingParameterInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCollectingSourceInformation$runtime_release, reason: from getter */
        public boolean getCollectingSourceInformation() {
            return this.collectingSourceInformation;
        }

        public final Set<ComposerImpl> getComposers() {
            return this.composers;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public PersistentCompositionLocalMap getCompositionLocalScope$runtime_release() {
            return getCompositionLocalScope();
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCompoundHashKey$runtime_release, reason: from getter */
        public int getCompoundHashKey() {
            return this.compoundHashKey;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public d7.j getEffectCoroutineContext() {
            return ComposerImpl.this.parentContext.getEffectCoroutineContext();
        }

        public final Set<Set<CompositionData>> getInspectionTables() {
            return this.inspectionTables;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getObserverHolder$runtime_release, reason: from getter */
        public CompositionObserverHolder getObserverHolder() {
            return this.observerHolder;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public d7.j getRecomposeCoroutineContext$runtime_release() {
            return CompositionKt.getRecomposeCoroutineContext(ComposerImpl.this.getComposition());
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void insertMovableContent$runtime_release(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.insertMovableContent$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidate$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.invalidate$runtime_release(ComposerImpl.this.getComposition());
            ComposerImpl.this.parentContext.invalidate$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidateScope$runtime_release(RecomposeScopeImpl scope) {
            ComposerImpl.this.parentContext.invalidateScope$runtime_release(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void movableContentStateReleased$runtime_release(MovableContentStateReference reference, MovableContentState data) {
            ComposerImpl.this.parentContext.movableContentStateReleased$runtime_release(reference, data);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference reference) {
            return ComposerImpl.this.parentContext.movableContentStateResolve$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void recordInspectionTable$runtime_release(Set<CompositionData> table) {
            Set hashSet = this.inspectionTables;
            if (hashSet == null) {
                hashSet = new HashSet();
                this.inspectionTables = hashSet;
            }
            hashSet.add(table);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposer$runtime_release(Composer composer) {
            super.registerComposer$runtime_release((ComposerImpl) composer);
            this.composers.add(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.registerComposition$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportRemovedComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.reportRemovedComposition$runtime_release(composition);
        }

        public final void setInspectionTables(Set<Set<CompositionData>> set) {
            this.inspectionTables = set;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void startComposing$runtime_release() {
            ComposerImpl.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposer$runtime_release(Composer composer) {
            Set<Set<CompositionData>> set = this.inspectionTables;
            if (set != null) {
                Iterator<T> it = set.iterator();
                while (it.hasNext()) {
                    ((Set) it.next()).remove(((ComposerImpl) composer).slotTable);
                }
            }
            q0.a(this.composers).remove(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.unregisterComposition$runtime_release(composition);
        }

        public final void updateCompositionLocalScope(PersistentCompositionLocalMap scope) {
            setCompositionLocalScope(scope);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ MovableContent<Object> $content;
        final /* synthetic */ Object $parameter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MovableContent<Object> movableContent, Object obj) {
            super(2);
            this.$content = movableContent;
            this.$parameter = obj;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(316014703, i10, -1, "androidx.compose.runtime.ComposerImpl.invokeMovableContentLambda.<anonymous> (Composer.kt:3291)");
            }
            this.$content.getContent().invoke(this.$parameter, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [androidx.compose.runtime.ComposerImpl$derivedStateObserver$1] */
    public ComposerImpl(Applier<?> applier, CompositionContext compositionContext, SlotTable slotTable, Set<RememberObserver> set, ChangeList changeList, ChangeList changeList2, ControlledComposition controlledComposition) {
        this.applier = applier;
        this.parentContext = compositionContext;
        this.slotTable = slotTable;
        this.abandonSet = set;
        this.changes = changeList;
        this.lateChanges = changeList2;
        this.composition = controlledComposition;
        this.sourceMarkersEnabled = compositionContext.getCollectingSourceInformation() || compositionContext.getCollectingCallByInformation$runtime_release();
        this.derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.ComposerImpl$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                ComposerImpl composerImpl = this.this$0;
                composerImpl.childrenComposing--;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.childrenComposing++;
            }
        };
        this.invalidateStack = new Stack<>();
        SlotReader slotReaderOpenReader = slotTable.openReader();
        slotReaderOpenReader.close();
        this.reader = slotReaderOpenReader;
        SlotTable slotTable2 = new SlotTable();
        if (compositionContext.getCollectingSourceInformation()) {
            slotTable2.collectSourceInformation();
        }
        if (compositionContext.getCollectingCallByInformation$runtime_release()) {
            slotTable2.collectCalledByInformation();
        }
        this.insertTable = slotTable2;
        SlotWriter slotWriterOpenWriter = slotTable2.openWriter();
        slotWriterOpenWriter.close(true);
        this.writer = slotWriterOpenWriter;
        this.changeListWriter = new ComposerChangeListWriter(this, this.changes);
        SlotReader slotReaderOpenReader2 = this.insertTable.openReader();
        try {
            Anchor anchor = slotReaderOpenReader2.anchor(0);
            slotReaderOpenReader2.close();
            this.insertAnchor = anchor;
            this.insertFixups = new FixupList();
        } catch (Throwable th) {
            slotReaderOpenReader2.close();
            throw th;
        }
    }

    private final void abortRoot() {
        cleanUpCompose();
        this.pendingStack.clear();
        this.parentStateStack.clear();
        this.entersStack.clear();
        this.providersInvalidStack.clear();
        this.providerUpdates = null;
        this.insertFixups.clear();
        this.compoundKeyHash = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.forciblyRecompose = false;
        this.reusingGroup = -1;
        if (!this.reader.getClosed()) {
            this.reader.close();
        }
        if (this.writer.getClosed()) {
            return;
        }
        forceFreshInsertTable();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addRecomposeScope() {
        /*
            r3 = this;
            boolean r0 = r3.getInserting()
            if (r0 == 0) goto L1f
            androidx.compose.runtime.RecomposeScopeImpl r0 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.ControlledComposition r1 = r3.getComposition()
            androidx.compose.runtime.CompositionImpl r1 = (androidx.compose.runtime.CompositionImpl) r1
            r0.<init>(r1)
            androidx.compose.runtime.Stack<androidx.compose.runtime.RecomposeScopeImpl> r1 = r3.invalidateStack
            r1.push(r0)
            r3.updateValue(r0)
            int r1 = r3.compositionToken
            r0.start(r1)
            return
        L1f:
            java.util.List<androidx.compose.runtime.Invalidation> r0 = r3.invalidations
            androidx.compose.runtime.SlotReader r1 = r3.reader
            int r1 = r1.getParent()
            androidx.compose.runtime.Invalidation r0 = androidx.compose.runtime.ComposerKt.access$removeLocation(r0, r1)
            androidx.compose.runtime.SlotReader r1 = r3.reader
            java.lang.Object r1 = r1.next()
            androidx.compose.runtime.Composer$Companion r2 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r2 = r2.getEmpty()
            boolean r2 = kotlin.jvm.internal.p.a(r1, r2)
            if (r2 == 0) goto L4c
            androidx.compose.runtime.RecomposeScopeImpl r1 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.ControlledComposition r2 = r3.getComposition()
            androidx.compose.runtime.CompositionImpl r2 = (androidx.compose.runtime.CompositionImpl) r2
            r1.<init>(r2)
            r3.updateValue(r1)
            goto L4e
        L4c:
            androidx.compose.runtime.RecomposeScopeImpl r1 = (androidx.compose.runtime.RecomposeScopeImpl) r1
        L4e:
            if (r0 != 0) goto L5c
            boolean r0 = r1.getForcedRecompose()
            r2 = 0
            if (r0 == 0) goto L5a
            r1.setForcedRecompose(r2)
        L5a:
            if (r0 == 0) goto L5d
        L5c:
            r2 = 1
        L5d:
            r1.setRequiresRecompose(r2)
            androidx.compose.runtime.Stack<androidx.compose.runtime.RecomposeScopeImpl> r0 = r3.invalidateStack
            r0.push(r1)
            int r0 = r3.compositionToken
            r1.start(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.addRecomposeScope():void");
    }

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compoundKeyHash = 0;
        this.nodeExpected = false;
        this.changeListWriter.resetTransientState();
        this.invalidateStack.clear();
        clearUpdatedNodeCounts();
    }

    private final void clearUpdatedNodeCounts() {
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    private final int compoundKeyOf(int group, int rGroupIndex, int recomposeGroup, int recomposeKey) {
        if (group == recomposeGroup) {
            return recomposeKey;
        }
        int iGroupCompoundKeyPart = groupCompoundKeyPart(this.reader, group);
        if (iGroupCompoundKeyPart == 126665345) {
            return iGroupCompoundKeyPart;
        }
        int iParent = this.reader.parent(group);
        if (iParent != recomposeGroup) {
            recomposeKey = compoundKeyOf(iParent, rGroupIndexOf(iParent), recomposeGroup, recomposeKey);
        }
        if (this.reader.hasObjectKey(group)) {
            rGroupIndex = 0;
        }
        return Integer.rotateLeft(Integer.rotateLeft(recomposeKey, 3) ^ iGroupCompoundKeyPart, 3) ^ rGroupIndex;
    }

    private final void createFreshInsertTable() {
        ComposerKt.runtimeCheck(this.writer.getClosed());
        forceFreshInsertTable();
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap persistentCompositionLocalMap = this.providerCache;
        return persistentCompositionLocalMap != null ? persistentCompositionLocalMap : currentCompositionLocalScope(this.reader.getParent());
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void doCompose(androidx.compose.runtime.collection.ScopeMap<androidx.compose.runtime.RecomposeScopeImpl, java.lang.Object> r21, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.doCompose(androidx.compose.runtime.collection.ScopeMap, r7.p):void");
    }

    private final void doRecordDownsFor(int group, int nearestCommonRoot) {
        if (group <= 0 || group == nearestCommonRoot) {
            return;
        }
        doRecordDownsFor(this.reader.parent(group), nearestCommonRoot);
        if (this.reader.isNode(group)) {
            this.changeListWriter.moveDown(nodeAt(this.reader, group));
        }
    }

    private final void end(boolean isNode) {
        int iOrdinal;
        List<KeyInfo> list;
        List<KeyInfo> list2;
        int iOrdinal2;
        int iPeek2 = this.parentStateStack.peek2() - 1;
        if (getInserting()) {
            int parent = this.writer.getParent();
            int iGroupKey = this.writer.groupKey(parent);
            Object objGroupObjectKey = this.writer.groupObjectKey(parent);
            Object objGroupAux = this.writer.groupAux(parent);
            if (objGroupObjectKey != null) {
                iOrdinal2 = (objGroupObjectKey instanceof Enum ? ((Enum) objGroupObjectKey).ordinal() : objGroupObjectKey.hashCode()) ^ Integer.rotateRight(getCompoundKeyHash(), 3);
            } else if (objGroupAux == null || iGroupKey != 207 || objGroupAux.equals(Composer.INSTANCE.getEmpty())) {
                iOrdinal2 = Integer.rotateRight(iPeek2 ^ getCompoundKeyHash(), 3) ^ iGroupKey;
            } else {
                this.compoundKeyHash = Integer.rotateRight(Integer.rotateRight(iPeek2 ^ getCompoundKeyHash(), 3) ^ objGroupAux.hashCode(), 3);
            }
            this.compoundKeyHash = Integer.rotateRight(iOrdinal2, 3);
        } else {
            int parent2 = this.reader.getParent();
            int iGroupKey2 = this.reader.groupKey(parent2);
            Object objGroupObjectKey2 = this.reader.groupObjectKey(parent2);
            Object objGroupAux2 = this.reader.groupAux(parent2);
            if (objGroupObjectKey2 != null) {
                iOrdinal = (objGroupObjectKey2 instanceof Enum ? ((Enum) objGroupObjectKey2).ordinal() : objGroupObjectKey2.hashCode()) ^ Integer.rotateRight(getCompoundKeyHash(), 3);
            } else if (objGroupAux2 == null || iGroupKey2 != 207 || objGroupAux2.equals(Composer.INSTANCE.getEmpty())) {
                iOrdinal = Integer.rotateRight(iPeek2 ^ getCompoundKeyHash(), 3) ^ iGroupKey2;
            } else {
                this.compoundKeyHash = Integer.rotateRight(Integer.rotateRight(iPeek2 ^ getCompoundKeyHash(), 3) ^ objGroupAux2.hashCode(), 3);
            }
            this.compoundKeyHash = Integer.rotateRight(iOrdinal, 3);
        }
        int i10 = this.groupNodeCount;
        Pending pending = this.pending;
        if (pending != null && pending.getKeyInfos().size() > 0) {
            List<KeyInfo> keyInfos = pending.getKeyInfos();
            List<KeyInfo> used = pending.getUsed();
            Set setFastToSet = ListUtilsKt.fastToSet(used);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int size = used.size();
            int size2 = keyInfos.size();
            int i11 = 0;
            int i12 = 0;
            int iUpdatedNodeCountOf = 0;
            while (i11 < size2) {
                KeyInfo keyInfo = keyInfos.get(i11);
                if (setFastToSet.contains(keyInfo)) {
                    list = keyInfos;
                    if (!linkedHashSet.contains(keyInfo)) {
                        if (i12 < size) {
                            KeyInfo keyInfo2 = used.get(i12);
                            if (keyInfo2 != keyInfo) {
                                int iNodePositionOf = pending.nodePositionOf(keyInfo2);
                                linkedHashSet.add(keyInfo2);
                                if (iNodePositionOf != iUpdatedNodeCountOf) {
                                    int iUpdatedNodeCountOf2 = pending.updatedNodeCountOf(keyInfo2);
                                    list2 = used;
                                    this.changeListWriter.moveNode(pending.getStartIndex() + iNodePositionOf, iUpdatedNodeCountOf + pending.getStartIndex(), iUpdatedNodeCountOf2);
                                    pending.registerMoveNode(iNodePositionOf, iUpdatedNodeCountOf, iUpdatedNodeCountOf2);
                                } else {
                                    list2 = used;
                                }
                            } else {
                                list2 = used;
                                i11++;
                            }
                            i12++;
                            iUpdatedNodeCountOf += pending.updatedNodeCountOf(keyInfo2);
                            keyInfos = list;
                            used = list2;
                        }
                    }
                    keyInfos = list;
                } else {
                    this.changeListWriter.removeNode(pending.nodePositionOf(keyInfo) + pending.getStartIndex(), keyInfo.getNodes());
                    pending.updateNodeCount(keyInfo.getLocation(), 0);
                    this.changeListWriter.moveReaderRelativeTo(keyInfo.getLocation());
                    this.reader.reposition(keyInfo.getLocation());
                    recordDelete();
                    this.reader.skipGroup();
                    list = keyInfos;
                    ComposerKt.removeRange(this.invalidations, keyInfo.getLocation(), this.reader.groupSize(keyInfo.getLocation()) + keyInfo.getLocation());
                }
                i11++;
                keyInfos = list;
            }
            this.changeListWriter.endNodeMovement();
            if (keyInfos.size() > 0) {
                this.changeListWriter.moveReaderRelativeTo(this.reader.getGroupEnd());
                this.reader.skipToGroupEnd();
            }
        }
        int i13 = this.nodeIndex;
        while (!this.reader.isGroupEnd()) {
            int currentGroup = this.reader.getCurrentGroup();
            recordDelete();
            this.changeListWriter.removeNode(i13, this.reader.skipGroup());
            ComposerKt.removeRange(this.invalidations, currentGroup, this.reader.getCurrentGroup());
        }
        boolean inserting = getInserting();
        if (inserting) {
            if (isNode) {
                this.insertFixups.endNodeInsert();
                i10 = 1;
            }
            this.reader.endEmpty();
            int parent3 = this.writer.getParent();
            this.writer.endGroup();
            if (!this.reader.getInEmpty()) {
                int iInsertedGroupVirtualIndex = insertedGroupVirtualIndex(parent3);
                this.writer.endInsert();
                this.writer.close(true);
                recordInsert(this.insertAnchor);
                this.inserting = false;
                if (!this.slotTable.isEmpty()) {
                    updateNodeCount(iInsertedGroupVirtualIndex, 0);
                    updateNodeCountOverrides(iInsertedGroupVirtualIndex, i10);
                }
            }
        } else {
            if (isNode) {
                this.changeListWriter.moveUp();
            }
            int remainingSlots = this.reader.getRemainingSlots();
            if (remainingSlots > 0) {
                this.changeListWriter.trimValues(remainingSlots);
            }
            this.changeListWriter.endCurrentGroup();
            int parent4 = this.reader.getParent();
            if (i10 != updatedNodeCount(parent4)) {
                updateNodeCountOverrides(parent4, i10);
            }
            if (isNode) {
                i10 = 1;
            }
            this.reader.endGroup();
            this.changeListWriter.endNodeMovement();
        }
        exitGroup(i10, inserting);
    }

    private final void endGroup() {
        end(false);
    }

    private final void endRoot() {
        endGroup();
        this.parentContext.doneComposing$runtime_release();
        endGroup();
        this.changeListWriter.endRoot();
        finalizeCompose();
        this.reader.close();
        this.forciblyRecompose = false;
        this.providersInvalid = ComposerKt.asBool(this.providersInvalidStack.pop());
    }

    private final void ensureWriter() {
        if (this.writer.getClosed()) {
            SlotWriter slotWriterOpenWriter = this.insertTable.openWriter();
            this.writer = slotWriterOpenWriter;
            slotWriterOpenWriter.skipToGroupEnd();
            this.writerHasAProvider = false;
            this.providerCache = null;
        }
    }

    private final void enterGroup(boolean isNode, Pending newPending) {
        this.pendingStack.push(this.pending);
        this.pending = newPending;
        this.parentStateStack.push(this.groupNodeCount);
        this.parentStateStack.push(this.rGroupIndex);
        this.parentStateStack.push(this.nodeIndex);
        if (isNode) {
            this.nodeIndex = 0;
        }
        this.groupNodeCount = 0;
        this.rGroupIndex = 0;
    }

    private final void exitGroup(int expectedNodeCount, boolean inserting) {
        Pending pendingPop = this.pendingStack.pop();
        if (pendingPop != null && !inserting) {
            pendingPop.setGroupIndex(pendingPop.getGroupIndex() + 1);
        }
        this.pending = pendingPop;
        this.nodeIndex = this.parentStateStack.pop() + expectedNodeCount;
        this.rGroupIndex = this.parentStateStack.pop();
        this.groupNodeCount = this.parentStateStack.pop() + expectedNodeCount;
    }

    private final void finalizeCompose() {
        this.changeListWriter.finalizeComposition();
        if (!this.pendingStack.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Start/end imbalance");
        }
        cleanUpCompose();
    }

    private final void forceFreshInsertTable() {
        SlotTable slotTable = new SlotTable();
        if (this.sourceMarkersEnabled) {
            slotTable.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime_release()) {
            slotTable.collectCalledByInformation();
        }
        this.insertTable = slotTable;
        SlotWriter slotWriterOpenWriter = slotTable.openWriter();
        slotWriterOpenWriter.close(true);
        this.writer = slotWriterOpenWriter;
    }

    public static /* synthetic */ void getCompoundKeyHash$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getDefaultsInvalid$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getInserting$annotations() {
    }

    private final Object getNode(SlotReader slotReader) {
        return slotReader.node(slotReader.getParent());
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getSkipping$annotations() {
    }

    private final int groupCompoundKeyPart(SlotReader slotReader, int i10) {
        Object objGroupAux;
        if (!slotReader.hasObjectKey(i10)) {
            int iGroupKey = slotReader.groupKey(i10);
            return (iGroupKey != 207 || (objGroupAux = slotReader.groupAux(i10)) == null || objGroupAux.equals(Composer.INSTANCE.getEmpty())) ? iGroupKey : objGroupAux.hashCode();
        }
        Object objGroupObjectKey = slotReader.groupObjectKey(i10);
        if (objGroupObjectKey != null) {
            return objGroupObjectKey instanceof Enum ? ((Enum) objGroupObjectKey).ordinal() : objGroupObjectKey instanceof MovableContent ? MovableContentKt.movableContentKey : objGroupObjectKey.hashCode();
        }
        return 0;
    }

    private final void insertMovableContentGuarded(List<x> references) throws Throwable {
        ComposerChangeListWriter composerChangeListWriter;
        ChangeList changeList;
        ComposerChangeListWriter composerChangeListWriter2;
        ChangeList changeList2;
        SlotTable slotTable;
        Anchor anchor;
        SlotReader slotReader;
        IntMap intMap;
        ComposerChangeListWriter composerChangeListWriter3;
        ChangeList changeList3;
        int i10;
        int i11;
        SlotTable slotTable2;
        ComposerChangeListWriter composerChangeListWriter4 = this.changeListWriter;
        ChangeList changeList4 = this.lateChanges;
        ChangeList changeList5 = composerChangeListWriter4.getChangeList();
        try {
            composerChangeListWriter4.setChangeList(changeList4);
            this.changeListWriter.resetSlots();
            int size = references.size();
            int i12 = 0;
            int i13 = 0;
            while (i13 < size) {
                try {
                    x xVar = references.get(i13);
                    MovableContentStateReference movableContentStateReference = (MovableContentStateReference) xVar.f22608i;
                    MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) xVar.f22609l;
                    Anchor anchor2 = movableContentStateReference.getAnchor();
                    int iAnchorIndex = movableContentStateReference.getSlotTable().anchorIndex(anchor2);
                    IntRef intRef = new IntRef(i12, 1, null);
                    this.changeListWriter.determineMovableContentNodeIndex(intRef, anchor2);
                    if (movableContentStateReference2 == null) {
                        if (kotlin.jvm.internal.p.a(movableContentStateReference.getSlotTable(), this.insertTable)) {
                            createFreshInsertTable();
                        }
                        SlotReader slotReaderOpenReader = movableContentStateReference.getSlotTable().openReader();
                        try {
                            slotReaderOpenReader.reposition(iAnchorIndex);
                            this.changeListWriter.moveReaderToAbsolute(iAnchorIndex);
                            ChangeList changeList6 = new ChangeList();
                            recomposeMovableContent$default(this, null, null, null, null, new ComposerImpl$insertMovableContentGuarded$1$1$1$1(this, changeList6, slotReaderOpenReader, movableContentStateReference), 15, null);
                            this.changeListWriter.includeOperationsIn(changeList6, intRef);
                            slotReaderOpenReader.close();
                            i11 = size;
                            composerChangeListWriter2 = composerChangeListWriter4;
                            changeList2 = changeList5;
                            i10 = i13;
                        } finally {
                        }
                    } else {
                        MovableContentState movableContentStateMovableContentStateResolve$runtime_release = this.parentContext.movableContentStateResolve$runtime_release(movableContentStateReference2);
                        if (movableContentStateMovableContentStateResolve$runtime_release == null || (slotTable = movableContentStateMovableContentStateResolve$runtime_release.getSlotTable()) == null) {
                            slotTable = movableContentStateReference2.getSlotTable();
                        }
                        if (movableContentStateMovableContentStateResolve$runtime_release == null || (slotTable2 = movableContentStateMovableContentStateResolve$runtime_release.getSlotTable()) == null || (anchor = slotTable2.anchor(0)) == null) {
                            anchor = movableContentStateReference2.getAnchor();
                        }
                        List<? extends Object> listCollectNodesFrom = ComposerKt.collectNodesFrom(slotTable, anchor);
                        if (!listCollectNodesFrom.isEmpty()) {
                            this.changeListWriter.copyNodesToNewAnchorLocation(listCollectNodesFrom, intRef);
                            if (kotlin.jvm.internal.p.a(movableContentStateReference.getSlotTable(), this.slotTable)) {
                                int iAnchorIndex2 = this.slotTable.anchorIndex(anchor2);
                                updateNodeCount(iAnchorIndex2, updatedNodeCount(iAnchorIndex2) + listCollectNodesFrom.size());
                            }
                        }
                        this.changeListWriter.copySlotTableToAnchorLocation(movableContentStateMovableContentStateResolve$runtime_release, this.parentContext, movableContentStateReference2, movableContentStateReference);
                        SlotReader slotReaderOpenReader2 = slotTable.openReader();
                        try {
                            SlotReader reader = getReader();
                            int[] iArr = this.nodeCountOverrides;
                            IntMap intMap2 = this.providerUpdates;
                            this.nodeCountOverrides = null;
                            this.providerUpdates = null;
                            try {
                                setReader$runtime_release(slotReaderOpenReader2);
                                int iAnchorIndex3 = slotTable.anchorIndex(anchor);
                                slotReaderOpenReader2.reposition(iAnchorIndex3);
                                this.changeListWriter.moveReaderToAbsolute(iAnchorIndex3);
                                ChangeList changeList7 = new ChangeList();
                                ComposerChangeListWriter composerChangeListWriter5 = this.changeListWriter;
                                ChangeList changeList8 = composerChangeListWriter5.getChangeList();
                                try {
                                    composerChangeListWriter5.setChangeList(changeList7);
                                    slotReader = slotReaderOpenReader2;
                                    try {
                                        ComposerChangeListWriter composerChangeListWriter6 = this.changeListWriter;
                                        i10 = i13;
                                        boolean implicitRootStart = composerChangeListWriter6.getImplicitRootStart();
                                        i11 = size;
                                        try {
                                            composerChangeListWriter6.setImplicitRootStart(false);
                                            try {
                                                intMap = intMap2;
                                                composerChangeListWriter2 = composerChangeListWriter4;
                                                changeList2 = changeList5;
                                                changeList3 = changeList8;
                                                composerChangeListWriter3 = composerChangeListWriter5;
                                                try {
                                                    recomposeMovableContent(movableContentStateReference2.getComposition(), movableContentStateReference.getComposition(), Integer.valueOf(slotReader.getCurrentGroup()), movableContentStateReference2.getInvalidations$runtime_release(), new ComposerImpl$insertMovableContentGuarded$1$1$2$1$1$1$1(this, movableContentStateReference));
                                                    try {
                                                        composerChangeListWriter6.setImplicitRootStart(implicitRootStart);
                                                        try {
                                                            composerChangeListWriter3.setChangeList(changeList3);
                                                            this.changeListWriter.includeOperationsIn(changeList7, intRef);
                                                            try {
                                                                setReader$runtime_release(reader);
                                                                this.nodeCountOverrides = iArr;
                                                                this.providerUpdates = intMap;
                                                                try {
                                                                    slotReader.close();
                                                                } catch (Throwable th) {
                                                                    th = th;
                                                                    composerChangeListWriter = composerChangeListWriter2;
                                                                    changeList = changeList2;
                                                                    composerChangeListWriter.setChangeList(changeList);
                                                                    throw th;
                                                                }
                                                            } catch (Throwable th2) {
                                                                th = th2;
                                                                slotReader.close();
                                                                throw th;
                                                            }
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            setReader$runtime_release(reader);
                                                            this.nodeCountOverrides = iArr;
                                                            this.providerUpdates = intMap;
                                                            throw th;
                                                        }
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        composerChangeListWriter3.setChangeList(changeList3);
                                                        throw th;
                                                    }
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    composerChangeListWriter6.setImplicitRootStart(implicitRootStart);
                                                    throw th;
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                intMap = intMap2;
                                                changeList3 = changeList8;
                                                composerChangeListWriter3 = composerChangeListWriter5;
                                            }
                                        } catch (Throwable th7) {
                                            th = th7;
                                            intMap = intMap2;
                                            composerChangeListWriter3 = composerChangeListWriter5;
                                            changeList3 = changeList8;
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        intMap = intMap2;
                                        composerChangeListWriter3 = composerChangeListWriter5;
                                        changeList3 = changeList8;
                                        composerChangeListWriter3.setChangeList(changeList3);
                                        throw th;
                                    }
                                } catch (Throwable th9) {
                                    th = th9;
                                    intMap = intMap2;
                                    slotReader = slotReaderOpenReader2;
                                }
                            } catch (Throwable th10) {
                                th = th10;
                                intMap = intMap2;
                                slotReader = slotReaderOpenReader2;
                            }
                        } catch (Throwable th11) {
                            th = th11;
                            slotReader = slotReaderOpenReader2;
                        }
                    }
                    this.changeListWriter.skipToEndOfCurrentGroup();
                    i13 = i10 + 1;
                    size = i11;
                    composerChangeListWriter4 = composerChangeListWriter2;
                    changeList5 = changeList2;
                    i12 = 0;
                } catch (Throwable th12) {
                    th = th12;
                    composerChangeListWriter2 = composerChangeListWriter4;
                    changeList2 = changeList5;
                }
            }
            ComposerChangeListWriter composerChangeListWriter7 = composerChangeListWriter4;
            ChangeList changeList9 = changeList5;
            this.changeListWriter.endMovableContentPlacement();
            this.changeListWriter.moveReaderToAbsolute(0);
            composerChangeListWriter7.setChangeList(changeList9);
        } catch (Throwable th13) {
            th = th13;
            composerChangeListWriter = composerChangeListWriter4;
            changeList = changeList5;
        }
    }

    private final int insertedGroupVirtualIndex(int index) {
        return (-2) - index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invokeMovableContentLambda(MovableContent<Object> content, PersistentCompositionLocalMap locals, Object parameter, boolean force) {
        startMovableGroup(MovableContentKt.movableContentKey, content);
        updateSlot(parameter);
        int compoundKeyHash = getCompoundKeyHash();
        try {
            this.compoundKeyHash = MovableContentKt.movableContentKey;
            boolean z = false;
            if (getInserting()) {
                SlotWriter.markGroup$default(this.writer, 0, 1, null);
            }
            if (!getInserting() && !kotlin.jvm.internal.p.a(this.reader.getGroupAux(), locals)) {
                z = true;
            }
            if (z) {
                recordProviderUpdate(locals);
            }
            m2949startBaiHCIY(202, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2962getGroupULZAiWs(), locals);
            this.providerCache = null;
            if (!getInserting() || force) {
                boolean z5 = this.providersInvalid;
                this.providersInvalid = z;
                ActualJvm_jvmKt.invokeComposable(this, ComposableLambdaKt.composableLambdaInstance(316014703, true, new AnonymousClass1(content, parameter)));
                this.providersInvalid = z5;
            } else {
                this.writerHasAProvider = true;
                SlotWriter slotWriter = this.writer;
                this.parentContext.insertMovableContent$runtime_release(new MovableContentStateReference(content, parameter, getComposition(), this.insertTable, slotWriter.anchor(slotWriter.parent(slotWriter.getParent())), z.f19728i, currentCompositionLocalScope()));
            }
            endGroup();
            this.providerCache = null;
            this.compoundKeyHash = compoundKeyHash;
            endMovableGroup();
        } catch (Throwable th) {
            endGroup();
            this.providerCache = null;
            this.compoundKeyHash = compoundKeyHash;
            endMovableGroup();
            throw th;
        }
    }

    private final Object nodeAt(SlotReader slotReader, int i10) {
        return slotReader.node(i10);
    }

    private final int nodeIndexOf(int groupLocation, int group, int recomposeGroup, int recomposeIndex) {
        int iParent = this.reader.parent(group);
        while (iParent != recomposeGroup && !this.reader.isNode(iParent)) {
            iParent = this.reader.parent(iParent);
        }
        if (this.reader.isNode(iParent)) {
            recomposeIndex = 0;
        }
        if (iParent == group) {
            return recomposeIndex;
        }
        int iUpdatedNodeCount = (updatedNodeCount(iParent) - this.reader.nodeCount(group)) + recomposeIndex;
        loop1: while (recomposeIndex < iUpdatedNodeCount && iParent != groupLocation) {
            iParent++;
            while (iParent < groupLocation) {
                int iGroupSize = this.reader.groupSize(iParent) + iParent;
                if (groupLocation >= iGroupSize) {
                    recomposeIndex += this.reader.isNode(iParent) ? 1 : updatedNodeCount(iParent);
                    iParent = iGroupSize;
                }
            }
            break loop1;
        }
        return recomposeIndex;
    }

    private final int rGroupIndexOf(int group) {
        int iParent = this.reader.parent(group) + 1;
        int i10 = 0;
        while (iParent < group) {
            if (!this.reader.hasObjectKey(iParent)) {
                i10++;
            }
            iParent += this.reader.groupSize(iParent);
        }
        return i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b A[Catch: all -> 0x0022, TRY_LEAVE, TryCatch #0 {all -> 0x0022, blocks: (B:3:0x0005, B:5:0x0010, B:7:0x001e, B:11:0x0028, B:10:0x0024, B:14:0x002f, B:16:0x0035, B:18:0x003b), top: B:23:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final <R> R recomposeMovableContent(androidx.compose.runtime.ControlledComposition r7, androidx.compose.runtime.ControlledComposition r8, java.lang.Integer r9, java.util.List<? extends x6.x> r10, r7.a<? extends R> r11) {
        /*
            r6 = this;
            boolean r0 = r6.isComposing
            int r1 = r6.nodeIndex
            r2 = 1
            r6.isComposing = r2     // Catch: java.lang.Throwable -> L22
            r2 = 0
            r6.nodeIndex = r2     // Catch: java.lang.Throwable -> L22
            int r3 = r10.size()     // Catch: java.lang.Throwable -> L22
        Le:
            if (r2 >= r3) goto L2b
            java.lang.Object r4 = r10.get(r2)     // Catch: java.lang.Throwable -> L22
            x6.x r4 = (x6.x) r4     // Catch: java.lang.Throwable -> L22
            java.lang.Object r5 = r4.f22608i     // Catch: java.lang.Throwable -> L22
            androidx.compose.runtime.RecomposeScopeImpl r5 = (androidx.compose.runtime.RecomposeScopeImpl) r5     // Catch: java.lang.Throwable -> L22
            java.lang.Object r4 = r4.f22609l     // Catch: java.lang.Throwable -> L22
            if (r4 == 0) goto L24
            r6.tryImminentInvalidation$runtime_release(r5, r4)     // Catch: java.lang.Throwable -> L22
            goto L28
        L22:
            r7 = move-exception
            goto L44
        L24:
            r4 = 0
            r6.tryImminentInvalidation$runtime_release(r5, r4)     // Catch: java.lang.Throwable -> L22
        L28:
            int r2 = r2 + 1
            goto Le
        L2b:
            if (r7 == 0) goto L3b
            if (r9 == 0) goto L34
            int r9 = r9.intValue()     // Catch: java.lang.Throwable -> L22
            goto L35
        L34:
            r9 = -1
        L35:
            java.lang.Object r7 = r7.delegateInvalidations(r8, r9, r11)     // Catch: java.lang.Throwable -> L22
            if (r7 != 0) goto L3f
        L3b:
            java.lang.Object r7 = r11.invoke()     // Catch: java.lang.Throwable -> L22
        L3f:
            r6.isComposing = r0
            r6.nodeIndex = r1
            return r7
        L44:
            r6.isComposing = r0
            r6.nodeIndex = r1
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.recomposeMovableContent(androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.ControlledComposition, java.lang.Integer, java.util.List, r7.a):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object recomposeMovableContent$default(ComposerImpl composerImpl, ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer num, List list, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            controlledComposition = null;
        }
        if ((i10 & 2) != 0) {
            controlledComposition2 = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            list = z.f19728i;
        }
        return composerImpl.recomposeMovableContent(controlledComposition, controlledComposition2, num, list, aVar);
    }

    private final void recomposeToGroupEnd() {
        boolean z = this.isComposing;
        this.isComposing = true;
        int parent = this.reader.getParent();
        int iGroupSize = this.reader.groupSize(parent) + parent;
        int i10 = this.nodeIndex;
        int compoundKeyHash = getCompoundKeyHash();
        int i11 = this.groupNodeCount;
        int i12 = this.rGroupIndex;
        Invalidation invalidationFirstInRange = ComposerKt.firstInRange(this.invalidations, this.reader.getCurrentGroup(), iGroupSize);
        boolean z5 = false;
        int i13 = parent;
        while (invalidationFirstInRange != null) {
            int location = invalidationFirstInRange.getLocation();
            ComposerKt.removeLocation(this.invalidations, location);
            if (invalidationFirstInRange.isInvalid()) {
                this.reader.reposition(location);
                int currentGroup = this.reader.getCurrentGroup();
                recordUpsAndDowns(i13, currentGroup, parent);
                this.nodeIndex = nodeIndexOf(location, currentGroup, parent, i10);
                this.rGroupIndex = rGroupIndexOf(currentGroup);
                int iParent = this.reader.parent(currentGroup);
                this.compoundKeyHash = compoundKeyOf(iParent, rGroupIndexOf(iParent), parent, compoundKeyHash);
                this.providerCache = null;
                invalidationFirstInRange.getScope().compose(this);
                this.providerCache = null;
                this.reader.restoreParent(parent);
                i13 = currentGroup;
                z5 = true;
            } else {
                this.invalidateStack.push(invalidationFirstInRange.getScope());
                invalidationFirstInRange.getScope().rereadTrackedInstances();
                this.invalidateStack.pop();
            }
            invalidationFirstInRange = ComposerKt.firstInRange(this.invalidations, this.reader.getCurrentGroup(), iGroupSize);
        }
        if (z5) {
            recordUpsAndDowns(i13, parent, parent);
            this.reader.skipToGroupEnd();
            int iUpdatedNodeCount = updatedNodeCount(parent);
            this.nodeIndex = i10 + iUpdatedNodeCount;
            this.groupNodeCount = i11 + iUpdatedNodeCount;
            this.rGroupIndex = i12;
        } else {
            skipReaderToGroupEnd();
        }
        this.compoundKeyHash = compoundKeyHash;
        this.isComposing = z;
    }

    private final void recordDelete() {
        reportFreeMovableContent(this.reader.getCurrentGroup());
        this.changeListWriter.removeCurrentGroup();
    }

    private final void recordInsert(Anchor anchor) {
        if (this.insertFixups.isEmpty()) {
            this.changeListWriter.insertSlots(anchor, this.insertTable);
        } else {
            this.changeListWriter.insertSlots(anchor, this.insertTable, this.insertFixups);
            this.insertFixups = new FixupList();
        }
    }

    private final void recordProviderUpdate(PersistentCompositionLocalMap providers) {
        IntMap<PersistentCompositionLocalMap> intMap = this.providerUpdates;
        if (intMap == null) {
            intMap = new IntMap<>(0, 1, null);
            this.providerUpdates = intMap;
        }
        intMap.set(this.reader.getCurrentGroup(), providers);
    }

    private final void recordUpsAndDowns(int oldGroup, int newGroup, int commonRoot) {
        SlotReader slotReader = this.reader;
        int iNearestCommonRootOf = ComposerKt.nearestCommonRootOf(slotReader, oldGroup, newGroup, commonRoot);
        while (oldGroup > 0 && oldGroup != iNearestCommonRootOf) {
            if (slotReader.isNode(oldGroup)) {
                this.changeListWriter.moveUp();
            }
            oldGroup = slotReader.parent(oldGroup);
        }
        doRecordDownsFor(newGroup, iNearestCommonRootOf);
    }

    private final Anchor rememberObserverAnchor() {
        int i10;
        int i11;
        if (getInserting()) {
            if (!ComposerKt.isAfterFirstChild(this.writer)) {
                return null;
            }
            int currentGroup = this.writer.getCurrentGroup() - 1;
            int iParent = this.writer.parent(currentGroup);
            while (true) {
                int i12 = iParent;
                i11 = currentGroup;
                currentGroup = i12;
                if (currentGroup == this.writer.getParent() || currentGroup < 0) {
                    break;
                }
                iParent = this.writer.parent(currentGroup);
            }
            return this.writer.anchor(i11);
        }
        if (!ComposerKt.isAfterFirstChild(this.reader)) {
            return null;
        }
        int currentGroup2 = this.reader.getCurrentGroup() - 1;
        int iParent2 = this.reader.parent(currentGroup2);
        while (true) {
            int i13 = iParent2;
            i10 = currentGroup2;
            currentGroup2 = i13;
            if (currentGroup2 == this.reader.getParent() || currentGroup2 < 0) {
                break;
            }
            iParent2 = this.reader.parent(currentGroup2);
        }
        return this.reader.anchor(i10);
    }

    private final void reportAllMovableContent() {
        if (this.slotTable.containsMark()) {
            ChangeList changeList = new ChangeList();
            this.deferredChanges = changeList;
            SlotReader slotReaderOpenReader = this.slotTable.openReader();
            try {
                this.reader = slotReaderOpenReader;
                ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
                ChangeList changeList2 = composerChangeListWriter.getChangeList();
                try {
                    composerChangeListWriter.setChangeList(changeList);
                    reportFreeMovableContent(0);
                    this.changeListWriter.releaseMovableContent();
                } finally {
                    composerChangeListWriter.setChangeList(changeList2);
                }
            } finally {
                slotReaderOpenReader.close();
            }
        }
    }

    private final void reportFreeMovableContent(int groupBeingRemoved) {
        reportFreeMovableContent$reportGroup(this, groupBeingRemoved, false, 0);
        this.changeListWriter.endNodeMovement();
    }

    private static final int reportFreeMovableContent$reportGroup(ComposerImpl composerImpl, int i10, boolean z, int i11) {
        SlotReader slotReader = composerImpl.reader;
        if (!slotReader.hasMark(i10)) {
            if (!slotReader.containsMark(i10)) {
                if (slotReader.isNode(i10)) {
                    return 1;
                }
                return slotReader.nodeCount(i10);
            }
            int iGroupSize = slotReader.groupSize(i10) + i10;
            int iReportFreeMovableContent$reportGroup = 0;
            for (int iGroupSize2 = i10 + 1; iGroupSize2 < iGroupSize; iGroupSize2 += slotReader.groupSize(iGroupSize2)) {
                boolean zIsNode = slotReader.isNode(iGroupSize2);
                if (zIsNode) {
                    composerImpl.changeListWriter.endNodeMovement();
                    composerImpl.changeListWriter.moveDown(slotReader.node(iGroupSize2));
                }
                iReportFreeMovableContent$reportGroup += reportFreeMovableContent$reportGroup(composerImpl, iGroupSize2, zIsNode || z, zIsNode ? 0 : i11 + iReportFreeMovableContent$reportGroup);
                if (zIsNode) {
                    composerImpl.changeListWriter.endNodeMovement();
                    composerImpl.changeListWriter.moveUp();
                }
            }
            if (slotReader.isNode(i10)) {
                return 1;
            }
            return iReportFreeMovableContent$reportGroup;
        }
        int iGroupKey = slotReader.groupKey(i10);
        Object objGroupObjectKey = slotReader.groupObjectKey(i10);
        if (iGroupKey != 126665345 || !(objGroupObjectKey instanceof MovableContent)) {
            if (iGroupKey != 206 || !kotlin.jvm.internal.p.a(objGroupObjectKey, ComposerKt.getReference())) {
                if (slotReader.isNode(i10)) {
                    return 1;
                }
                return slotReader.nodeCount(i10);
            }
            Object objGroupGet = slotReader.groupGet(i10, 0);
            CompositionContextHolder compositionContextHolder = objGroupGet instanceof CompositionContextHolder ? (CompositionContextHolder) objGroupGet : null;
            if (compositionContextHolder != null) {
                for (ComposerImpl composerImpl2 : compositionContextHolder.getRef().getComposers()) {
                    composerImpl2.reportAllMovableContent();
                    composerImpl.parentContext.reportRemovedComposition$runtime_release(composerImpl2.getComposition());
                }
            }
            return slotReader.nodeCount(i10);
        }
        MovableContent movableContent = (MovableContent) objGroupObjectKey;
        Object objGroupGet2 = slotReader.groupGet(i10, 0);
        Anchor anchor = slotReader.anchor(i10);
        List listFilterToRange = ComposerKt.filterToRange(composerImpl.invalidations, i10, slotReader.groupSize(i10) + i10);
        ArrayList arrayList = new ArrayList(listFilterToRange.size());
        int size = listFilterToRange.size();
        for (int i12 = 0; i12 < size; i12++) {
            Invalidation invalidation = (Invalidation) listFilterToRange.get(i12);
            arrayList.add(new x(invalidation.getScope(), invalidation.getInstances()));
        }
        MovableContentStateReference movableContentStateReference = new MovableContentStateReference(movableContent, objGroupGet2, composerImpl.getComposition(), composerImpl.slotTable, anchor, arrayList, composerImpl.currentCompositionLocalScope(i10));
        composerImpl.parentContext.deletedMovableContent$runtime_release(movableContentStateReference);
        composerImpl.changeListWriter.recordSlotEditing();
        composerImpl.changeListWriter.releaseMovableGroupAtCurrent(composerImpl.getComposition(), composerImpl.parentContext, movableContentStateReference);
        if (!z) {
            return slotReader.nodeCount(i10);
        }
        composerImpl.changeListWriter.endNodeMovementAndDeleteNode(i11, i10);
        return 0;
    }

    private final void skipGroup() {
        this.groupNodeCount = this.reader.skipGroup() + this.groupNodeCount;
    }

    private final void skipReaderToGroupEnd() {
        this.groupNodeCount = this.reader.getParentNodes();
        this.reader.skipToGroupEnd();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d5  */
    /* JADX INFO: renamed from: start-BaiHCIY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void m2949startBaiHCIY(int r11, java.lang.Object r12, int r13, java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.m2949startBaiHCIY(int, java.lang.Object, int, java.lang.Object):void");
    }

    private final void startGroup(int key) {
        m2949startBaiHCIY(key, null, GroupKind.INSTANCE.m2962getGroupULZAiWs(), null);
    }

    private final void startReaderGroup(boolean isNode, Object data) {
        if (isNode) {
            this.reader.startNode();
            return;
        }
        if (data != null && this.reader.getGroupAux() != data) {
            this.changeListWriter.updateAuxData(data);
        }
        this.reader.startGroup();
    }

    private final void startRoot() {
        this.rGroupIndex = 0;
        this.reader = this.slotTable.openReader();
        startGroup(100);
        this.parentContext.startComposing$runtime_release();
        this.parentProvider = this.parentContext.getCompositionLocalScope$runtime_release();
        this.providersInvalidStack.push(ComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = changed(this.parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = this.parentContext.getCollectingParameterInformation();
        }
        if (!this.sourceMarkersEnabled) {
            this.sourceMarkersEnabled = this.parentContext.getCollectingSourceInformation();
        }
        Set<CompositionData> set = (Set) CompositionLocalMapKt.read(this.parentProvider, InspectionTablesKt.getLocalInspectionTables());
        if (set != null) {
            set.add(this.slotTable);
            this.parentContext.recordInspectionTable$runtime_release(set);
        }
        startGroup(this.parentContext.getCompoundHashKey());
    }

    private final void updateCompoundKeyWhenWeEnterGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                this.compoundKeyHash = Integer.rotateLeft(((Enum) dataKey).ordinal() ^ Integer.rotateLeft(getCompoundKeyHash(), 3), 3);
                return;
            } else {
                this.compoundKeyHash = Integer.rotateLeft(dataKey.hashCode() ^ Integer.rotateLeft(getCompoundKeyHash(), 3), 3);
                return;
            }
        }
        if (data == null || groupKey != 207 || data.equals(Composer.INSTANCE.getEmpty())) {
            this.compoundKeyHash = Integer.rotateLeft(groupKey ^ Integer.rotateLeft(getCompoundKeyHash(), 3), 3) ^ rGroupIndex;
        } else {
            this.compoundKeyHash = Integer.rotateLeft(data.hashCode() ^ Integer.rotateLeft(getCompoundKeyHash(), 3), 3) ^ rGroupIndex;
        }
    }

    private final void updateCompoundKeyWhenWeEnterGroupKeyHash(int keyHash, int rGroupIndex) {
        this.compoundKeyHash = Integer.rotateLeft(keyHash ^ Integer.rotateLeft(getCompoundKeyHash(), 3), 3) ^ rGroupIndex;
    }

    private final void updateCompoundKeyWhenWeExitGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                this.compoundKeyHash = Integer.rotateRight(((Enum) dataKey).ordinal() ^ Integer.rotateRight(getCompoundKeyHash(), 3), 3);
                return;
            } else {
                this.compoundKeyHash = Integer.rotateRight(dataKey.hashCode() ^ Integer.rotateRight(getCompoundKeyHash(), 3), 3);
                return;
            }
        }
        if (data == null || groupKey != 207 || data.equals(Composer.INSTANCE.getEmpty())) {
            this.compoundKeyHash = Integer.rotateRight(groupKey ^ Integer.rotateRight(rGroupIndex ^ getCompoundKeyHash(), 3), 3);
        } else {
            this.compoundKeyHash = Integer.rotateRight(data.hashCode() ^ Integer.rotateRight(rGroupIndex ^ getCompoundKeyHash(), 3), 3);
        }
    }

    private final void updateCompoundKeyWhenWeExitGroupKeyHash(int groupKey, int rGroupIndex) {
        this.compoundKeyHash = Integer.rotateRight(groupKey ^ Integer.rotateRight(rGroupIndex ^ getCompoundKeyHash(), 3), 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x009a, code lost:
    
        if (((r3 & ((~r3) << r18)) & (-9187201950435737472L)) == 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x009c, code lost:
    
        r2 = r5.a(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a4, code lost:
    
        if (r5.f21868f != 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b6, code lost:
    
        if (((r5.f21863a[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b9, code lost:
    
        r2 = r5.f21866d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bb, code lost:
    
        if (r2 <= 8) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00d2, code lost:
    
        if (java.lang.Long.compare((((long) r5.f21867e) * 32) ^ Long.MIN_VALUE, (((long) r2) * 25) ^ Long.MIN_VALUE) > 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d4, code lost:
    
        r5.e(t.i0.b(r5.f21866d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00de, code lost:
    
        r5.e(t.i0.b(r5.f21866d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e7, code lost:
    
        r2 = r5.a(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00eb, code lost:
    
        r5.f21867e++;
        r3 = r5.f21868f;
        r4 = r5.f21863a;
        r6 = r2 >> 3;
        r13 = r4[r6];
        r7 = (r2 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0105, code lost:
    
        if (((r13 >> r7) & 255) != 128) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0108, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x010a, code lost:
    
        r5.f21868f = r3 - r16;
        r4[r6] = ((~(255 << r7)) & r13) | (r11 << r7);
        r3 = r5.f21866d;
        r6 = ((r2 - 7) & r3) + (r3 & 7);
        r3 = r6 >> 3;
        r6 = (r6 & 7) << 3;
        r4[r3] = (r4[r3] & (~(255 << r6))) | (r11 << r6);
        r15 = ~r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateNodeCount(int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.updateNodeCount(int, int):void");
    }

    private final void updateNodeCountOverrides(int group, int newCount) {
        int iUpdatedNodeCount = updatedNodeCount(group);
        if (iUpdatedNodeCount != newCount) {
            int i10 = newCount - iUpdatedNodeCount;
            int size = this.pendingStack.getSize() - 1;
            while (group != -1) {
                int iUpdatedNodeCount2 = updatedNodeCount(group) + i10;
                updateNodeCount(group, iUpdatedNodeCount2);
                int i11 = size;
                while (true) {
                    if (-1 < i11) {
                        Pending pendingPeek = this.pendingStack.peek(i11);
                        if (pendingPeek != null && pendingPeek.updateNodeCount(group, iUpdatedNodeCount2)) {
                            size = i11 - 1;
                            break;
                        }
                        i11--;
                    } else {
                        break;
                    }
                }
                if (group < 0) {
                    group = this.reader.getParent();
                } else if (this.reader.isNode(group)) {
                    return;
                } else {
                    group = this.reader.parent(group);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    private final PersistentCompositionLocalMap updateProviderMapGroup(PersistentCompositionLocalMap parentScope, PersistentCompositionLocalMap currentProviders) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builderBuilder2 = parentScope.builder2();
        builderBuilder2.putAll(currentProviders);
        ?? Build2 = builderBuilder2.build2();
        startGroup(ComposerKt.providerMapsKey, ComposerKt.getProviderMaps());
        updateSlot(Build2);
        updateSlot(currentProviders);
        endGroup();
        return Build2;
    }

    private final void updateSlot(Object value) {
        nextSlot();
        updateValue(value);
    }

    private final int updatedNodeCount(int group) {
        int i10;
        if (group >= 0) {
            int[] iArr = this.nodeCountOverrides;
            return (iArr == null || (i10 = iArr[group]) < 0) ? this.reader.nodeCount(group) : i10;
        }
        t tVar = this.nodeCountVirtualOverrides;
        if (tVar == null || tVar.b(group) < 0) {
            return 0;
        }
        return tVar.c(group);
    }

    private final void validateNodeExpected() {
        if (!this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.nodeExpected = false;
    }

    private final void validateNodeNotExpected() {
        if (this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
        }
    }

    private final <R> R withReader(SlotReader reader, r7.a<? extends R> block) {
        SlotReader reader2 = getReader();
        int[] iArr = this.nodeCountOverrides;
        IntMap intMap = this.providerUpdates;
        this.nodeCountOverrides = null;
        this.providerUpdates = null;
        try {
            setReader$runtime_release(reader);
            return (R) block.invoke();
        } finally {
            setReader$runtime_release(reader2);
            this.nodeCountOverrides = iArr;
            this.providerUpdates = intMap;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public <V, T> void apply(V value, p<? super T, ? super V, t0> block) {
        if (getInserting()) {
            this.insertFixups.updateNode(value, block);
        } else {
            this.changeListWriter.updateNode(value, block);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionContext buildContext() {
        startGroup(ComposerKt.referenceKey, ComposerKt.getReference());
        if (getInserting()) {
            SlotWriter.markGroup$default(this.writer, 0, 1, null);
        }
        Object objNextSlot = nextSlot();
        CompositionContextHolder compositionContextHolder = objNextSlot instanceof CompositionContextHolder ? (CompositionContextHolder) objNextSlot : null;
        if (compositionContextHolder == null) {
            int compoundKeyHash = getCompoundKeyHash();
            boolean z = this.forceRecomposeScopes;
            boolean z5 = this.sourceMarkersEnabled;
            ControlledComposition composition = getComposition();
            CompositionImpl compositionImpl = composition instanceof CompositionImpl ? (CompositionImpl) composition : null;
            compositionContextHolder = new CompositionContextHolder(new CompositionContextImpl(compoundKeyHash, z, z5, compositionImpl != null ? compositionImpl.getObserverHolder() : null));
            updateValue(compositionContextHolder);
        }
        compositionContextHolder.getRef().updateCompositionLocalScope(currentCompositionLocalScope());
        endGroup();
        return compositionContextHolder.getRef();
    }

    @ComposeCompilerApi
    public final <T> T cache(boolean invalid, r7.a<? extends T> block) {
        T t2 = (T) nextSlotForCache();
        if (t2 != Composer.INSTANCE.getEmpty() && !invalid) {
            return t2;
        }
        T t10 = (T) block.invoke();
        updateCachedValue(t10);
        return t10;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(Object value) {
        if (kotlin.jvm.internal.p.a(nextSlot(), value)) {
            return false;
        }
        updateValue(value);
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changedInstance(Object value) {
        if (nextSlot() == value) {
            return false;
        }
        updateValue(value);
        return true;
    }

    public final void changesApplied$runtime_release() {
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void collectParameterInformation() {
        this.forceRecomposeScopes = true;
        this.sourceMarkersEnabled = true;
        this.slotTable.collectSourceInformation();
        this.insertTable.collectSourceInformation();
        this.writer.updateToTableMaps();
    }

    public final void composeContent$runtime_release(ScopeMap<RecomposeScopeImpl, Object> invalidationsRequested, p<? super Composer, ? super Integer, t0> content) {
        if (!this.changes.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        doCompose(invalidationsRequested, content);
    }

    @Override // androidx.compose.runtime.Composer
    public <T> T consume(CompositionLocal<T> key) {
        return (T) CompositionLocalMapKt.read(currentCompositionLocalScope(), key);
    }

    @Override // androidx.compose.runtime.Composer
    public <T> void createNode(r7.a<? extends T> factory) {
        validateNodeExpected();
        if (!getInserting()) {
            ComposerKt.composeImmediateRuntimeError("createNode() can only be called when inserting");
        }
        int iPeek = this.parentStateStack.peek();
        SlotWriter slotWriter = this.writer;
        Anchor anchor = slotWriter.anchor(slotWriter.getParent());
        this.groupNodeCount++;
        this.insertFixups.createAndInsertNode(factory, iPeek, anchor);
    }

    public final void deactivate$runtime_release() {
        this.invalidateStack.clear();
        this.invalidations.clear();
        this.changes.clear();
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void deactivateToEndGroup(boolean changed) {
        if (!(this.groupNodeCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling dactivateToEndGroup");
        }
        if (getInserting()) {
            return;
        }
        if (!changed) {
            skipReaderToGroupEnd();
            return;
        }
        int currentGroup = this.reader.getCurrentGroup();
        int currentEnd = this.reader.getCurrentEnd();
        this.changeListWriter.deactivateCurrentGroup();
        ComposerKt.removeRange(this.invalidations, currentGroup, currentEnd);
        this.reader.skipToGroupEnd();
    }

    @Override // androidx.compose.runtime.Composer
    public void disableReusing() {
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void disableSourceInformation() {
        this.sourceMarkersEnabled = false;
    }

    public final void dispose$runtime_release() {
        Trace trace = Trace.INSTANCE;
        Object objBeginSection = trace.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.unregisterComposer$runtime_release(this);
            deactivate$runtime_release();
            getApplier().clear();
            this.isDisposed = true;
            trace.endSection(objBeginSection);
        } catch (Throwable th) {
            Trace.INSTANCE.endSection(objBeginSection);
            throw th;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void enableReusing() {
        this.reusing = this.reusingGroup >= 0;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endDefaults() {
        endGroup();
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release == null || !currentRecomposeScope$runtime_release.getUsed()) {
            return;
        }
        currentRecomposeScope$runtime_release.setDefaultsInScope(true);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endMovableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void endNode() {
        end(true);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProvider() {
        endGroup();
        endGroup();
        this.providersInvalid = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void endProviders() {
        endGroup();
        endGroup();
        this.providersInvalid = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public ScopeUpdateScope endRestartGroup() {
        Anchor anchor;
        r7.l<Composition, t0> lVarEnd;
        RecomposeScopeImpl recomposeScopeImpl = null;
        RecomposeScopeImpl recomposeScopeImplPop = this.invalidateStack.isNotEmpty() ? this.invalidateStack.pop() : null;
        if (recomposeScopeImplPop != null) {
            recomposeScopeImplPop.setRequiresRecompose(false);
        }
        if (recomposeScopeImplPop != null && (lVarEnd = recomposeScopeImplPop.end(this.compositionToken)) != null) {
            this.changeListWriter.endCompositionScope(lVarEnd, getComposition());
        }
        if (recomposeScopeImplPop != null && !recomposeScopeImplPop.getSkipped$runtime_release() && (recomposeScopeImplPop.getUsed() || this.forceRecomposeScopes)) {
            if (recomposeScopeImplPop.getAnchor() == null) {
                if (getInserting()) {
                    SlotWriter slotWriter = this.writer;
                    anchor = slotWriter.anchor(slotWriter.getParent());
                } else {
                    SlotReader slotReader = this.reader;
                    anchor = slotReader.anchor(slotReader.getParent());
                }
                recomposeScopeImplPop.setAnchor(anchor);
            }
            recomposeScopeImplPop.setDefaultsInvalid(false);
            recomposeScopeImpl = recomposeScopeImplPop;
        }
        end(false);
        return recomposeScopeImpl;
    }

    @Override // androidx.compose.runtime.Composer
    public void endReusableGroup() {
        if (this.reusing && this.reader.getParent() == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    public final void endReuseFromRoot() {
        if (!(!this.isComposing && this.reusingGroup == 100)) {
            PreconditionsKt.throwIllegalArgumentException("Cannot disable reuse from root if it was caused by other groups");
        }
        this.reusingGroup = -1;
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void endToMarker(int marker) {
        if (marker < 0) {
            int i10 = -marker;
            SlotWriter slotWriter = this.writer;
            while (true) {
                int parent = slotWriter.getParent();
                if (parent <= i10) {
                    return;
                } else {
                    end(slotWriter.isNode(parent));
                }
            }
        } else {
            if (getInserting()) {
                SlotWriter slotWriter2 = this.writer;
                while (getInserting()) {
                    end(slotWriter2.isNode(slotWriter2.getParent()));
                }
            }
            SlotReader slotReader = this.reader;
            while (true) {
                int parent2 = slotReader.getParent();
                if (parent2 <= marker) {
                    return;
                } else {
                    end(slotReader.isNode(parent2));
                }
            }
        }
    }

    public final boolean forceRecomposeScopes$runtime_release() {
        if (this.forceRecomposeScopes) {
            return false;
        }
        this.forceRecomposeScopes = true;
        this.forciblyRecompose = true;
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public Applier<?> getApplier() {
        return this.applier;
    }

    @Override // androidx.compose.runtime.Composer
    public d7.j getApplyCoroutineContext() {
        return this.parentContext.getEffectCoroutineContext();
    }

    public final boolean getAreChildrenComposing$runtime_release() {
        return this.childrenComposing > 0;
    }

    public final int getChangeCount$runtime_release() {
        return this.changes.getSize();
    }

    @Override // androidx.compose.runtime.Composer
    public ControlledComposition getComposition() {
        return this.composition;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionData getCompositionData() {
        return this.slotTable;
    }

    @Override // androidx.compose.runtime.Composer
    public int getCompoundKeyHash() {
        return this.compoundKeyHash;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionLocalMap getCurrentCompositionLocalMap() {
        return currentCompositionLocalScope();
    }

    @Override // androidx.compose.runtime.Composer
    public int getCurrentMarker() {
        return getInserting() ? -this.writer.getParent() : this.reader.getParent();
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime_release() {
        Stack<RecomposeScopeImpl> stack = this.invalidateStack;
        if (this.childrenComposing == 0 && stack.isNotEmpty()) {
            return stack.peek();
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getDefaultsInvalid() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        return !getSkipping() || this.providersInvalid || ((currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release()) != null && currentRecomposeScope$runtime_release.getDefaultsInvalid());
    }

    /* JADX INFO: renamed from: getDeferredChanges$runtime_release, reason: from getter */
    public final ChangeList getDeferredChanges() {
        return this.deferredChanges;
    }

    public final boolean getHasInvalidations() {
        return !this.invalidations.isEmpty();
    }

    public final boolean getHasPendingChanges$runtime_release() {
        return this.changes.isNotEmpty();
    }

    /* JADX INFO: renamed from: getInsertTable$runtime_release, reason: from getter */
    public final SlotTable getInsertTable() {
        return this.insertTable;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getInserting() {
        return this.inserting;
    }

    /* JADX INFO: renamed from: getReader$runtime_release, reason: from getter */
    public final SlotReader getReader() {
        return this.reader;
    }

    @Override // androidx.compose.runtime.Composer
    public RecomposeScope getRecomposeScope() {
        return getCurrentRecomposeScope$runtime_release();
    }

    @Override // androidx.compose.runtime.Composer
    public Object getRecomposeScopeIdentity() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            return currentRecomposeScope$runtime_release.getAnchor();
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getSkipping() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        return (getInserting() || this.reusing || this.providersInvalid || (currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release()) == null || currentRecomposeScope$runtime_release.getRequiresRecompose() || this.forciblyRecompose) ? false : true;
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContent(MovableContent<?> value, Object parameter) {
        invokeMovableContentLambda(value, currentCompositionLocalScope(), parameter, false);
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContentReferences(List<x> references) {
        try {
            insertMovableContentGuarded(references);
            cleanUpCompose();
        } catch (Throwable th) {
            abortRoot();
            throw th;
        }
    }

    /* JADX INFO: renamed from: isComposing$runtime_release, reason: from getter */
    public final boolean getIsComposing() {
        return this.isComposing;
    }

    /* JADX INFO: renamed from: isDisposed$runtime_release, reason: from getter */
    public final boolean getIsDisposed() {
        return this.isDisposed;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Object joinKey(Object left, Object right) {
        Object key = ComposerKt.getKey(this.reader.getGroupObjectKey(), left, right);
        return key == null ? new JoinedKey(left, right) : key;
    }

    public final Object nextSlot() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object next = this.reader.next();
        return (!this.reusing || (next instanceof ReusableRememberObserver)) ? next : Composer.INSTANCE.getEmpty();
    }

    public final Object nextSlotForCache() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object next = this.reader.next();
        return (!this.reusing || (next instanceof ReusableRememberObserver)) ? next instanceof RememberObserverHolder ? ((RememberObserverHolder) next).getWrapped() : next : Composer.INSTANCE.getEmpty();
    }

    public final int parentKey$runtime_release() {
        if (getInserting()) {
            SlotWriter slotWriter = this.writer;
            return slotWriter.groupKey(slotWriter.getParent());
        }
        SlotReader slotReader = this.reader;
        return slotReader.groupKey(slotReader.getParent());
    }

    public final void prepareCompose$runtime_release(r7.a<t0> block) {
        if (this.isComposing) {
            ComposerKt.composeImmediateRuntimeError("Preparing a composition while composing is not supported");
        }
        this.isComposing = true;
        try {
            block.invoke();
        } finally {
            this.isComposing = false;
        }
    }

    public final boolean recompose$runtime_release(ScopeMap<RecomposeScopeImpl, Object> invalidationsRequested) {
        if (!this.changes.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        if (invalidationsRequested.getSize() <= 0 && this.invalidations.isEmpty() && !this.forciblyRecompose) {
            return false;
        }
        doCompose(invalidationsRequested, null);
        return this.changes.isNotEmpty();
    }

    @Override // androidx.compose.runtime.Composer
    public void recordSideEffect(r7.a<t0> effect) {
        this.changeListWriter.sideEffect(effect);
    }

    @Override // androidx.compose.runtime.Composer
    public void recordUsed(RecomposeScope scope) {
        RecomposeScopeImpl recomposeScopeImpl = scope instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) scope : null;
        if (recomposeScopeImpl == null) {
            return;
        }
        recomposeScopeImpl.setUsed(true);
    }

    @Override // androidx.compose.runtime.Composer
    public Object rememberedValue() {
        return nextSlotForCache();
    }

    public final void setDeferredChanges$runtime_release(ChangeList changeList) {
        this.deferredChanges = changeList;
    }

    public final void setInsertTable$runtime_release(SlotTable slotTable) {
        this.insertTable = slotTable;
    }

    public final void setReader$runtime_release(SlotReader slotReader) {
        this.reader = slotReader;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bb  */
    @Override // androidx.compose.runtime.Composer
    @androidx.compose.runtime.ComposeCompilerApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void skipCurrentGroup() {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.skipCurrentGroup():void");
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipToGroupEnd() {
        if (!(this.groupNodeCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling skipAndEndGroup");
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            currentRecomposeScope$runtime_release.scopeSkipped();
        }
        if (this.invalidations.isEmpty()) {
            skipReaderToGroupEnd();
        } else {
            recomposeToGroupEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformation(String sourceInformation) {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGroupSourceInformation(sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerEnd() {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGrouplessCallSourceInformationEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerStart(int key, String sourceInformation) {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGrouplessCallSourceInformationStart(key, sourceInformation);
        }
    }

    public final int stacksSize$runtime_release() {
        return this.parentStateStack.getTos() + this.pendingStack.getSize() + this.providersInvalidStack.getTos() + this.invalidateStack.getSize() + this.entersStack.getTos();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startDefaults() {
        m2949startBaiHCIY(-127, null, GroupKind.INSTANCE.m2962getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startMovableGroup(int key, Object dataKey) {
        m2949startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m2962getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    public void startNode() {
        m2949startBaiHCIY(125, null, GroupKind.INSTANCE.m2963getNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProvider(ProvidedValue<?> value) {
        PersistentCompositionLocalMap persistentCompositionLocalMapCurrentCompositionLocalScope = currentCompositionLocalScope();
        startGroup(201, ComposerKt.getProvider());
        Object objRememberedValue = rememberedValue();
        ValueHolder<?> valueHolder = kotlin.jvm.internal.p.a(objRememberedValue, Composer.INSTANCE.getEmpty()) ? null : (ValueHolder) objRememberedValue;
        CompositionLocal<?> compositionLocal = value.getCompositionLocal();
        ValueHolder<?> valueHolderUpdatedStateOf$runtime_release = compositionLocal.updatedStateOf$runtime_release(value, valueHolder);
        boolean zA = kotlin.jvm.internal.p.a(valueHolderUpdatedStateOf$runtime_release, valueHolder);
        if (!zA) {
            updateRememberedValue(valueHolderUpdatedStateOf$runtime_release);
        }
        boolean z = true;
        boolean z5 = false;
        if (getInserting()) {
            if (value.getCanOverride() || !CompositionLocalMapKt.contains(persistentCompositionLocalMapCurrentCompositionLocalScope, compositionLocal)) {
                persistentCompositionLocalMapCurrentCompositionLocalScope = persistentCompositionLocalMapCurrentCompositionLocalScope.putValue(compositionLocal, valueHolderUpdatedStateOf$runtime_release);
            }
            this.writerHasAProvider = true;
        } else {
            SlotReader slotReader = this.reader;
            PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) slotReader.groupAux(slotReader.getCurrentGroup());
            persistentCompositionLocalMapCurrentCompositionLocalScope = (!(getSkipping() && zA) && (value.getCanOverride() || !CompositionLocalMapKt.contains(persistentCompositionLocalMapCurrentCompositionLocalScope, compositionLocal))) ? persistentCompositionLocalMapCurrentCompositionLocalScope.putValue(compositionLocal, valueHolderUpdatedStateOf$runtime_release) : persistentCompositionLocalMap;
            if (!this.reusing && persistentCompositionLocalMap == persistentCompositionLocalMapCurrentCompositionLocalScope) {
                z = false;
            }
            z5 = z;
        }
        if (z5 && !getInserting()) {
            recordProviderUpdate(persistentCompositionLocalMapCurrentCompositionLocalScope);
        }
        this.providersInvalidStack.push(ComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = z5;
        this.providerCache = persistentCompositionLocalMapCurrentCompositionLocalScope;
        m2949startBaiHCIY(202, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2962getGroupULZAiWs(), persistentCompositionLocalMapCurrentCompositionLocalScope);
    }

    @Override // androidx.compose.runtime.Composer
    public void startProviders(ProvidedValue<?>[] values) {
        PersistentCompositionLocalMap persistentCompositionLocalMapUpdateProviderMapGroup;
        PersistentCompositionLocalMap persistentCompositionLocalMapCurrentCompositionLocalScope = currentCompositionLocalScope();
        startGroup(201, ComposerKt.getProvider());
        boolean z = true;
        boolean z5 = false;
        if (getInserting()) {
            persistentCompositionLocalMapUpdateProviderMapGroup = updateProviderMapGroup(persistentCompositionLocalMapCurrentCompositionLocalScope, CompositionLocalMapKt.updateCompositionMap$default(values, persistentCompositionLocalMapCurrentCompositionLocalScope, null, 4, null));
            this.writerHasAProvider = true;
        } else {
            PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) this.reader.groupGet(0);
            PersistentCompositionLocalMap persistentCompositionLocalMap2 = (PersistentCompositionLocalMap) this.reader.groupGet(1);
            PersistentCompositionLocalMap persistentCompositionLocalMapUpdateCompositionMap = CompositionLocalMapKt.updateCompositionMap(values, persistentCompositionLocalMapCurrentCompositionLocalScope, persistentCompositionLocalMap2);
            if (getSkipping() && !this.reusing && persistentCompositionLocalMap2.equals(persistentCompositionLocalMapUpdateCompositionMap)) {
                skipGroup();
                persistentCompositionLocalMapUpdateProviderMapGroup = persistentCompositionLocalMap;
            } else {
                persistentCompositionLocalMapUpdateProviderMapGroup = updateProviderMapGroup(persistentCompositionLocalMapCurrentCompositionLocalScope, persistentCompositionLocalMapUpdateCompositionMap);
                if (!this.reusing && kotlin.jvm.internal.p.a(persistentCompositionLocalMapUpdateProviderMapGroup, persistentCompositionLocalMap)) {
                    z = false;
                }
                z5 = z;
            }
        }
        if (z5 && !getInserting()) {
            recordProviderUpdate(persistentCompositionLocalMapUpdateProviderMapGroup);
        }
        this.providersInvalidStack.push(ComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = z5;
        this.providerCache = persistentCompositionLocalMapUpdateProviderMapGroup;
        m2949startBaiHCIY(202, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2962getGroupULZAiWs(), persistentCompositionLocalMapUpdateProviderMapGroup);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceGroup(int key) {
        if (this.pending != null) {
            m2949startBaiHCIY(key, null, GroupKind.INSTANCE.m2962getGroupULZAiWs(), null);
            return;
        }
        validateNodeNotExpected();
        this.compoundKeyHash = this.rGroupIndex ^ Integer.rotateLeft(Integer.rotateLeft(getCompoundKeyHash(), 3) ^ key, 3);
        this.rGroupIndex++;
        SlotReader slotReader = this.reader;
        if (getInserting()) {
            slotReader.beginEmpty();
            this.writer.startGroup(key, Composer.INSTANCE.getEmpty());
            enterGroup(false, null);
            return;
        }
        if (slotReader.getGroupKey() == key && !slotReader.getHasObjectKey()) {
            slotReader.startGroup();
            enterGroup(false, null);
            return;
        }
        if (!slotReader.isGroupEnd()) {
            int i10 = this.nodeIndex;
            int currentGroup = slotReader.getCurrentGroup();
            recordDelete();
            this.changeListWriter.removeNode(i10, slotReader.skipGroup());
            ComposerKt.removeRange(this.invalidations, currentGroup, slotReader.getCurrentGroup());
        }
        slotReader.beginEmpty();
        this.inserting = true;
        this.providerCache = null;
        ensureWriter();
        SlotWriter slotWriter = this.writer;
        slotWriter.beginInsert();
        int currentGroup2 = slotWriter.getCurrentGroup();
        slotWriter.startGroup(key, Composer.INSTANCE.getEmpty());
        this.insertAnchor = slotWriter.anchor(currentGroup2);
        enterGroup(false, null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceableGroup(int key) {
        m2949startBaiHCIY(key, null, GroupKind.INSTANCE.m2962getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Composer startRestartGroup(int key) {
        startReplaceGroup(key);
        addRecomposeScope();
        return this;
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableGroup(int key, Object dataKey) {
        if (!getInserting() && this.reader.getGroupKey() == key && !kotlin.jvm.internal.p.a(this.reader.getGroupAux(), dataKey) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.getCurrentGroup();
            this.reusing = true;
        }
        m2949startBaiHCIY(key, null, GroupKind.INSTANCE.m2962getGroupULZAiWs(), dataKey);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableNode() {
        m2949startBaiHCIY(125, null, GroupKind.INSTANCE.m2964getReusableNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    public final void startReuseFromRoot() {
        this.reusingGroup = 100;
        this.reusing = true;
    }

    public final boolean tryImminentInvalidation$runtime_release(RecomposeScopeImpl scope, Object instance) {
        Anchor anchor = scope.getAnchor();
        if (anchor == null) {
            return false;
        }
        int indexFor = anchor.toIndexFor(this.reader.getTable());
        if (!this.isComposing || indexFor < this.reader.getCurrentGroup()) {
            return false;
        }
        ComposerKt.insertIfMissing(this.invalidations, indexFor, scope, instance);
        return true;
    }

    public final void updateCachedValue(Object value) {
        if (value instanceof RememberObserver) {
            if (getInserting()) {
                this.changeListWriter.remember((RememberObserver) value);
            }
            this.abandonSet.add(value);
            value = new RememberObserverHolder((RememberObserver) value, rememberObserverAnchor());
        }
        updateValue(value);
    }

    @Override // androidx.compose.runtime.Composer
    public void updateRememberedValue(Object value) {
        updateCachedValue(value);
    }

    public final void updateValue(Object value) {
        if (getInserting()) {
            this.writer.update(value);
            return;
        }
        if (!this.reader.getHadNext()) {
            ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
            SlotReader slotReader = this.reader;
            composerChangeListWriter.appendValue(slotReader.anchor(slotReader.getParent()), value);
            return;
        }
        int groupSlotIndex = this.reader.getGroupSlotIndex() - 1;
        if (!this.changeListWriter.getPastParent()) {
            this.changeListWriter.updateValue(value, groupSlotIndex);
            return;
        }
        ComposerChangeListWriter composerChangeListWriter2 = this.changeListWriter;
        SlotReader slotReader2 = this.reader;
        composerChangeListWriter2.updateAnchoredValue(value, slotReader2.anchor(slotReader2.getParent()), groupSlotIndex);
    }

    @Override // androidx.compose.runtime.Composer
    public void useNode() {
        validateNodeExpected();
        if (getInserting()) {
            ComposerKt.composeImmediateRuntimeError("useNode() called while inserting");
        }
        Object node = getNode(this.reader);
        this.changeListWriter.moveDown(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            this.changeListWriter.useNode(node);
        }
    }

    public final void verifyConsistent$runtime_release() {
        this.insertTable.verifyWellFormed();
    }

    private final void startGroup(int key, Object dataKey) {
        m2949startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m2962getGroupULZAiWs(), null);
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope(int group) {
        PersistentCompositionLocalMap persistentCompositionLocalMap;
        if (getInserting() && this.writerHasAProvider) {
            int parent = this.writer.getParent();
            while (parent > 0) {
                if (this.writer.groupKey(parent) == 202 && kotlin.jvm.internal.p.a(this.writer.groupObjectKey(parent), ComposerKt.getCompositionLocalMap())) {
                    PersistentCompositionLocalMap persistentCompositionLocalMap2 = (PersistentCompositionLocalMap) this.writer.groupAux(parent);
                    this.providerCache = persistentCompositionLocalMap2;
                    return persistentCompositionLocalMap2;
                }
                parent = this.writer.parent(parent);
            }
        }
        if (this.reader.getGroupsSize() > 0) {
            while (group > 0) {
                if (this.reader.groupKey(group) == 202 && kotlin.jvm.internal.p.a(this.reader.groupObjectKey(group), ComposerKt.getCompositionLocalMap())) {
                    IntMap<PersistentCompositionLocalMap> intMap = this.providerUpdates;
                    if (intMap == null || (persistentCompositionLocalMap = intMap.get(group)) == null) {
                        persistentCompositionLocalMap = (PersistentCompositionLocalMap) this.reader.groupAux(group);
                    }
                    this.providerCache = persistentCompositionLocalMap;
                    return persistentCompositionLocalMap;
                }
                group = this.reader.parent(group);
            }
        }
        PersistentCompositionLocalMap persistentCompositionLocalMap3 = this.parentProvider;
        this.providerCache = persistentCompositionLocalMap3;
        return persistentCompositionLocalMap3;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(char value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Character) && value == ((Character) objNextSlot).charValue()) {
            return false;
        }
        updateValue(Character.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(byte value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Byte) && value == ((Number) objNextSlot).byteValue()) {
            return false;
        }
        updateValue(Byte.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(short value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Short) && value == ((Number) objNextSlot).shortValue()) {
            return false;
        }
        updateValue(Short.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(boolean value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Boolean) && value == ((Boolean) objNextSlot).booleanValue()) {
            return false;
        }
        updateValue(Boolean.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(float value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Float) && value == ((Number) objNextSlot).floatValue()) {
            return false;
        }
        updateValue(Float.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(long value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Long) && value == ((Number) objNextSlot).longValue()) {
            return false;
        }
        updateValue(Long.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(double value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Double) && value == ((Number) objNextSlot).doubleValue()) {
            return false;
        }
        updateValue(Double.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(int value) {
        Object objNextSlot = nextSlot();
        if ((objNextSlot instanceof Integer) && value == ((Number) objNextSlot).intValue()) {
            return false;
        }
        updateValue(Integer.valueOf(value));
        return true;
    }
}
