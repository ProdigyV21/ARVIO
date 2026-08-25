package androidx.compose.runtime.collection;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.p;
import r7.l;
import r7.q;
import s7.e;
import s7.f;
import t7.a;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`\u0003:\u0006\u0088\u0001\u0089\u0001\u008a\u0001B!\b\u0001\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\f\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\u0010J#\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b\u0013\u0010\u0015J\u001e\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086\b¢\u0006\u0004\b\u0013\u0010\u0016J\u001e\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0086\b¢\u0006\u0004\b\u0013\u0010\u0017J\u001b\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0013\u0010\u0018J#\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019¢\u0006\u0004\b\u0013\u0010\u001aJ\u001b\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019¢\u0006\u0004\b\u0013\u0010\u001bJ/\u0010\u001e\u001a\u00020\u000b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ/\u0010 \u001a\u00020\u000b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b \u0010\u001fJ\u0013\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u000f¢\u0006\u0004\b$\u0010%J\u0018\u0010&\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b&\u0010\rJ\u001b\u0010'\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0004\b'\u0010\u0016J\u001b\u0010'\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019¢\u0006\u0004\b'\u0010\u001bJ\u001b\u0010'\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b'\u0010\u0017J\u001b\u0010)\u001a\u00020\u000b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b)\u0010\u0017J\u0015\u0010+\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u0006¢\u0006\u0004\b+\u0010,J\r\u0010-\u001a\u00028\u0000¢\u0006\u0004\b-\u0010.J/\u0010-\u001a\u00028\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b-\u0010/J\u0012\u00100\u001a\u0004\u0018\u00018\u0000H\u0086\b¢\u0006\u0004\b0\u0010.J1\u00100\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b0\u0010/JR\u00108\u001a\u00028\u0001\"\u0004\b\u0001\u001012\u0006\u00102\u001a\u00028\u00012'\u00107\u001a#\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000103H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b8\u00109Jg\u0010;\u001a\u00028\u0001\"\u0004\b\u0001\u001012\u0006\u00102\u001a\u00028\u00012<\u00107\u001a8\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010:H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b;\u0010<JR\u0010=\u001a\u00028\u0001\"\u0004\b\u0001\u001012\u0006\u00102\u001a\u00028\u00012'\u00107\u001a#\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00028\u000103H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b=\u00109Jg\u0010>\u001a\u00028\u0001\"\u0004\b\u0001\u001012\u0006\u00102\u001a\u00028\u00012<\u00107\u001a8\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00028\u00010:H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b>\u0010<J/\u0010@\u001a\u00020\u000f2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b@\u0010AJ5\u0010B\u001a\u00020\u000f2\u0018\u0010?\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f03H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bB\u0010CJ/\u0010D\u001a\u00020\u000f2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bD\u0010AJ5\u0010E\u001a\u00020\u000f2\u0018\u0010?\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f03H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0086\n¢\u0006\u0004\bF\u0010GJ\u0015\u0010H\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\bH\u0010IJ/\u0010J\u001a\u00020\u00062\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bJ\u0010KJ/\u0010L\u001a\u00020\u00062\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bL\u0010KJ\r\u0010M\u001a\u00020\u000b¢\u0006\u0004\bM\u0010NJ\r\u0010O\u001a\u00020\u000b¢\u0006\u0004\bO\u0010NJ\r\u0010P\u001a\u00028\u0000¢\u0006\u0004\bP\u0010.J/\u0010P\u001a\u00028\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bP\u0010/J\u0015\u0010Q\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\bQ\u0010IJ\u0012\u0010R\u001a\u0004\u0018\u00018\u0000H\u0086\b¢\u0006\u0004\bR\u0010.J1\u0010R\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bR\u0010/J=\u0010T\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0006\b\u0001\u00101\u0018\u00012\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bT\u0010UJR\u0010V\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0006\b\u0001\u00101\u0018\u00012'\u0010S\u001a#\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000103H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bV\u0010WJT\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\u0006\b\u0001\u00101\u0018\u00012)\u0010S\u001a%\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u000103H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bX\u0010YJ?\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\u0006\b\u0001\u00101\u0018\u00012\u0014\u0010S\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\bZ\u0010[J\u0018\u0010\\\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00028\u0000H\u0086\n¢\u0006\u0004\b\\\u0010]J\u0018\u0010^\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00028\u0000H\u0086\n¢\u0006\u0004\b^\u0010]J\u0015\u0010_\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b_\u0010\rJ\u001b\u0010`\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0004\b`\u0010\u0016J\u001b\u0010`\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b`\u0010\u0017J\u001b\u0010`\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019¢\u0006\u0004\b`\u0010\u001bJ\u0015\u0010a\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\ba\u0010GJ\u001d\u0010d\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020\u00062\u0006\u0010c\u001a\u00020\u0006¢\u0006\u0004\bd\u0010eJ\u0017\u0010g\u001a\u00020\u000f2\u0006\u0010f\u001a\u00020\u0006H\u0001¢\u0006\u0004\bg\u0010,J$\u0010h\u001a\u00020\u000f2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\u001cH\u0086\b¢\u0006\u0004\bh\u0010AJ\u001b\u0010i\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019¢\u0006\u0004\bi\u0010\u001bJ \u0010j\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\bj\u0010kJ%\u0010o\u001a\u00020\u000f2\u0016\u0010n\u001a\u0012\u0012\u0004\u0012\u00028\u00000lj\b\u0012\u0004\u0012\u00028\u0000`m¢\u0006\u0004\bo\u0010pJ/\u0010r\u001a\u00020\u00062\u0012\u0010q\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u001cH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\br\u0010KJ\u000f\u0010t\u001a\u00020sH\u0001¢\u0006\u0004\bt\u0010uR0\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b\u0005\u0010v\u0012\u0004\b{\u0010%\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR\u001e\u0010|\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b|\u0010}R&\u0010\u0007\u001a\u00020\u00062\u0006\u0010~\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0004\b\u0007\u0010\u007f\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0015\u0010\u0083\u0001\u001a\u00020\u00068Æ\u0002¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0081\u0001R\u0016\u0010\u0087\u0001\u001a\u00030\u0084\u00018Æ\u0002¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001¨\u0006\u008b\u0001"}, d2 = {"Landroidx/compose/runtime/collection/MutableVector;", "T", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "", "content", "", ContentDisposition.Parameters.Size, "<init>", "([Ljava/lang/Object;I)V", "element", "", "add", "(Ljava/lang/Object;)Z", "index", "Lx6/t0;", "(ILjava/lang/Object;)V", "", "elements", "addAll", "(ILjava/util/List;)Z", "(ILandroidx/compose/runtime/collection/MutableVector;)Z", "(Ljava/util/List;)Z", "(Landroidx/compose/runtime/collection/MutableVector;)Z", "([Ljava/lang/Object;)Z", "", "(ILjava/util/Collection;)Z", "(Ljava/util/Collection;)Z", "Lkotlin/Function1;", "predicate", "any", "(Lr7/l;)Z", "reversedAny", "", "asMutableList", "()Ljava/util/List;", "clear", "()V", "contains", "containsAll", "other", "contentEquals", "capacity", "ensureCapacity", "(I)V", "first", "()Ljava/lang/Object;", "(Lr7/l;)Ljava/lang/Object;", "firstOrNull", "R", "initial", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "acc", "operation", "fold", "(Ljava/lang/Object;Lr7/p;)Ljava/lang/Object;", "Lkotlin/Function3;", "foldIndexed", "(Ljava/lang/Object;Lr7/q;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "block", "forEach", "(Lr7/l;)V", "forEachIndexed", "(Lr7/p;)V", "forEachReversed", "forEachReversedIndexed", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "indexOfFirst", "(Lr7/l;)I", "indexOfLast", "isEmpty", "()Z", "isNotEmpty", "last", "lastIndexOf", "lastOrNull", "transform", "map", "(Lr7/l;)[Ljava/lang/Object;", "mapIndexed", "(Lr7/p;)[Ljava/lang/Object;", "mapIndexedNotNull", "(Lr7/p;)Landroidx/compose/runtime/collection/MutableVector;", "mapNotNull", "(Lr7/l;)Landroidx/compose/runtime/collection/MutableVector;", "plusAssign", "(Ljava/lang/Object;)V", "minusAssign", "remove", "removeAll", "removeAt", TtmlNode.START, TtmlNode.END, "removeRange", "(II)V", "newSize", "setSize", "removeIf", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "sortWith", "(Ljava/util/Comparator;)V", "selector", "sumBy", "", "throwNoSuchElementException", "()Ljava/lang/Void;", "[Ljava/lang/Object;", "getContent", "()[Ljava/lang/Object;", "setContent", "([Ljava/lang/Object;)V", "getContent$annotations", "list", "Ljava/util/List;", "<set-?>", "I", "getSize", "()I", "getLastIndex", "lastIndex", "Lx7/i;", "getIndices", "()Lx7/i;", "indices", "MutableVectorList", "SubList", "VectorListIterator", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MutableVector<T> implements RandomAccess {
    public static final int $stable = 8;
    private T[] content;
    private List<T> list;
    private int size;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010+\n\u0002\b\u0012\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u0017H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001a\u0010\u0014J\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001b\u0010\nJ\u001f\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001b\u0010\u001dJ%\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010\u001e\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\u001e\u0010\u000eJ\u000f\u0010 \u001a\u00020\u001cH\u0016¢\u0006\u0004\b \u0010!J\u0015\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"H\u0016¢\u0006\u0004\b#\u0010$J\u001d\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b#\u0010%J\u0017\u0010&\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0004\b&\u0010\nJ\u001d\u0010'\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b'\u0010\u000eJ\u0017\u0010(\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b(\u0010\u0012J\u001d\u0010)\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b)\u0010\u000eJ \u0010*\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b*\u0010+J%\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000fH\u0016¢\u0006\u0004\b.\u0010/R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u00100R\u0014\u00103\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102¨\u00064"}, d2 = {"Landroidx/compose/runtime/collection/MutableVector$MutableVectorList;", "T", "", "Landroidx/compose/runtime/collection/MutableVector;", "vector", "<init>", "(Landroidx/compose/runtime/collection/MutableVector;)V", "element", "", "contains", "(Ljava/lang/Object;)Z", "", "elements", "containsAll", "(Ljava/util/Collection;)Z", "", "index", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "lastIndexOf", "add", "Lx6/t0;", "(ILjava/lang/Object;)V", "addAll", "(ILjava/util/Collection;)Z", "clear", "()V", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "Landroidx/compose/runtime/collection/MutableVector;", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MutableVectorList<T> implements List<T>, e {
        private final MutableVector<T> vector;

        public MutableVectorList(MutableVector<T> mutableVector) {
            this.vector = mutableVector;
        }

        @Override // java.util.List, java.util.Collection
        public boolean add(T element) {
            return this.vector.add(element);
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends T> elements) {
            return this.vector.addAll(index, elements);
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            this.vector.clear();
        }

        @Override // java.util.List, java.util.Collection
        public boolean contains(Object element) {
            return this.vector.contains(element);
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            return this.vector.containsAll(elements);
        }

        @Override // java.util.List
        public T get(int index) {
            MutableVectorKt.checkIndex(this, index);
            return this.vector.getContent()[index];
        }

        public int getSize() {
            return this.vector.getSize();
        }

        @Override // java.util.List
        public int indexOf(Object element) {
            return this.vector.indexOf(element);
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.vector.isEmpty();
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return new VectorListIterator(this, 0);
        }

        @Override // java.util.List
        public int lastIndexOf(Object element) {
            return this.vector.lastIndexOf(element);
        }

        @Override // java.util.List
        public ListIterator<T> listIterator() {
            return new VectorListIterator(this, 0);
        }

        @Override // java.util.List
        public final /* bridge */ T remove(int i10) {
            return removeAt(i10);
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection<? extends Object> elements) {
            return this.vector.removeAll(elements);
        }

        public T removeAt(int index) {
            MutableVectorKt.checkIndex(this, index);
            return this.vector.removeAt(index);
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection<? extends Object> elements) {
            return this.vector.retainAll(elements);
        }

        @Override // java.util.List
        public T set(int index, T element) {
            MutableVectorKt.checkIndex(this, index);
            return this.vector.set(index, element);
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.List
        public List<T> subList(int fromIndex, int toIndex) {
            MutableVectorKt.checkSubIndex(this, fromIndex, toIndex);
            return new SubList(this, fromIndex, toIndex);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return o.a(this);
        }

        @Override // java.util.List
        public void add(int index, T element) {
            this.vector.add(index, element);
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends T> elements) {
            return this.vector.addAll(elements);
        }

        @Override // java.util.List
        public ListIterator<T> listIterator(int index) {
            return new VectorListIterator(this, index);
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object element) {
            return this.vector.remove(element);
        }

        @Override // java.util.List, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) o.b(this, tArr);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0010)\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010+\n\u0002\b\u0013\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B%\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001b\u0010\u0015J\u0017\u0010\u001c\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001c\u0010\fJ\u001f\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001c\u0010\u001eJ%\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010\u001f\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0016¢\u0006\u0004\b\u001f\u0010\u0010J\u000f\u0010!\u001a\u00020\u001dH\u0016¢\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00010#H\u0016¢\u0006\u0004\b$\u0010%J\u001d\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00010#2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b$\u0010&J\u0017\u0010'\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b'\u0010\fJ\u001d\u0010(\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0016¢\u0006\u0004\b(\u0010\u0010J\u0017\u0010)\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b)\u0010\u0013J\u001d\u0010*\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0016¢\u0006\u0004\b*\u0010\u0010J \u0010+\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b+\u0010,J%\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0016¢\u0006\u0004\b/\u00100R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00101R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00102R\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u00102R\u0014\u00105\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Landroidx/compose/runtime/collection/MutableVector$SubList;", "T", "", "list", "", TtmlNode.START, TtmlNode.END, "<init>", "(Ljava/util/List;II)V", "element", "", "contains", "(Ljava/lang/Object;)Z", "", "elements", "containsAll", "(Ljava/util/Collection;)Z", "index", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "lastIndexOf", "add", "Lx6/t0;", "(ILjava/lang/Object;)V", "addAll", "(ILjava/util/Collection;)Z", "clear", "()V", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "Ljava/util/List;", "I", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SubList<T> implements List<T>, e {
        private int end;
        private final List<T> list;
        private final int start;

        public SubList(List<T> list, int i10, int i11) {
            this.list = list;
            this.start = i10;
            this.end = i11;
        }

        @Override // java.util.List, java.util.Collection
        public boolean add(T element) {
            List<T> list = this.list;
            int i10 = this.end;
            this.end = i10 + 1;
            list.add(i10, element);
            return true;
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends T> elements) {
            this.list.addAll(index + this.start, elements);
            this.end = elements.size() + this.end;
            return elements.size() > 0;
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            int i10 = this.end - 1;
            int i11 = this.start;
            if (i11 <= i10) {
                while (true) {
                    this.list.remove(i10);
                    if (i10 == i11) {
                        break;
                    } else {
                        i10--;
                    }
                }
            }
            this.end = this.start;
        }

        @Override // java.util.List, java.util.Collection
        public boolean contains(Object element) {
            int i10 = this.end;
            for (int i11 = this.start; i11 < i10; i11++) {
                if (p.a(this.list.get(i11), element)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public T get(int index) {
            MutableVectorKt.checkIndex(this, index);
            return this.list.get(index + this.start);
        }

        public int getSize() {
            return this.end - this.start;
        }

        @Override // java.util.List
        public int indexOf(Object element) {
            int i10 = this.end;
            for (int i11 = this.start; i11 < i10; i11++) {
                if (p.a(this.list.get(i11), element)) {
                    return i11 - this.start;
                }
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.end == this.start;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return new VectorListIterator(this, 0);
        }

        @Override // java.util.List
        public int lastIndexOf(Object element) {
            int i10 = this.end - 1;
            int i11 = this.start;
            if (i11 > i10) {
                return -1;
            }
            while (!p.a(this.list.get(i10), element)) {
                if (i10 == i11) {
                    return -1;
                }
                i10--;
            }
            return i10 - this.start;
        }

        @Override // java.util.List
        public ListIterator<T> listIterator() {
            return new VectorListIterator(this, 0);
        }

        @Override // java.util.List
        public final /* bridge */ T remove(int i10) {
            return removeAt(i10);
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection<? extends Object> elements) {
            int i10 = this.end;
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
            return i10 != this.end;
        }

        public T removeAt(int index) {
            MutableVectorKt.checkIndex(this, index);
            this.end--;
            return this.list.remove(index + this.start);
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection<? extends Object> elements) {
            int i10 = this.end;
            int i11 = i10 - 1;
            int i12 = this.start;
            if (i12 <= i11) {
                while (true) {
                    if (!elements.contains(this.list.get(i11))) {
                        this.list.remove(i11);
                        this.end--;
                    }
                    if (i11 == i12) {
                        break;
                    }
                    i11--;
                }
            }
            return i10 != this.end;
        }

        @Override // java.util.List
        public T set(int index, T element) {
            MutableVectorKt.checkIndex(this, index);
            return this.list.set(index + this.start, element);
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.List
        public List<T> subList(int fromIndex, int toIndex) {
            MutableVectorKt.checkSubIndex(this, fromIndex, toIndex);
            return new SubList(this, fromIndex, toIndex);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return o.a(this);
        }

        @Override // java.util.List
        public void add(int index, T element) {
            this.list.add(index + this.start, element);
            this.end++;
        }

        @Override // java.util.List
        public ListIterator<T> listIterator(int index) {
            return new VectorListIterator(this, index);
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object element) {
            int i10 = this.end;
            for (int i11 = this.start; i11 < i10; i11++) {
                if (p.a(this.list.get(i11), element)) {
                    this.list.remove(i11);
                    this.end--;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) o.b(this, tArr);
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends T> elements) {
            this.list.addAll(this.end, elements);
            this.end = elements.size() + this.end;
            return elements.size() > 0;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0011\u0010\u000bJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\rJ\u000f\u0010\u0015\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0015\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0019\u0010\u0018R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/collection/MutableVector$VectorListIterator;", "T", "", "", "list", "", "index", "<init>", "(Ljava/util/List;I)V", "", "hasNext", "()Z", LinkHeader.Rel.Next, "()Ljava/lang/Object;", "Lx6/t0;", "remove", "()V", "hasPrevious", "nextIndex", "()I", "previous", "previousIndex", "element", "add", "(Ljava/lang/Object;)V", "set", "Ljava/util/List;", "I", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class VectorListIterator<T> implements ListIterator<T>, f {
        private int index;
        private final List<T> list;

        public VectorListIterator(List<T> list, int i10) {
            this.list = list;
            this.index = i10;
        }

        @Override // java.util.ListIterator
        public void add(T element) {
            this.list.add(this.index, element);
            this.index++;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.index < this.list.size();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public T next() {
            List<T> list = this.list;
            int i10 = this.index;
            this.index = i10 + 1;
            return list.get(i10);
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public T previous() {
            int i10 = this.index - 1;
            this.index = i10;
            return this.list.get(i10);
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            int i10 = this.index - 1;
            this.index = i10;
            this.list.remove(i10);
        }

        @Override // java.util.ListIterator
        public void set(T element) {
            this.list.set(this.index, element);
        }
    }

    public MutableVector(T[] tArr, int i10) {
        this.content = tArr;
        this.size = i10;
    }

    public static /* synthetic */ void getContent$annotations() {
    }

    public final boolean add(T element) {
        ensureCapacity(this.size + 1);
        T[] tArr = this.content;
        int i10 = this.size;
        tArr[i10] = element;
        this.size = i10 + 1;
        return true;
    }

    public final boolean addAll(int index, List<? extends T> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(elements.size() + this.size);
        T[] tArr = this.content;
        if (index != this.size) {
            r.N(tArr, tArr, elements.size() + index, index, this.size);
        }
        int size = elements.size();
        for (int i10 = 0; i10 < size; i10++) {
            tArr[index + i10] = elements.get(i10);
        }
        this.size = elements.size() + this.size;
        return true;
    }

    public final boolean any(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            while (!((Boolean) predicate.invoke(content[i10])).booleanValue()) {
                i10++;
                if (i10 >= size) {
                }
            }
            return true;
        }
        return false;
    }

    public final List<T> asMutableList() {
        List<T> list = this.list;
        if (list != null) {
            return list;
        }
        MutableVectorList mutableVectorList = new MutableVectorList(this);
        this.list = mutableVectorList;
        return mutableVectorList;
    }

    public final void clear() {
        T[] tArr = this.content;
        int size = getSize();
        while (true) {
            size--;
            if (-1 >= size) {
                this.size = 0;
                return;
            }
            tArr[size] = null;
        }
    }

    public final boolean contains(T element) {
        int size = getSize() - 1;
        if (size >= 0) {
            for (int i10 = 0; !p.a(getContent()[i10], element); i10++) {
                if (i10 != size) {
                }
            }
            return true;
        }
        return false;
    }

    public final boolean containsAll(List<? extends T> elements) {
        int size = elements.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (!contains(elements.get(i10))) {
                return false;
            }
        }
        return true;
    }

    public final boolean contentEquals(MutableVector<T> other) {
        if (other.size != this.size) {
            return false;
        }
        int size = getSize() - 1;
        if (size >= 0) {
            for (int i10 = 0; p.a(other.getContent()[i10], getContent()[i10]); i10++) {
                if (i10 != size) {
                }
            }
            return false;
        }
        return true;
    }

    public final void ensureCapacity(int capacity) {
        T[] tArr = this.content;
        if (tArr.length < capacity) {
            this.content = (T[]) Arrays.copyOf(tArr, Math.max(capacity, tArr.length * 2));
        }
    }

    public final T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("MutableVector is empty.");
        }
        return getContent()[0];
    }

    public final T firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return getContent()[0];
    }

    public final <R> R fold(R initial, r7.p<? super R, ? super T, ? extends R> operation) {
        int size = getSize();
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            do {
                initial = (R) operation.invoke(initial, content[i10]);
                i10++;
            } while (i10 < size);
        }
        return initial;
    }

    public final <R> R foldIndexed(R initial, q<? super Integer, ? super R, ? super T, ? extends R> operation) {
        int size = getSize();
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            do {
                initial = (R) operation.invoke(Integer.valueOf(i10), initial, content[i10]);
                i10++;
            } while (i10 < size);
        }
        return initial;
    }

    public final <R> R foldRight(R initial, r7.p<? super T, ? super R, ? extends R> operation) {
        int size = getSize();
        if (size > 0) {
            int i10 = size - 1;
            T[] content = getContent();
            do {
                initial = (R) operation.invoke(content[i10], initial);
                i10--;
            } while (i10 >= 0);
        }
        return initial;
    }

    public final <R> R foldRightIndexed(R initial, q<? super Integer, ? super T, ? super R, ? extends R> operation) {
        int size = getSize();
        if (size > 0) {
            int i10 = size - 1;
            T[] content = getContent();
            do {
                initial = (R) operation.invoke(Integer.valueOf(i10), content[i10], initial);
                i10--;
            } while (i10 >= 0);
        }
        return initial;
    }

    public final void forEach(l<? super T, t0> block) {
        int size = getSize();
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            do {
                block.invoke(content[i10]);
                i10++;
            } while (i10 < size);
        }
    }

    public final void forEachIndexed(r7.p<? super Integer, ? super T, t0> block) {
        int size = getSize();
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            do {
                block.invoke(Integer.valueOf(i10), content[i10]);
                i10++;
            } while (i10 < size);
        }
    }

    public final void forEachReversed(l<? super T, t0> block) {
        int size = getSize();
        if (size > 0) {
            int i10 = size - 1;
            T[] content = getContent();
            do {
                block.invoke(content[i10]);
                i10--;
            } while (i10 >= 0);
        }
    }

    public final void forEachReversedIndexed(r7.p<? super Integer, ? super T, t0> block) {
        if (getSize() > 0) {
            int size = getSize() - 1;
            T[] content = getContent();
            do {
                block.invoke(Integer.valueOf(size), content[size]);
                size--;
            } while (size >= 0);
        }
    }

    public final T get(int index) {
        return getContent()[index];
    }

    public final T[] getContent() {
        return this.content;
    }

    public final i getIndices() {
        return new i(0, getSize() - 1, 1);
    }

    public final int getLastIndex() {
        return getSize() - 1;
    }

    public final int getSize() {
        return this.size;
    }

    public final int indexOf(T element) {
        int i10 = this.size;
        if (i10 <= 0) {
            return -1;
        }
        T[] tArr = this.content;
        int i11 = 0;
        while (!p.a(element, tArr[i11])) {
            i11++;
            if (i11 >= i10) {
                return -1;
            }
        }
        return i11;
    }

    public final int indexOfFirst(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size <= 0) {
            return -1;
        }
        T[] content = getContent();
        int i10 = 0;
        while (!((Boolean) predicate.invoke(content[i10])).booleanValue()) {
            i10++;
            if (i10 >= size) {
                return -1;
            }
        }
        return i10;
    }

    public final int indexOfLast(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size <= 0) {
            return -1;
        }
        int i10 = size - 1;
        T[] content = getContent();
        while (!((Boolean) predicate.invoke(content[i10])).booleanValue()) {
            i10--;
            if (i10 < 0) {
                return -1;
            }
        }
        return i10;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isNotEmpty() {
        return this.size != 0;
    }

    public final T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("MutableVector is empty.");
        }
        return getContent()[getSize() - 1];
    }

    public final int lastIndexOf(T element) {
        int i10 = this.size;
        if (i10 <= 0) {
            return -1;
        }
        int i11 = i10 - 1;
        T[] tArr = this.content;
        while (!p.a(element, tArr[i11])) {
            i11--;
            if (i11 < 0) {
                return -1;
            }
        }
        return i11;
    }

    public final T lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return getContent()[getSize() - 1];
    }

    public final <R> R[] map(l<? super T, ? extends R> transform) {
        getSize();
        p.h();
        throw null;
    }

    public final <R> R[] mapIndexed(r7.p<? super Integer, ? super T, ? extends R> transform) {
        getSize();
        p.h();
        throw null;
    }

    public final <R> MutableVector<R> mapIndexedNotNull(r7.p<? super Integer, ? super T, ? extends R> transform) {
        getSize();
        p.h();
        throw null;
    }

    public final <R> MutableVector<R> mapNotNull(l<? super T, ? extends R> transform) {
        getSize();
        p.h();
        throw null;
    }

    public final void minusAssign(T element) {
        remove(element);
    }

    public final void plusAssign(T element) {
        add(element);
    }

    public final boolean remove(T element) {
        int iIndexOf = indexOf(element);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(List<? extends T> elements) {
        int i10 = this.size;
        int size = elements.size();
        for (int i11 = 0; i11 < size; i11++) {
            remove(elements.get(i11));
        }
        return i10 != this.size;
    }

    public final T removeAt(int index) {
        T[] tArr = this.content;
        T t2 = tArr[index];
        if (index != getSize() - 1) {
            r.N(tArr, tArr, index, index + 1, this.size);
        }
        int i10 = this.size - 1;
        this.size = i10;
        tArr[i10] = null;
        return t2;
    }

    public final void removeIf(l<? super T, Boolean> predicate) {
        int size = getSize();
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            if (((Boolean) predicate.invoke(getContent()[i11])).booleanValue()) {
                i10++;
            } else if (i10 > 0) {
                getContent()[i11 - i10] = getContent()[i11];
            }
        }
        int i12 = size - i10;
        Arrays.fill(getContent(), i12, size, (Object) null);
        setSize(i12);
    }

    public final void removeRange(int start, int end) {
        if (end > start) {
            int i10 = this.size;
            if (end < i10) {
                T[] tArr = this.content;
                r.N(tArr, tArr, start, end, i10);
            }
            int i11 = this.size - (end - start);
            int size = getSize() - 1;
            if (i11 <= size) {
                int i12 = i11;
                while (true) {
                    this.content[i12] = null;
                    if (i12 == size) {
                        break;
                    } else {
                        i12++;
                    }
                }
            }
            this.size = i11;
        }
    }

    public final boolean retainAll(Collection<? extends T> elements) {
        int i10 = this.size;
        for (int size = getSize() - 1; -1 < size; size--) {
            if (!elements.contains(getContent()[size])) {
                removeAt(size);
            }
        }
        return i10 != this.size;
    }

    public final boolean reversedAny(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size <= 0) {
            return false;
        }
        int i10 = size - 1;
        T[] content = getContent();
        while (!((Boolean) predicate.invoke(content[i10])).booleanValue()) {
            i10--;
            if (i10 < 0) {
                return false;
            }
        }
        return true;
    }

    public final T set(int index, T element) {
        T[] tArr = this.content;
        T t2 = tArr[index];
        tArr[index] = element;
        return t2;
    }

    public final void setContent(T[] tArr) {
        this.content = tArr;
    }

    public final void setSize(int newSize) {
        this.size = newSize;
    }

    public final void sortWith(Comparator<T> comparator) {
        Arrays.sort(this.content, 0, this.size, comparator);
    }

    public final int sumBy(l<? super T, Integer> selector) {
        int size = getSize();
        int iIntValue = 0;
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            do {
                iIntValue += ((Number) selector.invoke(content[i10])).intValue();
                i10++;
            } while (i10 < size);
        }
        return iIntValue;
    }

    public final Void throwNoSuchElementException() {
        throw new NoSuchElementException("MutableVector contains no element matching the predicate.");
    }

    public final boolean containsAll(Collection<? extends T> elements) {
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final T firstOrNull(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size <= 0) {
            return null;
        }
        T[] content = getContent();
        int i10 = 0;
        do {
            T t2 = content[i10];
            if (((Boolean) predicate.invoke(t2)).booleanValue()) {
                return t2;
            }
            i10++;
        } while (i10 < size);
        return null;
    }

    public final void add(int index, T element) {
        ensureCapacity(this.size + 1);
        T[] tArr = this.content;
        int i10 = this.size;
        if (index != i10) {
            r.N(tArr, tArr, index + 1, index, i10);
        }
        tArr[index] = element;
        this.size++;
    }

    public final T first(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size > 0) {
            T[] content = getContent();
            int i10 = 0;
            do {
                T t2 = content[i10];
                if (((Boolean) predicate.invoke(t2)).booleanValue()) {
                    return t2;
                }
                i10++;
            } while (i10 < size);
        }
        throwNoSuchElementException();
        throw new KotlinNothingValueException();
    }

    public final T lastOrNull(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size <= 0) {
            return null;
        }
        int i10 = size - 1;
        T[] content = getContent();
        do {
            T t2 = content[i10];
            if (((Boolean) predicate.invoke(t2)).booleanValue()) {
                return t2;
            }
            i10--;
        } while (i10 >= 0);
        return null;
    }

    public final T last(l<? super T, Boolean> predicate) {
        int size = getSize();
        if (size > 0) {
            int i10 = size - 1;
            T[] content = getContent();
            do {
                T t2 = content[i10];
                if (((Boolean) predicate.invoke(t2)).booleanValue()) {
                    return t2;
                }
                i10--;
            } while (i10 >= 0);
        }
        throwNoSuchElementException();
        throw new KotlinNothingValueException();
    }

    public final boolean removeAll(MutableVector<T> elements) {
        int i10 = this.size;
        int size = elements.getSize() - 1;
        if (size >= 0) {
            int i11 = 0;
            while (true) {
                remove(elements.getContent()[i11]);
                if (i11 == size) {
                    break;
                }
                i11++;
            }
        }
        return i10 != this.size;
    }

    public final boolean containsAll(MutableVector<T> elements) {
        int i10 = new i(0, elements.getSize() - 1, 1).f22620l;
        if (i10 >= 0) {
            for (int i11 = 0; contains(elements.getContent()[i11]); i11++) {
                if (i11 != i10) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean removeAll(Collection<? extends T> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        int i10 = this.size;
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
        return i10 != this.size;
    }

    public final boolean addAll(int index, MutableVector<T> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(this.size + elements.size);
        T[] tArr = this.content;
        int i10 = this.size;
        if (index != i10) {
            r.N(tArr, tArr, elements.size + index, index, i10);
        }
        r.N(elements.content, tArr, index, 0, elements.size);
        this.size += elements.size;
        return true;
    }

    public final boolean addAll(List<? extends T> elements) {
        return addAll(getSize(), (List) elements);
    }

    public final boolean addAll(MutableVector<T> elements) {
        return addAll(getSize(), elements);
    }

    public final boolean addAll(T[] elements) {
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(this.size + elements.length);
        r.R(elements, this.content, this.size, 0, 0, 12);
        this.size += elements.length;
        return true;
    }

    public final boolean addAll(int index, Collection<? extends T> elements) {
        int i10 = 0;
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(elements.size() + this.size);
        T[] tArr = this.content;
        if (index != this.size) {
            r.N(tArr, tArr, elements.size() + index, index, this.size);
        }
        for (T t2 : elements) {
            int i11 = i10 + 1;
            if (i10 >= 0) {
                tArr[i10 + index] = t2;
                i10 = i11;
            } else {
                a.Q();
                throw null;
            }
        }
        this.size = elements.size() + this.size;
        return true;
    }

    public final boolean addAll(Collection<? extends T> elements) {
        return addAll(this.size, elements);
    }
}
