package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0012\u001aË\u0003\u0010-\u001a\u00020\u001a2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\t2\u001a\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u001c\b\u0002\u0010\u001c\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001a0\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001a0\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u001d2\u0014\b\u0002\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001a0\u001d2\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u000f2\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001a0\u001d2\u0016\b\u0002\u0010(\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u001a0\u001d2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\b\b\u0002\u0010,\u001a\u00020+H\u0007¢\u0006\u0004\b-\u0010.\u001a£\u0002\u0010L\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u00012\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00190\u00002\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00062\u0006\u00106\u001a\u0002052\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u0002052\u0014\u0010=\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u001a0\u001d2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001a0%26\u0010C\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(B\u0012\u0004\u0012\u00020\u000f0\u00182\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0%2\u0006\u0010A\u001a\u00020\u00032\u0018\u0010G\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u00000E2\u0018\u0010I\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020H0\u00000EH\u0003¢\u0006\u0004\bJ\u0010K\u001a/\u0010N\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00062\u0006\u00108\u001a\u0002072\u0006\u0010M\u001a\u00020\u0003H\u0003¢\u0006\u0004\bN\u0010O\u001a%\u0010S\u001a\b\u0012\u0004\u0012\u00020R0\u00002\u0006\u0010P\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u0003H\u0002¢\u0006\u0004\bS\u0010T\u001a\u001f\u0010W\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u0003H\u0002¢\u0006\u0004\bW\u0010X\u001a/\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00190\u00002\b\u0010Y\u001a\u0004\u0018\u00010\n2\u0006\u0010Z\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\\\u0010]\u001a#\u0010_\u001a\u00020\u000f*\u00020^2\u0006\u0010/\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0006H\u0002¢\u0006\u0004\b_\u0010`\u001a\u0017\u0010a\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0001H\u0002¢\u0006\u0004\ba\u0010b\u001a#\u0010c\u001a\u00020\u000f*\u00020^2\u0006\u0010/\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0006H\u0002¢\u0006\u0004\bc\u0010`\u001aO\u0010d\u001a\b\u0012\u0004\u0012\u00020^0\u00002\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00190\u00002\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u00062\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u00102\u001a\u00020\tH\u0002¢\u0006\u0004\bd\u0010e\u001aE\u0010k\u001a\u00020\u001a2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020^0f2\u0006\u0010h\u001a\u00020\t2\u0006\u0010i\u001a\u00020\u00062\u0006\u0010j\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0006H\u0002¢\u0006\u0004\bk\u0010l\"\u0014\u0010m\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\bm\u0010n\"\u0014\u0010o\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\bo\u0010n\"\u0014\u0010p\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\bp\u0010n\"\u0014\u0010q\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\bq\u0010n\"\u0014\u0010r\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\br\u0010n¨\u0006x²\u0006\u000e\u0010s\u001a\u00020\u000f8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010t\u001a\u0004\u0018\u00010\t8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010u\u001a\u0004\u0018\u00010\t8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010v\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010w\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002"}, d2 = {"", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channels", "", "channelWindowOffset", "totalChannelCount", "", "clockTickMillis", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "", "epgLoadingChannelIds", "epgAttemptedChannelIds", "", "isGuideBackfillLoading", "hasGuideSource", "selectedChannelId", "focusSelectedChannelSignal", "focusEpgSignal", "Lcom/arflix/tv/ui/screens/tv/live/EpgGridFocusMode;", "focusMode", "scrollResetKey", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/IptvProgram;", "Lx6/t0;", "onChannelSelect", "onProgramSelect", "Lkotlin/Function1;", "onChannelFocused", "onChannelFavoriteToggle", "favorites", "variantCountFor", "onOpenVariants", "compact", "gridFocused", "Lkotlin/Function0;", "onMoveLeftFromChannels", "onEnterEpg", "onExitEpg", "onRequestPreviousChannels", "onRequestNextChannels", "Landroidx/compose/ui/Modifier;", "modifier", "EpgGrid", "(Ljava/util/List;IIJLjava/util/Map;Ljava/util/Set;Ljava/util/Set;ZZLjava/lang/String;IILcom/arflix/tv/ui/screens/tv/live/EpgGridFocusMode;Ljava/lang/String;Lr7/p;Lr7/p;Lr7/l;Lr7/l;Ljava/util/Set;Lr7/l;Lr7/l;ZZLr7/a;Lr7/l;Lr7/l;Lr7/a;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;IIII)V", "channel", "programs", "placeholderTitle", "noProgrammeData", "windowStartMillis", "windowEndMillis", "Landroidx/compose/ui/unit/Dp;", "totalWidth", "", "pxPerMin", "stripe", "isActive", "epgMode", "rowHeight", "onClick", "onFocused", "Lx6/y;", ContentDisposition.Parameters.Name, "rowIdx", "anchorStartMin", "onMoveVertically", "onMoveLeftFromStart", "", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequesters", "Lcom/arflix/tv/ui/screens/tv/live/ProgramFocusTarget;", "focusTargets", "ProgramsRow-n3xTMnA", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;JJJFFZZZFLr7/l;Lr7/a;Lr7/p;Lr7/a;ILjava/util/Map;Ljava/util/Map;Landroidx/compose/runtime/Composer;II)V", "ProgramsRow", "hScrollOffsetPx", "NowLine", "(JJFILandroidx/compose/runtime/Composer;I)V", "startMillis", "count", "Lcom/arflix/tv/ui/screens/tv/live/TimeSlot;", "buildHalfHourSlots", "(JI)Ljava/util/List;", "nowMillis", "pastWindowMinutes", "roundedGuideWindowStart", "(JI)J", "item", TtmlNode.START, TtmlNode.END, "programsInWindow", "(Lcom/arflix/tv/data/model/IptvNowNext;JJ)Ljava/util/List;", "Lcom/arflix/tv/ui/screens/tv/live/ProgramPlacement;", "isCatchupSupported", "(Lcom/arflix/tv/ui/screens/tv/live/ProgramPlacement;Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;J)Z", "effectiveCatchupDays", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;)I", "canFocus", "buildProgramPlacements", "(Ljava/util/List;JJJLjava/lang/String;Ljava/lang/String;)Ljava/util/List;", "", "placements", LinkHeader.Parameters.Title, "gapStart", "gapEnd", "addPlaceholderPlacement", "(Ljava/util/List;Ljava/lang/String;JJJJ)V", "EpgPastWindowMinutes", "I", "EpgFutureWindowMinutes", "CompactEpgPastWindowMinutes", "CompactEpgFutureWindowMinutes", "ChannelWindowPrefetchThreshold", "didPositionInitialSelection", "activeChannelFocusId", "pendingChannelFocusId", "handledSelectedFocusSignal", "handledEpgFocusSignal", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class EpgGridKt {
    private static final int ChannelWindowPrefetchThreshold = 10;
    private static final int CompactEpgFutureWindowMinutes = 360;
    private static final int CompactEpgPastWindowMinutes = 90;
    private static final int EpgFutureWindowMinutes = 600;
    private static final int EpgPastWindowMinutes = 120;

    /* JADX WARN: Removed duplicated region for block: B:103:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x04ec  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x05f2  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0604  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x060e  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0616  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0657  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x06ad  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x06c2  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x06f2  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x071a  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x071e  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x072d  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0733  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0780  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x0787  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x078c  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0791  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x079e  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x07df  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x07ea  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0818  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x084c  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x0869  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x087d  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0893  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x08a2  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:547:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x08c8  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x08da  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x08dc  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x08f3  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x08f5  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x0912  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x0922  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x095c  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x0976  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0978  */
    /* JADX WARN: Removed duplicated region for block: B:580:0x098a  */
    /* JADX WARN: Removed duplicated region for block: B:584:0x0994  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x09c4  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x09da  */
    /* JADX WARN: Removed duplicated region for block: B:592:0x09e0  */
    /* JADX WARN: Removed duplicated region for block: B:594:0x09e4  */
    /* JADX WARN: Removed duplicated region for block: B:595:0x09e6  */
    /* JADX WARN: Removed duplicated region for block: B:602:0x0a03  */
    /* JADX WARN: Removed duplicated region for block: B:605:0x0a3f  */
    /* JADX WARN: Removed duplicated region for block: B:606:0x0a41  */
    /* JADX WARN: Removed duplicated region for block: B:609:0x0a4b  */
    /* JADX WARN: Removed duplicated region for block: B:610:0x0a4d  */
    /* JADX WARN: Removed duplicated region for block: B:613:0x0a5a  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x0a5c  */
    /* JADX WARN: Removed duplicated region for block: B:621:0x0a74  */
    /* JADX WARN: Removed duplicated region for block: B:624:0x0aa6  */
    /* JADX WARN: Removed duplicated region for block: B:627:0x0acf  */
    /* JADX WARN: Removed duplicated region for block: B:629:0x0ad4  */
    /* JADX WARN: Removed duplicated region for block: B:631:0x0ad9  */
    /* JADX WARN: Removed duplicated region for block: B:632:0x0adc  */
    /* JADX WARN: Removed duplicated region for block: B:635:0x0aea  */
    /* JADX WARN: Removed duplicated region for block: B:636:0x0aed  */
    /* JADX WARN: Removed duplicated region for block: B:639:0x0af8  */
    /* JADX WARN: Removed duplicated region for block: B:640:0x0afb  */
    /* JADX WARN: Removed duplicated region for block: B:643:0x0b13  */
    /* JADX WARN: Removed duplicated region for block: B:644:0x0b16  */
    /* JADX WARN: Removed duplicated region for block: B:647:0x0b24  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x0b27  */
    /* JADX WARN: Removed duplicated region for block: B:651:0x0b3c  */
    /* JADX WARN: Removed duplicated region for block: B:652:0x0b3f  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x0b59  */
    /* JADX WARN: Removed duplicated region for block: B:656:0x0b5c  */
    /* JADX WARN: Removed duplicated region for block: B:661:0x0b6b  */
    /* JADX WARN: Removed duplicated region for block: B:666:0x0c3c  */
    /* JADX WARN: Removed duplicated region for block: B:667:0x0c3e  */
    /* JADX WARN: Removed duplicated region for block: B:674:0x0c6f  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x0ccc  */
    /* JADX WARN: Removed duplicated region for block: B:678:0x0cce  */
    /* JADX WARN: Removed duplicated region for block: B:681:0x0cd7  */
    /* JADX WARN: Removed duplicated region for block: B:682:0x0cd9  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x0ce1  */
    /* JADX WARN: Removed duplicated region for block: B:686:0x0ce3  */
    /* JADX WARN: Removed duplicated region for block: B:689:0x0cf1  */
    /* JADX WARN: Removed duplicated region for block: B:690:0x0cf3  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x0d11  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:700:0x0d9d  */
    /* JADX WARN: Removed duplicated region for block: B:703:0x0da9  */
    /* JADX WARN: Removed duplicated region for block: B:704:0x0dad  */
    /* JADX WARN: Removed duplicated region for block: B:707:0x0dbe  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0dcf  */
    /* JADX WARN: Removed duplicated region for block: B:714:0x0e31  */
    /* JADX WARN: Removed duplicated region for block: B:717:0x0e3d  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x0e41  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x0e52  */
    /* JADX WARN: Removed duplicated region for block: B:725:0x0e67  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x0ec0  */
    /* JADX WARN: Removed duplicated region for block: B:732:0x0ecc  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x0ed0  */
    /* JADX WARN: Removed duplicated region for block: B:736:0x0ee1  */
    /* JADX WARN: Removed duplicated region for block: B:740:0x0ef6  */
    /* JADX WARN: Removed duplicated region for block: B:744:0x0f2c  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x0f38  */
    /* JADX WARN: Removed duplicated region for block: B:748:0x0f3c  */
    /* JADX WARN: Removed duplicated region for block: B:751:0x0f4d  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0f62  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x102d  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x1039  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x103d  */
    /* JADX WARN: Removed duplicated region for block: B:766:0x104e  */
    /* JADX WARN: Removed duplicated region for block: B:770:0x1063  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x10df  */
    /* JADX WARN: Removed duplicated region for block: B:778:0x10ec  */
    /* JADX WARN: Removed duplicated region for block: B:781:0x11c8  */
    /* JADX WARN: Removed duplicated region for block: B:784:0x11d4  */
    /* JADX WARN: Removed duplicated region for block: B:785:0x11d8  */
    /* JADX WARN: Removed duplicated region for block: B:788:0x11e9  */
    /* JADX WARN: Removed duplicated region for block: B:792:0x11fe  */
    /* JADX WARN: Removed duplicated region for block: B:796:0x1235  */
    /* JADX WARN: Removed duplicated region for block: B:799:0x1241  */
    /* JADX WARN: Removed duplicated region for block: B:800:0x1245  */
    /* JADX WARN: Removed duplicated region for block: B:803:0x1256  */
    /* JADX WARN: Removed duplicated region for block: B:807:0x1269  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:811:0x1280  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:845:0x14e5  */
    /* JADX WARN: Removed duplicated region for block: B:848:0x15a7  */
    /* JADX WARN: Removed duplicated region for block: B:852:0x15d8  */
    /* JADX WARN: Removed duplicated region for block: B:857:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x013d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EpgGrid(final java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r120, int r121, int r122, final long r123, final java.util.Map<java.lang.String, com.arflix.tv.data.model.IptvNowNext> r125, java.util.Set<java.lang.String> r126, java.util.Set<java.lang.String> r127, boolean r128, boolean r129, final java.lang.String r130, final int r131, int r132, com.arflix.tv.ui.screens.tv.live.EpgGridFocusMode r133, java.lang.String r134, final r7.p<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, ? super com.arflix.tv.data.model.IptvProgram, x6.t0> r135, r7.p<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, ? super com.arflix.tv.data.model.IptvProgram, x6.t0> r136, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r137, final r7.l<? super java.lang.String, x6.t0> r138, final java.util.Set<java.lang.String> r139, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, java.lang.Integer> r140, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r141, boolean r142, boolean r143, r7.a<x6.t0> r144, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r145, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r146, r7.a<x6.t0> r147, r7.a<x6.t0> r148, androidx.compose.ui.Modifier r149, androidx.compose.runtime.Composer r150, final int r151, final int r152, final int r153, final int r154) {
        /*
            Method dump skipped, instruction units count: 5630
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt.EpgGrid(java.util.List, int, int, long, java.util.Map, java.util.Set, java.util.Set, boolean, boolean, java.lang.String, int, int, com.arflix.tv.ui.screens.tv.live.EpgGridFocusMode, java.lang.String, r7.p, r7.p, r7.l, r7.l, java.util.Set, r7.l, r7.l, boolean, boolean, r7.a, r7.l, r7.l, r7.a, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0049, code lost:
    
        if ((r10 instanceof x6.c0) == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean EpgGrid$keepChannelFocus(java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r10, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r11, java.util.LinkedHashMap<java.lang.String, androidx.compose.ui.focus.FocusRequester> r12, ka.k0 r13, int r14, r7.a<x6.t0> r15, int r16, r7.a<x6.t0> r17, androidx.compose.runtime.MutableState<java.lang.String> r18, androidx.compose.runtime.MutableState<java.lang.String> r19, androidx.compose.foundation.lazy.LazyListState r20, androidx.compose.ui.focus.FocusRequester r21, java.lang.String r22, androidx.compose.ui.focus.FocusRequester r23, int r24) {
        /*
            r2 = r24
            java.lang.Object r0 = kotlin.collections.x.p0(r2, r10)
            r6 = r0
            com.arflix.tv.ui.screens.tv.live.EnrichedChannel r6 = (com.arflix.tv.ui.screens.tv.live.EnrichedChannel) r6
            r9 = 1
            if (r6 != 0) goto Ld
            goto L4b
        Ld:
            r0 = r14
            r1 = r15
            r3 = r16
            r4 = r17
            r5 = r2
            r2 = r10
            EpgGrid$requestMoreRowsIfNeeded(r0, r1, r2, r3, r4, r5)
            java.lang.String r10 = r6.getId()
            r14 = r18
            EpgGrid$lambda$25(r14, r10)
            java.lang.String r10 = r6.getId()
            r14 = r19
            EpgGrid$lambda$28(r14, r10)
            r11.invoke(r6)
            java.lang.String r10 = r6.getId()
            java.lang.Object r10 = r12.get(r10)
            androidx.compose.ui.focus.FocusRequester r10 = (androidx.compose.ui.focus.FocusRequester) r10
            if (r10 == 0) goto L4c
            r10.requestFocus()     // Catch: java.lang.Throwable -> L3f
            x6.t0 r10 = x6.t0.f22605a     // Catch: java.lang.Throwable -> L3f
            goto L47
        L3f:
            r0 = move-exception
            r10 = r0
            x6.c0 r11 = new x6.c0
            r11.<init>(r10)
            r10 = r11
        L47:
            boolean r10 = r10 instanceof x6.c0
            if (r10 != 0) goto L4c
        L4b:
            return r9
        L4c:
            com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$keepChannelFocus$2 r0 = new com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$keepChannelFocus$2
            r8 = 0
            r3 = r12
            r1 = r20
            r5 = r21
            r7 = r23
            r2 = r24
            r4 = r6
            r6 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r10 = 3
            r11 = 0
            r12 = 0
            ka.m0.p(r13, r12, r11, r0, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt.EpgGrid$keepChannelFocus(java.util.List, r7.l, java.util.LinkedHashMap, ka.k0, int, r7.a, int, r7.a, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.lazy.LazyListState, androidx.compose.ui.focus.FocusRequester, java.lang.String, androidx.compose.ui.focus.FocusRequester, int):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$0$0(EnrichedChannel enrichedChannel) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int EpgGrid$lambda$1$0(EnrichedChannel enrichedChannel) {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$2$0(EnrichedChannel enrichedChannel) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean EpgGrid$lambda$21(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void EpgGrid$lambda$22(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String EpgGrid$lambda$24(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String EpgGrid$lambda$27(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$38$0(EpgGridFocusMode epgGridFocusMode, r7.l lVar, EnrichedChannel enrichedChannel, r7.a aVar, FocusRequester focusRequester) {
        if (epgGridFocusMode == EpgGridFocusMode.Epg) {
            lVar.invoke(enrichedChannel);
            try {
                focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        } else {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$4$0(EnrichedChannel enrichedChannel) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$45$1(float f10, List list, final EpgGridFocusMode epgGridFocusMode, final r7.l lVar, final EnrichedChannel enrichedChannel, final r7.a aVar, final FocusRequester focusRequester, final LazyListState lazyListState, final List list2, final MutableState mutableState, final float f11, final String str, final boolean z, final Map map, final Set set, final r7.l lVar2, final float f12, final long j10, final r7.p pVar, final r7.l lVar3, final long j11, final r7.l lVar4, final ka.k0 k0Var, final int i10, final r7.a aVar2, final int i11, final r7.a aVar3, final MutableState mutableState2, final r7.l lVar5, final HashMap map2, final r7.l lVar6, final ScrollState scrollState, final long j12, final boolean z5, final Set set2, final boolean z10, final Set set3, final float f13, final r7.p pVar2, Density density, final LinkedHashMap linkedHashMap, final FocusRequester focusRequester2, final LinkedHashMap linkedHashMap2, final LinkedHashMap linkedHashMap3, final BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i12) {
        int i13;
        Modifier.Companion companion;
        float f14;
        Modifier.Companion companion2;
        Composer composer2;
        if ((i12 & 6) == 0) {
            i13 = i12 | (composer.changed(boxWithConstraintsScope) ? 4 : 2);
        } else {
            i13 = i12;
        }
        if ((i13 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(334244862, i13, -1, "com.arflix.tv.ui.screens.tv.live.EpgGrid.<anonymous>.<anonymous> (EpgGrid.kt:437)");
            }
            float fM5678constructorimpl = Dp.m5678constructorimpl(list.size() * f10);
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null);
            boolean zChanged = ((i13 & 14) == 4) | composer.changed(epgGridFocusMode.ordinal()) | composer.changed(lVar) | composer.changed(enrichedChannel) | composer.changed(aVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                companion = companion3;
                f14 = fM5678constructorimpl;
                r7.l<KeyEvent, Boolean> lVar7 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$16$2$1$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return m6385invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6385invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                            Key.Companion companion4 = Key.INSTANCE;
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion4.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion4.m4182getEscapeEK5gGoQ())) {
                                if (epgGridFocusMode == EpgGridFocusMode.Epg) {
                                    lVar.invoke(enrichedChannel);
                                    try {
                                        focusRequester.requestFocus();
                                    } catch (Throwable unused) {
                                    }
                                } else {
                                    aVar.invoke();
                                }
                                return Boolean.TRUE;
                            }
                        }
                        return Boolean.FALSE;
                    }
                };
                composer.updateRememberedValue(lVar7);
                objRememberedValue = lVar7;
            } else {
                companion = companion3;
                f14 = fM5678constructorimpl;
            }
            Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifierFillMaxSize$default, (r7.l) objRememberedValue);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnKeyEvent);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion5 = companion;
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(SizeKt.fillMaxSize$default(companion5, 0.0f, 1, null), null, false, 3, null);
            boolean zChanged2 = composer.changed(z5) | composer.changed(list2) | composer.changed(mutableState) | composer.changed(epgGridFocusMode.ordinal()) | composer.changed(f11) | composer.changed(str) | composer.changed(z) | composer.changed(map) | composer.changed(set) | composer.changed(lVar2) | composer.changed(f12) | composer.changed(j10) | composer.changed(pVar) | composer.changed(lVar3) | composer.changed(aVar) | composer.changed(j11) | composer.changed(lVar4) | composer.changedInstance(k0Var) | composer.changed(lazyListState) | composer.changed(i10) | composer.changed(aVar2) | composer.changed(i11) | composer.changed(aVar3) | composer.changed(mutableState2) | composer.changed(lVar5) | composer.changed(map2) | composer.changed(lVar6) | composer.changed(scrollState) | composer.changed(j12) | composer.changed(set2) | composer.changed(z10) | composer.changed(set3) | composer.changed(f14) | composer.changed(f13) | composer.changed(lVar) | composer.changed(pVar2);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                final float f15 = f14;
                companion2 = companion5;
                objRememberedValue2 = new r7.l() { // from class: com.arflix.tv.ui.screens.tv.live.x
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return EpgGridKt.EpgGrid$lambda$45$1$1$0$0(list2, epgGridFocusMode, f11, mutableState, linkedHashMap, str, z, map, set, lVar2, f12, focusRequester2, focusRequester, j10, pVar, lVar3, aVar, j11, lVar4, k0Var, lazyListState, i10, aVar2, i11, aVar3, mutableState2, lVar5, map2, lVar6, scrollState, linkedHashMap2, linkedHashMap3, j12, z5, set2, z10, set3, f15, f13, lVar, pVar2, (LazyListScope) obj);
                    }
                };
                composer2 = composer;
                composer2.updateRememberedValue(objRememberedValue2);
            } else {
                composer2 = composer;
                companion2 = companion5;
            }
            LazyDslKt.LazyColumn(modifierArvioDpadFocusGroup$default, lazyListState, null, false, null, null, null, false, (r7.l) objRememberedValue2, composer2, 0, 252);
            if (j11 > j10 || j10 >= j12) {
                composer2.startReplaceGroup(1665563006);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(1664555878);
                float fM5678constructorimpl2 = Dp.m5678constructorimpl(Dp.m5678constructorimpl(((int) ((j10 - j11) / 60000)) * f13) - density.mo282toDpu2uoSUM(scrollState.getValue()));
                if (Dp.m5677compareTo0680j_4(fM5678constructorimpl2, Dp.m5678constructorimpl(0)) >= 0) {
                    composer2.startReplaceGroup(1664782116);
                    float fM5678constructorimpl3 = Dp.m5678constructorimpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(1) + f12) + fM5678constructorimpl2);
                    Modifier.Companion companion6 = companion2;
                    Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(SizeKt.fillMaxHeight$default(OffsetKt.m490offsetVpY3zN4$default(companion6, fM5678constructorimpl3, 0.0f, 2, null), 0.0f, 1, null), Dp.m5678constructorimpl(2));
                    LiveColors liveColors = LiveColors.INSTANCE;
                    BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(modifierM583width3ABfNKs, liveColors.m6397getAccent0d7_KjU(), null, 2, null), composer2, 0);
                    BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m583width3ABfNKs(SizeKt.fillMaxHeight$default(OffsetKt.m490offsetVpY3zN4$default(companion6, Dp.m5678constructorimpl(fM5678constructorimpl3 - Dp.m5678constructorimpl(3)), 0.0f, 2, null), 0.0f, 1, null), Dp.m5678constructorimpl(8)), Color.m3471copywmQWz5c$default(liveColors.m6397getAccent0d7_KjU(), 0.22f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer2, 0);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(1665545150);
                    composer2.endReplaceGroup();
                }
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$45$1$1$0$0(List list, EpgGridFocusMode epgGridFocusMode, float f10, MutableState mutableState, LinkedHashMap linkedHashMap, String str, boolean z, Map map, Set set, r7.l lVar, float f11, FocusRequester focusRequester, FocusRequester focusRequester2, long j10, r7.p pVar, r7.l lVar2, r7.a aVar, long j11, r7.l lVar3, ka.k0 k0Var, LazyListState lazyListState, int i10, r7.a aVar2, int i11, r7.a aVar3, MutableState mutableState2, r7.l lVar4, HashMap map2, r7.l lVar5, ScrollState scrollState, LinkedHashMap linkedHashMap2, LinkedHashMap linkedHashMap3, long j12, boolean z5, Set set2, boolean z10, Set set3, float f12, float f13, r7.l lVar6, r7.p pVar2, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new EpgGridKt$EpgGrid$lambda$45$1$1$0$0$$inlined$itemsIndexed$1(new l0(14), list), new EpgGridKt$EpgGrid$lambda$45$1$1$0$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new EpgGridKt$EpgGrid$lambda$45$1$1$0$0$$inlined$itemsIndexed$3(list, epgGridFocusMode, f10, mutableState, linkedHashMap, str, z, map, set, lVar, f11, focusRequester, focusRequester2, j10, pVar, lVar2, aVar, j11, lVar3, list, k0Var, lazyListState, i10, aVar2, i11, aVar3, mutableState2, lVar4, map2, lVar5, scrollState, linkedHashMap2, linkedHashMap3, j12, z5, set2, z10, set3, f12, f13, lVar6, pVar2)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object EpgGrid$lambda$45$1$1$0$0$0(int i10, EnrichedChannel enrichedChannel) {
        return androidx.fragment.app.a2.j(i10, enrichedChannel.getId(), "#");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$46(List list, int i10, int i11, long j10, Map map, Set set, Set set2, boolean z, boolean z5, String str, int i12, int i13, EpgGridFocusMode epgGridFocusMode, String str2, r7.p pVar, r7.p pVar2, r7.l lVar, r7.l lVar2, Set set3, r7.l lVar3, r7.l lVar4, boolean z10, boolean z11, r7.a aVar, r7.l lVar5, r7.l lVar6, r7.a aVar2, r7.a aVar3, Modifier modifier, int i14, int i15, int i16, int i17, Composer composer, int i18) {
        EpgGrid(list, i10, i11, j10, map, set, set2, z, z5, str, i12, i13, epgGridFocusMode, str2, pVar, pVar2, lVar, lVar2, set3, lVar3, lVar4, z10, z11, aVar, lVar5, lVar6, aVar2, aVar3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i14 | 1), RecomposeScopeImplKt.updateChangedFlags(i15), RecomposeScopeImplKt.updateChangedFlags(i16), i17);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgGrid$lambda$5$0(EnrichedChannel enrichedChannel) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean EpgGrid$moveChannelFocus(String str, HashMap<String, Integer> map, r7.a<x6.t0> aVar, List<EnrichedChannel> list, r7.a<x6.t0> aVar2, MutableState<String> mutableState, r7.l<? super EnrichedChannel, x6.t0> lVar, LinkedHashMap<String, FocusRequester> linkedHashMap, ka.k0 k0Var, int i10, int i11, MutableState<String> mutableState2, LazyListState lazyListState, FocusRequester focusRequester, FocusRequester focusRequester2, int i12) {
        int iIntValue;
        Integer num;
        String strEpgGrid$lambda$24 = EpgGrid$lambda$24(mutableState);
        if (strEpgGrid$lambda$24 == null) {
            strEpgGrid$lambda$24 = str;
        }
        if (strEpgGrid$lambda$24 == null || (num = map.get(strEpgGrid$lambda$24)) == null) {
            Integer num2 = str != null ? map.get(str) : null;
            if (num2 == null) {
                return true;
            }
            iIntValue = num2.intValue();
        } else {
            iIntValue = num.intValue();
        }
        int i13 = iIntValue + i12;
        if (i13 < 0) {
            aVar.invoke();
            return true;
        }
        if (i13 < list.size()) {
            return EpgGrid$keepChannelFocus(list, lVar, linkedHashMap, k0Var, i10, aVar, i11, aVar2, mutableState, mutableState2, lazyListState, focusRequester, str, focusRequester2, i13);
        }
        aVar2.invoke();
        return true;
    }

    private static final Integer EpgGrid$nearestProgramIndex(List<EnrichedChannel> list, LinkedHashMap<String, List<ProgramFocusTarget>> linkedHashMap, int i10, int i11) {
        Object next;
        EnrichedChannel enrichedChannel = (EnrichedChannel) kotlin.collections.x.p0(i10, list);
        if (enrichedChannel != null) {
            List<ProgramFocusTarget> list2 = linkedHashMap.get(enrichedChannel.getId());
            if (list2 == null) {
                list2 = kotlin.collections.z.f19728i;
            }
            if (!list2.isEmpty()) {
                ga.d dVar = (ga.d) kotlin.collections.x.h1(list2).iterator();
                Iterator it = dVar.f15570m;
                if (it.hasNext()) {
                    next = dVar.next();
                    if (it.hasNext()) {
                        int iDistanceTo = ((ProgramFocusTarget) ((kotlin.collections.d0) next).f19689b).distanceTo(i11);
                        do {
                            Object next2 = dVar.next();
                            int iDistanceTo2 = ((ProgramFocusTarget) ((kotlin.collections.d0) next2).f19689b).distanceTo(i11);
                            if (iDistanceTo > iDistanceTo2) {
                                next = next2;
                                iDistanceTo = iDistanceTo2;
                            }
                        } while (it.hasNext());
                    }
                } else {
                    next = null;
                }
                kotlin.collections.d0 d0Var = (kotlin.collections.d0) next;
                if (d0Var != null) {
                    return Integer.valueOf(d0Var.f19688a);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void EpgGrid$requestMoreRowsIfNeeded(int i10, r7.a<x6.t0> aVar, List<EnrichedChannel> list, int i11, r7.a<x6.t0> aVar2, int i12) {
        if (i12 <= 10 && i10 > 0) {
            aVar.invoke();
        }
        int i13 = i10 + i12;
        if (list.isEmpty() || t7.a.w(list) - i12 > 10 || i13 >= i11 - 1) {
            return;
        }
        aVar2.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean EpgGrid$requestNearestProgramFocus(List<EnrichedChannel> list, LinkedHashMap<String, List<ProgramFocusTarget>> linkedHashMap, LinkedHashMap<String, List<FocusRequester>> linkedHashMap2, ka.k0 k0Var, LazyListState lazyListState, int i10, int i11) {
        Integer numEpgGrid$nearestProgramIndex = EpgGrid$nearestProgramIndex(list, linkedHashMap, i10, i11);
        if (numEpgGrid$nearestProgramIndex != null) {
            return EpgGrid$requestProgramFocus(list, linkedHashMap2, k0Var, lazyListState, i10, numEpgGrid$nearestProgramIndex.intValue());
        }
        return false;
    }

    private static final boolean EpgGrid$requestProgramFocus(List<EnrichedChannel> list, LinkedHashMap<String, List<FocusRequester>> linkedHashMap, ka.k0 k0Var, LazyListState lazyListState, int i10, int i11) {
        EnrichedChannel enrichedChannel = (EnrichedChannel) kotlin.collections.x.p0(i10, list);
        if (enrichedChannel != null) {
            List<FocusRequester> list2 = linkedHashMap.get(enrichedChannel.getId());
            if (list2 == null) {
                list2 = kotlin.collections.z.f19728i;
            }
            List<FocusRequester> list3 = list2;
            if (!list3.isEmpty()) {
                ka.m0.p(k0Var, null, 0, new EpgGridKt$EpgGrid$requestProgramFocus$1(lazyListState, i10, list3, qb.d.n(i11, 0, t7.a.w(list3)), null), 3);
                return true;
            }
        }
        return false;
    }

    private static final void NowLine(final long j10, final long j11, final float f10, final int i10, Composer composer, final int i11) {
        int i12;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(1975025205);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(j10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(j11) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 2048 : 1024;
        }
        if ((i12 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1975025205, i12, -1, "com.arflix.tv.ui.screens.tv.live.NowLine (EpgGrid.kt:779)");
            }
            Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            float fMo281toDpu2uoSUM = density.mo281toDpu2uoSUM(density.mo285toPx0680j_4(Dp.m5678constructorimpl(((int) ((j10 - j11) / 60000)) * f10)) - i10);
            if (Dp.m5677compareTo0680j_4(fMo281toDpu2uoSUM, Dp.m5678constructorimpl(0)) < 0) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i13 = 0;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.c0
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            switch (i13) {
                                case 0:
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return EpgGridKt.NowLine$lambda$1(j10, j11, f10, i10, i11, (Composer) obj, iIntValue);
                                default:
                                    int iIntValue2 = ((Integer) obj2).intValue();
                                    return EpgGridKt.NowLine$lambda$2(j10, j11, f10, i10, i11, (Composer) obj, iIntValue2);
                            }
                        }
                    };
                    scopeUpdateScopeEndRestartGroup.updateScope(pVar);
                }
                return;
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(SizeKt.fillMaxHeight$default(OffsetKt.m490offsetVpY3zN4$default(companion, fMo281toDpu2uoSUM, 0.0f, 2, null), 0.0f, 1, null), Dp.m5678constructorimpl(2));
            LiveColors liveColors = LiveColors.INSTANCE;
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(modifierM583width3ABfNKs, liveColors.m6397getAccent0d7_KjU(), null, 2, null), composerStartRestartGroup, 0);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m583width3ABfNKs(SizeKt.fillMaxHeight$default(OffsetKt.m490offsetVpY3zN4$default(companion, Dp.m5678constructorimpl(fMo281toDpu2uoSUM - Dp.m5678constructorimpl(3)), 0.0f, 2, null), 0.0f, 1, null), Dp.m5678constructorimpl(8)), Color.m3471copywmQWz5c$default(liveColors.m6397getAccent0d7_KjU(), 0.22f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i14 = 1;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.c0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    switch (i14) {
                        case 0:
                            int iIntValue = ((Integer) obj2).intValue();
                            return EpgGridKt.NowLine$lambda$1(j10, j11, f10, i10, i11, (Composer) obj, iIntValue);
                        default:
                            int iIntValue2 = ((Integer) obj2).intValue();
                            return EpgGridKt.NowLine$lambda$2(j10, j11, f10, i10, i11, (Composer) obj, iIntValue2);
                    }
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NowLine$lambda$1(long j10, long j11, float f10, int i10, int i11, Composer composer, int i12) {
        NowLine(j10, j11, f10, i10, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NowLine$lambda$2(long j10, long j11, float f10, int i10, int i11, Composer composer, int i12) {
        NowLine(j10, j11, f10, i10, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x05cb  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0615  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0619  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x061c  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0631  */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v2, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v3, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v4, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r2v38, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v78 */
    /* JADX WARN: Type inference failed for: r2v79 */
    /* JADX WARN: Type inference failed for: r4v18, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r4v51, types: [androidx.compose.runtime.Composer] */
    /* JADX INFO: renamed from: ProgramsRow-n3xTMnA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6383ProgramsRown3xTMnA(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r49, final java.util.List<com.arflix.tv.data.model.IptvProgram> r50, final java.lang.String r51, final java.lang.String r52, final long r53, final long r55, final long r57, final float r59, final float r60, final boolean r61, final boolean r62, final boolean r63, final float r64, final r7.l<? super com.arflix.tv.data.model.IptvProgram, x6.t0> r65, final r7.a<x6.t0> r66, final r7.p<? super java.lang.Integer, ? super java.lang.Integer, java.lang.Boolean> r67, final r7.a<java.lang.Boolean> r68, final int r69, final java.util.Map<java.lang.String, java.util.List<androidx.compose.ui.focus.FocusRequester>> r70, final java.util.Map<java.lang.String, java.util.List<com.arflix.tv.ui.screens.tv.live.ProgramFocusTarget>> r71, androidx.compose.runtime.Composer r72, final int r73, final int r74) {
        /*
            Method dump skipped, instruction units count: 1803
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt.m6383ProgramsRown3xTMnA(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, java.util.List, java.lang.String, java.lang.String, long, long, long, float, float, boolean, boolean, boolean, float, r7.l, r7.a, r7.p, r7.a, int, java.util.Map, java.util.Map, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult ProgramsRow_n3xTMnA$lambda$0$5$0(final Map map, final EnrichedChannel enrichedChannel, final List list, final Map map2, final List list2, DisposableEffectScope disposableEffectScope) {
        map.put(enrichedChannel.getId(), list);
        map2.put(enrichedChannel.getId(), list2);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.EpgGridKt$ProgramsRow_n3xTMnA$lambda$0$5$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                if (map.get(enrichedChannel.getId()) == list) {
                    map.remove(enrichedChannel.getId());
                }
                if (map2.get(enrichedChannel.getId()) == list2) {
                    map2.remove(enrichedChannel.getId());
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProgramsRow_n3xTMnA$lambda$0$6$0$0(boolean z, boolean z5, r7.l lVar, ProgramPlacement programPlacement) {
        if (z && z5) {
            lVar.invoke(programPlacement.getProgram());
        } else if (!z) {
            lVar.invoke(null);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramsRow_n3xTMnA$lambda$0$6$1$0(int i10, BoxScope boxScope, r7.a aVar, List list) {
        if (i10 <= 0) {
            return ((Boolean) aVar.invoke()).booleanValue();
        }
        try {
            ((FocusRequester) list.get(i10 - 1)).requestFocus();
        } catch (Throwable unused) {
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramsRow_n3xTMnA$lambda$0$6$2$0(int i10, List list, BoxScope boxScope) {
        if (i10 < 0 || i10 >= t7.a.w(list)) {
            return false;
        }
        try {
            ((FocusRequester) list.get(i10 + 1)).requestFocus();
        } catch (Throwable unused) {
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramsRow_n3xTMnA$lambda$0$6$3$0(r7.p pVar, int i10, ProgramPlacement programPlacement) {
        return ((Boolean) pVar.invoke(Integer.valueOf(i10 - 1), Integer.valueOf(programPlacement.getStartMin()))).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramsRow_n3xTMnA$lambda$0$6$4$0(r7.p pVar, int i10, ProgramPlacement programPlacement) {
        return ((Boolean) pVar.invoke(Integer.valueOf(i10 + 1), Integer.valueOf(programPlacement.getStartMin()))).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProgramsRow_n3xTMnA$lambda$1(EnrichedChannel enrichedChannel, List list, String str, String str2, long j10, long j11, long j12, float f10, float f11, boolean z, boolean z5, boolean z10, float f12, r7.l lVar, r7.a aVar, r7.p pVar, r7.a aVar2, int i10, Map map, Map map2, int i11, int i12, Composer composer, int i13) {
        m6383ProgramsRown3xTMnA(enrichedChannel, list, str, str2, j10, j11, j12, f10, f11, z, z5, z10, f12, lVar, aVar, pVar, aVar2, i10, map, map2, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12));
        return x6.t0.f22605a;
    }

    private static final void addPlaceholderPlacement(List<ProgramPlacement> list, String str, long j10, long j11, long j12, long j13) {
        if (j11 <= j10) {
            return;
        }
        long j14 = j11 - 1;
        long jO = qb.d.o(j13, j10, j14);
        long j15 = jO - 1800000;
        if (j15 < j10) {
            j15 = j10;
        }
        if (j14 < j10) {
            j14 = j10;
        }
        long j16 = j15 > j14 ? j14 : j15;
        long jMin = Math.min(j11, Math.max(3600000 + j16, jO + 1800000));
        IptvProgram iptvProgram = new IptvProgram(str, null, j16, jMin, null, 18, null);
        int i10 = (int) ((j16 - j12) / 60000);
        int i11 = (int) ((jMin - j16) / 60000);
        if (i11 < 1) {
            i11 = 1;
        }
        list.add(new ProgramPlacement(iptvProgram, i10, i11, j16, jMin, true));
    }

    private static final List<TimeSlot> buildHalfHourSlots(long j10, int i10) {
        ArrayList arrayList = new ArrayList(i10);
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (int i11 = 0; i11 < i10; i11++) {
            long j11 = (((long) i11) * 1800000) + j10;
            arrayList.add(new TimeSlot(j11, MiniPlayerKt.formatClock(j11), j11 <= jCurrentTimeMillis && jCurrentTimeMillis <= 1799999 + j11));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.util.List<com.arflix.tv.ui.screens.tv.live.ProgramPlacement> buildProgramPlacements(java.util.List<com.arflix.tv.data.model.IptvProgram> r24, long r25, long r27, long r29, java.lang.String r31, java.lang.String r32) {
        /*
            r10 = r27
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = r24.isEmpty()
            if (r1 == 0) goto L10
            r1 = r31
            goto L12
        L10:
            r1 = r32
        L12:
            java.util.Iterator r12 = r24.iterator()
            r2 = r25
        L18:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L84
            java.lang.Object r4 = r12.next()
            r14 = r4
            com.arflix.tv.data.model.IptvProgram r14 = (com.arflix.tv.data.model.IptvProgram) r14
            long r4 = r14.getStartUtcMillis()
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 <= 0) goto L42
            long r4 = r14.getStartUtcMillis()
            long r4 = java.lang.Math.min(r4, r10)
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 <= 0) goto L42
            r6 = r25
            r8 = r29
            addPlaceholderPlacement(r0, r1, r2, r4, r6, r8)
            r2 = r4
            goto L44
        L42:
            r6 = r25
        L44:
            int r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r4 >= 0) goto L18
            long r4 = r14.getStartUtcMillis()
            long r8 = java.lang.Math.max(r6, r2)
            long r17 = java.lang.Math.max(r4, r8)
            long r4 = r14.getEndUtcMillis()
            long r19 = java.lang.Math.min(r4, r10)
            int r4 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r4 <= 0) goto L18
            com.arflix.tv.ui.screens.tv.live.ProgramPlacement r13 = new com.arflix.tv.ui.screens.tv.live.ProgramPlacement
            long r2 = r17 - r6
            r4 = 60000(0xea60, double:2.9644E-319)
            long r2 = r2 / r4
            int r15 = (int) r2
            long r2 = r19 - r17
            long r2 = r2 / r4
            int r2 = (int) r2
            r3 = 1
            if (r2 >= r3) goto L73
            r16 = r3
            goto L75
        L73:
            r16 = r2
        L75:
            r22 = 32
            r23 = 0
            r21 = 0
            r13.<init>(r14, r15, r16, r17, r19, r21, r22, r23)
            r0.add(r13)
            r2 = r19
            goto L18
        L84:
            r6 = r25
            int r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r4 >= 0) goto L90
            r8 = r29
            r4 = r10
            addPlaceholderPlacement(r0, r1, r2, r4, r6, r8)
        L90:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt.buildProgramPlacements(java.util.List, long, long, long, java.lang.String, java.lang.String):java.util.List");
    }

    public static /* synthetic */ List buildProgramPlacements$default(List list, long j10, long j11, long j12, String str, String str2, int i10, Object obj) {
        String str3 = (i10 & 16) != 0 ? "" : str;
        return buildProgramPlacements(list, j10, j11, j12, str3, (i10 & 32) != 0 ? str3 : str2);
    }

    private static final boolean canFocus(ProgramPlacement programPlacement, EnrichedChannel enrichedChannel, long j10) {
        if (programPlacement.isPlaceholder()) {
            return false;
        }
        return !programPlacement.isPast(j10) || isCatchupSupported(programPlacement, enrichedChannel, j10);
    }

    private static final int effectiveCatchupDays(EnrichedChannel enrichedChannel) {
        String catchupSource;
        int iN = qb.d.n(enrichedChannel.getCatchupDays(), 0, 7);
        if (iN > 0) {
            return iN;
        }
        IptvChannel source = enrichedChannel.getSource();
        String catchupType = source.getCatchupType();
        if ((catchupType == null || kotlin.text.o.h0(catchupType)) && (((catchupSource = source.getCatchupSource()) == null || kotlin.text.o.h0(catchupSource)) && !kotlin.text.o.T(source.getStreamUrl(), "/timeshift/", true))) {
            return (source.getXtreamStreamId() != null || kotlin.text.o.T(source.getStreamUrl(), "/live/", true)) ? 2 : 0;
        }
        return 7;
    }

    private static final boolean isCatchupSupported(ProgramPlacement programPlacement, EnrichedChannel enrichedChannel, long j10) {
        if (kotlin.jvm.internal.p.a(programPlacement.getProgram().getCatchupAvailable(), Boolean.TRUE)) {
            return true;
        }
        int iEffectiveCatchupDays = effectiveCatchupDays(enrichedChannel);
        return iEffectiveCatchupDays > 0 && !programPlacement.isPlaceholder() && programPlacement.getProgram().getStartUtcMillis() >= j10 - (((long) iEffectiveCatchupDays) * 86400000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<IptvProgram> programsInWindow(IptvNowNext iptvNowNext, long j10, long j11) {
        if (iptvNowNext == null) {
            return kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<T> it = iptvNowNext.getRecent().iterator();
        while (it.hasNext()) {
            programsInWindow$add(j10, j11, arrayList, (IptvProgram) it.next());
        }
        programsInWindow$add(j10, j11, arrayList, iptvNowNext.getNow());
        programsInWindow$add(j10, j11, arrayList, iptvNowNext.getNext());
        programsInWindow$add(j10, j11, arrayList, iptvNowNext.getLater());
        Iterator<T> it2 = iptvNowNext.getUpcoming().iterator();
        while (it2.hasNext()) {
            programsInWindow$add(j10, j11, arrayList, (IptvProgram) it2.next());
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            IptvProgram iptvProgram = (IptvProgram) obj;
            if (hashSet.add(new x6.j0(Long.valueOf(iptvProgram.getStartUtcMillis()), Long.valueOf(iptvProgram.getEndUtcMillis()), iptvProgram.getTitle()))) {
                arrayList2.add(obj);
            }
        }
        return kotlin.collections.x.W0(arrayList2, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.EpgGridKt$programsInWindow$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Long.valueOf(((IptvProgram) t2).getStartUtcMillis()), Long.valueOf(((IptvProgram) t10).getStartUtcMillis()));
            }
        });
    }

    private static final void programsInWindow$add(long j10, long j11, ArrayList<IptvProgram> arrayList, IptvProgram iptvProgram) {
        if (iptvProgram != null && iptvProgram.getEndUtcMillis() > j10 && iptvProgram.getStartUtcMillis() < j11) {
            arrayList.add(iptvProgram);
        }
    }

    private static final long roundedGuideWindowStart(long j10, int i10) {
        return (j10 - (j10 % 1800000)) - (((long) i10) * 60000);
    }
}
