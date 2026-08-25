package androidx.compose.ui.text.android;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\r\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000f\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0005H\u0017¢\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0005H\u0017¢\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0019\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001d\u0010\u0017J\u000f\u0010\u001e\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001e\u0010\u0017J\u000f\u0010\u001f\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001f\u0010\u0017J+\u0010$\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010#\u001a\u00020\u0015H\u0017¢\u0006\u0004\b$\u0010%J#\u0010$\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b$\u0010&JA\u0010$\u001a\u00020\u00152\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\b\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010#\u001a\u00020\u0015H\u0017¢\u0006\u0004\b$\u0010,J9\u0010$\u001a\u00020\u00152\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b$\u0010-J)\u0010/\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010 2\u0006\u0010.\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0015H\u0017¢\u0006\u0004\b/\u00100J!\u0010/\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010 2\u0006\u0010.\u001a\u00020\u0015H\u0016¢\u0006\u0004\b/\u00101J?\u0010/\u001a\u00020\u00152\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010.\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0015H\u0017¢\u0006\u0004\b/\u00102J7\u0010/\u001a\u00020\u00152\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0015H\u0016¢\u0006\u0004\b/\u00103J\u000f\u00104\u001a\u00020\u0005H\u0016¢\u0006\u0004\b4\u0010\u0003J\u000f\u00105\u001a\u00020\u0015H\u0016¢\u0006\u0004\b5\u0010\u0017J\u0017\u00107\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u0015H\u0016¢\u0006\u0004\b7\u0010\u001cJ\u001f\u0010:\u001a\u00020\u00052\u0006\u00108\u001a\u00020'2\u0006\u00109\u001a\u00020'H\u0016¢\u0006\u0004\b:\u0010;J\u001f\u0010>\u001a\u00020\u00052\u0006\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020'H\u0016¢\u0006\u0004\b>\u0010;J\u0017\u0010@\u001a\u00020\u00052\u0006\u0010?\u001a\u00020'H\u0016¢\u0006\u0004\b@\u0010AJ\u001f\u0010B\u001a\u00020\u00052\u0006\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020'H\u0016¢\u0006\u0004\bB\u0010;J\u0019\u0010E\u001a\u00020\u00052\b\u0010D\u001a\u0004\u0018\u00010CH\u0016¢\u0006\u0004\bE\u0010FJ\u0019\u0010G\u001a\u00020\u00052\b\u0010D\u001a\u0004\u0018\u00010CH\u0016¢\u0006\u0004\bG\u0010FJ\u0017\u0010I\u001a\u00020\u00052\u0006\u0010H\u001a\u00020CH\u0017¢\u0006\u0004\bI\u0010FJ\u001f\u0010M\u001a\u00020\n2\u0006\u0010J\u001a\u00020 2\u0006\u0010L\u001a\u00020KH\u0017¢\u0006\u0004\bM\u0010NJ\u001f\u0010M\u001a\u00020\n2\u0006\u0010J\u001a\u00020\b2\u0006\u0010L\u001a\u00020KH\u0017¢\u0006\u0004\bM\u0010OJ\u0017\u0010M\u001a\u00020\n2\u0006\u0010J\u001a\u00020 H\u0016¢\u0006\u0004\bM\u0010PJ\u0017\u0010M\u001a\u00020\n2\u0006\u0010J\u001a\u00020\bH\u0016¢\u0006\u0004\bM\u0010\fJ7\u0010M\u001a\u00020\n2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010L\u001a\u00020KH\u0017¢\u0006\u0004\bM\u0010QJ/\u0010M\u001a\u00020\n2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0016¢\u0006\u0004\bM\u0010RJ/\u0010M\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015H\u0016¢\u0006\u0004\bM\u0010SJ\u0017\u0010T\u001a\u00020\n2\u0006\u0010J\u001a\u00020 H\u0017¢\u0006\u0004\bT\u0010PJ\u0017\u0010T\u001a\u00020\n2\u0006\u0010J\u001a\u00020\bH\u0017¢\u0006\u0004\bT\u0010\fJ/\u0010T\u001a\u00020\n2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0017¢\u0006\u0004\bT\u0010RJ/\u0010T\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015H\u0017¢\u0006\u0004\bT\u0010SJ\u001f\u0010W\u001a\u00020\n2\u0006\u0010V\u001a\u00020U2\u0006\u0010L\u001a\u00020KH\u0017¢\u0006\u0004\bW\u0010XJ\u0017\u0010W\u001a\u00020\n2\u0006\u0010V\u001a\u00020UH\u0016¢\u0006\u0004\bW\u0010YJ\u0017\u0010Z\u001a\u00020\n2\u0006\u0010V\u001a\u00020UH\u0017¢\u0006\u0004\bZ\u0010YJ\u0011\u0010\\\u001a\u0004\u0018\u00010[H\u0016¢\u0006\u0004\b\\\u0010]J\u0019\u0010_\u001a\u00020\u00052\b\u0010^\u001a\u0004\u0018\u00010[H\u0016¢\u0006\u0004\b_\u0010`J\u001f\u0010c\u001a\u00020\n2\u0006\u0010J\u001a\u00020 2\u0006\u0010b\u001a\u00020aH\u0017¢\u0006\u0004\bc\u0010dJ\u0017\u0010c\u001a\u00020\n2\u0006\u0010J\u001a\u00020 H\u0017¢\u0006\u0004\bc\u0010PJ\u001f\u0010c\u001a\u00020\n2\u0006\u0010V\u001a\u00020U2\u0006\u0010b\u001a\u00020aH\u0017¢\u0006\u0004\bc\u0010eJ\u0017\u0010c\u001a\u00020\n2\u0006\u0010V\u001a\u00020UH\u0017¢\u0006\u0004\bc\u0010YJ7\u0010c\u001a\u00020\n2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010b\u001a\u00020aH\u0017¢\u0006\u0004\bc\u0010fJ/\u0010c\u001a\u00020\n2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0017¢\u0006\u0004\bc\u0010RJ\u0017\u0010i\u001a\u00020\u00052\u0006\u0010h\u001a\u00020gH\u0016¢\u0006\u0004\bi\u0010jJ\u001f\u0010i\u001a\u00020\u00052\u0006\u0010h\u001a\u00020g2\u0006\u0010k\u001a\u00020 H\u0016¢\u0006\u0004\bi\u0010lJ\u001f\u0010i\u001a\u00020\u00052\u0006\u0010h\u001a\u00020g2\u0006\u0010k\u001a\u00020\bH\u0016¢\u0006\u0004\bi\u0010mJ7\u0010r\u001a\u00020\u00052\u0006\u0010n\u001a\u00020 2\u0006\u0010o\u001a\u00020'2\u0006\u0010p\u001a\u00020'2\u0006\u0010q\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\br\u0010sJO\u0010r\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010o\u001a\u00020'2\u0006\u0010p\u001a\u00020'2\u0006\u0010q\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\br\u0010tJ/\u0010y\u001a\u00020\u00052\u0006\u0010u\u001a\u00020\u00152\u0006\u0010v\u001a\u00020\u00152\u0006\u0010w\u001a\u00020\u00152\u0006\u0010x\u001a\u00020\u0015H\u0016¢\u0006\u0004\by\u0010zJ1\u0010{\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b{\u0010|J3\u0010{\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\u0010}\u001a\u0004\u0018\u00010\b2\u0006\u0010k\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b{\u0010~J3\u0010{\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\u0010}\u001a\u0004\u0018\u00010\b2\u0006\u0010k\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b{\u0010\u007fJc\u0010{\u001a\u00020\u00052\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010\u0083\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0007\u0010\u0086\u0001\u001a\u00020\u00152\u0007\u0010\u0087\u0001\u001a\u00020\u00152\u0007\u0010\u0088\u0001\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0017¢\u0006\u0005\b{\u0010\u0089\u0001Jc\u0010{\u001a\u00020\u00052\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010\u0083\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020\u00152\u0007\u0010\u0085\u0001\u001a\u00020\u00152\u0007\u0010\u0086\u0001\u001a\u00020\u00152\u0007\u0010\u0087\u0001\u001a\u00020\u00152\u0007\u0010\u0088\u0001\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0017¢\u0006\u0005\b{\u0010\u008a\u0001J*\u0010{\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010D\u001a\u00020C2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0005\b{\u0010\u008b\u0001J^\u0010\u0092\u0001\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0007\u0010\u008c\u0001\u001a\u00020\u00152\u0007\u0010\u008d\u0001\u001a\u00020\u00152\b\u0010\u008f\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u00152\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0080\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u00152\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J5\u0010\u0097\u0001\u001a\u00020\u00052\u0007\u0010\u0094\u0001\u001a\u00020'2\u0007\u0010\u0095\u0001\u001a\u00020'2\u0007\u0010\u0096\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J\u001a\u0010\u009a\u0001\u001a\u00020\u00052\u0007\u0010\u0099\u0001\u001a\u00020\u0015H\u0016¢\u0006\u0005\b\u009a\u0001\u0010\u001cJ\u001c\u0010\u009a\u0001\u001a\u00020\u00052\b\u0010\u0099\u0001\u001a\u00030\u009b\u0001H\u0017¢\u0006\u0006\b\u009a\u0001\u0010\u009c\u0001J%\u0010\u009a\u0001\u001a\u00020\u00052\u0007\u0010\u0099\u0001\u001a\u00020\u00152\b\u0010\u009e\u0001\u001a\u00030\u009d\u0001H\u0016¢\u0006\u0006\b\u009a\u0001\u0010\u009f\u0001J%\u0010\u009a\u0001\u001a\u00020\u00052\u0007\u0010\u0099\u0001\u001a\u00020\u00152\b\u0010\u009e\u0001\u001a\u00030 \u0001H\u0017¢\u0006\u0006\b\u009a\u0001\u0010¡\u0001J&\u0010\u009a\u0001\u001a\u00020\u00052\b\u0010\u0099\u0001\u001a\u00030\u009b\u00012\b\u0010\u009e\u0001\u001a\u00030 \u0001H\u0017¢\u0006\u0006\b\u009a\u0001\u0010¢\u0001J>\u0010§\u0001\u001a\u00020\u00052\u0007\u0010£\u0001\u001a\u00020'2\u0007\u0010¤\u0001\u001a\u00020'2\u0007\u0010¥\u0001\u001a\u00020'2\u0007\u0010¦\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b§\u0001\u0010¨\u0001J6\u0010«\u0001\u001a\u00020\u00052\b\u0010©\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010ª\u0001\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b«\u0001\u0010¬\u0001J$\u0010«\u0001\u001a\u00020\u00052\b\u0010©\u0001\u001a\u00030\u008e\u00012\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b«\u0001\u0010\u00ad\u0001J\"\u0010®\u0001\u001a\u00020\u00052\u0006\u0010n\u001a\u00020 2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b®\u0001\u0010¯\u0001J:\u0010®\u0001\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b®\u0001\u0010¨\u0001J\u001a\u0010°\u0001\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b°\u0001\u0010±\u0001J.\u0010´\u0001\u001a\u00020\u00052\b\u0010³\u0001\u001a\u00030²\u00012\u0006\u0010k\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0017¢\u0006\u0006\b´\u0001\u0010µ\u0001J.\u0010´\u0001\u001a\u00020\u00052\b\u0010³\u0001\u001a\u00030²\u00012\u0006\u0010k\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0017¢\u0006\u0006\b´\u0001\u0010¶\u0001J\"\u0010·\u0001\u001a\u00020\u00052\u0006\u0010V\u001a\u00020U2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b·\u0001\u0010¸\u0001J,\u0010¹\u0001\u001a\u00020\u00052\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b¹\u0001\u0010º\u0001J8\u0010»\u0001\u001a\u00020\u00052\n\u0010©\u0001\u001a\u0005\u0018\u00010\u008e\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010ª\u0001\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b»\u0001\u0010¬\u0001J$\u0010»\u0001\u001a\u00020\u00052\b\u0010©\u0001\u001a\u00030\u008e\u00012\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b»\u0001\u0010\u00ad\u0001J@\u0010À\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030¼\u00012\u0007\u0010¾\u0001\u001a\u00020\u00152\u0007\u0010ª\u0001\u001a\u00020\u00152\b\u0010¿\u0001\u001a\u00030\u008e\u00012\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bÀ\u0001\u0010Á\u0001J.\u0010À\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030Â\u00012\b\u0010¿\u0001\u001a\u00030\u008e\u00012\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bÀ\u0001\u0010Ã\u0001J\"\u0010Ä\u0001\u001a\u00020\u00052\u0006\u0010J\u001a\u00020 2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bÄ\u0001\u0010¯\u0001J\"\u0010Ä\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bÄ\u0001\u0010Å\u0001J:\u0010Ä\u0001\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bÄ\u0001\u0010¨\u0001J*\u0010Æ\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u00152\u0006\u0010w\u001a\u00020\u00152\u0006\u0010x\u001a\u00020\u0015H\u0016¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001J4\u0010Ê\u0001\u001a\u00020\u00052\u0006\u0010J\u001a\u00020 2\u0007\u0010È\u0001\u001a\u00020'2\u0007\u0010É\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bÊ\u0001\u0010Ë\u0001JL\u0010Ê\u0001\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0007\u0010È\u0001\u001a\u00020'2\u0007\u0010É\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bÊ\u0001\u0010Ì\u0001JP\u0010Ó\u0001\u001a\u00020\u00052\u0007\u0010Í\u0001\u001a\u00020 2\u0007\u0010Î\u0001\u001a\u00020'2\u0007\u0010Ï\u0001\u001a\u00020'2\u0007\u0010Ð\u0001\u001a\u00020 2\u0007\u0010Ñ\u0001\u001a\u00020'2\u0007\u0010Ò\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J@\u0010Ó\u0001\u001a\u00020\u00052\u0007\u0010Í\u0001\u001a\u00020 2\b\u0010Õ\u0001\u001a\u00030\u008e\u00012\u0007\u0010Ð\u0001\u001a\u00020 2\b\u0010Ö\u0001\u001a\u00030\u008e\u00012\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bÓ\u0001\u0010×\u0001JS\u0010ß\u0001\u001a\u00020\u00052\b\u0010Ø\u0001\u001a\u00030\u0080\u00012\u0007\u0010Ù\u0001\u001a\u00020\u00152\b\u0010Ú\u0001\u001a\u00030\u008e\u00012\u0007\u0010Û\u0001\u001a\u00020\u00152\u0007\u0010Ü\u0001\u001a\u00020\u00152\b\u0010Þ\u0001\u001a\u00030Ý\u00012\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bß\u0001\u0010à\u0001JH\u0010á\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030¼\u00012\u0007\u0010¾\u0001\u001a\u00020\u00152\u0007\u0010ª\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bá\u0001\u0010â\u0001J6\u0010á\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030Â\u00012\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bá\u0001\u0010ã\u0001JH\u0010á\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030Â\u00012\u0007\u0010ä\u0001\u001a\u00020\u00152\u0007\u0010å\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bá\u0001\u0010æ\u0001JH\u0010á\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030ç\u00012\u0007\u0010ä\u0001\u001a\u00020\u00152\u0007\u0010å\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bá\u0001\u0010è\u0001JP\u0010ë\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030¼\u00012\u0007\u0010¾\u0001\u001a\u00020\u00152\u0007\u0010ª\u0001\u001a\u00020\u00152\u0006\u0010V\u001a\u00020U2\u0007\u0010é\u0001\u001a\u00020'2\u0007\u0010ê\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bë\u0001\u0010ì\u0001J>\u0010ë\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030Â\u00012\u0006\u0010V\u001a\u00020U2\u0007\u0010é\u0001\u001a\u00020'2\u0007\u0010ê\u0001\u001a\u00020'2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\bë\u0001\u0010í\u0001Jc\u0010ñ\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030¼\u00012\u0007\u0010¾\u0001\u001a\u00020\u00152\u0007\u0010ª\u0001\u001a\u00020\u00152\u0007\u0010î\u0001\u001a\u00020\u00152\u0007\u0010ï\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0007\u0010ð\u0001\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bñ\u0001\u0010ò\u0001Jc\u0010ñ\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030ç\u00012\u0007\u0010ä\u0001\u001a\u00020\u00152\u0007\u0010å\u0001\u001a\u00020\u00152\u0007\u0010ó\u0001\u001a\u00020\u00152\u0007\u0010ô\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0007\u0010ð\u0001\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bñ\u0001\u0010õ\u0001Jc\u0010ñ\u0001\u001a\u00020\u00052\b\u0010½\u0001\u001a\u00030ö\u00012\u0007\u0010ä\u0001\u001a\u00020\u00152\u0007\u0010å\u0001\u001a\u00020\u00152\u0007\u0010ó\u0001\u001a\u00020\u00152\u0007\u0010ô\u0001\u001a\u00020\u00152\u0007\u0010\u0084\u0001\u001a\u00020'2\u0007\u0010\u0085\u0001\u001a\u00020'2\u0007\u0010ð\u0001\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0017¢\u0006\u0006\bñ\u0001\u0010÷\u0001J\u0088\u0001\u0010\u0080\u0002\u001a\u00020\u00052\b\u0010\u009e\u0001\u001a\u00030ø\u00012\u0007\u0010ù\u0001\u001a\u00020\u00152\b\u0010\u008f\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u00152\n\u0010ú\u0001\u001a\u0005\u0018\u00010\u008e\u00012\u0007\u0010û\u0001\u001a\u00020\u00152\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0080\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u00152\n\u0010ý\u0001\u001a\u0005\u0018\u00010ü\u00012\u0007\u0010þ\u0001\u001a\u00020\u00152\u0007\u0010ÿ\u0001\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0006\b\u0080\u0002\u0010\u0081\u0002J\u001c\u0010\u0084\u0002\u001a\u00020\u00052\b\u0010\u0083\u0002\u001a\u00030\u0082\u0002H\u0017¢\u0006\u0006\b\u0084\u0002\u0010\u0085\u0002R\u0019\u0010\u0086\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b\u0086\u0002\u0010\u0087\u0002¨\u0006\u0088\u0002"}, d2 = {"Landroidx/compose/ui/text/android/TextAndroidCanvas;", "Landroid/graphics/Canvas;", "<init>", "()V", "canvas", "Lx6/t0;", "setCanvas", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Rect;", "bounds", "", "getClipBounds", "(Landroid/graphics/Rect;)Z", "Landroid/graphics/Bitmap;", "bitmap", "setBitmap", "(Landroid/graphics/Bitmap;)V", "enableZ", "disableZ", "isOpaque", "()Z", "", "getWidth", "()I", "getHeight", "getDensity", "density", "setDensity", "(I)V", "getMaximumBitmapWidth", "getMaximumBitmapHeight", "save", "Landroid/graphics/RectF;", "Landroid/graphics/Paint;", "paint", "saveFlags", "saveLayer", "(Landroid/graphics/RectF;Landroid/graphics/Paint;I)I", "(Landroid/graphics/RectF;Landroid/graphics/Paint;)I", "", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "(FFFFLandroid/graphics/Paint;I)I", "(FFFFLandroid/graphics/Paint;)I", "alpha", "saveLayerAlpha", "(Landroid/graphics/RectF;II)I", "(Landroid/graphics/RectF;I)I", "(FFFFII)I", "(FFFFI)I", "restore", "getSaveCount", "saveCount", "restoreToCount", "dx", "dy", "translate", "(FF)V", "sx", "sy", "scale", "degrees", "rotate", "(F)V", "skew", "Landroid/graphics/Matrix;", "matrix", "concat", "(Landroid/graphics/Matrix;)V", "setMatrix", "ctm", "getMatrix", "rect", "Landroid/graphics/Region$Op;", "op", "clipRect", "(Landroid/graphics/RectF;Landroid/graphics/Region$Op;)Z", "(Landroid/graphics/Rect;Landroid/graphics/Region$Op;)Z", "(Landroid/graphics/RectF;)Z", "(FFFFLandroid/graphics/Region$Op;)Z", "(FFFF)Z", "(IIII)Z", "clipOutRect", "Landroid/graphics/Path;", "path", "clipPath", "(Landroid/graphics/Path;Landroid/graphics/Region$Op;)Z", "(Landroid/graphics/Path;)Z", "clipOutPath", "Landroid/graphics/DrawFilter;", "getDrawFilter", "()Landroid/graphics/DrawFilter;", "filter", "setDrawFilter", "(Landroid/graphics/DrawFilter;)V", "Landroid/graphics/Canvas$EdgeType;", LinkHeader.Parameters.Type, "quickReject", "(Landroid/graphics/RectF;Landroid/graphics/Canvas$EdgeType;)Z", "(Landroid/graphics/Path;Landroid/graphics/Canvas$EdgeType;)Z", "(FFFFLandroid/graphics/Canvas$EdgeType;)Z", "Landroid/graphics/Picture;", "picture", "drawPicture", "(Landroid/graphics/Picture;)V", "dst", "(Landroid/graphics/Picture;Landroid/graphics/RectF;)V", "(Landroid/graphics/Picture;Landroid/graphics/Rect;)V", "oval", "startAngle", "sweepAngle", "useCenter", "drawArc", "(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V", "(FFFFFFZLandroid/graphics/Paint;)V", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "r", "g", "b", "drawARGB", "(IIII)V", "drawBitmap", "(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V", "src", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/RectF;Landroid/graphics/Paint;)V", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V", "", "colors", "offset", "stride", "x", "y", "width", "height", "hasAlpha", "([IIIFFIIZLandroid/graphics/Paint;)V", "([IIIIIIIZLandroid/graphics/Paint;)V", "(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V", "meshWidth", "meshHeight", "", "verts", "vertOffset", "colorOffset", "drawBitmapMesh", "(Landroid/graphics/Bitmap;II[FI[IILandroid/graphics/Paint;)V", "cx", "cy", "radius", "drawCircle", "(FFFLandroid/graphics/Paint;)V", TtmlNode.ATTR_TTS_COLOR, "drawColor", "", "(J)V", "Landroid/graphics/PorterDuff$Mode;", "mode", "(ILandroid/graphics/PorterDuff$Mode;)V", "Landroid/graphics/BlendMode;", "(ILandroid/graphics/BlendMode;)V", "(JLandroid/graphics/BlendMode;)V", "startX", "startY", "stopX", "stopY", "drawLine", "(FFFFLandroid/graphics/Paint;)V", "pts", "count", "drawLines", "([FIILandroid/graphics/Paint;)V", "([FLandroid/graphics/Paint;)V", "drawOval", "(Landroid/graphics/RectF;Landroid/graphics/Paint;)V", "drawPaint", "(Landroid/graphics/Paint;)V", "Landroid/graphics/NinePatch;", "patch", "drawPatch", "(Landroid/graphics/NinePatch;Landroid/graphics/Rect;Landroid/graphics/Paint;)V", "(Landroid/graphics/NinePatch;Landroid/graphics/RectF;Landroid/graphics/Paint;)V", "drawPath", "(Landroid/graphics/Path;Landroid/graphics/Paint;)V", "drawPoint", "(FFLandroid/graphics/Paint;)V", "drawPoints", "", "text", "index", "pos", "drawPosText", "([CII[FLandroid/graphics/Paint;)V", "", "(Ljava/lang/String;[FLandroid/graphics/Paint;)V", "drawRect", "(Landroid/graphics/Rect;Landroid/graphics/Paint;)V", "drawRGB", "(III)V", "rx", "ry", "drawRoundRect", "(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V", "(FFFFFFLandroid/graphics/Paint;)V", "outer", "outerRx", "outerRy", "inner", "innerRx", "innerRy", "drawDoubleRoundRect", "(Landroid/graphics/RectF;FFLandroid/graphics/RectF;FFLandroid/graphics/Paint;)V", "outerRadii", "innerRadii", "(Landroid/graphics/RectF;[FLandroid/graphics/RectF;[FLandroid/graphics/Paint;)V", "glyphIds", "glyphIdOffset", "positions", "positionOffset", "glyphCount", "Landroid/graphics/fonts/Font;", "font", "drawGlyphs", "([II[FIILandroid/graphics/fonts/Font;Landroid/graphics/Paint;)V", "drawText", "([CIIFFLandroid/graphics/Paint;)V", "(Ljava/lang/String;FFLandroid/graphics/Paint;)V", TtmlNode.START, TtmlNode.END, "(Ljava/lang/String;IIFFLandroid/graphics/Paint;)V", "", "(Ljava/lang/CharSequence;IIFFLandroid/graphics/Paint;)V", "hOffset", "vOffset", "drawTextOnPath", "([CIILandroid/graphics/Path;FFLandroid/graphics/Paint;)V", "(Ljava/lang/String;Landroid/graphics/Path;FFLandroid/graphics/Paint;)V", "contextIndex", "contextCount", "isRtl", "drawTextRun", "([CIIIIFFZLandroid/graphics/Paint;)V", "contextStart", "contextEnd", "(Ljava/lang/CharSequence;IIIIFFZLandroid/graphics/Paint;)V", "Landroid/graphics/text/MeasuredText;", "(Landroid/graphics/text/MeasuredText;IIIIFFZLandroid/graphics/Paint;)V", "Landroid/graphics/Canvas$VertexMode;", "vertexCount", "texs", "texOffset", "", "indices", "indexOffset", "indexCount", "drawVertices", "(Landroid/graphics/Canvas$VertexMode;I[FI[FI[II[SIILandroid/graphics/Paint;)V", "Landroid/graphics/RenderNode;", "renderNode", "drawRenderNode", "(Landroid/graphics/RenderNode;)V", "nativeCanvas", "Landroid/graphics/Canvas;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextAndroidCanvas extends Canvas {
    public static final int $stable = 8;
    private Canvas nativeCanvas;

    @Override // android.graphics.Canvas
    public boolean clipOutPath(Path path) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutPath(path);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(RectF rect) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(rect);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public boolean clipPath(Path path, Region.Op op) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipPath(path, op);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public boolean clipRect(RectF rect, Region.Op op) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rect, op);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public void concat(Matrix matrix) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.concat(matrix);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void disableZ() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.disableZ();
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawARGB(int a10, int r4, int g10, int b10) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawARGB(a10, r4, g10, b10);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, float left, float top, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, left, top, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawBitmapMesh(Bitmap bitmap, int meshWidth, int meshHeight, float[] verts, int vertOffset, int[] colors, int colorOffset, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmapMesh(bitmap, meshWidth, meshHeight, verts, vertOffset, colors, colorOffset, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawCircle(float cx, float cy, float radius, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawCircle(cx, cy, radius, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawColor(int color) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(color);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawDoubleRoundRect(RectF outer, float outerRx, float outerRy, RectF inner, float innerRx, float innerRy, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawDoubleRoundRect(outer, outerRx, outerRy, inner, innerRx, innerRy, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawGlyphs(int[] glyphIds, int glyphIdOffset, float[] positions, int positionOffset, int glyphCount, Font font, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawGlyphs(glyphIds, glyphIdOffset, positions, positionOffset, glyphCount, font, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawLine(float startX, float startY, float stopX, float stopY, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] pts, int offset, int count, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawLines(pts, offset, count, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawOval(RectF oval, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawOval(oval, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPaint(Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPaint(paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch patch, Rect dst, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPatch(patch, dst, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPath(Path path, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPath(path, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPicture(picture);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPoint(float x, float y10, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPoint(x, y10, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] pts, int offset, int count, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPoints(pts, offset, count, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    @e
    public void drawPosText(char[] text, int index, int count, float[] pos, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPosText(text, index, count, pos, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRGB(int r4, int g10, int b10) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRGB(r4, g10, b10);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRect(RectF rect, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRect(rect, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRenderNode(RenderNode renderNode) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRenderNode(renderNode);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(RectF rect, float rx, float ry, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRoundRect(rect, rx, ry, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawText(char[] text, int index, int count, float x, float y10, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(text, index, count, x, y10, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextOnPath(text, index, count, path, hOffset, vOffset, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(char[] text, int index, int count, int contextIndex, int contextCount, float x, float y10, boolean isRtl, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextRun(text, index, count, contextIndex, contextCount, x, y10, isRtl, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawVertices(Canvas.VertexMode mode, int vertexCount, float[] verts, int vertOffset, float[] texs, int texOffset, int[] colors, int colorOffset, short[] indices, int indexOffset, int indexCount, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawVertices(mode, vertexCount, verts, vertOffset, texs, texOffset, colors, colorOffset, indices, indexOffset, indexCount, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void enableZ() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.enableZ();
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public boolean getClipBounds(Rect bounds) {
        Canvas canvas = this.nativeCanvas;
        if (canvas == null) {
            p.i("nativeCanvas");
            throw null;
        }
        boolean clipBounds = canvas.getClipBounds(bounds);
        if (clipBounds) {
            bounds.set(0, 0, bounds.width(), Integer.MAX_VALUE);
        }
        return clipBounds;
    }

    @Override // android.graphics.Canvas
    public int getDensity() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getDensity();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public DrawFilter getDrawFilter() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getDrawFilter();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int getHeight() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getHeight();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public void getMatrix(Matrix ctm) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.getMatrix(ctm);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapHeight() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getMaximumBitmapHeight();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapWidth() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getMaximumBitmapWidth();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int getSaveCount() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getSaveCount();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int getWidth() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getWidth();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean isOpaque() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.isOpaque();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public boolean quickReject(RectF rect, Canvas.EdgeType type) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(rect, type);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public void restore() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.restore();
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void restoreToCount(int saveCount) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.restoreToCount(saveCount);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void rotate(float degrees) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.rotate(degrees);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public int save() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.save();
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public int saveLayer(RectF bounds, Paint paint, int saveFlags) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(bounds, paint, saveFlags);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public int saveLayerAlpha(RectF bounds, int alpha, int saveFlags) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(bounds, alpha, saveFlags);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public void scale(float sx, float sy) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.scale(sx, sy);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void setBitmap(Bitmap bitmap) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setBitmap(bitmap);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    public final void setCanvas(Canvas canvas) {
        this.nativeCanvas = canvas;
    }

    @Override // android.graphics.Canvas
    public void setDensity(int density) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setDensity(density);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void setDrawFilter(DrawFilter filter) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setDrawFilter(filter);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void setMatrix(Matrix matrix) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setMatrix(matrix);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void skew(float sx, float sy) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.skew(sx, sy);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void translate(float dx, float dy) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.translate(dx, dy);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(Rect rect) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(rect);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path path) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipPath(path);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public boolean clipRect(Rect rect, Region.Op op) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rect, op);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, src, dst, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawColor(long color) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(color);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawDoubleRoundRect(RectF outer, float[] outerRadii, RectF inner, float[] innerRadii, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawDoubleRoundRect(outer, outerRadii, inner, innerRadii, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] pts, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawLines(pts, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawOval(float left, float top, float right, float bottom, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawOval(left, top, right, bottom, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch patch, RectF dst, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPatch(patch, dst, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture, RectF dst) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPicture(picture, dst);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] pts, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPoints(pts, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    @e
    public void drawPosText(String text, float[] pos, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPosText(text, pos, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRect(Rect r4, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRect(r4, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRoundRect(left, top, right, bottom, rx, ry, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawText(String text, float x, float y10, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(text, x, y10, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextOnPath(text, path, hOffset, vOffset, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(CharSequence text, int start, int end, int contextStart, int contextEnd, float x, float y10, boolean isRtl, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextRun(text, start, end, contextStart, contextEnd, x, y10, isRtl, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF rect) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(rect);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF bounds, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(bounds, paint);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF bounds, int alpha) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(bounds, alpha);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(float left, float top, float right, float bottom) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(left, top, right, bottom);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF rect) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rect);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, src, dst, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawColor(int color, PorterDuff.Mode mode) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(color, mode);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture, Rect dst) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPicture(picture, dst);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRect(left, top, right, bottom, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawText(String text, int start, int end, float x, float y10, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(text, start, end, x, y10, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(MeasuredText text, int start, int end, int contextStart, int contextEnd, float x, float y10, boolean isRtl, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextRun(text, start, end, contextStart, contextEnd, x, y10, isRtl, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    @e
    public boolean quickReject(Path path, Canvas.EdgeType type) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(path, type);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public int saveLayer(float left, float top, float right, float bottom, Paint paint, int saveFlags) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(left, top, right, bottom, paint, saveFlags);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public int saveLayerAlpha(float left, float top, float right, float bottom, int alpha, int saveFlags) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(left, top, right, bottom, alpha, saveFlags);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(int left, int top, int right, int bottom) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(left, top, right, bottom);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect rect) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rect);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public void drawBitmap(int[] colors, int offset, int stride, float x, float y10, int width, int height, boolean hasAlpha, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(colors, offset, stride, x, y10, width, height, hasAlpha, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawColor(int color, BlendMode mode) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(color, mode);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawText(CharSequence text, int start, int end, float x, float y10, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(text, start, end, x, y10, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path path) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(path);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float left, float top, float right, float bottom, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(left, top, right, bottom, paint);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float left, float top, float right, float bottom, int alpha) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(left, top, right, bottom, alpha);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public boolean clipRect(float left, float top, float right, float bottom, Region.Op op) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(left, top, right, bottom, op);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    @e
    public void drawBitmap(int[] colors, int offset, int stride, int x, int y10, int width, int height, boolean hasAlpha, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(colors, offset, stride, x, y10, width, height, hasAlpha, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public void drawColor(long color, BlendMode mode) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(color, mode);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    @e
    public boolean quickReject(float left, float top, float right, float bottom, Canvas.EdgeType type) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(left, top, right, bottom, type);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float left, float top, float right, float bottom) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(left, top, right, bottom);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, matrix, paint);
        } else {
            p.i("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float left, float top, float right, float bottom) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(left, top, right, bottom);
        }
        p.i("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(int left, int top, int right, int bottom) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(left, top, right, bottom);
        }
        p.i("nativeCanvas");
        throw null;
    }
}
