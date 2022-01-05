window.addEventListener("load", init);

function init() {
    canvas = document.getElementById("canvas");
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    ctx = canvas.getContext("2d");
    var amountOfBalls = 500;
    var amountOfOrbitingBalls = 5;
    var speed = 2;
    var balls = [];
    var orbitingBalls = [];
    for (var i = 0; i < amountOfBalls; i++) {
        var r = Math.floor(Math.random() * (80) + 20);
        var a = Math.floor(Math.random() * (window.innerWidth - 2 * r) + 2 * r);
        var b = Math.floor(Math.random() * (window.innerHeight - 2 * r) + 2 * r);
        var ball = {
            x: a,
            y: b,
            xDirection: Math.random() < 0.5 ? -1 : 1,
            xDirection: Math.random() < 0.5 ? -1 : 1,
            yDirection: 1,
            radius: r,
            xBorder: window.innerWidth - r,
            yBorder: window.innerHeight - r,
        };
        balls.push(ball);
    }
    var a = window.innerWidth / 2;
    var b = window.innerHeight / 2;
    if(window.innerHeight > window.innerWidth){
        var r = window.innerWidth / 2;
    }else{
        var r = window.innerHeight / 2;
    }

    for (var i = 0; i < amountOfOrbitingBalls; i++) {
        var orbitingBall = {
            x: a,
            y: b,
            xDirection: 1,
            yDirection: 1,
            radius: r,
            degree: 0,
            angularVelocity: i * i * (Math.random() < 0.5 ? -1 : 1),
        }
        orbitingBalls.push(orbitingBall);
        r /= 2;
    }
    var args = { balls: balls, orbitingBalls: orbitingBalls };
    setInterval(painter, speed, args);
}

function painter(arguments) {
    ctx.clearRect(0, 0, window.innerWidth, window.innerHeight);
    ctx.fillStyle = "black";
    ctx.fillRect(0, 0, window.innerWidth, window.innerHeight)
    createBalls(arguments.balls);
    createOrbitingBalls(arguments.orbitingBalls);
}

function createBalls(balls) {
    ctx.fillStyle = "rgba(15, 157, 240, 0.3)";
    for (var i = 0; i < balls.length; i++) {
        var ball = balls[i];
        var x = ball.x;
        var y = ball.y;
        var xBorder = ball.xBorder;
        var yBorder = ball.yBorder;
        if (y >= yBorder) {
            ball.yDirection = -1;
        } else if (y <= ball.radius) {
            ball.yDirection = 1;
        }
        if (x >= xBorder) {
            ball.xDirection = -1;
        } else if (x <= ball.radius) {
            ball.xDirection = 1;
        }
        ball.y += ball.yDirection;
        ball.x += ball.xDirection;
        ctx.beginPath();
        ctx.arc(x, y, ball.radius, 0, Math.PI * 2, true);
        ctx.closePath();
        ctx.fill();
    }

}

function createOrbitingBalls(balls) {
    var x = balls[0].x;
    var y = balls[0].y;
    var r = balls[0].radius;
    ctx.strokeStyle = "rgb(0, 255, 255)";
    ctx.lineWidth = 5;
    ctx.beginPath();
    ctx.arc(x, y, r, 0, Math.PI * 2, true);
    ctx.closePath();
    ctx.stroke();
    for (var i = 1; i < balls.length; i++) {
        balls[i].degree += balls[i].angularVelocity;
        var theta = ((balls[i].degree) * Math.PI) / 180;
        x = x + r * Math.cos(theta);
        y = y + r * Math.sin(theta);
        r = balls[i].radius;
        ctx.beginPath();
        ctx.arc(x, y, r, 0, Math.PI * 2, true);
        ctx.closePath();
        ctx.stroke();
    }
}

//Abandoned
function checkSmash(balls) {
    var d = Math.sqrt(Math.pow(balls[0].x - balls[1].x, 2) + Math.pow(balls[0].y - balls[1].y, 2));
    if (d <= balls[0].radius + balls[1].radius) {
        console.log("smashing");
        var x = balls[0].xDirection;
        var y = balls[0].yDirection;
        balls[0].xDirection = balls[1].xDirection;
        balls[0].yDirection = balls[1].yDirection;
        balls[1].xDirection = x;
        balls[1].yDirection = y;
    }
}
