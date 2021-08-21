package com.descorp.rpgdocs.modules;

import java.text.MessageFormat;
import java.util.Formatter;

/**
 *
 * @author David
 */
public class EmailTemplateBean {

    private static final String template = "<div class=\"container\"> \n" +
"    <table>\n" +
"        <tbody>\n" +
"            <tr>\n" +
"                <td>\n" +
"                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                      <tr>\n" +
"                        <td align=\"center\"><h1>Você recebeu um convite!</h1></td>\n" +
"                      </tr>\n" +
"                        <tr>\n" +
"                            <td align=\"center\">\n" +
"                                Sei que você tem uma vida ocupada, mas seus amigos\n" +
"                                precisam de ajuda em uma batalha épica que está prestes\n" +
"                                a começar.   \n" +
"                                <h3>{0}</h3> " +
"                                acabou te enviar um convite para a mesa " +
"                                <h3>{1}</h3>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td align=\"center\">\n" +
"                                <a style=\"background-color: #4287f5; color: white; padding: 1rem; border-radius: 4px; text-decoration: none\" href=\"http://localhost:8080/RpgDocs_Descorp/boards/enter.xhtml?id={2}\">Me leve até eles!</a>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                    </table>\n" +
"                 </td>\n" +
"            </tr>\n" +
"        </tbody>\n" +
"    </table> \n" +
"</div>\n";
    
    
    public static String format(String from, String tableName, String tableIdentifier){
        
        //StringBuilder sbuf = new StringBuilder(template);
        //Formatter fmt = new Formatter(sbuf);
        
        String rs = MessageFormat.format(template, from, tableName, tableIdentifier);

        //fmt.format(template, from, tableName, tableIdentifier);
        return rs;
    }

    
}
