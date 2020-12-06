package Metier;

import DAO.DAOEmploye;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeJSON {

    public static  void CreatJSonfile() {
        DAOEmploye emp = new DAOEmploye();
        ArrayList<Employe> array = new ArrayList<>(emp.getAll());

        File myobj = new File("C:\\tmp\\EmployeJsonfile.json");
        myobj.delete();
        int i=0;
        try {
            FileWriter file = new FileWriter("C:\\tmp\\EmployeJsonfile.json", true);
            file.append("{ \n \"employes\": \n[\n" );
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(i < array.size()){
            JSONObject jsonObject = new JSONObject();
            ArrayList<Integer> num = new ArrayList<>();
            jsonObject.put("login",array.get(i).getLogin());
            jsonObject.put("password",array.get(i).getMotDePasse());
            jsonObject.put("chef",array.get(i).getChef());
            jsonObject.put("procedure",array.get(i).getProcédures());
            int j = 0;
            j=0;
            while(j < array.get(i).getEtape().size()){
                num.add(array.get(i).getEtape().get(j));
                j++;
            }
            jsonObject.put("etapes",num);

            try {
                FileWriter file = new FileWriter("C:\\tmp\\EmployeJsonfile.json", true);
                file.append(jsonObject.toJSONString()+"\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        try {
            FileWriter file = new FileWriter("C:\\tmp\\EmployeJsonfile.json", true);
            file.append("]\n}\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
