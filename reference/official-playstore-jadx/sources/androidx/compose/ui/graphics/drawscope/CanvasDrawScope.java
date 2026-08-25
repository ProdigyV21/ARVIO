package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0002\u0098\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J`\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J`\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJN\u0010#\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b!\u0010\"JN\u0010#\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b$\u0010%JF\u0010*\u001a\u00020\u00142\u0006\u0010'\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b(\u0010)J^\u0010*\u001a\u00020\u00142\u0006\u0010'\u001a\u00020&2\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020-2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0017ø\u0001\u0000¢\u0006\u0004\b1\u00102Jf\u0010*\u001a\u00020\u00142\u0006\u0010'\u001a\u00020&2\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020-2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u00104\u001a\u000203H\u0016ø\u0001\u0000¢\u0006\u0004\b5\u00106JV\u0010;\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u00108\u001a\u0002072\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b9\u0010:JV\u0010;\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u00108\u001a\u0002072\u0006\u0010 \u001a\u00020\u001f2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b<\u0010=JN\u0010B\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b@\u0010AJN\u0010B\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bC\u0010DJN\u0010F\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bE\u0010\"JN\u0010F\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bG\u0010%Jf\u0010N\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\t2\u0006\u0010K\u001a\u00020J2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bL\u0010MJf\u0010N\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\t2\u0006\u0010K\u001a\u00020J2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bO\u0010PJF\u0010U\u001a\u00020\u00142\u0006\u0010R\u001a\u00020Q2\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bS\u0010TJF\u0010U\u001a\u00020\u00142\u0006\u0010R\u001a\u00020Q2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u000f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\bV\u0010WJf\u0010^\u001a\u00020\u00142\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00060X2\u0006\u0010[\u001a\u00020Z2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\\\u0010]Jf\u0010^\u001a\u00020\u00142\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00060X2\u0006\u0010[\u001a\u00020Z2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b_\u0010`JL\u0010l\u001a\u00020\u00142\u0006\u0010b\u001a\u00020a2\u0006\u0010d\u001a\u00020c2\u0006\u0010f\u001a\u00020e2\u0006\u0010\u001e\u001a\u00020\u001d2\u0017\u0010i\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140g¢\u0006\u0002\bhH\u0086\bø\u0001\u0000¢\u0006\u0004\bj\u0010kJ\u000f\u0010n\u001a\u00020mH\u0002¢\u0006\u0004\bn\u0010oJ\u000f\u0010p\u001a\u00020mH\u0002¢\u0006\u0004\bp\u0010oJ\u0017\u0010r\u001a\u00020m2\u0006\u0010q\u001a\u00020\u001fH\u0002¢\u0006\u0004\br\u0010sJJ\u0010v\u001a\u00020m2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020\u001f2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0004\bt\u0010uJH\u0010v\u001a\u00020m2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u001f2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0004\bw\u0010xJj\u0010~\u001a\u00020m2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\t2\u0006\u0010y\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010{\u001a\u00020z2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0004\b|\u0010}Jm\u0010~\u001a\u00020m2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010y\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010{\u001a\u00020z2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0005\b\u007f\u0010\u0080\u0001J!\u0010\u0083\u0001\u001a\u00020\u0018*\u00020\u00182\u0006\u0010\u000f\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001R'\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0000X\u0081\u0004¢\u0006\u0017\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u0012\u0005\b\u0089\u0001\u0010\u0003\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010\u008b\u0001\u001a\u00030\u008a\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001b\u0010\u008f\u0001\u001a\u0004\u0018\u00010m8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001b\u0010\u0091\u0001\u001a\u0004\u0018\u00010m8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u0090\u0001R\u0016\u0010d\u001a\u00020c8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010b\u001a\u00020\t8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0017\u0010\u0097\u0001\u001a\u00020\t8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0095\u0001\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0099\u0001"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "<init>", "()V", "Landroidx/compose/ui/graphics/Brush;", "brush", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.START, TtmlNode.END, "", "strokeWidth", "Landroidx/compose/ui/graphics/StrokeCap;", "cap", "Landroidx/compose/ui/graphics/PathEffect;", "pathEffect", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "Lx6/t0;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "topLeft", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "style", "drawRect-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "drawRect-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/ImageBitmap;", "image", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "Landroidx/compose/ui/unit/IntOffset;", "srcOffset", "Landroidx/compose/ui/unit/IntSize;", "srcSize", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", "Landroidx/compose/ui/geometry/CornerRadius;", "cornerRadius", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "radius", TtmlNode.CENTER, "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval-AsUm42w", "drawOval", "drawOval-n-J9OG0", "startAngle", "sweepAngle", "", "useCenter", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawArc", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/Path;", "path", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "", "points", "Landroidx/compose/ui/graphics/PointMode;", "pointMode", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "Lkotlin/Function1;", "Lx6/n;", "block", "draw-yzxVdVo", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/graphics/Canvas;JLr7/l;)V", "draw", "Landroidx/compose/ui/graphics/Paint;", "obtainFillPaint", "()Landroidx/compose/ui/graphics/Paint;", "obtainStrokePaint", "drawStyle", "selectPaint", "(Landroidx/compose/ui/graphics/drawscope/DrawStyle;)Landroidx/compose/ui/graphics/Paint;", "configurePaint-swdJneE", "(Landroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;II)Landroidx/compose/ui/graphics/Paint;", "configurePaint", "configurePaint-2qPWKa0", "(JLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;II)Landroidx/compose/ui/graphics/Paint;", "miter", "Landroidx/compose/ui/graphics/StrokeJoin;", "join", "configureStrokePaint-Q_0CZUI", "(JFFIILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;II)Landroidx/compose/ui/graphics/Paint;", "configureStrokePaint", "configureStrokePaint-ho4zsrM", "(Landroidx/compose/ui/graphics/Brush;FFIILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;II)Landroidx/compose/ui/graphics/Paint;", "modulate-5vOe2sY", "(JF)J", "modulate", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope$DrawParams;", "drawParams", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope$DrawParams;", "getDrawParams", "()Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope$DrawParams;", "getDrawParams$annotations", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "drawContext", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "fillPaint", "Landroidx/compose/ui/graphics/Paint;", "strokePaint", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "getDensity", "()F", "getFontScale", "fontScale", "DrawParams", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CanvasDrawScope implements DrawScope {
    private Paint fillPaint;
    private Paint strokePaint;
    private final DrawParams drawParams = new DrawParams(null, null, null, 0, 15, null);
    private final DrawContext drawContext = new DrawContext() { // from class: androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1
        private final DrawTransform transform = CanvasDrawScopeKt.asDrawTransform(this);

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public Canvas getCanvas() {
            return this.this$0.getDrawParams().getCanvas();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public Density getDensity() {
            return this.this$0.getDrawParams().getDensity();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public LayoutDirection getLayoutDirection() {
            return this.this$0.getDrawParams().getLayoutDirection();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public long mo3922getSizeNHjbRc() {
            return this.this$0.getDrawParams().m3920getSizeNHjbRc();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public DrawTransform getTransform() {
            return this.transform;
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setCanvas(Canvas canvas) {
            this.this$0.getDrawParams().setCanvas(canvas);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setDensity(Density density) {
            this.this$0.getDrawParams().setDensity(density);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setLayoutDirection(LayoutDirection layoutDirection) {
            this.this$0.getDrawParams().setLayoutDirection(layoutDirection);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
        public void mo3923setSizeuvyYCjk(long j10) {
            this.this$0.getDrawParams().m3921setSizeuvyYCjk(j10);
        }
    };

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0081\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\bHÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010%R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010&\u001a\u0004\b'\u0010\u000f\"\u0004\b(\u0010)R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010*\u001a\u0004\b+\u0010\u0011\"\u0004\b,\u0010-R(\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0012\n\u0004\b\t\u0010.\u001a\u0004\b/\u0010\u0013\"\u0004\b0\u00101\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope$DrawParams;", "", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "<init>", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/graphics/Canvas;JLkotlin/jvm/internal/h;)V", "component1", "()Landroidx/compose/ui/unit/Density;", "component2", "()Landroidx/compose/ui/unit/LayoutDirection;", "component3", "()Landroidx/compose/ui/graphics/Canvas;", "component4-NH-jbRc", "()J", "component4", "copy-Ug5Nnss", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/graphics/Canvas;J)Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope$DrawParams;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/unit/Density;", "getDensity", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/graphics/Canvas;", "getCanvas", "setCanvas", "(Landroidx/compose/ui/graphics/Canvas;)V", "J", "getSize-NH-jbRc", "setSize-uvyYCjk", "(J)V", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class DrawParams {
        private Canvas canvas;
        private Density density;
        private LayoutDirection layoutDirection;
        private long size;

        public /* synthetic */ DrawParams(Density density, LayoutDirection layoutDirection, Canvas canvas, long j10, h hVar) {
            this(density, layoutDirection, canvas, j10);
        }

        /* JADX INFO: renamed from: copy-Ug5Nnss$default, reason: not valid java name */
        public static /* synthetic */ DrawParams m3917copyUg5Nnss$default(DrawParams drawParams, Density density, LayoutDirection layoutDirection, Canvas canvas, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                density = drawParams.density;
            }
            if ((i10 & 2) != 0) {
                layoutDirection = drawParams.layoutDirection;
            }
            if ((i10 & 4) != 0) {
                canvas = drawParams.canvas;
            }
            if ((i10 & 8) != 0) {
                j10 = drawParams.size;
            }
            Canvas canvas2 = canvas;
            return drawParams.m3919copyUg5Nnss(density, layoutDirection, canvas2, j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Density getDensity() {
            return this.density;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Canvas getCanvas() {
            return this.canvas;
        }

        /* JADX INFO: renamed from: component4-NH-jbRc, reason: not valid java name and from getter */
        public final long getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: copy-Ug5Nnss, reason: not valid java name */
        public final DrawParams m3919copyUg5Nnss(Density density, LayoutDirection layoutDirection, Canvas canvas, long size) {
            return new DrawParams(density, layoutDirection, canvas, size, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DrawParams)) {
                return false;
            }
            DrawParams drawParams = (DrawParams) other;
            return p.a(this.density, drawParams.density) && this.layoutDirection == drawParams.layoutDirection && p.a(this.canvas, drawParams.canvas) && Size.m3301equalsimpl0(this.size, drawParams.size);
        }

        public final Canvas getCanvas() {
            return this.canvas;
        }

        public final Density getDensity() {
            return this.density;
        }

        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public final long m3920getSizeNHjbRc() {
            return this.size;
        }

        public int hashCode() {
            return Size.m3306hashCodeimpl(this.size) + ((this.canvas.hashCode() + ((this.layoutDirection.hashCode() + (this.density.hashCode() * 31)) * 31)) * 31);
        }

        public final void setCanvas(Canvas canvas) {
            this.canvas = canvas;
        }

        public final void setDensity(Density density) {
            this.density = density;
        }

        public final void setLayoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
        public final void m3921setSizeuvyYCjk(long j10) {
            this.size = j10;
        }

        public String toString() {
            return "DrawParams(density=" + this.density + ", layoutDirection=" + this.layoutDirection + ", canvas=" + this.canvas + ", size=" + ((Object) Size.m3309toStringimpl(this.size)) + ')';
        }

        private DrawParams(Density density, LayoutDirection layoutDirection, Canvas canvas, long j10) {
            this.density = density;
            this.layoutDirection = layoutDirection;
            this.canvas = canvas;
            this.size = j10;
        }

        public /* synthetic */ DrawParams(Density density, LayoutDirection layoutDirection, Canvas canvas, long j10, int i10, h hVar) {
            this((i10 & 1) != 0 ? DrawContextKt.getDefaultDensity() : density, (i10 & 2) != 0 ? LayoutDirection.Ltr : layoutDirection, (i10 & 4) != 0 ? new EmptyCanvas() : canvas, (i10 & 8) != 0 ? Size.INSTANCE.m3314getZeroNHjbRc() : j10, null);
        }
    }

    /* JADX INFO: renamed from: configurePaint-2qPWKa0, reason: not valid java name */
    private final Paint m3886configurePaint2qPWKa0(long color, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode, int filterQuality) {
        Paint paintSelectPaint = selectPaint(style);
        long jM3894modulate5vOe2sY = m3894modulate5vOe2sY(color, alpha);
        if (!Color.m3473equalsimpl0(paintSelectPaint.mo3351getColor0d7_KjU(), jM3894modulate5vOe2sY)) {
            paintSelectPaint.mo3357setColor8_81llA(jM3894modulate5vOe2sY);
        }
        if (paintSelectPaint.getInternalShader() != null) {
            paintSelectPaint.setShader(null);
        }
        if (!p.a(paintSelectPaint.getInternalColorFilter(), colorFilter)) {
            paintSelectPaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m3389equalsimpl0(paintSelectPaint.get_blendMode(), blendMode)) {
            paintSelectPaint.mo3356setBlendModes9anfk8(blendMode);
        }
        if (!FilterQuality.m3567equalsimpl0(paintSelectPaint.mo3352getFilterQualityfv9h1I(), filterQuality)) {
            paintSelectPaint.mo3358setFilterQualityvDHp3xo(filterQuality);
        }
        return paintSelectPaint;
    }

    /* JADX INFO: renamed from: configurePaint-2qPWKa0$default, reason: not valid java name */
    public static /* synthetic */ Paint m3887configurePaint2qPWKa0$default(CanvasDrawScope canvasDrawScope, long j10, DrawStyle drawStyle, float f10, ColorFilter colorFilter, int i10, int i11, int i12, Object obj) {
        return canvasDrawScope.m3886configurePaint2qPWKa0(j10, drawStyle, f10, colorFilter, i10, (i12 & 32) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i11);
    }

    /* JADX INFO: renamed from: configurePaint-swdJneE, reason: not valid java name */
    private final Paint m3888configurePaintswdJneE(Brush brush, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode, int filterQuality) {
        Paint paintSelectPaint = selectPaint(style);
        if (brush != null) {
            brush.mo3425applyToPq9zytI(c.c(this), paintSelectPaint, alpha);
        } else {
            if (paintSelectPaint.getInternalShader() != null) {
                paintSelectPaint.setShader(null);
            }
            long jMo3351getColor0d7_KjU = paintSelectPaint.mo3351getColor0d7_KjU();
            Color.Companion companion = Color.INSTANCE;
            if (!Color.m3473equalsimpl0(jMo3351getColor0d7_KjU, companion.m3498getBlack0d7_KjU())) {
                paintSelectPaint.mo3357setColor8_81llA(companion.m3498getBlack0d7_KjU());
            }
            if (paintSelectPaint.getAlpha() != alpha) {
                paintSelectPaint.setAlpha(alpha);
            }
        }
        if (!p.a(paintSelectPaint.getInternalColorFilter(), colorFilter)) {
            paintSelectPaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m3389equalsimpl0(paintSelectPaint.get_blendMode(), blendMode)) {
            paintSelectPaint.mo3356setBlendModes9anfk8(blendMode);
        }
        if (!FilterQuality.m3567equalsimpl0(paintSelectPaint.mo3352getFilterQualityfv9h1I(), filterQuality)) {
            paintSelectPaint.mo3358setFilterQualityvDHp3xo(filterQuality);
        }
        return paintSelectPaint;
    }

    /* JADX INFO: renamed from: configurePaint-swdJneE$default, reason: not valid java name */
    public static /* synthetic */ Paint m3889configurePaintswdJneE$default(CanvasDrawScope canvasDrawScope, Brush brush, DrawStyle drawStyle, float f10, ColorFilter colorFilter, int i10, int i11, int i12, Object obj) {
        if ((i12 & 32) != 0) {
            i11 = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
        }
        return canvasDrawScope.m3888configurePaintswdJneE(brush, drawStyle, f10, colorFilter, i10, i11);
    }

    /* JADX INFO: renamed from: configureStrokePaint-Q_0CZUI, reason: not valid java name */
    private final Paint m3890configureStrokePaintQ_0CZUI(long color, float strokeWidth, float miter, int cap, int join, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode, int filterQuality) {
        Paint paintObtainStrokePaint = obtainStrokePaint();
        long jM3894modulate5vOe2sY = m3894modulate5vOe2sY(color, alpha);
        if (!Color.m3473equalsimpl0(paintObtainStrokePaint.mo3351getColor0d7_KjU(), jM3894modulate5vOe2sY)) {
            paintObtainStrokePaint.mo3357setColor8_81llA(jM3894modulate5vOe2sY);
        }
        if (paintObtainStrokePaint.getInternalShader() != null) {
            paintObtainStrokePaint.setShader(null);
        }
        if (!p.a(paintObtainStrokePaint.getInternalColorFilter(), colorFilter)) {
            paintObtainStrokePaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m3389equalsimpl0(paintObtainStrokePaint.get_blendMode(), blendMode)) {
            paintObtainStrokePaint.mo3356setBlendModes9anfk8(blendMode);
        }
        if (paintObtainStrokePaint.getStrokeWidth() != strokeWidth) {
            paintObtainStrokePaint.setStrokeWidth(strokeWidth);
        }
        if (paintObtainStrokePaint.getStrokeMiterLimit() != miter) {
            paintObtainStrokePaint.setStrokeMiterLimit(miter);
        }
        if (!StrokeCap.m3799equalsimpl0(paintObtainStrokePaint.mo3353getStrokeCapKaPHkGw(), cap)) {
            paintObtainStrokePaint.mo3359setStrokeCapBeK7IIE(cap);
        }
        if (!StrokeJoin.m3809equalsimpl0(paintObtainStrokePaint.mo3354getStrokeJoinLxFBmk8(), join)) {
            paintObtainStrokePaint.mo3360setStrokeJoinWw9F2mQ(join);
        }
        if (!p.a(paintObtainStrokePaint.getPathEffect(), pathEffect)) {
            paintObtainStrokePaint.setPathEffect(pathEffect);
        }
        if (!FilterQuality.m3567equalsimpl0(paintObtainStrokePaint.mo3352getFilterQualityfv9h1I(), filterQuality)) {
            paintObtainStrokePaint.mo3358setFilterQualityvDHp3xo(filterQuality);
        }
        return paintObtainStrokePaint;
    }

    /* JADX INFO: renamed from: configureStrokePaint-Q_0CZUI$default, reason: not valid java name */
    public static /* synthetic */ Paint m3891configureStrokePaintQ_0CZUI$default(CanvasDrawScope canvasDrawScope, long j10, float f10, float f11, int i10, int i11, PathEffect pathEffect, float f12, ColorFilter colorFilter, int i12, int i13, int i14, Object obj) {
        return canvasDrawScope.m3890configureStrokePaintQ_0CZUI(j10, f10, f11, i10, i11, pathEffect, f12, colorFilter, i12, (i14 & 512) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i13);
    }

    /* JADX INFO: renamed from: configureStrokePaint-ho4zsrM, reason: not valid java name */
    private final Paint m3892configureStrokePaintho4zsrM(Brush brush, float strokeWidth, float miter, int cap, int join, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode, int filterQuality) {
        Paint paintObtainStrokePaint = obtainStrokePaint();
        if (brush != null) {
            brush.mo3425applyToPq9zytI(c.c(this), paintObtainStrokePaint, alpha);
        } else if (paintObtainStrokePaint.getAlpha() != alpha) {
            paintObtainStrokePaint.setAlpha(alpha);
        }
        if (!p.a(paintObtainStrokePaint.getInternalColorFilter(), colorFilter)) {
            paintObtainStrokePaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m3389equalsimpl0(paintObtainStrokePaint.get_blendMode(), blendMode)) {
            paintObtainStrokePaint.mo3356setBlendModes9anfk8(blendMode);
        }
        if (paintObtainStrokePaint.getStrokeWidth() != strokeWidth) {
            paintObtainStrokePaint.setStrokeWidth(strokeWidth);
        }
        if (paintObtainStrokePaint.getStrokeMiterLimit() != miter) {
            paintObtainStrokePaint.setStrokeMiterLimit(miter);
        }
        if (!StrokeCap.m3799equalsimpl0(paintObtainStrokePaint.mo3353getStrokeCapKaPHkGw(), cap)) {
            paintObtainStrokePaint.mo3359setStrokeCapBeK7IIE(cap);
        }
        if (!StrokeJoin.m3809equalsimpl0(paintObtainStrokePaint.mo3354getStrokeJoinLxFBmk8(), join)) {
            paintObtainStrokePaint.mo3360setStrokeJoinWw9F2mQ(join);
        }
        if (!p.a(paintObtainStrokePaint.getPathEffect(), pathEffect)) {
            paintObtainStrokePaint.setPathEffect(pathEffect);
        }
        if (!FilterQuality.m3567equalsimpl0(paintObtainStrokePaint.mo3352getFilterQualityfv9h1I(), filterQuality)) {
            paintObtainStrokePaint.mo3358setFilterQualityvDHp3xo(filterQuality);
        }
        return paintObtainStrokePaint;
    }

    /* JADX INFO: renamed from: configureStrokePaint-ho4zsrM$default, reason: not valid java name */
    public static /* synthetic */ Paint m3893configureStrokePaintho4zsrM$default(CanvasDrawScope canvasDrawScope, Brush brush, float f10, float f11, int i10, int i11, PathEffect pathEffect, float f12, ColorFilter colorFilter, int i12, int i13, int i14, Object obj) {
        return canvasDrawScope.m3892configureStrokePaintho4zsrM(brush, f10, f11, i10, i11, pathEffect, f12, colorFilter, i12, (i14 & 512) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i13);
    }

    public static /* synthetic */ void getDrawParams$annotations() {
    }

    /* JADX INFO: renamed from: modulate-5vOe2sY, reason: not valid java name */
    private final long m3894modulate5vOe2sY(long j10, float f10) {
        return f10 == 1.0f ? j10 : Color.m3471copywmQWz5c$default(j10, Color.m3474getAlphaimpl(j10) * f10, 0.0f, 0.0f, 0.0f, 14, null);
    }

    private final Paint obtainFillPaint() {
        Paint paint = this.fillPaint;
        if (paint != null) {
            return paint;
        }
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo3361setStylek9PVt8s(PaintingStyle.INSTANCE.m3720getFillTiuSbCo());
        this.fillPaint = Paint;
        return Paint;
    }

    private final Paint obtainStrokePaint() {
        Paint paint = this.strokePaint;
        if (paint != null) {
            return paint;
        }
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo3361setStylek9PVt8s(PaintingStyle.INSTANCE.m3721getStrokeTiuSbCo());
        this.strokePaint = Paint;
        return Paint;
    }

    private final Paint selectPaint(DrawStyle drawStyle) {
        if (p.a(drawStyle, Fill.INSTANCE)) {
            return obtainFillPaint();
        }
        if (!(drawStyle instanceof Stroke)) {
            throw new NoWhenBranchMatchedException();
        }
        Paint paintObtainStrokePaint = obtainStrokePaint();
        Stroke stroke = (Stroke) drawStyle;
        if (paintObtainStrokePaint.getStrokeWidth() != stroke.getWidth()) {
            paintObtainStrokePaint.setStrokeWidth(stroke.getWidth());
        }
        if (!StrokeCap.m3799equalsimpl0(paintObtainStrokePaint.mo3353getStrokeCapKaPHkGw(), stroke.getCap())) {
            paintObtainStrokePaint.mo3359setStrokeCapBeK7IIE(stroke.getCap());
        }
        if (paintObtainStrokePaint.getStrokeMiterLimit() != stroke.getMiter()) {
            paintObtainStrokePaint.setStrokeMiterLimit(stroke.getMiter());
        }
        if (!StrokeJoin.m3809equalsimpl0(paintObtainStrokePaint.mo3354getStrokeJoinLxFBmk8(), stroke.getJoin())) {
            paintObtainStrokePaint.mo3360setStrokeJoinWw9F2mQ(stroke.getJoin());
        }
        if (!p.a(paintObtainStrokePaint.getPathEffect(), stroke.getPathEffect())) {
            paintObtainStrokePaint.setPathEffect(stroke.getPathEffect());
        }
        return paintObtainStrokePaint;
    }

    /* JADX INFO: renamed from: draw-yzxVdVo, reason: not valid java name */
    public final void m3895drawyzxVdVo(Density density, LayoutDirection layoutDirection, Canvas canvas, long size, l<? super DrawScope, t0> block) {
        DrawParams drawParams = getDrawParams();
        Density density2 = drawParams.getDensity();
        LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
        Canvas canvas2 = drawParams.getCanvas();
        long size2 = drawParams.getSize();
        DrawParams drawParams2 = getDrawParams();
        drawParams2.setDensity(density);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas);
        drawParams2.m3921setSizeuvyYCjk(size);
        canvas.save();
        block.invoke(this);
        canvas.restore();
        DrawParams drawParams3 = getDrawParams();
        drawParams3.setDensity(density2);
        drawParams3.setLayoutDirection(layoutDirection2);
        drawParams3.setCanvas(canvas2);
        drawParams3.m3921setSizeuvyYCjk(size2);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-illE91I, reason: not valid java name */
    public void mo3896drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawArc(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), startAngle, sweepAngle, useCenter, m3889configurePaintswdJneE$default(this, brush, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-yD3GUKo, reason: not valid java name */
    public void mo3897drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawArc(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), startAngle, sweepAngle, useCenter, m3887configurePaint2qPWKa0$default(this, color, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-V9BoPsw, reason: not valid java name */
    public void mo3898drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3334drawCircle9KIMszo(center, radius, m3889configurePaintswdJneE$default(this, brush, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-VaOC9Bg, reason: not valid java name */
    public void mo3899drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3334drawCircle9KIMszo(center, radius, m3887configurePaint2qPWKa0$default(this, color, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    @e
    /* JADX INFO: renamed from: drawImage-9jGpkUE, reason: not valid java name */
    public /* synthetic */ void mo3900drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3336drawImageRectHPBpro0(image, srcOffset, srcSize, dstOffset, dstSize, m3889configurePaintswdJneE$default(this, null, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
    public void mo3901drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality) {
        this.drawParams.getCanvas().mo3336drawImageRectHPBpro0(image, srcOffset, srcSize, dstOffset, dstSize, m3888configurePaintswdJneE(null, style, alpha, colorFilter, blendMode, filterQuality));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-gbVJVH8, reason: not valid java name */
    public void mo3902drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3335drawImaged4ec7I(image, topLeft, m3889configurePaintswdJneE$default(this, null, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-1RTmtNc, reason: not valid java name */
    public void mo3903drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3337drawLineWko1d7g(start, end, m3893configureStrokePaintho4zsrM$default(this, brush, strokeWidth, 4.0f, cap, StrokeJoin.INSTANCE.m3814getMiterLxFBmk8(), pathEffect, alpha, colorFilter, blendMode, 0, 512, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-NGM6Ib0, reason: not valid java name */
    public void mo3904drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3337drawLineWko1d7g(start, end, m3891configureStrokePaintQ_0CZUI$default(this, color, strokeWidth, 4.0f, cap, StrokeJoin.INSTANCE.m3814getMiterLxFBmk8(), pathEffect, alpha, colorFilter, blendMode, 0, 512, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawOval-AsUm42w, reason: not valid java name */
    public void mo3905drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawOval(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), m3889configurePaintswdJneE$default(this, brush, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawOval-n-J9OG0, reason: not valid java name */
    public void mo3906drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawOval(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), m3887configurePaint2qPWKa0$default(this, color, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-GBMwjPU, reason: not valid java name */
    public void mo3907drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawPath(path, m3889configurePaintswdJneE$default(this, brush, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-LG529CI, reason: not valid java name */
    public void mo3908drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawPath(path, m3887configurePaint2qPWKa0$default(this, color, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPoints-F8ZwMP8, reason: not valid java name */
    public void mo3909drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3338drawPointsO7TthRY(pointMode, points, m3891configureStrokePaintQ_0CZUI$default(this, color, strokeWidth, 4.0f, cap, StrokeJoin.INSTANCE.m3814getMiterLxFBmk8(), pathEffect, alpha, colorFilter, blendMode, 0, 512, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPoints-Gsft0Ws, reason: not valid java name */
    public void mo3910drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().mo3338drawPointsO7TthRY(pointMode, points, m3893configureStrokePaintho4zsrM$default(this, brush, strokeWidth, 4.0f, cap, StrokeJoin.INSTANCE.m3814getMiterLxFBmk8(), pathEffect, alpha, colorFilter, blendMode, 0, 512, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-AsUm42w, reason: not valid java name */
    public void mo3911drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawRect(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), m3889configurePaintswdJneE$default(this, brush, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-n-J9OG0, reason: not valid java name */
    public void mo3912drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawRect(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), m3887configurePaint2qPWKa0$default(this, color, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ, reason: not valid java name */
    public void mo3913drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawRoundRect(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), CornerRadius.m3211getXimpl(cornerRadius), CornerRadius.m3212getYimpl(cornerRadius), m3889configurePaintswdJneE$default(this, brush, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA, reason: not valid java name */
    public void mo3914drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode) {
        this.drawParams.getCanvas().drawRoundRect(Offset.m3236getXimpl(topLeft), Offset.m3237getYimpl(topLeft), Size.m3305getWidthimpl(size) + Offset.m3236getXimpl(topLeft), Size.m3302getHeightimpl(size) + Offset.m3237getYimpl(topLeft), CornerRadius.m3211getXimpl(cornerRadius), CornerRadius.m3212getYimpl(cornerRadius), m3887configurePaint2qPWKa0$default(this, color, style, alpha, colorFilter, blendMode, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
    public final /* synthetic */ long mo3915getCenterF1C5BW0() {
        return c.b(this);
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.drawParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.drawContext;
    }

    public final DrawParams getDrawParams() {
        return this.drawParams;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.drawParams.getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.drawParams.getLayoutDirection();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final /* synthetic */ long mo3916getSizeNHjbRc() {
        return c.c(this);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public final /* synthetic */ int mo278roundToPxR2X_6o(long j10) {
        return androidx.compose.ui.unit.a.a(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public final /* synthetic */ int mo279roundToPx0680j_4(float f10) {
        return androidx.compose.ui.unit.a.b(this, f10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public final /* synthetic */ float mo280toDpGaN1DYA(long j10) {
        return androidx.compose.ui.unit.b.a(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public final /* synthetic */ float mo281toDpu2uoSUM(float f10) {
        return androidx.compose.ui.unit.a.c(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public final /* synthetic */ long mo283toDpSizekrfVVM(long j10) {
        return androidx.compose.ui.unit.a.e(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public final /* synthetic */ float mo284toPxR2X_6o(long j10) {
        return androidx.compose.ui.unit.a.f(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public final /* synthetic */ float mo285toPx0680j_4(float f10) {
        return androidx.compose.ui.unit.a.g(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    public final /* synthetic */ Rect toRect(DpRect dpRect) {
        return androidx.compose.ui.unit.a.h(this, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public final /* synthetic */ long mo286toSizeXkaWNTQ(long j10) {
        return androidx.compose.ui.unit.a.i(this, j10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public final /* synthetic */ long mo287toSp0xMU5do(float f10) {
        return androidx.compose.ui.unit.b.b(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public final /* synthetic */ long mo288toSpkPz2Gy4(float f10) {
        return androidx.compose.ui.unit.a.j(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public final /* synthetic */ float mo282toDpu2uoSUM(int i10) {
        return androidx.compose.ui.unit.a.d(this, i10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public final /* synthetic */ long mo289toSpkPz2Gy4(int i10) {
        return androidx.compose.ui.unit.a.k(this, i10);
    }
}
