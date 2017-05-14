var bodyBgs = [];
bodyBgs[0] = "images/bg1.jpg";
bodyBgs[1] = "images/bg2.jpg";
bodyBgs[2] = "images/bg3.jpg";
bodyBgs[3] = "images/bg4.jpeg";

function changeBg() {
    var randomBgIndex = Math.round( Math.random() * 3 );
    var realBg = "url(" + bodyBgs[randomBgIndex] +")";
    document.body.style.backgroundImage=realBg; //改变背景图片
};

setInterval("changeBg();", 10000);
