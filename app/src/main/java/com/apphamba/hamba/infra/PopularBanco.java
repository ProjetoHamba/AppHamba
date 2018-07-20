package com.apphamba.hamba.infra;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.persistencia.TituloDao;

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
        vikings.setImagem(formatadorImagem.gerarFoto(R.drawable.vikings_hamba));
        tituloDao.inserir(vikings);

        Titulo narcos = new Titulo();
        narcos.setNome("Narcos");
        narcos.setSinopse("Um olhar narrado sobre as façanhas criminosas do traficante colombiano Pablo Escobar, bem como dos muitos outros chefões do tráfico de drogas que assolaram o país ao longo dos anos.");
        narcos.setAvaliacao(8);
        narcos.setGeneros("Biografia, Crime, Drama");
        narcos.setCriadores("Carlo Bernard, Chris Brancato, Doug Miro");
        narcos.setImagem(formatadorImagem.gerarFoto(R.drawable.narcos));
        tituloDao.inserir(narcos);

        Titulo gameOfThrones = new Titulo();
        gameOfThrones.setNome("Game of Thrones");
        gameOfThrones.setSinopse("Nove famílias nobres lutam pelo controle sobre as terras míticas de Westeros, enquanto um antigo inimigo retorna depois de ficar adormecido por milhares de anos.");
        gameOfThrones.setAvaliacao(9);
        gameOfThrones.setGeneros("Ação, Aventura, Drama");
        gameOfThrones.setCriadores("David Benioff, D.B. Weiss");
        gameOfThrones.setImagem(formatadorImagem.gerarFoto(R.drawable.got));
        tituloDao.inserir(gameOfThrones);

        Titulo strangerThings = new Titulo();
        strangerThings.setNome("Stranger Things");
        strangerThings.setSinopse("Quando um menino desaparece, sua mãe, um chefe de polícia e seus amigos precisam confrontar forças aterrorizantes para recuperá-lo.");
        strangerThings.setAvaliacao(8);
        strangerThings.setGeneros("Drama, Ficção, Terror");
        strangerThings.setCriadores("Matt Duffer, Ross Duffer");
        strangerThings.setImagem(formatadorImagem.gerarFoto(R.drawable.strangerthings));
        tituloDao.inserir(strangerThings);

        Titulo breakingBad = new Titulo();
        breakingBad.setNome("Breaking Bad");
        breakingBad.setSinopse("Um professor de química do ensino médio diagnosticado com câncer de pulmão inoperante se transforma em fabricação e venda de metanfetamina, a fim de garantir o futuro de sua família.");
        breakingBad.setAvaliacao(10);
        breakingBad.setGeneros("Chute na cara e porrada");
        breakingBad.setCriadores("eu");
        breakingBad.setImagem(formatadorImagem.gerarFoto(R.drawable.breakingbad));
        tituloDao.inserir(breakingBad);

        Titulo laCasaDePapel = new Titulo();
        laCasaDePapel.setNome("La Casa de Papel");
        laCasaDePapel.setSinopse("Oito ladrões fazem reféns e se trancam na Casa da Moeda Real da Espanha, enquanto um gênio do crime manipula a polícia para executar seu plano.");
        laCasaDePapel.setAvaliacao(8);
        laCasaDePapel.setGeneros("Crime, Drama, Suspense");
        laCasaDePapel.setCriadores("Vince Gilligan");
        laCasaDePapel.setImagem(formatadorImagem.gerarFoto(R.drawable.lacasa));
        tituloDao.inserir(laCasaDePapel);

        Titulo houseOfCards = new Titulo();
        houseOfCards.setNome("House of Cards");
        houseOfCards.setSinopse("Um congressista trabalha com sua esposa igualmente conivente para se vingar das pessoas que o traíram.");
        houseOfCards.setAvaliacao(8);
        houseOfCards.setGeneros("Drama");
        houseOfCards.setCriadores("Beau Willimo");
        houseOfCards.setImagem(formatadorImagem.gerarFoto(R.drawable.houseofcards));
        tituloDao.inserir(houseOfCards);

        Titulo prisonBreak = new Titulo();
        prisonBreak.setNome("Prison Break");
        prisonBreak.setSinopse("Devido a uma conspiração política, um homem inocente é enviado para o corredor da morte e sua única esperança é seu irmão, que tem como missão deliberadamente ser enviado para a mesma prisão, a fim de derrubá-los por dentro.");
        prisonBreak.setAvaliacao(8);
        prisonBreak.setGeneros("Ação, Crime, Drama");
        prisonBreak.setCriadores("Paul Scheuring");
        prisonBreak.setImagem(formatadorImagem.gerarFoto(R.drawable.prisonbreak));
        tituloDao.inserir(prisonBreak);

        Titulo mrRobot = new Titulo();
        mrRobot.setNome("Mr. Robot ");
        mrRobot.setSinopse("Elliot é um jovem programador que sofre de uma desordem que o torna anti-social. Acreditando que a única forma de se conectar com as pessoas é hackeando suas vidas, ele alia seu conhecimento ao fato de trabalhar em uma empresa de segurança online para proteger aqueles que ele ama daqueles que tentam, de alguma forma, prejudicá-los. Suas atividades chamam a atenção de Mr. Robot, um misterioso anarquista que convida Elliot a fazer parte de uma organização que atua na ilegalidade com o objetivo de derrubar as corporações americanas.");
        mrRobot.setAvaliacao(10);
        mrRobot.setGeneros("Crime, Drama");
        mrRobot.setCriadores("Sam Esmail");
        mrRobot.setImagem(formatadorImagem.gerarFoto(R.drawable.mrbot));
        tituloDao.inserir(mrRobot);
    }

}
