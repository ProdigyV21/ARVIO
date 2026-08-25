package androidx.compose.ui.semantics;

import androidx.compose.material3.d;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.r;
import kotlin.jvm.internal.v;
import kotlin.reflect.m;
import r7.l;
import r7.p;
import r7.q;
import x6.e;
import x6.o;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0001\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000H\u0002¢\u0006\u0004\b\u0001\u0010\u0002\u001a#\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001aA\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u001c\u0010\t\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\bH\u0000¢\u0006\u0004\b\u0006\u0010\n\u001a4\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\u0005\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0004\u001a\u00020\u0003H\u0082\b¢\u0006\u0004\b\u000e\u0010\u0007\u001a\u0011\u0010\u0011\u001a\u00020\u0010*\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u00020\u0010*\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u0012\u001a\u0013\u0010\u0014\u001a\u00020\u0010*\u00020\u000fH\u0007¢\u0006\u0004\b\u0014\u0010\u0012\u001a\u0011\u0010\u0015\u001a\u00020\u0010*\u00020\u000f¢\u0006\u0004\b\u0015\u0010\u0012\u001a\u0011\u0010\u0016\u001a\u00020\u0010*\u00020\u000f¢\u0006\u0004\b\u0016\u0010\u0012\u001a\u0011\u0010\u0017\u001a\u00020\u0010*\u00020\u000f¢\u0006\u0004\b\u0017\u0010\u0012\u001a\u0019\u0010\u0019\u001a\u00020\u0010*\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a%\u0010\u001f\u001a\u00020\u0010*\u00020\u000f2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b¢\u0006\u0004\b\u001f\u0010 \u001a\u0011\u0010!\u001a\u00020\u0010*\u00020\u000f¢\u0006\u0004\b!\u0010\u0012\u001a9\u0010&\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u001a\u0010%\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b¢\u0006\u0004\b&\u0010'\u001a-\u0010)\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\b)\u0010*\u001a-\u0010+\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\b+\u0010*\u001aW\u00100\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u000328\u0010%\u001a4\u0012\u0013\u0012\u00110,¢\u0006\f\b-\u0012\b\b\u0004\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110,¢\u0006\f\b-\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\f\u0018\u00010\b¢\u0006\u0004\b0\u00101\u001a1\u00102\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\f0\u001b¢\u0006\u0004\b2\u0010'\u001a3\u00103\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b¢\u0006\u0004\b3\u0010'\u001a3\u00105\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b¢\u0006\u0004\b5\u0010'\u001a3\u00106\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b¢\u0006\u0004\b6\u0010'\u001a3\u00107\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b¢\u0006\u0004\b7\u0010'\u001a-\u00108\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\b8\u0010*\u001a3\u00109\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b¢\u0006\u0004\b9\u0010'\u001a8\u0010>\u001a\u00020\u0010*\u00020\u000f2\u0006\u0010;\u001a\u00020:2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a/\u0010?\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(H\u0007¢\u0006\u0004\b?\u0010*\u001al\u0010D\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032M\u0010%\u001aI\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b\u0004\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b\u0004\u0012\u0004\b\b(B\u0012\u0013\u0012\u00110\f¢\u0006\f\b-\u0012\b\b\u0004\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020\f\u0018\u00010@¢\u0006\u0004\bD\u0010E\u001a-\u0010F\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bF\u0010*\u001a-\u0010G\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bG\u0010*\u001a-\u0010H\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bH\u0010*\u001a-\u0010I\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bI\u0010*\u001a-\u0010J\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bJ\u0010*\u001a-\u0010K\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bK\u0010*\u001a-\u0010L\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bL\u0010*\u001a-\u0010M\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bM\u0010*\u001a-\u0010N\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bN\u0010*\u001a-\u0010O\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bO\u0010*\u001a-\u0010P\u001a\u00020\u0010*\u00020\u000f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010(¢\u0006\u0004\bP\u0010*\"(\u0010U\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010Q\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bR\u0010S\"\u0004\bT\u0010\u001a\"/\u0010[\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010V\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bW\u0010S\"\u0004\bX\u0010\u001a*\u0004\bY\u0010Z\"/\u0010b\u001a\u00020\\*\u00020\u000f2\u0006\u0010V\u001a\u00020\\8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`*\u0004\ba\u0010Z\"/\u0010f\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010V\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bc\u0010S\"\u0004\bd\u0010\u001a*\u0004\be\u0010Z\"/\u0010m\u001a\u00020g*\u00020\u000f2\u0006\u0010V\u001a\u00020g8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bh\u0010i\"\u0004\bj\u0010k*\u0004\bl\u0010Z\"/\u0010s\u001a\u00020\f*\u00020\u000f2\u0006\u0010V\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bn\u0010o\"\u0004\bp\u0010q*\u0004\br\u0010Z\"5\u0010t\u001a\u00020\f*\u00020\u000f2\u0006\u0010V\u001a\u00020\f8F@FX\u0087\u008e\u0002¢\u0006\u0018\u0012\u0004\bv\u0010\u0012\u001a\u0004\bt\u0010o\"\u0004\bu\u0010q*\u0004\bw\u0010Z\"/\u0010x\u001a\u00020\f*\u00020\u000f2\u0006\u0010V\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bx\u0010o\"\u0004\by\u0010q*\u0004\bz\u0010Z\"0\u0010\u0080\u0001\u001a\u00020,*\u00020\u000f2\u0006\u0010V\u001a\u00020,8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~*\u0004\b\u007f\u0010Z\"7\u0010\u0087\u0001\u001a\u00030\u0081\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030\u0081\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001*\u0005\b\u0086\u0001\u0010Z\"7\u0010\u008b\u0001\u001a\u00030\u0081\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030\u0081\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u0088\u0001\u0010\u0083\u0001\"\u0006\b\u0089\u0001\u0010\u0085\u0001*\u0005\b\u008a\u0001\u0010Z\"5\u0010\u0090\u0001\u001a\u00030\u008c\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030\u008c\u00018F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b\u008d\u0001\u0010i\"\u0005\b\u008e\u0001\u0010k*\u0005\b\u008f\u0001\u0010Z\"3\u0010\u0094\u0001\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010V\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b\u0091\u0001\u0010S\"\u0005\b\u0092\u0001\u0010\u001a*\u0005\b\u0093\u0001\u0010Z\",\u0010\u0098\u0001\u001a\u000204*\u00020\u000f2\u0006\u0010Q\u001a\u0002048F@FX\u0086\u000e¢\u0006\u000f\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001\"\u0005\b5\u0010\u0097\u0001\"4\u0010\u009b\u0001\u001a\u000204*\u00020\u000f2\u0006\u0010V\u001a\u0002048F@FX\u0086\u008e\u0002¢\u0006\u0016\u001a\u0006\b\u0099\u0001\u0010\u0096\u0001\"\u0005\b6\u0010\u0097\u0001*\u0005\b\u009a\u0001\u0010Z\"3\u0010\u009c\u0001\u001a\u00020\f*\u00020\u000f2\u0006\u0010V\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b\u009c\u0001\u0010o\"\u0005\b\u009d\u0001\u0010q*\u0005\b\u009e\u0001\u0010Z\"5\u0010¢\u0001\u001a\u000204*\u00020\u000f2\u0006\u0010V\u001a\u0002048F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u009f\u0001\u0010\u0096\u0001\"\u0006\b \u0001\u0010\u0097\u0001*\u0005\b¡\u0001\u0010Z\"7\u0010©\u0001\u001a\u00030£\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030£\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b¤\u0001\u0010¥\u0001\"\u0006\b¦\u0001\u0010§\u0001*\u0005\b¨\u0001\u0010Z\":\u0010®\u0001\u001a\u00020:*\u00020\u000f2\u0006\u0010V\u001a\u00020:8G@GX\u0087\u008e\u0002¢\u0006\u001c\u0012\u0005\b¬\u0001\u0010\u0012\u001a\u0005\bª\u0001\u0010i\"\u0005\b«\u0001\u0010k*\u0005\b\u00ad\u0001\u0010Z\"3\u0010²\u0001\u001a\u00020\f*\u00020\u000f2\u0006\u0010V\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b¯\u0001\u0010o\"\u0005\b°\u0001\u0010q*\u0005\b±\u0001\u0010Z\"7\u0010¹\u0001\u001a\u00030³\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030³\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b´\u0001\u0010µ\u0001\"\u0006\b¶\u0001\u0010·\u0001*\u0005\b¸\u0001\u0010Z\"7\u0010À\u0001\u001a\u00030º\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030º\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b»\u0001\u0010¼\u0001\"\u0006\b½\u0001\u0010¾\u0001*\u0005\b¿\u0001\u0010Z\"7\u0010Ç\u0001\u001a\u00030Á\u0001*\u00020\u000f2\u0007\u0010V\u001a\u00030Á\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\bÂ\u0001\u0010Ã\u0001\"\u0006\bÄ\u0001\u0010Å\u0001*\u0005\bÆ\u0001\u0010Z\"E\u0010Ï\u0001\u001a\n\u0012\u0005\u0012\u00030É\u00010È\u0001*\u00020\u000f2\u000e\u0010V\u001a\n\u0012\u0005\u0012\u00030É\u00010È\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\bÊ\u0001\u0010Ë\u0001\"\u0006\bÌ\u0001\u0010Í\u0001*\u0005\bÎ\u0001\u0010Z\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Ð\u0001"}, d2 = {"T", "throwSemanticsGetNotSupported", "()Ljava/lang/Object;", "", ContentDisposition.Parameters.Name, "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "AccessibilityKey", "(Ljava/lang/String;)Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Lkotlin/Function2;", "mergePolicy", "(Ljava/lang/String;Lr7/p;)Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Lx6/o;", "", "Landroidx/compose/ui/semantics/AccessibilityAction;", "ActionPropertyKey", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "heading", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "disabled", "invisibleToUser", "popup", "dialog", "password", MediaTrack.ROLE_DESCRIPTION, "error", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/lang/String;)V", "Lkotlin/Function1;", "", "", "mapping", "indexForKey", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Lr7/l;)V", "selectableGroup", "label", "", "Landroidx/compose/ui/text/TextLayoutResult;", "action", "getTextLayoutResult", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/lang/String;Lr7/l;)V", "Lkotlin/Function0;", "onClick", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/lang/String;Lr7/a;)V", "onLongClick", "", "Lx6/y;", "x", "y", "scrollBy", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/lang/String;Lr7/p;)V", "scrollToIndex", "setProgress", "Landroidx/compose/ui/text/AnnotatedString;", "setText", "setTextSubstitution", "showTextSubstitution", "clearTextSubstitution", "insertTextAtCursor", "Landroidx/compose/ui/text/input/ImeAction;", "imeActionType", "onImeAction-9UiTYpY", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;ILjava/lang/String;Lr7/a;)V", "onImeAction", "performImeAction", "Lkotlin/Function3;", "startIndex", "endIndex", "relativeToOriginalText", "setSelection", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/lang/String;Lr7/q;)V", "copyText", "cutText", "pasteText", "expand", "collapse", "dismiss", "requestFocus", "pageUp", "pageDown", "pageLeft", "pageRight", "value", "getContentDescription", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", "setContentDescription", "contentDescription", "<set-?>", "getStateDescription", "setStateDescription", "getStateDescription$delegate", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/Object;", "stateDescription", "Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", "getProgressBarRangeInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", "setProgressBarRangeInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/ProgressBarRangeInfo;)V", "getProgressBarRangeInfo$delegate", "progressBarRangeInfo", "getPaneTitle", "setPaneTitle", "getPaneTitle$delegate", "paneTitle", "Landroidx/compose/ui/semantics/LiveRegionMode;", "getLiveRegion", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", "setLiveRegion-hR3wRGc", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;I)V", "getLiveRegion$delegate", "liveRegion", "getFocused", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", "setFocused", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Z)V", "getFocused$delegate", "focused", "isContainer", "setContainer", "isContainer$annotations", "isContainer$delegate", "isTraversalGroup", "setTraversalGroup", "isTraversalGroup$delegate", "getTraversalIndex", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)F", "setTraversalIndex", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;F)V", "getTraversalIndex$delegate", "traversalIndex", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "getHorizontalScrollAxisRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ScrollAxisRange;", "setHorizontalScrollAxisRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/ScrollAxisRange;)V", "getHorizontalScrollAxisRange$delegate", "horizontalScrollAxisRange", "getVerticalScrollAxisRange", "setVerticalScrollAxisRange", "getVerticalScrollAxisRange$delegate", "verticalScrollAxisRange", "Landroidx/compose/ui/semantics/Role;", "getRole", "setRole-kuIjeqM", "getRole$delegate", "role", "getTestTag", "setTestTag", "getTestTag$delegate", "testTag", "getText", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/text/AnnotatedString;)V", "text", "getTextSubstitution", "getTextSubstitution$delegate", "textSubstitution", "isShowingTextSubstitution", "setShowingTextSubstitution", "isShowingTextSubstitution$delegate", "getEditableText", "setEditableText", "getEditableText$delegate", "editableText", "Landroidx/compose/ui/text/TextRange;", "getTextSelectionRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)J", "setTextSelectionRange-FDrldGo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;J)V", "getTextSelectionRange$delegate", "textSelectionRange", "getImeAction", "setImeAction-4L7nppU", "getImeAction$annotations", "getImeAction$delegate", "imeAction", "getSelected", "setSelected", "getSelected$delegate", "selected", "Landroidx/compose/ui/semantics/CollectionInfo;", "getCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionInfo;", "setCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/CollectionInfo;)V", "getCollectionInfo$delegate", "collectionInfo", "Landroidx/compose/ui/semantics/CollectionItemInfo;", "getCollectionItemInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionItemInfo;", "setCollectionItemInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/CollectionItemInfo;)V", "getCollectionItemInfo$delegate", "collectionItemInfo", "Landroidx/compose/ui/state/ToggleableState;", "getToggleableState", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/state/ToggleableState;", "setToggleableState", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/state/ToggleableState;)V", "getToggleableState$delegate", "toggleableState", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "getCustomActions", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/util/List;", "setCustomActions", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/util/List;)V", "getCustomActions$delegate", "customActions", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SemanticsPropertiesKt {
    static final /* synthetic */ m<Object>[] $$delegatedProperties;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.ui.semantics.SemanticsPropertiesKt$ActionPropertyKey$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00010\u00002\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Lx6/o;", "", "T", "Landroidx/compose/ui/semantics/AccessibilityAction;", "parentValue", "childValue", "invoke", "(Landroidx/compose/ui/semantics/AccessibilityAction;Landroidx/compose/ui/semantics/AccessibilityAction;)Landroidx/compose/ui/semantics/AccessibilityAction;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1<T> extends r implements p<AccessibilityAction<T>, AccessibilityAction<T>, AccessibilityAction<T>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // r7.p
        public final AccessibilityAction<T> invoke(AccessibilityAction<T> accessibilityAction, AccessibilityAction<T> accessibilityAction2) {
            String label;
            o action;
            if (accessibilityAction == null || (label = accessibilityAction.getLabel()) == null) {
                label = accessibilityAction2.getLabel();
            }
            if (accessibilityAction == null || (action = accessibilityAction.getAction()) == null) {
                action = accessibilityAction2.getAction();
            }
            return new AccessibilityAction<>(label, action);
        }
    }

    static {
        v vVar = new v(SemanticsPropertiesKt.class, "stateDescription", "getStateDescription(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", 1);
        m0 m0Var = l0.f19747a;
        $$delegatedProperties = new m[]{m0Var.e(vVar), d.v(SemanticsPropertiesKt.class, "progressBarRangeInfo", "getProgressBarRangeInfo(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "paneTitle", "getPaneTitle(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "liveRegion", "getLiveRegion(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1, m0Var), d.v(SemanticsPropertiesKt.class, "focused", "getFocused(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1, m0Var), d.v(SemanticsPropertiesKt.class, "isContainer", "isContainer(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1, m0Var), d.v(SemanticsPropertiesKt.class, "isTraversalGroup", "isTraversalGroup(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1, m0Var), d.v(SemanticsPropertiesKt.class, "traversalIndex", "getTraversalIndex(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)F", 1, m0Var), d.v(SemanticsPropertiesKt.class, "horizontalScrollAxisRange", "getHorizontalScrollAxisRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ScrollAxisRange;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "verticalScrollAxisRange", "getVerticalScrollAxisRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ScrollAxisRange;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "role", "getRole(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1, m0Var), d.v(SemanticsPropertiesKt.class, "testTag", "getTestTag(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "textSubstitution", "getTextSubstitution(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "isShowingTextSubstitution", "isShowingTextSubstitution(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1, m0Var), d.v(SemanticsPropertiesKt.class, "editableText", "getEditableText(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "textSelectionRange", "getTextSelectionRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)J", 1, m0Var), d.v(SemanticsPropertiesKt.class, "imeAction", "getImeAction(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1, m0Var), d.v(SemanticsPropertiesKt.class, "selected", "getSelected(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1, m0Var), d.v(SemanticsPropertiesKt.class, "collectionInfo", "getCollectionInfo(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionInfo;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "collectionItemInfo", "getCollectionItemInfo(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionItemInfo;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "toggleableState", "getToggleableState(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/state/ToggleableState;", 1, m0Var), d.v(SemanticsPropertiesKt.class, "customActions", "getCustomActions(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/util/List;", 1, m0Var)};
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        semanticsProperties.getStateDescription();
        semanticsProperties.getProgressBarRangeInfo();
        semanticsProperties.getPaneTitle();
        semanticsProperties.getLiveRegion();
        semanticsProperties.getFocused();
        semanticsProperties.getIsTraversalGroup();
        semanticsProperties.getIsTraversalGroup();
        semanticsProperties.getTraversalIndex();
        semanticsProperties.getHorizontalScrollAxisRange();
        semanticsProperties.getVerticalScrollAxisRange();
        semanticsProperties.getRole();
        semanticsProperties.getTestTag();
        semanticsProperties.getTextSubstitution();
        semanticsProperties.getIsShowingTextSubstitution();
        semanticsProperties.getEditableText();
        semanticsProperties.getTextSelectionRange();
        semanticsProperties.getImeAction();
        semanticsProperties.getSelected();
        semanticsProperties.getCollectionInfo();
        semanticsProperties.getCollectionItemInfo();
        semanticsProperties.getToggleableState();
        SemanticsActions.INSTANCE.getCustomActions();
    }

    public static final <T> SemanticsPropertyKey<T> AccessibilityKey(String str) {
        return new SemanticsPropertyKey<>(str, true);
    }

    private static final <T extends o<? extends Boolean>> SemanticsPropertyKey<AccessibilityAction<T>> ActionPropertyKey(String str) {
        return AccessibilityKey(str, AnonymousClass1.INSTANCE);
    }

    public static final void clearTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getClearTextSubstitution(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void clearTextSubstitution$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        clearTextSubstitution(semanticsPropertyReceiver, str, aVar);
    }

    public static final void collapse(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getCollapse(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void collapse$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        collapse(semanticsPropertyReceiver, str, aVar);
    }

    public static final void copyText(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getCopyText(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void copyText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        copyText(semanticsPropertyReceiver, str, aVar);
    }

    public static final void cutText(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getCutText(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void cutText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        cutText(semanticsPropertyReceiver, str, aVar);
    }

    public static final void dialog(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getIsDialog(), t0.f22605a);
    }

    public static final void disabled(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getDisabled(), t0.f22605a);
    }

    public static final void dismiss(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getDismiss(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void dismiss$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        dismiss(semanticsPropertyReceiver, str, aVar);
    }

    public static final void error(SemanticsPropertyReceiver semanticsPropertyReceiver, String str) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getError(), str);
    }

    public static final void expand(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getExpand(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void expand$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        expand(semanticsPropertyReceiver, str, aVar);
    }

    public static final CollectionInfo getCollectionInfo(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getCollectionInfo().getValue(semanticsPropertyReceiver, $$delegatedProperties[18]);
    }

    public static final CollectionItemInfo getCollectionItemInfo(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getCollectionItemInfo().getValue(semanticsPropertyReceiver, $$delegatedProperties[19]);
    }

    public static final String getContentDescription(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return (String) throwSemanticsGetNotSupported();
    }

    public static final List<CustomAccessibilityAction> getCustomActions(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsActions.INSTANCE.getCustomActions().getValue(semanticsPropertyReceiver, $$delegatedProperties[21]);
    }

    public static final AnnotatedString getEditableText(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getEditableText().getValue(semanticsPropertyReceiver, $$delegatedProperties[14]);
    }

    public static final boolean getFocused(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getFocused().getValue(semanticsPropertyReceiver, $$delegatedProperties[4]).booleanValue();
    }

    public static final ScrollAxisRange getHorizontalScrollAxisRange(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange().getValue(semanticsPropertyReceiver, $$delegatedProperties[8]);
    }

    @e
    public static final int getImeAction(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getImeAction().getValue(semanticsPropertyReceiver, $$delegatedProperties[16]).getValue();
    }

    @e
    public static /* synthetic */ void getImeAction$annotations(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }

    public static final int getLiveRegion(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getLiveRegion().getValue(semanticsPropertyReceiver, $$delegatedProperties[3]).getValue();
    }

    public static final String getPaneTitle(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getPaneTitle().getValue(semanticsPropertyReceiver, $$delegatedProperties[2]);
    }

    public static final ProgressBarRangeInfo getProgressBarRangeInfo(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getProgressBarRangeInfo().getValue(semanticsPropertyReceiver, $$delegatedProperties[1]);
    }

    public static final int getRole(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getRole().getValue(semanticsPropertyReceiver, $$delegatedProperties[10]).getValue();
    }

    public static final boolean getSelected(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getSelected().getValue(semanticsPropertyReceiver, $$delegatedProperties[17]).booleanValue();
    }

    public static final String getStateDescription(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getStateDescription().getValue(semanticsPropertyReceiver, $$delegatedProperties[0]);
    }

    public static final String getTestTag(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getTestTag().getValue(semanticsPropertyReceiver, $$delegatedProperties[11]);
    }

    public static final AnnotatedString getText(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return (AnnotatedString) throwSemanticsGetNotSupported();
    }

    public static final void getTextLayoutResult(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super List<TextLayoutResult>, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getGetTextLayoutResult(), new AccessibilityAction(str, lVar));
    }

    public static /* synthetic */ void getTextLayoutResult$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        getTextLayoutResult(semanticsPropertyReceiver, str, lVar);
    }

    public static final long getTextSelectionRange(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getTextSelectionRange().getValue(semanticsPropertyReceiver, $$delegatedProperties[15]).getPackedValue();
    }

    public static final AnnotatedString getTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getTextSubstitution().getValue(semanticsPropertyReceiver, $$delegatedProperties[12]);
    }

    public static final ToggleableState getToggleableState(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getToggleableState().getValue(semanticsPropertyReceiver, $$delegatedProperties[20]);
    }

    public static final float getTraversalIndex(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getTraversalIndex().getValue(semanticsPropertyReceiver, $$delegatedProperties[7]).floatValue();
    }

    public static final ScrollAxisRange getVerticalScrollAxisRange(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getVerticalScrollAxisRange().getValue(semanticsPropertyReceiver, $$delegatedProperties[9]);
    }

    public static final void heading(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getHeading(), t0.f22605a);
    }

    public static final void indexForKey(SemanticsPropertyReceiver semanticsPropertyReceiver, l<Object, Integer> lVar) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getIndexForKey(), lVar);
    }

    public static final void insertTextAtCursor(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super AnnotatedString, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getInsertTextAtCursor(), new AccessibilityAction(str, lVar));
    }

    public static /* synthetic */ void insertTextAtCursor$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        insertTextAtCursor(semanticsPropertyReceiver, str, lVar);
    }

    public static final void invisibleToUser(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getInvisibleToUser(), t0.f22605a);
    }

    public static final boolean isContainer(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getIsTraversalGroup().getValue(semanticsPropertyReceiver, $$delegatedProperties[5]).booleanValue();
    }

    @e
    public static /* synthetic */ void isContainer$annotations(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }

    public static final boolean isShowingTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getIsShowingTextSubstitution().getValue(semanticsPropertyReceiver, $$delegatedProperties[13]).booleanValue();
    }

    public static final boolean isTraversalGroup(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return SemanticsProperties.INSTANCE.getIsTraversalGroup().getValue(semanticsPropertyReceiver, $$delegatedProperties[6]).booleanValue();
    }

    public static final void onClick(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getOnClick(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void onClick$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        onClick(semanticsPropertyReceiver, str, aVar);
    }

    /* JADX INFO: renamed from: onImeAction-9UiTYpY, reason: not valid java name */
    public static final void m5035onImeAction9UiTYpY(SemanticsPropertyReceiver semanticsPropertyReceiver, int i10, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getImeAction(), ImeAction.m5352boximpl(i10));
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getOnImeAction(), new AccessibilityAction(str, aVar));
    }

    /* JADX INFO: renamed from: onImeAction-9UiTYpY$default, reason: not valid java name */
    public static /* synthetic */ void m5036onImeAction9UiTYpY$default(SemanticsPropertyReceiver semanticsPropertyReceiver, int i10, String str, r7.a aVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            str = null;
        }
        m5035onImeAction9UiTYpY(semanticsPropertyReceiver, i10, str, aVar);
    }

    public static final void onLongClick(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getOnLongClick(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void onLongClick$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        onLongClick(semanticsPropertyReceiver, str, aVar);
    }

    public static final void pageDown(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getPageDown(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void pageDown$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        pageDown(semanticsPropertyReceiver, str, aVar);
    }

    public static final void pageLeft(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getPageLeft(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void pageLeft$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        pageLeft(semanticsPropertyReceiver, str, aVar);
    }

    public static final void pageRight(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getPageRight(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void pageRight$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        pageRight(semanticsPropertyReceiver, str, aVar);
    }

    public static final void pageUp(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getPageUp(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void pageUp$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        pageUp(semanticsPropertyReceiver, str, aVar);
    }

    public static final void password(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getPassword(), t0.f22605a);
    }

    public static final void pasteText(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getPasteText(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void pasteText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        pasteText(semanticsPropertyReceiver, str, aVar);
    }

    @e
    public static final void performImeAction(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getOnImeAction(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void performImeAction$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        performImeAction(semanticsPropertyReceiver, str, aVar);
    }

    public static final void popup(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getIsPopup(), t0.f22605a);
    }

    public static final void requestFocus(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a<Boolean> aVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getRequestFocus(), new AccessibilityAction(str, aVar));
    }

    public static /* synthetic */ void requestFocus$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        requestFocus(semanticsPropertyReceiver, str, aVar);
    }

    public static final void scrollBy(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, p<? super Float, ? super Float, Boolean> pVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getScrollBy(), new AccessibilityAction(str, pVar));
    }

    public static /* synthetic */ void scrollBy$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        scrollBy(semanticsPropertyReceiver, str, pVar);
    }

    public static final void scrollToIndex(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super Integer, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getScrollToIndex(), new AccessibilityAction(str, lVar));
    }

    public static /* synthetic */ void scrollToIndex$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        scrollToIndex(semanticsPropertyReceiver, str, lVar);
    }

    public static final void selectableGroup(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getSelectableGroup(), t0.f22605a);
    }

    public static final void setCollectionInfo(SemanticsPropertyReceiver semanticsPropertyReceiver, CollectionInfo collectionInfo) {
        SemanticsProperties.INSTANCE.getCollectionInfo().setValue(semanticsPropertyReceiver, $$delegatedProperties[18], collectionInfo);
    }

    public static final void setCollectionItemInfo(SemanticsPropertyReceiver semanticsPropertyReceiver, CollectionItemInfo collectionItemInfo) {
        SemanticsProperties.INSTANCE.getCollectionItemInfo().setValue(semanticsPropertyReceiver, $$delegatedProperties[19], collectionItemInfo);
    }

    public static final void setContainer(SemanticsPropertyReceiver semanticsPropertyReceiver, boolean z) {
        SemanticsProperties.INSTANCE.getIsTraversalGroup().setValue(semanticsPropertyReceiver, $$delegatedProperties[5], Boolean.valueOf(z));
    }

    public static final void setContentDescription(SemanticsPropertyReceiver semanticsPropertyReceiver, String str) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getContentDescription(), Collections.singletonList(str));
    }

    public static final void setCustomActions(SemanticsPropertyReceiver semanticsPropertyReceiver, List<CustomAccessibilityAction> list) {
        SemanticsActions.INSTANCE.getCustomActions().setValue(semanticsPropertyReceiver, $$delegatedProperties[21], list);
    }

    public static final void setEditableText(SemanticsPropertyReceiver semanticsPropertyReceiver, AnnotatedString annotatedString) {
        SemanticsProperties.INSTANCE.getEditableText().setValue(semanticsPropertyReceiver, $$delegatedProperties[14], annotatedString);
    }

    public static final void setFocused(SemanticsPropertyReceiver semanticsPropertyReceiver, boolean z) {
        SemanticsProperties.INSTANCE.getFocused().setValue(semanticsPropertyReceiver, $$delegatedProperties[4], Boolean.valueOf(z));
    }

    public static final void setHorizontalScrollAxisRange(SemanticsPropertyReceiver semanticsPropertyReceiver, ScrollAxisRange scrollAxisRange) {
        SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange().setValue(semanticsPropertyReceiver, $$delegatedProperties[8], scrollAxisRange);
    }

    @e
    /* JADX INFO: renamed from: setImeAction-4L7nppU, reason: not valid java name */
    public static final void m5037setImeAction4L7nppU(SemanticsPropertyReceiver semanticsPropertyReceiver, int i10) {
        SemanticsProperties.INSTANCE.getImeAction().setValue(semanticsPropertyReceiver, $$delegatedProperties[16], ImeAction.m5352boximpl(i10));
    }

    /* JADX INFO: renamed from: setLiveRegion-hR3wRGc, reason: not valid java name */
    public static final void m5038setLiveRegionhR3wRGc(SemanticsPropertyReceiver semanticsPropertyReceiver, int i10) {
        SemanticsProperties.INSTANCE.getLiveRegion().setValue(semanticsPropertyReceiver, $$delegatedProperties[3], LiveRegionMode.m5007boximpl(i10));
    }

    public static final void setPaneTitle(SemanticsPropertyReceiver semanticsPropertyReceiver, String str) {
        SemanticsProperties.INSTANCE.getPaneTitle().setValue(semanticsPropertyReceiver, $$delegatedProperties[2], str);
    }

    public static final void setProgress(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super Float, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getSetProgress(), new AccessibilityAction(str, lVar));
    }

    public static /* synthetic */ void setProgress$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        setProgress(semanticsPropertyReceiver, str, lVar);
    }

    public static final void setProgressBarRangeInfo(SemanticsPropertyReceiver semanticsPropertyReceiver, ProgressBarRangeInfo progressBarRangeInfo) {
        SemanticsProperties.INSTANCE.getProgressBarRangeInfo().setValue(semanticsPropertyReceiver, $$delegatedProperties[1], progressBarRangeInfo);
    }

    /* JADX INFO: renamed from: setRole-kuIjeqM, reason: not valid java name */
    public static final void m5039setRolekuIjeqM(SemanticsPropertyReceiver semanticsPropertyReceiver, int i10) {
        SemanticsProperties.INSTANCE.getRole().setValue(semanticsPropertyReceiver, $$delegatedProperties[10], Role.m5016boximpl(i10));
    }

    public static final void setSelected(SemanticsPropertyReceiver semanticsPropertyReceiver, boolean z) {
        SemanticsProperties.INSTANCE.getSelected().setValue(semanticsPropertyReceiver, $$delegatedProperties[17], Boolean.valueOf(z));
    }

    public static final void setSelection(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, q<? super Integer, ? super Integer, ? super Boolean, Boolean> qVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getSetSelection(), new AccessibilityAction(str, qVar));
    }

    public static /* synthetic */ void setSelection$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, q qVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        setSelection(semanticsPropertyReceiver, str, qVar);
    }

    public static final void setShowingTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver, boolean z) {
        SemanticsProperties.INSTANCE.getIsShowingTextSubstitution().setValue(semanticsPropertyReceiver, $$delegatedProperties[13], Boolean.valueOf(z));
    }

    public static final void setStateDescription(SemanticsPropertyReceiver semanticsPropertyReceiver, String str) {
        SemanticsProperties.INSTANCE.getStateDescription().setValue(semanticsPropertyReceiver, $$delegatedProperties[0], str);
    }

    public static final void setTestTag(SemanticsPropertyReceiver semanticsPropertyReceiver, String str) {
        SemanticsProperties.INSTANCE.getTestTag().setValue(semanticsPropertyReceiver, $$delegatedProperties[11], str);
    }

    public static final void setText(SemanticsPropertyReceiver semanticsPropertyReceiver, AnnotatedString annotatedString) {
        semanticsPropertyReceiver.set(SemanticsProperties.INSTANCE.getText(), Collections.singletonList(annotatedString));
    }

    public static /* synthetic */ void setText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        setText(semanticsPropertyReceiver, str, lVar);
    }

    /* JADX INFO: renamed from: setTextSelectionRange-FDrldGo, reason: not valid java name */
    public static final void m5040setTextSelectionRangeFDrldGo(SemanticsPropertyReceiver semanticsPropertyReceiver, long j10) {
        SemanticsProperties.INSTANCE.getTextSelectionRange().setValue(semanticsPropertyReceiver, $$delegatedProperties[15], TextRange.m5180boximpl(j10));
    }

    public static final void setTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver, AnnotatedString annotatedString) {
        SemanticsProperties.INSTANCE.getTextSubstitution().setValue(semanticsPropertyReceiver, $$delegatedProperties[12], annotatedString);
    }

    public static /* synthetic */ void setTextSubstitution$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        setTextSubstitution(semanticsPropertyReceiver, str, lVar);
    }

    public static final void setToggleableState(SemanticsPropertyReceiver semanticsPropertyReceiver, ToggleableState toggleableState) {
        SemanticsProperties.INSTANCE.getToggleableState().setValue(semanticsPropertyReceiver, $$delegatedProperties[20], toggleableState);
    }

    public static final void setTraversalGroup(SemanticsPropertyReceiver semanticsPropertyReceiver, boolean z) {
        SemanticsProperties.INSTANCE.getIsTraversalGroup().setValue(semanticsPropertyReceiver, $$delegatedProperties[6], Boolean.valueOf(z));
    }

    public static final void setTraversalIndex(SemanticsPropertyReceiver semanticsPropertyReceiver, float f10) {
        SemanticsProperties.INSTANCE.getTraversalIndex().setValue(semanticsPropertyReceiver, $$delegatedProperties[7], Float.valueOf(f10));
    }

    public static final void setVerticalScrollAxisRange(SemanticsPropertyReceiver semanticsPropertyReceiver, ScrollAxisRange scrollAxisRange) {
        SemanticsProperties.INSTANCE.getVerticalScrollAxisRange().setValue(semanticsPropertyReceiver, $$delegatedProperties[9], scrollAxisRange);
    }

    public static final void showTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super Boolean, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getShowTextSubstitution(), new AccessibilityAction(str, lVar));
    }

    public static /* synthetic */ void showTextSubstitution$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        showTextSubstitution(semanticsPropertyReceiver, str, lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T throwSemanticsGetNotSupported() {
        throw new UnsupportedOperationException("You cannot retrieve a semantics property directly - use one of the SemanticsConfiguration.getOr* methods instead");
    }

    public static final <T> SemanticsPropertyKey<T> AccessibilityKey(String str, p<? super T, ? super T, ? extends T> pVar) {
        return new SemanticsPropertyKey<>(str, true, pVar);
    }

    public static final void setTextSubstitution(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super AnnotatedString, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getSetTextSubstitution(), new AccessibilityAction(str, lVar));
    }

    public static final void setText(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, l<? super AnnotatedString, Boolean> lVar) {
        semanticsPropertyReceiver.set(SemanticsActions.INSTANCE.getSetText(), new AccessibilityAction(str, lVar));
    }
}
