// File last updated: 03-21-2010

var share_btn_status = 0;    /* 0-not ready, 1-ready to post */

var is_frontpage = false;

var AJAX_BASE_DIR = "http://www.learnjquery.org/ajax/";//GetSiteLocation(); //window.location.href + "/ajax/"; //"http://www.x.com/x.com/ajax/";



/**
 * Create a cookie with the given name and value and other optional parameters.
 *
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
 * @desc Create a cookie with all available options.
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example $.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value. Keep in mind that you have to use the same path and domain
 *       used when the cookie was set.
 *
 * @param String name The name of the cookie.
 * @param String value The value of the cookie.
 * @param Object options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object.
 *                             If a negative value is specified (e.g. a date in the past), the cookie will be deleted.
 *                             If set to null or omitted, the cookie will be a session cookie and will not be retained
 *                             when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will
 *                        require a secure protocol (like HTTPS).
 * @type undefined
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */

/**
 * Get the value of a cookie with the given name.
 *
 * @example $.cookie('the_cookie');
 * @desc Get the value of a cookie.
 *
 * @param String name The name of the cookie.
 * @return The value of the cookie.
 * @type String
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};


// Global SignIn(Out) Status used only for user interface states (has no effect on actual signed in/out state)
var SignedIn = 0;

function GetLastAjaxCommand() { return (ajaxFunctionHistory[ajaxFunctionHistorySize-1]).toString().toUpperCase(); }

// Utility string function: trim a string
function trimString(s) { return s.replace(/^\s*/, "").replace(/\s*$/, ""); }

// Javascript, javascript...
function setCharAt(str,index,chr)
{
    if (index > str.length-1)
        return str;
    return str.substr(0,index) + chr + str.substr(index+1);
}

function GetSiteLocation()
{
    if (document.URL.search(/localhost/) != -1)
        return "http://localhost/authenticsociety.com/ajax/";
    else
        return "http://www.authenticsociety.com/ajax/";
}

// get all id names in a parent container and construct an array of their values
function GetAllIDValuesFrom(element, parent)
{
    var idvaluelist = new Array();
    for (var i = 0; i < $(element, parent).length; i++)
        idvaluelist[i] = $(element, parent)[i].value;

    //alert(idvaluelist);

    return idvaluelist;
}

function GetAllIDNamesFrom(element, parent)
{
    var idnamelist = new Array();
    for (var i = 0; i < $(element, parent).length; i++)
        idnamelist[i] = $(element, parent)[i].id;
    return idnamelist;
}

// Interface update code
// Success action events for each of the comands above

// Shows a just added news story
function SuccessEvent_AddNews(html)
{
    var e = gbi('news_feed_div');
    e.innerHTML = html + '\r\n' + e.innerHTML;
}

// Hides the news from UI
function SuccessEvent_DeleteNews(id)
{
    var e = gbi('main_news_' + parseInt(id) + '_container');
    e.style.display = 'none';
}

function SuccessEvent_UpdateNews(batch)
{
    var params = batch.split("№", 2); // ? is a special splitter character (Russian)
    var id = params[0];
    switchdiv('news_id_' + parseInt(id) + '_solid', 'news_id_' + parseInt(id) + '_editable');
    var e = gbi('news_id_' + parseInt(id) + '_solid');
    if (e) e.innerHTML = params[1];
}

function SuccessEvent_AddFileToDatabase(data)
{
    //alert("SuccessEvent_AddFileToDatabase," + data);
}

function SuccessEvent_DeleteFilesFromDatabase(number_of_files)
{
    // Visually erase files from selected checkboxes
    for (var idx = 0; idx < number_of_files; idx++)
        if ($('input[id=chf' + idx + ']').attr('checked'))
            $('#chfcontainer' + idx).css('display', 'none');
    // Remove loading animation
}

/* Create account */

var email_client_list = new Array(/.*?(@gmail)/,/.*?(@yahoo)/,/.*?(@hotmail)/);
var email_client_url = new Array("http://www.gmail.com/","http://mail.yahoo.com/","http://www.hotmail.com/");

function SuccessEvent_UserAccountCreated(data)
{
    var res = 0, i = 0, msg = "", arr = new Array();
    if (data == 7) /* exists, not activated */
    {
        msg = "Your account is not yet active. Please check your email inbox (or filtered messages folder) for an email from Authentic Society containing activation code.";
        $('#CreateAccountErrorLog').html('<b>Note</b>: <img src = "http://www.authenticsociety.com/Images/Warning.png" align = "top" /> Account with this email address already exists. ' + msg);
        $('#ActivateAccountForm').fadeIn(500);
    }
    else
    if (data == 8) /* exists, activated */
    {
        msg = "This account is also already activated. To guarantee safety of your privacy, there is no way to recover your password if you forget it.";/* Please use <a href = 'http://localhost/authenticsociety.com/ForgotPassword'>Password Recovery</a> form if you don't remember your sign in credentials.";*/
        $('#CreateAccountErrorLog').html('<b>Note</b>: <img src = "http://www.authenticsociety.com/Images/Warning.png" align = "top" /> Account with this email address already exists. ' + msg);
    }
    else
    {
        

        var params = data.split("№", 3);
        var uid = params[0];
        var pwd = params[1];
        if (isnumeric(uid)) {
            $.cookie("asid", ""+uid, { expires: 7, path: '/' });
            ajax.httpExecute(SuccessEvent_SignIn, SIGNIN, [uid, pwd]);
        }


        //ForwardSignIn(params[0],params[1]);

        /*

        for (i = 0; i < email_client_list.length; i++) {
            arr = data.match(email_client_list[ i ]);
            if (arr != null)
                $('#email_client').html("<a href = '" + email_client_url[i] + "' target = '_blank'><img border = '0' src = 'http://www.authenticsociety.com/Images/Logos/" + arr[1] + ".png'/><br/><span style = 'font-size:11px;'>Go to my inbox</span></a>");
        }
        $('#ActivateAccountForm').fadeIn(500);

        */



    }
    $('#CreateAccountLoading').css('display', 'none');
}

/* Temporary */
function ForwardSignIn()
{

}

function SuccessEvent_CheckConfirmationNumber(batch)
{
    $('#CreateAccountLoading').css('display', 'none');
    $('#CreateAccountErrorLog').css('display', 'none');

    var params = batch.split("№", 4); // ? is a special splitter character (Russian)

    var id = params[0];
    var cc = params[1];
    var st = params[2]; // status

    var SUCCESS = st;

    //alert(id + "," + cc + ", SUCCESS = " + SUCCESS);

    if (SUCCESS == "true")
    {
        $('#ActivateAccountForm').css('display', 'none');
        $('#CodeAcceptFailMessage').css('display', 'none');
        $('#CodeAcceptSuccessMessage').fadeIn(500);
        var login_id = document.getElementById("login_id");
        id = id.replace(/\s/g, "");
        login_id.value = id;
    }
    else
    {
        $('#CodeAcceptFailMessage').fadeIn(500);
    }
}

function SuccessEvent_SignIn(ret)
{
    //alert(ret);

    var params = ret.split("№", 2);
    var re = new RegExp('Bad Login Initiation');
    if (params[0].match(re)) alert("Sorry, login provided is incorrect. There is no existing user with the username and password combination you have provided. Try again using correct credentials."); else { /* Successful login */ }
    if (params[0].match(re))
    {
        SignedIn = 0;
    }
    else
    {
        //alert(getBaseURL());

        redirect( getBaseURL() );

        SignedIn = 1;
    }
}

function SuccessEvent_SignOut(ret)
{
    deletecookie("asid");

    SignedIn = 0;

    //alert(getBaseURL());

    redirect( getBaseURL() );
}

function SuccessEvent_VoteUp(ret)
{

}

function SuccessEvent_VoteDown(ret)
{

}

function SuccessEvent_OpenComments(ret)
{

}

function SuccessEvent_PostComment(ret)
{

}

function SuccessEvent_DeleteAccount(ret)
{
    if (ret == 1) {
        alert("Your account has been deleted. You have been signed out.\r\n\r\nThanks for using Platform of Reason!");
        parent.window.SignOut();
    }
    else
        alert("Ooops, there has been a problem deleting your account at this time. Try again later.\r\n\r\n" + ret);
}

function SuccessEvent_RetreivePassword(ret)
{
    $('#retpw_msg').html(ret);
}

function SuccessEvent_UpdateProfile(ret)
{
    //alert(ret);
    
    $('#UpdateProfileLoading').css('display', 'none');

    $('#FinishedUpdatingProfile').css('display', 'block');

    setTimeout(function(){ $('#FinishedUpdatingProfile').fadeOut(500) }, 1500);
}

function SuccessEvent_SuggestArticleContent(ret)
{
    document.getElementById("suggest_box").innerHTML = "Your suggestion was received and will be considered -- thank you!";
}

function SuccessEvent_SendNotification1(ret)
{
    
}

/* Social Network */

var CommunityID = 0; /* Ideally define in feed.js, but IE sucks throwin' an error */

function SuccessEvent_MessagePosted(batch)
{
    var params = batch.split("№", 6); // № is a special splitter character (Russian)

    var id = params[0]; // user id
    var un = params[1]; // user name
    var st = params[2]; // message
    st = st.replace(/\+/g,'%2B');
    var tm = params[3]; // timestamp

    //alert(params[4]);

    var CommunityID = params[4]; // community id (not needed/used)
    var fi = params[5]; // feed id

    $('#inptbtn').removeAttr("disabled");

    $('#WordCount').css('display', 'block');

    $('#WordCount').text('');

    $('#PostLoading').css('display', 'none');

    $('#inptbtn').attr('src', 'http://www.authenticsociety.com/Images/Site/Buttons/Share.png');

    //CommunityID = ci;

    //alert(CommunityID);

    //alert([id,un,st,tm]);

    //st = st.replace(/#/g, 'POUND_SIGN');

    //$("#extra_arrow_" + fi).css("display", "none");


    ajax.httpExecute(SuccessEvent_UpdateFeedAfterMessagePosted, UPDATEFEEDAFTERMESSAGEPOSTED, [id,un,st,tm,fi,CommunityID]);
}

function SuccessEvent_UpdateFeedAfterMessagePosted(batch)
{
    var params = batch.split("№", 3); // № is a special splitter character (Russian)

    var CommunityID = params[0]; // community id (not needed/used)

    var data = params[1]; //

    var msgid = params[2];

    $('#main_feed').html( data + $('#main_feed').html() );

    $('#feed_message_' + msgid).animate({'background' : '#ffffff'});

    // Update stats
    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);
}

function SuccessEvent_DeleteFeedMessage(data)
{
    var tag = '#feed_message_' + trimString(data);

    //alert("'" + tag + "'");

    $(tag).fadeOut();

    // Update stats
    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);
}

function SuccessEvent_PostFeedComment(batch)
{
    var params = batch.split("№", 2); // № is a special splitter character (Russian)

    var fi = params[0]; // feedid
    var ms = params[1]; // msg
    //ms = ms.replace(/\+/g, '%2B');


    //alert(params);

    var tag = 'feed_' + trimString(fi) + '_new_comment_placeholder';

    $('#' + tag).html($('#' + tag).html() + ms);

    $('#comment_box_' + trimString(fi)).css('display', 'none');

    $('#comment_msg_' + trimString(fi)).val('Write a comment...');

    $('#comments_block_feed_' + trimString(fi)).css("marginTop", "0px");

    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    UnpauseFeedReload();
}

function SuccessEvent_LikeFeedMessage(ret)
{
    var params = ret.split("№", 2); // № is a special splitter character (Russian)

    var fi = params[0]; // feedid

    var ht = params[1]; // html

    $('#like_feed_' + trimString(fi) + '_link').css("display", "none");
    $('#unlike_feed_' + trimString(fi) + '_link').css("display", "inline");

    $("#extra_arrow_" + trimString(fi)).css("display", "block");

    $('#LikeField' + trimString(fi)).css("display", "block");

    $('#LikeField' + trimString(fi)).html(ht);

    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);
}

function SuccessEvent_LikeFeedComment(batch)
{
    var params = batch.split("№", 3); // № is a special splitter character (Russian)

    var fi = params[0]; // feedid
    var ci = params[1]; // comment id
    var ms = params[2]; // like message
    var tg = '#feed_' + trimString(fi) + '_cmt_' + trimString(ci);

    $(tg).html( ms );

    // enable/disable proper like buttons
    var unlike_button = '#unlike_feed_' + trimString(fi) + '_comment_' + trimString(ci) + '_button';
    var   like_button =   '#like_feed_' + trimString(fi) + '_comment_' + trimString(ci) + '_button';

    $(unlike_button).css("display", "inline");
    $(like_button).css("display", "none");

    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);
}

function SuccessEvent_UnlikeFeedComment(batch)
{
    var params = batch.split("№", 3); // № is a special splitter character (Russian)

    //alert(params);

    var fi = params[0]; // feedid
    var ci = params[1]; // comment id
    var ms = params[2]; // like message
    var tg = '#feed_' + trimString(fi) + '_cmt_' + trimString(ci);

    $(tg).html( ms );

    // enable/disable proper like buttons
    var unlike_button = '#unlike_feed_' + trimString(fi) + '_comment_' + trimString(ci) + '_button';
    var   like_button =   '#like_feed_' + trimString(fi) + '_comment_' + trimString(ci) + '_button';

    $(unlike_button).css("display", "none");
    $(like_button).css("display", "inline");

    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);
}

function SuccessEvent_UpdateFeedCommentLikeStatus(batch)
{
    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);
}

function SuccessEvent_UnlikeFeedMessage(batch)
{
    var params = batch.split("№", 3); // № is a special splitter character (Russian)

    var nl = params[0]; // number of remaining likes

    var fi = params[1]; // feedid

    var ht = params[2]; // html

    //$("#extra_arrow_" + trimString(fi)).css("display", "block");

    $('#like_feed_' + trimString(fi) + '_link').css("display", "inline");
    $('#unlike_feed_' + trimString(fi) + '_link').css("display", "none");

    if (nl == 0)
    {
        $('#LikeField' + trimString(fi)).css("display", "none");
        $("#extra_arrow_" + fi).css("display", "none"); // hide arrow
    }

    $('#LikeField' + trimString(fi)).html(ht);

    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);
}

function SuccessEvent_UserSetHasImage(data)
{
    //alert(data);
}

function SuccessEvent_NewInterestAdded(data)
{
    alert('SuccessEvent_NewInterestAdded');

    var params = data.split("№", 3); // № is a special splitter character (Russian)

    var id = params[0]; // item id

    var st = params[1]; // status message

    var up = params[2]; // updated content

    //alert(data);

    if (trimString(id) == "already_exists")
    {

    }
    else
    {
        $('#SortableInterestLevel1').html( $('#SortableInterestLevel1').html() + up );
    }


    $('#add_int1_msg').html(st);
}

function SuccessEvent_NewInterestAdded_L2(data)
{
    var params = data.split("№", 3); // № is a special splitter character (Russian)

    var id = params[0]; // item id

    var st = params[1]; // status message

    var up = params[2]; // updated content

    if (trimString(id) == "already_exists")
    {

    }
    else
    {
        $('#SortableInterestLevel2').html( $('#SortableInterestLevel2').html() + up );
    }

    $('#add_int2_msg').html(st);
}

function SuccessEvent_NewInterestAdded_L3(data)
{
    var params = data.split("№", 3); // № is a special splitter character (Russian)

    var id = params[0]; // item id

    var st = params[1]; // status message

    var up = params[2]; // updated content

    if (trimString(id) == "already_exists")
    {

    }
    else
    {
        $('#SortableInterestLevel3').html( $('#SortableInterestLevel3').html() + up );
    }

    $('#add_int3_msg').html(st);
}


function SuccessEvent_DeleteInterest(data)
{
    var id = trimString(data);//params[0]; // item id
    $('#sil1cont_' + id).fadeOut();
    preventUpdateAfterDelete = 0; // we can select interests again
}

function SuccessEvent_DeleteInterest_L2(data)
{
    var id = trimString(data);//params[0]; // item id
    $('#sil2cont_' + id).fadeOut();
    preventUpdateAfterDelete = 0; // we can select interests again
}

function SuccessEvent_DeleteInterest_L3(data)
{
    var id = trimString(data);//params[0]; // item id
    $('#sil3cont_' + id).fadeOut();
    preventUpdateAfterDelete = 0; // we can select interests again
}


//homepage/feed autosuggest

function SuccessEvent_AutoSuggestInt(data)
{
    $('#intas').css('display', 'block');
    //$('#intas').fadeIn();
    //$('#intl1closebutton').css("display", "block");
    $('#intas').html(data);
    $('#FindInterestClear').fadeIn(1000);
}




function SuccessEvent_AutoSuggest(data)
{
    $('#add_int_l1_autosuggest').css('display', 'block');
    $('#intl1closebutton').css("display", "block");
    $('#add_int_l1_autosuggest').html(data);
}

function SuccessEvent_AutoSuggest_L2(data)
{
    $('#add_int_l2_autosuggest').css('display', 'block');
    $('#intl2closebutton').css("display", "block");
    $('#add_int_l2_autosuggest').html(data);
}

function SuccessEvent_AutoSuggest_L3(data)
{
    $('#add_int_l3_autosuggest').css('display', 'block');
    $('#intl3closebutton').css("display", "block");
    $('#add_int_l3_autosuggest').html(data);
}

function SuccessEvent_LoadLevel2Interests(data)
{
    $('#Level2InterestContainer').html(data);
    // We need to re-establish sortable properties, after we html the new UL block in
    if ($(".sortable").sortable != undefined) {
        $(".sortable").sortable();
        attach_interest_event_l2(); // re-attach update event
    }
}

function SuccessEvent_LoadLevel3Interests(data)
{
    $('#Level3InterestContainer').html(data);
    // We need to re-establish sortable properties, after we html the new UL block in
    if ($(".sortable").sortable != undefined) {
        $(".sortable").sortable();
        attach_interest_event_l3(); // re-attach update event
    }
}

function SuccessEvent_SaveNewInterestOrder(data) { }

function SuccessEvent_DeleteFeedTab(data)
{
    //alert(data);
    var chantab = '#MyChannelNumber' + trimString(data);
    //alert(chantab);
    $(chantab).fadeOut();
}

function SuccessEvent_FeedSuggested(data)
{
    $('#suggestSubmit').fadeOut(500);
    $('#feedsmsg').html(data);
}

function SuccessEvent_FeedAutosuggest(data)
{
    $('#feedsuggestions').css('display','block');
    $('#feedsuggestions').html(data);
}

function SuccessEvent_AddFeedTab(data)
{
    if (isnumeric(trimString(data)))
    {
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#D0BBB3');}, 50);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#FFFFFF');}, 100);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#D0BBB3');}, 150);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#FFFFFF');}, 200);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#D0BBB3');}, 250);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#FFFFFF');}, 300);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').css('backgroundColor', '#D0BBB3');}, 350);
        setTimeout(function(){$('#feed_' + trimString(data) + '_tab').animate({backgroundColor: '#FFFFFF'}, 400);}, 400);
    }
    else
    {
        $('#MainFeedsView').html($('#MainFeedsView').html() + data);
    }
}

function SuccessEvent_SaveProfileInfo(data)
{
    //alert('Changes have been saved!');
    $('#profile_save_status').html('Changes have been saved!');
}

function SuccessEvent_SelectFeed(data)
{
    var params = data.split("№", 4); // № is a special splitter character (Russian)

    CommunityID = params[0]; // community id

    var audience = params[1]; // audience count

    var feed = params[2]; // data

    var feedname = params[3]; // feed name

    if (feedname == '')
        $('.AllChannelNameTags').text('this');
    else
        $('.AllChannelNameTags').text(feedname);

    $('#audiencenum').html(audience);

    $('#main_feed').html(feed);

    //alert('ok');

    $('#feed_loading_icon').fadeOut(500, function(){ $('#community_icon').fadeIn(); });

    //reset_the_clock();

    //ajax.httpExecute(SuccessEvent_GetAuthorityList, GETAUTHORITYLIST, [CommunityID]);

    //ReloadFeed(); //Let's do it again

    ///ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);
}

function reset_the_clock()
{
    canvas.width = 32;
    canvas.height = 32;
    resetclock = 0;
    //resetclock
    context.beginPath();
    context.strokeStyle = "#AAAAAA";
    context.lineWidth = 1;
    context.moveTo((dia/2)+Math.cos(angle)*dia/2,(dia/2)+Math.sin(angle)*dia/2);
    for (var i=0;i<100;i++) {
        angle += 0.1;
        context.lineTo((dia/2)+Math.cos(angle)*dia/2.2,(dia/2)+Math.sin(angle)*dia/2.2);
    }
    context.stroke();
    context.beginPath();
    angle = 0.0;
    context.moveTo((dia/2)+Math.cos(angle)*dia/2,(dia/2)+Math.sin(angle)*dia/2);
}

function SuccessEvent_ChangeGender(data)
{
    $('#gender_change').html(data);
}

function SuccessEvent_AddToFriends(data)
{
    $('#MenuFriend').html(data);
}

function SuccessEvent_SendFriendRequest(data)
{
    $('#MenuFriend').html(data);
}

function SuccessEvent_RemoveFromFriends(data)
{
    $('#MenuFriend').html(data);
}

function SuccessEvent_AcceptFriend(data)
{
    $('#frreqbox' + data).html('Accepted');
}

function SuccessEvent_DeclineFriend(data)
{
    $('#frreqbox' + data).html('Ok');
}

function SuccessEvent_Unmute(data)
{
    $('#muted_' + trimString(data)).fadeOut();
}

function SuccessEvent_Mute(data)
{
    $('#MenuMuteFriend').html('<span style = \'color:gray\'>Muted</span>');
}

function SuccessEvent_DeleteMailMessage(data)
{
    $('#inbox_msg_' + trimString(data)).fadeOut(500);
}

function SuccessEvent_RespondToMail(data)
{
    var params = data.split("№", 2); // № is a special splitter character (Russian)

    var data1 = params[0]; // id

    var data2 = params[1]; // data

    $('#just_posted').html($('#just_posted').html() + data2);

    document.getElementById('send_msg_button').disabled = false;

    document.getElementById('msgbox').value = '';
}

function SuccessEvent_ChangeReadStatus(data)
{
    var params = data.split("№", 3); // № is a special splitter character (Russian)

    var data1 = params[0]; // msg id

    var datax = params[1]; // unread message count

    var data2 = params[2]; // data

    $('#SuperContainer' + data1).html(data2);

    //$('#UnreadMessageCount').html(datax);

    //document.getElementById('send_msg_button').disabled = false;

    //document.getElementById('msgbox').value = '';
}

function SuccessEvent_SendMessage(data)
{
    document.getElementById('sndmsgbtn').disabled = false;

    redirect(getBaseURL() + 'Inbox');
}

function SuccessEvent_GetFeedStats(data)
{
    var params = data.split("№", 3); // № is a special splitter character (Russian)

    var data1 = params[0]; // messages posted

    var data2 = params[1]; // messages liked

    var data3 = params[2]; // percentage liked

    $('#mystats').html('');

    //$('#mystats').html('Out of <b id = "stats_msg">' + data1 + '</b> messages you posted in this feed <b id = "stats_percent">%' + data3 + '</b> (' + data2 + ') of them were <img src = "Images/AgreeSmaller.png" align = "top"/> liked.');
}

function SuccessEvent_GetAuthorityList(data)
{
    //alert(data;

    ajax.httpExecute(SuccessEvent_GetFeedStats, GETFEEDSTATS, [CommunityID]);

    $('#AuthorativeUsers').html(data);
}

function SuccessEvent_SendEmailInvitation(data)
{
    //alert(InviteEmailArray);

    //var params = data.split("№", 3); // № is a special splitter character (Russian)

    //var index = params[0]; // index of invite that was just sent

    //var inviter_id = params[1];

    //var result = params[2]; // index of invite that was just sent

    //alert('TotalInvitations=' + TotalInvitations);

    CurrentInvitation++;

    if (CurrentInvitation == TotalInvitations)
    {
        //reset
        CurrentInvitation = 0;
    }
    else
    {
        if (data == 'Sent')        
            $('#LoadingIcon' + (CurrentInvitation - 1)).html('<div style = "position: absolute;width:50px;height:16px;left:12px;"> <img src = "http://www.authenticsociety.com/Images/Check.png" align = "top"> Sent</div>');

        ajax.httpExecute(SuccessEvent_SendEmailInvitation, SENDEMAILINVITATION, [InviteNameArray[CurrentInvitation],InviteEmailArray[CurrentInvitation]]);
    }
}

var AlertTimer = null;

function SuccessEvent_GetAlerts(data)
{
    var params = data.split("№", 3); // № is a special splitter character (Russian)

    var mail = params[0];

    var reqs = params[1];

    var msgalerts = params[2];

    if (mail == 1)
    {
        $('#AlertBox1').html(msgalerts);
        haha();
    }

    if (reqs == 1)
    {
        if (mail == 1)
        {
            setTimeout(function(){hahaha();},2000); // if there is a previous alert
        }
        else
        {
            hahaha();
        }
    }

    if (mail == 1 || reqs == 1)
    {
        clearTimeout(AlertTimer);
        AlertTimer = null;
    }
}