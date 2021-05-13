<%@page import="model.Inventor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 
 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>

<meta charset="ISO-8859-1">
<title>Inventor Management</title>
</head>
<body>


<form id="formInventor" name="formInventor" method="post" action="Customer.jsp">  
					Inventor Name:  
					<input id="InventorName" name="InventorName" type="text" class="form-control form-control-sm">  
					
					<br> 
					Inventor Email:  
					<input id="InventorEmail" name="InventorEmail" type="text" class="form-control form-control-sm">  
					
					<br>
					 Inventor Type:  
					 <input id="InventorType" name="InventorType" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Inventor Contact:  
					 <input id="InventorContact" name="InventorContact" type="text" class="form-control form-control-sm">  
					 
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save Inventor Details" class="btn btn-primary">  
					 <input type="hidden" id="hidInventorIDSave" name="hidInventorIDSave" value=""> 
					 
					 
				</form> 
				  </div>
                </div>
            </div>
    
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
					
				
            <div class="row">
               

                <div class="container">
                    <h3 class="text-center">Inventor Management</h3>
                    <hr>
                    <div class="container text-left">

                       
                        
                    </div>
                    <br>
                
                   <div id="divItemsGrid">   
					<%    
						Inventor inventorObj = new Inventor();
						out.print(inventorObj.readInventor());   
					%>  
				
					<br>
					<br>
					 
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		</div>    

</body>
</html>