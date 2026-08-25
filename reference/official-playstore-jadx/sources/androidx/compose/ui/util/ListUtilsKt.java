package androidx.compose.ui.util;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import r7.l;
import r7.p;
import t7.a;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a?\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a?\u0010\u0007\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0007\u0010\u0006\u001aE\u0010\n\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\n\u0010\u000b\u001a?\u0010\u000e\u001a\u00020\f\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a?\u0010\u0010\u001a\u00020\f\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0010\u0010\u000f\u001aA\u0010\u0011\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a?\u0010\u0014\u001a\u00020\t\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001aK\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001aQ\u0010\u001b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00010\u001a*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u001b\u0010\u0012\u001a_\u0010\u001f\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016\"\u0010\b\u0002\u0010\u001d*\n\u0012\u0006\b\u0000\u0012\u00028\u00010\u001c*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u001e\u001a\u00028\u00022\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b\u001f\u0010 \u001aA\u0010!\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b!\u0010\u0012\u001aE\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\"\u0010\u0019\u001ab\u0010(\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010#\u001a\u00028\u00012'\u0010'\u001a#\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b(\u0010)\u001a`\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012'\u0010\u0017\u001a#\u0012\u0013\u0012\u00110\t¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(*\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b+\u0010,\u001ab\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012)\u0010\u0017\u001a%\u0012\u0013\u0012\u00110\t¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(*\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b-\u0010,\u001aQ\u0010.\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00010\u001a*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b.\u0010/\u001aQ\u00100\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b0\u0010,\u001a^\u00102\u001a\u00028\u0000\"\u0004\b\u0000\u00101\"\b\b\u0001\u0010\u0000*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\u00012'\u0010'\u001a#\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b2\u00103\u001a\u0083\u0001\u00108\u001a\b\u0012\u0004\u0012\u00028\u00020\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016\"\u0004\b\u0002\u00104*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u00010\u000126\u0010\u0017\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(6\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(7\u0012\u0004\u0012\u00028\u00020\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b8\u00109\u001aM\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b:\u0010\u0019\u001ag\u0010B\u001a\u00020A\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020;2\b\b\u0002\u0010?\u001a\u00020\t2\b\b\u0002\u0010@\u001a\u00020;2\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020;\u0018\u00010\u0002¢\u0006\u0004\bB\u0010C\u001aK\u0010E\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010D*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bE\u0010\u0019\u001aQ\u0010F\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00010\u001a*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bF\u0010\u0012\u001aQ\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010G0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bH\u0010\u0019\u001a)\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\b\b\u0000\u0010\u0000*\u00020I*\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001¢\u0006\u0004\bJ\u0010K\u001a?\u0010L\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0002H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bL\u0010\u0012\u001a\u007f\u0010Q\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\f\b\u0001\u0010O*\u00060Mj\u0002`N*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010P\u001a\u00028\u00012\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020;2\b\b\u0002\u0010?\u001a\u00020\t2\b\b\u0002\u0010@\u001a\u00020;2\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020;\u0018\u00010\u0002H\u0002¢\u0006\u0004\bQ\u0010R\u001a;\u0010T\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u00060Mj\u0002`N2\u0006\u0010S\u001a\u00028\u00002\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020;\u0018\u00010\u0002H\u0002¢\u0006\u0004\bT\u0010U¨\u0006V"}, d2 = {"T", "", "Lkotlin/Function1;", "Lx6/t0;", "action", "fastForEach", "(Ljava/util/List;Lr7/l;)V", "fastForEachReversed", "Lkotlin/Function2;", "", "fastForEachIndexed", "(Ljava/util/List;Lr7/p;)V", "", "predicate", "fastAll", "(Ljava/util/List;Lr7/l;)Z", "fastAny", "fastFirstOrNull", "(Ljava/util/List;Lr7/l;)Ljava/lang/Object;", "selector", "fastSumBy", "(Ljava/util/List;Lr7/l;)I", "R", "transform", "fastMap", "(Ljava/util/List;Lr7/l;)Ljava/util/List;", "", "fastMaxBy", "", "C", "destination", "fastMapTo", "(Ljava/util/List;Ljava/util/Collection;Lr7/l;)Ljava/util/Collection;", "fastLastOrNull", "fastFilter", "initial", "Lx6/y;", ContentDisposition.Parameters.Name, "acc", "operation", "fastFold", "(Ljava/util/List;Ljava/lang/Object;Lr7/p;)Ljava/lang/Object;", "index", "fastMapIndexed", "(Ljava/util/List;Lr7/p;)Ljava/util/List;", "fastMapIndexedNotNull", "fastMaxOfOrNull", "(Ljava/util/List;Lr7/l;)Ljava/lang/Comparable;", "fastZipWithNext", "S", "fastReduce", "(Ljava/util/List;Lr7/p;)Ljava/lang/Object;", "V", "other", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "fastZip", "(Ljava/util/List;Ljava/util/List;Lr7/p;)Ljava/util/List;", "fastMapNotNull", "", "separator", "prefix", "postfix", "limit", "truncated", "", "fastJoinToString", "(Ljava/util/List;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lr7/l;)Ljava/lang/String;", "K", "fastDistinctBy", "fastMinByOrNull", "", "fastFlatMap", "", "fastFilterNotNull", "(Ljava/util/List;)Ljava/util/List;", "fastFirst", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "buffer", "fastJoinTo", "(Ljava/util/List;Ljava/lang/Appendable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lr7/l;)Ljava/lang/Appendable;", "element", "appendElement", "(Ljava/lang/Appendable;Ljava/lang/Object;Lr7/l;)V", "ui-util_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void appendElement(Appendable appendable, T t2, l<? super T, ? extends CharSequence> lVar) throws IOException {
        if (lVar != null) {
            appendable.append((CharSequence) lVar.invoke(t2));
            return;
        }
        if (t2 == 0 ? true : t2 instanceof CharSequence) {
            appendable.append((CharSequence) t2);
        } else if (t2 instanceof Character) {
            appendable.append(((Character) t2).charValue());
        } else {
            appendable.append(String.valueOf(t2));
        }
    }

    public static final <T> boolean fastAll(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (!((Boolean) lVar.invoke(list.get(i10))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean fastAny(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((Boolean) lVar.invoke(list.get(i10))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final <T, K> List<T> fastDistinctBy(List<? extends T> list, l<? super T, ? extends K> lVar) {
        HashSet hashSet = new HashSet(list.size());
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            if (hashSet.add(lVar.invoke(t2))) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    public static final <T> List<T> fastFilter(List<? extends T> list, l<? super T, Boolean> lVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            if (((Boolean) lVar.invoke(t2)).booleanValue()) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    public static final <T> List<T> fastFilterNotNull(List<? extends T> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            if (t2 != null) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    public static final <T> T fastFirst(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            if (((Boolean) lVar.invoke(t2)).booleanValue()) {
                return t2;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final <T> T fastFirstOrNull(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            if (((Boolean) lVar.invoke(t2)).booleanValue()) {
                return t2;
            }
        }
        return null;
    }

    public static final <T, R> List<R> fastFlatMap(List<? extends T> list, l<? super T, ? extends Iterable<? extends R>> lVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            x.b0(arrayList, (Iterable) lVar.invoke(list.get(i10)));
        }
        return arrayList;
    }

    public static final <T, R> R fastFold(List<? extends T> list, R r4, p<? super R, ? super T, ? extends R> pVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            r4 = (R) pVar.invoke(r4, list.get(i10));
        }
        return r4;
    }

    public static final <T> void fastForEach(List<? extends T> list, l<? super T, t0> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            lVar.invoke(list.get(i10));
        }
    }

    public static final <T> void fastForEachIndexed(List<? extends T> list, p<? super Integer, ? super T, t0> pVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            pVar.invoke(Integer.valueOf(i10), list.get(i10));
        }
    }

    public static final <T> void fastForEachReversed(List<? extends T> list, l<? super T, t0> lVar) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i10 = size - 1;
            lVar.invoke(list.get(size));
            if (i10 < 0) {
                return;
            } else {
                size = i10;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T, A extends Appendable> A fastJoinTo(List<? extends T> list, A a10, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i10, CharSequence charSequence4, l<? super T, ? extends CharSequence> lVar) throws IOException {
        a10.append(charSequence2);
        int size = list.size();
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            T t2 = list.get(i12);
            i11++;
            if (i11 > 1) {
                a10.append(charSequence);
            }
            if (i10 >= 0 && i11 > i10) {
                break;
            }
            appendElement(a10, t2, lVar);
        }
        if (i10 >= 0 && i11 > i10) {
            a10.append(charSequence4);
        }
        a10.append(charSequence3);
        return a10;
    }

    public static final <T> String fastJoinToString(List<? extends T> list, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i10, CharSequence charSequence4, l<? super T, ? extends CharSequence> lVar) {
        return ((StringBuilder) fastJoinTo(list, new StringBuilder(), charSequence, charSequence2, charSequence3, i10, charSequence4, lVar)).toString();
    }

    public static /* synthetic */ String fastJoinToString$default(List list, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i10, CharSequence charSequence4, l lVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i11 & 2) != 0) {
            charSequence2 = "";
        }
        if ((i11 & 4) != 0) {
            charSequence3 = "";
        }
        if ((i11 & 8) != 0) {
            i10 = -1;
        }
        if ((i11 & 16) != 0) {
            charSequence4 = "...";
        }
        if ((i11 & 32) != 0) {
            lVar = null;
        }
        CharSequence charSequence5 = charSequence4;
        l lVar2 = lVar;
        return fastJoinToString(list, charSequence, charSequence2, charSequence3, i10, charSequence5, lVar2);
    }

    public static final <T> T fastLastOrNull(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size() - 1;
        if (size < 0) {
            return null;
        }
        while (true) {
            int i10 = size - 1;
            T t2 = list.get(size);
            if (((Boolean) lVar.invoke(t2)).booleanValue()) {
                return t2;
            }
            if (i10 < 0) {
                return null;
            }
            size = i10;
        }
    }

    public static final <T, R> List<R> fastMap(List<? extends T> list, l<? super T, ? extends R> lVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(lVar.invoke(list.get(i10)));
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapIndexed(List<? extends T> list, p<? super Integer, ? super T, ? extends R> pVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(pVar.invoke(Integer.valueOf(i10), list.get(i10)));
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapIndexedNotNull(List<? extends T> list, p<? super Integer, ? super T, ? extends R> pVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object objInvoke = pVar.invoke(Integer.valueOf(i10), list.get(i10));
            if (objInvoke != null) {
                arrayList.add(objInvoke);
            }
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapNotNull(List<? extends T> list, l<? super T, ? extends R> lVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object objInvoke = lVar.invoke(list.get(i10));
            if (objInvoke != null) {
                arrayList.add(objInvoke);
            }
        }
        return arrayList;
    }

    public static final <T, R, C extends Collection<? super R>> C fastMapTo(List<? extends T> list, C c10, l<? super T, ? extends R> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            c10.add(lVar.invoke(list.get(i10)));
        }
        return c10;
    }

    public static final <T, R extends Comparable<? super R>> T fastMaxBy(List<? extends T> list, l<? super T, ? extends R> lVar) {
        if (list.isEmpty()) {
            return null;
        }
        T t2 = list.get(0);
        Comparable comparable = (Comparable) lVar.invoke(t2);
        int iW = a.w(list);
        int i10 = 1;
        if (1 <= iW) {
            while (true) {
                T t10 = list.get(i10);
                Comparable comparable2 = (Comparable) lVar.invoke(t10);
                if (comparable.compareTo(comparable2) < 0) {
                    t2 = t10;
                    comparable = comparable2;
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return t2;
    }

    public static final <T, R extends Comparable<? super R>> R fastMaxOfOrNull(List<? extends T> list, l<? super T, ? extends R> lVar) {
        if (list.isEmpty()) {
            return null;
        }
        R r4 = (R) lVar.invoke(list.get(0));
        int iW = a.w(list);
        int i10 = 1;
        if (1 <= iW) {
            while (true) {
                Comparable comparable = (Comparable) lVar.invoke(list.get(i10));
                if (comparable.compareTo(r4) > 0) {
                    r4 = (R) comparable;
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return (R) r4;
    }

    public static final <T, R extends Comparable<? super R>> T fastMinByOrNull(List<? extends T> list, l<? super T, ? extends R> lVar) {
        if (list.isEmpty()) {
            return null;
        }
        T t2 = list.get(0);
        Comparable comparable = (Comparable) lVar.invoke(t2);
        int iW = a.w(list);
        int i10 = 1;
        if (1 <= iW) {
            while (true) {
                T t10 = list.get(i10);
                Comparable comparable2 = (Comparable) lVar.invoke(t10);
                if (comparable.compareTo(comparable2) > 0) {
                    t2 = t10;
                    comparable = comparable2;
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return t2;
    }

    public static final <S, T extends S> S fastReduce(List<? extends T> list, p<? super S, ? super T, ? extends S> pVar) {
        if (list.isEmpty()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        S s10 = (S) x.m0(list);
        int iW = a.w(list);
        int i10 = 1;
        if (1 <= iW) {
            while (true) {
                s10 = (S) pVar.invoke(s10, list.get(i10));
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return s10;
    }

    public static final <T> int fastSumBy(List<? extends T> list, l<? super T, Integer> lVar) {
        int size = list.size();
        int iIntValue = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iIntValue += ((Number) lVar.invoke(list.get(i10))).intValue();
        }
        return iIntValue;
    }

    public static final <T, R, V> List<V> fastZip(List<? extends T> list, List<? extends R> list2, p<? super T, ? super R, ? extends V> pVar) {
        int iMin = Math.min(list.size(), list2.size());
        ArrayList arrayList = new ArrayList(iMin);
        for (int i10 = 0; i10 < iMin; i10++) {
            arrayList.add(pVar.invoke(list.get(i10), list2.get(i10)));
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastZipWithNext(List<? extends T> list, p<? super T, ? super T, ? extends R> pVar) {
        if (list.size() == 0 || list.size() == 1) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        T t2 = list.get(0);
        int iW = a.w(list);
        while (i10 < iW) {
            i10++;
            T t10 = list.get(i10);
            arrayList.add(pVar.invoke(t2, t10));
            t2 = t10;
        }
        return arrayList;
    }
}
