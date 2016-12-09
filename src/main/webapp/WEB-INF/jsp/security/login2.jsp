
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
    <style>
   	html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; } 
    	
    </style>
<style type="text/css" media="all">
html,body{
height:100%;
margin:0;
padding:0;
}
body{
   background:url(resources/images/loginbg.jpg);
    background-size: 100% 100%;
   -moz-background-size:100% 100%;
    background-repeat: no-repeat;
}
#top{position:relative;float:right;width:100%;text-align:center;}
#floater{height:50%;margin-bottom:-150px;position:relative;}
#content{clear:both;height:350px;position:relative;margin:0 auto;width:50%;padding:20px;background-color:#d9ecf5;border-radius: 50%; }

.after{text-align:right;padding-right:20px;font-size:0.75em;}
</style>

    <title>Booking System Entry</title>
    <link rel="stylesheet" href="resources/styles/kendo.common.min.css" />

    <link rel="stylesheet" href="resources/styles/kendo.common-bootstrap.min.css">
    <link rel="stylesheet" href="resources/styles/kendo.bootstrap.min.css">
    <link rel="stylesheet" href="resources/styles/kendo.blueopal.min.css" />

    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/kendo.all.min.js"></script>
</head>
<body>
<div id="top">
<h1></h1>

</div>
<div id="floater">

</div>
       
        <div id="content">
	<!--<div id="logo">&nbsp;</div>-->
<center>
            <form id="loginForm"  name="loginForm"  action="/DrugPortal/filterUrl" method="post" >
 			<table>
              <tr >
                <td><label for="username" ><h4>User用戶</h4></label></td>
	<td><input type="text" class=" k-textbox" id="username" name="j_username" placeholder="Enter your user ID" required validationMessage="User is required!請輸入用戶！" ></td>           
              </tr>
              <tr >
                <td><label for="password" ><h4>Password密碼</h4></label></td>

               <td> <input type="password" class=" k-textbox" id="password" name="j_password" placeholder="Enter your pasword" required validationMessage="Password is required!請輸入密碼！"></td>

              </tr>
              <!--  
              <tr >
	<td><label for="clinics" class=""><h4>Clinic診所</h4></label></td>
                <td><input id="clinics" class="" name="j_clinic"  required validationMessage="Please select a clinic!請選擇診所！"/></td>
              </tr>
              -->
              <tr >
				<td><label for="languages" class=""><h4>Language語言</h4></label></td>
                <td><input id="languages" class="" name="j_locale" /></td>
              </tr>

            <tr>
				<td><label for="password" class=""  id="securtiyLabel" style="display:none"><h4>Security Code 安全碼</h4></label></td>	
                <td><input id="securityCode" style="display:none" class="  k-textbox" name="j_securityCode" placeholder="Enter your security code" validationMessage="Security Code is required!請輸入安全碼！" />	</td>
              </tr>

             
 			<tr  id="securtiyArea2" >
				<td><label class="" id="securtiyLabel2" style="display:none"></label></td>
                 <td><input type="button" class=" k-button " style="display:none" id="getCodeBtn"  value="Get Security Code獲取安全碼"></td>
             </tr>

			<tr>
				<td><label class=""></label></td>
                <td> <input type="submit" class=" k-button k-primary"  id="get" value="Sign登錄" ></td>
             </tr>
            
        </table>      

            </form>
<div id="errormsg" ></div>
</center>
        </div>
        <style>
#logo{
    background:url(resources/images/iRad_logo_nobg.png);
    background-size: 35% 100%;
   -moz-background-size:35% 100%;
    background-repeat: no-repeat;
} 
           .row {
	  display: table-row;
	}
         .column {
	  display: table-cell;
	  padding-left:5px;
	 padding-right:5px;
	text-align:left;
	/*border-radius: 20px;*/
	}

/*.k-dropdown .k-dropdown-wrap, .k-dropdown.k-state-border-down .k-dropdown-wrap.k-state-border-down {
    padding: 5px !important;
    border: 1px solid #959694 !important;
    border-radius: 20px 20px 20px 20px !important;
}
.k-dropdown .k-dropdown-wrap .k-select, .k-ie .k-dropdown .k-dropdown-wrap .k-select, .k-ff .k-dropdown .k-dropdown-wrap .k-select {
    background-color: #f6f7ef !important;
    padding: 8px !important;
    border-left: solid #959694 thin !important;
    border-right: solid #959694 thin !important;
    border-radius: 0 20px 20px 20px !important;
}
[data-role="popup"] {
    background-color: #fff !important;
    border-radius: 20px !important;
}
[data-role="popup"] ul li[class="k-item k-state-hover"], [data-role="popup"] ul li[class="k-item k-state-selected k-state-focused"] {
    border-radius: 20px;
}*/
           
        </style>

            <script>
			var securityCodeRequired=false;
                $(document).ready(function() {
				$.ajax({
                   url : "/DrugPortal/mobile/checkValidateSecurityCode",
                   type: "GET",                		        
                   success:function(data, textStatus, jqXHR) {

					console.log(data);
					if(data=="true"){
						securityCodeRequired=true;
						$("#securtiyLabel").show();
						$("#securtiyLabel2").show();
						$("#securityCode").show();
						$("#getCodeBtn").show();
					}
					else{
						securityCodeRequired=false;
						$("#securtiyLabel").hide();
						$("#securtiyLabel2").hide();
						$("#securtiyCode").hide();
						$("#getCodeBtn").hide();
			              		
					}

					$("#get").show();
			        }, 
			       error: function(jqXHR, textStatus, errorThrown) 
                    		        {
                    		            //if fails  
			          
			           			$("#errormsg").html("Failed to get security code,please contact system administrator!");
                    		        }
                  });
				$("#username").focusout(function() {
				    var userid=$("#username").val();
				    if(userid!==""){
				    	$.ajax({
			                   url : "/DrugPortal/mobile/getUser/"+userid,
			                   type: "GET",                		        
			                   success:function(data, textStatus, jqXHR) {

								console.log(data);
								 var languages = $("#languages").data("kendoDropDownList");
								 if(data.locale=="zh-HK")
									 languages.select(0);
								 else if(data.locale=="en-US")
									 languages.select(1);
								 else
									 languages.select(2);
								
						        }, 
						       error: function(jqXHR, textStatus, errorThrown) 
			                    		        {
			                    		            //if fails  					          
						           					$("#errormsg").html("Failed to get user information,please contact system administrator!");
			                    		        }
			                  });
				    }
				  });

		


	//initial clinics dropdownlist
/*
	$("#clinics").kendoDropDownList({
                       			 dataTextField: "clinicName",
                       			 dataValueField: "id",
                        			
                       			 index: 0
                        
                   		 });
	$.ajax({
                  	 url : "/DrugPortal/booking/clinics",
                  	type: "POST",                		        
                   	success:function(data, textStatus, jqXHR) {

			console.log(data);
			$("#clinics").kendoDropDownList({
                       			 dataTextField: "clinicName",
                       			 dataValueField: "id",
                        			dataSource: data,
                       			 index: 0
                        
                   		 });
			var clinics = $("#clinics ").data("kendoDropDownList");
                   		clinics .select(0);
					
		     }, 
			       error: function(jqXHR, textStatus, errorThrown) 
                    		        {
                    		            //if fails  
			          
			           	$("#errormsg").html("Error for getting clinics,please contact system administrator!");
                    		        }
                  });
	
*/
                    // create DropDownList from select HTML element


                    

                    var landata = [
	      { text: "繁體中文", value: "zh-HK" },
                        { text: "English", value: "en-US" },               
                        { text: "简体中文", value: "zh-CN" }
                    ];

                    // create DropDownList from input HTML element
                    $("#languages").kendoDropDownList({
                        dataTextField: "text",
                        dataValueField: "value",
                        dataSource: landata,
                        index: 0,
                        change: onChange
                    });

                    // create DropDownList from select HTML element


                    var languages = $("#languages").data("kendoDropDownList");

                    languages.select(0);


                    function onChange() {
                        var value = $("#languages").val();
                    };

	$("#getCodeBtn").click(function(){
		console.log($("#username").val())
		var userid=$.trim($("#username").val());
		if(userid==null || userid=="")
			$("#errormsg").html("<font color=red size=4>"+"Please enter User ID first請先輸入用戶ID"+"</font>");
		else{
			$.ajax({
                url : "/DrugPortal/mobile/genSecurityCode/"+userid,
                type: "GET",                		        
                success:function(data, textStatus, jqXHR){
					console.log(data);
					if(data==="Unregistered")
						$("#errormsg").html("<font color=red size=4>"+"Please register your mobile phone first請先註冊您的移動電話！"+"</font>");
					else if(data==="InvalidUser")
						$("#errormsg").html("<font color=red size=4>"+"Invalid user!該用戶名無效！"+"</font>");	
					else
						$("#errormsg").html("<font color=green size=4>"+"SecurityCode generated安全碼已生成"+"</font>");
			    }, 
			    error: function(jqXHR, textStatus, errorThrown) 
                {
                   	//if fails  
			         if($("#username").val()==="admin") {
						 console.log(textStatus);
						 console.log(errorThrown);
			         	$("#errormsg").html("Error for getting security code,please contact system administrator!");
					 }
                 }
             });
		}//else
	});

     $("#get").click(function(e) {
		if(securityCodeRequired && $("#securityCode").val()===""||$("#username").val()==="" || $("#password").val()===""){
			if($("#password").val()==="")
				$("#errormsg").html("<font color=red size=4>"+"Password is required!請輸入密碼！"+"</font>");
			
			else if($("#username").val()==="")
				$("#errormsg").html("<font color=red size=4>"+"User is required!請輸入用戶！"+"</font>");
			else if($("#securityCode").val()==="")
				$("#errormsg").html("<font color=red size=4>"+"Security Code required 請輸入安全碼！"+"</font>");
			e.preventDefault(); //STOP default action
           // e.unbind(); //unbind. to stop multiple form submit.
		}
		else{
			
			//----sumit form
						var validator = $("#loginForm").kendoValidator().data("kendoValidator");
						if (validator.validate()) {
							kendo.ui.progress($("#content"),true);
							$("#loginForm").submit(function(e){
								var postData = $(this).serializeArray();
								var formURL = $(this).attr("action");
								console.log("postdata:");
								console.log(postData);
								console.log(formURL);
								$.ajax({
								   url : formURL,
								   type: "POST",
								   data : postData,
								   success:function(data, textStatus, jqXHR){
									console.log(data);//data from com.versionsystem.service.security.SecurityLoginSuccessHandler or com.versionsystem.service.security.SecurityLoginFailureHandler 
									//data: return data from server
									data=data.replace(/success/,'\"success\"');
									data=data.replace(/message/,'\"message\"');
									data=data.replace(/targetUrl/,'\"targetUrl\"');
									console.log(data);
									var jdata=JSON.parse(data);
										
									if(jdata.success)
										window.location = 'home';
									else{
										kendo.ui.progress($("#content"),false);
										console.log("failure",data);
										$("#errormsg").html("<font color=red size=4>"+jdata.message+"</font>");
									//alert(jdata.message);
									}//else
									},//success function
									error: function(jqXHR, textStatus, errorThrown){
										//if fails  
									   kendo.ui.progress($("#content"),false);    
									   $("#errormsg").html("Invalid user or password!!");
									}//error function
								   });//ajax function
								   
								   e.preventDefault(); //STOP default action
								   e.unbind(); //unbind. to stop multiple form submit.
								 });   //submit function
							  
						}//validate function
						else
							console.log('invalid');
								
						//---end of submit form
			
			/*$.ajax({
                url : "/DrugPortal/mobile/checkSecurityCodeValid/"+$("#username").val()+"/"+$("#securityCode").val(),
                type: "GET",                		        
                success:function(data, textStatus, jqXHR){
					console.log(data);
					
					if(data==="NULL"){
						$("#errormsg").html("<font color=red size=4>"+"Please get Security Code first!請先獲取安全碼！"+"</font>");
						//e.preventDefault(); //STOP default action
            			//e.unbind(); //unbind. to stop multiple form submit.
					}
					else if(data==="INVALID"){
						$("#errormsg").html("<font color=red size=4>"+"Invalid security code！該安全碼無效！"+"</font>");
						//e.preventDefault(); //STOP default action
            			//e.unbind(); //unbind. to stop multiple form submit.
						}
					else if(data==="EXPIRED"){
						$("#errormsg").html("<font color=red size=4>"+"This security code has been expired！該安全碼已失效！"+"</font>");
						//e.preventDefault(); //STOP default action
            			//e.unbind(); //unbind. to stop multiple form submit.
					}
					else if(data==="InvalidUser"){
						$("#errormsg").html("<font color=red size=4>"+"Invalid User！該用戶名無效！"+"</font>");
						//e.preventDefault(); //STOP default action
            			//e.unbind(); //unbind. to stop multiple form submit.
					}
					else if(data==="VALID"){
						console.log("submit");
						
					}
			    }, 
			    error: function(jqXHR, textStatus, errorThrown) 
                {
                   	//if fails  
			         if($("#username").val()==="admin") {
						 console.log(textStatus);
						 console.log(errorThrown);
			         	$("#errormsg").html("Error for getting security code,please contact system administrator!");
					 }
                 }
             });
			*/
				
			
			
		}//else
       });//get click
	 
                    
                    		 
                    		
    });//document
            </script>
       


</body>
</html>
