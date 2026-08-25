package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\bI\b\u0007\u0018\u0000 ¬\u00012\u00020\u0001:\u0002¬\u0001B%\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\nB×\u0001\b\u0017\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*¢\u0006\u0004\b\b\u0010,Bï\u0001\b\u0017\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-¢\u0006\u0004\b\b\u0010/B\u0087\u0002\b\u0017\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000102¢\u0006\u0004\b\b\u00104B\u009f\u0002\b\u0017\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000102\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107¢\u0006\u0004\b\b\u00109B\u0097\u0002\b\u0016\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\b\b\u0002\u0010&\u001a\u00020%\u0012\b\b\u0002\u0010(\u001a\u00020'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\b\b\u0002\u00101\u001a\u000200\u0012\b\b\u0002\u00103\u001a\u000202\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107¢\u0006\u0004\b\b\u0010:B¡\u0002\b\u0016\u0012\b\u0010<\u001a\u0004\u0018\u00010;\u0012\b\b\u0002\u0010>\u001a\u00020=\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\b\b\u0002\u0010&\u001a\u00020%\u0012\b\b\u0002\u0010(\u001a\u00020'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\b\b\u0002\u00101\u001a\u000200\u0012\b\b\u0002\u00103\u001a\u000202\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107¢\u0006\u0004\b\b\u0010?B©\u0002\b\u0017\u0012\b\u0010<\u001a\u0004\u0018\u00010;\u0012\b\b\u0002\u0010>\u001a\u00020=\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\r\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u0010)\u001a\u00020\r\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000102\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107¢\u0006\u0004\b\b\u0010@J\u000f\u0010A\u001a\u00020\u0002H\u0007¢\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u00020\u0004H\u0007¢\u0006\u0004\bC\u0010DJ\u001b\u0010F\u001a\u00020\u00002\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0004\bF\u0010GJ \u0002\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\b\b\u0002\u00101\u001a\u0002002\b\b\u0002\u00103\u001a\u0002022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00108\u001a\u0004\u0018\u000107H\u0007ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ¨\u0002\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\n\b\u0002\u00101\u001a\u0004\u0018\u0001002\n\b\u0002\u00103\u001a\u0004\u0018\u0001022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00108\u001a\u0004\u0018\u000107H\u0007ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\u0017\u0010F\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0002H\u0007¢\u0006\u0004\bF\u0010LJ\u0017\u0010F\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0004H\u0007¢\u0006\u0004\bF\u0010MJ\u0018\u0010N\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\bN\u0010GJ\u0018\u0010N\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0004H\u0087\u0002¢\u0006\u0004\bN\u0010MJ\u0018\u0010N\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0002H\u0087\u0002¢\u0006\u0004\bN\u0010LJà\u0001\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*H\u0007ø\u0001\u0000¢\u0006\u0004\bO\u0010PJø\u0001\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-H\u0007ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ\u0090\u0002\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\n\b\u0002\u00101\u001a\u0004\u0018\u0001002\n\b\u0002\u00103\u001a\u0004\u0018\u000102H\u0007ø\u0001\u0000¢\u0006\u0004\bT\u0010UJ¨\u0002\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\n\b\u0002\u00101\u001a\u0004\u0018\u0001002\n\b\u0002\u00103\u001a\u0004\u0018\u0001022\n\b\u0002\u00108\u001a\u0004\u0018\u000107H\u0007ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ\u009e\u0002\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\b\b\u0002\u00101\u001a\u0002002\b\b\u0002\u00103\u001a\u0002022\n\b\u0002\u00108\u001a\u0004\u0018\u000107ø\u0001\u0000¢\u0006\u0004\bX\u0010YJ²\u0002\u0010Q\u001a\u00020\u00002\b\u0010<\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\n\b\u0002\u00101\u001a\u0004\u0018\u0001002\n\b\u0002\u00103\u001a\u0004\u0018\u0001022\n\b\u0002\u00108\u001a\u0004\u0018\u000107H\u0007ø\u0001\u0000¢\u0006\u0004\bZ\u0010[J¨\u0002\u0010Q\u001a\u00020\u00002\b\u0010<\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u00106\u001a\u0004\u0018\u0001052\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020\r2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\b\b\u0002\u00101\u001a\u0002002\b\b\u0002\u00103\u001a\u0002022\n\b\u0002\u00108\u001a\u0004\u0018\u000107ø\u0001\u0000¢\u0006\u0004\b\\\u0010]J\u001a\u0010_\u001a\u00020^2\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b_\u0010`J\u0015\u0010a\u001a\u00020^2\u0006\u0010E\u001a\u00020\u0000¢\u0006\u0004\ba\u0010bJ\u0015\u0010c\u001a\u00020^2\u0006\u0010E\u001a\u00020\u0000¢\u0006\u0004\bc\u0010bJ\u000f\u0010e\u001a\u00020dH\u0016¢\u0006\u0004\be\u0010fJ\u000f\u0010h\u001a\u00020dH\u0000¢\u0006\u0004\bg\u0010fJ\u000f\u0010i\u001a\u00020\u0017H\u0016¢\u0006\u0004\bi\u0010jR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010k\u001a\u0004\bl\u0010BR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010m\u001a\u0004\bn\u0010DR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010o\u001a\u0004\bp\u0010qR\u0013\u0010<\u001a\u0004\u0018\u00010;8F¢\u0006\u0006\u001a\u0004\br\u0010sR\u0017\u0010\f\u001a\u00020\u000b8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bt\u0010uR\u0011\u0010>\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0017\u0010\u000e\u001a\u00020\r8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bx\u0010uR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u000f8F¢\u0006\u0006\u001a\u0004\by\u0010zR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b}\u0010~R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00158F¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00178F¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010jR\u0018\u0010\u0019\u001a\u00020\r8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010uR\u001b\u0010\u001b\u001a\u0004\u0018\u00010\u001a8Fø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u001c8F¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u001e8F¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0018\u0010 \u001a\u00020\u000b8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u0010uR\u0015\u0010\"\u001a\u0004\u0018\u00010!8F¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0015\u0010$\u001a\u0004\u0018\u00010#8F¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0015\u00106\u001a\u0004\u0018\u0001058F¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0018\u0010&\u001a\u00020%8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0090\u0001\u0010fR'\u0010\u0095\u0001\u001a\u0004\u0018\u00010%8GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\u0012\u0006\b\u0093\u0001\u0010\u0094\u0001\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R\u0018\u0010(\u001a\u00020'8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0096\u0001\u0010fR'\u0010\u009a\u0001\u001a\u0004\u0018\u00010'8GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\u0012\u0006\b\u0099\u0001\u0010\u0094\u0001\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0018\u0010)\u001a\u00020\r8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u009b\u0001\u0010uR\u0015\u0010+\u001a\u0004\u0018\u00010*8F¢\u0006\b\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0015\u0010.\u001a\u0004\u0018\u00010-8F¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0018\u00103\u001a\u0002028Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b \u0001\u0010fR'\u0010¤\u0001\u001a\u0004\u0018\u0001028GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\u0012\u0006\b£\u0001\u0010\u0094\u0001\u001a\u0006\b¡\u0001\u0010¢\u0001R\u0018\u00101\u001a\u0002008Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b¥\u0001\u0010fR'\u0010©\u0001\u001a\u0004\u0018\u0001008GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\u0012\u0006\b¨\u0001\u0010\u0094\u0001\u001a\u0006\b¦\u0001\u0010§\u0001R\u0015\u00108\u001a\u0004\u0018\u0001078F¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u00ad\u0001"}, d2 = {"Landroidx/compose/ui/text/TextStyle;", "", "Landroidx/compose/ui/text/SpanStyle;", "spanStyle", "Landroidx/compose/ui/text/ParagraphStyle;", "paragraphStyle", "Landroidx/compose/ui/text/PlatformTextStyle;", "platformStyle", "<init>", "(Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/ParagraphStyle;Landroidx/compose/ui/text/PlatformTextStyle;)V", "(Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/ParagraphStyle;)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/unit/TextUnit;", TtmlNode.ATTR_TTS_FONT_SIZE, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontSynthesis;", "fontSynthesis", "Landroidx/compose/ui/text/font/FontFamily;", TtmlNode.ATTR_TTS_FONT_FAMILY, "", "fontFeatureSettings", "letterSpacing", "Landroidx/compose/ui/text/style/BaselineShift;", "baselineShift", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "textGeometricTransform", "Landroidx/compose/ui/text/intl/LocaleList;", "localeList", "background", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/graphics/Shadow;", "shadow", "Landroidx/compose/ui/text/style/TextAlign;", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextDirection;", "textDirection", "lineHeight", "Landroidx/compose/ui/text/style/TextIndent;", "textIndent", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/text/style/LineHeightStyle;", "lineHeightStyle", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/text/style/LineBreak;", "lineBreak", "Landroidx/compose/ui/text/style/Hyphens;", "hyphens", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "Landroidx/compose/ui/text/style/TextMotion;", "textMotion", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/style/TextMotion;Lkotlin/jvm/internal/h;)V", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/graphics/Brush;", "brush", "", "alpha", "(Landroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;Lkotlin/jvm/internal/h;)V", "(Landroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/style/TextMotion;Lkotlin/jvm/internal/h;)V", "toSpanStyle", "()Landroidx/compose/ui/text/SpanStyle;", "toParagraphStyle", "()Landroidx/compose/ui/text/ParagraphStyle;", "other", "merge", "(Landroidx/compose/ui/text/TextStyle;)Landroidx/compose/ui/text/TextStyle;", "merge-dA7vx0o", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/TextStyle;", "merge-Z1GrekI", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/TextStyle;", "(Landroidx/compose/ui/text/SpanStyle;)Landroidx/compose/ui/text/TextStyle;", "(Landroidx/compose/ui/text/ParagraphStyle;)Landroidx/compose/ui/text/TextStyle;", "plus", "copy-HL5avdY", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;)Landroidx/compose/ui/text/TextStyle;", "copy", "copy-NOaFTUo", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;)Landroidx/compose/ui/text/TextStyle;", "copy-CXVQc50", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;)Landroidx/compose/ui/text/TextStyle;", "copy-v2rsoow", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/TextStyle;", "copy-p1EtxEg", "(JJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/TextStyle;", "copy-aIRg9q4", "(Landroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/TextStyle;", "copy-Ns73l9s", "(Landroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/graphics/drawscope/DrawStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformTextStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/TextStyle;", "", "equals", "(Ljava/lang/Object;)Z", "hasSameLayoutAffectingAttributes", "(Landroidx/compose/ui/text/TextStyle;)Z", "hasSameDrawAffectingAttributes", "", "hashCode", "()I", "hashCodeLayoutAffectingAttributes$ui_text_release", "hashCodeLayoutAffectingAttributes", "toString", "()Ljava/lang/String;", "Landroidx/compose/ui/text/SpanStyle;", "getSpanStyle$ui_text_release", "Landroidx/compose/ui/text/ParagraphStyle;", "getParagraphStyle$ui_text_release", "Landroidx/compose/ui/text/PlatformTextStyle;", "getPlatformStyle", "()Landroidx/compose/ui/text/PlatformTextStyle;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "getColor-0d7_KjU", "()J", "getAlpha", "()F", "getFontSize-XSAIIZE", "getFontWeight", "()Landroidx/compose/ui/text/font/FontWeight;", "getFontStyle-4Lr2A7w", "()Landroidx/compose/ui/text/font/FontStyle;", "getFontSynthesis-ZQGJjVo", "()Landroidx/compose/ui/text/font/FontSynthesis;", "getFontFamily", "()Landroidx/compose/ui/text/font/FontFamily;", "getFontFeatureSettings", "getLetterSpacing-XSAIIZE", "getBaselineShift-5SSeXJ0", "()Landroidx/compose/ui/text/style/BaselineShift;", "getTextGeometricTransform", "()Landroidx/compose/ui/text/style/TextGeometricTransform;", "getLocaleList", "()Landroidx/compose/ui/text/intl/LocaleList;", "getBackground-0d7_KjU", "getTextDecoration", "()Landroidx/compose/ui/text/style/TextDecoration;", "getShadow", "()Landroidx/compose/ui/graphics/Shadow;", "getDrawStyle", "()Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "getTextAlign-e0LSkKk", "getTextAlign-buA522U", "()Landroidx/compose/ui/text/style/TextAlign;", "getTextAlign-buA522U$annotations", "()V", "deprecated_boxing_textAlign", "getTextDirection-s_7X-co", "getTextDirection-mmuk1to", "()Landroidx/compose/ui/text/style/TextDirection;", "getTextDirection-mmuk1to$annotations", "deprecated_boxing_textDirection", "getLineHeight-XSAIIZE", "getTextIndent", "()Landroidx/compose/ui/text/style/TextIndent;", "getLineHeightStyle", "()Landroidx/compose/ui/text/style/LineHeightStyle;", "getHyphens-vmbZdU8", "getHyphens-EaSxIns", "()Landroidx/compose/ui/text/style/Hyphens;", "getHyphens-EaSxIns$annotations", "deprecated_boxing_hyphens", "getLineBreak-rAG3T2k", "getLineBreak-LgCVezo", "()Landroidx/compose/ui/text/style/LineBreak;", "getLineBreak-LgCVezo$annotations", "deprecated_boxing_lineBreak", "getTextMotion", "()Landroidx/compose/ui/text/style/TextMotion;", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextStyle {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TextStyle Default = new TextStyle(0, 0, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777215, (h) null);
    private final ParagraphStyle paragraphStyle;
    private final PlatformTextStyle platformStyle;
    private final SpanStyle spanStyle;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/TextStyle$Companion;", "", "()V", "Default", "Landroidx/compose/ui/text/TextStyle;", "getDefault$annotations", "getDefault", "()Landroidx/compose/ui/text/TextStyle;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getDefault$annotations() {
        }

        public final TextStyle getDefault() {
            return TextStyle.Default;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, h hVar) {
        this(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, drawStyle, i10, i11, j14, textIndent, platformTextStyle, lineHeightStyle, i12, i13, textMotion);
    }

    /* JADX INFO: renamed from: copy-CXVQc50$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5200copyCXVQc50$default(TextStyle textStyle, long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, int i10, Object obj) {
        Hyphens hyphensM5485boximpl;
        LineBreak lineBreak2;
        long jM5151getColor0d7_KjU = (i10 & 1) != 0 ? textStyle.spanStyle.m5151getColor0d7_KjU() : j10;
        long jM5152getFontSizeXSAIIZE = (i10 & 2) != 0 ? textStyle.spanStyle.getFontSize() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight;
        FontStyle fontStyleM5153getFontStyle4Lr2A7w = (i10 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle;
        FontSynthesis fontSynthesisM5154getFontSynthesisZQGJjVo = (i10 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily;
        String fontFeatureSettings = (i10 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str;
        long jM5155getLetterSpacingXSAIIZE = (i10 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : j12;
        BaselineShift baselineShiftM5150getBaselineShift5SSeXJ0 = (i10 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : localeList;
        long j15 = jM5151getColor0d7_KjU;
        long jM5149getBackground0d7_KjU = (i10 & 2048) != 0 ? textStyle.spanStyle.getBackground() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? textStyle.spanStyle.getBackground() : textDecoration;
        Shadow shadow2 = (i10 & 8192) != 0 ? textStyle.spanStyle.getShadow() : shadow;
        TextDecoration textDecoration3 = textDecoration2;
        TextAlign textAlignM5575boximpl = (i10 & 16384) != 0 ? TextAlign.m5575boximpl(textStyle.paragraphStyle.getTextAlign()) : textAlign;
        TextDirection textDirectionM5589boximpl = (i10 & 32768) != 0 ? TextDirection.m5589boximpl(textStyle.paragraphStyle.getTextDirection()) : textDirection;
        long jM5105getLineHeightXSAIIZE = (i10 & 65536) != 0 ? textStyle.paragraphStyle.getLineHeight() : j14;
        TextIndent textIndent2 = (i10 & 131072) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent;
        PlatformTextStyle platformTextStyle2 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i10 & 524288) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : lineHeightStyle;
        LineBreak lineBreakM5495boximpl = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? LineBreak.m5495boximpl(textStyle.paragraphStyle.getLineBreak()) : lineBreak;
        if ((i10 & 2097152) != 0) {
            lineBreak2 = lineBreakM5495boximpl;
            hyphensM5485boximpl = Hyphens.m5485boximpl(textStyle.paragraphStyle.getHyphens());
        } else {
            hyphensM5485boximpl = hyphens;
            lineBreak2 = lineBreakM5495boximpl;
        }
        return textStyle.m5213copyCXVQc50(j15, jM5152getFontSizeXSAIIZE, fontWeight2, fontStyleM5153getFontStyle4Lr2A7w, fontSynthesisM5154getFontSynthesisZQGJjVo, fontFamily2, fontFeatureSettings, jM5155getLetterSpacingXSAIIZE, baselineShiftM5150getBaselineShift5SSeXJ0, textGeometricTransform2, localeList2, jM5149getBackground0d7_KjU, textDecoration3, shadow2, textAlignM5575boximpl, textDirectionM5589boximpl, jM5105getLineHeightXSAIIZE, textIndent2, platformTextStyle2, lineHeightStyle2, lineBreak2, hyphensM5485boximpl);
    }

    /* JADX INFO: renamed from: copy-HL5avdY$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5201copyHL5avdY$default(TextStyle textStyle, long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, int i10, Object obj) {
        long jM5151getColor0d7_KjU = (i10 & 1) != 0 ? textStyle.spanStyle.m5151getColor0d7_KjU() : j10;
        return textStyle.m5214copyHL5avdY(jM5151getColor0d7_KjU, (i10 & 2) != 0 ? textStyle.spanStyle.getFontSize() : j11, (i10 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight, (i10 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle, (i10 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis, (i10 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily, (i10 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str, (i10 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : j12, (i10 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift, (i10 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform, (i10 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : localeList, (i10 & 2048) != 0 ? textStyle.spanStyle.getBackground() : j13, (i10 & 4096) != 0 ? textStyle.spanStyle.getBackground() : textDecoration, (i10 & 8192) != 0 ? textStyle.spanStyle.getShadow() : shadow, (i10 & 16384) != 0 ? TextAlign.m5575boximpl(textStyle.paragraphStyle.getTextAlign()) : textAlign, (i10 & 32768) != 0 ? TextDirection.m5589boximpl(textStyle.paragraphStyle.getTextDirection()) : textDirection, (i10 & 65536) != 0 ? textStyle.paragraphStyle.getLineHeight() : j14, (i10 & 131072) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent);
    }

    /* JADX INFO: renamed from: copy-NOaFTUo$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5202copyNOaFTUo$default(TextStyle textStyle, long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i10, Object obj) {
        LineHeightStyle lineHeightStyle2;
        PlatformTextStyle platformTextStyle2;
        long jM5151getColor0d7_KjU = (i10 & 1) != 0 ? textStyle.spanStyle.m5151getColor0d7_KjU() : j10;
        long jM5152getFontSizeXSAIIZE = (i10 & 2) != 0 ? textStyle.spanStyle.getFontSize() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight;
        FontStyle fontStyleM5153getFontStyle4Lr2A7w = (i10 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle;
        FontSynthesis fontSynthesisM5154getFontSynthesisZQGJjVo = (i10 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily;
        String fontFeatureSettings = (i10 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str;
        long jM5155getLetterSpacingXSAIIZE = (i10 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : j12;
        BaselineShift baselineShiftM5150getBaselineShift5SSeXJ0 = (i10 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : localeList;
        long j15 = jM5151getColor0d7_KjU;
        long jM5149getBackground0d7_KjU = (i10 & 2048) != 0 ? textStyle.spanStyle.getBackground() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? textStyle.spanStyle.getBackground() : textDecoration;
        Shadow shadow2 = (i10 & 8192) != 0 ? textStyle.spanStyle.getShadow() : shadow;
        TextDecoration textDecoration3 = textDecoration2;
        TextAlign textAlignM5575boximpl = (i10 & 16384) != 0 ? TextAlign.m5575boximpl(textStyle.paragraphStyle.getTextAlign()) : textAlign;
        TextDirection textDirectionM5589boximpl = (i10 & 32768) != 0 ? TextDirection.m5589boximpl(textStyle.paragraphStyle.getTextDirection()) : textDirection;
        long jM5105getLineHeightXSAIIZE = (i10 & 65536) != 0 ? textStyle.paragraphStyle.getLineHeight() : j14;
        TextIndent textIndent2 = (i10 & 131072) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent;
        PlatformTextStyle platformTextStyle3 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textStyle.platformStyle : platformTextStyle;
        if ((i10 & 524288) != 0) {
            platformTextStyle2 = platformTextStyle3;
            lineHeightStyle2 = textStyle.paragraphStyle.getLineHeightStyle();
        } else {
            lineHeightStyle2 = lineHeightStyle;
            platformTextStyle2 = platformTextStyle3;
        }
        return textStyle.m5215copyNOaFTUo(j15, jM5152getFontSizeXSAIIZE, fontWeight2, fontStyleM5153getFontStyle4Lr2A7w, fontSynthesisM5154getFontSynthesisZQGJjVo, fontFamily2, fontFeatureSettings, jM5155getLetterSpacingXSAIIZE, baselineShiftM5150getBaselineShift5SSeXJ0, textGeometricTransform2, localeList2, jM5149getBackground0d7_KjU, textDecoration3, shadow2, textAlignM5575boximpl, textDirectionM5589boximpl, jM5105getLineHeightXSAIIZE, textIndent2, platformTextStyle2, lineHeightStyle2);
    }

    /* JADX INFO: renamed from: copy-Ns73l9s$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5203copyNs73l9s$default(TextStyle textStyle, Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, int i14, Object obj) {
        TextMotion textMotion2;
        int i15;
        TextDecoration textDecoration2;
        DrawStyle drawStyle2;
        int i16;
        int i17;
        TextIndent textIndent2;
        PlatformTextStyle platformTextStyle2;
        LineHeightStyle lineHeightStyle2;
        Shadow shadow2;
        long j14;
        FontWeight fontWeight2;
        FontStyle fontStyle2;
        FontSynthesis fontSynthesis2;
        String str2;
        long j15;
        BaselineShift baselineShift2;
        TextGeometricTransform textGeometricTransform2;
        LocaleList localeList2;
        long j16;
        long j17;
        Brush brush2;
        float f11;
        int i18;
        TextStyle textStyle2;
        FontFamily fontFamily2;
        float alpha = (i14 & 2) != 0 ? textStyle.spanStyle.getAlpha() : f10;
        long jM5152getFontSizeXSAIIZE = (i14 & 4) != 0 ? textStyle.spanStyle.getFontSize() : j10;
        FontWeight fontWeight3 = (i14 & 8) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight;
        FontStyle fontStyleM5153getFontStyle4Lr2A7w = (i14 & 16) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle;
        FontSynthesis fontSynthesisM5154getFontSynthesisZQGJjVo = (i14 & 32) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis;
        FontFamily fontFamily3 = (i14 & 64) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily;
        String fontFeatureSettings = (i14 & 128) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str;
        long jM5155getLetterSpacingXSAIIZE = (i14 & 256) != 0 ? textStyle.spanStyle.getLetterSpacing() : j11;
        BaselineShift baselineShiftM5150getBaselineShift5SSeXJ0 = (i14 & 512) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift;
        TextGeometricTransform textGeometricTransform3 = (i14 & 1024) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform;
        LocaleList localeList3 = (i14 & 2048) != 0 ? textStyle.spanStyle.getLocaleList() : localeList;
        long jM5149getBackground0d7_KjU = (i14 & 4096) != 0 ? textStyle.spanStyle.getBackground() : j12;
        float f12 = alpha;
        TextDecoration textDecoration3 = (i14 & 8192) != 0 ? textStyle.spanStyle.getBackground() : textDecoration;
        Shadow shadow3 = (i14 & 16384) != 0 ? textStyle.spanStyle.getShadow() : shadow;
        DrawStyle drawStyle3 = (i14 & 32768) != 0 ? textStyle.spanStyle.getDrawStyle() : drawStyle;
        int iM5107getTextAligne0LSkKk = (i14 & 65536) != 0 ? textStyle.paragraphStyle.getTextAlign() : i10;
        int iM5109getTextDirections_7Xco = (i14 & 131072) != 0 ? textStyle.paragraphStyle.getTextDirection() : i11;
        long jM5105getLineHeightXSAIIZE = (i14 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textStyle.paragraphStyle.getLineHeight() : j13;
        TextIndent textIndent3 = (i14 & 524288) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent;
        PlatformTextStyle platformTextStyle3 = (i14 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle3 = (i14 & 2097152) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : lineHeightStyle;
        int iM5104getLineBreakrAG3T2k = (i14 & 4194304) != 0 ? textStyle.paragraphStyle.getLineBreak() : i12;
        int iM5102getHyphensvmbZdU8 = (i14 & 8388608) != 0 ? textStyle.paragraphStyle.getHyphens() : i13;
        if ((i14 & 16777216) != 0) {
            i15 = iM5102getHyphensvmbZdU8;
            textMotion2 = textStyle.paragraphStyle.getTextMotion();
            textDecoration2 = textDecoration3;
            drawStyle2 = drawStyle3;
            i16 = iM5107getTextAligne0LSkKk;
            i17 = iM5109getTextDirections_7Xco;
            textIndent2 = textIndent3;
            platformTextStyle2 = platformTextStyle3;
            lineHeightStyle2 = lineHeightStyle3;
            i18 = iM5104getLineBreakrAG3T2k;
            shadow2 = shadow3;
            j14 = jM5152getFontSizeXSAIIZE;
            fontWeight2 = fontWeight3;
            fontStyle2 = fontStyleM5153getFontStyle4Lr2A7w;
            fontSynthesis2 = fontSynthesisM5154getFontSynthesisZQGJjVo;
            fontFamily2 = fontFamily3;
            str2 = fontFeatureSettings;
            j15 = jM5155getLetterSpacingXSAIIZE;
            baselineShift2 = baselineShiftM5150getBaselineShift5SSeXJ0;
            textGeometricTransform2 = textGeometricTransform3;
            localeList2 = localeList3;
            j16 = jM5149getBackground0d7_KjU;
            j17 = jM5105getLineHeightXSAIIZE;
            brush2 = brush;
            f11 = f12;
            textStyle2 = textStyle;
        } else {
            textMotion2 = textMotion;
            i15 = iM5102getHyphensvmbZdU8;
            textDecoration2 = textDecoration3;
            drawStyle2 = drawStyle3;
            i16 = iM5107getTextAligne0LSkKk;
            i17 = iM5109getTextDirections_7Xco;
            textIndent2 = textIndent3;
            platformTextStyle2 = platformTextStyle3;
            lineHeightStyle2 = lineHeightStyle3;
            shadow2 = shadow3;
            j14 = jM5152getFontSizeXSAIIZE;
            fontWeight2 = fontWeight3;
            fontStyle2 = fontStyleM5153getFontStyle4Lr2A7w;
            fontSynthesis2 = fontSynthesisM5154getFontSynthesisZQGJjVo;
            str2 = fontFeatureSettings;
            j15 = jM5155getLetterSpacingXSAIIZE;
            baselineShift2 = baselineShiftM5150getBaselineShift5SSeXJ0;
            textGeometricTransform2 = textGeometricTransform3;
            localeList2 = localeList3;
            j16 = jM5149getBackground0d7_KjU;
            j17 = jM5105getLineHeightXSAIIZE;
            brush2 = brush;
            f11 = f12;
            i18 = iM5104getLineBreakrAG3T2k;
            textStyle2 = textStyle;
            fontFamily2 = fontFamily3;
        }
        return textStyle2.m5216copyNs73l9s(brush2, f11, j14, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, str2, j15, baselineShift2, textGeometricTransform2, localeList2, j16, textDecoration2, shadow2, drawStyle2, i16, i17, j17, textIndent2, platformTextStyle2, lineHeightStyle2, i18, i15, textMotion2);
    }

    /* JADX INFO: renamed from: copy-aIRg9q4$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5204copyaIRg9q4$default(TextStyle textStyle, Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, int i10, Object obj) {
        TextMotion textMotion2;
        Hyphens hyphens2;
        TextDecoration textDecoration2;
        DrawStyle drawStyle2;
        TextAlign textAlign2;
        TextDirection textDirection2;
        TextIndent textIndent2;
        PlatformTextStyle platformTextStyle2;
        LineHeightStyle lineHeightStyle2;
        Shadow shadow2;
        long j14;
        FontWeight fontWeight2;
        FontStyle fontStyle2;
        FontSynthesis fontSynthesis2;
        String str2;
        long j15;
        BaselineShift baselineShift2;
        TextGeometricTransform textGeometricTransform2;
        LocaleList localeList2;
        long j16;
        long j17;
        Brush brush2;
        float f11;
        LineBreak lineBreak2;
        TextStyle textStyle2;
        FontFamily fontFamily2;
        float alpha = (i10 & 2) != 0 ? textStyle.spanStyle.getAlpha() : f10;
        long jM5152getFontSizeXSAIIZE = (i10 & 4) != 0 ? textStyle.spanStyle.getFontSize() : j10;
        FontWeight fontWeight3 = (i10 & 8) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight;
        FontStyle fontStyleM5153getFontStyle4Lr2A7w = (i10 & 16) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle;
        FontSynthesis fontSynthesisM5154getFontSynthesisZQGJjVo = (i10 & 32) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis;
        FontFamily fontFamily3 = (i10 & 64) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily;
        String fontFeatureSettings = (i10 & 128) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str;
        long jM5155getLetterSpacingXSAIIZE = (i10 & 256) != 0 ? textStyle.spanStyle.getLetterSpacing() : j11;
        BaselineShift baselineShiftM5150getBaselineShift5SSeXJ0 = (i10 & 512) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift;
        TextGeometricTransform textGeometricTransform3 = (i10 & 1024) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform;
        LocaleList localeList3 = (i10 & 2048) != 0 ? textStyle.spanStyle.getLocaleList() : localeList;
        long jM5149getBackground0d7_KjU = (i10 & 4096) != 0 ? textStyle.spanStyle.getBackground() : j12;
        float f12 = alpha;
        TextDecoration textDecoration3 = (i10 & 8192) != 0 ? textStyle.spanStyle.getBackground() : textDecoration;
        Shadow shadow3 = (i10 & 16384) != 0 ? textStyle.spanStyle.getShadow() : shadow;
        DrawStyle drawStyle3 = (i10 & 32768) != 0 ? textStyle.spanStyle.getDrawStyle() : drawStyle;
        TextAlign textAlignM5575boximpl = (i10 & 65536) != 0 ? TextAlign.m5575boximpl(textStyle.paragraphStyle.getTextAlign()) : textAlign;
        TextDirection textDirectionM5589boximpl = (i10 & 131072) != 0 ? TextDirection.m5589boximpl(textStyle.paragraphStyle.getTextDirection()) : textDirection;
        long jM5105getLineHeightXSAIIZE = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textStyle.paragraphStyle.getLineHeight() : j13;
        TextIndent textIndent3 = (i10 & 524288) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent;
        PlatformTextStyle platformTextStyle3 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle3 = (i10 & 2097152) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : lineHeightStyle;
        LineBreak lineBreakM5495boximpl = (i10 & 4194304) != 0 ? LineBreak.m5495boximpl(textStyle.paragraphStyle.getLineBreak()) : lineBreak;
        Hyphens hyphensM5485boximpl = (i10 & 8388608) != 0 ? Hyphens.m5485boximpl(textStyle.paragraphStyle.getHyphens()) : hyphens;
        if ((i10 & 16777216) != 0) {
            hyphens2 = hyphensM5485boximpl;
            textMotion2 = textStyle.paragraphStyle.getTextMotion();
            textDecoration2 = textDecoration3;
            drawStyle2 = drawStyle3;
            textAlign2 = textAlignM5575boximpl;
            textDirection2 = textDirectionM5589boximpl;
            textIndent2 = textIndent3;
            platformTextStyle2 = platformTextStyle3;
            lineHeightStyle2 = lineHeightStyle3;
            lineBreak2 = lineBreakM5495boximpl;
            shadow2 = shadow3;
            j14 = jM5152getFontSizeXSAIIZE;
            fontWeight2 = fontWeight3;
            fontStyle2 = fontStyleM5153getFontStyle4Lr2A7w;
            fontSynthesis2 = fontSynthesisM5154getFontSynthesisZQGJjVo;
            fontFamily2 = fontFamily3;
            str2 = fontFeatureSettings;
            j15 = jM5155getLetterSpacingXSAIIZE;
            baselineShift2 = baselineShiftM5150getBaselineShift5SSeXJ0;
            textGeometricTransform2 = textGeometricTransform3;
            localeList2 = localeList3;
            j16 = jM5149getBackground0d7_KjU;
            j17 = jM5105getLineHeightXSAIIZE;
            brush2 = brush;
            f11 = f12;
            textStyle2 = textStyle;
        } else {
            textMotion2 = textMotion;
            hyphens2 = hyphensM5485boximpl;
            textDecoration2 = textDecoration3;
            drawStyle2 = drawStyle3;
            textAlign2 = textAlignM5575boximpl;
            textDirection2 = textDirectionM5589boximpl;
            textIndent2 = textIndent3;
            platformTextStyle2 = platformTextStyle3;
            lineHeightStyle2 = lineHeightStyle3;
            shadow2 = shadow3;
            j14 = jM5152getFontSizeXSAIIZE;
            fontWeight2 = fontWeight3;
            fontStyle2 = fontStyleM5153getFontStyle4Lr2A7w;
            fontSynthesis2 = fontSynthesisM5154getFontSynthesisZQGJjVo;
            str2 = fontFeatureSettings;
            j15 = jM5155getLetterSpacingXSAIIZE;
            baselineShift2 = baselineShiftM5150getBaselineShift5SSeXJ0;
            textGeometricTransform2 = textGeometricTransform3;
            localeList2 = localeList3;
            j16 = jM5149getBackground0d7_KjU;
            j17 = jM5105getLineHeightXSAIIZE;
            brush2 = brush;
            f11 = f12;
            lineBreak2 = lineBreakM5495boximpl;
            textStyle2 = textStyle;
            fontFamily2 = fontFamily3;
        }
        return textStyle2.m5217copyaIRg9q4(brush2, f11, j14, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, str2, j15, baselineShift2, textGeometricTransform2, localeList2, j16, textDecoration2, shadow2, drawStyle2, textAlign2, textDirection2, j17, textIndent2, platformTextStyle2, lineHeightStyle2, lineBreak2, hyphens2, textMotion2);
    }

    /* JADX INFO: renamed from: copy-p1EtxEg$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5205copyp1EtxEg$default(TextStyle textStyle, long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, int i14, Object obj) {
        TextMotion textMotion2;
        int i15;
        long jM5151getColor0d7_KjU = (i14 & 1) != 0 ? textStyle.spanStyle.m5151getColor0d7_KjU() : j10;
        long jM5152getFontSizeXSAIIZE = (i14 & 2) != 0 ? textStyle.spanStyle.getFontSize() : j11;
        FontWeight fontWeight2 = (i14 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight;
        FontStyle fontStyleM5153getFontStyle4Lr2A7w = (i14 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle;
        FontSynthesis fontSynthesisM5154getFontSynthesisZQGJjVo = (i14 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis;
        FontFamily fontFamily2 = (i14 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily;
        String fontFeatureSettings = (i14 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str;
        long jM5155getLetterSpacingXSAIIZE = (i14 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : j12;
        BaselineShift baselineShiftM5150getBaselineShift5SSeXJ0 = (i14 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i14 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform;
        LocaleList localeList2 = (i14 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : localeList;
        long j15 = jM5151getColor0d7_KjU;
        long jM5149getBackground0d7_KjU = (i14 & 2048) != 0 ? textStyle.spanStyle.getBackground() : j13;
        TextDecoration textDecoration2 = (i14 & 4096) != 0 ? textStyle.spanStyle.getBackground() : textDecoration;
        Shadow shadow2 = (i14 & 8192) != 0 ? textStyle.spanStyle.getShadow() : shadow;
        TextDecoration textDecoration3 = textDecoration2;
        DrawStyle drawStyle2 = (i14 & 16384) != 0 ? textStyle.spanStyle.getDrawStyle() : drawStyle;
        int iM5107getTextAligne0LSkKk = (i14 & 32768) != 0 ? textStyle.paragraphStyle.getTextAlign() : i10;
        int iM5109getTextDirections_7Xco = (i14 & 65536) != 0 ? textStyle.paragraphStyle.getTextDirection() : i11;
        long jM5105getLineHeightXSAIIZE = (i14 & 131072) != 0 ? textStyle.paragraphStyle.getLineHeight() : j14;
        TextIndent textIndent2 = (i14 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent;
        PlatformTextStyle platformTextStyle2 = (i14 & 524288) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i14 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : lineHeightStyle;
        int iM5104getLineBreakrAG3T2k = (i14 & 2097152) != 0 ? textStyle.paragraphStyle.getLineBreak() : i12;
        int iM5102getHyphensvmbZdU8 = (i14 & 4194304) != 0 ? textStyle.paragraphStyle.getHyphens() : i13;
        if ((i14 & 8388608) != 0) {
            i15 = iM5102getHyphensvmbZdU8;
            textMotion2 = textStyle.paragraphStyle.getTextMotion();
        } else {
            textMotion2 = textMotion;
            i15 = iM5102getHyphensvmbZdU8;
        }
        return textStyle.m5218copyp1EtxEg(j15, jM5152getFontSizeXSAIIZE, fontWeight2, fontStyleM5153getFontStyle4Lr2A7w, fontSynthesisM5154getFontSynthesisZQGJjVo, fontFamily2, fontFeatureSettings, jM5155getLetterSpacingXSAIIZE, baselineShiftM5150getBaselineShift5SSeXJ0, textGeometricTransform2, localeList2, jM5149getBackground0d7_KjU, textDecoration3, shadow2, drawStyle2, iM5107getTextAligne0LSkKk, iM5109getTextDirections_7Xco, jM5105getLineHeightXSAIIZE, textIndent2, platformTextStyle2, lineHeightStyle2, iM5104getLineBreakrAG3T2k, i15, textMotion2);
    }

    /* JADX INFO: renamed from: copy-v2rsoow$default, reason: not valid java name */
    public static /* synthetic */ TextStyle m5206copyv2rsoow$default(TextStyle textStyle, long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, int i10, Object obj) {
        TextMotion textMotion2;
        Hyphens hyphens2;
        long jM5151getColor0d7_KjU = (i10 & 1) != 0 ? textStyle.spanStyle.m5151getColor0d7_KjU() : j10;
        long jM5152getFontSizeXSAIIZE = (i10 & 2) != 0 ? textStyle.spanStyle.getFontSize() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : fontWeight;
        FontStyle fontStyleM5153getFontStyle4Lr2A7w = (i10 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : fontStyle;
        FontSynthesis fontSynthesisM5154getFontSynthesisZQGJjVo = (i10 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily;
        String fontFeatureSettings = (i10 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : str;
        long jM5155getLetterSpacingXSAIIZE = (i10 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : j12;
        BaselineShift baselineShiftM5150getBaselineShift5SSeXJ0 = (i10 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : localeList;
        long j15 = jM5151getColor0d7_KjU;
        long jM5149getBackground0d7_KjU = (i10 & 2048) != 0 ? textStyle.spanStyle.getBackground() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? textStyle.spanStyle.getBackground() : textDecoration;
        Shadow shadow2 = (i10 & 8192) != 0 ? textStyle.spanStyle.getShadow() : shadow;
        TextDecoration textDecoration3 = textDecoration2;
        DrawStyle drawStyle2 = (i10 & 16384) != 0 ? textStyle.spanStyle.getDrawStyle() : drawStyle;
        TextAlign textAlignM5575boximpl = (i10 & 32768) != 0 ? TextAlign.m5575boximpl(textStyle.paragraphStyle.getTextAlign()) : textAlign;
        TextDirection textDirectionM5589boximpl = (i10 & 65536) != 0 ? TextDirection.m5589boximpl(textStyle.paragraphStyle.getTextDirection()) : textDirection;
        long jM5105getLineHeightXSAIIZE = (i10 & 131072) != 0 ? textStyle.paragraphStyle.getLineHeight() : j14;
        TextIndent textIndent2 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textStyle.paragraphStyle.getTextIndent() : textIndent;
        PlatformTextStyle platformTextStyle2 = (i10 & 524288) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : lineHeightStyle;
        LineBreak lineBreakM5495boximpl = (i10 & 2097152) != 0 ? LineBreak.m5495boximpl(textStyle.paragraphStyle.getLineBreak()) : lineBreak;
        Hyphens hyphensM5485boximpl = (i10 & 4194304) != 0 ? Hyphens.m5485boximpl(textStyle.paragraphStyle.getHyphens()) : hyphens;
        if ((i10 & 8388608) != 0) {
            hyphens2 = hyphensM5485boximpl;
            textMotion2 = textStyle.paragraphStyle.getTextMotion();
        } else {
            textMotion2 = textMotion;
            hyphens2 = hyphensM5485boximpl;
        }
        return textStyle.m5219copyv2rsoow(j15, jM5152getFontSizeXSAIIZE, fontWeight2, fontStyleM5153getFontStyle4Lr2A7w, fontSynthesisM5154getFontSynthesisZQGJjVo, fontFamily2, fontFeatureSettings, jM5155getLetterSpacingXSAIIZE, baselineShiftM5150getBaselineShift5SSeXJ0, textGeometricTransform2, localeList2, jM5149getBackground0d7_KjU, textDecoration3, shadow2, drawStyle2, textAlignM5575boximpl, textDirectionM5589boximpl, jM5105getLineHeightXSAIIZE, textIndent2, platformTextStyle2, lineHeightStyle2, lineBreakM5495boximpl, hyphens2, textMotion2);
    }

    @e
    /* JADX INFO: renamed from: getHyphens-EaSxIns$annotations, reason: not valid java name */
    public static /* synthetic */ void m5207getHyphensEaSxIns$annotations() {
    }

    @e
    /* JADX INFO: renamed from: getLineBreak-LgCVezo$annotations, reason: not valid java name */
    public static /* synthetic */ void m5208getLineBreakLgCVezo$annotations() {
    }

    @e
    /* JADX INFO: renamed from: getTextAlign-buA522U$annotations, reason: not valid java name */
    public static /* synthetic */ void m5209getTextAlignbuA522U$annotations() {
    }

    @e
    /* JADX INFO: renamed from: getTextDirection-mmuk1to$annotations, reason: not valid java name */
    public static /* synthetic */ void m5210getTextDirectionmmuk1to$annotations() {
    }

    public static /* synthetic */ TextStyle merge$default(TextStyle textStyle, TextStyle textStyle2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textStyle2 = null;
        }
        return textStyle.merge(textStyle2);
    }

    @e
    /* JADX INFO: renamed from: copy-CXVQc50, reason: not valid java name */
    public final /* synthetic */ TextStyle m5213copyCXVQc50(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformTextStyle platformStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens) {
        return new TextStyle(new SpanStyle(Color.m3473equalsimpl0(color, this.spanStyle.m5151getColor0d7_KjU()) ? this.spanStyle.getTextForegroundStyle() : TextForegroundStyle.INSTANCE.m5603from8_81llA(color), fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, getDrawStyle(), (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), getTextMotion(), (h) null), platformStyle);
    }

    @e
    /* JADX INFO: renamed from: copy-HL5avdY, reason: not valid java name */
    public final /* synthetic */ TextStyle m5214copyHL5avdY(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent) {
        return new TextStyle(new SpanStyle(Color.m3473equalsimpl0(color, this.spanStyle.m5151getColor0d7_KjU()) ? this.spanStyle.getTextForegroundStyle() : TextForegroundStyle.INSTANCE.m5603from8_81llA(color), fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, this.spanStyle.getPlatformStyle(), this.spanStyle.getDrawStyle(), (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, this.paragraphStyle.getPlatformStyle(), getLineHeightStyle(), m5230getLineBreakrAG3T2k(), m5227getHyphensvmbZdU8(), getTextMotion(), (h) null), this.platformStyle);
    }

    @e
    /* JADX INFO: renamed from: copy-NOaFTUo, reason: not valid java name */
    public final /* synthetic */ TextStyle m5215copyNOaFTUo(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformTextStyle platformStyle, LineHeightStyle lineHeightStyle) {
        return new TextStyle(new SpanStyle(Color.m3473equalsimpl0(color, this.spanStyle.m5151getColor0d7_KjU()) ? this.spanStyle.getTextForegroundStyle() : TextForegroundStyle.INSTANCE.m5603from8_81llA(color), fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, this.spanStyle.getDrawStyle(), (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, m5230getLineBreakrAG3T2k(), m5227getHyphensvmbZdU8(), getTextMotion(), (h) null), platformStyle);
    }

    /* JADX INFO: renamed from: copy-Ns73l9s, reason: not valid java name */
    public final TextStyle m5216copyNs73l9s(Brush brush, float alpha, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int textAlign, int textDirection, long lineHeight, TextIndent textIndent, PlatformTextStyle platformStyle, LineHeightStyle lineHeightStyle, int lineBreak, int hyphens, TextMotion textMotion) {
        return new TextStyle(new SpanStyle(brush, alpha, fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak, hyphens, textMotion, (h) null), platformStyle);
    }

    @e
    /* JADX INFO: renamed from: copy-aIRg9q4, reason: not valid java name */
    public final /* synthetic */ TextStyle m5217copyaIRg9q4(Brush brush, float alpha, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformTextStyle platformStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        return new TextStyle(new SpanStyle(brush, alpha, fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion, (h) null), platformStyle);
    }

    /* JADX INFO: renamed from: copy-p1EtxEg, reason: not valid java name */
    public final TextStyle m5218copyp1EtxEg(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int textAlign, int textDirection, long lineHeight, TextIndent textIndent, PlatformTextStyle platformStyle, LineHeightStyle lineHeightStyle, int lineBreak, int hyphens, TextMotion textMotion) {
        return new TextStyle(new SpanStyle(Color.m3473equalsimpl0(color, this.spanStyle.m5151getColor0d7_KjU()) ? this.spanStyle.getTextForegroundStyle() : TextForegroundStyle.INSTANCE.m5603from8_81llA(color), fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak, hyphens, textMotion, (h) null), platformStyle);
    }

    @e
    /* JADX INFO: renamed from: copy-v2rsoow, reason: not valid java name */
    public final /* synthetic */ TextStyle m5219copyv2rsoow(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformTextStyle platformStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        return new TextStyle(new SpanStyle(Color.m3473equalsimpl0(color, this.spanStyle.m5151getColor0d7_KjU()) ? this.spanStyle.getTextForegroundStyle() : TextForegroundStyle.INSTANCE.m5603from8_81llA(color), fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion, (h) null), platformStyle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextStyle)) {
            return false;
        }
        TextStyle textStyle = (TextStyle) other;
        return p.a(this.spanStyle, textStyle.spanStyle) && p.a(this.paragraphStyle, textStyle.paragraphStyle) && p.a(this.platformStyle, textStyle.platformStyle);
    }

    public final float getAlpha() {
        return this.spanStyle.getAlpha();
    }

    /* JADX INFO: renamed from: getBackground-0d7_KjU, reason: not valid java name */
    public final long m5220getBackground0d7_KjU() {
        return this.spanStyle.getBackground();
    }

    /* JADX INFO: renamed from: getBaselineShift-5SSeXJ0, reason: not valid java name */
    public final BaselineShift m5221getBaselineShift5SSeXJ0() {
        return this.spanStyle.getBaselineShift();
    }

    public final Brush getBrush() {
        return this.spanStyle.getBrush();
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m5222getColor0d7_KjU() {
        return this.spanStyle.m5151getColor0d7_KjU();
    }

    public final DrawStyle getDrawStyle() {
        return this.spanStyle.getDrawStyle();
    }

    public final FontFamily getFontFamily() {
        return this.spanStyle.getFontFamily();
    }

    public final String getFontFeatureSettings() {
        return this.spanStyle.getFontFeatureSettings();
    }

    /* JADX INFO: renamed from: getFontSize-XSAIIZE, reason: not valid java name */
    public final long m5223getFontSizeXSAIIZE() {
        return this.spanStyle.getFontSize();
    }

    /* JADX INFO: renamed from: getFontStyle-4Lr2A7w, reason: not valid java name */
    public final FontStyle m5224getFontStyle4Lr2A7w() {
        return this.spanStyle.getFontStyle();
    }

    /* JADX INFO: renamed from: getFontSynthesis-ZQGJjVo, reason: not valid java name */
    public final FontSynthesis m5225getFontSynthesisZQGJjVo() {
        return this.spanStyle.getFontSynthesis();
    }

    public final FontWeight getFontWeight() {
        return this.spanStyle.getFontWeight();
    }

    /* JADX INFO: renamed from: getHyphens-EaSxIns, reason: not valid java name */
    public final Hyphens m5226getHyphensEaSxIns() {
        return Hyphens.m5485boximpl(m5227getHyphensvmbZdU8());
    }

    /* JADX INFO: renamed from: getHyphens-vmbZdU8, reason: not valid java name */
    public final int m5227getHyphensvmbZdU8() {
        return this.paragraphStyle.getHyphens();
    }

    /* JADX INFO: renamed from: getLetterSpacing-XSAIIZE, reason: not valid java name */
    public final long m5228getLetterSpacingXSAIIZE() {
        return this.spanStyle.getLetterSpacing();
    }

    /* JADX INFO: renamed from: getLineBreak-LgCVezo, reason: not valid java name */
    public final LineBreak m5229getLineBreakLgCVezo() {
        return LineBreak.m5495boximpl(m5230getLineBreakrAG3T2k());
    }

    /* JADX INFO: renamed from: getLineBreak-rAG3T2k, reason: not valid java name */
    public final int m5230getLineBreakrAG3T2k() {
        return this.paragraphStyle.getLineBreak();
    }

    /* JADX INFO: renamed from: getLineHeight-XSAIIZE, reason: not valid java name */
    public final long m5231getLineHeightXSAIIZE() {
        return this.paragraphStyle.getLineHeight();
    }

    public final LineHeightStyle getLineHeightStyle() {
        return this.paragraphStyle.getLineHeightStyle();
    }

    public final LocaleList getLocaleList() {
        return this.spanStyle.getLocaleList();
    }

    /* JADX INFO: renamed from: getParagraphStyle$ui_text_release, reason: from getter */
    public final ParagraphStyle getParagraphStyle() {
        return this.paragraphStyle;
    }

    public final PlatformTextStyle getPlatformStyle() {
        return this.platformStyle;
    }

    public final Shadow getShadow() {
        return this.spanStyle.getShadow();
    }

    /* JADX INFO: renamed from: getSpanStyle$ui_text_release, reason: from getter */
    public final SpanStyle getSpanStyle() {
        return this.spanStyle;
    }

    /* JADX INFO: renamed from: getTextAlign-buA522U, reason: not valid java name */
    public final TextAlign m5232getTextAlignbuA522U() {
        return TextAlign.m5575boximpl(m5233getTextAligne0LSkKk());
    }

    /* JADX INFO: renamed from: getTextAlign-e0LSkKk, reason: not valid java name */
    public final int m5233getTextAligne0LSkKk() {
        return this.paragraphStyle.getTextAlign();
    }

    public final TextDecoration getTextDecoration() {
        return this.spanStyle.getBackground();
    }

    /* JADX INFO: renamed from: getTextDirection-mmuk1to, reason: not valid java name */
    public final TextDirection m5234getTextDirectionmmuk1to() {
        return TextDirection.m5589boximpl(m5235getTextDirections_7Xco());
    }

    /* JADX INFO: renamed from: getTextDirection-s_7X-co, reason: not valid java name */
    public final int m5235getTextDirections_7Xco() {
        return this.paragraphStyle.getTextDirection();
    }

    public final TextGeometricTransform getTextGeometricTransform() {
        return this.spanStyle.getTextGeometricTransform();
    }

    public final TextIndent getTextIndent() {
        return this.paragraphStyle.getTextIndent();
    }

    public final TextMotion getTextMotion() {
        return this.paragraphStyle.getTextMotion();
    }

    public final boolean hasSameDrawAffectingAttributes(TextStyle other) {
        return this == other || this.spanStyle.hasSameNonLayoutAttributes$ui_text_release(other.spanStyle);
    }

    public final boolean hasSameLayoutAffectingAttributes(TextStyle other) {
        if (this != other) {
            return p.a(this.paragraphStyle, other.paragraphStyle) && this.spanStyle.hasSameLayoutAffectingAttributes$ui_text_release(other.spanStyle);
        }
        return true;
    }

    public int hashCode() {
        int iHashCode = (this.paragraphStyle.hashCode() + (this.spanStyle.hashCode() * 31)) * 31;
        PlatformTextStyle platformTextStyle = this.platformStyle;
        return iHashCode + (platformTextStyle != null ? platformTextStyle.hashCode() : 0);
    }

    public final int hashCodeLayoutAffectingAttributes$ui_text_release() {
        int iHashCode = (this.paragraphStyle.hashCode() + (this.spanStyle.hashCodeLayoutAffectingAttributes$ui_text_release() * 31)) * 31;
        PlatformTextStyle platformTextStyle = this.platformStyle;
        return iHashCode + (platformTextStyle != null ? platformTextStyle.hashCode() : 0);
    }

    public final TextStyle merge(TextStyle other) {
        return (other == null || other.equals(Default)) ? this : new TextStyle(toSpanStyle().merge(other.toSpanStyle()), toParagraphStyle().merge(other.toParagraphStyle()));
    }

    @e
    /* JADX INFO: renamed from: merge-Z1GrekI, reason: not valid java name */
    public final /* synthetic */ TextStyle m5236mergeZ1GrekI(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, PlatformTextStyle platformStyle, TextMotion textMotion) {
        SpanStyle spanStyleM5156fastMergedSHsh3o = SpanStyleKt.m5156fastMergedSHsh3o(this.spanStyle, color, null, Float.NaN, fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, drawStyle);
        ParagraphStyle paragraphStyleM5110fastMergej5T8yCg = ParagraphStyleKt.m5110fastMergej5T8yCg(this.paragraphStyle, textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion);
        return (this.spanStyle == spanStyleM5156fastMergedSHsh3o && this.paragraphStyle == paragraphStyleM5110fastMergej5T8yCg) ? this : new TextStyle(spanStyleM5156fastMergedSHsh3o, paragraphStyleM5110fastMergej5T8yCg);
    }

    /* JADX INFO: renamed from: merge-dA7vx0o, reason: not valid java name */
    public final TextStyle m5237mergedA7vx0o(long color, long fontSize, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long letterSpacing, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long background, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int textAlign, int textDirection, long lineHeight, TextIndent textIndent, LineHeightStyle lineHeightStyle, int lineBreak, int hyphens, PlatformTextStyle platformStyle, TextMotion textMotion) {
        SpanStyle spanStyleM5156fastMergedSHsh3o = SpanStyleKt.m5156fastMergedSHsh3o(this.spanStyle, color, null, Float.NaN, fontSize, fontWeight, fontStyle, fontSynthesis, fontFamily, fontFeatureSettings, letterSpacing, baselineShift, textGeometricTransform, localeList, background, textDecoration, shadow, platformStyle != null ? platformStyle.getSpanStyle() : null, drawStyle);
        ParagraphStyle paragraphStyleM5110fastMergej5T8yCg = ParagraphStyleKt.m5110fastMergej5T8yCg(this.paragraphStyle, textAlign, textDirection, lineHeight, textIndent, platformStyle != null ? platformStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak, hyphens, textMotion);
        return (this.spanStyle == spanStyleM5156fastMergedSHsh3o && this.paragraphStyle == paragraphStyleM5110fastMergej5T8yCg) ? this : new TextStyle(spanStyleM5156fastMergedSHsh3o, paragraphStyleM5110fastMergej5T8yCg);
    }

    public final TextStyle plus(TextStyle other) {
        return merge(other);
    }

    public final ParagraphStyle toParagraphStyle() {
        return this.paragraphStyle;
    }

    public final SpanStyle toSpanStyle() {
        return this.spanStyle;
    }

    public String toString() {
        return "TextStyle(color=" + ((Object) Color.m3480toStringimpl(m5222getColor0d7_KjU())) + ", brush=" + getBrush() + ", alpha=" + getAlpha() + ", fontSize=" + ((Object) TextUnit.m5872toStringimpl(m5223getFontSizeXSAIIZE())) + ", fontWeight=" + getFontWeight() + ", fontStyle=" + m5224getFontStyle4Lr2A7w() + ", fontSynthesis=" + m5225getFontSynthesisZQGJjVo() + ", fontFamily=" + getFontFamily() + ", fontFeatureSettings=" + getFontFeatureSettings() + ", letterSpacing=" + ((Object) TextUnit.m5872toStringimpl(m5228getLetterSpacingXSAIIZE())) + ", baselineShift=" + m5221getBaselineShift5SSeXJ0() + ", textGeometricTransform=" + getTextGeometricTransform() + ", localeList=" + getLocaleList() + ", background=" + ((Object) Color.m3480toStringimpl(m5220getBackground0d7_KjU())) + ", textDecoration=" + getTextDecoration() + ", shadow=" + getShadow() + ", drawStyle=" + getDrawStyle() + ", textAlign=" + ((Object) TextAlign.m5580toStringimpl(m5233getTextAligne0LSkKk())) + ", textDirection=" + ((Object) TextDirection.m5594toStringimpl(m5235getTextDirections_7Xco())) + ", lineHeight=" + ((Object) TextUnit.m5872toStringimpl(m5231getLineHeightXSAIIZE())) + ", textIndent=" + getTextIndent() + ", platformStyle=" + this.platformStyle + ", lineHeightStyle=" + getLineHeightStyle() + ", lineBreak=" + ((Object) LineBreak.m5506toStringimpl(m5230getLineBreakrAG3T2k())) + ", hyphens=" + ((Object) Hyphens.m5490toStringimpl(m5227getHyphensvmbZdU8())) + ", textMotion=" + getTextMotion() + ')';
    }

    @e
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, h hVar) {
        this(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, drawStyle, textAlign, textDirection, j14, textIndent, platformTextStyle, lineHeightStyle, lineBreak, hyphens, textMotion);
    }

    public final TextStyle plus(ParagraphStyle other) {
        return merge(other);
    }

    @e
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, h hVar) {
        this(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, textAlign, textDirection, j14, textIndent, platformTextStyle, lineHeightStyle, lineBreak, hyphens);
    }

    public final TextStyle plus(SpanStyle other) {
        return merge(other);
    }

    @e
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, h hVar) {
        this(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, textAlign, textDirection, j14, textIndent, platformTextStyle, lineHeightStyle);
    }

    @e
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, h hVar) {
        this(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, textAlign, textDirection, j14, textIndent);
    }

    public /* synthetic */ TextStyle(Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, h hVar) {
        this(brush, f10, j10, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j11, baselineShift, textGeometricTransform, localeList, j12, textDecoration, shadow, drawStyle, i10, i11, j13, textIndent, platformTextStyle, lineHeightStyle, i12, i13, textMotion);
    }

    @e
    public /* synthetic */ TextStyle(Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, h hVar) {
        this(brush, f10, j10, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j11, baselineShift, textGeometricTransform, localeList, j12, textDecoration, shadow, drawStyle, textAlign, textDirection, j13, textIndent, platformTextStyle, lineHeightStyle, lineBreak, hyphens, textMotion);
    }

    public final TextStyle merge(SpanStyle other) {
        return new TextStyle(toSpanStyle().merge(other), toParagraphStyle());
    }

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle, PlatformTextStyle platformTextStyle) {
        this.spanStyle = spanStyle;
        this.paragraphStyle = paragraphStyle;
        this.platformStyle = platformTextStyle;
    }

    public final TextStyle merge(ParagraphStyle other) {
        return new TextStyle(toSpanStyle(), toParagraphStyle().merge(other));
    }

    public /* synthetic */ TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle, PlatformTextStyle platformTextStyle, int i10, h hVar) {
        this(spanStyle, paragraphStyle, (i10 & 4) != 0 ? null : platformTextStyle);
    }

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle) {
        this(spanStyle, paragraphStyle, TextStyleKt.createPlatformTextStyleInternal(spanStyle.getPlatformStyle(), paragraphStyle.getPlatformStyle()));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, int i10, h hVar) {
        long jM3508getUnspecified0d7_KjU = (i10 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM5876getUnspecifiedXSAIIZE = (i10 & 2) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? null : fontWeight;
        FontStyle fontStyle2 = (i10 & 8) != 0 ? null : fontStyle;
        FontSynthesis fontSynthesis2 = (i10 & 16) != 0 ? null : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? null : fontFamily;
        String str2 = (i10 & 64) != 0 ? null : str;
        long jM5876getUnspecifiedXSAIIZE2 = (i10 & 128) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j12;
        BaselineShift baselineShift2 = (i10 & 256) != 0 ? null : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? null : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? null : localeList;
        long jM3508getUnspecified0d7_KjU2 = (i10 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? null : textDecoration;
        long j15 = jM3508getUnspecified0d7_KjU;
        Shadow shadow2 = (i10 & 8192) != 0 ? null : shadow;
        TextAlign textAlign2 = (i10 & 16384) != 0 ? null : textAlign;
        TextDirection textDirection2 = (i10 & 32768) != 0 ? null : textDirection;
        long jM5876getUnspecifiedXSAIIZE3 = (i10 & 65536) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j14;
        long j16 = jM5876getUnspecifiedXSAIIZE;
        FontWeight fontWeight3 = fontWeight2;
        TextDecoration textDecoration3 = textDecoration2;
        FontStyle fontStyle3 = fontStyle2;
        FontSynthesis fontSynthesis3 = fontSynthesis2;
        FontFamily fontFamily3 = fontFamily2;
        String str3 = str2;
        long j17 = jM5876getUnspecifiedXSAIIZE2;
        BaselineShift baselineShift3 = baselineShift2;
        TextGeometricTransform textGeometricTransform3 = textGeometricTransform2;
        LocaleList localeList3 = localeList2;
        long j18 = jM3508getUnspecified0d7_KjU2;
        this(j15, j16, fontWeight3, fontStyle3, fontSynthesis3, fontFamily3, str3, j17, baselineShift3, textGeometricTransform3, localeList3, j18, textDecoration3, shadow2, textAlign2, textDirection2, jM5876getUnspecifiedXSAIIZE3, (i10 & 131072) != 0 ? null : textIndent, null);
    }

    private TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent) {
        SpanStyle spanStyle = new SpanStyle(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, (PlatformSpanStyle) null, (DrawStyle) null, (h) null);
        this(spanStyle, new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j14, textIndent, (PlatformParagraphStyle) null, (LineHeightStyle) null, LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), (TextMotion) null, (h) null), null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i10, h hVar) {
        long jM3508getUnspecified0d7_KjU = (i10 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM5876getUnspecifiedXSAIIZE = (i10 & 2) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? null : fontWeight;
        FontStyle fontStyle2 = (i10 & 8) != 0 ? null : fontStyle;
        FontSynthesis fontSynthesis2 = (i10 & 16) != 0 ? null : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? null : fontFamily;
        String str2 = (i10 & 64) != 0 ? null : str;
        long jM5876getUnspecifiedXSAIIZE2 = (i10 & 128) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j12;
        BaselineShift baselineShift2 = (i10 & 256) != 0 ? null : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? null : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? null : localeList;
        long jM3508getUnspecified0d7_KjU2 = (i10 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? null : textDecoration;
        long j15 = jM3508getUnspecified0d7_KjU;
        Shadow shadow2 = (i10 & 8192) != 0 ? null : shadow;
        TextAlign textAlign2 = (i10 & 16384) != 0 ? null : textAlign;
        TextDirection textDirection2 = (i10 & 32768) != 0 ? null : textDirection;
        long jM5876getUnspecifiedXSAIIZE3 = (i10 & 65536) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j14;
        TextIndent textIndent2 = (i10 & 131072) != 0 ? null : textIndent;
        PlatformTextStyle platformTextStyle2 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : platformTextStyle;
        long j16 = jM5876getUnspecifiedXSAIIZE;
        FontWeight fontWeight3 = fontWeight2;
        TextDecoration textDecoration3 = textDecoration2;
        FontStyle fontStyle3 = fontStyle2;
        FontSynthesis fontSynthesis3 = fontSynthesis2;
        FontFamily fontFamily3 = fontFamily2;
        String str3 = str2;
        long j17 = jM5876getUnspecifiedXSAIIZE2;
        BaselineShift baselineShift3 = baselineShift2;
        TextGeometricTransform textGeometricTransform3 = textGeometricTransform2;
        LocaleList localeList3 = localeList2;
        long j18 = jM3508getUnspecified0d7_KjU2;
        this(j15, j16, fontWeight3, fontStyle3, fontSynthesis3, fontFamily3, str3, j17, baselineShift3, textGeometricTransform3, localeList3, j18, textDecoration3, shadow2, textAlign2, textDirection2, jM5876getUnspecifiedXSAIIZE3, textIndent2, platformTextStyle2, (i10 & 524288) != 0 ? null : lineHeightStyle, null);
    }

    private TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle) {
        this(new SpanStyle(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, (DrawStyle) null, (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j14, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), (TextMotion) null, (h) null), platformTextStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, int i10, h hVar) {
        long jM3508getUnspecified0d7_KjU = (i10 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM5876getUnspecifiedXSAIIZE = (i10 & 2) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? null : fontWeight;
        FontStyle fontStyle2 = (i10 & 8) != 0 ? null : fontStyle;
        FontSynthesis fontSynthesis2 = (i10 & 16) != 0 ? null : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? null : fontFamily;
        String str2 = (i10 & 64) != 0 ? null : str;
        long jM5876getUnspecifiedXSAIIZE2 = (i10 & 128) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j12;
        BaselineShift baselineShift2 = (i10 & 256) != 0 ? null : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? null : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? null : localeList;
        long jM3508getUnspecified0d7_KjU2 = (i10 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? null : textDecoration;
        long j15 = jM3508getUnspecified0d7_KjU;
        Shadow shadow2 = (i10 & 8192) != 0 ? null : shadow;
        TextAlign textAlign2 = (i10 & 16384) != 0 ? null : textAlign;
        TextDirection textDirection2 = (i10 & 32768) != 0 ? null : textDirection;
        long jM5876getUnspecifiedXSAIIZE3 = (i10 & 65536) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j14;
        TextIndent textIndent2 = (i10 & 131072) != 0 ? null : textIndent;
        PlatformTextStyle platformTextStyle2 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i10 & 524288) != 0 ? null : lineHeightStyle;
        LineBreak lineBreak2 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? null : lineBreak;
        long j16 = jM5876getUnspecifiedXSAIIZE;
        FontWeight fontWeight3 = fontWeight2;
        TextDecoration textDecoration3 = textDecoration2;
        FontStyle fontStyle3 = fontStyle2;
        FontSynthesis fontSynthesis3 = fontSynthesis2;
        FontFamily fontFamily3 = fontFamily2;
        String str3 = str2;
        long j17 = jM5876getUnspecifiedXSAIIZE2;
        BaselineShift baselineShift3 = baselineShift2;
        TextGeometricTransform textGeometricTransform3 = textGeometricTransform2;
        LocaleList localeList3 = localeList2;
        long j18 = jM3508getUnspecified0d7_KjU2;
        this(j15, j16, fontWeight3, fontStyle3, fontSynthesis3, fontFamily3, str3, j17, baselineShift3, textGeometricTransform3, localeList3, j18, textDecoration3, shadow2, textAlign2, textDirection2, jM5876getUnspecifiedXSAIIZE3, textIndent2, platformTextStyle2, lineHeightStyle2, lineBreak2, (i10 & 2097152) != 0 ? null : hyphens, null);
    }

    private TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens) {
        SpanStyle spanStyle = new SpanStyle(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, (DrawStyle) null, 32768, (h) null);
        int value = textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk();
        int value2 = textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco();
        PlatformParagraphStyle paragraphStyle = platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null;
        this(spanStyle, new ParagraphStyle(value, value2, j14, textIndent, paragraphStyle, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), (TextMotion) null, 256, (h) null), platformTextStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, int i10, h hVar) {
        long jM3508getUnspecified0d7_KjU = (i10 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM5876getUnspecifiedXSAIIZE = (i10 & 2) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11;
        FontWeight fontWeight2 = (i10 & 4) != 0 ? null : fontWeight;
        FontStyle fontStyle2 = (i10 & 8) != 0 ? null : fontStyle;
        FontSynthesis fontSynthesis2 = (i10 & 16) != 0 ? null : fontSynthesis;
        FontFamily fontFamily2 = (i10 & 32) != 0 ? null : fontFamily;
        String str2 = (i10 & 64) != 0 ? null : str;
        long jM5876getUnspecifiedXSAIIZE2 = (i10 & 128) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j12;
        BaselineShift baselineShift2 = (i10 & 256) != 0 ? null : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i10 & 512) != 0 ? null : textGeometricTransform;
        LocaleList localeList2 = (i10 & 1024) != 0 ? null : localeList;
        long jM3508getUnspecified0d7_KjU2 = (i10 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        TextDecoration textDecoration2 = (i10 & 4096) != 0 ? null : textDecoration;
        long j15 = jM3508getUnspecified0d7_KjU;
        Shadow shadow2 = (i10 & 8192) != 0 ? null : shadow;
        DrawStyle drawStyle2 = (i10 & 16384) != 0 ? null : drawStyle;
        TextAlign textAlign2 = (i10 & 32768) != 0 ? null : textAlign;
        TextDirection textDirection2 = (i10 & 65536) != 0 ? null : textDirection;
        long jM5876getUnspecifiedXSAIIZE3 = (i10 & 131072) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j14;
        TextIndent textIndent2 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : textIndent;
        PlatformTextStyle platformTextStyle2 = (i10 & 524288) != 0 ? null : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? null : lineHeightStyle;
        LineBreak lineBreak2 = (i10 & 2097152) != 0 ? null : lineBreak;
        Hyphens hyphens2 = (i10 & 4194304) != 0 ? null : hyphens;
        long j16 = jM5876getUnspecifiedXSAIIZE;
        FontWeight fontWeight3 = fontWeight2;
        TextDecoration textDecoration3 = textDecoration2;
        FontStyle fontStyle3 = fontStyle2;
        FontSynthesis fontSynthesis3 = fontSynthesis2;
        FontFamily fontFamily3 = fontFamily2;
        String str3 = str2;
        long j17 = jM5876getUnspecifiedXSAIIZE2;
        BaselineShift baselineShift3 = baselineShift2;
        TextGeometricTransform textGeometricTransform3 = textGeometricTransform2;
        LocaleList localeList3 = localeList2;
        long j18 = jM3508getUnspecified0d7_KjU2;
        this(j15, j16, fontWeight3, fontStyle3, fontSynthesis3, fontFamily3, str3, j17, baselineShift3, textGeometricTransform3, localeList3, j18, textDecoration3, shadow2, drawStyle2, textAlign2, textDirection2, jM5876getUnspecifiedXSAIIZE3, textIndent2, platformTextStyle2, lineHeightStyle2, lineBreak2, hyphens2, (i10 & 8388608) != 0 ? null : textMotion, (h) null);
    }

    private TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        this(new SpanStyle(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j14, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion, (h) null), platformTextStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, int i14, h hVar) {
        long jM3508getUnspecified0d7_KjU = (i14 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM5876getUnspecifiedXSAIIZE = (i14 & 2) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11;
        FontWeight fontWeight2 = (i14 & 4) != 0 ? null : fontWeight;
        FontStyle fontStyle2 = (i14 & 8) != 0 ? null : fontStyle;
        FontSynthesis fontSynthesis2 = (i14 & 16) != 0 ? null : fontSynthesis;
        FontFamily fontFamily2 = (i14 & 32) != 0 ? null : fontFamily;
        String str2 = (i14 & 64) != 0 ? null : str;
        long jM5876getUnspecifiedXSAIIZE2 = (i14 & 128) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j12;
        BaselineShift baselineShift2 = (i14 & 256) != 0 ? null : baselineShift;
        TextGeometricTransform textGeometricTransform2 = (i14 & 512) != 0 ? null : textGeometricTransform;
        LocaleList localeList2 = (i14 & 1024) != 0 ? null : localeList;
        long jM3508getUnspecified0d7_KjU2 = (i14 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        TextDecoration textDecoration2 = (i14 & 4096) != 0 ? null : textDecoration;
        long j15 = jM3508getUnspecified0d7_KjU;
        Shadow shadow2 = (i14 & 8192) != 0 ? null : shadow;
        DrawStyle drawStyle2 = (i14 & 16384) != 0 ? null : drawStyle;
        int iM5588getUnspecifiede0LSkKk = (i14 & 32768) != 0 ? TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk() : i10;
        int iM5601getUnspecifieds_7Xco = (i14 & 65536) != 0 ? TextDirection.INSTANCE.m5601getUnspecifieds_7Xco() : i11;
        long jM5876getUnspecifiedXSAIIZE3 = (i14 & 131072) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j14;
        TextIndent textIndent2 = (i14 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : textIndent;
        PlatformTextStyle platformTextStyle2 = (i14 & 524288) != 0 ? null : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i14 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? null : lineHeightStyle;
        int iM5515getUnspecifiedrAG3T2k = (i14 & 2097152) != 0 ? LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k() : i12;
        int iM5494getUnspecifiedvmbZdU8 = (i14 & 4194304) != 0 ? Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8() : i13;
        long j16 = jM5876getUnspecifiedXSAIIZE;
        FontWeight fontWeight3 = fontWeight2;
        TextDecoration textDecoration3 = textDecoration2;
        FontStyle fontStyle3 = fontStyle2;
        FontSynthesis fontSynthesis3 = fontSynthesis2;
        FontFamily fontFamily3 = fontFamily2;
        String str3 = str2;
        long j17 = jM5876getUnspecifiedXSAIIZE2;
        BaselineShift baselineShift3 = baselineShift2;
        TextGeometricTransform textGeometricTransform3 = textGeometricTransform2;
        LocaleList localeList3 = localeList2;
        long j18 = jM3508getUnspecified0d7_KjU2;
        this(j15, j16, fontWeight3, fontStyle3, fontSynthesis3, fontFamily3, str3, j17, baselineShift3, textGeometricTransform3, localeList3, j18, textDecoration3, shadow2, drawStyle2, iM5588getUnspecifiede0LSkKk, iM5601getUnspecifieds_7Xco, jM5876getUnspecifiedXSAIIZE3, textIndent2, platformTextStyle2, lineHeightStyle2, iM5515getUnspecifiedrAG3T2k, iM5494getUnspecifiedvmbZdU8, (i14 & 8388608) != 0 ? null : textMotion, (h) null);
    }

    private TextStyle(long j10, long j11, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j12, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j13, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j14, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion) {
        this(new SpanStyle(j10, j11, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j12, baselineShift, textGeometricTransform, localeList, j13, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(i10, i11, j14, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, i12, i13, textMotion, (h) null), platformTextStyle);
    }

    public /* synthetic */ TextStyle(Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, int i14, h hVar) {
        this(brush, (i14 & 2) != 0 ? Float.NaN : f10, (i14 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10, (i14 & 8) != 0 ? null : fontWeight, (i14 & 16) != 0 ? null : fontStyle, (i14 & 32) != 0 ? null : fontSynthesis, (i14 & 64) != 0 ? null : fontFamily, (i14 & 128) != 0 ? null : str, (i14 & 256) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11, (i14 & 512) != 0 ? null : baselineShift, (i14 & 1024) != 0 ? null : textGeometricTransform, (i14 & 2048) != 0 ? null : localeList, (i14 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12, (i14 & 8192) != 0 ? null : textDecoration, (i14 & 16384) != 0 ? null : shadow, (32768 & i14) != 0 ? null : drawStyle, (65536 & i14) != 0 ? TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk() : i10, (131072 & i14) != 0 ? TextDirection.INSTANCE.m5601getUnspecifieds_7Xco() : i11, (262144 & i14) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j13, (524288 & i14) != 0 ? null : textIndent, (1048576 & i14) != 0 ? null : platformTextStyle, (2097152 & i14) != 0 ? null : lineHeightStyle, (4194304 & i14) != 0 ? LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k() : i12, (8388608 & i14) != 0 ? Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8() : i13, (i14 & 16777216) != 0 ? null : textMotion, (h) null);
    }

    private TextStyle(Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i10, int i11, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion) {
        this(new SpanStyle(brush, f10, j10, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j11, baselineShift, textGeometricTransform, localeList, j12, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(i10, i11, j13, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, i12, i13, textMotion, (h) null), platformTextStyle);
    }

    public /* synthetic */ TextStyle(Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, int i10, h hVar) {
        this(brush, (i10 & 2) != 0 ? Float.NaN : f10, (i10 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10, (i10 & 8) != 0 ? null : fontWeight, (i10 & 16) != 0 ? null : fontStyle, (i10 & 32) != 0 ? null : fontSynthesis, (i10 & 64) != 0 ? null : fontFamily, (i10 & 128) != 0 ? null : str, (i10 & 256) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j11, (i10 & 512) != 0 ? null : baselineShift, (i10 & 1024) != 0 ? null : textGeometricTransform, (i10 & 2048) != 0 ? null : localeList, (i10 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12, (i10 & 8192) != 0 ? null : textDecoration, (i10 & 16384) != 0 ? null : shadow, (32768 & i10) != 0 ? null : drawStyle, (65536 & i10) != 0 ? null : textAlign, (131072 & i10) != 0 ? null : textDirection, (262144 & i10) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j13, (524288 & i10) != 0 ? null : textIndent, (1048576 & i10) != 0 ? null : platformTextStyle, (2097152 & i10) != 0 ? null : lineHeightStyle, (4194304 & i10) != 0 ? null : lineBreak, (8388608 & i10) != 0 ? null : hyphens, (i10 & 16777216) != 0 ? null : textMotion, (h) null);
    }

    private TextStyle(Brush brush, float f10, long j10, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j11, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j12, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, TextAlign textAlign, TextDirection textDirection, long j13, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        this(new SpanStyle(brush, f10, j10, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j11, baselineShift, textGeometricTransform, localeList, j12, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle, (h) null), new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j13, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion, (h) null), platformTextStyle);
    }
}
