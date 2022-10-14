<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<style>
	header {
		height:250px;
		background:;
	}
	main{
		height:1100px;	
		background:;
	}
	

	</style>
<body>
	<button data-toggle-id="subscribe-mail">
  구독 폼 보여주기
</button>

<form id="subscribe-mail" hidden>
		    <main> 

			
		    <table border>
	    	
			    	<tr>
			    		<td><img src="Small1.jpg" width=135  height=230></td>
			    		<td><img src="Small2.jpg" width=135  height=230></td>
			    		<td><img src="Small3.jpg" width=135  height=230></td>
			    		
			    	</tr>
			    	
			    	<tr align=center>
			    		<td>사진1</td>
			    		<td>사진2</td>
			    		<td>사진3</td>
			    	
			    	</tr>
			    
			    	<tr>
			    		<td><img src="Small5.jpg" width=135  height=230></td>
			    		<td><img src="Small6.jpg" width=135  height=230></td>
			    		<td><img src="Small7.jpg" width=135  height=230></td>
	 		
			    	</tr>
			    	<tr align=center>
			    		<td>사진4</td>
			    		<td>사진5</td>
			    		<td>사진6</td>
			    	</tr>
			
			    	<tr>
			    		<td><img src="Small8.jpg" width=135  height=230></td>
			    		<td><img src="Small4.jpg" width=135  height=230></td>
			    		<td><img src="Small9.jpg" width=135  height=230></td>
			    		
			    	</tr>
			    	<tr align=center>
			    		<td>사진7</td>
			    		<td>사진8</td>
			    		<td>사진9</td>
			    	
			    	</tr>
		    	</table>
		    	
		    </main>

</form>

<script>
  document.addEventListener('click', function(event) {
    let id = event.target.dataset.toggleId;
    if (!id) return;

    let elem = document.getElementById(id);

    elem.hidden = !elem.hidden;
  });
</script>
	
</body>
</html>

