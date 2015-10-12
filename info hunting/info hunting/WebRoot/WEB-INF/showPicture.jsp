
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.xmlbean" %>
<%@ page import="java.util.ArrayList" %>
 
<%String url=(String)request.getAttribute("url");


  url=new String(url.getBytes("ISO-8859-1"),"gb2312");
  

   %>  
<html>
<head>

        <title>图片预览</title>
		<meta charset="UTF-8" />
        
        <link rel="stylesheet" type="text/css" href="css/style.css" />
		<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css' />
		<link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css' />
    <style>
html,body{
margin:0;
padding:0;
}
#head{
width:100%;
background:#f6f6f6;
height:115px;
}

#navigation{
	position:relative; bottom:-15px; left:40px;
}
.cl{
	
    width:1px;
    padding:4px;
    background-color:#FFFFFF;
}
.c1 input{
    font-size:18px;
    height:28px;
}
#condition{

float:left;
padding: 25px;
background:f6f6f6;
width:200px;

}
#condition p {
margin: 0;
padding: 0;
padding-bottom: 15px;
}
#condition h2 {
margin: 0;
padding: 0;
}

#content{
margin-left:200px;
padding: 25px;
background:#f6f6f6;
}
#content p {
margin: 0;
padding: 0;
padding-bottom: 15px;
}
#content h2 {
margin: 0;
padding: 0;
}

#end{
clear: both;
background:#f6f6f6;
height:200px;
}
#end_1{
	height:80px;
	background:#E0FFFF;
}
#end_2{
	height:100px;
}
.style1 {font-size: large}
</style>

</head>
  
    <body bgcolor=#f6f6f6>
		  		<script>
					function subFrm(){
						document.frm.submit();
					}
				</script>

<div id="head">

	<div align="right">
		<a  href="http://css.jorux.com/wp-admin/post.php#" >注册</a>&nbsp;
		<a  href="http://css.jorux.com/wp-admin/post.php#" >登陆</a>&nbsp;</div>
	<div id="navigation">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <b>全部</b>
		            <a href="wendang.jsp">文档</a>          
                    <a href="tupian.jsp" >图片</a>
                    <a href="shipin.jsp">视频</a>
                    <a href="yinyue.jsp">音乐</a>
                    <a href="upload.jsp">上传</a>
  </div>
		<br>
		<form   action="search"  method="post" >
		<table width="867" border="0" cellspacing="0">

  <tr>
    <th width="190" height="49" scope="col">&nbsp;</th>
    <th width="540" scope="col">
	<div  align="left" class=c1>

	<input name="key"  type="text"  size="30" maxlength="20">
	<input type="submit" value="search" width="60px" height="40px" onClick="javascript:fsubmit(document.frm);return false;" >&nbsp;
	</div>
	<br>
	<div align="left">The time of this search is <s:property value="#request.time"/> s.找到<s:property value="#request.num"/>条
	</div>
	
	</th>
    </table>
</form>		
</div>

<s:set name="onelist" value="#session.onelist1"/>  
<s:subset source="#onelist" start="#request.star" count="#request.sizes">        
   <s:if test="#onelist==null||#onelist.size()==0">
      <tr height="30"><td align="center" style="border:1 solid">★★★ sorry,no result is found!★★★</td></tr>
   </s:if>
   <s:else> 
   <br>&nbsp;&nbsp;&nbsp;&nbsp;           
    <s:iterator status="oneStatus" >
   <s:if test="#oneStatus.odd"><tr height="23"></s:if>

	


		<div class="content">
			
			<div class="iw_wrapper">
				<ul class="iw_thumbs" id="iw_thumbs">
					<li><img src=getUrl() data-img=getUrl() alt="Thumb1"/><div><h2>Serenity</h2><p>Far far away, behind the word mountains there live the blind texts.</p></div></li>
					
				</ul>
			</div>
			<div id="iw_ribbon" class="iw_ribbon">
				<span class="iw_close"></span>
				<span class="iw_zoom">Click thumb to zoom</span>
			</div>
		</div>
		   <s:if test="#oneStatus.even"></s:if>
   </s:iterator>
      </s:else> 
   </s:subset>  
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript">
			$(window).load(function(){
				var $iw_thumbs			= $('#iw_thumbs'),
					$iw_ribbon			= $('#iw_ribbon'),
					$iw_ribbon_close	= $iw_ribbon.children('span.iw_close'),
					$iw_ribbon_zoom		= $iw_ribbon.children('span.iw_zoom');
					
					ImageWall	= (function() {
							// window width and height
						var w_dim,
						    // index of current image
							current				= -1,
							isRibbonShown		= false,
							isFullMode			= false,
							// ribbon / images animation settings
							ribbonAnim			= {speed : 500, easing : 'easeOutExpo'},
							imgAnim				= {speed : 400, easing : 'jswing'},
							// init function : call masonry, calculate window dimentions, initialize some events
							init				= function() {
								$iw_thumbs.imagesLoaded(function(){
									$iw_thumbs.masonry({
										isAnimated	: true
									});
								});
								getWindowsDim();
								initEventsHandler();
							},
							// calculate window dimentions
							getWindowsDim		= function() {
								w_dim = {
									width	: $(window).width(),
									height	: $(window).height()
								};
							},
							// initialize some events
							initEventsHandler	= function() {
								
								// click on a image
								$iw_thumbs.delegate('li', 'click', function() {
									if($iw_ribbon.is(':animated')) return false;
									
									var $el = $(this);
									
									if($el.data('ribbon')) {
										showFullImage($el);
									}
									else if(!isRibbonShown) {
										isRibbonShown = true;
										
										$el.data('ribbon',true);
										
										// set the current
										current = $el.index();
									
										showRibbon($el);
									}
								});
								
								// click ribbon close
								$iw_ribbon_close.bind('click', closeRibbon);
								
								// on window resize we need to recalculate the window dimentions
								$(window).bind('resize', function() {
											getWindowsDim();
											if($iw_ribbon.is(':animated'))
												return false;
											closeRibbon();
										 })
								         .bind('scroll', function() {
											if($iw_ribbon.is(':animated'))
												return false;
											closeRibbon();
										 });
								
							},
							showRibbon			= function($el) {
								var	$img	= $el.children('img'),
									$descrp	= $img.next();
								
								// fadeOut all the other images
								$iw_thumbs.children('li').not($el).animate({opacity : 0.2}, imgAnim.speed);
								
								// increase the image z-index, and set the height to 100px (default height)
								$img.css('z-index', 100)
									.data('originalHeight',$img.height())
									.stop()
									.animate({
										height 		: '100px'
									}, imgAnim.speed, imgAnim.easing);
								
								// the ribbon will animate from the left or right
								// depending on the position of the image
								var ribbonCssParam 		= {
										top	: $el.offset().top - $(window).scrollTop() - 6 + 'px'
									},
									descriptionCssParam,
									dir;
								
								if( $el.offset().left < (w_dim.width / 2) ) {
									dir = 'left';
									ribbonCssParam.left 	= 0;
									ribbonCssParam.right 	= 'auto';
								}
								else {
									dir = 'right';
									ribbonCssParam.right 	= 0;
									ribbonCssParam.left 	= 'auto';
								}
								
								$iw_ribbon.css(ribbonCssParam)
								          .show()
										  .stop()
										  .animate({width : '100%'}, ribbonAnim.speed, ribbonAnim.easing, function() {
												switch(dir) {
													case 'left' :
														descriptionCssParam		= {
															'left' 			: $img.outerWidth(true) + 'px',
															'text-align' 	: 'left'
														};
														break;
													case 'right' :	
														descriptionCssParam		= {
															'left' 			: '-200px',
															'text-align' 	: 'right'
														};
														break;
												};
												$descrp.css(descriptionCssParam).fadeIn();
												// show close button and zoom
												$iw_ribbon_close.show();
												$iw_ribbon_zoom.show();
										  });
								
							},
							// close the ribbon
							// when in full mode slides in the middle of the page
							// when not slides left
							closeRibbon			= function() {
								isRibbonShown 	= false
								
								$iw_ribbon_close.hide();
								$iw_ribbon_zoom.hide();
								
								if(!isFullMode) {
								
									// current wall image
									var $el	 		= $iw_thumbs.children('li').eq(current);
									
									resetWall($el);
									
									// slide out ribbon
									$iw_ribbon.stop()
											  .animate({width : '0%'}, ribbonAnim.speed, ribbonAnim.easing); 
										  
								}
								else {
									$iw_ribbon.stop().animate({
										opacity		: 0.8,
										height 		: '0px',
										marginTop	: w_dim.height/2 + 'px' // half of window height
									}, ribbonAnim.speed, function() {
										$iw_ribbon.css({
											'width'		: '0%',
											'height'	: '126px',
											'margin-top': '0px'
										}).children('img').remove();
									});
									
									isFullMode	= false;
								}
							},
							resetWall			= function($el) {
								var $img		= $el.children('img'),
									$descrp		= $img.next();
									
								$el.data('ribbon',false);
								
								// reset the image z-index and height
								$img.css('z-index',1).stop().animate({
									height 		: $img.data('originalHeight')
								}, imgAnim.speed,imgAnim.easing);
								
								// fadeOut the description
								$descrp.fadeOut();

								// fadeIn all the other images
								$iw_thumbs.children('li').not($el).animate({opacity : 1}, imgAnim.speed);								
							},
							showFullImage		= function($el) {
								isFullMode	= true;
								
								$iw_ribbon_close.hide();
								
								var	$img	= $el.children('img'),
									large	= $img.data('img'),
								
									// add a loading image on top of the image
									$loading = $('<span class="iw_loading"></span>');
								
								$el.append($loading);
								
								// preload large image
								$('<img/>').load(function() {
									var $largeImage	= $(this);
									
									$loading.remove();
									
									$iw_ribbon_zoom.hide();
									
									resizeImage($largeImage);
									
									// reset the current image in the wall
									resetWall($el);
									
									// animate ribbon in and out
									$iw_ribbon.stop().animate({
										opacity		: 1,
										height 		: '0px',
										marginTop	: '63px' // half of ribbons height
									}, ribbonAnim.speed, function() {
										// add the large image to the DOM
										$iw_ribbon.prepend($largeImage);
										
										$iw_ribbon_close.show();
										
										$iw_ribbon.animate({
											height 		: '100%',
											marginTop	: '0px',
											top			: '0px'
										}, ribbonAnim.speed);
									});
								}).attr('src',large);
									
							},
							resizeImage			= function($image) {
								var widthMargin		= 100,
									heightMargin 	= 100,
								
									windowH      	= w_dim.height - heightMargin,
									windowW      	= w_dim.width - widthMargin,
									theImage     	= new Image();
									
								theImage.src     	= $image.attr("src");
								
								var imgwidth     	= theImage.width,
									imgheight    	= theImage.height;

								if((imgwidth > windowW) || (imgheight > windowH)) {
									if(imgwidth > imgheight) {
										var newwidth 	= windowW,
											ratio 		= imgwidth / windowW,
											newheight 	= imgheight / ratio;
											
										theImage.height = newheight;
										theImage.width	= newwidth;
										
										if(newheight > windowH) {
											var newnewheight 	= windowH,
												newratio 		= newheight/windowH,
												newnewwidth 	= newwidth/newratio;
										
											theImage.width 		= newnewwidth;
											theImage.height		= newnewheight;
										}
									}
									else {
										var newheight 	= windowH,
											ratio 		= imgheight / windowH,
											newwidth 	= imgwidth / ratio;
										
										theImage.height = newheight;
										theImage.width	= newwidth;
										
										if(newwidth > windowW) {
											var newnewwidth 	= windowW,
											    newratio 		= newwidth/windowW,
												newnewheight 	= newheight/newratio;
									
											theImage.height 	= newnewheight;
											theImage.width		= newnewwidth;
										}
									}
								}
									
								$image.css({
									'width'			: theImage.width + 'px',
									'height'		: theImage.height + 'px',
									'margin-left'	: -theImage.width / 2 + 'px',
									'margin-top'	: -theImage.height / 2 + 'px'
								});							
							};
							
						return {init : init};	
					})();
				
				ImageWall.init();
			});
		</script>
    </body>
</html>