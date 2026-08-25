package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006Jq\u0010\u001c\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJq\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 JY\u0010%\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$JY\u0010%\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'JO\u0010,\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010+Jm\u0010,\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010.\u001a\u00020-2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u00020-2\b\b\u0002\u00102\u001a\u00020/2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b3\u00104Jw\u0010,\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010.\u001a\u00020-2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u00020-2\b\b\u0002\u00102\u001a\u00020/2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u00106\u001a\u000205H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108Jk\u0010B\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\b\b\u0002\u0010;\u001a\u00020\t2\b\b\u0002\u0010=\u001a\u00020<2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\b\b\u0003\u0010\u0012\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b@\u0010AJk\u0010B\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\b\b\u0002\u0010;\u001a\u00020\t2\b\b\u0002\u0010=\u001a\u00020<2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\b\b\u0003\u0010\u0012\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010DJY\u0010G\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bE\u0010FJY\u0010G\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bH\u0010IJM\u0010N\u001a\u00020\u00192\u0006\u0010K\u001a\u00020J2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bL\u0010MJM\u0010N\u001a\u00020\u00192\u0006\u0010K\u001a\u00020J2\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010PJq\u0010W\u001a\u00020\u00192\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u000e0Q2\u0006\u0010T\u001a\u00020S2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010;\u001a\u00020\t2\b\b\u0002\u0010=\u001a\u00020<2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\b\b\u0003\u0010\u0012\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bU\u0010VJq\u0010W\u001a\u00020\u00192\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u000e0Q2\u0006\u0010T\u001a\u00020S2\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010;\u001a\u00020\t2\b\b\u0002\u0010=\u001a\u00020<2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\b\b\u0003\u0010\u0012\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bX\u0010YJY\u0010[\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bZ\u0010FJY\u0010[\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b\\\u0010IJc\u0010a\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010^\u001a\u00020]2\b\b\u0003\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b_\u0010`Jc\u0010a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010^\u001a\u00020]2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0003\u0010\u0012\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bb\u0010cJ\u0017\u0010h\u001a\u00020e*\u00020dH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bf\u0010gJ\u0017\u0010h\u001a\u00020e*\u00020iH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bj\u0010kJ\u0017\u0010n\u001a\u00020d*\u00020iH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bl\u0010mJ\u001a\u0010n\u001a\u00020d*\u00020\tH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010pJ\u001a\u0010n\u001a\u00020d*\u00020eH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010qJ\u0017\u0010u\u001a\u00020r*\u00020\u0010H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bs\u0010tJ\u0017\u0010w\u001a\u00020\t*\u00020dH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bv\u0010pJ\u0017\u0010w\u001a\u00020\t*\u00020iH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bx\u0010mJ\u0014\u0010{\u001a\u00020z*\u00020yH\u0097\u0001¢\u0006\u0004\b{\u0010|J\u0017\u0010~\u001a\u00020\u0010*\u00020rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b}\u0010tJ\u0019\u0010\u0081\u0001\u001a\u00020i*\u00020dH\u0097\u0001ø\u0001\u0000¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u001d\u0010\u0081\u0001\u001a\u00020i*\u00020\tH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0082\u0001\u0010\u0080\u0001J\u001d\u0010\u0081\u0001\u001a\u00020i*\u00020eH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J\u0012\u0010\u0084\u0001\u001a\u00020\u0019H\u0016¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001f\u0010\u0089\u0001\u001a\u00020\u0019*\u00030\u0086\u00012\b\u0010\u0088\u0001\u001a\u00030\u0087\u0001¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J;\u0010\u0091\u0001\u001a\u00020\u00192\b\u0010\u0088\u0001\u001a\u00030\u0087\u00012\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u008c\u0001\u001a\u00030\u008b\u00012\b\u0010\u008e\u0001\u001a\u00030\u008d\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J;\u0010\u0094\u0001\u001a\u00020\u00192\b\u0010\u0088\u0001\u001a\u00030\u0087\u00012\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u008c\u0001\u001a\u00030\u008b\u00012\b\u0010\u008e\u0001\u001a\u00030\u0086\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0015\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0004\u0010\u0095\u0001R\u001c\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u0086\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u0096\u0001R\u001c\u0010\"\u001a\u00020\u000e8VX\u0096\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0017\u0010\u009b\u0001\u001a\u00020\t8\u0016X\u0097\u0005¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0018\u0010\u009f\u0001\u001a\u00030\u009c\u00018\u0016X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0017\u0010¡\u0001\u001a\u00020\t8\u0016X\u0097\u0005¢\u0006\b\u001a\u0006\b \u0001\u0010\u009a\u0001R\u0018\u0010¥\u0001\u001a\u00030¢\u00018\u0016X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u001c\u0010\u0011\u001a\u00020\u00108VX\u0096\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b¦\u0001\u0010\u0098\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006§\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeDrawScope;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "canvasDrawScope", "<init>", "(Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "Landroidx/compose/ui/graphics/Brush;", "brush", "", "startAngle", "sweepAngle", "", "useCenter", "Landroidx/compose/ui/geometry/Offset;", "topLeft", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "alpha", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "style", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "Lx6/t0;", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawArc", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "radius", TtmlNode.CENTER, "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/ImageBitmap;", "image", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "Landroidx/compose/ui/unit/IntOffset;", "srcOffset", "Landroidx/compose/ui/unit/IntSize;", "srcSize", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", TtmlNode.START, TtmlNode.END, "strokeWidth", "Landroidx/compose/ui/graphics/StrokeCap;", "cap", "Landroidx/compose/ui/graphics/PathEffect;", "pathEffect", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine", "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval", "drawOval-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/Path;", "path", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "", "points", "Landroidx/compose/ui/graphics/PointMode;", "pointMode", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect-AsUm42w", "drawRect", "drawRect-n-J9OG0", "Landroidx/compose/ui/geometry/CornerRadius;", "cornerRadius", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/unit/Dp;", "", "roundToPx-0680j_4", "(F)I", "roundToPx", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp-GaN1DYA", "(J)F", "toDp", "toDp-u2uoSUM", "(F)F", "(I)F", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toDpSize", "toPx-0680j_4", "toPx", "toPx--R2X_6o", "Landroidx/compose/ui/unit/DpRect;", "Landroidx/compose/ui/geometry/Rect;", "toRect", "(Landroidx/compose/ui/unit/DpRect;)Landroidx/compose/ui/geometry/Rect;", "toSize-XkaWNTQ", "toSize", "toSp-0xMU5do", "(F)J", "toSp", "toSp-kPz2Gy4", "(I)J", "drawContent", "()V", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "performDraw", "(Landroidx/compose/ui/node/DrawModifierNode;Landroidx/compose/ui/graphics/Canvas;)V", "Landroidx/compose/ui/node/NodeCoordinator;", "coordinator", "Landroidx/compose/ui/Modifier$Node;", "drawNode", "draw-x_KDEd0$ui_release", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/Modifier$Node;)V", "draw", "drawDirect-x_KDEd0$ui_release", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/node/DrawModifierNode;)V", "drawDirect", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "Landroidx/compose/ui/node/DrawModifierNode;", "getCenter-F1C5BW0", "()J", "getDensity", "()F", "density", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "drawContext", "getFontScale", "fontScale", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getSize-NH-jbRc", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayoutNodeDrawScope implements DrawScope, ContentDrawScope {
    public static final int $stable = 0;
    private final CanvasDrawScope canvasDrawScope;
    private DrawModifierNode drawNode;

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutNodeDrawScope() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX INFO: renamed from: draw-x_KDEd0$ui_release, reason: not valid java name */
    public final void m4764drawx_KDEd0$ui_release(Canvas canvas, long size, NodeCoordinator coordinator, Modifier.Node drawNode) {
        Canvas canvas2;
        long j10;
        NodeCoordinator nodeCoordinator;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(4);
        MutableVector mutableVector = null;
        while (drawNode != null) {
            if (drawNode instanceof DrawModifierNode) {
                canvas2 = canvas;
                j10 = size;
                nodeCoordinator = coordinator;
                m4765drawDirectx_KDEd0$ui_release(canvas2, j10, nodeCoordinator, (DrawModifierNode) drawNode);
            } else {
                canvas2 = canvas;
                j10 = size;
                nodeCoordinator = coordinator;
                if ((drawNode.getKindSet() & iM4843constructorimpl) != 0 && (drawNode instanceof DelegatingNode)) {
                    int i10 = 0;
                    for (Modifier.Node delegate = ((DelegatingNode) drawNode).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                            i10++;
                            if (i10 == 1) {
                                drawNode = delegate;
                            } else {
                                if (mutableVector == null) {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (drawNode != null) {
                                    mutableVector.add(drawNode);
                                    drawNode = null;
                                }
                                mutableVector.add(delegate);
                            }
                        }
                    }
                    if (i10 == 1) {
                    }
                }
                canvas = canvas2;
                size = j10;
                coordinator = nodeCoordinator;
            }
            drawNode = DelegatableNodeKt.pop(mutableVector);
            canvas = canvas2;
            size = j10;
            coordinator = nodeCoordinator;
        }
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-illE91I */
    public void mo3896drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3896drawArcillE91I(brush, startAngle, sweepAngle, useCenter, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    public void mo3897drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3897drawArcyD3GUKo(color, startAngle, sweepAngle, useCenter, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-V9BoPsw */
    public void mo3898drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3898drawCircleV9BoPsw(brush, radius, center, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    public void mo3899drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3899drawCircleVaOC9Bg(color, radius, center, alpha, style, colorFilter, blendMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    @Override // androidx.compose.ui.graphics.drawscope.ContentDrawScope
    public void drawContent() {
        Canvas canvas = getDrawContext().getCanvas();
        DrawModifierNode drawModifierNode = this.drawNode;
        Modifier.Node nodeNextDrawNode = LayoutNodeDrawScopeKt.nextDrawNode(drawModifierNode);
        if (nodeNextDrawNode == 0) {
            NodeCoordinator nodeCoordinatorM4726requireCoordinator64DMado = DelegatableNodeKt.m4726requireCoordinator64DMado(drawModifierNode, NodeKind.m4843constructorimpl(4));
            if (nodeCoordinatorM4726requireCoordinator64DMado.getTail() == drawModifierNode.getNode()) {
                nodeCoordinatorM4726requireCoordinator64DMado = nodeCoordinatorM4726requireCoordinator64DMado.getWrapped();
            }
            nodeCoordinatorM4726requireCoordinator64DMado.performDraw(canvas);
            return;
        }
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(4);
        MutableVector mutableVector = null;
        while (nodeNextDrawNode != 0) {
            if (nodeNextDrawNode instanceof DrawModifierNode) {
                performDraw((DrawModifierNode) nodeNextDrawNode, canvas);
            } else if ((nodeNextDrawNode.getKindSet() & iM4843constructorimpl) != 0 && (nodeNextDrawNode instanceof DelegatingNode)) {
                Modifier.Node delegate = ((DelegatingNode) nodeNextDrawNode).getDelegate();
                int i10 = 0;
                nodeNextDrawNode = nodeNextDrawNode;
                while (delegate != null) {
                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                        i10++;
                        if (i10 == 1) {
                            nodeNextDrawNode = delegate;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (nodeNextDrawNode != 0) {
                                mutableVector.add(nodeNextDrawNode);
                                nodeNextDrawNode = 0;
                            }
                            mutableVector.add(delegate);
                        }
                    }
                    delegate = delegate.getChild();
                    nodeNextDrawNode = nodeNextDrawNode;
                }
                if (i10 == 1) {
                }
            }
            nodeNextDrawNode = DelegatableNodeKt.pop(mutableVector);
        }
    }

    /* JADX INFO: renamed from: drawDirect-x_KDEd0$ui_release, reason: not valid java name */
    public final void m4765drawDirectx_KDEd0$ui_release(Canvas canvas, long size, NodeCoordinator coordinator, DrawModifierNode drawNode) {
        DrawModifierNode drawModifierNode = this.drawNode;
        this.drawNode = drawNode;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        LayoutDirection layoutDirection = coordinator.getLayoutDirection();
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope.getDrawParams();
        Density density = drawParams.getDensity();
        LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
        Canvas canvas2 = drawParams.getCanvas();
        long size2 = drawParams.getSize();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope.getDrawParams();
        drawParams2.setDensity(coordinator);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas);
        drawParams2.m3921setSizeuvyYCjk(size);
        canvas.save();
        drawNode.draw(this);
        canvas.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope.getDrawParams();
        drawParams3.setDensity(density);
        drawParams3.setLayoutDirection(layoutDirection2);
        drawParams3.setCanvas(canvas2);
        drawParams3.m3921setSizeuvyYCjk(size2);
        this.drawNode = drawModifierNode;
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    @x6.e
    /* JADX INFO: renamed from: drawImage-9jGpkUE */
    public /* synthetic */ void mo3900drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3900drawImage9jGpkUE(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    public void mo3901drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality) {
        this.canvasDrawScope.mo3901drawImageAZ2fEMs(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, filterQuality);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    public void mo3902drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3902drawImagegbVJVH8(image, topLeft, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    public void mo3903drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3903drawLine1RTmtNc(brush, start, end, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    public void mo3904drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3904drawLineNGM6Ib0(color, start, end, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawOval-AsUm42w */
    public void mo3905drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3905drawOvalAsUm42w(brush, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawOval-n-J9OG0 */
    public void mo3906drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3906drawOvalnJ9OG0(color, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    public void mo3907drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3907drawPathGBMwjPU(path, brush, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-LG529CI */
    public void mo3908drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3908drawPathLG529CI(path, color, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPoints-F8ZwMP8 */
    public void mo3909drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3909drawPointsF8ZwMP8(points, pointMode, color, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPoints-Gsft0Ws */
    public void mo3910drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3910drawPointsGsft0Ws(points, pointMode, brush, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-AsUm42w */
    public void mo3911drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3911drawRectAsUm42w(brush, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    public void mo3912drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3912drawRectnJ9OG0(color, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    public void mo3913drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3913drawRoundRectZuiqVtQ(brush, topLeft, size, cornerRadius, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    public void mo3914drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo3914drawRoundRectuAw5IA(color, topLeft, size, cornerRadius, style, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getCenter-F1C5BW0 */
    public long mo3915getCenterF1C5BW0() {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.graphics.drawscope.c.b(canvasDrawScope);
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.canvasDrawScope.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.canvasDrawScope.getDrawContext();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.canvasDrawScope.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.canvasDrawScope.getLayoutDirection();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getSize-NH-jbRc */
    public long mo3916getSizeNHjbRc() {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.graphics.drawscope.c.c(canvasDrawScope);
    }

    public final void performDraw(DrawModifierNode drawModifierNode, Canvas canvas) {
        NodeCoordinator nodeCoordinatorM4726requireCoordinator64DMado = DelegatableNodeKt.m4726requireCoordinator64DMado(drawModifierNode, NodeKind.m4843constructorimpl(4));
        nodeCoordinatorM4726requireCoordinator64DMado.getLayoutNode().getMDrawScope$ui_release().m4765drawDirectx_KDEd0$ui_release(canvas, IntSizeKt.m5854toSizeozmzZPI(nodeCoordinatorM4726requireCoordinator64DMado.mo4638getSizeYbymL2g()), nodeCoordinatorM4726requireCoordinator64DMado, drawModifierNode);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public int mo278roundToPxR2X_6o(long j10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.a(canvasDrawScope, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public int mo279roundToPx0680j_4(float f10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.b(canvasDrawScope, f10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo280toDpGaN1DYA(long j10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.b.a(canvasDrawScope, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo281toDpu2uoSUM(float f10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.c(canvasDrawScope, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public long mo283toDpSizekrfVVM(long j10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.e(canvasDrawScope, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public float mo284toPxR2X_6o(long j10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.f(canvasDrawScope, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public float mo285toPx0680j_4(float f10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.g(canvasDrawScope, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.h(canvasDrawScope, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public long mo286toSizeXkaWNTQ(long j10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.i(canvasDrawScope, j10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo287toSp0xMU5do(float f10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.b.b(canvasDrawScope, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo288toSpkPz2Gy4(float f10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.j(canvasDrawScope, f10);
    }

    public LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope) {
        this.canvasDrawScope = canvasDrawScope;
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo282toDpu2uoSUM(int i10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.d(canvasDrawScope, i10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo289toSpkPz2Gy4(int i10) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.getClass();
        return androidx.compose.ui.unit.a.k(canvasDrawScope, i10);
    }

    public /* synthetic */ LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }
}
