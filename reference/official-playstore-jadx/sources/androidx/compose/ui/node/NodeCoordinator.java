package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.MutableRectKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b?\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\b \u0018\u0000 ¯\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0004¯\u0002°\u0002B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u0086\b¢\u0006\u0004\b\u0011\u0010\u0012J=\u0010\u0011\u001a\u00020\u000f\"\u0006\b\u0000\u0010\u0013\u0018\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0014ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001b\u001a\u00020\u000b¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u000fH\u0010¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u000fH&¢\u0006\u0004\b \u0010\u001eJ\u001f\u0010#\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\tH\u0014¢\u0006\u0004\b#\u0010$J\u000f\u0010&\u001a\u00020\u000fH\u0000¢\u0006\u0004\b%\u0010\u001eJ+\u0010-\u001a\u00020*2\u0006\u0010(\u001a\u00020'2\u000e\b\u0004\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020*0)H\u0084\bø\u0001\u0000¢\u0006\u0004\b+\u0010,J\r\u0010.\u001a\u00020\u000f¢\u0006\u0004\b.\u0010\u001eJ=\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0019\u00105\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0002\b4H\u0014ø\u0001\u0000¢\u0006\u0004\b6\u00107J;\u0010:\u001a\u00020\u000f2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0019\u00105\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0002\b4ø\u0001\u0000¢\u0006\u0004\b9\u00107J\u0015\u0010=\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020;¢\u0006\u0004\b=\u0010>J\u0017\u0010?\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020;H\u0016¢\u0006\u0004\b?\u0010>J\r\u0010@\u001a\u00020\u000f¢\u0006\u0004\b@\u0010\u001eJ2\u0010B\u001a\u00020\u000f2\u0019\u00105\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0002\b42\b\b\u0002\u0010A\u001a\u00020\u000b¢\u0006\u0004\bB\u0010CJ8\u0010N\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020F2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\bL\u0010MJ:\u0010P\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020F2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\bO\u0010MJ\r\u0010R\u001a\u00020Q¢\u0006\u0004\bR\u0010SJ\u001a\u0010W\u001a\u00020F2\u0006\u0010T\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bU\u0010VJ\u001a\u0010Z\u001a\u00020F2\u0006\u0010X\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bY\u0010VJ\"\u0010_\u001a\u00020F2\u0006\u0010[\u001a\u00020\u00032\u0006\u0010\\\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\b]\u0010^J\"\u0010d\u001a\u00020\u000f2\u0006\u0010[\u001a\u00020\u00032\u0006\u0010a\u001a\u00020`H\u0016ø\u0001\u0000¢\u0006\u0004\bb\u0010cJ\u001f\u0010f\u001a\u00020Q2\u0006\u0010[\u001a\u00020\u00032\u0006\u0010e\u001a\u00020\u000bH\u0016¢\u0006\u0004\bf\u0010gJ\u001a\u0010i\u001a\u00020F2\u0006\u0010X\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bh\u0010VJ,\u0010j\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020;2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u000f0\rH\u0084\b¢\u0006\u0004\bj\u0010kJ\u001a\u0010m\u001a\u00020F2\u0006\u00100\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bl\u0010VJ\u001a\u0010o\u001a\u00020F2\u0006\u00100\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bn\u0010VJ\u001f\u0010r\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020;2\u0006\u0010q\u001a\u00020pH\u0004¢\u0006\u0004\br\u0010sJ\r\u0010t\u001a\u00020\u000f¢\u0006\u0004\bt\u0010\u001eJ\r\u0010u\u001a\u00020\u000f¢\u0006\u0004\bu\u0010\u001eJ)\u0010{\u001a\u00020\u000f2\u0006\u0010w\u001a\u00020v2\u0006\u0010e\u001a\u00020\u000b2\b\b\u0002\u0010x\u001a\u00020\u000bH\u0000¢\u0006\u0004\by\u0010zJ\u001a\u0010~\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020FH\u0004ø\u0001\u0000¢\u0006\u0004\b|\u0010}J\u001b\u0010\u0080\u0001\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020FH\u0004ø\u0001\u0000¢\u0006\u0004\b\u007f\u0010}J\u0011\u0010\u0081\u0001\u001a\u00020\u000fH\u0016¢\u0006\u0005\b\u0081\u0001\u0010\u001eJ\u0011\u0010\u0082\u0001\u001a\u00020\u000fH\u0016¢\u0006\u0005\b\u0082\u0001\u0010\u001eJ\u001b\u0010\u0086\u0001\u001a\u00020\u00002\u0007\u0010\u0083\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u000f\u0010\u0087\u0001\u001a\u00020\u000b¢\u0006\u0005\b\u0087\u0001\u0010\u001cJ\u001f\u0010\u008b\u0001\u001a\u00030\u0088\u00012\b\u0010\u0089\u0001\u001a\u00030\u0088\u0001H\u0004ø\u0001\u0000¢\u0006\u0005\b\u008a\u0001\u0010VJ'\u0010\u008e\u0001\u001a\u0002012\u0006\u0010G\u001a\u00020F2\b\u0010\u0089\u0001\u001a\u00030\u0088\u0001H\u0004ø\u0001\u0000¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J\u001c\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J!\u0010\u0093\u0001\u001a\u00020\u000b2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J?\u0010\u0095\u0001\u001a\u00020\u000f2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0019\u00105\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0002\b4H\u0002ø\u0001\u0000¢\u0006\u0005\b\u0094\u0001\u00107J\u0019\u0010\u0096\u0001\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020;H\u0002¢\u0006\u0005\b\u0096\u0001\u0010>J\u001d\u0010\u0098\u0001\u001a\u00020\u000f2\t\b\u0002\u0010\u0097\u0001\u001a\u00020\u000bH\u0002¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001JC\u0010\u009c\u0001\u001a\u00020\u000f*\u0004\u0018\u00010\u000e2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020F2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001JL\u0010 \u0001\u001a\u00020\u000f*\u0004\u0018\u00010\u000e2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020F2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0007\u0010\u009d\u0001\u001a\u000201H\u0002ø\u0001\u0000¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001JL\u0010¢\u0001\u001a\u00020\u000f*\u0004\u0018\u00010\u000e2\u0006\u0010E\u001a\u00020D2\u0006\u0010G\u001a\u00020F2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0007\u0010\u009d\u0001\u001a\u000201H\u0002ø\u0001\u0000¢\u0006\u0006\b¡\u0001\u0010\u009f\u0001J\u0016\u0010£\u0001\u001a\u00020\u0000*\u00020\u0003H\u0002¢\u0006\u0006\b£\u0001\u0010¤\u0001J&\u0010¨\u0001\u001a\u00020\u000f2\u0007\u0010¥\u0001\u001a\u00020\u00002\u0006\u0010a\u001a\u00020`H\u0002ø\u0001\u0000¢\u0006\u0006\b¦\u0001\u0010§\u0001J&\u0010ª\u0001\u001a\u00020\u000f2\u0007\u0010¥\u0001\u001a\u00020\u00002\u0006\u0010a\u001a\u00020`H\u0002ø\u0001\u0000¢\u0006\u0006\b©\u0001\u0010§\u0001J'\u0010®\u0001\u001a\u00020F2\u0007\u0010¥\u0001\u001a\u00020\u00002\u0007\u0010«\u0001\u001a\u00020FH\u0002ø\u0001\u0000¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001J,\u0010®\u0001\u001a\u00020\u000f2\u0007\u0010¥\u0001\u001a\u00020\u00002\u0007\u0010¯\u0001\u001a\u00020v2\u0006\u0010e\u001a\u00020\u000bH\u0002¢\u0006\u0006\b®\u0001\u0010°\u0001J\"\u0010±\u0001\u001a\u00020\u000f2\u0006\u0010w\u001a\u00020v2\u0006\u0010e\u001a\u00020\u000bH\u0002¢\u0006\u0006\b±\u0001\u0010²\u0001J\u001c\u0010´\u0001\u001a\u00020F2\u0006\u0010G\u001a\u00020FH\u0002ø\u0001\u0000¢\u0006\u0005\b³\u0001\u0010VR\u001d\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\u0006\u0010µ\u0001\u001a\u0006\b¶\u0001\u0010·\u0001R+\u0010¸\u0001\u001a\u0004\u0018\u00010\u00008\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b¸\u0001\u0010¹\u0001\u001a\u0006\bº\u0001\u0010»\u0001\"\u0006\b¼\u0001\u0010½\u0001R+\u0010¾\u0001\u001a\u0004\u0018\u00010\u00008\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b¾\u0001\u0010¹\u0001\u001a\u0006\b¿\u0001\u0010»\u0001\"\u0006\bÀ\u0001\u0010½\u0001R\u0019\u0010Á\u0001\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u0019\u0010Ã\u0001\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0001\u0010Â\u0001RN\u00105\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0002\b42\u001a\u0010Ä\u0001\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0002\b48\u0004@BX\u0084\u000e¢\u0006\u000f\n\u0005\b5\u0010Å\u0001\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u001a\u0010É\u0001\u001a\u00030È\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010Ê\u0001R\u001a\u0010Ì\u0001\u001a\u00030Ë\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÌ\u0001\u0010Í\u0001R\u0019\u0010Î\u0001\u001a\u0002018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÎ\u0001\u0010Ï\u0001R\u001c\u0010Ñ\u0001\u001a\u0005\u0018\u00010Ð\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÑ\u0001\u0010Ò\u0001R)\u0010Õ\u0001\u001a\u0012\u0012\u0005\u0012\u00030Ô\u0001\u0012\u0004\u0012\u00020\t\u0018\u00010Ó\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÕ\u0001\u0010Ö\u0001R6\u00100\u001a\u00020/2\u0007\u0010Ä\u0001\u001a\u00020/8\u0016@TX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0017\n\u0005\b0\u0010×\u0001\u001a\u0006\bØ\u0001\u0010Ù\u0001\"\u0006\bÚ\u0001\u0010Û\u0001R0\u00102\u001a\u0002012\u0007\u0010Ä\u0001\u001a\u0002018\u0006@DX\u0086\u000e¢\u0006\u0017\n\u0005\b2\u0010Ï\u0001\u001a\u0006\bÜ\u0001\u0010Ý\u0001\"\u0006\bÞ\u0001\u0010ß\u0001R\u001b\u0010à\u0001\u001a\u0004\u0018\u00010v8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bà\u0001\u0010á\u0001R\u001c\u0010ã\u0001\u001a\u0005\u0018\u00010â\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bã\u0001\u0010ä\u0001R*\u0010å\u0001\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u000f0\r8\u0002X\u0082\u0004¢\u0006\u000f\n\u0006\bå\u0001\u0010Å\u0001\u0012\u0005\bæ\u0001\u0010\u001eR\u001d\u0010ç\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bç\u0001\u0010è\u0001R)\u0010é\u0001\u001a\u00020\u000b2\u0007\u0010Ä\u0001\u001a\u00020\u000b8\u0000@BX\u0080\u000e¢\u0006\u000f\n\u0006\bé\u0001\u0010Â\u0001\u001a\u0005\bê\u0001\u0010\u001cR0\u0010ì\u0001\u001a\u0005\u0018\u00010ë\u00012\n\u0010Ä\u0001\u001a\u0005\u0018\u00010ë\u00018\u0006@BX\u0086\u000e¢\u0006\u0010\n\u0006\bì\u0001\u0010í\u0001\u001a\u0006\bî\u0001\u0010ï\u0001R\u0017\u0010ò\u0001\u001a\u00020\u000e8&X¦\u0004¢\u0006\b\u001a\u0006\bð\u0001\u0010ñ\u0001R\u0018\u0010õ\u0001\u001a\u00030Ë\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bó\u0001\u0010ô\u0001R\u0017\u0010÷\u0001\u001a\u0002018VX\u0096\u0004¢\u0006\b\u001a\u0006\bö\u0001\u0010Ý\u0001R\u0017\u0010ù\u0001\u001a\u0002018VX\u0096\u0004¢\u0006\b\u001a\u0006\bø\u0001\u0010Ý\u0001R\u0019\u0010ü\u0001\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bú\u0001\u0010û\u0001R\u0017\u0010ÿ\u0001\u001a\u00020\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\bý\u0001\u0010þ\u0001R\u001b\u0010\u0082\u0002\u001a\u00030\u0080\u00028Fø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b\u0081\u0002\u0010Ù\u0001R\u0018\u0010\u0086\u0002\u001a\u00030\u0083\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0084\u0002\u0010\u0085\u0002R\u0019\u0010\u0088\u0002\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0087\u0002\u0010û\u0001R\u0016\u0010\u008a\u0002\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0089\u0002\u0010\u001cR\u0016\u0010\u008b\u0002\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008b\u0002\u0010\u001cR,\u0010\u0091\u0002\u001a\u00030Ð\u00012\b\u0010\u008c\u0002\u001a\u00030Ð\u00018P@PX\u0090\u000e¢\u0006\u0010\u001a\u0006\b\u008d\u0002\u0010\u008e\u0002\"\u0006\b\u008f\u0002\u0010\u0090\u0002R0\u0010\u0097\u0002\u001a\u0005\u0018\u00010\u0092\u00022\n\u0010Ä\u0001\u001a\u0005\u0018\u00010\u0092\u00028&@dX¦\u000e¢\u0006\u0010\u001a\u0006\b\u0093\u0002\u0010\u0094\u0002\"\u0006\b\u0095\u0002\u0010\u0096\u0002R\u001f\u0010\u009b\u0002\u001a\n\u0012\u0005\u0012\u00030Ô\u00010\u0098\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0099\u0002\u0010\u009a\u0002R\u001a\u0010\u009f\u0002\u001a\u0005\u0018\u00010\u009c\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009d\u0002\u0010\u009e\u0002R\u0016\u0010¡\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\b\u001a\u0006\b \u0002\u0010þ\u0001R\u0016\u0010£\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\b\u001a\u0006\b¢\u0002\u0010þ\u0001R\u0017\u0010¦\u0002\u001a\u00020v8DX\u0084\u0004¢\u0006\b\u001a\u0006\b¤\u0002\u0010¥\u0002R\u001d\u0010¨\u0002\u001a\u00020'8@X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b§\u0002\u0010Ù\u0001R\u0016\u0010©\u0002\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b©\u0002\u0010\u001cR\u001b\u0010\u0089\u0001\u001a\u00030\u0088\u00018Fø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\bª\u0002\u0010Ù\u0001R\u0018\u0010®\u0002\u001a\u00030«\u00028BX\u0082\u0004¢\u0006\b\u001a\u0006\b¬\u0002\u0010\u00ad\u0002\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006±\u0002"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/node/OwnerScope;", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "", "mask", "", "includeTail", "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier$Node;", "Lx6/t0;", "block", "visitNodes", "(IZLr7/l;)V", "T", "Landroidx/compose/ui/node/NodeKind;", LinkHeader.Parameters.Type, "visitNodes-aLcG6gQ", "(ILr7/l;)V", "head-H91voCI", "(I)Landroidx/compose/ui/Modifier$Node;", TtmlNode.TAG_HEAD, "isTransparent", "()Z", "replace$ui_release", "()V", "replace", "ensureLookaheadDelegateCreated", "width", "height", "onMeasureResultChanged", "(II)V", "onCoordinatesUsed$ui_release", "onCoordinatesUsed", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/Placeable;", "performingMeasure-K40F9xA", "(JLr7/a;)Landroidx/compose/ui/layout/Placeable;", "performingMeasure", "onMeasured", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "placeAt-f8xVGno", "(JFLr7/l;)V", "placeAt", "placeSelfApparentToRealOffset-f8xVGno", "placeSelfApparentToRealOffset", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "draw", "(Landroidx/compose/ui/graphics/Canvas;)V", "performDraw", "onPlaced", "forceUpdateLayerParameters", "updateLayerBlock", "(Lr7/l;Z)V", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "hitTestSource", "Landroidx/compose/ui/geometry/Offset;", "pointerPosition", "Landroidx/compose/ui/node/HitTestResult;", "hitTestResult", "isTouchEvent", "isInLayer", "hitTest-YqVAtuI", "(Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "hitTest", "hitTestChild-YqVAtuI", "hitTestChild", "Landroidx/compose/ui/geometry/Rect;", "touchBoundsInRoot", "()Landroidx/compose/ui/geometry/Rect;", "relativeToWindow", "windowToLocal-MK-Hz9U", "(J)J", "windowToLocal", "relativeToLocal", "localToWindow-MK-Hz9U", "localToWindow", "sourceCoordinates", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "localPositionOf", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformFrom", "clipBounds", "localBoundingBoxOf", "(Landroidx/compose/ui/layout/LayoutCoordinates;Z)Landroidx/compose/ui/geometry/Rect;", "localToRoot-MK-Hz9U", "localToRoot", "withPositionTranslation", "(Landroidx/compose/ui/graphics/Canvas;Lr7/l;)V", "toParentPosition-MK-Hz9U", "toParentPosition", "fromParentPosition-MK-Hz9U", "fromParentPosition", "Landroidx/compose/ui/graphics/Paint;", "paint", "drawBorder", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Paint;)V", "onLayoutNodeAttach", "onRelease", "Landroidx/compose/ui/geometry/MutableRect;", "bounds", "clipToMinimumTouchTargetSize", "rectInParent$ui_release", "(Landroidx/compose/ui/geometry/MutableRect;ZZ)V", "rectInParent", "withinLayerBounds-k-4lQ0M", "(J)Z", "withinLayerBounds", "isPointerInBounds-k-4lQ0M", "isPointerInBounds", "invalidateLayer", "onLayoutModifierNodeChanged", "other", "findCommonAncestor$ui_release", "(Landroidx/compose/ui/node/NodeCoordinator;)Landroidx/compose/ui/node/NodeCoordinator;", "findCommonAncestor", "shouldSharePointerInputWithSiblings", "Landroidx/compose/ui/geometry/Size;", "minimumTouchTargetSize", "calculateMinimumTouchTargetPadding-E7KxVPU", "calculateMinimumTouchTargetPadding", "distanceInMinimumTouchTarget-tz77jQw", "(JJ)F", "distanceInMinimumTouchTarget", "headNode", "(Z)Landroidx/compose/ui/Modifier$Node;", "hasNode-H91voCI", "(I)Z", "hasNode", "placeSelf-f8xVGno", "placeSelf", "drawContainedDrawModifiers", "invokeOnLayoutChange", "updateLayerParameters", "(Z)V", "hit-1hIXUjU", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "hit", "distanceFromEdge", "hitNear-JHbHoSQ", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;ZZF)V", "hitNear", "speculativeHit-JHbHoSQ", "speculativeHit", "toCoordinator", "(Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/node/NodeCoordinator;", "ancestor", "transformToAncestor-EL8BTi8", "(Landroidx/compose/ui/node/NodeCoordinator;[F)V", "transformToAncestor", "transformFromAncestor-EL8BTi8", "transformFromAncestor", "offset", "ancestorToLocal-R5De75A", "(Landroidx/compose/ui/node/NodeCoordinator;J)J", "ancestorToLocal", "rect", "(Landroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/geometry/MutableRect;Z)V", "fromParentRect", "(Landroidx/compose/ui/geometry/MutableRect;Z)V", "offsetFromEdge-MK-Hz9U", "offsetFromEdge", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "wrapped", "Landroidx/compose/ui/node/NodeCoordinator;", "getWrapped$ui_release", "()Landroidx/compose/ui/node/NodeCoordinator;", "setWrapped$ui_release", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "wrappedBy", "getWrappedBy$ui_release", "setWrappedBy$ui_release", "released", "Z", "isClipping", "<set-?>", "Lr7/l;", "getLayerBlock", "()Lr7/l;", "Landroidx/compose/ui/unit/Density;", "layerDensity", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/LayoutDirection;", "layerLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "lastLayerAlpha", "F", "Landroidx/compose/ui/layout/MeasureResult;", "_measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "", "Landroidx/compose/ui/layout/AlignmentLine;", "oldAlignmentLines", "Ljava/util/Map;", "J", "getPosition-nOcc-ac", "()J", "setPosition--gyyYBs", "(J)V", "getZIndex", "()F", "setZIndex", "(F)V", "_rectCache", "Landroidx/compose/ui/geometry/MutableRect;", "Landroidx/compose/ui/node/LayerPositionalProperties;", "layerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "drawBlock", "getDrawBlock$annotations", "invalidateParentLayer", "Lr7/a;", "lastLayerDrawingWasSkipped", "getLastLayerDrawingWasSkipped$ui_release", "Landroidx/compose/ui/node/OwnedLayer;", "layer", "Landroidx/compose/ui/node/OwnedLayer;", "getLayer", "()Landroidx/compose/ui/node/OwnedLayer;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "tail", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getDensity", "density", "getFontScale", "fontScale", "getParent", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "parent", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "alignmentLinesOwner", "getChild", "child", "getHasMeasureResult", "hasMeasureResult", "isAttached", "value", "getMeasureResult$ui_release", "()Landroidx/compose/ui/layout/MeasureResult;", "setMeasureResult$ui_release", "(Landroidx/compose/ui/layout/MeasureResult;)V", "measureResult", "Landroidx/compose/ui/node/LookaheadDelegate;", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "setLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "lookaheadDelegate", "", "getProvidedAlignmentLines", "()Ljava/util/Set;", "providedAlignmentLines", "", "getParentData", "()Ljava/lang/Object;", "parentData", "getParentLayoutCoordinates", "parentLayoutCoordinates", "getParentCoordinates", "parentCoordinates", "getRectCache", "()Landroidx/compose/ui/geometry/MutableRect;", "rectCache", "getLastMeasurementConstraints-msEJaDk$ui_release", "lastMeasurementConstraints", "isValidOwnerScope", "getMinimumTouchTargetSize-NH-jbRc", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "snapshotObserver", "Companion", "HitTestSource", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope {
    public static final int $stable = 0;
    public static final String ExpectAttachedLayoutCoordinates = "LayoutCoordinate operations are only valid when isAttached is true";
    public static final String UnmeasuredError = "Asking for measurement result of unmeasured layout modifier";
    private MeasureResult _measureResult;
    private MutableRect _rectCache;
    private boolean isClipping;
    private boolean lastLayerDrawingWasSkipped;
    private OwnedLayer layer;
    private l<? super GraphicsLayerScope, t0> layerBlock;
    private LayerPositionalProperties layerPositionalProperties;
    private final LayoutNode layoutNode;
    private Map<AlignmentLine, Integer> oldAlignmentLines;
    private boolean released;
    private NodeCoordinator wrapped;
    private NodeCoordinator wrappedBy;
    private float zIndex;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final l<NodeCoordinator, t0> onCommitAffectingLayerParams = NodeCoordinator$Companion$onCommitAffectingLayerParams$1.INSTANCE;
    private static final l<NodeCoordinator, t0> onCommitAffectingLayer = NodeCoordinator$Companion$onCommitAffectingLayer$1.INSTANCE;
    private static final ReusableGraphicsLayerScope graphicsLayerScope = new ReusableGraphicsLayerScope();
    private static final LayerPositionalProperties tmpLayerPositionalProperties = new LayerPositionalProperties();
    private static final float[] tmpMatrix = Matrix.m3684constructorimpl$default(null, 1, null);
    private static final HitTestSource PointerInputSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$PointerInputSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-YqVAtuI, reason: not valid java name */
        public void mo4832childHitTestYqVAtuI(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
            layoutNode.m4752hitTestM_7yMNQ$ui_release(pointerPosition, hitTestResult, isTouchEvent, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw, reason: not valid java name */
        public int mo4833entityTypeOLwlOKw() {
            return NodeKind.m4843constructorimpl(16);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v7 */
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
            MutableVector mutableVector = null;
            while (node != 0) {
                if (node instanceof PointerInputModifierNode) {
                    if (((PointerInputModifierNode) node).interceptOutOfBoundsChildEvents()) {
                        return true;
                    }
                } else if ((node.getKindSet() & iM4843constructorimpl) != 0 && (node instanceof DelegatingNode)) {
                    Modifier.Node delegate = ((DelegatingNode) node).getDelegate();
                    int i10 = 0;
                    node = node;
                    while (delegate != null) {
                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                            i10++;
                            if (i10 == 1) {
                                node = delegate;
                            } else {
                                if (mutableVector == null) {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node != 0) {
                                    mutableVector.add(node);
                                    node = 0;
                                }
                                mutableVector.add(delegate);
                            }
                        }
                        delegate = delegate.getChild();
                        node = node;
                    }
                    if (i10 == 1) {
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector);
            }
            return false;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            return true;
        }
    };
    private static final HitTestSource SemanticsSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$SemanticsSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-YqVAtuI */
        public void mo4832childHitTestYqVAtuI(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
            layoutNode.m4753hitTestSemanticsM_7yMNQ$ui_release(pointerPosition, hitTestResult, isTouchEvent, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw */
        public int mo4833entityTypeOLwlOKw() {
            return NodeKind.m4843constructorimpl(8);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            return false;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            SemanticsConfiguration collapsedSemantics$ui_release = parentLayoutNode.getCollapsedSemantics$ui_release();
            boolean z = false;
            if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.getIsClearingSemantics()) {
                z = true;
            }
            return !z;
        }
    };
    private Density layerDensity = getLayoutNode().getDensity();
    private LayoutDirection layerLayoutDirection = getLayoutNode().getLayoutDirection();
    private float lastLayerAlpha = 0.8f;
    private long position = IntOffset.INSTANCE.m5812getZeronOccac();
    private final l<Canvas, t0> drawBlock = new NodeCoordinator$drawBlock$1(this);
    private final r7.a<t0> invalidateParentLayer = new NodeCoordinator$invalidateParentLayer$1(this);

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\f\u001a\u00020\u000b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R \u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$Companion;", "", "<init>", "()V", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "PointerInputSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "getPointerInputSource", "()Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "SemanticsSource", "getSemanticsSource", "", "ExpectAttachedLayoutCoordinates", "Ljava/lang/String;", "UnmeasuredError", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "graphicsLayerScope", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "Lkotlin/Function1;", "Landroidx/compose/ui/node/NodeCoordinator;", "Lx6/t0;", "onCommitAffectingLayer", "Lr7/l;", "onCommitAffectingLayerParams", "Landroidx/compose/ui/node/LayerPositionalProperties;", "tmpLayerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "Landroidx/compose/ui/graphics/Matrix;", "tmpMatrix", "[F", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final HitTestSource getPointerInputSource() {
            return NodeCoordinator.PointerInputSource;
        }

        public final HitTestSource getSemanticsSource() {
            return NodeCoordinator.SemanticsSource;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0002H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\r\u0010\u000eJ:\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH&ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u001aÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "", "Landroidx/compose/ui/node/NodeKind;", "entityType-OLwlOKw", "()I", "entityType", "Landroidx/compose/ui/Modifier$Node;", "node", "", "interceptOutOfBoundsChildEvents", "(Landroidx/compose/ui/Modifier$Node;)Z", "Landroidx/compose/ui/node/LayoutNode;", "parentLayoutNode", "shouldHitTestChildren", "(Landroidx/compose/ui/node/LayoutNode;)Z", "layoutNode", "Landroidx/compose/ui/geometry/Offset;", "pointerPosition", "Landroidx/compose/ui/node/HitTestResult;", "hitTestResult", "isTouchEvent", "isInLayer", "Lx6/t0;", "childHitTest-YqVAtuI", "(Landroidx/compose/ui/node/LayoutNode;JLandroidx/compose/ui/node/HitTestResult;ZZ)V", "childHitTest", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface HitTestSource {
        /* JADX INFO: renamed from: childHitTest-YqVAtuI */
        void mo4832childHitTestYqVAtuI(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer);

        /* JADX INFO: renamed from: entityType-OLwlOKw */
        int mo4833entityTypeOLwlOKw();

        boolean interceptOutOfBoundsChildEvents(Modifier.Node node);

        boolean shouldHitTestChildren(LayoutNode parentLayoutNode);
    }

    /* JADX INFO: renamed from: androidx.compose.ui.node.NodeCoordinator$updateLayerParameters$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ l<GraphicsLayerScope, t0> $layerBlock;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super GraphicsLayerScope, t0> lVar) {
            super(0);
            this.$layerBlock = lVar;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4839invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4839invoke() {
            this.$layerBlock.invoke(NodeCoordinator.graphicsLayerScope);
        }
    }

    public NodeCoordinator(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    private final void ancestorToLocal(NodeCoordinator ancestor, MutableRect rect, boolean clipBounds) {
        if (ancestor == this) {
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.ancestorToLocal(ancestor, rect, clipBounds);
        }
        fromParentRect(rect, clipBounds);
    }

    /* JADX INFO: renamed from: ancestorToLocal-R5De75A, reason: not valid java name */
    private final long m4809ancestorToLocalR5De75A(NodeCoordinator ancestor, long offset) {
        if (ancestor == this) {
            return offset;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        return (nodeCoordinator == null || p.a(ancestor, nodeCoordinator)) ? m4820fromParentPositionMKHz9U(offset) : m4820fromParentPositionMKHz9U(nodeCoordinator.m4809ancestorToLocalR5De75A(ancestor, offset));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawContainedDrawModifiers(Canvas canvas) {
        Modifier.Node nodeM4823headH91voCI = m4823headH91voCI(NodeKind.m4843constructorimpl(4));
        if (nodeM4823headH91voCI == null) {
            performDraw(canvas);
        } else {
            getLayoutNode().getMDrawScope$ui_release().m4764drawx_KDEd0$ui_release(canvas, IntSizeKt.m5854toSizeozmzZPI(mo4638getSizeYbymL2g()), this, nodeM4823headH91voCI);
        }
    }

    private final void fromParentRect(MutableRect bounds, boolean clipBounds) {
        float fM5802getXimpl = IntOffset.m5802getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() - fM5802getXimpl);
        bounds.setRight(bounds.getRight() - fM5802getXimpl);
        float fM5803getYimpl = IntOffset.m5803getYimpl(getPosition());
        bounds.setTop(bounds.getTop() - fM5803getYimpl);
        bounds.setBottom(bounds.getBottom() - fM5803getYimpl);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(bounds, true);
            if (this.isClipping && clipBounds) {
                bounds.intersect(0.0f, 0.0f, IntSize.m5844getWidthimpl(mo4638getSizeYbymL2g()), IntSize.m5843getHeightimpl(mo4638getSizeYbymL2g()));
                bounds.isEmpty();
            }
        }
    }

    private static /* synthetic */ void getDrawBlock$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OwnerSnapshotObserver getSnapshotObserver() {
        return LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
    }

    /* JADX INFO: renamed from: hasNode-H91voCI, reason: not valid java name */
    private final boolean m4810hasNodeH91voCI(int type) {
        Modifier.Node nodeHeadNode = headNode(NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(type));
        return nodeHeadNode != null && DelegatableNodeKt.m4724has64DMado(nodeHeadNode, type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node headNode(boolean includeTail) {
        Modifier.Node tail;
        if (getLayoutNode().getOuterCoordinator$ui_release() == this) {
            return getLayoutNode().getNodes().getHead();
        }
        if (!includeTail) {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            if (nodeCoordinator != null) {
                return nodeCoordinator.getTail();
            }
            return null;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        if (nodeCoordinator2 == null || (tail = nodeCoordinator2.getTail()) == null) {
            return null;
        }
        return tail.getChild();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hit-1hIXUjU, reason: not valid java name */
    public final void m4811hit1hIXUjU(Modifier.Node node, HitTestSource hitTestSource, long j10, HitTestResult hitTestResult, boolean z, boolean z5) {
        if (node == null) {
            mo4747hitTestChildYqVAtuI(hitTestSource, j10, hitTestResult, z, z5);
        } else {
            hitTestResult.hit(node, z5, new NodeCoordinator$hit$1(this, node, hitTestSource, j10, hitTestResult, z, z5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hitNear-JHbHoSQ, reason: not valid java name */
    public final void m4812hitNearJHbHoSQ(Modifier.Node node, HitTestSource hitTestSource, long j10, HitTestResult hitTestResult, boolean z, boolean z5, float f10) {
        if (node == null) {
            mo4747hitTestChildYqVAtuI(hitTestSource, j10, hitTestResult, z, z5);
        } else {
            hitTestResult.hitInMinimumTouchTarget(node, f10, z5, new NodeCoordinator$hitNear$1(this, node, hitTestSource, j10, hitTestResult, z, z5, f10));
        }
    }

    /* JADX INFO: renamed from: offsetFromEdge-MK-Hz9U, reason: not valid java name */
    private final long m4813offsetFromEdgeMKHz9U(long pointerPosition) {
        float fM3236getXimpl = Offset.m3236getXimpl(pointerPosition);
        float fMax = Math.max(0.0f, fM3236getXimpl < 0.0f ? -fM3236getXimpl : fM3236getXimpl - getMeasuredWidth());
        float fM3237getYimpl = Offset.m3237getYimpl(pointerPosition);
        return OffsetKt.Offset(fMax, Math.max(0.0f, fM3237getYimpl < 0.0f ? -fM3237getYimpl : fM3237getYimpl - getMeasuredHeight()));
    }

    /* JADX INFO: renamed from: placeSelf-f8xVGno, reason: not valid java name */
    private final void m4814placeSelff8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
        updateLayerBlock$default(this, layerBlock, false, 2, null);
        if (!IntOffset.m5801equalsimpl0(getPosition(), position)) {
            m4828setPositiongyyYBs(position);
            getLayoutNode().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo4893movegyyYBs(position);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            invalidateAlignmentLinesFromPositionChange(this);
            Owner owner = getLayoutNode().getOwner();
            if (owner != null) {
                owner.onLayoutChange(getLayoutNode());
            }
        }
        this.zIndex = zIndex;
    }

    public static /* synthetic */ void rectInParent$ui_release$default(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z, boolean z5, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rectInParent");
        }
        if ((i10 & 4) != 0) {
            z5 = false;
        }
        nodeCoordinator.rectInParent$ui_release(mutableRect, z, z5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: speculativeHit-JHbHoSQ, reason: not valid java name */
    public final void m4815speculativeHitJHbHoSQ(Modifier.Node node, HitTestSource hitTestSource, long j10, HitTestResult hitTestResult, boolean z, boolean z5, float f10) {
        if (node == null) {
            mo4747hitTestChildYqVAtuI(hitTestSource, j10, hitTestResult, z, z5);
        } else if (hitTestSource.interceptOutOfBoundsChildEvents(node)) {
            hitTestResult.speculativeHit(node, f10, z5, new NodeCoordinator$speculativeHit$1(this, node, hitTestSource, j10, hitTestResult, z, z5, f10));
        } else {
            m4815speculativeHitJHbHoSQ(NodeCoordinatorKt.m4841nextUntilhw7D004(node, hitTestSource.mo4833entityTypeOLwlOKw(), NodeKind.m4843constructorimpl(2)), hitTestSource, j10, hitTestResult, z, z5, f10);
        }
    }

    private final NodeCoordinator toCoordinator(LayoutCoordinates layoutCoordinates) {
        NodeCoordinator coordinator;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
        return (lookaheadLayoutCoordinates == null || (coordinator = lookaheadLayoutCoordinates.getCoordinator()) == null) ? (NodeCoordinator) layoutCoordinates : coordinator;
    }

    /* JADX INFO: renamed from: transformFromAncestor-EL8BTi8, reason: not valid java name */
    private final void m4816transformFromAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        if (p.a(ancestor, this)) {
            return;
        }
        this.wrappedBy.m4816transformFromAncestorEL8BTi8(ancestor, matrix);
        if (!IntOffset.m5801equalsimpl0(getPosition(), IntOffset.INSTANCE.m5812getZeronOccac())) {
            float[] fArr = tmpMatrix;
            Matrix.m3693resetimpl(fArr);
            Matrix.m3704translateimpl$default(fArr, -IntOffset.m5802getXimpl(getPosition()), -IntOffset.m5803getYimpl(getPosition()), 0.0f, 4, null);
            Matrix.m3701timesAssign58bKbWc(matrix, fArr);
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo4890inverseTransform58bKbWc(matrix);
        }
    }

    /* JADX INFO: renamed from: transformToAncestor-EL8BTi8, reason: not valid java name */
    private final void m4817transformToAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        for (NodeCoordinator nodeCoordinator = this; !nodeCoordinator.equals(ancestor); nodeCoordinator = nodeCoordinator.wrappedBy) {
            OwnedLayer ownedLayer = nodeCoordinator.layer;
            if (ownedLayer != null) {
                ownedLayer.mo4895transform58bKbWc(matrix);
            }
            if (!IntOffset.m5801equalsimpl0(nodeCoordinator.getPosition(), IntOffset.INSTANCE.m5812getZeronOccac())) {
                float[] fArr = tmpMatrix;
                Matrix.m3693resetimpl(fArr);
                Matrix.m3704translateimpl$default(fArr, IntOffset.m5802getXimpl(r1), IntOffset.m5803getYimpl(r1), 0.0f, 4, null);
                Matrix.m3701timesAssign58bKbWc(matrix, fArr);
            }
        }
    }

    public static /* synthetic */ void updateLayerBlock$default(NodeCoordinator nodeCoordinator, l lVar, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLayerBlock");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        nodeCoordinator.updateLayerBlock(lVar, z);
    }

    private final void updateLayerParameters(boolean invokeOnLayoutChange) {
        Owner owner;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer == null) {
            if (this.layerBlock != null) {
                throw new IllegalStateException("null layer with a non-null layerBlock");
            }
            return;
        }
        l<? super GraphicsLayerScope, t0> lVar = this.layerBlock;
        if (lVar == null) {
            throw new IllegalStateException("updateLayerParameters requires a non-null layerBlock");
        }
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = graphicsLayerScope;
        reusableGraphicsLayerScope.reset();
        reusableGraphicsLayerScope.setGraphicsDensity$ui_release(getLayoutNode().getDensity());
        reusableGraphicsLayerScope.m3762setSizeuvyYCjk(IntSizeKt.m5854toSizeozmzZPI(mo4638getSizeYbymL2g()));
        getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingLayerParams, new AnonymousClass1(lVar));
        LayerPositionalProperties layerPositionalProperties = this.layerPositionalProperties;
        if (layerPositionalProperties == null) {
            layerPositionalProperties = new LayerPositionalProperties();
            this.layerPositionalProperties = layerPositionalProperties;
        }
        layerPositionalProperties.copyFrom(reusableGraphicsLayerScope);
        ownedLayer.updateLayerProperties(reusableGraphicsLayerScope, getLayoutNode().getLayoutDirection(), getLayoutNode().getDensity());
        this.isClipping = reusableGraphicsLayerScope.getClip();
        this.lastLayerAlpha = reusableGraphicsLayerScope.getAlpha();
        if (!invokeOnLayoutChange || (owner = getLayoutNode().getOwner()) == null) {
            return;
        }
        owner.onLayoutChange(getLayoutNode());
    }

    public static /* synthetic */ void updateLayerParameters$default(NodeCoordinator nodeCoordinator, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLayerParameters");
        }
        if ((i10 & 1) != 0) {
            z = true;
        }
        nodeCoordinator.updateLayerParameters(z);
    }

    /* JADX INFO: renamed from: calculateMinimumTouchTargetPadding-E7KxVPU, reason: not valid java name */
    public final long m4818calculateMinimumTouchTargetPaddingE7KxVPU(long minimumTouchTargetSize) {
        return SizeKt.Size(Math.max(0.0f, (Size.m3305getWidthimpl(minimumTouchTargetSize) - getMeasuredWidth()) / 2.0f), Math.max(0.0f, (Size.m3302getHeightimpl(minimumTouchTargetSize) - getMeasuredHeight()) / 2.0f));
    }

    /* JADX INFO: renamed from: distanceInMinimumTouchTarget-tz77jQw, reason: not valid java name */
    public final float m4819distanceInMinimumTouchTargettz77jQw(long pointerPosition, long minimumTouchTargetSize) {
        if (getMeasuredWidth() >= Size.m3305getWidthimpl(minimumTouchTargetSize) && getMeasuredHeight() >= Size.m3302getHeightimpl(minimumTouchTargetSize)) {
            return Float.POSITIVE_INFINITY;
        }
        long jM4818calculateMinimumTouchTargetPaddingE7KxVPU = m4818calculateMinimumTouchTargetPaddingE7KxVPU(minimumTouchTargetSize);
        float fM3305getWidthimpl = Size.m3305getWidthimpl(jM4818calculateMinimumTouchTargetPaddingE7KxVPU);
        float fM3302getHeightimpl = Size.m3302getHeightimpl(jM4818calculateMinimumTouchTargetPaddingE7KxVPU);
        long jM4813offsetFromEdgeMKHz9U = m4813offsetFromEdgeMKHz9U(pointerPosition);
        if ((fM3305getWidthimpl > 0.0f || fM3302getHeightimpl > 0.0f) && Offset.m3236getXimpl(jM4813offsetFromEdgeMKHz9U) <= fM3305getWidthimpl && Offset.m3237getYimpl(jM4813offsetFromEdgeMKHz9U) <= fM3302getHeightimpl) {
            return Offset.m3235getDistanceSquaredimpl(jM4813offsetFromEdgeMKHz9U);
        }
        return Float.POSITIVE_INFINITY;
    }

    public final void draw(Canvas canvas) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas);
            return;
        }
        float fM5802getXimpl = IntOffset.m5802getXimpl(getPosition());
        float fM5803getYimpl = IntOffset.m5803getYimpl(getPosition());
        canvas.translate(fM5802getXimpl, fM5803getYimpl);
        drawContainedDrawModifiers(canvas);
        canvas.translate(-fM5802getXimpl, -fM5803getYimpl);
    }

    public final void drawBorder(Canvas canvas, Paint paint) {
        canvas.drawRect(new Rect(0.5f, 0.5f, IntSize.m5844getWidthimpl(getMeasuredSize()) - 0.5f, IntSize.m5843getHeightimpl(getMeasuredSize()) - 0.5f), paint);
    }

    public abstract void ensureLookaheadDelegateCreated();

    public final NodeCoordinator findCommonAncestor$ui_release(NodeCoordinator other) {
        LayoutNode layoutNode = other.getLayoutNode();
        LayoutNode layoutNode2 = getLayoutNode();
        if (layoutNode == layoutNode2) {
            Modifier.Node tail = other.getTail();
            Modifier.Node tail2 = getTail();
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(2);
            if (!tail2.getNode().getIsAttached()) {
                throw new IllegalStateException("visitLocalAncestors called on an unattached node");
            }
            for (Modifier.Node parent$ui_release = tail2.getNode().getParent(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent()) {
                if ((parent$ui_release.getKindSet() & iM4843constructorimpl) != 0 && parent$ui_release == tail) {
                    return other;
                }
            }
            return this;
        }
        while (layoutNode.getDepth() > layoutNode2.getDepth()) {
            layoutNode = layoutNode.getParent$ui_release();
        }
        while (layoutNode2.getDepth() > layoutNode.getDepth()) {
            layoutNode2 = layoutNode2.getParent$ui_release();
        }
        while (layoutNode != layoutNode2) {
            layoutNode = layoutNode.getParent$ui_release();
            layoutNode2 = layoutNode2.getParent$ui_release();
            if (layoutNode == null || layoutNode2 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        if (layoutNode2 != getLayoutNode()) {
            if (layoutNode != other.getLayoutNode()) {
                return layoutNode.getInnerCoordinator$ui_release();
            }
            return other;
        }
        return this;
    }

    /* JADX INFO: renamed from: fromParentPosition-MK-Hz9U, reason: not valid java name */
    public long m4820fromParentPositionMKHz9U(long position) {
        long jM5814minusNvtHpc = IntOffsetKt.m5814minusNvtHpc(position, getPosition());
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer != null ? ownedLayer.mo4892mapOffset8S9VItk(jM5814minusNvtHpc, true) : jM5814minusNvtHpc;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public AlignmentLinesOwner getAlignmentLinesOwner() {
        return getLayoutNode().getLayoutDelegate().getAlignmentLinesOwner$ui_release();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return getLayoutNode().getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return getLayoutNode().getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    /* JADX INFO: renamed from: getLastLayerDrawingWasSkipped$ui_release, reason: from getter */
    public final boolean getLastLayerDrawingWasSkipped() {
        return this.lastLayerDrawingWasSkipped;
    }

    /* JADX INFO: renamed from: getLastMeasurementConstraints-msEJaDk$ui_release, reason: not valid java name */
    public final long m4821getLastMeasurementConstraintsmsEJaDk$ui_release() {
        return getMeasurementConstraints();
    }

    public final OwnedLayer getLayer() {
        return this.layer;
    }

    public final l<GraphicsLayerScope, t0> getLayerBlock() {
        return this.layerBlock;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return getLayoutNode().getLayoutDirection();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public abstract LookaheadDelegate getLookaheadDelegate();

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui_release() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException(UnmeasuredError);
    }

    /* JADX INFO: renamed from: getMinimumTouchTargetSize-NH-jbRc, reason: not valid java name */
    public final long m4822getMinimumTouchTargetSizeNHjbRc() {
        return this.layerDensity.mo286toSizeXkaWNTQ(getLayoutNode().getViewConfiguration().mo4756getMinimumTouchTargetSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentCoordinates() {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui_release();
        return this.wrappedBy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7 */
    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        if (!getLayoutNode().getNodes().m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(64))) {
            return null;
        }
        getTail();
        Object objModifyParentData = null;
        for (Modifier.Node tail = getLayoutNode().getNodes().getTail(); tail != null; tail = tail.getParent()) {
            if ((NodeKind.m4843constructorimpl(64) & tail.getKindSet()) != 0) {
                int iM4843constructorimpl = NodeKind.m4843constructorimpl(64);
                Modifier.Node nodePop = tail;
                MutableVector mutableVector = null;
                while (nodePop != 0) {
                    if (nodePop instanceof ParentDataModifierNode) {
                        objModifyParentData = ((ParentDataModifierNode) nodePop).modifyParentData(getLayoutNode().getDensity(), objModifyParentData);
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
        }
        return objModifyParentData;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui_release();
        return getLayoutNode().getOuterCoordinator$ui_release().wrappedBy;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: from getter */
    public long getPosition() {
        return this.position;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        LinkedHashSet linkedHashSet = null;
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != null; nodeCoordinator = nodeCoordinator.wrapped) {
            MeasureResult measureResult = nodeCoordinator._measureResult;
            Map<AlignmentLine, Integer> alignmentLines = measureResult != null ? measureResult.getAlignmentLines() : null;
            if (alignmentLines != null && (!alignmentLines.isEmpty())) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.addAll(alignmentLines.keySet());
            }
        }
        return linkedHashSet == null ? b0.f19686i : linkedHashSet;
    }

    public final MutableRect getRectCache() {
        MutableRect mutableRect = this._rectCache;
        if (mutableRect != null) {
            return mutableRect;
        }
        MutableRect mutableRect2 = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
        this._rectCache = mutableRect2;
        return mutableRect2;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public final long mo4638getSizeYbymL2g() {
        return getMeasuredSize();
    }

    public abstract Modifier.Node getTail();

    /* JADX INFO: renamed from: getWrapped$ui_release, reason: from getter */
    public final NodeCoordinator getWrapped() {
        return this.wrapped;
    }

    /* JADX INFO: renamed from: getWrappedBy$ui_release, reason: from getter */
    public final NodeCoordinator getWrappedBy() {
        return this.wrappedBy;
    }

    public final float getZIndex() {
        return this.zIndex;
    }

    /* JADX INFO: renamed from: head-H91voCI, reason: not valid java name */
    public final Modifier.Node m4823headH91voCI(int type) {
        boolean zM4852getIncludeSelfInTraversalH91voCI = NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node tail = getTail();
        if (!zM4852getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return null;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM4852getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & type) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & type) != 0) {
                return nodeHeadNode;
            }
            if (nodeHeadNode == tail) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: hitTest-YqVAtuI, reason: not valid java name */
    public final void m4824hitTestYqVAtuI(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
        Modifier.Node nodeM4823headH91voCI = m4823headH91voCI(hitTestSource.mo4833entityTypeOLwlOKw());
        if (!m4831withinLayerBoundsk4lQ0M(pointerPosition)) {
            if (isTouchEvent) {
                float fM4819distanceInMinimumTouchTargettz77jQw = m4819distanceInMinimumTouchTargettz77jQw(pointerPosition, m4822getMinimumTouchTargetSizeNHjbRc());
                if (Float.isInfinite(fM4819distanceInMinimumTouchTargettz77jQw) || Float.isNaN(fM4819distanceInMinimumTouchTargettz77jQw) || !hitTestResult.isHitInMinimumTouchTargetBetter(fM4819distanceInMinimumTouchTargettz77jQw, false)) {
                    return;
                }
                m4812hitNearJHbHoSQ(nodeM4823headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, false, fM4819distanceInMinimumTouchTargettz77jQw);
                return;
            }
            return;
        }
        if (nodeM4823headH91voCI == null) {
            mo4747hitTestChildYqVAtuI(hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer);
            return;
        }
        if (m4825isPointerInBoundsk4lQ0M(pointerPosition)) {
            m4811hit1hIXUjU(nodeM4823headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer);
            return;
        }
        float fM4819distanceInMinimumTouchTargettz77jQw2 = !isTouchEvent ? Float.POSITIVE_INFINITY : m4819distanceInMinimumTouchTargettz77jQw(pointerPosition, m4822getMinimumTouchTargetSizeNHjbRc());
        if (Float.isInfinite(fM4819distanceInMinimumTouchTargettz77jQw2) || Float.isNaN(fM4819distanceInMinimumTouchTargettz77jQw2) || !hitTestResult.isHitInMinimumTouchTargetBetter(fM4819distanceInMinimumTouchTargettz77jQw2, isInLayer)) {
            m4815speculativeHitJHbHoSQ(nodeM4823headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer, fM4819distanceInMinimumTouchTargettz77jQw2);
        } else {
            m4812hitNearJHbHoSQ(nodeM4823headH91voCI, hitTestSource, pointerPosition, hitTestResult, isTouchEvent, isInLayer, fM4819distanceInMinimumTouchTargettz77jQw2);
        }
    }

    /* JADX INFO: renamed from: hitTestChild-YqVAtuI */
    public void mo4747hitTestChildYqVAtuI(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, boolean isTouchEvent, boolean isInLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.m4824hitTestYqVAtuI(hitTestSource, nodeCoordinator.m4820fromParentPositionMKHz9U(pointerPosition), hitTestResult, isTouchEvent, isInLayer);
        }
    }

    public void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getTail().getIsAttached();
    }

    /* JADX INFO: renamed from: isPointerInBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m4825isPointerInBoundsk4lQ0M(long pointerPosition) {
        float fM3236getXimpl = Offset.m3236getXimpl(pointerPosition);
        float fM3237getYimpl = Offset.m3237getYimpl(pointerPosition);
        return fM3236getXimpl >= 0.0f && fM3237getYimpl >= 0.0f && fM3236getXimpl < ((float) getMeasuredWidth()) && fM3237getYimpl < ((float) getMeasuredHeight());
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return (this.layer == null || this.released || !getLayoutNode().isAttached()) ? false : true;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        if (!sourceCoordinates.isAttached()) {
            throw new IllegalStateException(("LayoutCoordinates " + sourceCoordinates + " is not attached!").toString());
        }
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        MutableRect rectCache = getRectCache();
        rectCache.setLeft(0.0f);
        rectCache.setTop(0.0f);
        rectCache.setRight(IntSize.m5844getWidthimpl(sourceCoordinates.mo4638getSizeYbymL2g()));
        rectCache.setBottom(IntSize.m5843getHeightimpl(sourceCoordinates.mo4638getSizeYbymL2g()));
        NodeCoordinator nodeCoordinator = coordinator;
        while (nodeCoordinator != nodeCoordinatorFindCommonAncestor$ui_release) {
            boolean z = clipBounds;
            rectInParent$ui_release$default(nodeCoordinator, rectCache, z, false, 4, null);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            nodeCoordinator = nodeCoordinator.wrappedBy;
            clipBounds = z;
        }
        ancestorToLocal(nodeCoordinatorFindCommonAncestor$ui_release, rectCache, clipBounds);
        return MutableRectKt.toRect(rectCache);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo4639localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        if (sourceCoordinates instanceof LookaheadLayoutCoordinates) {
            return Offset.m3245unaryMinusF1C5BW0(sourceCoordinates.mo4639localPositionOfR5De75A(this, Offset.m3245unaryMinusF1C5BW0(relativeToSource)));
        }
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        while (coordinator != nodeCoordinatorFindCommonAncestor$ui_release) {
            relativeToSource = coordinator.m4829toParentPositionMKHz9U(relativeToSource);
            coordinator = coordinator.wrappedBy;
        }
        return m4809ancestorToLocalR5De75A(nodeCoordinatorFindCommonAncestor$ui_release, relativeToSource);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo4640localToRootMKHz9U(long relativeToLocal) {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui_release();
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != null; nodeCoordinator = nodeCoordinator.wrappedBy) {
            relativeToLocal = nodeCoordinator.m4829toParentPositionMKHz9U(relativeToLocal);
        }
        return relativeToLocal;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo4641localToWindowMKHz9U(long relativeToLocal) {
        return LayoutNodeKt.requireOwner(getLayoutNode()).mo4897calculatePositionInWindowMKHz9U(mo4640localToRootMKHz9U(relativeToLocal));
    }

    public final void onCoordinatesUsed$ui_release() {
        getLayoutNode().getLayoutDelegate().onCoordinatesUsed();
    }

    public void onLayoutModifierNodeChanged() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    public final void onLayoutNodeAttach() {
        updateLayerBlock(this.layerBlock, true);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    public void onMeasureResultChanged(int width, int height) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo4894resizeozmzZPI(IntSizeKt.IntSize(width, height));
        } else {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            if (nodeCoordinator != null) {
                nodeCoordinator.invalidateLayer();
            }
        }
        m4667setMeasuredSizeozmzZPI(IntSizeKt.IntSize(width, height));
        updateLayerParameters(false);
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(4);
        boolean zM4852getIncludeSelfInTraversalH91voCI = NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(iM4843constructorimpl);
        Modifier.Node tail = getTail();
        if (zM4852getIncludeSelfInTraversalH91voCI || (tail = tail.getParent()) != null) {
            for (Modifier.Node nodeHeadNode = headNode(zM4852getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4843constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                if ((nodeHeadNode.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = nodeHeadNode;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof DrawModifierNode) {
                            ((DrawModifierNode) nodePop).onMeasureResultChanged();
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
                    break;
                }
            }
        }
        Owner owner = getLayoutNode().getOwner();
        if (owner != null) {
            owner.onLayoutChange(getLayoutNode());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v6 */
    public final void onMeasured() {
        Modifier.Node parent$ui_release;
        if (m4810hasNodeH91voCI(NodeKind.m4843constructorimpl(128))) {
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                try {
                    int iM4843constructorimpl = NodeKind.m4843constructorimpl(128);
                    boolean zM4852getIncludeSelfInTraversalH91voCI = NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(iM4843constructorimpl);
                    if (!zM4852getIncludeSelfInTraversalH91voCI) {
                        parent$ui_release = getTail().getParent();
                        if (parent$ui_release == null) {
                        }
                    }
                    parent$ui_release = getTail();
                    for (Modifier.Node nodeHeadNode = headNode(zM4852getIncludeSelfInTraversalH91voCI); nodeHeadNode != null; nodeHeadNode = nodeHeadNode.getChild()) {
                        if ((nodeHeadNode.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                            break;
                        }
                        if ((nodeHeadNode.getKindSet() & iM4843constructorimpl) != 0) {
                            Modifier.Node nodePop = nodeHeadNode;
                            MutableVector mutableVector = null;
                            while (nodePop != 0) {
                                if (nodePop instanceof LayoutAwareModifierNode) {
                                    ((LayoutAwareModifierNode) nodePop).mo236onRemeasuredozmzZPI(getMeasuredSize());
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
                        if (nodeHeadNode == parent$ui_release) {
                            break;
                        }
                    }
                } finally {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                }
            } finally {
                snapshotCreateNonObservableSnapshot.dispose();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    public final void onPlaced() {
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(128);
        boolean zM4852getIncludeSelfInTraversalH91voCI = NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(iM4843constructorimpl);
        Modifier.Node tail = getTail();
        if (!zM4852getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM4852getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM4843constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & iM4843constructorimpl) != 0) {
                Modifier.Node nodePop = nodeHeadNode;
                MutableVector mutableVector = null;
                while (nodePop != 0) {
                    if (nodePop instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) nodePop).onPlaced(this);
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

    public final void onRelease() {
        this.released = true;
        this.invalidateParentLayer.invoke();
        if (this.layer != null) {
            updateLayerBlock$default(this, null, false, 2, null);
        }
    }

    public void performDraw(Canvas canvas) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.draw(canvas);
        }
    }

    /* JADX INFO: renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    public final Placeable m4826performingMeasureK40F9xA(long constraints, r7.a<? extends Placeable> block) {
        m4668setMeasurementConstraintsBRTryo0(constraints);
        return (Placeable) block.invoke();
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo4632placeAtf8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
        m4814placeSelff8xVGno(position, zIndex, layerBlock);
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset-f8xVGno, reason: not valid java name */
    public final void m4827placeSelfApparentToRealOffsetf8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
        long jM4664getApparentToRealOffsetnOccac = getApparentToRealOffset();
        m4814placeSelff8xVGno(a0.c.c(IntOffset.m5803getYimpl(position), IntOffset.m5802getXimpl(jM4664getApparentToRealOffsetnOccac) + IntOffset.m5802getXimpl(position), jM4664getApparentToRealOffsetnOccac), zIndex, layerBlock);
    }

    public final void rectInParent$ui_release(MutableRect bounds, boolean clipBounds, boolean clipToMinimumTouchTargetSize) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping) {
                if (clipToMinimumTouchTargetSize) {
                    long jM4822getMinimumTouchTargetSizeNHjbRc = m4822getMinimumTouchTargetSizeNHjbRc();
                    float fM3305getWidthimpl = Size.m3305getWidthimpl(jM4822getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    float fM3302getHeightimpl = Size.m3302getHeightimpl(jM4822getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    bounds.intersect(-fM3305getWidthimpl, -fM3302getHeightimpl, IntSize.m5844getWidthimpl(mo4638getSizeYbymL2g()) + fM3305getWidthimpl, IntSize.m5843getHeightimpl(mo4638getSizeYbymL2g()) + fM3302getHeightimpl);
                } else if (clipBounds) {
                    bounds.intersect(0.0f, 0.0f, IntSize.m5844getWidthimpl(mo4638getSizeYbymL2g()), IntSize.m5843getHeightimpl(mo4638getSizeYbymL2g()));
                }
                if (bounds.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(bounds, false);
        }
        float fM5802getXimpl = IntOffset.m5802getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() + fM5802getXimpl);
        bounds.setRight(bounds.getRight() + fM5802getXimpl);
        float fM5803getYimpl = IntOffset.m5803getYimpl(getPosition());
        bounds.setTop(bounds.getTop() + fM5803getYimpl);
        bounds.setBottom(bounds.getBottom() + fM5803getYimpl);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui_release() {
        mo4632placeAtf8xVGno(getPosition(), this.zIndex, this.layerBlock);
    }

    public abstract void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate);

    public void setMeasureResult$ui_release(MeasureResult measureResult) {
        MeasureResult measureResult2 = this._measureResult;
        if (measureResult != measureResult2) {
            this._measureResult = measureResult;
            if (measureResult2 == null || measureResult.get$w() != measureResult2.get$w() || measureResult.get$h() != measureResult2.get$h()) {
                onMeasureResultChanged(measureResult.get$w(), measureResult.get$h());
            }
            Map<AlignmentLine, Integer> map = this.oldAlignmentLines;
            if (((map == null || map.isEmpty()) && measureResult.getAlignmentLines().isEmpty()) || p.a(measureResult.getAlignmentLines(), this.oldAlignmentLines)) {
                return;
            }
            getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            Map linkedHashMap = this.oldAlignmentLines;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.oldAlignmentLines = linkedHashMap;
            }
            linkedHashMap.clear();
            linkedHashMap.putAll(measureResult.getAlignmentLines());
        }
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    public void m4828setPositiongyyYBs(long j10) {
        this.position = j10;
    }

    public final void setWrapped$ui_release(NodeCoordinator nodeCoordinator) {
        this.wrapped = nodeCoordinator;
    }

    public final void setWrappedBy$ui_release(NodeCoordinator nodeCoordinator) {
        this.wrappedBy = nodeCoordinator;
    }

    public final void setZIndex(float f10) {
        this.zIndex = f10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7 */
    public final boolean shouldSharePointerInputWithSiblings() {
        Modifier.Node nodeHeadNode = headNode(NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(NodeKind.m4843constructorimpl(16)));
        if (nodeHeadNode != null && nodeHeadNode.getIsAttached()) {
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
            if (!nodeHeadNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitLocalDescendants called on an unattached node");
            }
            Modifier.Node node = nodeHeadNode.getNode();
            if ((node.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
                for (Modifier.Node child$ui_release = node.getChild(); child$ui_release != null; child$ui_release = child$ui_release.getChild()) {
                    if ((child$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                        Modifier.Node nodePop = child$ui_release;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof PointerInputModifierNode) {
                                if (((PointerInputModifierNode) nodePop).sharePointerInputWithSiblings()) {
                                    return true;
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
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: toParentPosition-MK-Hz9U, reason: not valid java name */
    public long m4829toParentPositionMKHz9U(long position) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            position = ownedLayer.mo4892mapOffset8S9VItk(position, false);
        }
        return IntOffsetKt.m5816plusNvtHpc(position, getPosition());
    }

    public final Rect touchBoundsInRoot() {
        if (!isAttached()) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        MutableRect rectCache = getRectCache();
        long jM4818calculateMinimumTouchTargetPaddingE7KxVPU = m4818calculateMinimumTouchTargetPaddingE7KxVPU(m4822getMinimumTouchTargetSizeNHjbRc());
        rectCache.setLeft(-Size.m3305getWidthimpl(jM4818calculateMinimumTouchTargetPaddingE7KxVPU));
        rectCache.setTop(-Size.m3302getHeightimpl(jM4818calculateMinimumTouchTargetPaddingE7KxVPU));
        rectCache.setRight(Size.m3305getWidthimpl(jM4818calculateMinimumTouchTargetPaddingE7KxVPU) + getMeasuredWidth());
        rectCache.setBottom(Size.m3302getHeightimpl(jM4818calculateMinimumTouchTargetPaddingE7KxVPU) + getMeasuredHeight());
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != layoutCoordinatesFindRootCoordinates; nodeCoordinator = nodeCoordinator.wrappedBy) {
            nodeCoordinator.rectInParent$ui_release(rectCache, false, true);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
        }
        return MutableRectKt.toRect(rectCache);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo4642transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator nodeCoordinatorFindCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        Matrix.m3693resetimpl(matrix);
        coordinator.m4817transformToAncestorEL8BTi8(nodeCoordinatorFindCommonAncestor$ui_release, matrix);
        m4816transformFromAncestorEL8BTi8(nodeCoordinatorFindCommonAncestor$ui_release, matrix);
    }

    public final void updateLayerBlock(l<? super GraphicsLayerScope, t0> layerBlock, boolean forceUpdateLayerParameters) {
        Owner owner;
        LayoutNode layoutNode = getLayoutNode();
        boolean z = (!forceUpdateLayerParameters && this.layerBlock == layerBlock && p.a(this.layerDensity, layoutNode.getDensity()) && this.layerLayoutDirection == layoutNode.getLayoutDirection()) ? false : true;
        this.layerBlock = layerBlock;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        if (!layoutNode.isAttached() || layerBlock == null) {
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.destroy();
                layoutNode.setInnerLayerCoordinatorIsDirty$ui_release(true);
                this.invalidateParentLayer.invoke();
                if (isAttached() && (owner = layoutNode.getOwner()) != null) {
                    owner.onLayoutChange(layoutNode);
                }
            }
            this.layer = null;
            this.lastLayerDrawingWasSkipped = false;
            return;
        }
        if (this.layer != null) {
            if (z) {
                updateLayerParameters$default(this, false, 1, null);
                return;
            }
            return;
        }
        OwnedLayer ownedLayerCreateLayer = LayoutNodeKt.requireOwner(layoutNode).createLayer(this.drawBlock, this.invalidateParentLayer);
        ownedLayerCreateLayer.mo4894resizeozmzZPI(getMeasuredSize());
        ownedLayerCreateLayer.mo4893movegyyYBs(getPosition());
        this.layer = ownedLayerCreateLayer;
        updateLayerParameters$default(this, false, 1, null);
        layoutNode.setInnerLayerCoordinatorIsDirty$ui_release(true);
        this.invalidateParentLayer.invoke();
    }

    public final void visitNodes(int mask, boolean includeTail, l<? super Modifier.Node, t0> block) {
        Modifier.Node tail = getTail();
        if (!includeTail && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(includeTail); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & mask) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & mask) != 0) {
                block.invoke(nodeHeadNode);
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: visitNodes-aLcG6gQ, reason: not valid java name */
    public final <T> void m4830visitNodesaLcG6gQ(int type, l<? super T, t0> block) {
        boolean zM4852getIncludeSelfInTraversalH91voCI = NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node tail = getTail();
        if (!zM4852getIncludeSelfInTraversalH91voCI && (tail = tail.getParent()) == null) {
            return;
        }
        for (Modifier.Node nodeHeadNode = headNode(zM4852getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & type) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
            if ((nodeHeadNode.getKindSet() & type) != 0) {
                p.h();
                throw null;
            }
            if (nodeHeadNode == tail) {
                return;
            }
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo4643windowToLocalMKHz9U(long relativeToWindow) {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        return mo4639localPositionOfR5De75A(layoutCoordinatesFindRootCoordinates, Offset.m3240minusMKHz9U(LayoutNodeKt.requireOwner(getLayoutNode()).mo4896calculateLocalPositionMKHz9U(relativeToWindow), LayoutCoordinatesKt.positionInRoot(layoutCoordinatesFindRootCoordinates)));
    }

    public final void withPositionTranslation(Canvas canvas, l<? super Canvas, t0> block) {
        float fM5802getXimpl = IntOffset.m5802getXimpl(getPosition());
        float fM5803getYimpl = IntOffset.m5803getYimpl(getPosition());
        canvas.translate(fM5802getXimpl, fM5803getYimpl);
        block.invoke(canvas);
        canvas.translate(-fM5802getXimpl, -fM5803getYimpl);
    }

    /* JADX INFO: renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m4831withinLayerBoundsk4lQ0M(long pointerPosition) {
        if (!OffsetKt.m3253isFinitek4lQ0M(pointerPosition)) {
            return false;
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer == null || !this.isClipping || ownedLayer.mo4891isInLayerk4lQ0M(pointerPosition);
    }
}
