package Presentation.Tools;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class cinVerifier extends InputVerifier {
    //private static final String [] CIN_PREFIX = {"AA" ,"AB", "AE" ,"AD" ,"B", "BB", "BE", "BH", "BJ", "BK", "BL", "Bm" ,"C", "CC", "CD" ,"CB" ,"D" ,"DA" ,"DB" ,"DC" ,"DJ" ,"DN" ,"E", "EE" ,"EA" ,"F" ,"FA" ,"FB" ,"FC" ,"FE" ,"FG" ,"FJ" ,"FK" ,"FL" ,"G" ,"GA" ,"GB" ,"GK" ,"GM" ,"H", "HH" ,"HA" ,"I" ,"IA" ,"IB" ,"IC" ,"ID" ,"IE" ,"J", "JK" ,"JA" ,"JB" ,"JC" ,"JD" ,"JE" ,"JF" ,"JT" ,"JY" ,"K", "KB" ,"KA" ,"L" ,"LA" ,"LB" ,"LC" ,"LE" ,"LF" ,"LG" ,"M" ,"MA" ,"MC" ,"N" ,"O", "OD" ,"P" ,"PA" ,"PB" ,"BX", "DF", "PP" ,"Q" ,"QA" ,"R" ,"RB" ,"RC" ,"S" ,"SH" ,"SJ" ,"SK" ,"T" ,"TA", "TK" ,"U" ,"UA" ,"UB" ,"UC" ,"UD" ,"V" ,"VA" ,"VM" ,"W" ,"WA" ,"WB" ,"X" ,"XA" ,"Y" ,"Z" ,"ZG" ,"ZT"};
    private static final String CIN_PREFIX = "(AA|AB|AE|AD|B|BB|BE|BH|BJ|BK|BL|Bm|C|CC|CD|CB|D|DA|DB|DC|DJ|DN|E|EE|EA|F|FA|FB|FC|FE|FG|FJ|FK|FL|G|GA|GB|GK|GM|H|HH|HA|I|IA|IB|IC|ID|IE|J|JK|JA|JB|JC|JD|JE|JF|JT|JY|K|KB|KA|L|LA|LB|LC|LE|LF|LG|M|MA|MC|N|O|OD|P|PA|PB|BX|DF|PP|Q|QA|R|RB|RC|S|SH|SJ|SK|T|TA|TK|U|UA|UB|UC|UD|V|VA|VM|W|WA|WB|X|XA|Y|Z|ZG|ZT)";
    public boolean verify(JComponent input) {
        JTextField cin = (JTextField) input;
     ///   if (!cin.getText().matches(CIN_PREFIX + "([0-9]{6})"))
     //       return false;
        return true;
    }
}
