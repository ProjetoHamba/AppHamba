package com.apphamba.hamba.infra.persistencia;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.R;
import com.apphamba.hamba.infra.servicos.FormatadorImagem;
import com.apphamba.hamba.titulo.dominio.Titulo;
import com.apphamba.hamba.titulo.persistencia.TituloDao;

public class PopularBanco {
    public static final PopularBanco criador = new PopularBanco();
    private FormatadorImagem formatadorImagem = new FormatadorImagem();
    private TituloDao tituloDao = new TituloDao();

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

        Titulo logan = new Titulo();
        logan.setNome("Logan");
        logan.setSinopse("Em 2029, Logan (Hugh Jackman) ganha a vida como chofer de limousine para cuidar do nonagenário Charles Xavier (Patrick Stewart). Debilitado fisicamente e esgotado emocionalmente, ele é procurado por Gabriela (Elizabeth Rodriguez), uma mexicana que precisa da ajuda do ex-X-Men para defender a pequena Laura Kinney / X-23 (Dafne Keen). Ao mesmo tempo em que se recusa a voltar à ativa, Logan é perseguido pelo mercenário Donald Pierce (Boyd Holbrook), interessado na menina.");
        logan.setAvaliacao(10);
        logan.setGeneros("Ação, Drama, Ficção Científica");
        logan.setCriadores("Brad Bird");
        logan.setTipo("Filme");;
        logan.setCartaz(formatadorImagem.getFotoByte(R.drawable.logan));
        long idLogan = tituloDao.inserir(logan);
        tituloDao.inserirImagemTitulo((int) idLogan, formatadorImagem.getFotoByte(R.drawable.logan));
        tituloDao.inserirImagemTitulo((int) idLogan, formatadorImagem.getFotoByte(R.drawable.default2));

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

        Titulo friends = new Titulo();
        friends.setNome("Friends");
        friends.setSinopse("Seis jovens são unidos por laços familiares, românticos e, principalmente, de amizade, enquanto tentam vingar em Nova York. Rachel é a garota mimada que deixa o noivo no altar para viver com a amiga dos tempos de escola Monica, sistemática e apaixonada pela culinária. Monica é irmã de Ross, um paleontólogo que é abandonado pela esposa, que descobriu ser lésbica. Do outro lado do corredor do apartamento de Monica e Rachel, moram Joey, um ator frustrado, e Chandler, de profissão misteriosa. A turma é completa pela exótica Phoebe. ");
        friends.setAvaliacao(10);
        friends.setGeneros("Comédia");
        friends.setCriadores("Marta Kauffman, David Crane");
        friends.setTipo("Filme");
        friends.setCartaz(formatadorImagem.getFotoByte(R.drawable.friends));
        long idFriends = tituloDao.inserir(friends);
        tituloDao.inserirImagemTitulo((int) idFriends, formatadorImagem.getFotoByte(R.drawable.friends));
        tituloDao.inserirImagemTitulo((int) idFriends, formatadorImagem.getFotoByte(R.drawable.default2));
    }

}
