package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.media3.exoplayer.RendererCapabilities;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0092\u0001\u0010\u0010\u001a\u00020\u00052&\u0010\u0007\u001a\"\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0000¢\u0006\u0002\b\u00062\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0013\b\u0002\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0092\u0001\u0010\u0012\u001a\u00020\u00052&\u0010\u0007\u001a\"\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0000¢\u0006\u0002\b\u00062\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0013\b\u0002\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0012\u0010\u0011\u001aW\u0010\u0016\u001a\u00020\u00052\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\u00062\u0006\u0010\u000f\u001a\u00020\u0013H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a²\u0006\f\u0010\u0018\u001a\u00020\u00178\nX\u008a\u0084\u0002²\u0006\f\u0010\u0019\u001a\u00020\u00178\nX\u008a\u0084\u0002²\u0006\f\u0010\u0018\u001a\u00020\u00178\nX\u008a\u0084\u0002²\u0006\f\u0010\u0019\u001a\u00020\u00178\nX\u008a\u0084\u0002"}, d2 = {"Lkotlin/Function1;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Lx6/y;", ContentDisposition.Parameters.Name, "interactionSource", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "imageCard", "Lkotlin/Function0;", LinkHeader.Parameters.Title, "Landroidx/compose/ui/Modifier;", "modifier", MediaTrack.ROLE_SUBTITLE, MediaTrack.ROLE_DESCRIPTION, "Landroidx/tv/material3/o;", "contentColor", "StandardCardContainer", "(Lr7/q;Lr7/p;Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Landroidx/tv/material3/o;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "WideCardContainer", "Landroidx/compose/ui/graphics/Color;", "CardContainerContent-ww6aTOc", "(Lr7/p;Lr7/p;Lr7/p;JLandroidx/compose/runtime/Composer;II)V", "CardContainerContent", "", "focused", "pressed", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CardContainerKt {
    /* JADX INFO: renamed from: CardContainerContent-ww6aTOc, reason: not valid java name */
    public static final void m5978CardContainerContentww6aTOc(r7.p<? super Composer, ? super Integer, x6.t0> pVar, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, long j10, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(821154005);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 32 : 16;
        }
        int i14 = i11 & 4;
        if (i14 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar3) ? 256 : 128;
        }
        if ((i11 & 8) != 0) {
            i12 |= 3072;
        } else if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(j10) ? 2048 : 1024;
        }
        if ((i12 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                pVar2 = j1.f6132e;
            }
            if (i14 != 0) {
                pVar3 = j1.f6133f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(821154005, i12, -1, "androidx.tv.material3.CardContainerContent (CardContainer.kt:154)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) o1.f6258a.provides(Color.m3462boximpl(j10)), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1573094805, true, new CardContainerKt$CardContainerContent$1(pVar, pVar2, pVar3)), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        r7.p<? super Composer, ? super Integer, x6.t0> pVar4 = pVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar5 = pVar3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p(pVar, pVar4, pVar5, j10, i10, i11, 0));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void StandardCardContainer(r7.q<? super androidx.compose.foundation.interaction.MutableInteractionSource, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r21, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, androidx.compose.ui.Modifier r23, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r25, androidx.tv.material3.o r26, androidx.compose.foundation.interaction.MutableInteractionSource r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardContainerKt.StandardCardContainer(r7.q, r7.p, androidx.compose.ui.Modifier, r7.p, r7.p, androidx.tv.material3.o, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void WideCardContainer(r7.q<? super androidx.compose.foundation.interaction.MutableInteractionSource, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r20, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r21, androidx.compose.ui.Modifier r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.tv.material3.o r25, androidx.compose.foundation.interaction.MutableInteractionSource r26, androidx.compose.runtime.Composer r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 826
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardContainerKt.WideCardContainer(r7.q, r7.p, androidx.compose.ui.Modifier, r7.p, r7.p, androidx.tv.material3.o, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }
}
