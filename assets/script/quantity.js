function increaseValue(button, limit, event) {
    event.preventDefault();
    const numberInput = button.parentElement.querySelector('.number');
    var value = parseInt(numberInput.innerHTML, 10);
    if(isNaN(value)) value = 0;
    if(limit && value >= limit) return;
    numberInput.innerHTML = value+1;
  }
  
  
  function decreaseValue(button, event) {
    event.preventDefault();
    const numberInput = button.parentElement.querySelector('.number');
    var value = parseInt(numberInput.innerHTML, 10);
    if(isNaN(value)) value = 0;  
    if(value < 1) return;
    numberInput.innerHTML = value-1;
  }