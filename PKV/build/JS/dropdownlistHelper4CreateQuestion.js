	  var xmlHttp;  
      var xmlHttp;
      function showTopics(str)
      {       	  
    	  if (typeof XMLHttpRequest != "undefined")
    	  {    		  
    		  xmlHttp= new XMLHttpRequest();
    	  }
    	  else if (window.ActiveXObject)
    	  {
    		  xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
    	  }
    	  if (xmlHttp==null)
    	  {
    		  alert("Browser does not support XMLHTTP Request");
    		  return;
    	  } 
    	  var url="../utilPages/topicDDL4CreateQuestion.jsp";
    	  url +="?exam=" + str;
      
    	  xmlHttp.onreadystatechange = topicChange;
    	  xmlHttp.open("GET", url, true);
    	  xmlHttp.send(null);
      }

      function topicChange()
      {   
    	  if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    	  {   
    		  document.getElementById("ddlTopic").innerHTML=xmlHttp.responseText;  
    	  }   
      }
