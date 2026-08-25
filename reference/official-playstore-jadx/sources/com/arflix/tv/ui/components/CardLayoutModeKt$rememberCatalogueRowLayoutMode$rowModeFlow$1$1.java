package com.arflix.tv.ui.components;

import java.util.LinkedHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lc1/g;", "profilePrefs", "settingsPrefs", "Lcom/arflix/tv/ui/components/CardLayoutMode;", "<anonymous>", "(Lc1/g;Lc1/g;)Lcom/arflix/tv/ui/components/CardLayoutMode;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1", f = "CardLayoutMode.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1 extends f7.j implements r7.q<c1.g, c1.g, d7.d<? super CardLayoutMode>, Object> {
    final /* synthetic */ String $normalizedRowKey;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1(String str, d7.d<? super CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1> dVar) {
        super(3, dVar);
        this.$normalizedRowKey = str;
    }

    @Override // r7.q
    public final Object invoke(c1.g gVar, c1.g gVar2, d7.d<? super CardLayoutMode> dVar) {
        CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1 cardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1 = new CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1(this.$normalizedRowKey, dVar);
        cardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1.L$0 = gVar;
        cardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1.L$1 = gVar2;
        return cardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1.invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        c1.g gVar = (c1.g) this.L$0;
        c1.g gVar2 = (c1.g) this.L$1;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String str = (String) ((c1.b) gVar).f7368a.get(CardLayoutModeKt.activeProfileIdKey);
        if (str == null) {
            str = "";
        }
        if (kotlin.text.o.h0(str)) {
            str = "default";
        }
        c1.e eVarProfileCatalogueRowLayoutModeKey = CardLayoutModeKt.profileCatalogueRowLayoutModeKey(str, this.$normalizedRowKey);
        c1.b bVar = (c1.b) gVar2;
        String str2 = (String) bVar.f7368a.get(CardLayoutModeKt.profileCardLayoutModeKey(str));
        c1.e eVar = CardLayoutModeKt.cardLayoutModeKey;
        LinkedHashMap linkedHashMap = bVar.f7368a;
        String str3 = (String) linkedHashMap.get(eVar);
        if (str2 == null) {
            str2 = str3;
        }
        String strNormalizeCardLayoutMode = CardLayoutModeKt.normalizeCardLayoutMode(str2);
        String str4 = (String) linkedHashMap.get(eVarProfileCatalogueRowLayoutModeKey);
        if (str4 != null) {
            strNormalizeCardLayoutMode = str4;
        }
        return CardLayoutModeKt.parseCardLayoutMode(strNormalizeCardLayoutMode);
    }
}
