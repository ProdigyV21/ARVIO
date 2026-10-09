import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { standaloneGuideRoutes } from "./standalone-guide-routes.mjs";

const siteRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const baseUrl = "https://arvio.tv";
const playUrl = "https://play.google.com/store/apps/details?id=com.arvio.tv";
const githubUrl = "https://github.com/ProdigyV21/ARVIO";
const lastmod = "2026-08-22";

const pageMap = {
  home: { en: "/", pt: "/pt-br/", es: "/es/" },
  guides: { en: "/guides/", pt: "/pt-br/guias/", es: "/es/guias/" },
  hub: { en: "/android-tv-media-hub/", pt: "/pt-br/central-midia-android-tv/", es: "/es/centro-multimedia-android-tv/" },
  jellyfin: { en: "/jellyfin-android-tv/", pt: "/pt-br/jellyfin-android-tv/", es: "/es/jellyfin-android-tv/" },
  servers: { en: "/plex-emby-jellyfin/", pt: "/pt-br/plex-emby-jellyfin/", es: "/es/plex-emby-jellyfin/" },
  sources: { en: "/debrid-usenet-android-tv/", pt: "/pt-br/debrid-usenet-android-tv/", es: "/es/debrid-usenet-android-tv/" },
  tracking: { en: "/trakt-simkl-sync/", pt: "/pt-br/sincronizacao-trakt-simkl/", es: "/es/sincronizacion-trakt-simkl/" },
  live: { en: "/live-tv-epg/", pt: "/pt-br/tv-ao-vivo-epg/", es: "/es/tv-en-vivo-epg/" },
  subtitles: { en: "/ai-subtitles-android-tv/", pt: "/pt-br/legendas-ia-android-tv/", es: "/es/subtitulos-ia-android-tv/" },
  firetv: { en: "/fire-tv-media-player/", pt: "/pt-br/arvio-fire-tv/", es: "/es/arvio-fire-tv/" },
  web: { en: "/arvio-web/", pt: "/pt-br/arvio-web/", es: "/es/arvio-web/" }
};

const images = {
  home: "a8d8836c-0b29-4232-a545-5c112c26ebbb.webp",
  hub: "a8d8836c-0b29-4232-a545-5c112c26ebbb.webp",
  jellyfin: "a1703ba5-e81a-44fc-a257-52f24cc895f4.webp",
  servers: "0db5f9ea-38ef-43d3-b57c-99c6ff9e7436.webp",
  sources: "58a51fcb-b75b-4891-82f3-f797e12afdc1.webp",
  tracking: "a8d8836c-0b29-4232-a545-5c112c26ebbb.webp",
  live: "cb602938-0a58-4480-8f8a-f21fac260e1d.webp",
  subtitles: "a9f9dd6c-4a0c-4ff5-b6d8-4cb8890875f6.webp",
  firetv: "a8d8836c-0b29-4232-a545-5c112c26ebbb.webp",
  web: "web-home.webp"
};

const locales = {
  pt: {
    htmlLang: "pt-BR",
    hreflang: "pt-BR",
    home: "Início",
    guides: "Guias",
    skip: "Ir para o conteúdo",
    mainNav: "Navegação principal",
    breadcrumb: "Navegação estrutural",
    footerNav: "Navegação do rodapé",
    install: "Instalar o ARVIO",
    play: "Google Play",
    source: "Ver código-fonte",
    browser: "Conhecer o ARVIO Web",
    questions: "Perguntas frequentes",
    related: "Guias relacionados",
    softwareBoundaryTitle: "Software, não conteúdo",
    softwareBoundary: "O ARVIO não hospeda, fornece, vende, inclui nem redistribui filmes, séries, canais, listas ou transmissões. Você conecta apenas servidores, serviços, arquivos e integrações que tem autorização para usar.",
    footerDescription: "Gerenciamento de mídia de código aberto para Android TV, Google TV e Android.",
    footerBoundary: "O ARVIO fornece software, não mídia.",
    languageLabel: "Idioma",
    languageLink: "Español",
    languageHref: "/es/",
    pageData: {
      hub: {
        title: "Central de mídia para Android TV, celular e navegador - ARVIO",
        description: "Organize servidores, serviços, TV ao vivo, Trakt, Simkl e reprodução no ARVIO para Android TV, Android, iPhone, iPad, Windows e Mac.",
        crumb: "Central de mídia",
        eyebrow: "Aplicativo Android gratuito",
        h1: "Seus serviços de mídia, organizados para a TV.",
        hero: "O ARVIO é uma central de gerenciamento de mídia gratuita e de código aberto para Android TV, Google TV e Android. O ARVIO Web opcional leva recursos compatíveis ao iPhone, iPad, Windows, Mac, Chromebook, Apple TV e navegadores de smart TVs.",
        sectionEyebrow: "Uma interface",
        sectionTitle: "Conecte o que você já usa.",
        sectionCopy: "O ARVIO não substitui seu servidor nem seu provedor. Ele apresenta serviços compatíveis em uma biblioteca consistente, com perfis, descoberta e reprodução pensados para controle remoto.",
        features: [
          ["Jellyfin, Plex e Emby", "Navegue por bibliotecas autorizadas, itens recentes e versões de mídia disponíveis em servidores domésticos compatíveis."],
          ["Trakt e Simkl", "Use listas, progresso, histórico e scrobbling, escolhendo qual rastreador alimenta cada recurso."],
          ["TV ao vivo e guia", "Conecte seu próprio provedor ou lista com categorias, favoritos, EPG e dados de catch-up quando houver suporte."],
          ["Perfis e nuvem", "Mantenha perfis separados e sincronize configurações compatíveis entre dispositivos com uma conta ARVIO."],
          ["Legendas e reprodução", "Escolha fontes de legendas, preferências do player e ferramentas opcionais de IA com suas próprias credenciais."],
          ["TV, celular e navegador", "Use o app gratuito no Android e adicione o acesso premium opcional pelo navegador em dispositivos Apple e computadores."]
        ],
        stepsTitle: "Comece pelas partes de que precisa.",
        steps: [
          ["Instale o ARVIO", "Use o Google Play no Android TV, Google TV ou celular Android, ou instale o APK oficial assinado pelo GitHub."],
          ["Crie um perfil", "Escolha idioma, aparência e comportamento de reprodução para cada pessoa ou uso compartilhado."],
          ["Conecte serviços autorizados", "Adicione somente servidores, contas, listas e integrações que você tem permissão para acessar."]
        ],
        faq: [
          ["O ARVIO é gratuito no Android TV?", "Sim. O aplicativo Android é gratuito e de código aberto. O ARVIO Web é um complemento premium opcional para navegador."],
          ["O ARVIO inclui filmes, séries ou canais?", "Não. O ARVIO é um software de gerenciamento e reprodução. Você fornece as fontes e os serviços autorizados."],
          ["Quais serviços o ARVIO organiza?", "Ele oferece suporte a servidores Jellyfin, Plex e Emby, TV ao vivo, Trakt, Simkl, legendas e integrações pessoais compatíveis."]
        ],
        noteTitle: "Limite de conteúdo",
        note: "O ARVIO fornece a interface e o player. A disponibilidade e a qualidade dependem da fonte conectada, da rede e dos recursos do dispositivo.",
        ctaTitle: "Uma central aberta para a sua configuração.",
        ctaCopy: "Instale gratuitamente no Android ou examine o projeto completo no GitHub.",
        related: ["servers", "tracking", "live"]
      },
      jellyfin: {
        title: "Jellyfin no Android TV, iPad, Windows e Mac - ARVIO",
        description: "Conecte seu servidor Jellyfin ao ARVIO e navegue por bibliotecas, versões e progresso no Android TV, celular e navegador.",
        crumb: "Jellyfin em vários dispositivos",
        eyebrow: "Integração com servidor doméstico",
        h1: "Leve sua biblioteca Jellyfin para o ARVIO.",
        hero: "Conecte um servidor Jellyfin que você tem autorização para usar e navegue por bibliotecas e versões de mídia no app Android gratuito ou no ARVIO Web opcional para iPhone, iPad, Windows, Mac e outros navegadores compatíveis.",
        sectionEyebrow: "Conectado, não copiado",
        sectionTitle: "Seu servidor continua no controle.",
        sectionCopy: "O ARVIO lê informações permitidas da biblioteca e mídias reproduzíveis. Regras de acesso, usuários, permissões e disponibilidade continuam sendo controlados pelo Jellyfin.",
        features: [
          ["Bibliotecas em vários dispositivos", "Abra as bibliotecas do servidor na página Biblioteca do ARVIO sem reconstruir a organização do Jellyfin."],
          ["Adicionados recentemente", "Veja as adições mais recentes em uma visualização rápida com os mesmos cartões e detalhes do ARVIO."],
          ["Versões de mídia", "Quando o Jellyfin informa vários arquivos, o ARVIO pode apresentá-los como opções separadas de reprodução."],
          ["Progresso e estado assistido", "A reprodução atualiza o servidor quando suportado, enquanto Trakt ou Simkl podem manter um histórico mais amplo."],
          ["Tokens protegidos", "Os tokens de acesso são guardados no armazenamento criptografado de credenciais nos dispositivos Android compatíveis."],
          ["Reprodução conforme o dispositivo", "O ARVIO tenta a reprodução compatível e o Jellyfin pode transcodificar formatos que o aparelho não reproduz diretamente."]
        ],
        stepsTitle: "Três etapas para abrir sua biblioteca.",
        steps: [
          ["Prepare o Jellyfin", "Confirme que o servidor está acessível e que o usuário pode abrir as bibliotecas desejadas."],
          ["Conecte nas configurações", "Adicione o Jellyfin como servidor doméstico e conclua o login ou o fluxo de código."],
          ["Abra a Biblioteca", "Selecione o Jellyfin, escolha uma biblioteca e navegue pelos títulos permitidos para esse usuário."]
        ],
        faq: [
          ["O ARVIO substitui o servidor Jellyfin?", "Não. O Jellyfin continua responsável pela biblioteca, pelos usuários e pelo acesso. O ARVIO funciona como cliente e interface de gerenciamento."],
          ["O ARVIO mostra diferentes versões de um arquivo?", "Sim, quando o servidor informa essas versões e o usuário conectado tem permissão para acessá-las."],
          ["Todo arquivo será reproduzido diretamente?", "Não necessariamente. Contêiner, codecs, áudio, legendas, rede, dispositivo e configuração de transcodificação influenciam a reprodução."]
        ],
        noteTitle: "A reprodução depende de todo o caminho",
        note: "Uma conexão válida não garante reprodução direta para todos os arquivos. O servidor pode precisar transcodificar formatos não compatíveis com o dispositivo ou navegador.",
        ctaTitle: "Use seu servidor Jellyfin do seu jeito.",
        ctaCopy: "O ARVIO é gratuito e de código aberto no Android.",
        related: ["servers", "tracking", "web"]
      },
      servers: {
        title: "Plex, Emby e Jellyfin na TV, iPad, Windows e Mac - ARVIO",
        description: "Navegue por Plex, Emby e Jellyfin em uma interface no Android TV, Android e no navegador opcional do ARVIO.",
        crumb: "Plex, Emby e Jellyfin",
        eyebrow: "Biblioteca com vários servidores",
        h1: "Plex, Emby e Jellyfin em uma interface conectada.",
        hero: "Abra servidores domésticos autorizados no app gratuito para Android TV e celular, ou use o ARVIO Web opcional no iPhone, iPad, Windows, Mac, Apple TV e navegadores compatíveis.",
        sectionEyebrow: "Escolha o servidor sem trocar de interface",
        sectionTitle: "Uma página Biblioteca, fontes separadas.",
        sectionCopy: "Cada servidor permanece uma fonte independente e confiável. Você navega entre bibliotecas mantendo o mesmo design de cartões, detalhes e comportamento do controle remoto.",
        features: [
          ["Separação por servidor", "Plex, Emby e Jellyfin continuam distintos, preservando permissões e estrutura de cada biblioteca."],
          ["Barra lateral de bibliotecas", "Selecione um serviço conectado e abra as bibliotecas que ele disponibiliza sem excesso de filtros."],
          ["Itens recentes", "Veja os títulos mais novos permitidos por cada servidor em uma visualização feita para leitura rápida."],
          ["Várias versões de mídia", "Arquivos 4K, 1080p, remux ou versões alternativas podem aparecer como opções separadas."],
          ["Experiência consistente", "Use detalhes, artes, metadados e controles familiares, independentemente do servidor de origem."],
          ["Configuração por perfil", "Defina quais servidores e catálogos ficam visíveis em cada perfil, inclusive em ambientes familiares."]
        ],
        stepsTitle: "Mantenha o controle no lugar certo.",
        steps: [
          ["Conecte um servidor", "Use o login ou o fluxo de conexão compatível para Plex, Emby ou Jellyfin."],
          ["Escolha as bibliotecas", "O ARVIO lê somente as bibliotecas disponíveis para o usuário autenticado."],
          ["Selecione uma versão", "Abra um título e escolha entre as versões de mídia que o servidor informa."]
        ],
        faq: [
          ["Posso conectar Plex, Emby e Jellyfin ao mesmo tempo?", "Sim. Eles podem ser conectados ao mesmo perfil e abertos como fontes de biblioteca separadas."],
          ["O ARVIO altera ou combina as bibliotecas?", "Não. Cada servidor permanece a fonte oficial. O ARVIO não move nem modifica a mídia."],
          ["Por que um título pode ter várias fontes?", "O servidor pode oferecer vários arquivos ou versões, e o ARVIO os apresenta para você escolher resolução e codificação."]
        ],
        noteTitle: "Nenhuma migração de biblioteca",
        note: "Adicionar um servidor não copia arquivos nem ignora permissões. O Plex, Emby ou Jellyfin original precisa estar acessível para navegação e reprodução.",
        ctaTitle: "Seus servidores. Uma interface tranquila.",
        ctaCopy: "O aplicativo Android continua gratuito e de código aberto.",
        related: ["jellyfin", "tracking", "sources"]
      },
      sources: {
        title: "Debrid e Usenet na TV, iPad, Windows e Mac - ARVIO",
        description: "Organize fontes compatíveis de debrid e Usenet no ARVIO com qualidade, tamanho, idioma e informações do addon visíveis.",
        crumb: "Fontes de debrid e Usenet",
        eyebrow: "Integrações pessoais compatíveis",
        h1: "Fontes de debrid e Usenet, organizadas com clareza.",
        hero: "Use fontes fornecidas por addons compatíveis no app Android gratuito ou no ARVIO Web opcional. O ARVIO preserva os detalhes da fonte e reproduz mídia HTTP resolvida no dispositivo compatível.",
        sectionEyebrow: "Seleção transparente",
        sectionTitle: "Veja o que o addon retornou antes de reproduzir.",
        sectionCopy: "Um addon compatível pode fornecer muito mais que uma URL. O ARVIO mantém os dados úteis visíveis para uma escolha informada.",
        features: [
          ["Qualidade e resolução", "Identifique 4K, 1080p, 720p e outras informações declaradas por meio de badges consistentes."],
          ["Tamanho e nome do arquivo", "Compare o tamanho informado e leia o nome completo quando o addon fornece esses valores."],
          ["Origem do addon e provedor", "Mantenha rótulos como debrid, Usenet, vault, index ou host em vez de reduzir tudo a uma fonte genérica."],
          ["Idioma e detalhes técnicos", "Veja idioma, lançamento, codec e outros metadados disponíveis sem duplicação desnecessária."],
          ["Ordenação útil", "Respeite a ordem dos addons e compare tamanho e qualidade para encontrar rapidamente as opções preferidas."],
          ["Android e navegador", "Use o player nativo ou opções compatíveis de navegador, VLC e Infuse conforme serviço, formato e dispositivo."]
        ],
        stepsTitle: "O addon resolve; o ARVIO apresenta e reproduz.",
        steps: [
          ["Configure uma integração", "Use separadamente um addon ou serviço compatível e uma conta que você tem autorização para usar."],
          ["Receba fontes resolvidas", "A integração retorna metadados e uma URL HTTP reproduzível. O ARVIO não pesquisa infraestrutura de Usenet ou debrid."],
          ["Escolha ou use autoplay", "Selecione um resultado específico ou deixe o autoplay usar o melhor candidato disponível segundo suas preferências."]
        ],
        faq: [
          ["O ARVIO inclui uma conta de debrid ou Usenet?", "Não. O ARVIO não inclui, vende nem compartilha contas de provedores."],
          ["O ARVIO reproduz torrent ou magnet diretamente?", "Não. A integração precisa retornar uma fonte de mídia HTTP resolvida que o dispositivo possa acessar."],
          ["Quais informações de fonte podem aparecer?", "Qualidade, tamanho, nome do arquivo, idioma, addon, provedor e outros detalhes enviados pela integração."]
        ],
        noteTitle: "Nenhuma conta, addon ou mídia está incluída",
        note: "O ARVIO não fornece assinaturas, índices, credenciais, arquivos ou transmissões e não oferece reprodução P2P direta. Use somente serviços e conteúdos autorizados.",
        ctaTitle: "Suas integrações, com os detalhes preservados.",
        ctaCopy: "O ARVIO é gratuito e de código aberto no Android.",
        related: ["hub", "tracking", "subtitles"]
      },
      tracking: {
        title: "Sincronização Trakt e Simkl na TV, iPad, Windows e Mac - ARVIO",
        description: "Conecte Trakt e Simkl ao ARVIO para listas, histórico, progresso e scrobbling em Android TV, celular e navegador.",
        crumb: "Sincronização Trakt e Simkl",
        eyebrow: "Integrações de acompanhamento",
        h1: "Mantenha Trakt e Simkl conectados entre dispositivos.",
        hero: "Carregue listas, progresso e histórico no app Android gratuito ou no ARVIO Web opcional. Escolha qual rastreador fornece cada recurso sem precisar abandonar o outro.",
        sectionEyebrow: "Controle por recurso",
        sectionTitle: "Use os dois serviços com responsabilidades claras.",
        sectionCopy: "O ARVIO pode conectar Trakt e Simkl ao mesmo perfil e permite escolher a origem de listas, progresso e histórico quando o recurso oferece essa seleção.",
        features: [
          ["Continue assistindo", "Recupere o próximo episódio ou a posição compatível usando o rastreador escolhido para progresso."],
          ["Listas e biblioteca", "Abra listas pessoais e coleções compatíveis na página Biblioteca com a mesma experiência de cartões."],
          ["Histórico assistido", "Marcar algo como assistido pode atualizar os rastreadores conectados conforme as preferências de sincronização."],
          ["Scrobbling", "Envie o andamento durante a reprodução para os serviços habilitados, respeitando tokens e limites da API."],
          ["Perfis independentes", "Cada perfil pode usar suas próprias conexões e preferências de rastreamento."],
          ["Sincronização entre dispositivos", "As escolhas compatíveis podem acompanhar a conta ARVIO no Android e no navegador opcional."]
        ],
        stepsTitle: "Conecte, escolha e acompanhe.",
        steps: [
          ["Autorize os serviços", "Conecte Trakt e Simkl pelos fluxos oficiais de autorização."],
          ["Escolha a origem", "Defina qual serviço fornece listas, progresso e outros dados configuráveis."],
          ["Reproduza normalmente", "O ARVIO atualiza o estado nos serviços habilitados quando a operação é compatível."]
        ],
        faq: [
          ["Posso conectar Trakt e Simkl juntos?", "Sim. Os dois podem permanecer conectados, com escolhas separadas para recursos compatíveis."],
          ["O ARVIO copia minhas senhas?", "Não. A conexão usa os fluxos de autorização e tokens fornecidos pelos serviços."],
          ["As alterações aparecem em outros aparelhos?", "Quando o serviço aceita a atualização, o novo estado fica disponível aos demais clientes conectados à mesma conta."]
        ],
        noteTitle: "Os serviços continuam sendo a fonte dos dados",
        note: "A disponibilidade, o tempo de atualização e os limites dependem das APIs do Trakt e do Simkl. O ARVIO não controla interrupções ou limites dessas plataformas.",
        ctaTitle: "Suas listas e seu progresso, conectados.",
        ctaCopy: "Use Trakt, Simkl ou ambos no ARVIO.",
        related: ["hub", "servers", "web"]
      },
      live: {
        title: "TV ao vivo e EPG no Android TV, iPad, Windows e Mac - ARVIO",
        description: "Use seu provedor autorizado de TV ao vivo no ARVIO com ordem do provedor, favoritos, EPG, busca e catch-up compatível.",
        crumb: "TV ao vivo e EPG",
        eyebrow: "Suporte a provedor autorizado",
        h1: "Um guia de TV ao vivo feito para todos os seus dispositivos.",
        hero: "Navegue pelo seu provedor no Android TV e celular ou use o ARVIO Web opcional no iPhone, iPad, Windows, Mac e navegadores compatíveis. O suporte de reprodução depende do provedor e do dispositivo.",
        sectionEyebrow: "Projetado para listas grandes",
        sectionTitle: "Canais, grupos e guia em uma experiência fluida.",
        sectionCopy: "O ARVIO mantém a ordem original do provedor, carrega dados em etapas e prioriza favoritos para que listas grandes fiquem utilizáveis rapidamente.",
        features: [
          ["Ordem do provedor", "Grupos e canais respeitam a sequência entregue pelo provedor, a menos que você os reorganize."],
          ["EPG progressivo", "Dados em cache aparecem primeiro e novas correspondências são preenchidas sem bloquear a navegação."],
          ["Favoritos prioritários", "Canais favoritos e seus programas recebem prioridade durante a abertura inicial do guia."],
          ["Pesquisa e categorias", "Encontre canais e programas e esconda ou reorganize grupos por perfil."],
          ["Catch-up compatível", "Consulte programas anteriores e reproduza arquivos de catch-up quando o provedor oferece URLs válidas."],
          ["Controle remoto e toque", "A interface se adapta à navegação por foco na TV e aos controles móveis."]
        ],
        stepsTitle: "Do provedor ao guia.",
        steps: [
          ["Adicione sua lista ou conta", "Use os dados fornecidos por um serviço que você tem autorização para acessar."],
          ["Carregue o EPG", "O ARVIO lê as fontes configuradas e associa programas por identificador e nome compatível."],
          ["Escolha um canal", "Abra a transmissão ao vivo ou um programa anterior quando o catch-up estiver disponível."]
        ],
        faq: [
          ["O ARVIO fornece canais ou listas?", "Não. Você precisa conectar um provedor ou uma lista que tem autorização para usar."],
          ["Por que alguns canais não têm guia?", "O provedor pode não fornecer EPG ou os identificadores e nomes podem não corresponder aos canais."],
          ["O catch-up funciona em todos os canais?", "Não. Ele depende da disponibilidade, do formato e das URLs de arquivo fornecidas pelo provedor."]
        ],
        noteTitle: "A qualidade depende do provedor",
        note: "EPG, duração do histórico, catch-up e estabilidade da transmissão vêm do serviço conectado. O ARVIO organiza e reproduz os dados recebidos.",
        ctaTitle: "Seu provedor, organizado para o controle remoto.",
        ctaCopy: "Use o ARVIO gratuitamente no Android TV e Android.",
        related: ["hub", "web", "tracking"]
      },
      subtitles: {
        title: "Legendas com IA no Android TV, iPad, Windows e Mac - ARVIO",
        description: "Pesquise, ajuste e traduza legendas no ARVIO com ferramentas opcionais de IA e suas próprias credenciais compatíveis.",
        crumb: "Legendas com IA",
        eyebrow: "Ferramentas opcionais de acessibilidade",
        h1: "Controle de legendas na TV, no celular e no navegador.",
        hero: "Use pesquisa e estilo de legendas no app Android gratuito e ferramentas opcionais de tradução por IA com sua própria conta compatível. Os recursos disponíveis dependem do player e da fonte.",
        sectionEyebrow: "Legendas que se adaptam",
        sectionTitle: "Legibilidade sem esconder o vídeo.",
        sectionCopy: "O ARVIO reúne busca, seleção, estilo e posicionamento de legendas em controles adequados para TV e celular.",
        features: [
          ["Pesquisa de legendas", "Consulte provedores compatíveis e escolha idioma e versão adequados ao arquivo."],
          ["Estilo personalizável", "Ajuste tamanho, cor, fundo e posição para diferentes telas e preferências."],
          ["Áreas pretas e enquadramento", "Quando o player permite, posicione a legenda em relação à tela e não apenas ao quadro do vídeo."],
          ["Tradução opcional com IA", "Use sua própria conta compatível para traduzir texto sem incluir uma assinatura de IA no ARVIO."],
          ["Controles para TV", "Altere a faixa e as preferências sem menus difíceis de navegar com o controle remoto."],
          ["Compatibilidade por formato", "SRT, WebVTT, legendas incorporadas e outros formatos dependem do player e da fonte."]
        ],
        stepsTitle: "Encontre e ajuste a faixa certa.",
        steps: [
          ["Abra as legendas", "Durante a reprodução, abra o seletor e veja as faixas disponíveis."],
          ["Escolha idioma e estilo", "Selecione a faixa e aplique as preferências de aparência do perfil."],
          ["Use IA quando necessário", "Configure sua própria credencial compatível e traduza somente quando esse recurso for útil."]
        ],
        faq: [
          ["O ARVIO inclui um serviço pago de IA?", "Não. As ferramentas de IA são opcionais e exigem sua própria conta compatível."],
          ["Todas as fontes oferecem legendas?", "Não. A disponibilidade depende da mídia, do addon, do servidor e dos provedores de legendas configurados."],
          ["Posso mudar a posição das legendas?", "Sim, nos players e formatos compatíveis, junto com tamanho, cor e fundo."]
        ],
        noteTitle: "A precisão varia",
        note: "Traduções automáticas podem conter erros. Revise o resultado quando a precisão for importante e respeite os termos do serviço de IA utilizado.",
        ctaTitle: "Legendas claras em qualquer tela.",
        ctaCopy: "O player Android do ARVIO continua gratuito e de código aberto.",
        related: ["hub", "sources", "web"]
      },
      firetv: {
        title: "ARVIO no Fire TV - instalação e compatibilidade",
        description: "Instale o APK oficial do ARVIO em dispositivos Fire TV compatíveis e use a interface feita para controle remoto.",
        crumb: "ARVIO no Fire TV",
        eyebrow: "Dispositivos Fire OS compatíveis",
        h1: "Use o ARVIO no Fire TV.",
        hero: "O aplicativo Android TV pode ser instalado manualmente em aparelhos Fire TV compatíveis, levando bibliotecas, rastreamento, TV ao vivo e o player do ARVIO para o Fire OS.",
        sectionEyebrow: "O mesmo APK oficial",
        sectionTitle: "Uma experiência de TV sem uma versão paralela.",
        sectionCopy: "O Fire TV executa o build Android assinado publicado no GitHub. A compatibilidade varia conforme a versão do Fire OS, o hardware e os serviços instalados.",
        features: [
          ["Interface para controle remoto", "Cartões, menus e player foram projetados para navegação por foco em uma televisão."],
          ["APK oficial assinado", "Use somente a versão publicada no repositório oficial para manter a assinatura e permitir atualizações compatíveis."],
          ["Servidores domésticos", "Conecte Jellyfin, Plex e Emby autorizados como em outros dispositivos Android compatíveis."],
          ["Rastreamento", "Use Trakt e Simkl para listas, progresso e histórico quando as contas estiverem conectadas."],
          ["TV ao vivo", "Abra seu provedor ou lista autorizada com EPG e recursos compatíveis."],
          ["Limitações da plataforma", "Google Play, codecs, memória e comportamento em segundo plano podem ser diferentes do Android TV comum."]
        ],
        stepsTitle: "Instalação consciente.",
        steps: [
          ["Baixe do GitHub", "Obtenha o APK de release na página oficial do projeto."],
          ["Autorize a instalação", "Ative temporariamente a permissão de instalar apps da fonte usada no Fire TV."],
          ["Abra e configure", "Conecte apenas contas, servidores e fontes que você tem permissão para usar."]
        ],
        faq: [
          ["O ARVIO está na Amazon Appstore?", "A disponibilidade pode variar. O método documentado é instalar o APK oficial do GitHub em hardware compatível."],
          ["O mesmo APK funciona em todo Fire TV?", "Não há garantia para todos os modelos. Desempenho e codecs variam conforme hardware e versão do Fire OS."],
          ["Posso atualizar sem perder dados?", "Sim, quando o novo APK usa o mesmo pacote e assinatura oficial. Não desinstale o app se quiser preservar os dados locais."]
        ],
        noteTitle: "Instalação manual exige cuidado",
        note: "Confirme sempre o repositório oficial e a assinatura. Builds de terceiros podem não atualizar corretamente e podem representar risco aos dados.",
        ctaTitle: "ARVIO na sua tela Fire TV.",
        ctaCopy: "Baixe o APK oficial assinado pelo GitHub.",
        ctaPrimary: "GitHub Releases",
        ctaPrimaryHref: "https://github.com/ProdigyV21/ARVIO/releases",
        related: ["hub", "live", "servers"]
      },
      web: {
        title: "ARVIO Web para iPhone, iPad, Windows, Mac e Apple TV",
        description: "Use o complemento premium ARVIO Web no iPhone, iPad, Windows, Mac, Apple TV e navegadores compatíveis.",
        crumb: "ARVIO Web",
        eyebrow: "Acesso premium opcional pelo navegador",
        h1: "Sua configuração ARVIO no iPhone, iPad, Windows e Mac.",
        hero: "O ARVIO Web estende perfis, bibliotecas, rastreamento e serviços autorizados a navegadores modernos em dispositivos Apple, Windows, macOS, ChromeOS e smart TVs compatíveis.",
        sectionEyebrow: "Um complemento, não uma substituição",
        sectionTitle: "Leve sua biblioteca para o navegador.",
        sectionCopy: "O app Android permanece gratuito e de código aberto. O ARVIO Web é uma assinatura adicional para quem precisa de acesso por navegador, downloads e integrações compatíveis em outras plataformas.",
        features: [
          ["iPhone e iPad", "Abra perfis e bibliotecas em navegadores modernos sem instalar um aplicativo iOS separado."],
          ["Windows e Mac", "Use a interface ARVIO no computador e envie mídia compatível ao VLC quando necessário."],
          ["Apple TV e smart TVs", "Acesse por navegadores compatíveis; suporte de reprodução varia conforme o sistema."],
          ["Configurações na nuvem", "Use perfis e configurações compatíveis vinculados à mesma conta ARVIO."],
          ["Bibliotecas domésticas", "Navegue por Jellyfin, Plex e Emby conectados quando a rede e o servidor permitem acesso."],
          ["Downloads compatíveis", "Baixe fontes permitidas quando a integração, a origem e o navegador oferecem suporte."]
        ],
        stepsTitle: "Comece pelo navegador.",
        steps: [
          ["Conecte sua conta", "Entre com a mesma conta ARVIO usada nos dispositivos Android."],
          ["Inicie o teste", "Use o período de teste disponível para confirmar compatibilidade com seus dispositivos."],
          ["Abra seus serviços", "Navegue pelos perfis, bibliotecas e integrações autorizadas sincronizadas."]
        ],
        faq: [
          ["O aplicativo Android deixa de ser gratuito?", "Não. O aplicativo Android continua gratuito e de código aberto."],
          ["O ARVIO Web funciona em qualquer navegador?", "Não necessariamente. Reprodução, codecs e integrações externas dependem do navegador e do sistema."],
          ["Posso usar no iPhone e no iPad?", "Sim, em navegadores compatíveis, com os recursos disponíveis no ARVIO Web."]
        ],
        noteTitle: "Compatibilidade do navegador importa",
        note: "Os navegadores não oferecem os mesmos codecs e controles do player Android. Alguns formatos exigem transcodificação ou abertura em um aplicativo externo compatível.",
        ctaTitle: "Teste o ARVIO no navegador.",
        ctaCopy: "O premium adiciona acesso por navegador; o app Android continua gratuito.",
        ctaPrimary: "Iniciar teste gratuito",
        ctaPrimaryHref: "/go/premium",
        related: ["hub", "servers", "tracking"]
      }
    }
  },
  es: {
    htmlLang: "es",
    hreflang: "es",
    home: "Inicio",
    guides: "Guías",
    skip: "Ir al contenido",
    mainNav: "Navegación principal",
    breadcrumb: "Ruta de navegación",
    footerNav: "Navegación del pie",
    install: "Instalar ARVIO",
    play: "Google Play",
    source: "Ver código fuente",
    browser: "Conocer ARVIO Web",
    questions: "Preguntas frecuentes",
    related: "Guías relacionadas",
    softwareBoundaryTitle: "Software, no contenido",
    softwareBoundary: "ARVIO no aloja, proporciona, vende, incluye ni redistribuye películas, series, canales, listas o emisiones. Solo conectas servidores, servicios, archivos e integraciones que estás autorizado a utilizar.",
    footerDescription: "Gestión multimedia de código abierto para Android TV, Google TV y Android.",
    footerBoundary: "ARVIO proporciona software, no contenido multimedia.",
    languageLabel: "Idioma",
    languageLink: "Português",
    languageHref: "/pt-br/",
    pageData: {
      hub: {
        title: "Centro multimedia para Android TV, móvil y navegador - ARVIO",
        description: "Organiza servidores, servicios, televisión en vivo, Trakt, Simkl y reproducción con ARVIO en Android TV, iPhone, iPad, Windows y Mac.",
        crumb: "Centro multimedia",
        eyebrow: "Aplicación Android gratuita",
        h1: "Tus servicios multimedia, organizados para el televisor.",
        hero: "ARVIO es un centro de gestión multimedia gratuito y de código abierto para Android TV, Google TV y Android. ARVIO Web opcional extiende funciones compatibles a iPhone, iPad, Windows, Mac, Chromebook, Apple TV y navegadores de televisores inteligentes.",
        sectionEyebrow: "Una interfaz",
        sectionTitle: "Conecta lo que ya utilizas.",
        sectionCopy: "ARVIO no sustituye tu servidor ni proveedor. Presenta servicios compatibles en una biblioteca coherente con perfiles, descubrimiento y reproducción pensados para el mando a distancia.",
        features: [
          ["Jellyfin, Plex y Emby", "Explora bibliotecas autorizadas, contenido reciente y versiones multimedia disponibles en servidores domésticos compatibles."],
          ["Trakt y Simkl", "Usa listas, progreso, historial y scrobbling, eligiendo qué servicio alimenta cada función."],
          ["Televisión en vivo y guía", "Conecta tu proveedor o lista con categorías, favoritos, EPG y datos de catch-up cuando exista compatibilidad."],
          ["Perfiles y nube", "Mantén perfiles separados y sincroniza ajustes compatibles entre dispositivos con una cuenta ARVIO."],
          ["Subtítulos y reproducción", "Elige fuentes de subtítulos, preferencias del reproductor y herramientas opcionales de IA con tus propias credenciales."],
          ["TV, móvil y navegador", "Utiliza la app gratuita en Android y añade acceso premium opcional desde navegadores en dispositivos Apple y ordenadores."]
        ],
        stepsTitle: "Empieza por las partes que necesitas.",
        steps: [
          ["Instala ARVIO", "Usa Google Play en Android TV, Google TV o Android móvil, o instala el APK oficial firmado desde GitHub."],
          ["Crea un perfil", "Elige idioma, aspecto y comportamiento de reproducción para cada persona o uso compartido."],
          ["Conecta servicios autorizados", "Añade únicamente servidores, cuentas, listas e integraciones a los que tengas permiso de acceso."]
        ],
        faq: [
          ["¿ARVIO es gratuito en Android TV?", "Sí. La aplicación Android es gratuita y de código abierto. ARVIO Web es un complemento premium opcional para navegador."],
          ["¿ARVIO incluye películas, series o canales?", "No. ARVIO es software de gestión y reproducción. Tú aportas las fuentes y servicios autorizados."],
          ["¿Qué servicios puede organizar ARVIO?", "Admite servidores Jellyfin, Plex y Emby, televisión en vivo, Trakt, Simkl, subtítulos e integraciones personales compatibles."]
        ],
        noteTitle: "Límite del contenido",
        note: "ARVIO proporciona la interfaz y el reproductor. La disponibilidad y calidad dependen de la fuente conectada, la red y las capacidades del dispositivo.",
        ctaTitle: "Un centro abierto para tu configuración.",
        ctaCopy: "Instálalo gratis en Android o revisa el proyecto completo en GitHub.",
        related: ["servers", "tracking", "live"]
      },
      jellyfin: {
        title: "Jellyfin en Android TV, iPad, Windows y Mac - ARVIO",
        description: "Conecta tu servidor Jellyfin a ARVIO y explora bibliotecas, versiones y progreso en Android TV, móvil y navegador.",
        crumb: "Jellyfin en varios dispositivos",
        eyebrow: "Integración con servidor doméstico",
        h1: "Lleva tu biblioteca Jellyfin a ARVIO.",
        hero: "Conecta un servidor Jellyfin que estés autorizado a utilizar y explora bibliotecas y versiones multimedia en la app Android gratuita o en ARVIO Web opcional para iPhone, iPad, Windows, Mac y otros navegadores compatibles.",
        sectionEyebrow: "Conectado, no copiado",
        sectionTitle: "Tu servidor mantiene el control.",
        sectionCopy: "ARVIO lee información permitida de la biblioteca y medios reproducibles. Las reglas de acceso, usuarios, permisos y disponibilidad siguen bajo el control de Jellyfin.",
        features: [
          ["Bibliotecas en varios dispositivos", "Abre las bibliotecas del servidor desde la página Biblioteca sin reconstruir la organización de Jellyfin."],
          ["Añadido recientemente", "Consulta las novedades en una vista rápida con las mismas tarjetas y detalles de ARVIO."],
          ["Versiones multimedia", "Cuando Jellyfin informa de varios archivos, ARVIO puede presentarlos como opciones separadas de reproducción."],
          ["Progreso y estado visto", "La reproducción actualiza el servidor cuando es compatible, mientras Trakt o Simkl pueden conservar un historial más amplio."],
          ["Tokens protegidos", "Los tokens de acceso se guardan en el almacenamiento cifrado de credenciales de dispositivos Android compatibles."],
          ["Reproducción según el dispositivo", "ARVIO intenta reproducir de forma compatible y Jellyfin puede transcodificar formatos no admitidos directamente."]
        ],
        stepsTitle: "Tres pasos para abrir tu biblioteca.",
        steps: [
          ["Prepara Jellyfin", "Confirma que el servidor es accesible y que el usuario puede abrir las bibliotecas deseadas."],
          ["Conecta desde ajustes", "Añade Jellyfin como servidor doméstico y completa el inicio de sesión o flujo de código."],
          ["Abre Biblioteca", "Selecciona Jellyfin, elige una biblioteca y explora los títulos permitidos para ese usuario."]
        ],
        faq: [
          ["¿ARVIO sustituye el servidor Jellyfin?", "No. Jellyfin sigue gestionando la biblioteca, usuarios y acceso. ARVIO actúa como cliente e interfaz de gestión."],
          ["¿ARVIO muestra distintas versiones de un archivo?", "Sí, cuando el servidor informa esas versiones y el usuario conectado tiene permiso para acceder a ellas."],
          ["¿Todos los archivos se reproducen directamente?", "No necesariamente. El contenedor, códecs, audio, subtítulos, red, dispositivo y transcodificación influyen en la reproducción."]
        ],
        noteTitle: "La reproducción depende de toda la ruta",
        note: "Una conexión válida no garantiza reproducción directa para todos los archivos. El servidor puede necesitar transcodificar formatos no compatibles con el dispositivo o navegador.",
        ctaTitle: "Utiliza tu servidor Jellyfin a tu manera.",
        ctaCopy: "ARVIO es gratuito y de código abierto en Android.",
        related: ["servers", "tracking", "web"]
      },
      servers: {
        title: "Plex, Emby y Jellyfin en TV, iPad, Windows y Mac - ARVIO",
        description: "Explora Plex, Emby y Jellyfin desde una interfaz en Android TV, Android y el navegador opcional de ARVIO.",
        crumb: "Plex, Emby y Jellyfin",
        eyebrow: "Biblioteca con varios servidores",
        h1: "Plex, Emby y Jellyfin en una interfaz conectada.",
        hero: "Abre servidores domésticos autorizados en la app gratuita para Android TV y móvil, o utiliza ARVIO Web opcional en iPhone, iPad, Windows, Mac, Apple TV y navegadores compatibles.",
        sectionEyebrow: "Elige servidor sin cambiar de interfaz",
        sectionTitle: "Una página Biblioteca, fuentes separadas.",
        sectionCopy: "Cada servidor sigue siendo una fuente independiente y fiable. Puedes cambiar de biblioteca manteniendo el mismo diseño de tarjetas, detalles y comportamiento del mando.",
        features: [
          ["Separación por servidor", "Plex, Emby y Jellyfin permanecen separados, conservando permisos y estructura de cada biblioteca."],
          ["Barra lateral de bibliotecas", "Selecciona un servicio conectado y abre las bibliotecas que ofrece sin llenar la pantalla de filtros."],
          ["Contenido reciente", "Consulta los títulos permitidos más recientes de cada servidor en una vista fácil de recorrer."],
          ["Varias versiones multimedia", "Los archivos 4K, 1080p, remux o alternativos pueden aparecer como opciones separadas."],
          ["Experiencia coherente", "Usa detalles, imágenes, metadatos y controles familiares sin importar el servidor de origen."],
          ["Configuración por perfil", "Decide qué servidores y catálogos son visibles para cada perfil, incluidos entornos familiares."]
        ],
        stepsTitle: "Mantén el control donde corresponde.",
        steps: [
          ["Conecta un servidor", "Usa el inicio de sesión o flujo compatible para Plex, Emby o Jellyfin."],
          ["Selecciona bibliotecas", "ARVIO solo lee las bibliotecas disponibles para el usuario autenticado."],
          ["Elige una versión", "Abre un título y selecciona entre las versiones multimedia informadas por el servidor."]
        ],
        faq: [
          ["¿Puedo conectar Plex, Emby y Jellyfin a la vez?", "Sí. Pueden conectarse al mismo perfil y abrirse como fuentes de biblioteca independientes."],
          ["¿ARVIO altera o fusiona las bibliotecas?", "No. Cada servidor sigue siendo la fuente oficial. ARVIO no mueve ni modifica los archivos."],
          ["¿Por qué un título puede tener varias fuentes?", "El servidor puede ofrecer varios archivos o versiones, y ARVIO los muestra para elegir resolución o codificación."]
        ],
        noteTitle: "Sin migración de biblioteca",
        note: "Añadir un servidor no copia archivos ni evita permisos. El Plex, Emby o Jellyfin original debe estar accesible para explorar y reproducir.",
        ctaTitle: "Tus servidores. Una interfaz tranquila.",
        ctaCopy: "La aplicación Android sigue siendo gratuita y de código abierto.",
        related: ["jellyfin", "tracking", "sources"]
      },
      sources: {
        title: "Debrid y Usenet en TV, iPad, Windows y Mac - ARVIO",
        description: "Organiza fuentes compatibles de debrid y Usenet en ARVIO con calidad, tamaño, idioma e información del addon visibles.",
        crumb: "Fuentes de debrid y Usenet",
        eyebrow: "Integraciones personales compatibles",
        h1: "Fuentes de debrid y Usenet organizadas con claridad.",
        hero: "Utiliza fuentes proporcionadas por addons compatibles en la app Android gratuita o en ARVIO Web opcional. ARVIO conserva los detalles y reproduce medios HTTP resueltos en dispositivos compatibles.",
        sectionEyebrow: "Selección transparente",
        sectionTitle: "Comprueba lo que devolvió el addon antes de reproducir.",
        sectionCopy: "Un addon compatible puede proporcionar mucho más que una URL. ARVIO mantiene visibles los datos útiles para que puedas elegir con información.",
        features: [
          ["Calidad y resolución", "Identifica 4K, 1080p, 720p y otros datos declarados mediante distintivos coherentes."],
          ["Tamaño y nombre del archivo", "Compara el tamaño indicado y lee el nombre completo cuando el addon proporciona esos valores."],
          ["Addon y proveedor", "Conserva etiquetas como debrid, Usenet, vault, index o host en lugar de reducir todo a una fuente genérica."],
          ["Idioma y datos técnicos", "Consulta idioma, lanzamiento, códec y otros metadatos disponibles sin duplicaciones innecesarias."],
          ["Ordenación útil", "Respeta el orden de addons y compara tamaño y calidad para localizar opciones preferidas rápidamente."],
          ["Android y navegador", "Usa el reproductor nativo u opciones compatibles de navegador, VLC e Infuse según servicio, formato y dispositivo."]
        ],
        stepsTitle: "El addon resuelve; ARVIO presenta y reproduce.",
        steps: [
          ["Configura una integración", "Utiliza por separado un addon o servicio compatible y una cuenta que estés autorizado a usar."],
          ["Recibe fuentes resueltas", "La integración devuelve metadatos y una URL HTTP reproducible. ARVIO no busca por sí mismo en infraestructura de Usenet o debrid."],
          ["Elige o usa autoplay", "Selecciona un resultado o deja que autoplay utilice el mejor candidato disponible según tus preferencias."]
        ],
        faq: [
          ["¿ARVIO incluye una cuenta de debrid o Usenet?", "No. ARVIO no incluye, vende ni comparte cuentas de proveedores."],
          ["¿ARVIO reproduce torrent o magnet directamente?", "No. La integración debe devolver una fuente multimedia HTTP resuelta y accesible para el dispositivo."],
          ["¿Qué información puede mostrar una fuente?", "Calidad, tamaño, nombre del archivo, idioma, addon, proveedor y otros datos enviados por la integración."]
        ],
        noteTitle: "No se incluye ninguna cuenta, addon o contenido",
        note: "ARVIO no proporciona suscripciones, índices, credenciales, archivos o emisiones y no ofrece reproducción P2P directa. Utiliza únicamente servicios y contenido autorizados.",
        ctaTitle: "Tus integraciones, conservando todos los detalles.",
        ctaCopy: "ARVIO es gratuito y de código abierto en Android.",
        related: ["hub", "tracking", "subtitles"]
      },
      tracking: {
        title: "Sincronización de Trakt y Simkl en TV, iPad, Windows y Mac - ARVIO",
        description: "Conecta Trakt y Simkl a ARVIO para listas, historial, progreso y scrobbling en Android TV, móvil y navegador.",
        crumb: "Sincronización de Trakt y Simkl",
        eyebrow: "Integraciones de seguimiento",
        h1: "Mantén Trakt y Simkl conectados entre dispositivos.",
        hero: "Carga listas, progreso e historial en la app Android gratuita o en ARVIO Web opcional. Elige qué servicio aporta cada función sin tener que abandonar el otro.",
        sectionEyebrow: "Control por función",
        sectionTitle: "Utiliza ambos servicios con responsabilidades claras.",
        sectionCopy: "ARVIO puede conectar Trakt y Simkl al mismo perfil y permite elegir el origen de listas, progreso e historial cuando la función ofrece esa selección.",
        features: [
          ["Seguir viendo", "Recupera el siguiente episodio o una posición compatible mediante el servicio elegido para el progreso."],
          ["Listas y biblioteca", "Abre listas personales y colecciones compatibles en Biblioteca con la misma experiencia de tarjetas."],
          ["Historial visto", "Marcar contenido como visto puede actualizar los servicios conectados según las preferencias de sincronización."],
          ["Scrobbling", "Envía el progreso durante la reproducción a los servicios habilitados, respetando tokens y límites de API."],
          ["Perfiles independientes", "Cada perfil puede utilizar conexiones y preferencias de seguimiento propias."],
          ["Sincronización entre dispositivos", "Las opciones compatibles pueden seguir a tu cuenta ARVIO en Android y en el navegador opcional."]
        ],
        stepsTitle: "Conecta, elige y realiza el seguimiento.",
        steps: [
          ["Autoriza los servicios", "Conecta Trakt y Simkl mediante sus flujos oficiales de autorización."],
          ["Elige el origen", "Define qué servicio aporta listas, progreso y otros datos configurables."],
          ["Reproduce normalmente", "ARVIO actualiza el estado en los servicios habilitados cuando la operación es compatible."]
        ],
        faq: [
          ["¿Puedo conectar Trakt y Simkl juntos?", "Sí. Ambos pueden permanecer conectados, con opciones separadas para funciones compatibles."],
          ["¿ARVIO copia mis contraseñas?", "No. La conexión utiliza flujos de autorización y tokens proporcionados por los servicios."],
          ["¿Los cambios aparecen en otros dispositivos?", "Cuando el servicio acepta la actualización, el nuevo estado queda disponible para otros clientes de la misma cuenta."]
        ],
        noteTitle: "Los servicios siguen siendo el origen de los datos",
        note: "La disponibilidad, velocidad de actualización y límites dependen de las API de Trakt y Simkl. ARVIO no controla interrupciones ni límites externos.",
        ctaTitle: "Tus listas y tu progreso, conectados.",
        ctaCopy: "Utiliza Trakt, Simkl o ambos en ARVIO.",
        related: ["hub", "servers", "web"]
      },
      live: {
        title: "Televisión en vivo y EPG en Android TV, iPad, Windows y Mac - ARVIO",
        description: "Usa tu proveedor autorizado de televisión en vivo en ARVIO con orden original, favoritos, EPG, búsqueda y catch-up compatible.",
        crumb: "Televisión en vivo y EPG",
        eyebrow: "Compatibilidad con proveedor autorizado",
        h1: "Una guía de televisión en vivo para todas tus pantallas.",
        hero: "Explora tu proveedor en Android TV y móvil o utiliza ARVIO Web opcional en iPhone, iPad, Windows, Mac y navegadores compatibles. La reproducción depende del proveedor y dispositivo.",
        sectionEyebrow: "Diseñado para listas grandes",
        sectionTitle: "Canales, grupos y guía en una experiencia fluida.",
        sectionCopy: "ARVIO conserva el orden original del proveedor, carga datos por etapas y prioriza favoritos para que las listas grandes sean utilizables rápidamente.",
        features: [
          ["Orden del proveedor", "Los grupos y canales respetan la secuencia entregada por el proveedor salvo que los reorganices."],
          ["EPG progresivo", "Los datos en caché aparecen primero y las nuevas coincidencias se completan sin bloquear la navegación."],
          ["Favoritos prioritarios", "Los canales favoritos y sus programas tienen prioridad durante la apertura inicial de la guía."],
          ["Búsqueda y categorías", "Encuentra canales y programas y oculta o reordena grupos por perfil."],
          ["Catch-up compatible", "Consulta programas anteriores y reproduce archivos cuando el proveedor ofrece direcciones válidas."],
          ["Mando y pantalla táctil", "La interfaz se adapta a la navegación por foco del televisor y a los controles móviles."]
        ],
        stepsTitle: "Del proveedor a la guía.",
        steps: [
          ["Añade tu lista o cuenta", "Usa los datos proporcionados por un servicio que estés autorizado a utilizar."],
          ["Carga el EPG", "ARVIO lee las fuentes configuradas y relaciona programas por identificador y nombre compatible."],
          ["Elige un canal", "Abre la emisión en vivo o un programa anterior cuando exista catch-up."]
        ],
        faq: [
          ["¿ARVIO proporciona canales o listas?", "No. Debes conectar un proveedor o una lista que tengas autorización para utilizar."],
          ["¿Por qué algunos canales no tienen guía?", "El proveedor puede no ofrecer EPG o los identificadores y nombres pueden no coincidir."],
          ["¿El catch-up funciona en todos los canales?", "No. Depende de la disponibilidad, formato y direcciones de archivo proporcionadas por el proveedor."]
        ],
        noteTitle: "La calidad depende del proveedor",
        note: "El EPG, duración del historial, catch-up y estabilidad de la emisión proceden del servicio conectado. ARVIO organiza y reproduce los datos recibidos.",
        ctaTitle: "Tu proveedor, organizado para el mando.",
        ctaCopy: "Utiliza ARVIO gratis en Android TV y Android.",
        related: ["hub", "web", "tracking"]
      },
      subtitles: {
        title: "Subtítulos con IA en Android TV, iPad, Windows y Mac - ARVIO",
        description: "Busca, ajusta y traduce subtítulos en ARVIO con herramientas opcionales de IA y tus propias credenciales compatibles.",
        crumb: "Subtítulos con IA",
        eyebrow: "Herramientas opcionales de accesibilidad",
        h1: "Control de subtítulos en TV, móvil y navegador.",
        hero: "Utiliza búsqueda y estilos de subtítulos en la app Android gratuita y herramientas opcionales de traducción mediante IA con tu propia cuenta compatible. Las funciones dependen del reproductor y la fuente.",
        sectionEyebrow: "Subtítulos que se adaptan",
        sectionTitle: "Legibilidad sin ocultar el vídeo.",
        sectionCopy: "ARVIO reúne búsqueda, selección, estilo y posición de subtítulos en controles adecuados para televisión y móvil.",
        features: [
          ["Búsqueda de subtítulos", "Consulta proveedores compatibles y elige idioma y versión apropiados para el archivo."],
          ["Estilo personalizable", "Ajusta tamaño, color, fondo y posición para distintas pantallas y preferencias."],
          ["Barras negras y encuadre", "Cuando el reproductor lo permite, posiciona los subtítulos respecto a la pantalla y no solo al vídeo."],
          ["Traducción opcional con IA", "Utiliza tu propia cuenta compatible para traducir texto sin incluir una suscripción de IA en ARVIO."],
          ["Controles para TV", "Cambia de pista y ajusta preferencias sin menús difíciles de usar con el mando."],
          ["Compatibilidad por formato", "SRT, WebVTT, subtítulos integrados y otros formatos dependen del reproductor y la fuente."]
        ],
        stepsTitle: "Encuentra y ajusta la pista correcta.",
        steps: [
          ["Abre Subtítulos", "Durante la reproducción, abre el selector y consulta las pistas disponibles."],
          ["Elige idioma y estilo", "Selecciona la pista y aplica las preferencias visuales del perfil."],
          ["Usa IA cuando la necesites", "Configura tu credencial compatible y traduce únicamente cuando resulte útil."]
        ],
        faq: [
          ["¿ARVIO incluye un servicio de IA de pago?", "No. Las herramientas de IA son opcionales y requieren tu propia cuenta compatible."],
          ["¿Todas las fuentes ofrecen subtítulos?", "No. La disponibilidad depende del contenido, addon, servidor y proveedores configurados."],
          ["¿Puedo cambiar la posición de los subtítulos?", "Sí, en reproductores y formatos compatibles, además del tamaño, color y fondo."]
        ],
        noteTitle: "La precisión puede variar",
        note: "Las traducciones automáticas pueden contener errores. Revisa los resultados cuando la precisión sea importante y respeta los términos del servicio de IA.",
        ctaTitle: "Subtítulos claros en cualquier pantalla.",
        ctaCopy: "El reproductor Android de ARVIO sigue siendo gratuito y de código abierto.",
        related: ["hub", "sources", "web"]
      },
      firetv: {
        title: "ARVIO en Fire TV - instalación y compatibilidad",
        description: "Instala el APK oficial de ARVIO en dispositivos Fire TV compatibles y utiliza una interfaz diseñada para mando a distancia.",
        crumb: "ARVIO en Fire TV",
        eyebrow: "Dispositivos Fire OS compatibles",
        h1: "Utiliza ARVIO en Fire TV.",
        hero: "La aplicación Android TV puede instalarse manualmente en dispositivos Fire TV compatibles, llevando bibliotecas, seguimiento, televisión en vivo y el reproductor de ARVIO a Fire OS.",
        sectionEyebrow: "El mismo APK oficial",
        sectionTitle: "Una experiencia de TV sin una versión paralela.",
        sectionCopy: "Fire TV ejecuta el build Android firmado publicado en GitHub. La compatibilidad varía según la versión de Fire OS, el hardware y los servicios instalados.",
        features: [
          ["Interfaz para mando", "Las tarjetas, menús y reproductor están diseñados para navegación por foco en un televisor."],
          ["APK oficial firmado", "Utiliza la versión del repositorio oficial para mantener la firma y permitir actualizaciones compatibles."],
          ["Servidores domésticos", "Conecta Jellyfin, Plex y Emby autorizados como en otros dispositivos Android compatibles."],
          ["Seguimiento", "Utiliza Trakt y Simkl para listas, progreso e historial cuando las cuentas estén conectadas."],
          ["Televisión en vivo", "Abre tu proveedor o lista autorizada con EPG y funciones compatibles."],
          ["Limitaciones de plataforma", "Google Play, códecs, memoria y comportamiento en segundo plano pueden diferir de Android TV."]
        ],
        stepsTitle: "Instalación consciente.",
        steps: [
          ["Descarga desde GitHub", "Obtén el APK de release desde la página oficial del proyecto."],
          ["Autoriza la instalación", "Activa temporalmente el permiso para instalar apps desde la fuente utilizada en Fire TV."],
          ["Abre y configura", "Conecta únicamente cuentas, servidores y fuentes que estés autorizado a utilizar."]
        ],
        faq: [
          ["¿ARVIO está en Amazon Appstore?", "La disponibilidad puede variar. El método documentado es instalar el APK oficial desde GitHub en hardware compatible."],
          ["¿El mismo APK funciona en todos los Fire TV?", "No se garantiza en todos los modelos. El rendimiento y los códecs dependen del hardware y Fire OS."],
          ["¿Puedo actualizar sin perder datos?", "Sí, si el nuevo APK utiliza el mismo paquete y firma oficial. No desinstales la app si quieres conservar los datos locales."]
        ],
        noteTitle: "La instalación manual requiere atención",
        note: "Comprueba siempre el repositorio oficial y la firma. Builds de terceros pueden no actualizarse correctamente y pueden poner en riesgo tus datos.",
        ctaTitle: "ARVIO en tu pantalla Fire TV.",
        ctaCopy: "Descarga el APK oficial firmado desde GitHub.",
        ctaPrimary: "GitHub Releases",
        ctaPrimaryHref: "https://github.com/ProdigyV21/ARVIO/releases",
        related: ["hub", "live", "servers"]
      },
      web: {
        title: "ARVIO Web para iPhone, iPad, Windows, Mac y Apple TV",
        description: "Utiliza el complemento premium ARVIO Web en iPhone, iPad, Windows, Mac, Apple TV y navegadores compatibles.",
        crumb: "ARVIO Web",
        eyebrow: "Acceso premium opcional desde navegador",
        h1: "Tu configuración ARVIO en iPhone, iPad, Windows y Mac.",
        hero: "ARVIO Web extiende perfiles, bibliotecas, seguimiento y servicios autorizados a navegadores modernos en dispositivos Apple, Windows, macOS, ChromeOS y televisores inteligentes compatibles.",
        sectionEyebrow: "Un complemento, no un sustituto",
        sectionTitle: "Lleva tu biblioteca al navegador.",
        sectionCopy: "La app Android sigue siendo gratuita y de código abierto. ARVIO Web es una suscripción adicional para quienes necesitan acceso por navegador, descargas e integraciones compatibles en otras plataformas.",
        features: [
          ["iPhone y iPad", "Abre perfiles y bibliotecas en navegadores modernos sin instalar una aplicación iOS independiente."],
          ["Windows y Mac", "Utiliza la interfaz ARVIO en el ordenador y envía medios compatibles a VLC cuando sea necesario."],
          ["Apple TV y smart TVs", "Accede desde navegadores compatibles; la reproducción depende del sistema."],
          ["Ajustes en la nube", "Utiliza perfiles y configuraciones compatibles vinculados a la misma cuenta ARVIO."],
          ["Bibliotecas domésticas", "Explora Jellyfin, Plex y Emby conectados cuando la red y el servidor permiten el acceso."],
          ["Descargas compatibles", "Descarga fuentes permitidas cuando la integración, el origen y el navegador ofrecen compatibilidad."]
        ],
        stepsTitle: "Empieza desde el navegador.",
        steps: [
          ["Conecta tu cuenta", "Inicia sesión con la misma cuenta ARVIO utilizada en tus dispositivos Android."],
          ["Inicia la prueba", "Utiliza el periodo de prueba disponible para confirmar la compatibilidad de tus dispositivos."],
          ["Abre tus servicios", "Explora perfiles, bibliotecas e integraciones autorizadas sincronizadas."]
        ],
        faq: [
          ["¿La aplicación Android deja de ser gratuita?", "No. La aplicación Android sigue siendo gratuita y de código abierto."],
          ["¿ARVIO Web funciona en cualquier navegador?", "No necesariamente. La reproducción, códecs e integraciones externas dependen del navegador y sistema."],
          ["¿Puedo utilizarlo en iPhone y iPad?", "Sí, desde navegadores compatibles y con las funciones disponibles en ARVIO Web."]
        ],
        noteTitle: "La compatibilidad del navegador importa",
        note: "Los navegadores no ofrecen los mismos códecs y controles que el reproductor Android. Algunos formatos requieren transcodificación o una aplicación externa compatible.",
        ctaTitle: "Prueba ARVIO en el navegador.",
        ctaCopy: "Premium añade acceso web; la app Android continúa siendo gratuita.",
        ctaPrimary: "Iniciar prueba gratuita",
        ctaPrimaryHref: "/go/premium",
        related: ["hub", "servers", "tracking"]
      }
    }
  }
};

const directoryLabels = {
  pt: {
    hub: ["Comece aqui", "Central de mídia em vários dispositivos", "Perfis, servidores, rastreamento, TV ao vivo, legendas e reprodução em Android e navegador."],
    jellyfin: ["Servidores domésticos", "Jellyfin na TV e no navegador", "Navegue por bibliotecas Jellyfin sem substituir seu servidor."],
    servers: ["Servidores domésticos", "Plex, Emby e Jellyfin juntos", "Organize vários servidores autorizados em uma experiência de biblioteca."],
    sources: ["Fontes e reprodução", "Fontes de debrid e Usenet", "Mantenha qualidade, tamanho, idioma e origem visíveis."],
    tracking: ["Acompanhamento", "Sincronização Trakt e Simkl", "Conecte listas, progresso, histórico e scrobbling."],
    live: ["TV ao vivo", "TV ao vivo e EPG", "Use seu provedor autorizado com guia, favoritos e catch-up compatível."],
    subtitles: ["Acessibilidade", "Legendas com IA", "Pesquise, ajuste e traduza legendas com ferramentas opcionais."],
    firetv: ["Dispositivos", "ARVIO no Fire TV", "Instale o APK oficial em hardware Fire TV compatível."],
    web: ["Escolha a hospedagem", "ARVIO Web: gratuito ou gerenciado", "Compare a hospedagem própria gratuita, o Premium opcional, os perfis e a compatibilidade do navegador."]
  },
  es: {
    hub: ["Empieza aquí", "Centro multimedia en varios dispositivos", "Perfiles, servidores, seguimiento, televisión en vivo, subtítulos y reproducción en Android y navegador."],
    jellyfin: ["Servidores domésticos", "Jellyfin en TV y navegador", "Explora bibliotecas Jellyfin sin sustituir tu servidor."],
    servers: ["Servidores domésticos", "Plex, Emby y Jellyfin juntos", "Organiza varios servidores autorizados en una experiencia de biblioteca."],
    sources: ["Fuentes y reproducción", "Fuentes de debrid y Usenet", "Mantén visibles calidad, tamaño, idioma y procedencia."],
    tracking: ["Seguimiento", "Sincronización de Trakt y Simkl", "Conecta listas, progreso, historial y scrobbling."],
    live: ["Televisión en vivo", "Televisión en vivo y EPG", "Usa tu proveedor autorizado con guía, favoritos y catch-up compatible."],
    subtitles: ["Accesibilidad", "Subtítulos con IA", "Busca, ajusta y traduce subtítulos con herramientas opcionales."],
    firetv: ["Dispositivos", "ARVIO en Fire TV", "Instala el APK oficial en hardware Fire TV compatible."],
    web: ["Elige el alojamiento", "ARVIO Web: gratis o gestionado", "Compara el autoalojamiento gratuito, Premium opcional, los perfiles y la compatibilidad del navegador."]
  }
};

const entryCopy = {
  pt: {
    title: "ARVIO Brasil - Central de mídia gratuita para Android TV",
    description: "Conheça o ARVIO em português: uma central de mídia gratuita e de código aberto para Android TV, servidores domésticos, TV ao vivo, Trakt, Simkl e legendas.",
    eyebrow: "ARVIO em português do Brasil",
    h1: "Tudo o que você assiste. Uma interface organizada.",
    lead: "O ARVIO reúne servidores, serviços e ferramentas de reprodução que você já usa em uma experiência rápida para Android TV, Google TV e Android. O ARVIO Web opcional amplia o acesso para iPhone, iPad, Windows, Mac e outros navegadores compatíveis.",
    sectionTitle: "Conheça os principais recursos.",
    sectionCopy: "Comece pela visão geral ou abra um guia específico para seu servidor, rastreador, provedor ou dispositivo.",
    guidesButton: "Abrir todos os guias",
    freeTitle: "Gratuito e de código aberto no Android.",
    freeCopy: "O premium é um complemento opcional para navegador; o aplicativo Android continua gratuito."
  },
  es: {
    title: "ARVIO en español - Centro multimedia gratuito para Android TV",
    description: "Conoce ARVIO en español: un centro multimedia gratuito y de código abierto para Android TV, servidores domésticos, TV en vivo, Trakt, Simkl y subtítulos.",
    eyebrow: "ARVIO en español",
    h1: "Todo lo que ves. Una interfaz organizada.",
    lead: "ARVIO reúne servidores, servicios y herramientas de reproducción que ya utilizas en una experiencia rápida para Android TV, Google TV y Android. ARVIO Web opcional amplía el acceso a iPhone, iPad, Windows, Mac y otros navegadores compatibles.",
    sectionTitle: "Descubre las funciones principales.",
    sectionCopy: "Empieza por la visión general o abre una guía específica para tu servidor, servicio de seguimiento, proveedor o dispositivo.",
    guidesButton: "Abrir todas las guías",
    freeTitle: "Gratuito y de código abierto en Android.",
    freeCopy: "Premium es un complemento opcional para navegador; la aplicación Android sigue siendo gratuita."
  }
};

const guideCopy = {
  pt: {
    title: "Guias do ARVIO - Android TV, servidores, rastreamento e TV ao vivo",
    description: "Guias do ARVIO em português para Android TV, Jellyfin, Plex, Emby, Trakt, Simkl, TV ao vivo, legendas, debrid, Usenet e acesso pelo navegador.",
    eyebrow: "Guias do produto",
    h1: "Monte sua própria central de mídia conectada.",
    lead: "Comece pelo aplicativo Android gratuito, hospede o ARVIO Web no seu próprio servidor ou escolha a hospedagem gerenciada. Estes guias ajudam você a conectar suas fontes e testar o dispositivo que usa.",
    noteTitle: "Software, não conteúdo",
    ctaTitle: "Gratuito e de código aberto no Android.",
    ctaCopy: "Instale pelo Google Play ou examine o projeto completo no GitHub."
  },
  es: {
    title: "Guías de ARVIO - Android TV, servidores, seguimiento y TV en vivo",
    description: "Guías de ARVIO en español para Android TV, Jellyfin, Plex, Emby, Trakt, Simkl, televisión en vivo, subtítulos, debrid, Usenet y navegador.",
    eyebrow: "Guías del producto",
    h1: "Crea tu propio centro multimedia conectado.",
    lead: "Empieza con la app Android gratuita, aloja ARVIO Web en tu propio servidor o elige alojamiento gestionado. Estas guías te ayudan a conectar tus fuentes y probar el dispositivo que utilizas.",
    noteTitle: "Software, no contenido",
    ctaTitle: "Gratuito y de código abierto en Android.",
    ctaCopy: "Instálalo desde Google Play o revisa el proyecto completo en GitHub."
  }
};

function absolute(urlPath) {
  return `${baseUrl}${urlPath}`;
}

function hreflangLinks(key) {
  const links = pageMap[key];
  return `<!-- hreflang:start -->
  <link rel="alternate" hreflang="en" href="${absolute(links.en)}">
  <link rel="alternate" hreflang="pt-BR" href="${absolute(links.pt)}">
  <link rel="alternate" hreflang="es" href="${absolute(links.es)}">
  <link rel="alternate" hreflang="x-default" href="${absolute(links.en)}">
  <!-- hreflang:end -->`;
}

function header(localeKey, current = "", pageKey = current || "home") {
  const locale = locales[localeKey];
  const otherLocale = localeKey === "pt" ? "es" : "pt";
  const languageHref = pageMap[pageKey][otherLocale];
  return `<a class="skip-link" href="#content">${locale.skip}</a>
  <header class="site-header"><div class="header-inner"><a class="brand-link" href="${pageMap.home[localeKey]}" aria-label="ARVIO ${locale.home}"><img src="/assets/arvio-wordmark.png" alt="ARVIO"></a><nav class="header-nav" aria-label="${locale.mainNav}"><a href="${pageMap.home[localeKey]}"${current === "home" ? ' aria-current="page"' : ""}>${locale.home}</a><a href="${pageMap.guides[localeKey]}"${current === "guides" ? ' aria-current="page"' : ""}>${locale.guides}</a><a href="${languageHref}">${locale.languageLink}</a><a class="play-link" href="${playUrl}">${locale.play}</a></nav></div></header>`;
}

function footer(localeKey, extra = "") {
  const locale = locales[localeKey];
  return `<footer class="site-footer"><div class="shell"><div class="footer-main"><div class="footer-brand"><img src="/assets/arvio-wordmark.png" alt="ARVIO"><p>${locale.footerDescription}</p></div><nav class="footer-links" aria-label="${locale.footerNav}"><a href="${pageMap.home[localeKey]}">${locale.home}</a><a href="${pageMap.guides[localeKey]}">${locale.guides}</a><a href="/privacy">Privacy</a><a href="${githubUrl}">GitHub</a></nav></div><div class="footer-bottom"><span>© 2026 ARVIO</span><span>${extra || locale.footerBoundary}</span></div></div></footer>`;
}

function structuredData(localeKey, key, page) {
  const locale = locales[localeKey];
  const url = absolute(pageMap[key][localeKey]);
  return JSON.stringify({
    "@context": "https://schema.org",
    "@graph": [
      {
        "@type": "SoftwareApplication",
        name: "ARVIO",
        applicationCategory: "MultimediaApplication",
        operatingSystem: "Android TV, Google TV, Android",
        url,
        downloadUrl: playUrl,
        codeRepository: githubUrl,
        inLanguage: locale.htmlLang,
        offers: { "@type": "Offer", price: "0", priceCurrency: "USD" },
        description: page.description
      },
      {
        "@type": "BreadcrumbList",
        itemListElement: [
          { "@type": "ListItem", position: 1, name: "ARVIO", item: absolute(pageMap.home[localeKey]) },
          { "@type": "ListItem", position: 2, name: locale.guides, item: absolute(pageMap.guides[localeKey]) },
          { "@type": "ListItem", position: 3, name: page.crumb, item: url }
        ]
      },
      {
        "@type": "FAQPage",
        inLanguage: locale.htmlLang,
        mainEntity: page.faq.map(([question, answer]) => ({
          "@type": "Question",
          name: question,
          acceptedAnswer: { "@type": "Answer", text: answer }
        }))
      }
    ]
  });
}

function renderFeaturePage(localeKey, key) {
  const locale = locales[localeKey];
  const page = locale.pageData[key];
  const pathForLocale = pageMap[key][localeKey];
  const relatedCards = page.related.map((relatedKey) => {
    const [category, title, description] = directoryLabels[localeKey][relatedKey];
    return `<a class="related-link" href="${pageMap[relatedKey][localeKey]}"><small>${category}</small><strong>${title}</strong><span>${description}</span></a>`;
  }).join("");
  const features = page.features.map(([title, copy]) => `<article class="feature-item"><h3>${title}</h3><p>${copy}</p></article>`).join("");
  const steps = page.steps.map(([title, copy]) => `<article class="step"><h3>${title}</h3><p>${copy}</p></article>`).join("");
  const faq = page.faq.map(([question, answer]) => `<details><summary>${question}</summary><p>${answer}</p></details>`).join("");
  const primaryLabel = page.ctaPrimary || locale.install;
  const primaryHref = page.ctaPrimaryHref || playUrl;
  return `<!DOCTYPE html>
<html lang="${locale.htmlLang}">
<head>
  <meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${page.title}</title>
  <meta name="description" content="${page.description}">
  <link rel="canonical" href="${absolute(pathForLocale)}">
  ${hreflangLinks(key)}
  <link rel="icon" href="/assets/arvio-icon-512.png" type="image/png"><link rel="stylesheet" href="/guides/guide.css">
  <meta property="og:type" content="website"><meta property="og:site_name" content="ARVIO"><meta property="og:locale" content="${localeKey === "pt" ? "pt_BR" : "es_ES"}"><meta property="og:title" content="${page.title}"><meta property="og:description" content="${page.description}"><meta property="og:url" content="${absolute(pathForLocale)}"><meta property="og:image" content="${baseUrl}/assets/${images[key]}">
  <meta name="twitter:card" content="summary_large_image"><meta name="twitter:title" content="${page.title}"><meta name="twitter:description" content="${page.description}"><meta name="twitter:image" content="${baseUrl}/assets/${images[key]}">
  <script type="application/ld+json">${structuredData(localeKey, key, page)}</script>
</head>
<body>
  ${header(localeKey, "", key)}
  <main id="content">
    <section class="hero"><img class="hero-media" src="/assets/${images[key]}" alt="${page.crumb} - ARVIO"><div class="hero-shade" aria-hidden="true"></div><div class="hero-content"><nav class="breadcrumb" aria-label="${locale.breadcrumb}"><a href="${pageMap.home[localeKey]}">ARVIO</a><span aria-hidden="true">/</span><a href="${pageMap.guides[localeKey]}">${locale.guides}</a><span aria-hidden="true">/</span><span>${page.crumb}</span></nav><p class="eyebrow">${page.eyebrow}</p><h1>${page.h1}</h1><p class="hero-copy">${page.hero}</p><div class="actions"><a class="button" href="${primaryHref}">${primaryLabel}</a><a class="button secondary" href="${pageMap.guides[localeKey]}">${locale.guides}</a></div></div></section>

    <section class="section"><div class="shell"><div class="section-head"><p class="eyebrow">${page.sectionEyebrow}</p><h2>${page.sectionTitle}</h2><p>${page.sectionCopy}</p></div><div class="feature-list">${features}</div></div></section>
    <section class="section"><div class="shell"><div class="section-head"><h2>${page.stepsTitle}</h2></div><div class="steps">${steps}</div></div></section>
    <section class="section compact"><div class="shell"><div class="note"><h2>${page.noteTitle}</h2><p>${page.note}</p></div></div></section>
    <section class="section"><div class="shell"><div class="section-head"><p class="eyebrow">${locale.questions}</p><h2>${page.crumb}: FAQ</h2></div><div class="faq-list">${faq}</div></div></section>
    <section class="section"><div class="shell"><div class="section-head"><p class="eyebrow">${locale.related}</p><h2>${locale.guides}</h2></div><div class="related-grid">${relatedCards}</div></div></section>
    <section class="cta-band"><div class="shell cta-inner"><div><h2>${page.ctaTitle}</h2><p>${page.ctaCopy}</p></div><div class="actions"><a class="button" href="${primaryHref}">${primaryLabel}</a><a class="button secondary" href="${githubUrl}">${locale.source}</a></div></div></section>
  </main>
  ${footer(localeKey)}
</body>
</html>
`;
}

function renderDirectory(localeKey, isHome = false) {
  const locale = locales[localeKey];
  const copy = isHome ? entryCopy[localeKey] : guideCopy[localeKey];
  const key = isHome ? "home" : "guides";
  const route = pageMap[key][localeKey];
  const extraCards = standaloneGuideRoutes.map(({ route, labels }) => {
    const [category, title, description] = labels[localeKey];
    return `<a class="guide-card" href="${route}" hreflang="en"><small>${category}</small><h2>${title}</h2><p>${description}</p></a>`;
  }).join("");
  const cards = extraCards + Object.keys(directoryLabels[localeKey]).map((pageKey) => {
    const [category, title, description] = directoryLabels[localeKey][pageKey];
    return `<a class="guide-card" href="${pageMap[pageKey][localeKey]}"><small>${category}</small><h2>${title}</h2><p>${description}</p></a>`;
  }).join("");
  const graphType = isHome ? "WebPage" : "CollectionPage";
  const json = JSON.stringify({
    "@context": "https://schema.org",
    "@type": graphType,
    name: copy.title,
    url: absolute(route),
    inLanguage: locale.htmlLang,
    description: copy.description,
    isPartOf: { "@type": "WebSite", name: "ARVIO", url: baseUrl }
  });
  const heroAlt = localeKey === "pt" ? "Interface do ARVIO em uma televisão" : "Interfaz de ARVIO en un televisor";
  const intro = isHome ? `<section class="hero"><img class="hero-media" src="/assets/${images.home}" alt="${heroAlt}"><div class="hero-shade" aria-hidden="true"></div><div class="hero-content"><nav class="breadcrumb" aria-label="${locale.breadcrumb}"><span>ARVIO</span></nav><p class="eyebrow">${copy.eyebrow}</p><h1>${copy.h1}</h1><p class="hero-copy">${copy.lead}</p><div class="actions"><a class="button" href="${playUrl}">${locale.install}</a><a class="button secondary" href="${pageMap.guides[localeKey]}">${copy.guidesButton}</a></div></div></section>` : `<section class="guide-intro"><div class="shell"><nav class="breadcrumb" aria-label="${locale.breadcrumb}"><a href="${pageMap.home[localeKey]}">ARVIO</a><span aria-hidden="true">/</span><span>${locale.guides}</span></nav><p class="eyebrow">${copy.eyebrow}</p><h1>${copy.h1}</h1><p class="lead">${copy.lead}</p></div></section>`;
  const sectionHeading = isHome ? `<div class="section-head"><h2>${copy.sectionTitle}</h2><p>${copy.sectionCopy}</p></div>` : "";
  const noteTitle = isHome ? locale.softwareBoundaryTitle : copy.noteTitle;
  const ctaTitle = isHome ? copy.freeTitle : copy.ctaTitle;
  const ctaCopy = isHome ? copy.freeCopy : copy.ctaCopy;
  return `<!DOCTYPE html>
<html lang="${locale.htmlLang}">
<head>
  <meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${copy.title}</title>
  <meta name="description" content="${copy.description}">
  <link rel="canonical" href="${absolute(route)}">
  ${hreflangLinks(key)}
  <link rel="icon" href="/assets/arvio-icon-512.png" type="image/png"><link rel="stylesheet" href="/guides/guide.css">
  <meta property="og:type" content="website"><meta property="og:site_name" content="ARVIO"><meta property="og:locale" content="${localeKey === "pt" ? "pt_BR" : "es_ES"}"><meta property="og:title" content="${copy.title}"><meta property="og:description" content="${copy.description}"><meta property="og:url" content="${absolute(route)}"><meta property="og:image" content="${baseUrl}/assets/arvio-social.webp">
  <meta name="twitter:card" content="summary_large_image"><meta name="twitter:title" content="${copy.title}"><meta name="twitter:description" content="${copy.description}"><meta name="twitter:image" content="${baseUrl}/assets/arvio-social.webp">
  <script type="application/ld+json">${json}</script>
  <script defer src="/assets/premium-journey.js?v=2"></script>
</head>
<body>
  ${header(localeKey, key)}
  <main id="content">
    ${intro}
    <section class="section"><div class="shell">${sectionHeading}<div class="guide-directory">${cards}</div></div></section>
    <section class="section compact"><div class="shell"><div class="note"><h2>${noteTitle}</h2><p>${locale.softwareBoundary}</p></div></div></section>
    <section class="cta-band"><div class="shell cta-inner"><div><h2>${ctaTitle}</h2><p>${ctaCopy}</p></div><div class="actions"><a class="button" href="${playUrl}">${locale.install}</a><a class="button secondary" href="${githubUrl}">${locale.source}</a></div></div></section>
  </main>
  ${footer(localeKey)}
</body>
</html>
`;
}

function writeRoute(route, html) {
  const directory = path.join(siteRoot, route.replace(/^\//, ""));
  fs.mkdirSync(directory, { recursive: true });
  fs.writeFileSync(path.join(directory, "index.html"), html, "utf8");
}

function addEnglishHreflang(key) {
  const englishPath = pageMap[key].en === "/" ? path.join(siteRoot, "index.html") : path.join(siteRoot, pageMap[key].en.replace(/^\//, ""), "index.html");
  let html = fs.readFileSync(englishPath, "utf8");
  const block = hreflangLinks(key);
  if (/<!-- hreflang:start -->[\s\S]*?<!-- hreflang:end -->/.test(html)) {
    html = html.replace(/<!-- hreflang:start -->[\s\S]*?<!-- hreflang:end -->/, block);
  } else {
    const canonicalPattern = /(<link rel="canonical"[^>]*>)/;
    if (!canonicalPattern.test(html)) throw new Error(`Canonical link not found in ${englishPath}`);
    html = html.replace(canonicalPattern, `$1\n  ${block}`);
  }
  fs.writeFileSync(englishPath, html, "utf8");
}

function writeSitemap() {
  const priorities = { home: "1.0", guides: "0.9", hub: "0.9", sources: "0.9", jellyfin: "0.8", servers: "0.8", tracking: "0.8", live: "0.8", web: "0.8", subtitles: "0.7", firetv: "0.7" };
  // Regenerating translations must not replace newer English update dates.
  const sitemapFile = path.join(siteRoot, "sitemap.xml");
  const existing = fs.existsSync(sitemapFile) ? fs.readFileSync(sitemapFile, "utf8") : "";
  const existingDates = new Map([...existing.matchAll(/<url>\s*<loc>([^<]+)<\/loc>\s*<lastmod>([^<]+)<\/lastmod>/gu)].map(([, url, date]) => [url, date]));
  const entries = [];
  for (const [key, routes] of Object.entries(pageMap)) {
    for (const localeKey of ["en", "pt", "es"]) {
      const updated = existingDates.get(absolute(routes[localeKey])) || (key === "guides" ? "2026-10-02" : lastmod);
      entries.push(`  <url>\n    <loc>${absolute(routes[localeKey])}</loc>\n    <lastmod>${updated}</lastmod>\n    <changefreq>${key === "home" ? "weekly" : "monthly"}</changefreq>\n    <priority>${localeKey === "en" ? priorities[key] : Math.max(Number(priorities[key]) - 0.1, 0.6).toFixed(1)}</priority>\n  </url>`);
    }
  }
  for (const route of ["/premium/", "/media-kit/"]) entries.push(`  <url><loc>${absolute(route)}</loc></url>`);
  for (const { route, updated } of standaloneGuideRoutes) entries.push(`  <url><loc>${absolute(route)}</loc><lastmod>${updated}</lastmod><changefreq>monthly</changefreq></url>`);
  fs.writeFileSync(path.join(siteRoot, "sitemap.xml"), `<?xml version="1.0" encoding="UTF-8"?>\n<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">\n${entries.join("\n")}\n</urlset>\n`, "utf8");
}

const directoriesOnly = process.argv.includes("--directories-only");
for (const localeKey of ["pt", "es"]) {
  writeRoute(pageMap.guides[localeKey], renderDirectory(localeKey, false));
  if (directoriesOnly) continue;
  for (const key of Object.keys(locales[localeKey].pageData)) {
    writeRoute(pageMap[key][localeKey], renderFeaturePage(localeKey, key));
  }
}

if (!directoriesOnly) for (const key of Object.keys(pageMap)) addEnglishHreflang(key);
writeSitemap();
if (!directoriesOnly) await import("./generate-localized-homepages.mjs");

console.log(directoriesOnly ? "Updated localized guide directories and sitemap; feature pages and homepages preserved." : "Generated 22 localized pages, matching localized homepages, reciprocal hreflang links, and sitemap entries.");
