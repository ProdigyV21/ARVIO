package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.WavUtil;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0083\u0001\u0010\u0010\u001a\u00020\f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032,\b\u0002\u0010\n\u001a&\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u0013*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a)\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001aQ\u0010!\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u000e2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0003¢\u0006\u0004\b!\u0010\"¨\u0006(²\u0006\u000e\u0010\u0012\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010#\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010'\u001a\u00020&8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channels", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "Lkotlin/Function2;", "Ld7/d;", "", "searchProvider", "Lkotlin/Function0;", "Lx6/t0;", "onDismiss", "Lkotlin/Function1;", "onPick", "SearchOverlay", "(Ljava/util/List;Ljava/util/Map;Lr7/p;Lr7/a;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "query", "Lcom/arflix/tv/data/model/IptvProgram;", "bestProgramMatch", "(Lcom/arflix/tv/data/model/IptvNowNext;Ljava/lang/String;)Lcom/arflix/tv/data/model/IptvProgram;", "program", "guide", "Lcom/arflix/tv/ui/screens/tv/live/GuideMatchLabels;", "labels", "labelProgramMatch", "(Lcom/arflix/tv/data/model/IptvProgram;Lcom/arflix/tv/data/model/IptvNowNext;Lcom/arflix/tv/ui/screens/tv/live/GuideMatchLabels;)Ljava/lang/String;", "channel", "matchText", "onMoveUp", "Landroidx/compose/ui/Modifier;", "modifier", "SearchResultRow", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/lang/String;Lr7/l;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "debounced", "Lcom/arflix/tv/ui/screens/tv/live/SearchResult;", "results", "", "focused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SearchOverlayKt {
    /* JADX WARN: Removed duplicated region for block: B:102:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0642  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:201:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchOverlay(java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r75, java.util.Map<java.lang.String, com.arflix.tv.data.model.IptvNowNext> r76, r7.p<? super java.lang.String, ? super d7.d<? super java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>>, ? extends java.lang.Object> r77, final r7.a<x6.t0> r78, r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r79, androidx.compose.runtime.Composer r80, int r81, int r82) {
        /*
            Method dump skipped, instruction units count: 1687
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt.SearchOverlay(java.util.List, java.util.Map, r7.p, r7.a, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SearchOverlay$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchOverlay$lambda$14$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchOverlay$lambda$16$1$0$0$0(FocusRequester focusRequester, KeyboardActionScope keyboardActionScope) {
        try {
            focusRequester.requestFocus();
        } catch (Throwable unused) {
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchOverlay$lambda$16$1$0$3$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchOverlay$lambda$16$1$0$4(MutableState mutableState, r7.p pVar, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2 = composer;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer2.changedInstance(pVar) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(107629215, i11, -1, "com.arflix.tv.ui.screens.tv.live.SearchOverlay.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SearchOverlay.kt:231)");
            }
            if (SearchOverlay$lambda$1(mutableState).length() == 0) {
                composer2.startReplaceGroup(-1958562371);
                i12 = i11;
                TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.live_hint_search, composer2, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, new TextStyle(LiveColors.INSTANCE.m6404getFgMute0d7_KjU(), TextUnitKt.getSp(18), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777212, (kotlin.jvm.internal.h) null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                composer2 = composer;
                composer2.endReplaceGroup();
            } else {
                i12 = i11;
                composer2.startReplaceGroup(-1958310589);
                composer2.endReplaceGroup();
            }
            if (a0.c.A(composer2, i12 & 14, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchOverlay$lambda$16$1$1$0(MutableState mutableState, FocusRequester focusRequester, r7.l lVar, FocusRequester focusRequester2, LazyListScope lazyListScope) {
        List<SearchResult> listSearchOverlay$lambda$7 = SearchOverlay$lambda$7(mutableState);
        lazyListScope.items(listSearchOverlay$lambda$7.size(), new SearchOverlayKt$SearchOverlay$lambda$16$1$1$0$$inlined$itemsIndexed$default$1(new l0(2), listSearchOverlay$lambda$7), new SearchOverlayKt$SearchOverlay$lambda$16$1$1$0$$inlined$itemsIndexed$default$2(listSearchOverlay$lambda$7), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new SearchOverlayKt$SearchOverlay$lambda$16$1$1$0$$inlined$itemsIndexed$default$3(listSearchOverlay$lambda$7, focusRequester, lVar, mutableState, focusRequester2)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object SearchOverlay$lambda$16$1$1$0$0(int i10, SearchResult searchResult) {
        return androidx.fragment.app.a2.j(i10, searchResult.getChannel().getId(), "#");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchOverlay$lambda$17(List list, Map map, r7.p pVar, r7.a aVar, r7.l lVar, int i10, int i11, Composer composer, int i12) {
        SearchOverlay(list, map, pVar, aVar, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SearchOverlay$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<SearchResult> SearchOverlay$lambda$7(MutableState<List<SearchResult>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0623  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x06cd  */
    /* JADX WARN: Removed duplicated region for block: B:194:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0169  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchResultRow(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r106, java.lang.String r107, final r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r108, r7.a<x6.t0> r109, androidx.compose.ui.Modifier r110, androidx.compose.runtime.Composer r111, int r112, int r113) {
        /*
            Method dump skipped, instruction units count: 1760
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt.SearchResultRow(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, java.lang.String, r7.l, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean SearchResultRow$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SearchResultRow$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchResultRow$lambda$3$0(MutableState mutableState, FocusState focusState) {
        SearchResultRow$lambda$2(mutableState, focusState.getHasFocus());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchResultRow$lambda$7(EnrichedChannel enrichedChannel, String str, r7.l lVar, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SearchResultRow(enrichedChannel, str, lVar, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IptvProgram bestProgramMatch(IptvNowNext iptvNowNext, String str) {
        Object obj;
        Object next;
        Object next2;
        z6.c cVarN = t7.a.n();
        IptvProgram now = iptvNowNext.getNow();
        if (now != null) {
            cVarN.add(now);
        }
        IptvProgram next3 = iptvNowNext.getNext();
        if (next3 != null) {
            cVarN.add(next3);
        }
        IptvProgram later = iptvNowNext.getLater();
        if (later != null) {
            cVarN.add(later);
        }
        Iterator it = kotlin.collections.x.X0(iptvNowNext.getUpcoming(), 8).iterator();
        while (it.hasNext()) {
            cVarN.add((IptvProgram) it.next());
        }
        z6.c cVarE = t7.a.e(cVarN);
        ListIterator listIterator = cVarE.listIterator(0);
        while (true) {
            z6.a aVar = (z6.a) listIterator;
            obj = null;
            if (!aVar.hasNext()) {
                next = null;
                break;
            }
            next = aVar.next();
            if (kotlin.text.u.L(((IptvProgram) next).getTitle(), str, true)) {
                break;
            }
        }
        IptvProgram iptvProgram = (IptvProgram) next;
        if (iptvProgram == null) {
            ListIterator listIterator2 = cVarE.listIterator(0);
            while (true) {
                z6.a aVar2 = (z6.a) listIterator2;
                if (!aVar2.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = aVar2.next();
                if (kotlin.text.u.P(((IptvProgram) next2).getTitle().toLowerCase(Locale.ROOT), str, false)) {
                    break;
                }
            }
            iptvProgram = (IptvProgram) next2;
            if (iptvProgram == null) {
                ListIterator listIterator3 = cVarE.listIterator(0);
                while (true) {
                    z6.a aVar3 = (z6.a) listIterator3;
                    if (!aVar3.hasNext()) {
                        break;
                    }
                    Object next4 = aVar3.next();
                    if (kotlin.text.o.T(((IptvProgram) next4).getTitle().toLowerCase(Locale.ROOT), str, false)) {
                        obj = next4;
                        break;
                    }
                }
                return (IptvProgram) obj;
            }
        }
        return iptvProgram;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String labelProgramMatch(IptvProgram iptvProgram, IptvNowNext iptvNowNext, GuideMatchLabels guideMatchLabels) {
        String later;
        if (kotlin.jvm.internal.p.a(iptvProgram, iptvNowNext != null ? iptvNowNext.getNow() : null)) {
            later = guideMatchLabels.getNow();
        } else {
            if (kotlin.jvm.internal.p.a(iptvProgram, iptvNowNext != null ? iptvNowNext.getNext() : null)) {
                later = guideMatchLabels.getNext();
            } else {
                later = kotlin.jvm.internal.p.a(iptvProgram, iptvNowNext != null ? iptvNowNext.getLater() : null) ? guideMatchLabels.getLater() : guideMatchLabels.getGuide();
            }
        }
        return androidx.compose.foundation.c.t(later, ": ", iptvProgram.getTitle());
    }
}
