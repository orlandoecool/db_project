import handler.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import utilities.HardWiredUtility;
import utilities.JoinLinkedHashMaps;
import utilities.QueryParamUtility;


//Main URI path
@Path("/")
public class Main {

    //User
    private static UsersHandler usrs = new UsersHandler();
    //User subcategories
    private static AdminsHandler admns = new AdminsHandler();
    private static SuppliersHandler spplrs = new SuppliersHandler();
    private static RequestersHandler rqstr = new RequestersHandler();
    //Resources
    private static ResourcesHandler rs = new ResourcesHandler();
    //Resources relations
    private static InventoryHandler inv = new InventoryHandler();
    private static RequestsHandler rqsts = new RequestsHandler();
    //Requester and Inventory relations
    private static ReserveHandler rsrv = new ReserveHandler();
    private static PurchasesHandler prchs = new PurchasesHandler();

    //HARDWIRED
    HardWiredUtility hw = new HardWiredUtility();

    public static void main(String[] args) {

    }

    @GET
    @Produces("text/plain")
    public String getWelcomeMessage() {
        return "WELCOME TO HURRICANE MARIA DISASTER THINGY STUFF.";
    }

    @GET
    @Path("db_project/error")
    @Produces("text/plain")
    public Response get404ErrorMessage() {
        return Response.status(404).build();
    }

    @GET
    @Path("db_project/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        if (usrs.getAllUsers().isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(usrs.getAllUsers());
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResourcesById(@PathParam("id") int id) {
        if (usrs.getUserById(id).isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(usrs.getUserById(id)) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersWithArg(@QueryParam("uID") @DefaultValue("-1") int uID,
                                    @QueryParam("uFName") @DefaultValue("UNDECLARED") String uFName,
                                    @QueryParam("uLName") @DefaultValue("UNDECLARED") String uLName,
                                    @QueryParam("uGender") @DefaultValue("UNDECLARED") String uGender,
                                    @QueryParam("uBirthDate") @DefaultValue("UNDECLARED") String uBirthDate,
                                    @QueryParam("uRegion") @DefaultValue("UNDECLARED") String uRegion,
                                    @QueryParam("uPhoneNumber") @DefaultValue("-1") int uPhoneNumber,
                                    @QueryParam("uAddress") @DefaultValue("UNDECLARED") String uAddress,
                                    @QueryParam("username") @DefaultValue("UNDECLARED") String username,
                                    @QueryParam("password") @DefaultValue("UNDECLARED") String password) {
        ArrayList<LinkedHashMap<String, Object>> result = usrs.getUsersWithArg(uID, uFName, uLName, uGender, uBirthDate, uRegion, uPhoneNumber, uAddress, username, password);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/admins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdmins() {
        ArrayList<LinkedHashMap<String, Object>> result = admns.getAllAdmins();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/admins/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = admns.getAdminById(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/admins/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminsWithArg(@QueryParam("adminID") @DefaultValue("-1") int adminID,
                                     @QueryParam("uID") @DefaultValue("-1") int uID) {
        ArrayList<LinkedHashMap<String, Object>> result = admns.getAdminsWithArg(adminID, uID);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/suppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSuppliers() {
        ArrayList<LinkedHashMap<String, Object>> result = spplrs.getAllSuppliers();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/suppliers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupplierById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = spplrs.getSupplierById(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/suppliers/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuppliersWithArg(@QueryParam("suppID") @DefaultValue("-1") int suppID,
                                        @QueryParam("uID") @DefaultValue("-1") int uID) {
        ArrayList<LinkedHashMap<String, Object>> result = spplrs.getSuppliersWithArg(suppID, uID);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/requesters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRequesters() {
        ArrayList<LinkedHashMap<String, Object>> result = rqstr.getAllRequesters();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/requesters/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequesterById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = rqstr.getRequesterId(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/requesters/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestersWith(@QueryParam("reqID") @DefaultValue("-1") int reqID,
                                      @QueryParam("uID") @DefaultValue("-1") int uID) {
        ArrayList<LinkedHashMap<String, Object>> result = rqstr.getRequestersWithArg(reqID, uID);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources() {
        ArrayList<LinkedHashMap<String, Object>> result = rs.getAllResources();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/resources/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourceById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = rs.getResourceById(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/resources/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourcesWith(@QueryParam("resID") @DefaultValue("-1") int resID,
                                     @QueryParam("resCategory") @DefaultValue("UNDECLARED") String resCategory,
                                     @QueryParam("resSubCategory") @DefaultValue("UNDECLARED") String resSubCategory) {
        ArrayList<LinkedHashMap<String, Object>> result = rs.getResourcesWithArg(resID, resCategory, resSubCategory);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInventory() {
        ArrayList<LinkedHashMap<String, Object>> result = inv.getAllInventory();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/inventory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = inv.getInventoryById(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/inventory/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryWithArg(@QueryParam("invID") @DefaultValue("-1") int invID,
                                        @QueryParam("suppID") @DefaultValue("-1") int suppID,
                                        @QueryParam("invDate") @DefaultValue("UNDECLARED") String invDate,
                                        @QueryParam("invQty") @DefaultValue("-1") int invQty,
                                        @QueryParam("invPrice") @DefaultValue("-1") int invPrice,
                                        @QueryParam("invReserved") @DefaultValue("-1") int invReserved) {
        ArrayList<LinkedHashMap<String, Object>> result = inv.getInventoryWithArg(invID, suppID, invDate, invQty, invPrice, invReserved);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/reserve")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReserves() {
        ArrayList<LinkedHashMap<String, Object>> result = rsrv.getAllReserves();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/reserve/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReserveId(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = rsrv.getReserveId(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/reserve/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReserveWithArg(@QueryParam("reqID") @DefaultValue("-1") int reqID,
                                      @QueryParam("invID") @DefaultValue("-1") int invID,
                                      @QueryParam("resDate") @DefaultValue("UNDECLARED") String resDate,
                                      @QueryParam("resExpDate") @DefaultValue("UNDECLARED") String resExpDate,
                                      @QueryParam("resQty") @DefaultValue("-1") int resQty) {
        ArrayList<LinkedHashMap<String, Object>> result = rsrv.getReserveWithArg(reqID, invID, resDate, resExpDate, resQty);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/purchases")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPurchases() {
        ArrayList<LinkedHashMap<String, Object>> result = prchs.getAllPurchases();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/purchases/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchasesById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = prchs.getPurchaseById(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/purchases/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReserveWithArg(@QueryParam("reqID") @DefaultValue("-1") int reqID,
                                      @QueryParam("invID") @DefaultValue("-1") int invID,
                                      @QueryParam("prchsDate") @DefaultValue("UNDECLARED") String purchaseDate,
                                      @QueryParam("prchsQty") @DefaultValue("-1") int purchaseQty) {
        ArrayList<LinkedHashMap<String, Object>> result = prchs.getPurchaseWithArg(reqID, invID, purchaseDate, purchaseQty);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/requests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRequests() {
        ArrayList<LinkedHashMap<String, Object>> result = rqsts.getAllRequests();
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/requests/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestsById(@PathParam("id") int id) {
        LinkedHashMap<String, Object> result = rqsts.getRequestsById(id);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<LinkedHashMap<String, Object>> entity =
                new GenericEntity<LinkedHashMap<String, Object>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/requests/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestWithArg(@QueryParam("reqID") @DefaultValue("-1") int reqID,
                                      @QueryParam("resID") @DefaultValue("-1") int resID,
                                      @QueryParam("rqstDate") @DefaultValue("UNDECLARED") String rqstDate,
                                      @QueryParam("rqstsQty") @DefaultValue("-1") int rqstsQty) {
        ArrayList<LinkedHashMap<String, Object>> result = rqsts.getRequestsWithArg(reqID, resID, rqstDate, rqstsQty);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    /*@GET
    @Path("db_project/users/suppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJSuppliers() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) getAllUsers().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllSuppliers().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/admins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJAdmins() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) getAllUsers().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllAdmins().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequesters() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) getAllUsers().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllRequesters().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {};
        return Response.ok(entity).build();
    }


    @GET
    @Path("db_project/users/suppliers/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJSuppliersNIJInventory() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) usersNIJSuppliers().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllInventory().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/suppliers/inventory/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJSuppliersNIJInventoryNIJResources() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) usersNIJSuppliersNIJInventory().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllResources().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters/reserve")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJReserve() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) usersNIJRequesters().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllReserves().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters/reserve/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJReserveNIJInventory() {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) usersNIJRequestersNIJReserve().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllReserves().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }


    @GET
    @Path("db_project/users/requesters/reserve/inventory/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJReserveNIJInventoryNIJResources() {
        ArrayList<LinkedHashMap<String, Object>> userNIJReqNIJResNIJInv =
                (ArrayList<LinkedHashMap<String, Object>>) usersNIJRequestersNIJReserveNIJInventory().getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = listNIJ(rs.getAllResources(), userNIJReqNIJResNIJInv);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters/purchases")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJPurchases(){
        ArrayList<LinkedHashMap<String, Object>> userNIJReq =
                (ArrayList<LinkedHashMap<String, Object>>) usersNIJRequesters().getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = listNIJ(prchs.getAllPurchases(), userNIJReq);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters/purchases/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJPurchasesNIJInventory(){
        ArrayList<LinkedHashMap<String, Object>> userNIJReqNIJPur =
                (ArrayList<LinkedHashMap<String, Object>>) usersNIJRequestersNIJPurchases().getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = listNIJ(inv.getAllInventory(), userNIJReqNIJPur);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        return Response.ok(entity).build();
    }


    @GET
    @Path("db_project/users/requesters/purchases/inventory/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJPurchasesNIJInventoryNIJResources(){
        ArrayList<LinkedHashMap<String, Object>> userNIJReqNIJPurNIJInv =
                (ArrayList<LinkedHashMap<String, Object>>) usersNIJRequestersNIJPurchasesNIJInventory().getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = listNIJ(rs.getAllResources(), userNIJReqNIJPurNIJInv);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters/requests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJRequests(){
        ArrayList<LinkedHashMap<String, Object>> userNIJReq =
                (ArrayList<LinkedHashMap<String, Object>>) usersNIJRequesters().getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = listNIJ(rqsts.getAllRequests(), userNIJReq);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/requesters/requests/resources")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJRequestersNIJResources(){
        ArrayList<LinkedHashMap<String, Object>> userNIJReqNIJRqst =
                (ArrayList<LinkedHashMap<String, Object>>) usersNIJRequestersNIJRequests().getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = listNIJ(rs.getAllResources(), userNIJReqNIJRqst);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/users/suppliers/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersNIJSupplierWithArg(@Context UriInfo uriInfo) {
        ArrayList<LinkedHashMap<String, Object>> result =
                listNIJ((ArrayList<LinkedHashMap<String, Object>>) getAllUsers().getEntity(),
                        (ArrayList<LinkedHashMap<String, Object>>) getAllSuppliers().getEntity());
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        QueryParamUtility qpu = new QueryParamUtility();
        if(qpu.findQueryParam("users", "suppliers", uriInfo).isEmpty()) return get404ErrorMessage();
        return Response.ok(entity).build();
    }*/

    /*@GET
    @Path("db_project/user/admin/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/supplier/inventory/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/supplier/inventory/resources/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/reserve/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/reserve/inventory/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/reserve/inventory/resources/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/purchases/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/purchases/inventory/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/purchases/inventory/resources/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/requests/with")
    @Produces(MediaType.APPLICATION_JSON)

    @GET
    @Path("db_project/user/requesters/requests/resources/with")
    @Produces(MediaType.APPLICATION_JSON)
    */

    //*******USING AUTOMATION***********//

    @GET
    @Path("db_project/nij/{entity1}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response entity1NIJentity2(@PathParam("entity1") String entity1) {
        ArrayList<LinkedHashMap<String, Object>> list = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity1).getEntity();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(list);
        if(usrs.getAllUsers().isEmpty()) return get404ErrorMessage();
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/nij/{entity1}/{entity2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response entity1NIJentity2(@PathParam("entity1") String entity1, @PathParam("entity2") String entity2) {
        ArrayList<LinkedHashMap<String, Object>> list1 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity1).getEntity();
        ArrayList<LinkedHashMap<String, Object>> list2 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity2).getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = hw.listNIJ(list1, list2);
        if (result.isEmpty()) return get404ErrorMessage();
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(result) {
                };
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/nij/{entity1}/{entity2}/{entity3}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response entity1NIJentity2NIJentity3(@PathParam("entity1") String entity1, @PathParam("entity2") String entity2, @PathParam("entity3") String entity3) {
        ArrayList<LinkedHashMap<String, Object>> list1 = (ArrayList<LinkedHashMap<String, Object>>) entity1NIJentity2(entity1, entity2).getEntity();
        ArrayList<LinkedHashMap<String, Object>> list2 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity3).getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = hw.listNIJ(list1, list2);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        if (result.isEmpty()) return get404ErrorMessage();
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/nij/{entity1}/{entity2}/{entity3}/{entity4}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response entity1NIJentity2NIJentity3NIJentity4(@PathParam("entity1") String entity1, @PathParam("entity2") String entity2, @PathParam("entity3") String entity3, @PathParam("entity4") String entity4) {
        ArrayList<LinkedHashMap<String, Object>> list1 = (ArrayList<LinkedHashMap<String, Object>>) entity1NIJentity2NIJentity3(entity1, entity2, entity3).getEntity();
        ArrayList<LinkedHashMap<String, Object>> list2 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity4).getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = hw.listNIJ(list1, list2);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        if (result.isEmpty()) return get404ErrorMessage();
        return Response.ok(entity).build();
    }

    @GET
    @Path("db_project/nij/{entity1}/{entity2}/{entity3}/{entity4}/{entity5}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response entity1NIJentity2NIJentity3NIJentity4NIJentity5(@PathParam("entity1") String entity1, @PathParam("entity2") String entity2, @PathParam("entity3") String entity3, @PathParam("entity4") String entity4, @PathParam("entity5") String entity5) {
        ArrayList<LinkedHashMap<String, Object>> list1 = (ArrayList<LinkedHashMap<String, Object>>) entity1NIJentity2NIJentity3NIJentity4(entity1, entity2, entity3, entity4).getEntity();
        ArrayList<LinkedHashMap<String, Object>> list2 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity5).getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = hw.listNIJ(list1, list2);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        if (result.isEmpty()) return get404ErrorMessage();
        return Response.ok(entity).build();
    }

    //USING ARGUMENTS

    @GET
    @Path("db_project/nij/{entity1}/{entity2}/with")
    @Produces(MediaType.APPLICATION_JSON)
    public Response entity1NIJentity2WithArg(@PathParam("entity1") String entity1, @PathParam("entity2") String entity2, @Context UriInfo uriInfo) {
        ArrayList<LinkedHashMap<String, Object>> list1 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity1).getEntity();
        ArrayList<LinkedHashMap<String, Object>> list2 = (ArrayList<LinkedHashMap<String, Object>>) getResponse(entity2).getEntity();
        ArrayList<LinkedHashMap<String, Object>> result = hw.listNIJ(list1, list2);
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity = GE(result);
        if (result.isEmpty()) return get404ErrorMessage();
        //With Arg
        QueryParamUtility qpu = new QueryParamUtility();
        LinkedHashMap<String , Object> queryParam = qpu.findQueryParam(entity1, entity2, uriInfo);
        return Response.ok(entity).build();
    }

    private static GenericEntity<ArrayList<LinkedHashMap<String, Object>>> GE(ArrayList<LinkedHashMap<String, Object>> list){
        GenericEntity<ArrayList<LinkedHashMap<String, Object>>> entity =
                new GenericEntity<ArrayList<LinkedHashMap<String, Object>>>(list) {
                };
        return entity;
    }

    private Response getResponse(String entity){
        if(entity.equals("users")) return getAllUsers();
        else if(entity.equals("admins")) return getAllAdmins();
        else if(entity.equals("suppliers")) return getAllSuppliers();
        else if(entity.equals("admins")) return getAllAdmins();
        else if(entity.equals("requesters")) return getAllRequesters();
        else if(entity.equals("inventory")) return getAllInventory();
        else if(entity.equals("requests")) return getAllRequests();
        else if(entity.equals("reserve")) return getAllReserves();
        else if(entity.equals("purchases")) return getAllPurchases();
        else if(entity.equals("resources")) return getAllResources();

        return null;
    }

}