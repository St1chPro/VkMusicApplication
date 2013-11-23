package logic;

import exceptions.ParserException;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 20.10.13
 * Time: 20:49
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */
public class Parser
{
    private String parseString;

    public Parser(String parseString)
    {
        this.parseString = parseString;
    }

    //вытаскивает ip_h, to и origin
    public String parseAuthorizationContent(String name) throws ParserException
    {
        String value;

        Pattern regex = Pattern.compile(".*" + name + "\"\\s+value=\"([^\"]*)\".*");
        Matcher regexMatcher = regex.matcher(parseString);
        regexMatcher.matches();
        value = regexMatcher.group(1);

        if(value == null)
        {
            throw new ParserException("Value == null. Something wrong in parseAuthorizationContent method");
        }
        return value;
    }

    //вытаскивает action
    public String parseActionRedirect(String action) throws ParserException
    {
        //<?xml version="1.0" encoding="utf-8"?><!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"><html xmlns="http://www.w3.org/1999/xhtml" ><head><meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" /><meta http-equiv="content-type" content="text/html; charset=utf-8" /><meta name="format-detection" content="telephone=no" /><meta http-equiv="X-UA-Compatible" content="IE=edge" /><meta name="MobileOptimized" content="176" /><meta name="HandheldFriendly" content="True" /><base id="base"><meta name="robots" content="noindex,nofollow" /><title>Отримання доступу до ВКонтакті</title><script type="text/javascript"><!--(function(k,a,d,e,f){function l(){var c=function(){var b=!1;try{b=new XMLHttpRequest}catch(a){try{b=new ActiveXObject("Msxml2.XMLHTTP")}catch(d){try{b=new ActiveXObject("Microsoft.XMLHTTP")}catch(c){b=!1}}}return b?e:f}(),g=function(){var b=k.createElement("div");b.innerHTML='<input type="file" />';b=b.getElementsByTagName("input")[0];return"file"!=b.type||b.disabled?f:e}(),h="ontouchstart"in a?e:f,m;try{m=typeof navigator.geolocation!==d?e:typeof a.google!==d&&typeof google.gears!==d?e:typeof device!==d&&typeof device.getServiceObject!==d?e:typeof Mojo!==d&&"Mojo.Service.Request"!==typeof Mojo.Service.Request?e:f}catch(p){m=f}var l=function(b){try{var d=a.Audio?new Audio:k.createElement("audio");if(d.canPlayType&&d.canPlayType(b)&&"no"!=d.canPlayType(b))return e}catch(c){}return f}("audio/mpeg"),n=function(b){try{for(var a=k.createElement("div"),c=0,g=["webkit","Moz","ms","O",""],h=g.length;c<h;c++){var l=g[c],m=l?l+b:b.toLowerCase();if(typeof a.style[m]!==d)return e}return f}catch(n){return f}}("Transform");return c+g+h+m+l+n+((a.XMLHttpRequest||a.XDomainRequest)&&(a.FormData||a.FileReader&&(a.XMLHttpRequest&&XMLHttpRequest.sendAsBinary||a.ArrayBuffer&&a.Uint8Array&&(a.MozBlobBuilder||a.WebKitBlobBuilder||a.BlobBuilder)))?e:f)}var c=a.screen,g=c.width||0,c=c.height||0,n=a.devicePixelRatio||1,p=(k.cookie.match(/(^|;\s+)remixmdevice=([^;]+)/)||[])[2]||"",h=p.split("/");p&&g==h[0]&&c==h[1]&&7==h[3].length||(g=[g,c,n,l()].join("/"),k.cookie="remixmdevice=; expires="+(new Date(0)).toUTCString()+"; path=/",k.cookie="remixmdevice="+g+"; expires="+(new Date((new Date).getTime()+7776E6)).toUTCString()+"; path=/; domain=.vk.com",location.replace(location.toString()))})(document,window,"undefined","!","-");(function(a,d){var c=a.hash||"",b=c.substr(2);"#/"==c.substr(0,2)&&!d&&(b.match(/^\/*(away|login.css)(\.php)?([^a-z0-9\.]|$)/)&&(b=""),a.replace(a.protocol+"//"+a.host+"/"+b))})(location,1);//--></script><link type="text/css" rel="stylesheet" href="https://m.vk.com/css/s_c.css?95"></link><link type="text/css" rel="stylesheet" media="only screen" href="https://m.vk.com/css/s_gyz.css?98"></link><link rel="shortcut icon" href="/images/faviconnew.ico"></link></head><body id="vk" class="_hover"><div id="vk_utils"></div><div id="vk_head" class="mhead"><div class="hb_wrap"><div class="hb_btn">&nbsp;</div></div></div><div id="vk_wrap"><div id="l"></div><div id="m"><div id="mhead" class="mhead"><a href="/oauth/logout?hash=d89a7f254253003727&success_url=Y2xpZW50X2lkPTM5NDI0NjAmcmVkaXJlY3RfdXJpPWh0dHBzJTNBJTJGJTJGb2F1dGgudmsuY29tJTJGYmxhbmsuaHRtbCZyZXNwb25zZV90eXBlPXRva2VuJnNjb3BlPTgmdj01LjImc3RhdGU9JmRpc3BsYXk9cGFnZQ--&success_hash=4da3dfea0109252ba8" class="hb_wrap mhb_notify" accesskey="#"><div class="hb_btn"><em class="mh_btn_label">вийти</em></div></a><a href="https://m.vk.com/" accesskey="*" class="hb_wrap mhb_logo"><div class="hb_btn mhi_logo">&nbsp;</div><h1 class="hb_btn mh_header">&nbsp;</h1></a></div><div id="mcont" class="mcont"><div class="pcont fit_box"><a href="https://m.vk.com/id197420456" class="owner_panel"><div class="op_inline_user op_inline_user_link"><img src="https://pp.vk.me/c314624/v314624456/2b83/VUSfzeH_ed0.jpg" class="op_img" /><h2 class="op_header"><span>Александр Онуфриев</span></h2></div></a><div class="form_item"><div class="fi_row">Додаток <b><a href="http://vk.com/app3942460" target="_blank">Vk Music Downloader</a></b> запитує доступ до Вашого акаунту.</div><div class="fi_row"><div class="apps_access_item clear_fix"><div class="apps_access_icon apps_access_profile"></div><div class="fl_l apps_access_item_info"><b>Доступ до загальної інформації</b>Додатку будуть доступні Ваші особисті дані</div></div><div class="apps_access_item clear_fix"><div class="apps_access_icon apps_access_media"></div><div class="fl_l apps_access_item_info"><b>Доступ до аудіозаписів</b>Додатку будуть доступні Ваші аудіозаписи</div></div></div><form method="post" action="https://login.css.vk.com/?act=grant_access&client_id=3942460&settings=8&redirect_uri=https%3A%2F%2Foauth.vk.com%2Fblank.html&response_type=token&direct_hash=e827ca5925f5265553&token_type=0&v=5.2&state=&display=page&ip_h=0d3ce8e2e21950d1fe&hash=bb74c179796e59e3d4&https=1"><div class="fi_row"><input class="button" type="submit" value="Дозволити" /><div class="near_btn"><a href="https://login.css.vk.com/?act=grant_access&client_id=3942460&settings=8&redirect_uri=https%3A%2F%2Foauth.vk.com%2Fblank.html&response_type=token&direct_hash=e827ca5925f5265553&token_type=0&v=5.2&state=&display=page&ip_h=0d3ce8e2e21950d1fe&hash=bb74c179796e59e3d4&https=1&cancel=1">Скасувати</a></div></div></form></div></div></div><div id="mfoot" class="mfoot"><div class="pfoot"><ul class="footer_menu"><li class="fm_row"><a class="fm_item" href="https://m.vk.com/settings?act=change_regional&from=oauth&to=YXV0aG9yaXplP2NsaWVudF9pZD0zOTQyNDYwJnJlZGlyZWN0X3VyaT1odHRwcyUzQSUyRiUyRm9hdXRoLnZrLmNvbSUyRmJsYW5rLmh0bWwmcmVzcG9uc2VfdHlwZT10b2tlbiZzY29wZT04JnY9NS4yJnN0YXRlPSZkaXNwbGF5PXBhZ2U-&hash=a82490cd70db573757&lang_id=0">Русский</a></li><li class="fm_row"><a class="fm_item" href="https://m.vk.com/settings?act=change_regional&from=oauth&to=YXV0aG9yaXplP2NsaWVudF9pZD0zOTQyNDYwJnJlZGlyZWN0X3VyaT1odHRwcyUzQSUyRiUyRm9hdXRoLnZrLmNvbSUyRmJsYW5rLmh0bWwmcmVzcG9uc2VfdHlwZT10b2tlbiZzY29wZT04JnY9NS4yJnN0YXRlPSZkaXNwbGF5PXBhZ2U-&hash=a82490cd70db573757&lang_id=3">English</a></li><li class="fm_row"><a class="fm_item" href="https://m.vk.com/settings?from=oauth&to=YXV0aG9yaXplP2NsaWVudF9pZD0zOTQyNDYwJnJlZGlyZWN0X3VyaT1odHRwcyUzQSUyRiUyRm9hdXRoLnZrLmNvbSUyRmJsYW5rLmh0bWwmcmVzcG9uc2VfdHlwZT10b2tlbiZzY29wZT04JnY9NS4yJnN0YXRlPSZkaXNwbGF5PXBhZ2U-">all languages »</a></li></ul></div></div></div><div id="z"></div></div><div id="vk_bottom"></div><script type="text/javascript"><!--parent&&parent!=window&&(document.getElementsByTagName('body')[0].innerHTML='');//--></script></body></html>
        String value;

        Pattern regex = Pattern.compile(".*" + action + "=\"([^\"]*)\".*");
        Matcher regexMatcher = regex.matcher(parseString);
        regexMatcher.matches();
        value = regexMatcher.group(1);

        if(value == null)
        {
            throw new ParserException("Value == null. Something wrong in parseActionRedirect method");
        }

        return value;
    }

    //вытаскивает access_token, userid, expires_in
    public String parseAccessTokenContent(String name) throws ParserException
    {
        // https://oauth.vk.com/blank.html#access_token=590cd66feebb9a13a2480f7238e8b9f8bdcd19ac13ae4d74cafc133b7f857230b1e56a84c6bba95f9ea3c&expires_in=86400&user_id=17303521
        String value;

        Pattern regex = Pattern.compile(".*" + name + "=([^&]*).*");
        Matcher regexMatcher = regex.matcher(parseString);
        regexMatcher.matches();
        value = regexMatcher.group(1);

        if(value == null)
        {
            throw new ParserException("Value == null. Something wrong in parseAccessTokenContent method");
        }
        return value;
    }
}
