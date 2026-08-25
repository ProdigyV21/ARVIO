package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b6\b\u0007\u0018\u00002\u00020\u0001Bß\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u0002\u0012\u0006\u0010 \u001a\u00020\u0002\u0012\u0006\u0010!\u001a\u00020\u0002\u0012\u0006\u0010\"\u001a\u00020\u0002\u0012\u0006\u0010#\u001a\u00020\u0002\u0012\u0006\u0010$\u001a\u00020\u0002\u0012\u0006\u0010%\u001a\u00020\u0002\u0012\u0006\u0010&\u001a\u00020\u0002\u0012\u0006\u0010'\u001a\u00020\u0002\u0012\u0006\u0010(\u001a\u00020\u0002\u0012\u0006\u0010)\u001a\u00020\u0002\u0012\u0006\u0010*\u001a\u00020\u0002\u0012\u0006\u0010+\u001a\u00020\u0002\u0012\u0006\u0010,\u001a\u00020\u0002\u0012\u0006\u0010-\u001a\u00020\u0002\u0012\u0006\u0010.\u001a\u00020\u0002¢\u0006\u0004\b/\u00100JÀ\u0003\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020\u00022\b\b\u0002\u0010 \u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020\u00022\b\b\u0002\u0010#\u001a\u00020\u00022\b\b\u0002\u0010$\u001a\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00022\b\b\u0002\u0010&\u001a\u00020\u00022\b\b\u0002\u0010'\u001a\u00020\u00022\b\b\u0002\u0010(\u001a\u00020\u00022\b\b\u0002\u0010)\u001a\u00020\u00022\b\b\u0002\u0010*\u001a\u00020\u00022\b\b\u0002\u0010+\u001a\u00020\u00022\b\b\u0002\u0010,\u001a\u00020\u00022\b\b\u0002\u0010-\u001a\u00020\u00022\b\b\u0002\u0010.\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102J#\u00108\u001a\u00020\r*\u0004\u0018\u00010\r2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\r04H\u0000¢\u0006\u0004\b6\u00107J-\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\b?\u0010@J-\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bB\u0010@J-\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bD\u0010@J-\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bF\u0010@J-\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bH\u0010@J-\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bJ\u0010@J-\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bL\u0010@J-\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bN\u0010@J-\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bP\u0010@J-\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u0002092\u0006\u0010=\u001a\u00020<H\u0001¢\u0006\u0004\bR\u0010@J\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020>2\u0006\u0010;\u001a\u000209H\u0001¢\u0006\u0004\bT\u0010UJ\u001a\u0010W\u001a\u0002092\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\bW\u0010XJ\u000f\u0010Z\u001a\u00020YH\u0016¢\u0006\u0004\bZ\u0010[R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\\\u001a\u0004\b]\u0010^R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\\\u001a\u0004\b_\u0010^R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\\\u001a\u0004\b`\u0010^R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\\\u001a\u0004\ba\u0010^R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\\\u001a\u0004\bb\u0010^R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\\\u001a\u0004\bc\u0010^R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\\\u001a\u0004\bd\u0010^R\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010\\\u001a\u0004\be\u0010^R\u001d\u0010\u000b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010\\\u001a\u0004\bf\u0010^R\u001d\u0010\f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\f\u0010\\\u001a\u0004\bg\u0010^R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010h\u001a\u0004\bi\u0010jR\u001d\u0010\u000f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000f\u0010\\\u001a\u0004\bk\u0010^R\u001d\u0010\u0010\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0010\u0010\\\u001a\u0004\bl\u0010^R\u001d\u0010\u0011\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0011\u0010\\\u001a\u0004\bm\u0010^R\u001d\u0010\u0012\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0012\u0010\\\u001a\u0004\bn\u0010^R\u001d\u0010\u0013\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0013\u0010\\\u001a\u0004\bo\u0010^R\u001d\u0010\u0014\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0014\u0010\\\u001a\u0004\bp\u0010^R\u001d\u0010\u0015\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0015\u0010\\\u001a\u0004\bq\u0010^R\u001d\u0010\u0016\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0016\u0010\\\u001a\u0004\br\u0010^R\u001d\u0010\u0017\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0017\u0010\\\u001a\u0004\bs\u0010^R\u001d\u0010\u0018\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0018\u0010\\\u001a\u0004\bt\u0010^R\u001d\u0010\u0019\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0019\u0010\\\u001a\u0004\bu\u0010^R\u001d\u0010\u001a\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001a\u0010\\\u001a\u0004\bv\u0010^R\u001d\u0010\u001b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001b\u0010\\\u001a\u0004\bw\u0010^R\u001d\u0010\u001c\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001c\u0010\\\u001a\u0004\bx\u0010^R\u001d\u0010\u001d\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001d\u0010\\\u001a\u0004\by\u0010^R\u001d\u0010\u001e\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001e\u0010\\\u001a\u0004\bz\u0010^R\u001d\u0010\u001f\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001f\u0010\\\u001a\u0004\b{\u0010^R\u001d\u0010 \u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b \u0010\\\u001a\u0004\b|\u0010^R\u001d\u0010!\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b!\u0010\\\u001a\u0004\b}\u0010^R\u001d\u0010\"\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\"\u0010\\\u001a\u0004\b~\u0010^R\u001d\u0010#\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b#\u0010\\\u001a\u0004\b\u007f\u0010^R\u001e\u0010$\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b$\u0010\\\u001a\u0005\b\u0080\u0001\u0010^R\u001e\u0010%\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b%\u0010\\\u001a\u0005\b\u0081\u0001\u0010^R\u001e\u0010&\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b&\u0010\\\u001a\u0005\b\u0082\u0001\u0010^R\u001e\u0010'\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b'\u0010\\\u001a\u0005\b\u0083\u0001\u0010^R\u001e\u0010(\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b(\u0010\\\u001a\u0005\b\u0084\u0001\u0010^R\u001e\u0010)\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b)\u0010\\\u001a\u0005\b\u0085\u0001\u0010^R\u001e\u0010*\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b*\u0010\\\u001a\u0005\b\u0086\u0001\u0010^R\u001e\u0010+\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b+\u0010\\\u001a\u0005\b\u0087\u0001\u0010^R\u001e\u0010,\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b,\u0010\\\u001a\u0005\b\u0088\u0001\u0010^R\u001e\u0010-\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b-\u0010\\\u001a\u0005\b\u0089\u0001\u0010^R\u001e\u0010.\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\r\n\u0004\b.\u0010\\\u001a\u0005\b\u008a\u0001\u0010^R\u0017\u0010\u008d\u0001\u001a\u00020\r8AX\u0080\u0004¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u008f\u0001²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0001\u001a\u0002098\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/TextFieldColors;", "", "Landroidx/compose/ui/graphics/Color;", "focusedTextColor", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "textSelectionColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "<init>", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "copy-ejIjP34", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/TextFieldColors;", "copy", "Lkotlin/Function0;", "block", "takeOrElse$material3_release", "(Landroidx/compose/foundation/text/selection/TextSelectionColors;Lr7/a;)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "takeOrElse", "", "enabled", "isError", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/runtime/State;", "leadingIconColor$material3_release", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "leadingIconColor", "trailingIconColor$material3_release", "trailingIconColor", "indicatorColor$material3_release", "indicatorColor", "containerColor$material3_release", "containerColor", "placeholderColor$material3_release", "placeholderColor", "labelColor$material3_release", "labelColor", "textColor$material3_release", "textColor", "supportingTextColor$material3_release", "supportingTextColor", "prefixColor$material3_release", "prefixColor", "suffixColor$material3_release", "suffixColor", "cursorColor$material3_release", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getFocusedTextColor-0d7_KjU", "()J", "getUnfocusedTextColor-0d7_KjU", "getDisabledTextColor-0d7_KjU", "getErrorTextColor-0d7_KjU", "getFocusedContainerColor-0d7_KjU", "getUnfocusedContainerColor-0d7_KjU", "getDisabledContainerColor-0d7_KjU", "getErrorContainerColor-0d7_KjU", "getCursorColor-0d7_KjU", "getErrorCursorColor-0d7_KjU", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "getTextSelectionColors", "()Landroidx/compose/foundation/text/selection/TextSelectionColors;", "getFocusedIndicatorColor-0d7_KjU", "getUnfocusedIndicatorColor-0d7_KjU", "getDisabledIndicatorColor-0d7_KjU", "getErrorIndicatorColor-0d7_KjU", "getFocusedLeadingIconColor-0d7_KjU", "getUnfocusedLeadingIconColor-0d7_KjU", "getDisabledLeadingIconColor-0d7_KjU", "getErrorLeadingIconColor-0d7_KjU", "getFocusedTrailingIconColor-0d7_KjU", "getUnfocusedTrailingIconColor-0d7_KjU", "getDisabledTrailingIconColor-0d7_KjU", "getErrorTrailingIconColor-0d7_KjU", "getFocusedLabelColor-0d7_KjU", "getUnfocusedLabelColor-0d7_KjU", "getDisabledLabelColor-0d7_KjU", "getErrorLabelColor-0d7_KjU", "getFocusedPlaceholderColor-0d7_KjU", "getUnfocusedPlaceholderColor-0d7_KjU", "getDisabledPlaceholderColor-0d7_KjU", "getErrorPlaceholderColor-0d7_KjU", "getFocusedSupportingTextColor-0d7_KjU", "getUnfocusedSupportingTextColor-0d7_KjU", "getDisabledSupportingTextColor-0d7_KjU", "getErrorSupportingTextColor-0d7_KjU", "getFocusedPrefixColor-0d7_KjU", "getUnfocusedPrefixColor-0d7_KjU", "getDisabledPrefixColor-0d7_KjU", "getErrorPrefixColor-0d7_KjU", "getFocusedSuffixColor-0d7_KjU", "getUnfocusedSuffixColor-0d7_KjU", "getDisabledSuffixColor-0d7_KjU", "getErrorSuffixColor-0d7_KjU", "getSelectionColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "selectionColors", "focused", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldColors {
    public static final int $stable = 0;
    private final long cursorColor;
    private final long disabledContainerColor;
    private final long disabledIndicatorColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconColor;
    private final long disabledPlaceholderColor;
    private final long disabledPrefixColor;
    private final long disabledSuffixColor;
    private final long disabledSupportingTextColor;
    private final long disabledTextColor;
    private final long disabledTrailingIconColor;
    private final long errorContainerColor;
    private final long errorCursorColor;
    private final long errorIndicatorColor;
    private final long errorLabelColor;
    private final long errorLeadingIconColor;
    private final long errorPlaceholderColor;
    private final long errorPrefixColor;
    private final long errorSuffixColor;
    private final long errorSupportingTextColor;
    private final long errorTextColor;
    private final long errorTrailingIconColor;
    private final long focusedContainerColor;
    private final long focusedIndicatorColor;
    private final long focusedLabelColor;
    private final long focusedLeadingIconColor;
    private final long focusedPlaceholderColor;
    private final long focusedPrefixColor;
    private final long focusedSuffixColor;
    private final long focusedSupportingTextColor;
    private final long focusedTextColor;
    private final long focusedTrailingIconColor;
    private final TextSelectionColors textSelectionColors;
    private final long unfocusedContainerColor;
    private final long unfocusedIndicatorColor;
    private final long unfocusedLabelColor;
    private final long unfocusedLeadingIconColor;
    private final long unfocusedPlaceholderColor;
    private final long unfocusedPrefixColor;
    private final long unfocusedSuffixColor;
    private final long unfocusedSupportingTextColor;
    private final long unfocusedTextColor;
    private final long unfocusedTrailingIconColor;

    public /* synthetic */ TextFieldColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, long j50, long j51, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, textSelectionColors, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42, j43, j44, j45, j46, j47, j48, j49, j50, j51);
    }

    private static final boolean containerColor$lambda$45(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: renamed from: copy-ejIjP34$default, reason: not valid java name */
    public static /* synthetic */ TextFieldColors m2069copyejIjP34$default(TextFieldColors textFieldColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, long j50, long j51, int i10, int i11, Object obj) {
        long j52;
        long j53;
        long j54 = (i10 & 1) != 0 ? textFieldColors.focusedTextColor : j10;
        long j55 = (i10 & 2) != 0 ? textFieldColors.unfocusedTextColor : j11;
        long j56 = (i10 & 4) != 0 ? textFieldColors.disabledTextColor : j12;
        long j57 = (i10 & 8) != 0 ? textFieldColors.errorTextColor : j13;
        long j58 = (i10 & 16) != 0 ? textFieldColors.focusedContainerColor : j14;
        long j59 = (i10 & 32) != 0 ? textFieldColors.unfocusedContainerColor : j15;
        if ((i10 & 64) != 0) {
            j52 = j54;
            j53 = textFieldColors.disabledContainerColor;
        } else {
            j52 = j54;
            j53 = j16;
        }
        return textFieldColors.m2070copyejIjP34(j52, j55, j56, j57, j58, j59, j53, (i10 & 128) != 0 ? textFieldColors.errorContainerColor : j17, (i10 & 256) != 0 ? textFieldColors.cursorColor : j18, (i10 & 512) != 0 ? textFieldColors.errorCursorColor : j19, (i10 & 1024) != 0 ? textFieldColors.textSelectionColors : textSelectionColors, (i10 & 2048) != 0 ? textFieldColors.focusedIndicatorColor : j20, (i10 & 4096) != 0 ? textFieldColors.unfocusedIndicatorColor : j21, (i10 & 8192) != 0 ? textFieldColors.disabledIndicatorColor : j22, (i10 & 16384) != 0 ? textFieldColors.errorIndicatorColor : j23, (i10 & 32768) != 0 ? textFieldColors.focusedLeadingIconColor : j24, (i10 & 65536) != 0 ? textFieldColors.unfocusedLeadingIconColor : j25, (i10 & 131072) != 0 ? textFieldColors.disabledLeadingIconColor : j26, (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? textFieldColors.errorLeadingIconColor : j27, (i10 & 524288) != 0 ? textFieldColors.focusedTrailingIconColor : j28, (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? textFieldColors.unfocusedTrailingIconColor : j29, (i10 & 2097152) != 0 ? textFieldColors.disabledTrailingIconColor : j30, (i10 & 4194304) != 0 ? textFieldColors.errorTrailingIconColor : j31, (i10 & 8388608) != 0 ? textFieldColors.focusedLabelColor : j32, (i10 & 16777216) != 0 ? textFieldColors.unfocusedLabelColor : j33, (i10 & 33554432) != 0 ? textFieldColors.disabledLabelColor : j34, (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? textFieldColors.errorLabelColor : j35, (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? textFieldColors.focusedPlaceholderColor : j36, (i10 & 268435456) != 0 ? textFieldColors.unfocusedPlaceholderColor : j37, (i10 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? textFieldColors.disabledPlaceholderColor : j38, (i10 & 1073741824) != 0 ? textFieldColors.errorPlaceholderColor : j39, (i10 & Integer.MIN_VALUE) != 0 ? textFieldColors.focusedSupportingTextColor : j40, (i11 & 1) != 0 ? textFieldColors.unfocusedSupportingTextColor : j41, (i11 & 2) != 0 ? textFieldColors.disabledSupportingTextColor : j42, (i11 & 4) != 0 ? textFieldColors.errorSupportingTextColor : j43, (i11 & 8) != 0 ? textFieldColors.focusedPrefixColor : j44, (i11 & 16) != 0 ? textFieldColors.unfocusedPrefixColor : j45, (i11 & 32) != 0 ? textFieldColors.disabledPrefixColor : j46, (i11 & 64) != 0 ? textFieldColors.errorPrefixColor : j47, (i11 & 128) != 0 ? textFieldColors.focusedSuffixColor : j48, (i11 & 256) != 0 ? textFieldColors.unfocusedSuffixColor : j49, (i11 & 512) != 0 ? textFieldColors.disabledSuffixColor : j50, (i11 & 1024) != 0 ? textFieldColors.errorSuffixColor : j51);
    }

    private static final boolean indicatorColor$lambda$44(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean labelColor$lambda$47(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean leadingIconColor$lambda$42(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean placeholderColor$lambda$46(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean prefixColor$lambda$50(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean suffixColor$lambda$51(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean supportingTextColor$lambda$49(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean textColor$lambda$48(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean trailingIconColor$lambda$43(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public final State<Color> containerColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(-1921164569);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1921164569, i10, -1, "androidx.compose.material3.TextFieldColors.containerColor (TextFieldDefaults.kt:2108)");
        }
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(!z ? this.disabledContainerColor : z5 ? this.errorContainerColor : containerColor$lambda$45(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedContainerColor : this.unfocusedContainerColor, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    /* JADX INFO: renamed from: copy-ejIjP34, reason: not valid java name */
    public final TextFieldColors m2070copyejIjP34(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, TextSelectionColors textSelectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor) {
        Color.Companion companion = Color.INSTANCE;
        return new TextFieldColors(focusedTextColor != companion.m3508getUnspecified0d7_KjU() ? focusedTextColor : this.focusedTextColor, unfocusedTextColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedTextColor : this.unfocusedTextColor, disabledTextColor != companion.m3508getUnspecified0d7_KjU() ? disabledTextColor : this.disabledTextColor, errorTextColor != companion.m3508getUnspecified0d7_KjU() ? errorTextColor : this.errorTextColor, focusedContainerColor != companion.m3508getUnspecified0d7_KjU() ? focusedContainerColor : this.focusedContainerColor, unfocusedContainerColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedContainerColor : this.unfocusedContainerColor, disabledContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledContainerColor : this.disabledContainerColor, errorContainerColor != companion.m3508getUnspecified0d7_KjU() ? errorContainerColor : this.errorContainerColor, cursorColor != companion.m3508getUnspecified0d7_KjU() ? cursorColor : this.cursorColor, errorCursorColor != companion.m3508getUnspecified0d7_KjU() ? errorCursorColor : this.errorCursorColor, takeOrElse$material3_release(textSelectionColors, new TextFieldColors$copy$11(this)), focusedIndicatorColor != companion.m3508getUnspecified0d7_KjU() ? focusedIndicatorColor : this.focusedIndicatorColor, unfocusedIndicatorColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedIndicatorColor : this.unfocusedIndicatorColor, disabledIndicatorColor != companion.m3508getUnspecified0d7_KjU() ? disabledIndicatorColor : this.disabledIndicatorColor, errorIndicatorColor != companion.m3508getUnspecified0d7_KjU() ? errorIndicatorColor : this.errorIndicatorColor, focusedLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? focusedLeadingIconColor : this.focusedLeadingIconColor, unfocusedLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedLeadingIconColor : this.unfocusedLeadingIconColor, disabledLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledLeadingIconColor : this.disabledLeadingIconColor, errorLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? errorLeadingIconColor : this.errorLeadingIconColor, focusedTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? focusedTrailingIconColor : this.focusedTrailingIconColor, unfocusedTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedTrailingIconColor : this.unfocusedTrailingIconColor, disabledTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledTrailingIconColor : this.disabledTrailingIconColor, errorTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? errorTrailingIconColor : this.errorTrailingIconColor, focusedLabelColor != companion.m3508getUnspecified0d7_KjU() ? focusedLabelColor : this.focusedLabelColor, unfocusedLabelColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedLabelColor : this.unfocusedLabelColor, disabledLabelColor != companion.m3508getUnspecified0d7_KjU() ? disabledLabelColor : this.disabledLabelColor, errorLabelColor != companion.m3508getUnspecified0d7_KjU() ? errorLabelColor : this.errorLabelColor, focusedPlaceholderColor != companion.m3508getUnspecified0d7_KjU() ? focusedPlaceholderColor : this.focusedPlaceholderColor, unfocusedPlaceholderColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedPlaceholderColor : this.unfocusedPlaceholderColor, disabledPlaceholderColor != companion.m3508getUnspecified0d7_KjU() ? disabledPlaceholderColor : this.disabledPlaceholderColor, errorPlaceholderColor != companion.m3508getUnspecified0d7_KjU() ? errorPlaceholderColor : this.errorPlaceholderColor, focusedSupportingTextColor != companion.m3508getUnspecified0d7_KjU() ? focusedSupportingTextColor : this.focusedSupportingTextColor, unfocusedSupportingTextColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedSupportingTextColor : this.unfocusedSupportingTextColor, disabledSupportingTextColor != companion.m3508getUnspecified0d7_KjU() ? disabledSupportingTextColor : this.disabledSupportingTextColor, errorSupportingTextColor != companion.m3508getUnspecified0d7_KjU() ? errorSupportingTextColor : this.errorSupportingTextColor, focusedPrefixColor != companion.m3508getUnspecified0d7_KjU() ? focusedPrefixColor : this.focusedPrefixColor, unfocusedPrefixColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedPrefixColor : this.unfocusedPrefixColor, disabledPrefixColor != companion.m3508getUnspecified0d7_KjU() ? disabledPrefixColor : this.disabledPrefixColor, errorPrefixColor != companion.m3508getUnspecified0d7_KjU() ? errorPrefixColor : this.errorPrefixColor, focusedSuffixColor != companion.m3508getUnspecified0d7_KjU() ? focusedSuffixColor : this.focusedSuffixColor, unfocusedSuffixColor != companion.m3508getUnspecified0d7_KjU() ? unfocusedSuffixColor : this.unfocusedSuffixColor, disabledSuffixColor != companion.m3508getUnspecified0d7_KjU() ? disabledSuffixColor : this.disabledSuffixColor, errorSuffixColor != companion.m3508getUnspecified0d7_KjU() ? errorSuffixColor : this.errorSuffixColor, null);
    }

    public final State<Color> cursorColor$material3_release(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(-1885422187);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1885422187, i10, -1, "androidx.compose.material3.TextFieldColors.cursorColor (TextFieldDefaults.kt:2269)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? this.errorCursorColor : this.cursorColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof TextFieldColors)) {
            return false;
        }
        TextFieldColors textFieldColors = (TextFieldColors) other;
        return Color.m3473equalsimpl0(this.focusedTextColor, textFieldColors.focusedTextColor) && Color.m3473equalsimpl0(this.unfocusedTextColor, textFieldColors.unfocusedTextColor) && Color.m3473equalsimpl0(this.disabledTextColor, textFieldColors.disabledTextColor) && Color.m3473equalsimpl0(this.errorTextColor, textFieldColors.errorTextColor) && Color.m3473equalsimpl0(this.focusedContainerColor, textFieldColors.focusedContainerColor) && Color.m3473equalsimpl0(this.unfocusedContainerColor, textFieldColors.unfocusedContainerColor) && Color.m3473equalsimpl0(this.disabledContainerColor, textFieldColors.disabledContainerColor) && Color.m3473equalsimpl0(this.errorContainerColor, textFieldColors.errorContainerColor) && Color.m3473equalsimpl0(this.cursorColor, textFieldColors.cursorColor) && Color.m3473equalsimpl0(this.errorCursorColor, textFieldColors.errorCursorColor) && p.a(this.textSelectionColors, textFieldColors.textSelectionColors) && Color.m3473equalsimpl0(this.focusedIndicatorColor, textFieldColors.focusedIndicatorColor) && Color.m3473equalsimpl0(this.unfocusedIndicatorColor, textFieldColors.unfocusedIndicatorColor) && Color.m3473equalsimpl0(this.disabledIndicatorColor, textFieldColors.disabledIndicatorColor) && Color.m3473equalsimpl0(this.errorIndicatorColor, textFieldColors.errorIndicatorColor) && Color.m3473equalsimpl0(this.focusedLeadingIconColor, textFieldColors.focusedLeadingIconColor) && Color.m3473equalsimpl0(this.unfocusedLeadingIconColor, textFieldColors.unfocusedLeadingIconColor) && Color.m3473equalsimpl0(this.disabledLeadingIconColor, textFieldColors.disabledLeadingIconColor) && Color.m3473equalsimpl0(this.errorLeadingIconColor, textFieldColors.errorLeadingIconColor) && Color.m3473equalsimpl0(this.focusedTrailingIconColor, textFieldColors.focusedTrailingIconColor) && Color.m3473equalsimpl0(this.unfocusedTrailingIconColor, textFieldColors.unfocusedTrailingIconColor) && Color.m3473equalsimpl0(this.disabledTrailingIconColor, textFieldColors.disabledTrailingIconColor) && Color.m3473equalsimpl0(this.errorTrailingIconColor, textFieldColors.errorTrailingIconColor) && Color.m3473equalsimpl0(this.focusedLabelColor, textFieldColors.focusedLabelColor) && Color.m3473equalsimpl0(this.unfocusedLabelColor, textFieldColors.unfocusedLabelColor) && Color.m3473equalsimpl0(this.disabledLabelColor, textFieldColors.disabledLabelColor) && Color.m3473equalsimpl0(this.errorLabelColor, textFieldColors.errorLabelColor) && Color.m3473equalsimpl0(this.focusedPlaceholderColor, textFieldColors.focusedPlaceholderColor) && Color.m3473equalsimpl0(this.unfocusedPlaceholderColor, textFieldColors.unfocusedPlaceholderColor) && Color.m3473equalsimpl0(this.disabledPlaceholderColor, textFieldColors.disabledPlaceholderColor) && Color.m3473equalsimpl0(this.errorPlaceholderColor, textFieldColors.errorPlaceholderColor) && Color.m3473equalsimpl0(this.focusedSupportingTextColor, textFieldColors.focusedSupportingTextColor) && Color.m3473equalsimpl0(this.unfocusedSupportingTextColor, textFieldColors.unfocusedSupportingTextColor) && Color.m3473equalsimpl0(this.disabledSupportingTextColor, textFieldColors.disabledSupportingTextColor) && Color.m3473equalsimpl0(this.errorSupportingTextColor, textFieldColors.errorSupportingTextColor) && Color.m3473equalsimpl0(this.focusedPrefixColor, textFieldColors.focusedPrefixColor) && Color.m3473equalsimpl0(this.unfocusedPrefixColor, textFieldColors.unfocusedPrefixColor) && Color.m3473equalsimpl0(this.disabledPrefixColor, textFieldColors.disabledPrefixColor) && Color.m3473equalsimpl0(this.errorPrefixColor, textFieldColors.errorPrefixColor) && Color.m3473equalsimpl0(this.focusedSuffixColor, textFieldColors.focusedSuffixColor) && Color.m3473equalsimpl0(this.unfocusedSuffixColor, textFieldColors.unfocusedSuffixColor) && Color.m3473equalsimpl0(this.disabledSuffixColor, textFieldColors.disabledSuffixColor) && Color.m3473equalsimpl0(this.errorSuffixColor, textFieldColors.errorSuffixColor);
    }

    /* JADX INFO: renamed from: getCursorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCursorColor() {
        return this.cursorColor;
    }

    /* JADX INFO: renamed from: getDisabledContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledContainerColor() {
        return this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIndicatorColor() {
        return this.disabledIndicatorColor;
    }

    /* JADX INFO: renamed from: getDisabledLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLabelColor() {
        return this.disabledLabelColor;
    }

    /* JADX INFO: renamed from: getDisabledLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLeadingIconColor() {
        return this.disabledLeadingIconColor;
    }

    /* JADX INFO: renamed from: getDisabledPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledPlaceholderColor() {
        return this.disabledPlaceholderColor;
    }

    /* JADX INFO: renamed from: getDisabledPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledPrefixColor() {
        return this.disabledPrefixColor;
    }

    /* JADX INFO: renamed from: getDisabledSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSuffixColor() {
        return this.disabledSuffixColor;
    }

    /* JADX INFO: renamed from: getDisabledSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSupportingTextColor() {
        return this.disabledSupportingTextColor;
    }

    /* JADX INFO: renamed from: getDisabledTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTextColor() {
        return this.disabledTextColor;
    }

    /* JADX INFO: renamed from: getDisabledTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTrailingIconColor() {
        return this.disabledTrailingIconColor;
    }

    /* JADX INFO: renamed from: getErrorContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorContainerColor() {
        return this.errorContainerColor;
    }

    /* JADX INFO: renamed from: getErrorCursorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorCursorColor() {
        return this.errorCursorColor;
    }

    /* JADX INFO: renamed from: getErrorIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorIndicatorColor() {
        return this.errorIndicatorColor;
    }

    /* JADX INFO: renamed from: getErrorLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorLabelColor() {
        return this.errorLabelColor;
    }

    /* JADX INFO: renamed from: getErrorLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorLeadingIconColor() {
        return this.errorLeadingIconColor;
    }

    /* JADX INFO: renamed from: getErrorPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorPlaceholderColor() {
        return this.errorPlaceholderColor;
    }

    /* JADX INFO: renamed from: getErrorPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorPrefixColor() {
        return this.errorPrefixColor;
    }

    /* JADX INFO: renamed from: getErrorSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorSuffixColor() {
        return this.errorSuffixColor;
    }

    /* JADX INFO: renamed from: getErrorSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorSupportingTextColor() {
        return this.errorSupportingTextColor;
    }

    /* JADX INFO: renamed from: getErrorTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorTextColor() {
        return this.errorTextColor;
    }

    /* JADX INFO: renamed from: getErrorTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorTrailingIconColor() {
        return this.errorTrailingIconColor;
    }

    /* JADX INFO: renamed from: getFocusedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedContainerColor() {
        return this.focusedContainerColor;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedIndicatorColor() {
        return this.focusedIndicatorColor;
    }

    /* JADX INFO: renamed from: getFocusedLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedLabelColor() {
        return this.focusedLabelColor;
    }

    /* JADX INFO: renamed from: getFocusedLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedLeadingIconColor() {
        return this.focusedLeadingIconColor;
    }

    /* JADX INFO: renamed from: getFocusedPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedPlaceholderColor() {
        return this.focusedPlaceholderColor;
    }

    /* JADX INFO: renamed from: getFocusedPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedPrefixColor() {
        return this.focusedPrefixColor;
    }

    /* JADX INFO: renamed from: getFocusedSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedSuffixColor() {
        return this.focusedSuffixColor;
    }

    /* JADX INFO: renamed from: getFocusedSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedSupportingTextColor() {
        return this.focusedSupportingTextColor;
    }

    /* JADX INFO: renamed from: getFocusedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedTextColor() {
        return this.focusedTextColor;
    }

    /* JADX INFO: renamed from: getFocusedTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedTrailingIconColor() {
        return this.focusedTrailingIconColor;
    }

    public final TextSelectionColors getSelectionColors(Composer composer, int i10) {
        composer.startReplaceableGroup(997785083);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(997785083, i10, -1, "androidx.compose.material3.TextFieldColors.<get-selectionColors> (TextFieldDefaults.kt:2277)");
        }
        TextSelectionColors textSelectionColors = this.textSelectionColors;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textSelectionColors;
    }

    public final TextSelectionColors getTextSelectionColors() {
        return this.textSelectionColors;
    }

    /* JADX INFO: renamed from: getUnfocusedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedContainerColor() {
        return this.unfocusedContainerColor;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedIndicatorColor() {
        return this.unfocusedIndicatorColor;
    }

    /* JADX INFO: renamed from: getUnfocusedLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedLabelColor() {
        return this.unfocusedLabelColor;
    }

    /* JADX INFO: renamed from: getUnfocusedLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedLeadingIconColor() {
        return this.unfocusedLeadingIconColor;
    }

    /* JADX INFO: renamed from: getUnfocusedPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedPlaceholderColor() {
        return this.unfocusedPlaceholderColor;
    }

    /* JADX INFO: renamed from: getUnfocusedPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedPrefixColor() {
        return this.unfocusedPrefixColor;
    }

    /* JADX INFO: renamed from: getUnfocusedSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedSuffixColor() {
        return this.unfocusedSuffixColor;
    }

    /* JADX INFO: renamed from: getUnfocusedSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedSupportingTextColor() {
        return this.unfocusedSupportingTextColor;
    }

    /* JADX INFO: renamed from: getUnfocusedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedTextColor() {
        return this.unfocusedTextColor;
    }

    /* JADX INFO: renamed from: getUnfocusedTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedTrailingIconColor() {
        return this.unfocusedTrailingIconColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.errorSuffixColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a((this.textSelectionColors.hashCode() + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.focusedTextColor) * 31, 31, this.unfocusedTextColor), 31, this.disabledTextColor), 31, this.errorTextColor), 31, this.focusedContainerColor), 31, this.unfocusedContainerColor), 31, this.disabledContainerColor), 31, this.errorContainerColor), 31, this.cursorColor), 31, this.errorCursorColor)) * 31, 31, this.focusedIndicatorColor), 31, this.unfocusedIndicatorColor), 31, this.disabledIndicatorColor), 31, this.errorIndicatorColor), 31, this.focusedLeadingIconColor), 31, this.unfocusedLeadingIconColor), 31, this.disabledLeadingIconColor), 31, this.errorLeadingIconColor), 31, this.focusedTrailingIconColor), 31, this.unfocusedTrailingIconColor), 31, this.disabledTrailingIconColor), 31, this.errorTrailingIconColor), 31, this.focusedLabelColor), 31, this.unfocusedLabelColor), 31, this.disabledLabelColor), 31, this.errorLabelColor), 31, this.focusedPlaceholderColor), 31, this.unfocusedPlaceholderColor), 31, this.disabledPlaceholderColor), 31, this.errorPlaceholderColor), 31, this.focusedSupportingTextColor), 31, this.unfocusedSupportingTextColor), 31, this.disabledSupportingTextColor), 31, this.errorSupportingTextColor), 31, this.focusedPrefixColor), 31, this.unfocusedPrefixColor), 31, this.disabledPrefixColor), 31, this.errorPrefixColor), 31, this.focusedSuffixColor), 31, this.unfocusedSuffixColor), 31, this.disabledSuffixColor);
    }

    public final State<Color> indicatorColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        Composer composer2;
        State<Color> stateRememberUpdatedState;
        composer.startReplaceableGroup(-1877482635);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1877482635, i10, -1, "androidx.compose.material3.TextFieldColors.indicatorColor (TextFieldDefaults.kt:2079)");
        }
        long j10 = !z ? this.disabledIndicatorColor : z5 ? this.errorIndicatorColor : indicatorColor$lambda$44(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedIndicatorColor : this.unfocusedIndicatorColor;
        if (z) {
            composer.startReplaceableGroup(715804770);
            composer2 = composer;
            stateRememberUpdatedState = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(j10, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2, 48, 12);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composer;
            composer2.startReplaceableGroup(715804875);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(j10), composer2, 0);
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> labelColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(1167161306);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1167161306, i10, -1, "androidx.compose.material3.TextFieldColors.labelColor (TextFieldDefaults.kt:2158)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledLabelColor : z5 ? this.errorLabelColor : labelColor$lambda$47(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedLabelColor : this.unfocusedLabelColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> leadingIconColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(925127045);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(925127045, i10, -1, "androidx.compose.material3.TextFieldColors.leadingIconColor (TextFieldDefaults.kt:2027)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledLeadingIconColor : z5 ? this.errorLeadingIconColor : leadingIconColor$lambda$42(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedLeadingIconColor : this.unfocusedLeadingIconColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> placeholderColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(653850713);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(653850713, i10, -1, "androidx.compose.material3.TextFieldColors.placeholderColor (TextFieldDefaults.kt:2133)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledPlaceholderColor : z5 ? this.errorPlaceholderColor : placeholderColor$lambda$46(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedPlaceholderColor : this.unfocusedPlaceholderColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> prefixColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(129569364);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(129569364, i10, -1, "androidx.compose.material3.TextFieldColors.prefixColor (TextFieldDefaults.kt:2226)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledPrefixColor : z5 ? this.errorPrefixColor : prefixColor$lambda$50(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedPrefixColor : this.unfocusedPrefixColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> suffixColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(1575329427);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1575329427, i10, -1, "androidx.compose.material3.TextFieldColors.suffixColor (TextFieldDefaults.kt:2251)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledSuffixColor : z5 ? this.errorSuffixColor : suffixColor$lambda$51(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedSuffixColor : this.unfocusedSuffixColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> supportingTextColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(1464709698);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1464709698, i10, -1, "androidx.compose.material3.TextFieldColors.supportingTextColor (TextFieldDefaults.kt:2200)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledSupportingTextColor : z5 ? this.errorSupportingTextColor : supportingTextColor$lambda$49(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedSupportingTextColor : this.unfocusedSupportingTextColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final TextSelectionColors takeOrElse$material3_release(TextSelectionColors textSelectionColors, r7.a<TextSelectionColors> aVar) {
        return textSelectionColors == null ? (TextSelectionColors) aVar.invoke() : textSelectionColors;
    }

    public final State<Color> textColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(68412911);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(68412911, i10, -1, "androidx.compose.material3.TextFieldColors.textColor (TextFieldDefaults.kt:2183)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledTextColor : z5 ? this.errorTextColor : textColor$lambda$48(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedTextColor : this.unfocusedTextColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public final State<Color> trailingIconColor$material3_release(boolean z, boolean z5, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(-109504137);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-109504137, i10, -1, "androidx.compose.material3.TextFieldColors.trailingIconColor (TextFieldDefaults.kt:2053)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(!z ? this.disabledTrailingIconColor : z5 ? this.errorTrailingIconColor : trailingIconColor$lambda$43(FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i10 >> 6) & 14)) ? this.focusedTrailingIconColor : this.unfocusedTrailingIconColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    private TextFieldColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, long j50, long j51) {
        this.focusedTextColor = j10;
        this.unfocusedTextColor = j11;
        this.disabledTextColor = j12;
        this.errorTextColor = j13;
        this.focusedContainerColor = j14;
        this.unfocusedContainerColor = j15;
        this.disabledContainerColor = j16;
        this.errorContainerColor = j17;
        this.cursorColor = j18;
        this.errorCursorColor = j19;
        this.textSelectionColors = textSelectionColors;
        this.focusedIndicatorColor = j20;
        this.unfocusedIndicatorColor = j21;
        this.disabledIndicatorColor = j22;
        this.errorIndicatorColor = j23;
        this.focusedLeadingIconColor = j24;
        this.unfocusedLeadingIconColor = j25;
        this.disabledLeadingIconColor = j26;
        this.errorLeadingIconColor = j27;
        this.focusedTrailingIconColor = j28;
        this.unfocusedTrailingIconColor = j29;
        this.disabledTrailingIconColor = j30;
        this.errorTrailingIconColor = j31;
        this.focusedLabelColor = j32;
        this.unfocusedLabelColor = j33;
        this.disabledLabelColor = j34;
        this.errorLabelColor = j35;
        this.focusedPlaceholderColor = j36;
        this.unfocusedPlaceholderColor = j37;
        this.disabledPlaceholderColor = j38;
        this.errorPlaceholderColor = j39;
        this.focusedSupportingTextColor = j40;
        this.unfocusedSupportingTextColor = j41;
        this.disabledSupportingTextColor = j42;
        this.errorSupportingTextColor = j43;
        this.focusedPrefixColor = j44;
        this.unfocusedPrefixColor = j45;
        this.disabledPrefixColor = j46;
        this.errorPrefixColor = j47;
        this.focusedSuffixColor = j48;
        this.unfocusedSuffixColor = j49;
        this.disabledSuffixColor = j50;
        this.errorSuffixColor = j51;
    }
}
