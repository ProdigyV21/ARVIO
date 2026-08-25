package com.arflix.tv.ui.screens.player;

import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import com.arflix.tv.data.repository.SkipInterval;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0003\b\u0081\u0001\b\u0087\b\u0018\u00002\u00020\u0001BË\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\f\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0007\u0012\b\b\u0002\u0010 \u001a\u00020\u0007\u0012\b\b\u0002\u0010!\u001a\u00020\u0007\u0012\b\b\u0002\u0010\"\u001a\u00020\u0007\u0012\b\b\u0002\u0010#\u001a\u00020\u0007\u0012\b\b\u0002\u0010$\u001a\u00020\u0007\u0012\b\b\u0002\u0010%\u001a\u00020\u0003\u0012\b\b\u0002\u0010&\u001a\u00020\u0007\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010(\u001a\u00020\u0003\u0012\b\b\u0002\u0010)\u001a\u00020\u0003\u0012\b\b\u0002\u0010*\u001a\u00020\u0014\u0012\b\b\u0002\u0010+\u001a\u00020\u0003\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-\u0012\b\b\u0002\u0010.\u001a\u00020\u0003\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u00102\u001a\u00020\u0003\u0012\b\b\u0002\u00103\u001a\u00020\u0003\u0012\b\b\u0002\u00104\u001a\u00020\u0003\u0012\b\b\u0002\u00105\u001a\u00020\u0007\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u00107\u001a\u00020\u0003\u0012\b\b\u0002\u00108\u001a\u00020\u0003\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010:\u001a\u00020\u0007\u0012\b\b\u0002\u0010;\u001a\u00020\u0007\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b?\u0010@J\t\u0010w\u001a\u00020\u0003HÆ\u0003J\t\u0010x\u001a\u00020\u0003HÆ\u0003J\t\u0010y\u001a\u00020\u0003HÆ\u0003J\t\u0010z\u001a\u00020\u0007HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010~\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u000f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u00070\fHÆ\u0003J\u0010\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\fHÆ\u0003J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\rHÆ\u0003J\f\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\n\u0010\u0083\u0001\u001a\u00020\u0014HÆ\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\n\u0010\u0085\u0001\u001a\u00020\u0014HÆ\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\fHÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\fHÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u001dHÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008f\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0090\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0091\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0092\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0093\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0094\u0001\u001a\u00020\u0007HÆ\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\n\u0010\u0096\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0098\u0001\u001a\u00020\u0014HÆ\u0003J\n\u0010\u0099\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u009a\u0001\u001a\u0004\u0018\u00010-HÆ\u0003J\n\u0010\u009b\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u000100HÆ\u0003¢\u0006\u0002\u0010kJ\f\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\n\u0010\u009e\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0007HÆ\u0003J\f\u0010¢\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\n\u0010£\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¤\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¥\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\n\u0010¦\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010§\u0001\u001a\u00020\u0007HÆ\u0003J\f\u0010¨\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010©\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ª\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003JÔ\u0004\u0010«\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\f2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\b\b\u0002\u0010\u001a\u001a\u00020\u00032\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00072\b\b\u0002\u0010!\u001a\u00020\u00072\b\b\u0002\u0010\"\u001a\u00020\u00072\b\b\u0002\u0010#\u001a\u00020\u00072\b\b\u0002\u0010$\u001a\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00072\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00142\b\b\u0002\u0010+\u001a\u00020\u00032\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\b\b\u0002\u0010.\u001a\u00020\u00032\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u00102\u001a\u00020\u00032\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00072\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u00107\u001a\u00020\u00032\b\b\u0002\u00108\u001a\u00020\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010:\u001a\u00020\u00072\b\b\u0002\u0010;\u001a\u00020\u00072\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0003\u0010¬\u0001J\u0016\u0010\u00ad\u0001\u001a\u00020\u00032\t\u0010®\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010¯\u0001\u001a\u00020\u0014HÖ\u0081\u0004J\u000b\u0010°\u0001\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010AR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010AR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010AR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bD\u0010CR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bE\u0010CR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bF\u0010CR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\bI\u0010HR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010HR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bM\u0010CR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0011\u0010\u0016\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\bR\u0010OR\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bS\u0010AR\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bT\u0010AR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\f¢\u0006\b\n\u0000\u001a\u0004\bU\u0010HR\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bV\u0010AR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\bW\u0010HR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0011\u0010\u001e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010CR\u0011\u0010\u001f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b[\u0010CR\u0011\u0010 \u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010CR\u0011\u0010!\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b]\u0010CR\u0011\u0010\"\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b^\u0010CR\u0011\u0010#\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b_\u0010CR\u0011\u0010$\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b`\u0010CR\u0011\u0010%\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\ba\u0010AR\u0011\u0010&\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bb\u0010CR\u0013\u0010'\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bc\u0010CR\u0011\u0010(\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010AR\u0011\u0010)\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bd\u0010AR\u0011\u0010*\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\be\u0010OR\u0011\u0010+\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bf\u0010AR\u0013\u0010,\u001a\u0004\u0018\u00010-¢\u0006\b\n\u0000\u001a\u0004\bg\u0010hR\u0011\u0010.\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bi\u0010AR\u0015\u0010/\u001a\u0004\u0018\u000100¢\u0006\n\n\u0002\u0010l\u001a\u0004\bj\u0010kR\u0013\u00101\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bm\u0010CR\u0011\u00102\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bn\u0010AR\u0011\u00103\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010AR\u0011\u00104\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010AR\u0011\u00105\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bo\u0010CR\u0013\u00106\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bp\u0010CR\u0011\u00107\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010AR\u0011\u00108\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010AR\u0013\u00109\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bq\u0010CR\u0011\u0010:\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\br\u0010CR\u0011\u0010;\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bs\u0010CR\u0013\u0010<\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bt\u0010CR\u0013\u0010=\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bu\u0010CR\u0013\u0010>\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bv\u0010C¨\u0006±\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/player/PlayerUiState;", "", "isLoading", "", "isLoadingStreams", "isLoadingSubtitles", LinkHeader.Parameters.Title, "", "backdropUrl", "logoUrl", "posterUrl", "streams", "", "Lcom/arflix/tv/data/model/StreamSource;", "addonOrderedIds", "subtitles", "Lcom/arflix/tv/data/model/Subtitle;", "selectedStream", "selectedStreamUrl", "streamSelectionNonce", "", "selectedSubtitle", "subtitleSelectionNonce", "subtitlePreloadEnabled", "dolbyVisionCompatEnabled", "preloadedSubtitles", "subtitlePreloadComplete", "pendingSubtitleAddons", "savedPosition", "", "preferredAudioLanguage", "preferredSubtitleLang", "secondarySubtitleLang", "frameRateMatchingMode", "subtitleSize", "subtitleColor", "subtitleStyle", "subtitleStylized", "subtitleOffset", "error", "isSetupError", "autoPlayNext", "volumeBoostDb", "showLoadingStats", "activeSkipInterval", "Lcom/arflix/tv/data/repository/SkipInterval;", "skipIntervalDismissed", "streamProgress", "", "streamLoadPhase", "sourceSearchActive", "isAiTranslating", "isAiAvailable", "aiTargetLanguageName", "aiErrorToast", "isLiveAudioTranslating", "isFindingBestMatch", "matchToast", "matchStatusText", "matchLanguageName", "episodeTitle", "overview", "releaseYear", "<init>", "(ZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/model/StreamSource;Ljava/lang/String;ILcom/arflix/tv/data/model/Subtitle;IZZLjava/util/List;ZLjava/util/List;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZZIZLcom/arflix/tv/data/repository/SkipInterval;ZLjava/lang/Float;Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()Z", "getTitle", "()Ljava/lang/String;", "getBackdropUrl", "getLogoUrl", "getPosterUrl", "getStreams", "()Ljava/util/List;", "getAddonOrderedIds", "getSubtitles", "getSelectedStream", "()Lcom/arflix/tv/data/model/StreamSource;", "getSelectedStreamUrl", "getStreamSelectionNonce", "()I", "getSelectedSubtitle", "()Lcom/arflix/tv/data/model/Subtitle;", "getSubtitleSelectionNonce", "getSubtitlePreloadEnabled", "getDolbyVisionCompatEnabled", "getPreloadedSubtitles", "getSubtitlePreloadComplete", "getPendingSubtitleAddons", "getSavedPosition", "()J", "getPreferredAudioLanguage", "getPreferredSubtitleLang", "getSecondarySubtitleLang", "getFrameRateMatchingMode", "getSubtitleSize", "getSubtitleColor", "getSubtitleStyle", "getSubtitleStylized", "getSubtitleOffset", "getError", "getAutoPlayNext", "getVolumeBoostDb", "getShowLoadingStats", "getActiveSkipInterval", "()Lcom/arflix/tv/data/repository/SkipInterval;", "getSkipIntervalDismissed", "getStreamProgress", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getStreamLoadPhase", "getSourceSearchActive", "getAiTargetLanguageName", "getAiErrorToast", "getMatchToast", "getMatchStatusText", "getMatchLanguageName", "getEpisodeTitle", "getOverview", "getReleaseYear", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component50", "component51", "component52", "copy", "(ZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/model/StreamSource;Ljava/lang/String;ILcom/arflix/tv/data/model/Subtitle;IZZLjava/util/List;ZLjava/util/List;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZZIZLcom/arflix/tv/data/repository/SkipInterval;ZLjava/lang/Float;Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/ui/screens/player/PlayerUiState;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlayerUiState {
    public static final int $stable = 0;
    private final SkipInterval activeSkipInterval;
    private final List<String> addonOrderedIds;
    private final String aiErrorToast;
    private final String aiTargetLanguageName;
    private final boolean autoPlayNext;
    private final String backdropUrl;
    private final boolean dolbyVisionCompatEnabled;
    private final String episodeTitle;
    private final String error;
    private final String frameRateMatchingMode;
    private final boolean isAiAvailable;
    private final boolean isAiTranslating;
    private final boolean isFindingBestMatch;
    private final boolean isLiveAudioTranslating;
    private final boolean isLoading;
    private final boolean isLoadingStreams;
    private final boolean isLoadingSubtitles;
    private final boolean isSetupError;
    private final String logoUrl;
    private final String matchLanguageName;
    private final String matchStatusText;
    private final String matchToast;
    private final String overview;
    private final List<String> pendingSubtitleAddons;
    private final String posterUrl;
    private final String preferredAudioLanguage;
    private final String preferredSubtitleLang;
    private final List<Subtitle> preloadedSubtitles;
    private final String releaseYear;
    private final long savedPosition;
    private final String secondarySubtitleLang;
    private final StreamSource selectedStream;
    private final String selectedStreamUrl;
    private final Subtitle selectedSubtitle;
    private final boolean showLoadingStats;
    private final boolean skipIntervalDismissed;
    private final boolean sourceSearchActive;
    private final String streamLoadPhase;
    private final Float streamProgress;
    private final int streamSelectionNonce;
    private final List<StreamSource> streams;
    private final String subtitleColor;
    private final String subtitleOffset;
    private final boolean subtitlePreloadComplete;
    private final boolean subtitlePreloadEnabled;
    private final int subtitleSelectionNonce;
    private final String subtitleSize;
    private final String subtitleStyle;
    private final boolean subtitleStylized;
    private final List<Subtitle> subtitles;
    private final String title;
    private final int volumeBoostDb;

    public PlayerUiState() {
        this(false, false, false, null, null, null, null, null, null, null, null, null, 0, null, 0, false, false, null, false, null, 0L, null, null, null, null, null, null, null, false, null, null, false, false, 0, false, null, false, null, null, false, false, false, null, null, false, false, null, null, null, null, null, null, -1, 1048575, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PlayerUiState copy$default(PlayerUiState playerUiState, boolean z, boolean z5, boolean z10, String str, String str2, String str3, String str4, List list, List list2, List list3, StreamSource streamSource, String str5, int i10, Subtitle subtitle, int i11, boolean z11, boolean z12, List list4, boolean z13, List list5, long j10, String str6, String str7, String str8, String str9, String str10, String str11, String str12, boolean z14, String str13, String str14, boolean z15, boolean z16, int i12, boolean z17, SkipInterval skipInterval, boolean z18, Float f10, String str15, boolean z19, boolean z20, boolean z21, String str16, String str17, boolean z22, boolean z23, String str18, String str19, String str20, String str21, String str22, String str23, int i13, int i14, Object obj) {
        boolean z24 = (i13 & 1) != 0 ? playerUiState.isLoading : z;
        return playerUiState.copy(z24, (i13 & 2) != 0 ? playerUiState.isLoadingStreams : z5, (i13 & 4) != 0 ? playerUiState.isLoadingSubtitles : z10, (i13 & 8) != 0 ? playerUiState.title : str, (i13 & 16) != 0 ? playerUiState.backdropUrl : str2, (i13 & 32) != 0 ? playerUiState.logoUrl : str3, (i13 & 64) != 0 ? playerUiState.posterUrl : str4, (i13 & 128) != 0 ? playerUiState.streams : list, (i13 & 256) != 0 ? playerUiState.addonOrderedIds : list2, (i13 & 512) != 0 ? playerUiState.subtitles : list3, (i13 & 1024) != 0 ? playerUiState.selectedStream : streamSource, (i13 & 2048) != 0 ? playerUiState.selectedStreamUrl : str5, (i13 & 4096) != 0 ? playerUiState.streamSelectionNonce : i10, (i13 & 8192) != 0 ? playerUiState.selectedSubtitle : subtitle, (i13 & 16384) != 0 ? playerUiState.subtitleSelectionNonce : i11, (i13 & 32768) != 0 ? playerUiState.subtitlePreloadEnabled : z11, (i13 & 65536) != 0 ? playerUiState.dolbyVisionCompatEnabled : z12, (i13 & 131072) != 0 ? playerUiState.preloadedSubtitles : list4, (i13 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? playerUiState.subtitlePreloadComplete : z13, (i13 & 524288) != 0 ? playerUiState.pendingSubtitleAddons : list5, (i13 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? playerUiState.savedPosition : j10, (i13 & 2097152) != 0 ? playerUiState.preferredAudioLanguage : str6, (i13 & 4194304) != 0 ? playerUiState.preferredSubtitleLang : str7, (i13 & 8388608) != 0 ? playerUiState.secondarySubtitleLang : str8, (i13 & 16777216) != 0 ? playerUiState.frameRateMatchingMode : str9, (i13 & 33554432) != 0 ? playerUiState.subtitleSize : str10, (i13 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? playerUiState.subtitleColor : str11, (i13 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? playerUiState.subtitleStyle : str12, (i13 & 268435456) != 0 ? playerUiState.subtitleStylized : z14, (i13 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? playerUiState.subtitleOffset : str13, (i13 & 1073741824) != 0 ? playerUiState.error : str14, (i13 & Integer.MIN_VALUE) != 0 ? playerUiState.isSetupError : z15, (i14 & 1) != 0 ? playerUiState.autoPlayNext : z16, (i14 & 2) != 0 ? playerUiState.volumeBoostDb : i12, (i14 & 4) != 0 ? playerUiState.showLoadingStats : z17, (i14 & 8) != 0 ? playerUiState.activeSkipInterval : skipInterval, (i14 & 16) != 0 ? playerUiState.skipIntervalDismissed : z18, (i14 & 32) != 0 ? playerUiState.streamProgress : f10, (i14 & 64) != 0 ? playerUiState.streamLoadPhase : str15, (i14 & 128) != 0 ? playerUiState.sourceSearchActive : z19, (i14 & 256) != 0 ? playerUiState.isAiTranslating : z20, (i14 & 512) != 0 ? playerUiState.isAiAvailable : z21, (i14 & 1024) != 0 ? playerUiState.aiTargetLanguageName : str16, (i14 & 2048) != 0 ? playerUiState.aiErrorToast : str17, (i14 & 4096) != 0 ? playerUiState.isLiveAudioTranslating : z22, (i14 & 8192) != 0 ? playerUiState.isFindingBestMatch : z23, (i14 & 16384) != 0 ? playerUiState.matchToast : str18, (i14 & 32768) != 0 ? playerUiState.matchStatusText : str19, (i14 & 65536) != 0 ? playerUiState.matchLanguageName : str20, (i14 & 131072) != 0 ? playerUiState.episodeTitle : str21, (i14 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? playerUiState.overview : str22, (i14 & 524288) != 0 ? playerUiState.releaseYear : str23);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final List<Subtitle> component10() {
        return this.subtitles;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final StreamSource getSelectedStream() {
        return this.selectedStream;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSelectedStreamUrl() {
        return this.selectedStreamUrl;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getStreamSelectionNonce() {
        return this.streamSelectionNonce;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Subtitle getSelectedSubtitle() {
        return this.selectedSubtitle;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getSubtitleSelectionNonce() {
        return this.subtitleSelectionNonce;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getSubtitlePreloadEnabled() {
        return this.subtitlePreloadEnabled;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getDolbyVisionCompatEnabled() {
        return this.dolbyVisionCompatEnabled;
    }

    public final List<Subtitle> component18() {
        return this.preloadedSubtitles;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getSubtitlePreloadComplete() {
        return this.subtitlePreloadComplete;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsLoadingStreams() {
        return this.isLoadingStreams;
    }

    public final List<String> component20() {
        return this.pendingSubtitleAddons;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final long getSavedPosition() {
        return this.savedPosition;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getPreferredAudioLanguage() {
        return this.preferredAudioLanguage;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getPreferredSubtitleLang() {
        return this.preferredSubtitleLang;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getSecondarySubtitleLang() {
        return this.secondarySubtitleLang;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getFrameRateMatchingMode() {
        return this.frameRateMatchingMode;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final String getSubtitleSize() {
        return this.subtitleSize;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getSubtitleColor() {
        return this.subtitleColor;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final String getSubtitleStyle() {
        return this.subtitleStyle;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final boolean getSubtitleStylized() {
        return this.subtitleStylized;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsLoadingSubtitles() {
        return this.isLoadingSubtitles;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final String getSubtitleOffset() {
        return this.subtitleOffset;
    }

    /* JADX INFO: renamed from: component31, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component32, reason: from getter */
    public final boolean getIsSetupError() {
        return this.isSetupError;
    }

    /* JADX INFO: renamed from: component33, reason: from getter */
    public final boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    /* JADX INFO: renamed from: component34, reason: from getter */
    public final int getVolumeBoostDb() {
        return this.volumeBoostDb;
    }

    /* JADX INFO: renamed from: component35, reason: from getter */
    public final boolean getShowLoadingStats() {
        return this.showLoadingStats;
    }

    /* JADX INFO: renamed from: component36, reason: from getter */
    public final SkipInterval getActiveSkipInterval() {
        return this.activeSkipInterval;
    }

    /* JADX INFO: renamed from: component37, reason: from getter */
    public final boolean getSkipIntervalDismissed() {
        return this.skipIntervalDismissed;
    }

    /* JADX INFO: renamed from: component38, reason: from getter */
    public final Float getStreamProgress() {
        return this.streamProgress;
    }

    /* JADX INFO: renamed from: component39, reason: from getter */
    public final String getStreamLoadPhase() {
        return this.streamLoadPhase;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component40, reason: from getter */
    public final boolean getSourceSearchActive() {
        return this.sourceSearchActive;
    }

    /* JADX INFO: renamed from: component41, reason: from getter */
    public final boolean getIsAiTranslating() {
        return this.isAiTranslating;
    }

    /* JADX INFO: renamed from: component42, reason: from getter */
    public final boolean getIsAiAvailable() {
        return this.isAiAvailable;
    }

    /* JADX INFO: renamed from: component43, reason: from getter */
    public final String getAiTargetLanguageName() {
        return this.aiTargetLanguageName;
    }

    /* JADX INFO: renamed from: component44, reason: from getter */
    public final String getAiErrorToast() {
        return this.aiErrorToast;
    }

    /* JADX INFO: renamed from: component45, reason: from getter */
    public final boolean getIsLiveAudioTranslating() {
        return this.isLiveAudioTranslating;
    }

    /* JADX INFO: renamed from: component46, reason: from getter */
    public final boolean getIsFindingBestMatch() {
        return this.isFindingBestMatch;
    }

    /* JADX INFO: renamed from: component47, reason: from getter */
    public final String getMatchToast() {
        return this.matchToast;
    }

    /* JADX INFO: renamed from: component48, reason: from getter */
    public final String getMatchStatusText() {
        return this.matchStatusText;
    }

    /* JADX INFO: renamed from: component49, reason: from getter */
    public final String getMatchLanguageName() {
        return this.matchLanguageName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBackdropUrl() {
        return this.backdropUrl;
    }

    /* JADX INFO: renamed from: component50, reason: from getter */
    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    /* JADX INFO: renamed from: component51, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component52, reason: from getter */
    public final String getReleaseYear() {
        return this.releaseYear;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    public final List<StreamSource> component8() {
        return this.streams;
    }

    public final List<String> component9() {
        return this.addonOrderedIds;
    }

    public final PlayerUiState copy(boolean isLoading, boolean isLoadingStreams, boolean isLoadingSubtitles, String title, String backdropUrl, String logoUrl, String posterUrl, List<StreamSource> streams, List<String> addonOrderedIds, List<Subtitle> subtitles, StreamSource selectedStream, String selectedStreamUrl, int streamSelectionNonce, Subtitle selectedSubtitle, int subtitleSelectionNonce, boolean subtitlePreloadEnabled, boolean dolbyVisionCompatEnabled, List<Subtitle> preloadedSubtitles, boolean subtitlePreloadComplete, List<String> pendingSubtitleAddons, long savedPosition, String preferredAudioLanguage, String preferredSubtitleLang, String secondarySubtitleLang, String frameRateMatchingMode, String subtitleSize, String subtitleColor, String subtitleStyle, boolean subtitleStylized, String subtitleOffset, String error, boolean isSetupError, boolean autoPlayNext, int volumeBoostDb, boolean showLoadingStats, SkipInterval activeSkipInterval, boolean skipIntervalDismissed, Float streamProgress, String streamLoadPhase, boolean sourceSearchActive, boolean isAiTranslating, boolean isAiAvailable, String aiTargetLanguageName, String aiErrorToast, boolean isLiveAudioTranslating, boolean isFindingBestMatch, String matchToast, String matchStatusText, String matchLanguageName, String episodeTitle, String overview, String releaseYear) {
        return new PlayerUiState(isLoading, isLoadingStreams, isLoadingSubtitles, title, backdropUrl, logoUrl, posterUrl, streams, addonOrderedIds, subtitles, selectedStream, selectedStreamUrl, streamSelectionNonce, selectedSubtitle, subtitleSelectionNonce, subtitlePreloadEnabled, dolbyVisionCompatEnabled, preloadedSubtitles, subtitlePreloadComplete, pendingSubtitleAddons, savedPosition, preferredAudioLanguage, preferredSubtitleLang, secondarySubtitleLang, frameRateMatchingMode, subtitleSize, subtitleColor, subtitleStyle, subtitleStylized, subtitleOffset, error, isSetupError, autoPlayNext, volumeBoostDb, showLoadingStats, activeSkipInterval, skipIntervalDismissed, streamProgress, streamLoadPhase, sourceSearchActive, isAiTranslating, isAiAvailable, aiTargetLanguageName, aiErrorToast, isLiveAudioTranslating, isFindingBestMatch, matchToast, matchStatusText, matchLanguageName, episodeTitle, overview, releaseYear);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerUiState)) {
            return false;
        }
        PlayerUiState playerUiState = (PlayerUiState) other;
        return this.isLoading == playerUiState.isLoading && this.isLoadingStreams == playerUiState.isLoadingStreams && this.isLoadingSubtitles == playerUiState.isLoadingSubtitles && kotlin.jvm.internal.p.a(this.title, playerUiState.title) && kotlin.jvm.internal.p.a(this.backdropUrl, playerUiState.backdropUrl) && kotlin.jvm.internal.p.a(this.logoUrl, playerUiState.logoUrl) && kotlin.jvm.internal.p.a(this.posterUrl, playerUiState.posterUrl) && kotlin.jvm.internal.p.a(this.streams, playerUiState.streams) && kotlin.jvm.internal.p.a(this.addonOrderedIds, playerUiState.addonOrderedIds) && kotlin.jvm.internal.p.a(this.subtitles, playerUiState.subtitles) && kotlin.jvm.internal.p.a(this.selectedStream, playerUiState.selectedStream) && kotlin.jvm.internal.p.a(this.selectedStreamUrl, playerUiState.selectedStreamUrl) && this.streamSelectionNonce == playerUiState.streamSelectionNonce && kotlin.jvm.internal.p.a(this.selectedSubtitle, playerUiState.selectedSubtitle) && this.subtitleSelectionNonce == playerUiState.subtitleSelectionNonce && this.subtitlePreloadEnabled == playerUiState.subtitlePreloadEnabled && this.dolbyVisionCompatEnabled == playerUiState.dolbyVisionCompatEnabled && kotlin.jvm.internal.p.a(this.preloadedSubtitles, playerUiState.preloadedSubtitles) && this.subtitlePreloadComplete == playerUiState.subtitlePreloadComplete && kotlin.jvm.internal.p.a(this.pendingSubtitleAddons, playerUiState.pendingSubtitleAddons) && this.savedPosition == playerUiState.savedPosition && kotlin.jvm.internal.p.a(this.preferredAudioLanguage, playerUiState.preferredAudioLanguage) && kotlin.jvm.internal.p.a(this.preferredSubtitleLang, playerUiState.preferredSubtitleLang) && kotlin.jvm.internal.p.a(this.secondarySubtitleLang, playerUiState.secondarySubtitleLang) && kotlin.jvm.internal.p.a(this.frameRateMatchingMode, playerUiState.frameRateMatchingMode) && kotlin.jvm.internal.p.a(this.subtitleSize, playerUiState.subtitleSize) && kotlin.jvm.internal.p.a(this.subtitleColor, playerUiState.subtitleColor) && kotlin.jvm.internal.p.a(this.subtitleStyle, playerUiState.subtitleStyle) && this.subtitleStylized == playerUiState.subtitleStylized && kotlin.jvm.internal.p.a(this.subtitleOffset, playerUiState.subtitleOffset) && kotlin.jvm.internal.p.a(this.error, playerUiState.error) && this.isSetupError == playerUiState.isSetupError && this.autoPlayNext == playerUiState.autoPlayNext && this.volumeBoostDb == playerUiState.volumeBoostDb && this.showLoadingStats == playerUiState.showLoadingStats && kotlin.jvm.internal.p.a(this.activeSkipInterval, playerUiState.activeSkipInterval) && this.skipIntervalDismissed == playerUiState.skipIntervalDismissed && kotlin.jvm.internal.p.a(this.streamProgress, playerUiState.streamProgress) && kotlin.jvm.internal.p.a(this.streamLoadPhase, playerUiState.streamLoadPhase) && this.sourceSearchActive == playerUiState.sourceSearchActive && this.isAiTranslating == playerUiState.isAiTranslating && this.isAiAvailable == playerUiState.isAiAvailable && kotlin.jvm.internal.p.a(this.aiTargetLanguageName, playerUiState.aiTargetLanguageName) && kotlin.jvm.internal.p.a(this.aiErrorToast, playerUiState.aiErrorToast) && this.isLiveAudioTranslating == playerUiState.isLiveAudioTranslating && this.isFindingBestMatch == playerUiState.isFindingBestMatch && kotlin.jvm.internal.p.a(this.matchToast, playerUiState.matchToast) && kotlin.jvm.internal.p.a(this.matchStatusText, playerUiState.matchStatusText) && kotlin.jvm.internal.p.a(this.matchLanguageName, playerUiState.matchLanguageName) && kotlin.jvm.internal.p.a(this.episodeTitle, playerUiState.episodeTitle) && kotlin.jvm.internal.p.a(this.overview, playerUiState.overview) && kotlin.jvm.internal.p.a(this.releaseYear, playerUiState.releaseYear);
    }

    public final SkipInterval getActiveSkipInterval() {
        return this.activeSkipInterval;
    }

    public final List<String> getAddonOrderedIds() {
        return this.addonOrderedIds;
    }

    public final String getAiErrorToast() {
        return this.aiErrorToast;
    }

    public final String getAiTargetLanguageName() {
        return this.aiTargetLanguageName;
    }

    public final boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    public final String getBackdropUrl() {
        return this.backdropUrl;
    }

    public final boolean getDolbyVisionCompatEnabled() {
        return this.dolbyVisionCompatEnabled;
    }

    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    public final String getError() {
        return this.error;
    }

    public final String getFrameRateMatchingMode() {
        return this.frameRateMatchingMode;
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final String getMatchLanguageName() {
        return this.matchLanguageName;
    }

    public final String getMatchStatusText() {
        return this.matchStatusText;
    }

    public final String getMatchToast() {
        return this.matchToast;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final List<String> getPendingSubtitleAddons() {
        return this.pendingSubtitleAddons;
    }

    public final String getPosterUrl() {
        return this.posterUrl;
    }

    public final String getPreferredAudioLanguage() {
        return this.preferredAudioLanguage;
    }

    public final String getPreferredSubtitleLang() {
        return this.preferredSubtitleLang;
    }

    public final List<Subtitle> getPreloadedSubtitles() {
        return this.preloadedSubtitles;
    }

    public final String getReleaseYear() {
        return this.releaseYear;
    }

    public final long getSavedPosition() {
        return this.savedPosition;
    }

    public final String getSecondarySubtitleLang() {
        return this.secondarySubtitleLang;
    }

    public final StreamSource getSelectedStream() {
        return this.selectedStream;
    }

    public final String getSelectedStreamUrl() {
        return this.selectedStreamUrl;
    }

    public final Subtitle getSelectedSubtitle() {
        return this.selectedSubtitle;
    }

    public final boolean getShowLoadingStats() {
        return this.showLoadingStats;
    }

    public final boolean getSkipIntervalDismissed() {
        return this.skipIntervalDismissed;
    }

    public final boolean getSourceSearchActive() {
        return this.sourceSearchActive;
    }

    public final String getStreamLoadPhase() {
        return this.streamLoadPhase;
    }

    public final Float getStreamProgress() {
        return this.streamProgress;
    }

    public final int getStreamSelectionNonce() {
        return this.streamSelectionNonce;
    }

    public final List<StreamSource> getStreams() {
        return this.streams;
    }

    public final String getSubtitleColor() {
        return this.subtitleColor;
    }

    public final String getSubtitleOffset() {
        return this.subtitleOffset;
    }

    public final boolean getSubtitlePreloadComplete() {
        return this.subtitlePreloadComplete;
    }

    public final boolean getSubtitlePreloadEnabled() {
        return this.subtitlePreloadEnabled;
    }

    public final int getSubtitleSelectionNonce() {
        return this.subtitleSelectionNonce;
    }

    public final String getSubtitleSize() {
        return this.subtitleSize;
    }

    public final String getSubtitleStyle() {
        return this.subtitleStyle;
    }

    public final boolean getSubtitleStylized() {
        return this.subtitleStylized;
    }

    public final List<Subtitle> getSubtitles() {
        return this.subtitles;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getVolumeBoostDb() {
        return this.volumeBoostDb;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c((((((this.isLoading ? 1231 : 1237) * 31) + (this.isLoadingStreams ? 1231 : 1237)) * 31) + (this.isLoadingSubtitles ? 1231 : 1237)) * 31, 31, this.title);
        String str = this.backdropUrl;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.logoUrl;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.posterUrl;
        int iE = androidx.compose.material3.d.e(this.subtitles, androidx.compose.material3.d.e(this.addonOrderedIds, androidx.compose.material3.d.e(this.streams, (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31, 31), 31), 31);
        StreamSource streamSource = this.selectedStream;
        int iHashCode3 = (iE + (streamSource == null ? 0 : streamSource.hashCode())) * 31;
        String str4 = this.selectedStreamUrl;
        int iHashCode4 = (((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.streamSelectionNonce) * 31;
        Subtitle subtitle = this.selectedSubtitle;
        int iE2 = androidx.compose.material3.d.e(this.pendingSubtitleAddons, (androidx.compose.material3.d.e(this.preloadedSubtitles, (((((((iHashCode4 + (subtitle == null ? 0 : subtitle.hashCode())) * 31) + this.subtitleSelectionNonce) * 31) + (this.subtitlePreloadEnabled ? 1231 : 1237)) * 31) + (this.dolbyVisionCompatEnabled ? 1231 : 1237)) * 31, 31) + (this.subtitlePreloadComplete ? 1231 : 1237)) * 31, 31);
        long j10 = this.savedPosition;
        int iC2 = androidx.compose.foundation.c.c((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iE2 + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31, this.preferredAudioLanguage), 31, this.preferredSubtitleLang), 31, this.secondarySubtitleLang), 31, this.frameRateMatchingMode), 31, this.subtitleSize), 31, this.subtitleColor), 31, this.subtitleStyle) + (this.subtitleStylized ? 1231 : 1237)) * 31, 31, this.subtitleOffset);
        String str5 = this.error;
        int iHashCode5 = (((((((((iC2 + (str5 == null ? 0 : str5.hashCode())) * 31) + (this.isSetupError ? 1231 : 1237)) * 31) + (this.autoPlayNext ? 1231 : 1237)) * 31) + this.volumeBoostDb) * 31) + (this.showLoadingStats ? 1231 : 1237)) * 31;
        SkipInterval skipInterval = this.activeSkipInterval;
        int iHashCode6 = (((iHashCode5 + (skipInterval == null ? 0 : skipInterval.hashCode())) * 31) + (this.skipIntervalDismissed ? 1231 : 1237)) * 31;
        Float f10 = this.streamProgress;
        int iHashCode7 = (iHashCode6 + (f10 == null ? 0 : f10.hashCode())) * 31;
        String str6 = this.streamLoadPhase;
        int iC3 = androidx.compose.foundation.c.c((((((((iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31) + (this.sourceSearchActive ? 1231 : 1237)) * 31) + (this.isAiTranslating ? 1231 : 1237)) * 31) + (this.isAiAvailable ? 1231 : 1237)) * 31, 31, this.aiTargetLanguageName);
        String str7 = this.aiErrorToast;
        int iHashCode8 = (((((iC3 + (str7 == null ? 0 : str7.hashCode())) * 31) + (this.isLiveAudioTranslating ? 1231 : 1237)) * 31) + (this.isFindingBestMatch ? 1231 : 1237)) * 31;
        String str8 = this.matchToast;
        int iC4 = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31, 31, this.matchStatusText), 31, this.matchLanguageName);
        String str9 = this.episodeTitle;
        int iHashCode9 = (iC4 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.overview;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.releaseYear;
        return iHashCode10 + (str11 != null ? str11.hashCode() : 0);
    }

    public final boolean isAiAvailable() {
        return this.isAiAvailable;
    }

    public final boolean isAiTranslating() {
        return this.isAiTranslating;
    }

    public final boolean isFindingBestMatch() {
        return this.isFindingBestMatch;
    }

    public final boolean isLiveAudioTranslating() {
        return this.isLiveAudioTranslating;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isLoadingStreams() {
        return this.isLoadingStreams;
    }

    public final boolean isLoadingSubtitles() {
        return this.isLoadingSubtitles;
    }

    public final boolean isSetupError() {
        return this.isSetupError;
    }

    public String toString() {
        boolean z = this.isLoading;
        boolean z5 = this.isLoadingStreams;
        boolean z10 = this.isLoadingSubtitles;
        String str = this.title;
        String str2 = this.backdropUrl;
        String str3 = this.logoUrl;
        String str4 = this.posterUrl;
        List<StreamSource> list = this.streams;
        List<String> list2 = this.addonOrderedIds;
        List<Subtitle> list3 = this.subtitles;
        StreamSource streamSource = this.selectedStream;
        String str5 = this.selectedStreamUrl;
        int i10 = this.streamSelectionNonce;
        Subtitle subtitle = this.selectedSubtitle;
        int i11 = this.subtitleSelectionNonce;
        boolean z11 = this.subtitlePreloadEnabled;
        boolean z12 = this.dolbyVisionCompatEnabled;
        List<Subtitle> list4 = this.preloadedSubtitles;
        boolean z13 = this.subtitlePreloadComplete;
        List<String> list5 = this.pendingSubtitleAddons;
        long j10 = this.savedPosition;
        String str6 = this.preferredAudioLanguage;
        String str7 = this.preferredSubtitleLang;
        String str8 = this.secondarySubtitleLang;
        String str9 = this.frameRateMatchingMode;
        String str10 = this.subtitleSize;
        String str11 = this.subtitleColor;
        String str12 = this.subtitleStyle;
        boolean z14 = this.subtitleStylized;
        String str13 = this.subtitleOffset;
        String str14 = this.error;
        boolean z15 = this.isSetupError;
        boolean z16 = this.autoPlayNext;
        int i12 = this.volumeBoostDb;
        boolean z17 = this.showLoadingStats;
        SkipInterval skipInterval = this.activeSkipInterval;
        boolean z18 = this.skipIntervalDismissed;
        Float f10 = this.streamProgress;
        String str15 = this.streamLoadPhase;
        boolean z19 = this.sourceSearchActive;
        boolean z20 = this.isAiTranslating;
        boolean z21 = this.isAiAvailable;
        String str16 = this.aiTargetLanguageName;
        String str17 = this.aiErrorToast;
        boolean z22 = this.isLiveAudioTranslating;
        boolean z23 = this.isFindingBestMatch;
        String str18 = this.matchToast;
        String str19 = this.matchStatusText;
        String str20 = this.matchLanguageName;
        String str21 = this.episodeTitle;
        String str22 = this.overview;
        String str23 = this.releaseYear;
        StringBuilder sb2 = new StringBuilder("PlayerUiState(isLoading=");
        sb2.append(z);
        sb2.append(", isLoadingStreams=");
        sb2.append(z5);
        sb2.append(", isLoadingSubtitles=");
        a2.B(sb2, z10, ", title=", str, ", backdropUrl=");
        y.a.i(sb2, str2, ", logoUrl=", str3, ", posterUrl=");
        sb2.append(str4);
        sb2.append(", streams=");
        sb2.append(list);
        sb2.append(", addonOrderedIds=");
        a2.A(sb2, list2, ", subtitles=", list3, ", selectedStream=");
        sb2.append(streamSource);
        sb2.append(", selectedStreamUrl=");
        sb2.append(str5);
        sb2.append(", streamSelectionNonce=");
        sb2.append(i10);
        sb2.append(", selectedSubtitle=");
        sb2.append(subtitle);
        sb2.append(", subtitleSelectionNonce=");
        sb2.append(i11);
        sb2.append(", subtitlePreloadEnabled=");
        sb2.append(z11);
        sb2.append(", dolbyVisionCompatEnabled=");
        sb2.append(z12);
        sb2.append(", preloadedSubtitles=");
        sb2.append(list4);
        sb2.append(", subtitlePreloadComplete=");
        sb2.append(z13);
        sb2.append(", pendingSubtitleAddons=");
        sb2.append(list5);
        sb2.append(", savedPosition=");
        sb2.append(j10);
        sb2.append(", preferredAudioLanguage=");
        sb2.append(str6);
        y.a.i(sb2, ", preferredSubtitleLang=", str7, ", secondarySubtitleLang=", str8);
        y.a.i(sb2, ", frameRateMatchingMode=", str9, ", subtitleSize=", str10);
        y.a.i(sb2, ", subtitleColor=", str11, ", subtitleStyle=", str12);
        sb2.append(", subtitleStylized=");
        sb2.append(z14);
        sb2.append(", subtitleOffset=");
        sb2.append(str13);
        sb2.append(", error=");
        sb2.append(str14);
        sb2.append(", isSetupError=");
        sb2.append(z15);
        sb2.append(", autoPlayNext=");
        sb2.append(z16);
        sb2.append(", volumeBoostDb=");
        sb2.append(i12);
        sb2.append(", showLoadingStats=");
        sb2.append(z17);
        sb2.append(", activeSkipInterval=");
        sb2.append(skipInterval);
        sb2.append(", skipIntervalDismissed=");
        sb2.append(z18);
        sb2.append(", streamProgress=");
        sb2.append(f10);
        sb2.append(", streamLoadPhase=");
        sb2.append(str15);
        sb2.append(", sourceSearchActive=");
        sb2.append(z19);
        sb2.append(", isAiTranslating=");
        sb2.append(z20);
        sb2.append(", isAiAvailable=");
        sb2.append(z21);
        y.a.i(sb2, ", aiTargetLanguageName=", str16, ", aiErrorToast=", str17);
        sb2.append(", isLiveAudioTranslating=");
        sb2.append(z22);
        sb2.append(", isFindingBestMatch=");
        sb2.append(z23);
        y.a.i(sb2, ", matchToast=", str18, ", matchStatusText=", str19);
        y.a.i(sb2, ", matchLanguageName=", str20, ", episodeTitle=", str21);
        y.a.i(sb2, ", overview=", str22, ", releaseYear=", str23);
        sb2.append(")");
        return sb2.toString();
    }

    public PlayerUiState(boolean z, boolean z5, boolean z10, String str, String str2, String str3, String str4, List<StreamSource> list, List<String> list2, List<Subtitle> list3, StreamSource streamSource, String str5, int i10, Subtitle subtitle, int i11, boolean z11, boolean z12, List<Subtitle> list4, boolean z13, List<String> list5, long j10, String str6, String str7, String str8, String str9, String str10, String str11, String str12, boolean z14, String str13, String str14, boolean z15, boolean z16, int i12, boolean z17, SkipInterval skipInterval, boolean z18, Float f10, String str15, boolean z19, boolean z20, boolean z21, String str16, String str17, boolean z22, boolean z23, String str18, String str19, String str20, String str21, String str22, String str23) {
        this.isLoading = z;
        this.isLoadingStreams = z5;
        this.isLoadingSubtitles = z10;
        this.title = str;
        this.backdropUrl = str2;
        this.logoUrl = str3;
        this.posterUrl = str4;
        this.streams = list;
        this.addonOrderedIds = list2;
        this.subtitles = list3;
        this.selectedStream = streamSource;
        this.selectedStreamUrl = str5;
        this.streamSelectionNonce = i10;
        this.selectedSubtitle = subtitle;
        this.subtitleSelectionNonce = i11;
        this.subtitlePreloadEnabled = z11;
        this.dolbyVisionCompatEnabled = z12;
        this.preloadedSubtitles = list4;
        this.subtitlePreloadComplete = z13;
        this.pendingSubtitleAddons = list5;
        this.savedPosition = j10;
        this.preferredAudioLanguage = str6;
        this.preferredSubtitleLang = str7;
        this.secondarySubtitleLang = str8;
        this.frameRateMatchingMode = str9;
        this.subtitleSize = str10;
        this.subtitleColor = str11;
        this.subtitleStyle = str12;
        this.subtitleStylized = z14;
        this.subtitleOffset = str13;
        this.error = str14;
        this.isSetupError = z15;
        this.autoPlayNext = z16;
        this.volumeBoostDb = i12;
        this.showLoadingStats = z17;
        this.activeSkipInterval = skipInterval;
        this.skipIntervalDismissed = z18;
        this.streamProgress = f10;
        this.streamLoadPhase = str15;
        this.sourceSearchActive = z19;
        this.isAiTranslating = z20;
        this.isAiAvailable = z21;
        this.aiTargetLanguageName = str16;
        this.aiErrorToast = str17;
        this.isLiveAudioTranslating = z22;
        this.isFindingBestMatch = z23;
        this.matchToast = str18;
        this.matchStatusText = str19;
        this.matchLanguageName = str20;
        this.episodeTitle = str21;
        this.overview = str22;
        this.releaseYear = str23;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PlayerUiState(boolean z, boolean z5, boolean z10, String str, String str2, String str3, String str4, List list, List list2, List list3, StreamSource streamSource, String str5, int i10, Subtitle subtitle, int i11, boolean z11, boolean z12, List list4, boolean z13, List list5, long j10, String str6, String str7, String str8, String str9, String str10, String str11, String str12, boolean z14, String str13, String str14, boolean z15, boolean z16, int i12, boolean z17, SkipInterval skipInterval, boolean z18, Float f10, String str15, boolean z19, boolean z20, boolean z21, String str16, String str17, boolean z22, boolean z23, String str18, String str19, String str20, String str21, String str22, String str23, int i13, int i14, kotlin.jvm.internal.h hVar) {
        boolean z24 = (i13 & 1) != 0 ? true : z;
        boolean z25 = (i13 & 2) != 0 ? false : z5;
        boolean z26 = (i13 & 4) != 0 ? false : z10;
        String str24 = (i13 & 8) != 0 ? "" : str;
        String str25 = (i13 & 16) != 0 ? null : str2;
        String str26 = (i13 & 32) != 0 ? null : str3;
        String str27 = (i13 & 64) != 0 ? null : str4;
        int i15 = i13 & 128;
        List list6 = kotlin.collections.z.f19728i;
        this(z24, z25, z26, str24, str25, str26, str27, i15 != 0 ? list6 : list, (i13 & 256) != 0 ? list6 : list2, (i13 & 512) != 0 ? list6 : list3, (i13 & 1024) != 0 ? null : streamSource, (i13 & 2048) != 0 ? null : str5, (i13 & 4096) != 0 ? 0 : i10, (i13 & 8192) != 0 ? null : subtitle, (i13 & 16384) != 0 ? 0 : i11, (i13 & 32768) != 0 ? true : z11, (i13 & 65536) != 0 ? true : z12, (i13 & 131072) != 0 ? list6 : list4, (i13 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? false : z13, (i13 & 524288) == 0 ? list5 : list6, (i13 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? 0L : j10, (i13 & 2097152) != 0 ? "en" : str6, (i13 & 4194304) != 0 ? "" : str7, (i13 & 8388608) != 0 ? "" : str8, (i13 & 16777216) != 0 ? "Off" : str9, (i13 & 33554432) != 0 ? "Medium" : str10, (i13 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? "White" : str11, (i13 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? "Bold" : str12, (i13 & 268435456) != 0 ? true : z14, (i13 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? "Bottom" : str13, (i13 & 1073741824) != 0 ? null : str14, (i13 & Integer.MIN_VALUE) != 0 ? false : z15, (i14 & 1) != 0 ? true : z16, (i14 & 2) != 0 ? 0 : i12, (i14 & 4) != 0 ? true : z17, (i14 & 8) != 0 ? null : skipInterval, (i14 & 16) != 0 ? false : z18, (i14 & 32) != 0 ? null : f10, (i14 & 64) != 0 ? null : str15, (i14 & 128) != 0 ? false : z19, (i14 & 256) != 0 ? false : z20, (i14 & 512) != 0 ? false : z21, (i14 & 1024) != 0 ? "" : str16, (i14 & 2048) != 0 ? null : str17, (i14 & 4096) != 0 ? false : z22, (i14 & 8192) != 0 ? false : z23, (i14 & 16384) != 0 ? null : str18, (i14 & 32768) != 0 ? "" : str19, (i14 & 65536) == 0 ? str20 : "", (i14 & 131072) != 0 ? null : str21, (i14 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : str22, (i14 & 524288) != 0 ? null : str23);
    }
}
