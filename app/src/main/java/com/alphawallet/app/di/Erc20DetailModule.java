package com.alphawallet.app.di;

import com.alphawallet.app.interact.FetchTokensInteract;
import com.alphawallet.app.interact.FetchTransactionsInteract;
import com.alphawallet.app.interact.FindDefaultNetworkInteract;
import com.alphawallet.app.interact.GenericWalletInteract;
import com.alphawallet.app.repository.EthereumNetworkRepositoryType;
import com.alphawallet.app.repository.TokenRepositoryType;
import com.alphawallet.app.repository.TransactionRepositoryType;
import com.alphawallet.app.repository.WalletRepositoryType;
import com.alphawallet.app.router.MyAddressRouter;
import com.alphawallet.app.router.TransactionDetailRouter;
import com.alphawallet.app.service.AssetDefinitionService;
import com.alphawallet.app.service.TokensService;
import com.alphawallet.app.viewmodel.Erc20DetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc20DetailModule {
    @Provides
    Erc20DetailViewModelFactory provideErc20DetailViewModelFactory(MyAddressRouter myAddressRouter,
                                                                   FetchTransactionsInteract fetchTransactionsInteract,
                                                                   FindDefaultNetworkInteract findDefaultNetworkInteract,
                                                                   GenericWalletInteract genericWalletInteract,
                                                                   TransactionDetailRouter transactionDetailRouter,
                                                                   AssetDefinitionService assetDefinitionService,
                                                                   TokensService tokensService,
                                                                   FetchTokensInteract fetchTokensInteract) {
        return new Erc20DetailViewModelFactory(myAddressRouter,
                fetchTransactionsInteract,
                findDefaultNetworkInteract,
                genericWalletInteract,
                transactionDetailRouter,
                assetDefinitionService,
                tokensService, fetchTokensInteract);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepositoryType) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepositoryType);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    TransactionDetailRouter provideTransactionDetailRouter() {
        return new TransactionDetailRouter();
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }
}
