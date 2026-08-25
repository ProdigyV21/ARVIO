package io.ktor.http;

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.u;
import org.jsoup.helper.HttpConnection;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\bÊ\u0001\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\fR\u001a\u0010\u000f\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0017\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012R\u001a\u0010\u001b\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0012R\u001a\u0010\u001d\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u001d\u0010\u0010\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b \u0010\u0012R\u001a\u0010!\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b!\u0010\u0010\u001a\u0004\b\"\u0010\u0012R\u001a\u0010#\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b#\u0010\u0010\u001a\u0004\b$\u0010\u0012R\u001a\u0010%\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b%\u0010\u0010\u001a\u0004\b&\u0010\u0012R\u001a\u0010'\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b'\u0010\u0010\u001a\u0004\b(\u0010\u0012R\u001a\u0010)\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b)\u0010\u0010\u001a\u0004\b*\u0010\u0012R\u001a\u0010+\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b+\u0010\u0010\u001a\u0004\b,\u0010\u0012R\u001a\u0010-\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b-\u0010\u0010\u001a\u0004\b.\u0010\u0012R\u001a\u0010/\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b/\u0010\u0010\u001a\u0004\b0\u0010\u0012R\u001a\u00101\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b1\u0010\u0010\u001a\u0004\b2\u0010\u0012R\u001a\u00103\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b3\u0010\u0010\u001a\u0004\b4\u0010\u0012R\u001a\u00105\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b5\u0010\u0010\u001a\u0004\b6\u0010\u0012R\u001a\u00107\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b7\u0010\u0010\u001a\u0004\b8\u0010\u0012R\u001a\u00109\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b9\u0010\u0010\u001a\u0004\b:\u0010\u0012R\u001a\u0010;\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b;\u0010\u0010\u001a\u0004\b<\u0010\u0012R\u001a\u0010=\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b=\u0010\u0010\u001a\u0004\b>\u0010\u0012R\u001a\u0010?\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b?\u0010\u0010\u001a\u0004\b@\u0010\u0012R\u001a\u0010A\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bA\u0010\u0010\u001a\u0004\bB\u0010\u0012R\u001a\u0010C\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bC\u0010\u0010\u001a\u0004\bD\u0010\u0012R\u001a\u0010E\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bE\u0010\u0010\u001a\u0004\bF\u0010\u0012R\u001a\u0010G\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bG\u0010\u0010\u001a\u0004\bH\u0010\u0012R\u001a\u0010I\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bI\u0010\u0010\u001a\u0004\bJ\u0010\u0012R\u001a\u0010K\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bK\u0010\u0010\u001a\u0004\bL\u0010\u0012R\u001a\u0010M\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bM\u0010\u0010\u001a\u0004\bN\u0010\u0012R\u001a\u0010O\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bO\u0010\u0010\u001a\u0004\bP\u0010\u0012R\u001a\u0010Q\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bQ\u0010\u0010\u001a\u0004\bR\u0010\u0012R\u001a\u0010S\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bS\u0010\u0010\u001a\u0004\bT\u0010\u0012R\u001a\u0010U\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bU\u0010\u0010\u001a\u0004\bV\u0010\u0012R\u001a\u0010W\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bW\u0010\u0010\u001a\u0004\bX\u0010\u0012R\u001a\u0010Y\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bY\u0010\u0010\u001a\u0004\bZ\u0010\u0012R\u001a\u0010[\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b[\u0010\u0010\u001a\u0004\b\\\u0010\u0012R\u001a\u0010]\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b]\u0010\u0010\u001a\u0004\b^\u0010\u0012R\u001a\u0010_\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b_\u0010\u0010\u001a\u0004\b`\u0010\u0012R\u001a\u0010a\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\ba\u0010\u0010\u001a\u0004\bb\u0010\u0012R\u001a\u0010c\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bc\u0010\u0010\u001a\u0004\bd\u0010\u0012R\u001a\u0010e\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\be\u0010\u0010\u001a\u0004\bf\u0010\u0012R\u001a\u0010g\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bg\u0010\u0010\u001a\u0004\bh\u0010\u0012R\u001a\u0010i\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bi\u0010\u0010\u001a\u0004\bj\u0010\u0012R\u001a\u0010k\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bk\u0010\u0010\u001a\u0004\bl\u0010\u0012R\u001a\u0010m\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bm\u0010\u0010\u001a\u0004\bn\u0010\u0012R\u001a\u0010o\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bo\u0010\u0010\u001a\u0004\bp\u0010\u0012R\u001a\u0010q\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bq\u0010\u0010\u001a\u0004\br\u0010\u0012R\u001a\u0010s\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bs\u0010\u0010\u001a\u0004\bt\u0010\u0012R\u001a\u0010u\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bu\u0010\u0010\u001a\u0004\bv\u0010\u0012R\u001a\u0010w\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\bw\u0010\u0010\u001a\u0004\bx\u0010\u0012R\u001a\u0010y\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\by\u0010\u0010\u001a\u0004\bz\u0010\u0012R\u001a\u0010{\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b{\u0010\u0010\u001a\u0004\b|\u0010\u0012R\u001a\u0010}\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b}\u0010\u0010\u001a\u0004\b~\u0010\u0012R\u001b\u0010\u007f\u001a\u00020\u00048\u0006X\u0086D¢\u0006\r\n\u0004\b\u007f\u0010\u0010\u001a\u0005\b\u0080\u0001\u0010\u0012R\u001d\u0010\u0081\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010\u0010\u001a\u0005\b\u0082\u0001\u0010\u0012R\u001d\u0010\u0083\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0083\u0001\u0010\u0010\u001a\u0005\b\u0084\u0001\u0010\u0012R\u001d\u0010\u0085\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0085\u0001\u0010\u0010\u001a\u0005\b\u0086\u0001\u0010\u0012R\u001d\u0010\u0087\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0087\u0001\u0010\u0010\u001a\u0005\b\u0088\u0001\u0010\u0012R\u001d\u0010\u0089\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0089\u0001\u0010\u0010\u001a\u0005\b\u008a\u0001\u0010\u0012R\u001d\u0010\u008b\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u008b\u0001\u0010\u0010\u001a\u0005\b\u008c\u0001\u0010\u0012R\u001d\u0010\u008d\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u008d\u0001\u0010\u0010\u001a\u0005\b\u008e\u0001\u0010\u0012R\u001d\u0010\u008f\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u008f\u0001\u0010\u0010\u001a\u0005\b\u0090\u0001\u0010\u0012R\u001d\u0010\u0091\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0091\u0001\u0010\u0010\u001a\u0005\b\u0092\u0001\u0010\u0012R\u001d\u0010\u0093\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0093\u0001\u0010\u0010\u001a\u0005\b\u0094\u0001\u0010\u0012R\u001d\u0010\u0095\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0095\u0001\u0010\u0010\u001a\u0005\b\u0096\u0001\u0010\u0012R\u001d\u0010\u0097\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0097\u0001\u0010\u0010\u001a\u0005\b\u0098\u0001\u0010\u0012R\u001d\u0010\u0099\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u0099\u0001\u0010\u0010\u001a\u0005\b\u009a\u0001\u0010\u0012R\u001d\u0010\u009b\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u009b\u0001\u0010\u0010\u001a\u0005\b\u009c\u0001\u0010\u0012R\u001d\u0010\u009d\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u009d\u0001\u0010\u0010\u001a\u0005\b\u009e\u0001\u0010\u0012R\u001d\u0010\u009f\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u009f\u0001\u0010\u0010\u001a\u0005\b \u0001\u0010\u0012R\u001d\u0010¡\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b¡\u0001\u0010\u0010\u001a\u0005\b¢\u0001\u0010\u0012R\u001d\u0010£\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b£\u0001\u0010\u0010\u001a\u0005\b¤\u0001\u0010\u0012R\u001d\u0010¥\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b¥\u0001\u0010\u0010\u001a\u0005\b¦\u0001\u0010\u0012R\u001d\u0010§\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b§\u0001\u0010\u0010\u001a\u0005\b¨\u0001\u0010\u0012R\u001d\u0010©\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b©\u0001\u0010\u0010\u001a\u0005\bª\u0001\u0010\u0012R\u001d\u0010«\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b«\u0001\u0010\u0010\u001a\u0005\b¬\u0001\u0010\u0012R\u001d\u0010\u00ad\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b\u00ad\u0001\u0010\u0010\u001a\u0005\b®\u0001\u0010\u0012R\u001d\u0010¯\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b¯\u0001\u0010\u0010\u001a\u0005\b°\u0001\u0010\u0012R\u001d\u0010±\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b±\u0001\u0010\u0010\u001a\u0005\b²\u0001\u0010\u0012R\u001d\u0010³\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b³\u0001\u0010\u0010\u001a\u0005\b´\u0001\u0010\u0012R\u001d\u0010µ\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bµ\u0001\u0010\u0010\u001a\u0005\b¶\u0001\u0010\u0012R\u001d\u0010·\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b·\u0001\u0010\u0010\u001a\u0005\b¸\u0001\u0010\u0012R\u001d\u0010¹\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b¹\u0001\u0010\u0010\u001a\u0005\bº\u0001\u0010\u0012R\u001d\u0010»\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b»\u0001\u0010\u0010\u001a\u0005\b¼\u0001\u0010\u0012R\u001d\u0010½\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b½\u0001\u0010\u0010\u001a\u0005\b¾\u0001\u0010\u0012R\u001d\u0010¿\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\b¿\u0001\u0010\u0010\u001a\u0005\bÀ\u0001\u0010\u0012R\u001d\u0010Á\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÁ\u0001\u0010\u0010\u001a\u0005\bÂ\u0001\u0010\u0012R\u001d\u0010Ã\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÃ\u0001\u0010\u0010\u001a\u0005\bÄ\u0001\u0010\u0012R\u001d\u0010Å\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÅ\u0001\u0010\u0010\u001a\u0005\bÆ\u0001\u0010\u0012R\u001d\u0010Ç\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÇ\u0001\u0010\u0010\u001a\u0005\bÈ\u0001\u0010\u0012R\u001d\u0010É\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÉ\u0001\u0010\u0010\u001a\u0005\bÊ\u0001\u0010\u0012R\u001d\u0010Ë\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bË\u0001\u0010\u0010\u001a\u0005\bÌ\u0001\u0010\u0012R\u001d\u0010Í\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÍ\u0001\u0010\u0010\u001a\u0005\bÎ\u0001\u0010\u0012R\u001d\u0010Ï\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÏ\u0001\u0010\u0010\u001a\u0005\bÐ\u0001\u0010\u0012R\u001d\u0010Ñ\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÑ\u0001\u0010\u0010\u001a\u0005\bÒ\u0001\u0010\u0012R\u001d\u0010Ó\u0001\u001a\u00020\u00048\u0006X\u0086D¢\u0006\u000e\n\u0005\bÓ\u0001\u0010\u0010\u001a\u0005\bÔ\u0001\u0010\u0012R\u001e\u0010Ö\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040Õ\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÖ\u0001\u0010×\u0001R#\u0010Ù\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040Ø\u00018\u0006¢\u0006\u0010\n\u0006\bÙ\u0001\u0010Ú\u0001\u001a\u0006\bÛ\u0001\u0010Ü\u0001R%\u0010à\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040Õ\u00018FX\u0087\u0004¢\u0006\u000f\u0012\u0005\bß\u0001\u0010\u0003\u001a\u0006\bÝ\u0001\u0010Þ\u0001¨\u0006á\u0001"}, d2 = {"Lio/ktor/http/HttpHeaders;", "", "<init>", "()V", "", "header", "", "isUnsafe", "(Ljava/lang/String;)Z", ContentDisposition.Parameters.Name, "Lx6/t0;", "checkHeaderName", "(Ljava/lang/String;)V", "value", "checkHeaderValue", "Accept", "Ljava/lang/String;", "getAccept", "()Ljava/lang/String;", "AcceptCharset", "getAcceptCharset", "AcceptEncoding", "getAcceptEncoding", "AcceptLanguage", "getAcceptLanguage", "AcceptRanges", "getAcceptRanges", "Age", "getAge", "Allow", "getAllow", "ALPN", "getALPN", "AuthenticationInfo", "getAuthenticationInfo", "Authorization", "getAuthorization", "CacheControl", "getCacheControl", "Connection", "getConnection", "ContentDisposition", "getContentDisposition", "ContentEncoding", "getContentEncoding", "ContentLanguage", "getContentLanguage", "ContentLength", "getContentLength", "ContentLocation", "getContentLocation", "ContentRange", "getContentRange", "ContentType", "getContentType", "Cookie", "getCookie", "DASL", "getDASL", "Date", "getDate", "DAV", "getDAV", "Depth", "getDepth", "Destination", "getDestination", "ETag", "getETag", "Expect", "getExpect", "Expires", "getExpires", "From", "getFrom", "Forwarded", "getForwarded", "Host", "getHost", "HTTP2Settings", "getHTTP2Settings", "If", "getIf", "IfMatch", "getIfMatch", "IfModifiedSince", "getIfModifiedSince", "IfNoneMatch", "getIfNoneMatch", "IfRange", "getIfRange", "IfScheduleTagMatch", "getIfScheduleTagMatch", "IfUnmodifiedSince", "getIfUnmodifiedSince", "LastModified", "getLastModified", "Location", "getLocation", "LockToken", "getLockToken", "Link", "getLink", "MaxForwards", "getMaxForwards", "MIMEVersion", "getMIMEVersion", "OrderingType", "getOrderingType", "Origin", "getOrigin", "Overwrite", "getOverwrite", "Position", "getPosition", "Pragma", "getPragma", PostgrestQueryBuilder.HEADER_PREFER, "getPrefer", "PreferenceApplied", "getPreferenceApplied", "ProxyAuthenticate", "getProxyAuthenticate", "ProxyAuthenticationInfo", "getProxyAuthenticationInfo", "ProxyAuthorization", "getProxyAuthorization", "PublicKeyPins", "getPublicKeyPins", "PublicKeyPinsReportOnly", "getPublicKeyPinsReportOnly", "Range", "getRange", "Referrer", "getReferrer", "RetryAfter", "getRetryAfter", "ScheduleReply", "getScheduleReply", "ScheduleTag", "getScheduleTag", "SecWebSocketAccept", "getSecWebSocketAccept", "SecWebSocketExtensions", "getSecWebSocketExtensions", "SecWebSocketKey", "getSecWebSocketKey", "SecWebSocketProtocol", "getSecWebSocketProtocol", "SecWebSocketVersion", "getSecWebSocketVersion", "Server", "getServer", "SetCookie", "getSetCookie", "SLUG", "getSLUG", "StrictTransportSecurity", "getStrictTransportSecurity", "TE", "getTE", "Timeout", "getTimeout", "Trailer", "getTrailer", "TransferEncoding", "getTransferEncoding", "Upgrade", "getUpgrade", "UserAgent", "getUserAgent", "Vary", "getVary", "Via", "getVia", "Warning", "getWarning", "WWWAuthenticate", "getWWWAuthenticate", "AccessControlAllowOrigin", "getAccessControlAllowOrigin", "AccessControlAllowMethods", "getAccessControlAllowMethods", "AccessControlAllowCredentials", "getAccessControlAllowCredentials", "AccessControlAllowHeaders", "getAccessControlAllowHeaders", "AccessControlRequestMethod", "getAccessControlRequestMethod", "AccessControlRequestHeaders", "getAccessControlRequestHeaders", "AccessControlExposeHeaders", "getAccessControlExposeHeaders", "AccessControlMaxAge", "getAccessControlMaxAge", "XHttpMethodOverride", "getXHttpMethodOverride", "XForwardedHost", "getXForwardedHost", "XForwardedServer", "getXForwardedServer", "XForwardedProto", "getXForwardedProto", "XForwardedFor", "getXForwardedFor", "XForwardedPort", "getXForwardedPort", "XRequestId", "getXRequestId", "XCorrelationId", "getXCorrelationId", "XTotalCount", "getXTotalCount", "", "UnsafeHeadersArray", "[Ljava/lang/String;", "", "UnsafeHeadersList", "Ljava/util/List;", "getUnsafeHeadersList", "()Ljava/util/List;", "getUnsafeHeaders", "()[Ljava/lang/String;", "getUnsafeHeaders$annotations", "UnsafeHeaders", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpHeaders {
    private static final String[] UnsafeHeadersArray;
    private static final List<String> UnsafeHeadersList;
    public static final HttpHeaders INSTANCE = new HttpHeaders();
    private static final String Accept = "Accept";
    private static final String AcceptCharset = "Accept-Charset";
    private static final String AcceptEncoding = "Accept-Encoding";
    private static final String AcceptLanguage = "Accept-Language";
    private static final String AcceptRanges = "Accept-Ranges";
    private static final String Age = "Age";
    private static final String Allow = "Allow";
    private static final String ALPN = "ALPN";
    private static final String AuthenticationInfo = "Authentication-Info";
    private static final String Authorization = "Authorization";
    private static final String CacheControl = "Cache-Control";
    private static final String Connection = "Connection";
    private static final String ContentDisposition = "Content-Disposition";
    private static final String ContentEncoding = HttpConnection.CONTENT_ENCODING;
    private static final String ContentLanguage = "Content-Language";
    private static final String ContentLength = "Content-Length";
    private static final String ContentLocation = "Content-Location";
    private static final String ContentRange = "Content-Range";
    private static final String ContentType = HttpConnection.CONTENT_TYPE;
    private static final String Cookie = "Cookie";
    private static final String DASL = "DASL";
    private static final String Date = "Date";
    private static final String DAV = "DAV";
    private static final String Depth = "Depth";
    private static final String Destination = "Destination";
    private static final String ETag = "ETag";
    private static final String Expect = "Expect";
    private static final String Expires = "Expires";
    private static final String From = "From";
    private static final String Forwarded = "Forwarded";
    private static final String Host = "Host";
    private static final String HTTP2Settings = "HTTP2-Settings";
    private static final String If = "If";
    private static final String IfMatch = "If-Match";
    private static final String IfModifiedSince = "If-Modified-Since";
    private static final String IfNoneMatch = "If-None-Match";
    private static final String IfRange = "If-Range";
    private static final String IfScheduleTagMatch = "If-Schedule-Tag-Match";
    private static final String IfUnmodifiedSince = "If-Unmodified-Since";
    private static final String LastModified = "Last-Modified";
    private static final String Location = "Location";
    private static final String LockToken = "Lock-Token";
    private static final String Link = "Link";
    private static final String MaxForwards = "Max-Forwards";
    private static final String MIMEVersion = "MIME-Version";
    private static final String OrderingType = "Ordering-Type";
    private static final String Origin = "Origin";
    private static final String Overwrite = "Overwrite";
    private static final String Position = "Position";
    private static final String Pragma = "Pragma";
    private static final String Prefer = PostgrestQueryBuilder.HEADER_PREFER;
    private static final String PreferenceApplied = "Preference-Applied";
    private static final String ProxyAuthenticate = "Proxy-Authenticate";
    private static final String ProxyAuthenticationInfo = "Proxy-Authentication-Info";
    private static final String ProxyAuthorization = "Proxy-Authorization";
    private static final String PublicKeyPins = "Public-Key-Pins";
    private static final String PublicKeyPinsReportOnly = "Public-Key-Pins-Report-Only";
    private static final String Range = "Range";
    private static final String Referrer = "Referer";
    private static final String RetryAfter = "Retry-After";
    private static final String ScheduleReply = "Schedule-Reply";
    private static final String ScheduleTag = "Schedule-Tag";
    private static final String SecWebSocketAccept = "Sec-WebSocket-Accept";
    private static final String SecWebSocketExtensions = "Sec-WebSocket-Extensions";
    private static final String SecWebSocketKey = "Sec-WebSocket-Key";
    private static final String SecWebSocketProtocol = "Sec-WebSocket-Protocol";
    private static final String SecWebSocketVersion = "Sec-WebSocket-Version";
    private static final String Server = "Server";
    private static final String SetCookie = "Set-Cookie";
    private static final String SLUG = "SLUG";
    private static final String StrictTransportSecurity = "Strict-Transport-Security";
    private static final String TE = "TE";
    private static final String Timeout = "Timeout";
    private static final String Trailer = "Trailer";
    private static final String TransferEncoding = "Transfer-Encoding";
    private static final String Upgrade = "Upgrade";
    private static final String UserAgent = "User-Agent";
    private static final String Vary = "Vary";
    private static final String Via = "Via";
    private static final String Warning = "Warning";
    private static final String WWWAuthenticate = "WWW-Authenticate";
    private static final String AccessControlAllowOrigin = "Access-Control-Allow-Origin";
    private static final String AccessControlAllowMethods = "Access-Control-Allow-Methods";
    private static final String AccessControlAllowCredentials = "Access-Control-Allow-Credentials";
    private static final String AccessControlAllowHeaders = "Access-Control-Allow-Headers";
    private static final String AccessControlRequestMethod = "Access-Control-Request-Method";
    private static final String AccessControlRequestHeaders = "Access-Control-Request-Headers";
    private static final String AccessControlExposeHeaders = "Access-Control-Expose-Headers";
    private static final String AccessControlMaxAge = "Access-Control-Max-Age";
    private static final String XHttpMethodOverride = "X-Http-Method-Override";
    private static final String XForwardedHost = "X-Forwarded-Host";
    private static final String XForwardedServer = "X-Forwarded-Server";
    private static final String XForwardedProto = "X-Forwarded-Proto";
    private static final String XForwardedFor = "X-Forwarded-For";
    private static final String XForwardedPort = "X-Forwarded-Port";
    private static final String XRequestId = "X-Request-ID";
    private static final String XCorrelationId = "X-Correlation-ID";
    private static final String XTotalCount = "X-Total-Count";

    static {
        String[] strArr = {"Transfer-Encoding", "Upgrade"};
        UnsafeHeadersArray = strArr;
        UnsafeHeadersList = Arrays.asList(strArr);
    }

    private HttpHeaders() {
    }

    @e
    public static /* synthetic */ void getUnsafeHeaders$annotations() {
    }

    public final void checkHeaderName(String name) {
        int i10 = 0;
        int i11 = 0;
        while (i10 < name.length()) {
            char cCharAt = name.charAt(i10);
            int i12 = i11 + 1;
            if (p.c(cCharAt, 32) <= 0 || HttpHeadersKt.isDelimiter(cCharAt)) {
                throw new IllegalHeaderNameException(name, i11);
            }
            i10++;
            i11 = i12;
        }
    }

    public final void checkHeaderValue(String value) {
        int i10 = 0;
        int i11 = 0;
        while (i10 < value.length()) {
            char cCharAt = value.charAt(i10);
            int i12 = i11 + 1;
            if (p.c(cCharAt, 32) < 0 && cCharAt != '\t') {
                throw new IllegalHeaderValueException(value, i11);
            }
            i10++;
            i11 = i12;
        }
    }

    public final String getALPN() {
        return ALPN;
    }

    public final String getAccept() {
        return Accept;
    }

    public final String getAcceptCharset() {
        return AcceptCharset;
    }

    public final String getAcceptEncoding() {
        return AcceptEncoding;
    }

    public final String getAcceptLanguage() {
        return AcceptLanguage;
    }

    public final String getAcceptRanges() {
        return AcceptRanges;
    }

    public final String getAccessControlAllowCredentials() {
        return AccessControlAllowCredentials;
    }

    public final String getAccessControlAllowHeaders() {
        return AccessControlAllowHeaders;
    }

    public final String getAccessControlAllowMethods() {
        return AccessControlAllowMethods;
    }

    public final String getAccessControlAllowOrigin() {
        return AccessControlAllowOrigin;
    }

    public final String getAccessControlExposeHeaders() {
        return AccessControlExposeHeaders;
    }

    public final String getAccessControlMaxAge() {
        return AccessControlMaxAge;
    }

    public final String getAccessControlRequestHeaders() {
        return AccessControlRequestHeaders;
    }

    public final String getAccessControlRequestMethod() {
        return AccessControlRequestMethod;
    }

    public final String getAge() {
        return Age;
    }

    public final String getAllow() {
        return Allow;
    }

    public final String getAuthenticationInfo() {
        return AuthenticationInfo;
    }

    public final String getAuthorization() {
        return Authorization;
    }

    public final String getCacheControl() {
        return CacheControl;
    }

    public final String getConnection() {
        return Connection;
    }

    public final String getContentDisposition() {
        return ContentDisposition;
    }

    public final String getContentEncoding() {
        return ContentEncoding;
    }

    public final String getContentLanguage() {
        return ContentLanguage;
    }

    public final String getContentLength() {
        return ContentLength;
    }

    public final String getContentLocation() {
        return ContentLocation;
    }

    public final String getContentRange() {
        return ContentRange;
    }

    public final String getContentType() {
        return ContentType;
    }

    public final String getCookie() {
        return Cookie;
    }

    public final String getDASL() {
        return DASL;
    }

    public final String getDAV() {
        return DAV;
    }

    public final String getDate() {
        return Date;
    }

    public final String getDepth() {
        return Depth;
    }

    public final String getDestination() {
        return Destination;
    }

    public final String getETag() {
        return ETag;
    }

    public final String getExpect() {
        return Expect;
    }

    public final String getExpires() {
        return Expires;
    }

    public final String getForwarded() {
        return Forwarded;
    }

    public final String getFrom() {
        return From;
    }

    public final String getHTTP2Settings() {
        return HTTP2Settings;
    }

    public final String getHost() {
        return Host;
    }

    public final String getIf() {
        return If;
    }

    public final String getIfMatch() {
        return IfMatch;
    }

    public final String getIfModifiedSince() {
        return IfModifiedSince;
    }

    public final String getIfNoneMatch() {
        return IfNoneMatch;
    }

    public final String getIfRange() {
        return IfRange;
    }

    public final String getIfScheduleTagMatch() {
        return IfScheduleTagMatch;
    }

    public final String getIfUnmodifiedSince() {
        return IfUnmodifiedSince;
    }

    public final String getLastModified() {
        return LastModified;
    }

    public final String getLink() {
        return Link;
    }

    public final String getLocation() {
        return Location;
    }

    public final String getLockToken() {
        return LockToken;
    }

    public final String getMIMEVersion() {
        return MIMEVersion;
    }

    public final String getMaxForwards() {
        return MaxForwards;
    }

    public final String getOrderingType() {
        return OrderingType;
    }

    public final String getOrigin() {
        return Origin;
    }

    public final String getOverwrite() {
        return Overwrite;
    }

    public final String getPosition() {
        return Position;
    }

    public final String getPragma() {
        return Pragma;
    }

    public final String getPrefer() {
        return Prefer;
    }

    public final String getPreferenceApplied() {
        return PreferenceApplied;
    }

    public final String getProxyAuthenticate() {
        return ProxyAuthenticate;
    }

    public final String getProxyAuthenticationInfo() {
        return ProxyAuthenticationInfo;
    }

    public final String getProxyAuthorization() {
        return ProxyAuthorization;
    }

    public final String getPublicKeyPins() {
        return PublicKeyPins;
    }

    public final String getPublicKeyPinsReportOnly() {
        return PublicKeyPinsReportOnly;
    }

    public final String getRange() {
        return Range;
    }

    public final String getReferrer() {
        return Referrer;
    }

    public final String getRetryAfter() {
        return RetryAfter;
    }

    public final String getSLUG() {
        return SLUG;
    }

    public final String getScheduleReply() {
        return ScheduleReply;
    }

    public final String getScheduleTag() {
        return ScheduleTag;
    }

    public final String getSecWebSocketAccept() {
        return SecWebSocketAccept;
    }

    public final String getSecWebSocketExtensions() {
        return SecWebSocketExtensions;
    }

    public final String getSecWebSocketKey() {
        return SecWebSocketKey;
    }

    public final String getSecWebSocketProtocol() {
        return SecWebSocketProtocol;
    }

    public final String getSecWebSocketVersion() {
        return SecWebSocketVersion;
    }

    public final String getServer() {
        return Server;
    }

    public final String getSetCookie() {
        return SetCookie;
    }

    public final String getStrictTransportSecurity() {
        return StrictTransportSecurity;
    }

    public final String getTE() {
        return TE;
    }

    public final String getTimeout() {
        return Timeout;
    }

    public final String getTrailer() {
        return Trailer;
    }

    public final String getTransferEncoding() {
        return TransferEncoding;
    }

    public final String[] getUnsafeHeaders() {
        String[] strArr = UnsafeHeadersArray;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public final List<String> getUnsafeHeadersList() {
        return UnsafeHeadersList;
    }

    public final String getUpgrade() {
        return Upgrade;
    }

    public final String getUserAgent() {
        return UserAgent;
    }

    public final String getVary() {
        return Vary;
    }

    public final String getVia() {
        return Via;
    }

    public final String getWWWAuthenticate() {
        return WWWAuthenticate;
    }

    public final String getWarning() {
        return Warning;
    }

    public final String getXCorrelationId() {
        return XCorrelationId;
    }

    public final String getXForwardedFor() {
        return XForwardedFor;
    }

    public final String getXForwardedHost() {
        return XForwardedHost;
    }

    public final String getXForwardedPort() {
        return XForwardedPort;
    }

    public final String getXForwardedProto() {
        return XForwardedProto;
    }

    public final String getXForwardedServer() {
        return XForwardedServer;
    }

    public final String getXHttpMethodOverride() {
        return XHttpMethodOverride;
    }

    public final String getXRequestId() {
        return XRequestId;
    }

    public final String getXTotalCount() {
        return XTotalCount;
    }

    public final boolean isUnsafe(String header) {
        for (String str : UnsafeHeadersArray) {
            if (u.L(str, header, true)) {
                return true;
            }
        }
        return false;
    }
}
