package br.pro.ednilsonrossi.meupocket.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import br.pro.ednilsonrossi.meupocket.model.Site;

public class SiteDao {

    private static SharedPreferences sharedPreferences ;
    private static SharedPreferences.Editor editor;
    private static List<Site> listSite;

    public static void addSite(Context context, Site site) {

        sharedPreferences = context.getSharedPreferences("SITES", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        listSite = new ArrayList<>();
        listSite.add(site);

        try {
            JSONArray jsonArray = new JSONArray();

            for (Site s : listSite) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", s.getTitulo());
                jsonObject.put("url", s.getEndereco());
                jsonArray.put(jsonObject);
            }
            String sites = jsonArray.toString();

            editor.putString("NEW_SITE", sites);
            editor.commit();

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível inserir o site", e);
        }
    }

    public static final List<Site> recuperateAllv2(Context context){

        sharedPreferences = context.getSharedPreferences("SITES", context.MODE_PRIVATE);
        String sites = sharedPreferences.getString("NEW_SITE", "");

        JSONObject jsonObject;
        JSONArray jsonArray;

        List<Site> siteList;
        siteList = new ArrayList<>();

        try {
            jsonArray = new JSONArray(sites);

            for( int i = 0; i < jsonArray.length(); i++ ){
                jsonObject = jsonArray.getJSONObject(i);

                Site site = new Site(jsonObject.getString("title"), jsonObject.getString("url"));
                siteList.add(site);
            }
        } catch (JSONException ex){
            Log.e("ERRO_POCKET","Não foi possível encontrar o site");
        }

        return siteList;
    }

// VERSÃO 1
//  public static final List<Site> recuperateAll(){
//        ArrayList<Site> arrayList = new ArrayList<>(10);
//
//        arrayList.add(new Site("Google", "www.google.com.br"));
//        arrayList.add(new Site("Terra", "www.terra.com.br"));
//        arrayList.add(new Site("IFSP", "www.ifsp.edu.br"));
//        arrayList.add(new Site("IFSP Câmpus Araraquara", "arq.ifsp.edu.br"));
//        arrayList.add(new Site("Developers", "https://developer.android.com"));
//        arrayList.add(new Site("Vida de Programador", "https://vidadeprogramador.com.br/"));
//        arrayList.add(new Site("Stackoverflow", "https://stackoverflow.com/"));
//        arrayList.add(new Site("Youtube", "www.youtube.com.br"));
//        arrayList.add(new Site("Gmail", "www.gmail.com"));
//        arrayList.add(new Site("Facebook", "www.facebook.com"));
//        arrayList.add(new Site("Whatsapp", "www.whatsapp.com"));
//        arrayList.add(new Site("Get Pocket", "www.getpocket.com"));
//
//        return arrayList;
//    }


}