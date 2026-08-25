package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import io.ktor.http.ContentDisposition;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.j;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u001d\n\u0002\u0010(\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010*\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B?\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0010\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0019\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u0015\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u001dJ%\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\n2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001eJ\u0018\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\nH\u0096\u0002¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\nH\u0016¢\u0006\u0004\b!\u0010 J\u001d\u0010\"\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0016¢\u0006\u0004\b\"\u0010\u001aJ!\u0010%\u001a\u00020\u00142\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140#¢\u0006\u0004\b%\u0010&J \u0010'\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b'\u0010(J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000)H\u0096\u0002¢\u0006\u0004\b*\u0010+J\u0015\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0016¢\u0006\u0004\b-\u0010.J\u001d\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000,2\u0006\u0010\u001b\u001a\u00020\nH\u0016¢\u0006\u0004\b-\u0010/J\u000f\u00100\u001a\u00020\nH\u0002¢\u0006\u0004\b0\u0010\u000fJ\u0017\u00102\u001a\u00020\n2\u0006\u00101\u001a\u00020\nH\u0002¢\u0006\u0004\b2\u00103J\u000f\u00102\u001a\u00020\nH\u0002¢\u0006\u0004\b2\u0010\u000fJ\u001f\u00105\u001a\u00020\u00142\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\b5\u00106J)\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0010\u00104\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006H\u0002¢\u0006\u0004\b7\u00108J/\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\b:\u0010;J!\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\b>\u0010?JA\u0010C\u001a\u00020\u001c2\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u000e\u0010A\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\bC\u0010DJA\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u000e\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010F\u001a\u00020\nH\u0002¢\u0006\u0004\bG\u0010HJ?\u0010L\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010I\u001a\u00020\n2\u000e\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070JH\u0002¢\u0006\u0004\bL\u0010MJG\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0006\u00100\u001a\u00020\n2\u0014\u0010N\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u0006H\u0002¢\u0006\u0004\bO\u0010PJO\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0006\u00100\u001a\u00020\n2\u0006\u0010F\u001a\u00020\n2\u0014\u0010Q\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060JH\u0002¢\u0006\u0004\bR\u0010SJ1\u0010T\u001a\u00020\u001c2\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00028\u0000H\u0002¢\u0006\u0004\bT\u0010UJI\u0010X\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010F\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010W\u001a\u00020VH\u0002¢\u0006\u0004\bX\u0010YJ]\u0010X\u001a\u00020\u001c2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00172\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010Z\u001a\u00020\n2\u0016\u0010N\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00060\u00062\u0006\u0010[\u001a\u00020\n2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\bX\u0010]JW\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010^\u001a\u00020\n2\u0006\u0010Z\u001a\u00020\n2\u0016\u0010N\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00060\u00062\u0006\u0010[\u001a\u00020\n2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\b_\u0010`Jm\u0010c\u001a\u00020\u001c2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00172\u0006\u0010\u001b\u001a\u00020\n2\u000e\u0010a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010b\u001a\u00020\n2\u0016\u0010N\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00060\u00062\u0006\u0010[\u001a\u00020\n2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\bc\u0010dJ\u001f\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\u001b\u001a\u00020\nH\u0002¢\u0006\u0004\be\u0010fJ;\u0010g\u001a\u0004\u0018\u00010\u00072\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0006\u00100\u001a\u00020\n2\u0006\u0010F\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH\u0002¢\u0006\u0004\bg\u0010hJ?\u0010j\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010F\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010i\u001a\u00020VH\u0002¢\u0006\u0004\bj\u0010kJ1\u0010l\u001a\u00020\u001c2\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0006\u00100\u001a\u00020\n2\u0006\u0010F\u001a\u00020\nH\u0002¢\u0006\u0004\bl\u0010mJA\u0010n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010F\u001a\u00020\n2\u0006\u00100\u001a\u00020\n2\u0006\u0010i\u001a\u00020VH\u0002¢\u0006\u0004\bn\u0010kJ#\u0010\"\u001a\u00020\u00142\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140#H\u0002¢\u0006\u0004\b\"\u0010&J1\u0010o\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u00101\u001a\u00020\nH\u0002¢\u0006\u0004\bo\u0010;J7\u0010p\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010F\u001a\u00020\nH\u0002¢\u0006\u0004\bp\u0010qJ3\u0010s\u001a\u00020\n2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140#2\u0006\u00102\u001a\u00020\n2\u0006\u0010r\u001a\u00020VH\u0002¢\u0006\u0004\bs\u0010tJC\u0010\"\u001a\u00020\n2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140#2\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010u\u001a\u00020\n2\u0006\u0010r\u001a\u00020VH\u0002¢\u0006\u0004\b\"\u0010vJw\u0010z\u001a\u00020\n2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140#2\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010u\u001a\u00020\n2\u0006\u0010w\u001a\u00020\n2\u0006\u0010r\u001a\u00020V2\u0014\u0010y\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060x2\u0014\u0010N\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060xH\u0002¢\u0006\u0004\bz\u0010{JG\u0010~\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010F\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010|\u001a\u00028\u00002\u0006\u0010}\u001a\u00020VH\u0002¢\u0006\u0004\b~\u0010YJ'\u0010\u0080\u0001\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u007f2\u0006\u0010\u001b\u001a\u00020\nH\u0002¢\u0006\u0005\b\u0080\u0001\u0010/R\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0005\u0010\u0081\u0001R!\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\b\u0010\u0082\u0001R\u001f\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\t\u0010\u0082\u0001R&\u0010\u000b\u001a\u00020\n8\u0000@\u0000X\u0080\u000e¢\u0006\u0016\n\u0005\b\u000b\u0010\u0083\u0001\u001a\u0005\b\u0084\u0001\u0010\u000f\"\u0006\b\u0085\u0001\u0010\u0086\u0001R\u001a\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R;\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0011\u0010\u008a\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00068\u0000@BX\u0080\u000e¢\u0006\u000e\n\u0005\b@\u0010\u0082\u0001\u001a\u0005\b\u008b\u0001\u0010?R7\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000f\u0010\u008a\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068\u0000@BX\u0080\u000e¢\u0006\u000e\n\u0005\bE\u0010\u0082\u0001\u001a\u0005\b\u008c\u0001\u0010?R'\u00101\u001a\u00020\n2\u0007\u0010\u008a\u0001\u001a\u00020\n8\u0016@RX\u0096\u000e¢\u0006\u000e\n\u0005\b1\u0010\u0083\u0001\u001a\u0005\b\u008d\u0001\u0010\u000f¨\u0006\u008e\u0001"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVectorBuilder;", "E", "Lkotlin/collections/j;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList$Builder;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "vector", "", "", "vectorRoot", "vectorTail", "", "rootShift", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;[Ljava/lang/Object;[Ljava/lang/Object;I)V", "getModCount$runtime_release", "()I", "getModCount", "build", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "element", "", "add", "(Ljava/lang/Object;)Z", "", "elements", "addAll", "(Ljava/util/Collection;)Z", "index", "Lx6/t0;", "(ILjava/lang/Object;)V", "(ILjava/util/Collection;)Z", "get", "(I)Ljava/lang/Object;", "removeAt", "removeAll", "Lkotlin/Function1;", "predicate", "removeAllWithPredicate", "(Lr7/l;)Z", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "", "iterator", "()Ljava/util/Iterator;", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "rootSize", ContentDisposition.Parameters.Size, "tailSize", "(I)I", "buffer", "isMutable", "([Ljava/lang/Object;)Z", "makeMutable", "([Ljava/lang/Object;)[Ljava/lang/Object;", "distance", "makeMutableShiftingRight", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "mutableBufferWith", "(Ljava/lang/Object;)[Ljava/lang/Object;", "mutableBuffer", "()[Ljava/lang/Object;", "root", "filledTail", "newTail", "pushFilledTail", "([Ljava/lang/Object;[Ljava/lang/Object;[Ljava/lang/Object;)V", "tail", "shift", "pushTail", "([Ljava/lang/Object;[Ljava/lang/Object;I)[Ljava/lang/Object;", "bufferIndex", "", "sourceIterator", "copyToBuffer", "([Ljava/lang/Object;ILjava/util/Iterator;)[Ljava/lang/Object;", "buffers", "pushBuffersIncreasingHeightIfNeeded", "([Ljava/lang/Object;I[[Ljava/lang/Object;)[Ljava/lang/Object;", "buffersIterator", "pushBuffers", "([Ljava/lang/Object;IILjava/util/Iterator;)[Ljava/lang/Object;", "insertIntoTail", "([Ljava/lang/Object;ILjava/lang/Object;)V", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;", "elementCarry", "insertIntoRoot", "([Ljava/lang/Object;IILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "rightShift", "nullBuffers", "nextBuffer", "(Ljava/util/Collection;II[[Ljava/lang/Object;I[Ljava/lang/Object;)V", "startLeafIndex", "shiftLeafBuffers", "(II[[Ljava/lang/Object;I[Ljava/lang/Object;)[Ljava/lang/Object;", "startBuffer", "startBufferSize", "splitToBuffers", "(Ljava/util/Collection;I[Ljava/lang/Object;I[[Ljava/lang/Object;I[Ljava/lang/Object;)V", "bufferFor", "(I)[Ljava/lang/Object;", "removeFromTailAt", "([Ljava/lang/Object;III)Ljava/lang/Object;", "tailCarry", "removeFromRootAt", "([Ljava/lang/Object;IILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "pullLastBufferFromRoot", "([Ljava/lang/Object;II)V", "pullLastBuffer", "retainFirst", "nullifyAfter", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "bufferRef", "removeAllFromTail", "(Lr7/l;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)I", "bufferSize", "(Lr7/l;[Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)I", "toBufferSize", "", "recyclableBuffers", "recyclableRemoveAll", "(Lr7/l;[Ljava/lang/Object;IILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;Ljava/util/List;Ljava/util/List;)I", "e", "oldElementCarry", "setInRoot", "", "leafBufferIterator", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "[Ljava/lang/Object;", "I", "getRootShift$runtime_release", "setRootShift$runtime_release", "(I)V", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "ownership", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "<set-?>", "getRoot$runtime_release", "getTail$runtime_release", "getSize", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PersistentVectorBuilder<E> extends j implements PersistentList.Builder<E> {
    public static final int $stable = 8;
    private MutabilityOwnership ownership = new MutabilityOwnership();
    private Object[] root;
    private int rootShift;
    private int size;
    private Object[] tail;
    private PersistentList<? extends E> vector;
    private Object[] vectorRoot;
    private Object[] vectorTail;

    /* JADX INFO: renamed from: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder$removeAll$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<E, Boolean> {
        final /* synthetic */ Collection<E> $elements;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Collection<? extends E> collection) {
            super(1);
            this.$elements = collection;
        }

        @Override // r7.l
        public final Boolean invoke(E e5) {
            return Boolean.valueOf(this.$elements.contains(e5));
        }
    }

    public PersistentVectorBuilder(PersistentList<? extends E> persistentList, Object[] objArr, Object[] objArr2, int i10) {
        this.vector = persistentList;
        this.vectorRoot = objArr;
        this.vectorTail = objArr2;
        this.rootShift = i10;
        this.root = this.vectorRoot;
        this.tail = this.vectorTail;
        this.size = this.vector.size();
    }

    private final Object[] bufferFor(int index) {
        if (rootSize() <= index) {
            return this.tail;
        }
        Object[] objArr = this.root;
        for (int i10 = this.rootShift; i10 > 0; i10 -= 5) {
            objArr = objArr[UtilsKt.indexSegment(index, i10)];
        }
        return objArr;
    }

    private final Object[] copyToBuffer(Object[] buffer, int bufferIndex, Iterator<? extends Object> sourceIterator) {
        while (bufferIndex < 32 && sourceIterator.hasNext()) {
            buffer[bufferIndex] = sourceIterator.next();
            bufferIndex++;
        }
        return buffer;
    }

    private final Object[] insertIntoRoot(Object[] root, int shift, int index, Object element, ObjectRef elementCarry) {
        Object obj;
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            elementCarry.setValue(root[31]);
            Object[] objArrMakeMutable = makeMutable(root);
            System.arraycopy(root, iIndexSegment, objArrMakeMutable, iIndexSegment + 1, 31 - iIndexSegment);
            objArrMakeMutable[iIndexSegment] = element;
            return objArrMakeMutable;
        }
        Object[] objArrMakeMutable2 = makeMutable(root);
        int i10 = shift - 5;
        objArrMakeMutable2[iIndexSegment] = insertIntoRoot((Object[]) objArrMakeMutable2[iIndexSegment], i10, index, element, elementCarry);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || (obj = objArrMakeMutable2[iIndexSegment]) == null) {
                break;
            }
            objArrMakeMutable2[iIndexSegment] = insertIntoRoot((Object[]) obj, i10, 0, elementCarry.getValue(), elementCarry);
        }
        return objArrMakeMutable2;
    }

    private final void insertIntoTail(Object[] root, int index, E element) {
        int iTailSize = tailSize();
        Object[] objArrMakeMutable = makeMutable(this.tail);
        if (iTailSize < 32) {
            kotlin.collections.r.N(this.tail, objArrMakeMutable, index + 1, index, iTailSize);
            objArrMakeMutable[index] = element;
            this.root = root;
            this.tail = objArrMakeMutable;
            this.size = size() + 1;
            return;
        }
        Object[] objArr = this.tail;
        Object obj = objArr[31];
        kotlin.collections.r.N(objArr, objArrMakeMutable, index + 1, index, 31);
        objArrMakeMutable[index] = element;
        pushFilledTail(root, objArrMakeMutable, mutableBufferWith(obj));
    }

    private final boolean isMutable(Object[] buffer) {
        return buffer.length == 33 && buffer[32] == this.ownership;
    }

    private final ListIterator<Object[]> leafBufferIterator(int index) {
        Object[] objArr = this.root;
        if (objArr == null) {
            throw new IllegalStateException("Invalid root");
        }
        int iRootSize = rootSize() >> 5;
        ListImplementation.checkPositionIndex$runtime_release(index, iRootSize);
        int i10 = this.rootShift;
        return i10 == 0 ? new SingleElementListIterator(objArr, index) : new TrieIterator(objArr, index, iRootSize, i10 / 5);
    }

    private final Object[] makeMutable(Object[] buffer) {
        if (buffer == null) {
            return mutableBuffer();
        }
        if (isMutable(buffer)) {
            return buffer;
        }
        Object[] objArrMutableBuffer = mutableBuffer();
        int length = buffer.length;
        kotlin.collections.r.R(buffer, objArrMutableBuffer, 0, 0, length > 32 ? 32 : length, 6);
        return objArrMutableBuffer;
    }

    private final Object[] makeMutableShiftingRight(Object[] buffer, int distance) {
        if (isMutable(buffer)) {
            System.arraycopy(buffer, 0, buffer, distance, 32 - distance);
            return buffer;
        }
        Object[] objArrMutableBuffer = mutableBuffer();
        System.arraycopy(buffer, 0, objArrMutableBuffer, distance, 32 - distance);
        return objArrMutableBuffer;
    }

    private final Object[] mutableBuffer() {
        Object[] objArr = new Object[33];
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] mutableBufferWith(Object element) {
        Object[] objArr = new Object[33];
        objArr[0] = element;
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] nullifyAfter(Object[] root, int index, int shift) {
        if (!(shift >= 0)) {
            PreconditionsKt.throwIllegalArgumentException("shift should be positive");
        }
        if (shift == 0) {
            return root;
        }
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object objNullifyAfter = nullifyAfter((Object[]) root[iIndexSegment], index, shift - 5);
        if (iIndexSegment < 31) {
            int i10 = iIndexSegment + 1;
            if (root[i10] != null) {
                if (isMutable(root)) {
                    Arrays.fill(root, i10, 32, (Object) null);
                }
                Object[] objArrMutableBuffer = mutableBuffer();
                System.arraycopy(root, 0, objArrMutableBuffer, 0, i10);
                root = objArrMutableBuffer;
            }
        }
        if (objNullifyAfter == root[iIndexSegment]) {
            return root;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        objArrMakeMutable[iIndexSegment] = objNullifyAfter;
        return objArrMakeMutable;
    }

    private final Object[] pullLastBuffer(Object[] root, int shift, int rootSize, ObjectRef tailCarry) {
        Object[] objArrPullLastBuffer;
        int iIndexSegment = UtilsKt.indexSegment(rootSize - 1, shift);
        if (shift == 5) {
            tailCarry.setValue(root[iIndexSegment]);
            objArrPullLastBuffer = null;
        } else {
            objArrPullLastBuffer = pullLastBuffer((Object[]) root[iIndexSegment], shift - 5, rootSize, tailCarry);
        }
        if (objArrPullLastBuffer == null && iIndexSegment == 0) {
            return null;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        objArrMakeMutable[iIndexSegment] = objArrPullLastBuffer;
        return objArrMakeMutable;
    }

    private final void pullLastBufferFromRoot(Object[] root, int rootSize, int shift) {
        if (shift == 0) {
            this.root = null;
            if (root == null) {
                root = new Object[0];
            }
            this.tail = root;
            this.size = rootSize;
            this.rootShift = shift;
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArrPullLastBuffer = pullLastBuffer(root, shift, rootSize, objectRef);
        this.tail = (Object[]) objectRef.getValue();
        this.size = rootSize;
        if (objArrPullLastBuffer[1] == null) {
            this.root = (Object[]) objArrPullLastBuffer[0];
            this.rootShift = shift - 5;
        } else {
            this.root = objArrPullLastBuffer;
            this.rootShift = shift;
        }
    }

    private final Object[] pushBuffers(Object[] root, int rootSize, int shift, Iterator<Object[]> buffersIterator) {
        if (!buffersIterator.hasNext()) {
            PreconditionsKt.throwIllegalArgumentException("invalid buffersIterator");
        }
        if (!(shift >= 0)) {
            PreconditionsKt.throwIllegalArgumentException("negative shift");
        }
        if (shift == 0) {
            return buffersIterator.next();
        }
        Object[] objArrMakeMutable = makeMutable(root);
        int iIndexSegment = UtilsKt.indexSegment(rootSize, shift);
        int i10 = shift - 5;
        objArrMakeMutable[iIndexSegment] = pushBuffers((Object[]) objArrMakeMutable[iIndexSegment], rootSize, i10, buffersIterator);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || !buffersIterator.hasNext()) {
                break;
            }
            objArrMakeMutable[iIndexSegment] = pushBuffers((Object[]) objArrMakeMutable[iIndexSegment], 0, i10, buffersIterator);
        }
        return objArrMakeMutable;
    }

    private final Object[] pushBuffersIncreasingHeightIfNeeded(Object[] root, int rootSize, Object[][] buffers) {
        ab.j jVar = new ab.j(buffers, 4);
        int i10 = rootSize >> 5;
        int i11 = this.rootShift;
        Object[] objArrPushBuffers = i10 < (1 << i11) ? pushBuffers(root, rootSize, i11, jVar) : makeMutable(root);
        while (jVar.hasNext()) {
            this.rootShift += 5;
            objArrPushBuffers = mutableBufferWith(objArrPushBuffers);
            int i12 = this.rootShift;
            pushBuffers(objArrPushBuffers, 1 << i12, i12, jVar);
        }
        return objArrPushBuffers;
    }

    private final void pushFilledTail(Object[] root, Object[] filledTail, Object[] newTail) {
        int size = size() >> 5;
        int i10 = this.rootShift;
        if (size > (1 << i10)) {
            this.root = pushTail(mutableBufferWith(root), filledTail, this.rootShift + 5);
            this.tail = newTail;
            this.rootShift += 5;
            this.size = size() + 1;
            return;
        }
        if (root == null) {
            this.root = filledTail;
            this.tail = newTail;
            this.size = size() + 1;
        } else {
            this.root = pushTail(root, filledTail, i10);
            this.tail = newTail;
            this.size = size() + 1;
        }
    }

    private final Object[] pushTail(Object[] root, Object[] tail, int shift) {
        int iIndexSegment = UtilsKt.indexSegment(size() - 1, shift);
        Object[] objArrMakeMutable = makeMutable(root);
        if (shift == 5) {
            objArrMakeMutable[iIndexSegment] = tail;
            return objArrMakeMutable;
        }
        objArrMakeMutable[iIndexSegment] = pushTail((Object[]) objArrMakeMutable[iIndexSegment], tail, shift - 5);
        return objArrMakeMutable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int recyclableRemoveAll(l<? super E, Boolean> predicate, Object[] buffer, int bufferSize, int toBufferSize, ObjectRef bufferRef, List<Object[]> recyclableBuffers, List<Object[]> buffers) {
        if (isMutable(buffer)) {
            recyclableBuffers.add(buffer);
        }
        Object[] objArr = (Object[]) bufferRef.getValue();
        Object[] objArrRemove = objArr;
        for (int i10 = 0; i10 < bufferSize; i10++) {
            Object obj = buffer[i10];
            if (!((Boolean) predicate.invoke(obj)).booleanValue()) {
                if (toBufferSize == 32) {
                    objArrRemove = !recyclableBuffers.isEmpty() ? recyclableBuffers.remove(recyclableBuffers.size() - 1) : mutableBuffer();
                    toBufferSize = 0;
                }
                objArrRemove[toBufferSize] = obj;
                toBufferSize++;
            }
        }
        bufferRef.setValue(objArrRemove);
        if (objArr != bufferRef.getValue()) {
            buffers.add(objArr);
        }
        return toBufferSize;
    }

    private final int removeAllFromTail(l<? super E, Boolean> predicate, int tailSize, ObjectRef bufferRef) {
        int iRemoveAll = removeAll(predicate, this.tail, tailSize, bufferRef);
        if (iRemoveAll == tailSize) {
            CommonFunctionsKt.m3083assert(bufferRef.getValue() == this.tail);
            return tailSize;
        }
        Object[] objArr = (Object[]) bufferRef.getValue();
        Arrays.fill(objArr, iRemoveAll, tailSize, (Object) null);
        this.tail = objArr;
        this.size = size() - (tailSize - iRemoveAll);
        return iRemoveAll;
    }

    private final Object[] removeFromRootAt(Object[] root, int shift, int index, ObjectRef tailCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            Object obj = root[iIndexSegment];
            Object[] objArrMakeMutable = makeMutable(root);
            int i10 = iIndexSegment + 1;
            System.arraycopy(root, i10, objArrMakeMutable, iIndexSegment, 32 - i10);
            objArrMakeMutable[31] = tailCarry.getValue();
            tailCarry.setValue(obj);
            return objArrMakeMutable;
        }
        int iIndexSegment2 = root[31] == null ? UtilsKt.indexSegment(rootSize() - 1, shift) : 31;
        Object[] objArrMakeMutable2 = makeMutable(root);
        int i11 = shift - 5;
        int i12 = iIndexSegment + 1;
        if (i12 <= iIndexSegment2) {
            while (true) {
                objArrMakeMutable2[iIndexSegment2] = removeFromRootAt((Object[]) objArrMakeMutable2[iIndexSegment2], i11, 0, tailCarry);
                if (iIndexSegment2 == i12) {
                    break;
                }
                iIndexSegment2--;
            }
        }
        objArrMakeMutable2[iIndexSegment] = removeFromRootAt((Object[]) objArrMakeMutable2[iIndexSegment], i11, index, tailCarry);
        return objArrMakeMutable2;
    }

    private final Object removeFromTailAt(Object[] root, int rootSize, int shift, int index) {
        int size = size() - rootSize;
        CommonFunctionsKt.m3083assert(index < size);
        if (size == 1) {
            Object obj = this.tail[0];
            pullLastBufferFromRoot(root, rootSize, shift);
            return obj;
        }
        Object[] objArr = this.tail;
        Object obj2 = objArr[index];
        Object[] objArrMakeMutable = makeMutable(objArr);
        int i10 = index + 1;
        System.arraycopy(objArr, i10, objArrMakeMutable, index, size - i10);
        objArrMakeMutable[size - 1] = null;
        this.root = root;
        this.tail = objArrMakeMutable;
        this.size = (rootSize + size) - 1;
        this.rootShift = shift;
        return obj2;
    }

    private final Object[] retainFirst(Object[] root, int size) {
        if (!((size & 31) == 0)) {
            PreconditionsKt.throwIllegalArgumentException("invalid size");
        }
        if (size == 0) {
            this.rootShift = 0;
            return null;
        }
        int i10 = size - 1;
        while (true) {
            int i11 = this.rootShift;
            if ((i10 >> i11) != 0) {
                return nullifyAfter(root, i10, i11);
            }
            this.rootShift = i11 - 5;
            root = root[0];
        }
    }

    private final int rootSize() {
        if (size() <= 32) {
            return 0;
        }
        return UtilsKt.rootSize(size());
    }

    private final Object[] setInRoot(Object[] root, int shift, int index, E e5, ObjectRef oldElementCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object[] objArrMakeMutable = makeMutable(root);
        if (shift != 0) {
            objArrMakeMutable[iIndexSegment] = setInRoot((Object[]) objArrMakeMutable[iIndexSegment], shift - 5, index, e5, oldElementCarry);
            return objArrMakeMutable;
        }
        if (objArrMakeMutable != root) {
            ((AbstractList) this).modCount++;
        }
        oldElementCarry.setValue(objArrMakeMutable[iIndexSegment]);
        objArrMakeMutable[iIndexSegment] = e5;
        return objArrMakeMutable;
    }

    private final Object[] shiftLeafBuffers(int startLeafIndex, int rightShift, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        if (this.root == null) {
            throw new IllegalStateException("root is null");
        }
        ListIterator<Object[]> listIteratorLeafBufferIterator = leafBufferIterator(rootSize() >> 5);
        while (listIteratorLeafBufferIterator.previousIndex() != startLeafIndex) {
            Object[] objArrPrevious = listIteratorLeafBufferIterator.previous();
            kotlin.collections.r.N(objArrPrevious, nextBuffer, 0, 32 - rightShift, 32);
            nextBuffer = makeMutableShiftingRight(objArrPrevious, rightShift);
            nullBuffers--;
            buffers[nullBuffers] = nextBuffer;
        }
        return listIteratorLeafBufferIterator.previous();
    }

    private final void splitToBuffers(Collection<? extends E> elements, int index, Object[] startBuffer, int startBufferSize, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        Object[] objArrMutableBuffer;
        if (nullBuffers < 1) {
            PreconditionsKt.throwIllegalArgumentException("requires at least one nullBuffer");
        }
        Object[] objArrMakeMutable = makeMutable(startBuffer);
        buffers[0] = objArrMakeMutable;
        int i10 = index & 31;
        int size = ((elements.size() + index) - 1) & 31;
        int i11 = (startBufferSize - i10) + size;
        if (i11 < 32) {
            kotlin.collections.r.N(objArrMakeMutable, nextBuffer, size + 1, i10, startBufferSize);
        } else {
            int i12 = i11 - 31;
            if (nullBuffers == 1) {
                objArrMutableBuffer = objArrMakeMutable;
            } else {
                objArrMutableBuffer = mutableBuffer();
                nullBuffers--;
                buffers[nullBuffers] = objArrMutableBuffer;
            }
            int i13 = startBufferSize - i12;
            kotlin.collections.r.N(objArrMakeMutable, nextBuffer, 0, i13, startBufferSize);
            kotlin.collections.r.N(objArrMakeMutable, objArrMutableBuffer, size + 1, i10, i13);
            nextBuffer = objArrMutableBuffer;
        }
        Iterator<? extends E> it = elements.iterator();
        copyToBuffer(objArrMakeMutable, i10, it);
        for (int i14 = 1; i14 < nullBuffers; i14++) {
            buffers[i14] = copyToBuffer(mutableBuffer(), 0, it);
        }
        copyToBuffer(nextBuffer, 0, it);
    }

    private final int tailSize(int size) {
        return size <= 32 ? size : size - UtilsKt.rootSize(size);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E element) {
        ((AbstractList) this).modCount++;
        int iTailSize = tailSize();
        if (iTailSize < 32) {
            Object[] objArrMakeMutable = makeMutable(this.tail);
            objArrMakeMutable[iTailSize] = element;
            this.tail = objArrMakeMutable;
            this.size = size() + 1;
        } else {
            pushFilledTail(this.root, this.tail, mutableBufferWith(element));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int iTailSize = tailSize();
        Iterator<? extends E> it = elements.iterator();
        if (32 - iTailSize >= elements.size()) {
            this.tail = copyToBuffer(makeMutable(this.tail), iTailSize, it);
            this.size = elements.size() + size();
        } else {
            int size = ((elements.size() + iTailSize) - 1) / 32;
            Object[][] objArr = new Object[size][];
            objArr[0] = copyToBuffer(makeMutable(this.tail), iTailSize, it);
            for (int i10 = 1; i10 < size; i10++) {
                objArr[i10] = copyToBuffer(mutableBuffer(), 0, it);
            }
            this.root = pushBuffersIncreasingHeightIfNeeded(this.root, rootSize(), objArr);
            this.tail = copyToBuffer(mutableBuffer(), 0, it);
            this.size = elements.size() + size();
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int index) {
        ListImplementation.checkElementIndex$runtime_release(index, size());
        return (E) bufferFor(index)[index & 31];
    }

    public final int getModCount$runtime_release() {
        return ((AbstractList) this).modCount;
    }

    /* JADX INFO: renamed from: getRoot$runtime_release, reason: from getter */
    public final Object[] getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: getRootShift$runtime_release, reason: from getter */
    public final int getRootShift() {
        return this.rootShift;
    }

    @Override // kotlin.collections.j
    public int getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: getTail$runtime_release, reason: from getter */
    public final Object[] getTail() {
        return this.tail;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> elements) {
        return removeAllWithPredicate(new AnonymousClass1(elements));
    }

    public final boolean removeAllWithPredicate(l<? super E, Boolean> predicate) {
        boolean zRemoveAll = removeAll(predicate);
        if (zRemoveAll) {
            ((AbstractList) this).modCount++;
        }
        return zRemoveAll;
    }

    @Override // kotlin.collections.j
    public E removeAt(int index) {
        ListImplementation.checkElementIndex$runtime_release(index, size());
        ((AbstractList) this).modCount++;
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            return (E) removeFromTailAt(this.root, iRootSize, this.rootShift, index - iRootSize);
        }
        ObjectRef objectRef = new ObjectRef(this.tail[0]);
        removeFromTailAt(removeFromRootAt(this.root, this.rootShift, index, objectRef), iRootSize, this.rootShift, 0);
        return (E) objectRef.getValue();
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int index, E element) {
        ListImplementation.checkElementIndex$runtime_release(index, size());
        if (rootSize() > index) {
            ObjectRef objectRef = new ObjectRef(null);
            this.root = setInRoot(this.root, this.rootShift, index, element, objectRef);
            return (E) objectRef.getValue();
        }
        Object[] objArrMakeMutable = makeMutable(this.tail);
        if (objArrMakeMutable != this.tail) {
            ((AbstractList) this).modCount++;
        }
        int i10 = index & 31;
        E e5 = (E) objArrMakeMutable[i10];
        objArrMakeMutable[i10] = element;
        this.tail = objArrMakeMutable;
        return e5;
    }

    public final void setRootShift$runtime_release(int i10) {
        this.rootShift = i10;
    }

    private final boolean removeAll(l<? super E, Boolean> predicate) {
        int iTailSize = tailSize();
        ObjectRef objectRef = new ObjectRef(null);
        if (this.root == null) {
            return removeAllFromTail(predicate, iTailSize, objectRef) != iTailSize;
        }
        ListIterator<Object[]> listIteratorLeafBufferIterator = leafBufferIterator(0);
        int iRemoveAll = 32;
        while (iRemoveAll == 32 && listIteratorLeafBufferIterator.hasNext()) {
            iRemoveAll = removeAll(predicate, listIteratorLeafBufferIterator.next(), 32, objectRef);
        }
        if (iRemoveAll == 32) {
            CommonFunctionsKt.m3083assert(!listIteratorLeafBufferIterator.hasNext());
            int iRemoveAllFromTail = removeAllFromTail(predicate, iTailSize, objectRef);
            if (iRemoveAllFromTail == 0) {
                pullLastBufferFromRoot(this.root, size(), this.rootShift);
            }
            return iRemoveAllFromTail != iTailSize;
        }
        int iPreviousIndex = listIteratorLeafBufferIterator.previousIndex() << 5;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iRecyclableRemoveAll = iRemoveAll;
        while (listIteratorLeafBufferIterator.hasNext()) {
            iRecyclableRemoveAll = recyclableRemoveAll(predicate, listIteratorLeafBufferIterator.next(), 32, iRecyclableRemoveAll, objectRef, arrayList2, arrayList);
        }
        int iRecyclableRemoveAll2 = recyclableRemoveAll(predicate, this.tail, iTailSize, iRecyclableRemoveAll, objectRef, arrayList2, arrayList);
        Object[] objArr = (Object[]) objectRef.getValue();
        Arrays.fill(objArr, iRecyclableRemoveAll2, 32, (Object) null);
        Object[] objArrPushBuffers = arrayList.isEmpty() ? this.root : pushBuffers(this.root, iPreviousIndex, this.rootShift, arrayList.iterator());
        int size = iPreviousIndex + (arrayList.size() << 5);
        this.root = retainFirst(objArrPushBuffers, size);
        this.tail = objArr;
        this.size = size + iRecyclableRemoveAll2;
        return true;
    }

    private final int tailSize() {
        return tailSize(size());
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection.Builder
    public PersistentList<E> build() {
        PersistentVector persistentVectorPersistentVectorOf;
        if (this.root == this.vectorRoot && this.tail == this.vectorTail) {
            persistentVectorPersistentVectorOf = this.vector;
        } else {
            this.ownership = new MutabilityOwnership();
            Object[] objArr = this.root;
            this.vectorRoot = objArr;
            Object[] objArr2 = this.tail;
            this.vectorTail = objArr2;
            persistentVectorPersistentVectorOf = objArr == null ? objArr2.length == 0 ? UtilsKt.persistentVectorOf() : new SmallPersistentVector(Arrays.copyOf(this.tail, size())) : new PersistentVector(objArr, objArr2, size(), this.rootShift);
        }
        this.vector = persistentVectorPersistentVectorOf;
        return (PersistentList<E>) persistentVectorPersistentVectorOf;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int index) {
        ListImplementation.checkPositionIndex$runtime_release(index, size());
        return new PersistentVectorMutableIterator(this, index);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractList, java.util.List
    public void add(int index, E element) {
        ListImplementation.checkPositionIndex$runtime_release(index, size());
        if (index == size()) {
            add(element);
            return;
        }
        ((AbstractList) this).modCount++;
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            insertIntoTail(this.root, index - iRootSize, element);
        } else {
            ObjectRef objectRef = new ObjectRef(null);
            insertIntoTail(insertIntoRoot(this.root, this.rootShift, index, element, objectRef), 0, objectRef.getValue());
        }
    }

    private final void insertIntoRoot(Collection<? extends E> elements, int index, int rightShift, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        if (this.root != null) {
            int i10 = index >> 5;
            int iRootSize = nullBuffers - (((rootSize() >> 5) - 1) - i10);
            splitToBuffers(elements, index, shiftLeafBuffers(i10, rightShift, buffers, nullBuffers, nextBuffer), 32, buffers, iRootSize, iRootSize < nullBuffers ? buffers[iRootSize] : nextBuffer);
            return;
        }
        throw new IllegalStateException("root is null");
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends E> elements) {
        PersistentVectorBuilder<E> persistentVectorBuilder;
        Collection<? extends E> collection;
        Object[] objArrMutableBuffer;
        ListImplementation.checkPositionIndex$runtime_release(index, size());
        if (index == size()) {
            return addAll(elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i10 = (index >> 5) << 5;
        int size = ((elements.size() + (size() - i10)) - 1) / 32;
        if (size == 0) {
            CommonFunctionsKt.m3083assert(index >= rootSize());
            int i11 = index & 31;
            int size2 = ((elements.size() + index) - 1) & 31;
            Object[] objArr = this.tail;
            Object[] objArrMakeMutable = makeMutable(objArr);
            System.arraycopy(objArr, i11, objArrMakeMutable, size2 + 1, tailSize() - i11);
            copyToBuffer(objArrMakeMutable, i11, elements.iterator());
            this.tail = objArrMakeMutable;
            this.size = elements.size() + size();
            return true;
        }
        Object[][] objArr2 = new Object[size][];
        int iTailSize = tailSize();
        int iTailSize2 = tailSize(elements.size() + size());
        if (index >= rootSize()) {
            objArrMutableBuffer = mutableBuffer();
            persistentVectorBuilder = this;
            collection = elements;
            persistentVectorBuilder.splitToBuffers(collection, index, this.tail, iTailSize, objArr2, size, objArrMutableBuffer);
            objArr2 = objArr2;
        } else {
            persistentVectorBuilder = this;
            collection = elements;
            if (iTailSize2 > iTailSize) {
                int i12 = iTailSize2 - iTailSize;
                Object[] objArrMakeMutableShiftingRight = makeMutableShiftingRight(persistentVectorBuilder.tail, i12);
                persistentVectorBuilder.insertIntoRoot(collection, index, i12, objArr2, size, objArrMakeMutableShiftingRight);
                objArr2 = objArr2;
                objArrMutableBuffer = objArrMakeMutableShiftingRight;
            } else {
                Object[] objArr3 = persistentVectorBuilder.tail;
                objArrMutableBuffer = mutableBuffer();
                int i13 = iTailSize - iTailSize2;
                System.arraycopy(objArr3, i13, objArrMutableBuffer, 0, iTailSize - i13);
                int i14 = 32 - i13;
                Object[] objArrMakeMutableShiftingRight2 = makeMutableShiftingRight(persistentVectorBuilder.tail, i14);
                int i15 = size - 1;
                objArr2[i15] = objArrMakeMutableShiftingRight2;
                persistentVectorBuilder.insertIntoRoot(collection, index, i14, objArr2, i15, objArrMakeMutableShiftingRight2);
                persistentVectorBuilder = persistentVectorBuilder;
                collection = collection;
            }
        }
        persistentVectorBuilder.root = pushBuffersIncreasingHeightIfNeeded(persistentVectorBuilder.root, i10, objArr2);
        persistentVectorBuilder.tail = objArrMutableBuffer;
        persistentVectorBuilder.size = collection.size() + size();
        return true;
    }

    private final int removeAll(l<? super E, Boolean> predicate, Object[] buffer, int bufferSize, ObjectRef bufferRef) {
        Object[] objArrMakeMutable = buffer;
        int i10 = bufferSize;
        boolean z = false;
        for (int i11 = 0; i11 < bufferSize; i11++) {
            Object obj = buffer[i11];
            if (((Boolean) predicate.invoke(obj)).booleanValue()) {
                if (!z) {
                    objArrMakeMutable = makeMutable(buffer);
                    z = true;
                    i10 = i11;
                }
            } else if (z) {
                objArrMakeMutable[i10] = obj;
                i10++;
            }
        }
        bufferRef.setValue(objArrMakeMutable);
        return i10;
    }
}
