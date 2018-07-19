package com.apphamba.hamba.infra;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dbapphamba";

    public static final String INSERIR_SERIE = "INSERT INTO `serie` (`id_titulo`,`distribuidor`,`quantidade_temporada`) VALUES ";
    public static final String INSERIR_TITULO = "INSERT INTO `titulo` (`nome`,`sinopse`,`avaliacao`,`generos`, `criadores`) VALUES ";


    public DataBase() {
        super(HambaApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "senha text NOT NULL, " +
                "email text NOT NULL, " +
                "ativo text NOT NULL); ");

        db.execSQL("CREATE TABLE pessoa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome text NOT NULL, " +
                "id_usuario interger NOT NULL);");

        db.execSQL("CREATE TABLE titulo(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome text NOT NULL, " +
                "sinopse text, " +
                "avaliacao int, " +
                "generos text, " +
                "criadores text, " +
                "imagem BLOB);");

        db.execSQL("CREATE TABLE serie(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_titulo interger, " +
                "distribuidor text, " +
                "quantidade_temporada int);");

        db.execSQL("CREATE TABLE temporada(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_serie interger, " +
                "nome text, " +
                "numero_temporada int, " +
                "quantidade_episodio int, " +
                "data_lancamento text ); ");

        db.execSQL("CREATE TABLE episodio(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_temporada interger, " +
                "nome text, " +
                "numero_episodio int);");

        db.execSQL("CREATE TABLE filme(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_titulo interger, " +
                "duracao int);");

        //db.execSQL(ConstantesBanco.INSERIR_TITULO);
        //db.execSQL(ConstantesBanco.INSERIR_USUARIO);
        //TODO gerar o resto
        //gerarSeries( db );
        //gerarTitulos( db );



    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE usuario;");
        db.execSQL("DROP TABLE pessoa;");
        db.execSQL("DROP TABLE titulo;");
        db.execSQL("DROP TABLE serie;");
        db.execSQL("DROP TABLE temporada;");
        db.execSQL("DROP TABLE episodio;");
        db.execSQL("DROP TABLE filme;");
        this.onCreate(db);
    }

    private SQLiteDatabase getBancoLeitura(Context context){
        SQLiteDatabase bancoDados = this.getReadableDatabase();
        return bancoDados;
    }

    private SQLiteDatabase getBancoEscrita(Context context) {
        SQLiteDatabase bancoDados = this.getWritableDatabase();
        return bancoDados;
    }

    //private void gerarSeries(SQLiteDatabase database){
       // database.execSQL(INSERIR_SERIE+ "('1','MGM Television','6');");
        //database.execSQL(INSERIR_SERIE+ "('2','C More','8');");
        //database.execSQL(INSERIR_SERIE+ "('3','Netflix','2');");
        //database.execSQL(INSERIR_SERIE+ "('4','Netflix','2');");
        //database.execSQL(INSERIR_SERIE+ "('5','Netflix','3');");
        //database.execSQL(INSERIR_SERIE+ "('6','American Movie Classics','5');");
        //database.execSQL(INSERIR_SERIE+ "('7','Netflix','5');");
        //database.execSQL(INSERIR_SERIE+ "('8','Fox Network','6');");
   // }
    //public void gerarTitulos(SQLiteDatabase dataBase){
        //dataBase.execSQL( INSERIR_TITULO+ "('Vikings','O mundo dos Vikings é trazido à vida através da jornada de Ragnar Lothbrok, o primeiro Viking a emergir da lenda nórdica e nas páginas da história - um homem à beira do mito.','8','Ação, Aventura, Drama','Michael Hirst');");
       // dataBase.execSQL( INSERIR_TITULO+ "('Game of Thrones','Nove famílias nobres lutam pelo controle sobre as terras míticas de Westeros, enquanto um antigo inimigo retorna depois de ficar adormecido por milhares de anos.','9','Ação, Aventura, Drama','David Benioff, D.B. Weiss');" );
       // dataBase.execSQL( INSERIR_TITULO+ "('Stranger Things','Quando um menino desaparece, sua mãe, um chefe de polícia e seus amigos precisam confrontar forças aterrorizantes para recuperá-lo.','9','Drama, Ficção, Terror','Matt Duffer, Ross Duffer');");
       // dataBase.execSQL( INSERIR_TITULO+ "('La Casa de Papel','Oito ladrões fazem reféns e se trancam na Casa da Moeda Real da Espanha, enquanto um gênio do crime manipula a polícia para executar seu plano.','8','Ação, Crime, Suspense','Álex Pina'); " );
        //dataBase.execSQL( INSERIR_TITULO+ "('Narcos','Um olhar narrado sobre as façanhas criminosas do traficante colombiano Pablo Escobar, bem como dos muitos outros chefões do tráfico de drogas que assolaram o país ao longo dos anos.','8','Biografia, Crime, Drama','Carlo Bernard, Chris Brancato, Doug Miro'); ");
       // dataBase.execSQL( INSERIR_TITULO+ "('Breaking Bad','Um professor de química do ensino médio diagnosticado com câncer de pulmão inoperante se transforma em fabricação e venda de metanfetamina, a fim de garantir o futuro de sua família.','9','Crime, Drama, Suspense','Vince Gilligan'); ");
       // dataBase.execSQL( INSERIR_TITULO+ "('House of Cards','Um congressista trabalha com sua esposa igualmente conivente para se vingar das pessoas que o traíram.','9','Drama', 'Beau Willimon'); ");
    // dataBase.execSQL( INSERIR_TITULO+ "('Prison Break','Devido a uma conspiração política, um homem inocente é enviado para o corredor da morte e sua única esperança é seu irmão, que tem como missão deliberadamente ser enviado para a mesma prisão, a fim de derrubá-los por dentro.','8','Ação, Crime, Drama','Paul Scheuring');");
}


