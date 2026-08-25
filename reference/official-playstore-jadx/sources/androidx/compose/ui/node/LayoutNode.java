package androidx.compose.ui.node;

import android.view.View;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.JvmActuals_jvmKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.platform.q;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ø\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\n\b\u0000\u0018\u0000 \u0083\u00032\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007:\b\u0083\u0003\u0084\u0003\u0085\u0003\u0086\u0003B\u001b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J$\u0010\u0014\u001a\u00020\u000e2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0\u0012H\u0086\b¢\u0006\u0004\b\u0014\u0010\u0015J*\u0010\u0017\u001a\u00020\u000e2\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0\u0016H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aH\u0017¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010!\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010#\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\"\u0010\u0010J\u001f\u0010'\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0000¢\u0006\u0004\b%\u0010&J\u000f\u0010)\u001a\u00020\u000eH\u0000¢\u0006\u0004\b(\u0010\u0010J'\u0010.\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0000¢\u0006\u0004\b,\u0010-J\u000f\u00100\u001a\u00020\u000eH\u0000¢\u0006\u0004\b/\u0010\u0010J\u0017\u00105\u001a\u00020\u000e2\u0006\u00102\u001a\u000201H\u0000¢\u0006\u0004\b3\u00104J\u000f\u00107\u001a\u00020\u000eH\u0000¢\u0006\u0004\b6\u0010\u0010J\u000f\u00109\u001a\u000208H\u0016¢\u0006\u0004\b9\u0010:J\u000f\u0010<\u001a\u00020\u000eH\u0000¢\u0006\u0004\b;\u0010\u0010J\u000f\u0010>\u001a\u00020\u000eH\u0000¢\u0006\u0004\b=\u0010\u0010J\u001f\u0010B\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\nH\u0000¢\u0006\u0004\bA\u0010&J\u000f\u0010D\u001a\u00020\u000eH\u0000¢\u0006\u0004\bC\u0010\u0010J\u000f\u0010F\u001a\u00020\u000eH\u0000¢\u0006\u0004\bE\u0010\u0010J\u0017\u0010K\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020GH\u0000¢\u0006\u0004\bI\u0010JJ6\u0010T\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020L2\u0006\u0010O\u001a\u00020N2\b\b\u0002\u0010P\u001a\u00020\b2\b\b\u0002\u0010Q\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ6\u0010W\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020L2\u0006\u0010U\u001a\u00020N2\b\b\u0002\u0010P\u001a\u00020\b2\b\b\u0002\u0010Q\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\bV\u0010SJ\u0017\u0010[\u001a\u00020\u000e2\u0006\u0010X\u001a\u00020\u0000H\u0000¢\u0006\u0004\bY\u0010ZJ#\u0010`\u001a\u00020\u000e2\b\b\u0002\u0010\\\u001a\u00020\b2\b\b\u0002\u0010]\u001a\u00020\bH\u0000¢\u0006\u0004\b^\u0010_J#\u0010b\u001a\u00020\u000e2\b\b\u0002\u0010\\\u001a\u00020\b2\b\b\u0002\u0010]\u001a\u00020\bH\u0000¢\u0006\u0004\ba\u0010_J\u000f\u0010d\u001a\u00020\u000eH\u0000¢\u0006\u0004\bc\u0010\u0010J\u001e\u0010h\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0eH\u0080\b¢\u0006\u0004\bf\u0010gJ\u0019\u0010k\u001a\u00020\u000e2\b\b\u0002\u0010\\\u001a\u00020\bH\u0000¢\u0006\u0004\bi\u0010jJ\u0019\u0010m\u001a\u00020\u000e2\b\b\u0002\u0010\\\u001a\u00020\bH\u0000¢\u0006\u0004\bl\u0010jJ\u000f\u0010o\u001a\u00020\u000eH\u0000¢\u0006\u0004\bn\u0010\u0010J\u0015\u0010r\u001a\b\u0012\u0004\u0012\u00020q0pH\u0016¢\u0006\u0004\br\u0010sJ\u000f\u0010u\u001a\u00020\u000eH\u0000¢\u0006\u0004\bt\u0010\u0010J\u001e\u0010z\u001a\u00020\b2\n\b\u0002\u0010w\u001a\u0004\u0018\u00010vH\u0000ø\u0001\u0000¢\u0006\u0004\bx\u0010yJ\u001e\u0010|\u001a\u00020\b2\n\b\u0002\u0010w\u001a\u0004\u0018\u00010vH\u0000ø\u0001\u0000¢\u0006\u0004\b{\u0010yJ\u000f\u0010~\u001a\u00020\u000eH\u0000¢\u0006\u0004\b}\u0010\u0010J\u0010\u0010\u0080\u0001\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u007f\u0010\u0010J\u0011\u0010\u0082\u0001\u001a\u00020\u000eH\u0000¢\u0006\u0005\b\u0081\u0001\u0010\u0010J\u001a\u0010\u0084\u0001\u001a\u00020\u000e2\t\b\u0002\u0010\u0083\u0001\u001a\u00020\b¢\u0006\u0005\b\u0084\u0001\u0010jJ\u0011\u0010\u0086\u0001\u001a\u00020\u000eH\u0000¢\u0006\u0005\b\u0085\u0001\u0010\u0010J\u0011\u0010\u0087\u0001\u001a\u00020\u000eH\u0016¢\u0006\u0005\b\u0087\u0001\u0010\u0010J\u0011\u0010\u0088\u0001\u001a\u00020\u000eH\u0016¢\u0006\u0005\b\u0088\u0001\u0010\u0010J'\u0010\u008b\u0001\u001a\u00020\u000e2\u0013\u0010\u0013\u001a\u000f\u0012\u0005\u0012\u00030\u0089\u0001\u0012\u0004\u0012\u00020\u000e0\u0012H\u0080\b¢\u0006\u0005\b\u008a\u0001\u0010\u0015J'\u0010\u008e\u0001\u001a\u00020\u000e2\u0013\u0010\u0013\u001a\u000f\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020\u000e0\u0012H\u0080\b¢\u0006\u0005\b\u008d\u0001\u0010\u0015J\u0011\u0010\u0090\u0001\u001a\u00020\u000eH\u0000¢\u0006\u0005\b\u008f\u0001\u0010\u0010J\u0011\u0010\u0092\u0001\u001a\u00020\u000eH\u0000¢\u0006\u0005\b\u0091\u0001\u0010\u0010J\u0011\u0010\u0093\u0001\u001a\u00020\u000eH\u0016¢\u0006\u0005\b\u0093\u0001\u0010\u0010J\u0011\u0010\u0094\u0001\u001a\u00020\u000eH\u0016¢\u0006\u0005\b\u0094\u0001\u0010\u0010J\u0011\u0010\u0095\u0001\u001a\u00020\u000eH\u0016¢\u0006\u0005\b\u0095\u0001\u0010\u0010J\u0011\u0010\u0096\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u0096\u0001\u0010\u0010J\u0011\u0010\u0097\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u0097\u0001\u0010\u0010J\u001a\u0010\u0099\u0001\u001a\u00020\u000e2\u0007\u0010\u0098\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0005\b\u0099\u0001\u0010ZJ\u001d\u0010\u009b\u0001\u001a\u0002082\t\b\u0002\u0010\u009a\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001J\u0011\u0010\u009d\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u009d\u0001\u0010\u0010J\u0011\u0010\u009e\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u009e\u0001\u0010\u0010J\u0011\u0010\u009f\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u009f\u0001\u0010\u0010J\u0011\u0010 \u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b \u0001\u0010\u0010J\u0012\u0010¡\u0001\u001a\u00020\bH\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u0011\u0010£\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b£\u0001\u0010\u0010R\u0015\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010¤\u0001R'\u0010\u000b\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0017\n\u0005\b\u000b\u0010¥\u0001\u001a\u0006\b¦\u0001\u0010§\u0001\"\u0006\b¨\u0001\u0010©\u0001R9\u0010«\u0001\u001a\u00020\n2\u0007\u0010ª\u0001\u001a\u00020\n8W@WX\u0097\u000e¢\u0006\u001f\n\u0006\b«\u0001\u0010¥\u0001\u0012\u0005\b®\u0001\u0010\u0010\u001a\u0006\b¬\u0001\u0010§\u0001\"\u0006\b\u00ad\u0001\u0010©\u0001R(\u0010¯\u0001\u001a\u00020\b8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0006\b¯\u0001\u0010¤\u0001\u001a\u0006\b°\u0001\u0010¢\u0001\"\u0005\b±\u0001\u0010jR5\u0010³\u0001\u001a\u0004\u0018\u00010\u00002\t\u0010²\u0001\u001a\u0004\u0018\u00010\u00008\u0000@BX\u0080\u000e¢\u0006\u0017\n\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001\"\u0005\b·\u0001\u0010ZR\u0019\u0010¸\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¸\u0001\u0010¥\u0001R\u001e\u0010º\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000¹\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bº\u0001\u0010»\u0001R\"\u0010½\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u0000\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b½\u0001\u0010¾\u0001R\u0019\u0010¿\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¿\u0001\u0010¤\u0001R\u001b\u0010À\u0001\u001a\u0004\u0018\u00010\u00008\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0001\u0010´\u0001R,\u00102\u001a\u0004\u0018\u0001012\t\u0010ª\u0001\u001a\u0004\u0018\u0001018\u0000@BX\u0080\u000e¢\u0006\u000f\n\u0005\b2\u0010Á\u0001\u001a\u0006\bÂ\u0001\u0010Ã\u0001R3\u0010Æ\u0001\u001a\f\u0018\u00010Ä\u0001j\u0005\u0018\u0001`Å\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bÆ\u0001\u0010Ç\u0001\u001a\u0006\bÈ\u0001\u0010É\u0001\"\u0006\bÊ\u0001\u0010Ë\u0001R)\u0010\u009a\u0001\u001a\u00020\n8\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u009a\u0001\u0010¥\u0001\u001a\u0006\bÌ\u0001\u0010§\u0001\"\u0006\bÍ\u0001\u0010©\u0001R\u0017\u0010h\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bh\u0010¤\u0001R\u001c\u0010Ï\u0001\u001a\u0005\u0018\u00010Î\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÏ\u0001\u0010Ð\u0001R\u001e\u0010Ñ\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000¼\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÑ\u0001\u0010¾\u0001R\u0019\u0010Ò\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÒ\u0001\u0010¤\u0001R4\u0010Õ\u0001\u001a\u00030Ó\u00012\b\u0010Ô\u0001\u001a\u00030Ó\u00018\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\bÕ\u0001\u0010Ö\u0001\u001a\u0006\b×\u0001\u0010Ø\u0001\"\u0006\bÙ\u0001\u0010Ú\u0001R \u0010Ü\u0001\u001a\u00030Û\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bÜ\u0001\u0010Ý\u0001\u001a\u0006\bÞ\u0001\u0010ß\u0001R4\u0010á\u0001\u001a\u00030à\u00012\b\u0010Ô\u0001\u001a\u00030à\u00018\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\bá\u0001\u0010â\u0001\u001a\u0006\bã\u0001\u0010ä\u0001\"\u0006\bå\u0001\u0010æ\u0001R4\u0010è\u0001\u001a\u00030ç\u00012\b\u0010Ô\u0001\u001a\u00030ç\u00018\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\bè\u0001\u0010é\u0001\u001a\u0006\bê\u0001\u0010ë\u0001\"\u0006\bì\u0001\u0010í\u0001R4\u0010ï\u0001\u001a\u00030î\u00012\b\u0010Ô\u0001\u001a\u00030î\u00018\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\bï\u0001\u0010ð\u0001\u001a\u0006\bñ\u0001\u0010ò\u0001\"\u0006\bó\u0001\u0010ô\u0001R4\u0010ö\u0001\u001a\u00030õ\u00012\b\u0010Ô\u0001\u001a\u00030õ\u00018\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\bö\u0001\u0010÷\u0001\u001a\u0006\bø\u0001\u0010ù\u0001\"\u0006\bú\u0001\u0010û\u0001R*\u0010ý\u0001\u001a\u00030ü\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bý\u0001\u0010þ\u0001\u001a\u0006\bÿ\u0001\u0010\u0080\u0002\"\u0006\b\u0081\u0002\u0010\u0082\u0002R\u001a\u0010\u0083\u0002\u001a\u00030ü\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0083\u0002\u0010þ\u0001R/\u0010\u0084\u0002\u001a\u00020\b8\u0000@\u0000X\u0081\u000e¢\u0006\u001e\n\u0006\b\u0084\u0002\u0010¤\u0001\u0012\u0005\b\u0087\u0002\u0010\u0010\u001a\u0006\b\u0085\u0002\u0010¢\u0001\"\u0005\b\u0086\u0002\u0010jR \u0010\u0089\u0002\u001a\u00030\u0088\u00028\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0089\u0002\u0010\u008a\u0002\u001a\u0006\b\u008b\u0002\u0010\u008c\u0002R \u0010\u008e\u0002\u001a\u00030\u008d\u00028\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u008e\u0002\u0010\u008f\u0002\u001a\u0006\b\u0090\u0002\u0010\u0091\u0002R,\u0010\u0093\u0002\u001a\u0005\u0018\u00010\u0092\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0093\u0002\u0010\u0094\u0002\u001a\u0006\b\u0095\u0002\u0010\u0096\u0002\"\u0006\b\u0097\u0002\u0010\u0098\u0002R\u001c\u0010\u0099\u0002\u001a\u0005\u0018\u00010\u008c\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0099\u0002\u0010\u009a\u0002R(\u0010\u009b\u0002\u001a\u00020\b8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0006\b\u009b\u0002\u0010¤\u0001\u001a\u0006\b\u009c\u0002\u0010¢\u0001\"\u0005\b\u009d\u0002\u0010jR4\u0010\u009f\u0002\u001a\u00030\u009e\u00022\b\u0010Ô\u0001\u001a\u00030\u009e\u00028\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\b\u009f\u0002\u0010 \u0002\u001a\u0006\b¡\u0002\u0010¢\u0002\"\u0006\b£\u0002\u0010¤\u0002R6\u0010¥\u0002\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0006\b¥\u0002\u0010¦\u0002\u001a\u0006\b§\u0002\u0010¨\u0002\"\u0005\b©\u0002\u0010\u0015R6\u0010ª\u0002\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0006\bª\u0002\u0010¦\u0002\u001a\u0006\b«\u0002\u0010¨\u0002\"\u0005\b¬\u0002\u0010\u0015R(\u0010\u00ad\u0002\u001a\u00020\b8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0006\b\u00ad\u0002\u0010¤\u0001\u001a\u0006\b®\u0002\u0010¢\u0001\"\u0005\b¯\u0002\u0010jR*\u0010°\u0002\u001a\u00020\b2\u0007\u0010ª\u0001\u001a\u00020\b8\u0016@RX\u0096\u000e¢\u0006\u0010\n\u0006\b°\u0002\u0010¤\u0001\u001a\u0006\b°\u0002\u0010¢\u0001R\u0016\u0010±\u0002\u001a\u0004\u0018\u00010\b8F¢\u0006\b\u001a\u0006\b±\u0002\u0010²\u0002R\u001c\u0010´\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000p8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b³\u0002\u0010sR\u001d\u0010·\u0002\u001a\t\u0012\u0005\u0012\u00030µ\u00020p8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b¶\u0002\u0010sR\u001d\u0010¹\u0002\u001a\t\u0012\u0005\u0012\u00030µ\u00020p8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b¸\u0002\u0010sR\u001e\u0010¼\u0002\u001a\t\u0012\u0004\u0012\u00020\u00000¼\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bº\u0002\u0010»\u0002R\u001c\u0010¾\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000p8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b½\u0002\u0010sR\u0019\u0010À\u0002\u001a\u0004\u0018\u00010\u00008@X\u0080\u0004¢\u0006\b\u001a\u0006\b¿\u0002\u0010¶\u0001R\u0017\u0010Á\u0002\u001a\u00020\b8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÁ\u0002\u0010¢\u0001R\u0018\u0010Å\u0002\u001a\u00030Â\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bÃ\u0002\u0010Ä\u0002R\u001f\u0010É\u0002\u001a\n\u0018\u00010Æ\u0002R\u00030\u008d\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bÇ\u0002\u0010È\u0002R\u001d\u0010Í\u0002\u001a\b0Ê\u0002R\u00030\u008d\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bË\u0002\u0010Ì\u0002R\u001a\u0010Ð\u0002\u001a\u0005\u0018\u00010Î\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bÎ\u0002\u0010Ï\u0002R%\u0010Ó\u0002\u001a\t\u0012\u0004\u0012\u00020\u00000¼\u00018@X\u0081\u0004¢\u0006\u000f\u0012\u0005\bÒ\u0002\u0010\u0010\u001a\u0006\bÑ\u0002\u0010»\u0002R\u0017\u0010Ô\u0002\u001a\u00020\b8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÔ\u0002\u0010¢\u0001R\u0017\u0010Ö\u0002\u001a\u00020\b8@X\u0080\u0004¢\u0006\b\u001a\u0006\bÕ\u0002\u0010¢\u0001R\u0017\u0010Ø\u0002\u001a\u00020\n8VX\u0096\u0004¢\u0006\b\u001a\u0006\b×\u0002\u0010§\u0001R\u0017\u0010Ú\u0002\u001a\u00020\n8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÙ\u0002\u0010§\u0001R\u0017\u0010Ü\u0002\u001a\u00020\b8@X\u0080\u0004¢\u0006\b\u001a\u0006\bÛ\u0002\u0010¢\u0001R\u0018\u0010à\u0002\u001a\u00030Ý\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bÞ\u0002\u0010ß\u0002R\u0017\u0010á\u0002\u001a\u00020\b8VX\u0096\u0004¢\u0006\b\u001a\u0006\bá\u0002\u0010¢\u0001R\u0014\u0010â\u0002\u001a\u00020\b8F¢\u0006\b\u001a\u0006\bâ\u0002\u0010¢\u0001R\u0017\u0010ä\u0002\u001a\u00020\n8@X\u0080\u0004¢\u0006\b\u001a\u0006\bã\u0002\u0010§\u0001R\u0018\u0010æ\u0002\u001a\u00030ü\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bå\u0002\u0010\u0080\u0002R\u0018\u0010è\u0002\u001a\u00030ü\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bç\u0002\u0010\u0080\u0002R\u0018\u0010ë\u0002\u001a\u00030\u008c\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bé\u0002\u0010ê\u0002R\u0018\u0010í\u0002\u001a\u00030\u008c\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bì\u0002\u0010ê\u0002R\u0018\u0010ñ\u0002\u001a\u00030î\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\bï\u0002\u0010ð\u0002R\u0017\u0010ó\u0002\u001a\u00020\b8@X\u0080\u0004¢\u0006\b\u001a\u0006\bò\u0002\u0010¢\u0001R\u0017\u0010õ\u0002\u001a\u00020\b8@X\u0080\u0004¢\u0006\b\u001a\u0006\bô\u0002\u0010¢\u0001R\u0017\u0010÷\u0002\u001a\u00020\b8@X\u0080\u0004¢\u0006\b\u001a\u0006\bö\u0002\u0010¢\u0001R\u0017\u0010ù\u0002\u001a\u00020\b8@X\u0080\u0004¢\u0006\b\u001a\u0006\bø\u0002\u0010¢\u0001R\u0019\u0010ü\u0002\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\bú\u0002\u0010û\u0002R\u0018\u0010\u0080\u0003\u001a\u00030ý\u00028BX\u0082\u0004¢\u0006\b\u001a\u0006\bþ\u0002\u0010ÿ\u0002R\u001a\u0010\u0082\u0003\u001a\u0005\u0018\u00010\u008c\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0081\u0003\u0010ê\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0087\u0003"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "Landroidx/compose/ui/layout/Remeasurement;", "Landroidx/compose/ui/node/OwnerScope;", "Landroidx/compose/ui/layout/LayoutInfo;", "Landroidx/compose/ui/node/ComposeUiNode;", "Landroidx/compose/ui/node/InteroperableComposeUiNode;", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "", "isVirtual", "", "semanticsId", "<init>", "(ZI)V", "Lx6/t0;", "updateChildrenIfDirty$ui_release", "()V", "updateChildrenIfDirty", "Lkotlin/Function1;", "block", "forEachChild", "(Lr7/l;)V", "Lkotlin/Function2;", "forEachChildIndexed", "(Lr7/p;)V", "Landroid/view/View;", "Landroidx/compose/ui/viewinterop/InteropView;", "getInteropView", "()Landroid/view/View;", "index", "instance", "insertAt$ui_release", "(ILandroidx/compose/ui/node/LayoutNode;)V", "insertAt", "onZSortedChildrenInvalidated$ui_release", "onZSortedChildrenInvalidated", "count", "removeAt$ui_release", "(II)V", "removeAt", "removeAll$ui_release", "removeAll", "from", "to", "move$ui_release", "(III)V", "move", "invalidateSemantics$ui_release", "invalidateSemantics", "Landroidx/compose/ui/node/Owner;", "owner", "attach$ui_release", "(Landroidx/compose/ui/node/Owner;)V", "attach", "detach$ui_release", "detach", "", "toString", "()Ljava/lang/String;", "invalidateLayer$ui_release", "invalidateLayer", "invalidateParentData$ui_release", "invalidateParentData", "x", "y", "place$ui_release", "place", "replace$ui_release", "replace", "lookaheadReplace$ui_release", "lookaheadReplace", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "draw$ui_release", "(Landroidx/compose/ui/graphics/Canvas;)V", "draw", "Landroidx/compose/ui/geometry/Offset;", "pointerPosition", "Landroidx/compose/ui/node/HitTestResult;", "hitTestResult", "isTouchEvent", "isInLayer", "hitTest-M_7yMNQ$ui_release", "(JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "hitTest", "hitSemanticsEntities", "hitTestSemantics-M_7yMNQ$ui_release", "hitTestSemantics", "it", "rescheduleRemeasureOrRelayout$ui_release", "(Landroidx/compose/ui/node/LayoutNode;)V", "rescheduleRemeasureOrRelayout", "forceRequest", "scheduleMeasureAndLayout", "requestRemeasure$ui_release", "(ZZ)V", "requestRemeasure", "requestLookaheadRemeasure$ui_release", "requestLookaheadRemeasure", "invalidateMeasurements$ui_release", "invalidateMeasurements", "Lkotlin/Function0;", "ignoreRemeasureRequests$ui_release", "(Lr7/a;)V", "ignoreRemeasureRequests", "requestRelayout$ui_release", "(Z)V", "requestRelayout", "requestLookaheadRelayout$ui_release", "requestLookaheadRelayout", "dispatchOnPositionedCallbacks$ui_release", "dispatchOnPositionedCallbacks", "", "Landroidx/compose/ui/layout/ModifierInfo;", "getModifierInfo", "()Ljava/util/List;", "invalidateLayers$ui_release", "invalidateLayers", "Landroidx/compose/ui/unit/Constraints;", "constraints", "lookaheadRemeasure-_Sx5XlM$ui_release", "(Landroidx/compose/ui/unit/Constraints;)Z", "lookaheadRemeasure", "remeasure-_Sx5XlM$ui_release", "remeasure", "markLayoutPending$ui_release", "markLayoutPending", "markMeasurePending$ui_release", "markMeasurePending", "markLookaheadLayoutPending$ui_release", "markLookaheadLayoutPending", "isRootOfInvalidation", "invalidateSubtree", "markLookaheadMeasurePending$ui_release", "markLookaheadMeasurePending", "forceRemeasure", "onLayoutComplete", "Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "forEachCoordinator$ui_release", "forEachCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "forEachCoordinatorIncludingInner$ui_release", "forEachCoordinatorIncludingInner", "clearSubtreeIntrinsicsUsage$ui_release", "clearSubtreeIntrinsicsUsage", "resetSubtreeIntrinsicsUsage$ui_release", "resetSubtreeIntrinsicsUsage", "onReuse", "onDeactivate", "onRelease", "recreateUnfoldedChildrenIfDirty", "invalidateUnfoldedVirtualChildren", "child", "onChildRemoved", "depth", "debugTreeToString", "(I)Ljava/lang/String;", "onDensityOrLayoutDirectionChanged", "resetModifierState", "invalidateFocusOnAttach", "invalidateFocusOnDetach", "shouldInvalidateParentLayer", "()Z", "clearSubtreePlacementIntrinsicsUsage", "Z", "I", "getSemanticsId", "()I", "setSemanticsId", "(I)V", "<set-?>", "compositeKeyHash", "getCompositeKeyHash", "setCompositeKeyHash", "getCompositeKeyHash$annotations", "isVirtualLookaheadRoot", "isVirtualLookaheadRoot$ui_release", "setVirtualLookaheadRoot$ui_release", "newRoot", "lookaheadRoot", "Landroidx/compose/ui/node/LayoutNode;", "getLookaheadRoot$ui_release", "()Landroidx/compose/ui/node/LayoutNode;", "setLookaheadRoot", "virtualChildrenCount", "Landroidx/compose/ui/node/MutableVectorWithMutationTracking;", "_foldedChildren", "Landroidx/compose/ui/node/MutableVectorWithMutationTracking;", "Landroidx/compose/runtime/collection/MutableVector;", "_unfoldedChildren", "Landroidx/compose/runtime/collection/MutableVector;", "unfoldedVirtualChildrenListDirty", "_foldedParent", "Landroidx/compose/ui/node/Owner;", "getOwner$ui_release", "()Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "Landroidx/compose/ui/viewinterop/InteropViewFactoryHolder;", "interopViewFactoryHolder", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "getInteropViewFactoryHolder$ui_release", "()Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "setInteropViewFactoryHolder$ui_release", "(Landroidx/compose/ui/viewinterop/AndroidViewHolder;)V", "getDepth$ui_release", "setDepth$ui_release", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "_collapsedSemantics", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "_zSortedChildren", "zSortedChildrenInvalidated", "Landroidx/compose/ui/layout/MeasurePolicy;", "value", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "setMeasurePolicy", "(Landroidx/compose/ui/layout/MeasurePolicy;)V", "Landroidx/compose/ui/node/IntrinsicsPolicy;", "intrinsicsPolicy", "Landroidx/compose/ui/node/IntrinsicsPolicy;", "getIntrinsicsPolicy$ui_release", "()Landroidx/compose/ui/node/IntrinsicsPolicy;", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "setViewConfiguration", "(Landroidx/compose/ui/platform/ViewConfiguration;)V", "Landroidx/compose/runtime/CompositionLocalMap;", "compositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "setCompositionLocalMap", "(Landroidx/compose/runtime/CompositionLocalMap;)V", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "intrinsicsUsageByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getIntrinsicsUsageByParent$ui_release", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setIntrinsicsUsageByParent$ui_release", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "previousIntrinsicsUsageByParent", "canMultiMeasure", "getCanMultiMeasure$ui_release", "setCanMultiMeasure$ui_release", "getCanMultiMeasure$ui_release$annotations", "Landroidx/compose/ui/node/NodeChain;", "nodes", "Landroidx/compose/ui/node/NodeChain;", "getNodes$ui_release", "()Landroidx/compose/ui/node/NodeChain;", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "layoutDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "getLayoutDelegate$ui_release", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "subcompositionsState", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "getSubcompositionsState$ui_release", "()Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "setSubcompositionsState$ui_release", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "_innerLayerCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "innerLayerCoordinatorIsDirty", "getInnerLayerCoordinatorIsDirty$ui_release", "setInnerLayerCoordinatorIsDirty$ui_release", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "onAttach", "Lr7/l;", "getOnAttach$ui_release", "()Lr7/l;", "setOnAttach$ui_release", "onDetach", "getOnDetach$ui_release", "setOnDetach$ui_release", "needsOnPositionedDispatch", "getNeedsOnPositionedDispatch$ui_release", "setNeedsOnPositionedDispatch$ui_release", "isDeactivated", "isPlacedInLookahead", "()Ljava/lang/Boolean;", "getFoldedChildren$ui_release", "foldedChildren", "Landroidx/compose/ui/layout/Measurable;", "getChildMeasurables$ui_release", "childMeasurables", "getChildLookaheadMeasurables$ui_release", "childLookaheadMeasurables", "get_children$ui_release", "()Landroidx/compose/runtime/collection/MutableVector;", "_children", "getChildren$ui_release", "children", "getParent$ui_release", "parent", "isAttached", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "getLayoutState$ui_release", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "layoutState", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$LookaheadPassDelegate;", "getLookaheadPassDelegate$ui_release", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$LookaheadPassDelegate;", "lookaheadPassDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "getMeasurePassDelegate$ui_release", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "measurePassDelegate", "getCollapsedSemantics$ui_release", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "collapsedSemantics", "getZSortedChildren", "getZSortedChildren$annotations", "zSortedChildren", "isValidOwnerScope", "getHasFixedInnerContentConstraints$ui_release", "hasFixedInnerContentConstraints", "getWidth", "width", "getHeight", "height", "getAlignmentLinesRequired$ui_release", "alignmentLinesRequired", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getMDrawScope$ui_release", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "mDrawScope", "isPlaced", "isPlacedByParent", "getPlaceOrder$ui_release", "placeOrder", "getMeasuredByParent$ui_release", "measuredByParent", "getMeasuredByParentInLookahead$ui_release", "measuredByParentInLookahead", "getInnerCoordinator$ui_release", "()Landroidx/compose/ui/node/NodeCoordinator;", "innerCoordinator", "getOuterCoordinator$ui_release", "outerCoordinator", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "getMeasurePending$ui_release", "measurePending", "getLayoutPending$ui_release", "layoutPending", "getLookaheadMeasurePending$ui_release", "lookaheadMeasurePending", "getLookaheadLayoutPending$ui_release", "lookaheadLayoutPending", "getParentInfo", "()Landroidx/compose/ui/layout/LayoutInfo;", "parentInfo", "", "getZIndex", "()F", "zIndex", "getInnerLayerCoordinator", "innerLayerCoordinator", "Companion", "LayoutState", "NoIntrinsicsMeasurePolicy", "UsageByParent", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayoutNode implements ComposeNodeLifecycleCallback, Remeasurement, OwnerScope, LayoutInfo, ComposeUiNode, InteroperableComposeUiNode, Owner.OnLayoutCompletedListener {
    public static final int NotPlacedPlaceOrder = Integer.MAX_VALUE;
    private SemanticsConfiguration _collapsedSemantics;
    private final MutableVectorWithMutationTracking<LayoutNode> _foldedChildren;
    private LayoutNode _foldedParent;
    private NodeCoordinator _innerLayerCoordinator;
    private MutableVector<LayoutNode> _unfoldedChildren;
    private final MutableVector<LayoutNode> _zSortedChildren;
    private boolean canMultiMeasure;
    private int compositeKeyHash;
    private CompositionLocalMap compositionLocalMap;
    private Density density;
    private int depth;
    private boolean ignoreRemeasureRequests;
    private boolean innerLayerCoordinatorIsDirty;
    private AndroidViewHolder interopViewFactoryHolder;
    private final IntrinsicsPolicy intrinsicsPolicy;
    private UsageByParent intrinsicsUsageByParent;
    private boolean isDeactivated;
    private final boolean isVirtual;
    private boolean isVirtualLookaheadRoot;
    private final LayoutNodeLayoutDelegate layoutDelegate;
    private LayoutDirection layoutDirection;
    private LayoutNode lookaheadRoot;
    private MeasurePolicy measurePolicy;
    private Modifier modifier;
    private boolean needsOnPositionedDispatch;
    private final NodeChain nodes;
    private l<? super Owner, t0> onAttach;
    private l<? super Owner, t0> onDetach;
    private Owner owner;
    private UsageByParent previousIntrinsicsUsageByParent;
    private int semanticsId;
    private LayoutNodeSubcompositionsState subcompositionsState;
    private boolean unfoldedVirtualChildrenListDirty;
    private ViewConfiguration viewConfiguration;
    private int virtualChildrenCount;
    private boolean zSortedChildrenInvalidated;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final NoIntrinsicsMeasurePolicy ErrorMeasurePolicy = new NoIntrinsicsMeasurePolicy() { // from class: androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1
        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* JADX INFO: renamed from: measure-3p2s80s */
        public /* bridge */ /* synthetic */ MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List list, long j10) {
            return (MeasureResult) m4757measure3p2s80s(measureScope, (List<? extends Measurable>) list, j10);
        }

        /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
        public Void m4757measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
            throw new IllegalStateException("Undefined measure and it is required");
        }
    };
    private static final r7.a<LayoutNode> Constructor = LayoutNode$Companion$Constructor$1.INSTANCE;
    private static final ViewConfiguration DummyViewConfiguration = new ViewConfiguration() { // from class: androidx.compose.ui.node.LayoutNode$Companion$DummyViewConfiguration$1
        @Override // androidx.compose.ui.platform.ViewConfiguration
        public long getDoubleTapMinTimeMillis() {
            return 40L;
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public long getDoubleTapTimeoutMillis() {
            return 300L;
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public long getLongPressTimeoutMillis() {
            return 400L;
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public final /* synthetic */ float getMaximumFlingVelocity() {
            return q.a(this);
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        /* JADX INFO: renamed from: getMinimumTouchTargetSize-MYxV2XQ, reason: not valid java name */
        public long mo4756getMinimumTouchTargetSizeMYxV2XQ() {
            return DpSize.INSTANCE.m5786getZeroMYxV2XQ();
        }

        @Override // androidx.compose.ui.platform.ViewConfiguration
        public float getTouchSlop() {
            return 16.0f;
        }
    };
    private static final Comparator<LayoutNode> ZComparator = new d(0);

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\u00020\n8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$Companion;", "", "<init>", "()V", "Lkotlin/Function0;", "Landroidx/compose/ui/node/LayoutNode;", "Constructor", "Lr7/a;", "getConstructor$ui_release", "()Lr7/a;", "Landroidx/compose/ui/platform/ViewConfiguration;", "DummyViewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getDummyViewConfiguration$ui_release", "()Landroidx/compose/ui/platform/ViewConfiguration;", "Ljava/util/Comparator;", "ZComparator", "Ljava/util/Comparator;", "getZComparator$ui_release", "()Ljava/util/Comparator;", "Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "ErrorMeasurePolicy", "Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "", "NotPlacedPlaceOrder", "I", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final r7.a<LayoutNode> getConstructor$ui_release() {
            return LayoutNode.Constructor;
        }

        public final ViewConfiguration getDummyViewConfiguration$ui_release() {
            return LayoutNode.DummyViewConfiguration;
        }

        public final Comparator<LayoutNode> getZComparator$ui_release() {
            return LayoutNode.ZComparator;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$LayoutState;", "", "(Ljava/lang/String;I)V", "Measuring", "LookaheadMeasuring", "LayingOut", "LookaheadLayingOut", "Idle", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum LayoutState {
        Measuring,
        LookaheadMeasuring,
        LayingOut,
        LookaheadLayingOut,
        Idle
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\r\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000e\u001a\u00020\fH\u0016J\"\u0010\u000f\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\u0010\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000e\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "error", "", "(Ljava/lang/String;)V", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "", "maxIntrinsicWidth", "height", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class NoIntrinsicsMeasurePolicy implements MeasurePolicy {
        public static final int $stable = 0;
        private final String error;

        public NoIntrinsicsMeasurePolicy(String str) {
            this.error = str;
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return ((Number) m4758maxIntrinsicHeight(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i10)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return ((Number) m4759maxIntrinsicWidth(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i10)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return ((Number) m4760minIntrinsicHeight(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i10)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return ((Number) m4761minIntrinsicWidth(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i10)).intValue();
        }

        /* JADX INFO: renamed from: maxIntrinsicHeight, reason: collision with other method in class */
        public Void m4758maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
            throw new IllegalStateException(this.error.toString());
        }

        /* JADX INFO: renamed from: maxIntrinsicWidth, reason: collision with other method in class */
        public Void m4759maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
            throw new IllegalStateException(this.error.toString());
        }

        /* JADX INFO: renamed from: minIntrinsicHeight, reason: collision with other method in class */
        public Void m4760minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
            throw new IllegalStateException(this.error.toString());
        }

        /* JADX INFO: renamed from: minIntrinsicWidth, reason: collision with other method in class */
        public Void m4761minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
            throw new IllegalStateException(this.error.toString());
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "", "(Ljava/lang/String;I)V", "InMeasureBlock", "InLayoutBlock", "NotUsed", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum UsageByParent {
        InMeasureBlock,
        InLayoutBlock,
        NotUsed
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutState.values().length];
            try {
                iArr[LayoutState.Idle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutNode() {
        this(false, 0 == true ? 1 : 0, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ZComparator$lambda$39(LayoutNode layoutNode, LayoutNode layoutNode2) {
        return layoutNode.getZIndex() == layoutNode2.getZIndex() ? p.c(layoutNode.getPlaceOrder$ui_release(), layoutNode2.getPlaceOrder$ui_release()) : Float.compare(layoutNode.getZIndex(), layoutNode2.getZIndex());
    }

    private final void clearSubtreePlacementIntrinsicsUsage() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = UsageByParent.NotUsed;
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode = content[i10];
                if (layoutNode.intrinsicsUsageByParent == UsageByParent.InLayoutBlock) {
                    layoutNode.clearSubtreePlacementIntrinsicsUsage();
                }
                i10++;
            } while (i10 < size);
        }
    }

    private final String debugTreeToString(int depth) {
        StringBuilder sb2 = new StringBuilder();
        for (int i10 = 0; i10 < depth; i10++) {
            sb2.append("  ");
        }
        sb2.append("|-");
        sb2.append(toString());
        sb2.append('\n');
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i11 = 0;
            do {
                sb2.append(content[i11].debugTreeToString(depth + 1));
                i11++;
            } while (i11 < size);
        }
        String string = sb2.toString();
        return depth == 0 ? string.substring(0, string.length() - 1) : string;
    }

    public static /* synthetic */ String debugTreeToString$default(LayoutNode layoutNode, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 0;
        }
        return layoutNode.debugTreeToString(i10);
    }

    @x6.e
    public static /* synthetic */ void getCanMultiMeasure$ui_release$annotations() {
    }

    public static /* synthetic */ void getCompositeKeyHash$annotations() {
    }

    private final NodeCoordinator getInnerLayerCoordinator() {
        if (this.innerLayerCoordinatorIsDirty) {
            NodeCoordinator innerCoordinator$ui_release = getInnerCoordinator$ui_release();
            NodeCoordinator wrappedBy = getOuterCoordinator$ui_release().getWrappedBy();
            this._innerLayerCoordinator = null;
            while (true) {
                if (p.a(innerCoordinator$ui_release, wrappedBy)) {
                    break;
                }
                if ((innerCoordinator$ui_release != null ? innerCoordinator$ui_release.getLayer() : null) != null) {
                    this._innerLayerCoordinator = innerCoordinator$ui_release;
                    break;
                }
                innerCoordinator$ui_release = innerCoordinator$ui_release != null ? innerCoordinator$ui_release.getWrappedBy() : null;
            }
        }
        NodeCoordinator nodeCoordinator = this._innerLayerCoordinator;
        if (nodeCoordinator == null || nodeCoordinator.getLayer() != null) {
            return nodeCoordinator;
        }
        throw new IllegalStateException("layer was not set");
    }

    private final float getZIndex() {
        return getMeasurePassDelegate$ui_release().getZIndex$ui_release();
    }

    public static /* synthetic */ void getZSortedChildren$annotations() {
    }

    /* JADX INFO: renamed from: hitTest-M_7yMNQ$ui_release$default, reason: not valid java name */
    public static /* synthetic */ void m4748hitTestM_7yMNQ$ui_release$default(LayoutNode layoutNode, long j10, HitTestResult hitTestResult, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        boolean z10 = z;
        if ((i10 & 8) != 0) {
            z5 = true;
        }
        layoutNode.m4752hitTestM_7yMNQ$ui_release(j10, hitTestResult, z10, z5);
    }

    /* JADX INFO: renamed from: hitTestSemantics-M_7yMNQ$ui_release$default, reason: not valid java name */
    public static /* synthetic */ void m4749hitTestSemanticsM_7yMNQ$ui_release$default(LayoutNode layoutNode, long j10, HitTestResult hitTestResult, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            z5 = true;
        }
        layoutNode.m4753hitTestSemanticsM_7yMNQ$ui_release(j10, hitTestResult, z, z5);
    }

    private final void invalidateFocusOnAttach() {
        if (this.nodes.has$ui_release(NodeKind.m4843constructorimpl(1024) | NodeKind.m4843constructorimpl(2048) | NodeKind.m4843constructorimpl(4096))) {
            for (Modifier.Node head = this.nodes.getHead(); head != null; head = head.getChild()) {
                if (((NodeKind.m4843constructorimpl(1024) & head.getKindSet()) != 0) | ((NodeKind.m4843constructorimpl(2048) & head.getKindSet()) != 0) | ((NodeKind.m4843constructorimpl(4096) & head.getKindSet()) != 0)) {
                    NodeKindKt.autoInvalidateInsertedNode(head);
                }
            }
        }
    }

    private final void invalidateFocusOnDetach() {
        NodeChain nodeChain = this.nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if ((nodeChain.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node tail = nodeChain.getTail(); tail != null; tail = tail.getParent()) {
                if ((tail.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = tail;
                    MutableVector mutableVector = null;
                    while (nodePop != null) {
                        if (nodePop instanceof FocusTargetNode) {
                            FocusTargetNode focusTargetNode = (FocusTargetNode) nodePop;
                            if (focusTargetNode.getFocusState().isFocused()) {
                                LayoutNodeKt.requireOwner(this).getFocusOwner().clearFocus(true, false);
                                focusTargetNode.scheduleInvalidationForFocusEvents$ui_release();
                            }
                        } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            int i10 = 0;
                            for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild()) {
                                if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                    i10++;
                                    if (i10 == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != null) {
                                            mutableVector.add(nodePop);
                                            nodePop = null;
                                        }
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                            }
                            if (i10 == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
            }
        }
    }

    public static /* synthetic */ void invalidateSubtree$default(LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        layoutNode.invalidateSubtree(z);
    }

    private final void invalidateUnfoldedVirtualChildren() {
        LayoutNode layoutNode;
        if (this.virtualChildrenCount > 0) {
            this.unfoldedVirtualChildrenListDirty = true;
        }
        if (!this.isVirtual || (layoutNode = this._foldedParent) == null) {
            return;
        }
        layoutNode.invalidateUnfoldedVirtualChildren();
    }

    /* JADX INFO: renamed from: lookaheadRemeasure-_Sx5XlM$ui_release$default, reason: not valid java name */
    public static /* synthetic */ boolean m4750lookaheadRemeasure_Sx5XlM$ui_release$default(LayoutNode layoutNode, Constraints constraints, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            constraints = layoutNode.layoutDelegate.m4771getLastLookaheadConstraintsDWUhwKw();
        }
        return layoutNode.m4754lookaheadRemeasure_Sx5XlM$ui_release(constraints);
    }

    private final void onChildRemoved(LayoutNode child) {
        if (child.layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
            this.layoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(r0.getChildrenAccessingCoordinatesDuringPlacement() - 1);
        }
        if (this.owner != null) {
            child.detach$ui_release();
        }
        child._foldedParent = null;
        child.getOuterCoordinator$ui_release().setWrappedBy$ui_release(null);
        if (child.isVirtual) {
            this.virtualChildrenCount--;
            MutableVector<LayoutNode> vector = child._foldedChildren.getVector();
            int size = vector.getSize();
            if (size > 0) {
                LayoutNode[] content = vector.getContent();
                int i10 = 0;
                do {
                    content[i10].getOuterCoordinator$ui_release().setWrappedBy$ui_release(null);
                    i10++;
                } while (i10 < size);
            }
        }
        invalidateUnfoldedVirtualChildren();
        onZSortedChildrenInvalidated$ui_release();
    }

    private final void onDensityOrLayoutDirectionChanged() {
        invalidateMeasurements$ui_release();
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
        invalidateLayers$ui_release();
    }

    private final void recreateUnfoldedChildrenIfDirty() {
        if (this.unfoldedVirtualChildrenListDirty) {
            int i10 = 0;
            this.unfoldedVirtualChildrenListDirty = false;
            MutableVector<LayoutNode> mutableVector = this._unfoldedChildren;
            if (mutableVector == null) {
                mutableVector = new MutableVector<>(new LayoutNode[16], 0);
                this._unfoldedChildren = mutableVector;
            }
            mutableVector.clear();
            MutableVector<LayoutNode> vector = this._foldedChildren.getVector();
            int size = vector.getSize();
            if (size > 0) {
                LayoutNode[] content = vector.getContent();
                do {
                    LayoutNode layoutNode = content[i10];
                    if (layoutNode.isVirtual) {
                        mutableVector.addAll(mutableVector.getSize(), layoutNode.get_children$ui_release());
                    } else {
                        mutableVector.add(layoutNode);
                    }
                    i10++;
                } while (i10 < size);
            }
            this.layoutDelegate.markChildrenDirty();
        }
    }

    /* JADX INFO: renamed from: remeasure-_Sx5XlM$ui_release$default, reason: not valid java name */
    public static /* synthetic */ boolean m4751remeasure_Sx5XlM$ui_release$default(LayoutNode layoutNode, Constraints constraints, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            constraints = layoutNode.layoutDelegate.m4770getLastConstraintsDWUhwKw();
        }
        return layoutNode.m4755remeasure_Sx5XlM$ui_release(constraints);
    }

    public static /* synthetic */ void requestLookaheadRelayout$ui_release$default(LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        layoutNode.requestLookaheadRelayout$ui_release(z);
    }

    public static /* synthetic */ void requestLookaheadRemeasure$ui_release$default(LayoutNode layoutNode, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        layoutNode.requestLookaheadRemeasure$ui_release(z, z5);
    }

    public static /* synthetic */ void requestRelayout$ui_release$default(LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        layoutNode.requestRelayout$ui_release(z);
    }

    public static /* synthetic */ void requestRemeasure$ui_release$default(LayoutNode layoutNode, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        layoutNode.requestRemeasure$ui_release(z, z5);
    }

    private final void resetModifierState() {
        this.nodes.resetState$ui_release();
    }

    private final void setLookaheadRoot(LayoutNode layoutNode) {
        if (p.a(layoutNode, this.lookaheadRoot)) {
            return;
        }
        this.lookaheadRoot = layoutNode;
        if (layoutNode != null) {
            this.layoutDelegate.ensureLookaheadDelegateCreated$ui_release();
            NodeCoordinator wrapped = getInnerCoordinator$ui_release().getWrapped();
            for (NodeCoordinator outerCoordinator$ui_release = getOuterCoordinator$ui_release(); !p.a(outerCoordinator$ui_release, wrapped) && outerCoordinator$ui_release != null; outerCoordinator$ui_release = outerCoordinator$ui_release.getWrapped()) {
                outerCoordinator$ui_release.ensureLookaheadDelegateCreated();
            }
        }
        invalidateMeasurements$ui_release();
    }

    private final boolean shouldInvalidateParentLayer() {
        if (this.nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(4)) && !this.nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(2))) {
            return true;
        }
        for (Modifier.Node head = this.nodes.getHead(); head != null; head = head.getChild()) {
            if ((NodeKind.m4843constructorimpl(2) & head.getKindSet()) != 0 && DelegatableNodeKt.m4726requireCoordinator64DMado(head, NodeKind.m4843constructorimpl(2)).getLayer() != null) {
                return false;
            }
            if ((NodeKind.m4843constructorimpl(4) & head.getKindSet()) != 0) {
                return true;
            }
        }
        return true;
    }

    public final void attach$ui_release(Owner owner) {
        LayoutNode layoutNode;
        int i10 = 0;
        if (this.owner != null) {
            throw new IllegalStateException(("Cannot attach " + this + " as it already is attached.  Tree: " + debugTreeToString$default(this, 0, 1, null)).toString());
        }
        LayoutNode layoutNode2 = this._foldedParent;
        if (layoutNode2 != null) {
            if (!p.a(layoutNode2 != null ? layoutNode2.owner : null, owner)) {
                StringBuilder sb2 = new StringBuilder("Attaching to a different owner(");
                sb2.append(owner);
                sb2.append(") than the parent's owner(");
                LayoutNode parent$ui_release = getParent$ui_release();
                sb2.append(parent$ui_release != null ? parent$ui_release.owner : null);
                sb2.append("). This tree: ");
                sb2.append(debugTreeToString$default(this, 0, 1, null));
                sb2.append(" Parent tree: ");
                LayoutNode layoutNode3 = this._foldedParent;
                sb2.append(layoutNode3 != null ? debugTreeToString$default(layoutNode3, 0, 1, null) : null);
                throw new IllegalStateException(sb2.toString().toString());
            }
        }
        LayoutNode parent$ui_release2 = getParent$ui_release();
        if (parent$ui_release2 == null) {
            getMeasurePassDelegate$ui_release().setPlaced$ui_release(true);
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = getLookaheadPassDelegate$ui_release();
            if (lookaheadPassDelegate$ui_release != null) {
                lookaheadPassDelegate$ui_release.setPlaced(true);
            }
        }
        getOuterCoordinator$ui_release().setWrappedBy$ui_release(parent$ui_release2 != null ? parent$ui_release2.getInnerCoordinator$ui_release() : null);
        this.owner = owner;
        this.depth = (parent$ui_release2 != null ? parent$ui_release2.depth : -1) + 1;
        if (this.nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(8))) {
            invalidateSemantics$ui_release();
        }
        owner.onAttach(this);
        if (this.isVirtualLookaheadRoot) {
            setLookaheadRoot(this);
        } else {
            LayoutNode layoutNode4 = this._foldedParent;
            if (layoutNode4 == null || (layoutNode = layoutNode4.lookaheadRoot) == null) {
                layoutNode = this.lookaheadRoot;
            }
            setLookaheadRoot(layoutNode);
        }
        if (!getIsDeactivated()) {
            this.nodes.markAsAttached();
        }
        MutableVector<LayoutNode> vector = this._foldedChildren.getVector();
        int size = vector.getSize();
        if (size > 0) {
            LayoutNode[] content = vector.getContent();
            do {
                content[i10].attach$ui_release(owner);
                i10++;
            } while (i10 < size);
        }
        if (!getIsDeactivated()) {
            this.nodes.runAttachLifecycle();
        }
        invalidateMeasurements$ui_release();
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateMeasurements$ui_release();
        }
        NodeCoordinator wrapped = getInnerCoordinator$ui_release().getWrapped();
        for (NodeCoordinator outerCoordinator$ui_release = getOuterCoordinator$ui_release(); !p.a(outerCoordinator$ui_release, wrapped) && outerCoordinator$ui_release != null; outerCoordinator$ui_release = outerCoordinator$ui_release.getWrapped()) {
            outerCoordinator$ui_release.onLayoutNodeAttach();
        }
        l<? super Owner, t0> lVar = this.onAttach;
        if (lVar != null) {
            lVar.invoke(owner);
        }
        this.layoutDelegate.updateParentData();
        if (getIsDeactivated()) {
            return;
        }
        invalidateFocusOnAttach();
    }

    public final void clearSubtreeIntrinsicsUsage$ui_release() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = UsageByParent.NotUsed;
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode = content[i10];
                if (layoutNode.intrinsicsUsageByParent != UsageByParent.NotUsed) {
                    layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
                }
                i10++;
            } while (i10 < size);
        }
    }

    public final void detach$ui_release() {
        Owner owner = this.owner;
        if (owner == null) {
            StringBuilder sb2 = new StringBuilder("Cannot detach node that is already detached!  Tree: ");
            LayoutNode parent$ui_release = getParent$ui_release();
            sb2.append(parent$ui_release != null ? debugTreeToString$default(parent$ui_release, 0, 1, null) : null);
            throw new IllegalStateException(sb2.toString().toString());
        }
        invalidateFocusOnDetach();
        LayoutNode parent$ui_release2 = getParent$ui_release();
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateLayer$ui_release();
            parent$ui_release2.invalidateMeasurements$ui_release();
            LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate$ui_release = getMeasurePassDelegate$ui_release();
            UsageByParent usageByParent = UsageByParent.NotUsed;
            measurePassDelegate$ui_release.setMeasuredByParent$ui_release(usageByParent);
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = getLookaheadPassDelegate$ui_release();
            if (lookaheadPassDelegate$ui_release != null) {
                lookaheadPassDelegate$ui_release.setMeasuredByParent$ui_release(usageByParent);
            }
        }
        this.layoutDelegate.resetAlignmentLines();
        l<? super Owner, t0> lVar = this.onDetach;
        if (lVar != null) {
            lVar.invoke(owner);
        }
        if (this.nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(8))) {
            invalidateSemantics$ui_release();
        }
        this.nodes.runDetachLifecycle$ui_release();
        this.ignoreRemeasureRequests = true;
        MutableVector<LayoutNode> vector = this._foldedChildren.getVector();
        int size = vector.getSize();
        if (size > 0) {
            LayoutNode[] content = vector.getContent();
            int i10 = 0;
            do {
                content[i10].detach$ui_release();
                i10++;
            } while (i10 < size);
        }
        this.ignoreRemeasureRequests = false;
        this.nodes.markAsDetached$ui_release();
        owner.onDetach(this);
        this.owner = null;
        setLookaheadRoot(null);
        this.depth = 0;
        getMeasurePassDelegate$ui_release().onNodeDetached();
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release2 = getLookaheadPassDelegate$ui_release();
        if (lookaheadPassDelegate$ui_release2 != null) {
            lookaheadPassDelegate$ui_release2.onNodeDetached();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    public final void dispatchOnPositionedCallbacks$ui_release() {
        if (getLayoutState$ui_release() != LayoutState.Idle || getLayoutPending$ui_release() || getMeasurePending$ui_release() || getIsDeactivated() || !isPlaced()) {
            return;
        }
        NodeChain nodeChain = this.nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(256);
        if ((nodeChain.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof GlobalPositionAwareModifierNode) {
                            GlobalPositionAwareModifierNode globalPositionAwareModifierNode = (GlobalPositionAwareModifierNode) nodePop;
                            globalPositionAwareModifierNode.onGloballyPositioned(DelegatableNodeKt.m4726requireCoordinator64DMado(globalPositionAwareModifierNode, NodeKind.m4843constructorimpl(256)));
                        } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                            int i10 = 0;
                            nodePop = nodePop;
                            while (delegate$ui_release != null) {
                                if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                    i10++;
                                    if (i10 == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                                delegate$ui_release = delegate$ui_release.getChild();
                                nodePop = nodePop;
                            }
                            if (i10 == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if ((head.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                    return;
                }
            }
        }
    }

    public final void draw$ui_release(Canvas canvas) {
        getOuterCoordinator$ui_release().draw(canvas);
    }

    public final void forEachChild(l<? super LayoutNode, t0> block) {
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                block.invoke(content[i10]);
                i10++;
            } while (i10 < size);
        }
    }

    public final void forEachChildIndexed(r7.p<? super Integer, ? super LayoutNode, t0> block) {
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                block.invoke(Integer.valueOf(i10), content[i10]);
                i10++;
            } while (i10 < size);
        }
    }

    public final void forEachCoordinator$ui_release(l<? super LayoutModifierNodeCoordinator, t0> block) {
        NodeCoordinator outerCoordinator$ui_release = getOuterCoordinator$ui_release();
        NodeCoordinator innerCoordinator$ui_release = getInnerCoordinator$ui_release();
        while (outerCoordinator$ui_release != innerCoordinator$ui_release) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) outerCoordinator$ui_release;
            block.invoke(layoutModifierNodeCoordinator);
            outerCoordinator$ui_release = layoutModifierNodeCoordinator.getWrapped();
        }
    }

    public final void forEachCoordinatorIncludingInner$ui_release(l<? super NodeCoordinator, t0> block) {
        NodeCoordinator wrapped = getInnerCoordinator$ui_release().getWrapped();
        for (NodeCoordinator outerCoordinator$ui_release = getOuterCoordinator$ui_release(); !p.a(outerCoordinator$ui_release, wrapped) && outerCoordinator$ui_release != null; outerCoordinator$ui_release = outerCoordinator$ui_release.getWrapped()) {
            block.invoke(outerCoordinator$ui_release);
        }
    }

    @Override // androidx.compose.ui.layout.Remeasurement
    public void forceRemeasure() {
        if (this.lookaheadRoot != null) {
            requestLookaheadRemeasure$ui_release$default(this, false, false, 1, null);
        } else {
            requestRemeasure$ui_release$default(this, false, false, 1, null);
        }
        Constraints constraintsM4770getLastConstraintsDWUhwKw = this.layoutDelegate.m4770getLastConstraintsDWUhwKw();
        if (constraintsM4770getLastConstraintsDWUhwKw != null) {
            Owner owner = this.owner;
            if (owner != null) {
                owner.mo4899measureAndLayout0kLqBqw(this, constraintsM4770getLastConstraintsDWUhwKw.getValue());
                return;
            }
            return;
        }
        Owner owner2 = this.owner;
        if (owner2 != null) {
            e.f(owner2, false, 1, null);
        }
    }

    public final boolean getAlignmentLinesRequired$ui_release() {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui_release;
        AlignmentLines alignmentLines;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        return layoutNodeLayoutDelegate.getAlignmentLinesOwner$ui_release().getAlignmentLines().getRequired$ui_release() || !((lookaheadAlignmentLinesOwner$ui_release = layoutNodeLayoutDelegate.getLookaheadAlignmentLinesOwner$ui_release()) == null || (alignmentLines = lookaheadAlignmentLinesOwner$ui_release.getAlignmentLines()) == null || !alignmentLines.getRequired$ui_release());
    }

    /* JADX INFO: renamed from: getCanMultiMeasure$ui_release, reason: from getter */
    public final boolean getCanMultiMeasure() {
        return this.canMultiMeasure;
    }

    public final List<Measurable> getChildLookaheadMeasurables$ui_release() {
        return getLookaheadPassDelegate$ui_release().getChildDelegates$ui_release();
    }

    public final List<Measurable> getChildMeasurables$ui_release() {
        return getMeasurePassDelegate$ui_release().getChildDelegates$ui_release();
    }

    public final List<LayoutNode> getChildren$ui_release() {
        return get_children$ui_release().asMutableList();
    }

    public final SemanticsConfiguration getCollapsedSemantics$ui_release() {
        if (!this.nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(8)) || this._collapsedSemantics != null) {
            return this._collapsedSemantics;
        }
        k0 k0Var = new k0();
        k0Var.f19746i = new SemanticsConfiguration();
        LayoutNodeKt.requireOwner(this).getSnapshotObserver().observeSemanticsReads$ui_release(this, new LayoutNode$collapsedSemantics$1(this, k0Var));
        Object obj = k0Var.f19746i;
        this._collapsedSemantics = (SemanticsConfiguration) obj;
        return (SemanticsConfiguration) obj;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public int getCompositeKeyHash() {
        return this.compositeKeyHash;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public CompositionLocalMap getCompositionLocalMap() {
        return this.compositionLocalMap;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public LayoutCoordinates getCoordinates() {
        return getInnerCoordinator$ui_release();
    }

    @Override // androidx.compose.ui.layout.LayoutInfo, androidx.compose.ui.node.ComposeUiNode
    public Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getDepth$ui_release, reason: from getter */
    public final int getDepth() {
        return this.depth;
    }

    public final List<LayoutNode> getFoldedChildren$ui_release() {
        return this._foldedChildren.asList();
    }

    public final boolean getHasFixedInnerContentConstraints$ui_release() {
        long jM4821getLastMeasurementConstraintsmsEJaDk$ui_release = getInnerCoordinator$ui_release().m4821getLastMeasurementConstraintsmsEJaDk$ui_release();
        return Constraints.m5644getHasFixedWidthimpl(jM4821getLastMeasurementConstraintsmsEJaDk$ui_release) && Constraints.m5643getHasFixedHeightimpl(jM4821getLastMeasurementConstraintsmsEJaDk$ui_release);
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getHeight() {
        return this.layoutDelegate.getHeight$ui_release();
    }

    public final NodeCoordinator getInnerCoordinator$ui_release() {
        return this.nodes.getInnerCoordinator();
    }

    /* JADX INFO: renamed from: getInnerLayerCoordinatorIsDirty$ui_release, reason: from getter */
    public final boolean getInnerLayerCoordinatorIsDirty() {
        return this.innerLayerCoordinatorIsDirty;
    }

    @Override // androidx.compose.ui.node.InteroperableComposeUiNode
    public View getInteropView() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            return androidViewHolder.getView();
        }
        return null;
    }

    /* JADX INFO: renamed from: getInteropViewFactoryHolder$ui_release, reason: from getter */
    public final AndroidViewHolder getInteropViewFactoryHolder() {
        return this.interopViewFactoryHolder;
    }

    /* JADX INFO: renamed from: getIntrinsicsPolicy$ui_release, reason: from getter */
    public final IntrinsicsPolicy getIntrinsicsPolicy() {
        return this.intrinsicsPolicy;
    }

    /* JADX INFO: renamed from: getIntrinsicsUsageByParent$ui_release, reason: from getter */
    public final UsageByParent getIntrinsicsUsageByParent() {
        return this.intrinsicsUsageByParent;
    }

    /* JADX INFO: renamed from: getLayoutDelegate$ui_release, reason: from getter */
    public final LayoutNodeLayoutDelegate getLayoutDelegate() {
        return this.layoutDelegate;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo, androidx.compose.ui.node.ComposeUiNode
    public LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final boolean getLayoutPending$ui_release() {
        return this.layoutDelegate.getLayoutPending$ui_release();
    }

    public final LayoutState getLayoutState$ui_release() {
        return this.layoutDelegate.getLayoutState$ui_release();
    }

    public final boolean getLookaheadLayoutPending$ui_release() {
        return this.layoutDelegate.getLookaheadLayoutPending$ui_release();
    }

    public final boolean getLookaheadMeasurePending$ui_release() {
        return this.layoutDelegate.getLookaheadMeasurePending$ui_release();
    }

    public final LayoutNodeLayoutDelegate.LookaheadPassDelegate getLookaheadPassDelegate$ui_release() {
        return this.layoutDelegate.getLookaheadPassDelegate$ui_release();
    }

    /* JADX INFO: renamed from: getLookaheadRoot$ui_release, reason: from getter */
    public final LayoutNode getLookaheadRoot() {
        return this.lookaheadRoot;
    }

    public final LayoutNodeDrawScope getMDrawScope$ui_release() {
        return LayoutNodeKt.requireOwner(this).getSharedDrawScope();
    }

    public final LayoutNodeLayoutDelegate.MeasurePassDelegate getMeasurePassDelegate$ui_release() {
        return this.layoutDelegate.getMeasurePassDelegate$ui_release();
    }

    public final boolean getMeasurePending$ui_release() {
        return this.layoutDelegate.getMeasurePending$ui_release();
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public MeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    public final UsageByParent getMeasuredByParent$ui_release() {
        return getMeasurePassDelegate$ui_release().getMeasuredByParent$ui_release();
    }

    public final UsageByParent getMeasuredByParentInLookahead$ui_release() {
        UsageByParent measuredByParent$ui_release;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = getLookaheadPassDelegate$ui_release();
        return (lookaheadPassDelegate$ui_release == null || (measuredByParent$ui_release = lookaheadPassDelegate$ui_release.getMeasuredByParent$ui_release()) == null) ? UsageByParent.NotUsed : measuredByParent$ui_release;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public List<ModifierInfo> getModifierInfo() {
        return this.nodes.getModifierInfo();
    }

    /* JADX INFO: renamed from: getNeedsOnPositionedDispatch$ui_release, reason: from getter */
    public final boolean getNeedsOnPositionedDispatch() {
        return this.needsOnPositionedDispatch;
    }

    /* JADX INFO: renamed from: getNodes$ui_release, reason: from getter */
    public final NodeChain getNodes() {
        return this.nodes;
    }

    public final l<Owner, t0> getOnAttach$ui_release() {
        return this.onAttach;
    }

    public final l<Owner, t0> getOnDetach$ui_release() {
        return this.onDetach;
    }

    public final NodeCoordinator getOuterCoordinator$ui_release() {
        return this.nodes.getOuterCoordinator();
    }

    /* JADX INFO: renamed from: getOwner$ui_release, reason: from getter */
    public final Owner getOwner() {
        return this.owner;
    }

    public final LayoutNode getParent$ui_release() {
        LayoutNode layoutNode = this._foldedParent;
        while (layoutNode != null && layoutNode.isVirtual) {
            layoutNode = layoutNode._foldedParent;
        }
        return layoutNode;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public LayoutInfo getParentInfo() {
        return getParent$ui_release();
    }

    public final int getPlaceOrder$ui_release() {
        return getMeasurePassDelegate$ui_release().getPlaceOrder$ui_release();
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getSemanticsId() {
        return this.semanticsId;
    }

    /* JADX INFO: renamed from: getSubcompositionsState$ui_release, reason: from getter */
    public final LayoutNodeSubcompositionsState getSubcompositionsState() {
        return this.subcompositionsState;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo, androidx.compose.ui.node.ComposeUiNode
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getWidth() {
        return this.layoutDelegate.getWidth$ui_release();
    }

    public final MutableVector<LayoutNode> getZSortedChildren() {
        if (this.zSortedChildrenInvalidated) {
            this._zSortedChildren.clear();
            MutableVector<LayoutNode> mutableVector = this._zSortedChildren;
            mutableVector.addAll(mutableVector.getSize(), get_children$ui_release());
            this._zSortedChildren.sortWith(ZComparator);
            this.zSortedChildrenInvalidated = false;
        }
        return this._zSortedChildren;
    }

    public final MutableVector<LayoutNode> get_children$ui_release() {
        updateChildrenIfDirty$ui_release();
        return this.virtualChildrenCount == 0 ? this._foldedChildren.getVector() : this._unfoldedChildren;
    }

    /* JADX INFO: renamed from: hitTest-M_7yMNQ$ui_release, reason: not valid java name */
    public final void m4752hitTestM_7yMNQ$ui_release(long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
        getOuterCoordinator$ui_release().m4824hitTestYqVAtuI(NodeCoordinator.INSTANCE.getPointerInputSource(), getOuterCoordinator$ui_release().m4820fromParentPositionMKHz9U(pointerPosition), hitTestResult, isTouchEvent, isInLayer);
    }

    /* JADX INFO: renamed from: hitTestSemantics-M_7yMNQ$ui_release, reason: not valid java name */
    public final void m4753hitTestSemanticsM_7yMNQ$ui_release(long pointerPosition, HitTestResult hitSemanticsEntities, boolean isTouchEvent, boolean isInLayer) {
        getOuterCoordinator$ui_release().m4824hitTestYqVAtuI(NodeCoordinator.INSTANCE.getSemanticsSource(), getOuterCoordinator$ui_release().m4820fromParentPositionMKHz9U(pointerPosition), hitSemanticsEntities, true, isInLayer);
    }

    public final void ignoreRemeasureRequests$ui_release(r7.a<t0> block) {
        this.ignoreRemeasureRequests = true;
        block.invoke();
        this.ignoreRemeasureRequests = false;
    }

    public final void insertAt$ui_release(int index, LayoutNode instance) {
        if (instance._foldedParent != null) {
            StringBuilder sb2 = new StringBuilder("Cannot insert ");
            sb2.append(instance);
            sb2.append(" because it already has a parent. This tree: ");
            sb2.append(debugTreeToString$default(this, 0, 1, null));
            sb2.append(" Other tree: ");
            LayoutNode layoutNode = instance._foldedParent;
            sb2.append(layoutNode != null ? debugTreeToString$default(layoutNode, 0, 1, null) : null);
            throw new IllegalStateException(sb2.toString().toString());
        }
        if (instance.owner != null) {
            throw new IllegalStateException(("Cannot insert " + instance + " because it already has an owner. This tree: " + debugTreeToString$default(this, 0, 1, null) + " Other tree: " + debugTreeToString$default(instance, 0, 1, null)).toString());
        }
        instance._foldedParent = this;
        this._foldedChildren.add(index, instance);
        onZSortedChildrenInvalidated$ui_release();
        if (instance.isVirtual) {
            this.virtualChildrenCount++;
        }
        invalidateUnfoldedVirtualChildren();
        Owner owner = this.owner;
        if (owner != null) {
            instance.attach$ui_release(owner);
        }
        if (instance.layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
            layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() + 1);
        }
    }

    public final void invalidateLayer$ui_release() {
        NodeCoordinator innerLayerCoordinator = getInnerLayerCoordinator();
        if (innerLayerCoordinator != null) {
            innerLayerCoordinator.invalidateLayer();
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
    }

    public final void invalidateLayers$ui_release() {
        NodeCoordinator outerCoordinator$ui_release = getOuterCoordinator$ui_release();
        NodeCoordinator innerCoordinator$ui_release = getInnerCoordinator$ui_release();
        while (outerCoordinator$ui_release != innerCoordinator$ui_release) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) outerCoordinator$ui_release;
            OwnedLayer layer = layoutModifierNodeCoordinator.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
            outerCoordinator$ui_release = layoutModifierNodeCoordinator.getWrapped();
        }
        OwnedLayer layer2 = getInnerCoordinator$ui_release().getLayer();
        if (layer2 != null) {
            layer2.invalidate();
        }
    }

    public final void invalidateMeasurements$ui_release() {
        if (this.lookaheadRoot != null) {
            requestLookaheadRemeasure$ui_release$default(this, false, false, 3, null);
        } else {
            requestRemeasure$ui_release$default(this, false, false, 3, null);
        }
    }

    public final void invalidateParentData$ui_release() {
        this.layoutDelegate.invalidateParentData();
    }

    public final void invalidateSemantics$ui_release() {
        this._collapsedSemantics = null;
        LayoutNodeKt.requireOwner(this).onSemanticsChange();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v14 */
    public final void invalidateSubtree(boolean isRootOfInvalidation) {
        LayoutNode parent$ui_release;
        if (isRootOfInvalidation && (parent$ui_release = getParent$ui_release()) != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
        invalidateSemantics$ui_release();
        requestRemeasure$ui_release$default(this, false, false, 3, null);
        NodeChain nodeChain = this.nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(2);
        if ((nodeChain.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof LayoutModifierNode) {
                            OwnedLayer layer = DelegatableNodeKt.m4726requireCoordinator64DMado((LayoutModifierNode) nodePop, NodeKind.m4843constructorimpl(2)).getLayer();
                            if (layer != null) {
                                layer.invalidate();
                            }
                        } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                            int i10 = 0;
                            nodePop = nodePop;
                            while (delegate$ui_release != null) {
                                if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                    i10++;
                                    if (i10 == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                                delegate$ui_release = delegate$ui_release.getChild();
                                nodePop = nodePop;
                            }
                            if (i10 == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if ((head.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                    break;
                }
            }
        }
        MutableVector<LayoutNode> mutableVector2 = get_children$ui_release();
        int size = mutableVector2.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector2.getContent();
            int i11 = 0;
            do {
                content[i11].invalidateSubtree(false);
                i11++;
            } while (i11 < size);
        }
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public boolean isAttached() {
        return this.owner != null;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    /* JADX INFO: renamed from: isDeactivated, reason: from getter */
    public boolean getIsDeactivated() {
        return this.isDeactivated;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public boolean isPlaced() {
        return getMeasurePassDelegate$ui_release().isPlaced();
    }

    public final boolean isPlacedByParent() {
        return getMeasurePassDelegate$ui_release().isPlacedByParent();
    }

    public final Boolean isPlacedInLookahead() {
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = getLookaheadPassDelegate$ui_release();
        if (lookaheadPassDelegate$ui_release != null) {
            return Boolean.valueOf(lookaheadPassDelegate$ui_release.isPlaced());
        }
        return null;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return isAttached();
    }

    /* JADX INFO: renamed from: isVirtualLookaheadRoot$ui_release, reason: from getter */
    public final boolean getIsVirtualLookaheadRoot() {
        return this.isVirtualLookaheadRoot;
    }

    /* JADX INFO: renamed from: lookaheadRemeasure-_Sx5XlM$ui_release, reason: not valid java name */
    public final boolean m4754lookaheadRemeasure_Sx5XlM$ui_release(Constraints constraints) {
        if (constraints == null || this.lookaheadRoot == null) {
            return false;
        }
        return getLookaheadPassDelegate$ui_release().m4774remeasureBRTryo0(constraints.getValue());
    }

    public final void lookaheadReplace$ui_release() {
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        getLookaheadPassDelegate$ui_release().replace();
    }

    public final void markLayoutPending$ui_release() {
        this.layoutDelegate.markLayoutPending$ui_release();
    }

    public final void markLookaheadLayoutPending$ui_release() {
        this.layoutDelegate.markLookaheadLayoutPending$ui_release();
    }

    public final void markLookaheadMeasurePending$ui_release() {
        this.layoutDelegate.markLookaheadMeasurePending$ui_release();
    }

    public final void markMeasurePending$ui_release() {
        this.layoutDelegate.markMeasurePending$ui_release();
    }

    public final void move$ui_release(int from, int to, int count) {
        if (from == to) {
            return;
        }
        for (int i10 = 0; i10 < count; i10++) {
            this._foldedChildren.add(from > to ? to + i10 : (to + count) - 2, this._foldedChildren.removeAt(from > to ? from + i10 : from));
        }
        onZSortedChildrenInvalidated$ui_release();
        invalidateUnfoldedVirtualChildren();
        invalidateMeasurements$ui_release();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onDeactivate();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onDeactivate();
        }
        this.isDeactivated = true;
        resetModifierState();
        if (isAttached()) {
            invalidateSemantics$ui_release();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // androidx.compose.ui.node.Owner.OnLayoutCompletedListener
    public void onLayoutComplete() {
        NodeCoordinator innerCoordinator$ui_release = getInnerCoordinator$ui_release();
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(128);
        boolean zM4852getIncludeSelfInTraversalH91voCI = NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(iM4843constructorimpl);
        Modifier.Node tail = innerCoordinator$ui_release.getTail();
        if (!zM4852getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = innerCoordinator$ui_release.headNode(zM4852getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4843constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & iM4843constructorimpl) != 0) {
                Modifier.Node nodePop = nodeHeadNode;
                MutableVector mutableVector = null;
                while (nodePop != 0) {
                    if (nodePop instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) nodePop).onPlaced(getInnerCoordinator$ui_release());
                    } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                        Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                        int i10 = 0;
                        nodePop = nodePop;
                        while (delegate$ui_release != null) {
                            if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                i10++;
                                if (i10 == 1) {
                                    nodePop = delegate$ui_release;
                                } else {
                                    if (mutableVector == null) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (nodePop != 0) {
                                        mutableVector.add(nodePop);
                                        nodePop = 0;
                                    }
                                    mutableVector.add(delegate$ui_release);
                                }
                            }
                            delegate$ui_release = delegate$ui_release.getChild();
                            nodePop = nodePop;
                        }
                        if (i10 == 1) {
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                }
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onRelease();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onRelease();
        }
        NodeCoordinator wrapped = getInnerCoordinator$ui_release().getWrapped();
        for (NodeCoordinator outerCoordinator$ui_release = getOuterCoordinator$ui_release(); !p.a(outerCoordinator$ui_release, wrapped) && outerCoordinator$ui_release != null; outerCoordinator$ui_release = outerCoordinator$ui_release.getWrapped()) {
            outerCoordinator$ui_release.onRelease();
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        if (!isAttached()) {
            throw new IllegalArgumentException("onReuse is only expected on attached node");
        }
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onReuse();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onReuse();
        }
        if (getIsDeactivated()) {
            this.isDeactivated = false;
            invalidateSemantics$ui_release();
        } else {
            resetModifierState();
        }
        setSemanticsId(SemanticsModifierKt.generateSemanticsId());
        this.nodes.markAsAttached();
        this.nodes.runAttachLifecycle();
        rescheduleRemeasureOrRelayout$ui_release(this);
    }

    public final void onZSortedChildrenInvalidated$ui_release() {
        if (!this.isVirtual) {
            this.zSortedChildrenInvalidated = true;
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.onZSortedChildrenInvalidated$ui_release();
        }
    }

    public final void place$ui_release(int x, int y10) {
        Placeable.PlacementScope placementScope;
        NodeCoordinator innerCoordinator$ui_release;
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release == null || (innerCoordinator$ui_release = parent$ui_release.getInnerCoordinator$ui_release()) == null || (placementScope = innerCoordinator$ui_release.getPlacementScope()) == null) {
            placementScope = LayoutNodeKt.requireOwner(this).getPlacementScope();
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, getMeasurePassDelegate$ui_release(), x, y10, 0.0f, 4, null);
    }

    /* JADX INFO: renamed from: remeasure-_Sx5XlM$ui_release, reason: not valid java name */
    public final boolean m4755remeasure_Sx5XlM$ui_release(Constraints constraints) {
        if (constraints == null) {
            return false;
        }
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreeIntrinsicsUsage$ui_release();
        }
        return getMeasurePassDelegate$ui_release().m4779remeasureBRTryo0(constraints.getValue());
    }

    public final void removeAll$ui_release() {
        int size = this._foldedChildren.getSize();
        while (true) {
            size--;
            if (-1 >= size) {
                this._foldedChildren.clear();
                return;
            }
            onChildRemoved(this._foldedChildren.get(size));
        }
    }

    public final void removeAt$ui_release(int index, int count) {
        if (count < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(count, "count (", ") must be greater than 0").toString());
        }
        int i10 = (count + index) - 1;
        if (index > i10) {
            return;
        }
        while (true) {
            onChildRemoved(this._foldedChildren.removeAt(i10));
            if (i10 == index) {
                return;
            } else {
                i10--;
            }
        }
    }

    public final void replace$ui_release() {
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        getMeasurePassDelegate$ui_release().replace();
    }

    public final void requestLookaheadRelayout$ui_release(boolean forceRequest) {
        Owner owner;
        if (this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        owner.onRequestRelayout(this, true, forceRequest);
    }

    public final void requestLookaheadRemeasure$ui_release(boolean forceRequest, boolean scheduleMeasureAndLayout) {
        if (this.lookaheadRoot == null) {
            throw new IllegalStateException("Lookahead measure cannot be requested on a node that is not a part of theLookaheadScope");
        }
        Owner owner = this.owner;
        if (owner == null || this.ignoreRemeasureRequests || this.isVirtual) {
            return;
        }
        owner.onRequestMeasure(this, true, forceRequest, scheduleMeasureAndLayout);
        getLookaheadPassDelegate$ui_release().invalidateIntrinsicsParent(forceRequest);
    }

    public final void requestRelayout$ui_release(boolean forceRequest) {
        Owner owner;
        if (this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        e.h(owner, this, false, forceRequest, 2, null);
    }

    public final void requestRemeasure$ui_release(boolean forceRequest, boolean scheduleMeasureAndLayout) {
        Owner owner;
        if (this.ignoreRemeasureRequests || this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        e.g(owner, this, false, forceRequest, scheduleMeasureAndLayout, 2, null);
        getMeasurePassDelegate$ui_release().invalidateIntrinsicsParent(forceRequest);
    }

    public final void rescheduleRemeasureOrRelayout$ui_release(LayoutNode it) {
        if (WhenMappings.$EnumSwitchMapping$0[it.getLayoutState$ui_release().ordinal()] != 1) {
            throw new IllegalStateException("Unexpected state " + it.getLayoutState$ui_release());
        }
        if (it.getLookaheadMeasurePending$ui_release()) {
            requestLookaheadRemeasure$ui_release$default(it, true, false, 2, null);
            return;
        }
        if (it.getLookaheadLayoutPending$ui_release()) {
            it.requestLookaheadRelayout$ui_release(true);
        }
        if (it.getMeasurePending$ui_release()) {
            requestRemeasure$ui_release$default(it, true, false, 2, null);
        } else if (it.getLayoutPending$ui_release()) {
            it.requestRelayout$ui_release(true);
        }
    }

    public final void resetSubtreeIntrinsicsUsage$ui_release() {
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode = content[i10];
                UsageByParent usageByParent = layoutNode.previousIntrinsicsUsageByParent;
                layoutNode.intrinsicsUsageByParent = usageByParent;
                if (usageByParent != UsageByParent.NotUsed) {
                    layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                }
                i10++;
            } while (i10 < size);
        }
    }

    public final void setCanMultiMeasure$ui_release(boolean z) {
        this.canMultiMeasure = z;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setCompositeKeyHash(int i10) {
        this.compositeKeyHash = i10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setCompositionLocalMap(CompositionLocalMap compositionLocalMap) {
        this.compositionLocalMap = compositionLocalMap;
        setDensity((Density) compositionLocalMap.get(CompositionLocalsKt.getLocalDensity()));
        setLayoutDirection((LayoutDirection) compositionLocalMap.get(CompositionLocalsKt.getLocalLayoutDirection()));
        setViewConfiguration((ViewConfiguration) compositionLocalMap.get(CompositionLocalsKt.getLocalViewConfiguration()));
        NodeChain nodeChain = this.nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(32768);
        if ((nodeChain.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof CompositionLocalConsumerModifierNode) {
                            Modifier.Node node = ((CompositionLocalConsumerModifierNode) nodePop).getNode();
                            if (node.getIsAttached()) {
                                NodeKindKt.autoInvalidateUpdatedNode(node);
                            } else {
                                node.setUpdatedNodeAwaitingAttachForInvalidation$ui_release(true);
                            }
                        } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                            int i10 = 0;
                            nodePop = nodePop;
                            while (delegate$ui_release != null) {
                                if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                    i10++;
                                    if (i10 == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                                delegate$ui_release = delegate$ui_release.getChild();
                                nodePop = nodePop;
                            }
                            if (i10 == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if ((head.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setDensity(Density density) {
        if (p.a(this.density, density)) {
            return;
        }
        this.density = density;
        onDensityOrLayoutDirectionChanged();
        NodeChain nodeChain = this.nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
        if ((nodeChain.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof PointerInputModifierNode) {
                            ((PointerInputModifierNode) nodePop).onDensityChange();
                        } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                            int i10 = 0;
                            nodePop = nodePop;
                            while (delegate$ui_release != null) {
                                if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                    i10++;
                                    if (i10 == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                                delegate$ui_release = delegate$ui_release.getChild();
                                nodePop = nodePop;
                            }
                            if (i10 == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if ((head.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                    return;
                }
            }
        }
    }

    public final void setDepth$ui_release(int i10) {
        this.depth = i10;
    }

    public final void setInnerLayerCoordinatorIsDirty$ui_release(boolean z) {
        this.innerLayerCoordinatorIsDirty = z;
    }

    public final void setInteropViewFactoryHolder$ui_release(AndroidViewHolder androidViewHolder) {
        this.interopViewFactoryHolder = androidViewHolder;
    }

    public final void setIntrinsicsUsageByParent$ui_release(UsageByParent usageByParent) {
        this.intrinsicsUsageByParent = usageByParent;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setLayoutDirection(LayoutDirection layoutDirection) {
        if (this.layoutDirection != layoutDirection) {
            this.layoutDirection = layoutDirection;
            onDensityOrLayoutDirectionChanged();
        }
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setMeasurePolicy(MeasurePolicy measurePolicy) {
        if (p.a(this.measurePolicy, measurePolicy)) {
            return;
        }
        this.measurePolicy = measurePolicy;
        this.intrinsicsPolicy.updateFrom(getMeasurePolicy());
        invalidateMeasurements$ui_release();
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setModifier(Modifier modifier) {
        if (this.isVirtual && getModifier() != Modifier.INSTANCE) {
            throw new IllegalArgumentException("Modifiers are not supported on virtual LayoutNodes");
        }
        if (getIsDeactivated()) {
            throw new IllegalArgumentException("modifier is updated when deactivated");
        }
        this.modifier = modifier;
        this.nodes.updateFrom$ui_release(modifier);
        this.layoutDelegate.updateParentData();
        if (this.nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(512)) && this.lookaheadRoot == null) {
            setLookaheadRoot(this);
        }
    }

    public final void setNeedsOnPositionedDispatch$ui_release(boolean z) {
        this.needsOnPositionedDispatch = z;
    }

    public final void setOnAttach$ui_release(l<? super Owner, t0> lVar) {
        this.onAttach = lVar;
    }

    public final void setOnDetach$ui_release(l<? super Owner, t0> lVar) {
        this.onDetach = lVar;
    }

    public void setSemanticsId(int i10) {
        this.semanticsId = i10;
    }

    public final void setSubcompositionsState$ui_release(LayoutNodeSubcompositionsState layoutNodeSubcompositionsState) {
        this.subcompositionsState = layoutNodeSubcompositionsState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setViewConfiguration(ViewConfiguration viewConfiguration) {
        if (p.a(this.viewConfiguration, viewConfiguration)) {
            return;
        }
        this.viewConfiguration = viewConfiguration;
        NodeChain nodeChain = this.nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
        if ((nodeChain.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node head = nodeChain.getHead(); head != null; head = head.getChild()) {
                if ((head.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = head;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof PointerInputModifierNode) {
                            ((PointerInputModifierNode) nodePop).onViewConfigurationChange();
                        } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                            Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                            int i10 = 0;
                            nodePop = nodePop;
                            while (delegate$ui_release != null) {
                                if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                    i10++;
                                    if (i10 == 1) {
                                        nodePop = delegate$ui_release;
                                    } else {
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (nodePop != 0) {
                                            mutableVector.add(nodePop);
                                            nodePop = 0;
                                        }
                                        mutableVector.add(delegate$ui_release);
                                    }
                                }
                                delegate$ui_release = delegate$ui_release.getChild();
                                nodePop = nodePop;
                            }
                            if (i10 == 1) {
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                    }
                }
                if ((head.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                    return;
                }
            }
        }
    }

    public final void setVirtualLookaheadRoot$ui_release(boolean z) {
        this.isVirtualLookaheadRoot = z;
    }

    public String toString() {
        return JvmActuals_jvmKt.simpleIdentityToString(this, null) + " children: " + getChildren$ui_release().size() + " measurePolicy: " + getMeasurePolicy();
    }

    public final void updateChildrenIfDirty$ui_release() {
        if (this.virtualChildrenCount > 0) {
            recreateUnfoldedChildrenIfDirty();
        }
    }

    public LayoutNode(boolean z, int i10) {
        this.isVirtual = z;
        this.semanticsId = i10;
        this._foldedChildren = new MutableVectorWithMutationTracking<>(new MutableVector(new LayoutNode[16], 0), new LayoutNode$_foldedChildren$1(this));
        this._zSortedChildren = new MutableVector<>(new LayoutNode[16], 0);
        this.zSortedChildrenInvalidated = true;
        this.measurePolicy = ErrorMeasurePolicy;
        this.intrinsicsPolicy = new IntrinsicsPolicy(this);
        this.density = LayoutNodeKt.DefaultDensity;
        this.layoutDirection = LayoutDirection.Ltr;
        this.viewConfiguration = DummyViewConfiguration;
        this.compositionLocalMap = CompositionLocalMap.INSTANCE.getEmpty();
        UsageByParent usageByParent = UsageByParent.NotUsed;
        this.intrinsicsUsageByParent = usageByParent;
        this.previousIntrinsicsUsageByParent = usageByParent;
        this.nodes = new NodeChain(this);
        this.layoutDelegate = new LayoutNodeLayoutDelegate(this);
        this.innerLayerCoordinatorIsDirty = true;
        this.modifier = Modifier.INSTANCE;
    }

    public /* synthetic */ LayoutNode(boolean z, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? false : z, (i11 & 2) != 0 ? SemanticsModifierKt.generateSemanticsId() : i10);
    }
}
