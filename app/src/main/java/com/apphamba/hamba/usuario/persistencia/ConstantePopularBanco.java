package com.apphamba.hamba.usuario.persistencia;

//POPULAR BANCO COM EXEMPLOS DE
public class ConstantePopularBanco {

    public static final String INSERIR_TITULO = "INSERT INTO `titulo` (`nome`,`sinopse`,`avaliacao`,`generos` `criadores`) VALUES " +
            "('Friends','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('Vikings','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('Game of Thrones','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('xuxa','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('Kees','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('zzzz','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('xxx','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('qqq','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('wwww','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('eee','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria)" +
            "('rrrr','xxxxxxxxxxxxxxx','80','comedia,romance','jose,maria);" ;

    public static final String INSERIR_SERIE = "INSERT INTO `serie` (`id_titulo`,`distribuidor`,`quantidade_temporada`) VALUES " +
            "('1','xxxxxxxxxxxxxxx','ABC','1')" +
            "('2','xxxxxxxxxxxxxxx','BCA','1')" +
            "('3','xxxxxxxxxxxxxxx','ADD','1')" +
            "('4','xxxxxxxxxxxxxxx','AZX','1')" +
            "('5','xxxxxxxxxxxxxxx','AUZ','1')" +
            "('6','xxxxxxxxxxxxxxx','TMC','1')" +
            "('7','xxxxxxxxxxxxxxx','KKK','1')" +
            "('8','xxxxxxxxxxxxxxx','ZZZ','1')" +
            "('9','xxxxxxxxxxxxxxx','QQQ','1')" +
            "('10','xxxxxxxxxxxxxxx','EEE','1')" +
            "('11','xxxxxxxxxxxxxxx','8WWW','1');";

    public static final String INSERIR_TEMPORADA = "INSERT INTO `serie` (`id_serie`,`nome`,`numero_temporada`,`quantidade_episodio`,`data_lancamento`) VALUES " +
            "('1','Teste','1','1','11/11/11)" +
            "('2','Teste1','1','1','11/11/11)" +
            "('3','Teste2','1','1','11/11/11)" +
            "('4','Teste3','1','1','11/11/11)" +
            "('5','Teste4','1','1','11/11/11)" +
            "('6','Teste5','1','1','11/11/11)" +
            "('7','Teste6','1','1','11/11/11)" +
            "('8','Teste7','1','1','11/11/11)" +
            "('9','Teste8','1','1','11/11/11)" +
            "('10','Teste9','1','1','11/11/11)" +
            "('11','Teste10','1','1','11/11/11);";

    public static final String INSERIR_EPISODIO = "INSERT INTO `serie` (`id_temporada`,`nome`,`numero_episodio`) VALUES " +
            "('1','epi1','1')" +
            "('2','epi2','1')" +
            "('3','epi3','1')" +
            "('4','epi4','1')" +
            "('5','epi5','1')" +
            "('6','epi6','1')" +
            "('7','epi7','1')" +
            "('8','epi8','1')" +
            "('9','epi9','1')" +
            "('10','ep10','1')" +
            "('11','epi11','1');";

}
