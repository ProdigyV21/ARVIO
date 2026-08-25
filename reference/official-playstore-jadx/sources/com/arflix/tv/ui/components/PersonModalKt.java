package com.arflix.tv.ui.components;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.PersonDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0002\u001a\u00020\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001aW\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u001a\b\u0002\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001aO\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000e2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0003¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001f\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c²\u0006\u000e\u0010\u0014\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "dateStr", "formatBirthday", "(Ljava/lang/String;)Ljava/lang/String;", "", "isVisible", "Lcom/arflix/tv/data/model/PersonDetails;", "person", "isLoading", "Lkotlin/Function0;", "Lx6/t0;", "onClose", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/MediaType;", "", "onMediaClick", "PersonModal", "(ZLcom/arflix/tv/data/model/PersonDetails;ZLr7/a;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/ScrollState;", "scrollState", "focusedKnownForIndex", "MobilePersonContent", "(Lcom/arflix/tv/data/model/PersonDetails;Landroidx/compose/foundation/ScrollState;ILr7/p;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/data/model/MediaItem;", "item", "isFocused", "HorizontalKnownForCard", "(Lcom/arflix/tv/data/model/MediaItem;ZLandroidx/compose/runtime/Composer;I)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PersonModalKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0303  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HorizontalKnownForCard(com.arflix.tv.data.model.MediaItem r72, boolean r73, androidx.compose.runtime.Composer r74, int r75) {
        /*
            Method dump skipped, instruction units count: 1761
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PersonModalKt.HorizontalKnownForCard(com.arflix.tv.data.model.MediaItem, boolean, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HorizontalKnownForCard$lambda$1(MediaItem mediaItem, boolean z, int i10, Composer composer, int i11) {
        HorizontalKnownForCard(mediaItem, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0673  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0760  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0773  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void MobilePersonContent(com.arflix.tv.data.model.PersonDetails r79, androidx.compose.foundation.ScrollState r80, int r81, r7.p<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, x6.t0> r82, r7.a<x6.t0> r83, androidx.compose.runtime.Composer r84, int r85) {
        /*
            Method dump skipped, instruction units count: 2232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PersonModalKt.MobilePersonContent(com.arflix.tv.data.model.PersonDetails, androidx.compose.foundation.ScrollState, int, r7.p, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobilePersonContent$lambda$0$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobilePersonContent$lambda$0$3$0(PersonDetails personDetails, r7.p pVar, LazyListScope lazyListScope) {
        List<MediaItem> knownFor = personDetails.getKnownFor();
        lazyListScope.items(knownFor.size(), null, new PersonModalKt$MobilePersonContent$lambda$0$3$0$$inlined$itemsIndexed$default$2(knownFor), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new PersonModalKt$MobilePersonContent$lambda$0$3$0$$inlined$itemsIndexed$default$3(knownFor, pVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobilePersonContent$lambda$1(PersonDetails personDetails, ScrollState scrollState, int i10, r7.p pVar, r7.a aVar, int i11, Composer composer, int i12) {
        MobilePersonContent(personDetails, scrollState, i10, pVar, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0149  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PersonModal(boolean r19, com.arflix.tv.data.model.PersonDetails r20, boolean r21, r7.a<x6.t0> r22, r7.p<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, x6.t0> r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PersonModalKt.PersonModal(boolean, com.arflix.tv.data.model.PersonDetails, boolean, r7.a, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PersonModal$lambda$1$0(MediaType mediaType, int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0ae8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x022c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 PersonModal$lambda$10(androidx.compose.ui.focus.FocusRequester r81, final r7.a r82, final boolean r83, final com.arflix.tv.data.model.PersonDetails r84, final r7.p r85, final androidx.compose.runtime.MutableIntState r86, boolean r87, androidx.compose.foundation.ScrollState r88, androidx.compose.animation.AnimatedVisibilityScope r89, androidx.compose.runtime.Composer r90, int r91) {
        /*
            Method dump skipped, instruction units count: 2798
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PersonModalKt.PersonModal$lambda$10(androidx.compose.ui.focus.FocusRequester, r7.a, boolean, com.arflix.tv.data.model.PersonDetails, r7.p, androidx.compose.runtime.MutableIntState, boolean, androidx.compose.foundation.ScrollState, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PersonModal$lambda$10$1$1$1$1$0(PersonDetails personDetails, MutableIntState mutableIntState, androidx.tv.foundation.lazy.list.g0 g0Var) {
        List<MediaItem> knownFor = personDetails.getKnownFor();
        g0Var.items(knownFor.size(), null, new PersonModalKt$PersonModal$lambda$10$1$1$1$1$0$$inlined$itemsIndexed$default$2(knownFor), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new PersonModalKt$PersonModal$lambda$10$1$1$1$1$0$$inlined$itemsIndexed$default$3(knownFor, mutableIntState)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PersonModal$lambda$11(boolean z, PersonDetails personDetails, boolean z5, r7.a aVar, r7.p pVar, int i10, int i11, Composer composer, int i12) {
        PersonModal(z, personDetails, z5, aVar, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PersonModal$lambda$7$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int PersonModal$lambda$8$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int PersonModal$lambda$9$0(int i10) {
        return i10;
    }

    private static final String formatBirthday(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            Locale locale = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM yyyy", locale);
            Date date = simpleDateFormat.parse(str);
            if (date != null) {
                String str2 = simpleDateFormat2.format(date);
                if (str2 != null) {
                    return str2;
                }
            }
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
        }
        return str;
    }
}
