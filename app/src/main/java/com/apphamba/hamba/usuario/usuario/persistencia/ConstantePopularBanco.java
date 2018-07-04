package com.apphamba.hamba.usuario.usuario.persistencia;

//POPULAR BANCO COM EXEMPLOS DE
public class ConstantePopularBanco {
    public static final String INSERIR_USUARIO = "INSERT INTO `usuario` (`email`,`senha`) VALUES " +
            "('a@gmail.com','111111')," +
            "('b@gmail.com','111111')," +
            "('c@gmail.com','111111')," +
            "('d@gmail.com','111111')," +
            "('e@gmail.com','111111')," +
            "('f@gmail.com','111111')," +
            "('g@gmail.com','111111')," +
            "('h@gmail.com','111111')," +
            "('i@gmail.com','111111')," +
            "('j@gmail.com','111111');";

    public static final String INSERIR_PESSOA = "INSERT INTO `pessoa` (`nome`,`sexo`,`data_nasc`,`id_est_usuario`) VALUES " +
            "('Maite Stokes','Feminino',1)," +
            "('Kane Kramer','Masculino',2)," +
            "('Jenette Caldwell','Feminino','19881201',3)," +
            "('Nomlanga Dennis','Masculino','19900212',4)," +
            "('Leigh Day','Masculino','20001120',5)," +
            "('Laith Miller','Feminino','19850417',6)," +
            "('Amela Anderson','Feminino','19400123',7)," +
            "('Lucy Whitehead','Feminino','19730202',8)," +
            "('Indigo Ortega','Masculino','19870227',9)," +
            "('Akeem Stout','Feminino','19950505',10);";


}
