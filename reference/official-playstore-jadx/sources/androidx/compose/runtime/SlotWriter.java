package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.collections.z;
import r7.p;
import t.v;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0018\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0000\u0018\u0000 ÿ\u00012\u00020\u0001:\u0002ÿ\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\fJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\fJ\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0012\u0010\nJ\u0015\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\nJ\u001d\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0017\u0010\u000fJ\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0017\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\fJ\u0015\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\b¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u001e¢\u0006\u0004\b!\u0010\"J\u0019\u0010$\u001a\u0004\u0018\u00010\u00012\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b&\u0010'J\u0015\u0010)\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0006¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b-\u0010,J\r\u0010.\u001a\u00020\u001e¢\u0006\u0004\b.\u0010\"J\u0015\u00101\u001a\u00020\u001e2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b1\u00102J\u001d\u00104\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\u0006\u0010#\u001a\u00020/¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u00020\u001e¢\u0006\u0004\b6\u0010\"J\u0017\u00107\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b7\u0010,J\u001f\u00107\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b7\u0010'J\u0017\u00108\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b8\u0010,J\u0017\u00109\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b9\u0010,J!\u00109\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b9\u0010:J\u001d\u0010;\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b;\u0010<J)\u00109\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010#\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b9\u0010=J\u000f\u0010>\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b>\u0010?J\u001f\u0010@\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b@\u0010AJ\u001f\u0010@\u001a\u0004\u0018\u00010\u00012\u0006\u0010B\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b@\u0010CJ<\u0010F\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u001a\u0010E\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u001e0DH\u0086\b¢\u0006\u0004\bF\u0010GJ\u0017\u0010I\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0006H\u0000¢\u0006\u0004\bH\u0010\fJ\u0017\u0010K\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0006H\u0000¢\u0006\u0004\bJ\u0010\fJ\u0017\u0010M\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0006H\u0000¢\u0006\u0004\bL\u0010\fJ\u0015\u0010O\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020\u0006¢\u0006\u0004\bO\u0010*J\u0015\u0010P\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bP\u0010QJ\r\u0010R\u001a\u00020\u001e¢\u0006\u0004\bR\u0010\"J\r\u0010S\u001a\u00020\u001e¢\u0006\u0004\bS\u0010\"J\r\u0010T\u001a\u00020\u001e¢\u0006\u0004\bT\u0010\"J\r\u0010U\u001a\u00020\u001e¢\u0006\u0004\bU\u0010\"J\u0015\u0010U\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u0006¢\u0006\u0004\bU\u0010*J\u001f\u0010U\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\b\u0010V\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\bU\u0010WJ\u001f\u0010Y\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\b\u0010X\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\bY\u0010WJ)\u0010Y\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\b\u0010X\u001a\u0004\u0018\u00010\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\bY\u0010ZJ)\u0010\\\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\b\u0010X\u001a\u0004\u0018\u00010\u00012\b\u0010[\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\\\u0010ZJ\u001f\u0010\\\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\b\u0010[\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\\\u0010WJ\r\u0010]\u001a\u00020\u0006¢\u0006\u0004\b]\u0010^J\u0015\u0010_\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b_\u0010*J\u0015\u0010_\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b_\u0010QJ\r\u0010`\u001a\u00020\u0006¢\u0006\u0004\b`\u0010^J\r\u0010a\u001a\u00020\b¢\u0006\u0004\ba\u0010bJ\u0015\u0010d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010c¢\u0006\u0004\bd\u0010eJR\u0010i\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u000628\u0010E\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(h\u0012\u0004\u0012\u00020\u001e0DH\u0086\b¢\u0006\u0004\bi\u0010jJR\u0010k\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u000628\u0010E\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(h\u0012\u0004\u0012\u00020\u001e0DH\u0086\b¢\u0006\u0004\bk\u0010jJ\u0015\u0010m\u001a\u00020\u001e2\u0006\u0010l\u001a\u00020\u0006¢\u0006\u0004\bm\u0010*J+\u0010p\u001a\b\u0012\u0004\u0012\u00020\u00180o2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010l\u001a\u00020\u00062\u0006\u0010n\u001a\u00020\u0000¢\u0006\u0004\bp\u0010qJ-\u0010s\u001a\b\u0012\u0004\u0012\u00020\u00180o2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010r\u001a\u00020\b¢\u0006\u0004\bs\u0010tJ\r\u0010u\u001a\u00020\u001e¢\u0006\u0004\bu\u0010\"J+\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00180o2\u0006\u0010l\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\bv\u0010wJ\u0017\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010xJ\u0017\u0010y\u001a\u00020\u001e2\b\b\u0002\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\by\u0010*J\u0015\u0010z\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bz\u0010\u001cJ\u000f\u0010{\u001a\u00020/H\u0016¢\u0006\u0004\b{\u0010|J\u0019\u0010~\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\u0006H\u0000¢\u0006\u0004\b}\u0010xJ\r\u0010\u007f\u001a\u00020/¢\u0006\u0004\b\u007f\u0010|J\u0011\u0010\u0081\u0001\u001a\u00020\u001eH\u0000¢\u0006\u0005\b\u0080\u0001\u0010\"J\u0011\u0010\u0083\u0001\u001a\u00020\u001eH\u0000¢\u0006\u0005\b\u0082\u0001\u0010\"J'\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0006\u0010\u001b\u001a\u00020\u00062\b\u00100\u001a\u0004\u0018\u00010/H\u0002¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J4\u0010U\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u00062\b\u0010X\u001a\u0004\u0018\u00010\u00012\u0006\u0010\t\u001a\u00020\b2\b\u0010[\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0005\bU\u0010\u0087\u0001J\u0019\u0010\u0088\u0001\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u0088\u0001\u0010\nJ\u0019\u0010\u0089\u0001\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u0089\u0001\u0010\nJ\u0011\u0010\u008a\u0001\u001a\u00020\u001eH\u0002¢\u0006\u0005\b\u008a\u0001\u0010\"J\u0019\u0010\u008b\u0001\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u008b\u0001\u0010*J#\u0010\u008d\u0001\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u00062\u0007\u00109\u001a\u00030\u008c\u0001H\u0002¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u0019\u0010\u008f\u0001\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u008f\u0001\u0010\nJ\u0011\u0010\u0090\u0001\u001a\u00020\u001eH\u0002¢\u0006\u0005\b\u0090\u0001\u0010\"J\u0011\u0010\u0091\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u0091\u0001\u0010^J+\u0010\u0093\u0001\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010]\u001a\u00020\u00062\u0007\u0010\u0092\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\u0019\u0010\u0095\u0001\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u0095\u0001\u0010*J\"\u0010\u0096\u0001\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J\u0011\u0010\u0098\u0001\u001a\u00020\u001eH\u0002¢\u0006\u0005\b\u0098\u0001\u0010\"J\u001a\u0010\u009a\u0001\u001a\u00020\u001e2\u0007\u0010\u0099\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u009a\u0001\u0010*J#\u0010\u009b\u0001\u001a\u00020\u001e2\u0007\u0010\u0099\u0001\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0006\b\u009b\u0001\u0010\u0097\u0001J#\u0010\u009e\u0001\u001a\u00020\b2\u0007\u0010\u009c\u0001\u001a\u00020\u00062\u0007\u0010\u009d\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u009e\u0001\u0010\u0016J\u001d\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0006\b\u009f\u0001\u0010 \u0001J,\u0010¡\u0001\u001a\u00020\u001e2\u0007\u0010\u009c\u0001\u001a\u00020\u00062\u0007\u0010\u009d\u0001\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0006\b¡\u0001\u0010\u0094\u0001J#\u0010¢\u0001\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0005\b¢\u0001\u0010WJ$\u0010¥\u0001\u001a\u00020\u001e2\u0007\u0010£\u0001\u001a\u00020\u00062\u0007\u0010¤\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b¥\u0001\u0010\u0097\u0001JQ\u0010ª\u0001\u001a\u00020\b2\u0007\u0010¦\u0001\u001a\u00020\u00062\u0007\u0010\u0099\u0001\u001a\u00020\u00062+\u0010©\u0001\u001a&\u0012\u0004\u0012\u00020\u0018\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u00010§\u0001j\u0012\u0012\u0004\u0012\u00020\u0018\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u0001`¨\u0001H\u0002¢\u0006\u0006\bª\u0001\u0010«\u0001J-\u0010®\u0001\u001a\u00020\u001e2\u0007\u0010¬\u0001\u001a\u00020\u00062\u0007\u0010\u00ad\u0001\u001a\u00020\u00062\u0007\u0010\u0099\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b®\u0001\u0010\u0094\u0001J$\u0010±\u0001\u001a\u00020\u001e*\b0¯\u0001j\u0003`°\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0006\b±\u0001\u0010²\u0001J\u0019\u0010³\u0001\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0005\b³\u0001\u0010\fJ\u001a\u0010µ\u0001\u001a\u00020\u00062\u0007\u0010´\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0005\bµ\u0001\u0010\fJ\u001d\u0010\u001b\u001a\u00020\u0006*\u00030¶\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0005\b\u001b\u0010·\u0001J\u0019\u0010´\u0001\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0005\b´\u0001\u0010\fJ \u0010´\u0001\u001a\u00020\u0006*\u00030¶\u00012\u0007\u0010¸\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b´\u0001\u0010·\u0001J \u0010¹\u0001\u001a\u00020\u0006*\u00030¶\u00012\u0007\u0010¸\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b¹\u0001\u0010·\u0001J)\u0010º\u0001\u001a\u00020\u001e*\u00030¶\u00012\u0007\u0010¸\u0001\u001a\u00020\u00062\u0007\u0010´\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\bº\u0001\u0010»\u0001J \u0010¼\u0001\u001a\u00020\u0006*\u00030¶\u00012\u0007\u0010¸\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b¼\u0001\u0010·\u0001J \u0010½\u0001\u001a\u00020\u0006*\u00030¶\u00012\u0007\u0010¸\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\b½\u0001\u0010·\u0001J\u001d\u0010¾\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060o*\u00030¶\u0001H\u0002¢\u0006\u0006\b¾\u0001\u0010¿\u0001J\u0018\u0010À\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060oH\u0002¢\u0006\u0006\bÀ\u0001\u0010Á\u0001J5\u0010Ä\u0001\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0007\u0010¦\u0001\u001a\u00020\u00062\u0007\u0010Â\u0001\u001a\u00020\u00062\u0007\u0010Ã\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\bÄ\u0001\u0010Å\u0001J,\u0010Æ\u0001\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0007\u0010Â\u0001\u001a\u00020\u00062\u0007\u0010Ã\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001J\"\u0010È\u0001\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0007\u0010¦\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0005\bÈ\u0001\u0010<J\u0019\u0010É\u0001\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0005\bÉ\u0001\u0010\fR\u001d\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\u000f\n\u0005\b\u0003\u0010Ê\u0001\u001a\u0006\bË\u0001\u0010Ì\u0001R\u001a\u0010Í\u0001\u001a\u00030¶\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÍ\u0001\u0010Î\u0001R\"\u0010Ð\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010Ï\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÐ\u0001\u0010Ñ\u0001R+\u0010Ô\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00180Ò\u0001j\t\u0012\u0004\u0012\u00020\u0018`Ó\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010Õ\u0001R=\u0010©\u0001\u001a&\u0012\u0004\u0012\u00020\u0018\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u00010§\u0001j\u0012\u0012\u0004\u0012\u00020\u0018\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u0001`¨\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b©\u0001\u0010Ö\u0001R#\u0010Ù\u0001\u001a\f\u0012\u0005\u0012\u00030Ø\u0001\u0018\u00010×\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÙ\u0001\u0010Ú\u0001R\u0019\u0010Û\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÛ\u0001\u0010Ü\u0001R\u0019\u0010Ý\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÝ\u0001\u0010Ü\u0001R\u0019\u0010Þ\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÞ\u0001\u0010Ü\u0001R\u0019\u0010ß\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bß\u0001\u0010Ü\u0001R\u0019\u0010à\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bà\u0001\u0010Ü\u0001R\u0019\u0010á\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bá\u0001\u0010Ü\u0001R\u0019\u0010â\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bâ\u0001\u0010Ü\u0001R\u0019\u0010ã\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bã\u0001\u0010Ü\u0001R\u0017\u0010\u000b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u000b\u0010Ü\u0001R\u0018\u0010å\u0001\u001a\u00030ä\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bå\u0001\u0010æ\u0001R\u0018\u0010ç\u0001\u001a\u00030ä\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bç\u0001\u0010æ\u0001R\u0018\u0010è\u0001\u001a\u00030ä\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bè\u0001\u0010æ\u0001R)\u0010ê\u0001\u001a\u00020\u00062\u0007\u0010é\u0001\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0006\bê\u0001\u0010Ü\u0001\u001a\u0005\bë\u0001\u0010^R)\u0010ì\u0001\u001a\u00020\u00062\u0007\u0010é\u0001\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0006\bì\u0001\u0010Ü\u0001\u001a\u0005\bí\u0001\u0010^R'\u0010\u001b\u001a\u00020\u00062\u0007\u0010é\u0001\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0005\b\u001b\u0010Ü\u0001\u001a\u0005\bî\u0001\u0010^R)\u0010ï\u0001\u001a\u00020\b2\u0007\u0010é\u0001\u001a\u00020\b8\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0006\bï\u0001\u0010ð\u0001\u001a\u0005\bñ\u0001\u0010bR\u001c\u0010ò\u0001\u001a\u0005\u0018\u00010\u008c\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bò\u0001\u0010ó\u0001R\u0013\u0010ô\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\bô\u0001\u0010bR\u0013\u0010ö\u0001\u001a\u00020\u00068F¢\u0006\u0007\u001a\u0005\bõ\u0001\u0010^R\u0011\u0010\t\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010bR\u0013\u0010ø\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\b÷\u0001\u0010bR\u0013\u0010ú\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\bù\u0001\u0010bR\u0016\u0010\u0099\u0001\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bû\u0001\u0010^R\u0016\u0010ý\u0001\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bü\u0001\u0010^R\u0016\u0010Ã\u0001\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bþ\u0001\u0010^¨\u0006\u0080\u0002"}, d2 = {"Landroidx/compose/runtime/SlotWriter;", "", "Landroidx/compose/runtime/SlotTable;", "table", "<init>", "(Landroidx/compose/runtime/SlotTable;)V", "", "index", "", "isNode", "(I)Z", "nodeCount", "(I)I", "groupKey", "groupObjectKey", "(I)Ljava/lang/Object;", "groupSize", "groupAux", "indexInParent", "indexInCurrentGroup", "group", "indexInGroup", "(II)Z", "node", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "(Landroidx/compose/runtime/Anchor;)Ljava/lang/Object;", "parent", "(Landroidx/compose/runtime/Anchor;)I", "normalClose", "Lx6/t0;", "close", "(Z)V", "reset", "()V", "value", "update", "(Ljava/lang/Object;)Ljava/lang/Object;", "appendSlot", "(Landroidx/compose/runtime/Anchor;Ljava/lang/Object;)V", "count", "trimTailSlots", "(I)V", "updateAux", "(Ljava/lang/Object;)V", "insertAux", "updateToTableMaps", "", "sourceInformation", "recordGroupSourceInformation", "(Ljava/lang/String;)V", "key", "recordGrouplessCallSourceInformationStart", "(ILjava/lang/String;)V", "recordGrouplessCallSourceInformationEnd", "updateNode", "updateParentNode", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "slotIndexOfGroupSlotIndex", "(II)I", "(IILjava/lang/Object;)Ljava/lang/Object;", "skip", "()Ljava/lang/Object;", "slot", "(Landroidx/compose/runtime/Anchor;I)Ljava/lang/Object;", "groupIndex", "(II)Ljava/lang/Object;", "Lkotlin/Function2;", "block", "forEachTailSlot", "(IILr7/p;)V", "slotsStartIndex$runtime_release", "slotsStartIndex", "slotsEndIndex$runtime_release", "slotsEndIndex", "slotsEndAllIndex$runtime_release", "slotsEndAllIndex", "amount", "advanceBy", "seek", "(Landroidx/compose/runtime/Anchor;)V", "skipToGroupEnd", "beginInsert", "endInsert", "startGroup", "dataKey", "(ILjava/lang/Object;)V", "objectKey", "startNode", "(ILjava/lang/Object;Ljava/lang/Object;)V", "aux", "startData", "endGroup", "()I", "ensureStarted", "skipGroup", "removeGroup", "()Z", "", "groupSlots", "()Ljava/util/Iterator;", "Lx6/y;", ContentDisposition.Parameters.Name, "data", "forEachData", "(ILr7/p;)V", "forAllData", "offset", "moveGroup", "writer", "", "moveTo", "(Landroidx/compose/runtime/Anchor;ILandroidx/compose/runtime/SlotWriter;)Ljava/util/List;", "removeSourceGroup", "moveFrom", "(Landroidx/compose/runtime/SlotTable;IZ)Ljava/util/List;", "bashCurrentGroup", "moveIntoGroupFrom", "(ILandroidx/compose/runtime/SlotTable;I)Ljava/util/List;", "(I)Landroidx/compose/runtime/Anchor;", "markGroup", "anchorIndex", "toString", "()Ljava/lang/String;", "tryAnchor$runtime_release", "tryAnchor", "toDebugString", "verifyDataAnchors$runtime_release", "verifyDataAnchors", "verifyParentAnchors$runtime_release", "verifyParentAnchors", "Landroidx/compose/runtime/GroupSourceInformation;", "groupSourceInformationFor", "(ILjava/lang/String;)Landroidx/compose/runtime/GroupSourceInformation;", "(ILjava/lang/Object;ZLjava/lang/Object;)V", "containsGroupMark", "containsAnyGroupMarks", "recalculateMarks", "updateContainsMark", "Landroidx/compose/runtime/PrioritySet;", "updateContainsMarkNow", "(ILandroidx/compose/runtime/PrioritySet;)V", "childContainsAnyMarks", "saveCurrentGroupEnd", "restoreCurrentGroupEnd", "firstChild", "fixParentAnchorsFor", "(III)V", "moveGroupGapTo", "moveSlotGapTo", "(II)V", "clearSlotGap", ContentDisposition.Parameters.Size, "insertGroups", "insertSlots", TtmlNode.START, "len", "removeGroups", "sourceInformationOf", "(I)Landroidx/compose/runtime/GroupSourceInformation;", "removeSlots", "updateNodeOfGroup", "previousGapStart", "newGapStart", "updateAnchors", "gapStart", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "sourceInformationMap", "removeAnchors", "(IILjava/util/HashMap;)Z", "originalLocation", "newLocation", "moveAnchors", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "groupAsString", "(Ljava/lang/StringBuilder;I)V", "groupIndexToAddress", "dataIndex", "dataIndexToDataAddress", "", "([II)I", "address", "slotIndex", "updateDataIndex", "([III)V", "nodeIndex", "auxIndex", "dataIndexes", "([I)Ljava/util/List;", UserMetadata.KEYDATA_FILENAME, "()Ljava/util/List;", "gapLen", "capacity", "dataIndexToDataAnchor", "(IIII)I", "dataAnchorToDataIndex", "(III)I", "parentIndexToAnchor", "parentAnchorToIndex", "Landroidx/compose/runtime/SlotTable;", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "groups", "[I", "", "slots", "[Ljava/lang/Object;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "anchors", "Ljava/util/ArrayList;", "Ljava/util/HashMap;", "Lt/v;", "Lt/w;", "calledByMap", "Lt/v;", "groupGapStart", "I", "groupGapLen", "currentSlot", "currentSlotEnd", "slotsGapStart", "slotsGapLen", "slotsGapOwner", "insertCount", "Landroidx/compose/runtime/IntStack;", "startStack", "Landroidx/compose/runtime/IntStack;", "endStack", "nodeCountStack", "<set-?>", "currentGroup", "getCurrentGroup", "currentGroupEnd", "getCurrentGroupEnd", "getParent", "closed", "Z", "getClosed", "pendingRecalculateMarks", "Landroidx/compose/runtime/PrioritySet;", "isGroupEnd", "getSlotsSize", "slotsSize", "getCollectingSourceInformation", "collectingSourceInformation", "getCollectingCalledInformation", "collectingCalledInformation", "getSize$runtime_release", "getCurrentGroupSlotIndex", "currentGroupSlotIndex", "getCapacity", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SlotWriter {
    private ArrayList<Anchor> anchors;
    private v calledByMap;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private PrioritySet pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private final SlotTable table;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private final IntStack startStack = new IntStack();
    private final IntStack endStack = new IntStack();
    private final IntStack nodeCountStack = new IntStack();
    private int parent = -1;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fH\u0002¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/SlotWriter$Companion;", "", "()V", "moveGroup", "", "Landroidx/compose/runtime/Anchor;", "fromWriter", "Landroidx/compose/runtime/SlotWriter;", "fromIndex", "", "toWriter", "updateFromCursor", "", "updateToCursor", "removeSourceGroup", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<Anchor> moveGroup(SlotWriter fromWriter, int fromIndex, SlotWriter toWriter, boolean updateFromCursor, boolean updateToCursor, boolean removeSourceGroup) {
            boolean zRemoveGroups;
            List<Anchor> list;
            int i10;
            int iGroupSize = fromWriter.groupSize(fromIndex);
            int i11 = fromIndex + iGroupSize;
            int iDataIndex = fromWriter.dataIndex(fromIndex);
            int iDataIndex2 = fromWriter.dataIndex(i11);
            int i12 = iDataIndex2 - iDataIndex;
            boolean zContainsAnyGroupMarks = fromWriter.containsAnyGroupMarks(fromIndex);
            toWriter.insertGroups(iGroupSize);
            toWriter.insertSlots(i12, toWriter.getCurrentGroup());
            if (fromWriter.groupGapStart < i11) {
                fromWriter.moveGroupGapTo(i11);
            }
            if (fromWriter.slotsGapStart < iDataIndex2) {
                fromWriter.moveSlotGapTo(iDataIndex2, i11);
            }
            int[] iArr = toWriter.groups;
            int currentGroup = toWriter.getCurrentGroup();
            r.K(currentGroup * 5, fromIndex * 5, i11 * 5, fromWriter.groups, iArr);
            Object[] objArr = toWriter.slots;
            int i13 = toWriter.currentSlot;
            r.N(fromWriter.slots, objArr, i13, iDataIndex, iDataIndex2);
            int parent = toWriter.getParent();
            SlotTableKt.updateParentAnchor(iArr, currentGroup, parent);
            int i14 = currentGroup - fromIndex;
            int i15 = currentGroup + iGroupSize;
            int iDataIndex3 = i13 - toWriter.dataIndex(iArr, currentGroup);
            int i16 = toWriter.slotsGapOwner;
            int i17 = toWriter.slotsGapLen;
            int length = objArr.length;
            int i18 = i16;
            int i19 = currentGroup;
            while (true) {
                zRemoveGroups = false;
                if (i19 >= i15) {
                    break;
                }
                if (i19 != currentGroup) {
                    i10 = iDataIndex3;
                    SlotTableKt.updateParentAnchor(iArr, i19, SlotTableKt.parentAnchor(iArr, i19) + i14);
                } else {
                    i10 = iDataIndex3;
                }
                int i20 = i15;
                SlotTableKt.updateDataAnchor(iArr, i19, toWriter.dataIndexToDataAnchor(toWriter.dataIndex(iArr, i19) + i10, i18 >= i19 ? toWriter.slotsGapStart : 0, i17, length));
                if (i19 == i18) {
                    i18++;
                }
                i19++;
                i15 = i20;
                iDataIndex3 = i10;
            }
            int i21 = i15;
            toWriter.slotsGapOwner = i18;
            int iLocationOf = SlotTableKt.locationOf(fromWriter.anchors, fromIndex, fromWriter.getSize$runtime_release());
            int iLocationOf2 = SlotTableKt.locationOf(fromWriter.anchors, i11, fromWriter.getSize$runtime_release());
            if (iLocationOf < iLocationOf2) {
                ArrayList arrayList = fromWriter.anchors;
                ArrayList arrayList2 = new ArrayList(iLocationOf2 - iLocationOf);
                for (int i22 = iLocationOf; i22 < iLocationOf2; i22++) {
                    Anchor anchor = (Anchor) arrayList.get(i22);
                    anchor.setLocation$runtime_release(anchor.getLocation() + i14);
                    arrayList2.add(anchor);
                }
                toWriter.anchors.addAll(SlotTableKt.locationOf(toWriter.anchors, toWriter.getCurrentGroup(), toWriter.getSize$runtime_release()), arrayList2);
                arrayList.subList(iLocationOf, iLocationOf2).clear();
                list = arrayList2;
            } else {
                list = z.f19728i;
            }
            if (!list.isEmpty()) {
                HashMap map = fromWriter.sourceInformationMap;
                HashMap map2 = toWriter.sourceInformationMap;
                if (map != null && map2 != null) {
                    int size = list.size();
                    for (int i23 = 0; i23 < size; i23++) {
                        Anchor anchor2 = list.get(i23);
                        GroupSourceInformation groupSourceInformation = (GroupSourceInformation) map.get(anchor2);
                        if (groupSourceInformation != null) {
                            map.remove(anchor2);
                            map2.put(anchor2, groupSourceInformation);
                        }
                    }
                }
            }
            int parent2 = toWriter.getParent();
            GroupSourceInformation groupSourceInformationSourceInformationOf = toWriter.sourceInformationOf(parent);
            if (groupSourceInformationSourceInformationOf != null) {
                int iGroupSize2 = parent2 + 1;
                int currentGroup2 = toWriter.getCurrentGroup();
                int i24 = -1;
                while (iGroupSize2 < currentGroup2) {
                    i24 = iGroupSize2;
                    iGroupSize2 = SlotTableKt.groupSize(toWriter.groups, iGroupSize2) + iGroupSize2;
                }
                groupSourceInformationSourceInformationOf.addGroupAfter(toWriter, i24, currentGroup2);
            }
            int iParent = fromWriter.parent(fromIndex);
            if (removeSourceGroup) {
                if (updateFromCursor) {
                    boolean z = iParent >= 0;
                    if (z) {
                        fromWriter.startGroup();
                        fromWriter.advanceBy(iParent - fromWriter.getCurrentGroup());
                        fromWriter.startGroup();
                    }
                    fromWriter.advanceBy(fromIndex - fromWriter.getCurrentGroup());
                    boolean zRemoveGroup = fromWriter.removeGroup();
                    if (z) {
                        fromWriter.skipToGroupEnd();
                        fromWriter.endGroup();
                        fromWriter.skipToGroupEnd();
                        fromWriter.endGroup();
                    }
                    zRemoveGroups = zRemoveGroup;
                } else {
                    zRemoveGroups = fromWriter.removeGroups(fromIndex, iGroupSize);
                    fromWriter.removeSlots(iDataIndex, i12, fromIndex - 1);
                }
            }
            if (zRemoveGroups) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            toWriter.nodeCount += SlotTableKt.isNode(iArr, currentGroup) ? 1 : SlotTableKt.nodeCount(iArr, currentGroup);
            if (updateToCursor) {
                toWriter.currentGroup = i21;
                toWriter.currentSlot = i13 + i12;
            }
            if (zContainsAnyGroupMarks) {
                toWriter.updateContainsMark(parent);
            }
            return list;
        }

        public static /* synthetic */ List moveGroup$default(Companion companion, SlotWriter slotWriter, int i10, SlotWriter slotWriter2, boolean z, boolean z5, boolean z10, int i11, Object obj) {
            if ((i11 & 32) != 0) {
                z10 = true;
            }
            return companion.moveGroup(slotWriter, i10, slotWriter2, z, z5, z10);
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SlotWriter$groupSlots$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"androidx/compose/runtime/SlotWriter$groupSlots$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "hasNext", "", LinkHeader.Rel.Next, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Object>, s7.a {
        final /* synthetic */ int $end;
        private int current;
        final /* synthetic */ SlotWriter this$0;

        public AnonymousClass1(int i10, int i11, SlotWriter slotWriter) {
            this.$end = i11;
            this.this$0 = slotWriter;
            this.current = i10;
        }

        public final int getCurrent() {
            return this.current;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current < this.$end;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                return null;
            }
            Object[] objArr = this.this$0.slots;
            SlotWriter slotWriter = this.this$0;
            int i10 = this.current;
            this.current = i10 + 1;
            return objArr[slotWriter.dataIndexToDataAddress(i10)];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setCurrent(int i10) {
            this.current = i10;
        }
    }

    public SlotWriter(SlotTable slotTable) {
        this.table = slotTable;
        this.groups = slotTable.getGroups();
        this.slots = slotTable.getSlots();
        this.anchors = slotTable.getAnchors$runtime_release();
        this.sourceInformationMap = slotTable.getSourceInformationMap$runtime_release();
        this.calledByMap = slotTable.getCalledByMap();
        this.groupGapStart = slotTable.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - slotTable.getGroupsSize();
        this.slotsGapStart = slotTable.getSlotsSize();
        this.slotsGapLen = this.slots.length - slotTable.getSlotsSize();
        this.slotsGapOwner = slotTable.getGroupsSize();
        this.currentGroupEnd = slotTable.getGroupsSize();
    }

    public static /* synthetic */ Anchor anchor$default(SlotWriter slotWriter, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i10);
    }

    private final int auxIndex(int[] iArr, int i10) {
        return SlotTableKt.countOneBits(SlotTableKt.groupInfo(iArr, i10) >> 29) + dataIndex(iArr, i10);
    }

    private final boolean childContainsAnyMarks(int group) {
        int iGroupSize = group + 1;
        int iGroupSize2 = groupSize(group) + group;
        while (iGroupSize < iGroupSize2) {
            if (SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(iGroupSize))) {
                return true;
            }
            iGroupSize += groupSize(iGroupSize);
        }
        return false;
    }

    private final void clearSlotGap() {
        int i10 = this.slotsGapStart;
        Arrays.fill(this.slots, i10, this.slotsGapLen + i10, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean containsAnyGroupMarks(int group) {
        return group >= 0 && SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(group));
    }

    private final boolean containsGroupMark(int group) {
        return group >= 0 && SlotTableKt.containsMark(this.groups, groupIndexToAddress(group));
    }

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return dataIndex < this.slotsGapStart ? dataIndex : dataIndex + this.slotsGapLen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    private final List<Integer> dataIndexes(int[] iArr) {
        List listDataAnchors$default = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        ArrayList arrayListI0 = x.I0(x.U0(listDataAnchors$default, qb.d.X(0, this.groupGapStart)), x.U0(listDataAnchors$default, qb.d.X(this.groupGapStart + this.groupGapLen, iArr.length / 5)));
        ArrayList arrayList = new ArrayList(arrayListI0.size());
        int size = arrayListI0.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(Integer.valueOf(dataAnchorToDataIndex(((Number) arrayListI0.get(i10)).intValue(), this.slotsGapLen, this.slots.length)));
        }
        return arrayList;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int iParentIndexToAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        while (firstChild < endGroup) {
            SlotTableKt.updateParentAnchor(this.groups, groupIndexToAddress(firstChild), iParentIndexToAnchor);
            int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(firstChild)) + firstChild;
            fixParentAnchorsFor(firstChild, iGroupSize, firstChild + 1);
            firstChild = iGroupSize;
        }
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    private final int getCurrentGroupSlotIndex() {
        return this.currentSlot - slotsStartIndex$runtime_release(this.parent);
    }

    private final void groupAsString(StringBuilder sb2, int i10) {
        int iGroupIndexToAddress = groupIndexToAddress(i10);
        sb2.append("Group(");
        if (i10 < 10) {
            sb2.append(' ');
        }
        if (i10 < 100) {
            sb2.append(' ');
        }
        if (i10 < 1000) {
            sb2.append(' ');
        }
        sb2.append(i10);
        if (iGroupIndexToAddress != i10) {
            sb2.append("(");
            sb2.append(iGroupIndexToAddress);
            sb2.append(")");
        }
        sb2.append('#');
        sb2.append(SlotTableKt.groupSize(this.groups, iGroupIndexToAddress));
        sb2.append('^');
        sb2.append(parentAnchorToIndex(SlotTableKt.parentAnchor(this.groups, iGroupIndexToAddress)));
        sb2.append(": key=");
        sb2.append(SlotTableKt.key(this.groups, iGroupIndexToAddress));
        sb2.append(", nodes=");
        sb2.append(SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress));
        sb2.append(", dataAnchor=");
        sb2.append(SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress));
        sb2.append(", parentAnchor=");
        sb2.append(SlotTableKt.parentAnchor(this.groups, iGroupIndexToAddress));
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            sb2.append(", node=" + SlotTableKt.summarize(String.valueOf(this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))]), 10));
        }
        int iSlotIndex = slotIndex(this.groups, iGroupIndexToAddress);
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i10 + 1));
        if (iDataIndex > iSlotIndex) {
            sb2.append(", [");
            for (int i11 = iSlotIndex; i11 < iDataIndex; i11++) {
                if (i11 != iSlotIndex) {
                    sb2.append(", ");
                }
                sb2.append(String.valueOf(SlotTableKt.summarize(String.valueOf(this.slots[dataIndexToDataAddress(i11)]), 10)));
            }
            sb2.append(']');
        }
        sb2.append(")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int groupIndexToAddress(int index) {
        return index < this.groupGapStart ? index : index + this.groupGapLen;
    }

    private final GroupSourceInformation groupSourceInformationFor(int parent, String sourceInformation) {
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map == null) {
            return null;
        }
        Anchor anchor = anchor(parent);
        GroupSourceInformation groupSourceInformation = map.get(anchor);
        if (groupSourceInformation == null) {
            groupSourceInformation = new GroupSourceInformation(0, sourceInformation, 0);
            if (sourceInformation == null) {
                int iGroupSize = parent + 1;
                int i10 = this.currentGroup;
                while (iGroupSize < i10) {
                    groupSourceInformation.reportGroup(this, iGroupSize);
                    iGroupSize += SlotTableKt.groupSize(this.groups, iGroupSize);
                }
            }
            map.put(anchor, groupSourceInformation);
        }
        return groupSourceInformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int size) {
        if (size > 0) {
            int i10 = this.currentGroup;
            moveGroupGapTo(i10);
            int i11 = this.groupGapStart;
            int i12 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i13 = length - i12;
            if (i12 < size) {
                int iMax = Math.max(Math.max(length * 2, i13 + size), 32);
                int[] iArr2 = new int[iMax * 5];
                int i14 = iMax - i13;
                r.K(0, 0, i11 * 5, iArr, iArr2);
                r.K((i11 + i14) * 5, (i12 + i11) * 5, length * 5, iArr, iArr2);
                this.groups = iArr2;
                i12 = i14;
            }
            int i15 = this.currentGroupEnd;
            if (i15 >= i11) {
                this.currentGroupEnd = i15 + size;
            }
            int i16 = i11 + size;
            this.groupGapStart = i16;
            this.groupGapLen = i12 - size;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(i13 > 0 ? dataIndex(i10 + size) : 0, this.slotsGapOwner >= i11 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            for (int i17 = i11; i17 < i16; i17++) {
                SlotTableKt.updateDataAnchor(this.groups, i17, iDataIndexToDataAnchor);
            }
            int i18 = this.slotsGapOwner;
            if (i18 >= i11) {
                this.slotsGapOwner = i18 + size;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int i10 = this.slotsGapStart;
            int i11 = this.slotsGapLen;
            if (i11 < size) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i12 = length - i11;
                int iMax = Math.max(Math.max(length * 2, i12 + size), 32);
                Object[] objArr2 = new Object[iMax];
                for (int i13 = 0; i13 < iMax; i13++) {
                    objArr2[i13] = null;
                }
                int i14 = iMax - i12;
                r.N(objArr, objArr2, 0, 0, i10);
                r.N(objArr, objArr2, i10 + i14, i11 + i10, length);
                this.slots = objArr2;
                i11 = i14;
            }
            int i15 = this.currentSlotEnd;
            if (i15 >= i10) {
                this.currentSlotEnd = i15 + size;
            }
            this.slotsGapStart = i10 + size;
            this.slotsGapLen = i11 - size;
        }
    }

    private final List<Integer> keys() {
        List listKeys$default = SlotTableKt.keys$default(this.groups, 0, 1, null);
        ArrayList arrayList = new ArrayList(listKeys$default.size());
        int size = listKeys$default.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = listKeys$default.get(i10);
            ((Number) obj).intValue();
            int i11 = this.groupGapStart;
            if (i10 < i11 || i10 >= i11 + this.groupGapLen) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ void markGroup$default(SlotWriter slotWriter, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = slotWriter.parent;
        }
        slotWriter.markGroup(i10);
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        Anchor anchor;
        int iAnchorIndex;
        int i10 = size + originalLocation;
        int size$runtime_release = getSize$runtime_release();
        int iLocationOf = SlotTableKt.locationOf(this.anchors, originalLocation, size$runtime_release);
        ArrayList arrayList = new ArrayList();
        if (iLocationOf >= 0) {
            while (iLocationOf < this.anchors.size() && (iAnchorIndex = anchorIndex((anchor = this.anchors.get(iLocationOf)))) >= originalLocation && iAnchorIndex < i10) {
                arrayList.add(anchor);
                this.anchors.remove(iLocationOf);
            }
        }
        int i11 = newLocation - originalLocation;
        int size2 = arrayList.size();
        for (int i12 = 0; i12 < size2; i12++) {
            Anchor anchor2 = (Anchor) arrayList.get(i12);
            int iAnchorIndex2 = anchorIndex(anchor2) + i11;
            if (iAnchorIndex2 >= this.groupGapStart) {
                anchor2.setLocation$runtime_release(-(size$runtime_release - iAnchorIndex2));
            } else {
                anchor2.setLocation$runtime_release(iAnchorIndex2);
            }
            this.anchors.add(SlotTableKt.locationOf(this.anchors, iAnchorIndex2, size$runtime_release), anchor2);
        }
    }

    public static /* synthetic */ List moveFrom$default(SlotWriter slotWriter, SlotTable slotTable, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            z = true;
        }
        return slotWriter.moveFrom(slotTable, i10, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int index) {
        int i10 = this.groupGapLen;
        int i11 = this.groupGapStart;
        if (i11 != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(i11, index);
            }
            if (i10 > 0) {
                int[] iArr = this.groups;
                int i12 = index * 5;
                int i13 = i10 * 5;
                int i14 = i11 * 5;
                if (index < i11) {
                    r.K(i13 + i12, i12, i14, iArr, iArr);
                } else {
                    r.K(i14, i14 + i13, i12 + i13, iArr, iArr);
                }
            }
            if (index < i11) {
                i11 = index + i10;
            }
            int capacity = getCapacity();
            ComposerKt.runtimeCheck(i11 < capacity);
            while (i11 < capacity) {
                int iParentAnchor = SlotTableKt.parentAnchor(this.groups, i11);
                int iParentIndexToAnchor = parentIndexToAnchor(parentAnchorToIndex(iParentAnchor), index);
                if (iParentIndexToAnchor != iParentAnchor) {
                    SlotTableKt.updateParentAnchor(this.groups, i11, iParentIndexToAnchor);
                }
                i11++;
                if (i11 == index) {
                    i11 += i10;
                }
            }
        }
        this.groupGapStart = index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int index, int group) {
        int i10 = this.slotsGapLen;
        int i11 = this.slotsGapStart;
        int i12 = this.slotsGapOwner;
        if (i11 != index) {
            Object[] objArr = this.slots;
            if (index < i11) {
                r.N(objArr, objArr, index + i10, index, i11);
            } else {
                r.N(objArr, objArr, i11, i11 + i10, index + i10);
            }
        }
        int iMin = Math.min(group + 1, getSize$runtime_release());
        if (i12 != iMin) {
            int length = this.slots.length - i10;
            if (iMin < i12) {
                int iGroupIndexToAddress = groupIndexToAddress(iMin);
                int iGroupIndexToAddress2 = groupIndexToAddress(i12);
                int i13 = this.groupGapStart;
                while (iGroupIndexToAddress < iGroupIndexToAddress2) {
                    int iDataAnchor = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress);
                    if (iDataAnchor < 0) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a positive anchor");
                    }
                    SlotTableKt.updateDataAnchor(this.groups, iGroupIndexToAddress, -((length - iDataAnchor) + 1));
                    iGroupIndexToAddress++;
                    if (iGroupIndexToAddress == i13) {
                        iGroupIndexToAddress += this.groupGapLen;
                    }
                }
            } else {
                int iGroupIndexToAddress3 = groupIndexToAddress(i12);
                int iGroupIndexToAddress4 = groupIndexToAddress(iMin);
                while (iGroupIndexToAddress3 < iGroupIndexToAddress4) {
                    int iDataAnchor2 = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress3);
                    if (iDataAnchor2 >= 0) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a negative anchor");
                    }
                    SlotTableKt.updateDataAnchor(this.groups, iGroupIndexToAddress3, iDataAnchor2 + length + 1);
                    iGroupIndexToAddress3++;
                    if (iGroupIndexToAddress3 == this.groupGapStart) {
                        iGroupIndexToAddress3 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = iMin;
        }
        this.slotsGapStart = index;
    }

    private final int nodeIndex(int[] iArr, int i10) {
        return dataIndex(iArr, i10);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : (getSize$runtime_release() + index) - (-2);
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime_release() - index) + 2);
    }

    private final void recalculateMarks() {
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (prioritySet.isNotEmpty()) {
                updateContainsMarkNow(prioritySet.takeMax(), prioritySet);
            }
        }
    }

    private final boolean removeAnchors(int gapStart, int size, HashMap<Anchor, GroupSourceInformation> sourceInformationMap) {
        int i10 = size + gapStart;
        int iLocationOf = SlotTableKt.locationOf(this.anchors, i10, getCapacity() - this.groupGapLen);
        if (iLocationOf >= this.anchors.size()) {
            iLocationOf--;
        }
        int i11 = iLocationOf + 1;
        int i12 = 0;
        while (iLocationOf >= 0) {
            Anchor anchor = this.anchors.get(iLocationOf);
            int iAnchorIndex = anchorIndex(anchor);
            if (iAnchorIndex < gapStart) {
                break;
            }
            if (iAnchorIndex < i10) {
                anchor.setLocation$runtime_release(Integer.MIN_VALUE);
                if (sourceInformationMap != null) {
                    sourceInformationMap.remove(anchor);
                }
                if (i12 == 0) {
                    i12 = iLocationOf + 1;
                }
                i11 = iLocationOf;
            }
            iLocationOf--;
        }
        boolean z = i11 < i12;
        if (z) {
            this.anchors.subList(i11, i12).clear();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeGroups(int start, int len) {
        if (len > 0) {
            ArrayList<Anchor> arrayList = this.anchors;
            moveGroupGapTo(start);
            zRemoveAnchors = arrayList.isEmpty() ? false : removeAnchors(start, len, this.sourceInformationMap);
            this.groupGapStart = start;
            this.groupGapLen += len;
            int i10 = this.slotsGapOwner;
            if (i10 > start) {
                this.slotsGapOwner = Math.max(start, i10 - len);
            }
            int i11 = this.currentGroupEnd;
            if (i11 >= this.groupGapStart) {
                this.currentGroupEnd = i11 - len;
            }
            int i12 = this.parent;
            if (containsGroupMark(i12)) {
                updateContainsMark(i12);
            }
        }
        return zRemoveAnchors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int i10 = this.slotsGapLen;
            int i11 = start + len;
            moveSlotGapTo(i11, group);
            this.slotsGapStart = start;
            this.slotsGapLen = i10 + len;
            Arrays.fill(this.slots, start, i11, (Object) null);
            int i12 = this.currentSlotEnd;
            if (i12 >= start) {
                this.currentSlotEnd = i12 - len;
            }
        }
    }

    private final int restoreCurrentGroupEnd() {
        int capacity = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = capacity;
        return capacity;
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int slotIndex(int[] iArr, int i10) {
        return i10 >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.slotAnchor(iArr, i10), this.slotsGapLen, this.slots.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GroupSourceInformation sourceInformationOf(int group) {
        Anchor anchorTryAnchor$runtime_release;
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map == null || (anchorTryAnchor$runtime_release = tryAnchor$runtime_release(group)) == null) {
            return null;
        }
        return map.get(anchorTryAnchor$runtime_release);
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        Anchor anchor;
        int location$runtime_release;
        Anchor anchor2;
        int location$runtime_release2;
        int i10;
        int capacity = getCapacity() - this.groupGapLen;
        if (previousGapStart >= newGapStart) {
            for (int iLocationOf = SlotTableKt.locationOf(this.anchors, newGapStart, capacity); iLocationOf < this.anchors.size() && (location$runtime_release = (anchor = this.anchors.get(iLocationOf)).getLocation()) >= 0; iLocationOf++) {
                anchor.setLocation$runtime_release(-(capacity - location$runtime_release));
            }
            return;
        }
        for (int iLocationOf2 = SlotTableKt.locationOf(this.anchors, previousGapStart, capacity); iLocationOf2 < this.anchors.size() && (location$runtime_release2 = (anchor2 = this.anchors.get(iLocationOf2)).getLocation()) < 0 && (i10 = location$runtime_release2 + capacity) < newGapStart; iLocationOf2++) {
            anchor2.setLocation$runtime_release(i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void updateContainsMark(int group) {
        if (group >= 0) {
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet == null) {
                prioritySet = new PrioritySet(null, 1, 0 == true ? 1 : 0);
                this.pendingRecalculateMarks = prioritySet;
            }
            prioritySet.add(group);
        }
    }

    private final void updateContainsMarkNow(int group, PrioritySet set) {
        int iGroupIndexToAddress = groupIndexToAddress(group);
        boolean zChildContainsAnyMarks = childContainsAnyMarks(group);
        if (SlotTableKt.containsMark(this.groups, iGroupIndexToAddress) != zChildContainsAnyMarks) {
            SlotTableKt.updateContainsMark(this.groups, iGroupIndexToAddress, zChildContainsAnyMarks);
            int iParent = parent(group);
            if (iParent >= 0) {
                set.add(iParent);
            }
        }
    }

    private final void updateDataIndex(int[] iArr, int i10, int i11) {
        SlotTableKt.updateDataAnchor(iArr, i10, dataIndexToDataAnchor(i11, this.slotsGapStart, this.slotsGapLen, this.slots.length));
    }

    private final void updateNodeOfGroup(int index, Object value) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        int[] iArr = this.groups;
        if (!(iGroupIndexToAddress < iArr.length && SlotTableKt.isNode(iArr, iGroupIndexToAddress))) {
            ComposerKt.composeImmediateRuntimeError("Updating the node of a group at " + index + " that was not created with as a node group");
        }
        this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))] = value;
    }

    public final void advanceBy(int amount) {
        boolean z = false;
        if (!(amount >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek backwards");
        }
        if (!(this.insertCount <= 0)) {
            PreconditionsKt.throwIllegalStateException("Cannot call seek() while inserting");
        }
        if (amount == 0) {
            return;
        }
        int i10 = this.currentGroup + amount;
        if (i10 >= this.parent && i10 <= this.currentGroupEnd) {
            z = true;
        }
        if (!z) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')');
        }
        this.currentGroup = i10;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i10));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
    }

    public final Anchor anchor(int index) {
        ArrayList<Anchor> arrayList = this.anchors;
        int iSearch = SlotTableKt.search(arrayList, index, getSize$runtime_release());
        if (iSearch >= 0) {
            return arrayList.get(iSearch);
        }
        if (index > this.groupGapStart) {
            index = -(getSize$runtime_release() - index);
        }
        Anchor anchor = new Anchor(index);
        arrayList.add(-(iSearch + 1), anchor);
        return anchor;
    }

    public final int anchorIndex(Anchor anchor) {
        int location$runtime_release = anchor.getLocation();
        return location$runtime_release < 0 ? getSize$runtime_release() + location$runtime_release : location$runtime_release;
    }

    public final void appendSlot(Anchor anchor, Object value) {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Can only append a slot if not current inserting");
        }
        int i10 = this.currentSlot;
        int i11 = this.currentSlotEnd;
        int iAnchorIndex = anchorIndex(anchor);
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(iAnchorIndex + 1));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
        insertSlots(1, iAnchorIndex);
        if (i10 >= iDataIndex) {
            i10++;
            i11++;
        }
        this.slots[iDataIndex] = value;
        this.currentSlot = i10;
        this.currentSlotEnd = i11;
    }

    public final void bashCurrentGroup() {
        SlotTableKt.updateGroupKey(this.groups, this.currentGroup, -3);
    }

    public final void beginInsert() {
        int i10 = this.insertCount;
        this.insertCount = i10 + 1;
        if (i10 == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void close(boolean normalClose) {
        this.closed = true;
        if (normalClose && this.startStack.isEmpty()) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            clearSlotGap();
            recalculateMarks();
        }
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors, this.sourceInformationMap, this.calledByMap);
    }

    public final int endGroup() {
        boolean z = this.insertCount > 0;
        int i10 = this.currentGroup;
        int i11 = this.currentGroupEnd;
        int i12 = this.parent;
        int iGroupIndexToAddress = groupIndexToAddress(i12);
        int i13 = this.nodeCount;
        int i14 = i10 - i12;
        boolean zIsNode = SlotTableKt.isNode(this.groups, iGroupIndexToAddress);
        if (z) {
            SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i14);
            SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i13);
            this.nodeCount = this.nodeCountStack.pop() + (zIsNode ? 1 : i13);
            int iParent = parent(this.groups, i12);
            this.parent = iParent;
            int size$runtime_release = iParent < 0 ? getSize$runtime_release() : groupIndexToAddress(iParent + 1);
            int iDataIndex = size$runtime_release >= 0 ? dataIndex(this.groups, size$runtime_release) : 0;
            this.currentSlot = iDataIndex;
            this.currentSlotEnd = iDataIndex;
            return i13;
        }
        if (!(i10 == i11)) {
            ComposerKt.composeImmediateRuntimeError("Expected to be at the end of a group");
        }
        int iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
        int iNodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress);
        SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i14);
        SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i13);
        int iPop = this.startStack.pop();
        restoreCurrentGroupEnd();
        this.parent = iPop;
        int iParent2 = parent(this.groups, i12);
        int iPop2 = this.nodeCountStack.pop();
        this.nodeCount = iPop2;
        if (iParent2 == iPop) {
            this.nodeCount = iPop2 + (zIsNode ? 0 : i13 - iNodeCount);
            return i13;
        }
        int i15 = i14 - iGroupSize;
        int i16 = zIsNode ? 0 : i13 - iNodeCount;
        if (i15 != 0 || i16 != 0) {
            while (iParent2 != 0 && iParent2 != iPop && (i16 != 0 || i15 != 0)) {
                int iGroupIndexToAddress2 = groupIndexToAddress(iParent2);
                if (i15 != 0) {
                    SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress2, SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2) + i15);
                }
                if (i16 != 0) {
                    int[] iArr = this.groups;
                    SlotTableKt.updateNodeCount(iArr, iGroupIndexToAddress2, SlotTableKt.nodeCount(iArr, iGroupIndexToAddress2) + i16);
                }
                if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress2)) {
                    i16 = 0;
                }
                iParent2 = parent(this.groups, iParent2);
            }
        }
        this.nodeCount += i16;
        return i13;
    }

    public final void endInsert() {
        if (!(this.insertCount > 0)) {
            PreconditionsKt.throwIllegalStateException("Unbalanced begin/end insert");
        }
        int i10 = this.insertCount - 1;
        this.insertCount = i10;
        if (i10 == 0) {
            if (!(this.nodeCountStack.getTos() == this.startStack.getTos())) {
                ComposerKt.composeImmediateRuntimeError("startGroup/endGroup mismatch while inserting");
            }
            restoreCurrentGroupEnd();
        }
    }

    public final void ensureStarted(int index) {
        boolean z = false;
        if (!(this.insertCount <= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot call ensureStarted() while inserting");
        }
        int i10 = this.parent;
        if (i10 != index) {
            if (index >= i10 && index < this.currentGroupEnd) {
                z = true;
            }
            if (!z) {
                ComposerKt.composeImmediateRuntimeError("Started group at " + index + " must be a subgroup of the group at " + i10);
            }
            int i11 = this.currentGroup;
            int i12 = this.currentSlot;
            int i13 = this.currentSlotEnd;
            this.currentGroup = index;
            startGroup();
            this.currentGroup = i11;
            this.currentSlot = i12;
            this.currentSlotEnd = i13;
        }
    }

    public final void forAllData(int group, p<? super Integer, Object, t0> block) {
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(groupSize(getCurrentGroup()) + getCurrentGroup()));
        for (int iDataIndex2 = dataIndex(this.groups, groupIndexToAddress(group)); iDataIndex2 < iDataIndex; iDataIndex2++) {
            block.invoke(Integer.valueOf(iDataIndex2), this.slots[dataIndexToDataAddress(iDataIndex2)]);
        }
    }

    public final void forEachData(int group, p<? super Integer, Object, t0> block) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(group));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(group + 1));
        for (int i10 = iSlotIndex; i10 < iDataIndex; i10++) {
            block.invoke(Integer.valueOf(i10 - iSlotIndex), this.slots[dataIndexToDataAddress(i10)]);
        }
    }

    public final void forEachTailSlot(int groupIndex, int count, p<? super Integer, Object, t0> block) {
        int iSlotsStartIndex$runtime_release = slotsStartIndex$runtime_release(groupIndex);
        int iSlotsEndIndex$runtime_release = slotsEndIndex$runtime_release(groupIndex);
        for (int iMax = Math.max(iSlotsStartIndex$runtime_release, iSlotsEndIndex$runtime_release - count); iMax < iSlotsEndIndex$runtime_release; iMax++) {
            block.invoke(Integer.valueOf(iMax), this.slots[dataIndexToDataAddress(iMax)]);
        }
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final boolean getCollectingCalledInformation() {
        return this.calledByMap != null;
    }

    public final boolean getCollectingSourceInformation() {
        return this.sourceInformationMap != null;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getCurrentGroupEnd() {
        return this.currentGroupEnd;
    }

    public final int getParent() {
        return this.parent;
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    public final int getSlotsSize() {
        return this.slots.length - this.slotsGapLen;
    }

    /* JADX INFO: renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final Object groupAux(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        return SlotTableKt.hasAux(this.groups, iGroupIndexToAddress) ? this.slots[auxIndex(this.groups, iGroupIndexToAddress)] : Composer.INSTANCE.getEmpty();
    }

    public final int groupKey(int index) {
        return SlotTableKt.key(this.groups, groupIndexToAddress(index));
    }

    public final Object groupObjectKey(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        if (SlotTableKt.hasObjectKey(this.groups, iGroupIndexToAddress)) {
            return this.slots[SlotTableKt.objectKeyIndex(this.groups, iGroupIndexToAddress)];
        }
        return null;
    }

    public final int groupSize(int index) {
        return SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
    }

    public final Iterator<Object> groupSlots() {
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int[] iArr = this.groups;
        int i10 = this.currentGroup;
        return new AnonymousClass1(iDataIndex, dataIndex(iArr, groupIndexToAddress(groupSize(i10) + i10)), this);
    }

    public final boolean indexInCurrentGroup(int index) {
        return indexInGroup(index, this.currentGroup);
    }

    public final boolean indexInGroup(int index, int group) {
        int iIndexOf;
        int capacity;
        if (group == this.parent) {
            capacity = this.currentGroupEnd;
        } else if (group <= this.startStack.peekOr(0) && (iIndexOf = this.startStack.indexOf(group)) >= 0) {
            capacity = (getCapacity() - this.groupGapLen) - this.endStack.peek(iIndexOf);
        } else {
            int iGroupSize = groupSize(group);
            capacity = iGroupSize + group;
        }
        return index > group && index < capacity;
    }

    public final boolean indexInParent(int index) {
        int i10 = this.parent;
        if (index <= i10 || index >= this.currentGroupEnd) {
            return i10 == 0 && index == 0;
        }
        return true;
    }

    public final void insertAux(Object value) {
        if (!(this.insertCount >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot insert auxiliary data when not inserting");
        }
        int i10 = this.parent;
        int iGroupIndexToAddress = groupIndexToAddress(i10);
        if (SlotTableKt.hasAux(this.groups, iGroupIndexToAddress)) {
            ComposerKt.composeImmediateRuntimeError("Group already has auxiliary data");
        }
        insertSlots(1, i10);
        int iAuxIndex = auxIndex(this.groups, iGroupIndexToAddress);
        int iDataIndexToDataAddress = dataIndexToDataAddress(iAuxIndex);
        int i11 = this.currentSlot;
        if (i11 > iAuxIndex) {
            int i12 = i11 - iAuxIndex;
            if (!(i12 < 3)) {
                PreconditionsKt.throwIllegalStateException("Moving more than two slot not supported");
            }
            if (i12 > 1) {
                Object[] objArr = this.slots;
                objArr[iDataIndexToDataAddress + 2] = objArr[iDataIndexToDataAddress + 1];
            }
            Object[] objArr2 = this.slots;
            objArr2[iDataIndexToDataAddress + 1] = objArr2[iDataIndexToDataAddress];
        }
        SlotTableKt.addAux(this.groups, iGroupIndexToAddress);
        this.slots[iDataIndexToDataAddress] = value;
        this.currentSlot++;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final boolean isNode() {
        int i10 = this.currentGroup;
        return i10 < this.currentGroupEnd && SlotTableKt.isNode(this.groups, groupIndexToAddress(i10));
    }

    public final void markGroup(int group) {
        int iGroupIndexToAddress = groupIndexToAddress(group);
        if (SlotTableKt.hasMark(this.groups, iGroupIndexToAddress)) {
            return;
        }
        SlotTableKt.updateMark(this.groups, iGroupIndexToAddress, true);
        if (SlotTableKt.containsMark(this.groups, iGroupIndexToAddress)) {
            return;
        }
        updateContainsMark(parent(group));
    }

    public final List<Anchor> moveFrom(SlotTable table, int index, boolean removeSourceGroup) {
        ComposerKt.runtimeCheck(this.insertCount > 0);
        if (index != 0 || this.currentGroup != 0 || this.table.getGroupsSize() != 0 || SlotTableKt.groupSize(table.getGroups(), index) != table.getGroupsSize()) {
            SlotWriter slotWriterOpenWriter = table.openWriter();
            try {
                List<Anchor> listMoveGroup = INSTANCE.moveGroup(slotWriterOpenWriter, index, this, true, true, removeSourceGroup);
                slotWriterOpenWriter.close(true);
                return listMoveGroup;
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        }
        int[] iArr = this.groups;
        Object[] objArr = this.slots;
        ArrayList<Anchor> arrayList = this.anchors;
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        v vVar = this.calledByMap;
        int[] groups = table.getGroups();
        int groupsSize = table.getGroupsSize();
        Object[] slots = table.getSlots();
        int slotsSize = table.getSlotsSize();
        HashMap<Anchor, GroupSourceInformation> sourceInformationMap$runtime_release = table.getSourceInformationMap$runtime_release();
        v calledByMap$runtime_release = table.getCalledByMap();
        this.groups = groups;
        this.slots = slots;
        this.anchors = table.getAnchors$runtime_release();
        this.groupGapStart = groupsSize;
        this.groupGapLen = (groups.length / 5) - groupsSize;
        this.slotsGapStart = slotsSize;
        this.slotsGapLen = slots.length - slotsSize;
        this.slotsGapOwner = groupsSize;
        this.sourceInformationMap = sourceInformationMap$runtime_release;
        this.calledByMap = calledByMap$runtime_release;
        table.setTo$runtime_release(iArr, 0, objArr, 0, arrayList, map, vVar);
        return this.anchors;
    }

    public final void moveGroup(int offset) {
        if (this.insertCount != 0) {
            ComposerKt.composeImmediateRuntimeError("Cannot move a group while inserting");
        }
        if (offset < 0) {
            ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
        }
        if (offset == 0) {
            return;
        }
        int i10 = this.currentGroup;
        int i11 = this.parent;
        int i12 = this.currentGroupEnd;
        int iGroupSize = i10;
        for (int i13 = offset; i13 > 0; i13--) {
            iGroupSize += SlotTableKt.groupSize(this.groups, groupIndexToAddress(iGroupSize));
            if (iGroupSize > i12) {
                ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
            }
        }
        int iGroupSize2 = SlotTableKt.groupSize(this.groups, groupIndexToAddress(iGroupSize));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int iDataIndex2 = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        int i14 = iGroupSize + iGroupSize2;
        int iDataIndex3 = dataIndex(this.groups, groupIndexToAddress(i14));
        int i15 = iDataIndex3 - iDataIndex2;
        insertSlots(i15, Math.max(this.currentGroup - 1, 0));
        insertGroups(iGroupSize2);
        int[] iArr = this.groups;
        int iGroupIndexToAddress = groupIndexToAddress(i14) * 5;
        r.K(groupIndexToAddress(i10) * 5, iGroupIndexToAddress, (iGroupSize2 * 5) + iGroupIndexToAddress, iArr, iArr);
        if (i15 > 0) {
            Object[] objArr = this.slots;
            r.N(objArr, objArr, iDataIndex, dataIndexToDataAddress(iDataIndex2 + i15), dataIndexToDataAddress(iDataIndex3 + i15));
        }
        int i16 = iDataIndex2 + i15;
        int i17 = i16 - iDataIndex;
        int i18 = this.slotsGapStart;
        int i19 = this.slotsGapLen;
        int length = this.slots.length;
        int i20 = this.slotsGapOwner;
        int i21 = i10 + iGroupSize2;
        int i22 = i10;
        while (i22 < i21) {
            int iGroupIndexToAddress2 = groupIndexToAddress(i22);
            int i23 = i17;
            int iDataIndex4 = dataIndex(iArr, iGroupIndexToAddress2) - i23;
            int i24 = i18;
            if (i20 < iGroupIndexToAddress2) {
                i18 = 0;
            }
            updateDataIndex(iArr, iGroupIndexToAddress2, dataIndexToDataAnchor(iDataIndex4, i18, i19, length));
            i22++;
            i18 = i24;
            i17 = i23;
        }
        moveAnchors(i14, i10, iGroupSize2);
        if (removeGroups(i14, iGroupSize2)) {
            ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
        }
        fixParentAnchorsFor(i11, this.currentGroupEnd, i10);
        if (i15 > 0) {
            removeSlots(i16, i15, i14 - 1);
        }
    }

    public final List<Anchor> moveIntoGroupFrom(int offset, SlotTable table, int index) {
        ComposerKt.runtimeCheck(this.insertCount <= 0 && groupSize(this.currentGroup + offset) == 1);
        int i10 = this.currentGroup;
        int i11 = this.currentSlot;
        int i12 = this.currentSlotEnd;
        advanceBy(offset);
        startGroup();
        beginInsert();
        SlotWriter slotWriterOpenWriter = table.openWriter();
        try {
            List<Anchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, slotWriterOpenWriter, index, this, false, true, false, 32, null);
            slotWriterOpenWriter.close(true);
            endInsert();
            endGroup();
            this.currentGroup = i10;
            this.currentSlot = i11;
            this.currentSlotEnd = i12;
            return listMoveGroup$default;
        } catch (Throwable th) {
            slotWriterOpenWriter.close(false);
            throw th;
        }
    }

    public final List<Anchor> moveTo(Anchor anchor, int offset, SlotWriter writer) {
        ComposerKt.runtimeCheck(writer.insertCount > 0);
        ComposerKt.runtimeCheck(this.insertCount == 0);
        ComposerKt.runtimeCheck(anchor.getValid());
        int iAnchorIndex = anchorIndex(anchor) + offset;
        int i10 = this.currentGroup;
        ComposerKt.runtimeCheck(i10 <= iAnchorIndex && iAnchorIndex < this.currentGroupEnd);
        int iParent = parent(iAnchorIndex);
        int iGroupSize = groupSize(iAnchorIndex);
        int iNodeCount = isNode(iAnchorIndex) ? 1 : nodeCount(iAnchorIndex);
        List<Anchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, this, iAnchorIndex, writer, false, false, false, 32, null);
        updateContainsMark(iParent);
        boolean z = iNodeCount > 0;
        while (iParent >= i10) {
            int iGroupIndexToAddress = groupIndexToAddress(iParent);
            int[] iArr = this.groups;
            SlotTableKt.updateGroupSize(iArr, iGroupIndexToAddress, SlotTableKt.groupSize(iArr, iGroupIndexToAddress) - iGroupSize);
            if (z) {
                if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
                    z = false;
                } else {
                    int[] iArr2 = this.groups;
                    SlotTableKt.updateNodeCount(iArr2, iGroupIndexToAddress, SlotTableKt.nodeCount(iArr2, iGroupIndexToAddress) - iNodeCount);
                }
            }
            iParent = parent(iParent);
        }
        if (z) {
            ComposerKt.runtimeCheck(this.nodeCount >= iNodeCount);
            this.nodeCount -= iNodeCount;
        }
        return listMoveGroup$default;
    }

    public final Object node(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))];
        }
        return null;
    }

    public final int nodeCount(int index) {
        return SlotTableKt.nodeCount(this.groups, groupIndexToAddress(index));
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final void recordGroupSourceInformation(String sourceInformation) {
        if (this.insertCount > 0) {
            groupSourceInformationFor(this.parent, sourceInformation);
        }
    }

    public final void recordGrouplessCallSourceInformationEnd() {
        GroupSourceInformation groupSourceInformationGroupSourceInformationFor;
        if (this.insertCount <= 0 || (groupSourceInformationGroupSourceInformationFor = groupSourceInformationFor(this.parent, null)) == null) {
            return;
        }
        groupSourceInformationGroupSourceInformationFor.endGrouplessCall(getCurrentGroupSlotIndex());
    }

    public final void recordGrouplessCallSourceInformationStart(int key, String value) {
        if (this.insertCount > 0) {
            v vVar = this.calledByMap;
            if (vVar != null) {
                SlotTableKt.add(vVar, key, groupKey(this.parent));
            }
            GroupSourceInformation groupSourceInformationGroupSourceInformationFor = groupSourceInformationFor(this.parent, null);
            if (groupSourceInformationGroupSourceInformationFor != null) {
                groupSourceInformationGroupSourceInformationFor.startGrouplessCall(key, value, getCurrentGroupSlotIndex());
            }
        }
    }

    public final boolean removeGroup() {
        Anchor anchorTryAnchor$runtime_release;
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot remove group while inserting");
        }
        int i10 = this.currentGroup;
        int i11 = this.currentSlot;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i10));
        int iSkipGroup = skipGroup();
        GroupSourceInformation groupSourceInformationSourceInformationOf = sourceInformationOf(this.parent);
        if (groupSourceInformationSourceInformationOf != null && (anchorTryAnchor$runtime_release = tryAnchor$runtime_release(i10)) != null) {
            groupSourceInformationSourceInformationOf.removeAnchor(anchorTryAnchor$runtime_release);
        }
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (prioritySet.isNotEmpty() && prioritySet.peek() >= i10) {
                prioritySet.takeMax();
            }
        }
        boolean zRemoveGroups = removeGroups(i10, this.currentGroup - i10);
        removeSlots(iDataIndex, this.currentSlot - iDataIndex, i10 - 1);
        this.currentGroup = i10;
        this.currentSlot = i11;
        this.nodeCount -= iSkipGroup;
        return zRemoveGroups;
    }

    public final void reset() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot reset when inserting");
        }
        recalculateMarks();
        this.currentGroup = 0;
        this.currentGroupEnd = getCapacity() - this.groupGapLen;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
        this.nodeCount = 0;
    }

    public final void seek(Anchor anchor) {
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void set(Object value) {
        if (!(this.currentSlot <= this.currentSlotEnd)) {
            ComposerKt.composeImmediateRuntimeError("Writing to an invalid slot");
        }
        this.slots[dataIndexToDataAddress(this.currentSlot - 1)] = value;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i10 = this.currentSlot;
        this.currentSlot = i10 + 1;
        return objArr[dataIndexToDataAddress(i10)];
    }

    public final int skipGroup() {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress) + this.currentGroup;
        this.currentGroup = iGroupSize;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            return 1;
        }
        return SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress);
    }

    public final void skipToGroupEnd() {
        int i10 = this.currentGroupEnd;
        this.currentGroup = i10;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i10));
    }

    public final Object slot(Anchor anchor, int index) {
        return slot(anchorIndex(anchor), index);
    }

    public final int slotIndexOfGroupSlotIndex(int group, int index) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(group));
        int i10 = iSlotIndex + index;
        if (!(i10 >= iSlotIndex && i10 < dataIndex(this.groups, groupIndexToAddress(group + 1)))) {
            ComposerKt.composeImmediateRuntimeError("Write to an invalid slot index " + index + " for group " + group);
        }
        return i10;
    }

    public final int slotsEndAllIndex$runtime_release(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupSize(groupIndex) + groupIndex));
    }

    public final int slotsEndIndex$runtime_release(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
    }

    public final int slotsStartIndex$runtime_release(int groupIndex) {
        return slotIndex(this.groups, groupIndexToAddress(groupIndex));
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startGroup() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Key must be supplied when inserting");
        }
        Composer.Companion companion = Composer.INSTANCE;
        startGroup(0, companion.getEmpty(), false, companion.getEmpty());
    }

    public final void startNode(int key, Object objectKey) {
        startGroup(key, objectKey, true, Composer.INSTANCE.getEmpty());
    }

    public final String toDebugString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(toString());
        sb2.append('\n');
        sb2.append("  parent:    " + this.parent);
        sb2.append('\n');
        sb2.append("  current:   " + this.currentGroup);
        sb2.append('\n');
        sb2.append("  group gap: " + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + '(' + this.groupGapLen + ')');
        sb2.append('\n');
        sb2.append("  slots gap: " + this.slotsGapStart + '-' + (this.slotsGapStart + this.slotsGapLen) + '(' + this.slotsGapLen + ')');
        sb2.append('\n');
        StringBuilder sb3 = new StringBuilder("  gap owner: ");
        sb3.append(this.slotsGapOwner);
        sb2.append(sb3.toString());
        sb2.append('\n');
        int size$runtime_release = getSize$runtime_release();
        for (int i10 = 0; i10 < size$runtime_release; i10++) {
            groupAsString(sb2, i10);
            sb2.append('\n');
        }
        return sb2.toString();
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    public final void trimTailSlots(int count) {
        ComposerKt.runtimeCheck(count > 0);
        int i10 = this.parent;
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(i10));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i10 + 1)) - count;
        ComposerKt.runtimeCheck(iDataIndex >= iSlotIndex);
        removeSlots(iDataIndex, count, i10);
        int i11 = this.currentSlot;
        if (i11 >= iSlotIndex) {
            this.currentSlot = i11 - count;
        }
    }

    public final Anchor tryAnchor$runtime_release(int group) {
        if (group < 0 || group >= getSize$runtime_release()) {
            return null;
        }
        return SlotTableKt.find(this.anchors, group, getSize$runtime_release());
    }

    public final Object update(Object value) {
        Object objSkip = skip();
        set(value);
        return objSkip;
    }

    public final void updateAux(Object value) {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (!SlotTableKt.hasAux(this.groups, iGroupIndexToAddress)) {
            ComposerKt.composeImmediateRuntimeError("Updating the data of a group that was not created with a data slot");
        }
        this.slots[dataIndexToDataAddress(auxIndex(this.groups, iGroupIndexToAddress))] = value;
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void updateToTableMaps() {
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime_release();
        this.calledByMap = this.table.getCalledByMap();
    }

    public final void verifyDataAnchors$runtime_release() {
        int i10 = this.slotsGapOwner;
        int length = this.slots.length - this.slotsGapLen;
        int size$runtime_release = getSize$runtime_release();
        int i11 = 0;
        int i12 = 0;
        boolean z = false;
        while (i11 < size$runtime_release) {
            int iGroupIndexToAddress = groupIndexToAddress(i11);
            int iDataAnchor = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress);
            int iDataIndex = dataIndex(this.groups, iGroupIndexToAddress);
            if (!(iDataIndex >= i12)) {
                StringBuilder sbV = androidx.compose.foundation.c.v("Data index out of order at ", i11, ", previous = ", i12, ", current = ");
                sbV.append(iDataIndex);
                PreconditionsKt.throwIllegalStateException(sbV.toString());
            }
            if (!(iDataIndex <= length)) {
                PreconditionsKt.throwIllegalStateException("Data index, " + iDataIndex + ", out of bound at " + i11);
            }
            if (iDataAnchor < 0 && !z) {
                if (!(i10 == i11)) {
                    PreconditionsKt.throwIllegalStateException("Expected the slot gap owner to be " + i10 + " found gap at " + i11);
                }
                z = true;
            }
            i11++;
            i12 = iDataIndex;
        }
    }

    public final void verifyParentAnchors$runtime_release() {
        int i10 = this.groupGapStart;
        int i11 = this.groupGapLen;
        int capacity = getCapacity();
        int i12 = 0;
        while (true) {
            if (i12 >= i10) {
                break;
            }
            if (!(SlotTableKt.parentAnchor(this.groups, i12) > -2)) {
                PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + i12);
            }
            i12++;
        }
        for (int i13 = i11 + i10; i13 < capacity; i13++) {
            int iParentAnchor = SlotTableKt.parentAnchor(this.groups, i13);
            if (parentAnchorToIndex(iParentAnchor) < i10) {
                if (!(iParentAnchor > -2)) {
                    PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + i13);
                }
            } else if (!(iParentAnchor <= -2)) {
                PreconditionsKt.throwIllegalStateException("Expected an end relative anchor at " + i13);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int[] iArr, int i10) {
        return i10 >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.dataAnchor(iArr, i10), this.slotsGapLen, this.slots.length);
    }

    public final boolean isNode(int index) {
        return SlotTableKt.isNode(this.groups, groupIndexToAddress(index));
    }

    public final int parent(Anchor anchor) {
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final Object slot(int groupIndex, int index) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(groupIndex));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
        int i10 = index + iSlotIndex;
        if (iSlotIndex > i10 || i10 >= iDataIndex) {
            return Composer.INSTANCE.getEmpty();
        }
        return this.slots[dataIndexToDataAddress(i10)];
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    public final void startNode(int key, Object objectKey, Object node) {
        startGroup(key, objectKey, true, node);
    }

    public final void updateNode(Anchor anchor, Object value) {
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    private final int parent(int[] iArr, int i10) {
        return parentAnchorToIndex(SlotTableKt.parentAnchor(iArr, groupIndexToAddress(i10)));
    }

    public final Object node(Anchor anchor) {
        return node(anchor.toIndexFor(this));
    }

    public final void startGroup(int key) {
        Composer.Companion companion = Composer.INSTANCE;
        startGroup(key, companion.getEmpty(), false, companion.getEmpty());
    }

    public final Object set(int index, Object value) {
        return set(this.currentGroup, index, value);
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final Object set(int group, int index, Object value) {
        int iDataIndexToDataAddress = dataIndexToDataAddress(slotIndexOfGroupSlotIndex(group, index));
        Object[] objArr = this.slots;
        Object obj = objArr[iDataIndexToDataAddress];
        objArr[iDataIndexToDataAddress] = value;
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v2 */
    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int iGroupSize;
        GroupSourceInformation groupSourceInformationSourceInformationOf;
        int i10 = this.parent;
        Object[] objArr = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (objArr != false) {
            int i11 = this.currentGroup;
            int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i11));
            insertGroups(1);
            this.currentSlot = iDataIndex;
            this.currentSlotEnd = iDataIndex;
            int iGroupIndexToAddress = groupIndexToAddress(i11);
            Composer.Companion companion = Composer.INSTANCE;
            ?? r12 = objectKey != companion.getEmpty() ? 1 : 0;
            ?? r13 = (isNode || aux == companion.getEmpty()) ? 0 : 1;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(iDataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
            if (iDataIndexToDataAnchor >= 0 && this.slotsGapOwner < i11) {
                iDataIndexToDataAnchor = -(((this.slots.length - this.slotsGapLen) - iDataIndexToDataAnchor) + 1);
            }
            SlotTableKt.initGroup(this.groups, iGroupIndexToAddress, key, isNode, r12, r13, this.parent, iDataIndexToDataAnchor);
            int i12 = (isNode ? 1 : 0) + r12 + r13;
            if (i12 > 0) {
                insertSlots(i12, i11);
                Object[] objArr2 = this.slots;
                int i13 = this.currentSlot;
                if (isNode) {
                    objArr2[i13] = aux;
                    i13++;
                }
                if (r12 != 0) {
                    objArr2[i13] = objectKey;
                    i13++;
                }
                if (r13 != 0) {
                    objArr2[i13] = aux;
                    i13++;
                }
                this.currentSlot = i13;
            }
            this.nodeCount = 0;
            iGroupSize = i11 + 1;
            this.parent = i11;
            this.currentGroup = iGroupSize;
            if (i10 >= 0 && (groupSourceInformationSourceInformationOf = sourceInformationOf(i10)) != null) {
                groupSourceInformationSourceInformationOf.reportGroup(this, i11);
            }
        } else {
            this.startStack.push(i10);
            saveCurrentGroupEnd();
            int i14 = this.currentGroup;
            int iGroupIndexToAddress2 = groupIndexToAddress(i14);
            if (!kotlin.jvm.internal.p.a(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, iGroupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress2);
            this.parent = i14;
            this.currentGroup = i14 + 1;
            iGroupSize = i14 + SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2);
        }
        this.currentGroupEnd = iGroupSize;
    }

    public final void ensureStarted(Anchor anchor) {
        ensureStarted(anchor.toIndexFor(this));
    }
}
