package Metier;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import DAO.DAOProcedure;
import org.json.simple.JSONObject;
public class ProcedureJSON {
    public static void CreatJSonfile()
    {
        DAOProcedure proc = new DAOProcedure();
        ArrayList<Procedure> array = new ArrayList<>(proc.getAll());
        File f = new File("C:\\tmp\\ProcedureJsonfile.json");
        f.delete();
        int i=0;
        while(i < array.size()){
            JSONObject jsonObject = new JSONObject();
            ArrayList<String> nom = new ArrayList<>();
            ArrayList<String> desc = new ArrayList<>();
            ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
            int j = 0;
            jsonObject.put("nom",array.get(i).getName());
            while(j < array.get(i).getEtape().size()){
                ArrayList<Integer> lst = new ArrayList<Integer>();
                int k =0;
                while(k < array.get(i).getEtape().get(j).getDocument().size()){
                    int l = 0;
                    while(l < array.get(i).getDocument().size()){
                        if( array.get(i).getEtape().get(j).getDocument().get(k).getNom().equals(array.get(i).getDocument().get(l).getNom()))
                        {
                            lst.add(l);
                            break;}
                        l++;
                    }
                    k++;
                }
                j++;
                list.add(lst);
            }
            j=0;
            while(j<array.get(i).getDocument().size()){
                nom.add(array.get(i).getDocument().get(j).getNom());
                desc.add(array.get(i).getDocument().get(j).getDescription());
                j++;
            }
            jsonObject.put("documents",nom);
            jsonObject.put("descDocuments",desc);
            jsonObject.put("nbrEtapes",array.get(i).getEtape().size());
            jsonObject.put("etapeDoc",list);

            try {
                FileWriter file = new FileWriter("C:\\tmp\\ProcedureJsonfile.json",true);
                if(i == 0){
                    file.append("{ \n procedures: [ \n");
                    file.append(jsonObject.toJSONString() + ", \n");
                }
                if(i>0 && i< array.size()- 1)
                    file.append(jsonObject.toJSONString() + ", \n");
                if(i == array.size() - 1)
                {
                    file.append(jsonObject.toJSONString() + "\n");
                    file.append("] }");
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
