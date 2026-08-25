package com.arflix.tv.ui.theme;

import androidx.compose.foundation.c;
import androidx.compose.ui.graphics.Color;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import y.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\bo\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b'\b\u0087\b\u0018\u00002\u00020\u0001Bå\u0002\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0002\u0012\b\b\u0002\u0010 \u001a\u00020\u0002\u0012\b\b\u0002\u0010!\u001a\u00020\u0002\u0012\b\b\u0002\u0010\"\u001a\u00020\u0002\u0012\b\b\u0002\u0010#\u001a\u00020\u0002\u0012\b\b\u0002\u0010$\u001a\u00020\u0002\u0012\b\b\u0002\u0010%\u001a\u00020\u0002¢\u0006\u0004\b&\u0010'J\u0010\u0010*\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b(\u0010)J\u0010\u0010,\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b+\u0010)J\u0010\u0010.\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b-\u0010)J\u0010\u00100\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b/\u0010)J\u0010\u00102\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b1\u0010)J\u0010\u00104\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b3\u0010)J\u0010\u00106\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b5\u0010)J\u0010\u00108\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b7\u0010)J\u0010\u0010:\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b9\u0010)J\u0010\u0010<\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b;\u0010)J\u0010\u0010>\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b=\u0010)J\u0010\u0010@\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b?\u0010)J\u0010\u0010B\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bA\u0010)J\u0010\u0010D\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bC\u0010)J\u0010\u0010F\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bE\u0010)J\u0010\u0010H\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bG\u0010)J\u0010\u0010J\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bI\u0010)J\u0010\u0010L\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bK\u0010)J\u0010\u0010N\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bM\u0010)J\u0010\u0010P\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bO\u0010)J\u0010\u0010R\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bQ\u0010)J\u0010\u0010T\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bS\u0010)J\u0010\u0010V\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bU\u0010)J\u0010\u0010X\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bW\u0010)J\u0010\u0010Z\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bY\u0010)J\u0010\u0010\\\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b[\u0010)J\u0010\u0010^\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b]\u0010)J\u0010\u0010`\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b_\u0010)J\u0010\u0010b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\ba\u0010)J\u0010\u0010d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bc\u0010)J\u0010\u0010f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\be\u0010)J\u0010\u0010h\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bg\u0010)J\u0010\u0010j\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bi\u0010)J\u0010\u0010l\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bk\u0010)J\u0010\u0010n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\bm\u0010)Jî\u0002\u0010q\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020\u00022\b\b\u0002\u0010 \u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020\u00022\b\b\u0002\u0010#\u001a\u00020\u00022\b\b\u0002\u0010$\u001a\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\bo\u0010pJ\u0010\u0010s\u001a\u00020rHÖ\u0001¢\u0006\u0004\bs\u0010tJ\u0010\u0010v\u001a\u00020uHÖ\u0001¢\u0006\u0004\bv\u0010wJ\u001a\u0010z\u001a\u00020y2\b\u0010x\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bz\u0010{R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010|\u001a\u0004\b}\u0010)R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010|\u001a\u0004\b~\u0010)R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010|\u001a\u0004\b\u007f\u0010)R\u0018\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0006\u0010|\u001a\u0005\b\u0080\u0001\u0010)R\u0018\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0007\u0010|\u001a\u0005\b\u0081\u0001\u0010)R\u0018\u0010\b\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\b\u0010|\u001a\u0005\b\u0082\u0001\u0010)R\u0018\u0010\t\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\t\u0010|\u001a\u0005\b\u0083\u0001\u0010)R\u0018\u0010\n\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\n\u0010|\u001a\u0005\b\u0084\u0001\u0010)R\u0018\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u000b\u0010|\u001a\u0005\b\u0085\u0001\u0010)R\u0018\u0010\f\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\f\u0010|\u001a\u0005\b\u0086\u0001\u0010)R\u0018\u0010\r\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\r\u0010|\u001a\u0005\b\u0087\u0001\u0010)R\u0018\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u000e\u0010|\u001a\u0005\b\u0088\u0001\u0010)R\u0018\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u000f\u0010|\u001a\u0005\b\u0089\u0001\u0010)R\u0018\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0010\u0010|\u001a\u0005\b\u008a\u0001\u0010)R\u0018\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0011\u0010|\u001a\u0005\b\u008b\u0001\u0010)R\u0018\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0012\u0010|\u001a\u0005\b\u008c\u0001\u0010)R\u0018\u0010\u0013\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0013\u0010|\u001a\u0005\b\u008d\u0001\u0010)R\u0018\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0014\u0010|\u001a\u0005\b\u008e\u0001\u0010)R\u0018\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0015\u0010|\u001a\u0005\b\u008f\u0001\u0010)R\u0018\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0016\u0010|\u001a\u0005\b\u0090\u0001\u0010)R\u0018\u0010\u0017\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0017\u0010|\u001a\u0005\b\u0091\u0001\u0010)R\u0018\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0018\u0010|\u001a\u0005\b\u0092\u0001\u0010)R\u0018\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u0019\u0010|\u001a\u0005\b\u0093\u0001\u0010)R\u0018\u0010\u001a\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u001a\u0010|\u001a\u0005\b\u0094\u0001\u0010)R\u0018\u0010\u001b\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u001b\u0010|\u001a\u0005\b\u0095\u0001\u0010)R\u0018\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u001c\u0010|\u001a\u0005\b\u0096\u0001\u0010)R\u0018\u0010\u001d\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u001d\u0010|\u001a\u0005\b\u0097\u0001\u0010)R\u0018\u0010\u001e\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u001e\u0010|\u001a\u0005\b\u0098\u0001\u0010)R\u0018\u0010\u001f\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u001f\u0010|\u001a\u0005\b\u0099\u0001\u0010)R\u0018\u0010 \u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b \u0010|\u001a\u0005\b\u009a\u0001\u0010)R\u0018\u0010!\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b!\u0010|\u001a\u0005\b\u009b\u0001\u0010)R\u0018\u0010\"\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\"\u0010|\u001a\u0005\b\u009c\u0001\u0010)R\u0018\u0010#\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b#\u0010|\u001a\u0005\b\u009d\u0001\u0010)R\u0018\u0010$\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b$\u0010|\u001a\u0005\b\u009e\u0001\u0010)R\u0018\u0010%\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b%\u0010|\u001a\u0005\b\u009f\u0001\u0010)¨\u0006 \u0001"}, d2 = {"Lcom/arflix/tv/ui/theme/ArvioColors;", "", "Landroidx/compose/ui/graphics/Color;", "arcticWhite", "arcticWhite90", "arcticWhite70", "arcticWhite50", "arcticBlack", "arcticGray", "cyan", "cyanDark", "cyanGlow", "purple", "purpleDark", "purpleGlow", "pink", "pinkDark", "pinkGlow", "backgroundDark", "backgroundCard", "backgroundElevated", "backgroundGlass", "textPrimary", "textSecondary", "textTertiary", "borderLight", "borderGradient", "success", "error", "warning", "info", "imdbYellow", "accentRed", "focusRing", "focusGlow", "particleCyan", "particlePurple", "particlePink", "<init>", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/h;)V", "component1-0d7_KjU", "()J", "component1", "component2-0d7_KjU", "component2", "component3-0d7_KjU", "component3", "component4-0d7_KjU", "component4", "component5-0d7_KjU", "component5", "component6-0d7_KjU", "component6", "component7-0d7_KjU", "component7", "component8-0d7_KjU", "component8", "component9-0d7_KjU", "component9", "component10-0d7_KjU", "component10", "component11-0d7_KjU", "component11", "component12-0d7_KjU", "component12", "component13-0d7_KjU", "component13", "component14-0d7_KjU", "component14", "component15-0d7_KjU", "component15", "component16-0d7_KjU", "component16", "component17-0d7_KjU", "component17", "component18-0d7_KjU", "component18", "component19-0d7_KjU", "component19", "component20-0d7_KjU", "component20", "component21-0d7_KjU", "component21", "component22-0d7_KjU", "component22", "component23-0d7_KjU", "component23", "component24-0d7_KjU", "component24", "component25-0d7_KjU", "component25", "component26-0d7_KjU", "component26", "component27-0d7_KjU", "component27", "component28-0d7_KjU", "component28", "component29-0d7_KjU", "component29", "component30-0d7_KjU", "component30", "component31-0d7_KjU", "component31", "component32-0d7_KjU", "component32", "component33-0d7_KjU", "component33", "component34-0d7_KjU", "component34", "component35-0d7_KjU", "component35", "copy-IWRRXjM", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Lcom/arflix/tv/ui/theme/ArvioColors;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getArcticWhite-0d7_KjU", "getArcticWhite90-0d7_KjU", "getArcticWhite70-0d7_KjU", "getArcticWhite50-0d7_KjU", "getArcticBlack-0d7_KjU", "getArcticGray-0d7_KjU", "getCyan-0d7_KjU", "getCyanDark-0d7_KjU", "getCyanGlow-0d7_KjU", "getPurple-0d7_KjU", "getPurpleDark-0d7_KjU", "getPurpleGlow-0d7_KjU", "getPink-0d7_KjU", "getPinkDark-0d7_KjU", "getPinkGlow-0d7_KjU", "getBackgroundDark-0d7_KjU", "getBackgroundCard-0d7_KjU", "getBackgroundElevated-0d7_KjU", "getBackgroundGlass-0d7_KjU", "getTextPrimary-0d7_KjU", "getTextSecondary-0d7_KjU", "getTextTertiary-0d7_KjU", "getBorderLight-0d7_KjU", "getBorderGradient-0d7_KjU", "getSuccess-0d7_KjU", "getError-0d7_KjU", "getWarning-0d7_KjU", "getInfo-0d7_KjU", "getImdbYellow-0d7_KjU", "getAccentRed-0d7_KjU", "getFocusRing-0d7_KjU", "getFocusGlow-0d7_KjU", "getParticleCyan-0d7_KjU", "getParticlePurple-0d7_KjU", "getParticlePink-0d7_KjU", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArvioColors {
    public static final int $stable = 0;
    private final long accentRed;
    private final long arcticBlack;
    private final long arcticGray;
    private final long arcticWhite;
    private final long arcticWhite50;
    private final long arcticWhite70;
    private final long arcticWhite90;
    private final long backgroundCard;
    private final long backgroundDark;
    private final long backgroundElevated;
    private final long backgroundGlass;
    private final long borderGradient;
    private final long borderLight;
    private final long cyan;
    private final long cyanDark;
    private final long cyanGlow;
    private final long error;
    private final long focusGlow;
    private final long focusRing;
    private final long imdbYellow;
    private final long info;
    private final long particleCyan;
    private final long particlePink;
    private final long particlePurple;
    private final long pink;
    private final long pinkDark;
    private final long pinkGlow;
    private final long purple;
    private final long purpleDark;
    private final long purpleGlow;
    private final long success;
    private final long textPrimary;
    private final long textSecondary;
    private final long textTertiary;
    private final long warning;

    public /* synthetic */ ArvioColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42, j43, j44);
    }

    /* JADX INFO: renamed from: copy-IWRRXjM$default, reason: not valid java name */
    public static /* synthetic */ ArvioColors m6539copyIWRRXjM$default(ArvioColors arvioColors, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, int i10, int i11, Object obj) {
        long j45;
        long j46;
        long j47;
        long j48;
        long j49;
        long j50;
        long j51;
        long j52;
        long j53;
        long j54;
        long j55;
        long j56;
        long j57;
        long j58;
        long j59;
        long j60;
        long j61;
        long j62;
        long j63;
        long j64;
        long j65;
        long j66;
        long j67;
        long j68;
        long j69;
        long j70;
        long j71;
        long j72;
        long j73;
        long j74;
        ArvioColors arvioColors2;
        long j75;
        long j76;
        long j77;
        long j78;
        long j79 = (i10 & 1) != 0 ? arvioColors.arcticWhite : j10;
        long j80 = (i10 & 2) != 0 ? arvioColors.arcticWhite90 : j11;
        long j81 = (i10 & 4) != 0 ? arvioColors.arcticWhite70 : j12;
        long j82 = (i10 & 8) != 0 ? arvioColors.arcticWhite50 : j13;
        long j83 = (i10 & 16) != 0 ? arvioColors.arcticBlack : j14;
        long j84 = (i10 & 32) != 0 ? arvioColors.arcticGray : j15;
        long j85 = (i10 & 64) != 0 ? arvioColors.cyan : j16;
        long j86 = j79;
        long j87 = (i10 & 128) != 0 ? arvioColors.cyanDark : j17;
        long j88 = (i10 & 256) != 0 ? arvioColors.cyanGlow : j18;
        long j89 = (i10 & 512) != 0 ? arvioColors.purple : j19;
        long j90 = (i10 & 1024) != 0 ? arvioColors.purpleDark : j20;
        long j91 = (i10 & 2048) != 0 ? arvioColors.purpleGlow : j21;
        long j92 = (i10 & 4096) != 0 ? arvioColors.pink : j22;
        long j93 = (i10 & 8192) != 0 ? arvioColors.pinkDark : j23;
        long j94 = (i10 & 16384) != 0 ? arvioColors.pinkGlow : j24;
        long j95 = (i10 & 32768) != 0 ? arvioColors.backgroundDark : j25;
        long j96 = (i10 & 65536) != 0 ? arvioColors.backgroundCard : j26;
        long j97 = (i10 & 131072) != 0 ? arvioColors.backgroundElevated : j27;
        long j98 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? arvioColors.backgroundGlass : j28;
        long j99 = (i10 & 524288) != 0 ? arvioColors.textPrimary : j29;
        long j100 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? arvioColors.textSecondary : j30;
        long j101 = (i10 & 2097152) != 0 ? arvioColors.textTertiary : j31;
        long j102 = (i10 & 4194304) != 0 ? arvioColors.borderLight : j32;
        long j103 = (i10 & 8388608) != 0 ? arvioColors.borderGradient : j33;
        long j104 = (i10 & 16777216) != 0 ? arvioColors.success : j34;
        long j105 = (i10 & 33554432) != 0 ? arvioColors.error : j35;
        long j106 = (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? arvioColors.warning : j36;
        long j107 = (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? arvioColors.info : j37;
        long j108 = (i10 & 268435456) != 0 ? arvioColors.imdbYellow : j38;
        long j109 = (i10 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? arvioColors.accentRed : j39;
        long j110 = (i10 & 1073741824) != 0 ? arvioColors.focusRing : j40;
        long j111 = (i10 & Integer.MIN_VALUE) != 0 ? arvioColors.focusGlow : j41;
        long j112 = (i11 & 1) != 0 ? arvioColors.particleCyan : j42;
        long j113 = (i11 & 2) != 0 ? arvioColors.particlePurple : j43;
        if ((i11 & 4) != 0) {
            j46 = j113;
            j45 = arvioColors.particlePink;
            j48 = j107;
            j49 = j108;
            j50 = j109;
            j51 = j110;
            j52 = j111;
            j53 = j112;
            j55 = j100;
            j56 = j101;
            j57 = j102;
            j58 = j103;
            j59 = j104;
            j60 = j105;
            j47 = j106;
            j62 = j93;
            j63 = j94;
            j64 = j95;
            j65 = j96;
            j66 = j97;
            j67 = j98;
            j54 = j99;
            j69 = j85;
            j70 = j87;
            j71 = j88;
            j72 = j89;
            j73 = j90;
            j74 = j91;
            j61 = j92;
            arvioColors2 = arvioColors;
            j75 = j80;
            j76 = j81;
            j77 = j82;
            j78 = j83;
            j68 = j84;
        } else {
            j45 = j44;
            j46 = j113;
            j47 = j106;
            j48 = j107;
            j49 = j108;
            j50 = j109;
            j51 = j110;
            j52 = j111;
            j53 = j112;
            j54 = j99;
            j55 = j100;
            j56 = j101;
            j57 = j102;
            j58 = j103;
            j59 = j104;
            j60 = j105;
            j61 = j92;
            j62 = j93;
            j63 = j94;
            j64 = j95;
            j65 = j96;
            j66 = j97;
            j67 = j98;
            j68 = j84;
            j69 = j85;
            j70 = j87;
            j71 = j88;
            j72 = j89;
            j73 = j90;
            j74 = j91;
            arvioColors2 = arvioColors;
            j75 = j80;
            j76 = j81;
            j77 = j82;
            j78 = j83;
        }
        return arvioColors2.m6575copyIWRRXjM(j86, j75, j76, j77, j78, j68, j69, j70, j71, j72, j73, j74, j61, j62, j63, j64, j65, j66, j67, j54, j55, j56, j57, j58, j59, j60, j47, j48, j49, j50, j51, j52, j53, j46, j45);
    }

    /* JADX INFO: renamed from: component1-0d7_KjU, reason: not valid java name and from getter */
    public final long getArcticWhite() {
        return this.arcticWhite;
    }

    /* JADX INFO: renamed from: component10-0d7_KjU, reason: not valid java name and from getter */
    public final long getPurple() {
        return this.purple;
    }

    /* JADX INFO: renamed from: component11-0d7_KjU, reason: not valid java name and from getter */
    public final long getPurpleDark() {
        return this.purpleDark;
    }

    /* JADX INFO: renamed from: component12-0d7_KjU, reason: not valid java name and from getter */
    public final long getPurpleGlow() {
        return this.purpleGlow;
    }

    /* JADX INFO: renamed from: component13-0d7_KjU, reason: not valid java name and from getter */
    public final long getPink() {
        return this.pink;
    }

    /* JADX INFO: renamed from: component14-0d7_KjU, reason: not valid java name and from getter */
    public final long getPinkDark() {
        return this.pinkDark;
    }

    /* JADX INFO: renamed from: component15-0d7_KjU, reason: not valid java name and from getter */
    public final long getPinkGlow() {
        return this.pinkGlow;
    }

    /* JADX INFO: renamed from: component16-0d7_KjU, reason: not valid java name and from getter */
    public final long getBackgroundDark() {
        return this.backgroundDark;
    }

    /* JADX INFO: renamed from: component17-0d7_KjU, reason: not valid java name and from getter */
    public final long getBackgroundCard() {
        return this.backgroundCard;
    }

    /* JADX INFO: renamed from: component18-0d7_KjU, reason: not valid java name and from getter */
    public final long getBackgroundElevated() {
        return this.backgroundElevated;
    }

    /* JADX INFO: renamed from: component19-0d7_KjU, reason: not valid java name and from getter */
    public final long getBackgroundGlass() {
        return this.backgroundGlass;
    }

    /* JADX INFO: renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
    public final long getArcticWhite90() {
        return this.arcticWhite90;
    }

    /* JADX INFO: renamed from: component20-0d7_KjU, reason: not valid java name and from getter */
    public final long getTextPrimary() {
        return this.textPrimary;
    }

    /* JADX INFO: renamed from: component21-0d7_KjU, reason: not valid java name and from getter */
    public final long getTextSecondary() {
        return this.textSecondary;
    }

    /* JADX INFO: renamed from: component22-0d7_KjU, reason: not valid java name and from getter */
    public final long getTextTertiary() {
        return this.textTertiary;
    }

    /* JADX INFO: renamed from: component23-0d7_KjU, reason: not valid java name and from getter */
    public final long getBorderLight() {
        return this.borderLight;
    }

    /* JADX INFO: renamed from: component24-0d7_KjU, reason: not valid java name and from getter */
    public final long getBorderGradient() {
        return this.borderGradient;
    }

    /* JADX INFO: renamed from: component25-0d7_KjU, reason: not valid java name and from getter */
    public final long getSuccess() {
        return this.success;
    }

    /* JADX INFO: renamed from: component26-0d7_KjU, reason: not valid java name and from getter */
    public final long getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component27-0d7_KjU, reason: not valid java name and from getter */
    public final long getWarning() {
        return this.warning;
    }

    /* JADX INFO: renamed from: component28-0d7_KjU, reason: not valid java name and from getter */
    public final long getInfo() {
        return this.info;
    }

    /* JADX INFO: renamed from: component29-0d7_KjU, reason: not valid java name and from getter */
    public final long getImdbYellow() {
        return this.imdbYellow;
    }

    /* JADX INFO: renamed from: component3-0d7_KjU, reason: not valid java name and from getter */
    public final long getArcticWhite70() {
        return this.arcticWhite70;
    }

    /* JADX INFO: renamed from: component30-0d7_KjU, reason: not valid java name and from getter */
    public final long getAccentRed() {
        return this.accentRed;
    }

    /* JADX INFO: renamed from: component31-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusRing() {
        return this.focusRing;
    }

    /* JADX INFO: renamed from: component32-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusGlow() {
        return this.focusGlow;
    }

    /* JADX INFO: renamed from: component33-0d7_KjU, reason: not valid java name and from getter */
    public final long getParticleCyan() {
        return this.particleCyan;
    }

    /* JADX INFO: renamed from: component34-0d7_KjU, reason: not valid java name and from getter */
    public final long getParticlePurple() {
        return this.particlePurple;
    }

    /* JADX INFO: renamed from: component35-0d7_KjU, reason: not valid java name and from getter */
    public final long getParticlePink() {
        return this.particlePink;
    }

    /* JADX INFO: renamed from: component4-0d7_KjU, reason: not valid java name and from getter */
    public final long getArcticWhite50() {
        return this.arcticWhite50;
    }

    /* JADX INFO: renamed from: component5-0d7_KjU, reason: not valid java name and from getter */
    public final long getArcticBlack() {
        return this.arcticBlack;
    }

    /* JADX INFO: renamed from: component6-0d7_KjU, reason: not valid java name and from getter */
    public final long getArcticGray() {
        return this.arcticGray;
    }

    /* JADX INFO: renamed from: component7-0d7_KjU, reason: not valid java name and from getter */
    public final long getCyan() {
        return this.cyan;
    }

    /* JADX INFO: renamed from: component8-0d7_KjU, reason: not valid java name and from getter */
    public final long getCyanDark() {
        return this.cyanDark;
    }

    /* JADX INFO: renamed from: component9-0d7_KjU, reason: not valid java name and from getter */
    public final long getCyanGlow() {
        return this.cyanGlow;
    }

    /* JADX INFO: renamed from: copy-IWRRXjM, reason: not valid java name */
    public final ArvioColors m6575copyIWRRXjM(long arcticWhite, long arcticWhite90, long arcticWhite70, long arcticWhite50, long arcticBlack, long arcticGray, long cyan, long cyanDark, long cyanGlow, long purple, long purpleDark, long purpleGlow, long pink, long pinkDark, long pinkGlow, long backgroundDark, long backgroundCard, long backgroundElevated, long backgroundGlass, long textPrimary, long textSecondary, long textTertiary, long borderLight, long borderGradient, long success, long error, long warning, long info, long imdbYellow, long accentRed, long focusRing, long focusGlow, long particleCyan, long particlePurple, long particlePink) {
        return new ArvioColors(arcticWhite, arcticWhite90, arcticWhite70, arcticWhite50, arcticBlack, arcticGray, cyan, cyanDark, cyanGlow, purple, purpleDark, purpleGlow, pink, pinkDark, pinkGlow, backgroundDark, backgroundCard, backgroundElevated, backgroundGlass, textPrimary, textSecondary, textTertiary, borderLight, borderGradient, success, error, warning, info, imdbYellow, accentRed, focusRing, focusGlow, particleCyan, particlePurple, particlePink, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArvioColors)) {
            return false;
        }
        ArvioColors arvioColors = (ArvioColors) other;
        return Color.m3473equalsimpl0(this.arcticWhite, arvioColors.arcticWhite) && Color.m3473equalsimpl0(this.arcticWhite90, arvioColors.arcticWhite90) && Color.m3473equalsimpl0(this.arcticWhite70, arvioColors.arcticWhite70) && Color.m3473equalsimpl0(this.arcticWhite50, arvioColors.arcticWhite50) && Color.m3473equalsimpl0(this.arcticBlack, arvioColors.arcticBlack) && Color.m3473equalsimpl0(this.arcticGray, arvioColors.arcticGray) && Color.m3473equalsimpl0(this.cyan, arvioColors.cyan) && Color.m3473equalsimpl0(this.cyanDark, arvioColors.cyanDark) && Color.m3473equalsimpl0(this.cyanGlow, arvioColors.cyanGlow) && Color.m3473equalsimpl0(this.purple, arvioColors.purple) && Color.m3473equalsimpl0(this.purpleDark, arvioColors.purpleDark) && Color.m3473equalsimpl0(this.purpleGlow, arvioColors.purpleGlow) && Color.m3473equalsimpl0(this.pink, arvioColors.pink) && Color.m3473equalsimpl0(this.pinkDark, arvioColors.pinkDark) && Color.m3473equalsimpl0(this.pinkGlow, arvioColors.pinkGlow) && Color.m3473equalsimpl0(this.backgroundDark, arvioColors.backgroundDark) && Color.m3473equalsimpl0(this.backgroundCard, arvioColors.backgroundCard) && Color.m3473equalsimpl0(this.backgroundElevated, arvioColors.backgroundElevated) && Color.m3473equalsimpl0(this.backgroundGlass, arvioColors.backgroundGlass) && Color.m3473equalsimpl0(this.textPrimary, arvioColors.textPrimary) && Color.m3473equalsimpl0(this.textSecondary, arvioColors.textSecondary) && Color.m3473equalsimpl0(this.textTertiary, arvioColors.textTertiary) && Color.m3473equalsimpl0(this.borderLight, arvioColors.borderLight) && Color.m3473equalsimpl0(this.borderGradient, arvioColors.borderGradient) && Color.m3473equalsimpl0(this.success, arvioColors.success) && Color.m3473equalsimpl0(this.error, arvioColors.error) && Color.m3473equalsimpl0(this.warning, arvioColors.warning) && Color.m3473equalsimpl0(this.info, arvioColors.info) && Color.m3473equalsimpl0(this.imdbYellow, arvioColors.imdbYellow) && Color.m3473equalsimpl0(this.accentRed, arvioColors.accentRed) && Color.m3473equalsimpl0(this.focusRing, arvioColors.focusRing) && Color.m3473equalsimpl0(this.focusGlow, arvioColors.focusGlow) && Color.m3473equalsimpl0(this.particleCyan, arvioColors.particleCyan) && Color.m3473equalsimpl0(this.particlePurple, arvioColors.particlePurple) && Color.m3473equalsimpl0(this.particlePink, arvioColors.particlePink);
    }

    /* JADX INFO: renamed from: getAccentRed-0d7_KjU, reason: not valid java name */
    public final long m6576getAccentRed0d7_KjU() {
        return this.accentRed;
    }

    /* JADX INFO: renamed from: getArcticBlack-0d7_KjU, reason: not valid java name */
    public final long m6577getArcticBlack0d7_KjU() {
        return this.arcticBlack;
    }

    /* JADX INFO: renamed from: getArcticGray-0d7_KjU, reason: not valid java name */
    public final long m6578getArcticGray0d7_KjU() {
        return this.arcticGray;
    }

    /* JADX INFO: renamed from: getArcticWhite-0d7_KjU, reason: not valid java name */
    public final long m6579getArcticWhite0d7_KjU() {
        return this.arcticWhite;
    }

    /* JADX INFO: renamed from: getArcticWhite50-0d7_KjU, reason: not valid java name */
    public final long m6580getArcticWhite500d7_KjU() {
        return this.arcticWhite50;
    }

    /* JADX INFO: renamed from: getArcticWhite70-0d7_KjU, reason: not valid java name */
    public final long m6581getArcticWhite700d7_KjU() {
        return this.arcticWhite70;
    }

    /* JADX INFO: renamed from: getArcticWhite90-0d7_KjU, reason: not valid java name */
    public final long m6582getArcticWhite900d7_KjU() {
        return this.arcticWhite90;
    }

    /* JADX INFO: renamed from: getBackgroundCard-0d7_KjU, reason: not valid java name */
    public final long m6583getBackgroundCard0d7_KjU() {
        return this.backgroundCard;
    }

    /* JADX INFO: renamed from: getBackgroundDark-0d7_KjU, reason: not valid java name */
    public final long m6584getBackgroundDark0d7_KjU() {
        return this.backgroundDark;
    }

    /* JADX INFO: renamed from: getBackgroundElevated-0d7_KjU, reason: not valid java name */
    public final long m6585getBackgroundElevated0d7_KjU() {
        return this.backgroundElevated;
    }

    /* JADX INFO: renamed from: getBackgroundGlass-0d7_KjU, reason: not valid java name */
    public final long m6586getBackgroundGlass0d7_KjU() {
        return this.backgroundGlass;
    }

    /* JADX INFO: renamed from: getBorderGradient-0d7_KjU, reason: not valid java name */
    public final long m6587getBorderGradient0d7_KjU() {
        return this.borderGradient;
    }

    /* JADX INFO: renamed from: getBorderLight-0d7_KjU, reason: not valid java name */
    public final long m6588getBorderLight0d7_KjU() {
        return this.borderLight;
    }

    /* JADX INFO: renamed from: getCyan-0d7_KjU, reason: not valid java name */
    public final long m6589getCyan0d7_KjU() {
        return this.cyan;
    }

    /* JADX INFO: renamed from: getCyanDark-0d7_KjU, reason: not valid java name */
    public final long m6590getCyanDark0d7_KjU() {
        return this.cyanDark;
    }

    /* JADX INFO: renamed from: getCyanGlow-0d7_KjU, reason: not valid java name */
    public final long m6591getCyanGlow0d7_KjU() {
        return this.cyanGlow;
    }

    /* JADX INFO: renamed from: getError-0d7_KjU, reason: not valid java name */
    public final long m6592getError0d7_KjU() {
        return this.error;
    }

    /* JADX INFO: renamed from: getFocusGlow-0d7_KjU, reason: not valid java name */
    public final long m6593getFocusGlow0d7_KjU() {
        return this.focusGlow;
    }

    /* JADX INFO: renamed from: getFocusRing-0d7_KjU, reason: not valid java name */
    public final long m6594getFocusRing0d7_KjU() {
        return this.focusRing;
    }

    /* JADX INFO: renamed from: getImdbYellow-0d7_KjU, reason: not valid java name */
    public final long m6595getImdbYellow0d7_KjU() {
        return this.imdbYellow;
    }

    /* JADX INFO: renamed from: getInfo-0d7_KjU, reason: not valid java name */
    public final long m6596getInfo0d7_KjU() {
        return this.info;
    }

    /* JADX INFO: renamed from: getParticleCyan-0d7_KjU, reason: not valid java name */
    public final long m6597getParticleCyan0d7_KjU() {
        return this.particleCyan;
    }

    /* JADX INFO: renamed from: getParticlePink-0d7_KjU, reason: not valid java name */
    public final long m6598getParticlePink0d7_KjU() {
        return this.particlePink;
    }

    /* JADX INFO: renamed from: getParticlePurple-0d7_KjU, reason: not valid java name */
    public final long m6599getParticlePurple0d7_KjU() {
        return this.particlePurple;
    }

    /* JADX INFO: renamed from: getPink-0d7_KjU, reason: not valid java name */
    public final long m6600getPink0d7_KjU() {
        return this.pink;
    }

    /* JADX INFO: renamed from: getPinkDark-0d7_KjU, reason: not valid java name */
    public final long m6601getPinkDark0d7_KjU() {
        return this.pinkDark;
    }

    /* JADX INFO: renamed from: getPinkGlow-0d7_KjU, reason: not valid java name */
    public final long m6602getPinkGlow0d7_KjU() {
        return this.pinkGlow;
    }

    /* JADX INFO: renamed from: getPurple-0d7_KjU, reason: not valid java name */
    public final long m6603getPurple0d7_KjU() {
        return this.purple;
    }

    /* JADX INFO: renamed from: getPurpleDark-0d7_KjU, reason: not valid java name */
    public final long m6604getPurpleDark0d7_KjU() {
        return this.purpleDark;
    }

    /* JADX INFO: renamed from: getPurpleGlow-0d7_KjU, reason: not valid java name */
    public final long m6605getPurpleGlow0d7_KjU() {
        return this.purpleGlow;
    }

    /* JADX INFO: renamed from: getSuccess-0d7_KjU, reason: not valid java name */
    public final long m6606getSuccess0d7_KjU() {
        return this.success;
    }

    /* JADX INFO: renamed from: getTextPrimary-0d7_KjU, reason: not valid java name */
    public final long m6607getTextPrimary0d7_KjU() {
        return this.textPrimary;
    }

    /* JADX INFO: renamed from: getTextSecondary-0d7_KjU, reason: not valid java name */
    public final long m6608getTextSecondary0d7_KjU() {
        return this.textSecondary;
    }

    /* JADX INFO: renamed from: getTextTertiary-0d7_KjU, reason: not valid java name */
    public final long m6609getTextTertiary0d7_KjU() {
        return this.textTertiary;
    }

    /* JADX INFO: renamed from: getWarning-0d7_KjU, reason: not valid java name */
    public final long m6610getWarning0d7_KjU() {
        return this.warning;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.particlePink) + c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(c.a(Color.m3479hashCodeimpl(this.arcticWhite) * 31, 31, this.arcticWhite90), 31, this.arcticWhite70), 31, this.arcticWhite50), 31, this.arcticBlack), 31, this.arcticGray), 31, this.cyan), 31, this.cyanDark), 31, this.cyanGlow), 31, this.purple), 31, this.purpleDark), 31, this.purpleGlow), 31, this.pink), 31, this.pinkDark), 31, this.pinkGlow), 31, this.backgroundDark), 31, this.backgroundCard), 31, this.backgroundElevated), 31, this.backgroundGlass), 31, this.textPrimary), 31, this.textSecondary), 31, this.textTertiary), 31, this.borderLight), 31, this.borderGradient), 31, this.success), 31, this.error), 31, this.warning), 31, this.info), 31, this.imdbYellow), 31, this.accentRed), 31, this.focusRing), 31, this.focusGlow), 31, this.particleCyan), 31, this.particlePurple);
    }

    public String toString() {
        String strM3480toStringimpl = Color.m3480toStringimpl(this.arcticWhite);
        String strM3480toStringimpl2 = Color.m3480toStringimpl(this.arcticWhite90);
        String strM3480toStringimpl3 = Color.m3480toStringimpl(this.arcticWhite70);
        String strM3480toStringimpl4 = Color.m3480toStringimpl(this.arcticWhite50);
        String strM3480toStringimpl5 = Color.m3480toStringimpl(this.arcticBlack);
        String strM3480toStringimpl6 = Color.m3480toStringimpl(this.arcticGray);
        String strM3480toStringimpl7 = Color.m3480toStringimpl(this.cyan);
        String strM3480toStringimpl8 = Color.m3480toStringimpl(this.cyanDark);
        String strM3480toStringimpl9 = Color.m3480toStringimpl(this.cyanGlow);
        String strM3480toStringimpl10 = Color.m3480toStringimpl(this.purple);
        String strM3480toStringimpl11 = Color.m3480toStringimpl(this.purpleDark);
        String strM3480toStringimpl12 = Color.m3480toStringimpl(this.purpleGlow);
        String strM3480toStringimpl13 = Color.m3480toStringimpl(this.pink);
        String strM3480toStringimpl14 = Color.m3480toStringimpl(this.pinkDark);
        String strM3480toStringimpl15 = Color.m3480toStringimpl(this.pinkGlow);
        String strM3480toStringimpl16 = Color.m3480toStringimpl(this.backgroundDark);
        String strM3480toStringimpl17 = Color.m3480toStringimpl(this.backgroundCard);
        String strM3480toStringimpl18 = Color.m3480toStringimpl(this.backgroundElevated);
        String strM3480toStringimpl19 = Color.m3480toStringimpl(this.backgroundGlass);
        String strM3480toStringimpl20 = Color.m3480toStringimpl(this.textPrimary);
        String strM3480toStringimpl21 = Color.m3480toStringimpl(this.textSecondary);
        String strM3480toStringimpl22 = Color.m3480toStringimpl(this.textTertiary);
        String strM3480toStringimpl23 = Color.m3480toStringimpl(this.borderLight);
        String strM3480toStringimpl24 = Color.m3480toStringimpl(this.borderGradient);
        String strM3480toStringimpl25 = Color.m3480toStringimpl(this.success);
        String strM3480toStringimpl26 = Color.m3480toStringimpl(this.error);
        String strM3480toStringimpl27 = Color.m3480toStringimpl(this.warning);
        String strM3480toStringimpl28 = Color.m3480toStringimpl(this.info);
        String strM3480toStringimpl29 = Color.m3480toStringimpl(this.imdbYellow);
        String strM3480toStringimpl30 = Color.m3480toStringimpl(this.accentRed);
        String strM3480toStringimpl31 = Color.m3480toStringimpl(this.focusRing);
        String strM3480toStringimpl32 = Color.m3480toStringimpl(this.focusGlow);
        String strM3480toStringimpl33 = Color.m3480toStringimpl(this.particleCyan);
        String strM3480toStringimpl34 = Color.m3480toStringimpl(this.particlePurple);
        String strM3480toStringimpl35 = Color.m3480toStringimpl(this.particlePink);
        StringBuilder sbR = a2.r("ArvioColors(arcticWhite=", strM3480toStringimpl, ", arcticWhite90=", strM3480toStringimpl2, ", arcticWhite70=");
        a.i(sbR, strM3480toStringimpl3, ", arcticWhite50=", strM3480toStringimpl4, ", arcticBlack=");
        a.i(sbR, strM3480toStringimpl5, ", arcticGray=", strM3480toStringimpl6, ", cyan=");
        a.i(sbR, strM3480toStringimpl7, ", cyanDark=", strM3480toStringimpl8, ", cyanGlow=");
        a.i(sbR, strM3480toStringimpl9, ", purple=", strM3480toStringimpl10, ", purpleDark=");
        a.i(sbR, strM3480toStringimpl11, ", purpleGlow=", strM3480toStringimpl12, ", pink=");
        a.i(sbR, strM3480toStringimpl13, ", pinkDark=", strM3480toStringimpl14, ", pinkGlow=");
        a.i(sbR, strM3480toStringimpl15, ", backgroundDark=", strM3480toStringimpl16, ", backgroundCard=");
        a.i(sbR, strM3480toStringimpl17, ", backgroundElevated=", strM3480toStringimpl18, ", backgroundGlass=");
        a.i(sbR, strM3480toStringimpl19, ", textPrimary=", strM3480toStringimpl20, ", textSecondary=");
        a.i(sbR, strM3480toStringimpl21, ", textTertiary=", strM3480toStringimpl22, ", borderLight=");
        a.i(sbR, strM3480toStringimpl23, ", borderGradient=", strM3480toStringimpl24, ", success=");
        a.i(sbR, strM3480toStringimpl25, ", error=", strM3480toStringimpl26, ", warning=");
        a.i(sbR, strM3480toStringimpl27, ", info=", strM3480toStringimpl28, ", imdbYellow=");
        a.i(sbR, strM3480toStringimpl29, ", accentRed=", strM3480toStringimpl30, ", focusRing=");
        a.i(sbR, strM3480toStringimpl31, ", focusGlow=", strM3480toStringimpl32, ", particleCyan=");
        a.i(sbR, strM3480toStringimpl33, ", particlePurple=", strM3480toStringimpl34, ", particlePink=");
        return a0.c.p(sbR, strM3480toStringimpl35, ")");
    }

    private ArvioColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44) {
        this.arcticWhite = j10;
        this.arcticWhite90 = j11;
        this.arcticWhite70 = j12;
        this.arcticWhite50 = j13;
        this.arcticBlack = j14;
        this.arcticGray = j15;
        this.cyan = j16;
        this.cyanDark = j17;
        this.cyanGlow = j18;
        this.purple = j19;
        this.purpleDark = j20;
        this.purpleGlow = j21;
        this.pink = j22;
        this.pinkDark = j23;
        this.pinkGlow = j24;
        this.backgroundDark = j25;
        this.backgroundCard = j26;
        this.backgroundElevated = j27;
        this.backgroundGlass = j28;
        this.textPrimary = j29;
        this.textSecondary = j30;
        this.textTertiary = j31;
        this.borderLight = j32;
        this.borderGradient = j33;
        this.success = j34;
        this.error = j35;
        this.warning = j36;
        this.info = j37;
        this.imdbYellow = j38;
        this.accentRed = j39;
        this.focusRing = j40;
        this.focusGlow = j41;
        this.particleCyan = j42;
        this.particlePurple = j43;
        this.particlePink = j44;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ArvioColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, int i10, int i11, h hVar) {
        long arcticWhite = (i10 & 1) != 0 ? ColorKt.getArcticWhite() : j10;
        long arcticWhite90 = (i10 & 2) != 0 ? ColorKt.getArcticWhite90() : j11;
        long arcticWhite70 = (i10 & 4) != 0 ? ColorKt.getArcticWhite70() : j12;
        long arcticWhite50 = (i10 & 8) != 0 ? ColorKt.getArcticWhite50() : j13;
        long arcticBlack = (i10 & 16) != 0 ? ColorKt.getArcticBlack() : j14;
        long arcticGray = (i10 & 32) != 0 ? ColorKt.getArcticGray() : j15;
        long arcticWhite2 = (i10 & 64) != 0 ? ColorKt.getArcticWhite() : j16;
        long arcticGray2 = (i10 & 128) != 0 ? ColorKt.getArcticGray() : j17;
        this(arcticWhite, arcticWhite90, arcticWhite70, arcticWhite50, arcticBlack, arcticGray, arcticWhite2, arcticGray2, (i10 & 256) != 0 ? ColorKt.getFocusGlow() : j18, (i10 & 512) != 0 ? ColorKt.getArcticWhite() : j19, (i10 & 1024) != 0 ? ColorKt.getArcticGray() : j20, (i10 & 2048) != 0 ? ColorKt.getFocusGlow() : j21, (i10 & 4096) != 0 ? ColorKt.getAccentWhite() : j22, (i10 & 8192) != 0 ? ColorKt.getArcticGray() : j23, (i10 & 16384) != 0 ? ColorKt.getFocusGlow() : j24, (i10 & 32768) != 0 ? ColorKt.getBackgroundDark() : j25, (i10 & 65536) != 0 ? ColorKt.getBackgroundCard() : j26, (i10 & 131072) != 0 ? ColorKt.getBackgroundElevated() : j27, (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? ColorKt.getBackgroundGlass() : j28, (i10 & 524288) != 0 ? ColorKt.getTextPrimary() : j29, (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? ColorKt.getTextSecondary() : j30, (i10 & 2097152) != 0 ? ColorKt.getTextTertiary() : j31, (i10 & 4194304) != 0 ? ColorKt.getBorderLight() : j32, (i10 & 8388608) != 0 ? ColorKt.getBorderGradient() : j33, (i10 & 16777216) != 0 ? ColorKt.getSuccessGreen() : j34, (i10 & 33554432) != 0 ? ColorKt.getErrorRed() : j35, (i10 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? ColorKt.getWarningOrange() : j36, (i10 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? ColorKt.getInfoBlue() : j37, (i10 & 268435456) != 0 ? ColorKt.getImdbYellow() : j38, (i10 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? ColorKt.getAccentRed() : j39, (i10 & 1073741824) != 0 ? ColorKt.getFocusRing() : j40, (i10 & Integer.MIN_VALUE) != 0 ? ColorKt.getFocusGlow() : j41, (i11 & 1) != 0 ? ColorKt.getParticleCyan() : j42, (i11 & 2) != 0 ? ColorKt.getParticlePurple() : j43, (i11 & 4) != 0 ? ColorKt.getParticlePink() : j44, null);
    }
}
