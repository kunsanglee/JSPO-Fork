// const min_price = document.querySelector('#min_price');
// const max_price = document.querySelector('#max_price');
// const min_box = document.querySelector('#min_box');
// const max_box = document.querySelector('#max_box');
//
// min_price.addEventListener('mousedown',()=>{
//     min_price.addEventListener('mousemove',(e)=>{
//
//         let min = min_price.value * 1000;
//         let max = max_price.value * 1000;
//         if (min<max){
//             min_box.value = min.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
//
//         }else if(min>=max){
//             min_price.removeEventListener('mousedown',()=>{});
//             min_price.value = max_price.value - 1;
//             min_box.value = min.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
//
//         }
//
//
//     })
// });
//
// max_price.addEventListener('mousedown',()=> {
//     max_price.addEventListener('mousemove', () => {
//         let min = min_price.value * 1000;
//         let max = max_price.value * 1000;
//         if (max>min) {
//             max_box.value = max.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
//         }else if(max<=min){
//             max_price.removeEventListener('mousedown',()=>{});
//
//             max_price.value = min_price.value;
//             max_box.value = max.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
//             min_price.value = max_price.value - 1;
//             min_box.value = min.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
//
//         }
//     })
// });

const inputLeft = document.getElementById("input-left");
const inputRight = document.getElementById("input-right");

const thumbLeft = document.querySelector(".slider > .thumb.left");
const thumbRight = document.querySelector(".slider > .thumb.right");
const range = document.querySelector(".slider > .range");

const setLeftValue = () => {
    const _this = inputLeft;
    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

    // 교차되지 않게, 1을 빼준 건 완전히 겹치기보다는 어느 정도 간격을 남겨두기 위해.
    _this.value = Math.min(parseInt(_this.value), parseInt(inputRight.value) - 1);

    // input, thumb 같이 움직이도록
    const percent = ((_this.value - min) / (max - min)) * 100;
    thumbLeft.style.left = percent + "%";
    range.style.left = percent + "%";
};

const setRightValue = () => {
    const _this = inputRight;
    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

    // 교차되지 않게, 1을 더해준 건 완전히 겹치기보다는 어느 정도 간격을 남겨두기 위해.
    _this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 1);

    // input, thumb 같이 움직이도록
    const percent = ((_this.value - min) / (max - min)) * 100;
    thumbRight.style.right = 100 - percent + "%";
    range.style.right = 100 - percent + "%";
};


inputLeft.addEventListener("input", setLeftValue);
inputRight.addEventListener("input", setRightValue);

const outputleft = document.getElementById("left");
const outputright = document.getElementById("right");

inputLeft.oninput = function () {
    outputleft.innerHTML = (this.value * 5000).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "원";

}
inputRight.oninput = function () {
    outputright.innerHTML = (this.value * 5000).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "원";
}



