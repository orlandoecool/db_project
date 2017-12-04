import com.sun.deploy.xml.GeneralEntity;
import handler.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;


//Main URI path
@Path("/")
public class Main {

    private static UsersHandler usrs = new UsersHandler();
    private static AdminsHandler admns = new AdminsHandler();
    private static SuppliersHandler spplrs = new SuppliersHandler();
    private static RequestersHandler rqstr = new RequestersHandler();
    private static InventoryHandler inv = new InventoryHandler();
    private static ResourcesHandler rs = new ResourcesHandler();
    private static ReserveHandler res = new ReserveHandler();
    private static PurchasesHandler purchasesH = new PurchasesHandler();
    private static RequestsHandler requestsH = new RequestsHandler();

    public static void main(String[] args){

    }

    @GET
    @Produces("text/plain")
    public String getWelcomeMessage(){
        return "WELCOME TO HURRICANE MARIA DISASTER THINGY STUFF.";
    }

    @GET
    @Path("db_project/error")
    @Produces("text/plain")
    public Response get404ErrorMessage(){
        return Response.status(404).build();
    }

    @GET
    @Path("db_project/users")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllUsers(){
        return usrs.getAllUsers();
    }

    @GET
    @Path("db_project/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getAllResourcesById(@PathParam("id") int id){
        return usrs.getUserById(id);
    }

    @GET
    @Path("db_project/users/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersWith(@QueryParam("uID") @DefaultValue("-1") int uID,
                                        @QueryParam("uFName") @DefaultValue("UNDECLARED") String uFName,
                                        @QueryParam("uLName") @DefaultValue("UNDECLARED") String uLName,
                                        @QueryParam("uGender") @DefaultValue("UNDECLARED") String uGender,
                                        @QueryParam("uBirthDate") @DefaultValue("UNDECLARED") String uBirthDate,
                                        @QueryParam("uRegion") @DefaultValue("UNDECLARED") String uRegion,
                                        @QueryParam("uPhoneNumber") @DefaultValue("-1") int uPhoneNumber,
                                        @QueryParam("uAddress") @DefaultValue("UNDECLARED") String uAddress,
                                        @QueryParam("username") @DefaultValue("UNDECLARED") String username,
                                        @QueryParam("password") @DefaultValue("UNDECLARED") String password){
        return usrs.getUsersWithArg(uID, uFName, uLName, uGender, uBirthDate, uRegion, uPhoneNumber, uAddress, username, password);
    }

    @GET
    @Path("db_project/admins")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllAdmins(){
        return admns.getAllAdmins();
    }

    @GET
    @Path("db_project/admins/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getAdminById(@PathParam("id") int id){
        return admns.getAdminById(id);
    }

    @GET
    @Path("db_project/admins/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersWith(@QueryParam("adminID") @DefaultValue("-1") int adminID,
                                 @QueryParam("uID") @DefaultValue("-1") int uID){
        return admns.getAdminsWithArg(adminID, uID);
    }

    @GET
    @Path("db_project/suppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllSuppliers(){
        return spplrs.getSuppliersNaturalJoinUser();
    }

    @GET
    @Path("db_project/suppliers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getSupplierById(@PathParam("id") int id){
        return spplrs.getSupplierById(id);
    }

    @GET
    @Path("db_project/suppliers/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuppliersWithArg(@QueryParam("suppID") @DefaultValue("-1") int suppID,
                                 @QueryParam("uID") @DefaultValue("-1") int uID){
        return spplrs.getSuppliersWithArg(suppID, uID);
    }

    @GET
    @Path("db_project/requesters")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllRequesters(){
        return rqstr.getRequestersNaturalJoinUser();
    }

    @GET
    @Path("db_project/requesters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getRequesterById(@PathParam("id") int id){
        return rqstr.getRequesterId(id);
    }

    @GET
    @Path("db_project/requesters/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestersWith(@QueryParam("reqID") @DefaultValue("-1") int reqID,
                                        @QueryParam("uID") @DefaultValue("-1") int uID){
        return rqstr.getRequestersWithArg(reqID, uID);
    }

    @GET
    @Path("db_project/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllResources(){
        return rs.getAllResources();
    }

    @GET
    @Path("db_project/resources/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getResourceById(@PathParam("id") int id){
        return rs.getResourceById(id);
    }

    @GET
    @Path("db_project/resources/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourcesWith(@QueryParam("resID") @DefaultValue("-1") int reqID,
                                     @QueryParam("resCategory") @DefaultValue("UNDECLARED") String resCategory,
                                     @QueryParam("resSubCategory") @DefaultValue("UNDECLARED") String resSubCategory){
        return rs.getResourcesWithArg(reqID, resCategory, resSubCategory);
    }

    @GET
    @Path("db_project/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllInventory(){
        return inv.getAllInventory();
    }

    @GET
    @Path("db_project/inventory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getInventoryById(@PathParam("id") int id){
        return inv.getInventoryById(id);
    }

    @GET
    @Path("db_project/inventory/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryWithArg(@QueryParam("invID") @DefaultValue("-1") int invID,
                                        @QueryParam("suppID") @DefaultValue("-1") int suppID,
                                        @QueryParam("invDate") @DefaultValue("UNDECLARED") String invDate,
                                        @QueryParam("invQty") @DefaultValue("-1") int invQty,
                                        @QueryParam("invPrice") @DefaultValue("-1") int invPrice,
                                        @QueryParam("invReserved") @DefaultValue("-1") int invReserved){
        return inv.getInventoryWithArg(invID, suppID, invDate, invQty, invPrice, invReserved);
    }

    @GET
    @Path("db_project/reserve")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllReserves(){
        return res.getRequestersNaturalJoinInventory();
    }

    @GET
    @Path("db_project/reserve/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedHashMap<String, Object> getReserveId(@PathParam("id") int id){
        return res.getReserveId(id);
    }

    @GET
    @Path("db_project/reserve/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryWithArg(@QueryParam("reqID") @DefaultValue("-1") int reqID,
                                        @QueryParam("invID") @DefaultValue("-1") int invID,
                                        @QueryParam("resDate") @DefaultValue("UNDECLARED") String resDate,
                                        @QueryParam("resExpDate") @DefaultValue("UNDECLARED") String resExpDate,
                                        @QueryParam("resQty") @DefaultValue("-1") int resQty){
        return res.getReserveWithArg(reqID, invID, resDate, resExpDate, resQty);
    }

    @GET
    @Path("db_project/purchases")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LinkedHashMap<String, Object>> getAllPurchases(){
        return purchasesH.getRequestersNatJPurchasesNatJInventory();
    }

    @GET
    @Path("db_project/requests")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Hashtable<String, Object>> getAllRequests(){return requestsH.getAllRequests();
    }

    @GET
    @Path("db_project/requests/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hashtable<String, Object> getRequestsById(@PathParam("id") int id){
        return requestsH.getRequestsById(id);
    }

    @GET
    @Path("db_project/inventory/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryWithArg(@QueryParam("rqstsID") @DefaultValue("-1") int rqstsID,
                                        @QueryParam("rqstsQty") @DefaultValue("-1") int rqstsQty,
                                        @QueryParam("rqstsDate") @DefaultValue("UNDECLARED") String rqstsDate,
                                        @QueryParam("reqID") @DefaultValue("-1") int reqID){
        return requestsH.getRequestsWithArg(rqstsID, rqstsQty, rqstsDate, reqID);
    }

}
