package com.arflix.tv.ui.screens.plugin;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.ScraperInfo;
import com.arflix.tv.ui.screens.plugin.PluginUiEvent;
import f7.j;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginScreenKt$PluginScreen$7$1", f = "PluginScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PluginScreenKt$PluginScreen$7$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $enterTrigger;
    final /* synthetic */ r7.a<t0> $onEnterTriggerHandled;
    final /* synthetic */ l<Integer, t0> $onFocusedIndexChanged;
    final /* synthetic */ List<PluginRepository> $repositories;
    final /* synthetic */ List<ScraperInfo> $scrapers;
    final /* synthetic */ int $scrapersCount;
    final /* synthetic */ MutableState<Boolean> $showAddDialog$delegate;
    final /* synthetic */ MutableState<Boolean> $showResetDialog$delegate;
    final /* synthetic */ int $totalItems;
    final /* synthetic */ PluginViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PluginScreenKt$PluginScreen$7$1(int i10, List<PluginRepository> list, PluginViewModel pluginViewModel, l<? super Integer, t0> lVar, int i11, List<ScraperInfo> list2, int i12, r7.a<t0> aVar, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, d7.d<? super PluginScreenKt$PluginScreen$7$1> dVar) {
        super(2, dVar);
        this.$enterTrigger = i10;
        this.$repositories = list;
        this.$viewModel = pluginViewModel;
        this.$onFocusedIndexChanged = lVar;
        this.$scrapersCount = i11;
        this.$scrapers = list2;
        this.$totalItems = i12;
        this.$onEnterTriggerHandled = aVar;
        this.$showAddDialog$delegate = mutableState;
        this.$showResetDialog$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new PluginScreenKt$PluginScreen$7$1(this.$enterTrigger, this.$repositories, this.$viewModel, this.$onFocusedIndexChanged, this.$scrapersCount, this.$scrapers, this.$totalItems, this.$onEnterTriggerHandled, this.$showAddDialog$delegate, this.$showResetDialog$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        int i10 = this.$enterTrigger;
        if (i10 >= 0) {
            if (i10 == 0) {
                PluginScreenKt.PluginScreen$lambda$7(this.$showAddDialog$delegate, true);
            } else if (1 > i10 || i10 > this.$repositories.size()) {
                int size = this.$repositories.size() + 1;
                if (i10 > this.$repositories.size() + this.$scrapersCount || size > i10) {
                    if (i10 == this.$totalItems - 1) {
                        PluginScreenKt.PluginScreen$lambda$10(this.$showResetDialog$delegate, true);
                    }
                } else if (!this.$scrapers.isEmpty()) {
                    this.$viewModel.onEvent(new PluginUiEvent.ToggleScraper(this.$scrapers.get((this.$enterTrigger - 1) - this.$repositories.size()).getId(), !r5.getEnabled()));
                }
            } else {
                this.$viewModel.onEvent(new PluginUiEvent.RemoveRepository(this.$repositories.get(this.$enterTrigger - 1).getId()));
                l<Integer, t0> lVar = this.$onFocusedIndexChanged;
                int i11 = this.$enterTrigger - 1;
                if (i11 < 0) {
                    i11 = 0;
                }
                lVar.invoke(new Integer(i11));
            }
            this.$onEnterTriggerHandled.invoke();
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((PluginScreenKt$PluginScreen$7$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
