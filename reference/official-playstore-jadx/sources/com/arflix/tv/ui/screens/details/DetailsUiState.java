package com.arflix.tv.ui.screens.details;

import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.PersonDetails;
import com.arflix.tv.data.model.Review;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0003\b\u0093\u0001\b\u0087\b\u0018\u00002\u00020\u0001B\u009f\u0005\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\f\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\f\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0014\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\f\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\f\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\f\u0012\b\b\u0002\u0010!\u001a\u00020\u0002\u0012\b\b\u0002\u0010#\u001a\u00020\"\u0012\b\b\u0002\u0010$\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060%\u0012\b\b\u0002\u0010'\u001a\u00020\b\u0012\b\b\u0002\u0010(\u001a\u00020\b\u0012\b\b\u0002\u0010)\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\f\u0012\b\b\u0002\u0010+\u001a\u00020\u0002\u0012\b\b\u0002\u0010,\u001a\u00020\u0002\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010/\u001a\u00020.\u0012\u000e\b\u0002\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060\f\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u0002040\f\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u00107\u001a\u00020\b\u0012\b\b\u0002\u00108\u001a\u00020\b\u0012 \b\u0002\u0010:\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b090\u0014\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\"\u0012\b\b\u0002\u0010A\u001a\u00020\u0002\u0012\b\b\u0002\u0010B\u001a\u00020\u0006\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\f\u0012\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bI\u0010JJ\u0012\u0010K\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\bK\u0010LJ\u0012\u0010M\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bM\u0010NJ\u0012\u0010O\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\bO\u0010PJ\u0012\u0010Q\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bQ\u0010NJ\u0012\u0010R\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bR\u0010NJ\u0016\u0010S\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003¢\u0006\u0004\bS\u0010TJ\u0010\u0010U\u001a\u00020\bHÆ\u0003¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020\bHÆ\u0003¢\u0006\u0004\bW\u0010VJ\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00110\fHÆ\u0003¢\u0006\u0004\bX\u0010TJ\u0016\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00040\fHÆ\u0003¢\u0006\u0004\bY\u0010TJ\u001c\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0014HÆ\u0003¢\u0006\u0004\bZ\u0010[J\u0016\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00160\fHÆ\u0003¢\u0006\u0004\b\\\u0010TJ\u0012\u0010]\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b]\u0010NJ\u0010\u0010^\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b^\u0010JJ\u0012\u0010_\u001a\u0004\u0018\u00010\u001aHÆ\u0003¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\ba\u0010JJ\u0016\u0010b\u001a\b\u0012\u0004\u0012\u00020\u001d0\fHÆ\u0003¢\u0006\u0004\bb\u0010TJ\u0016\u0010c\u001a\b\u0012\u0004\u0012\u00020\u001f0\fHÆ\u0003¢\u0006\u0004\bc\u0010TJ\u0010\u0010d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bd\u0010JJ\u0010\u0010e\u001a\u00020\"HÆ\u0003¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bg\u0010JJ\u0016\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00060%HÆ\u0003¢\u0006\u0004\bh\u0010iJ\u0010\u0010j\u001a\u00020\bHÆ\u0003¢\u0006\u0004\bj\u0010VJ\u0010\u0010k\u001a\u00020\bHÆ\u0003¢\u0006\u0004\bk\u0010VJ\u0010\u0010l\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bl\u0010JJ\u0016\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00060\fHÆ\u0003¢\u0006\u0004\bm\u0010TJ\u0010\u0010n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bn\u0010JJ\u0010\u0010o\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bo\u0010JJ\u0012\u0010p\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bp\u0010NJ\u0010\u0010q\u001a\u00020.HÆ\u0003¢\u0006\u0004\bq\u0010rJ\u0016\u0010s\u001a\b\u0012\u0004\u0012\u00020\u00060\fHÆ\u0003¢\u0006\u0004\bs\u0010TJ\u0012\u0010t\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bt\u0010NJ\u0012\u0010u\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bu\u0010NJ\u0012\u0010v\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bv\u0010NJ\u0016\u0010w\u001a\b\u0012\u0004\u0012\u0002040\fHÆ\u0003¢\u0006\u0004\bw\u0010TJ\u0012\u0010x\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\bx\u0010NJ\u0010\u0010y\u001a\u00020\bHÆ\u0003¢\u0006\u0004\by\u0010VJ\u0010\u0010z\u001a\u00020\bHÆ\u0003¢\u0006\u0004\bz\u0010VJ(\u0010{\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b090\u0014HÆ\u0003¢\u0006\u0004\b{\u0010[J\u0012\u0010|\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b|\u0010PJ\u0012\u0010}\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b}\u0010PJ\u0012\u0010~\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b~\u0010PJ\u0012\u0010\u007f\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u007f\u0010PJ\u0014\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0005\b\u0080\u0001\u0010NJ\u0015\u0010\u0081\u0001\u001a\u0004\u0018\u00010\"HÆ\u0003¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u0012\u0010\u0083\u0001\u001a\u00020\u0002HÆ\u0003¢\u0006\u0005\b\u0083\u0001\u0010JJ\u0012\u0010\u0084\u0001\u001a\u00020\u0006HÆ\u0003¢\u0006\u0005\b\u0084\u0001\u0010NJ\u0014\u0010\u0085\u0001\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0005\b\u0085\u0001\u0010PJ\u0014\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0005\b\u0086\u0001\u0010NJ\u0018\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\fHÆ\u0003¢\u0006\u0005\b\u0087\u0001\u0010TJ\u0014\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0005\b\u0088\u0001\u0010NJ«\u0005\u0010\u0089\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00142\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00022\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00022\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u00022\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060%2\b\b\u0002\u0010'\u001a\u00020\b2\b\b\u0002\u0010(\u001a\u00020\b2\b\b\u0002\u0010)\u001a\u00020\u00022\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\b\b\u0002\u0010+\u001a\u00020\u00022\b\b\u0002\u0010,\u001a\u00020\u00022\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010/\u001a\u00020.2\u000e\b\u0002\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u0002040\f2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u00107\u001a\u00020\b2\b\b\u0002\u00108\u001a\u00020\b2 \b\u0002\u0010:\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b090\u00142\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010A\u001a\u00020\u00022\b\b\u0002\u0010B\u001a\u00020\u00062\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0012\u0010\u008b\u0001\u001a\u00020\u0006HÖ\u0001¢\u0006\u0005\b\u008b\u0001\u0010NJ\u0012\u0010\u008c\u0001\u001a\u00020\bHÖ\u0001¢\u0006\u0005\b\u008c\u0001\u0010VJ\u001e\u0010\u008e\u0001\u001a\u00020\u00022\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0018\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\r\n\u0005\b\u0003\u0010\u0090\u0001\u001a\u0004\b\u0003\u0010JR\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\u000e\n\u0005\b\u0005\u0010\u0091\u0001\u001a\u0005\b\u0092\u0001\u0010LR\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b\u0007\u0010\u0093\u0001\u001a\u0005\b\u0094\u0001\u0010NR\u001b\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\u000e\n\u0005\b\t\u0010\u0095\u0001\u001a\u0005\b\u0096\u0001\u0010PR\u001b\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b\n\u0010\u0093\u0001\u001a\u0005\b\u0097\u0001\u0010NR\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b\u000b\u0010\u0093\u0001\u001a\u0005\b\u0098\u0001\u0010NR\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006¢\u0006\u000e\n\u0005\b\u000e\u0010\u0099\u0001\u001a\u0005\b\u009a\u0001\u0010TR\u0019\u0010\u000f\u001a\u00020\b8\u0006¢\u0006\u000e\n\u0005\b\u000f\u0010\u009b\u0001\u001a\u0005\b\u009c\u0001\u0010VR\u0019\u0010\u0010\u001a\u00020\b8\u0006¢\u0006\u000e\n\u0005\b\u0010\u0010\u009b\u0001\u001a\u0005\b\u009d\u0001\u0010VR\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\f8\u0006¢\u0006\u000e\n\u0005\b\u0012\u0010\u0099\u0001\u001a\u0005\b\u009e\u0001\u0010TR\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\f8\u0006¢\u0006\u000e\n\u0005\b\u0013\u0010\u0099\u0001\u001a\u0005\b\u009f\u0001\u0010TR%\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00148\u0006¢\u0006\u000e\n\u0005\b\u0015\u0010 \u0001\u001a\u0005\b¡\u0001\u0010[R\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\f8\u0006¢\u0006\u000e\n\u0005\b\u0017\u0010\u0099\u0001\u001a\u0005\b¢\u0001\u0010TR\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b\u0018\u0010\u0093\u0001\u001a\u0005\b£\u0001\u0010NR\u0019\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u0019\u0010\u0090\u0001\u001a\u0005\b¤\u0001\u0010JR\u001b\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006¢\u0006\u000e\n\u0005\b\u001b\u0010¥\u0001\u001a\u0005\b¦\u0001\u0010`R\u0018\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\r\n\u0005\b\u001c\u0010\u0090\u0001\u001a\u0004\b\u001c\u0010JR\u001f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\f8\u0006¢\u0006\u000e\n\u0005\b\u001e\u0010\u0099\u0001\u001a\u0005\b§\u0001\u0010TR\u001f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\f8\u0006¢\u0006\u000e\n\u0005\b \u0010\u0099\u0001\u001a\u0005\b¨\u0001\u0010TR\u0018\u0010!\u001a\u00020\u00028\u0006¢\u0006\r\n\u0005\b!\u0010\u0090\u0001\u001a\u0004\b!\u0010JR\u0019\u0010#\u001a\u00020\"8\u0006¢\u0006\u000e\n\u0005\b#\u0010©\u0001\u001a\u0005\bª\u0001\u0010fR\u0019\u0010$\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b$\u0010\u0090\u0001\u001a\u0005\b«\u0001\u0010JR\u001f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060%8\u0006¢\u0006\u000e\n\u0005\b&\u0010¬\u0001\u001a\u0005\b\u00ad\u0001\u0010iR\u0019\u0010'\u001a\u00020\b8\u0006¢\u0006\u000e\n\u0005\b'\u0010\u009b\u0001\u001a\u0005\b®\u0001\u0010VR\u0019\u0010(\u001a\u00020\b8\u0006¢\u0006\u000e\n\u0005\b(\u0010\u009b\u0001\u001a\u0005\b¯\u0001\u0010VR\u0019\u0010)\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b)\u0010\u0090\u0001\u001a\u0005\b°\u0001\u0010JR\u001f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\f8\u0006¢\u0006\u000e\n\u0005\b*\u0010\u0099\u0001\u001a\u0005\b±\u0001\u0010TR\u0018\u0010+\u001a\u00020\u00028\u0006¢\u0006\r\n\u0005\b+\u0010\u0090\u0001\u001a\u0004\b+\u0010JR\u0019\u0010,\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b,\u0010\u0090\u0001\u001a\u0005\b²\u0001\u0010JR\u001b\u0010-\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b-\u0010\u0093\u0001\u001a\u0005\b³\u0001\u0010NR\u0019\u0010/\u001a\u00020.8\u0006¢\u0006\u000e\n\u0005\b/\u0010´\u0001\u001a\u0005\bµ\u0001\u0010rR\u001f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060\f8\u0006¢\u0006\u000e\n\u0005\b0\u0010\u0099\u0001\u001a\u0005\b¶\u0001\u0010TR\u001b\u00101\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b1\u0010\u0093\u0001\u001a\u0005\b·\u0001\u0010NR\u001b\u00102\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b2\u0010\u0093\u0001\u001a\u0005\b¸\u0001\u0010NR\u001b\u00103\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b3\u0010\u0093\u0001\u001a\u0005\b¹\u0001\u0010NR\u001f\u00105\u001a\b\u0012\u0004\u0012\u0002040\f8\u0006¢\u0006\u000e\n\u0005\b5\u0010\u0099\u0001\u001a\u0005\bº\u0001\u0010TR\u001b\u00106\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b6\u0010\u0093\u0001\u001a\u0005\b»\u0001\u0010NR\u0019\u00107\u001a\u00020\b8\u0006¢\u0006\u000e\n\u0005\b7\u0010\u009b\u0001\u001a\u0005\b¼\u0001\u0010VR\u0019\u00108\u001a\u00020\b8\u0006¢\u0006\u000e\n\u0005\b8\u0010\u009b\u0001\u001a\u0005\b½\u0001\u0010VR1\u0010:\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b090\u00148\u0006¢\u0006\u000e\n\u0005\b:\u0010 \u0001\u001a\u0005\b¾\u0001\u0010[R\u001b\u0010;\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\u000e\n\u0005\b;\u0010\u0095\u0001\u001a\u0005\b¿\u0001\u0010PR\u001b\u0010<\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\u000e\n\u0005\b<\u0010\u0095\u0001\u001a\u0005\bÀ\u0001\u0010PR\u001b\u0010=\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\u000e\n\u0005\b=\u0010\u0095\u0001\u001a\u0005\bÁ\u0001\u0010PR\u001b\u0010>\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\u000e\n\u0005\b>\u0010\u0095\u0001\u001a\u0005\bÂ\u0001\u0010PR\u001b\u0010?\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\b?\u0010\u0093\u0001\u001a\u0005\bÃ\u0001\u0010NR\u001c\u0010@\u001a\u0004\u0018\u00010\"8\u0006¢\u0006\u000f\n\u0005\b@\u0010Ä\u0001\u001a\u0006\bÅ\u0001\u0010\u0082\u0001R\u0019\u0010A\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\bA\u0010\u0090\u0001\u001a\u0005\bÆ\u0001\u0010JR\u0019\u0010B\u001a\u00020\u00068\u0006¢\u0006\u000e\n\u0005\bB\u0010\u0093\u0001\u001a\u0005\bÇ\u0001\u0010NR\u001b\u0010C\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\u000e\n\u0005\bC\u0010\u0095\u0001\u001a\u0005\bÈ\u0001\u0010PR\u001b\u0010D\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\bD\u0010\u0093\u0001\u001a\u0005\bÉ\u0001\u0010NR\u001f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\f8\u0006¢\u0006\u000e\n\u0005\bE\u0010\u0099\u0001\u001a\u0005\bÊ\u0001\u0010TR\u001b\u0010F\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\u000e\n\u0005\bF\u0010\u0093\u0001\u001a\u0005\bË\u0001\u0010N¨\u0006Ì\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/details/DetailsUiState;", "", "", "isLoading", "Lcom/arflix/tv/data/model/MediaItem;", "item", "", "imdbId", "", "tvdbId", "logoUrl", "trailerKey", "", "Lcom/arflix/tv/data/model/Episode;", "episodes", "totalSeasons", "currentSeason", "Lcom/arflix/tv/data/model/CastMember;", "cast", "similar", "", "similarLogoUrls", "Lcom/arflix/tv/data/model/Review;", "reviews", "error", "showPersonModal", "Lcom/arflix/tv/data/model/PersonDetails;", "selectedPerson", "isLoadingPerson", "Lcom/arflix/tv/data/model/StreamSource;", "streams", "Lcom/arflix/tv/data/model/Subtitle;", "subtitles", "isLoadingStreams", "", "streamSearchStartTime", "pluginScrapersLoading", "", "loadingPluginNames", "completedAddons", "totalAddons", "hasStreamingAddons", "addonOrderedIds", "isInWatchlist", "showEpisodeRatings", "toastMessage", "Lcom/arflix/tv/ui/screens/details/ToastType;", "toastType", "genres", "language", "budget", "showStatus", "Lcom/arflix/tv/ui/screens/details/StreamingServiceUi;", "streamingServices", "providerRegion", "initialEpisodeIndex", "initialSeasonIndex", "Lx6/x;", "seasonProgress", "playSeason", "playEpisode", "playTmdbSeason", "playTmdbEpisode", "playLabel", "playPositionMs", "autoPlaySingleSource", "autoPlayMinQuality", "collectionId", "collectionName", "collectionItems", "collectionPosterPath", "<init>", "(ZLcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;IILjava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;ZLcom/arflix/tv/data/model/PersonDetails;ZLjava/util/List;Ljava/util/List;ZJZLjava/util/Set;IIZLjava/util/List;ZZLjava/lang/String;Lcom/arflix/tv/ui/screens/details/ToastType;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;IILjava/util/Map;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "component1", "()Z", "component2", "()Lcom/arflix/tv/data/model/MediaItem;", "component3", "()Ljava/lang/String;", "component4", "()Ljava/lang/Integer;", "component5", "component6", "component7", "()Ljava/util/List;", "component8", "()I", "component9", "component10", "component11", "component12", "()Ljava/util/Map;", "component13", "component14", "component15", "component16", "()Lcom/arflix/tv/data/model/PersonDetails;", "component17", "component18", "component19", "component20", "component21", "()J", "component22", "component23", "()Ljava/util/Set;", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "()Lcom/arflix/tv/ui/screens/details/ToastType;", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "()Ljava/lang/Long;", "component47", "component48", "component49", "component50", "component51", "component52", "copy", "(ZLcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;IILjava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;ZLcom/arflix/tv/data/model/PersonDetails;ZLjava/util/List;Ljava/util/List;ZJZLjava/util/Set;IIZLjava/util/List;ZZLjava/lang/String;Lcom/arflix/tv/ui/screens/details/ToastType;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;IILjava/util/Map;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lcom/arflix/tv/ui/screens/details/DetailsUiState;", "toString", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "Lcom/arflix/tv/data/model/MediaItem;", "getItem", "Ljava/lang/String;", "getImdbId", "Ljava/lang/Integer;", "getTvdbId", "getLogoUrl", "getTrailerKey", "Ljava/util/List;", "getEpisodes", "I", "getTotalSeasons", "getCurrentSeason", "getCast", "getSimilar", "Ljava/util/Map;", "getSimilarLogoUrls", "getReviews", "getError", "getShowPersonModal", "Lcom/arflix/tv/data/model/PersonDetails;", "getSelectedPerson", "getStreams", "getSubtitles", "J", "getStreamSearchStartTime", "getPluginScrapersLoading", "Ljava/util/Set;", "getLoadingPluginNames", "getCompletedAddons", "getTotalAddons", "getHasStreamingAddons", "getAddonOrderedIds", "getShowEpisodeRatings", "getToastMessage", "Lcom/arflix/tv/ui/screens/details/ToastType;", "getToastType", "getGenres", "getLanguage", "getBudget", "getShowStatus", "getStreamingServices", "getProviderRegion", "getInitialEpisodeIndex", "getInitialSeasonIndex", "getSeasonProgress", "getPlaySeason", "getPlayEpisode", "getPlayTmdbSeason", "getPlayTmdbEpisode", "getPlayLabel", "Ljava/lang/Long;", "getPlayPositionMs", "getAutoPlaySingleSource", "getAutoPlayMinQuality", "getCollectionId", "getCollectionName", "getCollectionItems", "getCollectionPosterPath", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DetailsUiState {
    public static final int $stable = 0;
    private final List<String> addonOrderedIds;
    private final String autoPlayMinQuality;
    private final boolean autoPlaySingleSource;
    private final String budget;
    private final List<CastMember> cast;
    private final Integer collectionId;
    private final List<MediaItem> collectionItems;
    private final String collectionName;
    private final String collectionPosterPath;
    private final int completedAddons;
    private final int currentSeason;
    private final List<Episode> episodes;
    private final String error;
    private final List<String> genres;
    private final boolean hasStreamingAddons;
    private final String imdbId;
    private final int initialEpisodeIndex;
    private final int initialSeasonIndex;
    private final boolean isInWatchlist;
    private final boolean isLoading;
    private final boolean isLoadingPerson;
    private final boolean isLoadingStreams;
    private final MediaItem item;
    private final String language;
    private final Set<String> loadingPluginNames;
    private final String logoUrl;
    private final Integer playEpisode;
    private final String playLabel;
    private final Long playPositionMs;
    private final Integer playSeason;
    private final Integer playTmdbEpisode;
    private final Integer playTmdbSeason;
    private final boolean pluginScrapersLoading;
    private final String providerRegion;
    private final List<Review> reviews;
    private final Map<Integer, x6.x> seasonProgress;
    private final PersonDetails selectedPerson;
    private final boolean showEpisodeRatings;
    private final boolean showPersonModal;
    private final String showStatus;
    private final List<MediaItem> similar;
    private final Map<String, String> similarLogoUrls;
    private final long streamSearchStartTime;
    private final List<StreamingServiceUi> streamingServices;
    private final List<StreamSource> streams;
    private final List<Subtitle> subtitles;
    private final String toastMessage;
    private final ToastType toastType;
    private final int totalAddons;
    private final int totalSeasons;
    private final String trailerKey;
    private final Integer tvdbId;

    public DetailsUiState() {
        this(false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -1, 1048575, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DetailsUiState copy$default(DetailsUiState detailsUiState, boolean z, MediaItem mediaItem, String str, Integer num, String str2, String str3, List list, int i10, int i11, List list2, List list3, Map map, List list4, String str4, boolean z5, PersonDetails personDetails, boolean z10, List list5, List list6, boolean z11, long j10, boolean z12, Set set, int i12, int i13, boolean z13, List list7, boolean z14, boolean z15, String str5, ToastType toastType, List list8, String str6, String str7, String str8, List list9, String str9, int i14, int i15, Map map2, Integer num2, Integer num3, Integer num4, Integer num5, String str10, Long l10, boolean z16, String str11, Integer num6, String str12, List list10, String str13, int i16, int i17, Object obj) {
        boolean z17 = (i16 & 1) != 0 ? detailsUiState.isLoading : z;
        return detailsUiState.copy(z17, (i16 & 2) != 0 ? detailsUiState.item : mediaItem, (i16 & 4) != 0 ? detailsUiState.imdbId : str, (i16 & 8) != 0 ? detailsUiState.tvdbId : num, (i16 & 16) != 0 ? detailsUiState.logoUrl : str2, (i16 & 32) != 0 ? detailsUiState.trailerKey : str3, (i16 & 64) != 0 ? detailsUiState.episodes : list, (i16 & 128) != 0 ? detailsUiState.totalSeasons : i10, (i16 & 256) != 0 ? detailsUiState.currentSeason : i11, (i16 & 512) != 0 ? detailsUiState.cast : list2, (i16 & 1024) != 0 ? detailsUiState.similar : list3, (i16 & 2048) != 0 ? detailsUiState.similarLogoUrls : map, (i16 & 4096) != 0 ? detailsUiState.reviews : list4, (i16 & 8192) != 0 ? detailsUiState.error : str4, (i16 & 16384) != 0 ? detailsUiState.showPersonModal : z5, (i16 & 32768) != 0 ? detailsUiState.selectedPerson : personDetails, (i16 & 65536) != 0 ? detailsUiState.isLoadingPerson : z10, (i16 & 131072) != 0 ? detailsUiState.streams : list5, (i16 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? detailsUiState.subtitles : list6, (i16 & 524288) != 0 ? detailsUiState.isLoadingStreams : z11, (i16 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? detailsUiState.streamSearchStartTime : j10, (i16 & 2097152) != 0 ? detailsUiState.pluginScrapersLoading : z12, (i16 & 4194304) != 0 ? detailsUiState.loadingPluginNames : set, (i16 & 8388608) != 0 ? detailsUiState.completedAddons : i12, (i16 & 16777216) != 0 ? detailsUiState.totalAddons : i13, (i16 & 33554432) != 0 ? detailsUiState.hasStreamingAddons : z13, (i16 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? detailsUiState.addonOrderedIds : list7, (i16 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? detailsUiState.isInWatchlist : z14, (i16 & 268435456) != 0 ? detailsUiState.showEpisodeRatings : z15, (i16 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? detailsUiState.toastMessage : str5, (i16 & 1073741824) != 0 ? detailsUiState.toastType : toastType, (i16 & Integer.MIN_VALUE) != 0 ? detailsUiState.genres : list8, (i17 & 1) != 0 ? detailsUiState.language : str6, (i17 & 2) != 0 ? detailsUiState.budget : str7, (i17 & 4) != 0 ? detailsUiState.showStatus : str8, (i17 & 8) != 0 ? detailsUiState.streamingServices : list9, (i17 & 16) != 0 ? detailsUiState.providerRegion : str9, (i17 & 32) != 0 ? detailsUiState.initialEpisodeIndex : i14, (i17 & 64) != 0 ? detailsUiState.initialSeasonIndex : i15, (i17 & 128) != 0 ? detailsUiState.seasonProgress : map2, (i17 & 256) != 0 ? detailsUiState.playSeason : num2, (i17 & 512) != 0 ? detailsUiState.playEpisode : num3, (i17 & 1024) != 0 ? detailsUiState.playTmdbSeason : num4, (i17 & 2048) != 0 ? detailsUiState.playTmdbEpisode : num5, (i17 & 4096) != 0 ? detailsUiState.playLabel : str10, (i17 & 8192) != 0 ? detailsUiState.playPositionMs : l10, (i17 & 16384) != 0 ? detailsUiState.autoPlaySingleSource : z16, (i17 & 32768) != 0 ? detailsUiState.autoPlayMinQuality : str11, (i17 & 65536) != 0 ? detailsUiState.collectionId : num6, (i17 & 131072) != 0 ? detailsUiState.collectionName : str12, (i17 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? detailsUiState.collectionItems : list10, (i17 & 524288) != 0 ? detailsUiState.collectionPosterPath : str13);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final List<CastMember> component10() {
        return this.cast;
    }

    public final List<MediaItem> component11() {
        return this.similar;
    }

    public final Map<String, String> component12() {
        return this.similarLogoUrls;
    }

    public final List<Review> component13() {
        return this.reviews;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getShowPersonModal() {
        return this.showPersonModal;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final PersonDetails getSelectedPerson() {
        return this.selectedPerson;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getIsLoadingPerson() {
        return this.isLoadingPerson;
    }

    public final List<StreamSource> component18() {
        return this.streams;
    }

    public final List<Subtitle> component19() {
        return this.subtitles;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MediaItem getItem() {
        return this.item;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final boolean getIsLoadingStreams() {
        return this.isLoadingStreams;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final long getStreamSearchStartTime() {
        return this.streamSearchStartTime;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final boolean getPluginScrapersLoading() {
        return this.pluginScrapersLoading;
    }

    public final Set<String> component23() {
        return this.loadingPluginNames;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final int getCompletedAddons() {
        return this.completedAddons;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final int getTotalAddons() {
        return this.totalAddons;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final boolean getHasStreamingAddons() {
        return this.hasStreamingAddons;
    }

    public final List<String> component27() {
        return this.addonOrderedIds;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final boolean getIsInWatchlist() {
        return this.isInWatchlist;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final boolean getShowEpisodeRatings() {
        return this.showEpisodeRatings;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getImdbId() {
        return this.imdbId;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final String getToastMessage() {
        return this.toastMessage;
    }

    /* JADX INFO: renamed from: component31, reason: from getter */
    public final ToastType getToastType() {
        return this.toastType;
    }

    public final List<String> component32() {
        return this.genres;
    }

    /* JADX INFO: renamed from: component33, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component34, reason: from getter */
    public final String getBudget() {
        return this.budget;
    }

    /* JADX INFO: renamed from: component35, reason: from getter */
    public final String getShowStatus() {
        return this.showStatus;
    }

    public final List<StreamingServiceUi> component36() {
        return this.streamingServices;
    }

    /* JADX INFO: renamed from: component37, reason: from getter */
    public final String getProviderRegion() {
        return this.providerRegion;
    }

    /* JADX INFO: renamed from: component38, reason: from getter */
    public final int getInitialEpisodeIndex() {
        return this.initialEpisodeIndex;
    }

    /* JADX INFO: renamed from: component39, reason: from getter */
    public final int getInitialSeasonIndex() {
        return this.initialSeasonIndex;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getTvdbId() {
        return this.tvdbId;
    }

    public final Map<Integer, x6.x> component40() {
        return this.seasonProgress;
    }

    /* JADX INFO: renamed from: component41, reason: from getter */
    public final Integer getPlaySeason() {
        return this.playSeason;
    }

    /* JADX INFO: renamed from: component42, reason: from getter */
    public final Integer getPlayEpisode() {
        return this.playEpisode;
    }

    /* JADX INFO: renamed from: component43, reason: from getter */
    public final Integer getPlayTmdbSeason() {
        return this.playTmdbSeason;
    }

    /* JADX INFO: renamed from: component44, reason: from getter */
    public final Integer getPlayTmdbEpisode() {
        return this.playTmdbEpisode;
    }

    /* JADX INFO: renamed from: component45, reason: from getter */
    public final String getPlayLabel() {
        return this.playLabel;
    }

    /* JADX INFO: renamed from: component46, reason: from getter */
    public final Long getPlayPositionMs() {
        return this.playPositionMs;
    }

    /* JADX INFO: renamed from: component47, reason: from getter */
    public final boolean getAutoPlaySingleSource() {
        return this.autoPlaySingleSource;
    }

    /* JADX INFO: renamed from: component48, reason: from getter */
    public final String getAutoPlayMinQuality() {
        return this.autoPlayMinQuality;
    }

    /* JADX INFO: renamed from: component49, reason: from getter */
    public final Integer getCollectionId() {
        return this.collectionId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component50, reason: from getter */
    public final String getCollectionName() {
        return this.collectionName;
    }

    public final List<MediaItem> component51() {
        return this.collectionItems;
    }

    /* JADX INFO: renamed from: component52, reason: from getter */
    public final String getCollectionPosterPath() {
        return this.collectionPosterPath;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTrailerKey() {
        return this.trailerKey;
    }

    public final List<Episode> component7() {
        return this.episodes;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getTotalSeasons() {
        return this.totalSeasons;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getCurrentSeason() {
        return this.currentSeason;
    }

    public final DetailsUiState copy(boolean isLoading, MediaItem item, String imdbId, Integer tvdbId, String logoUrl, String trailerKey, List<Episode> episodes, int totalSeasons, int currentSeason, List<CastMember> cast, List<MediaItem> similar, Map<String, String> similarLogoUrls, List<Review> reviews, String error, boolean showPersonModal, PersonDetails selectedPerson, boolean isLoadingPerson, List<StreamSource> streams, List<Subtitle> subtitles, boolean isLoadingStreams, long streamSearchStartTime, boolean pluginScrapersLoading, Set<String> loadingPluginNames, int completedAddons, int totalAddons, boolean hasStreamingAddons, List<String> addonOrderedIds, boolean isInWatchlist, boolean showEpisodeRatings, String toastMessage, ToastType toastType, List<String> genres, String language, String budget, String showStatus, List<StreamingServiceUi> streamingServices, String providerRegion, int initialEpisodeIndex, int initialSeasonIndex, Map<Integer, x6.x> seasonProgress, Integer playSeason, Integer playEpisode, Integer playTmdbSeason, Integer playTmdbEpisode, String playLabel, Long playPositionMs, boolean autoPlaySingleSource, String autoPlayMinQuality, Integer collectionId, String collectionName, List<MediaItem> collectionItems, String collectionPosterPath) {
        return new DetailsUiState(isLoading, item, imdbId, tvdbId, logoUrl, trailerKey, episodes, totalSeasons, currentSeason, cast, similar, similarLogoUrls, reviews, error, showPersonModal, selectedPerson, isLoadingPerson, streams, subtitles, isLoadingStreams, streamSearchStartTime, pluginScrapersLoading, loadingPluginNames, completedAddons, totalAddons, hasStreamingAddons, addonOrderedIds, isInWatchlist, showEpisodeRatings, toastMessage, toastType, genres, language, budget, showStatus, streamingServices, providerRegion, initialEpisodeIndex, initialSeasonIndex, seasonProgress, playSeason, playEpisode, playTmdbSeason, playTmdbEpisode, playLabel, playPositionMs, autoPlaySingleSource, autoPlayMinQuality, collectionId, collectionName, collectionItems, collectionPosterPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DetailsUiState)) {
            return false;
        }
        DetailsUiState detailsUiState = (DetailsUiState) other;
        return this.isLoading == detailsUiState.isLoading && kotlin.jvm.internal.p.a(this.item, detailsUiState.item) && kotlin.jvm.internal.p.a(this.imdbId, detailsUiState.imdbId) && kotlin.jvm.internal.p.a(this.tvdbId, detailsUiState.tvdbId) && kotlin.jvm.internal.p.a(this.logoUrl, detailsUiState.logoUrl) && kotlin.jvm.internal.p.a(this.trailerKey, detailsUiState.trailerKey) && kotlin.jvm.internal.p.a(this.episodes, detailsUiState.episodes) && this.totalSeasons == detailsUiState.totalSeasons && this.currentSeason == detailsUiState.currentSeason && kotlin.jvm.internal.p.a(this.cast, detailsUiState.cast) && kotlin.jvm.internal.p.a(this.similar, detailsUiState.similar) && kotlin.jvm.internal.p.a(this.similarLogoUrls, detailsUiState.similarLogoUrls) && kotlin.jvm.internal.p.a(this.reviews, detailsUiState.reviews) && kotlin.jvm.internal.p.a(this.error, detailsUiState.error) && this.showPersonModal == detailsUiState.showPersonModal && kotlin.jvm.internal.p.a(this.selectedPerson, detailsUiState.selectedPerson) && this.isLoadingPerson == detailsUiState.isLoadingPerson && kotlin.jvm.internal.p.a(this.streams, detailsUiState.streams) && kotlin.jvm.internal.p.a(this.subtitles, detailsUiState.subtitles) && this.isLoadingStreams == detailsUiState.isLoadingStreams && this.streamSearchStartTime == detailsUiState.streamSearchStartTime && this.pluginScrapersLoading == detailsUiState.pluginScrapersLoading && kotlin.jvm.internal.p.a(this.loadingPluginNames, detailsUiState.loadingPluginNames) && this.completedAddons == detailsUiState.completedAddons && this.totalAddons == detailsUiState.totalAddons && this.hasStreamingAddons == detailsUiState.hasStreamingAddons && kotlin.jvm.internal.p.a(this.addonOrderedIds, detailsUiState.addonOrderedIds) && this.isInWatchlist == detailsUiState.isInWatchlist && this.showEpisodeRatings == detailsUiState.showEpisodeRatings && kotlin.jvm.internal.p.a(this.toastMessage, detailsUiState.toastMessage) && this.toastType == detailsUiState.toastType && kotlin.jvm.internal.p.a(this.genres, detailsUiState.genres) && kotlin.jvm.internal.p.a(this.language, detailsUiState.language) && kotlin.jvm.internal.p.a(this.budget, detailsUiState.budget) && kotlin.jvm.internal.p.a(this.showStatus, detailsUiState.showStatus) && kotlin.jvm.internal.p.a(this.streamingServices, detailsUiState.streamingServices) && kotlin.jvm.internal.p.a(this.providerRegion, detailsUiState.providerRegion) && this.initialEpisodeIndex == detailsUiState.initialEpisodeIndex && this.initialSeasonIndex == detailsUiState.initialSeasonIndex && kotlin.jvm.internal.p.a(this.seasonProgress, detailsUiState.seasonProgress) && kotlin.jvm.internal.p.a(this.playSeason, detailsUiState.playSeason) && kotlin.jvm.internal.p.a(this.playEpisode, detailsUiState.playEpisode) && kotlin.jvm.internal.p.a(this.playTmdbSeason, detailsUiState.playTmdbSeason) && kotlin.jvm.internal.p.a(this.playTmdbEpisode, detailsUiState.playTmdbEpisode) && kotlin.jvm.internal.p.a(this.playLabel, detailsUiState.playLabel) && kotlin.jvm.internal.p.a(this.playPositionMs, detailsUiState.playPositionMs) && this.autoPlaySingleSource == detailsUiState.autoPlaySingleSource && kotlin.jvm.internal.p.a(this.autoPlayMinQuality, detailsUiState.autoPlayMinQuality) && kotlin.jvm.internal.p.a(this.collectionId, detailsUiState.collectionId) && kotlin.jvm.internal.p.a(this.collectionName, detailsUiState.collectionName) && kotlin.jvm.internal.p.a(this.collectionItems, detailsUiState.collectionItems) && kotlin.jvm.internal.p.a(this.collectionPosterPath, detailsUiState.collectionPosterPath);
    }

    public final List<String> getAddonOrderedIds() {
        return this.addonOrderedIds;
    }

    public final String getAutoPlayMinQuality() {
        return this.autoPlayMinQuality;
    }

    public final boolean getAutoPlaySingleSource() {
        return this.autoPlaySingleSource;
    }

    public final String getBudget() {
        return this.budget;
    }

    public final List<CastMember> getCast() {
        return this.cast;
    }

    public final Integer getCollectionId() {
        return this.collectionId;
    }

    public final List<MediaItem> getCollectionItems() {
        return this.collectionItems;
    }

    public final String getCollectionName() {
        return this.collectionName;
    }

    public final String getCollectionPosterPath() {
        return this.collectionPosterPath;
    }

    public final int getCompletedAddons() {
        return this.completedAddons;
    }

    public final int getCurrentSeason() {
        return this.currentSeason;
    }

    public final List<Episode> getEpisodes() {
        return this.episodes;
    }

    public final String getError() {
        return this.error;
    }

    public final List<String> getGenres() {
        return this.genres;
    }

    public final boolean getHasStreamingAddons() {
        return this.hasStreamingAddons;
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final int getInitialEpisodeIndex() {
        return this.initialEpisodeIndex;
    }

    public final int getInitialSeasonIndex() {
        return this.initialSeasonIndex;
    }

    public final MediaItem getItem() {
        return this.item;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final Set<String> getLoadingPluginNames() {
        return this.loadingPluginNames;
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final Integer getPlayEpisode() {
        return this.playEpisode;
    }

    public final String getPlayLabel() {
        return this.playLabel;
    }

    public final Long getPlayPositionMs() {
        return this.playPositionMs;
    }

    public final Integer getPlaySeason() {
        return this.playSeason;
    }

    public final Integer getPlayTmdbEpisode() {
        return this.playTmdbEpisode;
    }

    public final Integer getPlayTmdbSeason() {
        return this.playTmdbSeason;
    }

    public final boolean getPluginScrapersLoading() {
        return this.pluginScrapersLoading;
    }

    public final String getProviderRegion() {
        return this.providerRegion;
    }

    public final List<Review> getReviews() {
        return this.reviews;
    }

    public final Map<Integer, x6.x> getSeasonProgress() {
        return this.seasonProgress;
    }

    public final PersonDetails getSelectedPerson() {
        return this.selectedPerson;
    }

    public final boolean getShowEpisodeRatings() {
        return this.showEpisodeRatings;
    }

    public final boolean getShowPersonModal() {
        return this.showPersonModal;
    }

    public final String getShowStatus() {
        return this.showStatus;
    }

    public final List<MediaItem> getSimilar() {
        return this.similar;
    }

    public final Map<String, String> getSimilarLogoUrls() {
        return this.similarLogoUrls;
    }

    public final long getStreamSearchStartTime() {
        return this.streamSearchStartTime;
    }

    public final List<StreamingServiceUi> getStreamingServices() {
        return this.streamingServices;
    }

    public final List<StreamSource> getStreams() {
        return this.streams;
    }

    public final List<Subtitle> getSubtitles() {
        return this.subtitles;
    }

    public final String getToastMessage() {
        return this.toastMessage;
    }

    public final ToastType getToastType() {
        return this.toastType;
    }

    public final int getTotalAddons() {
        return this.totalAddons;
    }

    public final int getTotalSeasons() {
        return this.totalSeasons;
    }

    public final String getTrailerKey() {
        return this.trailerKey;
    }

    public final Integer getTvdbId() {
        return this.tvdbId;
    }

    public int hashCode() {
        int i10 = (this.isLoading ? 1231 : 1237) * 31;
        MediaItem mediaItem = this.item;
        int iHashCode = (i10 + (mediaItem == null ? 0 : mediaItem.hashCode())) * 31;
        String str = this.imdbId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.tvdbId;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.logoUrl;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.trailerKey;
        int iE = androidx.compose.material3.d.e(this.reviews, a2.g(this.similarLogoUrls, androidx.compose.material3.d.e(this.similar, androidx.compose.material3.d.e(this.cast, (((androidx.compose.material3.d.e(this.episodes, (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31, 31) + this.totalSeasons) * 31) + this.currentSeason) * 31, 31), 31), 31), 31);
        String str4 = this.error;
        int iHashCode5 = (((iE + (str4 == null ? 0 : str4.hashCode())) * 31) + (this.showPersonModal ? 1231 : 1237)) * 31;
        PersonDetails personDetails = this.selectedPerson;
        int iE2 = androidx.compose.material3.d.e(this.subtitles, androidx.compose.material3.d.e(this.streams, (((iHashCode5 + (personDetails == null ? 0 : personDetails.hashCode())) * 31) + (this.isLoadingPerson ? 1231 : 1237)) * 31, 31), 31);
        int i11 = this.isLoadingStreams ? 1231 : 1237;
        long j10 = this.streamSearchStartTime;
        int iE3 = (((androidx.compose.material3.d.e(this.addonOrderedIds, (((((((this.loadingPluginNames.hashCode() + ((((((iE2 + i11) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31) + (this.pluginScrapersLoading ? 1231 : 1237)) * 31)) * 31) + this.completedAddons) * 31) + this.totalAddons) * 31) + (this.hasStreamingAddons ? 1231 : 1237)) * 31, 31) + (this.isInWatchlist ? 1231 : 1237)) * 31) + (this.showEpisodeRatings ? 1231 : 1237)) * 31;
        String str5 = this.toastMessage;
        int iE4 = androidx.compose.material3.d.e(this.genres, (this.toastType.hashCode() + ((iE3 + (str5 == null ? 0 : str5.hashCode())) * 31)) * 31, 31);
        String str6 = this.language;
        int iHashCode6 = (iE4 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.budget;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.showStatus;
        int iE5 = androidx.compose.material3.d.e(this.streamingServices, (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31, 31);
        String str9 = this.providerRegion;
        int iG = a2.g(this.seasonProgress, (((((iE5 + (str9 == null ? 0 : str9.hashCode())) * 31) + this.initialEpisodeIndex) * 31) + this.initialSeasonIndex) * 31, 31);
        Integer num2 = this.playSeason;
        int iHashCode8 = (iG + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.playEpisode;
        int iHashCode9 = (iHashCode8 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.playTmdbSeason;
        int iHashCode10 = (iHashCode9 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.playTmdbEpisode;
        int iHashCode11 = (iHashCode10 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str10 = this.playLabel;
        int iHashCode12 = (iHashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31;
        Long l10 = this.playPositionMs;
        int iC = androidx.compose.foundation.c.c((((iHashCode12 + (l10 == null ? 0 : l10.hashCode())) * 31) + (this.autoPlaySingleSource ? 1231 : 1237)) * 31, 31, this.autoPlayMinQuality);
        Integer num6 = this.collectionId;
        int iHashCode13 = (iC + (num6 == null ? 0 : num6.hashCode())) * 31;
        String str11 = this.collectionName;
        int iE6 = androidx.compose.material3.d.e(this.collectionItems, (iHashCode13 + (str11 == null ? 0 : str11.hashCode())) * 31, 31);
        String str12 = this.collectionPosterPath;
        return iE6 + (str12 != null ? str12.hashCode() : 0);
    }

    public final boolean isInWatchlist() {
        return this.isInWatchlist;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isLoadingPerson() {
        return this.isLoadingPerson;
    }

    public final boolean isLoadingStreams() {
        return this.isLoadingStreams;
    }

    public String toString() {
        boolean z = this.isLoading;
        MediaItem mediaItem = this.item;
        String str = this.imdbId;
        Integer num = this.tvdbId;
        String str2 = this.logoUrl;
        String str3 = this.trailerKey;
        List<Episode> list = this.episodes;
        int i10 = this.totalSeasons;
        int i11 = this.currentSeason;
        List<CastMember> list2 = this.cast;
        List<MediaItem> list3 = this.similar;
        Map<String, String> map = this.similarLogoUrls;
        List<Review> list4 = this.reviews;
        String str4 = this.error;
        boolean z5 = this.showPersonModal;
        PersonDetails personDetails = this.selectedPerson;
        boolean z10 = this.isLoadingPerson;
        List<StreamSource> list5 = this.streams;
        List<Subtitle> list6 = this.subtitles;
        boolean z11 = this.isLoadingStreams;
        long j10 = this.streamSearchStartTime;
        boolean z12 = this.pluginScrapersLoading;
        Set<String> set = this.loadingPluginNames;
        int i12 = this.completedAddons;
        int i13 = this.totalAddons;
        boolean z13 = this.hasStreamingAddons;
        List<String> list7 = this.addonOrderedIds;
        boolean z14 = this.isInWatchlist;
        boolean z15 = this.showEpisodeRatings;
        String str5 = this.toastMessage;
        ToastType toastType = this.toastType;
        List<String> list8 = this.genres;
        String str6 = this.language;
        String str7 = this.budget;
        String str8 = this.showStatus;
        List<StreamingServiceUi> list9 = this.streamingServices;
        String str9 = this.providerRegion;
        int i14 = this.initialEpisodeIndex;
        int i15 = this.initialSeasonIndex;
        Map<Integer, x6.x> map2 = this.seasonProgress;
        Integer num2 = this.playSeason;
        Integer num3 = this.playEpisode;
        Integer num4 = this.playTmdbSeason;
        Integer num5 = this.playTmdbEpisode;
        String str10 = this.playLabel;
        Long l10 = this.playPositionMs;
        boolean z16 = this.autoPlaySingleSource;
        String str11 = this.autoPlayMinQuality;
        Integer num6 = this.collectionId;
        String str12 = this.collectionName;
        List<MediaItem> list10 = this.collectionItems;
        String str13 = this.collectionPosterPath;
        StringBuilder sb2 = new StringBuilder("DetailsUiState(isLoading=");
        sb2.append(z);
        sb2.append(", item=");
        sb2.append(mediaItem);
        sb2.append(", imdbId=");
        sb2.append(str);
        sb2.append(", tvdbId=");
        sb2.append(num);
        sb2.append(", logoUrl=");
        y.a.i(sb2, str2, ", trailerKey=", str3, ", episodes=");
        sb2.append(list);
        sb2.append(", totalSeasons=");
        sb2.append(i10);
        sb2.append(", currentSeason=");
        sb2.append(i11);
        sb2.append(", cast=");
        sb2.append(list2);
        sb2.append(", similar=");
        sb2.append(list3);
        sb2.append(", similarLogoUrls=");
        sb2.append(map);
        sb2.append(", reviews=");
        sb2.append(list4);
        sb2.append(", error=");
        sb2.append(str4);
        sb2.append(", showPersonModal=");
        sb2.append(z5);
        sb2.append(", selectedPerson=");
        sb2.append(personDetails);
        sb2.append(", isLoadingPerson=");
        sb2.append(z10);
        sb2.append(", streams=");
        sb2.append(list5);
        sb2.append(", subtitles=");
        sb2.append(list6);
        sb2.append(", isLoadingStreams=");
        sb2.append(z11);
        sb2.append(", streamSearchStartTime=");
        sb2.append(j10);
        sb2.append(", pluginScrapersLoading=");
        sb2.append(z12);
        sb2.append(", loadingPluginNames=");
        sb2.append(set);
        sb2.append(", completedAddons=");
        sb2.append(i12);
        sb2.append(", totalAddons=");
        sb2.append(i13);
        sb2.append(", hasStreamingAddons=");
        sb2.append(z13);
        sb2.append(", addonOrderedIds=");
        sb2.append(list7);
        sb2.append(", isInWatchlist=");
        sb2.append(z14);
        sb2.append(", showEpisodeRatings=");
        sb2.append(z15);
        sb2.append(", toastMessage=");
        sb2.append(str5);
        sb2.append(", toastType=");
        sb2.append(toastType);
        sb2.append(", genres=");
        sb2.append(list8);
        y.a.i(sb2, ", language=", str6, ", budget=", str7);
        sb2.append(", showStatus=");
        sb2.append(str8);
        sb2.append(", streamingServices=");
        sb2.append(list9);
        sb2.append(", providerRegion=");
        sb2.append(str9);
        sb2.append(", initialEpisodeIndex=");
        sb2.append(i14);
        sb2.append(", initialSeasonIndex=");
        sb2.append(i15);
        sb2.append(", seasonProgress=");
        sb2.append(map2);
        sb2.append(", playSeason=");
        sb2.append(num2);
        sb2.append(", playEpisode=");
        sb2.append(num3);
        sb2.append(", playTmdbSeason=");
        sb2.append(num4);
        sb2.append(", playTmdbEpisode=");
        sb2.append(num5);
        sb2.append(", playLabel=");
        sb2.append(str10);
        sb2.append(", playPositionMs=");
        sb2.append(l10);
        sb2.append(", autoPlaySingleSource=");
        sb2.append(z16);
        sb2.append(", autoPlayMinQuality=");
        sb2.append(str11);
        sb2.append(", collectionId=");
        sb2.append(num6);
        sb2.append(", collectionName=");
        sb2.append(str12);
        sb2.append(", collectionItems=");
        sb2.append(list10);
        sb2.append(", collectionPosterPath=");
        sb2.append(str13);
        sb2.append(")");
        return sb2.toString();
    }

    public DetailsUiState(boolean z, MediaItem mediaItem, String str, Integer num, String str2, String str3, List<Episode> list, int i10, int i11, List<CastMember> list2, List<MediaItem> list3, Map<String, String> map, List<Review> list4, String str4, boolean z5, PersonDetails personDetails, boolean z10, List<StreamSource> list5, List<Subtitle> list6, boolean z11, long j10, boolean z12, Set<String> set, int i12, int i13, boolean z13, List<String> list7, boolean z14, boolean z15, String str5, ToastType toastType, List<String> list8, String str6, String str7, String str8, List<StreamingServiceUi> list9, String str9, int i14, int i15, Map<Integer, x6.x> map2, Integer num2, Integer num3, Integer num4, Integer num5, String str10, Long l10, boolean z16, String str11, Integer num6, String str12, List<MediaItem> list10, String str13) {
        this.isLoading = z;
        this.item = mediaItem;
        this.imdbId = str;
        this.tvdbId = num;
        this.logoUrl = str2;
        this.trailerKey = str3;
        this.episodes = list;
        this.totalSeasons = i10;
        this.currentSeason = i11;
        this.cast = list2;
        this.similar = list3;
        this.similarLogoUrls = map;
        this.reviews = list4;
        this.error = str4;
        this.showPersonModal = z5;
        this.selectedPerson = personDetails;
        this.isLoadingPerson = z10;
        this.streams = list5;
        this.subtitles = list6;
        this.isLoadingStreams = z11;
        this.streamSearchStartTime = j10;
        this.pluginScrapersLoading = z12;
        this.loadingPluginNames = set;
        this.completedAddons = i12;
        this.totalAddons = i13;
        this.hasStreamingAddons = z13;
        this.addonOrderedIds = list7;
        this.isInWatchlist = z14;
        this.showEpisodeRatings = z15;
        this.toastMessage = str5;
        this.toastType = toastType;
        this.genres = list8;
        this.language = str6;
        this.budget = str7;
        this.showStatus = str8;
        this.streamingServices = list9;
        this.providerRegion = str9;
        this.initialEpisodeIndex = i14;
        this.initialSeasonIndex = i15;
        this.seasonProgress = map2;
        this.playSeason = num2;
        this.playEpisode = num3;
        this.playTmdbSeason = num4;
        this.playTmdbEpisode = num5;
        this.playLabel = str10;
        this.playPositionMs = l10;
        this.autoPlaySingleSource = z16;
        this.autoPlayMinQuality = str11;
        this.collectionId = num6;
        this.collectionName = str12;
        this.collectionItems = list10;
        this.collectionPosterPath = str13;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DetailsUiState(boolean z, MediaItem mediaItem, String str, Integer num, String str2, String str3, List list, int i10, int i11, List list2, List list3, Map map, List list4, String str4, boolean z5, PersonDetails personDetails, boolean z10, List list5, List list6, boolean z11, long j10, boolean z12, Set set, int i12, int i13, boolean z13, List list7, boolean z14, boolean z15, String str5, ToastType toastType, List list8, String str6, String str7, String str8, List list9, String str9, int i14, int i15, Map map2, Integer num2, Integer num3, Integer num4, Integer num5, String str10, Long l10, boolean z16, String str11, Integer num6, String str12, List list10, String str13, int i16, int i17, kotlin.jvm.internal.h hVar) {
        boolean z17 = (i16 & 1) != 0 ? true : z;
        MediaItem mediaItem2 = (i16 & 2) != 0 ? null : mediaItem;
        String str14 = (i16 & 4) != 0 ? null : str;
        Integer num7 = (i16 & 8) != 0 ? null : num;
        String str15 = (i16 & 16) != 0 ? null : str2;
        String str16 = (i16 & 32) != 0 ? null : str3;
        int i18 = i16 & 64;
        List list11 = kotlin.collections.z.f19728i;
        List list12 = i18 != 0 ? list11 : list;
        int i19 = (i16 & 128) != 0 ? 1 : i10;
        int i20 = (i16 & 256) != 0 ? 1 : i11;
        List list13 = (i16 & 512) != 0 ? list11 : list2;
        List list14 = (i16 & 1024) != 0 ? list11 : list3;
        int i21 = i16 & 2048;
        Map map3 = kotlin.collections.a0.f19683i;
        this(z17, mediaItem2, str14, num7, str15, str16, list12, i19, i20, list13, list14, i21 != 0 ? map3 : map, (i16 & 4096) != 0 ? list11 : list4, (i16 & 8192) != 0 ? null : str4, (i16 & 16384) != 0 ? false : z5, (i16 & 32768) != 0 ? null : personDetails, (i16 & 65536) != 0 ? false : z10, (i16 & 131072) != 0 ? list11 : list5, (i16 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? list11 : list6, (i16 & 524288) != 0 ? false : z11, (i16 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? 0L : j10, (i16 & 2097152) != 0 ? false : z12, (i16 & 4194304) != 0 ? kotlin.collections.b0.f19686i : set, (i16 & 8388608) != 0 ? 0 : i12, (i16 & 16777216) != 0 ? 0 : i13, (i16 & 33554432) != 0 ? true : z13, (i16 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? list11 : list7, (i16 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? false : z14, (i16 & 268435456) != 0 ? true : z15, (i16 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? null : str5, (i16 & 1073741824) != 0 ? ToastType.INFO : toastType, (i16 & Integer.MIN_VALUE) != 0 ? list11 : list8, (i17 & 1) != 0 ? null : str6, (i17 & 2) != 0 ? null : str7, (i17 & 4) != 0 ? null : str8, (i17 & 8) != 0 ? list11 : list9, (i17 & 16) != 0 ? null : str9, (i17 & 32) != 0 ? 0 : i14, (i17 & 64) == 0 ? i15 : 0, (i17 & 128) == 0 ? map2 : map3, (i17 & 256) != 0 ? null : num2, (i17 & 512) != 0 ? null : num3, (i17 & 1024) != 0 ? null : num4, (i17 & 2048) != 0 ? null : num5, (i17 & 4096) != 0 ? null : str10, (i17 & 8192) != 0 ? null : l10, (i17 & 16384) != 0 ? true : z16, (i17 & 32768) != 0 ? "Any" : str11, (i17 & 65536) != 0 ? null : num6, (i17 & 131072) != 0 ? null : str12, (i17 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0 ? list10 : list11, (i17 & 524288) != 0 ? null : str13);
    }
}
