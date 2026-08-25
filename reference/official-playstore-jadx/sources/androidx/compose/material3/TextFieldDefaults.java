package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.WavUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b>\n\u0002\u0018\u0002\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJJ\u0010\u0016\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J8\u0010\u001e\u001a\u00020\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ8\u0010 \u001a\u00020\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001dJ:\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0011H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001dJ\u000f\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010#JÂ\u0003\u0010\n\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020$2\b\b\u0002\u0010)\u001a\u00020$2\b\b\u0002\u0010*\u001a\u00020$2\b\b\u0002\u0010+\u001a\u00020$2\b\b\u0002\u0010,\u001a\u00020$2\b\b\u0002\u0010-\u001a\u00020$2\b\b\u0002\u0010.\u001a\u00020$2\n\b\u0002\u00100\u001a\u0004\u0018\u00010/2\b\b\u0002\u00101\u001a\u00020$2\b\b\u0002\u00102\u001a\u00020$2\b\b\u0002\u00103\u001a\u00020$2\b\b\u0002\u00104\u001a\u00020$2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u00106\u001a\u00020$2\b\b\u0002\u00107\u001a\u00020$2\b\b\u0002\u00108\u001a\u00020$2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$2\b\b\u0002\u0010<\u001a\u00020$2\b\b\u0002\u0010=\u001a\u00020$2\b\b\u0002\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u0010@\u001a\u00020$2\b\b\u0002\u0010A\u001a\u00020$2\b\b\u0002\u0010B\u001a\u00020$2\b\b\u0002\u0010C\u001a\u00020$2\b\b\u0002\u0010D\u001a\u00020$2\b\b\u0002\u0010E\u001a\u00020$2\b\b\u0002\u0010F\u001a\u00020$2\b\b\u0002\u0010G\u001a\u00020$2\b\b\u0002\u0010H\u001a\u00020$2\b\b\u0002\u0010I\u001a\u00020$2\b\b\u0002\u0010J\u001a\u00020$2\b\b\u0002\u0010K\u001a\u00020$2\b\b\u0002\u0010L\u001a\u00020$2\b\b\u0002\u0010M\u001a\u00020$2\b\b\u0002\u0010N\u001a\u00020$2\b\b\u0002\u0010O\u001a\u00020$2\b\b\u0002\u0010P\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0004\bQ\u0010RJ¨\u0002\u0010d\u001a\u00020\r2\u0006\u0010T\u001a\u00020S2\u0011\u0010W\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bV2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0015\b\u0002\u0010[\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010\\\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010]\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010_\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010`\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010b\u001a\u00020\u001b2\u0013\b\u0002\u0010c\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bVH\u0007¢\u0006\u0004\bd\u0010eJ9\u0010f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\bf\u0010\u000fJP\u0010k\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010g\u001a\u00020\u00112\b\b\u0002\u0010h\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\bi\u0010jJ:\u0010m\u001a\u00020\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\bl\u0010\u001dJ:\u0010o\u001a\u00020\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\bn\u0010\u001dJ:\u0010q\u001a\u00020\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\bp\u0010\u001dJ¬\u0003\u0010u\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020$2\b\b\u0002\u0010r\u001a\u00020$2\b\b\u0002\u0010,\u001a\u00020$2\b\b\u0002\u0010-\u001a\u00020$2\b\b\u0002\u0010.\u001a\u00020$2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u00020$2\b\b\u0002\u00102\u001a\u00020$2\b\b\u0002\u00103\u001a\u00020$2\b\b\u0002\u00104\u001a\u00020$2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u00106\u001a\u00020$2\b\b\u0002\u00107\u001a\u00020$2\b\b\u0002\u00108\u001a\u00020$2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$2\b\b\u0002\u0010<\u001a\u00020$2\b\b\u0002\u0010=\u001a\u00020$2\b\b\u0002\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u0010@\u001a\u00020$2\b\b\u0002\u0010A\u001a\u00020$2\b\b\u0002\u0010B\u001a\u00020$2\b\b\u0002\u0010C\u001a\u00020$2\b\b\u0002\u0010D\u001a\u00020$2\b\b\u0002\u0010E\u001a\u00020$2\b\b\u0002\u0010F\u001a\u00020$2\b\b\u0002\u0010G\u001a\u00020$2\b\b\u0002\u0010H\u001a\u00020$2\b\b\u0002\u0010I\u001a\u00020$2\b\b\u0002\u0010J\u001a\u00020$2\b\b\u0002\u0010K\u001a\u00020$2\b\b\u0002\u0010L\u001a\u00020$2\b\b\u0002\u0010M\u001a\u00020$2\b\b\u0002\u0010N\u001a\u00020$2\b\b\u0002\u0010O\u001a\u00020$2\b\b\u0002\u0010P\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0004\bs\u0010tJ¬\u0003\u0010{\u001a\u00020\t2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020$2\b\b\u0002\u0010r\u001a\u00020$2\b\b\u0002\u0010,\u001a\u00020$2\b\b\u0002\u0010-\u001a\u00020$2\b\b\u0002\u0010.\u001a\u00020$2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u0010v\u001a\u00020$2\b\b\u0002\u0010w\u001a\u00020$2\b\b\u0002\u0010x\u001a\u00020$2\b\b\u0002\u0010y\u001a\u00020$2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u00106\u001a\u00020$2\b\b\u0002\u00107\u001a\u00020$2\b\b\u0002\u00108\u001a\u00020$2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$2\b\b\u0002\u0010<\u001a\u00020$2\b\b\u0002\u0010=\u001a\u00020$2\b\b\u0002\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u0010@\u001a\u00020$2\b\b\u0002\u0010A\u001a\u00020$2\b\b\u0002\u0010B\u001a\u00020$2\b\b\u0002\u0010C\u001a\u00020$2\b\b\u0002\u0010D\u001a\u00020$2\b\b\u0002\u0010E\u001a\u00020$2\b\b\u0002\u0010F\u001a\u00020$2\b\b\u0002\u0010G\u001a\u00020$2\b\b\u0002\u0010H\u001a\u00020$2\b\b\u0002\u0010I\u001a\u00020$2\b\b\u0002\u0010J\u001a\u00020$2\b\b\u0002\u0010K\u001a\u00020$2\b\b\u0002\u0010L\u001a\u00020$2\b\b\u0002\u0010M\u001a\u00020$2\b\b\u0002\u0010N\u001a\u00020$2\b\b\u0002\u0010O\u001a\u00020$2\b\b\u0002\u0010P\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0004\bz\u0010tJ¨\u0002\u0010|\u001a\u00020\r2\u0006\u0010T\u001a\u00020S2\u0011\u0010W\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bV2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0015\b\u0002\u0010[\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010\\\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010]\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010_\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010`\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010b\u001a\u00020\u001b2\u0013\b\u0002\u0010c\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bVH\u0007¢\u0006\u0004\b|\u0010eJ\u009e\u0002\u0010}\u001a\u00020\r2\u0006\u0010T\u001a\u00020S2\u0011\u0010W\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bV2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0015\b\u0002\u0010[\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010\\\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010]\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010_\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010`\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010b\u001a\u00020\u001b2\u0013\b\u0002\u0010c\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bVH\u0007¢\u0006\u0004\b}\u0010~Jý\u0002\u0010u\u001a\u00020\t2\b\b\u0002\u0010\u007f\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010r\u001a\u00020$2\b\b\u0002\u0010-\u001a\u00020$2\b\b\u0002\u0010.\u001a\u00020$2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u00020$2\b\b\u0002\u00102\u001a\u00020$2\b\b\u0002\u00103\u001a\u00020$2\b\b\u0002\u00104\u001a\u00020$2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u00106\u001a\u00020$2\b\b\u0002\u00107\u001a\u00020$2\b\b\u0002\u00108\u001a\u00020$2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$2\b\b\u0002\u0010<\u001a\u00020$2\b\b\u0002\u0010=\u001a\u00020$2\b\b\u0002\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u0010@\u001a\u00020$2\t\b\u0002\u0010\u0080\u0001\u001a\u00020$2\b\b\u0002\u0010C\u001a\u00020$2\b\b\u0002\u0010E\u001a\u00020$2\b\b\u0002\u0010F\u001a\u00020$2\b\b\u0002\u0010G\u001a\u00020$2\b\b\u0002\u0010H\u001a\u00020$2\b\b\u0002\u0010I\u001a\u00020$2\b\b\u0002\u0010J\u001a\u00020$2\b\b\u0002\u0010K\u001a\u00020$2\b\b\u0002\u0010L\u001a\u00020$2\b\b\u0002\u0010M\u001a\u00020$2\b\b\u0002\u0010N\u001a\u00020$2\b\b\u0002\u0010O\u001a\u00020$2\b\b\u0002\u0010P\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001Jý\u0002\u0010{\u001a\u00020\t2\b\b\u0002\u0010\u007f\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010r\u001a\u00020$2\b\b\u0002\u0010-\u001a\u00020$2\b\b\u0002\u0010.\u001a\u00020$2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u0010v\u001a\u00020$2\b\b\u0002\u0010w\u001a\u00020$2\b\b\u0002\u0010x\u001a\u00020$2\b\b\u0002\u0010y\u001a\u00020$2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u00106\u001a\u00020$2\b\b\u0002\u00107\u001a\u00020$2\b\b\u0002\u00108\u001a\u00020$2\b\b\u0002\u00109\u001a\u00020$2\b\b\u0002\u0010:\u001a\u00020$2\b\b\u0002\u0010;\u001a\u00020$2\b\b\u0002\u0010<\u001a\u00020$2\b\b\u0002\u0010=\u001a\u00020$2\b\b\u0002\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u0010@\u001a\u00020$2\t\b\u0002\u0010\u0080\u0001\u001a\u00020$2\b\b\u0002\u0010C\u001a\u00020$2\b\b\u0002\u0010E\u001a\u00020$2\b\b\u0002\u0010F\u001a\u00020$2\b\b\u0002\u0010G\u001a\u00020$2\b\b\u0002\u0010H\u001a\u00020$2\b\b\u0002\u0010I\u001a\u00020$2\b\b\u0002\u0010J\u001a\u00020$2\b\b\u0002\u0010K\u001a\u00020$2\b\b\u0002\u0010L\u001a\u00020$2\b\b\u0002\u0010M\u001a\u00020$2\b\b\u0002\u0010N\u001a\u00020$2\b\b\u0002\u0010O\u001a\u00020$2\b\b\u0002\u0010P\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0083\u0001\u0010\u0082\u0001Jû\u0001\u0010|\u001a\u00020\r2\u0006\u0010T\u001a\u00020S2\u0011\u0010W\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bV2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0015\b\u0002\u0010[\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010\\\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010]\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010b\u001a\u00020\u001b2\u0013\b\u0002\u0010c\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bVH\u0007¢\u0006\u0005\b|\u0010\u0084\u0001Jñ\u0001\u0010}\u001a\u00020\r2\u0006\u0010T\u001a\u00020S2\u0011\u0010W\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bV2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0015\b\u0002\u0010[\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010\\\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010]\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010U¢\u0006\u0002\bV2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010b\u001a\u00020\u001b2\u0013\b\u0002\u0010c\u001a\r\u0012\u0004\u0012\u00020\r0U¢\u0006\u0002\bVH\u0007¢\u0006\u0005\b}\u0010\u0085\u0001R\"\u0010\u0086\u0001\u001a\u00020\u00118\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0006\b\u0086\u0001\u0010\u0087\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\"\u0010\u008a\u0001\u001a\u00020\u00118\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0006\b\u008a\u0001\u0010\u0087\u0001\u001a\u0006\b\u008b\u0001\u0010\u0089\u0001R\"\u0010\u008c\u0001\u001a\u00020\u00118\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0006\b\u008c\u0001\u0010\u0087\u0001\u001a\u0006\b\u008d\u0001\u0010\u0089\u0001R\"\u0010\u008e\u0001\u001a\u00020\u00118\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0006\b\u008e\u0001\u0010\u0087\u0001\u001a\u0006\b\u008f\u0001\u0010\u0089\u0001R,\u0010\u0090\u0001\u001a\u00020\u00118\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0017\n\u0006\b\u0090\u0001\u0010\u0087\u0001\u0012\u0005\b\u0092\u0001\u0010\u0003\u001a\u0006\b\u0091\u0001\u0010\u0089\u0001R,\u0010\u0093\u0001\u001a\u00020\u00118\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0017\n\u0006\b\u0093\u0001\u0010\u0087\u0001\u0012\u0005\b\u0095\u0001\u0010\u0003\u001a\u0006\b\u0094\u0001\u0010\u0089\u0001R\u0013\u0010\f\u001a\u00020\u000b8G¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001c\u0010\u009b\u0001\u001a\u00020\t*\u00030\u0098\u00018AX\u0080\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001e\u0010\u009e\u0001\u001a\u00020\u000b8GX\u0087\u0004¢\u0006\u000f\u0012\u0005\b\u009d\u0001\u0010\u0003\u001a\u0006\b\u009c\u0001\u0010\u0097\u0001R\u001e\u0010¡\u0001\u001a\u00020\u000b8GX\u0087\u0004¢\u0006\u000f\u0012\u0005\b \u0001\u0010\u0003\u001a\u0006\b\u009f\u0001\u0010\u0097\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006¢\u0001"}, d2 = {"Landroidx/compose/material3/TextFieldDefaults;", "", "<init>", "()V", "", "enabled", "isError", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/material3/TextFieldColors;", "colors", "Landroidx/compose/ui/graphics/Shape;", "shape", "Lx6/t0;", "ContainerBox", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "indicatorLine", TtmlNode.START, TtmlNode.END, "top", "bottom", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPaddingWithLabel-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "contentPaddingWithLabel", "contentPaddingWithoutLabel-a9UjIt4", "contentPaddingWithoutLabel", "supportingTextPadding-a9UjIt4$material3_release", "supportingTextPadding", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "Landroidx/compose/ui/graphics/Color;", "focusedTextColor", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "selectionColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "", "value", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "singleLine", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", TtmlNode.RUBY_CONTAINER, "DecorationBox", "(Ljava/lang/String;Lr7/p;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "FilledContainerBox", "focusedBorderThickness", "unfocusedBorderThickness", "OutlinedBorderContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "OutlinedBorderContainerBox", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "outlinedTextFieldPadding-a9UjIt4", "outlinedTextFieldPadding", "containerColor", "textFieldColors-M37tBTI", "(JJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "textFieldColors", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "outlinedTextFieldColors-M37tBTI", "outlinedTextFieldColors", "TextFieldDecorationBox", "OutlinedTextFieldDecorationBox", "(Ljava/lang/String;Lr7/p;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "textColor", "placeholderColor", "textFieldColors-eS1Emto", "(JJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIII)Landroidx/compose/material3/TextFieldColors;", "outlinedTextFieldColors-eS1Emto", "(Ljava/lang/String;Lr7/p;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lr7/p;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "MinHeight", "F", "getMinHeight-D9Ej5fM", "()F", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedIndicatorThickness", "getUnfocusedIndicatorThickness-D9Ej5fM", "FocusedIndicatorThickness", "getFocusedIndicatorThickness-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "getUnfocusedBorderThickness-D9Ej5fM$annotations", "FocusedBorderThickness", "getFocusedBorderThickness-D9Ej5fM", "getFocusedBorderThickness-D9Ej5fM$annotations", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "Landroidx/compose/material3/ColorScheme;", "getDefaultTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "defaultTextFieldColors", "getOutlinedShape", "getOutlinedShape$annotations", "outlinedShape", "getFilledShape", "getFilledShape$annotations", "filledShape", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    private static final float FocusedBorderThickness;
    private static final float FocusedIndicatorThickness;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m5678constructorimpl(56);
    private static final float MinWidth = Dp.m5678constructorimpl(280);
    private static final float UnfocusedBorderThickness;
    private static final float UnfocusedIndicatorThickness;

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$ContainerBox$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, int i10, int i11) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
            this.$shape = shape;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.ContainerBox(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$DecorationBox$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05581 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05581(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
            this.$shape = shape;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-435523791, i10, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:496)");
            }
            TextFieldDefaults.INSTANCE.ContainerBox(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, composer, 196608, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$DecorationBox$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(String str, p<? super Composer, ? super Integer, t0> pVar, boolean z, boolean z5, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar9, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$innerTextField = pVar;
            this.$enabled = z;
            this.$singleLine = z5;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = interactionSource;
            this.$isError = z10;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$prefix = pVar6;
            this.$suffix = pVar7;
            this.$supportingText = pVar8;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$contentPadding = paddingValues;
            this.$container = pVar9;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.DecorationBox(this.$value, this.$innerTextField, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$shape, this.$colors, this.$contentPadding, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$FilledContainerBox$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05591 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05591(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, int i10, int i11) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
            this.$shape = shape;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.FilledContainerBox(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$OutlinedTextFieldDecorationBox$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05601 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05601(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1153197597, i10, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1096)");
            }
            OutlinedTextFieldDefaults.INSTANCE.m1744ContainerBoxnbWgWpA(this.$enabled, this.$isError, this.$interactionSource, this.$colors, null, 0.0f, 0.0f, composer, 12582912, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$OutlinedTextFieldDecorationBox$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05612 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05612(String str, p<? super Composer, ? super Integer, t0> pVar, boolean z, boolean z5, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, TextFieldColors textFieldColors, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar9, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$innerTextField = pVar;
            this.$enabled = z;
            this.$singleLine = z5;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = interactionSource;
            this.$isError = z10;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$prefix = pVar6;
            this.$suffix = pVar7;
            this.$supportingText = pVar8;
            this.$colors = textFieldColors;
            this.$contentPadding = paddingValues;
            this.$container = pVar9;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.OutlinedTextFieldDecorationBox(this.$value, this.$innerTextField, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$colors, this.$contentPadding, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$OutlinedTextFieldDecorationBox$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(144282315, i10, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1377)");
            }
            OutlinedTextFieldDefaults.INSTANCE.m1744ContainerBoxnbWgWpA(this.$enabled, this.$isError, this.$interactionSource, this.$colors, null, 0.0f, 0.0f, composer, 12582912, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$OutlinedTextFieldDecorationBox$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(String str, p<? super Composer, ? super Integer, t0> pVar, boolean z, boolean z5, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, TextFieldColors textFieldColors, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar7, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$innerTextField = pVar;
            this.$enabled = z;
            this.$singleLine = z5;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = interactionSource;
            this.$isError = z10;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$supportingText = pVar6;
            this.$colors = textFieldColors;
            this.$contentPadding = paddingValues;
            this.$container = pVar7;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.OutlinedTextFieldDecorationBox(this.$value, this.$innerTextField, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$supportingText, this.$colors, this.$contentPadding, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$TextFieldDecorationBox$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05621 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05621(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
            this.$shape = shape;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2023266550, i10, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1029)");
            }
            TextFieldDefaults.INSTANCE.ContainerBox(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, composer, 196608, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$TextFieldDecorationBox$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05632 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05632(String str, p<? super Composer, ? super Integer, t0> pVar, boolean z, boolean z5, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar9, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$innerTextField = pVar;
            this.$enabled = z;
            this.$singleLine = z5;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = interactionSource;
            this.$isError = z10;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$prefix = pVar6;
            this.$suffix = pVar7;
            this.$supportingText = pVar8;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$contentPadding = paddingValues;
            this.$container = pVar9;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.TextFieldDecorationBox(this.$value, this.$innerTextField, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$shape, this.$colors, this.$contentPadding, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$TextFieldDecorationBox$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05643 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05643(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
            this.$shape = shape;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1171460386, i10, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1333)");
            }
            TextFieldDefaults.INSTANCE.ContainerBox(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, composer, 196608, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$TextFieldDecorationBox$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05654 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05654(String str, p<? super Composer, ? super Integer, t0> pVar, boolean z, boolean z5, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar7, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$innerTextField = pVar;
            this.$enabled = z;
            this.$singleLine = z5;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = interactionSource;
            this.$isError = z10;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$supportingText = pVar6;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$contentPadding = paddingValues;
            this.$container = pVar7;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldDefaults.this.TextFieldDecorationBox(this.$value, this.$innerTextField, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$supportingText, this.$shape, this.$colors, this.$contentPadding, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl(1);
        UnfocusedIndicatorThickness = fM5678constructorimpl;
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(2);
        FocusedIndicatorThickness = fM5678constructorimpl2;
        UnfocusedBorderThickness = fM5678constructorimpl;
        FocusedBorderThickness = fM5678constructorimpl2;
    }

    private TextFieldDefaults() {
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2113contentPaddingWithLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m2124contentPaddingWithLabela9UjIt4(f10, f11, f12, f13);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2114contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m2125contentPaddingWithoutLabela9UjIt4(f10, f11, f12, f13);
    }

    @x6.e
    public static /* synthetic */ void getFilledShape$annotations() {
    }

    @x6.e
    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m2115getFocusedBorderThicknessD9Ej5fM$annotations() {
    }

    @x6.e
    public static /* synthetic */ void getOutlinedShape$annotations() {
    }

    @x6.e
    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m2116getUnfocusedBorderThicknessD9Ej5fM$annotations() {
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2117indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, float f10, float f11, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            f10 = FocusedIndicatorThickness;
        }
        return textFieldDefaults.m2132indicatorLinegv0btCI(modifier, z, z5, interactionSource, textFieldColors, f10, (i10 & 32) != 0 ? UnfocusedIndicatorThickness : f11);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2118outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m2135outlinedTextFieldPaddinga9UjIt4(f10, f11, f12, f13);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2119supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults textFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getSupportingTopPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = Dp.m5678constructorimpl(0);
        }
        return textFieldDefaults.m2136supportingTextPaddinga9UjIt4$material3_release(f10, f11, f12, f13);
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2120textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m2139textFieldWithLabelPaddinga9UjIt4(f10, f11, f12, f13);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2121textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m2140textFieldWithoutLabelPaddinga9UjIt4(f10, f11, f12, f13);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void ContainerBox(boolean r19, boolean r20, androidx.compose.foundation.interaction.InteractionSource r21, androidx.compose.material3.TextFieldColors r22, androidx.compose.ui.graphics.Shape r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.ContainerBox(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:279:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0125  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void DecorationBox(java.lang.String r36, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, boolean r38, boolean r39, androidx.compose.ui.text.input.VisualTransformation r40, androidx.compose.foundation.interaction.InteractionSource r41, boolean r42, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, androidx.compose.ui.graphics.Shape r50, androidx.compose.material3.TextFieldColors r51, androidx.compose.foundation.layout.PaddingValues r52, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r53, androidx.compose.runtime.Composer r54, int r55, int r56, int r57) {
        /*
            Method dump skipped, instruction units count: 1165
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.DecorationBox(java.lang.String, r7.p, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0109  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void FilledContainerBox(boolean r18, boolean r19, androidx.compose.foundation.interaction.InteractionSource r20, androidx.compose.material3.TextFieldColors r21, androidx.compose.ui.graphics.Shape r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.FilledContainerBox(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011b  */
    @x6.e
    /* JADX INFO: renamed from: OutlinedBorderContainerBox-nbWgWpA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2122OutlinedBorderContainerBoxnbWgWpA(boolean r22, boolean r23, androidx.compose.foundation.interaction.InteractionSource r24, androidx.compose.material3.TextFieldColors r25, androidx.compose.ui.graphics.Shape r26, float r27, float r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.m2122OutlinedBorderContainerBoxnbWgWpA(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:248:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011f  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void OutlinedTextFieldDecorationBox(java.lang.String r36, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, boolean r38, boolean r39, androidx.compose.ui.text.input.VisualTransformation r40, androidx.compose.foundation.interaction.InteractionSource r41, boolean r42, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, androidx.compose.material3.TextFieldColors r50, androidx.compose.foundation.layout.PaddingValues r51, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r52, androidx.compose.runtime.Composer r53, int r54, int r55, int r56) {
        /*
            Method dump skipped, instruction units count: 940
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox(java.lang.String, r7.p, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:279:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0127  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void TextFieldDecorationBox(java.lang.String r37, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r38, boolean r39, boolean r40, androidx.compose.ui.text.input.VisualTransformation r41, androidx.compose.foundation.interaction.InteractionSource r42, boolean r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r50, androidx.compose.ui.graphics.Shape r51, androidx.compose.material3.TextFieldColors r52, androidx.compose.foundation.layout.PaddingValues r53, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r54, androidx.compose.runtime.Composer r55, int r56, int r57, int r58) {
        /*
            Method dump skipped, instruction units count: 1068
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox(java.lang.String, r7.p, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public final TextFieldColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(831731228);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(831731228, i10, -1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:197)");
        }
        TextFieldColors defaultTextFieldColors = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i10 << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultTextFieldColors;
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m2123colors0hiis_0(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, long j50, long j51, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        composer.startReplaceableGroup(1513344955);
        long jM3508getUnspecified0d7_KjU = (i15 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i15 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i15 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i15 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i15 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i15 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        long jM3508getUnspecified0d7_KjU7 = (i15 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j16;
        long jM3508getUnspecified0d7_KjU8 = (i15 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j17;
        long jM3508getUnspecified0d7_KjU9 = (i15 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j18;
        long jM3508getUnspecified0d7_KjU10 = (i15 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j19;
        TextSelectionColors textSelectionColors2 = (i15 & 1024) != 0 ? null : textSelectionColors;
        long jM3508getUnspecified0d7_KjU11 = (i15 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j20;
        long jM3508getUnspecified0d7_KjU12 = (i15 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j21;
        long jM3508getUnspecified0d7_KjU13 = (i15 & 8192) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j22;
        long jM3508getUnspecified0d7_KjU14 = (i15 & 16384) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j23;
        long jM3508getUnspecified0d7_KjU15 = (32768 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j24;
        long jM3508getUnspecified0d7_KjU16 = (65536 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j25;
        long jM3508getUnspecified0d7_KjU17 = (131072 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j26;
        long jM3508getUnspecified0d7_KjU18 = (262144 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j27;
        long jM3508getUnspecified0d7_KjU19 = (524288 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j28;
        long jM3508getUnspecified0d7_KjU20 = (1048576 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j29;
        long jM3508getUnspecified0d7_KjU21 = (2097152 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j30;
        long jM3508getUnspecified0d7_KjU22 = (4194304 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j31;
        long jM3508getUnspecified0d7_KjU23 = (8388608 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j32;
        long jM3508getUnspecified0d7_KjU24 = (16777216 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j33;
        long jM3508getUnspecified0d7_KjU25 = (33554432 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j34;
        long jM3508getUnspecified0d7_KjU26 = (67108864 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j35;
        long jM3508getUnspecified0d7_KjU27 = (134217728 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j36;
        long jM3508getUnspecified0d7_KjU28 = (268435456 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j37;
        long jM3508getUnspecified0d7_KjU29 = (536870912 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j38;
        long jM3508getUnspecified0d7_KjU30 = (i15 & 1073741824) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j39;
        long jM3508getUnspecified0d7_KjU31 = (i16 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j40;
        long jM3508getUnspecified0d7_KjU32 = (i16 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j41;
        long jM3508getUnspecified0d7_KjU33 = (i16 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j42;
        long jM3508getUnspecified0d7_KjU34 = (i16 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j43;
        long jM3508getUnspecified0d7_KjU35 = (i16 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j44;
        long jM3508getUnspecified0d7_KjU36 = (i16 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j45;
        long jM3508getUnspecified0d7_KjU37 = (i16 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j46;
        long jM3508getUnspecified0d7_KjU38 = (i16 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j47;
        long jM3508getUnspecified0d7_KjU39 = (i16 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j48;
        long jM3508getUnspecified0d7_KjU40 = (i16 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j49;
        long jM3508getUnspecified0d7_KjU41 = (i16 & 1024) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j50;
        long jM3508getUnspecified0d7_KjU42 = (i16 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j51;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1513344955, i10, i11, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:298)");
        }
        TextFieldColors textFieldColorsM2070copyejIjP34 = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i14 >> 6) & 112).m2070copyejIjP34(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5, jM3508getUnspecified0d7_KjU6, jM3508getUnspecified0d7_KjU7, jM3508getUnspecified0d7_KjU8, jM3508getUnspecified0d7_KjU9, jM3508getUnspecified0d7_KjU10, textSelectionColors2, jM3508getUnspecified0d7_KjU11, jM3508getUnspecified0d7_KjU12, jM3508getUnspecified0d7_KjU13, jM3508getUnspecified0d7_KjU14, jM3508getUnspecified0d7_KjU15, jM3508getUnspecified0d7_KjU16, jM3508getUnspecified0d7_KjU17, jM3508getUnspecified0d7_KjU18, jM3508getUnspecified0d7_KjU19, jM3508getUnspecified0d7_KjU20, jM3508getUnspecified0d7_KjU21, jM3508getUnspecified0d7_KjU22, jM3508getUnspecified0d7_KjU23, jM3508getUnspecified0d7_KjU24, jM3508getUnspecified0d7_KjU25, jM3508getUnspecified0d7_KjU26, jM3508getUnspecified0d7_KjU27, jM3508getUnspecified0d7_KjU28, jM3508getUnspecified0d7_KjU29, jM3508getUnspecified0d7_KjU30, jM3508getUnspecified0d7_KjU31, jM3508getUnspecified0d7_KjU32, jM3508getUnspecified0d7_KjU33, jM3508getUnspecified0d7_KjU34, jM3508getUnspecified0d7_KjU35, jM3508getUnspecified0d7_KjU36, jM3508getUnspecified0d7_KjU37, jM3508getUnspecified0d7_KjU38, jM3508getUnspecified0d7_KjU39, jM3508getUnspecified0d7_KjU40, jM3508getUnspecified0d7_KjU41, jM3508getUnspecified0d7_KjU42);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM2070copyejIjP34;
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2124contentPaddingWithLabela9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m525PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2125contentPaddingWithoutLabela9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m525PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors getDefaultTextFieldColors(ColorScheme colorScheme, Composer composer, int i10) {
        composer.startReplaceableGroup(1341970309);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1341970309, i10, -1, "androidx.compose.material3.TextFieldDefaults.<get-defaultTextFieldColors> (TextFieldDefaults.kt:346)");
        }
        TextFieldColors defaultTextFieldColorsCached = colorScheme.getDefaultTextFieldColorsCached();
        if (defaultTextFieldColorsCached == null) {
            FilledTextFieldTokens filledTextFieldTokens = FilledTextFieldTokens.INSTANCE;
            TextFieldColors textFieldColors = new TextFieldColors(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusInputColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorInputColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getCaretColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorFocusCaretColor()), (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusActiveIndicatorColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getActiveIndicatorColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledActiveIndicatorColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorActiveIndicatorColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getLeadingIconColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getTrailingIconColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledTrailingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusLabelColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getLabelColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledLabelColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorLabelColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPlaceholderColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusSupportingColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getSupportingColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledSupportingColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorSupportingColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), null);
            colorScheme.setDefaultTextFieldColorsCached$material3_release(textFieldColors);
            defaultTextFieldColorsCached = textFieldColors;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultTextFieldColorsCached;
    }

    public final Shape getFilledShape(Composer composer, int i10) {
        composer.startReplaceableGroup(611926497);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611926497, i10, -1, "androidx.compose.material3.TextFieldDefaults.<get-filledShape> (TextFieldDefaults.kt:534)");
        }
        Shape shape = getShape(composer, i10 & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return shape;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2126getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m2127getFocusedIndicatorThicknessD9Ej5fM() {
        return FocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m2128getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m2129getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final Shape getOutlinedShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-584749279);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-584749279, i10, -1, "androidx.compose.material3.TextFieldDefaults.<get-outlinedShape> (TextFieldDefaults.kt:527)");
        }
        Shape shape = OutlinedTextFieldDefaults.INSTANCE.getShape(composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return shape;
    }

    public final Shape getShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-1941327459);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1941327459, i10, -1, "androidx.compose.material3.TextFieldDefaults.<get-shape> (TextFieldDefaults.kt:58)");
        }
        Shape value = ShapesKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2130getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m2131getUnfocusedIndicatorThicknessD9Ej5fM() {
        return UnfocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m2132indicatorLinegv0btCI(Modifier modifier, boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, float f10, float f11) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new TextFieldDefaults$indicatorLinegv0btCI$$inlined$debugInspectorInfo$1(z, z5, interactionSource, textFieldColors, f10, f11) : InspectableValueKt.getNoInspectorInfo(), new TextFieldDefaults$indicatorLine$2(z, z5, interactionSource, textFieldColors, f10, f11));
    }

    @x6.e
    /* JADX INFO: renamed from: outlinedTextFieldColors-M37tBTI, reason: not valid java name */
    public final TextFieldColors m2133outlinedTextFieldColorsM37tBTI(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, TextSelectionColors textSelectionColors, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        composer.startReplaceableGroup(618732090);
        long value = (i15 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusInputColor(), composer, 6) : j10;
        long value2 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default = (i15 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i15 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorInputColor(), composer, 6) : j13;
        long jM3507getTransparent0d7_KjU = (i15 & 16) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j14;
        long jM3507getTransparent0d7_KjU2 = (i15 & 32) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j15;
        long value4 = (i15 & 64) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j16;
        long value5 = (i15 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), composer, 6) : j17;
        TextSelectionColors textSelectionColors2 = (i15 & 256) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value6 = (i15 & 512) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor(), composer, 6) : j18;
        long value7 = (i15 & 1024) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getOutlineColor(), composer, 6) : j19;
        long jM3471copywmQWz5c$default2 = (i15 & 2048) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j20;
        long value8 = (i15 & 4096) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor(), composer, 6) : j21;
        long value9 = (i15 & 8192) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), composer, 6) : j22;
        long value10 = (i15 & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j23;
        long jM3471copywmQWz5c$default3 = (32768 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j24;
        long value11 = (65536 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), composer, 6) : j25;
        long value12 = (131072 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), composer, 6) : j26;
        long value13 = (262144 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j27;
        long jM3471copywmQWz5c$default4 = (524288 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j28;
        long value14 = (1048576 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), composer, 6) : j29;
        long value15 = (2097152 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor(), composer, 6) : j30;
        long value16 = (4194304 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getLabelColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default5 = (8388608 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        long value17 = (16777216 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor(), composer, 6) : j33;
        long value18 = (33554432 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j34;
        long value19 = (67108864 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j35;
        long jM3471copywmQWz5c$default6 = (134217728 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j36;
        long value20 = (268435456 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j37;
        long value21 = (536870912 & i15) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor(), composer, 6) : j38;
        long value22 = (i15 & 1073741824) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getSupportingColor(), composer, 6) : j39;
        long jM3471copywmQWz5c$default7 = (i16 & 1) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j40;
        long value23 = (i16 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor(), composer, 6) : j41;
        long value24 = (i16 & 4) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j42;
        long value25 = (i16 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j43;
        long jM3471copywmQWz5c$default8 = (i16 & 16) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j44;
        long value26 = (i16 & 32) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j45;
        long value27 = (i16 & 64) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j46;
        long value28 = (i16 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j47;
        long jM3471copywmQWz5c$default9 = (i16 & 256) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j48;
        long value29 = (i16 & 512) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j49;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(618732090, i10, i11, "androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:933)");
        }
        int i17 = i10 << 6;
        int i18 = i11 << 6;
        int i19 = i12 << 6;
        int i20 = i13 << 6;
        int i21 = i13 >> 24;
        long j50 = value2;
        long j51 = jM3507getTransparent0d7_KjU;
        long j52 = jM3507getTransparent0d7_KjU2;
        long j53 = value4;
        long j54 = value;
        long j55 = value3;
        TextFieldColors textFieldColorsM1745colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m1745colors0hiis_0(j54, j50, jM3471copywmQWz5c$default, j55, j51, j51, j51, j52, j53, value5, textSelectionColors2, value6, value7, jM3471copywmQWz5c$default2, value8, value9, value10, jM3471copywmQWz5c$default3, value11, value12, value13, jM3471copywmQWz5c$default4, value14, value15, value16, jM3471copywmQWz5c$default5, value17, value18, value19, jM3471copywmQWz5c$default6, value20, value21, value22, jM3471copywmQWz5c$default7, value23, value24, value25, jM3471copywmQWz5c$default8, value26, value27, value28, jM3471copywmQWz5c$default9, value29, composer, (i10 & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | ((i10 << 3) & 458752) | (i17 & 3670016) | (i17 & 29360128) | (i17 & 234881024) | (i17 & 1879048192), (i18 & 1879048192) | (i18 & 896) | ((i10 >> 24) & 126) | (i18 & 7168) | (i18 & 57344) | (i18 & 458752) | (i18 & 3670016) | (i18 & 29360128) | (i18 & 234881024), ((i11 >> 24) & 126) | (i19 & 896) | (i19 & 7168) | (i19 & 57344) | (i19 & 458752) | (i19 & 3670016) | (i19 & 29360128) | (i19 & 234881024) | (i19 & 1879048192), (i20 & 896) | ((i12 >> 24) & 126) | (i20 & 7168) | (i20 & 57344) | (i20 & 458752) | (i20 & 3670016) | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192), (i21 & 112) | (i21 & 14) | 3072 | ((i14 << 6) & 896), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1745colors0hiis_0;
    }

    @x6.e
    /* JADX INFO: renamed from: outlinedTextFieldColors-eS1Emto, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2134outlinedTextFieldColorseS1Emto(long j10, long j11, long j12, long j13, long j14, TextSelectionColors textSelectionColors, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15) {
        composer.startReplaceableGroup(1767818445);
        long value = (i14 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputColor(), composer, 6) : j10;
        long jM3471copywmQWz5c$default = (i14 & 2) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long jM3507getTransparent0d7_KjU = (i14 & 4) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j12;
        long value2 = (i14 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j13;
        long value3 = (i14 & 16) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), composer, 6) : j14;
        TextSelectionColors textSelectionColors2 = (i14 & 32) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value4 = (i14 & 64) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor(), composer, 6) : j15;
        long value5 = (i14 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getOutlineColor(), composer, 6) : j16;
        long jM3471copywmQWz5c$default2 = (i14 & 256) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor(), composer, 6), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long value6 = (i14 & 512) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor(), composer, 6) : j18;
        long value7 = (i14 & 1024) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), composer, 6) : j19;
        long value8 = (i14 & 2048) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j20;
        long jM3471copywmQWz5c$default3 = (i14 & 4096) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j21;
        long value9 = (i14 & 8192) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), composer, 6) : j22;
        long value10 = (i14 & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), composer, 6) : j23;
        long value11 = (32768 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j24;
        long jM3471copywmQWz5c$default4 = (65536 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j25;
        long value12 = (131072 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), composer, 6) : j26;
        long value13 = (262144 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor(), composer, 6) : j27;
        long value14 = (524288 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getLabelColor(), composer, 6) : j28;
        long jM3471copywmQWz5c$default5 = (1048576 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j29;
        long value15 = (2097152 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor(), composer, 6) : j30;
        long value16 = (4194304 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default6 = (8388608 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        long value17 = (16777216 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor(), composer, 6) : j33;
        long value18 = (33554432 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getSupportingColor(), composer, 6) : j34;
        long jM3471copywmQWz5c$default7 = (67108864 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j35;
        long value19 = (134217728 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor(), composer, 6) : j36;
        long value20 = (268435456 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j37;
        long value21 = (536870912 & i14) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j38;
        long jM3471copywmQWz5c$default8 = (i14 & 1073741824) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j39;
        long value22 = (i15 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j40;
        long value23 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j41;
        long value24 = (i15 & 4) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j42;
        long jM3471copywmQWz5c$default9 = (i15 & 8) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j43;
        long value25 = (i15 & 16) != 0 ? ColorSchemeKt.getValue(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j44;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767818445, i10, i11, "androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:1262)");
        }
        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
        int i16 = i10 << 3;
        int i17 = (i10 & 14) | (i16 & 112) | (i16 & 896);
        int i18 = i10 << 9;
        int i19 = i17 | (i18 & 7168) | ((i10 << 6) & 57344) | (i18 & 458752) | ((i10 << 12) & 3670016);
        int i20 = i10 << 15;
        int i21 = i19 | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192);
        int i22 = i11 << 15;
        int i23 = ((i10 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128) | (i22 & 234881024) | (i22 & 1879048192);
        int i24 = i12 << 15;
        int i25 = ((i11 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i24 & 458752) | (i24 & 3670016) | (i24 & 29360128);
        int i26 = i12 << 18;
        int i27 = i25 | (i26 & 234881024) | (i26 & 1879048192);
        int i28 = i12 >> 9;
        int i29 = ((i12 >> 6) & 14) | (i28 & 112) | (i28 & 896) | (i28 & 7168) | (i28 & 57344) | (i28 & 458752) | (i28 & 3670016);
        int i30 = i13 << 21;
        int i31 = i29 | (i30 & 29360128) | (i30 & 234881024) | (i30 & 1879048192);
        int i32 = i13 >> 9;
        int i33 = (i32 & 14) | 3072 | (i32 & 112) | (i32 & 896);
        long j45 = value;
        long j46 = jM3507getTransparent0d7_KjU;
        TextFieldColors textFieldColorsM1745colors0hiis_0 = outlinedTextFieldDefaults.m1745colors0hiis_0(j45, value, jM3471copywmQWz5c$default, j45, j46, j46, j46, j46, value2, value3, textSelectionColors2, value4, value5, jM3471copywmQWz5c$default2, value6, value7, value8, jM3471copywmQWz5c$default3, value9, value10, value11, jM3471copywmQWz5c$default4, value12, value13, value14, jM3471copywmQWz5c$default5, value15, value16, value16, jM3471copywmQWz5c$default6, value16, value17, value18, jM3471copywmQWz5c$default7, value19, value20, value21, jM3471copywmQWz5c$default8, value22, value23, value24, jM3471copywmQWz5c$default9, value25, composer, i21, i23, i27, i31, i33, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM1745colors0hiis_0;
    }

    @x6.e
    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2135outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return OutlinedTextFieldDefaults.INSTANCE.m1746contentPaddinga9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release, reason: not valid java name */
    public final PaddingValues m2136supportingTextPaddinga9UjIt4$material3_release(float start, float top, float end, float bottom) {
        return PaddingKt.m525PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    @x6.e
    /* JADX INFO: renamed from: textFieldColors-M37tBTI, reason: not valid java name */
    public final TextFieldColors m2137textFieldColorsM37tBTI(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, TextSelectionColors textSelectionColors, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        composer.startReplaceableGroup(568209592);
        long value = (i15 & 1) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusInputColor(), composer, 6) : j10;
        long value2 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputColor(), composer, 6) : j11;
        long jM3471copywmQWz5c$default = (i15 & 4) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long value3 = (i15 & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorInputColor(), composer, 6) : j13;
        long value4 = (i15 & 16) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerColor(), composer, 6) : j14;
        long value5 = (i15 & 32) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerColor(), composer, 6) : j15;
        long value6 = (i15 & 64) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j16;
        long value7 = (i15 & 128) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), composer, 6) : j17;
        TextSelectionColors textSelectionColors2 = (i15 & 256) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value8 = (i15 & 512) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor(), composer, 6) : j18;
        long value9 = (i15 & 1024) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor(), composer, 6) : j19;
        long jM3471copywmQWz5c$default2 = (i15 & 2048) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j20;
        long value10 = (i15 & 4096) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor(), composer, 6) : j21;
        long value11 = (i15 & 8192) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), composer, 6) : j22;
        long value12 = (i15 & 16384) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j23;
        long jM3471copywmQWz5c$default3 = (32768 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j24;
        long value13 = (65536 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), composer, 6) : j25;
        long value14 = (131072 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), composer, 6) : j26;
        long value15 = (262144 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j27;
        long jM3471copywmQWz5c$default4 = (524288 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j28;
        long value16 = (1048576 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), composer, 6) : j29;
        long value17 = (2097152 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusLabelColor(), composer, 6) : j30;
        long value18 = (4194304 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getLabelColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default5 = (8388608 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLabelColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        long value19 = (16777216 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorLabelColor(), composer, 6) : j33;
        long value20 = (33554432 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j34;
        long value21 = (67108864 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j35;
        long jM3471copywmQWz5c$default6 = (134217728 & i15) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j36;
        long value22 = (268435456 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j37;
        long value23 = (536870912 & i15) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusSupportingColor(), composer, 6) : j38;
        long value24 = (i15 & 1073741824) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getSupportingColor(), composer, 6) : j39;
        long jM3471copywmQWz5c$default7 = (i16 & 1) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j40;
        long value25 = (i16 & 2) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorSupportingColor(), composer, 6) : j41;
        long value26 = (i16 & 4) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j42;
        long value27 = (i16 & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j43;
        long jM3471copywmQWz5c$default8 = (i16 & 16) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j44;
        long value28 = (i16 & 32) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j45;
        long value29 = (i16 & 64) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j46;
        long value30 = (i16 & 128) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j47;
        long jM3471copywmQWz5c$default9 = (i16 & 256) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j48;
        long value31 = (i16 & 512) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j49;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(568209592, i10, i11, "androidx.compose.material3.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:783)");
        }
        int i17 = i10 << 6;
        int i18 = (65534 & i10) | ((i10 << 3) & 458752) | (i17 & 3670016) | (i17 & 29360128) | (i17 & 234881024) | (i17 & 1879048192);
        int i19 = i11 << 6;
        int i20 = (i19 & 896) | ((i10 >> 24) & 126) | (i19 & 7168) | (i19 & 57344) | (i19 & 458752) | (i19 & 3670016) | (i19 & 29360128) | (i19 & 234881024) | (i19 & 1879048192);
        int i21 = i12 << 6;
        int i22 = (i21 & 896) | ((i11 >> 24) & 126) | (i21 & 7168) | (i21 & 57344) | (i21 & 458752) | (i21 & 3670016) | (i21 & 29360128) | (i21 & 234881024) | (i21 & 1879048192);
        int i23 = i13 << 6;
        int i24 = i14 << 6;
        int i25 = ((i13 >> 24) & 126) | (i24 & 896) | (i24 & 7168);
        long j50 = value2;
        long j51 = value4;
        long j52 = value5;
        long j53 = value6;
        long j54 = value;
        long j55 = value3;
        TextFieldColors textFieldColorsM2123colors0hiis_0 = m2123colors0hiis_0(j54, j50, jM3471copywmQWz5c$default, j55, j51, j51, j51, j52, j53, value7, textSelectionColors2, value8, value9, jM3471copywmQWz5c$default2, value10, value11, value12, jM3471copywmQWz5c$default3, value13, value14, value15, jM3471copywmQWz5c$default4, value16, value17, value18, jM3471copywmQWz5c$default5, value19, value20, value21, jM3471copywmQWz5c$default6, value22, value23, value24, jM3471copywmQWz5c$default7, value25, value26, value27, jM3471copywmQWz5c$default8, value28, value29, value30, jM3471copywmQWz5c$default9, value31, composer, i18, i20, i22, (i23 & 896) | ((i12 >> 24) & 126) | (i23 & 7168) | (i23 & 57344) | (i23 & 458752) | (i23 & 3670016) | (i23 & 29360128) | (i23 & 234881024) | (i23 & 1879048192), i25, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM2123colors0hiis_0;
    }

    @x6.e
    /* JADX INFO: renamed from: textFieldColors-eS1Emto, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2138textFieldColorseS1Emto(long j10, long j11, long j12, long j13, long j14, TextSelectionColors textSelectionColors, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15) {
        composer.startReplaceableGroup(-595874869);
        long value = (i14 & 1) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputColor(), composer, 6) : j10;
        long jM3471copywmQWz5c$default = (i14 & 2) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long value2 = (i14 & 4) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerColor(), composer, 6) : j12;
        long value3 = (i14 & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), composer, 6) : j13;
        long value4 = (i14 & 16) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), composer, 6) : j14;
        TextSelectionColors textSelectionColors2 = (i14 & 32) != 0 ? (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()) : textSelectionColors;
        long value5 = (i14 & 64) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor(), composer, 6) : j15;
        long value6 = (i14 & 128) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor(), composer, 6) : j16;
        long jM3471copywmQWz5c$default2 = (i14 & 256) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long value7 = (i14 & 512) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor(), composer, 6) : j18;
        long value8 = (i14 & 1024) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), composer, 6) : j19;
        long value9 = (i14 & 2048) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getLeadingIconColor(), composer, 6) : j20;
        long jM3471copywmQWz5c$default3 = (i14 & 4096) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j21;
        long value10 = (i14 & 8192) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), composer, 6) : j22;
        long value11 = (i14 & 16384) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), composer, 6) : j23;
        long value12 = (32768 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getTrailingIconColor(), composer, 6) : j24;
        long jM3471copywmQWz5c$default4 = (65536 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j25;
        long value13 = (131072 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), composer, 6) : j26;
        long value14 = (262144 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusLabelColor(), composer, 6) : j27;
        long value15 = (524288 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getLabelColor(), composer, 6) : j28;
        long jM3471copywmQWz5c$default5 = (1048576 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLabelColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j29;
        long value16 = (2097152 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorLabelColor(), composer, 6) : j30;
        long value17 = (4194304 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), composer, 6) : j31;
        long jM3471copywmQWz5c$default6 = (8388608 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j32;
        long value18 = (16777216 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getFocusSupportingColor(), composer, 6) : j33;
        long value19 = (33554432 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getSupportingColor(), composer, 6) : j34;
        long jM3471copywmQWz5c$default7 = (67108864 & i14) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j35;
        long value20 = (134217728 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getErrorSupportingColor(), composer, 6) : j36;
        long value21 = (268435456 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j37;
        long value22 = (536870912 & i14) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j38;
        long jM3471copywmQWz5c$default8 = (i14 & 1073741824) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j39;
        long value23 = (i15 & 1) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), composer, 6) : j40;
        long value24 = (i15 & 2) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j41;
        long value25 = (i15 & 4) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j42;
        long jM3471copywmQWz5c$default9 = (i15 & 8) != 0 ? Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null) : j43;
        long value26 = (i15 & 16) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), composer, 6) : j44;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-595874869, i10, i11, "androidx.compose.material3.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:1167)");
        }
        int i16 = i10 << 3;
        int i17 = (i10 & 14) | (i16 & 112) | (i16 & 896);
        int i18 = i10 << 9;
        int i19 = i17 | (i18 & 7168) | ((i10 << 6) & 57344) | (i18 & 458752) | ((i10 << 12) & 3670016);
        int i20 = i10 << 15;
        int i21 = i19 | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192);
        int i22 = i11 << 15;
        int i23 = ((i10 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128) | (i22 & 234881024) | (i22 & 1879048192);
        int i24 = i12 << 15;
        int i25 = ((i11 >> 15) & WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE) | (i24 & 458752) | (i24 & 3670016) | (i24 & 29360128);
        int i26 = i12 << 18;
        int i27 = i25 | (i26 & 234881024) | (i26 & 1879048192);
        int i28 = i12 >> 9;
        int i29 = ((i12 >> 6) & 14) | (i28 & 112) | (i28 & 896) | (i28 & 7168) | (i28 & 57344) | (i28 & 458752) | (i28 & 3670016);
        int i30 = i13 << 21;
        long j45 = value;
        long j46 = value2;
        TextFieldColors textFieldColorsM2123colors0hiis_0 = m2123colors0hiis_0(j45, value, jM3471copywmQWz5c$default, j45, j46, j46, j46, j46, value3, value4, textSelectionColors2, value5, value6, jM3471copywmQWz5c$default2, value7, value8, value9, jM3471copywmQWz5c$default3, value10, value11, value12, jM3471copywmQWz5c$default4, value13, value14, value15, jM3471copywmQWz5c$default5, value16, value17, value17, jM3471copywmQWz5c$default6, value17, value18, value19, jM3471copywmQWz5c$default7, value20, value21, value22, jM3471copywmQWz5c$default8, value23, value24, value25, jM3471copywmQWz5c$default9, value26, composer, i21, i23, i27, i29 | (i30 & 29360128) | (i30 & 234881024) | (i30 & 1879048192), (i13 >> 9) & 8190, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM2123colors0hiis_0;
    }

    @x6.e
    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2139textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return m2124contentPaddingWithLabela9UjIt4(start, end, top, bottom);
    }

    @x6.e
    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2140textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return m2125contentPaddingWithoutLabela9UjIt4(start, top, end, bottom);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:224:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011f  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ void OutlinedTextFieldDecorationBox(java.lang.String r33, r7.p r34, boolean r35, boolean r36, androidx.compose.ui.text.input.VisualTransformation r37, androidx.compose.foundation.interaction.InteractionSource r38, boolean r39, r7.p r40, r7.p r41, r7.p r42, r7.p r43, r7.p r44, androidx.compose.material3.TextFieldColors r45, androidx.compose.foundation.layout.PaddingValues r46, r7.p r47, androidx.compose.runtime.Composer r48, int r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 837
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox(java.lang.String, r7.p, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, r7.p, r7.p, r7.p, r7.p, r7.p, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:252:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0126  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ void TextFieldDecorationBox(java.lang.String r36, r7.p r37, boolean r38, boolean r39, androidx.compose.ui.text.input.VisualTransformation r40, androidx.compose.foundation.interaction.InteractionSource r41, boolean r42, r7.p r43, r7.p r44, r7.p r45, r7.p r46, r7.p r47, androidx.compose.ui.graphics.Shape r48, androidx.compose.material3.TextFieldColors r49, androidx.compose.foundation.layout.PaddingValues r50, r7.p r51, androidx.compose.runtime.Composer r52, int r53, int r54, int r55) {
        /*
            Method dump skipped, instruction units count: 982
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox(java.lang.String, r7.p, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, r7.p, r7.p, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
