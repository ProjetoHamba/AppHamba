package com.apphamba.hamba.infra;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphamba.hamba.R;
import com.apphamba.hamba.titulos.dominio.Titulo;
import com.apphamba.hamba.titulos.persistencia.TituloDao;

public class PopularBanco {
    public static final PopularBanco criador = new PopularBanco();

    private FormatadorImagem formatadorImagem = new FormatadorImagem();
    private TituloDao tituloDao = new TituloDao(HambaApp.getContext());

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
        Titulo Vikings = new Titulo();
        Vikings.setNome("Vikings");
        Vikings.setSinopse("O mundo dos Vikings é trazido à vida através da jornada de Ragnar Lothbrok, o primeiro Viking a emergir da lenda nórdica e nas páginas da história - um homem à beira do do mito");
        Vikings.setAvaliacao(8);
        Vikings.setGeneros("Ação, Aventura, Drama");
        Vikings.setCriadores("Michael Hirst");
        Vikings.setImagem(formatadorImagem.gerarFoto(R.drawable.vikings_hamba));
        tituloDao.inserir(Vikings);

        Titulo Narcos = new Titulo();
        Narcos.setNome("Narcos");
        Narcos.setSinopse("Um olhar narrado sobre as façanhas criminosas do traficante colombiano Pablo Escobar, bem como dos muitos outros chefões do tráfico de drogas que assolaram o país ao longo dos anos.");
        Narcos.setAvaliacao(8);
        Narcos.setGeneros("Biografia, Crime, Drama");
        Narcos.setCriadores("Carlo Bernard, Chris Brancato, Doug Miro");
        Narcos.setImagem(formatadorImagem.gerarFoto(R.drawable.narcos));
        tituloDao.inserir(Narcos);

        Titulo GameOfThrones = new Titulo();
        GameOfThrones.setNome("Game of Thrones");
        GameOfThrones.setSinopse("Nove famílias nobres lutam pelo controle sobre as terras míticas de Westeros, enquanto um antigo inimigo retorna depois de ficar adormecido por milhares de anos.");
        GameOfThrones.setAvaliacao(9);
        GameOfThrones.setGeneros("Ação, Aventura, Drama");
        GameOfThrones.setCriadores("David Benioff, D.B. Weiss");
        GameOfThrones.setImagem(formatadorImagem.gerarFoto(R.drawable.got));
        tituloDao.inserir(GameOfThrones);

        Titulo StrangerThings = new Titulo();
        StrangerThings.setNome("Stranger Things");
        StrangerThings.setSinopse("Quando um menino desaparece, sua mãe, um chefe de polícia e seus amigos precisam confrontar forças aterrorizantes para recuperá-lo.");
        StrangerThings.setAvaliacao(8);
        StrangerThings.setGeneros("Drama, Ficção, Terror");
        StrangerThings.setCriadores("Matt Duffer, Ross Duffer");
        StrangerThings.setImagem(formatadorImagem.gerarFoto(R.drawable.strangerthings));
        tituloDao.inserir(StrangerThings);

        Titulo BreakingBad = new Titulo();
        BreakingBad.setNome("Breaking Bad");
        BreakingBad.setSinopse("Um professor de química do ensino médio diagnosticado com câncer de pulmão inoperante se transforma em fabricação e venda de metanfetamina, a fim de garantir o futuro de sua família.");
        BreakingBad.setAvaliacao(10);
        BreakingBad.setGeneros("Chute na cara e porrada");
        BreakingBad.setCriadores("eu");
        BreakingBad.setImagem(formatadorImagem.gerarFoto(R.drawable.breakingbad));
        tituloDao.inserir(BreakingBad);

        Titulo LaCasaDePapel = new Titulo();
        LaCasaDePapel.setNome("La Casa de Papel");
        LaCasaDePapel.setSinopse("Oito ladrões fazem reféns e se trancam na Casa da Moeda Real da Espanha, enquanto um gênio do crime manipula a polícia para executar seu plano.");
        LaCasaDePapel.setAvaliacao(8);
        LaCasaDePapel.setGeneros("Crime, Drama, Suspense");
        LaCasaDePapel.setCriadores("Vince Gilligan");
        LaCasaDePapel.setImagem(formatadorImagem.gerarFoto(R.drawable.lacasa));
        tituloDao.inserir(LaCasaDePapel);

        Titulo HouseOfCards = new Titulo();
        HouseOfCards.setNome("House of Cards");
        HouseOfCards.setSinopse("Um congressista trabalha com sua esposa igualmente conivente para se vingar das pessoas que o traíram.");
        HouseOfCards.setAvaliacao(8);
        HouseOfCards.setGeneros("Drama");
        HouseOfCards.setCriadores("Beau Willimo");
        HouseOfCards.setImagem(formatadorImagem.gerarFoto(R.drawable.houseofcards));
        tituloDao.inserir(HouseOfCards);

        Titulo PrisonBreak = new Titulo();
        PrisonBreak.setNome("Prison Break");
        PrisonBreak.setSinopse("Devido a uma conspiração política, um homem inocente é enviado para o corredor da morte e sua única esperança é seu irmão, que tem como missão deliberadamente ser enviado para a mesma prisão, a fim de derrubá-los por dentro.");
        PrisonBreak.setAvaliacao(8);
        PrisonBreak.setGeneros("Ação, Crime, Drama");
        PrisonBreak.setCriadores("Paul Scheuring");
        PrisonBreak.setImagem(formatadorImagem.gerarFoto(R.drawable.prisonbreak));
        tituloDao.inserir(PrisonBreak);
    }

}
