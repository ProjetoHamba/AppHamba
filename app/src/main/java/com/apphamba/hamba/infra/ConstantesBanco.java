package com.apphamba.hamba.infra;

//POPULAR BANCO COM EXEMPLOS DE
public class ConstantesBanco {

    public static final String INSERIR_USUARIO = "INSERT INTO `usuario` (`email`, `senha`) VALUES" +
            "('a@g.c','123');";

    public static final String INSERIR_TITULO = "INSERT INTO `titulo` (`nome`,`sinopse`,`avaliacao`,`generos`, `criadores`) VALUES " +
            "('Vikings','O mundo dos Vikings é trazido à vida através da jornada de Ragnar Lothbrok, o primeiro Viking a emergir da lenda nórdica e nas páginas da história - um homem à beira do mito.','8','Ação, Aventura, Drama','Michael Hirst');";
            /*", " +
            "('Game of Thrones','Nove famílias nobres lutam pelo controle sobre as terras míticas de Westeros, enquanto um antigo inimigo retorna depois de ficar adormecido por milhares de anos.','9','Ação, Aventura, Drama','David Benioff, D.B. Weiss'), " +
            "('Stranger Things','Quando um menino desaparece, sua mãe, um chefe de polícia e seus amigos precisam confrontar forças aterrorizantes para recuperá-lo.','9','Drama, Ficção, Terror','Matt Duffer, Ross Duffer'), " +
            "('La Casa de Papel','Oito ladrões fazem reféns e se trancam na Casa da Moeda Real da Espanha, enquanto um gênio do crime manipula a polícia para executar seu plano.','8','Ação, Crime, Suspense','Álex Pina'), " +
            "('Narcos','Um olhar narrado sobre as façanhas criminosas do traficante colombiano Pablo Escobar, bem como dos muitos outros chefões do tráfico de drogas que assolaram o país ao longo dos anos.','8','Biografia, Crime, Drama','Carlo Bernard, Chris Brancato, Doug Miro'), " +
            "('Breaking Bad','Um professor de química do ensino médio diagnosticado com câncer de pulmão inoperante se transforma em fabricação e venda de metanfetamina, a fim de garantir o futuro de sua família.','9','Crime, Drama, Suspense','Vince Gilligan'), " +
            "('House of Cards','Um congressista trabalha com sua esposa igualmente conivente para se vingar das pessoas que o traíram.','9','Drama', 'Beau Willimon'), " +
            "('Prison Break','Devido a uma conspiração política, um homem inocente é enviado para o corredor da morte e sua única esperança é seu irmão, que tem como missão deliberadamente ser enviado para a mesma prisão, a fim de derrubá-los por dentro.','8','Ação, Crime, Drama','Paul Scheuring');";*/

    public static final String INSERIR_SERIE = "INSERT INTO `serie` (`id_titulo`,`distribuidor`,`quantidade_temporada`) VALUES " +
            "('1','MGM Television','6')" +
            "('2','C More','8')" +
            "('3','Netflix','2')" +
            "('4','Netflix','2')" +
            "('5','Netflix','3')" +
            "('6','American Movie Classics','5')" +
            "('7','Netflix','5')" +
            "('8','Fox Network','6');";

    public static final String INSERIR_TEMPORADA = "INSERT INTO `serie` (`id_serie`,`nome`,`numero_temporada`,`quantidade_episodio`,`data_lancamento`) VALUES " +
            "('1','Teste','1','1','03/03/2013)" +
            "('2','Teste1','1','1','11/11/11)" +
            "('3','Teste2','1','1','11/11/11)" +
            "('4','Teste3','1','1','11/11/11)" +
            "('5','Teste4','1','1','11/11/11)" +
            "('6','Teste5','1','1','11/11/11)" +
            "('7','Teste6','1','1','11/11/11)" +
            "('8','Teste7','1','1','11/11/11);";

    public static final String INSERIR_EPISODIO = "INSERT INTO `serie` (`id_temporada`,`nome`,`numero_episodio`) VALUES " +
            "('1','epi1','1')" +
            "('2','epi2','1')" +
            "('3','epi3','1')" +
            "('4','epi4','1')" +
            "('5','epi5','1')" +
            "('6','epi6','1')" +
            "('7','epi7','1')" +
            "('8','epi8','1');";

}