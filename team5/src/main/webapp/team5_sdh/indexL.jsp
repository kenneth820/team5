<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인화면</title>
</head>
<body onload="loaded()" onkeydown="keydown()" onkeyup="keyup()">
<div style="flex: 80%;" >
   <canvas width="884px"height="909px" id="c1"></canvas>
   <div>
            <script>
    
           
           var context; //화가 객체
    
           // 플레이어 이동 방향과 속도
           var dx=0;
           var dy=0;
    
           // 키 이벤트로 인해 인식된 keycode변수
           var keycode;
    
           // 전역변수로서 이미지 객체 생성
           var imgChar= new Image();
           imgChar.src="./ms18.png";
           var imgBg= new Image();
           imgBg.src="./bg4.png";
    		
           // 플레이어 캐릭터의 중심좌표
           var x=800, y=850; //캐릭터의 위치좌표
           var w=50, h=50; //플레이어 이미지의 절반 사이즈
    
           function loaded(){
               canvas= document.getElementById('c1');
               context= canvas.getContext('2d');
    
               runGame(); //게임을 진행하는 함수
               //10ms 마다 runGame()를 다시 호출
               setInterval(runGame,10); //1초에 100번 호출
           }
    
           function runGame(){
               moveAll(); //캐릭터 움직이기
               drawAll(); // 이미지들 그리기
           }
    
           function moveAll(){
               //플레이어의 좌표 변경
               x+=dx;
               y+=dy;
           }
           function drawAll(){
               //배경 그리기
               context.drawImage(imgBg,0,0,884,909);
               //context.drawImage(imgBg,0,0,800,500);
               //플레이어 그리기
               context.drawImage(imgChar,x-w,y-h,w*2,h*2);
/* 	           console.log(x);
	           console.log(y); 
     */
               // 키 코드값 글씨 그리기
               context.fillStyle="white";
               context.font="30px sans-serif";
               context.fillText(keycode, 10, 40);
           }
    
           function keydown(){
               //눌러진 key의 코드값
               keycode=event.keyCode;
               switch(keycode){
                   case 37: dx=-5; break; //left
                   case 38: dy=-5; break; //up
                   case 39: dx=5; break; //right
                   case 40: dy=5; break; //down
               }
           }
           function keyup(){
               //떨어진 key의 코드값
               keycode=event.keyCode;
               switch(keycode){
                   case 37: 
                   case 39: dx=0; break;
                   case 38:
                   case 40: dy=0; break;
               }
           }
           // 사운드 작업 : 크롬은 자동 재생을 막은 듯 함. Edge는 될 것임
           /* var sd= new Audio('./dragon_flight.mp3');
           sd.volume= 1.0; //0.0 ~ 1.0
           sd.loof= true; //반복재생여부
           sd.play(); */

		</script>

   </div>
</div>
</body>
</html>