<%-- 
    Document   : DoorLockPage.jsp
    Created on : Oct 20, 2018, 6:34:33 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.CardKey"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.reception.HotelReceptionServiceImpl"%>

<%
    boolean errorReadingCard = false;
    String roomNumber = (String) request.getParameter("roomNumber");
    
    Date endDate = null;
    Date startDate = null;
    CardKey cardKey = null;
    
    HotelReceptionService hotelReceptionService = (HotelReceptionService) session.getAttribute("hotelReceptionService");

    // If the user session has no hotelReceptionService, create a new one
    if (hotelReceptionService == null) {
        hotelReceptionService = new HotelReceptionServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelReceptionService.setSecretKeyProvider(secretKeyProvider);
        session.setAttribute("hotelReceptionService", hotelReceptionService);
    }
      
    boolean doorUnlocked = false;
    
    // used to access request card code
        String requestCardCode = (String) request.getParameter("cardCode");
        if (requestCardCode != null) {
            try {
                cardKey = hotelReceptionService.readCard(requestCardCode);
                if (cardKey!= null && roomNumber.equals(cardKey.getRoomNumber())){
                    doorUnlocked = true;
                }
            } catch (Exception ex) {
                errorReadingCard = true;
            }
        }
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Door Lock Page</title>
    </head>
    <body>
        <h1>Door Lock</h1>

        <form action="./DoorLockPage.jsp">
            Enter Room Number:<br>
            <input type="text" name="roomNumber" value="<%=roomNumber%>">
            <br>
            Enter Card Code:<br>
            <input type="text" name="cardCode" value="">
            <br>
            <input type="submit" value="Unlock Door">
        </form> 
        <br>
        <div id="result"></div>

    </body>
</html>
