package handler;

import dao.PurchasesDao;

import java.util.ArrayList;
//import java.util.LinkedHashMap;
import java.util.LinkedHashMap;


public class PurchasesHandler {


    public static LinkedHashMap<String, Object> build_purchases_dic(Object[] row){
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("reqID", row[0]);
        result.put("invID", row[1]);
        result.put("purchaseAmount", row[2]);
        result.put("purchaseDate", row[3]);
        return result;
    }

    public static ArrayList<LinkedHashMap<String, Object>> getAllPurchases(){
        PurchasesDao prchs = new PurchasesDao();
        ArrayList<Object[]> prchsList = prchs.getAllPurchases();
        ArrayList<LinkedHashMap<String,Object>> result = new ArrayList<>();
        for(int i = 0; i < prchsList.size(); i++){
            result.add(build_purchases_dic(prchsList.get(i)));
        }
        return result;
    }

    public static LinkedHashMap<String, Object> getPurchaseById(int id){
        PurchasesDao prchs = new PurchasesDao();
        ArrayList<Object[]> prchsList = prchs.getAllPurchases();
        return build_purchases_dic(prchsList.get(id));
    }


    public static ArrayList<LinkedHashMap<String, Object>> getRequestersNatJPurchasesNatJInventory(){
        RequestersHandler requesterH = new RequestersHandler();
        InventoryHandler inventoryH = new InventoryHandler();

        ArrayList<LinkedHashMap<String, Object>> purchases = getAllPurchases();
        ArrayList<LinkedHashMap<String, Object>> requesters = requesterH.getAllRequesters();
        ArrayList<LinkedHashMap<String, Object>> inventory = inventoryH.getAllInventory();

        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();

        for(int i = 0; i < purchases.size(); i++){

            LinkedHashMap<String, Object> allUserInfoFromPurchase = new LinkedHashMap<>();

            allUserInfoFromPurchase.put("reqID", purchases.get(i).get("reqId"));
            allUserInfoFromPurchase.put("invID", purchases.get(i).get("invID"));
            allUserInfoFromPurchase.put("purchaseAmount", purchases.get(i).get("purchaseAmount"));
            allUserInfoFromPurchase.put("purchaseDate", purchases.get(i).get("purchaseDate"));

            results.add(allUserInfoFromPurchase);
        }
        return results;
    }

}
