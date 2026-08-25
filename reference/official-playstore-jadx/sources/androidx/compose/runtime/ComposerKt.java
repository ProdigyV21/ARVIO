package androidx.compose.runtime;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import t.d0;
import t.k0;
import t.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a5\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\b\u0005H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\u000b\u0010\r\u001a'\u0010\u0010\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u000f\u0010\u0012\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001f\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\nH\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a/\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\nH\u0007¢\u0006\u0004\b\u0015\u0010\u0019\u001a\u000f\u0010\u001a\u001a\u00020\fH\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0017\u0010\u001c\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001b\u0010!\u001a\u00020\f*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0004\b!\u0010\"\u001a>\u0010(\u001a\u00020\f\"\u0004\b\u0000\u0010#*\u00020\u001e2\b\u0010%\u001a\u0004\u0018\u00010$2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00028\u00000&H\u0080\b¢\u0006\u0004\b(\u0010)\u001a\u001b\u0010*\u001a\u00020\f*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0004\b*\u0010\"\u001a-\u0010/\u001a\u00020\f*\u00020\u001e2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010-H\u0002¢\u0006\u0004\b/\u00100\u001a3\u00105\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000104\"\u0004\b\u0000\u00101\"\b\b\u0001\u00102*\u00020-2\u0006\u00103\u001a\u00020\u000eH\u0002¢\u0006\u0004\b5\u00106\u001a/\u0010:\u001a\u0004\u0018\u00010-2\b\u00107\u001a\u0004\u0018\u00010-2\b\u00108\u001a\u0004\u0018\u00010-2\b\u00109\u001a\u0004\u0018\u00010-H\u0002¢\u0006\u0004\b:\u0010;\u001a!\u0010?\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010>\u001a\u00020\u000eH\u0002¢\u0006\u0004\b?\u0010@\u001a!\u0010A\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010>\u001a\u00020\u000eH\u0002¢\u0006\u0004\bA\u0010@\u001a3\u0010E\u001a\u00020\f*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010>\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020B2\b\u0010D\u001a\u0004\u0018\u00010-H\u0002¢\u0006\u0004\bE\u0010F\u001a+\u0010I\u001a\u0004\u0018\u00010=*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002¢\u0006\u0004\bI\u0010J\u001a#\u0010K\u001a\u0004\u0018\u00010=*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010>\u001a\u00020\u000eH\u0002¢\u0006\u0004\bK\u0010L\u001a)\u0010M\u001a\u00020\f*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002¢\u0006\u0004\bM\u0010N\u001a/\u0010O\u001a\b\u0012\u0004\u0012\u00020=0<*\b\u0012\u0004\u0012\u00020=0<2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002¢\u0006\u0004\bO\u0010P\u001a\u0013\u0010Q\u001a\u00020\u000e*\u00020\u0002H\u0002¢\u0006\u0004\bQ\u0010R\u001a\u0013\u0010S\u001a\u00020\u0002*\u00020\u000eH\u0002¢\u0006\u0004\bS\u0010T\u001a#\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0V*\u00020U2\u0006\u0010%\u001a\u00020$H\u0002¢\u0006\u0004\bW\u0010X\u001a#\u0010[\u001a\u00020\u000e*\u00020Y2\u0006\u0010,\u001a\u00020\u000e2\u0006\u0010Z\u001a\u00020\u000eH\u0002¢\u0006\u0004\b[\u0010\\\u001a+\u0010`\u001a\u00020\u000e*\u00020Y2\u0006\u0010]\u001a\u00020\u000e2\u0006\u0010^\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000eH\u0002¢\u0006\u0004\b`\u0010a\u001a1\u0010c\u001a\u00020\f2\u0006\u00107\u001a\u00020\u00022\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0\u0004H\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001¢\u0006\u0004\bc\u0010d\u001a\u0017\u0010c\u001a\u00020\f2\u0006\u00107\u001a\u00020\u0002H\u0000¢\u0006\u0004\bc\u0010e\u001a\u0017\u0010h\u001a\u00020g2\u0006\u0010f\u001a\u00020\nH\u0000¢\u0006\u0004\bh\u0010i\u001a\u0017\u0010j\u001a\u00020\f2\u0006\u0010f\u001a\u00020\nH\u0000¢\u0006\u0004\bj\u0010k\"\u001e\u0010m\u001a\u0004\u0018\u00010l8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\bm\u0010n\u0012\u0004\bo\u0010\u001b\"\u0014\u0010p\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\bp\u0010q\"\u0014\u0010r\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\br\u0010q\"\u0014\u0010s\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\bs\u0010q\"\u001a\u0010t\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\f\n\u0004\bt\u0010q\u0012\u0004\bu\u0010\u001b\" \u0010v\u001a\u00020-8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bv\u0010w\u0012\u0004\bz\u0010\u001b\u001a\u0004\bx\u0010y\"\u001a\u0010{\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\f\n\u0004\b{\u0010q\u0012\u0004\b|\u0010\u001b\" \u0010}\u001a\u00020-8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b}\u0010w\u0012\u0004\b\u007f\u0010\u001b\u001a\u0004\b~\u0010y\"\u001d\u0010\u0080\u0001\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\u000e\n\u0005\b\u0080\u0001\u0010q\u0012\u0005\b\u0081\u0001\u0010\u001b\"$\u0010\u0082\u0001\u001a\u00020-8\u0000X\u0081\u0004¢\u0006\u0015\n\u0005\b\u0082\u0001\u0010w\u0012\u0005\b\u0084\u0001\u0010\u001b\u001a\u0005\b\u0083\u0001\u0010y\"\u001d\u0010\u0085\u0001\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\u000e\n\u0005\b\u0085\u0001\u0010q\u0012\u0005\b\u0086\u0001\u0010\u001b\"$\u0010\u0087\u0001\u001a\u00020-8\u0000X\u0081\u0004¢\u0006\u0015\n\u0005\b\u0087\u0001\u0010w\u0012\u0005\b\u0089\u0001\u0010\u001b\u001a\u0005\b\u0088\u0001\u0010y\"\u001d\u0010\u008a\u0001\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\u000e\n\u0005\b\u008a\u0001\u0010q\u0012\u0005\b\u008b\u0001\u0010\u001b\"$\u0010\u008c\u0001\u001a\u00020-8\u0000X\u0081\u0004¢\u0006\u0015\n\u0005\b\u008c\u0001\u0010w\u0012\u0005\b\u008e\u0001\u0010\u001b\u001a\u0005\b\u008d\u0001\u0010y\"\u001d\u0010\u008f\u0001\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\u000e\n\u0005\b\u008f\u0001\u0010q\u0012\u0005\b\u0090\u0001\u0010\u001b\"$\u0010\u0091\u0001\u001a\u00020-8\u0000X\u0081\u0004¢\u0006\u0015\n\u0005\b\u0091\u0001\u0010w\u0012\u0005\b\u0093\u0001\u0010\u001b\u001a\u0005\b\u0092\u0001\u0010y\"\u001d\u0010\u0094\u0001\u001a\u00020\u000e8\u0000X\u0081T¢\u0006\u000e\n\u0005\b\u0094\u0001\u0010q\u0012\u0005\b\u0095\u0001\u0010\u001b\"\u0016\u0010\u0096\u0001\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0007\n\u0005\b\u0096\u0001\u0010q\"\u001e\u0010\u0098\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u0097\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001\"\u001b\u0010\u009a\u0001\u001a\u00020\u0002*\u00020\u001e8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001\"\u001b\u0010\u009a\u0001\u001a\u00020\u0002*\u00020Y8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009c\u0001\"\u001c\u0010 \u0001\u001a\u00020-*\u00030\u009d\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001¨\u0006¡\u0001"}, d2 = {"T", "Landroidx/compose/runtime/Composer;", "", "invalid", "Lkotlin/Function0;", "Landroidx/compose/runtime/DisallowComposableCalls;", "block", "cache", "(Landroidx/compose/runtime/Composer;ZLr7/a;)Ljava/lang/Object;", "composer", "", "sourceInformation", "Lx6/t0;", "(Landroidx/compose/runtime/Composer;Ljava/lang/String;)V", "", "key", "sourceInformationMarkerStart", "(Landroidx/compose/runtime/Composer;ILjava/lang/String;)V", "isTraceInProgress", "()Z", "info", "traceEventStart", "(ILjava/lang/String;)V", "dirty1", "dirty2", "(IIILjava/lang/String;)V", "traceEventEnd", "()V", "sourceInformationMarkerEnd", "(Landroidx/compose/runtime/Composer;)V", "Landroidx/compose/runtime/SlotWriter;", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "removeCurrentGroup", "(Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "R", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "Lkotlin/Function2;", "cb", "withAfterAnchorInfo", "(Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/Anchor;Lr7/p;)V", "deactivateCurrentGroup", "group", "index", "", "data", "removeData", "(Landroidx/compose/runtime/SlotWriter;IILjava/lang/Object;)V", "K", "V", "initialCapacity", "Landroidx/compose/runtime/MutableScatterMultiMap;", "multiMap", "(I)Lt/z;", "value", TtmlNode.LEFT, TtmlNode.RIGHT, "getKey", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "", "Landroidx/compose/runtime/Invalidation;", "location", "findLocation", "(Ljava/util/List;I)I", "findInsertLocation", "Landroidx/compose/runtime/RecomposeScopeImpl;", "scope", "instance", "insertIfMissing", "(Ljava/util/List;ILandroidx/compose/runtime/RecomposeScopeImpl;Ljava/lang/Object;)V", TtmlNode.START, TtmlNode.END, "firstInRange", "(Ljava/util/List;II)Landroidx/compose/runtime/Invalidation;", "removeLocation", "(Ljava/util/List;I)Landroidx/compose/runtime/Invalidation;", "removeRange", "(Ljava/util/List;II)V", "filterToRange", "(Ljava/util/List;II)Ljava/util/List;", "asInt", "(Z)I", "asBool", "(I)Z", "Landroidx/compose/runtime/SlotTable;", "", "collectNodesFrom", "(Landroidx/compose/runtime/SlotTable;Landroidx/compose/runtime/Anchor;)Ljava/util/List;", "Landroidx/compose/runtime/SlotReader;", "root", "distanceFrom", "(Landroidx/compose/runtime/SlotReader;II)I", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "common", "nearestCommonRootOf", "(Landroidx/compose/runtime/SlotReader;III)I", "lazyMessage", "runtimeCheck", "(ZLr7/a;)V", "(Z)V", "message", "", "composeRuntimeError", "(Ljava/lang/String;)Ljava/lang/Void;", "composeImmediateRuntimeError", "(Ljava/lang/String;)V", "Landroidx/compose/runtime/CompositionTracer;", "compositionTracer", "Landroidx/compose/runtime/CompositionTracer;", "getCompositionTracer$annotations", "rootKey", "I", "nodeKey", "defaultsKey", "invocationKey", "getInvocationKey$annotations", "invocation", "Ljava/lang/Object;", "getInvocation", "()Ljava/lang/Object;", "getInvocation$annotations", "providerKey", "getProviderKey$annotations", "provider", "getProvider", "getProvider$annotations", "compositionLocalMapKey", "getCompositionLocalMapKey$annotations", "compositionLocalMap", "getCompositionLocalMap", "getCompositionLocalMap$annotations", "providerValuesKey", "getProviderValuesKey$annotations", "providerValues", "getProviderValues", "getProviderValues$annotations", "providerMapsKey", "getProviderMapsKey$annotations", "providerMaps", "getProviderMaps", "getProviderMaps$annotations", "referenceKey", "getReferenceKey$annotations", "reference", "getReference", "getReference$annotations", "reuseKey", "getReuseKey$annotations", "invalidGroupLocation", "Ljava/util/Comparator;", "InvalidationLocationAscending", "Ljava/util/Comparator;", "isAfterFirstChild", "(Landroidx/compose/runtime/SlotWriter;)Z", "(Landroidx/compose/runtime/SlotReader;)Z", "Landroidx/compose/runtime/KeyInfo;", "getJoinedKey", "(Landroidx/compose/runtime/KeyInfo;)Ljava/lang/Object;", "joinedKey", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ComposerKt {
    public static final int compositionLocalMapKey = 202;
    private static CompositionTracer compositionTracer = null;
    private static final int defaultsKey = -127;
    private static final int invalidGroupLocation = -2;
    public static final int invocationKey = 200;
    private static final int nodeKey = 125;
    public static final int providerKey = 201;
    public static final int providerMapsKey = 204;
    public static final int providerValuesKey = 203;
    public static final int referenceKey = 206;
    public static final int reuseKey = 207;
    private static final int rootKey = 100;
    private static final Object invocation = new OpaqueKey("provider");
    private static final Object provider = new OpaqueKey("provider");
    private static final Object compositionLocalMap = new OpaqueKey("compositionLocalMap");
    private static final Object providerValues = new OpaqueKey("providerValues");
    private static final Object providerMaps = new OpaqueKey("providers");
    private static final Object reference = new OpaqueKey("reference");
    private static final Comparator<Invalidation> InvalidationLocationAscending = new c();

    /* JADX INFO: Access modifiers changed from: private */
    public static final int InvalidationLocationAscending$lambda$15(Invalidation invalidation, Invalidation invalidation2) {
        return p.c(invalidation.getLocation(), invalidation2.getLocation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int i10) {
        return i10 != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    @ComposeCompilerApi
    public static final <T> T cache(Composer composer, boolean z, r7.a<? extends T> aVar) {
        T t2 = (T) composer.rememberedValue();
        if (!z && t2 != Composer.INSTANCE.getEmpty()) {
            return t2;
        }
        T t10 = (T) aVar.invoke();
        composer.updateRememberedValue(t10);
        return t10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> collectNodesFrom(SlotTable slotTable, Anchor anchor) {
        ArrayList arrayList = new ArrayList();
        SlotReader slotReaderOpenReader = slotTable.openReader();
        try {
            collectNodesFrom$lambda$10$collectFromGroup(slotReaderOpenReader, arrayList, slotTable.anchorIndex(anchor));
            return arrayList;
        } finally {
            slotReaderOpenReader.close();
        }
    }

    private static final void collectNodesFrom$lambda$10$collectFromGroup(SlotReader slotReader, List<Object> list, int i10) {
        if (slotReader.isNode(i10)) {
            list.add(slotReader.node(i10));
            return;
        }
        int iGroupSize = i10 + 1;
        int iGroupSize2 = slotReader.groupSize(i10) + i10;
        while (iGroupSize < iGroupSize2) {
            collectNodesFrom$lambda$10$collectFromGroup(slotReader, list, iGroupSize);
            iGroupSize += slotReader.groupSize(iGroupSize);
        }
    }

    public static final void composeImmediateRuntimeError(String str) {
        throw new ComposeRuntimeError(a0.c.l("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (", str, "). Please report to Google or use https://goo.gle/compose-feedback"));
    }

    public static final Void composeRuntimeError(String str) {
        throw new ComposeRuntimeError(a0.c.l("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (", str, "). Please report to Google or use https://goo.gle/compose-feedback"));
    }

    public static final void deactivateCurrentGroup(SlotWriter slotWriter, RememberManager rememberManager) {
        int iAnchorIndex;
        int slotsSize;
        int currentGroup = slotWriter.getCurrentGroup();
        int currentGroupEnd = slotWriter.getCurrentGroupEnd();
        while (currentGroup < currentGroupEnd) {
            Object objNode = slotWriter.node(currentGroup);
            if (objNode instanceof ComposeNodeLifecycleCallback) {
                rememberManager.deactivating((ComposeNodeLifecycleCallback) objNode, slotWriter.getSlotsSize() - slotWriter.slotsStartIndex$runtime_release(currentGroup), -1, -1);
            }
            int iSlotIndex = slotWriter.slotIndex(slotWriter.groups, slotWriter.groupIndexToAddress(currentGroup));
            int i10 = currentGroup + 1;
            int iDataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i10));
            for (int i11 = iSlotIndex; i11 < iDataIndex; i11++) {
                int i12 = i11 - iSlotIndex;
                Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(i11)];
                if (obj instanceof RememberObserverHolder) {
                    RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                    RememberObserver wrapped = rememberObserverHolder.getWrapped();
                    if (!(wrapped instanceof ReusableRememberObserver)) {
                        removeData(slotWriter, currentGroup, i12, obj);
                        int slotsSize2 = slotWriter.getSlotsSize() - i12;
                        Anchor after = rememberObserverHolder.getAfter();
                        if (after == null || !after.getValid()) {
                            iAnchorIndex = -1;
                            slotsSize = -1;
                        } else {
                            iAnchorIndex = slotWriter.anchorIndex(after);
                            slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
                        }
                        rememberManager.forgetting(wrapped, slotsSize2, iAnchorIndex, slotsSize);
                    }
                } else if (obj instanceof RecomposeScopeImpl) {
                    removeData(slotWriter, currentGroup, i12, obj);
                    ((RecomposeScopeImpl) obj).release();
                }
            }
            currentGroup = i10;
        }
    }

    private static final int distanceFrom(SlotReader slotReader, int i10, int i11) {
        int i12 = 0;
        while (i10 > 0 && i10 != i11) {
            i10 = slotReader.parent(i10);
            i12++;
        }
        return i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Invalidation> filterToRange(List<Invalidation> list, int i10, int i11) {
        ArrayList arrayList = new ArrayList();
        for (int iFindInsertLocation = findInsertLocation(list, i10); iFindInsertLocation < list.size(); iFindInsertLocation++) {
            Invalidation invalidation = list.get(iFindInsertLocation);
            if (invalidation.getLocation() >= i11) {
                break;
            }
            arrayList.add(invalidation);
        }
        return arrayList;
    }

    private static final int findInsertLocation(List<Invalidation> list, int i10) {
        int iFindLocation = findLocation(list, i10);
        return iFindLocation < 0 ? -(iFindLocation + 1) : iFindLocation;
    }

    private static final int findLocation(List<Invalidation> list, int i10) {
        int size = list.size() - 1;
        int i11 = 0;
        while (i11 <= size) {
            int i12 = (i11 + size) >>> 1;
            int iC = p.c(list.get(i12).getLocation(), i10);
            if (iC < 0) {
                i11 = i12 + 1;
            } else {
                if (iC <= 0) {
                    return i12;
                }
                size = i12 - 1;
            }
        }
        return -(i11 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation firstInRange(List<Invalidation> list, int i10, int i11) {
        int iFindInsertLocation = findInsertLocation(list, i10);
        if (iFindInsertLocation >= list.size()) {
            return null;
        }
        Invalidation invalidation = list.get(iFindInsertLocation);
        if (invalidation.getLocation() < i11) {
            return invalidation;
        }
        return null;
    }

    public static final Object getCompositionLocalMap() {
        return compositionLocalMap;
    }

    public static /* synthetic */ void getCompositionLocalMap$annotations() {
    }

    public static /* synthetic */ void getCompositionLocalMapKey$annotations() {
    }

    private static /* synthetic */ void getCompositionTracer$annotations() {
    }

    public static final Object getInvocation() {
        return invocation;
    }

    public static /* synthetic */ void getInvocation$annotations() {
    }

    public static /* synthetic */ void getInvocationKey$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getJoinedKey(KeyInfo keyInfo) {
        return keyInfo.getObjectKey() != null ? new JoinedKey(Integer.valueOf(keyInfo.getKey()), keyInfo.getObjectKey()) : Integer.valueOf(keyInfo.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getKey(Object obj, Object obj2, Object obj3) {
        JoinedKey joinedKey = obj instanceof JoinedKey ? (JoinedKey) obj : null;
        if (joinedKey == null) {
            return null;
        }
        if (p.a(joinedKey.getLeft(), obj2) && p.a(joinedKey.getRight(), obj3)) {
            return obj;
        }
        Object key = getKey(joinedKey.getLeft(), obj2, obj3);
        return key == null ? getKey(joinedKey.getRight(), obj2, obj3) : key;
    }

    public static final Object getProvider() {
        return provider;
    }

    public static /* synthetic */ void getProvider$annotations() {
    }

    public static /* synthetic */ void getProviderKey$annotations() {
    }

    public static final Object getProviderMaps() {
        return providerMaps;
    }

    public static /* synthetic */ void getProviderMaps$annotations() {
    }

    public static /* synthetic */ void getProviderMapsKey$annotations() {
    }

    public static final Object getProviderValues() {
        return providerValues;
    }

    public static /* synthetic */ void getProviderValues$annotations() {
    }

    public static /* synthetic */ void getProviderValuesKey$annotations() {
    }

    public static final Object getReference() {
        return reference;
    }

    public static /* synthetic */ void getReference$annotations() {
    }

    public static /* synthetic */ void getReferenceKey$annotations() {
    }

    public static /* synthetic */ void getReuseKey$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertIfMissing(List<Invalidation> list, int i10, RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        int iFindLocation = findLocation(list, i10);
        if (iFindLocation < 0) {
            int i11 = -(iFindLocation + 1);
            if (!(obj instanceof DerivedState)) {
                obj = null;
            }
            list.add(i11, new Invalidation(recomposeScopeImpl, i10, obj));
            return;
        }
        Invalidation invalidation = list.get(iFindLocation);
        if (!(obj instanceof DerivedState)) {
            invalidation.setInstances(null);
            return;
        }
        Object instances = invalidation.getInstances();
        if (instances == null) {
            invalidation.setInstances(obj);
            return;
        }
        if (instances instanceof d0) {
            ((d0) instances).d(obj);
            return;
        }
        int i12 = k0.f21841a;
        d0 d0Var = new d0(2);
        d0Var.f21836b[d0Var.f(instances)] = instances;
        d0Var.f21836b[d0Var.f(obj)] = obj;
        invalidation.setInstances(d0Var);
    }

    public static final boolean isAfterFirstChild(SlotWriter slotWriter) {
        return slotWriter.getCurrentGroup() > slotWriter.getParent() + 1;
    }

    @ComposeCompilerApi
    public static final boolean isTraceInProgress() {
        CompositionTracer compositionTracer2 = compositionTracer;
        return compositionTracer2 != null && compositionTracer2.isTraceInProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> z multiMap(int i10) {
        return MutableScatterMultiMap.m2966constructorimpl(new z(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nearestCommonRootOf(SlotReader slotReader, int i10, int i11, int i12) {
        if (i10 != i11) {
            if (i10 == i12 || i11 == i12) {
                return i12;
            }
            if (slotReader.parent(i10) == i11) {
                return i11;
            }
            if (slotReader.parent(i11) != i10) {
                if (slotReader.parent(i10) == slotReader.parent(i11)) {
                    return slotReader.parent(i10);
                }
                int iDistanceFrom = distanceFrom(slotReader, i10, i12);
                int iDistanceFrom2 = distanceFrom(slotReader, i11, i12);
                int i13 = iDistanceFrom - iDistanceFrom2;
                for (int i14 = 0; i14 < i13; i14++) {
                    i10 = slotReader.parent(i10);
                }
                int i15 = iDistanceFrom2 - iDistanceFrom;
                for (int i16 = 0; i16 < i15; i16++) {
                    i11 = slotReader.parent(i11);
                }
                while (i10 != i11) {
                    i10 = slotReader.parent(i10);
                    i11 = slotReader.parent(i11);
                }
                return i10;
            }
        }
        return i10;
    }

    public static final void removeCurrentGroup(SlotWriter slotWriter, RememberManager rememberManager) {
        int slotsSize;
        int iDataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.groupSize(slotWriter.getCurrentGroup()) + slotWriter.getCurrentGroup()));
        for (int iDataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.getCurrentGroup())); iDataIndex2 < iDataIndex; iDataIndex2++) {
            Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(iDataIndex2)];
            int iAnchorIndex = -1;
            if (obj instanceof ComposeNodeLifecycleCallback) {
                rememberManager.releasing((ComposeNodeLifecycleCallback) obj, slotWriter.getSlotsSize() - iDataIndex2, -1, -1);
            }
            if (obj instanceof RememberObserverHolder) {
                int slotsSize2 = slotWriter.getSlotsSize() - iDataIndex2;
                RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                Anchor after = rememberObserverHolder.getAfter();
                if (after == null || !after.getValid()) {
                    slotsSize = -1;
                } else {
                    iAnchorIndex = slotWriter.anchorIndex(after);
                    slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
                }
                rememberManager.forgetting(rememberObserverHolder.getWrapped(), slotsSize2, iAnchorIndex, slotsSize);
            }
            if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }
        slotWriter.removeGroup();
    }

    private static final void removeData(SlotWriter slotWriter, int i10, int i11, Object obj) {
        if (obj == slotWriter.set(i10, i11, Composer.INSTANCE.getEmpty())) {
            return;
        }
        composeImmediateRuntimeError("Slot table is out of sync");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation removeLocation(List<Invalidation> list, int i10) {
        int iFindLocation = findLocation(list, i10);
        if (iFindLocation >= 0) {
            return list.remove(iFindLocation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeRange(List<Invalidation> list, int i10, int i11) {
        int iFindInsertLocation = findInsertLocation(list, i10);
        while (iFindInsertLocation < list.size() && list.get(iFindInsertLocation).getLocation() < i11) {
            list.remove(iFindInsertLocation);
        }
    }

    public static final void runtimeCheck(boolean z, r7.a<String> aVar) {
        if (z) {
            return;
        }
        composeImmediateRuntimeError((String) aVar.invoke());
    }

    @ComposeCompilerApi
    public static final void sourceInformation(Composer composer, String str) {
        composer.sourceInformation(str);
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerEnd(Composer composer) {
        composer.sourceInformationMarkerEnd();
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerStart(Composer composer, int i10, String str) {
        composer.sourceInformationMarkerStart(i10, str);
    }

    @ComposeCompilerApi
    public static final void traceEventEnd() {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventEnd();
        }
    }

    public static final <R> void withAfterAnchorInfo(SlotWriter slotWriter, Anchor anchor, r7.p<? super Integer, ? super Integer, ? extends R> pVar) {
        int iAnchorIndex;
        int slotsSize;
        if (anchor == null || !anchor.getValid()) {
            iAnchorIndex = -1;
            slotsSize = -1;
        } else {
            iAnchorIndex = slotWriter.anchorIndex(anchor);
            slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
        }
        pVar.invoke(Integer.valueOf(iAnchorIndex), Integer.valueOf(slotsSize));
    }

    public static final boolean isAfterFirstChild(SlotReader slotReader) {
        return slotReader.getCurrentGroup() > slotReader.getParent() + 1;
    }

    public static final void runtimeCheck(boolean z) {
        if (z) {
            return;
        }
        composeImmediateRuntimeError("Check failed");
    }

    @ComposeCompilerApi
    public static final void traceEventStart(int i10, int i11, int i12, String str) {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventStart(i10, i11, i12, str);
        }
    }
}
