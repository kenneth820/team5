<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
     var canvas = document.getElementById("cv1");
        var ctx = canvas.getContext("2d");
        var readout = document.getElementById('readout');

        //캔버스 좌표----------------------------------------------------------
        function windowToCanvas(canvas, x, y) {
            var bbox = canvas.getBoundingClientRect(); //viewport 기준으로 나의 위치 알려줌
            return {
                x: x - bbox.left * (canvas.width / bbox.width),
                y: y - bbox.top * (canvas.height / bbox.height)
            };
        }

        //바탕 회색줄-----------------------------------------------------------
        function drawBackground() { //바탕에 회색 줄 그리기
            var VERTICAL_LINE_SPACING = 12; //줄 간격
            var i = ctx.canvas.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.strokeStyle = "lightgray";
            ctx.lineWidth = 0.5;
            while (i > VERTICAL_LINE_SPACING * 4) {
                ctx.beginPath();
                ctx.moveTo(0, i);
                ctx.lineTo(ctx.canvas.width, i);
                ctx.stroke();
                i -= VERTICAL_LINE_SPACING;
            }
        }

        //가이드라인-----------------------------------------------------------
        function drawGuidelines(x, y) {
            ctx.strokeStyle = 'rgba(0, 0, 230, 0.8)';
            ctx.lineWidth = 0.5;
            drawVerticalLine(x);
            drawHorizontalLine(y);
        }

        function updateReadout(x, y) { //div 부분에 좌표 입력(readout)
            readout.innerText = '(' + x.toFixe d(0) + ',' + y.toFixed(0) + ')';
        }

        //파란선---------------------------------------------------------------
        function drawHorizontalLine(y) { //마우스 있는 곳에 가로 선 그리기(파랑)
            ctx.beginPath();
            ctx.moveTo(0, y + 0.5);
            ctx.lineTo(ctx.canvas.width, y + 0.5);
            ctx.stroke();
        }
        //세로선---------------------------------------------------------------
        function drawVerticalLine(x) {
            ctx.beginPath();
            ctx.moveTo(x + 0.5, 0);
            ctx.lineTo(x + 0.5, ctx.canvas.height);
            ctx.stroke();
        }

        //함수실행(마우스가 canvas위에 있을때만)-----------------------------------------
        canvas.onmousemove = function (e) {    //마우스가 canvas 위에 있을 때 함수 실행

            var loc = windowToCanvas(canvas, e.clientX, e.clientY);
            //e.clientX: 마우스의 x좌표값, e.clicentY: 마우스 y좌표값
            drawBackground();
            drawGuidelines(loc.x, loc.y);
            updateReadout(loc.x, loc.y);

        };
</script>
</body>
</html>