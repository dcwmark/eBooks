var ajax_action = "";
var currentAjaxSuccessEvent = null;
var ajaxFunctionHistory = [];
var ajaxFunctionHistorySize = 0;

function is_array(obj) {
   if (obj != undefined && obj.constructor.toString().indexOf("Array") == -1)
      return false;
   else
      return true;
}

// ajax http request
var ajax = {
  newRequest:function() {
    var msProgID = new Array ( "Msxml2.XMLHTTP.6.0", "Msxml2.XMLHTTP.3.0", "Msxml2.XMLHTTP", "Microsoft.XMLHTTP" );
    if ( typeof window.ActiveXObject == 'undefined' )
        return new XMLHttpRequest();
    else {
        //alert("Microsoft");
      //for ( i=0; i<4; i++ ) {
//      r = new ActiveXObject(msProgID[i]);
  //      if (r)
    //      return r;
          return new ActiveXObject("Microsoft.XMLHTTP");
      //}
    }
    alert("AJAX error: Cannot create HTTP request object.");
  },

  httpAsyncRequest:function( how, href, params ) { //alert("act");
      return ajax.httpRequest( how, href, params, true );
  },
                                       // params=array
                                       // the alphabet is the default parameter name list, makes the process more automated for all cases
  httpExecute:function( success_event, php_func_name, params, is_string ) {
      var param_names = new Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v');
      var param_string = "?";

      // traditionally is_string is empty or equals "STRING",
      // but it can also be used to pass an array to override parameter names!
      // does "is_string" contain actual names of the parameters?
      // if so, replace the alphabetic values with them
      if (is_string != undefined && is_array(is_string))
          for (var i = 0; i < is_string.length; i++)
              param_names[i] = is_string[i];

      //alert(param_names);

      if (is_string == "STRING")
          // This is a explicit string
          param_string += "a=" + params;
      else
      {
          // When more than one params are passed as an array
          for (var i = 0; i < params.length; i++)
          {
              param_string += param_names[i] + '=' + params[i];
              if (i < params.length-1)
                  param_string += '&';
          }

          // When one parameter is passed as a param
          if (params.length == undefined)
              param_string += "a=" + params;
      }

      var url = '';
      
      if ('http://' == php_func_name.substr(0,7))
      {
      	  url = php_func_name + param_string;
      }
      else
      {
      	  url = AJAX_BASE_DIR + php_func_name + param_string;
      }

      currentAjaxSuccessEvent = success_event;

      //alert(param_string);

      return ajax.httpRequest( "POST", url, param_string, true );
  },

  httpRequest:function( how, href, params, async ) {

    //alert(how+'\r\n'+href+'\r\n'+params+'\r\n'+async);

    r = null;

    if (!href || href == "") // this function requires a url
      return;

      if (how) {
      how = how.toUpperCase(); // make sure request type is in upper case
      if (r = ajax.newRequest()) {
        if (r.overrideMimeType)
          r.overrideMimeType('text/xml');
        r.onreadystatechange = function() {
          switch (r.readyState) {
            case 4: // COMPLETE
              switch (r.status) {
                case 200: // page returned Ok

                  if (r.responseText)
                  {
                      if (currentAjaxSuccessEvent != null)
                          currentAjaxSuccessEvent(r.responseText);
                  }

                break;
                case 404: // page not found
                  //alert("404, page not found");
                break;
                case 500: // Internal Server Error
                  alert("404, internal server error");
                break;
                default:
                        // Things that are usually and likely to be wrong when an ajax call fails:
                  //var AJAX_COMMAND_ERROR_MESSAGE = "(!) Error while executing: " + GetLastAjaxCommand() + "\r\n\r\nr.responseText = " + r.responseText + "\r\n\r\n1. If this PHP action file has code accessing a MySQL table, ensure that\r\n    a.) MySQL server is running and,\r\n    b.) The table exists\r\n2. Make sure the PHP code for this command has no errors\r\n    (It's under /ajax/ directory)\r\n3. Check server settings for size limitation on HTTP request\r\n4. Domain permission issues?\r\n5. Make sure getBaseURL() results in a correct URL for this server's domain setup (It's in ajaxactionmapping.js)";
                  //alert(AJAX_COMMAND_ERROR_MESSAGE); // something else is wrong
              };
              break;
              default:
            };
          }

          r.open(how, href, async);

          if (how == 'POST') { // we need this, otherwise the server will discard posted data
              r.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
              r.setRequestHeader("Content-length", params.length);
              r.setRequestHeader("Connection", "close");
          }

          r.send(params);

        return r;
      } else { /* could not create http request */ }
    } else { /* request method not specified */ }
  }

}; // var ajax end