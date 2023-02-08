const min_price = document.querySelector('#min_price');
const max_price = document.querySelector('#max_price');
const min_box = document.querySelector('#min_box');
const max_box = document.querySelector('#max_box');

min_price.addEventListener('mousedown',()=>{
    min_price.addEventListener('mousemove',(e)=>{

        let min = min_price.value * 1000;
        let max = max_price.value * 1000;
        if (min<max){
            min_box.value = min.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

        }else if(min>=max){
            min_price.removeEventListener('mousedown',()=>{});
            min_price.value = max_price.value - 1;
            min_box.value = min.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

        }

        
    })
});

max_price.addEventListener('mousedown',()=> {
    max_price.addEventListener('mousemove', () => {
        let min = min_price.value * 1000;
        let max = max_price.value * 1000;
        if (max>min) {
            max_box.value = max.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        }else if(max<=min){
            max_price.removeEventListener('mousedown',()=>{});

            max_price.value = min_price.value;
            max_box.value = max.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            min_price.value = max_price.value - 1;
            min_box.value = min.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

        }
    })
});



