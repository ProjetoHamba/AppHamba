package com.apphamba.hamba.infra.persistencia;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FormatadorImagem;
import com.apphamba.hamba.titulo.dominio.Episodio;
import com.apphamba.hamba.titulo.dominio.Filme;
import com.apphamba.hamba.titulo.dominio.Serie;
import com.apphamba.hamba.titulo.dominio.Temporada;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.EpisodioDao;
import com.apphamba.hamba.titulo.persistencia.FilmeDao;
import com.apphamba.hamba.titulo.persistencia.SerieDao;
import com.apphamba.hamba.titulo.persistencia.TemporadaDao;
import com.apphamba.hamba.titulo.persistencia.TituloDao;
import com.apphamba.hamba.usuario.dominio.Pessoa;
import com.apphamba.hamba.usuario.dominio.Usuario;
import com.apphamba.hamba.usuario.persistencia.PessoaDAO;
import com.apphamba.hamba.usuario.persistencia.UsuarioDAO;

public class PopularBanco {
    public static final PopularBanco criador = new PopularBanco();
    private FormatadorImagem formatadorImagem = new FormatadorImagem();
    private TituloDao tituloDao = new TituloDao();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();

    public void popularBanco() {
        if (!this.bancoIsPopulado()) {
            this.inserirTitulos();
        }
    }

    private boolean bancoIsPopulado() {
        String query = "SELECT * FROM titulo";
        Cursor cursor = this.execQuery(query);
        boolean populado = false;
        if (cursor.moveToFirst()) {
            populado = true;
        }
        return populado;
    }

    private Cursor execQuery(String query) {
        DataBase bancoDados;
        bancoDados = new DataBase();
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, null);
        return cursor;
    }

    private void inserirTitulos() {
        Titulo vikings = new Titulo();
        vikings.setNome("Vikings");
        vikings.setSinopse("O mundo dos Vikings é trazido à vida através da jornada de Ragnar Lothbrok, o primeiro Viking a emergir da lenda nórdica e nas páginas da história - um homem à beira do do mito");
        vikings.setAvaliacao(8);
        vikings.setGeneros("Ação, Aventura, Drama");
        vikings.setCriadores("Michael Hirst");
        vikings.setTipo("Serie");
        vikings.setCartaz(formatadorImagem.getFotoByte(R.drawable.vikings_hamba));
        long idVikings = tituloDao.inserir(vikings);
        tituloDao.inserirImagemTitulo((int) idVikings,formatadorImagem.getFotoByte(R.drawable.vk3));
        tituloDao.inserirImagemTitulo((int) idVikings,formatadorImagem.getFotoByte(R.drawable.vk4));

        Serie vkSerie = new Serie();
        vkSerie.setTitulo(idVikings);
        vkSerie.setDistribuidor("History");
        SerieDao serieDao = new SerieDao();
        long idVkSerie = serieDao.inserirSerie(vkSerie);

        Temporada temp1 = new Temporada();
        temp1.setNome("Temporada 1");
        temp1.setIdSerie(idVkSerie);
        temp1.setNumeroTemporada(1);
        temp1.setQuantidadeEdpisodios(3);
        temp1.setDataLancamento("3 de Março de 2013");
        TemporadaDao temporadaDao = new TemporadaDao();
        long t1 =temporadaDao.inserirTemporada(temp1);

        Temporada temp2 = new Temporada();
        temp2.setNome("Temporada 2");
        temp2.setIdSerie(idVkSerie);
        temp2.setNumeroTemporada(2);
        temp2.setQuantidadeEdpisodios(3);
        temp2.setDataLancamento("3 de Março de 2014");
        long t2 = temporadaDao.inserirTemporada(temp2);

        EpisodioDao episodioDao = new EpisodioDao();
        Episodio ep1T1 = new Episodio();
        ep1T1.setIdTemoporada(t1);
        ep1T1.setNome("Rituais de Passagem");
        ep1T1.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(ep1T1);
        Episodio ep2T1 = new Episodio();
        ep2T1.setIdTemoporada(t1);
        ep2T1.setNome("A Ira dos Homens do Norte");
        ep2T1.setNumeroEpisodio(2);
        episodioDao.inserirEpisodio(ep2T1);
        Episodio ep3T1 = new Episodio();
        ep3T1.setIdTemoporada(t1);
        ep3T1.setNome("Despossuídos");
        ep3T1.setNumeroEpisodio(3);
        episodioDao.inserirEpisodio(ep2T1);

        Episodio ep1T2 = new Episodio();
        ep1T2.setIdTemoporada(t2);
        ep1T2.setNome("Guerra de Irmãos");
        ep1T2.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(ep1T2);
        Episodio ep2T2 = new Episodio();
        ep2T2.setIdTemoporada(t2);
        ep2T2.setNome("Invasão");
        ep2T2.setNumeroEpisodio(2);
        episodioDao.inserirEpisodio(ep2T2);
        Episodio ep3T2 = new Episodio();
        ep3T2.setIdTemoporada(t2);
        ep3T2.setNome("Traição");
        ep3T2.setNumeroEpisodio(3);
        episodioDao.inserirEpisodio(ep3T2);

        Titulo narcos = new Titulo();
        narcos.setNome("Narcos");
        narcos.setSinopse("Um olhar narrado sobre as façanhas criminosas do traficante colombiano Pablo Escobar, bem como dos muitos outros chefões do tráfico de drogas que assolaram o país ao longo dos anos.");
        narcos.setAvaliacao(8);
        narcos.setGeneros("Biografia, Crime, Drama");
        narcos.setCriadores("Carlo Bernard, Chris Brancato, Doug Miro");
        narcos.setTipo("Serie");
        narcos.setCartaz(formatadorImagem.getFotoByte(R.drawable.narcos));
        long idNarcos = tituloDao.inserir(narcos);
        tituloDao.inserirImagemTitulo((int) idNarcos, formatadorImagem.getFotoByte(R.drawable.narcos));
        tituloDao.inserirImagemTitulo((int) idNarcos, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie narcosSerie = new Serie();
        narcosSerie.setTitulo(idNarcos);
        narcosSerie.setDistribuidor("Netflix");
        long idNarcSeries = serieDao.inserirSerie(narcosSerie);

        Temporada tempNarcos = new Temporada();
        tempNarcos.setNome("Temporada 1");
        tempNarcos.setIdSerie(idNarcSeries);
        tempNarcos.setNumeroTemporada(1);
        tempNarcos.setQuantidadeEdpisodios(1);
        tempNarcos.setDataLancamento("4 de Março de 2013");
        long tNarcos =temporadaDao.inserirTemporada(tempNarcos);

        Episodio epNarcos = new Episodio();
        epNarcos.setIdTemoporada(tNarcos);
        epNarcos.setNome("NARCOS EP 1");
        epNarcos.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epNarcos);

        Titulo gameOfThrones = new Titulo();
        gameOfThrones.setNome("Game of Thrones");
        gameOfThrones.setSinopse("Nove famílias nobres lutam pelo controle sobre as terras míticas de Westeros, enquanto um antigo inimigo retorna depois de ficar adormecido por milhares de anos.");
        gameOfThrones.setAvaliacao(9);
        gameOfThrones.setGeneros("Ação, Aventura, Drama");
        gameOfThrones.setCriadores("David Benioff, D.B. Weiss");
        gameOfThrones.setTipo("Serie");
        gameOfThrones.setCartaz(formatadorImagem.getFotoByte(R.drawable.got));
        long idGot = tituloDao.inserir(gameOfThrones);
        tituloDao.inserirImagemTitulo((int) idGot, formatadorImagem.getFotoByte(R.drawable.got));
        tituloDao.inserirImagemTitulo((int) idGot, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie gotSerie = new Serie();
        gotSerie.setTitulo(idGot);
        gotSerie.setDistribuidor("HBO");
        long idGotSeries = serieDao.inserirSerie(gotSerie);

        Temporada tempGot = new Temporada();
        tempGot.setNome("Temporada 1");
        tempGot.setIdSerie(idGotSeries);
        tempGot.setNumeroTemporada(1);
        tempGot.setQuantidadeEdpisodios(1);
        tempGot.setDataLancamento("4 de Março de 2013");
        long tGot =temporadaDao.inserirTemporada(tempGot);

        Episodio epGot = new Episodio();
        epGot.setIdTemoporada(tGot);
        epGot.setNome("GOT EP 1");
        epGot.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epGot);

        Titulo strangerThings = new Titulo();
        strangerThings.setNome("Stranger Things");
        strangerThings.setSinopse("Quando um menino desaparece, sua mãe, um chefe de polícia e seus amigos precisam confrontar forças aterrorizantes para recuperá-lo.");
        strangerThings.setAvaliacao(8);
        strangerThings.setGeneros("Drama, Ficção, Terror");
        strangerThings.setCriadores("Matt Duffer, Ross Duffer");
        strangerThings.setTipo("Serie");
        strangerThings.setCartaz(formatadorImagem.getFotoByte(R.drawable.strangerthings));
        long idStranger = tituloDao.inserir(strangerThings);
        tituloDao.inserirImagemTitulo((int) idStranger, formatadorImagem.getFotoByte(R.drawable.strangerthings));
        tituloDao.inserirImagemTitulo((int) idStranger, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie stgSerie = new Serie();
        stgSerie.setTitulo(idStranger);
        stgSerie.setDistribuidor("Netflix");
        long idStrangerSerie = serieDao.inserirSerie(stgSerie);

        Temporada tempStg = new Temporada();
        tempStg.setNome("Temporada 1");
        tempStg.setIdSerie(idStrangerSerie);
        tempStg.setNumeroTemporada(1);
        tempStg.setQuantidadeEdpisodios(1);
        tempStg.setDataLancamento("4 de Março de 2013");
        long tStg =temporadaDao.inserirTemporada(tempStg);

        Episodio epStg = new Episodio();
        epStg.setIdTemoporada(tStg);
        epStg.setNome("Stanger EP 1");
        epStg.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epStg);

        Titulo breakingBad = new Titulo();
        breakingBad.setNome("Breaking Bad");
        breakingBad.setSinopse("Um professor de química do ensino médio diagnosticado com câncer de pulmão inoperante se transforma em fabricação e venda de metanfetamina, a fim de garantir o futuro de sua família.");
        breakingBad.setAvaliacao(10);
        breakingBad.setGeneros("Acão");
        breakingBad.setCriadores("Cleto o rei delas");
        breakingBad.setTipo("Serie");
        breakingBad.setCartaz(formatadorImagem.getFotoByte(R.drawable.breakingbad));
        long idBraking = tituloDao.inserir(breakingBad);
        tituloDao.inserirImagemTitulo((int) idBraking, formatadorImagem.getFotoByte(R.drawable.breakingbad));
        tituloDao.inserirImagemTitulo((int) idBraking, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie breaSerie = new Serie();
        breaSerie.setTitulo(idBraking);
        breaSerie.setDistribuidor("Netflix");
        long idBkSerie = serieDao.inserirSerie(breaSerie);

        Temporada tempBreak = new Temporada();
        tempBreak.setNome("Temporada 1");
        tempBreak.setIdSerie(idBkSerie);
        tempBreak.setNumeroTemporada(1);
        tempBreak.setQuantidadeEdpisodios(1);
        tempBreak.setDataLancamento("4 de Março de 2013");
        long tBreak =temporadaDao.inserirTemporada(tempBreak);

        Episodio epBreak = new Episodio();
        epBreak.setIdTemoporada(tBreak);
        epBreak.setNome("Breaking Bad EP 1");
        epBreak.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epBreak);

        Titulo laCasaDePapel = new Titulo();
        laCasaDePapel.setNome("La Casa de Papel");
        laCasaDePapel.setSinopse("Oito ladrões fazem reféns e se trancam na Casa da Moeda Real da Espanha, enquanto um gênio do crime manipula a polícia para executar seu plano.");
        laCasaDePapel.setAvaliacao(8);
        laCasaDePapel.setGeneros("Crime, Drama, Suspense");
        laCasaDePapel.setCriadores("Vince Gilligan");
        laCasaDePapel.setTipo("Serie");
        laCasaDePapel.setCartaz(formatadorImagem.getFotoByte(R.drawable.lacasa));
        long idLaCasa = tituloDao.inserir(laCasaDePapel);
        tituloDao.inserirImagemTitulo((int) idLaCasa, formatadorImagem.getFotoByte(R.drawable.lacasa));
        tituloDao.inserirImagemTitulo((int) idLaCasa, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie LaCasaSerie = new Serie();
        LaCasaSerie.setTitulo(idLaCasa);
        LaCasaSerie.setDistribuidor("Netflix");
        long idLaCasaSerie = serieDao.inserirSerie(LaCasaSerie);

        Temporada tempLacasa = new Temporada();
        tempLacasa.setNome("Temporada 1");
        tempLacasa.setIdSerie(idLaCasaSerie);
        tempLacasa.setNumeroTemporada(1);
        tempLacasa.setQuantidadeEdpisodios(1);
        tempLacasa.setDataLancamento("4 de Março de 2013");
        long tLacasa =temporadaDao.inserirTemporada(tempLacasa);

        Episodio epLacasa = new Episodio();
        epLacasa.setIdTemoporada(tLacasa);
        epLacasa.setNome("LaCasa EP 1");
        epLacasa.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epLacasa);

        Titulo houseOfCards = new Titulo();
        houseOfCards.setNome("House of Cards");
        houseOfCards.setSinopse("Um congressista trabalha com sua esposa igualmente conivente para se vingar das pessoas que o traíram.");
        houseOfCards.setAvaliacao(8);
        houseOfCards.setGeneros("Drama");
        houseOfCards.setCriadores("Beau Willimo");
        houseOfCards.setTipo("Serie");
        houseOfCards.setCartaz(formatadorImagem.getFotoByte(R.drawable.houseofcards));
        long idHouse = tituloDao.inserir(houseOfCards);
        tituloDao.inserirImagemTitulo((int) idHouse, formatadorImagem.getFotoByte(R.drawable.houseofcards));
        tituloDao.inserirImagemTitulo((int) idHouse, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie houseSerie = new Serie();
        houseSerie.setTitulo(idHouse);
        houseSerie.setDistribuidor("Netflix");
        long idHouseSerie = serieDao.inserirSerie(houseSerie);

        Temporada tempHouse = new Temporada();
        tempHouse.setNome("Temporada 1");
        tempHouse.setIdSerie(idHouseSerie);
        tempHouse.setNumeroTemporada(1);
        tempHouse.setQuantidadeEdpisodios(1);
        tempHouse.setDataLancamento("4 de Março de 2013");
        long tHouse =temporadaDao.inserirTemporada(tempHouse);

        Episodio epHouse = new Episodio();
        epHouse.setIdTemoporada(tHouse);
        epHouse.setNome("House of Cards EP 1");
        epHouse.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epHouse);

        Titulo prisonBreak = new Titulo();
        prisonBreak.setNome("Prison Break");
        prisonBreak.setSinopse("Devido a uma conspiração política, um homem inocente é enviado para o corredor da morte e sua única esperança é seu irmão, que tem como missão deliberadamente ser enviado para a mesma prisão, a fim de derrubá-los por dentro.");
        prisonBreak.setAvaliacao(8);
        prisonBreak.setGeneros("Ação, Crime, Drama");
        prisonBreak.setCriadores("Paul Scheuring");
        prisonBreak.setTipo("Serie");
        prisonBreak.setCartaz(formatadorImagem.getFotoByte(R.drawable.prisonbreak));
        long idPrision = tituloDao.inserir(prisonBreak);
        tituloDao.inserirImagemTitulo((int) idPrision, formatadorImagem.getFotoByte(R.drawable.prisonbreak));
        tituloDao.inserirImagemTitulo((int) idPrision, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie prisonSerie = new Serie();
        prisonSerie.setTitulo(idPrision);
        prisonSerie.setDistribuidor("FOX");
        long idPrisonSerie = serieDao.inserirSerie(prisonSerie);

        Temporada tempPrison = new Temporada();
        tempPrison.setNome("Temporada 1");
        tempPrison.setIdSerie(idPrisonSerie);
        tempPrison.setNumeroTemporada(1);
        tempPrison.setQuantidadeEdpisodios(1);
        tempPrison.setDataLancamento("4 de Março de 2013");
        long tPrison =temporadaDao.inserirTemporada(tempPrison);

        Episodio epPrison = new Episodio();
        epPrison.setIdTemoporada(tPrison);
        epPrison.setNome("Prison Break EP 1");
        epPrison.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epPrison);

        Titulo mrRobot = new Titulo();
        mrRobot.setNome("Mr. Robot ");
        mrRobot.setSinopse("Elliot é um jovem programador que sofre de uma desordem que o torna anti-social. Acreditando que a única forma de se conectar com as pessoas é hackeando suas vidas, ele alia seu conhecimento ao fato de trabalhar em uma empresa de segurança online para proteger aqueles que ele ama daqueles que tentam, de alguma forma, prejudicá-los. Suas atividades chamam a atenção de Mr. Robot, um misterioso anarquista que convida Elliot a fazer parte de uma organização que atua na ilegalidade com o objetivo de derrubar as corporações americanas.");
        mrRobot.setAvaliacao(10);
        mrRobot.setGeneros("Crime, Drama");
        mrRobot.setCriadores("Sam Esmail");
        mrRobot.setTipo("Serie");
        mrRobot.setCartaz(formatadorImagem.getFotoByte(R.drawable.mrbot));
        long idRobot = tituloDao.inserir(mrRobot);
        tituloDao.inserirImagemTitulo((int) idRobot, formatadorImagem.getFotoByte(R.drawable.mrbot));
        tituloDao.inserirImagemTitulo((int) idRobot, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie robotSerie = new Serie();
        robotSerie.setTitulo(idRobot);
        robotSerie.setDistribuidor("USA Network");
        long idrobotSerie = serieDao.inserirSerie(robotSerie);

        Temporada tempRobot = new Temporada();
        tempRobot.setNome("Temporada 1");
        tempRobot.setIdSerie(idrobotSerie);
        tempRobot.setNumeroTemporada(1);
        tempRobot.setQuantidadeEdpisodios(1);
        tempRobot.setDataLancamento("4 de Março de 2013");
        long tRobot =temporadaDao.inserirTemporada(tempRobot);

        Episodio epRobot = new Episodio();
        epRobot.setIdTemoporada(tRobot);
        epRobot.setNome("Mr.Robot EP 1");
        epRobot.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epRobot);

        Titulo jurassic = new Titulo();
        jurassic.setNome("Jurassic World: Reino Ameaçado");
        jurassic.setSinopse("Quatro anos após o fechamento do Jurassic World, um vulcão prestes a entrar em erupção põe em risco a vida na ilha Nublar. No local não há mais qualquer presença humana, com os dinossauros vivendo livremente. Diante da situação, é preciso tomar uma decisão: deve-se retornar à ilha para salvar os animais ou abandoná-los para uma nova extinção? Decidida a resgatá-los, Claire (Bryce Dallas Howard) convoca Owen (Chris Pratt) a retornar à ilha com ela.");
        jurassic.setAvaliacao(7);
        jurassic.setGeneros("Ação, Aventura, Ficção Científica");
        jurassic.setCriadores("Derek Connolly, Colin Trevorrow");
        jurassic.setTipo("Filme");
        jurassic.setCartaz(formatadorImagem.getFotoByte(R.drawable.juras));
        long idJurassic = tituloDao.inserir(jurassic);
        tituloDao.inserirImagemTitulo((int) idJurassic, formatadorImagem.getFotoByte(R.drawable.juras));
        tituloDao.inserirImagemTitulo((int) idJurassic, formatadorImagem.getFotoByte(R.drawable.default2));
        FilmeDao filmeDao = new FilmeDao();

        Filme jurassicF = new Filme();
        jurassicF.setTitulo(tituloDao.getByID((int) idJurassic));
        jurassicF.setDuracao(120);
        filmeDao.inserir(jurassicF);

        Titulo incriveis = new Titulo();
        incriveis.setNome("Os Incríveis 2");
        incriveis.setSinopse("A família de super-heróis favorita de todo mundo está de volta em Os Incríveis 2 — mas dessa vez, Helena está sendo o destaque, deixando Bob em casa com Violeta e Flecha para se aventurar no dia a dia heroico de vida “normal”. É uma transição difícil para todo mundo, sendo os super poderes emergentes de Zezé o fator mais complicado. Quando um novo vilão traça uma trama brilhante e perigosa, a família e Gelado devem encontrar uma maneira de trabalhar juntos novamente — o que é mais fácil dizer do que fazer, mesmo quando são incríveis.");
        incriveis.setAvaliacao(9);
        incriveis.setGeneros("Ação, Aventura, Animação, Família");
        incriveis.setCriadores("Brad Bird");
        incriveis.setTipo("Filme");
        incriveis.setCartaz(formatadorImagem.getFotoByte(R.drawable.inc2));
        long idIncrivei = tituloDao.inserir(incriveis);
        tituloDao.inserirImagemTitulo((int) idIncrivei, formatadorImagem.getFotoByte(R.drawable.inc2));
        tituloDao.inserirImagemTitulo((int) idIncrivei, formatadorImagem.getFotoByte(R.drawable.default2));

        Filme incriveisF = new Filme();
        incriveisF.setTitulo(tituloDao.getByID((int) idIncrivei));
        incriveisF.setDuracao(120);
        filmeDao.inserir(incriveisF);

        Titulo logan = new Titulo();
        logan.setNome("Logan");
        logan.setSinopse("Em 2029, Logan (Hugh Jackman) ganha a vida como chofer de limousine para cuidar do nonagenário Charles Xavier (Patrick Stewart). Debilitado fisicamente e esgotado emocionalmente, ele é procurado por Gabriela (Elizabeth Rodriguez), uma mexicana que precisa da ajuda do ex-X-Men para defender a pequena Laura Kinney / X-23 (Dafne Keen). Ao mesmo tempo em que se recusa a voltar à ativa, Logan é perseguido pelo mercenário Donald Pierce (Boyd Holbrook), interessado na menina.");
        logan.setAvaliacao(10);
        logan.setGeneros("Ação, Drama, Ficção Científica");
        logan.setCriadores("Brad Bird");
        logan.setTipo("Filme");
        logan.setCartaz(formatadorImagem.getFotoByte(R.drawable.logan));
        long idLogan = tituloDao.inserir(logan);
        tituloDao.inserirImagemTitulo((int) idLogan, formatadorImagem.getFotoByte(R.drawable.logan));
        tituloDao.inserirImagemTitulo((int) idLogan, formatadorImagem.getFotoByte(R.drawable.default2));

        Filme loganF = new Filme();
        loganF.setTitulo(tituloDao.getByID((int) idLogan));
        loganF.setDuracao(120);
        filmeDao.inserir(loganF);

        Titulo sexy = new Titulo();
        sexy.setNome("Sexy por Acidente");
        sexy.setSinopse("Renee (Amy Schumer), uma mulher comum, luta diariamente com sua insegurança. Depois de cair de bicicleta e bater a cabeça, ela de repente acorda acreditando ser a mulher maiz capaz e bonita do mundo. E com isso Renee começa a viver a vida mais confiante e sem medo das falhas.");
        sexy.setAvaliacao(7);
        sexy.setGeneros("Comédia");
        sexy.setCriadores("Marc Silverstein, Abby Kohn");
        sexy.setTipo("Filme");
        sexy.setCartaz(formatadorImagem.getFotoByte(R.drawable.sexy));
        long idSexy = tituloDao.inserir(sexy);
        tituloDao.inserirImagemTitulo((int) idSexy, formatadorImagem.getFotoByte(R.drawable.sexy));
        tituloDao.inserirImagemTitulo((int) idSexy, formatadorImagem.getFotoByte(R.drawable.default2));

        Filme sexyF = new Filme();
        sexyF.setTitulo(tituloDao.getByID((int) idSexy));
        sexyF.setDuracao(120);
        filmeDao.inserir(sexyF);

        Titulo vingadores = new Titulo();
        vingadores.setNome("Vingadores: Guerra Infinita");
        vingadores.setSinopse("Como os Vingadores e seus aliados continuaram a proteger o mundo de ameaças muito grandes para qualquer herói, uma nova ameaça emergiu das sombras cósmicas: Thanos. Um déspota da infâmia intergalática, seu objetivo é coletar todas as seis Joias do Infinito, artefatos de poder inimaginável, e usá-las para infligir sua vontade distorcida em toda a realidade. Tudo o que os Vingadores lutaram levou até este momento - o destino da Terra e da própria existência nunca foi tão incerto.");
        vingadores.setAvaliacao(10);
        vingadores.setGeneros("Ação, Fantasia, Aventura, Ficção Científica");
        vingadores.setCriadores("Joe Russo, Anthony Russo");
        vingadores.setTipo("Filme");
        vingadores.setCartaz(formatadorImagem.getFotoByte(R.drawable.vinga));
        long idVing = tituloDao.inserir(vingadores);
        tituloDao.inserirImagemTitulo((int) idVing, formatadorImagem.getFotoByte(R.drawable.vinga));
        tituloDao.inserirImagemTitulo((int) idVing, formatadorImagem.getFotoByte(R.drawable.default2));

        Filme vingF = new Filme();
        vingF.setTitulo(tituloDao.getByID((int) idVing));
        vingF.setDuracao(120);
        filmeDao.inserir(vingF);

        Titulo friends = new Titulo();
        friends.setNome("Friends");
        friends.setSinopse("Seis jovens são unidos por laços familiares, românticos e, principalmente, de amizade, enquanto tentam vingar em Nova York. Rachel é a garota mimada que deixa o noivo no altar para viver com a amiga dos tempos de escola Monica, sistemática e apaixonada pela culinária. Monica é irmã de Ross, um paleontólogo que é abandonado pela esposa, que descobriu ser lésbica. Do outro lado do corredor do apartamento de Monica e Rachel, moram Joey, um ator frustrado, e Chandler, de profissão misteriosa. A turma é completa pela exótica Phoebe. ");
        friends.setAvaliacao(10);
        friends.setGeneros("Comédia");
        friends.setCriadores("Marta Kauffman, David Crane");
        friends.setTipo("Serie");
        friends.setCartaz(formatadorImagem.getFotoByte(R.drawable.friends));
        long idFriends = tituloDao.inserir(friends);
        tituloDao.inserirImagemTitulo((int) idFriends, formatadorImagem.getFotoByte(R.drawable.friends));
        tituloDao.inserirImagemTitulo((int) idFriends, formatadorImagem.getFotoByte(R.drawable.default2));

        Serie friendsSerie = new Serie();
        friendsSerie.setTitulo(idFriends);
        friendsSerie.setDistribuidor("USA Network");
        long idfriendsSerie = serieDao.inserirSerie(friendsSerie);

        Temporada tempFriends = new Temporada();
        tempFriends.setNome("Temporada 1");
        tempFriends.setIdSerie(idfriendsSerie);
        tempFriends.setNumeroTemporada(1);
        tempFriends.setQuantidadeEdpisodios(1);
        tempFriends.setDataLancamento("4 de Março de 2013");
        long tFriends =temporadaDao.inserirTemporada(tempFriends);

        Episodio epFriends = new Episodio();
        epFriends.setIdTemoporada(tFriends);
        epFriends.setNome("Friends EP 1");
        epFriends.setNumeroEpisodio(1);
        episodioDao.inserirEpisodio(epFriends);

        Usuario usuario1 = new Usuario();
        usuario1.setSenha("123");
        usuario1.setEmail("asdasxd@xd.xd");
        long user1 = usuarioDAO.inserir(usuario1);
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setUsuario(usuarioDAO.getByID(String.valueOf(user1)));
        pessoa1.setNome("italo");
        pessoaDAO.inserirPessoa(pessoa1);

        Usuario usuario2 = new Usuario();
        usuario2.setSenha("123");
        usuario2.setEmail("dffxdd@xd.xd");
        long user2 = usuarioDAO.inserir(usuario2);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setUsuario(usuarioDAO.getByID(String.valueOf(user2)));
        pessoa2.setNome("bruno");
        pessoaDAO.inserirPessoa(pessoa2);

        Usuario usuario3 = new Usuario();
        usuario3.setSenha("123");
        usuario3.setEmail("asdxxd@xd.xd");
        long user3 = usuarioDAO.inserir(usuario3);
        Pessoa pessoa3 = new Pessoa();
        pessoa3.setUsuario(usuarioDAO.getByID(String.valueOf(user3)));
        pessoa3.setNome("deish");
        pessoaDAO.inserirPessoa(pessoa3);

        Usuario usuario4 = new Usuario();
        usuario4.setSenha("123");
        usuario4.setEmail("asdasAxd@xd.xd");
        long user4 = usuarioDAO.inserir(usuario4);
        Pessoa pessoa4 = new Pessoa();
        pessoa4.setUsuario(usuarioDAO.getByID(String.valueOf(user4)));
        pessoa4.setNome("italo");
        pessoaDAO.inserirPessoa(pessoa4);

        Usuario usuario5 = new Usuario();
        usuario5.setSenha("123");
        usuario5.setEmail("fasffxd@xd.xd");
        long user5 = usuarioDAO.inserir(usuario5);
        Pessoa pessoa5 = new Pessoa();
        pessoa5.setUsuario(usuarioDAO.getByID(String.valueOf(user5)));
        pessoa5.setNome("KIM");
        pessoaDAO.inserirPessoa(pessoa5);

        Usuario usuario6 = new Usuario();
        usuario6.setSenha("123");
        usuario6.setEmail("asdgxd@xd.xd");
        long user6 = usuarioDAO.inserir(usuario6);
        Pessoa pessoa6 = new Pessoa();
        pessoa6.setUsuario(usuarioDAO.getByID(String.valueOf(user6)));
        pessoa6.setNome("FFo");
        pessoaDAO.inserirPessoa(pessoa6);

        Usuario usuario7 = new Usuario();
        usuario7.setSenha("123");
        usuario7.setEmail("assdxd@xd.xd");
        long user7 = usuarioDAO.inserir(usuario7);
        Pessoa pessoa7 = new Pessoa();
        pessoa7.setUsuario(usuarioDAO.getByID(String.valueOf(user7)));
        pessoa7.setNome("italo1");
        pessoaDAO.inserirPessoa(pessoa7);

        tituloDao.inserirNota(tituloDao.getByID((int) idVikings),pessoa1.getUsuario(), 3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idBraking),pessoa1.getUsuario(),4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idHouse),pessoa1.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idLogan),pessoa1.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idFriends),pessoa1.getUsuario(),5.0);

        tituloDao.inserirNota(tituloDao.getByID((int) idLogan),pessoa2.getUsuario(), 4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idGot),pessoa2.getUsuario(),1.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idPrision),pessoa2.getUsuario(),2.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idNarcos),pessoa2.getUsuario(),4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idJurassic),pessoa2.getUsuario(),4.0);

        tituloDao.inserirNota(tituloDao.getByID((int) idSexy),pessoa3.getUsuario(), 4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idJurassic),pessoa3.getUsuario(),1.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idHouse),pessoa3.getUsuario(),5.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idBraking),pessoa3.getUsuario(),2.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idVikings),pessoa3.getUsuario(),4.0);

        tituloDao.inserirNota(tituloDao.getByID((int) idVing),pessoa4.getUsuario(), 3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idLaCasa),pessoa4.getUsuario(),4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idGot),pessoa4.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idJurassic),pessoa4.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idSexy),pessoa4.getUsuario(),5.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idVikings),pessoa4.getUsuario(),5.0);

        tituloDao.inserirNota(tituloDao.getByID((int) idPrision),pessoa5.getUsuario(), 3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idIncrivei),pessoa5.getUsuario(),4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idVikings),pessoa5.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idSexy),pessoa5.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idLaCasa),pessoa5.getUsuario(),5.0);

        tituloDao.inserirNota(tituloDao.getByID((int) idVikings),pessoa6.getUsuario(), 3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idVing),pessoa6.getUsuario(),4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idLaCasa),pessoa6.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idLogan),pessoa6.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idNarcos),pessoa6.getUsuario(),5.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idGot),pessoa6.getUsuario(),5.0);

        tituloDao.inserirNota(tituloDao.getByID((int) idFriends),pessoa7.getUsuario(), 3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idStranger),pessoa7.getUsuario(),4.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idLogan),pessoa7.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idGot),pessoa7.getUsuario(),3.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idRobot),pessoa7.getUsuario(),5.0);
        tituloDao.inserirNota(tituloDao.getByID((int) idHouse),pessoa7.getUsuario(),5.0);
    }

}
